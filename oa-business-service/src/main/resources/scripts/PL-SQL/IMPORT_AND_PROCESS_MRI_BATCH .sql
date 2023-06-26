--------------------------------------------------------
--  File created - Monday-April-09-2018   
--------------------------------------------------------
DROP FUNCTION "OPENACCESS"."IMPORT_AND_PROCESS_MRI_BATCH";
DROP FUNCTION "OPENACCESS"."CREATE_GS";
DROP FUNCTION "OPENACCESS"."IMPORT_MR";
DROP FUNCTION "OPENACCESS"."LOG_ACTIVITY";
--------------------------------------------------------
--  DDL for Function IMPORT_AND_PROCESS_MRI_BATCH
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "OPENACCESS"."IMPORT_AND_PROCESS_MRI_BATCH" 
(
  v_imp_batch_id in varchar2
) RETURN VARCHAR2 AS 

v_batch_based boolean:=false;
v_duration_based boolean:=false;
v_process_id  VARCHAR2(50); 
v_records_processed number:=0;
v_created_Date DATE := SYSDATE;
v_created_By  varchar2(50):= 'admin';
v_status varchar2(50);
v_reason varchar2(200);
v_exception_code  NUMBER;
v_exception_msg  VARCHAR2(200);
v_result varchar(300):='SUCCESS';
v_log_result varchar(300):='SUCCESS'; 

BEGIN

	BEGIN		

    v_log_result := log_activity('PROCEDURE','IMPORT_AND_PROCESS_MRI_BATCH','START','Start',NULL,v_created_By, sysdate,v_imp_batch_id);
    v_result := import_mr(v_imp_batch_id);
    v_log_result := log_activity('PROCEDURE','IMPORT_AND_PROCESS_MRI_BATCH','import_mr','called',v_result,v_created_By, sysdate,v_imp_batch_id);

    if (instr(v_result,'SUCCESS') > 0) then 
	    v_result := create_gs(v_imp_batch_id, null, null);
	    v_log_result := log_activity('PROCEDURE','IMPORT_AND_PROCESS_MRI_BATCH','create_gs','called',v_result,v_created_By, sysdate,v_imp_batch_id);
	     if (instr(v_result,'FAILURE') > 0) then
	        v_reason := v_result;
            v_result := 'FAILURE';
	      end if;
    ELSE
     v_reason := v_result;
     -- v_result := 'FAILURE'; 
    end if;


	exception
	  when others then
	    v_exception_code := SQLCODE;
	    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
	    v_result := 'FAILURE';
	    v_reason := v_exception_code || ' - ' || v_exception_msg;
	    dbms_output.put_line(v_reason);
	END;
   <<THE_END>>

  v_log_result := log_activity('PROCEDURE','IMPORT_AND_PROCESS_MRI_BATCH','END',v_reason,v_result,v_created_By, sysdate,v_imp_batch_id );

  COMMIT;

  return v_result; 

END IMPORT_AND_PROCESS_MRI_BATCH;

/
--------------------------------------------------------
--  DDL for Function CREATE_GS
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "OPENACCESS"."CREATE_GS" 
(
  v_imp_batch_id in varchar2,
  v_month IN VARCHAR2,
  v_year in varchar2
) RETURN VARCHAR2 AS 

v_batch_based boolean:=false;
v_duration_based boolean:=false;
v_process_id  VARCHAR2(50); 
v_records_processed number:=0;
v_created_Date DATE := SYSDATE;
v_created_By  varchar2(50):= 'admin';
v_status varchar2(50);
v_reason varchar2(200);
v_exception_code  NUMBER;
v_exception_msg  VARCHAR2(200);
v_result varchar(300):='SUCCESS';
v_log_result varchar(300):='SUCCESS'; 
v_mr_id varchar2(200);
v_mr_hdr_init_rdr_date varchar2(50);
v_mr_hdr_gen_stmt_id varchar2(200);
v_seller_service_id varchar2(200);
V_TRADE_REL_COUNT varchar2(50);
v_create_stb_result varchar2(50);

BEGIN

	BEGIN		

      v_log_result := log_activity('PROCEDURE','create_gs','Start',v_result,v_reason,v_created_By, sysdate,v_imp_batch_id, v_month, v_year);

    -- this flag might be of better use in future
    if (v_imp_batch_id is null or v_imp_batch_id = '')then
      v_duration_based:= true;
    else
      v_batch_based := true;
    end if;

    v_process_id := T_PROCESS_GS_SEQ.nextval;
    INSERT INTO T_PROCESS_GS (ID,SYS_DT,STATUS,START_DT,END_DT,REMARKS)
      VALUES (v_process_id,v_created_Date,'PROCESSING',v_created_Date,NULL,NULL) ;

    if(v_duration_based) then
      FOR mr IN (SELECT mh.id FROM T_METER_READING_HDR mh  WHERE mh.M_GEN_STMT_ID IS NULL AND mh.GS_BATCH_ID IS NULL 					
                      AND READING_MONTH = v_month AND READING_YEAR = v_year ) 
      LOOP
          v_result := create_gs_from_mr( v_process_id,mr.id);
          if (v_result='SUCCESS') then
            v_records_processed := v_records_processed +1;
          end if;
      END LOOP;
    else	
      FOR mr IN (SELECT mh.id,mh.INIT_READING_DT,mh.M_GEN_STMT_ID GEN_STMT_ID,companymeter.M_COMPANY_SERVICE_ID
      FROM T_METER_READING_HDR mh LEFT JOIN M_COMPANY_METER companymeter on mh.M_COMPANY_METER_ID = companymeter.id WHERE mh.M_GEN_STMT_ID IS NULL AND mh.GS_BATCH_ID IS NULL 					
                    and mh.imp_batch_id = v_imp_batch_id)

      LOOP
          v_result:= create_gs_from_mr(v_process_id,mr.id);

          if (v_result='SUCCESS') then
            v_records_processed := v_records_processed +1;      
            SELECT mh.id,mh.INIT_READING_DT,mh.M_GEN_STMT_ID GEN_STMT_ID,companymeter.M_COMPANY_SERVICE_ID INTO v_mr_id,v_mr_hdr_init_rdr_date,v_mr_hdr_gen_stmt_id,v_seller_service_id
      FROM T_METER_READING_HDR mh LEFT JOIN M_COMPANY_METER companymeter on mh.M_COMPANY_METER_ID = companymeter.id WHERE mh.imp_batch_id = v_imp_batch_id  and mh.id = mr.id ;
--		v_log_result := log_activity('PROCEDURE','create_gs','v_mr_id',v_mr_id,v_result,v_created_By, v_created_Date,v_imp_batch_id);
--        v_log_result := log_activity('PROCEDURE','create_gs','v_mr_hdr_init_rdr_date',v_mr_hdr_init_rdr_date,v_result,v_created_By, v_created_Date,v_imp_batch_id);
--        v_log_result := log_activity('PROCEDURE','create_gs','v_mr_hdr_gen_stmt_id',v_mr_hdr_gen_stmt_id,v_result,v_created_By, v_created_Date,v_imp_batch_id);
--        v_log_result := log_activity('PROCEDURE','create_gs','v_seller_service_id',v_seller_service_id,v_result,v_created_By, v_created_Date,v_imp_batch_id);
--   


            SELECT COUNT(*) INTO V_TRADE_REL_COUNT FROM M_TRADE_RELATIONSHIP TRADE WHERE M_SELLER_COMP_SERVICE_ID = mr.M_COMPANY_SERVICE_ID AND M_BUYER_COMPANY_ID = 'TNEB' AND mr.INIT_READING_DT BETWEEN TRADE.FROM_DATE AND TRADE.TO_DATE;
--               v_log_result := log_activity('PROCEDURE','create_gs','V_TRADE_REL_COUNT',V_TRADE_REL_COUNT,v_result,v_created_By, v_created_Date,v_imp_batch_id);
            IF V_TRADE_REL_COUNT>0 THEN
                v_create_stb_result := CREATE_STB(v_mr_hdr_gen_stmt_id);
            END IF;
          end if;
      END LOOP;

	end if;

	exception
	  when others then
	    v_exception_code := SQLCODE;
	    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
	    v_result := 'FAILURE';
	    v_reason := v_exception_code || ' - ' || v_exception_msg;
	    dbms_output.put_line(v_reason);
      v_log_result := log_activity('PROCEDURE','create_gs','EH',v_reason,v_result,v_created_By, sysdate,v_imp_batch_id);
	END;
   <<THE_END>>

	update T_PROCESS_GS set status='COMPLETED', remarks='PROCESSED-RECORD-COUNT:'||v_records_processed, end_dt=sysdate where id = v_process_id;

    v_log_result := log_activity('PROCEDURE','create_gs','End',v_result,v_reason,v_created_By, sysdate,v_imp_batch_id, v_month,v_year);
  COMMIT;

  return v_result; 

END create_gs;

/
--------------------------------------------------------
--  DDL for Function IMPORT_MR
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "OPENACCESS"."IMPORT_MR" 
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
v_status varchar2(50);
v_reason varchar2(200);
v_exception_code  NUMBER;
v_exception_msg  VARCHAR2(200);
v_result varchar(300):='SUCCESS';
v_log_result VARCHAR2(50):='';

BEGIN

	BEGIN
    v_log_result := log_activity('PROCEDURE','IMPORT_MR','START','Start - '||V_BATCH_ID,V_RESULT,v_created_By, sysdate,V_BATCH_ID);

	  select status into v_status from imp_mr_header where id =v_batch_id;

        v_log_result := log_activity('PROCEDURE','IMPORT_MR','v_status',v_status,null,v_created_By, sysdate,V_BATCH_ID);

	  -- if the batch is already processing, then stop flow
	  if(v_status = 'PROCESSING') then
	    v_reason := 'Batch is already in processing. please wait';
	    V_RESULT := 'FAILURE';
	    GOTO THE_END;
	  end if;

	  -- set the batch to PROCESSING
	  update imp_mr_header set status='PROCESSING' where id = v_batch_id;

    v_log_result := log_activity('PROCEDURE','IMPORT_MR','PROCESSING',null,null,v_created_By, sysdate,V_BATCH_ID);

	  -- cleanup the imported meter lines, by ignoring dirty data
	  v_result := CLEANSE_MRI(v_batch_id);

    v_log_result := log_activity('PROCEDURE','IMPORT_MR','CLEANSE_MRI',v_result,null,v_created_By, sysdate,V_BATCH_ID);

	 if(substr(v_result,0,7) = 'FAILURE') THEN 
		 --dbms_output.put_line('cleanse result - '||v_result); 
	 	GOTO THE_END; 
	 END IF;

	 --loop through clean imported meter entries
	 FOR mri IN (SELECT mri.* FROM IMP_MR_lines mri WHERE mri.IMP_MR_HEADER_ID = v_batch_id and mri.status_code = 'CLEANSED')
	 LOOP
   begin

	  	-- get meter-id
	  	SELECT DISTINCT meter.id INTO v_meter_id FROM M_COMPANY_METER  meter
	  		JOIN M_COMPANY_SERVICE ser ON ser."number" = mri.SERVICE_NO AND meter.M_COMPANY_SERVICE_ID = ser.id WHERE METER_NUMBER = mri.meter_no;
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
		v_mrh.EXP_RKVAH_FINAL := mri.EXP_RKVAH_FINAL; v_mrh.EXP_RKVAH_INIT := mri.EXP_RKVAH_INIT;
		v_mrh.IMP_RKVAH_FINAL := mri.IMP_RKVAH_FINAL; v_mrh.IMP_RKVAH_INIT := mri.IMP_RKVAH_INIT;		
		v_mrh.EXP_KVAH_FINAL := mri.EXP_KVAH_FINAL; v_mrh.EXP_KVAH_INIT := mri.EXP_KVAH_INIT;
		v_mrh.IMP_KVAH_FINAL := mri.IMP_KVAH_FINAL; v_mrh.IMP_KVAH_INIT := mri.IMP_KVAH_INIT;
		v_mrh.RKVAH_DIFF := nvl(mri.IMP_RKVAH_FINAL,0) - nvl(mri.IMP_RKVAH_INIT, 0);
		v_mrh.RKVAH_UNITS := v_mrh.RKVAH_DIFF*mri.mf;
		v_mrh.KVAH_DIFF := nvl(mri.EXP_KVAH_FINAL - mri.EXP_KVAH_INIT - (mri.IMP_KVAH_FINAL - mri.IMP_KVAH_INIT), 0);
		v_mrh.KVAH_UNITS := v_mrh.KVAH_DIFF*mri.mf;
		--v_mrh.TOTAL_IMPORT_GEN := ;
		--v_mrh.TOTAL_EXPORT_GEN := ;
		v_mrh.CREATED_BY := v_created_By;
		v_mrh.CREATED_DATE := v_created_Date;
		INSERT INTO T_METER_READING_HDR VALUES v_mrh;

		--setting meter-reading for slot1
		v_mr_c1.ID := T_METER_READING_SLOT_SEQ.nextval;
		v_mr_c1.T_METER_READING_HDR_ID :=  v_mrh.id;
		v_mr_c1.SLOT_CODE := 'C1' ;
		v_mr_c1.IMP_INIT := mri.IMP_INIT_S1;
		v_mr_c1.IMP_FINAL := mri.IMP_FINAL_S1;
		v_mr_c1.EXP_INIT := mri.EXP_INIT_S1;
		v_mr_c1.EXP_FINAL := mri.EXP_FINAL_S1;
		v_mr_c1.IMP_DIFF := mri.IMP_FINAL_S1 - mri.IMP_INIT_S1;
		v_mr_c1.EXP_DIFF := mri.EXP_FINAL_S1 - mri.EXP_INIT_S1;
		v_mr_c1.IMP_UNITS := v_mr_c1.IMP_DIFF * mri.mf;
		v_mr_c1.EXP_UNITS := v_mr_c1.EXP_DIFF * mri.mf;
		v_mr_c1.net_units := v_mr_c1.EXP_UNITS - v_mr_c1.IMP_UNITS ;
		if(v_mr_c1.net_units <0) THEN v_mr_c1.net_units := 0; END IF;
		v_mr_c1.CREATED_BY := v_created_By;
		v_mr_c1.CREATED_DATE := v_created_Date;
		INSERT INTO T_METER_READING_SLOT VALUES v_mr_c1;

		--setting meter-reading for slot2
		v_mr_c2.ID := T_METER_READING_SLOT_SEQ.nextval;
		v_mr_c2.T_METER_READING_HDR_ID :=  v_mrh.id;
		v_mr_c2.SLOT_CODE := 'C2' ;
		v_mr_c2.IMP_INIT := mri.IMP_INIT_S2;
		v_mr_c2.IMP_FINAL := mri.IMP_FINAL_S2;
		v_mr_c2.EXP_INIT := mri.EXP_INIT_S2;
		v_mr_c2.EXP_FINAL := mri.EXP_FINAL_S2;
		v_mr_c2.IMP_DIFF := mri.IMP_FINAL_S2 - mri.IMP_INIT_S2;
		v_mr_c2.EXP_DIFF := mri.EXP_FINAL_S2 - mri.EXP_INIT_S2;
		v_mr_c2.IMP_UNITS := v_mr_c2.IMP_DIFF * mri.mf;
		v_mr_c2.EXP_UNITS := v_mr_c2.EXP_DIFF * mri.mf;
		v_mr_c2.net_units := v_mr_c2.EXP_UNITS - v_mr_c2.IMP_UNITS ;
		if(v_mr_c2.net_units <0) THEN v_mr_c2.net_units := 0; END IF;
		v_mr_c2.CREATED_BY := v_created_By;
		v_mr_c2.CREATED_DATE := v_created_Date;
		INSERT INTO T_METER_READING_SLOT VALUES v_mr_c2;

		--setting meter-reading for slot3
		v_mr_c3.ID := T_METER_READING_SLOT_SEQ.nextval;
		v_mr_c3.T_METER_READING_HDR_ID :=  v_mrh.id;
		v_mr_c3.SLOT_CODE := 'C3' ;
		v_mr_c3.IMP_INIT := mri.IMP_INIT_S3;
		v_mr_c3.IMP_FINAL := mri.IMP_FINAL_S3;
		v_mr_c3.EXP_INIT := mri.EXP_INIT_S3;
		v_mr_c3.EXP_FINAL := mri.EXP_FINAL_S3;
		v_mr_c3.IMP_DIFF := mri.IMP_FINAL_S3 - mri.IMP_INIT_S3;
		v_mr_c3.EXP_DIFF := mri.EXP_FINAL_S3 - mri.EXP_INIT_S3;
		v_mr_c3.IMP_UNITS := v_mr_c3.IMP_DIFF * mri.mf;
		v_mr_c3.EXP_UNITS := v_mr_c3.EXP_DIFF * mri.mf;
		v_mr_c3.net_units := v_mr_c3.EXP_UNITS - v_mr_c3.IMP_UNITS ;
		if(v_mr_c3.net_units <0) THEN v_mr_c3.net_units := 0; END IF;
		v_mr_c3.CREATED_BY := v_created_By;
		v_mr_c3.CREATED_DATE := v_created_Date;
		INSERT INTO T_METER_READING_SLOT VALUES v_mr_c3;

		--setting meter-reading for slot4
		v_mr_c4.ID := T_METER_READING_SLOT_SEQ.nextval;
		v_mr_c4.T_METER_READING_HDR_ID :=  v_mrh.id;
		v_mr_c4.SLOT_CODE := 'C4' ;
		v_mr_c4.IMP_INIT := mri.IMP_INIT_S4;
		v_mr_c4.IMP_FINAL := mri.IMP_FINAL_S4;
		v_mr_c4.EXP_INIT := mri.EXP_INIT_S4;
		v_mr_c4.EXP_FINAL := mri.EXP_FINAL_S4;
		v_mr_c4.IMP_DIFF := mri.IMP_FINAL_S4 - mri.IMP_INIT_S4;
		v_mr_c4.EXP_DIFF := mri.EXP_FINAL_S4 - mri.EXP_INIT_S4;
		v_mr_c4.IMP_UNITS := v_mr_c4.IMP_DIFF * mri.mf;
		v_mr_c4.EXP_UNITS := v_mr_c4.EXP_DIFF * mri.mf;
		v_mr_c4.net_units := v_mr_c4.EXP_UNITS - v_mr_c4.IMP_UNITS ;
		if(v_mr_c4.net_units <0) THEN v_mr_c4.net_units := 0; END IF;
		v_mr_c4.CREATED_BY := v_created_By;
		v_mr_c4.CREATED_DATE := v_created_Date;
		INSERT INTO T_METER_READING_SLOT VALUES v_mr_c4;

		--setting meter-reading for slot5
		v_mr_c5.ID := T_METER_READING_SLOT_SEQ.nextval;
		v_mr_c5.T_METER_READING_HDR_ID :=  v_mrh.id;
		v_mr_c5.SLOT_CODE := 'C5' ;
		v_mr_c5.IMP_INIT := mri.IMP_INIT_S5;
		v_mr_c5.IMP_FINAL := mri.IMP_FINAL_S5;
		v_mr_c5.EXP_INIT := mri.EXP_INIT_S5;
		v_mr_c5.EXP_FINAL := mri.EXP_FINAL_S5;
		v_mr_c5.IMP_DIFF := mri.IMP_FINAL_S5 - mri.IMP_INIT_S5;
		v_mr_c5.EXP_DIFF := mri.EXP_FINAL_S5 - mri.EXP_INIT_S5;
		v_mr_c5.IMP_UNITS := v_mr_c5.IMP_DIFF * mri.mf;
		v_mr_c5.EXP_UNITS := v_mr_c5.EXP_DIFF * mri.mf;
		v_mr_c5.net_units := v_mr_c5.EXP_UNITS - v_mr_c5.IMP_UNITS ;
		if(v_mr_c5.net_units <0) THEN v_mr_c5.net_units := 0; END IF;
		v_mr_c5.CREATED_BY := v_created_By;
		v_mr_c5.CREATED_DATE := v_created_Date;
		INSERT INTO T_METER_READING_SLOT VALUES v_mr_c5;

		UPDATE T_METER_READING_HDR SET 			
				total_import_gen = v_mr_c1.IMP_UNITS + v_mr_c2.IMP_UNITS + v_mr_c3.IMP_UNITS + v_mr_c4.IMP_UNITS + v_mr_c5.IMP_UNITS,				
				total_export_gen = v_mr_c1.EXP_UNITS + v_mr_c2.EXP_UNITS + v_mr_c3.EXP_UNITS + v_mr_c4.EXP_UNITS + v_mr_c5.EXP_UNITS,				
				net_gen_units = v_mr_c1.NET_UNITS + v_mr_c2.NET_UNITS + v_mr_c3.NET_UNITS + v_mr_c4.NET_UNITS + v_mr_c5.NET_UNITS
				WHERE id = v_mrh.id;

		v_imported := TRUE;

    exception
	  when others then
	    v_exception_code := SQLCODE;
	    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
	    v_result := 'FAILURE';
	    v_reason := v_exception_code || ' - ' || v_exception_msg;
	    v_log_result := log_activity('PROCEDURE','IMPORT_MR','CLEANSED-MRI-LOOP','End - '||V_BATCH_ID,v_result || ' - ' || v_reason,v_created_By, sysdate,V_BATCH_ID);
	   -- dbms_output.put_line('IMPORT_MR - '||v_reason);
	END;
	 END LOOP;

	exception
	  when others then
	    v_exception_code := SQLCODE;
	    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
	    v_result := 'FAILURE';
	    v_reason := v_exception_code || ' - ' || v_exception_msg;
     v_log_result := log_activity('PROCEDURE','IMPORT_MR','EXCEPTION',v_reason,V_RESULT,v_created_By, sysdate,V_BATCH_ID);
	   -- dbms_output.put_line('IMPORT_MR - '||v_reason);
	END;
   <<THE_END>>

  IF (NOT v_imported) THEN
  	v_result := 'FAILURE';
  	v_reason := 'No clean meter reading available to process';
  END IF;

  IF V_RESULT = 'SUCCESS' THEN
    update imp_mr_header set status='COMPLETED', REMARKS = '' where id = v_batch_id;
  ELSE
    update imp_mr_header set status='IMPORT-ERROR', REMARKS=v_reason where id = v_batch_id;
    v_result := v_result || ' - ' || v_reason;
  END IF;

  COMMIT;

 v_log_result := log_activity('PROCEDURE','IMPORT_MR','END','End - '||V_BATCH_ID,V_RESULT,v_created_By, sysdate,V_BATCH_ID);

  return v_result; 

END IMPORT_MR;

/
--------------------------------------------------------
--  DDL for Function LOG_ACTIVITY
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "OPENACCESS"."LOG_ACTIVITY" 
(
  v_type IN VARCHAR2,
  v_process IN VARCHAR2,
  v_activity IN VARCHAR2,
  v_message IN VARCHAR2,
  v_result IN VARCHAR2,
  v_created_by IN VARCHAR2,
  v_created_dt IN date,
  v_att1 in varchar2 default null,
  v_att2 in varchar2 default null,
  v_att3 in varchar2 default null
) RETURN VARCHAR2 AS 
BEGIN
	BEGIN
			INSERT INTO OPENACCESS.T_ACTIVITY_LOG (ID,PROCESS_TYPE,PROCESS_NAME,ACTIVITY_NAME,MESSAGE,"result",CREATED_BY,CREATED_DT, att1, att2, att3)
			VALUES (t_activity_log_seq.nextval,v_type,v_process,v_activity,v_message,v_result,v_created_by,sysdate,v_att1,v_att2,v_att3) ;
			COMMIT;
	exception
	  when others then
		dbms_output.put_line(SUBSTR(SQLERRM, 1, 200));
	END;
  return 'SUCCESS'; 
END log_activity;


/
-- Unable to render SYNONYM DDL for object PUBLIC.DBMS_OUTPUT with DBMS_METADATA attempting internal generator.
CREATE PUBLIC SYNONYM DBMS_OUTPUT FOR SYS.DBMS_OUTPUT
