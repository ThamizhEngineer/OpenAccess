create or replace FUNCTION DELETE_MASTER_WITH_SER_NUMBER 
(
  V_SERVICE_NUMBER IN VARCHAR2 
) RETURN VARCHAR2 AS 
v_process varchar2(50):='DELETE_MASTER_WITH_SER_NUMBER';
v_process_type varchar2(50):='FUNCTION';
v_result_code  varchar2(50):='FAILURE';
v_result_desc  varchar2(300):='';
v_log_result   varchar2(300):='';
v_master_cursor sys_refcursor ;
service_num varchar2(100);

v_seller_company_service_id varchar2(50);
v_f_agreement_id varchar2(50);
v_f_agreement_count varchar2(50);
v_t_oaa_count varchar2(50);
v_ewa_id varchar2(50);
v_ewa_count varchar2(50);
v_epa_id varchar2(50);
v_intent_id varchar2(50);
--v_noc_gen_id varchar2(50);
v_epa_count varchar2(50);
v_agreement_cursor sys_refcursor ;
v_ewa_cursor sys_refcursor ;
v_trade_count varchar2(50);
v_powerplant_id varchar2(50);
v_company_id varchar2(50);
v_signup_id varchar2(50);
v_service_count varchar2(50);
v_signup_count varchar2(50);
v_pp_count varchar2(50);
v_intent_count varchar2(50);
BEGIN
BEGIN    
    v_log_result := log_activity(v_process_type,v_process,'Start','','','', '',V_SERVICE_NUMBER);

    SELECT count(*) INTO v_service_count FROM M_COMPANY_SERVICE WHERE "number"=V_SERVICE_NUMBER;
    IF(v_service_count>0) THEN
    
    --OPEN v_master_cursor for SELECT id FROM M_COMPANY_SERVICE WHERE "number"=V_SERVICE_NUMBER;
    --LOOP 
    --FETCH v_master_cursor INTO v_seller_company_service_id;
    
    
        SELECT ID INTO v_seller_company_service_id FROM M_COMPANY_SERVICE WHERE "number"=V_SERVICE_NUMBER;
    
    
        SELECT COUNT(*) INTO v_f_agreement_count FROM F_AGREEMENT WHERE SELLER_COMP_SERV_ID=v_seller_company_service_id;
    
        IF(v_f_agreement_count >= '1') THEN
      OPEN v_agreement_cursor for SELECT ID FROM F_AGREEMENT WHERE SELLER_COMP_SERV_ID = v_seller_company_service_id;
        LOOP 
        FETCH v_agreement_cursor INTO v_f_agreement_id;
        EXIT WHEN v_agreement_cursor%NOTFOUND;
        DELETE FROM F_AGREEMENT_LINE WHERE F_AGREEMENT_ID=v_f_agreement_id;
        END LOOP;
        DELETE FROM F_AGREEMENT WHERE SELLER_COMP_SERV_ID = v_seller_company_service_id;
        END IF;
    
    
        SELECT COUNT(*) INTO v_t_oaa_count FROM T_OAA WHERE SELLER_COMP_SERV_ID=v_seller_company_service_id;
        IF(v_t_oaa_count >='1') THEN 
        DELETE FROM T_OAA WHERE SELLER_COMP_SERV_ID=v_seller_company_service_id;
        END IF;
    
        SELECT COUNT(*) INTO v_ewa_count FROM T_EWA WHERE SELLER_COMP_SERV_ID=v_seller_company_service_id;
        IF(v_ewa_count>='1') THEN
    
        OPEN v_ewa_cursor FOR SELECT ID FROM T_EWA WHERE SELLER_COMP_SERV_ID=v_seller_company_service_id;
        LOOP
        FETCH v_ewa_cursor INTO v_ewa_id;
        EXIT WHEN v_ewa_cursor%NOTFOUND;
        DELETE FROM T_EWA_LINE WHERE T_EWA_ID=v_ewa_id;
        END LOOP;
        DELETE FROM T_EWA WHERE SELLER_COMP_SERV_ID=v_seller_company_service_id;
        END IF;
    
        SELECT COUNT(*) INTO v_epa_count FROM T_EPA WHERE SELLER_COMP_SERV_ID=v_seller_company_service_id;
        IF(v_epa_count>='1') THEN
        SELECT ID INTO v_epa_id FROM T_EPA WHERE SELLER_COMP_SERV_ID=v_seller_company_service_id;
        DELETE FROM T_EPA_LINES WHERE T_EPA_ID=v_epa_id;
        DELETE FROM T_EPA WHERE SELLER_COMP_SERV_ID=v_seller_company_service_id;
        END IF;
    
    
        DELETE FROM T_CONSENT WHERE SELLER_COMP_SERV_ID=v_seller_company_service_id;
    --    SELECT COUNT(*) INTO v_intent_count FROM T_ES_INTENT WHERE SELLER_COMP_SERV_ID=v_seller_company_service_id;
    --    IF(v_intent_count>='0') THEN
    --    SELECT ID INTO v_intent_id FROM T_ES_INTENT WHERE SELLER_COMP_SERV_ID=v_seller_company_service_id;
    ----    SELECT ID INTO v_noc_gen_id FROM T_NOC_GENERATOR WHERE T_ES_INTENT_ID=v_intent_id;
    ----    DELETE T_NOC_GENERATOR_LINE WHERE T_NOC_GENERATOR_ID=v_noc_gen_id;
    ----    DELETE T_NOC_GENERATOR WHERE T_ES_INTENT_ID=v_intent_id;
    ----    DELETE T_NOC WHERE T_ES_INTENT_ID=v_intent_id;
    --    DELETE T_ES_INTENT_LINE WHERE T_EST_INTENT_ID=v_intent_id;
    --    DELETE T_ES_INTENT WHERE SELLER_COMP_SERV_ID=v_seller_company_service_id;
    --    END IF;
    
        select count(*) into v_trade_count from M_TRADE_RELATIONSHIP where M_SELLER_COMP_SERVICE_ID=v_seller_company_service_id;
        if(v_trade_count>='1')then
        delete from M_TRADE_RELATIONSHIP where M_SELLER_COMP_SERVICE_ID=v_seller_company_service_id;
        end if;
    
    --Master
    
    SELECT count(*) into v_pp_count FROM M_POWERPLANT WHERE M_SERVICE_ID=v_seller_company_service_id;
      IF(v_pp_count>0) THEN
        SELECT id into v_powerplant_id FROM M_POWERPLANT WHERE M_SERVICE_ID=v_seller_company_service_id;
        DELETE FROM M_GENERATOR WHERE M_POWERPLANT_ID=v_powerplant_id;
      ELSE
          v_log_result := log_activity(v_process_type,v_process,'Service-Check','POWERPLANT DOES NOT EXIST - COUNT = ' || v_signup_count,'','', '',V_SERVICE_NUMBER);
      END IF;
    
    DELETE FROM M_POWERPLANT WHERE M_SERVICE_ID=v_seller_company_service_id;
    DELETE FROM M_COMPANY_METER WHERE M_COMPANY_SERVICE_ID=v_seller_company_service_id;
    
    SELECT M_COMPANY_ID into v_company_id FROM M_COMPANY_SERVICE WHERE ID=v_seller_company_service_id;
    DELETE FROM M_COMPANY_SERVICE WHERE ID=v_seller_company_service_id;
    
    DELETE FROM M_COMPANY WHERE ID=v_company_id;
    
    
    SELECT count(*) into v_signup_count FROM M_SIGNUP WHERE HTSC_NUMBER=V_SERVICE_NUMBER;
    
      if(v_signup_count>0) THEN
        SELECT id into v_signup_id FROM M_SIGNUP WHERE HTSC_NUMBER=V_SERVICE_NUMBER;
        DELETE M_SIGNUP_TRADE_REL WHERE M_SIGNUP_ID=v_signup_id;
        DELETE M_SIGNUP WHERE HTSC_NUMBER=V_SERVICE_NUMBER;
      ELSE
        v_log_result := log_activity(v_process_type,v_process,'Signup-Check','SIGNUP DOES NOT EXIST','','', '',V_SERVICE_NUMBER);
      END IF;
    
    DELETE FROM AUTH_USER WHERE USER_NAME=V_SERVICE_NUMBER;
    COMMIT;
    v_result_code :='SUCCESS';
    v_result_desc := '';
    
    v_log_result := log_activity(v_process_type,v_process,'Complete','calling service refresh',v_result_code,'', '',V_SERVICE_NUMBER);

    view_refresh.COMP_SERV_REFRESH(V_SERVICE_NUMBER,v_result_code, v_result_desc);
    
    ELSE
        v_result_code :='FAILURE';
        v_result_desc := 'SERVICE NUMBER DOES NOT EXIST';
        v_log_result := log_activity(v_process_type,v_process,'Service-Check',v_result_desc,'','', '',V_SERVICE_NUMBER);
    
    END IF;
exception
  when others then
    v_result_code := 'FAILURE';
    v_result_desc := SQLCODE || ' - ' || SUBSTR(SQLERRM, 1, 200);
    v_log_result := log_activity(v_process_type,v_process,'EH','Exception - '||v_result_desc,v_result_code,v_result_desc, sysdate,V_SERVICE_NUMBER);
END;
<<THE_END>>
    v_log_result := log_activity(v_process_type,v_process,'End',v_result_desc,v_result_code,'', '',V_SERVICE_NUMBER);
  RETURN v_result_code|| ' - ' || v_result_desc;
END DELETE_MASTER_WITH_SER_NUMBER;