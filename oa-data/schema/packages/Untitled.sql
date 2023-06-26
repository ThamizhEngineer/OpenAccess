CREATE OR REPLACE PACKAGE BODY OPENACCESS.AUDIT_OPS AS

  procedure REGISTER_UNALLOC_SERVICE (
                                    i_org_id in varchar2 ,
                                    i_reading_month in varchar2 ,
                                    i_reading_year in varchar2 ,
                                    i_is_captive in varchar2 ,
                                    i_is_stb in varchar2 ,
                                    i_is_third_party in varchar2 ,
                                    o_result_code out varchar2,o_result_desc out varchar2) IS
    v_allot_order_count number :=0;
    v_process varchar2(50):='AUDIT_OPS.REGISTER_UNALLOC_SERVICE';
    v_process_type varchar2(500):='PACKAGE';
    v_stage varchar2(500):='';
    v_step varchar2(500):='';
    v_message varchar2(500):='';
    v_created_by varchar2(100):= v_process;
    v_log_result  varchar2(500);
    v_reason varchar2(200):='';     
    v_input  varchar2(200):='';      
    v_org_id varchar2(50):=''; v_reading_month varchar2(10):=''; v_reading_year varchar2(10):='';v_is_captive varchar2(10):='';v_is_stb varchar2(10):='';v_flow_type_code varchar2(50):=''; 
  BEGIN
    BEGIN  -- exception handling start
        
        
        o_result_code := 'FAILURE';
    
        v_input := i_org_id||','|| i_reading_month||','||i_reading_year ||','||i_is_captive ||','|| i_is_stb||','||i_is_third_party;
        v_stage:='INIT'; v_message:='START';
        v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,v_input,'', '');
       
       -- input validation
       
        if(i_org_id is null or i_org_id = '' ) then v_org_id:='%'; else v_org_id := i_org_id; end if; 
        if(i_reading_month is null or i_reading_month = '' ) then v_reading_month:='%'; else v_reading_month:=i_reading_month; end if; 
        if(i_reading_year is null or i_reading_year = '' ) then v_reading_year:='%'; else  v_reading_year:=i_reading_year; end if; 
        if(i_is_captive is null or i_is_captive = '' ) then v_is_captive:='%'; else  v_is_captive:=i_is_captive; end if; 
        if(i_is_stb is null or i_is_stb = '' ) then v_is_stb:='%'; else  v_is_stb:=i_is_stb;  end if; 
        if(i_is_third_party is null or i_is_third_party = '' or i_is_third_party = 'N' ) then v_flow_type_code:='%'; else v_flow_type_code:='THIRD_PARTY'; end if; 
        v_input := v_org_id||','|| v_reading_month||','||v_reading_year ||','||v_is_captive ||','|| v_is_stb||','||v_flow_type_code;
        
        for unalloc in (select gs.STMT_MONTH, gs.STMT_YEAR,GS.DISP_COMPANY_NAME,gs.DISP_ORG_NAME, gs.id as T_GEN_STMT_ID,
                            gs.STMT_GEN_DATE, nvl(gs.NET_GENERATION,0) NET_GENERATION ,nvl(gs.total_charged_amount,0) total_charged_amount,
                            nvl(C001.total_charges,0) + nvl(C002.total_charges,0) +
                            nvl(C003.total_charges,0) + nvl(C004.total_charges,0) +
                            nvl(C005.total_charges,0) + nvl(C006.total_charges,0) +
                            nvl(C007.total_charges,0) total_charges,
                            GS.M_ORG_ID, gs.M_COMPANY_ID, gs.m_company_service_id M_COMP_SERVICE_ID, gs.disp_service_number M_COMP_SERVICE_NUMBER, gs.EXCESS_UNIT_TYPE,
                            gs.IS_CAPTIVE,gs.IS_STB , case when FLOW_TYPE_CODE ='THIRD-PARTY' then 'Y' else 'N' end as IS_THIRD_PARTY , gs.FLOW_TYPE_CODE 
                            from t_gen_stmt gs
                            left join t_gen_stmt_charge C001 on C001.t_gen_stmt_id = gs.id AND
                            C001.CHARGE_CODE = 'C001'
                            left join t_gen_stmt_charge C002 on C002.t_gen_stmt_id = gs.id and
                            C002.CHARGE_CODE = 'C002'
                            left join t_gen_stmt_charge C003 on C003.t_gen_stmt_id = gs.id and
                            C003.CHARGE_CODE = 'C003'
                            left join t_gen_stmt_charge C004 on C004.t_gen_stmt_id = gs.id and
                            C004.CHARGE_CODE = 'C004'
                            left join t_gen_stmt_charge C005 on C005.t_gen_stmt_id = gs.id and
                            C005.CHARGE_CODE = 'C005'
                            left join t_gen_stmt_charge C006 on C006.t_gen_stmt_id = gs.id and
                            C006.CHARGE_CODE = 'C006'
                            left join t_gen_stmt_charge C007 on C007.t_gen_stmt_id = gs.id and
                            C007.CHARGE_CODE = 'C007'
                            where
                            gs.m_org_id like v_org_id
                            and gs.stmt_month like v_reading_month
                            and gs.stmt_year like v_reading_year
                            and  gs.IS_CAPTIVE like v_is_captive
                            and  gs.IS_STB like v_is_stb
                            and  gs.FLOW_TYPE_CODE like v_flow_type_code
                            and gs.id not in(select T_GEN_STMT_ID from t_energy_sale  where STATUS_CODE='APPROVED') 
                            and gs.id not in(select T_GEN_STMT_ID from a_unallocated_service) 
                            order by stmt_year desc, stmt_month desc)
        loop 
            BEGIN
            INSERT INTO A_UNALLOCATED_SERVICE (ID, STMT_MONTH, STMT_YEAR, DISP_COMPANY_NAME,DISP_ORG_NAME, T_GEN_STMT_ID, STMT_GEN_DATE, NET_GENERATION, TOTAL_CHARGES, M_ORG_ID, M_COMPANY_ID, 
                                                M_COMP_SERVICE_ID, M_COMP_SERVICE_NUMBER,EXCESS_UNIT_TYPE,IS_THIRD_PARTY,IS_CAPTIVE,IS_STB,FLOW_TYPE_CODE, IS_DELETED
                                                ,CREATED_DT, CREATED_BY)
                        VALUES (A_UNALLOCATED_SERVICE_SEQ.nextval, unalloc.STMT_MONTH, unalloc.STMT_YEAR, unalloc.DISP_COMPANY_NAME, unalloc.DISP_ORG_NAME, unalloc.T_GEN_STMT_ID, unalloc.STMT_GEN_DATE, unalloc.NET_GENERATION, unalloc.TOTAL_CHARGES, unalloc.M_ORG_ID, unalloc.M_COMPANY_ID
                                , unalloc.M_COMP_SERVICE_ID, unalloc.M_COMP_SERVICE_NUMBER, unalloc.EXCESS_UNIT_TYPE, unalloc.IS_THIRD_PARTY, unalloc.IS_CAPTIVE, unalloc.IS_STB, unalloc.FLOW_TYPE_CODE, 'N'
                                , sysdate, v_process);
            exception
            when others then
                v_stage:='EXCEPTION';
                v_message := SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 200);
                v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,v_input,sysdate,'');
            END;
        end loop;
        commit;
        o_result_code := 'SUCCESS';
    exception
    when others then
        v_stage:='EXCEPTION';
        v_message := SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 200);
        v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,v_input,sysdate,'');
    END;
    <<THE_END>>
    v_stage:='END'; v_step:='END';
    v_message:=o_result_code;
    v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,v_input,sysdate,'');  
  END REGISTER_UNALLOC_SERVICE;

END AUDIT_OPS;