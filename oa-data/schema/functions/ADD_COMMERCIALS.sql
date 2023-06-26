create or replace FUNCTION              "ADD_COMMERCIALS" 
(
  V_SERVICE_NUMBER IN VARCHAR2,
  V_REMARKS IN VARCHAR2 
) RETURN VARCHAR2 AS

v_seller_company_service_id varchar2(50);
v_seller_company_id varchar2(50);
v_seller_org_id varchar2(50);
v_ss_id varchar2(50);
v_buyer_company_service_id varchar2(50);
v_buyer_company_id varchar2(50);
v_buyer_org_id varchar2(50);
v_buyer_ss_id varchar2(50);
v_buyer_voltage_Code varchar2(50);
v_company_service M_COMPANY_SERVICE%ROWTYPE;
v_ewa T_EWA%ROWTYPE;
v_ewa_line T_EWA_LINE%ROWTYPE;
v_aggrement_ewa F_AGREEMENT%ROWTYPE;
v_aggrement_ewa_line F_AGREEMENT_LINE%ROWTYPE;
v_commercials_trade IMPORT_TRADE_REL%ROWTYPE;
v_commercials_cursor sys_refcursor ;
v_date_diff VARCHAR2(17);
v_oaa T_OAA%ROWTYPE;
v_aggrement_oaa F_AGREEMENT%ROWTYPE;
v_epa T_EPA%ROWTYPE;
v_epa_line T_EPA_LINES%ROWTYPE;
v_aggrement_epa F_AGREEMENT%ROWTYPE;
v_aggrement_epa_line F_AGREEMENT_LINE%ROWTYPE;
v_is_captive varchar2(50);
v_flow_type_code varchar2(50);
v_flow_type_for_agmt varchar2(50);
v_conversion number:=0.001;
v_m_trade_rel M_TRADE_RELATIONSHIP%ROWTYPE;
v_imp_trade_rel import_trade_rel%ROWTYPE;

v_created_by varchar2(100):='ADD_COMMERCIALS';
v_result VARCHAR(200):='SUCCESS';
v_result_code varchar2(100);
v_result_desc varchar2(300);
v_exception_code  NUMBER;
v_exception_msg  VARCHAR2(200);
v_log_result  varchar2(50);

v_no_dt_count number:=0;

BEGIN
BEGIN
    v_log_result := log_activity('PROCEDURE','ADD_COMMERCIALS','START','FOR SERVICE- '||V_SERVICE_NUMBER,'STARTED',v_created_by, sysdate,'');


    --Getting seller details
    SELECT service.ID,service.M_COMPANY_ID,service.M_ORG_ID,service.M_SUBSTATION_ID
    INTO v_seller_company_service_id,v_seller_company_id,v_seller_org_id,v_ss_id 
    FROM M_COMPANY_SERVICE service WHERE service."number"=V_SERVICE_NUMBER;
    -- -- dbms_output.put_line('v_seller_company_service_id-' || v_seller_company_service_id || '-v_seller_company_id-' || v_seller_company_id);


    --isCaptive
    SELECT IS_CAPTIVE INTO v_is_captive FROM M_COMPANY WHERE ID=v_seller_company_id;

    --Getting m_company_service
    SELECT * INTO v_company_service FROM M_COMPANY_SERVICE WHERE "number"=V_SERVICE_NUMBER;

    select * into v_imp_trade_rel from IMPORT_TRADE_REL where SELLER_COMPANY_SERVICE_NO = V_SERVICE_NUMBER  and IMPORT_REMARKS=V_REMARKS and rownum=1;

    -- ***************** ASSUMPTION THAT ALL TRADE-RELATIONSHIPS FOR A GENERATOR WILL BE HAVE SAME FLOW_TYPE_CODE *****************
    IF(v_imp_trade_rel.FLOW_TYPE_CODE = 'IS-CAPTIVE') THEN
        v_is_captive := 'Y';
        v_flow_type_code := 'IS-CAPTIVE';
        v_flow_type_for_agmt := 'CAPTIVE';   -- for some reason, flow-type in service and TRs is IS-CAPTIVE but in agreements its CAPTIVE
    elsif(v_imp_trade_rel.FLOW_TYPE_CODE = 'THIRD-PARTY') THEN
        v_is_captive := 'N';
        v_flow_type_code := 'THIRD-PARTY';
        v_flow_type_for_agmt := 'THIRD-PARTY';
    elsif(v_imp_trade_rel.FLOW_TYPE_CODE = 'STB') THEN
        v_is_captive := 'N';
        v_flow_type_code := 'STB';
        v_flow_type_for_agmt := 'THIRD-PARTY';
    else
        v_result_desc   := 'Unknown FlowTypeCode -'||v_imp_trade_rel.FLOW_TYPE_CODE;
        v_result   := 'FAILURE';
        GOTO THE_END;
    END IF;
    if(v_imp_trade_rel.from_date_str IS NOT NULL)THEN
        update import_trade_rel set from_date=to_date(from_date_str,'dd/mm/yyyy') , to_date=to_date(to_date_str,'dd/mm/yyyy'), QUANTUM = (v_company_service.CAPACITY/to_number(SHARE_PERCENT)*100)*v_conversion where SELLER_COMPANY_SERVICE_NO = V_SERVICE_NUMBER  and IMPORT_REMARKS=V_REMARKS;
    END IF;

    update import_trade_rel set QUANTUM = (v_company_service.CAPACITY/to_number(SHARE_PERCENT)*100)*v_conversion where SELLER_COMPANY_SERVICE_NO = V_SERVICE_NUMBER  and IMPORT_REMARKS=V_REMARKS;

    v_log_result := log_activity('PROCEDURE','ADD_COMMERCIALS','001','FOR SERVICE- '||V_SERVICE_NUMBER,'PROCESSING',v_created_by, sysdate,'');

    IF(v_flow_type_code in ('IS-CAPTIVE','THIRD-PARTY'))THEN

        --Creating EWA
        v_ewa.id := t_ewa_seq.nextval;
        v_ewa.code := 'EWA'||v_ewa.id;
        v_ewa.SELLER_COMP_SERV_ID := v_seller_company_service_id;
        v_ewa.SELLER_END_ORG_ID := v_seller_org_id;
        v_ewa.INJECTION_VOLTAGE_CODE := v_company_service.VOLTAGE_CODE;
        v_ewa.STATUS_CODE := 'COMPLETED';
        v_ewa.FLOW_TYPE_CODE := v_flow_type_for_agmt;
        v_ewa.ENABLED := 'Y';
        v_ewa.created_date := sysdate;
        v_ewa.SELLER_IS_CAPTIVE := v_is_captive;
        v_ewa.AGREEMENT_DT := nvl(v_imp_trade_rel.AGMT_DT,v_imp_trade_rel.from_Date);

        INSERT INTO T_EWA VALUES v_ewa;
        v_log_result := log_activity('PROCEDURE','ADD_COMMERCIALS','002-001','FOR SERVICE- '||V_SERVICE_NUMBER,'PROCESSING',v_created_by, sysdate,'');


        --Creating Agreement for EWA
        v_aggrement_ewa.id := F_AGREEMENT_SEQ.nextval;
        v_aggrement_ewa.CODE :=  'AGREEMENT'||v_aggrement_ewa.id;
        v_aggrement_ewa.T_EWA_ID := v_ewa.id;
        v_aggrement_ewa.SELLER_COMP_SERV_ID := v_ewa.SELLER_COMP_SERV_ID;
        v_aggrement_ewa.SELLER_EDC_ID := v_ewa.SELLER_END_ORG_ID;
        v_aggrement_ewa.SELLER_COMPANY_ID := v_seller_company_id;
        v_aggrement_ewa.IS_CAPTIVE := v_is_captive;
        v_aggrement_ewa.FLOW_TYPE := v_ewa.FLOW_TYPE_CODE;
        v_aggrement_ewa.STATUS_CODE := 'SIGNED';

        INSERT INTO F_AGREEMENT VALUES v_aggrement_ewa;
        v_log_result := log_activity('PROCEDURE','ADD_COMMERCIALS','002-101','FOR SERVICE- '||V_SERVICE_NUMBER,'PROCESSING',v_created_by, sysdate,'');

        OPEN v_commercials_cursor for SELECT * FROM IMPORT_TRADE_REL WHERE SELLER_COMPANY_SERVICE_NO = V_SERVICE_NUMBER and IMPORT_REMARKS=V_REMARKS;
        LOOP --Loop starts here
            FETCH v_commercials_cursor INTO v_commercials_trade;
            EXIT WHEN v_commercials_cursor%NOTFOUND;
            BEGIN    
                --Getting buyer details
                SELECT buyerservice.ID,buyerservice.M_ORG_ID,buyerservice.M_SUBSTATION_ID,buyerservice.VOLTAGE_CODE,buyerservice.M_COMPANY_ID
                INTO v_buyer_company_service_id,v_buyer_org_id,v_buyer_ss_id,v_buyer_voltage_Code,v_buyer_company_id
                FROM M_COMPANY_SERVICE buyerservice WHERE buyerservice."number"= v_commercials_trade.BUYER_COMPANY_SERVICE_NO;    
                ---- dbms_output.put_line('v_buyer_company_service_id-' || v_buyer_company_service_id );


                v_log_result := log_activity('PROCEDURE','ADD_COMMERCIALS','002-102','FOR SERVICE- '||V_SERVICE_NUMBER,'PROCESSING',v_created_by, sysdate,v_commercials_trade.BUYER_COMPANY_SERVICE_NO);
                --Updating EWA inside Loop
                SELECT (TO_DATE-FROM_DATE)
                INTO v_date_diff
                FROM IMPORT_TRADE_REL where BUYER_COMPANY_SERVICE_NO = v_commercials_trade.BUYER_COMPANY_SERVICE_NO and SELLER_COMPANY_SERVICE_NO = V_SERVICE_NUMBER and IMPORT_REMARKS=V_REMARKS and rownum=1;
                IF (v_date_diff>90 AND V_DATE_DIFF<1825)THEN
                v_ewa.AGMT_PERIOD_CODE := 'MTOA';
                ELSIF (v_date_diff>1825)THEN
                v_ewa.AGMT_PERIOD_CODE := 'LTOA';
                ELSE
                v_ewa.AGMT_PERIOD_CODE := 'STOA';
                END IF;
            ---- dbms_output.put_line('QUANTUM;-' || v_commercials_trade.QUANTUM);

                v_ewa.TOAL_APPROVED_UNITS := v_company_service.CAPACITY;
                v_ewa.FROM_DT := v_commercials_trade.FROM_DATE;
                v_ewa.TO_DT := v_commercials_trade.TO_DATE;
                v_ewa.AGREEMENT_DT := nvl(v_commercials_trade.AGMT_DT,v_ewa.FROM_DT);
                v_ewa.IMPORT_REMARKS := v_commercials_trade.IMPORT_REMARKS;

                v_log_result := log_activity('PROCEDURE','ADD_COMMERCIALS','002-103','FOR SERVICE- '||V_SERVICE_NUMBER,'PROCESSING',v_created_by, sysdate,v_commercials_trade.BUYER_COMPANY_SERVICE_NO);

                UPDATE T_EWA SET FROM_DT=v_ewa.FROM_DT, TO_DT=v_ewa.TO_DT,AGMT_PERIOD_CODE=v_ewa.AGMT_PERIOD_CODE,IMPORT_REMARKS=v_ewa.IMPORT_REMARKS,TOAL_APPROVED_UNITS=v_ewa.TOAL_APPROVED_UNITS, AGREEMENT_DT = v_ewa.AGREEMENT_DT WHERE id = v_ewa.id;

                v_aggrement_ewa.FROM_DATE := v_ewa.FROM_DT;
                v_aggrement_ewa.TO_DATE := v_ewa.TO_DT;
                v_aggrement_ewa.AGREEMENT_PERIOD_CODE := v_ewa.AGMT_PERIOD_CODE;
                v_aggrement_ewa.AGREEMENT_DATE := nvl(v_commercials_trade.AGMT_DT,v_ewa.FROM_DT);
                v_aggrement_ewa.IMPORT_REMARKS := v_commercials_trade.IMPORT_REMARKS;

                UPDATE F_AGREEMENT SET FROM_DATE=v_aggrement_ewa.FROM_DATE,TO_DATE = v_aggrement_ewa.TO_DATE,
                        AGREEMENT_PERIOD_CODE=v_aggrement_ewa.AGREEMENT_PERIOD_CODE,AGREEMENT_DATE=v_aggrement_ewa.AGREEMENT_DATE,
                        IMPORT_REMARKS=v_aggrement_ewa.IMPORT_REMARKS WHERE id = v_aggrement_ewa.id;

                --Creating Ewa Line
                v_ewa_line.id := t_ewa_line_seq.nextval;
                v_ewa_line.T_EWA_ID := v_ewa.id;
                v_ewa_line.BUYER_COMP_SERV_ID := v_buyer_company_service_id;   
                v_ewa_line.INTERVAL_TYPE_CODE := v_commercials_trade.INTERVAL_TYPE_CODE;
                v_ewa_line.SHARE_PERCENTAGE := v_commercials_trade.SHARE_PERCENT;
                v_ewa_line.IS_CAPTIVE := v_is_captive;
                v_ewa_line.ENABLED := 'Y';
                v_ewa_line.created_by := 'admin';
                v_ewa_line.created_dt := sysdate;
                v_ewa_line.created_date := sysdate;
                v_ewa_line.PROPOSED_UNITS := v_commercials_trade.QUANTUM;
                v_ewa_line.APPROVED_UNITS := v_commercials_trade.QUANTUM;
                v_ewa_line.INJECTION_PEAK_UNITS := v_commercials_trade.PEAK_UNITS;
                v_ewa_line.INJECTION_OFF_PEAK_UNITS := v_commercials_trade.OFF_PEAK_UNITS;
                v_ewa_line.IMPORT_REMARKS := v_commercials_trade.IMPORT_REMARKS;
                v_ewa_line.DRAWAL_VOLTAGE_CODE :=v_buyer_voltage_Code;

                v_log_result := log_activity('PROCEDURE','ADD_COMMERCIALS','002-104','FOR SERVICE- '||V_SERVICE_NUMBER,'PROCESSING',v_created_by, sysdate,v_commercials_trade.BUYER_COMPANY_SERVICE_NO);
                INSERT INTO t_ewa_line VALUES v_ewa_line;

                v_aggrement_ewa_line.id := F_AGREEMENT_LINE_SEQ.nextval;
                v_aggrement_ewa_line.F_AGREEMENT_ID := v_aggrement_ewa.id;
                v_aggrement_ewa_line.BUYER_COMP_SERV_ID := v_ewa_line.BUYER_COMP_SERV_ID;
                v_aggrement_ewa_line.BUYER_ORG_ID := v_ewa_line.BUYER_END_ORG_ID;
                v_aggrement_ewa_line.DRAWAL_VOLTAGE_CODE := v_ewa_line.DRAWAL_VOLTAGE_CODE;
                v_aggrement_ewa_line.PROPOSED_CAPACITY := v_ewa_line.PROPOSED_UNITS;
                v_aggrement_ewa_line.APPROVED_CAPACITY := v_ewa_line.APPROVED_UNITS;
                v_aggrement_ewa_line.FLOW_TYPE_CODE := v_ewa.AGMT_PERIOD_CODE;
                v_aggrement_ewa_line.AGMT_PERIOD_CODE := v_ewa.FLOW_TYPE_CODE;
                v_aggrement_ewa_line.APPROVED_DT := v_ewa_line.APPROVED_DT;
                v_aggrement_ewa_line.AGREEMENT_DT := v_ewa.AGREEMENT_DT;
                v_aggrement_ewa_line.IS_CAPTIVE := v_ewa_line.IS_CAPTIVE;
                v_aggrement_ewa_line.PEAK_UNITS :=v_ewa_line.DRAWAL_PEAK_UNITS;
                v_aggrement_ewa_line.OFF_PEAK_UNITS := v_ewa_line.DRAWAL_OFF_PEAK_UNITS;
                v_aggrement_ewa_line.IMPORT_REMARKS := v_commercials_trade.IMPORT_REMARKS;

                INSERT INTO F_AGREEMENT_LINE VALUES v_aggrement_ewa_line;

                v_log_result := log_activity('PROCEDURE','ADD_COMMERCIALS','002-105','FOR SERVICE- '||V_SERVICE_NUMBER,'PROCESSING',v_created_by, sysdate,v_commercials_trade.BUYER_COMPANY_SERVICE_NO);

                v_oaa.id := t_oaa_seq.nextval;
                v_oaa.CODE := 'OAA'||v_oaa.id;
                v_oaa.BUYER_COMP_SERV_ID := v_buyer_company_service_id;
                v_oaa.BUYER_END_ORG_ID := v_buyer_org_id;
                v_oaa.DRAWAL_DIST_VOLTAGE_CODE :=v_ewa_line.DRAWAL_VOLTAGE_CODE;
                v_oaa.AGMT_PERIOD_CODE := v_ewa.AGMT_PERIOD_CODE;
                v_oaa.FROM_DT := v_commercials_trade.from_date;
                v_oaa.TO_DT:= v_commercials_trade.to_date;
                v_oaa.PROPOSED_TOTAL_UNITS:= v_commercials_trade.QUANTUM;
                v_oaa.APPROVED_TOTAL_UNITS:= v_commercials_trade.QUANTUM;
                v_oaa.SELLER_COMP_SERV_ID:=v_seller_company_service_id;
                v_oaa.SELLER_END_ORG_ID := v_seller_org_id ;
                v_oaa.INJECTION_DIST_SS_ID:= v_company_service.M_SUBSTATION_ID;
                v_oaa.INJECTION_DIST_VOLTAGE_CODE :=  v_company_service.VOLTAGE_CODE;
                v_oaa.STATUS_CODE := 'COMPLETED';
                v_oaa.INTERVAL_TYPE_CODE := v_commercials_trade.INTERVAL_TYPE_CODE;
                v_oaa.C1_UNITS :=v_commercials_trade.C1;
                v_oaa.C2_UNITS :=v_commercials_trade.C2;
                v_oaa.C3_UNITS :=v_commercials_trade.C3;
                v_oaa.C4_UNITS :=v_commercials_trade.C4;
                v_oaa.C5_UNITS :=v_commercials_trade.C5;
                v_oaa.AGREEMENT_DT := v_ewa.AGREEMENT_DT;
                v_oaa.PEAK_UNITS := v_commercials_trade.PEAK_UNITS;
                v_oaa.OFF_PEAK_UNITS :=v_commercials_trade.OFF_PEAK_UNITS;
                v_oaa.FLOW_TYPE_CODE := v_flow_type_for_agmt;
                v_oaa.ENABLED := 'Y';
                v_oaa.created_by := 'admin';
                v_oaa.created_dt := sysdate;
                v_oaa.IMPORT_REMARKS := v_commercials_trade.IMPORT_REMARKS;

                INSERT INTO t_oaa VALUES v_oaa;


                v_aggrement_oaa.id := F_AGREEMENT_SEQ.nextval;
                v_aggrement_oaa.CODE :=  'AGREEMENT'||v_aggrement_oaa.id;
                v_aggrement_oaa.T_OAA_ID := v_oaa.id;
                v_aggrement_oaa.BUYER_COMP_SERV_ID := v_oaa.BUYER_COMP_SERV_ID;
                v_aggrement_oaa.BUYER_EDC_ID := v_oaa.BUYER_END_ORG_ID;
                v_aggrement_oaa.BUYER_COMP_ID := v_buyer_company_id;
                v_aggrement_oaa.AGREEMENT_PERIOD_CODE := v_oaa.AGMT_PERIOD_CODE;
                v_aggrement_oaa.FROM_DATE := v_oaa.FROM_DT;
                v_aggrement_oaa.TO_DATE:= v_oaa.TO_DT;
                v_aggrement_oaa.STATUS_CODE :='SIGNED';
                v_aggrement_oaa.SELLER_COMP_SERV_ID:=v_oaa.SELLER_COMP_SERV_ID;
                v_aggrement_oaa.INTERVAL_TYPE_CODE := v_oaa.INTERVAL_TYPE_CODE;
                v_aggrement_oaa.C1 :=v_oaa.C1_UNITS;
                v_aggrement_oaa.C2 :=v_oaa.C2_UNITS;
                v_aggrement_oaa.C3 :=v_oaa.C3_UNITS;
                v_aggrement_oaa.C4 :=v_oaa.C4_UNITS;
                v_aggrement_oaa.C5 :=v_oaa.C5_UNITS;
                v_aggrement_oaa.AGREEMENT_DATE :=v_oaa.AGREEMENT_DT;
                v_aggrement_oaa.PEAK_UNITS := v_oaa.PEAK_UNITS;
                v_aggrement_oaa.OFF_PEAK_UNITS :=v_oaa.OFF_PEAK_UNITS;
                v_aggrement_oaa.FLOW_TYPE := v_flow_type_for_agmt;
                v_aggrement_oaa.SELLER_COMPANY_ID := v_seller_company_id;
                v_aggrement_oaa.SELLER_EDC_ID := v_oaa.SELLER_END_ORG_ID;
                v_aggrement_oaa.IS_CAPTIVE := v_is_captive;
                v_aggrement_oaa.TOTAL_UNITS := v_oaa.PEAK_UNITS;
                v_aggrement_oaa.IMPORT_REMARKS := v_commercials_trade.IMPORT_REMARKS;

                INSERT INTO F_AGREEMENT VALUES v_aggrement_oaa;
                v_log_result := log_activity('PROCEDURE','ADD_COMMERCIALS','002-106','FOR SERVICE- '||V_SERVICE_NUMBER,'PROCESSING',v_created_by, sysdate,v_commercials_trade.BUYER_COMPANY_SERVICE_NO);
                --Start here 
                v_m_trade_rel.ID := M_TRADE_RELATIONSHIP_SEQ.nextval;
                v_m_trade_rel.M_SELLER_COMPANY_ID := v_seller_company_id;
                v_m_trade_rel.M_SELLER_COMP_SERVICE_ID := v_seller_company_service_id;
                v_m_trade_rel.M_BUYER_COMPANY_ID := v_buyer_company_id;
                v_m_trade_rel.M_BUYER_COMP_SERVICE_ID := v_buyer_company_service_id;
                v_m_trade_rel.QUANTUM := v_commercials_trade.QUANTUM;
                v_m_trade_rel.FROM_DATE := v_commercials_trade.FROM_DATE;
                v_m_trade_rel.TO_DATE := v_commercials_trade.TO_DATE;
                v_m_trade_rel.C1 := v_commercials_trade.C1;
                v_m_trade_rel.C2 := v_commercials_trade.C2;
                v_m_trade_rel.C3 := v_commercials_trade.C3;
                v_m_trade_rel.C4 := v_commercials_trade.C4;
                v_m_trade_rel.C5 := v_commercials_trade.C5;
                v_m_trade_rel.IS_CAPTIVE := v_is_captive;
                v_m_trade_rel.FLOW_TYPE_CODE := v_flow_type_code;
                v_m_trade_rel.PEAK_UNITS := v_commercials_trade.PEAK_UNITS;
                v_m_trade_rel.OFF_PEAK_UNITS := v_commercials_trade.OFF_PEAK_UNITS;
                v_m_trade_rel.INTERVAL_TYPE_CODE := nvl(v_commercials_trade.INTERVAL_TYPE_CODE,'04');
                v_m_trade_rel.SHARE_PERCENT := v_commercials_trade.SHARE_PERCENT;
                v_m_trade_rel.IMPORT_REMARKS := v_commercials_trade.IMPORT_REMARKS;
                v_m_trade_rel.agreement_type := v_ewa.AGMT_PERIOD_CODE ;
                v_m_trade_rel.enabled := 'Y' ;
                v_m_trade_rel.created_by := v_created_by ;
                v_m_trade_rel.created_date := sysdate ;
                v_m_trade_rel.agmt_dt := v_ewa.AGREEMENT_DT;


                INSERT INTO M_TRADE_RELATIONSHIP VALUES v_m_trade_rel;

                Update IMPORT_TRADE_REL set remarks='COMPLETED' WHERE BUYER_COMPANY_SERVICE_NO = v_commercials_trade.BUYER_COMPANY_SERVICE_NO and SELLER_COMPANY_SERVICE_NO = V_SERVICE_NUMBER and IMPORT_REMARKS=V_REMARKS;

                v_log_result := log_activity('PROCEDURE','ADD_COMMERCIALS','002-107','FOR SERVICE- '||V_SERVICE_NUMBER,'PROCESSING',v_created_by, sysdate,v_commercials_trade.BUYER_COMPANY_SERVICE_NO);
            EXCEPTION
            WHEN OTHERS THEN 
                v_exception_code := SQLCODE;
                v_exception_msg := SUBSTR(SQLERRM, 1, 200);
                v_result := 'FAILURE';
                v_result_desc := v_exception_code || ' - ' || v_exception_msg;
                -- dbms_output.put_line(v_result_desc);
                v_log_result := log_activity('PROCEDURE','ADD_COMMERCIALS','002-107','FOR SERVICE- '||V_SERVICE_NUMBER,'EXITING LOOP as one trade-relationship had error',v_created_by, sysdate,v_commercials_trade.BUYER_COMPANY_SERVICE_NO);
                Update IMPORT_TRADE_REL set remarks='FAILED', result=v_result, result_Desc=v_result_desc WHERE BUYER_COMPANY_SERVICE_NO=v_commercials_trade.BUYER_COMPANY_SERVICE_NO and SELLER_COMPANY_SERVICE_NO = V_SERVICE_NUMBER and IMPORT_REMARKS=V_REMARKS;
            end;    
        END LOOP;

    -------------------------------------------------------------
    -- else of captive-if
    ELSE
        --Creating epa
        v_epa.id := T_EPA_SEQ.nextval;
        v_epa.CODE :=  'EPA'||v_epa.id;
        v_epa.SELLER_COMP_SERV_ID := v_seller_company_service_id;
        v_epa.SELLER_END_ORG_ID := v_seller_org_id;
        v_epa.STATUS_CODE := 'COMPLETED';
        v_epa.FLOW_TYPE_CODE := v_flow_type_for_agmt;
        v_epa.ENABLED:= 'Y';
        v_epa.SELLER_IS_CAPTIVE := v_is_captive;
        v_epa.CREATED_DATE := sysdate;

        INSERT INTO T_EPA VALUES v_epa;

        --Create agreement for epa
        v_aggrement_epa.id := F_AGREEMENT_SEQ.nextval;
        v_aggrement_epa.CODE :=  'AGREEMENT'||v_aggrement_epa.id;
        v_aggrement_epa.T_EPA_ID := v_epa.id;
        v_aggrement_epa.SELLER_COMP_SERV_ID := v_epa.SELLER_COMP_SERV_ID;
        v_aggrement_epa.SELLER_EDC_ID := v_epa.SELLER_END_ORG_ID;
        v_aggrement_epa.SELLER_COMPANY_ID := v_seller_company_id;
        v_aggrement_epa.FLOW_TYPE := v_epa.FLOW_TYPE_CODE;
        v_aggrement_epa.STATUS_CODE := 'SIGNED';
        v_aggrement_epa.BUYER_COMP_SERV_ID :=  'TNEB-'||v_aggrement_epa.SELLER_EDC_ID;

        INSERT INTO F_AGREEMENT VALUES v_aggrement_epa;
        v_log_result := log_activity('PROCEDURE','ADD_COMMERCIALS','002-201','FOR SERVICE- '||V_SERVICE_NUMBER,'PROCESSING',v_created_by, sysdate,'');

        --Loop starts
        OPEN v_commercials_cursor for SELECT * FROM IMPORT_TRADE_REL WHERE SELLER_COMPANY_SERVICE_NO = V_SERVICE_NUMBER and IMPORT_REMARKS=V_REMARKS;
        LOOP
            FETCH v_commercials_cursor INTO v_commercials_trade;
            EXIT WHEN v_commercials_cursor%NOTFOUND;
            BEGIN
                --Getting buyer details
                SELECT buyerservice.ID,buyerservice.M_ORG_ID,buyerservice.M_SUBSTATION_ID,buyerservice.m_company_id
                INTO v_buyer_company_service_id,v_buyer_org_id,v_buyer_ss_id,v_buyer_company_id
                FROM M_COMPANY_SERVICE buyerservice WHERE buyerservice."number"= v_commercials_trade.BUYER_COMPANY_SERVICE_NO;    
                ---- dbms_output.put_line('v_buyer_company_service_id-' || v_buyer_company_service_id );

                SELECT (TO_DATE-FROM_DATE)
                INTO v_date_diff
                FROM IMPORT_TRADE_REL where BUYER_COMPANY_SERVICE_NO = v_commercials_trade.BUYER_COMPANY_SERVICE_NO and SELLER_COMPANY_SERVICE_NO = V_SERVICE_NUMBER and IMPORT_REMARKS=V_REMARKS and rownum=1;

                IF (v_date_diff>90 AND V_DATE_DIFF<1825)THEN
                    v_epa.AGMT_PERIOD_CODE := 'MTOA';
                ELSIF (v_date_diff>1825)THEN
                    v_epa.AGMT_PERIOD_CODE := 'LTOA';
                ELSE
                    v_epa.AGMT_PERIOD_CODE := 'STOA';
                END IF;

                v_log_result := log_activity('PROCEDURE','ADD_COMMERCIALS','002-202','FOR SERVICE- '||V_SERVICE_NUMBER,'PROCESSING',v_created_by, sysdate,v_commercials_trade.BUYER_COMPANY_SERVICE_NO);

                v_epa.APPROVED_TOTAL_UNITS := v_company_service.CAPACITY;
                v_epa.BUYER_COMP_SERV_ID :=v_buyer_company_service_id;
                v_epa.FROM_DT := v_commercials_trade.from_date;
                v_epa.TO_DT := v_commercials_trade.to_date;
                v_epa.PEAK_UNITS  := v_commercials_trade.PEAK_UNITS;
                v_epa.OFF_PEAK_UNITS  :=v_commercials_trade.OFF_PEAK_UNITS;
                v_epa.IMPORT_REMARKS := v_commercials_trade.IMPORT_REMARKS;
                v_epa.AGREEMENT_DT := NVL(v_commercials_trade.AGMT_DT,v_commercials_trade.from_date);

                --Updating t_epa inside loop
                UPDATE T_EPA SET 
                FROM_DT=v_epa.FROM_DT, TO_DT =  v_epa.TO_DT,PEAK_UNITS= v_epa.PEAK_UNITS,OFF_PEAK_UNITS= v_epa.OFF_PEAK_UNITS,
                AGMT_PERIOD_CODE=v_epa.AGMT_PERIOD_CODE,APPROVED_TOTAL_UNITS=v_epa.APPROVED_TOTAL_UNITS,
                BUYER_COMP_SERV_ID=v_epa.BUYER_COMP_SERV_ID,IMPORT_REMARKS=v_epa.IMPORT_REMARKS, AGREEMENT_DT = v_epa.AGREEMENT_DT
                WHERE id = v_epa.id;

                v_aggrement_epa.AGREEMENT_DATE := v_epa.AGREEMENT_DT;
                v_aggrement_epa.FROM_DATE := v_epa.FROM_DT;
                v_aggrement_epa.TO_DATE := v_epa.TO_DT;
                v_aggrement_epa.AGREEMENT_PERIOD_CODE := v_epa.AGMT_PERIOD_CODE;
                v_aggrement_epa.IMPORT_REMARKS := v_commercials_trade.IMPORT_REMARKS;

                --Updating agreement for epa
                UPDATE F_AGREEMENT SET 
                FROM_DATE=v_aggrement_epa.FROM_DATE, TO_DATE =  v_aggrement_epa.TO_DATE,AGREEMENT_PERIOD_CODE=v_aggrement_epa.AGREEMENT_PERIOD_CODE,
                AGREEMENT_DATE=v_aggrement_epa.FROM_DATE WHERE id = v_aggrement_epa.id;

                --Creating epa line
                v_epa_line.id := T_EPA_LINE_SEQ.nextval;
                v_epa_line.T_EPA_ID := v_epa.id;
                v_epa_line.ENABLED := 'Y';
                v_epa_line.CREATED_BY := 'admin';
                v_epa_line.CREATED_DT := sysdate;
                v_epa_line.PROPOSED_TOTAL_UNITS := v_commercials_trade.QUANTUM;
                v_epa_line.APPROVED_TOTAL_UNITS := v_commercials_trade.QUANTUM;
                v_epa_line.IMPORT_REMARKS := v_commercials_trade.IMPORT_REMARKS;

                INSERT INTO T_EPA_LINES VALUES v_epa_line;

                v_log_result := log_activity('PROCEDURE','ADD_COMMERCIALS','002-203','FOR SERVICE- '||V_SERVICE_NUMBER,'PROCESSING',v_created_by, sysdate,v_commercials_trade.BUYER_COMPANY_SERVICE_NO);
                --Creating oaa
                v_oaa.id := t_oaa_seq.nextval;
                v_oaa.CODE := 'OAA'||v_oaa.id;
                v_oaa.BUYER_COMP_SERV_ID := v_buyer_company_service_id;
                v_oaa.BUYER_END_ORG_ID := v_buyer_org_id;
                v_oaa.AGMT_PERIOD_CODE := v_epa.AGMT_PERIOD_CODE;
                v_oaa.FROM_DT := v_commercials_trade.from_date;
                v_oaa.TO_DT:= v_commercials_trade.to_date;
                v_oaa.PROPOSED_TOTAL_UNITS:= v_commercials_trade.QUANTUM;
                v_oaa.APPROVED_TOTAL_UNITS:= v_commercials_trade.QUANTUM;
                v_oaa.SELLER_COMP_SERV_ID:=v_seller_company_service_id;
                v_oaa.SELLER_END_ORG_ID := v_seller_org_id;
                v_oaa.INJECTION_DIST_SS_ID:= v_company_service.M_SUBSTATION_ID;
                v_oaa.INJECTION_DIST_VOLTAGE_CODE :=  v_company_service.VOLTAGE_CODE;
                v_oaa.STATUS_CODE := 'COMPLETED';
                v_oaa.INTERVAL_TYPE_CODE := v_commercials_trade.INTERVAL_TYPE_CODE;
                v_oaa.C1_UNITS :=v_commercials_trade.C1;
                v_oaa.C2_UNITS :=v_commercials_trade.C2;
                v_oaa.C3_UNITS :=v_commercials_trade.C3;
                v_oaa.C4_UNITS :=v_commercials_trade.C4;
                v_oaa.C5_UNITS :=v_commercials_trade.C5;
                v_oaa.AGREEMENT_DT := v_epa.AGREEMENT_DT;
                v_oaa.PEAK_UNITS := v_commercials_trade.PEAK_UNITS;
                v_oaa.OFF_PEAK_UNITS :=v_commercials_trade.OFF_PEAK_UNITS;
                v_oaa.FLOW_TYPE_CODE := v_flow_type_for_agmt;
                v_oaa.ENABLED := 'Y';
                v_oaa.created_by := 'admin';
                v_oaa.created_dt := sysdate;
                v_oaa.IMPORT_REMARKS := v_commercials_trade.IMPORT_REMARKS;

                INSERT INTO t_oaa VALUES v_oaa;

                --Creating agreement for oaa
                v_aggrement_oaa.id := F_AGREEMENT_SEQ.nextval;
                v_aggrement_oaa.CODE :=  'AGREEMENT'||v_aggrement_oaa.id;
                v_aggrement_oaa.T_OAA_ID := v_oaa.id;
                v_aggrement_oaa.BUYER_COMP_SERV_ID := v_oaa.BUYER_COMP_SERV_ID;
                v_aggrement_oaa.BUYER_EDC_ID := v_oaa.BUYER_END_ORG_ID;
                v_aggrement_oaa.AGREEMENT_PERIOD_CODE := v_oaa.AGMT_PERIOD_CODE;
                v_aggrement_oaa.FROM_DATE := v_oaa.FROM_DT;
                v_aggrement_oaa.TO_DATE:= v_oaa.TO_DT;
                v_aggrement_oaa.STATUS_CODE :='SIGNED';
                v_aggrement_oaa.SELLER_COMP_SERV_ID:=v_oaa.SELLER_COMP_SERV_ID;
                v_aggrement_oaa.INTERVAL_TYPE_CODE := v_oaa.INTERVAL_TYPE_CODE;
                v_aggrement_oaa.C1 :=v_oaa.C1_UNITS;
                v_aggrement_oaa.C2 :=v_oaa.C2_UNITS;
                v_aggrement_oaa.C3 :=v_oaa.C3_UNITS;
                v_aggrement_oaa.C4 :=v_oaa.C4_UNITS;
                v_aggrement_oaa.C5 :=v_oaa.C5_UNITS;
                v_aggrement_oaa.AGREEMENT_DATE :=v_oaa.AGREEMENT_DT;
                v_aggrement_oaa.PEAK_UNITS := v_oaa.PEAK_UNITS;
                v_aggrement_oaa.OFF_PEAK_UNITS :=v_oaa.OFF_PEAK_UNITS;
                v_aggrement_oaa.FLOW_TYPE :=v_oaa.FLOW_TYPE_CODE;
                v_aggrement_oaa.SELLER_COMPANY_ID := v_seller_company_id;
                v_aggrement_oaa.SELLER_EDC_ID := v_oaa.SELLER_END_ORG_ID;
                v_aggrement_oaa.IS_CAPTIVE := 'Y';
                v_aggrement_oaa.TOTAL_UNITS := v_oaa.PEAK_UNITS;
                v_aggrement_oaa.IMPORT_REMARKS := v_commercials_trade.IMPORT_REMARKS;

                INSERT INTO F_AGREEMENT VALUES v_aggrement_oaa;

                v_m_trade_rel.ID := M_TRADE_RELATIONSHIP_SEQ.nextval;
                v_m_trade_rel.M_SELLER_COMPANY_ID := v_seller_company_id;
                v_m_trade_rel.M_SELLER_COMP_SERVICE_ID := v_seller_company_service_id;
                v_m_trade_rel.M_BUYER_COMPANY_ID := v_buyer_company_id;
                v_m_trade_rel.M_BUYER_COMP_SERVICE_ID := v_buyer_company_service_id;
                v_m_trade_rel.QUANTUM := v_commercials_trade.QUANTUM;
                v_m_trade_rel.FROM_DATE := v_commercials_trade.FROM_DATE;
                v_m_trade_rel.TO_DATE := v_commercials_trade.TO_DATE;
                v_m_trade_rel.C1 := v_commercials_trade.C1;
                v_m_trade_rel.C2 := v_commercials_trade.C2;
                v_m_trade_rel.C3 := v_commercials_trade.C3;
                v_m_trade_rel.C4 := v_commercials_trade.C4;
                v_m_trade_rel.C5 := v_commercials_trade.C5;
                v_m_trade_rel.IS_CAPTIVE := v_is_captive;
                v_m_trade_rel.FLOW_TYPE_CODE := v_flow_type_code;
                v_m_trade_rel.PEAK_UNITS := v_commercials_trade.PEAK_UNITS;
                v_m_trade_rel.OFF_PEAK_UNITS := v_commercials_trade.OFF_PEAK_UNITS;
                v_m_trade_rel.INTERVAL_TYPE_CODE := nvl(v_commercials_trade.INTERVAL_TYPE_CODE,'04');
                v_m_trade_rel.SHARE_PERCENT := v_commercials_trade.SHARE_PERCENT; 
                v_m_trade_rel.IMPORT_REMARKS := v_commercials_trade.IMPORT_REMARKS;
                v_m_trade_rel.agreement_type := v_epa.AGMT_PERIOD_CODE ;
                v_m_trade_rel.enabled := 'Y' ;
                v_m_trade_rel.created_by := v_created_by ;
                v_m_trade_rel.created_date := sysdate ;
                v_m_trade_rel.agmt_dt := v_epa.AGREEMENT_DT;


                v_log_result := log_activity('PROCEDURE','ADD_COMMERCIALS','002-203','FOR SERVICE- '||V_SERVICE_NUMBER,'PROCESSING',v_created_by, sysdate,v_commercials_trade.BUYER_COMPANY_SERVICE_NO);

                INSERT INTO M_TRADE_RELATIONSHIP VALUES v_m_trade_rel;

                Update IMPORT_TRADE_REL set remarks='COMPLETED' WHERE BUYER_COMPANY_SERVICE_NO=v_commercials_trade.BUYER_COMPANY_SERVICE_NO and SELLER_COMPANY_SERVICE_NO = V_SERVICE_NUMBER and IMPORT_REMARKS=V_REMARKS;
            EXCEPTION
            WHEN OTHERS THEN 
                v_exception_code := SQLCODE;
                v_exception_msg := SUBSTR(SQLERRM, 1, 200);
                v_result := 'FAILURE';
                v_result_desc := v_exception_code || ' - ' || v_exception_msg;
                -- dbms_output.put_line(v_result_desc);
                v_log_result := log_activity('PROCEDURE','ADD_COMMERCIALS','002-204','FOR SERVICE- '||V_SERVICE_NUMBER,'EXITING LOOP as one trade-relationship had error',v_created_by, sysdate,v_commercials_trade.BUYER_COMPANY_SERVICE_NO);
                Update IMPORT_TRADE_REL set remarks='FAILED', result=v_result, result_Desc=v_result_desc WHERE BUYER_COMPANY_SERVICE_NO=v_commercials_trade.BUYER_COMPANY_SERVICE_NO and SELLER_COMPANY_SERVICE_NO = V_SERVICE_NUMBER and IMPORT_REMARKS=V_REMARKS;

            end;            
        END LOOP;

    END IF;
    v_result := 'SUCCESS';
    v_result_desc := '';
EXCEPTION
    WHEN OTHERS THEN 
        v_exception_code := SQLCODE;
        v_exception_msg := SUBSTR(SQLERRM, 1, 200);
        v_result := 'FAILURE';
        v_result_desc := v_exception_code || ' - ' || v_exception_msg;
        -- dbms_output.put_line(v_result_desc);
END;
<<THE_END>>

v_log_result := log_activity('PROCEDURE','ADD_COMMERCIALS',v_result,v_result_code,v_result_desc,v_created_by, sysdate,V_SERVICE_NUMBER);
RETURN v_result;

END ADD_COMMERCIALS;