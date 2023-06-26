create or replace FUNCTION "LOG_ACTIVITY"
(
  v_type IN VARCHAR2,
  v_process IN VARCHAR2,
  v_activity IN VARCHAR2,
  v_message IN VARCHAR2,
  v_result IN VARCHAR2,
  v_created_by IN VARCHAR2,
  v_att1 in varchar2 default null,
  v_att2 in varchar2 default null,
  v_att3 in varchar2 default null,
  v_att4 in varchar2 default null
) RETURN VARCHAR2 AS
BEGIN
	BEGIN
    
			INSERT INTO T_ACTIVITY_LOG (ID,PROCESS_TYPE,PROCESS_NAME,ACTIVITY_NAME,MESSAGE,"result",CREATED_BY,CREATED_DT, att1, att2, att3, att4)
			VALUES (t_activity_log_seq.nextval,v_type,v_process,v_activity,v_message,v_result,v_created_by,sysdate,v_att1,v_att2,v_att3,v_att4) ;
			COMMIT;
            
	exception
	  when others then
                  dbms_output.put_line('FLUSH LOGGIN_ERRORS table-'||SUBSTR(SQLERRM, 1, 200));
        /*
        begin
            insert into LOGGING_ERRORS values(SUBSTR(SQLERRM, 1, 200), sysdate);
        exception
        when others then
            dbms_output.put_line('FLUSH LOGGIN_ERRORS table-'||SUBSTR(SQLERRM, 1, 200));
        end;
        */
	END;
  return 'SUCCESS';
END log_activity;