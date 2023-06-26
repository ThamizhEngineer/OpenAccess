create or replace FUNCTION              "DELETE_ADD_COMMERCIALS" 
(
  V_REMARKS IN VARCHAR2 
) RETURN VARCHAR2 AS 

v_imp_trade_rel_cursor sys_refcursor ;
v_imp_trade_count NUMBER;
v_service_number_count NUMBER;
v_commercials_trade IMPORT_TRADE_REL%ROWTYPE;
v_service_number varchar2(50);
v_number varchar2(50);

v_log_result  varchar2(50);
v_created_by varchar2(100):='Admin';
v_result_code varchar2(100);
v_result_desc varchar2(300);
v_reason varchar2(200):='';
v_exception_code  NUMBER;
v_exception_msg  VARCHAR2(200);
v_result VARCHAR(200):='SUCCESS';
v_trade_remarks varchar2(100);
v_missing_fields_count number:=0;
BEGIN


      
    BEGIN --Exception starts here
    v_log_result := log_activity('PROCEDURE','DELETE_ADD_COMMERCIALS','START','for Remarks - '||V_REMARKS,'RUNNING',v_created_by, sysdate,'');

    
    IF(V_REMARKS IS NULL OR V_REMARKS = '') THEN
        v_reason   := 'Remarks is mandatory';
        v_result   := 'FAILURE';
        GOTO THE_END;
    else
        update IMPORT_TRADE_REL set SELLER_COMPANY_NAME=trim(SELLER_COMPANY_NAME),SELLER_END_EDC=trim(SELLER_END_EDC),SELLER_COMPANY_SERVICE_NO=trim(SELLER_COMPANY_SERVICE_NO), BUYER_COMPANY_SERVICE_NO=trim(BUYER_COMPANY_SERVICE_NO), remarks ='', status_Code='', result='', result_desc='' WHERE IMPORT_REMARKS=V_REMARKS;
    END IF;
  
    select COUNT(*) INTO v_missing_fields_count FROM IMPORT_TRADE_REL WHERE IMPORT_REMARKS=V_REMARKS
    and (SELLER_COMPANY_NAME is null or SELLER_END_EDC is null or SELLER_COMPANY_SERVICE_NO is null or BUYER_COMPANY_SERVICE_NO is null or FROM_DATE_STR is null or TO_DATE_STR is null or QUANTUM is null or flow_type_code is null) ;
    
     
    IF(v_missing_fields_count IS NULL OR v_missing_fields_count = '') THEN
        v_reason   := 'SELLER_COMPANY_NAME, SELLER_END_EDC, SELLER_COMPANY_SERVICE_NO, BUYER_COMPANY_SERVICE_NO, FROM_DATE_STR, TO_DATE_STR, QUANTUM, flow_type_code are mandatory ';
        v_result   := 'FAILURE';
        GOTO THE_END;
    END IF;
    
	SELECT COUNT(*) INTO v_imp_trade_count FROM IMPORT_TRADE_REL WHERE IMPORT_REMARKS=V_REMARKS;
    SELECT count(distinct SELLER_COMPANY_SERVICE_NO) INTO v_service_number_count FROM IMPORT_TRADE_REL WHERE IMPORT_REMARKS=V_REMARKS;
    v_log_result := log_activity('PROCEDURE','DELETE_ADD_COMMERCIALS','FINDING-RECORDS','','','', sysdate,'COMMERCIALS-COUNT-'||v_imp_trade_count,'SERVICES-COUNT-'||v_service_number_count);   
 
	IF(v_imp_trade_count >=1) THEN
    
	OPEN v_imp_trade_rel_cursor for SELECT distinct SELLER_COMPANY_SERVICE_NO FROM IMPORT_TRADE_REL WHERE IMPORT_REMARKS=V_REMARKS;
    LOOP
    BEGIN --Exception inside Loop
        FETCH v_imp_trade_rel_cursor INTO v_number;
        EXIT WHEN v_imp_trade_rel_cursor%NOTFOUND;
        v_log_result := log_activity('PROCEDURE','DELETE_ADD_COMMERCIALS','LOOP','Start -'||v_number,v_result||'-'||v_reason,'', sysdate,'REMARKS-'||v_remarks,'loop-for-service-'||v_number);
        v_result := DELETE_COMMERCIALS(v_number,V_REMARKS);
        if(v_result like 'FAILURE%' ) then
           v_result_desc :='RELS NOT DELETED';
           update IMPORT_TRADE_REL set result=v_result, result_Desc=v_result_desc where IMPORT_REMARKS=V_REMARKS and SELLER_COMPANY_SERVICE_NO = v_number;
        else
            v_result := ADD_COMMERCIALS(v_number,V_REMARKS);
        end if;
        
        view_refresh.COMP_SERV_REFRESH(V_SERVICE_NUMBER, v_result_code, v_result_desc);
    
    EXCEPTION
    WHEN OTHERS THEN
    v_exception_code := SQLCODE;
    v_exception_msg  := SUBSTR(SQLERRM, 1, 200);
    v_result         := 'FAILURE' || ' - ' || v_exception_code || ' - ' || v_exception_msg;
    v_log_result := log_activity('PROCEDURE','DELETE_ADD_COMMERCIALS','Loop','Exception - '||v_exception_code,v_result,'',sysdate,V_REMARKS,v_service_number);
    END;
	END LOOP;
	END IF;
          
    EXCEPTION
      WHEN OTHERS THEN 
        v_exception_code := SQLCODE;
        v_exception_msg := SUBSTR(SQLERRM, 1, 200);
        v_result := 'FAILURE';
        v_result_desc := v_exception_code || ' - ' || v_exception_msg;
        -- dbms_output.put_line(v_result_desc);
      END;
      <<THE_END>>
      COMMIT;
      v_log_result := log_activity('PROCEDURE','DELETE_ADD_COMMERCIALS','RESULT',v_result_code,v_result_desc,v_created_by, sysdate,V_REMARKS);


  RETURN v_result;
END DELETE_ADD_COMMERCIALS;