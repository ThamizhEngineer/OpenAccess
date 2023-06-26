CREATE OR REPLACE PACKAGE "EXCESS_UNITS" AS 

  PROCEDURE FIND_TYPE (i_service_number in varchar2, o_type out varchar2, o_reason out varchar2);
  FUNCTION FIND_TYPE_FN (i_service_number in varchar2) RETURN varchar2;
  PROCEDURE OPEN_BALANCES (i_service_number in varchar2,i_reading_month in varchar2,i_reading_year in varchar2, o_result out varchar2, o_reason out varchar2);
  PROCEDURE INCREASE_BALANCE (i_excess_source in varchar2,i_service_number in varchar2,i_reading_month in varchar2,i_reading_year in varchar2, i_unit_data in UD_EXCESS_UNIT, o_result out varchar2, o_reason out varchar2);
  PROCEDURE DECREASE_BALANCE (i_excess_source in varchar2,i_service_number in varchar2,i_reading_month in varchar2,i_reading_year in varchar2, i_unit_data in UD_EXCESS_UNIT, o_result out varchar2, o_reason out varchar2);  
  PROCEDURE RESET_BALANCES_TO_OPEN_BAL (i_service_number in varchar2,i_reading_month in varchar2,i_reading_year in varchar2,o_result out varchar2, o_reason out varchar2);  
  PROCEDURE DELETE_BALANCES (i_service_number in varchar2,i_reading_month in varchar2,i_reading_year in varchar2,o_result out varchar2, o_reason out varchar2);  
END EXCESS_UNITS;


CREATE OR REPLACE PACKAGE BODY EXCESS_UNITS AS

procedure FIND_TYPE (i_service_number in varchar2, o_type out varchar2, o_reason out varchar2)
  is
    v_process varchar2(50):='EXCESS_UNITS.FIND_TYPE';
    v_process_type varchar2(500):='PACKAGE';
    v_stage varchar2(500):='';
    v_step varchar2(500):='';
    v_message varchar2(500):='';
    service V_COMPANY_SERVICE%ROWTYPE;
    v_third_party_count number:=0;
    v_created_by varchar2(100):='admin';
    v_log_result  varchar2(500);
    v_reason varchar2(200):='';
    v_override_count number:=0;
    v_override_remarks  varchar2(500);
BEGIN
      BEGIN  -- exception handling start

        v_stage:='INIT'; v_message:='START';
        v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,'', '');
       -- input validation

      IF(i_service_number is null or i_service_number = ''  )   then
        Raise_Application_Error (-20343, 'company-service-number is mandatory  ');
      end if;


        select  companyservice.id, powerplant.FUEL_TYPE_CODE,fueltypecodes.FUEL_GROUP AS FUEL_GROUP_NAME,company.code as M_COMPANY_CODE, powerplant.COMMISSION_DATE , companyservice.comp_ser_type_code
        into service.id, service.FUEL_TYPE_CODE, service.FUEL_GROUP_NAME, service.M_COMPANY_CODE, service.COMMISSION_DATE, service.comp_ser_type_code from m_company_service companyservice
                    left join m_company company on company.id =companyservice.M_COMPANY_ID
                    left join M_POWERPLANT powerplant on  powerplant.M_SERVICE_ID=companyservice.id
                    left join m_fuel fueltypecodes on powerplant.FUEL_TYPE_CODE= fueltypecodes.fuel_code
                            WHERE "number" =  i_service_number;
        select count(*) into v_override_count from m_excess_unit_type_override where m_company_service_num = i_service_number and excess_unit_type in('NA','LAPSED','SURPLUS-STB','BANKING');
        if(v_override_count > 0) then
            select excess_unit_type,nvl(remarks,'NO REMARKS PROVIDED')  into o_type, v_override_remarks from m_excess_unit_type_override where m_company_service_num =i_service_number and rownum=1 and excess_unit_type in('NA','LAPSED','SURPLUS-STB','BANKING');
            o_reason:='Overriden - '||v_override_remarks;
            goto THE_END;
        end if;       
        select  count(*) into v_third_party_count from m_trade_relationship  where m_seller_comp_service_id= service.id and flow_type_code = 'THIRD-PARTY';
        if(v_third_party_count >0) then
            service.IS_THIRD_PARTY := 'Y';
        end if;

        --v_stage:='PROCESS'; v_message:=v_third_party_count ||'-'|| service.FUEL_TYPE_CODE ||'-'|| service.IS_THIRD_PARTY||'-'||service.commission_date;
--v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,'', '');
       
--      select * into service from v_company_service  WHERE "number" =  i_service_number;
      if (service.comp_ser_type_code <> '03') then
             o_type :='NA';
             o_reason :='001-Not a Seller';
      elsif (service.M_COMPANY_CODE = 'IEX') then
               o_type :='LAPSED';
               o_reason :='002-Company is IEX';
      elsif (service.FUEL_TYPE_CODE ='WIND')then
            if(service.IS_THIRD_PARTY = 'Y') then
               o_type :='LAPSED';
               o_reason :='003-Wind and ThirdParty';
            elsif(months_between(sysdate,service.commission_date) /12) > 20 then
               o_type :='LAPSED';
               o_reason :='004-Wind and commissionDate older than 20 years';
            elsif(service.commission_date is not null and service.commission_date > to_date('01-04-2018','DD-MM-YYYY')) then
               o_type :='SURPLUS-STB';
               o_reason :='005-Wind and commissionDate > 01-04-2018';
            else
               o_type :='BANKING';
               o_reason :='006-Wind and commissionDate <= 01-04-2018 and Captive';
            end if;
      elsif (service.FUEL_TYPE_CODE ='SOLAR') then
            if(service.IS_THIRD_PARTY = 'Y') then
               o_type :='LAPSED';
               o_reason :='007-Solar and Third Party';
            elsif(months_between(sysdate,service.commission_date) /12 > 20) then
               o_type :='LAPSED';
               o_reason :='008-Solar and commissionDate older than 20 years';
            else
               o_type :='SURPLUS-STB';
               o_reason :='009-Solar and Captive';
            end if;
      elsif (service.FUEL_GROUP_NAME ='FF') then
               o_type :='LAPSED';
               o_reason :='010-Thermal';
      else
             o_type :='UNKNOWN';
             o_reason :='011-Rules didnt match';
      end if;
     

    exception
    when others then
        v_stage:='EXCEPTION';
        v_message := SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 200);
        v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,'','');
    END;
    <<THE_END>>
    v_step:='END';
    v_message:=o_type;
    v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,'','');
END FIND_TYPE;

FUNCTION FIND_TYPE_FN (i_service_number in varchar2)
RETURN varchar2 as
o_type VARCHAR2(100);
O_REASON VARCHAR2(200);
BEGIN
EXCESS_UNITS.FIND_TYPE(
    i_service_number => i_service_number,
    O_TYPE => O_TYPE,
    O_REASON => O_REASON
  );
	RETURN o_type;
END FIND_TYPE_FN;
 

procedure OPEN_BALANCES (i_service_number in varchar2,i_reading_month in varchar2,i_reading_year in varchar2, o_result out varchar2, o_reason out varchar2)
  is
    v_process varchar2(500):='EXCESS_UNITS.OPEN_BALANCES';
    v_process_type varchar2(500):='PACKAGE';
    v_stage varchar2(500):='';
    v_step varchar2(500):='';
    v_message varchar2(500):=''; 
    v_excess_unit_type varchar2(50):='';    
    v_count number:=0; 
    v_excess_unit  UD_EXCESS_UNIT;
	v_exs_bal V_EXS_BALANCE%ROWTYPE;
	v_banking_bal T_EXS_BANKING_BALANCE%ROWTYPE;
	v_surplus_stb_bal T_EXS_SURPLUS_STB_BALANCE%ROWTYPE;
	v_lapsed_bal T_EXS_LAPSED_BALANCE%ROWTYPE;
    service V_COMPANY_SERVICE%ROWTYPE;
    v_third_party_count number:=0;
    v_created_by varchar2(100):='admin';
    v_log_result  varchar2(500);
    v_reason varchar2(200):=''; 
	
BEGIN
    BEGIN  -- exception handling start
		v_stage:='INIT'; v_step:=''; v_message:='START'; 
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);

		v_stage:='input validation';
		IF(i_service_number is null or i_service_number = '' or i_service_number = '%' or i_reading_month is null or i_reading_month = '' or i_reading_month is null or i_reading_month = ''  )   then
			--Raise_Application_Error (-20343, 'Input Data not complete!');
            o_result:='FAILURE';
            o_reason:='Input Data not complete! ';
            v_message:=o_result||' - '||o_reason;
            GOTO THE_END;         
		end if; 
        
        -- find the excess-unit-type for the service
        EXCESS_UNITS.FIND_TYPE( i_service_number => i_service_number, O_TYPE => v_excess_unit_type,O_REASON => O_REASON);
          
        -- stop for unsupported excess-unit-types
        if(v_excess_unit_type not in ('BANKING','SURPLUS-STB','LAPSED'  )) then
            o_result:='FAILURE';
            o_reason:='unsupported excess unit type - '||v_excess_unit_type;
            v_message:=o_result||' - '||o_reason;
            GOTO THE_END;            
			--Raise_Application_Error (-20343,v_message);
        end if;
        
       v_stage:='OPEN-BANKING-BALANCE'; v_step:=''; v_message:=''; 
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
       EXCESS_UNITS_HELPER.OPEN_BALANCE('BANKING' , i_service_number ,i_reading_month ,i_reading_year, o_result , o_reason );
       
       v_stage:='OPEN-SURPLUS-STB-BALANCE'; v_step:=''; v_message:=''; 
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
       EXCESS_UNITS_HELPER.OPEN_BALANCE('SURPLUS-STB' , i_service_number ,i_reading_month ,i_reading_year, o_result , o_reason );
       
       v_stage:='OPEN-LAPSED-BALANCE'; v_step:=''; v_message:=''; 
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
       EXCESS_UNITS_HELPER.OPEN_BALANCE('LAPSED' , i_service_number ,i_reading_month ,i_reading_year, o_result , o_reason );
    
    exception
    when others then 
        v_stage:='EXCEPTION';
        v_message := SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 100);
        v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
        -- dbms_output.put_line(o_reason);
    END;
    <<THE_END>>
    v_stage:='END'; v_step:='END';
    v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
END OPEN_BALANCES;

PROCEDURE INCREASE_BALANCE (i_excess_source in varchar2,i_service_number in varchar2,i_reading_month in varchar2,i_reading_year in varchar2, i_unit_data in UD_EXCESS_UNIT, o_result out varchar2, o_reason out varchar2)
IS
    v_process varchar2(500):='EXCESS_UNITS.INCREASE_BALANCE';
    v_process_type varchar2(500):='PACKAGE';
    v_stage varchar2(500):='';
    v_step varchar2(500):='';
    v_message varchar2(500):=''; 
    v_excess_unit_type varchar2(50):='';    
    v_count number:=0; 
    v_excess_unit  UD_EXCESS_UNIT;
	v_exs_bal V_EXS_BALANCE%ROWTYPE;
	v_banking_bal T_EXS_BANKING_BALANCE%ROWTYPE;
	v_surplus_stb_bal T_EXS_SURPLUS_STB_BALANCE%ROWTYPE;
	v_lapsed_bal T_EXS_LAPSED_BALANCE%ROWTYPE;
    service V_COMPANY_SERVICE%ROWTYPE;
    v_third_party_count number:=0;
    v_created_by varchar2(100):='admin';
    v_log_result  varchar2(500);
    v_balance_exists boolean:=false;
    v_reason varchar2(200):=''; 
BEGIN 
    BEGIN  -- exception handling start
		v_stage:='INIT'; v_step:=''; v_message:='START'; 
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
        
        -- find the excess-unit-type for the service
        EXCESS_UNITS.FIND_TYPE(i_service_number, v_excess_unit_type,O_REASON);
        
        v_stage:='CHECK-BALANCE-EXISTS'; v_step:=''; v_message:=''; 
         -- check if balance  exists for current month          
        select count(*) into v_count from V_EXS_BALANCE where EXCESS_UNIT_TYPE = v_excess_unit_type and  READING_MONTH = i_reading_month and READING_YEAR = i_reading_year and M_COMPANY_SERVICE_NUM = i_service_number ;
        
        -- if doesnt exists create balance
        if(v_count = 0) then
            open_balances(i_service_number ,i_reading_month ,i_reading_year, o_result , o_reason);
        else
            v_balance_exists := true;
        end if;
        select * into v_exs_bal from V_EXS_BALANCE where EXCESS_UNIT_TYPE=v_excess_unit_type and READING_MONTH = i_reading_month and READING_YEAR = i_reading_year and M_COMPANY_SERVICE_NUM = i_service_number ;
        
        if(i_excess_source = 'EA') then
            select round(nvl(i_unit_data.c1,0),5), round(nvl(i_unit_data.c2,0),5),round(nvl(i_unit_data.c3,0),5),round(nvl(i_unit_data.c4,0),5),round(nvl(i_unit_data.c5,0),5)  ,round(nvl(i_unit_data.total_units,0),5), i_unit_data.remarks,  i_unit_data.SRC_ID, v_process,  i_unit_data.update_dt 
            into v_exs_bal.INCR_EA1_C1, v_exs_bal.INCR_EA1_C2, v_exs_bal.INCR_EA1_C3, v_exs_bal.INCR_EA1_C4, v_exs_bal.INCR_EA1_C5, v_exs_bal.INCR_EA1_TOTAL_UNITS, v_exs_bal.INCR_EA1_REMARKS,v_exs_bal. INCR_EA1_SRC_ID, v_exs_bal.INCR_EA1_UPDATE_BY, v_exs_bal.INCR_EA1_UPDATE_DT from dual;
        else --source is HT
            select round(nvl(i_unit_data.c1,0),5), round(nvl(i_unit_data.c2,0),5),round(nvl(i_unit_data.c3,0),5),round(nvl(i_unit_data.c4,0),5),round(nvl(i_unit_data.c5,0),5)  ,round(nvl(i_unit_data.total_units,0),5),i_unit_data.remarks,  i_unit_data.SRC_ID, v_process,  i_unit_data.update_dt 
            into v_exs_bal.INCR_HT_C1, v_exs_bal.INCR_HT_C2, v_exs_bal.INCR_HT_C3, v_exs_bal.INCR_HT_C4, v_exs_bal.INCR_HT_C5, v_exs_bal.INCR_HT_TOTAL_UNITS, v_exs_bal.INCR_HT_REMARKS,v_exs_bal. INCR_HT_SRC_ID, v_exs_bal.INCR_HT_UPDATE_BY, v_exs_bal.INCR_HT_UPDATE_DT from dual;
         end if;
        
        v_stage:='PROCESS'; v_step:='FORMING OBJECT'; v_message:='id-'||v_exs_bal.id; 
        v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
        
        EXCESS_UNITS_HELPER.SET_OBJ (v_exs_bal, v_banking_bal ,v_surplus_stb_bal ,v_lapsed_bal);
        
       v_stage:='PROCESS'; v_step:='SAVING TO TABLE'; v_message:='id-'||v_exs_bal.id; 
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
        
        EXCESS_UNITS_HELPER.SAVE_TO_DB (v_excess_unit_type, v_balance_exists, v_banking_bal ,v_surplus_stb_bal ,v_lapsed_bal);
    exception
    when others then 
        v_stage:='EXCEPTION';
        v_message := SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 100);
        v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
        -- dbms_output.put_line(o_reason);
    END;
    <<THE_END>>
    v_stage:='END'; v_step:='END';
    v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);   

END INCREASE_BALANCE;
PROCEDURE DECREASE_BALANCE (i_excess_source in varchar2,i_service_number in varchar2,i_reading_month in varchar2,i_reading_year in varchar2, i_unit_data in UD_EXCESS_UNIT, o_result out varchar2, o_reason out varchar2)
IS
    v_process varchar2(500):='EXCESS_UNITS.DECREASE_BALANCE';
    v_process_type varchar2(500):='PACKAGE';
    v_stage varchar2(500):='';
    v_step varchar2(500):='';
    v_message varchar2(500):=''; 
    v_excess_unit_type varchar2(50):='';    
    v_count number:=0; 
    v_excess_unit  UD_EXCESS_UNIT;
	v_exs_bal V_EXS_BALANCE%ROWTYPE;
	v_banking_bal T_EXS_BANKING_BALANCE%ROWTYPE;
	v_surplus_stb_bal T_EXS_SURPLUS_STB_BALANCE%ROWTYPE;
	v_lapsed_bal T_EXS_LAPSED_BALANCE%ROWTYPE;
    service V_COMPANY_SERVICE%ROWTYPE;
    v_third_party_count number:=0;
    v_created_by varchar2(100):='admin';
    v_log_result  varchar2(500);
    v_reason varchar2(200):=''; 
    v_balance_exists boolean:=false;
BEGIN 
    BEGIN  -- exception handling start
		v_stage:='INIT'; v_step:=''; v_message:='START'; 
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
        
        
        if(i_excess_source <> 'EA') then
            o_result:='FAILURE';
            o_reason:='unsupported source - '||i_excess_source;
            v_message:=o_result||' - '||o_reason;
            GOTO THE_END;
        end if;
        
        
        -- find the excess-unit-type for the service
        EXCESS_UNITS.FIND_TYPE(i_service_number, v_excess_unit_type,O_REASON);
        
        v_stage:='CHECK-BALANCE-EXISTS'; v_step:=''; v_message:=''; 
         -- check if balance  exists for current month          
        select count(*) into v_count from V_EXS_BALANCE where EXCESS_UNIT_TYPE = v_excess_unit_type and  READING_MONTH = i_reading_month and READING_YEAR = i_reading_year and M_COMPANY_SERVICE_NUM = i_service_number ;
        
        -- if doesnt exists create balance
        if(v_count = 0) then
            open_balances(i_service_number ,i_reading_month ,i_reading_year, o_result , o_reason);
        else
            v_balance_exists :=true;
        end if;
        select * into v_exs_bal from V_EXS_BALANCE where EXCESS_UNIT_TYPE=v_excess_unit_type and READING_MONTH = i_reading_month and READING_YEAR = i_reading_year and M_COMPANY_SERVICE_NUM = i_service_number ;
        
        --source is assumed as EA
        select round(nvl(i_unit_data.c1,0),5), round(nvl(i_unit_data.c2,0),5),round(nvl(i_unit_data.c3,0),5),round(nvl(i_unit_data.c4,0),5),round(nvl(i_unit_data.c5,0),5)  ,round(nvl(i_unit_data.total_units,0),5), i_unit_data.remarks,  i_unit_data.SRC_ID, v_process,  i_unit_data.update_dt 
        into v_exs_bal.DECR_EA1_C1, v_exs_bal.DECR_EA1_C2, v_exs_bal.DECR_EA1_C3, v_exs_bal.DECR_EA1_C4, v_exs_bal.DECR_EA1_C5, v_exs_bal.DECR_EA1_TOTAL_UNITS, v_exs_bal.DECR_EA1_REMARKS,v_exs_bal. DECR_EA1_SRC_ID, v_exs_bal.DECR_EA1_UPDATE_BY, v_exs_bal.DECR_EA1_UPDATE_DT from dual;        
        
        v_stage:='PROCESS'; v_step:='FORMING OBJECT'; v_message:='id-'||v_exs_bal.id; 
        v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
        
        EXCESS_UNITS_HELPER.SET_OBJ (v_exs_bal, v_banking_bal ,v_surplus_stb_bal ,v_lapsed_bal);
        
       v_stage:='PROCESS'; v_step:='SAVING TO TABLE'; v_message:='id-'||v_exs_bal.id; 
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
        
        EXCESS_UNITS_HELPER.SAVE_TO_DB (v_excess_unit_type, v_balance_exists, v_banking_bal ,v_surplus_stb_bal ,v_lapsed_bal);
    exception
    when others then 
        v_stage:='EXCEPTION';
        v_message := SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 100);
        v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
        -- dbms_output.put_line(o_reason);
    END;
    <<THE_END>>
    v_stage:='END'; v_step:='END';
    v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);   

END DECREASE_BALANCE;
PROCEDURE RESET_BALANCES_TO_OPEN_BAL (i_service_number in varchar2,i_reading_month in varchar2,i_reading_year in varchar2, o_result out varchar2, o_reason out varchar2)
is
    v_process varchar2(500):='EXCESS_UNITS.RESET_BALANCES_TO_OPEN_BAL';
    v_process_type varchar2(500):='PACKAGE';
    v_stage varchar2(500):='';
    v_step varchar2(500):='';
    v_message varchar2(500):=''; 
    v_excess_unit_type varchar2(50):='';     
    v_created_by varchar2(100):='admin';
    v_log_result  varchar2(500);
    v_reason varchar2(200):=''; 
BEGIN
    BEGIN  -- exception handling start
		v_stage:='INIT'; v_step:=''; v_message:='START'; 
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
        
        v_stage:='RESET_BALANCES_TO_OPEN_BAL'; v_step:=''; v_message:=''; 
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
        UPDATE T_EXS_BANKING_BALANCE SET
            INCR_EA1_C1='',INCR_EA1_C2='',INCR_EA1_C3='',INCR_EA1_C4='',INCR_EA1_C5='',INCR_EA1_TOTAL_UNITS='',INCR_EA1_REMARKS='',INCR_EA1_SRC_ID='',INCR_EA1_UPDATE_BY='',INCR_EA1_UPDATE_DT='',
            DECR_EA1_C1='',DECR_EA1_C2='',DECR_EA1_C3='',DECR_EA1_C4='',DECR_EA1_C5='',DECR_EA1_TOTAL_UNITS='',DECR_EA1_REMARKS='',DECR_EA1_SRC_ID='',DECR_EA1_UPDATE_BY='',DECR_EA1_UPDATE_DT='',
            INCR_HT_C1='',INCR_HT_C2='',INCR_HT_C3='',INCR_HT_C4='',INCR_HT_C5='',INCR_HT_TOTAL_UNITS='',INCR_HT_REMARKS='',INCR_HT_SRC_ID='',INCR_HT_UPDATE_BY='',INCR_HT_UPDATE_DT='',
            CURR_C1='',CURR_C2='',CURR_C3='',CURR_C4='',CURR_C5='',CURR_TOTAL_UNITS='',CURR_REMARKS='',CURR_UPDATE_DT='', MODIFIED_BY=v_process, modified_dt=sysdate
        WHERE m_company_service_num = i_service_number AND reading_month = i_reading_month AND reading_year = i_reading_year;
        UPDATE T_EXS_SURPLUS_STB_BALANCE SET
            INCR_EA1_C1='',INCR_EA1_C2='',INCR_EA1_C3='',INCR_EA1_C4='',INCR_EA1_C5='',INCR_EA1_TOTAL_UNITS='',INCR_EA1_REMARKS='',INCR_EA1_SRC_ID='',INCR_EA1_UPDATE_BY='',INCR_EA1_UPDATE_DT='',
            DECR_EA1_C1='',DECR_EA1_C2='',DECR_EA1_C3='',DECR_EA1_C4='',DECR_EA1_C5='',DECR_EA1_TOTAL_UNITS='',DECR_EA1_REMARKS='',DECR_EA1_SRC_ID='',DECR_EA1_UPDATE_BY='',DECR_EA1_UPDATE_DT='',
            INCR_HT_C1='',INCR_HT_C2='',INCR_HT_C3='',INCR_HT_C4='',INCR_HT_C5='',INCR_HT_TOTAL_UNITS='',INCR_HT_REMARKS='',INCR_HT_SRC_ID='',INCR_HT_UPDATE_BY='',INCR_HT_UPDATE_DT='',
            CURR_C1='',CURR_C2='',CURR_C3='',CURR_C4='',CURR_C5='',CURR_TOTAL_UNITS='',CURR_REMARKS='',CURR_UPDATE_DT='', MODIFIED_BY=v_process, modified_dt=sysdate
        WHERE m_company_service_num = i_service_number AND reading_month = i_reading_month AND reading_year = i_reading_year;
        UPDATE T_EXS_LAPSED_BALANCE SET
            INCR_EA1_C1='',INCR_EA1_C2='',INCR_EA1_C3='',INCR_EA1_C4='',INCR_EA1_C5='',INCR_EA1_TOTAL_UNITS='',INCR_EA1_REMARKS='',INCR_EA1_SRC_ID='',INCR_EA1_UPDATE_BY='',INCR_EA1_UPDATE_DT='',
            DECR_EA1_C1='',DECR_EA1_C2='',DECR_EA1_C3='',DECR_EA1_C4='',DECR_EA1_C5='',DECR_EA1_TOTAL_UNITS='',DECR_EA1_REMARKS='',DECR_EA1_SRC_ID='',DECR_EA1_UPDATE_BY='',DECR_EA1_UPDATE_DT='',
            INCR_HT_C1='',INCR_HT_C2='',INCR_HT_C3='',INCR_HT_C4='',INCR_HT_C5='',INCR_HT_TOTAL_UNITS='',INCR_HT_REMARKS='',INCR_HT_SRC_ID='',INCR_HT_UPDATE_BY='',INCR_HT_UPDATE_DT='',
            CURR_C1='',CURR_C2='',CURR_C3='',CURR_C4='',CURR_C5='',CURR_TOTAL_UNITS='',CURR_REMARKS='',CURR_UPDATE_DT='', MODIFIED_BY=v_process, modified_dt=sysdate
        WHERE m_company_service_num = i_service_number AND reading_month = i_reading_month AND reading_year = i_reading_year;
        
        COMMIT;
    exception
    when others then 
        v_stage:='EXCEPTION';
        v_message := SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 100);
        v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
        -- dbms_output.put_line(o_reason);
    END;
    <<THE_END>>
    v_stage:='END'; v_step:='END';
    v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
END RESET_BALANCES_TO_OPEN_BAL;
PROCEDURE DELETE_BALANCES (i_service_number in varchar2,i_reading_month in varchar2,i_reading_year in varchar2, o_result out varchar2, o_reason out varchar2)
 is
    v_process varchar2(500):='EXCESS_UNITS.DELETE_BALANCES';
    v_process_type varchar2(500):='PACKAGE';
    v_stage varchar2(500):='';
    v_step varchar2(500):='';
    v_message varchar2(500):=''; 
    v_created_by varchar2(100):='admin';
    v_log_result  varchar2(500);
    v_reason varchar2(200):=''; 
BEGIN
    BEGIN  -- exception handling start
		v_stage:='INIT'; v_step:=''; v_message:='START'; 
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
        
        v_stage:='DELETE-ALL-BALANCES'; v_step:=''; v_message:=''; 
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
        DELETE FROM T_EXS_BANKING_BALANCE WHERE m_company_service_num = i_service_number AND reading_month = i_reading_month AND reading_year = i_reading_year;
        DELETE FROM T_EXS_SURPLUS_STB_BALANCE WHERE m_company_service_num = i_service_number AND reading_month = i_reading_month AND reading_year = i_reading_year;
        DELETE FROM T_EXS_LAPSED_BALANCE WHERE m_company_service_num = i_service_number AND reading_month = i_reading_month AND reading_year = i_reading_year;
        
        COMMIT;
    exception
    when others then 
        v_stage:='EXCEPTION';
        v_message := SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 100);
        v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
        -- dbms_output.put_line(o_reason);
    END;
    <<THE_END>>
    v_stage:='END'; v_step:='END';
    v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
END DELETE_BALANCES;
END EXCESS_UNITS;
