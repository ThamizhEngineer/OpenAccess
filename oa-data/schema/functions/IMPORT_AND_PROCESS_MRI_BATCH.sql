CREATE OR REPLACE FUNCTION OPENACCESS."IMPORT_AND_PROCESS_MRI_BATCH" 
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

    -- goto THE_END;
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
	    ---- dbms_output.put_line(v_reason);
      v_log_result := log_activity('PROCEDURE','IMPORT_AND_PROCESS_MRI_BATCH','EH',v_reason,v_result,v_created_By, sysdate,v_imp_batch_id );
	END;
   <<THE_END>>

  v_log_result := log_activity('PROCEDURE','IMPORT_AND_PROCESS_MRI_BATCH','END',v_reason,v_result,v_created_By, sysdate,v_imp_batch_id );

  COMMIT;

  return v_result;

END IMPORT_AND_PROCESS_MRI_BATCH;

