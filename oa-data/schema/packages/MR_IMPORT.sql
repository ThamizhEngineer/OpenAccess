CREATE OR REPLACE PACKAGE "MR_IMPORT"
is
	procedure process_mr_interface (v_remarks in varchar2,scenario in varchar2 default 'MERGE-WITH-NEXT-BILLING',v_result_code out varchar2, v_result_desc out varchar2);
  procedure interface_to_mri (v_batch_id in varchar2,scenario in varchar2,  v_remarks in varchar2, v_result_code out varchar2, v_result_desc out varchar2);
	procedure cleanse (v_batch_id in varchar2,scenario in varchar2, v_result_code out varchar2, v_result_desc out varchar2);
	procedure process_mri (v_batch_id in varchar2);
END;



CREATE OR REPLACE PACKAGE BODY "MR_IMPORT" AS
  procedure process_mr_interface (v_remarks in varchar2,scenario in varchar2 default 'MERGE-WITH-NEXT-BILLING', v_result_code out varchar2, v_result_desc out varchar2) is

    v_batch_id varchar2(100):='';
    v_created_by varchar2(100):='admin';
    v_log_result  varchar2(50);
    v_reason varchar2(200):='';
    v_exception_code  NUMBER;
    v_exception_msg  VARCHAR2(200);
	BEGIN
      BEGIN  -- exception handling start

      v_log_result := log_activity('PROCEDURE','mr_import.process_mr_interface','START',v_remarks,'',v_created_by, sysdate,'');
      v_batch_id :=IMP_MR_HEADER_SEQ.NEXTVAL;

      -- move data from import_meter_reading table to imp_mr_tables
      interface_to_mri(v_batch_id,scenario, v_remarks,v_result_code,v_result_desc);
      if(v_result_code = 'ERROR') then GOTO THE_END; END IF;

      -- populate meter_no
      cleanse(v_batch_id,scenario,v_result_code,v_result_desc);
      if(v_result_code = 'ERROR') then GOTO THE_END; END IF;

      -- call mr processing function
      -- note- MR are imported. after that gen stmt is not generated.
      v_result_code := import_mr(v_batch_id);

      exception
      when others then
        v_exception_code := SQLCODE;
        v_exception_msg := SUBSTR(SQLERRM, 1, 200);
        v_result_code := 'FAILURE';
        v_result_desc := v_exception_code || ' - ' || v_exception_msg;
        dbms_output.put_line(v_result_desc);
      END;
      <<THE_END>>
      commit;
      if(v_result_code = 'SUCCESS')then
        v_result_desc := v_batch_id;
      end if;
      v_log_result := log_activity('PROCEDURE','mr_import.process_mr_interface','RESULT',v_result_code,v_result_desc,v_created_by, sysdate,v_batch_id);
	END process_mr_interface;
  procedure interface_to_mri (v_batch_id in varchar2, scenario IN VARCHAR2,  v_remarks in varchar2, v_result_code out varchar2, v_result_desc out varchar2) is
    v_total number:=0;
    v_created_by varchar2(300):='admin';
    v_log_result  varchar2(50);
    v_exception_code  NUMBER;
    v_exception_msg  VARCHAR2(200);
	BEGIN
      BEGIN  -- exception handling start

       --init
        v_result_code :='SUCCESS';
        -- find the total no. of records to be imported
        select count(v_remarks) into v_total from IMPORT_METER_READING where remarks=v_remarks;

        -- create import header entry
        Insert into IMP_MR_HEADER (ID,IMPORT_DT,STATUS,REMARKS,MR_SOURCE_CODE,CREATED_BY,CREATED_DATE,ENABLED,CODE,IMPORT_REMARKS,TOTAL_COUNT,SUCCESS_COUNT,ERROR_COUNT)
        values (v_batch_id,SYSDATE,'CREATED',v_remarks,'02',v_created_by,SYSDATE,'Y','MRI'||v_batch_id,null,v_total,0,0);

        v_log_result := log_activity('PROCEDURE','mr_import.interface_to_mri','IMPORT','batch created - '||'MRI'||v_batch_id,'',v_created_by, sysdate,v_batch_id);

        update IMPORT_METER_READING set reading_dt =to_date(reading_dt_str,'yyyy-mm-dd'),
        init_reading_dt = init_reading_dt_str , FINAL_READING_DT = final_reading_dt_str
        where remarks = v_remarks;

       -- update IMPORT_METER_READING set reading_dt = to_date(reading_dt_str,'yyyy-mm-dd'),
        --init_reading_dt = to_date(init_reading_dt_str,'yyyy-mm-dd') , FINAL_READING_DT = to_date(final_reading_dt_str,'yyyy-mm-dd')
        --where remarks = v_remarks;

        IF(scenario = 'MERGE-WITH-NEXT-BILLING' ) THEN 
          update IMPORT_METER_READING set MERGE_WITH_NEXT_BILLING = 'Y', IMPORT_ASIS='Y' where remarks = v_remarks;
        ELSIF(scenario = 'OVERWRITE' OR scenario = 'NO-OVERWRITE' ) THEN
          update IMPORT_METER_READING set IMPORT_ASIS='Y' where remarks = v_remarks;
        end if;

        -- create import line entries
       insert into imp_mr_lines(
          ID,IMP_MR_HEADER_ID,SERVICE_NO,SYS_DT,INIT_READING_DT,FINAL_READING_DT,READING_MONTH,READING_YEAR,
          STATUS_CODE,METER_NO,MF,MERGE_WITH_NEXT_BILLING,
          IMP_FINAL_S1,IMP_FINAL_S2,IMP_FINAL_S3,IMP_FINAL_S4,IMP_FINAL_S5,
          EXP_FINAL_S1,EXP_FINAL_S2,EXP_FINAL_S3,EXP_FINAL_S4,EXP_FINAL_S5,
          IMP_RKVAH_FINAL,EXP_RKVAH_FINAL,IMP_KVAH_FINAL,EXP_KVAH_FINAL,
          IMP_INIT_S1,IMP_INIT_S2,IMP_INIT_S3,IMP_INIT_S4,IMP_INIT_S5,
          EXP_INIT_S1,EXP_INIT_S2,EXP_INIT_S3,EXP_INIT_S4,EXP_INIT_S5,
          IMP_RKVAH_INIT,EXP_RKVAH_INIT,IMP_KVAH_INIT,EXP_KVAH_INIT,IMPORT_ASIS )
        select imp_mr_lines_seq.nextval id, v_batch_id IMP_MR_HEADER_ID, SERVICE_NO, sysdate SYS_DT, INIT_READING_DT, FINAL_READING_DT,reading_month, reading_year,
          'CREATED' STATUS_CODE, METER_NO, MF,MERGE_WITH_NEXT_BILLING,
          IMP_SLOT1 IMP_FINAL_S1,IMP_SLOT2  IMP_FINAL_S2,IMP_SLOT3  IMP_FINAL_S3,IMP_SLOT4  IMP_FINAL_S4,IMP_SLOT5 IMP_FINAL_S5,
          EXP_SLOT1 EXP_FINAL_S1,EXP_SLOT2  EXP_FINAL_S2,EXP_SLOT3  EXP_FINAL_S3,EXP_SLOT4  EXP_FINAL_S4,EXP_SLOT5 EXP_FINAL_S5,
          IMP_RKVAH IMP_RKVAH_FINAL,EXP_RKVAH EXP_RKVAH_FINAL,IMP_KVAH IMP_KVAH_FINAL,EXP_KVAH EXP_KVAH_FINAL,
          IMP_SLOT1 IMP_INIT_S1,IMP_SLOT2  IMP_INIT_S2,IMP_SLOT3  IMP_INIT_S3,IMP_SLOT4  IMP_INIT_S4,IMP_SLOT5 IMP_INIT_S5,
          EXP_SLOT1 EXP_INIT_S1,EXP_SLOT2  EXP_INIT_S2,EXP_SLOT3  EXP_INIT_S3,EXP_SLOT4  EXP_INIT_S4,EXP_SLOT5 EXP_INIT_S5,
          IMP_RKVAH IMP_RKVAH_INIT,EXP_RKVAH EXP_RKVAH_INIT,IMP_KVAH IMP_KVAH_INIT,EXP_KVAH EXP_KVAH_INIT, IMPORT_ASIS
          from IMPORT_METER_READING
          where REMARKS = v_remarks;
        commit;
        v_result_code := 'SUCCESS';
      exception
      when others then
        v_exception_code := SQLCODE;
        v_exception_msg := SUBSTR(SQLERRM, 1, 200);
        v_result_code := 'FAILURE';
        v_result_desc := v_exception_code || ' - ' || v_exception_msg;
        dbms_output.put_line(v_result_desc);
      END;
      v_log_result := log_activity('PROCEDURE','mr_import.interface_to_mri','RESULT',v_result_code,v_result_desc,v_created_by, sysdate,v_batch_id);

	END interface_to_mri;
	procedure cleanse (v_batch_id in varchar2,scenario IN VARCHAR2,  v_result_code out varchar2, v_result_desc out varchar2) is
    v_created_by varchar2(300):='admin';
    v_remarks  varchar2(100);
    v_meter_no  varchar2(100);
    v_mf  varchar2(100);
    v_log_result  varchar2(50);
    v_exception_code  NUMBER;
    v_exception_msg  VARCHAR2(200);
	BEGIN
      BEGIN  -- exception handling start
      v_result_code:='SUCCESS';

      select remarks into v_remarks from imp_mr_header where id = v_batch_id;
      update imp_mr_lines set (meter_no, mf ) = (select meter_number, mf from v_company_service cs where cs."number"= imp_mr_lines.service_no) where IMP_MR_HEADER_ID = v_batch_id;

      IF(scenario = 'MERGE-WITH-NEXT-BILLING' or scenario = 'OVERWRITE') THEN 
        -- Delete existing(if) MeterReading from system for that month+year+service
        FOR rec IN (SELECT distinct mri.reading_month, mri.reading_year,mri.SERVICE_NO,mr.id t_meter_reading_hdr_id FROM imp_mr_lines mri 
                      join v_company_service ser on mri.service_no =  ser.m_comp_serv_number 
                      join t_meter_reading_hdr mr on ser.m_company_meter_id = mr.m_company_meter_id and  mri.reading_month = mr.reading_month and  mri.reading_year = mr.reading_year
                      where mri.imp_mr_header_id = v_batch_id)
        LOOP
          BEGIN
            DELETE_TXN.DELETE_BY_SERVICE('',rec.SERVICE_NO, rec.reading_month, rec.reading_year, 'Y', 'Y', 'Y', 'Y',v_result_code, v_result_desc);
          exception
          when others then
            v_exception_code := SQLCODE;
            v_exception_msg := SUBSTR(SQLERRM, 1, 200);
            v_result_code := 'FAILURE';
            v_result_desc := v_exception_code || ' - ' || v_exception_msg;
            dbms_output.put_line(v_result_desc);
          END;
        END LOOP;
      end IF;

      commit;
			v_result_code := 'SUCCESS';

      exception
      when others then
        v_exception_code := SQLCODE;
        v_exception_msg := SUBSTR(SQLERRM, 1, 200);
        v_result_code := 'FAILURE';
        v_result_desc := v_exception_code || ' - ' || v_exception_msg;
        dbms_output.put_line(v_result_desc);
      END;
      v_log_result := log_activity('PROCEDURE','mr_import.cleanse_mri','RESULT',v_result_code,v_result_desc,v_created_by, sysdate,v_batch_id);
	END cleanse;
	procedure process_mri (v_batch_id in varchar2) is
		v_result_code varchar2(100);
		BEGIN
			v_result_code := 'SUCCESS';
			DBMS_OUTPUT.PUT_LINE(v_result_code);
		END process_mri;
END mr_import;
