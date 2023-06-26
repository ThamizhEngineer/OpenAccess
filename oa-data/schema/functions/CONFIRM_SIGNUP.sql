create or replace FUNCTION "CONFIRM_SIGNUP"
(
  V_SIGNUP_ID IN VARCHAR2
) RETURN VARCHAR2 AS
v_signup M_SIGNUP%ROWTYPE;
v_exist_number_count NUMBER;
v_company_id varchar2(50);
v_powerplant_id varchar2(50);
v_company_service_id varchar2(50);
v_company_meter_id varchar2(50);
v_company_shareholder_id varchar2(50);
v_status varchar2(50);
v_reason varchar2(200);
v_is_buyer char(1);
v_is_seller char(1);
v_comp_serv_type_code varchar2(2);
V_BG_ID VARCHAR2(50);
V_TL_ID VARCHAR2(50);
V_DL_ID VARCHAR2(50);
V_UN_ID VARCHAR2(50);
v_exception_code  NUMBER;
v_exception_msg  VARCHAR2(200);
v_result varchar(200):='SUCCESS';
v_log_result VARCHAR2(50):='';
flag VARCHAR2(50):='';
v_signup_traderel M_SIGNUP_TRADE_REL%ROWTYPE ;
v_traderel M_TRADE_RELATIONSHIP%ROWTYPE ;
v_signup_traderel_cursor sys_refcursor ;
BEGIN
	BEGIN

        v_log_result := log_activity('PROCEDURE','CONFIRM_SIGNUP','START','Start - '||V_SIGNUP_ID,'','', sysdate,V_SIGNUP_ID);

        select  * into v_signup from M_SIGNUP where id =V_SIGNUP_ID;
        SELECT count("number") INTO v_exist_number_count FROM M_COMPANY_SERVICE WHERE "number" = v_signup.HTSC_NUMBER;


	  if(v_signup.is_complete = 'Y') then
	    v_reason := 'Signup already completed';
	    V_RESULT := 'FAILURE';
	    GOTO THE_END;
	  ELSIF( v_signup.HTSC_NUMBER IS NULL OR  v_signup.HTSC_NUMBER ='' ) THEN
	  	v_reason := 'Service Number is mandatory';
	    V_RESULT := 'FAILURE';
	    GOTO THE_END;
	  ELSIF(v_exist_number_count > 0) THEN

	  	v_reason := 'A service with number-'|| v_signup.HTSC_NUMBER||' already exists';
	    V_RESULT := 'FAILURE';
	    GOTO THE_END;
    ELSE
      IF(v_signup.PURPOSE = '01') THEN
      --01-BUYER
        v_is_buyer :='Y';
        v_is_seller :='N';
        v_comp_serv_type_code:='02';

      ELSE
        v_is_buyer :='N';
        v_is_seller :='Y';
        v_comp_serv_type_code:='03';

      END IF;
	  end if;
	  	v_company_id:= m_company_seq.nextval;
        v_company_service_id:= m_company_seq.nextval;
        v_company_meter_id:= m_company_seq.nextval;
	  	V_BG_ID:= m_company_seq.nextval;
	  	V_TL_ID:= m_company_seq.nextval;
	  	V_DL_ID:= m_company_seq.nextval;
	  	V_UN_ID:= m_company_seq.nextval;
		v_powerplant_id:= M_POWERPLANT_SEQ.NEXTVAL;


        Insert into M_COMPANY (ID,CODE,NAME,COMPANY_TYPE_CODE,REGISTRATION_NO,REGISTRATION_DATE,COB_DATE,INCORP_PLACE,IS_CAPTIVE,CAPTIVE_PURPOSE,PAN,TAN,CST,GST,ENABLED,
            CREATED_BY,CREATED_DATE,REMARKS,IS_INTERNAL,IS_BUYER,IS_SELLER, IMPORT_REMARKS)
        values (v_company_id,'COM'||v_company_id,v_signup.company_name,'01',v_signup.REGISTRATION_NO,v_signup.REGISTRATION_DATE,null,null,v_signup.IS_CAPTIVE,null,null,null,null,null,'Y',
            v_signup.created_by,sysdate,v_signup.remarks,'N',v_is_buyer,v_is_seller,v_signup.IMPORT_REMARKS);



        Insert into M_COMPANY_SERVICE (ID,COMP_SER_TYPE_CODE,"number",M_COMPANY_ID,M_ORG_ID,CAPACITY,M_SUBSTATION_ID,M_FEEDER_ID,REF_NUMBER,VOLTAGE_CODE,FUEL_TYPE_CODE,TARIFF,TOTAL_CAPACITY,ENABLED,REMARKS,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,TYPE,BANKING_SERVICE_ID,BANKING_SERVICE_NUMBER,TL_SERVICE_ID,TL_SERVICE_NUMBER,DL_SERVICE_ID,DL_SERVICE_NUMBER,UNADJUSTED_SERVICE_ID,UNADJUSTED_SERVICE_NUMBER, IMPORT_REMARKS,FLOW_TYPE_CODE,IS_REC)
        values (v_company_service_id,v_comp_serv_type_code, v_signup.HTSC_NUMBER,v_company_id,v_signup.M_ORG_ID,v_signup.TOTAL_CAPACITY,v_signup.M_SUBSTATION_ID,v_signup.M_FEEDER_ID,'SIGNUP:'||v_signup.ID,v_signup.VOLTAGE,v_signup.FUEL,v_signup.TARIFF,v_signup.TOTAL_CAPACITY,'Y',NULL,v_signup.created_by,SYSDATE,null,NULL,null,V_BG_ID,'BG'||V_BG_ID,V_DL_ID,'TL'||V_TL_ID,V_DL_ID,'DL'||V_DL_ID,V_DL_ID,'UN'||V_UN_ID,v_signup.IMPORT_REMARKS,v_signup.FLOW_TYPE_CODE,v_signup.IS_REC);



        IF(v_is_seller ='Y') THEN

        --insert company meter
          Insert into M_COMPANY_METER (ID,M_COMPANY_SERVICE_ID,METER_NUMBER,METER_MAKE_CODE,ACCURACY_CLASS_CODE,IS_ABTMETER,MF,REMARKS,CREATED_BY,CREATED_DATE,MODEM_NUMBER,ENABLED,METER_CT1,METER_CT2,METER_CT3,METER_PT1,METER_PT2,METER_PT3, IMPORT_REMARKS)
        values (v_company_meter_id,v_company_service_id,v_signup.METER_NUMBER,v_signup.METER_MAKE_CODE,v_signup.ACCURACY_CLASS_TYPE_CODE,v_signup.IS_ABT_METER,v_signup.MULTIPLICATION_FACTOR,v_signup.REMARKS,v_signup.CREATED_BY,SYSDATE,v_signup.MODEM_NO,'Y',v_signup.METER_CT_1,v_signup.METER_CT_2,v_signup.METER_CT_3,v_signup.METER_BT_1,v_signup.METER_BT_2,v_signup.METER_BT_3, v_signup.IMPORT_REMARKS);

        --insert powerplant
          Insert into M_POWERPLANT (ID,IS_PRIMARY,VERSION,CODE,NAME,PLANT_TYPE_CODE,FUEL_TYPE_CODE,M_SERVICE_ID,M_ORG_ID,T_GRID_CONN_APPLN_ID,TOTAL_CAPACITY,M_SUBSTATION_ID,INTERFACE_VOLTAGE_PHASE,INTERFACE_VOLTAGE_FREQUENCY,
                 COMMISSION_DATE,PURPOSE,ENABLED,STATUS,LINE1,CITY,STATE_CODE,PINCODE,VILLAGE,TALUK_CODE,DISTRICT_CODE,
                 PLS_SF_NO,PL_VILLAGE,PL_TOWN,PL_TALUK_CODE,
                PL_DISTRICT_CODE,WIND_PASS_CODE,WIND_ZONE_AREA_CODE,APPLICATION_DT,APPROVAL_DT,REMARKS,CREATED_BY,CREATED_DATE,IMPORT_REMARKS,PLANT_CLASS_TYPE_CODE,M_FEEDER_ID)
          values (v_powerplant_id,'Y',null,M_POWERPLANT_SEQ.CURRVAL,v_signup.POWERPLANT_NAME,v_signup.POWERPLANT_TYPE,v_signup.FUEL,v_company_service_id,v_signup.M_ORG_ID,null,v_signup.TOTAL_CAPACITY,v_signup.M_SUBSTATION_ID,null,null,
                v_signup.COMMISSION_DATE,v_signup.PURPOSE,'Y',null,v_signup.ADDRESS_LINE,v_signup.CITY,v_signup.STATE_CODE,null,v_signup.VILLAGE,v_signup.TALUK_CODE,
                v_signup.DISTRICT_CODE,null,v_signup.VILLAGE,null,v_signup.TALUK_CODE,
                v_signup.DISTRICT_CODE,v_signup.WIND_PASS_CODE,null,null,null,v_signup.REMARKS,'admin',sysdate,v_signup.IMPORT_REMARKS,v_signup.PLANT_CLASS_TYPE_CODE,v_signup.M_FEEDER_ID);


        --insert gen-unit
          Insert into m_generator (ID,M_POWERPLANT_ID,NAME,MAKE_CODE,SERIAL_NO,ROTOR_DIA,HUB_HEIGHT,CAPACITY,REFERENCE_ID,VOLTAGE_CODE,ENABLED,REMARKS,CREATED_BY,CREATED_DATE,NO_OF_UNITS, IMPORT_REMARKS)
          values (m_generator_seq.nextval,M_POWERPLANT_SEQ.currval,null,v_signup.GU_MODEL1,null,null,null,v_signup.GU_CAPACITY1,null,v_signup.VOLTAGE,'Y',v_signup.REMARKS,'admin', sysdate,v_signup.NO_OF_GU1,v_signup.IMPORT_REMARKS);


          if v_signup.GU_MODEL2 IS NOT NULL then
          Insert into m_generator (ID,M_POWERPLANT_ID,NAME,MAKE_CODE,SERIAL_NO,ROTOR_DIA,HUB_HEIGHT,CAPACITY,REFERENCE_ID,VOLTAGE_CODE,ENABLED,REMARKS,CREATED_BY,CREATED_DATE,NO_OF_UNITS, IMPORT_REMARKS)
          values (m_generator_seq.nextval,M_POWERPLANT_SEQ.currval,null,v_signup.GU_MODEL2,null,null,null,v_signup.GU_CAPACITY2,null,v_signup.VOLTAGE,'Y',v_signup.REMARKS,'admin', sysdate,v_signup.NO_OF_GU2,v_signup.IMPORT_REMARKS);

          end if;
           if v_signup.GU_MODEL3 IS NOT NULL then
          Insert into m_generator (ID,M_POWERPLANT_ID,NAME,MAKE_CODE,SERIAL_NO,ROTOR_DIA,HUB_HEIGHT,CAPACITY,REFERENCE_ID,VOLTAGE_CODE,ENABLED,REMARKS,CREATED_BY,CREATED_DATE,NO_OF_UNITS, IMPORT_REMARKS)
          values (m_generator_seq.nextval,M_POWERPLANT_SEQ.currval,null,v_signup.GU_MODEL3,null,null,null,v_signup.GU_CAPACITY3,null,v_signup.VOLTAGE,'Y',v_signup.REMARKS,'admin', sysdate,v_signup.NO_OF_GU3,v_signup.IMPORT_REMARKS);

          end if;
           if v_signup.GU_MODEL4 IS NOT NULL then
          Insert into m_generator (ID,M_POWERPLANT_ID,NAME,MAKE_CODE,SERIAL_NO,ROTOR_DIA,HUB_HEIGHT,CAPACITY,REFERENCE_ID,VOLTAGE_CODE,ENABLED,REMARKS,CREATED_BY,CREATED_DATE,NO_OF_UNITS, IMPORT_REMARKS)
          values (m_generator_seq.nextval,M_POWERPLANT_SEQ.currval,null,v_signup.GU_MODEL4,null,null,null,v_signup.GU_CAPACITY4,null,v_signup.VOLTAGE,'Y',v_signup.REMARKS,'admin', sysdate,v_signup.NO_OF_GU4,v_signup.IMPORT_REMARKS);

          end if;
           if v_signup.GU_MODEL5 IS NOT NULL then
          Insert into m_generator (ID,M_POWERPLANT_ID,NAME,MAKE_CODE,SERIAL_NO,ROTOR_DIA,HUB_HEIGHT,CAPACITY,REFERENCE_ID,VOLTAGE_CODE,ENABLED,REMARKS,CREATED_BY,CREATED_DATE,NO_OF_UNITS, IMPORT_REMARKS)
          values (m_generator_seq.nextval,M_POWERPLANT_SEQ.currval,null,v_signup.GU_MODEL5,null,null,null,v_signup.GU_CAPACITY5,null,v_signup.VOLTAGE,'Y',v_signup.REMARKS,'admin', sysdate,v_signup.NO_OF_GU5,v_signup.IMPORT_REMARKS);

          end if;
           if v_signup.GU_MODEL6 IS NOT NULL then
          Insert into m_generator (ID,M_POWERPLANT_ID,NAME,MAKE_CODE,SERIAL_NO,ROTOR_DIA,HUB_HEIGHT,CAPACITY,REFERENCE_ID,VOLTAGE_CODE,ENABLED,REMARKS,CREATED_BY,CREATED_DATE,NO_OF_UNITS, IMPORT_REMARKS)
          values (m_generator_seq.nextval,M_POWERPLANT_SEQ.currval,null,v_signup.GU_MODEL6,null,null,null,v_signup.GU_CAPACITY6,null,v_signup.VOLTAGE,'Y',v_signup.REMARKS,'admin', sysdate,v_signup.NO_OF_GU6,v_signup.IMPORT_REMARKS);

          end if;
          INSERT INTO AUTH_USER(ID,FIRST_NAME,LAST_NAME,USER_NAME,PASSWORD,USER_TYPE_CODE,IS_SUPER_USER,SYSTEM_KEY_CODE,SYSTEM_REF_KEY,EDC_CODE,COMPANY_ID,ORG_ID,COMPANY_SERVICE_ID, IMPORT_REMARKS, master_confirmed)
          values(USER_ID_SEQ.NEXTVAL,v_signup.HTSC_NUMBER,v_signup.company_name,v_signup.HTSC_NUMBER,'tneb','GEN','N','OA',v_company_service_id,v_signup.M_ORG_ID,v_company_id,v_signup.M_ORG_ID,v_company_service_id,v_signup.IMPORT_REMARKS,'Y');



            v_result := CREATE_AGMTS_FOR_SIGNUP(V_SIGNUP_ID);


            OPEN v_signup_traderel_cursor for SELECT *  FROM M_SIGNUP_TRADE_REL WHERE M_SIGNUP_ID = V_SIGNUP_ID;

             LOOP
            FETCH v_signup_traderel_cursor INTO v_signup_traderel;

            EXIT WHEN v_signup_traderel_cursor%NOTFOUND;

            v_traderel.id:= m_trade_relationship_seq.nextval;
            select v_company_id, v_company_service_id,v_signup_traderel.M_BUYER_COMPANY_ID,v_signup_traderel.M_BUYER_COMP_SERVICE_ID,v_signup_traderel.QUANTUM,v_signup_traderel.FROM_DATE,v_signup_traderel.TO_DATE,v_signup_traderel.C1,v_signup_traderel.C2
            ,v_signup_traderel.C3,v_signup_traderel.C4,v_signup_traderel.C5,v_signup_traderel.IS_CAPTIVE,v_signup_traderel.PEAK_UNITS,v_signup_traderel.OFF_PEAK_UNITS,v_signup_traderel.INTERVAL_TYPE_CODE,v_signup_traderel.SHARE_PERCENT,'SIGNUP',v_signup.IMPORT_REMARKS,v_signup.flow_type_code
            into v_traderel.M_SELLER_COMPANY_ID,v_traderel.M_SELLER_COMP_SERVICE_ID,v_traderel.M_BUYER_COMPANY_ID,v_traderel.M_BUYER_COMP_SERVICE_ID,v_traderel.QUANTUM,v_traderel.FROM_DATE,v_traderel.TO_DATE,v_traderel.C1,
            v_traderel.C2,v_traderel.C3,v_traderel.C4,v_traderel.C5,v_traderel.IS_CAPTIVE,v_traderel.PEAK_UNITS,v_traderel.OFF_PEAK_UNITS,v_traderel.INTERVAL_TYPE_CODE,v_traderel.SHARE_PERCENT,v_traderel.TRADE_RELATIONSHIP_SOURCE_CODE,v_traderel.IMPORT_REMARKS,v_traderel.flow_type_code from dual;

            if v_signup_traderel.INTERVAL_TYPE_CODE='04' then
            v_company_shareholder_id:= m_company_seq.nextval;

            insert into M_COMPANY_SHAREHOLDER (ID,M_COMPANY_ID,M_SHAREHOLDER_COMPANY_ID,"share",IMPORT_REMARKS) VALUES(v_company_shareholder_id,v_company_id,v_signup_traderel.M_BUYER_COMPANY_ID,v_signup_traderel.SHARE_PERCENT,v_signup.IMPORT_REMARKS);


            end if;

            INSERT INTO M_TRADE_RELATIONSHIP VALUES v_traderel;

            view_refresh.COMP_SERV_REFRESH(v_signup.HTSC_NUMBER, v_result, v_reason);

            END LOOP;


          update M_SIGNUP set is_complete = 'Y' where id=V_SIGNUP_ID;
        END IF;

	exception
	  when others then
	    v_exception_code := SQLCODE;
	    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
	    v_result := 'FAILURE';
	    v_reason := v_exception_code || ' - ' || v_exception_msg;
          v_log_result := log_activity('PROCEDURE','CONFIRM_SIGNUP','EH','Exception - '||v_exception_code,V_RESULT,v_reason, sysdate,V_SIGNUP_ID);
	END;
   <<THE_END>>

  IF V_RESULT = 'SUCCESS' THEN
    COMMIT;
  else
    v_result := v_result || ' - ' || v_reason;
  END IF;

   v_log_result := log_activity('PROCEDURE','CONFIRM_SIGNUP','End',V_RESULT,V_RESULT,v_reason, sysdate,V_SIGNUP_ID);

  return v_result;
END CONFIRM_SIGNUP;