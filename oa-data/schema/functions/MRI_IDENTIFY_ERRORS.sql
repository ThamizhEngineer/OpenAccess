CREATE OR REPLACE FUNCTION OPENACCESS."MRI_IDENTIFY_ERRORS" 
(
  V_BATCH_ID IN VARCHAR2
) RETURN VARCHAR2 AS

v_mri_rec IMP_MR_LINES%ROWTYPE;
v_status varchar2(50);
v_reason varchar2(200);
v_exception_code  NUMBER;
v_exception_msg  VARCHAR2(250);
v_result varchar(300):='SUCCESS';
v_LOG_result varchar(300);
BEGIN

	--if final ReadingDate <> InitialReadingDate and if Day of FinalReadingDate is 01, then update reduce the FinalReadingDate by one
	UPDATE IMP_MR_LINES SET final_READING_DT= to_char(to_date(final_READING_DT,'yyyy-mm-dd') -1,'yyyy-mm-dd')
	WHERE IMP_MR_HEADER_ID =  V_BATCH_ID
	AND to_date(INIT_READING_DT,'yyyy-mm-dd') != to_date(final_READING_DT,'yyyy-mm-dd')
	AND SUBSTR(final_READING_DT,9,2) = '01';


	-- -- dbms_output.put_line('Date stripped');

	/*
	--Ignore any record older than May-2017 - Mark as error with remarks
	UPDATE IMP_MR_LINES SET STATUS_CODE= 'ERROR', REMARKS = 'Too old records'
	WHERE IMP_MR_HEADER_ID =  V_BATCH_ID
	AND (final_READING_DT IS NOT NULL AND TO_NUMBER(SUBSTR(final_READING_DT,6,2)) < 5) ;


	*/

	--Mark all records as error with remarks if Final-ReadingDate < InitialReadingDate and not already marked as error with remarks
	UPDATE IMP_MR_LINES SET STATUS_CODE= 'ERROR', REMARKS = 'FinalReadingDate < InitialReadingDate '
	WHERE IMP_MR_HEADER_ID =  V_BATCH_ID
	AND STATUS_CODE = 'CREATED'
	AND (final_READING_DT IS NOT NULL AND  init_READING_DT IS NOT NULL AND to_date(final_READING_DT,'yyyy-mm-dd') < to_date(init_READING_DT,'yyyy-mm-dd'));





	--If the Service-No doesnt exists in m_company_service, mark as error with remarks
	UPDATE IMP_MR_LINES SET STATUS_CODE= 'ERROR', REMARKS = 'Unknown Service-no'
	WHERE IMP_MR_HEADER_ID =  V_BATCH_ID
	AND STATUS_CODE = 'CREATED'
	AND SERVICE_NO  IN
        (
         SELECT distinct SERVICE_NO  FROM IMP_MR_lines where imp_mr_header_id= V_BATCH_ID
          minus
         SELECT distinct  "number"  FROM M_COMPANY_SERVICE
        );



   --If the ServiceNo-MeterNo combo doesnt exist in v_company_service, mark as error with remarks
  UPDATE IMP_MR_LINES SET STATUS_CODE= 'ERROR', REMARKS = 'Wrong ServiceNo-MeterNo combo'
	WHERE IMP_MR_HEADER_ID =  V_BATCH_ID
	AND STATUS_CODE = 'CREATED'
	AND SERVICE_NO||'-'||METER_NO  IN
        (
         SELECT distinct SERVICE_NO||'-'||METER_NO  FROM IMP_MR_lines where imp_mr_header_id= V_BATCH_ID
          minus
         select "number"||'-'|| nvl(meter_number,'no-meter') meter_number from v_company_service where COMP_SER_TYPE_CODE='03'
        );




	--If the Meter_no doesnt exists in m_company_meter, mark as error with remarks
	UPDATE IMP_MR_LINES SET STATUS_CODE= 'ERROR', REMARKS = 'Unknown Meter-no'
    where IMP_MR_HEADER_ID =  V_BATCH_ID
        AND STATUS_CODE = 'CREATED'
        AND meter_no in
        (
         SELECT distinct meter_no  FROM IMP_MR_lines where imp_mr_header_id= V_BATCH_ID
          minus
         SELECT distinct  METER_NUMBER  FROM M_COMPANY_METER  meter
        );


	--If   Final values is null, mark as error with remarks
	UPDATE IMP_MR_LINES SET STATUS_CODE= 'ERROR', REMARKS = 'Initial/Final values is null'
	WHERE IMP_MR_HEADER_ID =  V_BATCH_ID
	AND STATUS_CODE = 'CREATED'
	AND ( EXP_FINAL_S1 is null or EXP_FINAL_S2 is null or EXP_FINAL_S3 is null or EXP_FINAL_S4 is null or EXP_FINAL_S5 is null
                     or   IMP_FINAL_S1 is null or IMP_FINAL_S2 is null or IMP_FINAL_S3 is null or IMP_FINAL_S4 is null or IMP_FINAL_S5 is null );

--If the initial or final reading date is null, mark as error with remarks
	UPDATE IMP_MR_LINES SET STATUS_CODE= 'ERROR', REMARKS = 'Initial or Final Reading date is null'
	WHERE IMP_MR_HEADER_ID =  V_BATCH_ID
	AND STATUS_CODE = 'CREATED'
	AND (init_READING_DT IS NULL OR final_READING_DT IS NULL );

	-- make sure the readings from mri, doesnt exist in MeterReading table
	 UPDATE IMP_MR_LINES SET STATUS_CODE= 'ERROR', REMARKS = 'Meter Reading Already Exists'
	 WHERE id IN (SELECT mri.id
		FROM IMP_MR_lines mri
		 JOIN M_COMPANY_METER meter ON meter.METER_NUMBER = mri.METER_NO and meter.ENABLED='Y'
		 JOIN M_COMPANY_SERVICE ser ON ser."number" = mri.SERVICE_NO AND meter.M_COMPANY_SERVICE_ID = ser.id
		 JOIN T_METER_READING_HDR mrh ON meter.id = mrh.M_COMPANY_METER_ID AND mri.READING_MONTH = mrh.READING_MONTH AND mri.READING_YEAR = mrh.READING_YEAR
		WHERE mri.IMP_MR_HEADER_ID = v_batch_id
		and mri.status_code = 'CREATED');

-- as MF from AMR is wrong/inconsistent, MF from OA is used
  for oa_data in (select mrl.id,  mrl.service_no,mrl.meter_no, ser.mf  from imp_mr_lines mrl
                join v_company_service ser on mrl.service_no=ser."number" and mrl.meter_no=ser.meter_number 
                where mrl.imp_mr_header_id= v_batch_id and mrl.status_code = 'CREATED')
  loop 
    update imp_mr_lines set mf = oa_data.mf, MODIFIED_BY='admin', MODIFIED_DATE=sysdate 
      where imp_mr_header_id= v_batch_id and service_no= oa_data.service_no and meter_no = oa_data.meter_no; 
  end loop;

--return 'MIDDLE';

-- if dual entries entry exists for a meter in same month
-- Find MRI with multiple entries for same month if dual entries entry exists for a meter in same reading month-year in same batch
	FOR mul_entry IN (SELECT  line.IMP_MR_HEADER_ID ,line.SERVICE_NO,line.METER_NO, line.READING_MONTH, line.READING_YEAR,  line.mf,count(line.METER_NO) readings_per_meter
			FROM IMP_MR_lines line
			LEFT JOIN IMP_MR_header head ON line.IMP_MR_HEADER_ID = head.id
			WHERE line.IMP_MR_HEADER_ID = v_batch_id
			and line.status_code = 'CREATED'
			GROUP BY line.IMP_MR_HEADER_ID, line.SERVICE_NO,line.METER_NO, line.READING_MONTH, line.READING_YEAR, line.mf
			HAVING count(line.METER_NO) >1)
	LOOP
    begin
		v_mri_rec.id := IMP_MR_lines_SEQ.nextval;
		v_mri_rec.IMP_MR_HEADER_ID := mul_entry.IMP_MR_HEADER_ID;
		v_mri_rec.STATUS_CODE := 'CREATED';
    v_mri_rec.SERVICE_NO := mul_entry.SERVICE_NO;
		v_mri_rec.METER_NO := mul_entry.METER_NO;
		v_mri_rec.READING_MONTH := mul_entry.READING_MONTH;
		v_mri_rec.READING_YEAR := mul_entry.READING_YEAR;
		v_mri_rec.mf := mul_entry.mf;
		v_mri_rec.sys_dt := TO_CHAR(SYSDATE,'yyyy-mm-dd');
		v_mri_rec.INIT_READING_DT := (mul_entry.READING_YEAR||'-'||mul_entry.READING_MONTH||'-'||'01' );
		v_mri_rec.FINAL_READING_DT := to_char(LAST_DAY( to_date((mul_entry.READING_YEAR||'-'||mul_entry.READING_MONTH||'-'||'01' ),'YYYY-MM-DD')),'YYYY-MM-DD');

	 		--find the min values for all INIT values for the given batchNo, serviceNo, meterNo, month, year
		SELECT  min(to_number(nvl(IMP_INIT_S1,0))) IMP_INIT_S1,min(to_number(nvl(IMP_INIT_S2,0))) IMP_INIT_S2,min(to_number(nvl(IMP_INIT_S3,0))) IMP_INIT_S3,min(to_number(nvl(IMP_INIT_S4,0))) IMP_INIT_S4,min(to_number(nvl(IMP_INIT_S5,0))) IMP_INIT_S5,
				min(to_number(nvl(EXP_INIT_S1,0))) EXP_INIT_S1,min(to_number(nvl(EXP_INIT_S2,0))) EXP_INIT_S2,min(to_number(nvl(EXP_INIT_S3,0))) EXP_INIT_S3,min(to_number(nvl(EXP_INIT_S4,0))) EXP_INIT_S4,min(to_number(nvl(EXP_INIT_S5,0))) EXP_INIT_S5,
				 min(to_number(nvl(IMP_RKVAH_INIT,0))) IMP_RKVAH_INIT,min(to_number(nvl(EXP_RKVAH_INIT,0))) EXP_RKVAH_INIT,min(to_number(nvl(IMP_KVAH_INIT,0))) IMP_KVAH_INIT,min(to_number(nvl(EXP_KVAH_INIT,0))) EXP_KVAH_INIT
			 INTO v_mri_rec.IMP_INIT_S1, v_mri_rec.IMP_INIT_S2, v_mri_rec.IMP_INIT_S3, v_mri_rec.IMP_INIT_S4, v_mri_rec.IMP_INIT_S5,
					v_mri_rec.EXP_INIT_S1, v_mri_rec.EXP_INIT_S2, v_mri_rec.EXP_INIT_S3, v_mri_rec.EXP_INIT_S4, v_mri_rec.EXP_INIT_S5,
					v_mri_rec.IMP_RKVAH_INIT ,v_mri_rec.EXP_RKVAH_INIT,v_mri_rec.IMP_KVAH_INIT,v_mri_rec.EXP_KVAH_INIT
			FROM  IMP_MR_lines lines
			WHERE lines.IMP_MR_HEADER_ID = v_batch_id AND lines.SERVICE_NO = mul_entry.service_no AND lines.METER_NO = mul_entry.meter_no
			AND lines.READING_MONTH = mul_entry.READING_MONTH AND lines.READING_YEAR = mul_entry.READING_YEAR
      group by lines.IMP_MR_HEADER_ID,lines.SERVICE_NO ,lines.METER_NO,lines.READING_MONTH ,lines.READING_YEAR ;


		--find the max values for all final values for the given batchNo, serviceNo, meterNo, month, year
		SELECT  max(to_number(nvl(IMP_FINAL_S1,0))) IMP_FINAL_S1,max(to_number(nvl(IMP_FINAL_S2,0))) IMP_FINAL_S2,max(to_number(nvl(IMP_FINAL_S3,0))) IMP_FINAL_S3,max(to_number(nvl(IMP_FINAL_S4,0))) IMP_FINAL_S4,max(to_number(nvl(IMP_FINAL_S5,0))) IMP_FINAL_S5,

				max(to_number(nvl(EXP_FINAL_S1,0))) EXP_FINAL_S1,max(to_number(nvl(EXP_FINAL_S2,0))) EXP_FINAL_S2,max(to_number(nvl(EXP_FINAL_S3,0))) EXP_FINAL_S3,max(to_number(nvl(EXP_FINAL_S4,0))) EXP_FINAL_S4,max(to_number(nvl(EXP_FINAL_S5,0))) EXP_FINAL_S5,
				 max(to_number(nvl(IMP_RKVAH_FINAL,0))) IMP_RKVAH_FINAL,max(to_number(nvl(EXP_RKVAH_FINAL,0))) EXP_RKVAH_FINAL,max(to_number(nvl(IMP_KVAH_FINAL,0))) IMP_KVAH_FINAL,max(to_number(nvl(EXP_KVAH_FINAL,0))) EXP_KVAH_FINAL
			 INTO v_mri_rec.IMP_FINAL_S1, v_mri_rec.IMP_FINAL_S2, v_mri_rec.IMP_FINAL_S3, v_mri_rec.IMP_FINAL_S4, v_mri_rec.IMP_FINAL_S5,
					v_mri_rec.EXP_FINAL_S1, v_mri_rec.EXP_FINAL_S2, v_mri_rec.EXP_FINAL_S3, v_mri_rec.EXP_FINAL_S4, v_mri_rec.EXP_FINAL_S5,
					v_mri_rec.IMP_RKVAH_FINAL ,v_mri_rec.EXP_RKVAH_FINAL,v_mri_rec.IMP_KVAH_FINAL,v_mri_rec.EXP_KVAH_FINAL
			FROM  IMP_MR_lines lines
			WHERE lines.IMP_MR_HEADER_ID = v_batch_id AND lines.SERVICE_NO = mul_entry.service_no AND lines.METER_NO = mul_entry.meter_no
			AND lines.READING_MONTH = mul_entry.READING_MONTH AND lines.READING_YEAR = mul_entry.READING_YEAR
      group by lines.IMP_MR_HEADER_ID,lines.SERVICE_NO ,lines.METER_NO,lines.READING_MONTH ,lines.READING_YEAR ;


		UPDATE IMP_MR_LINES lines SET STATUS_CODE= 'ERROR', REMARKS = 'Multiple entries in same month', REF_NO = v_mri_rec.id
			WHERE IMP_MR_HEADER_ID =  V_BATCH_ID AND lines.SERVICE_NO = mul_entry.service_no AND lines.METER_NO = mul_entry.meter_no
			AND lines.READING_MONTH = mul_entry.READING_MONTH AND lines.READING_YEAR = mul_entry.READING_YEAR;

		INSERT INTO IMP_MR_LINES VALUES v_mri_rec;


    exception
          when others then
            v_exception_code := SQLCODE;
            v_exception_msg := SUBSTR(SQLERRM, 1, 200);
            v_reason := v_exception_code || ' - ' || v_exception_msg;
          v_log_result := log_activity('PROCEDURE','MRI_IDENTIFY_ERRORS','LOOP-EXCEPTION',v_reason,v_result,'', sysdate,V_BATCH_ID);
          UPDATE IMP_MR_LINES SET STATUS_CODE = 'ERROR', remarks = 'Unknown Issues - possiblly data issue'
            WHERE  id = v_mri_rec.id;
        end;
	END LOOP;

	-- make sure the readings from mri, doesnt exist in MeterReading table
	 UPDATE IMP_MR_LINES SET STATUS_CODE= 'ERROR', REMARKS = 'Meter Reading Already Exists'
	 WHERE id IN (SELECT mri.id
		FROM IMP_MR_lines mri
		 JOIN M_COMPANY_METER meter ON meter.METER_NUMBER = mri.METER_NO and meter.ENABLED='Y'
		 JOIN M_COMPANY_SERVICE ser ON ser."number" = mri.SERVICE_NO AND meter.M_COMPANY_SERVICE_ID = ser.id
		 JOIN T_METER_READING_HDR mrh ON meter.id = mrh.M_COMPANY_METER_ID AND mri.READING_MONTH = mrh.READING_MONTH AND mri.READING_YEAR = mrh.READING_YEAR
		WHERE mri.IMP_MR_HEADER_ID = v_batch_id
		and mri.status_code = 'CREATED');


  RETURN 'SUCCESS';
	exception
	  when others then
	    v_exception_code := SQLCODE;
	    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
	   -- v_result := 'FAILURE';
	    v_reason := v_exception_code || ' - ' || v_exception_msg;
	   -- -- dbms_output.put_line('CLEANSE_MRI - '||v_reason);
      v_log_result := log_activity('PROCEDURE','MRI_IDENTIFY_ERRORS','EXCEPTION',v_reason,NULL,'', sysdate,V_BATCH_ID);

      return 'FAILURE';
END MRI_IDENTIFY_ERRORS;

