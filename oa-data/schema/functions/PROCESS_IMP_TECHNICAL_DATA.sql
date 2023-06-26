CREATE OR REPLACE FUNCTION OPENACCESS."PROCESS_IMP_TECHNICAL_DATA" 
(
  V_REMARKS IN VARCHAR2 
) RETURN VARCHAR2 AS 
v_created_By  varchar2(50):= 'admin';
v_status varchar2(50);
v_reason varchar2(200):='';
v_exception_code  NUMBER;
v_exception_msg  VARCHAR2(200);
v_result varchar(300):='SUCCESS';
v_log_result varchar(300):='SUCCESS';
v_total_count number:=0;

BEGIN

-- begin for exception handling
BEGIN

  v_log_result := log_activity('FUNCTION','PROCESS_IMP_TECHNICAL_DATA','START','Start - '||V_REMARKS,'','', sysdate,V_REMARKS);

  select count(*) into v_total_count from imp_technical_data where remarks = V_REMARKS;
  if(v_total_count=0) then
      Raise_Application_Error (-20343, 'No records to process');
  end if;

  FOR imp_data IN ( select * from imp_technical_data  where remarks =V_REMARKS)
    LOOP

      if imp_data.m_company_id is not null then
        update m_company set name=imp_data.COMPANY_NAME where id=imp_data.m_company_id;
      end if;

      if imp_data.M_COMPANY_SERVICE_ID is not null then
        update m_company_service set M_ORG_ID=imp_data.M_ORG_ID ,M_SUBSTATION_ID=imp_data.M_SUBSTATION_ID ,M_FEEDER_ID=imp_data.M_FEEDER_ID,
          TOTAL_CAPACITY=imp_data.TOTAL_CAPACITY_KW,IS_REC=imp_data.IS_REC,FUEL_TYPE_CODE=imp_data.FUEL_CODE,IMPORT_REMARKS=imp_data.IMPORT_REMARKS
          where id= imp_data.M_COMPANY_SERVICE_ID;
      end if;

      if imp_data.M_POWERPLANT_ID is not null then
          update m_powerplant set COMMISSION_DATE=imp_data.COMMISSION_DATE , VILLAGE=imp_data.VILLAGE ,
            PLS_SF_NO=imp_data.SF_NO,WIND_PASS_CODE=imp_data.WIND_PASS_CODE where id= imp_data.M_POWERPLANT_ID;
      end if;

      if imp_data.M_METER_ID_1 is not null then
        update m_company_meter set PT_RATIO = imp_data.METER1_PT_RATIO,CT_RATIO= imp_data.METER1_CT_RATIO,MF= imp_data.METER1_MF,
          METER_MAKE_CODE= imp_data.METER1_METER_MAKE_CODE,METER_NUMBER= imp_data.METER1_METER_NUMBER,MODEM_NUMBER= imp_data.METER1_MODEM_NO,
          ACCURACY_CLASS_CODE = imp_data.METER1_ACCURACY_CLASS_CODE where id =imp_data.M_METER_ID_1;
      end if;

      if imp_data.M_METER_ID_2 is not null then
        update m_company_meter set PT_RATIO = imp_data.METER2_PT_RATIO,CT_RATIO= imp_data.METER2_CT_RATIO,MF= imp_data.METER2_MF,
          METER_MAKE_CODE= imp_data.METER2_METER_MAKE_CODE,METER_NUMBER= imp_data.METER2_METER_NUMBER,MODEM_NUMBER= imp_data.METER2_MODEM_NO,
          ACCURACY_CLASS_CODE = imp_data.METER2_ACCURACY_CLASS_CODE where id =imp_data.M_METER_ID_2;
      end if;

      if imp_data.M_METER_ID_3 is not null then
        update m_company_meter set PT_RATIO = imp_data.METER3_PT_RATIO,CT_RATIO= imp_data.METER3_CT_RATIO,MF= imp_data.METER3_MF,
          METER_MAKE_CODE= imp_data.METER3_METER_MAKE_CODE,METER_NUMBER= imp_data.METER3_METER_NUMBER,MODEM_NUMBER= imp_data.METER3_MODEM_NO,
          ACCURACY_CLASS_CODE = imp_data.METER3_ACCURACY_CLASS_CODE where id =imp_data.M_METER_ID_3;
      end if;

      if imp_data.M_METER_ID_4 is not null then
        update m_company_meter set PT_RATIO = imp_data.METER4_PT_RATIO,CT_RATIO= imp_data.METER4_CT_RATIO,MF= imp_data.METER4_MF,
          METER_MAKE_CODE= imp_data.METER4_METER_MAKE_CODE,METER_NUMBER= imp_data.METER4_METER_NUMBER,MODEM_NUMBER= imp_data.METER4_MODEM_NO,
          ACCURACY_CLASS_CODE = imp_data.METER4_ACCURACY_CLASS_CODE where id =imp_data.M_METER_ID_4;
      end if;

      if imp_data.M_METER_ID_5 is not null then
        update m_company_meter set PT_RATIO = imp_data.METER5_PT_RATIO,CT_RATIO= imp_data.METER5_CT_RATIO,MF= imp_data.METER5_MF,
          METER_MAKE_CODE= imp_data.METER5_METER_MAKE_CODE,METER_NUMBER= imp_data.METER5_METER_NUMBER,MODEM_NUMBER= imp_data.METER5_MODEM_NO,
          ACCURACY_CLASS_CODE = imp_data.METER5_ACCURACY_CLASS_CODE where id =imp_data.M_METER_ID_5;
      end if;

      if imp_data.M_METER_ID_6 is not null then
        update m_company_meter set PT_RATIO = imp_data.METER6_PT_RATIO,CT_RATIO= imp_data.METER6_CT_RATIO,MF= imp_data.METER6_MF,
          METER_MAKE_CODE= imp_data.METER6_METER_MAKE_CODE,METER_NUMBER= imp_data.METER6_METER_NUMBER,MODEM_NUMBER= imp_data.METER6_MODEM_NO,
          ACCURACY_CLASS_CODE = imp_data.METER6_ACCURACY_CLASS_CODE where id =imp_data.M_METER_ID_6;
      end if;

      if imp_data.M_GENERATOR_ID_1 is not null then
        update m_generator set MAKE_CODE = imp_data.GEN1_GEN_MAKE_CODE,NO_OF_UNITS= imp_data.GEN1_NO_OF_GEN_UNITS,CAPACITY = imp_data.GEN1_GEN_UNIT_CAPACITY,
          VOLTAGE_CODE =imp_data.GEN1_VOLTAGE_CODE,SERIAL_NO=imp_data.GEN1_TURBINE_SL_NO,ROTOR_DIA = imp_data.GEN1_TURBINE_ROTOR_DIA,
          HUB_HEIGHT =imp_data.GEN1_TURBINE_HUB_HEIGHT where id= imp_data.M_GENERATOR_ID_1;
      end if;

      if imp_data.M_GENERATOR_ID_2 is not null then
        update m_generator set MAKE_CODE = imp_data.GEN2_GEN_MAKE_CODE,NO_OF_UNITS= imp_data.GEN2_NO_OF_GEN_UNITS,CAPACITY = imp_data.GEN2_GEN_UNIT_CAPACITY,
          VOLTAGE_CODE =imp_data.GEN2_VOLTAGE_CODE,SERIAL_NO=imp_data.GEN2_TURBINE_SL_NO,ROTOR_DIA = imp_data.GEN2_TURBINE_ROTOR_DIA,
          HUB_HEIGHT =imp_data.GEN2_TURBINE_HUB_HEIGHT where id= imp_data.M_GENERATOR_ID_2;
      end if;

      if imp_data.M_GENERATOR_ID_3 is not null then
        update m_generator set MAKE_CODE = imp_data.GEN3_GEN_MAKE_CODE,NO_OF_UNITS= imp_data.GEN3_NO_OF_GEN_UNITS,CAPACITY = imp_data.GEN3_GEN_UNIT_CAPACITY,
          VOLTAGE_CODE =imp_data.GEN3_VOLTAGE_CODE,SERIAL_NO=imp_data.GEN3_TURBINE_SL_NO,ROTOR_DIA = imp_data.GEN3_TURBINE_ROTOR_DIA,
          HUB_HEIGHT =imp_data.GEN3_TURBINE_HUB_HEIGHT where id= imp_data.M_GENERATOR_ID_3;
      end if;

      if imp_data.M_GENERATOR_ID_4 is not null then
        update m_generator set MAKE_CODE = imp_data.GEN4_GEN_MAKE_CODE,NO_OF_UNITS= imp_data.GEN4_NO_OF_GEN_UNITS,CAPACITY = imp_data.GEN4_GEN_UNIT_CAPACITY,
          VOLTAGE_CODE =imp_data.GEN4_VOLTAGE_CODE,SERIAL_NO=imp_data.GEN4_TURBINE_SL_NO,ROTOR_DIA = imp_data.GEN4_TURBINE_ROTOR_DIA,
          HUB_HEIGHT =imp_data.GEN4_TURBINE_HUB_HEIGHT where id= imp_data.M_GENERATOR_ID_4;
      end if;

      if imp_data.M_GENERATOR_ID_5 is not null then
        update m_generator set MAKE_CODE = imp_data.GEN5_GEN_MAKE_CODE,NO_OF_UNITS= imp_data.GEN5_NO_OF_GEN_UNITS,CAPACITY = imp_data.GEN5_GEN_UNIT_CAPACITY,
          VOLTAGE_CODE =imp_data.GEN5_VOLTAGE_CODE,SERIAL_NO=imp_data.GEN5_TURBINE_SL_NO,ROTOR_DIA = imp_data.GEN5_TURBINE_ROTOR_DIA,
          HUB_HEIGHT =imp_data.GEN5_TURBINE_HUB_HEIGHT where id= imp_data.M_GENERATOR_ID_5;
      end if;

      if imp_data.M_GENERATOR_ID_6 is not null then
          update m_generator set MAKE_CODE = imp_data.GEN6_GEN_MAKE_CODE,NO_OF_UNITS= imp_data.GEN6_NO_OF_GEN_UNITS,CAPACITY = imp_data.GEN6_GEN_UNIT_CAPACITY,
            VOLTAGE_CODE =imp_data.GEN6_VOLTAGE_CODE,SERIAL_NO=imp_data.GEN6_TURBINE_SL_NO,ROTOR_DIA = imp_data.GEN6_TURBINE_ROTOR_DIA,
            HUB_HEIGHT =imp_data.GEN6_TURBINE_HUB_HEIGHT where id= imp_data.M_GENERATOR_ID_6;
      end if;

    end loop;


    commit;
    exception
	  when others then
	    v_exception_code := SQLCODE;
	    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
	    v_result := 'FAILURE';
	    v_reason := v_exception_code || ' - ' || v_exception_msg;
      -- -- dbms_output.put_line(v_reason);
        v_log_result := log_activity('FUNCTION','PROCESS_IMP_TECHNICAL_DATA','EXCEPTION',v_reason,'','', sysdate,V_REMARKS);
    END;

  v_log_result := log_activity('FUNCTION','PROCESS_IMP_TECHNICAL_DATA','END','Import complete','','', sysdate,V_REMARKS);

  return V_RESULT || ' - ' || v_reason;

END PROCESS_IMP_TECHNICAL_DATA;

