CREATE OR REPLACE FUNCTION OPENACCESS."CLEANSE_MRI" 
(
  V_BATCH_ID IN VARCHAR2
) RETURN VARCHAR2 AS
v_count NUMBER; 
v_prev_month varchar2(100);
v_prev_year varchar2(100);
v_prev_read_in_oa boolean;
v_prev_read_in_imp_table boolean;
v_older_read_in_oa boolean;
v_older_read_in_imp_table boolean;
v_curr_month_first_date date;
v_mr_id varchar2(100);
v_mri_id varchar2(100);
v_mri_rec IMP_MR_LINES%ROWTYPE;
v_source varchar2(50);
v_is_complete varchar2(50);
v_status varchar2(50);
v_reason varchar2(200);
v_exception_code  NUMBER;
v_exception_msg  VARCHAR2(250);
v_result varchar(300):='SUCCESS';
v_log_result varchar(300);
mr_source_code VARCHAR2(50);
BEGIN 
	BEGIN
	    v_log_result := log_activity('PROCEDURE','CLEANSE_MRI','start',null,null,'', sysdate,V_BATCH_ID);

	SELECT mr_source_code INTO v_source FROM IMP_MR_HEADER WHERE ID = V_BATCH_ID;



	UPDATE IMP_MR_LINES SET STATUS_CODE = 'CREATED', is_first_entry = 'N'  WHERE IMP_MR_HEADER_ID = V_BATCH_ID;

--	---- dbms_output.put_line('In cleanse function');

	--if source="01" (mdms), then change the date formats to 'YYYY-MM-DD' and strip time from it
/*
	if(v_source = '01') THEN
		UPDATE IMP_MR_LINES SET sys_dt = TO_CHAR(to_date(sys_dt,'mm/dd/yyyy'),'yyyy-mm-dd'), INIT_READING_DT=TO_CHAR(to_date(INIT_READING_DT,'mm/dd/yyyy'),'yyyy-mm-dd') , FINAL_READING_DT=TO_CHAR(to_date(FINAL_READING_DT,'mm/dd/yyyy'),'yyyy-mm-dd')
		WHERE  IMP_MR_HEADER_ID = V_BATCH_ID;

		UPDATE IMP_MR_LINES SET READING_MONTH = SUBSTR(final_READING_DT,6,2),READING_YEAR = SUBSTR(final_READING_DT,0,4)
		WHERE  IMP_MR_HEADER_ID = V_BATCH_ID;

	ELSE 	*/
		UPDATE IMP_MR_LINES SET sys_dt = TO_CHAR(SYSDATE,'yyyy-mm-dd'),  INIT_READING_DT = (READING_YEAR||'-'||READING_MONTH||'-'||'01' ) ,
		FINAL_READING_DT = to_char(LAST_DAY( to_date((READING_YEAR||'-'||READING_MONTH||'-'||'01' ),'YYYY-MM-DD')),'YYYY-MM-DD')
		WHERE IMP_MR_HEADER_ID = V_BATCH_ID and nvl(MERGE_WITH_NEXT_BILLING,'N')='N';

	--END IF;
   -- cleanup the imported meter lines, by ignoring dirty data
	  v_result := MRI_IDENTIFY_ERRORS(v_batch_id);
      v_log_result := log_activity('PROCEDURE','CLEANSE_MRI','identifying errors',V_RESULT,V_RESULT,'', sysdate,V_BATCH_ID);

    if(substr(v_result,0,7) = 'FAILURE') THEN
	 	GOTO THE_END;
	 END IF;



	FOR mril IN (SELECT  line.id, line.IMP_MR_HEADER_ID ,line.SERVICE_NO,line.METER_NO, line.READING_MONTH, line.READING_YEAR
			FROM IMP_MR_lines line
			WHERE 1=1
			and line.IMP_MR_HEADER_ID = v_batch_id
            and status_code = 'CREATED'
		--  	and ( line.IS_FIRST_ENTRY = 'Y'
      --nvl(to_number(IMP_INIT_S1),0) = 0 and nvl(to_number(IMP_INIT_S2),0) = 0 and nvl(to_number(IMP_INIT_S3),0) = 0 and nvl(to_number(IMP_INIT_S4),0) = 0 and nvl(to_number(IMP_INIT_S5),0) = 0 and
				--nvl(to_number(EXP_INIT_S1),0) = 0 and nvl(to_number(EXP_INIT_S2),0) = 0 and nvl(to_number(EXP_INIT_S3),0) = 0 and nvl(to_number(EXP_INIT_S4),0) = 0 and nvl(to_number(EXP_INIT_S5),0) = 0
				--and nvl(to_number(IMP_RKVAH_INIT ),0) = 0 and nvl(to_number(v_mri_rec.EXP_RKVAH_INIT),0) = 0 and nvl(to_number(v_mri_rec.IMP_KVAH_INIT),0) = 0 and nvl(to_number(v_mri_rec.EXP_KVAH_INIT)=0
				--)
                ORDER BY line.SERVICE_NO,line.METER_NO,line.INIT_READING_DT)
	LOOP
    begin 
    v_prev_read_in_oa := false;  
    v_prev_read_in_imp_table := false;  
    v_older_read_in_oa := false;  
    v_older_read_in_imp_table := false;  

    v_prev_month := TO_CHAR(to_date((mril.READING_YEAR||'-'||mril.READING_MONTH||'-'||'01' ),'YYYY-MM-DD')-1,'MM');
    v_prev_year := TO_CHAR(to_date((mril.READING_YEAR||'-'||mril.READING_MONTH||'-'||'01' ),'YYYY-MM-DD')-1,'YYYY');

    v_curr_month_first_date := to_date((mril.READING_YEAR||'-'||mril.READING_MONTH||'-'||'01' ),'YYYY-MM-DD') ;
		SELECT * INTO v_mri_rec FROM IMP_MR_lines WHERE id = mril.id;

    if (nvl(v_mri_rec.MERGE_WITH_NEXT_BILLING,'N') = 'Y') then
      continue ; --leave the record untouched and skip the loop
    end if;

		SELECT count( mr.id )INTO v_count FROM T_METER_READING_HDR mr
						JOIN M_COMPANY_METER meter ON meter.id = mr.M_COMPANY_METER_ID and meter.ENABLED='Y'
						JOIN M_COMPANY_SERVICE ser ON meter.M_COMPANY_SERVICE_ID = ser.id
						WHERE ser."number" = mril.SERVICE_NO AND meter.METER_NUMBER = mril.meter_NO
						AND mr.READING_MONTH = v_prev_month
						AND mr.READING_YEAR = v_prev_month;
        if(v_count > 0 ) then v_prev_read_in_oa := true;   end if; 

		SELECT count(*) INTO v_count FROM  IMP_MR_lines where IMP_MR_HEADER_ID = v_batch_id AND SERVICE_NO = mril.SERVICE_NO AND meter_no = mril.meter_no
				AND READING_MONTH = v_prev_month
				AND READING_YEAR = v_prev_month ;
        if(v_count > 0 ) then v_prev_read_in_imp_table := true;     end if;

		SELECT count( mr.id )INTO v_count FROM T_METER_READING_HDR mr
						JOIN M_COMPANY_METER meter ON meter.id = mr.M_COMPANY_METER_ID and meter.ENABLED='Y'
						JOIN M_COMPANY_SERVICE ser ON meter.M_COMPANY_SERVICE_ID = ser.id
						WHERE ser."number" = mril.SERVICE_NO AND meter.METER_NUMBER = mril.meter_NO
						AND mr.FINAL_READING_DT  < v_curr_month_first_date;
        if(v_count > 0 ) then v_older_read_in_oa := true;     end if;

		SELECT count(*) INTO v_count FROM  IMP_MR_lines mr where IMP_MR_HEADER_ID = v_batch_id AND SERVICE_NO = mril.SERVICE_NO AND meter_no = mril.meter_no
				AND to_date(mr.FINAL_READING_DT,'YYYY-MM-DD')	  < v_curr_month_first_date;
        if(v_count > 0 ) then v_older_read_in_imp_table := true;     end if;



		if(v_prev_read_in_oa)THEN

			SELECT  mr.id INTO v_mr_id FROM T_METER_READING_HDR mr
			JOIN M_COMPANY_METER meter ON meter.id = mr.M_COMPANY_METER_ID and meter.ENABLED='Y'
			JOIN M_COMPANY_SERVICE ser ON meter.M_COMPANY_SERVICE_ID = ser.id
			WHERE ser."number" = mril.SERVICE_NO AND meter.METER_NUMBER = mril.meter_NO
			AND mr.READING_MONTH = v_prev_month
			AND mr.READING_YEAR = v_prev_year;

			SELECT nvl(IMP_FINAL,0),nvl(EXP_FINAL,0) INTO v_mri_rec.IMP_INIT_S1, v_mri_rec.EXP_INIT_S1  FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mr_id AND SLOT_CODE='C1';
			SELECT nvl(IMP_FINAL,0),nvl(EXP_FINAL,0) INTO v_mri_rec.IMP_INIT_S2, v_mri_rec.EXP_INIT_S2  FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mr_id AND SLOT_CODE='C2';
			SELECT nvl(IMP_FINAL,0),nvl(EXP_FINAL,0) INTO v_mri_rec.IMP_INIT_S3, v_mri_rec.EXP_INIT_S3  FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mr_id AND SLOT_CODE='C3';
			SELECT nvl(IMP_FINAL,0),nvl(EXP_FINAL,0) INTO v_mri_rec.IMP_INIT_S4, v_mri_rec.EXP_INIT_S4  FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mr_id AND SLOT_CODE='C4';
			SELECT nvl(IMP_FINAL,0),nvl(EXP_FINAL,0) INTO v_mri_rec.IMP_INIT_S5, v_mri_rec.EXP_INIT_S5  FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mr_id AND SLOT_CODE='C5';

              select nvl(IMP_RKVAH_FINAL,0),nvl(EXP_RKVAH_FINAL,0), to_char(FINAL_READING_DT+1,'YYYY-MM-DD' )
              INTO v_mri_rec.IMP_RKVAH_INIT, v_mri_rec.EXP_RKVAH_INIT , v_mri_rec.INIT_READING_DT
              from T_METER_READING_HDR where id = v_mr_id;
		ELSIF (v_prev_read_in_imp_table) THEN

			SELECT  id INTO  v_mri_id FROM  IMP_MR_lines where IMP_MR_HEADER_ID = v_batch_id AND SERVICE_NO = mril.SERVICE_NO AND meter_no = mril.meter_no
			AND READING_MONTH = v_prev_month
			AND READING_YEAR = v_prev_year;

			SELECT nvl(IMP_FINAL_S1,0), nvl(IMP_FINAL_S2,0), nvl(IMP_FINAL_S3,0), nvl(IMP_FINAL_S4,0), nvl(IMP_FINAL_S5,0),
					nvl(EXP_FINAL_S1,0), nvl(EXP_FINAL_S2,0), nvl(EXP_FINAL_S3,0), nvl(EXP_FINAL_S4,0), nvl(EXP_FINAL_S5,0)
				INTO
					v_mri_rec.IMP_INIT_S1, v_mri_rec.IMP_INIT_S2, v_mri_rec.IMP_INIT_S3, v_mri_rec.IMP_INIT_S4, v_mri_rec.IMP_INIT_S5,
					v_mri_rec.EXP_INIT_S1, v_mri_rec.EXP_INIT_S2, v_mri_rec.EXP_INIT_S3, v_mri_rec.EXP_INIT_S4, v_mri_rec.EXP_INIT_S5
				FROM  IMP_MR_lines where id = v_mri_id;

              select nvl(IMP_RKVAH_FINAL,0),nvl(EXP_RKVAH_FINAL,0) ,to_char(to_date(FINAL_READING_DT,'YYYY-MM-DD')+1,'YYYY-MM-DD' )
              INTO v_mri_rec.IMP_RKVAH_INIT, v_mri_rec.EXP_RKVAH_INIT  , v_mri_rec.INIT_READING_DT
              from IMP_MR_lines where id = v_mri_id;

		elsif(v_older_read_in_oa)THEN

			SELECT  id INTO v_mr_id from(
			SELECT mr.id FROM T_METER_READING_HDR mr
			JOIN M_COMPANY_METER meter ON meter.id = mr.M_COMPANY_METER_ID and meter.ENABLED='Y'
			JOIN M_COMPANY_SERVICE ser ON meter.M_COMPANY_SERVICE_ID = ser.id
			WHERE ser."number" = mril.SERVICE_NO AND meter.METER_NUMBER = mril.meter_NO
			AND mr.FINAL_READING_DT  <  v_curr_month_first_date
			ORDER BY mr.FINAL_READING_DT desc) WHERE  rownum =1;

			SELECT nvl(IMP_FINAL,0),nvl(EXP_FINAL,0) INTO v_mri_rec.IMP_INIT_S1, v_mri_rec.EXP_INIT_S1  FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mr_id AND SLOT_CODE='C1';
			SELECT nvl(IMP_FINAL,0),nvl(EXP_FINAL,0) INTO v_mri_rec.IMP_INIT_S2, v_mri_rec.EXP_INIT_S2  FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mr_id AND SLOT_CODE='C2';
			SELECT nvl(IMP_FINAL,0),nvl(EXP_FINAL,0) INTO v_mri_rec.IMP_INIT_S3, v_mri_rec.EXP_INIT_S3  FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mr_id AND SLOT_CODE='C3';
			SELECT nvl(IMP_FINAL,0),nvl(EXP_FINAL,0) INTO v_mri_rec.IMP_INIT_S4, v_mri_rec.EXP_INIT_S4  FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mr_id AND SLOT_CODE='C4';
			SELECT nvl(IMP_FINAL,0),nvl(EXP_FINAL,0) INTO v_mri_rec.IMP_INIT_S5, v_mri_rec.EXP_INIT_S5  FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mr_id AND SLOT_CODE='C5';

              select nvl(IMP_RKVAH_FINAL,0),nvl(EXP_RKVAH_FINAL,0), to_char(FINAL_READING_DT+1,'YYYY-MM-DD' )
              INTO v_mri_rec.IMP_RKVAH_INIT, v_mri_rec.EXP_RKVAH_INIT  , v_mri_rec.INIT_READING_DT
              from T_METER_READING_HDR where id = v_mr_id;
		ELSIF (v_older_read_in_imp_table) THEN


			SELECT  id INTO  v_mri_id from(
			SELECT mr.id FROM  IMP_MR_lines mr where IMP_MR_HEADER_ID = v_batch_id AND SERVICE_NO = mril.SERVICE_NO AND meter_no = mril.meter_no
			AND to_date(mr.FINAL_READING_DT,'YYYY-MM-DD')  < v_curr_month_first_date
			ORDER BY mr.FINAL_READING_DT DESC) WHERE  rownum = 1;



			SELECT nvl(IMP_FINAL_S1,0), nvl(IMP_FINAL_S2,0), nvl(IMP_FINAL_S3,0), nvl(IMP_FINAL_S4,0), nvl(IMP_FINAL_S5,0),
					nvl(EXP_FINAL_S1,0), nvl(EXP_FINAL_S2,0), nvl(EXP_FINAL_S3,0), nvl(EXP_FINAL_S4,0), nvl(EXP_FINAL_S5,0),
          nvl(IMP_RKVAH_FINAL,0),nvl(EXP_RKVAH_FINAL,0)
				INTO
					v_mri_rec.IMP_INIT_S1, v_mri_rec.IMP_INIT_S2, v_mri_rec.IMP_INIT_S3, v_mri_rec.IMP_INIT_S4, v_mri_rec.IMP_INIT_S5,
					v_mri_rec.EXP_INIT_S1, v_mri_rec.EXP_INIT_S2, v_mri_rec.EXP_INIT_S3, v_mri_rec.EXP_INIT_S4, v_mri_rec.EXP_INIT_S5,
          v_mri_rec.IMP_RKVAH_INIT, v_mri_rec.EXP_RKVAH_INIT
				FROM  IMP_MR_lines where id = v_mri_id;

          select nvl(IMP_RKVAH_FINAL,0),nvl(EXP_RKVAH_FINAL,0),to_char(to_date(FINAL_READING_DT,'YYYY-MM-DD')+1,'YYYY-MM-DD' )
          INTO v_mri_rec.IMP_RKVAH_INIT, v_mri_rec.EXP_RKVAH_INIT,    v_mri_rec.INIT_READING_DT
          from T_METER_READING_HDR where id = v_mri_id;
		ELSE
			SELECT 'Y',0,0,0,0,0,0,0,0,0,0,0,0 INTO v_mri_rec.is_first_entry,v_mri_rec.IMP_INIT_S1, v_mri_rec.IMP_INIT_S2, v_mri_rec.IMP_INIT_S3, v_mri_rec.IMP_INIT_S4, v_mri_rec.IMP_INIT_S5,
					v_mri_rec.EXP_INIT_S1, v_mri_rec.EXP_INIT_S2, v_mri_rec.EXP_INIT_S3, v_mri_rec.EXP_INIT_S4, v_mri_rec.EXP_INIT_S5,v_mri_rec.IMP_RKVAH_INIT, v_mri_rec.EXP_RKVAH_INIT   FROM dual;
		END IF;

		UPDATE IMP_MR_lines SET ROW = v_mri_rec WHERE id = v_mri_rec.id;
		--TO_CHAR(to_date((mri.READING_YEAR||'-'||mri.READING_MONTH||'-'||'01' ),'YYYY-MM-DD')-1,'MM')

	exception
	  when others then
	    v_exception_code := SQLCODE;
	    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
	    v_reason := v_exception_code || ' - ' || v_exception_msg;
      v_log_result := log_activity('PROCEDURE','CLEANSE_MRI-LOOP','EXCEPTION',v_reason,v_result,'', sysdate,V_BATCH_ID);
      UPDATE IMP_MR_LINES SET STATUS_CODE = 'ERROR', remarks = 'Unknown Issues - possibly data issue'
        WHERE  id = v_mri_rec.id;
    end;
    END LOOP;

	UPDATE IMP_MR_LINES SET STATUS_CODE = 'CLEANSED', remarks = ''
	WHERE  IMP_MR_HEADER_ID = V_BATCH_ID AND STATUS_CODE = 'CREATED';

	exception
	  when others then
	    v_exception_code := SQLCODE;
	    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
	    v_result := 'FAILURE';
	    v_reason := v_exception_code || ' - ' || v_exception_msg;
      v_log_result := log_activity('PROCEDURE','CLEANSE_MRI','EXCEPTION',v_reason,v_result,'', sysdate,V_BATCH_ID);

	    ---- dbms_output.put_line('CLEANSE_MRI - '||v_reason);

	END;
   <<THE_END>>

  IF V_RESULT = 'SUCCESS' THEN
    COMMIT;
  else
    v_result := v_result || ' - ' || v_reason;
  END IF;

	    v_log_result := log_activity('PROCEDURE','CLEANSE_MRI','End',v_result,v_result,'', sysdate,V_BATCH_ID);
  return v_result;
END CLEANSE_MRI;

