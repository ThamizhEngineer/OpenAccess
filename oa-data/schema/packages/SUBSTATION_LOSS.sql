CREATE OR REPLACE PACKAGE SUBSTATION_LOSS AS 

  /* TODO enter package declarations (types, exceptions, methods etc) here */ 
PROCEDURE START_IMPORT(I_BATCH_KEY IN VARCHAR2, O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2);
PROCEDURE CLEANSE(I_BATCH_KEY IN VARCHAR2, O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2);
PROCEDURE IMPORT(I_BATCH_KEY IN VARCHAR2, O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2);

END SUBSTATION_LOSS;


CREATE OR REPLACE PACKAGE BODY SUBSTATION_LOSS AS

PROCEDURE START_IMPORT(I_BATCH_KEY IN VARCHAR2, O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2) IS
    v_log_result  varchar2(50);
    v_reason varchar2(200):='';
    v_exception_code  NUMBER;
    v_exception_msg  VARCHAR2(200);
    v_created_by varchar2(100):='ADMIN';
    v_ss_loss t_substation_loss%ROWTYPE;
    v_Ss_count number:=0;
    v_def1 VARCHAR2(50) default null;
    v_def2 VARCHAR2(50) default null;
    BEGIN
    BEGIN --EXCEPTION STARTS HERE
    v_log_result := log_activity('PROCEDURE','SUBSTATION_LOSS.START_IMPORT','START_IMPORT','Start - '||I_BATCH_KEY,'','', sysdate,I_BATCH_KEY);
    SUBSTATION_LOSS.CLEANSE(I_BATCH_KEY,O_RESULT_CODE,O_RESULT_DESC);
    IF(O_RESULT_CODE = 'ERROR' OR O_RESULT_CODE = 'FAILURE') THEN GOTO THE_END; END IF;

    SUBSTATION_LOSS.IMPORT(I_BATCH_KEY,O_RESULT_CODE,O_RESULT_DESC);
    IF(O_RESULT_CODE = 'ERROR' OR O_RESULT_CODE = 'FAILURE') THEN GOTO THE_END; END IF;
    
    SELECT COUNT(*) into v_Ss_count FROM t_substation_loss WHERE batch_key=I_BATCH_KEY;

     IF(v_Ss_count>1)THEN
    SELECT * into v_ss_loss FROM t_substation_loss WHERE batch_key=I_BATCH_KEY AND ROWNUM=1;
    GENERATION_STATEMENT.CREATE_WITH_SS_LOSS(v_def1,v_def2,v_ss_loss.M_ORG_ID,v_ss_loss.MONTH,v_ss_loss.YEAR,O_RESULT_CODE,O_RESULT_DESC);
    ELSIF(v_Ss_count=1)THEN
    SELECT * into v_ss_loss FROM t_substation_loss WHERE batch_key=I_BATCH_KEY AND ROWNUM=1;
    GENERATION_STATEMENT.CREATE_WITH_SS_LOSS(v_ss_loss.M_SUBSTATION_ID,v_def2,v_ss_loss.M_ORG_ID,v_ss_loss.MONTH,v_ss_loss.YEAR,O_RESULT_CODE,O_RESULT_DESC);
    END IF;
    
    exception
        when others then
          v_exception_code := SQLCODE;
          v_exception_msg := SUBSTR(SQLERRM, 1, 200);
          o_result_code := 'FAILURE';
          o_result_desc := v_exception_code || ' - ' || v_exception_msg;
          v_log_result := log_activity('PROCEDURE','SUBSTATION_LOSS.START_IMPORT','EXCEPTION',O_RESULT_DESC,'','', sysdate,I_BATCH_KEY);
    END;--EXCEPTION ENDS HERE
    <<THE_END>>
    COMMIT;
    v_log_result := log_activity('PROCEDURE','SUBSTATION_LOSS.START_IMPORT','RESULT',O_RESULT_CODE,O_RESULT_DESC,v_created_by,sysdate,I_BATCH_KEY);

  END START_IMPORT;
  
  PROCEDURE CLEANSE(I_BATCH_KEY IN VARCHAR2, O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2) IS
    v_log_result  varchar2(50);
    v_reason varchar2(200):='';
    v_exception_code  NUMBER;
    v_exception_msg  VARCHAR2(200);
    v_imp_loss_cursor sys_refcursor ;
    v_imp_ss_loss IMP_SUBSTATION_LOSS%ROWTYPE;
    v_org_name  varchar2(50);v_ss_name  varchar2(50);
    v_org_count  NUMBER:=0;v_ss_count  NUMBER:=0;
    v_number NUMBER;
    BEGIN
    BEGIN --EXCEPTION STARTS HERE
    v_log_result := log_activity('PROCEDURE','SUBSTATION_LOSS.CLEANSE','CLEANSE','Start - '||I_BATCH_KEY,'','', sysdate,I_BATCH_KEY);

   --------------------------------------------------------
    OPEN v_imp_loss_cursor for select * from IMP_SUBSTATION_LOSS where BATCH_KEY=I_BATCH_KEY;
    LOOP
    FETCH v_imp_loss_cursor INTO v_imp_ss_loss;
    EXIT WHEN v_imp_loss_cursor%NOTFOUND;
	
   SELECT COUNT(*) INTO v_org_count FROM M_ORG WHERE ID=v_imp_ss_loss.M_ORG_ID;
   SELECT COUNT(*) INTO v_ss_count FROM M_SUBSTATION WHERE ID=v_imp_ss_loss.M_SUBSTATION_ID;
  
	IF(v_org_count=1 AND v_ss_count=1)THEN 
        v_log_result := log_activity('PROCEDURE','SUBSTATION_LOSS.CLEANSE','COUNT-CONDITION','ORG-COUNT-'||v_org_count,'SS_COUNT-'||v_ss_count,'', sysdate,I_BATCH_KEY);

   		SELECT NAME INTO v_org_name FROM M_ORG WHERE ID=v_imp_ss_loss.M_ORG_ID;
   		SELECT NAME INTO v_ss_name FROM M_SUBSTATION WHERE ID=v_imp_ss_loss.M_SUBSTATION_ID;
   		
		SELECT IS_NUMBER(v_imp_ss_loss.LOSS_PERCENT) INTO v_number FROM dual;		
 		IF v_number=1 THEN
 		UPDATE IMP_SUBSTATION_LOSS SET M_SUBSTATION_NAME=v_ss_name,M_ORG_NAME=v_org_name,IS_IMPORTED='Y',RESULT_DESC=O_RESULT_DESC WHERE ID=v_imp_ss_loss.ID;
 		ELSE 
 		v_reason:='LOSS PERCENT IS NOT NUMBER';
 	    v_log_result := log_activity('PROCEDURE','SUBSTATION_LOSS.CLEANSE','CLEANSE',v_imp_ss_loss.M_ORG_ID,v_reason,v_imp_ss_loss.M_SUBSTATION_ID, sysdate,I_BATCH_KEY);
        o_result_desc:=v_reason;
 		UPDATE IMP_SUBSTATION_LOSS SET IS_IMPORTED='N',RESULT_DESC=v_reason WHERE ID=v_imp_ss_loss.ID;
 		END IF;
	ELSE 
	 	v_reason:='ORG OR SUBSTATION ENTERED WRONG OR DUPLICATE FOUND';
 	    v_log_result := log_activity('PROCEDURE','SUBSTATION_LOSS.CLEANSE','CLEANSE',v_imp_ss_loss.M_ORG_ID,v_reason,v_imp_ss_loss.M_SUBSTATION_ID, sysdate,I_BATCH_KEY);
        o_result_desc:=v_reason;
	 	UPDATE IMP_SUBSTATION_LOSS SET IS_IMPORTED='N',RESULT_DESC=v_reason WHERE ID=v_imp_ss_loss.ID;
	END IF;
    END LOOP;
   --------------------------------------------------------
    exception
        when others then
          v_exception_code := SQLCODE;
          v_exception_msg := SUBSTR(SQLERRM, 1, 200);
          o_result_code := 'FAILURE';
          o_result_desc := v_exception_code || ' - ' || v_exception_msg;
          v_log_result := log_activity('PROCEDURE','SUBSTATION_LOSS.CLEANSE','EXCEPTION',O_RESULT_DESC,'','', sysdate,I_BATCH_KEY);
    END;--EXCEPTION ENDS HERE
  END CLEANSE;
  
  
  
    PROCEDURE IMPORT(I_BATCH_KEY IN VARCHAR2, O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2) IS
    v_log_result  varchar2(50);
    v_reason varchar2(200):='';
    v_exception_code  NUMBER;
    v_exception_msg  VARCHAR2(200);
    v_imp_loss_cursor sys_refcursor;
    v_imp_ss_loss IMP_SUBSTATION_LOSS%ROWTYPE;
    BEGIN
    BEGIN --EXCEPTION STARTS HERE
    v_log_result := log_activity('PROCEDURE','SUBSTATION_LOSS.IMPORT','IMPORT','Start - '||I_BATCH_KEY,'','', sysdate,I_BATCH_KEY);

   --------------------------------------------------------
    OPEN v_imp_loss_cursor for select * from IMP_SUBSTATION_LOSS where BATCH_KEY=I_BATCH_KEY AND IS_IMPORTED='Y';
    LOOP
    FETCH v_imp_loss_cursor INTO v_imp_ss_loss;
    EXIT WHEN v_imp_loss_cursor%NOTFOUND;
    
    INSERT INTO T_SUBSTATION_LOSS
    (ID, BATCH_KEY, M_ORG_ID, M_SUBSTATION_ID, "MONTH", "YEAR", LOSS_PERCENT, BULK_METER_READING, TOTAL_ALL_WEGS, ENABLED, CREATED_BY, CREATED_DT, M_ORG_NAME, M_SUBSTATION_NAME)
    VALUES(T_SUBSTATION_LOSS_SEQ.NEXTVAL, v_imp_ss_loss.BATCH_KEY, v_imp_ss_loss.M_ORG_ID, v_imp_ss_loss.M_SUBSTATION_ID, v_imp_ss_loss."MONTH", v_imp_ss_loss."YEAR", v_imp_ss_loss.LOSS_PERCENT, v_imp_ss_loss.BULK_METER_READING, v_imp_ss_loss.TOTAL_ALL_WEGS, 'Y', v_imp_ss_loss.ENABLED, sysdate, v_imp_ss_loss.M_ORG_NAME, v_imp_ss_loss.M_SUBSTATION_NAME);
    v_log_result := log_activity('PROCEDURE','SUBSTATION_LOSS.IMPORT','IMPORT','IMPORTED - '||I_BATCH_KEY,'','', sysdate,I_BATCH_KEY);

    END LOOP;
   --------------------------------------------------------
    exception
        when others then
          v_exception_code := SQLCODE;
          v_exception_msg := SUBSTR(SQLERRM, 1, 200);
          o_result_code := 'FAILURE';
          o_result_desc := v_exception_code || ' - ' || v_exception_msg;
          v_log_result := log_activity('PROCEDURE','SUBSTATION_LOSS.IMPORT','EXCEPTION',O_RESULT_DESC,'','', sysdate,I_BATCH_KEY);
    END;--EXCEPTION ENDS HERE
        v_log_result := log_activity('PROCEDURE','SUBSTATION_LOSS.IMPORT','IMPORT','END - '||I_BATCH_KEY,'','', sysdate,I_BATCH_KEY);

  END IMPORT;
  
END SUBSTATION_LOSS;
