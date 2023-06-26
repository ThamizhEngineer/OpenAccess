CREATE OR REPLACE PACKAGE "EXCESS_UNITS_SOURCE" AS 

  procedure update_excess_from_ea(V_ES_ID in varchar2, o_result_code out varchar2, o_result_desc out varchar2) ;
  procedure update_excess_from_ht(i_service_number in varchar2,i_reading_month in varchar2,i_reading_year in varchar2, o_result_code out varchar2, o_result_desc out varchar2) ;
  procedure find_excess_consumed_in_ea(i_ea in t_energy_sale%rowtype, i_excess_unit_type in varchar2,i_reading_month in varchar2,i_reading_year in varchar2, o_unit_data out UD_EXCESS_UNIT, o_result_code out varchar2, o_result_desc out varchar2) ;
  procedure find_excess_produced_in_ea(i_ea in t_energy_sale%rowtype, i_excess_unit_type in varchar2,i_reading_month in varchar2,i_reading_year in varchar2, o_unit_data out UD_EXCESS_UNIT, o_result_code out varchar2, o_result_desc out varchar2) ;  
END EXCESS_UNITS_SOURCE;



CREATE OR REPLACE PACKAGE BODY EXCESS_UNITS_SOURCE AS

  procedure update_excess_from_ea(V_ES_ID in varchar2, o_result_code out varchar2, o_result_desc out varchar2)  
  
    IS
    v_process varchar2(500):='EXCESS_UNITS_SOURCE.UPDATE_EXCESS_FROM_EA';
    v_process_type varchar2(500):='PACKAGE';
    v_stage varchar2(500):='';
    v_step varchar2(500):='';
    v_message varchar2(500):=''; 
    v_excess_unit_type varchar2(50):='';    
    v_count number:=0; 
    v_service_number varchar2(50);
    v_reading_month  varchar2(50);
    v_reading_year varchar2(50);
    v_ea  t_energy_sale%rowtype;
    v_unit_data  UD_EXCESS_UNIT;
	v_exs_bal V_EXS_BALANCE%ROWTYPE;
	v_banking_bal T_EXS_BANKING_BALANCE%ROWTYPE;
	v_surplus_stb_bal T_EXS_SURPLUS_STB_BALANCE%ROWTYPE;
	v_lapsed_bal T_EXS_LAPSED_BALANCE%ROWTYPE;
    v_serv V_COMPANY_SERVICE%ROWTYPE;  
    v_log_result  varchar2(500);
    v_exs_decrease_result  varchar2(50);
    v_exs_increase_result  varchar2(50);
    v_reason varchar2(200):=''; 
    BEGIN 
        BEGIN  -- exception handling start
            v_stage:='INIT'; v_step:=''; v_message:='START'; 
            v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,V_ES_ID,'','');
            
            select * into v_ea from t_energy_sale where id= V_ES_ID;
            select * into v_serv from v_company_service where id = v_ea.seller_comp_serv_id;
            v_service_number := v_serv."number"; v_reading_month := v_ea.MONTH;  v_reading_year := v_ea.YEAR; 
            v_excess_unit_type:=v_serv.excess_unit_type;
            
             
            find_excess_consumed_in_ea(v_ea, v_excess_unit_type ,v_reading_month ,v_reading_year, v_unit_data,v_exs_decrease_result , v_reason);
            if(v_exs_decrease_result = 'YES') then
                excess_units.decrease_balance ('EA',v_service_number ,v_reading_month ,v_reading_year , v_unit_data , o_result_code , o_result_desc );  
            end if;
            
            find_excess_produced_in_ea(v_ea, v_excess_unit_type ,v_reading_month ,v_reading_year, v_unit_data,v_exs_increase_result , v_reason);
            if(v_exs_increase_result = 'YES') then
                excess_units.increase_balance ('EA',v_service_number ,v_reading_month ,v_reading_year , v_unit_data ,  o_result_code , o_result_desc );            
            end if;
            
            if(v_exs_decrease_result = 'NO' and v_exs_increase_result = 'NO') then                            
                o_result_code:='FAILURE';
                o_result_desc:='no excess activity';
                v_message := o_result_code||' - '||o_result_desc;
                GOTO THE_END;
            END IF;
            /*
            if increase condition
                logic
            */
        exception
        when others then 
            v_stage:='EXCEPTION';
            v_message := SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 100);
            v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,V_ES_ID,'','');
            -- dbms_output.put_line(o_reason);
        END;
    <<THE_END>>
    v_stage:='END'; v_step:='END';
    v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,V_ES_ID,'','');

    END update_excess_from_ea;

  procedure update_excess_from_ht(i_service_number in varchar2,i_reading_month in varchar2,i_reading_year in varchar2, o_result_code out varchar2, o_result_desc out varchar2) 
  IS
    v_process varchar2(500):='EXCESS_UNITS_SOURCE.update_excess_from_ht';
    v_process_type varchar2(500):='PACKAGE';
    v_stage varchar2(500):='';
    v_step varchar2(500):='';
    v_message varchar2(500):='';  
    v_reason varchar2(200):=''; 
    v_log_result varchar2(200):=''; 
    v_serv V_COMPANY_SERVICE%ROWTYPE;  
    v_unit_data  UD_EXCESS_UNIT;
    BEGIN 
        BEGIN  
            v_stage:='INIT'; v_step:=''; v_message:='START'; 
            v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
            o_result_code:='NO';            
            v_unit_data := UD_EXCESS_UNIT(0,0,0,0,0,0,'','','');            
            
            FOR supplier in (  select ser."number" SUPLR_CODE, ser.M_COMPANY_ID,ser.BANKING_SERVICE_ID, READING_MNTH, READING_YR,sum(nvl(s.C1_WITHLOSS,0)) c1_wl, sum(nvl(s.C2_WITHLOSS,0)) c2_wl,sum(nvl(s.C3_WITHLOSS,0)) c3_wl, sum(nvl(s.C4_WITHLOSS,0)) c4_wl,sum(nvl(s.C5_WITHLOSS,0)) c5_wl 
                                from  F_ENERGY_ADJUSTMET s join m_company_service ser on ser."number"=s.suplr_code  where READING_MNTH=i_reading_month and READING_YR=i_reading_year 
                                and ser."number"=i_service_number
                                group by ser."number" ,ser.M_COMPANY_ID,ser.BANKING_SERVICE_ID,READING_MNTH, READING_YR)
              loop
              begin
                SELECT * INTO v_serv FROM V_COMPANY_SERVICE WHERE "number"=i_service_number;
                v_unit_data.c1 :=0; v_unit_data.c2 :=0; v_unit_data.c3 :=0; v_unit_data.c4 :=0; v_unit_data.c5 :=0;v_unit_data.total_units :=0;              
                                 
                IF TO_NUMBER(nvl(supplier.c1_wl,0))>0 THEN v_unit_data.c1 := TO_NUMBER(nvl(supplier.c1_wl,0)); o_result_code:='YES';   END IF;
                IF TO_NUMBER(nvl(supplier.c2_wl,0))>0 THEN v_unit_data.c2 := TO_NUMBER(nvl(supplier.c2_wl,0)); o_result_code:='YES';   END IF;
                IF TO_NUMBER(nvl(supplier.c3_wl,0))>0 THEN v_unit_data.c3 := TO_NUMBER(nvl(supplier.c3_wl,0)); o_result_code:='YES';   END IF;
                IF TO_NUMBER(nvl(supplier.c4_wl,0))>0 THEN v_unit_data.c4 := TO_NUMBER(nvl(supplier.c4_wl,0)); o_result_code:='YES';   END IF;
                IF TO_NUMBER(nvl(supplier.c5_wl,0))>0 THEN v_unit_data.c5 := TO_NUMBER(nvl(supplier.c5_wl,0)); o_result_code:='YES';   END IF;
                if( o_result_code='YES') then            
                    v_unit_data.total_units := v_unit_data.c1 + v_unit_data.c2 + v_unit_data.c3 + v_unit_data.c4 + v_unit_data.c5;
                end if;            
                v_unit_data.UPDATE_DT := sysdate; --v_unit_data.UPDATE_BY := v_process;
                excess_units.increase_balance ('HT',i_service_number ,i_reading_month ,i_reading_year , v_unit_data ,  o_result_code , o_result_desc );            
                    
           exception
            when others then
            v_stage:='EXCEPTION-INNER';
            v_message := SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 100);
            v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message, supplier.SUPLR_CODE,i_reading_month, i_reading_year);
               UPDATE F_ENERGY_ADJUSTMET SET imported='N', import_remarks=o_result_desc where READING_MNTH=i_reading_month and READING_YR=i_reading_year and SUPLR_CODE = supplier.SUPLR_CODE;
           END;
          END LOOP; 
        
         exception
        when others then 
            v_stage:='EXCEPTION-OUTER';
            v_message := SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 100);
            v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
            -- dbms_output.put_line(o_reason);
        END;
    <<THE_END>>
    v_stage:='END'; v_step:='END';
    v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
  END update_excess_from_ht;

  procedure find_excess_consumed_in_ea( i_ea in t_energy_sale%rowtype, i_excess_unit_type in varchar2,i_reading_month in varchar2,i_reading_year in varchar2, o_unit_data out UD_EXCESS_UNIT, o_result_code out varchar2, o_result_desc out varchar2) 
    IS
    v_process varchar2(500):='EXCESS_UNITS_SOURCE.find_excess_consumed_in_ea';
    v_process_type varchar2(500):='PACKAGE';
    v_stage varchar2(500):='';
    v_step varchar2(500):='';
    v_message varchar2(500):='';  
    v_reason varchar2(200):=''; 
    v_log_result varchar2(200):=''; 
    BEGIN 
        BEGIN  
            o_result_code:='NO';            
            o_unit_data := UD_EXCESS_UNIT(0,0,0,0,0,0,'','','');                       
            
            if(i_excess_unit_type in ('LAPSED','SURPLUS-STB')) then Raise_Application_Error (-20343, 'Invalid scenario'); end if;
            
            IF TO_NUMBER(nvl(i_ea.BC1,0))>0 THEN o_unit_data.c1 := TO_NUMBER(nvl(i_ea.BC1,0)); o_result_code:='YES';   END IF;
            IF TO_NUMBER(nvl(i_ea.BC2,0))>0 THEN o_unit_data.c2 := TO_NUMBER(nvl(i_ea.BC2,0)); o_result_code:='YES';   END IF;
            IF TO_NUMBER(nvl(i_ea.BC3,0))>0 THEN o_unit_data.c3 := TO_NUMBER(nvl(i_ea.BC3,0)); o_result_code:='YES';   END IF;
            IF TO_NUMBER(nvl(i_ea.BC4,0))>0 THEN o_unit_data.c4 := TO_NUMBER(nvl(i_ea.BC4,0)); o_result_code:='YES';   END IF;
            IF TO_NUMBER(nvl(i_ea.BC5,0))>0 THEN o_unit_data.c5 := TO_NUMBER(nvl(i_ea.BC5,0)); o_result_code:='YES';   END IF;
            if( o_result_code='YES') then            
                o_unit_data.total_units := o_unit_data.c1 + o_unit_data.c2 + o_unit_data.c3 + o_unit_data.c4 + o_unit_data.c5;
            end if;
            o_unit_data.SRC_ID := i_ea.id; o_unit_data.UPDATE_DT := sysdate; --o_unit_data.UPDATED_BY := v_process;
         exception
        when others then 
            v_stage:='EXCEPTION';
            v_message := SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 100);
            v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_ea.id,'','');
            -- dbms_output.put_line(o_reason);
        END;
    <<THE_END>>
    v_stage:='END'; v_step:='END';
    v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_ea.id,o_result_code,''); 
  END find_excess_consumed_in_ea;

  procedure find_excess_produced_in_ea(i_ea in t_energy_sale%rowtype, i_excess_unit_type in varchar2,i_reading_month in varchar2,i_reading_year in varchar2, o_unit_data out UD_EXCESS_UNIT, o_result_code out varchar2, o_result_desc out varchar2)
  IS
    v_process varchar2(500):='EXCESS_UNITS_SOURCE.find_excess_produced_in_ea';
    v_process_type varchar2(500):='PACKAGE';
    v_stage varchar2(500):='';
    v_step varchar2(500):='';
    v_message varchar2(500):='';  
    v_reason varchar2(200):=''; 
    v_log_result varchar2(200):=''; 
    BEGIN 
        BEGIN  
            o_result_code:='NO';            
            o_unit_data := UD_EXCESS_UNIT(0,0,0,0,0,0,'','','');                       
            
            IF TO_NUMBER(nvl(i_ea.AVAIL_GC1,0))  > TO_NUMBER(nvl(i_ea.GC1,0)) THEN 
                o_unit_data.c1:=(TO_NUMBER(nvl(i_ea.AVAIL_GC1,0)) - TO_NUMBER(nvl(i_ea.GC1,0))); 
                if i_excess_unit_type ='BANKING' then
                    o_unit_data.c1 :=o_unit_data.c1 -(o_unit_data.c1 *0.14);  
                end if;
                o_result_code:='YES';   
            END IF; 
            IF TO_NUMBER(nvl(i_ea.AVAIL_GC2,0))  > TO_NUMBER(nvl(i_ea.GC2,0)) THEN 
                o_unit_data.c2:=(TO_NUMBER(nvl(i_ea.AVAIL_GC2,0)) - TO_NUMBER(nvl(i_ea.GC2,0))); 
                if i_excess_unit_type ='BANKING' then 
                    o_unit_data.c2 :=o_unit_data.c2 -(o_unit_data.c2 *0.14);    
                end if;
                o_result_code:='YES';   
            END IF; 
            IF TO_NUMBER(nvl(i_ea.AVAIL_GC3,0))  > TO_NUMBER(nvl(i_ea.GC3,0)) THEN 
                o_unit_data.c3:=(TO_NUMBER(nvl(i_ea.AVAIL_GC3,0)) - TO_NUMBER(nvl(i_ea.GC3,0)));  
                if i_excess_unit_type ='BANKING' then
                    o_unit_data.c3 :=o_unit_data.c3 -(o_unit_data.c3 *0.14);    
                end if;
                o_result_code:='YES';   
            END IF; 
            IF TO_NUMBER(nvl(i_ea.AVAIL_GC4,0))  > TO_NUMBER(nvl(i_ea.GC4,0)) THEN 
                o_unit_data.c4:=(TO_NUMBER(nvl(i_ea.AVAIL_GC4,0)) - TO_NUMBER(nvl(i_ea.GC4,0)));  
                if i_excess_unit_type ='BANKING' then
                    o_unit_data.c4 :=o_unit_data.c4 -(o_unit_data.c4 *0.14);    
                end if;
                o_result_code:='YES';   
            END IF; 
            IF TO_NUMBER(nvl(i_ea.AVAIL_GC5,0))  > TO_NUMBER(nvl(i_ea.GC5,0)) THEN 
                o_unit_data.c5:=(TO_NUMBER(nvl(i_ea.AVAIL_GC5,0)) - TO_NUMBER(nvl(i_ea.GC5,0)));  
                if i_excess_unit_type ='BANKING' then
                    o_unit_data.c5 :=o_unit_data.c5 -(o_unit_data.c5 *0.14);    
                end if;
                o_result_code:='YES';   
            END IF; 
            if( o_result_code='YES') then            
                o_unit_data.c1 := round(o_unit_data.c1,0);
                o_unit_data.c2 := round(o_unit_data.c2,0);
                o_unit_data.c3 := round(o_unit_data.c3,0);
                o_unit_data.c4 := round(o_unit_data.c4,0);
                o_unit_data.c5 := round(o_unit_data.c5,0);
                o_unit_data.total_units := o_unit_data.c1 + o_unit_data.c2 + o_unit_data.c3 + o_unit_data.c4 + o_unit_data.c5;
            end if;
            o_unit_data.SRC_ID := i_ea.id; o_unit_data.UPDATE_DT := sysdate;-- o_unit_data.UPDATE_BY := v_process;
         exception
        when others then 
            v_stage:='EXCEPTION';
            v_message := SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 100);
            v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_ea.id,'','');
            -- dbms_output.put_line(o_reason);
        END;
    <<THE_END>>
    v_stage:='END'; v_step:='END';
    v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_ea.id,'','');
  END find_excess_produced_in_ea;

END EXCESS_UNITS_SOURCE;
