CREATE OR REPLACE FUNCTION OPENACCESS."IMPORT_MR" 
(
  V_BATCH_ID IN VARCHAR2
) RETURN VARCHAR2 AS
v_mrh T_METER_READING_HDR%ROWTYPE;
v_mr_c1 T_METER_READING_SLOT%ROWTYPE;
v_mr_c2 T_METER_READING_SLOT%ROWTYPE;
v_mr_c3 T_METER_READING_SLOT%ROWTYPE;
v_mr_c4 T_METER_READING_SLOT%ROWTYPE;
v_mr_c5 T_METER_READING_SLOT%ROWTYPE;
v_meter_id VARCHAR2(100);
v_imported BOOLEAN:=FALSE;
v_created_Date DATE := SYSDATE;
v_created_By  varchar2(50):= 'admin';
v_process_count number;
v_reason varchar2(200);
v_exception_code  NUMBER;
v_source varchar2(50);
v_exception_msg  VARCHAR2(200);
v_result varchar(300):='SUCCESS';
v_total_count number;
v_success_count number;
v_error_count number;
v_log_result VARCHAR2(50):='';

BEGIN

	BEGIN
		v_log_result := log_activity('PROCEDURE','IMPORT_MR','START','Start - '||V_BATCH_ID,V_RESULT,v_created_By, sysdate,V_BATCH_ID);

		select count(id) into v_process_count from imp_mr_header where status ='PROCESSING';

		-- if the batch is already processing, then stop flow
		if(v_process_count > 0) then
			v_reason := 'Another Batch is already processing . please wait';
		    V_RESULT := 'FAILURE';
		    GOTO THE_END;
		end if;

		-- find the total no. of records to be imported
		select count(import_remarks) into v_total_count from imp_mr_header where id =v_batch_id;

		-- set the batch to PROCESSING
		update imp_mr_header set status='PROCESSING', total_count=v_total_count, MODIFIED_DATE = sysdate where id = v_batch_id;
			SELECT mr_source_code INTO v_source FROM IMP_MR_HEADER WHERE ID = v_batch_id;

		-- cleanup the imported meter lines, by ignoring dirty data
		v_result := CLEANSE_MRI(v_batch_id);

		if(substr(v_result,0,7) = 'FAILURE') THEN
			 ---- dbms_output.put_line('cleanse result - '||v_result);
		 	GOTO THE_END;
		END IF;

		--loop through clean imported meter entries
		FOR mri IN (SELECT mri.* FROM IMP_MR_lines mri WHERE mri.IMP_MR_HEADER_ID = v_batch_id and mri.status_code = 'CLEANSED')
		LOOP
   		begin

	        v_log_result := log_activity('PROCEDURE','IMPORT_MR','loop start',mri.meter_no,null,'', sysdate,V_BATCH_ID);
		  	-- get meter-id
		  	SELECT DISTINCT meter.id INTO v_meter_id FROM M_COMPANY_METER  meter
		  		JOIN M_COMPANY_SERVICE ser ON ser."number" = mri.SERVICE_NO AND meter.M_COMPANY_SERVICE_ID = ser.id WHERE METER_NUMBER = mri.meter_no and meter.ENABLED='Y';
		 	--setting meter-header record
		 	v_mrh.id := T_METER_READING_SEQ.nextval;
			v_mrh.M_COMPANY_METER_ID := v_meter_id;
			v_mrh.STATUS_CODE := 'CREATED';
			v_mrh.IMP_BATCH_ID :=  mri.IMP_MR_HEADER_ID;
			v_mrh.sys_dt := v_created_Date;
			v_mrh.mf :=  mri.mf;
			v_mrh.READING_MONTH := mri.READING_MONTH;
			v_mrh.READING_YEAR := mri.READING_YEAR;
			v_mrh.INIT_READING_DT := to_date(mri.INIT_READING_DT,'yyyy-mm-dd');
			v_mrh.FINAL_READING_DT := to_date(mri.FINAL_READING_DT,'yyyy-mm-dd');
      v_mrh.MERGE_WITH_NEXT_BILLING := NVL(mri.MERGE_WITH_NEXT_BILLING,'N');
			v_mrh.EXP_RKVAH_FINAL := mri.EXP_RKVAH_FINAL; v_mrh.EXP_RKVAH_INIT := mri.EXP_RKVAH_INIT;
			v_mrh.IMP_RKVAH_FINAL := mri.IMP_RKVAH_FINAL; v_mrh.IMP_RKVAH_INIT := mri.IMP_RKVAH_INIT;
			v_mrh.EXP_KVAH_FINAL := mri.EXP_KVAH_FINAL; v_mrh.EXP_KVAH_INIT := mri.EXP_KVAH_INIT;
			v_mrh.IMP_KVAH_FINAL := mri.IMP_KVAH_FINAL; v_mrh.IMP_KVAH_INIT := mri.IMP_KVAH_INIT;
			v_mrh.RKVAH_DIFF := nvl(mri.IMP_RKVAH_FINAL,0) - nvl(mri.IMP_RKVAH_INIT, 0);
			v_mrh.RKVAH_UNITS := v_mrh.RKVAH_DIFF*mri.mf;
			v_mrh.KVAH_DIFF := nvl((mri.EXP_KVAH_FINAL - mri.EXP_KVAH_INIT)- (mri.IMP_KVAH_FINAL - mri.IMP_KVAH_INIT), 0);
--            v_mrh.KVAH_DIFF := nvl(( mri.IMP_KVAH_FINAL - mri.IMP_KVAH_INIT), 0);
			v_mrh.KVAH_UNITS := v_mrh.KVAH_DIFF*mri.mf;
            v_mrh.MR_SOURCE_CODE := v_source ;

			--v_mrh.TOTAL_IMPORT_GEN := ;
			--v_mrh.TOTAL_EXPORT_GEN := ;
			v_mrh.CREATED_BY := v_created_By;
			v_mrh.CREATED_DATE := v_created_Date;
			INSERT INTO T_METER_READING_HDR VALUES v_mrh;
	        v_log_result := log_activity('PROCEDURE','IMPORT_MR','log','insert meter-header record',null,'', sysdate,V_BATCH_ID);


			--setting meter-reading for slot1
			v_mr_c1.ID := T_METER_READING_SLOT_SEQ.nextval;
			v_mr_c1.T_METER_READING_HDR_ID :=  v_mrh.id;
			v_mr_c1.SLOT_CODE := 'C1' ;
			v_mr_c1.IMP_INIT := nvl(mri.IMP_INIT_S1,0);
			v_mr_c1.IMP_FINAL := nvl(mri.IMP_FINAL_S1,0);
			v_mr_c1.EXP_INIT := nvl(mri.EXP_INIT_S1,0);
			v_mr_c1.EXP_FINAL := nvl(mri.EXP_FINAL_S1,0);
			v_mr_c1.IMP_DIFF := v_mr_c1.IMP_FINAL - v_mr_c1.IMP_INIT ;
			v_mr_c1.EXP_DIFF := v_mr_c1.EXP_FINAL - v_mr_c1.EXP_INIT ;
			v_mr_c1.IMP_UNITS := round(nvl(v_mr_c1.IMP_DIFF * mri.mf,0),0);
			v_mr_c1.EXP_UNITS :=  round(nvl(v_mr_c1.EXP_DIFF * mri.mf,0),0);
			v_mr_c1.net_units := v_mr_c1.EXP_UNITS - v_mr_c1.IMP_UNITS ;
			if(v_mr_c1.net_units <0) THEN v_mr_c1.net_units := 0; END IF;
			v_mr_c1.CREATED_BY := v_created_By;
			v_mr_c1.CREATED_DATE := v_created_Date;
			INSERT INTO T_METER_READING_SLOT VALUES v_mr_c1;
	        v_log_result := log_activity('PROCEDURE','IMPORT_MR','log','slot-1',null,'', sysdate,V_BATCH_ID);


			--setting meter-reading for slot2
			v_mr_c2.ID := T_METER_READING_SLOT_SEQ.nextval;
			v_mr_c2.T_METER_READING_HDR_ID :=  v_mrh.id;
			v_mr_c2.SLOT_CODE := 'C2' ;
			v_mr_c2.IMP_INIT := nvl(mri.IMP_INIT_S2,0);
			v_mr_c2.IMP_FINAL := nvl(mri.IMP_FINAL_S2,0);
			v_mr_c2.EXP_INIT := nvl(mri.EXP_INIT_S2,0);
			v_mr_c2.EXP_FINAL := nvl(mri.EXP_FINAL_S2,0);
			v_mr_c2.IMP_DIFF := v_mr_c2.IMP_FINAL - v_mr_c2.IMP_INIT ;
			v_mr_c2.EXP_DIFF := v_mr_c2.EXP_FINAL - v_mr_c2.EXP_INIT ;
			v_mr_c2.IMP_UNITS := round(nvl(v_mr_c2.IMP_DIFF * mri.mf,0),0);
			v_mr_c2.EXP_UNITS := round(nvl(v_mr_c2.EXP_DIFF * mri.mf,0),0);
			v_mr_c2.net_units := v_mr_c2.EXP_UNITS - v_mr_c2.IMP_UNITS ;
			if(v_mr_c2.net_units <0) THEN v_mr_c2.net_units := 0; END IF;
			v_mr_c2.CREATED_BY := v_created_By;
			v_mr_c2.CREATED_DATE := v_created_Date;
			INSERT INTO T_METER_READING_SLOT VALUES v_mr_c2;
	        v_log_result := log_activity('PROCEDURE','IMPORT_MR','log','slot-2',null,'', sysdate,V_BATCH_ID);


			--setting meter-reading for slot3
			v_mr_c3.ID := T_METER_READING_SLOT_SEQ.nextval;
			v_mr_c3.T_METER_READING_HDR_ID :=  v_mrh.id;
			v_mr_c3.SLOT_CODE := 'C3' ;
			v_mr_c3.IMP_INIT := nvl(mri.IMP_INIT_S3,0);
			v_mr_c3.IMP_FINAL := nvl(mri.IMP_FINAL_S3,0);
			v_mr_c3.EXP_INIT := nvl(mri.EXP_INIT_S3,0);
			v_mr_c3.EXP_FINAL := nvl(mri.EXP_FINAL_S3,0);
			v_mr_c3.IMP_DIFF := v_mr_c3.IMP_FINAL - v_mr_c3.IMP_INIT ;
			v_mr_c3.EXP_DIFF := v_mr_c3.EXP_FINAL - v_mr_c3.EXP_INIT ;
			v_mr_c3.IMP_UNITS := round(nvl(v_mr_c3.IMP_DIFF * mri.mf,0),0);
			v_mr_c3.EXP_UNITS := round(nvl(v_mr_c3.EXP_DIFF * mri.mf,0),0);
			v_mr_c3.net_units := v_mr_c3.EXP_UNITS - v_mr_c3.IMP_UNITS ;
			if(v_mr_c3.net_units <0) THEN v_mr_c3.net_units := 0; END IF;
			v_mr_c3.CREATED_BY := v_created_By;
			v_mr_c3.CREATED_DATE := v_created_Date;
			INSERT INTO T_METER_READING_SLOT VALUES v_mr_c3;
	        v_log_result := log_activity('PROCEDURE','IMPORT_MR','log','slot-3',null,'', sysdate,V_BATCH_ID);

			--setting meter-reading for slot4
			v_mr_c4.ID := T_METER_READING_SLOT_SEQ.nextval;
			v_mr_c4.T_METER_READING_HDR_ID :=  v_mrh.id;
			v_mr_c4.SLOT_CODE := 'C4' ;
			v_mr_c4.IMP_INIT := nvl(mri.IMP_INIT_S4,0);
			v_mr_c4.IMP_FINAL := nvl(mri.IMP_FINAL_S4,0);
			v_mr_c4.EXP_INIT := nvl(mri.EXP_INIT_S4,0);
			v_mr_c4.EXP_FINAL := nvl(mri.EXP_FINAL_S4,0);
			v_mr_c4.IMP_DIFF := v_mr_c4.IMP_FINAL - v_mr_c4.IMP_INIT ;
			v_mr_c4.EXP_DIFF := v_mr_c4.EXP_FINAL - v_mr_c4.EXP_INIT ;
			v_mr_c4.IMP_UNITS := round(nvl(v_mr_c4.IMP_DIFF * mri.mf,0),0);
			v_mr_c4.EXP_UNITS := round(nvl(v_mr_c4.EXP_DIFF * mri.mf,0),0);
			v_mr_c4.net_units := v_mr_c4.EXP_UNITS - v_mr_c4.IMP_UNITS ;
			if(v_mr_c4.net_units <0) THEN v_mr_c4.net_units := 0; END IF;
			v_mr_c4.CREATED_BY := v_created_By;
			v_mr_c4.CREATED_DATE := v_created_Date;
			INSERT INTO T_METER_READING_SLOT VALUES v_mr_c4;
	        v_log_result := log_activity('PROCEDURE','IMPORT_MR','log','slot-4',null,'', sysdate,V_BATCH_ID);


			--setting meter-reading for slot5
			v_mr_c5.ID := T_METER_READING_SLOT_SEQ.nextval;
			v_mr_c5.T_METER_READING_HDR_ID :=  v_mrh.id;
			v_mr_c5.SLOT_CODE := 'C5' ;
			v_mr_c5.IMP_INIT := nvl(mri.IMP_INIT_S5,0);
			v_mr_c5.IMP_FINAL := nvl(mri.IMP_FINAL_S5,0);
			v_mr_c5.EXP_INIT := nvl(mri.EXP_INIT_S5,0);
			v_mr_c5.EXP_FINAL := nvl(mri.EXP_FINAL_S5,0);
			v_mr_c5.IMP_DIFF := v_mr_c5.IMP_FINAL - v_mr_c5.IMP_INIT ;
			v_mr_c5.EXP_DIFF := v_mr_c5.EXP_FINAL - v_mr_c5.EXP_INIT ;
			v_mr_c5.IMP_UNITS := round(nvl(v_mr_c5.IMP_DIFF * mri.mf,0),0);
			v_mr_c5.EXP_UNITS := round(nvl(v_mr_c5.EXP_DIFF * mri.mf,0),0);
			v_mr_c5.net_units := v_mr_c5.EXP_UNITS - v_mr_c5.IMP_UNITS ;
			if(v_mr_c5.net_units <0) THEN v_mr_c5.net_units := 0; END IF;
			v_mr_c5.CREATED_BY := v_created_By;
			v_mr_c5.CREATED_DATE := v_created_Date;
			INSERT INTO T_METER_READING_SLOT VALUES v_mr_c5;
	        v_log_result := log_activity('PROCEDURE','IMPORT_MR','log','slot-5',null,'', sysdate,V_BATCH_ID);


			UPDATE T_METER_READING_HDR SET
					total_import_gen = v_mr_c1.IMP_UNITS + v_mr_c2.IMP_UNITS + v_mr_c3.IMP_UNITS + v_mr_c4.IMP_UNITS + v_mr_c5.IMP_UNITS,
					total_export_gen = v_mr_c1.EXP_UNITS + v_mr_c2.EXP_UNITS + v_mr_c3.EXP_UNITS + v_mr_c4.EXP_UNITS + v_mr_c5.EXP_UNITS,
					net_gen_units = v_mr_c1.NET_UNITS + v_mr_c2.NET_UNITS + v_mr_c3.NET_UNITS + v_mr_c4.NET_UNITS + v_mr_c5.NET_UNITS
					WHERE id = v_mrh.id;
	        v_log_result := log_activity('PROCEDURE','IMPORT_MR','log','updated',null,'', sysdate,V_BATCH_ID);

	    		update IMP_MR_lines set STATUS_CODE = 'IMPORTED' where id = mri.id;

	   		v_imported := TRUE;

		    exception
			  when others then
			    v_exception_code := SQLCODE;
			    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
			    v_result := 'FAILURE';
			    v_reason := v_exception_code || ' - ' || v_exception_msg;
			    v_log_result := log_activity('PROCEDURE','IMPORT_MR','LOOP-EXCEPTION','End - '||V_BATCH_ID,v_result || ' - ' || v_reason,v_created_By, sysdate,V_BATCH_ID, mri.SERVICE_NO, mri.METER_NO);
			   -- -- dbms_output.put_line('IMPORT_MR - '||v_reason);
			END;
	  		v_result := 'SUCCESS'; -- resetting result after every loop. as individual loop's result doesnt affect overall procedure's result.
		 END LOOP;

	exception
	  when others then
	    v_exception_code := SQLCODE;
	    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
	    v_result := 'FAILURE';
	    v_reason := v_exception_code || ' - ' || v_exception_msg;
     v_log_result := log_activity('PROCEDURE','IMPORT_MR','EXCEPTION',v_reason,V_RESULT,v_created_By, sysdate,V_BATCH_ID);
	   -- -- dbms_output.put_line('IMPORT_MR - '||v_reason);
	END;
   <<THE_END>>

	IF (NOT v_imported) THEN
	  	v_result := 'FAILURE';
	  	v_reason := 'No clean meter reading available to process';
	END IF;


	select count(STATUS_CODE) into v_success_count from imp_mr_lines where imp_mr_header_id= v_batch_id and status_code='IMPORTED';
	select count(STATUS_CODE) into v_error_count from imp_mr_lines where imp_mr_header_id= v_batch_id and status_code<>'IMPORTED';

	IF V_RESULT = 'SUCCESS' THEN
	    update imp_mr_header set status='COMPLETED', REMARKS = '' , success_count= v_success_count, error_count= v_error_count where id = v_batch_id;
	ELSE
	    update imp_mr_header set status='IMPORT-ERROR', REMARKS=v_reason , success_count= v_success_count, error_count= v_error_count where id = v_batch_id;
	    v_result := v_result || ' - ' || v_reason;
	END IF;

	COMMIT;

	v_log_result := log_activity('PROCEDURE','IMPORT_MR','END','End - '||V_BATCH_ID,V_RESULT,v_created_By, sysdate,V_BATCH_ID);

	return v_result;

END IMPORT_MR;

