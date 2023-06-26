CREATE OR REPLACE FUNCTION OPENACCESS."PROCESS_EXP_TECHNICAL_DATA" 
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
v_total_service_no number:=0;
v_total_trade number:=0;
v_exp_tec_data exp_technical_data%ROWTYPE;
v_tr M_TRADE_RELATIONSHIP%ROWTYPE;
v_company_serv_id VARCHAR2(200);
i number:=1;
j NUMBER:=1;
BEGIN

-- begin for exception handling
BEGIN

   v_log_result := log_activity('FUNCTION','PROCESS_EXP_TECHNICAL_DATA','START','Start - '||V_REMARKS,'','', sysdate,V_REMARKS);

    select count(*) into v_total_count from exp_technical_data where remarks = V_REMARKS;
  if(v_total_count=0) then
      Raise_Application_Error (-20343, 'No records to process');
  end if;


 FOR exp_data IN ( select * from exp_technical_data  where remarks =V_REMARKS)
  LOOP
        select count(*) into v_total_service_no from m_company_service where "number"=exp_data.M_SERVICE_NUMBER;
         if(v_total_count=0) then
                v_reason:=exp_data.M_SERVICE_NUMBER||'- service number doesnot exist';
                v_log_result := log_activity('FUNCTION','PROCESS_EXP_TECHNICAL_DATA','EXCEPTION',v_reason,'','', sysdate,V_REMARKS);
        else
                SELECT COMP_SERV.M_COMPANY_NAME COMPANY_NAME,COMP_SERV.M_ORG_NAME EDC_NAME,COMP_SERV.M_ORG_ID,PP.TOTAL_CAPACITY TOTAL_CAPACITY_KW,PP.PLS_SF_NO,
                PP.VILLAGE,COMP_SERV.M_SUBSTATION_NAME SS_NAME_IN_OA,COMP_SERV.M_SUBSTATION_ID,COMP_SERV.M_FEEDER_NAME FEEDER_NAME_IN_OA,COMP_SERV.M_FEEDER_ID,
                COMP_SERV.IS_REC,  DECODE(IS_BUYER,'Y','BUYER','SELLER') PURPOSE,to_char(PP.COMMISSION_DATE,'dd/MM/yyyy') COMMISSION_DATE_STR,WIND_PASS.VALUE_DESC WIND_PASS_NAME,PP.COMMISSION_DATE,
                PP.WIND_PASS_CODE,PP.IMPORT_REMARKS,COMP_SERV.FUEL_TYPE_CODE,COMP_SERV.FUEL_TYPE_NAME,COMP_SERV.id,
                COMP_SERV.M_COMPANY_ID,pp.id
                INTO
                v_exp_tec_data.COMPANY_NAME,v_exp_tec_data.EDC_NAME,v_exp_tec_data.M_ORG_ID,v_exp_tec_data.TOTAL_CAPACITY_KW,v_exp_tec_data.SF_NO,
                v_exp_tec_data.VILLAGE,v_exp_tec_data.SS_NAME_IN_OA,v_exp_tec_data.M_SUBSTATION_ID,v_exp_tec_data.FEEDER_NAME_IN_OA,v_exp_tec_data.M_FEEDER_ID,
                v_exp_tec_data.IS_REC,v_exp_tec_data.PURPOSE,v_exp_tec_data.COMMISSION_DATE_STR,v_exp_tec_data.WIND_PASS_NAME,v_exp_tec_data.COMMISSION_DATE,
                v_exp_tec_data.WIND_PASS_CODE,v_exp_tec_data.IMPORT_REMARKS,v_exp_tec_data.FUEL,v_exp_tec_data.FUEL_CODE,v_exp_tec_data.M_COMPANY_SERVICE_ID,  
                v_exp_tec_data.M_COMPANY_ID,v_exp_tec_data.M_POWERPLANT_ID
                FROM V_COMPANY_SERVICE COMP_SERV 
                LEFT JOIN M_POWERPLANT PP ON PP.M_SERVICE_ID = COMP_SERV.ID
                LEFT JOIN M_GENERATOR GEN ON GEN.M_POWERPLANT_ID = PP.ID
                LEFT JOIN V_CODES CODE ON CODE.VALUE_CODE = GEN.MAKE_CODE AND CODE.LIST_CODE='GENERATOR_MAKE_CODE'
                LEFT JOIN M_COMPANY_METER METER ON METER.M_COMPANY_SERVICE_ID = COMP_SERV.ID 
                LEFT JOIN V_CODES METER_CODE ON METER_CODE.VALUE_CODE = COMP_SERV.METER_MAKE_CODE AND METER_CODE.LIST_CODE='METER_MAKE_CODE'
                LEFT JOIN V_CODES WIND_PASS ON WIND_PASS.VALUE_CODE = COMP_SERV.METER_MAKE_CODE AND WIND_PASS.LIST_CODE='WIND_PASS_CODE'
                WHERE "number"=exp_data.M_SERVICE_NUMBER;


                 FOR meter IN ( select * from m_company_meter  where M_COMPANY_SERVICE_ID =v_exp_tec_data.M_COMPANY_SERVICE_ID)
                  LOOP
                    if i=1 then
                    v_exp_tec_data.M_METER_ID_1:=meter.ID;
                    v_exp_tec_data.METER1_PT_RATIO:=meter.PT_RATIO;
                    v_exp_tec_data.METER1_CT_RATIO:=meter.CT_RATIO;
                    v_exp_tec_data.METER1_METER_MAKE_CODE:=meter.METER_MAKE_CODE;
                    v_exp_tec_data.METER1_METER_NUMBER:=meter.METER_NUMBER;
                    v_exp_tec_data.METER1_MODEM_NO:=meter.MODEM_NUMBER;
                    v_exp_tec_data.METER1_ACCURACY_CLASS_CODE:=meter.ACCURACY_CLASS_CODE;
                    v_exp_tec_data.METER1_MF:=meter.MF;
                    end if;

                    if i=2 then
                    v_exp_tec_data.M_METER_ID_2:=meter.ID;
                    v_exp_tec_data.METER2_PT_RATIO:=meter.PT_RATIO;
                    v_exp_tec_data.METER2_CT_RATIO:=meter.CT_RATIO;
                    v_exp_tec_data.METER2_METER_MAKE_CODE:=meter.METER_MAKE_CODE;
                    v_exp_tec_data.METER2_METER_NUMBER:=meter.METER_NUMBER;
                    v_exp_tec_data.METER2_MODEM_NO:=meter.MODEM_NUMBER;
                    v_exp_tec_data.METER2_ACCURACY_CLASS_CODE:=meter.ACCURACY_CLASS_CODE;
                    v_exp_tec_data.METER2_MF:=meter.MF;
                    end if;

                    if i=3 then
                    v_exp_tec_data.M_METER_ID_3:=meter.ID;
                    v_exp_tec_data.METER3_PT_RATIO:=meter.PT_RATIO;
                    v_exp_tec_data.METER3_CT_RATIO:=meter.CT_RATIO;
                    v_exp_tec_data.METER3_METER_MAKE_CODE:=meter.METER_MAKE_CODE;
                    v_exp_tec_data.METER3_METER_NUMBER:=meter.METER_NUMBER;
                    v_exp_tec_data.METER3_MODEM_NO:=meter.MODEM_NUMBER;
                    v_exp_tec_data.METER3_ACCURACY_CLASS_CODE:=meter.ACCURACY_CLASS_CODE;
                    v_exp_tec_data.METER3_MF:=meter.MF;
                    end if;

                    if i=4 then
                    v_exp_tec_data.M_METER_ID_4:=meter.ID;
                    v_exp_tec_data.METER4_PT_RATIO:=meter.PT_RATIO;
                    v_exp_tec_data.METER4_CT_RATIO:=meter.CT_RATIO;
                    v_exp_tec_data.METER4_METER_MAKE_CODE:=meter.METER_MAKE_CODE;
                    v_exp_tec_data.METER4_METER_NUMBER:=meter.METER_NUMBER;
                    v_exp_tec_data.METER4_MODEM_NO:=meter.MODEM_NUMBER;
                    v_exp_tec_data.METER4_ACCURACY_CLASS_CODE:=meter.ACCURACY_CLASS_CODE;
                    v_exp_tec_data.METER4_MF:=meter.MF;
                    end if;

                    if i=5 then
                    v_exp_tec_data.M_METER_ID_5:=meter.ID;
                    v_exp_tec_data.METER5_PT_RATIO:=meter.PT_RATIO;
                    v_exp_tec_data.METER5_CT_RATIO:=meter.CT_RATIO;
                    v_exp_tec_data.METER5_METER_MAKE_CODE:=meter.METER_MAKE_CODE;
                    v_exp_tec_data.METER5_METER_NUMBER:=meter.METER_NUMBER;
                    v_exp_tec_data.METER5_MODEM_NO:=meter.MODEM_NUMBER;
                    v_exp_tec_data.METER5_ACCURACY_CLASS_CODE:=meter.ACCURACY_CLASS_CODE;
                    v_exp_tec_data.METER5_MF:=meter.MF;
                    end if;

                    if i=6 then

                    v_exp_tec_data.M_METER_ID_6:=meter.ID;
                    v_exp_tec_data.METER6_PT_RATIO:=meter.PT_RATIO;
                    v_exp_tec_data.METER6_CT_RATIO:=meter.CT_RATIO;
                    v_exp_tec_data.METER6_METER_MAKE_CODE:=meter.METER_MAKE_CODE;
                    v_exp_tec_data.METER6_METER_NUMBER:=meter.METER_NUMBER;
                    v_exp_tec_data.METER6_MODEM_NO:=meter.MODEM_NUMBER;
                    v_exp_tec_data.METER6_ACCURACY_CLASS_CODE:=meter.ACCURACY_CLASS_CODE;
                    v_exp_tec_data.METER6_MF:=meter.MF;
                    end if;


                    i:=i+1;
                    END LOOP;


                     FOR generator IN ( select * from m_generator where M_POWERPLANT_ID =v_exp_tec_data.M_POWERPLANT_ID)
                      LOOP
                    if j=1 then
                    v_exp_tec_data.M_GENERATOR_ID_1:=generator.ID;
                    v_exp_tec_data.GEN1_GEN_MAKE_CODE:=generator.MAKE_CODE;
                    v_exp_tec_data.GEN1_NO_OF_GEN_UNITS:=generator.NO_OF_UNITS;
                    v_exp_tec_data.GEN1_GEN_UNIT_CAPACITY:=generator.CAPACITY;
                    v_exp_tec_data.GEN1_VOLTAGE_CODE:=generator.VOLTAGE_CODE;
                    v_exp_tec_data.GEN1_TURBINE_SL_NO:=generator.SERIAL_NO;
                    v_exp_tec_data.GEN1_TURBINE_ROTOR_DIA:=generator.ROTOR_DIA;
                    v_exp_tec_data.GEN1_TURBINE_HUB_HEIGHT:=generator.HUB_HEIGHT;
                    end if;

                    if j=2 then
                    v_exp_tec_data.M_GENERATOR_ID_2:=generator.ID;
                    v_exp_tec_data.GEN2_GEN_MAKE_CODE:=generator.MAKE_CODE;
                    v_exp_tec_data.GEN2_NO_OF_GEN_UNITS:=generator.NO_OF_UNITS;
                    v_exp_tec_data.GEN2_GEN_UNIT_CAPACITY:=generator.CAPACITY;
                    v_exp_tec_data.GEN2_VOLTAGE_CODE:=generator.VOLTAGE_CODE;
                    v_exp_tec_data.GEN2_TURBINE_SL_NO:=generator.SERIAL_NO;
                    v_exp_tec_data.GEN2_TURBINE_ROTOR_DIA:=generator.ROTOR_DIA;
                    v_exp_tec_data.GEN2_TURBINE_HUB_HEIGHT:=generator.HUB_HEIGHT;
                    end if;

                    if j=3 then
                    v_exp_tec_data.M_GENERATOR_ID_3:=generator.ID;
                    v_exp_tec_data.GEN3_GEN_MAKE_CODE:=generator.MAKE_CODE;
                    v_exp_tec_data.GEN3_NO_OF_GEN_UNITS:=generator.NO_OF_UNITS;
                    v_exp_tec_data.GEN3_GEN_UNIT_CAPACITY:=generator.CAPACITY;
                    v_exp_tec_data.GEN3_VOLTAGE_CODE:=generator.VOLTAGE_CODE;
                    v_exp_tec_data.GEN3_TURBINE_SL_NO:=generator.SERIAL_NO;
                    v_exp_tec_data.GEN3_TURBINE_ROTOR_DIA:=generator.ROTOR_DIA;
                    v_exp_tec_data.GEN3_TURBINE_HUB_HEIGHT:=generator.HUB_HEIGHT;
                    end if;

                    if j=4 then
                    v_exp_tec_data.M_GENERATOR_ID_4:=generator.ID;
                    v_exp_tec_data.GEN4_GEN_MAKE_CODE:=generator.MAKE_CODE;
                    v_exp_tec_data.GEN4_NO_OF_GEN_UNITS:=generator.NO_OF_UNITS;
                    v_exp_tec_data.GEN4_GEN_UNIT_CAPACITY:=generator.CAPACITY;
                    v_exp_tec_data.GEN4_VOLTAGE_CODE:=generator.VOLTAGE_CODE;
                    v_exp_tec_data.GEN4_TURBINE_SL_NO:=generator.SERIAL_NO;
                    v_exp_tec_data.GEN4_TURBINE_ROTOR_DIA:=generator.ROTOR_DIA;
                    v_exp_tec_data.GEN4_TURBINE_HUB_HEIGHT:=generator.HUB_HEIGHT;
                    end if;

                    if j=5 then
                    v_exp_tec_data.M_GENERATOR_ID_5:=generator.ID;
                    v_exp_tec_data.GEN5_GEN_MAKE_CODE:=generator.MAKE_CODE;
                    v_exp_tec_data.GEN5_NO_OF_GEN_UNITS:=generator.NO_OF_UNITS;
                    v_exp_tec_data.GEN5_GEN_UNIT_CAPACITY:=generator.CAPACITY;
                    v_exp_tec_data.GEN5_VOLTAGE_CODE:=generator.VOLTAGE_CODE;
                    v_exp_tec_data.GEN5_TURBINE_SL_NO:=generator.SERIAL_NO;
                    v_exp_tec_data.GEN5_TURBINE_ROTOR_DIA:=generator.ROTOR_DIA;
                    v_exp_tec_data.GEN5_TURBINE_HUB_HEIGHT:=generator.HUB_HEIGHT;
                    end if;

                    if j=6 then
                    v_exp_tec_data.M_GENERATOR_ID_6:=generator.ID;
                    v_exp_tec_data.GEN6_GEN_MAKE_CODE:=generator.MAKE_CODE;
                    v_exp_tec_data.GEN6_NO_OF_GEN_UNITS:=generator.NO_OF_UNITS;
                    v_exp_tec_data.GEN6_GEN_UNIT_CAPACITY:=generator.CAPACITY;
                    v_exp_tec_data.GEN6_VOLTAGE_CODE:=generator.VOLTAGE_CODE;
                    v_exp_tec_data.GEN6_TURBINE_SL_NO:=generator.SERIAL_NO;
                    v_exp_tec_data.GEN6_TURBINE_ROTOR_DIA:=generator.ROTOR_DIA;
                    v_exp_tec_data.GEN6_TURBINE_HUB_HEIGHT:=generator.HUB_HEIGHT;
                    end if;


                    j:=j+1;
                    END LOOP;

                     select count(*) into v_total_trade from M_TRADE_RELATIONSHIP  where M_SELLER_COMPANY_ID=v_company_serv_id and M_BUYER_COMPANY_ID='TNEB';

                    if v_total_trade >0 then
                        v_exp_tec_data.SURPLUS_ENERGY:='SELL-TO-BOARD';
                        v_exp_tec_data.NATURE_OF_BOARD :='SELL-TO-BOARD';
                        FOR trade IN ( select * from M_TRADE_RELATIONSHIP  where M_SELLER_COMPANY_ID=v_company_serv_id and M_BUYER_COMPANY_ID='TNEB')
                        LOOP

                            if trade.IS_CAPTIVE='Y' then
                                v_exp_tec_data.NATURE_OF_BOARD :='IS-CAPTIVE';
                --                else
                --                 v_exp_tec_data.NATURE_OF_BOARD :='NCES-THIRD-PARTY';
                            end if;

                        END LOOP;
                    else
                         v_exp_tec_data.SURPLUS_ENERGY:='BANKING';
                    end if;

                  update exp_technical_data set COMPANY_NAME= v_exp_tec_data.COMPANY_NAME ,
                    M_ORG_ID= v_exp_tec_data.M_ORG_ID ,
                    TOTAL_CAPACITY_KW= v_exp_tec_data.TOTAL_CAPACITY_KW ,
                    SF_NO= v_exp_tec_data.SF_NO ,
                    VILLAGE= v_exp_tec_data.VILLAGE ,

                    M_SUBSTATION_ID= v_exp_tec_data.M_SUBSTATION_ID ,

                    M_FEEDER_ID= v_exp_tec_data.M_FEEDER_ID ,

                    IS_REC= v_exp_tec_data.IS_REC ,

                    COMMISSION_DATE_STR= v_exp_tec_data.COMMISSION_DATE_STR ,
                    IMPORTED= v_exp_tec_data.IMPORTED ,
                    COMMISSION_DATE= v_exp_tec_data.COMMISSION_DATE ,
                    WIND_PASS_CODE= v_exp_tec_data.WIND_PASS_CODE ,
                    IMPORT_REMARKS= v_exp_tec_data.IMPORT_REMARKS ,

                    FUEL_CODE= v_exp_tec_data.FUEL_CODE ,
                    M_COMPANY_ID= v_exp_tec_data.M_COMPANY_ID ,
                    M_COMPANY_SERVICE_ID= v_exp_tec_data.M_COMPANY_SERVICE_ID ,
                    M_POWERPLANT_ID= v_exp_tec_data.M_POWERPLANT_ID ,
                    M_METER_ID_1= v_exp_tec_data.M_METER_ID_1 ,
                    METER1_PT_RATIO= v_exp_tec_data.METER1_PT_RATIO ,
                    METER1_CT_RATIO= v_exp_tec_data.METER1_CT_RATIO ,
                    METER1_MF= v_exp_tec_data.METER1_MF ,
                    METER1_METER_MAKE_CODE= v_exp_tec_data.METER1_METER_MAKE_CODE ,
                    METER1_METER_NUMBER= v_exp_tec_data.METER1_METER_NUMBER ,
                    METER1_MODEM_NO= v_exp_tec_data.METER1_MODEM_NO ,
                    METER1_IS_ABT= v_exp_tec_data.METER1_IS_ABT ,
                    METER1_ACCURACY_CLASS_CODE= v_exp_tec_data.METER1_ACCURACY_CLASS_CODE ,
                    M_METER_ID_2= v_exp_tec_data.M_METER_ID_2 ,
                    METER2_PT_RATIO= v_exp_tec_data.METER2_PT_RATIO ,
                    METER2_CT_RATIO= v_exp_tec_data.METER2_CT_RATIO ,
                    METER2_MF= v_exp_tec_data.METER2_MF ,
                    METER2_METER_MAKE_CODE= v_exp_tec_data.METER2_METER_MAKE_CODE ,
                    METER2_METER_NUMBER= v_exp_tec_data.METER2_METER_NUMBER ,
                    METER2_MODEM_NO= v_exp_tec_data.METER2_MODEM_NO ,
                    METER2_IS_ABT= v_exp_tec_data.METER2_IS_ABT ,
                    METER2_ACCURACY_CLASS_CODE= v_exp_tec_data.METER2_ACCURACY_CLASS_CODE ,

                    M_METER_ID_3= v_exp_tec_data.M_METER_ID_3 ,
                    METER3_PT_RATIO= v_exp_tec_data.METER3_PT_RATIO ,
                    METER3_CT_RATIO= v_exp_tec_data.METER3_CT_RATIO ,
                    METER3_MF= v_exp_tec_data.METER3_MF ,
                    METER3_METER_MAKE_CODE= v_exp_tec_data.METER3_METER_MAKE_CODE ,
                    METER3_METER_NUMBER= v_exp_tec_data.METER3_METER_NUMBER ,
                    METER3_MODEM_NO= v_exp_tec_data.METER3_MODEM_NO ,
                    METER3_IS_ABT= v_exp_tec_data.METER3_IS_ABT ,
                    METER3_ACCURACY_CLASS_CODE= v_exp_tec_data.METER3_ACCURACY_CLASS_CODE ,

                    M_METER_ID_4= v_exp_tec_data.M_METER_ID_4 ,
                    METER4_PT_RATIO= v_exp_tec_data.METER4_PT_RATIO ,
                    METER4_CT_RATIO= v_exp_tec_data.METER4_CT_RATIO ,
                    METER4_MF= v_exp_tec_data.METER4_MF ,
                    METER4_METER_MAKE_CODE= v_exp_tec_data.METER4_METER_MAKE_CODE ,
                    METER4_METER_NUMBER= v_exp_tec_data.METER4_METER_NUMBER ,
                    METER4_MODEM_NO= v_exp_tec_data.METER4_MODEM_NO ,
                    METER4_IS_ABT= v_exp_tec_data.METER4_IS_ABT ,
                    METER4_ACCURACY_CLASS_CODE= v_exp_tec_data.METER4_ACCURACY_CLASS_CODE ,

                    M_METER_ID_5= v_exp_tec_data.M_METER_ID_5 ,
                    METER5_PT_RATIO= v_exp_tec_data.METER5_PT_RATIO ,
                    METER5_CT_RATIO= v_exp_tec_data.METER5_CT_RATIO ,
                    METER5_MF= v_exp_tec_data.METER5_MF ,
                    METER5_METER_MAKE_CODE= v_exp_tec_data.METER5_METER_MAKE_CODE ,
                    METER5_METER_NUMBER= v_exp_tec_data.METER5_METER_NUMBER ,
                    METER5_MODEM_NO= v_exp_tec_data.METER5_MODEM_NO ,
                    METER5_IS_ABT= v_exp_tec_data.METER5_IS_ABT ,
                    METER5_ACCURACY_CLASS_CODE= v_exp_tec_data.METER5_ACCURACY_CLASS_CODE ,

                    M_METER_ID_6= v_exp_tec_data.M_METER_ID_6 ,
                    METER6_PT_RATIO= v_exp_tec_data.METER6_PT_RATIO ,
                    METER6_CT_RATIO= v_exp_tec_data.METER6_CT_RATIO ,
                    METER6_MF= v_exp_tec_data.METER6_MF ,
                    METER6_METER_MAKE_CODE= v_exp_tec_data.METER6_METER_MAKE_CODE ,
                    METER6_METER_NUMBER= v_exp_tec_data.METER6_METER_NUMBER ,
                    METER6_MODEM_NO= v_exp_tec_data.METER6_MODEM_NO ,
                    METER6_IS_ABT= v_exp_tec_data.METER6_IS_ABT ,
                    METER6_ACCURACY_CLASS_CODE= v_exp_tec_data.METER6_ACCURACY_CLASS_CODE ,


                    M_GENERATOR_ID_1= v_exp_tec_data.M_GENERATOR_ID_1 ,
                    GEN1_GEN_MAKE_CODE= v_exp_tec_data.GEN1_GEN_MAKE_CODE ,
                    GEN1_NO_OF_GEN_UNITS= v_exp_tec_data.GEN1_NO_OF_GEN_UNITS ,
                    GEN1_GEN_UNIT_CAPACITY= v_exp_tec_data.GEN1_GEN_UNIT_CAPACITY ,
                    GEN1_VOLTAGE_CODE= v_exp_tec_data.GEN1_VOLTAGE_CODE ,
                    GEN1_TURBINE_SL_NO= v_exp_tec_data.GEN1_TURBINE_SL_NO ,
                    GEN1_TURBINE_ROTOR_DIA= v_exp_tec_data.GEN1_TURBINE_ROTOR_DIA ,
                    GEN1_TURBINE_HUB_HEIGHT= v_exp_tec_data.GEN1_TURBINE_HUB_HEIGHT ,

                    M_GENERATOR_ID_2= v_exp_tec_data.M_GENERATOR_ID_2 ,
                    GEN2_GEN_MAKE_CODE= v_exp_tec_data.GEN2_GEN_MAKE_CODE ,
                    GEN2_NO_OF_GEN_UNITS= v_exp_tec_data.GEN2_NO_OF_GEN_UNITS ,
                    GEN2_GEN_UNIT_CAPACITY= v_exp_tec_data.GEN2_GEN_UNIT_CAPACITY ,
                    GEN2_VOLTAGE_CODE= v_exp_tec_data.GEN2_VOLTAGE_CODE ,
                    GEN2_TURBINE_SL_NO= v_exp_tec_data.GEN2_TURBINE_SL_NO ,
                    GEN2_TURBINE_ROTOR_DIA= v_exp_tec_data.GEN2_TURBINE_ROTOR_DIA ,
                    GEN2_TURBINE_HUB_HEIGHT= v_exp_tec_data.GEN2_TURBINE_HUB_HEIGHT ,

                    M_GENERATOR_ID_3= v_exp_tec_data.M_GENERATOR_ID_3 ,
                    GEN3_GEN_MAKE_CODE= v_exp_tec_data.GEN3_GEN_MAKE_CODE ,
                    GEN3_NO_OF_GEN_UNITS= v_exp_tec_data.GEN3_NO_OF_GEN_UNITS ,
                    GEN3_GEN_UNIT_CAPACITY= v_exp_tec_data.GEN3_GEN_UNIT_CAPACITY ,
                    GEN3_VOLTAGE_CODE= v_exp_tec_data.GEN3_VOLTAGE_CODE ,
                    GEN3_TURBINE_SL_NO= v_exp_tec_data.GEN3_TURBINE_SL_NO ,
                    GEN3_TURBINE_ROTOR_DIA= v_exp_tec_data.GEN3_TURBINE_ROTOR_DIA ,
                    GEN3_TURBINE_HUB_HEIGHT= v_exp_tec_data.GEN3_TURBINE_HUB_HEIGHT ,

                    M_GENERATOR_ID_4= v_exp_tec_data.M_GENERATOR_ID_4 ,
                    GEN4_GEN_MAKE_CODE= v_exp_tec_data.GEN4_GEN_MAKE_CODE ,
                    GEN4_NO_OF_GEN_UNITS= v_exp_tec_data.GEN4_NO_OF_GEN_UNITS ,
                    GEN4_GEN_UNIT_CAPACITY= v_exp_tec_data.GEN4_GEN_UNIT_CAPACITY ,
                    GEN4_VOLTAGE_CODE= v_exp_tec_data.GEN4_VOLTAGE_CODE ,
                    GEN4_TURBINE_SL_NO= v_exp_tec_data.GEN4_TURBINE_SL_NO ,
                    GEN4_TURBINE_ROTOR_DIA= v_exp_tec_data.GEN4_TURBINE_ROTOR_DIA ,
                    GEN4_TURBINE_HUB_HEIGHT= v_exp_tec_data.GEN4_TURBINE_HUB_HEIGHT ,

                    M_GENERATOR_ID_5= v_exp_tec_data.M_GENERATOR_ID_5 ,
                    GEN5_GEN_MAKE_CODE= v_exp_tec_data.GEN5_GEN_MAKE_CODE ,
                    GEN5_NO_OF_GEN_UNITS= v_exp_tec_data.GEN5_NO_OF_GEN_UNITS ,
                    GEN5_GEN_UNIT_CAPACITY= v_exp_tec_data.GEN5_GEN_UNIT_CAPACITY ,
                    GEN5_VOLTAGE_CODE= v_exp_tec_data.GEN5_VOLTAGE_CODE ,
                    GEN5_TURBINE_SL_NO= v_exp_tec_data.GEN5_TURBINE_SL_NO ,
                    GEN5_TURBINE_ROTOR_DIA= v_exp_tec_data.GEN5_TURBINE_ROTOR_DIA ,
                    GEN5_TURBINE_HUB_HEIGHT= v_exp_tec_data.GEN5_TURBINE_HUB_HEIGHT ,

                    M_GENERATOR_ID_6= v_exp_tec_data.M_GENERATOR_ID_6 ,
                    GEN6_GEN_MAKE_CODE= v_exp_tec_data.GEN6_GEN_MAKE_CODE ,
                    GEN6_NO_OF_GEN_UNITS= v_exp_tec_data.GEN6_NO_OF_GEN_UNITS ,
                    GEN6_GEN_UNIT_CAPACITY= v_exp_tec_data.GEN6_GEN_UNIT_CAPACITY ,
                    GEN6_VOLTAGE_CODE= v_exp_tec_data.GEN6_VOLTAGE_CODE ,
                    GEN6_TURBINE_SL_NO= v_exp_tec_data.GEN6_TURBINE_SL_NO ,
                    GEN6_TURBINE_ROTOR_DIA= v_exp_tec_data.GEN6_TURBINE_ROTOR_DIA ,
                    GEN6_TURBINE_HUB_HEIGHT= v_exp_tec_data.GEN6_TURBINE_HUB_HEIGHT 
                        where M_SERVICE_NUMBER = exp_data.M_SERVICE_NUMBER and   remarks =V_REMARKS;


            end if;
          END LOOP;



      commit;
    exception
	  when others then
	    v_exception_code := SQLCODE;
	    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
	    v_result := 'FAILURE';
	    v_reason := v_exception_code || ' - ' || v_exception_msg;
      -- -- dbms_output.put_line(v_reason);
        v_log_result := log_activity('FUNCTION','PROCESS_EXP_TECHNICAL_DATA','EXCEPTION',v_reason,'','', sysdate,V_REMARKS);
END;

v_log_result := log_activity('FUNCTION','PROCESS_EXP_TECHNICAL_DATA','END','Import complete','','', sysdate,V_REMARKS);

return V_RESULT || ' - ' || v_reason;

END PROCESS_EXP_TECHNICAL_DATA;

