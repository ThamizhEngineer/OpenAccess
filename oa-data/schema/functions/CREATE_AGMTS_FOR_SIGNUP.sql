create or replace FUNCTION            "CREATE_AGMTS_FOR_SIGNUP"
(
  V_SIGNUP_ID IN VARCHAR2
) RETURN VARCHAR2 AS
v_signup M_SIGNUP%ROWTYPE;
v_ewa T_EWA%ROWTYPE;
v_ewa_line T_EWA_LINE%ROWTYPE;
v_oaa T_OAA%ROWTYPE;
v_seller_company_id varchar2(50);
v_seller_service_id varchar2(50);
buyer_end_org_id varchar2(50);
v_reason varchar2(200);
v_exception_code  NUMBER;
v_exception_msg  VARCHAR2(64);
v_result varchar(200):='SUCCESS';
v_log_result VARCHAR2(50):='';
v_signup_traderel M_SIGNUP_TRADE_REL%ROWTYPE ;
v_signup_traderel_cursor sys_refcursor ;
V_DATE_DIFF VARCHAR2(17);
v_aggrement_ewa F_AGREEMENT%ROWTYPE;
v_aggrement_oaa F_AGREEMENT%ROWTYPE;
v_aggrement_ewa_line F_AGREEMENT_LINE%ROWTYPE;
v_aggrement_oaa_line F_AGREEMENT_LINE%ROWTYPE;
v_epa T_EPA%ROWTYPE;
v_epa_line T_EPA_LINES%ROWTYPE;
v_aggrement_epa F_AGREEMENT%ROWTYPE;
v_aggrement_epa_line F_AGREEMENT_LINE%ROWTYPE;
v_is_captive varchar2(10):='N';
v_flow_type_code varchar2(50);
v_flow_type_for_agmt varchar2(50);
v_fuel_grp varchar2(50);
BEGIN
    BEGIN

--    v_log_result := log_activity('PROCEDURE','CREATE_AGMTS_FOR_SIGNUP','START','Start - '||V_SIGNUP_ID,'','', sysdate,V_SIGNUP_ID);

    select  * into v_signup from M_SIGNUP where id =V_SIGNUP_ID;

	select  id ,m_company_id into v_seller_service_id,v_seller_company_id from m_company_service where "number" = v_signup.htsc_number;
	   -- dbms_output.put_line('v_seller_service_id' || v_seller_service_id);
   

    if v_flow_type_code ='IS-CAPTIVE' then
        v_flow_type_for_agmt :='CAPTIVE';
        v_is_captive:='Y';
    else
        v_flow_type_for_agmt := v_flow_type_code;
        v_is_captive:='N';
    end if;

-- IF(v_signup.IS_CAPTIVE='Y' OR v_signup.NATURE_OF_BOARD='WEG-THIRD-PARTY') THEN
IF v_signup.flow_type_code in ('THIRD_PARTY', 'IS-CAPTIVE') THEN

       -- dbms_output.put_line('1');
    -- insert EWA
    
       v_ewa.id := t_ewa_seq.nextval;
       v_ewa.code := 'EWA'||v_ewa.id;
       v_ewa.SELLER_COMP_SERV_ID := v_seller_service_id;
       v_ewa.SELLER_END_ORG_ID := v_signup.m_org_id;
       v_ewa.INJECTION_VOLTAGE_CODE := v_signup.voltage;
       v_ewa.TOAL_APPROVED_UNITS := v_signup.SANCTIONED_QUANTUM;
       v_ewa.FROM_DT := ''; --WILL BE UPDATED FROM TR LOOP
       v_ewa.TO_DT := ''; --WILL BE UPDATED FROM TR LOOP
       v_ewa.STATUS_CODE := 'COMPLETED';
-------------------------------

       v_ewa.FLOW_TYPE_CODE := v_flow_type_for_agmt;
       v_ewa.SELLER_IS_CAPTIVE := v_is_captive;
		/*
        IF(v_signup.NATURE_OF_BOARD='WEG-THIRD-PARTY')THEN
		       v_ewa.FLOW_TYPE_CODE := 'THIRD-PARTY';
       		   v_ewa.SELLER_IS_CAPTIVE := 'N';
		ELSE
		       v_ewa.FLOW_TYPE_CODE := 'CAPTIVE';
       		   v_ewa.SELLER_IS_CAPTIVE := 'Y';
		END IF;
        */
--------------------------------
       v_ewa.ENABLED := 'Y';
       v_ewa.created_date := sysdate;
       v_ewa.IMPORT_REMARKS := v_signup.IMPORT_REMARKS;

       -- dbms_output.put_line('2-'||v_ewa.id);
       INSERT INTO T_EWA VALUES v_ewa;
       -- dbms_output.put_line('3');

        v_aggrement_ewa.id := F_AGREEMENT_SEQ.nextval;
        v_aggrement_ewa.CODE :=  'AGREEMENT'||v_aggrement_ewa.id;
        v_aggrement_ewa.T_EWA_ID := v_ewa.id;
        v_aggrement_ewa.SELLER_COMP_SERV_ID := v_ewa.SELLER_COMP_SERV_ID;
        v_aggrement_ewa.SELLER_EDC_ID := v_ewa.SELLER_END_ORG_ID;
        v_aggrement_ewa.AGREEMENT_DATE := v_ewa.FROM_DT;
        v_aggrement_ewa.SELLER_COMPANY_ID := v_seller_company_id;
        v_aggrement_ewa.FROM_DATE := v_ewa.FROM_DT;
        v_aggrement_ewa.TO_DATE := v_ewa.TO_DT;
-------------------------------

       v_aggrement_ewa.IS_CAPTIVE := v_is_captive;
/*       
		IF(v_signup.NATURE_OF_BOARD='WEG-THIRD-PARTY')THEN
        		v_aggrement_ewa.IS_CAPTIVE := 'N';
		ELSE
			    v_aggrement_ewa.IS_CAPTIVE := 'Y';
		END IF;
        */
--------------------------------
        v_aggrement_ewa.AGREEMENT_PERIOD_CODE := v_ewa.AGMT_PERIOD_CODE;
        v_aggrement_ewa.FLOW_TYPE := v_ewa.FLOW_TYPE_CODE;
        v_aggrement_ewa.STATUS_CODE := 'SIGNED';
        v_aggrement_ewa.M_SIGNUP_ID := V_SIGNUP_ID;
        v_aggrement_ewa.IMPORT_REMARKS := v_signup.IMPORT_REMARKS;

        -- dbms_output.put_line('02'||v_aggrement_ewa.id);
        INSERT INTO F_AGREEMENT VALUES v_aggrement_ewa;
        -- dbms_output.put_line('03');

    for v_signup_traderel in (SELECT *  FROM M_SIGNUP_TRADE_REL WHERE M_SIGNUP_ID = V_SIGNUP_ID)
    LOOP
    BEGIN

    SELECT (TO_DATE-FROM_DATE)
	INTO V_DATE_DIFF
	FROM M_SIGNUP_TRADE_REL where M_SIGNUP_ID =V_SIGNUP_ID and rownum=1;
    -- dbms_output.put_line('v_date_diff' || V_DATE_DIFF );

    IF (V_DATE_DIFF>90 AND V_DATE_DIFF<1825)THEN
		v_ewa.AGMT_PERIOD_CODE := 'MTOA';
    ELSIF (V_DATE_DIFF>1825)THEN
		v_ewa.AGMT_PERIOD_CODE := 'LTOA';
    ELSE
        v_ewa.AGMT_PERIOD_CODE := 'STOA';
    END IF;
    -- dbms_output.put_line('agmt_period_code'||v_ewa.AGMT_PERIOD_CODE);

              -- dbms_output.put_line('4');
              -- set values fro ewa line
              v_ewa_line.id := t_ewa_line_seq.nextval;
              v_ewa_line.T_EWA_ID := v_ewa.id;
              v_ewa_line.BUYER_COMP_SERV_ID := v_signup_traderel.M_BUYER_COMP_SERVICE_ID;
              select m_org_id, voltage_code into v_ewa_line.BUYER_END_ORG_ID, v_ewa_line.DRAWAL_VOLTAGE_CODE from m_company_service where id = v_signup_traderel.M_BUYER_COMP_SERVICE_ID;
              v_ewa_line.INTERVAL_TYPE_CODE := v_signup_traderel.INTERVAL_TYPE_CODE;
              v_ewa_line.SHARE_PERCENTAGE := v_signup_traderel.SHARE_PERCENT;
-------------------------------

       v_ewa_line.IS_CAPTIVE := v_is_captive;
       /*
		IF(v_signup.NATURE_OF_BOARD='WEG-THIRD-PARTY')THEN
              v_ewa_line.IS_CAPTIVE := 'N';
		ELSE
		      v_ewa_line.IS_CAPTIVE := 'Y';
		END IF;
        */
--------------------------------
              v_ewa_line.ENABLED := 'Y';
              v_ewa_line.created_by := 'admin';
              v_ewa_line.created_dt := sysdate;
              v_ewa_line.created_date := sysdate;
              v_ewa_line.PROPOSED_UNITS := v_signup_traderel.QUANTUM;
              v_ewa_line.APPROVED_UNITS := v_signup_traderel.QUANTUM;
              v_ewa_line.INJECTION_PEAK_UNITS := v_signup_traderel.PEAK_UNITS;
              v_ewa_line.INJECTION_OFF_PEAK_UNITS := v_signup_traderel.OFF_PEAK_UNITS;
              v_ewa_line.IMPORT_REMARKS := v_signup.IMPORT_REMARKS;

                -- dbms_output.put_line('quantum' || v_signup_traderel.QUANTUM);
              -- dbms_output.put_line('5');

              v_ewa.FROM_DT := v_signup_traderel.from_date;
              v_ewa.TO_DT:= v_signup_traderel.to_date;

              UPDATE T_EWA SET FROM_DT=v_ewa.FROM_DT, TO_DT =  v_ewa.TO_DT,AGMT_PERIOD_CODE=v_ewa.AGMT_PERIOD_CODE WHERE id = v_ewa.id;
              UPDATE F_AGREEMENT SET FROM_DATE=v_ewa.FROM_DT, TO_DATE =  v_ewa.TO_DT,AGREEMENT_PERIOD_CODE=v_ewa.AGMT_PERIOD_CODE,AGREEMENT_DATE=v_ewa.FROM_DT WHERE id = v_aggrement_ewa.id;
              -- insert ewa line
              INSERT INTO t_ewa_line VALUES v_ewa_line;

    select M_COMPANY_ID into v_aggrement_ewa.BUYER_COMP_ID from m_company_service where id = v_signup_traderel.M_BUYER_COMP_SERVICE_ID;

    for v_ewa_line in (SELECT *  FROM T_EWA_LINE WHERE T_EWA_ID = v_ewa.ID)
    LOOP

        v_aggrement_ewa_line.id := F_AGREEMENT_LINE_SEQ.nextval;
        v_aggrement_ewa_line.F_AGREEMENT_ID := v_aggrement_ewa.id;
        v_aggrement_ewa_line.BUYER_COMP_SERV_ID := v_ewa_line.BUYER_COMP_SERV_ID;
        v_aggrement_ewa_line.BUYER_ORG_ID := v_ewa_line.BUYER_END_ORG_ID;
        v_aggrement_ewa_line.DRAWAL_VOLTAGE_CODE := v_ewa_line.DRAWAL_VOLTAGE_CODE;
        v_aggrement_ewa_line.PROPOSED_CAPACITY := v_ewa_line.PROPOSED_UNITS;
        v_aggrement_ewa_line.APPROVED_CAPACITY := v_ewa_line.APPROVED_UNITS;
        v_aggrement_ewa_line.FLOW_TYPE_CODE := v_ewa.FLOW_TYPE_CODE; 
        v_aggrement_ewa_line.AGMT_PERIOD_CODE := v_ewa.AGMT_PERIOD_CODE;
        v_aggrement_ewa_line.APPROVED_DT := v_ewa_line.APPROVED_DT;
        v_aggrement_ewa_line.AGREEMENT_DT := v_ewa.FROM_DT;
        v_aggrement_ewa_line.IS_CAPTIVE := v_ewa_line.IS_CAPTIVE;
        v_aggrement_ewa_line.PEAK_UNITS :=v_ewa_line.DRAWAL_PEAK_UNITS;
        v_aggrement_ewa_line.OFF_PEAK_UNITS := v_ewa_line.DRAWAL_OFF_PEAK_UNITS;
        v_aggrement_ewa_line.IMPORT_REMARKS := v_signup.IMPORT_REMARKS;
   END LOOP;

        INSERT INTO F_AGREEMENT_LINE VALUES v_aggrement_ewa_line;

              -- dbms_output.put_line('6-'||v_ewa_line.id );
              -- dbms_output.put_line('6-'||v_signup_traderel.from_date);
              -- dbms_output.put_line('6-'||v_signup_traderel.to_date );
              -- set values fro openaccess
              v_oaa.id := t_oaa_seq.nextval;
              v_oaa.CODE := 'OAA'||v_oaa.id;
              v_oaa.BUYER_COMP_SERV_ID := v_signup_traderel.M_BUYER_COMP_SERVICE_ID;
              v_oaa.BUYER_END_ORG_ID := v_ewa_line.BUYER_END_ORG_ID;
              v_oaa.DRAWAL_DIST_VOLTAGE_CODE :=v_ewa_line.DRAWAL_VOLTAGE_CODE;
              v_oaa.AGMT_PERIOD_CODE := v_ewa.AGMT_PERIOD_CODE; -- need to fix this
              v_oaa.FROM_DT := v_signup_traderel.from_date;
              v_oaa.TO_DT:= v_signup_traderel.to_date;
              v_oaa.PROPOSED_TOTAL_UNITS:= v_signup_traderel.QUANTUM;
              v_oaa.APPROVED_TOTAL_UNITS:= v_signup_traderel.QUANTUM;
              v_oaa.SELLER_COMP_SERV_ID:=v_seller_service_id;
              v_oaa.SELLER_END_ORG_ID := v_signup.m_org_id;
              v_oaa.INJECTION_DIST_SS_ID:= v_signup.m_substation_id;
              v_oaa.INJECTION_DIST_VOLTAGE_CODE :=  v_signup.voltage;
              v_oaa.STATUS_CODE := 'COMPLETED';
              v_oaa.INTERVAL_TYPE_CODE := v_signup_traderel.INTERVAL_TYPE_CODE;
              v_oaa.C1_UNITS :=v_signup_traderel.C1;
              v_oaa.C2_UNITS :=v_signup_traderel.C2;
              v_oaa.C3_UNITS :=v_signup_traderel.C3;
              v_oaa.C4_UNITS :=v_signup_traderel.C4;
              v_oaa.C5_UNITS :=v_signup_traderel.C5;
              v_oaa.AGREEMENT_DT :=sysdate;
              v_oaa.PEAK_UNITS := v_signup_traderel.PEAK_UNITS;
              v_oaa.OFF_PEAK_UNITS :=v_signup_traderel.OFF_PEAK_UNITS;
-------------------------------

              v_oaa.FLOW_TYPE_CODE := v_flow_type_for_agmt;
       /*
		IF(v_signup.NATURE_OF_BOARD='WEG-THIRD-PARTY')THEN
              v_oaa.FLOW_TYPE_CODE := 'THIRD-PARTY';
		ELSE
			  v_oaa.FLOW_TYPE_CODE := 'CAPTIVE';

		END IF;
        */
--------------------------------
              v_oaa.ENABLED := 'Y';
              v_oaa.created_by := 'admin';
              v_oaa.created_dt := sysdate;
              v_oaa.IMPORT_REMARKS := v_signup.IMPORT_REMARKS;


              -- dbms_output.put_line('7');
              INSERT INTO t_oaa VALUES v_oaa;

              select M_COMPANY_ID into v_aggrement_oaa.BUYER_COMP_ID from m_company_service where id = v_signup_traderel.M_BUYER_COMP_SERVICE_ID;


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
              v_aggrement_oaa.M_SIGNUP_ID := V_SIGNUP_ID;
              v_aggrement_oaa.SELLER_COMPANY_ID := v_seller_company_id;
              v_aggrement_oaa.SELLER_EDC_ID := v_oaa.SELLER_END_ORG_ID;
-------------------------------

              v_aggrement_oaa.IS_CAPTIVE := v_is_captive;
              /*
		IF(v_signup.NATURE_OF_BOARD='WEG-THIRD-PARTY')THEN
              v_aggrement_oaa.IS_CAPTIVE := 'N';
		ELSE
			  v_aggrement_oaa.IS_CAPTIVE := 'Y';

		END IF;
        */
--------------------------------
              v_aggrement_oaa.TOTAL_UNITS := v_oaa.PEAK_UNITS;
              v_aggrement_oaa.IMPORT_REMARKS := v_signup.IMPORT_REMARKS;

            INSERT INTO F_AGREEMENT VALUES v_aggrement_oaa;


              -- dbms_output.put_line('8-'||v_oaa.id );

            exception
            when others then
              v_exception_code := SQLCODE;
              v_exception_msg := SUBSTR(SQLERRM, 1, 64);
              v_result := 'FAILURE';
              v_reason := v_exception_code || ' - ' || v_exception_msg;
                  v_log_result := log_activity('PROCEDURE','CREATE_AGMTS_FOR_SIGNUP-loop','v_exception_code','Exception - '||v_exception_code,V_RESULT,v_reason, sysdate,V_SIGNUP_ID);
          END;
         END LOOP;
ELSE

       		-- dbms_output.put_line('EPA START');
            v_epa.id := T_EPA_SEQ.nextval;
            v_epa.CODE :=  'EPA'||v_epa.id;
            v_epa.SELLER_COMP_SERV_ID := v_seller_service_id;
            v_epa.SELLER_END_ORG_ID := v_signup.m_org_id;
            v_epa.APPROVED_TOTAL_UNITS := v_signup.SANCTIONED_QUANTUM;
            v_epa.STATUS_CODE := 'COMPLETED';
            v_epa.FLOW_TYPE_CODE := v_flow_type_for_agmt;
            v_epa.ENABLED:= 'Y';
            v_epa.SELLER_IS_CAPTIVE := '';
            v_epa.CREATED_DATE := sysdate;
            v_epa.FROM_DT := '';
            v_epa.TO_DT := '';
            v_epa.PEAK_UNITS  := '';
            v_epa.OFF_PEAK_UNITS  :='';
            v_epa.IMPORT_REMARKS := v_signup.IMPORT_REMARKS;

            INSERT INTO T_EPA VALUES v_epa;

        for v_signup_traderel in (SELECT *  FROM M_SIGNUP_TRADE_REL WHERE M_SIGNUP_ID = V_SIGNUP_ID)
        LOOP
        BEGIN
    SELECT (TO_DATE-FROM_DATE)
	INTO V_DATE_DIFF
	FROM M_SIGNUP_TRADE_REL where M_SIGNUP_ID =V_SIGNUP_ID and rownum=1;
    -- dbms_output.put_line('v_date_diff' || V_DATE_DIFF );
        IF (V_DATE_DIFF>90 AND V_DATE_DIFF<1825)THEN
			v_epa.AGMT_PERIOD_CODE := 'MTOA';
		ELSIF (V_DATE_DIFF>1825)THEN
			v_epa.AGMT_PERIOD_CODE := 'LTOA';
		ELSE
			v_epa.AGMT_PERIOD_CODE := 'STOA';
		END IF;
              -- set values fro epa line
              v_epa_line.id := T_EPA_LINE_SEQ.nextval;
              v_epa_line.T_EPA_ID := v_epa.id;
              v_epa_line.ENABLED := 'Y';
              v_epa_line.CREATED_BY := 'admin';
              v_epa_line.CREATED_DT := sysdate;
              v_epa_line.PROPOSED_TOTAL_UNITS := v_signup_traderel.QUANTUM;
              v_epa_line.APPROVED_TOTAL_UNITS := v_signup_traderel.QUANTUM;
              v_epa.PEAK_UNITS  := v_signup_traderel.PEAK_UNITS;
              v_epa.OFF_PEAK_UNITS  := v_signup_traderel.OFF_PEAK_UNITS;
              v_epa.BUYER_COMP_SERV_ID :=v_signup_traderel.M_BUYER_COMP_SERVICE_ID;

              v_epa.FROM_DT := v_signup_traderel.from_date;
              v_epa.TO_DT:= v_signup_traderel.to_date;
              v_epa.IMPORT_REMARKS := v_signup.IMPORT_REMARKS;

            UPDATE T_EPA SET FROM_DT=v_epa.FROM_DT, TO_DT =  v_epa.TO_DT,PEAK_UNITS= v_epa.PEAK_UNITS,OFF_PEAK_UNITS= v_epa.OFF_PEAK_UNITS,AGMT_PERIOD_CODE=v_epa.AGMT_PERIOD_CODE WHERE id = v_epa.id;
            UPDATE F_AGREEMENT SET FROM_DATE=v_epa.FROM_DT, TO_DATE =  v_epa.TO_DT,AGREEMENT_PERIOD_CODE=v_epa.AGMT_PERIOD_CODE,AGREEMENT_DATE=v_epa.FROM_DT WHERE id = v_aggrement_epa.id;

              -- insert ewa line

              INSERT INTO T_EPA_LINES VALUES v_epa_line;

                select M_COMPANY_ID,M_ORG_ID into v_aggrement_epa.BUYER_COMP_ID,v_aggrement_epa.BUYER_EDC_ID from m_company_service where id = v_signup_traderel.M_BUYER_COMP_SERVICE_ID;

           v_aggrement_epa.id := F_AGREEMENT_SEQ.nextval;
           v_aggrement_epa.CODE :=  'AGREEMENT'||v_aggrement_epa.id;
           v_aggrement_epa.T_EPA_ID := v_epa.id;
           v_aggrement_epa.SELLER_COMP_SERV_ID := v_epa.SELLER_COMP_SERV_ID;
           v_aggrement_epa.SELLER_EDC_ID := v_epa.SELLER_END_ORG_ID;
           v_aggrement_epa.AGREEMENT_DATE := v_epa.FROM_DT;
           v_aggrement_epa.SELLER_COMPANY_ID := v_seller_company_id;
           v_aggrement_epa.FROM_DATE := v_epa.FROM_DT;
           v_aggrement_epa.TO_DATE := v_epa.TO_DT;
           v_aggrement_epa.AGREEMENT_PERIOD_CODE := v_epa.AGMT_PERIOD_CODE;
           v_aggrement_epa.FLOW_TYPE := v_epa.FLOW_TYPE_CODE;
           v_aggrement_epa.STATUS_CODE := 'SIGNED';
           v_aggrement_epa.M_SIGNUP_ID := V_SIGNUP_ID;
           v_aggrement_epa.BUYER_COMP_SERV_ID :=  'TNEB-'||v_aggrement_epa.SELLER_EDC_ID;
           v_aggrement_epa.IMPORT_REMARKS := v_signup.IMPORT_REMARKS;

        INSERT INTO F_AGREEMENT VALUES v_aggrement_epa;
 select m_org_id into buyer_end_org_id from m_company_service where id = v_signup_traderel.M_BUYER_COMP_SERVICE_ID;

    -- dbms_output.put_line('start oaa for epa');
    -- dbms_output.put_line('from date-'||v_signup_traderel.from_date);
    -- dbms_output.put_line('to date-'||v_signup_traderel.to_date );
              -- set values fro openaccess
              v_oaa.id := t_oaa_seq.nextval;
              v_oaa.CODE := 'OAA'||v_oaa.id;
              v_oaa.BUYER_COMP_SERV_ID := v_signup_traderel.M_BUYER_COMP_SERVICE_ID;
              v_oaa.BUYER_END_ORG_ID := buyer_end_org_id;
--              v_oaa.DRAWAL_DIST_VOLTAGE_CODE :=v_ewa_line.DRAWAL_VOLTAGE_CODE;
              v_oaa.AGMT_PERIOD_CODE := v_epa.AGMT_PERIOD_CODE; -- need to fix this
              v_oaa.FROM_DT := v_signup_traderel.from_date;
              v_oaa.TO_DT:= v_signup_traderel.to_date;
              v_oaa.PROPOSED_TOTAL_UNITS:= v_signup_traderel.QUANTUM;
              v_oaa.APPROVED_TOTAL_UNITS:= v_signup_traderel.QUANTUM;
              v_oaa.SELLER_COMP_SERV_ID:=v_seller_service_id;
              v_oaa.SELLER_END_ORG_ID := v_signup.m_org_id;
              v_oaa.INJECTION_DIST_SS_ID:= v_signup.m_substation_id;
              v_oaa.INJECTION_DIST_VOLTAGE_CODE :=  v_signup.voltage;
              v_oaa.STATUS_CODE := 'COMPLETED';
              v_oaa.INTERVAL_TYPE_CODE := v_signup_traderel.INTERVAL_TYPE_CODE;
              v_oaa.C1_UNITS :=v_signup_traderel.C1;
              v_oaa.C2_UNITS :=v_signup_traderel.C2;
              v_oaa.C3_UNITS :=v_signup_traderel.C3;
              v_oaa.C4_UNITS :=v_signup_traderel.C4;
              v_oaa.C5_UNITS :=v_signup_traderel.C5;
              v_oaa.AGREEMENT_DT :=sysdate;
              v_oaa.PEAK_UNITS := v_signup_traderel.PEAK_UNITS;
              v_oaa.OFF_PEAK_UNITS :=v_signup_traderel.OFF_PEAK_UNITS;
              v_oaa.FLOW_TYPE_CODE := v_flow_type_for_agmt;
              v_oaa.ENABLED := 'Y';
              v_oaa.created_by := 'admin';
              v_oaa.created_dt := sysdate;
              v_oaa.IMPORT_REMARKS := v_signup.IMPORT_REMARKS;

                -- dbms_output.put_line('buyer end org id-'||v_oaa.BUYER_END_ORG_ID);
                -- dbms_output.put_line('seller service id-'||v_seller_service_id);

              -- dbms_output.put_line('7');
              INSERT INTO t_oaa VALUES v_oaa;

              select M_COMPANY_ID into v_aggrement_oaa.BUYER_COMP_ID from m_company_service where id = v_signup_traderel.M_BUYER_COMP_SERVICE_ID;


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
              v_aggrement_oaa.M_SIGNUP_ID := V_SIGNUP_ID;
              v_aggrement_oaa.SELLER_COMPANY_ID := v_seller_company_id;
              v_aggrement_oaa.SELLER_EDC_ID := v_oaa.SELLER_END_ORG_ID;
              v_aggrement_oaa.IS_CAPTIVE := v_is_captive;
              v_aggrement_oaa.TOTAL_UNITS := v_oaa.PEAK_UNITS;
              v_aggrement_oaa.IMPORT_REMARKS := v_signup.IMPORT_REMARKS;

            INSERT INTO F_AGREEMENT VALUES v_aggrement_oaa;


    END;
    END LOOP;


END IF;
    exception
      when others then
        v_exception_code := SQLCODE;
        v_exception_msg := SUBSTR(SQLERRM, 1, 64);
        v_result := 'FAILURE';
        v_reason := v_exception_code || ' - ' || v_exception_msg;
          v_log_result := log_activity('PROCEDURE','CREATE_AGMTS_FOR_SIGNUP','v_exception_code','Exception - '||v_exception_code,V_RESULT,v_reason, sysdate,V_SIGNUP_ID);
    END;
   <<THE_END>>


              -- dbms_output.put_line('8');
  IF V_RESULT = 'SUCCESS' THEN
    COMMIT;
  else
    v_result := v_result || ' - ' || v_reason;
  END IF;

   v_log_result := log_activity('PROCEDURE','CREATE_AGMTS_FOR_SIGNUP','End',V_RESULT,V_RESULT,v_reason, sysdate,V_SIGNUP_ID);

  return v_result;
END CREATE_AGMTS_FOR_SIGNUP;