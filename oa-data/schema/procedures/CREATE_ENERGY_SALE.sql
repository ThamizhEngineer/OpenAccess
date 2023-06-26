create or replace procedure CREATE_ENERGY_SALE 
(
  v_gen_stmt_id IN VARCHAR2 ,
  v_es_id out varchar2
) IS 
v_created_By  varchar2(50):= 'CREATE_ENERGY_SALE';
v_status varchar2(50);
v_reason varchar2(200):='';
v_stmt_month varchar2(50):='';
v_exception_code  NUMBER;
v_exception_msg  VARCHAR2(200);
v_result varchar(300):='SUCCESS';
v_log_result varchar(300):='SUCCESS';
v_process_name varchar(50):= 'CREATE_ENERGY_SALE';
v_gs_count number:=0;
v_service_count number:=0;
v_trade_count number:=0;
v_es_stb_count number:=0;
v_traderel M_TRADE_RELATIONSHIP%ROWTYPE;
v_gen_stmt t_gen_stmt%ROWTYPE;
v_gen_stmt_slot t_gen_stmt_slot%ROWTYPE;
v_comp_serv v_company_service%ROWTYPE;
v_traderel_cursor sys_refcursor;
v_gen_stmt_slot_cursor sys_refcursor;
v_es t_energy_sale%ROWTYPE;
v_es_stb t_energy_sale%ROWTYPE;
v_es_usage_summary t_es_usage_summary%ROWTYPE;
v_es_charge t_es_charge%ROWTYPE;
v_gs_slot1_units varchar2(50):=0; 
v_gs_slot2_units varchar2(50):=0;
v_gs_slot3_units varchar2(50):=0;
v_gs_slot4_units varchar2(50):=0;
v_gs_slot5_units varchar2(50):=0;
v_gs_slot1_bb varchar2(50):=0;
v_gs_slot2_bb varchar2(50):=0;
v_gs_slot3_bb varchar2(50):=0;
v_gs_slot4_bb varchar2(50):=0;
v_gs_slot5_bb varchar2(50):=0;

v_es_stb_c1 varchar2(50):=0;
v_es_stb_c2 varchar2(50):=0;
v_es_stb_c3 varchar2(50):=0;
v_es_stb_c4 varchar2(50):=0;
v_es_stb_c5 varchar2(50):=0;
v_es_stb_bc1 varchar2(50):=0;
v_es_stb_bc2 varchar2(50):=0;
v_es_stb_bc3 varchar2(50):=0;
v_es_stb_bc4 varchar2(50):=0;
v_es_stb_bc5 varchar2(50):=0;
v_es_stb_gc1 varchar2(50):=0;
v_es_stb_gc2 varchar2(50):=0;
v_es_stb_gc3 varchar2(50):=0;
v_es_stb_gc4 varchar2(50):=0;
v_es_stb_gc5 varchar2(50):=0;
v_service_number varchar2(50);
BEGIN

-- begin for exception handling
BEGIN

   v_log_result := log_activity('FUNCTION','CREATE_ENERGY_SALE','START','Start - '||v_gen_stmt_id,'',v_created_By,'','', '',v_gen_stmt_id);

   select count(*), DISP_SERVICE_NUMBER into v_gs_count, v_service_number from t_gen_stmt where id = v_gen_stmt_id and status_code!='ALLOCATED'  GROUP BY  DISP_SERVICE_NUMBER ;

   v_log_result := log_activity('FUNCTION','CREATE_ENERGY_SALE','PROCESS','v_gs_count - '||v_gs_count,'',v_created_By,'','', v_service_number,v_gen_stmt_id);

   if(v_gs_count=0) then
      Raise_Application_Error (-20343, 'Gen-Stmt already allocated or No records to process');
   ELSIF v_gs_count>1 then
      Raise_Application_Error (-20343, 'Multiple Gen-Stmts with same id!!');
   else  -- v_gs_count=1 
        select * into v_gen_stmt from t_gen_stmt where id = v_gen_stmt_id;

        select count(*) into v_service_count from v_company_service where m_company_id=v_gen_stmt.m_company_id;
        
        v_log_result := log_activity('FUNCTION','CREATE_ENERGY_SALE','PROCESS','v_service_count - '||v_service_count,'',v_created_By,'','', v_service_number,v_gen_stmt_id);
        
        if v_service_count = 1 then
--            select * into v_comp_serv from v_company_service where m_company_id=v_gen_stmt.m_company_id;
            v_stmt_month:=v_gen_stmt.stmt_month||'-'||v_gen_stmt.stmt_year;
--            dbms_output.put_line('v_stmt_month'||v_stmt_month);


            if v_gen_stmt.status_code='PART-ALLOC' then            
                 select count(*) into v_es_stb_count from t_energy_sale where T_GEN_STMT_ID=v_gen_stmt_id and IS_STB='Y';                 
                 if v_es_stb_count>0 then                 
                      select * into v_es_stb from t_energy_sale where T_GEN_STMT_ID=v_gen_stmt_id and IS_STB='Y';
                      v_es_stb_c1:=v_es_stb.c1;
                      v_es_stb_c2:=v_es_stb.c2;
                      v_es_stb_c3:=v_es_stb.c3;
                      v_es_stb_c4:=v_es_stb.c4;
                      v_es_stb_c5:=v_es_stb.c5; 
                      v_es_stb_bc1:=v_es_stb.bc1;
                      v_es_stb_bc2:=v_es_stb.bc2;
                      v_es_stb_bc3:=v_es_stb.bc3;
                      v_es_stb_bc4:=v_es_stb.bc4;
                      v_es_stb_bc5:=v_es_stb.bc5; 
                      v_es_stb_gc1:=v_es_stb.gc1;
                      v_es_stb_gc2:=v_es_stb.gc2;
                      v_es_stb_gc3:=v_es_stb.gc3;
                      v_es_stb_gc4:=v_es_stb.gc4;
                      v_es_stb_gc5:=v_es_stb.gc5; 
                 end if;        

            end if;


            OPEN v_gen_stmt_slot_cursor for select * from t_gen_stmt_slot where t_gen_stmt_id=v_gen_stmt_id;
            LOOP
            FETCH v_gen_stmt_slot_cursor INTO v_gen_stmt_slot;
            EXIT WHEN v_gen_stmt_slot_cursor%NOTFOUND;
                -- v_log_result := log_activity('FUNCTION','CREATE_ENERGY_SALE','PROCESS',v_gen_stmt_slot.slot_code||'-'||v_gen_stmt_slot.net_units,v_gen_stmt_slot.banked_balance,v_created_By,'','', v_service_number,v_gen_stmt_id);

                if v_gen_stmt_slot.slot_code='C1' then
                    v_gs_slot1_units:=v_gen_stmt_slot.net_units;
                    v_gs_slot1_bb:=v_gen_stmt_slot.banked_balance; 
                end if;
                 if v_gen_stmt_slot.slot_code='C2' then
                    v_gs_slot2_units:=v_gen_stmt_slot.net_units;
                    v_gs_slot2_bb:=v_gen_stmt_slot.banked_balance; 
                end if;
                 if v_gen_stmt_slot.slot_code='C3' then
                    v_gs_slot3_units:=v_gen_stmt_slot.net_units;
                    v_gs_slot3_bb:=v_gen_stmt_slot.banked_balance; 
                end if;
                 if v_gen_stmt_slot.slot_code='C4' then 
                    v_gs_slot4_units:=v_gen_stmt_slot.net_units;
                    v_gs_slot4_bb:=v_gen_stmt_slot.banked_balance; 
                end if;
                 if v_gen_stmt_slot.slot_code='C5' then
                    v_gs_slot5_units:=v_gen_stmt_slot.net_units;
                    v_gs_slot5_bb:=v_gen_stmt_slot.banked_balance; 
                end if;       
               --  v_log_result := log_activity('FUNCTION','CREATE_ENERGY_SALE','PROCESS','END-'||v_gen_stmt_slot.net_units,v_gen_stmt_slot.banked_balance,v_created_By,'','', v_service_number,v_gen_stmt_id);

            END LOOP;

            v_log_result := log_activity('FUNCTION','CREATE_ENERGY_SALE','PROCESS','Gen Stmt Slots Read','',v_created_By,'','', v_service_number,v_gen_stmt_id);
        
            v_es.AVAIL_C1:= to_number(nvl(v_gen_stmt.c1,0))-to_number(nvl(v_es_stb_c1,0)); 
            v_es.AVAIL_C2:= to_number(nvl(v_gen_stmt.c2,0))-to_number(nvl(v_es_stb_c2,0)); 
            v_es.AVAIL_C3:= to_number(nvl(v_gen_stmt.c3,0))-to_number(nvl(v_es_stb_c3,0)); 
            v_es.AVAIL_C4:= to_number(nvl(v_gen_stmt.c4,0))-to_number(nvl(v_es_stb_c4,0)); 
            v_es.AVAIL_C5:= to_number(nvl(v_gen_stmt.c5,0))-to_number(nvl(v_es_stb_c5,0)); 

            v_es.AVAIL_GC1:= to_number(nvl(v_gs_slot1_units,0))-to_number(nvl(v_es_stb_gc1,0)); 
            v_es.AVAIL_GC2:= to_number(nvl(v_gs_slot2_units,0))-to_number(nvl(v_es_stb_gc2,0)); 
            v_es.AVAIL_GC3:= to_number(nvl(v_gs_slot3_units,0))-to_number(nvl(v_es_stb_gc3,0)); 
            v_es.AVAIL_GC4:= to_number(nvl(v_gs_slot4_units,0))-to_number(nvl(v_es_stb_gc4,0)); 
            v_es.AVAIL_GC5:= to_number(nvl(v_gs_slot5_units,0))-to_number(nvl(v_es_stb_gc5,0)); 

            v_es.AVAIL_BC1:= to_number(nvl(v_gs_slot1_bb,0))-to_number(nvl(v_es_stb_bc1,0)); 
            v_es.AVAIL_BC2:= to_number(nvl(v_gs_slot2_bb,0))-to_number(nvl(v_es_stb_bc2,0)); 
            v_es.AVAIL_BC3:= to_number(nvl(v_gs_slot3_bb,0))-to_number(nvl(v_es_stb_bc3,0)); 
            v_es.AVAIL_BC4:= to_number(nvl(v_gs_slot4_bb,0))-to_number(nvl(v_es_stb_bc4,0)); 
            v_es.AVAIL_BC5:= to_number(nvl(v_gs_slot5_bb,0))-to_number(nvl(v_es_stb_bc5,0)); 

            v_es.T_GEN_STMT_ID :=v_gen_stmt_id;
            v_es.SELLER_COMP_SERV_ID :=v_gen_stmt.M_COMPANY_SERVICE_ID;
            v_es.SELLER_END_ORG_ID :=v_gen_stmt.M_ORG_ID;
            v_es.MONTH :=v_gen_stmt.stmt_month;
            v_es.year :=v_gen_stmt.stmt_year;
            v_es.INJECTING_VOLTAGE_CODE :=v_gen_stmt.INJECTING_VOLTAGE_CODE;
            v_es.FROM_DT :=v_gen_stmt.INIT_STMT_DT;
            v_es.TO_DT :=v_gen_stmt.FINAL_STMT_DT;
            v_es.SIMPLE_ENERGY_SALE :='N';
            v_es.IS_STB:='N';
            v_es.C1 :=0;
            v_es.C2 :=0;
            v_es.C3 :=0;
            v_es.C4 :=0;
            v_es.C5 :=0;
            v_es.BC1 :=0;
            v_es.BC2 :=0;
            v_es.BC3 :=0;
            v_es.BC4 :=0;
            v_es.BC5 :=0;
            v_es.GC1 :=0;
            v_es.GC2 :=0;
            v_es.GC3 :=0;
            v_es.GC4 :=0;
            v_es.GC5 :=0;
            v_es.NET_GENERATION :=0;
            v_es.NET_ALLOCATION :=0;
            v_es.FUEL_GROUPE := v_gen_stmt.DISP_FUEL_TYPE_GROUP;
            v_es.TOTAL_BANK_UNITS_USED :=0;
            v_es.CREATED_BY :=v_process_name;
            v_es.CREATED_DATE :=sysdate;
            v_es.M_SUBSTATION_ID :=v_gen_stmt.M_SUBSTATION_ID;
            v_es.ENABLED :='Y';
            v_es.STATUS_CODE :='CREATED';
            v_es.ID:=T_ENERGY_SALE_SEQ.NEXTVAL;
            v_es_id:= v_es.ID;
            insert into t_energy_sale values v_es;


            v_log_result := log_activity('FUNCTION','CREATE_ENERGY_SALE','PROCESS','Energy Sale inserted','',v_created_By,'',v_es_id, v_service_number,v_gen_stmt_id);
            select count(*) into v_trade_count from m_trade_relationship trade  where trade.m_seller_company_id=v_gen_stmt.m_company_id and to_date(v_stmt_month,'MM-YYYY') between trade.from_date and trade.to_date;
--            dbms_output.put_line('v_trade_count'||v_trade_count);

            if v_trade_count>0 then

                  OPEN v_traderel_cursor for select * from m_trade_relationship trade  where trade.m_seller_company_id=v_gen_stmt.m_company_id and to_date(v_stmt_month,'MM-YYYY') between trade.from_date and trade.to_date;
                    LOOP
                    FETCH v_traderel_cursor INTO v_traderel;
                    EXIT WHEN v_traderel_cursor%NOTFOUND;
                        v_es_usage_summary.T_ENERGY_SALE_ID:=v_es_id;
                        v_es_usage_summary.C1:=0;
                        v_es_usage_summary.C2:=0;
                        v_es_usage_summary.C3:=0; 
                        v_es_usage_summary.C4:=0;
                        v_es_usage_summary.C5:=0;
                        v_es_usage_summary.total:=0;
                        v_es_usage_summary.BUYER_COMP_SERV_ID:=v_traderel.M_BUYER_COMP_SERVICE_ID;
                        v_es_usage_summary.M_TRADE_RELATIONSHIP_ID:=v_traderel.id;
                        v_es_usage_summary.CREATED_DATE:=sysdate;
                        v_es_usage_summary.ENABLED:='Y';
                        v_es_usage_summary.id:=T_ES_USAGE_SUMMARY_SEQ.nextval;
                        insert into t_es_usage_summary values v_es_usage_summary;
                        
                        v_log_result := log_activity('FUNCTION','CREATE_ENERGY_SALE','PROCESS','v_es_usage_summary-'||v_es_usage_summary.id,'',v_created_By,'',v_es_id, v_service_number,v_gen_stmt_id);

                        v_es_charge.T_ENERGY_SALE_ID:=v_es_id;
                        v_es_charge.M_COMP_SERV_ID:=v_traderel.M_BUYER_COMP_SERVICE_ID;
                        v_es_charge.CHARGE_CODE:='C001';
                        v_es_charge.TOTAL_CHARGE:=0;
                        v_es_charge.CREATED_DATE:=sysdate;
                        v_es_charge.CREATED_BY:=v_process_name;
                        v_es_charge.ENABLED:='Y';
                        v_es_charge.id:=T_ES_CHARGE_SEQ.nextval;
                        insert into t_es_charge values v_es_charge;
                        --v_log_result := log_activity('FUNCTION','CREATE_ENERGY_SALE','PROCESS',v_es_charge.id,'',v_created_By,'',v_es_id, v_service_number,v_gen_stmt_id);

                        v_es_charge.T_ENERGY_SALE_ID:=v_es_id;
                        v_es_charge.M_COMP_SERV_ID:=v_traderel.M_BUYER_COMP_SERVICE_ID;
                        v_es_charge.CHARGE_CODE:='C002';
                        v_es_charge.TOTAL_CHARGE:=0;
                        v_es_charge.CREATED_DATE:=sysdate;
                        v_es_charge.CREATED_BY:=v_process_name;
                        v_es_charge.ENABLED:='Y';
                        v_es_charge.id:=T_ES_CHARGE_SEQ.nextval;
                        insert into t_es_charge values v_es_charge;
                        --v_log_result := log_activity('FUNCTION','CREATE_ENERGY_SALE','PROCESS',v_es_charge.id,'',v_created_By,'',v_es_id, v_service_number,v_gen_stmt_id);

                        v_es_charge.T_ENERGY_SALE_ID:=v_es_id;
                        v_es_charge.M_COMP_SERV_ID:=v_traderel.M_BUYER_COMP_SERVICE_ID;
                        v_es_charge.CHARGE_CODE:='C003';
                        v_es_charge.TOTAL_CHARGE:=0;
                        v_es_charge.CREATED_DATE:=sysdate;
                        v_es_charge.CREATED_BY:=v_process_name;
                        v_es_charge.ENABLED:='Y';
                        v_es_charge.id:=T_ES_CHARGE_SEQ.nextval;
                        insert into t_es_charge values v_es_charge;
                        --v_log_result := log_activity('FUNCTION','CREATE_ENERGY_SALE','PROCESS',v_es_charge.id,'',v_created_By,'',v_es_id, v_service_number,v_gen_stmt_id);

                        v_es_charge.T_ENERGY_SALE_ID:=v_es_id;
                        v_es_charge.M_COMP_SERV_ID:=v_traderel.M_BUYER_COMP_SERVICE_ID;
                        v_es_charge.CHARGE_CODE:='C004';
                        v_es_charge.TOTAL_CHARGE:=0;
                        v_es_charge.CREATED_DATE:=sysdate;
                        v_es_charge.CREATED_BY:=v_process_name;
                        v_es_charge.ENABLED:='Y';
                        v_es_charge.id:=T_ES_CHARGE_SEQ.nextval;
                        insert into t_es_charge values v_es_charge;
                        --v_log_result := log_activity('FUNCTION','CREATE_ENERGY_SALE','PROCESS',v_es_charge.id,'',v_created_By,'',v_es_id, v_service_number,v_gen_stmt_id);

                        v_es_charge.T_ENERGY_SALE_ID:=v_es_id;
                        v_es_charge.M_COMP_SERV_ID:=v_traderel.M_BUYER_COMP_SERVICE_ID;
                        v_es_charge.CHARGE_CODE:='C005';
                        v_es_charge.TOTAL_CHARGE:=0;
                        v_es_charge.CREATED_DATE:=sysdate;
                        v_es_charge.CREATED_BY:=v_process_name;
                        v_es_charge.ENABLED:='Y';
                        v_es_charge.id:=T_ES_CHARGE_SEQ.nextval;
                        insert into t_es_charge values v_es_charge;
                        --v_log_result := log_activity('FUNCTION','CREATE_ENERGY_SALE','PROCESS',v_es_charge.id,'',v_created_By,'',v_es_id, v_service_number,v_gen_stmt_id);

                        v_es_charge.T_ENERGY_SALE_ID:=v_es_id;
                        v_es_charge.M_COMP_SERV_ID:=v_traderel.M_BUYER_COMP_SERVICE_ID;
                        v_es_charge.CHARGE_CODE:='C006';
                        v_es_charge.TOTAL_CHARGE:=0;
                        v_es_charge.CREATED_DATE:=sysdate;
                        v_es_charge.CREATED_BY:=v_process_name;
                        v_es_charge.ENABLED:='Y';
                        v_es_charge.id:=T_ES_CHARGE_SEQ.nextval;
                        insert into t_es_charge values v_es_charge;
                        --v_log_result := log_activity('FUNCTION','CREATE_ENERGY_SALE','PROCESS',v_es_charge.id,'',v_created_By,'',v_es_id, v_service_number,v_gen_stmt_id);

                        v_es_charge.T_ENERGY_SALE_ID:=v_es_id;
                        v_es_charge.M_COMP_SERV_ID:=v_traderel.M_BUYER_COMP_SERVICE_ID;
                        v_es_charge.CHARGE_CODE:='C007';
                        v_es_charge.TOTAL_CHARGE:=0;
                        v_es_charge.CREATED_DATE:=sysdate;
                        v_es_charge.CREATED_BY:=v_process_name;
                        v_es_charge.ENABLED:='Y';
                        v_es_charge.id:=T_ES_CHARGE_SEQ.nextval;
                        insert into t_es_charge values v_es_charge;
                        --v_log_result := log_activity('FUNCTION','CREATE_ENERGY_SALE','PROCESS',v_es_charge.id,'',v_created_By,'',v_es_id, v_service_number,v_gen_stmt_id);

                        v_es_charge.T_ENERGY_SALE_ID:=v_es_id;
                        v_es_charge.M_COMP_SERV_ID:=v_traderel.M_BUYER_COMP_SERVICE_ID;
                        v_es_charge.CHARGE_CODE:='C008';
                        v_es_charge.TOTAL_CHARGE:=0;
                        v_es_charge.CREATED_DATE:=sysdate;
                        v_es_charge.CREATED_BY:=v_process_name;
                        v_es_charge.ENABLED:='Y';
                        v_es_charge.id:=T_ES_CHARGE_SEQ.nextval;
                        insert into t_es_charge values v_es_charge;
                        --v_log_result := log_activity('FUNCTION','CREATE_ENERGY_SALE','PROCESS',v_es_charge.id,'',v_created_By,'',v_es_id, v_service_number,v_gen_stmt_id);
                        
                        v_es_charge.T_ENERGY_SALE_ID:=v_es_id;
                        v_es_charge.M_COMP_SERV_ID:=v_traderel.M_BUYER_COMP_SERVICE_ID;
                        v_es_charge.CHARGE_CODE:='C009';
                        v_es_charge.TOTAL_CHARGE:=0;
                        v_es_charge.CREATED_DATE:=sysdate;
                        v_es_charge.CREATED_BY:=v_process_name;
                        v_es_charge.ENABLED:='Y';
                        v_es_charge.id:=T_ES_CHARGE_SEQ.nextval;
                        insert into t_es_charge values v_es_charge;
                        --v_log_result := log_activity('FUNCTION','CREATE_ENERGY_SALE','PROCESS',v_es_charge.id,'',v_created_By,'',v_es_id, v_service_number,v_gen_stmt_id);
                  END LOOP;

            update t_gen_stmt set status_code='ALLOCATED' where id=v_gen_stmt_id; 
            v_log_result := log_activity('FUNCTION','CREATE_ENERGY_SALE','PROCESS','Energy Sale Created','energy_sale_id'||v_es_id,v_created_By,'','gen_stmt_id'||v_gen_stmt_id, v_service_number,v_gen_stmt_id);

            else
                v_reason:='trade relationship does not exist';
                v_log_result := log_activity('FUNCTION','CREATE_ENERGY_SALE','trade relationship does not exist','v_trade_count - '||v_trade_count,'',v_created_By,'','', v_service_number,v_gen_stmt_id);
            end if;
        else 
            v_reason:='company does not exist';
            v_log_result := log_activity('FUNCTION','CREATE_ENERGY_SALE','company does not exist','v_service_count - '||v_service_count,'',v_created_By,'','', v_service_number, v_gen_stmt_id);
        end if;

   end if;



      commit;
    exception
	  when others then
	    v_exception_code := SQLCODE;
	    v_exception_msg := SUBSTR(SQLERRM, 1, 150);
	    v_result := 'FAILURE';
	    v_reason := v_exception_code || ' - ' || v_exception_msg;
       dbms_output.put_line(v_reason);
        v_log_result := log_activity('FUNCTION','CREATE_ENERGY_SALE','EXCEPTION',v_reason,'',v_created_By,'','', v_service_number,v_gen_stmt_id);
END;

v_log_result := log_activity('FUNCTION','CREATE_ENERGY_SALE','END',v_reason,'energy_sale_id'||v_es_id,v_created_By,'','gen_stmt_id'||v_gen_stmt_id, v_service_number,v_gen_stmt_id);



END CREATE_ENERGY_SALE;