CREATE OR REPLACE FUNCTION OPENACCESS."ADJUST_ENERGY_SALE_AVAILABLE_UNITS" 
(
  i_energy_sale_id IN VARCHAR2 
) RETURN VARCHAR2 AS 

v_created_By  varchar2(50):= 'admin';
v_status varchar2(50);
v_reason varchar2(200):='';
v_exception_code  NUMBER;
v_exception_msg  VARCHAR2(200);
v_result varchar(300):='SUCCESS';
v_log_result varchar(300):='SUCCESS';

v_count number:=0;
v_es_usage_count number:=0;
v_energy_sale t_energy_sale%ROWTYPE;
v_gen_stmt_slot t_gen_stmt_slot%ROWTYPE;
v_gen_stmt_slot_cursor sys_refcursor;
v_gs_slot1_units number:=0; 
v_gs_slot2_units number:=0;
v_gs_slot3_units number:=0;
v_gs_slot4_units number:=0;
v_gs_slot5_units number:=0;
v_gs_slot1_bb number:=0;
v_gs_slot2_bb number:=0;
v_gs_slot3_bb number:=0;
v_gs_slot4_bb number:=0;
v_gs_slot5_bb number:=0;

v_c1_count number:=0;
v_c2_count number:=0;
v_c3_count number:=0;
v_c4_count number:=0;
v_c5_count number:=0;

--v_test_count number:=0;

v_excess_es1 VARCHAR2(300);
    v_excess_es VARCHAR2(300);


BEGIN

-- begin for exception handling
BEGIN

   v_log_result := log_activity('FUNCTION','ADJUST_ENERGY_SALE_AVAILABLE_UNITS','START','Start - '||i_energy_sale_id,'','', sysdate,i_energy_sale_id);

   select count(*) into v_count from t_energy_sale where  month='02' and year='2019' AND status_code='APPROVED' AND C1=0 AND C2=0 AND C3=0 AND C4=0 AND C5=0  and id=i_energy_sale_id ;

   if v_count=1 then
     select * into v_energy_sale from t_energy_sale where month='02' and year='2019' AND status_code='APPROVED' AND C1=0 AND C2=0 AND C3=0 AND C4=0 AND C5=0  and id=i_energy_sale_id  ;

         select count(*) into v_es_usage_count from T_ES_USAGE_SUMMARY where T_ENERGY_SALE_ID=i_energy_sale_id ;

         if v_es_usage_count>0 then

                   OPEN v_gen_stmt_slot_cursor for select * from t_gen_stmt_slot where t_gen_stmt_id=v_energy_sale.t_gen_stmt_id;
            LOOP
            FETCH v_gen_stmt_slot_cursor INTO v_gen_stmt_slot;
            EXIT WHEN v_gen_stmt_slot_cursor%NOTFOUND;
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
            END LOOP;
--v_test_count:=v_test_count+1;
--                   -- dbms_output.put_line('v_gs_slot1_units ==>'||v_gs_slot1_units);
--                   -- dbms_output.put_line('v_gs_slot2_units ==>'||v_gs_slot2_units);
--                   -- dbms_output.put_line('v_gs_slot3_units ==>'||v_gs_slot3_units);
--                   -- dbms_output.put_line('v_gs_slot4_units ==>'||v_gs_slot4_units);
--                   -- dbms_output.put_line('v_gs_slot5_units ==>'||v_gs_slot5_units);
--                      
--                   -- dbms_output.put_line('v_gs_slot1_bb ==>'||v_gs_slot1_bb);
--                   -- dbms_output.put_line('v_gs_slot2_bb ==>'||v_gs_slot2_bb);
--                   -- dbms_output.put_line('v_gs_slot3_bb ==>'||v_gs_slot3_bb);
--                   -- dbms_output.put_line('v_gs_slot4_bb ==>'||v_gs_slot4_bb);
--                   -- dbms_output.put_line('v_gs_slot5_bb ==>'||v_gs_slot5_bb);

             FOR summary IN ( select * from T_ES_USAGE_SUMMARY where T_ENERGY_SALE_ID=i_energy_sale_id)
                  LOOP
--                 -- dbms_output.put_line('summary.c1 ==>'||summary.c1);
                   v_c1_count := to_number(nvl(summary.c1,0)) + v_c1_count ;
                   v_c2_count := to_number(nvl(summary.c2,0)) + v_c2_count ;
                   v_c3_count := to_number(nvl(summary.c3,0)) + v_c3_count ;
                   v_c4_count := to_number(nvl(summary.c4,0)) + v_c4_count ;
                   v_c5_count := to_number(nvl(summary.c5,0)) + v_c5_count ;

                  END LOOP;
--                  -- dbms_output.put_line('v_c1_count ==>'||v_c1_count);
--                  -- dbms_output.put_line('v_c2_count ==>'||v_c2_count);
--                  -- dbms_output.put_line('v_c3_count ==>'||v_c3_count);
--                  -- dbms_output.put_line('v_c4_count ==>'||v_c4_count);
--                  -- dbms_output.put_line('v_c5_count ==>'||v_c5_count);


                if (v_c1_count > v_gs_slot1_units) then
                  v_energy_sale.gc1:=v_gs_slot1_units;
                  v_energy_sale.bc1:=v_c1_count-v_gs_slot1_units;
                  v_energy_sale.c1:=to_number(v_energy_sale.gc1)+to_number(v_energy_sale.bc1);
                else if (v_c1_count <=v_gs_slot1_units) then
                    v_energy_sale.gc1:=v_c1_count;
                    v_energy_sale.bc1:=0;
                    v_energy_sale.c1:=to_number(v_energy_sale.gc1)+to_number(v_energy_sale.bc1);
                  end if;                 
                end if;

                if (v_c2_count > v_gs_slot2_units) then
                  v_energy_sale.gc2:=v_gs_slot2_units;
                  v_energy_sale.bc2:=v_c2_count-v_gs_slot2_units;
                  v_energy_sale.c2:=to_number(v_energy_sale.gc2)+to_number(v_energy_sale.bc2);
                else if (v_c2_count <=v_gs_slot2_units) then
                    v_energy_sale.gc2:=v_c2_count;
                    v_energy_sale.bc2:=0;
                    v_energy_sale.c2:=to_number(v_energy_sale.gc2)+to_number(v_energy_sale.bc2);
                  end if;                 
                end if;

                if (v_c3_count > v_gs_slot3_units) then
                  v_energy_sale.gc3:=v_gs_slot3_units;
                  v_energy_sale.bc3:=v_c3_count-v_gs_slot3_units;
                  v_energy_sale.c3:=to_number(v_energy_sale.gc3)+to_number(v_energy_sale.bc3);
                else if (v_c3_count <=v_gs_slot3_units) then
                    v_energy_sale.gc3:=v_c3_count;
                    v_energy_sale.bc3:=0;
                    v_energy_sale.c3:=to_number(v_energy_sale.gc3)+to_number(v_energy_sale.bc3);
                  end if;                 
                end if;

                if (v_c4_count > v_gs_slot4_units) then
                  v_energy_sale.gc4:=v_gs_slot4_units;
                  v_energy_sale.bc4:=v_c4_count-v_gs_slot4_units;
                  v_energy_sale.c4:=to_number(v_energy_sale.gc4)+to_number(v_energy_sale.bc4);
                else if (v_c4_count <=v_gs_slot4_units) then
                    v_energy_sale.gc4:=v_c4_count;
                    v_energy_sale.bc4:=0;
                    v_energy_sale.c4:=to_number(v_energy_sale.gc4)+to_number(v_energy_sale.bc4);
                  end if;                 
                end if;

                if (v_c5_count > v_gs_slot5_units) then
                  v_energy_sale.gc5:=v_gs_slot5_units;
                  v_energy_sale.bc5:=v_c5_count-v_gs_slot5_units;
                  v_energy_sale.c5:=to_number(v_energy_sale.gc5)+to_number(v_energy_sale.bc5);
                else if (v_c5_count <=v_gs_slot5_units) then
                    v_energy_sale.gc5:=v_c5_count;
                    v_energy_sale.bc5:=0;
                    v_energy_sale.c5:=to_number(v_energy_sale.gc5)+to_number(v_energy_sale.bc5);
                  end if;                 
                end if;

                v_energy_sale.NET_GENERATION:=(to_number(v_energy_sale.gc1)+to_number(v_energy_sale.gc2)+to_number(v_energy_sale.gc3)+to_number(v_energy_sale.gc4)+to_number(v_energy_sale.gc5));
                v_energy_sale.TOTAL_BANK_UNITS_USED:=(to_number(v_energy_sale.bc1)+to_number(v_energy_sale.bc2)+to_number(v_energy_sale.bc3)+to_number(v_energy_sale.bc4)+to_number(v_energy_sale.bc5));
                v_energy_sale.NET_ALLOCATION:=(to_number(v_energy_sale.c1)+to_number(v_energy_sale.c2)+to_number(v_energy_sale.c3)+to_number(v_energy_sale.c4)+to_number(v_energy_sale.c5));
                update t_energy_sale set gc1=v_energy_sale.gc1,gc2=v_energy_sale.gc2,gc3=v_energy_sale.gc3,gc4=v_energy_sale.gc4,gc5=v_energy_sale.gc5, bc1=v_energy_sale.bc1,bc2=v_energy_sale.bc2,bc3=v_energy_sale.bc3,bc4=v_energy_sale.bc4,bc5=v_energy_sale.bc5,
                c1=v_energy_sale.c1,c2=v_energy_sale.c2,c3=v_energy_sale.c3,c4=v_energy_sale.c4,c5=v_energy_sale.c5,NET_GENERATION=v_energy_sale.NET_GENERATION,TOTAL_BANK_UNITS_USED=v_energy_sale.TOTAL_BANK_UNITS_USED,NET_ALLOCATION= v_energy_sale.NET_ALLOCATION
                where id=i_energy_sale_id;



                 update F_ENERGY_SALE_ORDER set total_gc1=v_energy_sale.gc1,total_gc2=v_energy_sale.gc2,total_gc3=v_energy_sale.gc3,total_gc4=v_energy_sale.gc4,total_gc5=v_energy_sale.gc5, total_bc1=v_energy_sale.bc1,total_bc2=v_energy_sale.bc2,total_bc3=v_energy_sale.bc3,total_bc4=v_energy_sale.bc4,total_bc5=v_energy_sale.bc5,
                total_c1=v_energy_sale.c1,total_c2=v_energy_sale.c2,total_c3=v_energy_sale.c3,total_c4=v_energy_sale.c4,total_c5=v_energy_sale.c5,TOTAL_GEN_UNITS_SOLD=v_energy_sale.NET_GENERATION,TOTAL_BANKING_UNITS_SOLD=v_energy_sale.TOTAL_BANK_UNITS_USED,TOTAL_UNITS_SOLD= v_energy_sale.NET_ALLOCATION
                where T_ENERGY_SALE_ID=i_energy_sale_id;
                BANKING_BALANCE.confirm_energy_sale_event(i_energy_sale_id,v_excess_es,v_excess_es1);
--                 -- dbms_output.put_line('v_energy_sale.gc1 ==>'||v_energy_sale.gc1 ||'--v_energy_sale.bc1 ==>'||v_energy_sale.bc1 ||'--v_energy_sale.c1 ==>'||v_energy_sale.c1);
--                 -- dbms_output.put_line('v_energy_sale.gc2 ==>'||v_energy_sale.gc2 ||'--v_energy_sale.bc2 ==>'||v_energy_sale.bc2 ||'--v_energy_sale.c2 ==>'||v_energy_sale.c2);
--                 -- dbms_output.put_line('v_energy_sale.gc3 ==>'||v_energy_sale.gc3 ||'--v_energy_sale.bc3 ==>'||v_energy_sale.bc3 ||'--v_energy_sale.c3 ==>'||v_energy_sale.c3);
--                 -- dbms_output.put_line('v_energy_sale.gc4 ==>'||v_energy_sale.gc4 ||'--v_energy_sale.bc4 ==>'||v_energy_sale.bc4 ||'--v_energy_sale.c4 ==>'||v_energy_sale.c4);
--                 -- dbms_output.put_line('v_energy_sale.gc5 ==>'||v_energy_sale.gc5 ||'--v_energy_sale.bc5 ==>'||v_energy_sale.bc5 ||'--v_energy_sale.c5 ==>'||v_energy_sale.c5);
--                 -- dbms_output.put_line('v_net_generation==>'||v_energy_sale.net_generation);
--                 -- dbms_output.put_line('v_total_banked_units==>'||v_energy_sale.TOTAL_BANK_UNITS_USED);
--                 -- dbms_output.put_line('v_net_allocation==>'||v_energy_sale.net_allocation);

         else
            v_log_result := log_activity('FUNCTION','Energy Sale does not exist','ADJUST_ENERGY_SALE_AVAILABLE_UNITS','no data found','error_records -','', sysdate,i_energy_sale_id);
            V_RESULT:= 'FAILURE';
            v_reason := 'no data found';
           end if;

   else
     v_log_result := log_activity('FUNCTION','Energy Sale does not exist','ADJUST_ENERGY_SALE_AVAILABLE_UNITS','no data found','error_records -','', sysdate,i_energy_sale_id);
    V_RESULT:= 'FAILURE';
    v_reason := 'no data found';
   end if;

END;
v_log_result := log_activity('FUNCTION','ADJUST_ENERGY_SALE_AVAILABLE_UNITS','END','','','', sysdate,i_energy_sale_id);


return V_RESULT || ' - ' || v_reason;

END ADJUST_ENERGY_SALE_AVAILABLE_UNITS;

