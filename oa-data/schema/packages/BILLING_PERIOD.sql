CREATE OR REPLACE PACKAGE BILLING_PERIOD AS 

  PROCEDURE UPDATE_SURPLUS_FROM_HT(i_service_number in varchar2,i_reading_month in varchar2,i_reading_year in varchar2, o_result out varchar2, o_reason out varchar2);
  PROCEDURE OPEN_ALL_EXS_BALANCES(i_service_number in varchar2,i_reading_month in varchar2,i_reading_year in varchar2, o_result out varchar2, o_reason out varchar2);

END BILLING_PERIOD;


CREATE OR REPLACE PACKAGE body BILLING_PERIOD AS 
 
  PROCEDURE UPDATE_SURPLUS_FROM_HT(i_service_number in varchar2,i_reading_month in varchar2,i_reading_year in varchar2, o_result out varchar2, o_reason out varchar2)
    IS
    v_process varchar2(500):='BILLING_PERIOD.UPDATE_SURPLUS_FROM_HT';
    v_process_type varchar2(500):='PACKAGE';
    v_stage varchar2(500):='';
    v_step varchar2(500):='';
    v_message varchar2(500):=''; 
    v_log_result  varchar2(500);
    v_service_number varchar2(50);
    BEGIN 
    
		v_stage:='INIT'; v_message:='START'; 
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
        /*
         example, 
         on june-30th-2020 or in early july2020, to update surplus balance as the first step
            a. call by UPDATE_SURPLUS_FROM_HT('%','05', '2020')
            b. to update surplus for single service (12312312312), call UPDATE_SURPLUS_FROM_HT('12312312312','05', '2020')
        */
        
        if(i_service_number is NULL or i_service_number='') then
             v_service_number := '%';
        else
            v_service_number := i_service_number;
        end if;
        
        v_stage:='PROCESS'; v_message:='Updating Surplus from HT for - '||v_service_number; 
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
        
        for serv in ( select distinct suplr_code from f_energy_adjustmet where reading_mnth=i_reading_month and reading_yr=i_reading_year and suplr_code like v_service_number)
        LOOP
            excess_units_source.update_excess_from_ht(serv.suplr_code, i_reading_month,i_reading_year , o_result , o_reason);
        END LOOP;

        o_result :=''; o_reason:='';

        commit;
        <<THE_END>>
		v_stage:='END'; v_message:='END'; 
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
    END UPDATE_SURPLUS_FROM_HT;
    
  PROCEDURE OPEN_ALL_EXS_BALANCES(i_service_number in varchar2,i_reading_month in varchar2,i_reading_year in varchar2, o_result out varchar2, o_reason out varchar2)
    IS
    v_process varchar2(500):='BILLING_PERIOD.OPEN_ALL_EXS_BALANCES';
    v_process_type varchar2(500):='PACKAGE';
    v_stage varchar2(500):='';
    v_step varchar2(500):='';
    v_message varchar2(500):=''; 
    v_log_result  varchar2(500);
    v_service_number varchar2(50);
    BEGIN 
    
        /*
         example, 
         on june-30th-2020 or in early july2020, to open balances 
            a. call by OPEN_ALL_EXS_BALANCES('%','06', '2020')
            b. to open balance for single service (12312312312), call OPEN_ALL_EXS_BALANCES('12312312312','06', '2020')
        */
        
		v_stage:='INIT'; v_message:='START'; 
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
        
        if(i_service_number is null or i_service_number='') then
             v_service_number := '%';
        else
            v_service_number := i_service_number;
        end if;
         
        v_stage:='PROCESS'; v_message:='Opening bal for - '||v_service_number; 
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
        
        for serv in (select "number" from v_company_service where "number" like v_service_number and comp_ser_type_code='03')
        LOOP
            excess_units.open_balances(serv."number", i_reading_month,i_reading_year , o_result , o_reason);
        END LOOP;

        o_result :=''; o_reason:='';

        commit;
		v_stage:='END'; v_message:='END'; 
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
    END OPEN_ALL_EXS_BALANCES;
END BILLING_PERIOD;
