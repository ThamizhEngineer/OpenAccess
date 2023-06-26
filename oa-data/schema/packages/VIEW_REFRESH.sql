CREATE OR REPLACE PACKAGE VIEW_REFRESH AS 

 procedure COMP_SERV_REFRESH (i_service_number in varchar2, o_result_code out varchar2,o_result_desc out varchar2);
 procedure COMP_SERV_REFRESH_ALL (o_result_code out varchar2,o_result_desc out varchar2);

END VIEW_REFRESH;



CREATE OR REPLACE PACKAGE BODY "VIEW_REFRESH" AS 
    procedure COMP_SERV_REFRESH (i_service_number in varchar2,  o_result_code out varchar2,o_result_desc out varchar2) IS
    
    v_process varchar2(50):='VIEW_REFRESH.COMP_SERV_REFRESH';
    v_process_type varchar2(50):='PACKAGE';
    v_tr_count number:=0;
    v_temp TEMP_V_COMP_REFRESH%ROWTYPE;
    v_deleted_all_records boolean:=false;
    v_log_result   varchar2(300):='';
    
        BEGIN
        v_log_result := log_activity(v_process_type,v_process,'Start','','','', '',i_SERVICE_NUMBER);
            if(i_service_number='%') then
                DELETE FROM TEMP_V_COMP_REFRESH;
                v_deleted_all_records:= true;
            else
                DELETE FROM TEMP_V_COMP_REFRESH WHERE "number"=i_service_number;
    
            end if;
            for compserv in (select distinct companyservice.id,companyservice.COMP_SER_TYPE_CODE,companyservice.M_SECTION_ID,section.SECTION_NAME,section.SECTION_CODE,typecodes.value_desc as COMP_SER_TYPE_NAME,companyservice."number",companyservice.m_company_id,company.code as M_COMPANY_CODE,company.name AS M_COMPANY_NAME,company.IS_INTERNAL AS COMPANY_IS_INTERNAL,companyservice.m_org_id,
					org.code as M_ORG_CODE, org.name as M_ORG_NAME,companyservice.capacity,company.is_buyer,company.is_seller,companyservice.is_rec,company.is_captive,companyservice.FLOW_TYPE_CODE,companyservice."number" as m_comp_serv_number,
					companyservice.m_substation_id,substation.code as M_SUBSTATION_CODE,substation.name as M_SUBSTATION_NAME,substation.TYPE_OF_SS as TYPE_OF_SS,companyservice.m_feeder_id,feeder.code as M_FEEDER_CODE,feeder.name as M_FEEDER_NAME,companyservice.ref_number,companyservice.voltage_code,voltagecodes.VALUE_DESC as VOLTAGE_NAME,companyservice.tariff,
					companyservice.BANKING_SERVICE_ID, companyservice.BANKING_SERVICE_NUMBER, companyservice.DL_SERVICE_ID, companyservice.DL_SERVICE_NUMBER, companyservice.TL_SERVICE_ID, companyservice.TL_SERVICE_NUMBER,companyservice.UNADJUSTED_SERVICE_ID, companyservice.UNADJUSTED_SERVICE_NUMBER,companyservice.enabled,
					companymeter.id AS M_COMPANY_METER_ID,companymeter.MODEM_NUMBER, companymeter.METER_NUMBER, companymeter.MF, companymeter.ACCURACY_CLASS_CODE, nvl(companymeter.IS_ABTMETER,'N') IS_ABTMETER, companymeter.METER_MAKE_CODE,companymeter.METER_CT1,companymeter.METER_CT2,companymeter.METER_CT3,companymeter.METER_PT1,companymeter.METER_PT2,companymeter.METER_PT3,
                    powerplant.FUEL_TYPE_CODE,fueltypecodes.FUEL_NAME as FUEL_TYPE_NAME,fueltypecodes.FUEL_GROUP AS FUEL_GROUP_NAME,powerplant.COMMISSION_DATE
                    --,tr.flow_type_code tr_flow_type_code
					from m_company_service companyservice
					left join m_company company on company.id =companyservice.M_COMPANY_ID
					left join M_COMPANY_METER companymeter on companymeter.M_COMPANY_SERVICE_ID =companyservice.id AND companymeter.enabled='Y'
					left join m_org org on org.id=companyservice.M_ORG_ID
					left join m_substation substation on substation.id = companyservice.M_SUBSTATION_ID
					left join m_feeder feeder on feeder.id = companyservice.M_FEEDER_ID
					left join v_codes voltagecodes on companyservice.VOLTAGE_Code= voltagecodes.Value_Code AND  voltagecodes.list_code = 'VOLTAGE_CODE'
					left join v_codes typecodes on companyservice.COMP_SER_TYPE_CODE = typecodes.value_code and typecodes.list_code='SERVICE_TYPE_CODE'
                    left join M_POWERPLANT powerplant on  powerplant.M_SERVICE_ID=companyservice.id
                   -- left join m_trade_relationship tr on tr.m_seller_comp_service_id=companyservice.id
                    left join m_section section on section.Id=companyservice.M_Section_Id
                    left join m_fuel fueltypecodes on powerplant.FUEL_TYPE_CODE= fueltypecodes.fuel_code
                    where companyservice."number" like i_service_number)
                LOOP
                begin
                    v_temp.ID := compserv.id;
                    v_temp.ACCURACY_CLASS_CODE := compserv.ACCURACY_CLASS_CODE;
                    v_temp.BANKING_SERVICE_ID := compserv.BANKING_SERVICE_ID;
                    v_temp.BANKING_SERVICE_NUMBER := compserv.BANKING_SERVICE_NUMBER;
                    v_temp.CAPACITY := compserv.capacity;
                    v_temp.COMMISSION_DATE := compserv.COMMISSION_DATE;
                    v_temp.COMPANY_IS_INTERNAL := compserv.COMPANY_IS_INTERNAL;
                    v_temp.COMP_SER_TYPE_CODE := compserv.COMP_SER_TYPE_CODE;
                    v_temp.COMP_SER_TYPE_NAME := compserv.COMP_SER_TYPE_NAME;
                    v_temp.DL_SERVICE_ID := compserv.DL_SERVICE_ID;
                    v_temp.DL_SERVICE_NUMBER := compserv.DL_SERVICE_NUMBER;
                    v_temp.ENABLED := compserv.enabled;
                    v_temp.FLOW_TYPE_CODE := compserv.FLOW_TYPE_CODE; 
                    v_temp.FUEL_TYPE_CODE := compserv.FUEL_TYPE_CODE;
                    v_temp.FUEL_TYPE_NAME := compserv.FUEL_TYPE_NAME;
                    v_temp.FUEL_GROUP_NAME := compserv.FUEL_GROUP_NAME;
                    v_temp.IS_ABTMETER := compserv.IS_ABTMETER;
                    v_temp.IS_BUYER := compserv.is_buyer;
                    v_temp.IS_REC := compserv.is_rec;
                    v_temp.IS_SELLER := compserv.is_seller;
                    v_temp.METER_CT1 := compserv.METER_CT1;
                    v_temp.METER_CT2 := compserv.METER_CT2;
                    v_temp.METER_CT3 := compserv.METER_CT3;
                    v_temp.METER_MAKE_CODE := compserv.METER_MAKE_CODE;
                    v_temp.METER_NUMBER := compserv.METER_NUMBER;
                    v_temp.METER_PT1 := compserv.METER_PT1;
                    v_temp.METER_PT2 := compserv.METER_PT2;
                    v_temp.METER_PT3 := compserv.METER_PT3;
                    v_temp.MF := compserv.MF;
                    v_temp.MODEM_NUMBER := compserv.MODEM_NUMBER;
                    v_temp.M_COMPANY_CODE := compserv.M_COMPANY_CODE;
                    v_temp.M_COMPANY_ID := compserv.m_company_id;
                    v_temp.M_COMPANY_METER_ID := compserv.M_COMPANY_METER_ID;
                    v_temp.M_COMPANY_NAME := compserv.M_COMPANY_NAME;
                    v_temp.M_COMP_SERV_NUMBER := compserv.m_comp_serv_number;
                    v_temp.M_FEEDER_CODE := compserv.M_FEEDER_CODE;
                    v_temp.M_FEEDER_ID := compserv.m_feeder_id;
                    v_temp.M_FEEDER_NAME := compserv.M_FEEDER_NAME;
                    v_temp.M_ORG_CODE := compserv.M_ORG_CODE;
                    v_temp.M_ORG_ID := compserv.m_org_id;
                    v_temp.M_ORG_NAME := compserv.M_ORG_NAME;
                    v_temp.M_SECTION_ID := compserv.M_SECTION_ID;
                    v_temp.M_SECTION_NAME := compserv.SECTION_NAME;
                    v_temp.M_SECTION_CODE := compserv.SECTION_CODE;
                    v_temp.M_SUBSTATION_CODE := compserv.M_SUBSTATION_CODE;
                    v_temp.M_SUBSTATION_ID := compserv.m_substation_id;
                    v_temp.M_SUBSTATION_NAME := compserv.M_SUBSTATION_NAME;
                    v_temp.REF_NUMBER := compserv.ref_number;
                    v_temp.TARIFF := compserv.tariff;
                    v_temp.TL_SERVICE_ID := compserv.TL_SERVICE_ID;
                    v_temp.TL_SERVICE_NUMBER := compserv.TL_SERVICE_NUMBER;
                    v_temp.TYPE_OF_SS :=compserv.TYPE_OF_SS;
                    v_temp.UNADJUSTED_SERVICE_ID :=compserv.UNADJUSTED_SERVICE_ID; 
                    v_temp.UNADJUSTED_SERVICE_NUMBER := compserv.UNADJUSTED_SERVICE_NUMBER;
                    v_temp.VOLTAGE_CODE := compserv.voltage_code;
                    v_temp.VOLTAGE_NAME := compserv.VOLTAGE_NAME;
                    v_temp."number" := compserv."number";
                    
                    v_temp.IS_CAPTIVE := 'N';
                    v_temp.IS_STB := 'N';
                    v_temp.IS_THIRD_PARTY := 'N';
                    v_temp.tr_flow_type_code := NULL;
                     
                    for tr in (select  flow_type_code from m_trade_relationship  where m_seller_comp_service_id=compserv.id  and flow_type_code is not NULL GROUP BY flow_type_code ORDER BY count(flow_type_code) desc)                    
                    loop
                        if tr.flow_type_code = 'IS-CAPTIVE' THEN v_temp.IS_CAPTIVE := 'Y';
                        ELSIF tr.flow_type_code = 'STB' THEN v_temp.IS_STB := 'Y';
                        elsif tr.flow_type_code = 'THIRD-PARTY' THEN v_temp.IS_THIRD_PARTY := 'Y';
                        END if;
                        
                        if v_temp.tr_flow_type_code is null then v_temp.tr_flow_type_code := tr.flow_type_code; end if;
                    end loop;                    
                    if (v_temp.IS_SELLER = 'Y') then
                        excess_units.find_type(v_temp."number", v_temp.EXCESS_UNIT_TYPE, v_temp.EXCESS_UNIT_TYPE_REASON);
                    else
                        v_temp.EXCESS_UNIT_TYPE:='NA';
                        v_temp.EXCESS_UNIT_TYPE_REASON := 'Not a Seller';
                    end if;
                    INSERT INTO TEMP_V_COMP_REFRESH VALUES v_temp;
                    
                exception
                when others then
                    o_result_code := 'FAILURE';
                    o_result_desc := SQLCODE || ' - ' || SUBSTR(SQLERRM, 1, 200);
                    v_log_result := log_activity(v_process_type,v_process,'EH','Exception - '||o_result_desc,o_result_code,'', compserv."number",i_service_number);

                   -- dbms_output.put_line(o_result_desc);
                END;
                END LOOP;
            commit;
            o_result_code :='SUCCESS'; 
            v_log_result := log_activity(v_process_type,v_process,'End',o_result_desc,o_result_code,'', '',I_SERVICE_NUMBER);

        END COMP_SERV_REFRESH;
        
        procedure COMP_SERV_REFRESH_ALL ( o_result_code out varchar2,o_result_desc out varchar2) IS
        begin
            comp_serv_refresh('%', o_result_code, o_result_desc);
        END COMP_SERV_REFRESH_ALL;

      END VIEW_REFRESH;
