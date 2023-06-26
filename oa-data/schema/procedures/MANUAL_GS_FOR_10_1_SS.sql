CREATE OR REPLACE PROCEDURE OPENACCESS."MANUAL_GS_FOR_10_1_SS" (i_remarks in varchar2,
  o_result_code out varchar2, o_result_desc out varchar2) 
  is
    v_created_by varchar2(100):='admin';
    v_log_result  varchar2(50);
    v_reason varchar2(200):='';
    v_exception_code  NUMBER;
    v_exception_msg  VARCHAR2(200);
  begin
  BEGIN  -- exception handling start
      v_log_result := log_activity('PROCEDURE','MANUAL_GS_FOR_10_1_SS','START',I_remarks,'',v_created_by, sysdate,i_remarks);

      if(i_remarks is null or i_remarks = '' ) then
        Raise_Application_Error (-20343, 'i_remarks is mandatory  ');
      end if;

      update MANUAL_GS_101SS set PROCESSED=null,update_REMARKS=null where remarks = i_remarks;

      for ss in (select * from MANUAL_GS_101SS where REMARKS = i_remarks)
       loop
       begin
--         v_log_result := log_activity('PROCEDURE','MANUAL_GS_FOR_10_1_SS','inside for loop',ss.SUBSTATION_ID,'',v_created_by, sysdate,i_remarks);
--       -- dbms_output.put_line('VALUES:'||ss.SUBSTATION_ID||','||ss.GEN_SERVICE_NUMBER||','||ss.ORG_ID||','||ss.reading_month);
       GENERATION_STATEMENT.CREATE_WITH_SS_LOSS(ss.SUBSTATION_ID,ss.GEN_SERVICE_NUMBER ,ss.ORG_ID,ss.reading_month,ss.reading_year ,O_RESULT_CODE,O_RESULT_DESC );
--       -- dbms_output.put_line('SERVICE :'||ss.GEN_SERVICE_NUMBER);
         exception
        when others then
          v_exception_code := SQLCODE;
          v_exception_msg := SUBSTR(SQLERRM, 1, 200);
          o_result_code := 'FAILURE';
          o_result_desc := v_exception_code || ' - ' || v_exception_msg;
          -- -- dbms_output.put_line(o_result_desc);
          v_log_result := log_activity('PROCEDURE','MANUAL_GS_FOR_10_1_SS','EXCEPTION',o_result_desc,i_remarks,'', sysdate,i_remarks);

       END;
      end loop;

      o_result_code :='SUCCESS';
      exception
      when others then
        v_exception_code := SQLCODE;
        v_exception_msg := SUBSTR(SQLERRM, 1, 200);
        o_result_code := 'FAILURE';
        o_result_desc := v_exception_code || ' - ' || v_exception_msg;
        -- dbms_output.put_line(o_result_desc);
      END;
          commit;
       v_log_result := log_activity('PROCEDURE','MANUAL_GS_FOR_10_1_SS','RESULT',o_result_code,o_result_desc,v_created_by, sysdate,i_remarks);
	END MANUAL_GS_FOR_10_1_SS;

