--------------------------------------------------------
--  File created - Monday-April-09-2018   
--------------------------------------------------------
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
