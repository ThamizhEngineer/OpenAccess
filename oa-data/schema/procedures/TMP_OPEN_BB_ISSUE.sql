CREATE OR REPLACE PROCEDURE OPENACCESS.TMP_OPEN_BB_ISSUE is
v_result VARCHAR2(100);
v_log_result VARCHAR2(100);
O_RESULT_CODE VARCHAR2(200);
O_RESULT_DESC VARCHAR2(200);
v_banking_service_id varchar(50);
v_es_count number:=0; 
v_gen_stmt_count number:=0;
v_curr_month_bb_count number:=0;
v_bb_new T_BANKING_BALANCE%ROWTYPE;
v_gen_stmt t_gen_stmt%ROWTYPE;
v_gen_stmt_slot t_gen_stmt_slot%ROWTYPE;
v_es t_energy_sale%ROWTYPE;

BEGIN 
 v_log_result := log_activity('PROCEDURE','TMP_OPEN_BB_ISSUE','START','','','','','', sysdate,'');
 
      for bb in (select * from t_banking_balance where month='06' and M_COMPANY_ID in (select M_COMPANY_ID from DUMMY_BANKING))
      loop
        select count(*) into v_gen_stmt_count from t_gen_stmt where stmt_month='06' and stmt_year='2019'  and m_company_id=bb.m_company_id;  
        if v_gen_stmt_count=1 then
            
            for gen_stmt in (select * from t_gen_stmt where stmt_month='06' and stmt_year='2019'  and m_company_id=bb.m_company_id )
              loop
              
               for gen_stmt_slot in (select * from t_gen_stmt_slot where t_gen_stmt_id=gen_stmt.id )
               loop
                
                    if gen_stmt_slot.SLOT_CODE = 'C1' then
                    
                        update t_gen_stmt_slot set BANKED_BALANCE=bb.C1,REMARKS='05-BANKING-ISSUE' where id=gen_stmt_slot.id;
                        
                        select count(*) into v_es_count from t_energy_sale where t_gen_stmt_id=gen_stmt.id and  month='06' and year='2019';
                        if v_es_count=1 then
                            update t_energy_sale set avail_bc1=bb.c1,IMPORT_REMARKS='05-BANKING-ISSUE' where t_gen_stmt_id=gen_stmt.id and  month='06' and year='2019'; 
                            
                        end if;
                     v_log_result := log_activity('PROCEDURE','TMP_OPEN_BB_ISSUE','bb.M_COMPANY_ID'||bb.M_COMPANY_ID,'gen_stmt.id'||gen_stmt.id ,'','','','', sysdate,'');
                    end if;
                    
                    if gen_stmt_slot.SLOT_CODE = 'C2' then
                    
                        update t_gen_stmt_slot set BANKED_BALANCE=bb.C2,REMARKS='05-BANKING-ISSUE' where id=gen_stmt_slot.id;
                        
                        select count(*) into v_es_count from t_energy_sale where t_gen_stmt_id=gen_stmt.id and  month='06' and year='2019';
                        if v_es_count=1 then
                            update t_energy_sale set avail_bc2=bb.c2,IMPORT_REMARKS='05-BANKING-ISSUE'  where t_gen_stmt_id=gen_stmt.id and  month='06' and year='2019'; 
                        end if;
                    
                    end if;
                    
                     
                    if gen_stmt_slot.SLOT_CODE = 'C3' then
                    
                        update t_gen_stmt_slot set BANKED_BALANCE=bb.C3,REMARKS='05-BANKING-ISSUE' where id=gen_stmt_slot.id;
                        
                        select count(*) into v_es_count from t_energy_sale where t_gen_stmt_id=gen_stmt.id and  month='06' and year='2019';
                        if v_es_count=1 then
                            update t_energy_sale set avail_bc3=bb.c3,IMPORT_REMARKS='05-BANKING-ISSUE'  where t_gen_stmt_id=gen_stmt.id and  month='06' and year='2019'; 
                        end if;
                    
                    end if;
                    
                    
                     
                    if gen_stmt_slot.SLOT_CODE = 'C4' then
                    
                        update t_gen_stmt_slot set BANKED_BALANCE=bb.C4,REMARKS='05-BANKING-ISSUE' where id=gen_stmt_slot.id;
                        
                        select count(*) into v_es_count from t_energy_sale where t_gen_stmt_id=gen_stmt.id and  month='06' and year='2019';
                        if v_es_count=1 then
                            update t_energy_sale set avail_bc4=bb.c4,IMPORT_REMARKS='05-BANKING-ISSUE'  where t_gen_stmt_id=gen_stmt.id and  month='06' and year='2019'; 
                        end if;
                    
                    end if;
                    
                     
                    if gen_stmt_slot.SLOT_CODE = 'C5' then
                    
                        update t_gen_stmt_slot set BANKED_BALANCE=bb.C5,REMARKS='05-BANKING-ISSUE' where id=gen_stmt_slot.id;
                        
                        select count(*) into v_es_count from t_energy_sale where t_gen_stmt_id=gen_stmt.id and  month='06' and year='2019';
                        if v_es_count=1 then
                            update t_energy_sale set avail_bc5=bb.c5,IMPORT_REMARKS='05-BANKING-ISSUE'  where t_gen_stmt_id=gen_stmt.id and  month='06' and year='2019'; 
                        end if;
                    
                    end if;
                    
                
               end loop;
                
              end loop;
            
        end if;
      end loop;

 
 
 




 v_log_result := log_activity('PROCEDURE','TMP_OPEN_BB_ISSUE','End',o_result_desc,'','', sysdate,'');
END TMP_OPEN_BB_ISSUE;