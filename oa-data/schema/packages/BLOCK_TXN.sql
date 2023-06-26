CREATE OR REPLACE PACKAGE BLOCK_TXN AS 

  procedure SHOULD_BLOCK_TXN (i_service_number in varchar2,
                                    i_reading_month in varchar2 ,
                                    i_reading_year in varchar2 ,
                                    t_txn_type in varchar2 default 'ENERGY-ALLOCATION',
                                    o_result_code out varchar2,o_result_desc out varchar2);
  procedure CHK_ADJ_AND_BLOCK (i_batch_key in varchar2, 
                                    i_reading_month in varchar2 ,
                                    i_reading_year in varchar2 ,
                                    o_result_code out varchar2,o_result_desc out varchar2);
  procedure BLOCK_TXN (i_reference in varchar2,
                                    i_service_number in varchar2,
                                    i_reading_month in varchar2 ,
                                    i_reading_year in varchar2 ,
                                    t_txn_type in varchar2 default 'ENERGY-ALLOCATION',
                                    o_result_code out varchar2,o_result_desc out varchar2);
  procedure UNBLOCK_TXN (i_service_number in varchar2,
                                    i_reading_month in varchar2 ,
                                    i_reading_year in varchar2 ,
                                    t_txn_type in varchar2 default 'ENERGY-ALLOCATION',
                                    o_result_code out varchar2,o_result_desc out varchar2); 
  


END BLOCK_TXN;



CREATE OR REPLACE PACKAGE BODY BLOCK_TXN AS

  procedure SHOULD_BLOCK_TXN (i_service_number in varchar2,
                                    i_reading_month in varchar2 ,
                                    i_reading_year in varchar2 ,
                                    t_txn_type in varchar2 default 'ENERGY-ALLOCATION',
                                    o_result_code out varchar2,o_result_desc out varchar2) IS

    V_BLOCKED_COUNT number:=0;
    v_process varchar2(50):='BLOCK_TXN.SHOULD_BLOCK_TXN';
    v_process_type varchar2(500):='PACKAGE';
    v_stage varchar2(500):='';
    v_step varchar2(500):='';
    v_message varchar2(500):='';
    v_created_by varchar2(100):= v_process;
    v_log_result  varchar2(500);
    v_reason varchar2(200):='';                                          
  BEGIN
    BEGIN  -- exception handling start
    
        -- default values
        o_result_code := 'FALSE';
        o_result_desc := 'No Block records for given service-number, month and year';
    
        v_stage:='INIT'; v_message:='START';
        v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,sysdate, '');
        -- input validation
        
        IF(i_service_number is null or i_service_number = ''  )  OR (i_reading_month is null or i_reading_month = ''  ) OR (i_reading_year is null or i_reading_year = ''  )  then
            Raise_Application_Error (-20343, 'i_service_number,i_reading_month,i_reading_year are mandatory  ');
        end if;
         
         
         v_stage:='PROCESS'; v_message:='checking block-txn table';
         -- block the delete, if the service is blocked from doing transactions
         select count(*) into V_BLOCKED_COUNT from t_block_txn where m_comp_service_number = i_service_number and month=i_reading_month and year =i_reading_year and status='block';
         if(V_BLOCKED_COUNT > 0) THEN
             v_message := t_txn_type||' should be blocked!';
             o_result_code := 'TRUE';
             o_result_desc := v_message;
             v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,sysdate,''); 
         END IF;        
    exception
    when others then
        v_stage:='EXCEPTION';
        v_message := SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 200);
        o_result_code := 'EXCEPTION';
             o_result_desc := v_message;
        v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,sysdate,'');
    END;
    <<THE_END>>
    v_stage:='END'; v_step:='END';
    v_message:=o_result_code;
    v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,sysdate,'');    
  END SHOULD_BLOCK_TXN;

  procedure BLOCK_TXN (i_reference in varchar2,
                                    i_service_number in varchar2,
                                    i_reading_month in varchar2 ,
                                    i_reading_year in varchar2 ,
                                    t_txn_type in varchar2 default 'ENERGY-ALLOCATION',
                                    o_result_code out varchar2,o_result_desc out varchar2) IS

    V_BLOCK_TXN_COUNT number := 0;
    v_org_id varchar2(100) := '';
    v_service_id varchar2(100) := '';
    V_REMARKS  varchar2(200) := '';
    v_process varchar2(50):='BLOCK_TXN.CHECK_AND_BLOCK_TXN';
    v_process_type varchar2(500):='PACKAGE';
    v_stage varchar2(500):='';
    v_step varchar2(500):='';
    v_message varchar2(500):='';
    v_created_by varchar2(100):=v_process;
    v_log_result  varchar2(500);
    v_reason varchar2(200):='';                                          
  BEGIN
    BEGIN  -- exception handling start
    
        o_result_code := '';
    
        v_stage:='INIT'; v_message:='START';
        v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,'', '');
        -- input validation
        
        IF(i_service_number is null or i_service_number = ''  )  OR (i_reading_month is null or i_reading_month = ''  ) OR (i_reading_year is null or i_reading_year = ''  )  then
            Raise_Application_Error (-20343, 'i_service_number,i_reading_month,i_reading_year are mandatory  ');
        end if;
         
        v_stage:='PROCESS'; v_step:='fetching service info';
        select m_org_id,id into v_org_id,v_service_id from v_company_service where m_comp_serv_number = i_service_number;
         
         
         v_stage:='PROCESS'; v_step:='checking block-txn table';
         -- block the delete, if the service is blocked from doing transactions
         select count(*) into V_BLOCK_TXN_COUNT from t_block_txn where m_comp_service_number = i_service_number and month=i_reading_month and year =i_reading_year;
         
         v_message := i_reference|| '-' || t_txn_type||' should be blocked!';
         V_REMARKS := v_message;
         
         -- if there is entry, do update else insert into t_block_txn table
         if(V_BLOCK_TXN_COUNT > 0) THEN
             -- update block-status to block
             update T_BLOCK_TXN set STATUS='block', modified_date= sysdate,modified_by=v_created_by,REMARKS=V_REMARKS where M_COMP_SERVICE_NUMBER= i_service_number and year = i_reading_year and month= i_reading_month ;
         else
            -- insert a new block entry            
            Insert into T_BLOCK_TXN (ID,M_ORG_ID,M_COMP_SERVICE_ID,M_COMP_SERVICE_NUMBER,YEAR,MONTH,STATUS,CREATED_DT,CREATED_BY,REMARKS)
                values (T_BLOCK_TXN_SEQ.nextval,v_org_id,v_service_id,i_service_number,i_reading_year,i_reading_month,'block',sysdate,v_created_by,V_REMARKS);
            null;
         END IF;   
         o_result_code := 'SUCCESS';  
         commit;
         v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,sysdate,'');      
        
    exception
    when others then
        v_stage:='EXCEPTION';
        v_message := v_step  || ' - ' || SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 200);
        o_result_code := 'FAILURE';
        o_result_code := v_message;
        v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,sysdate,'');
    END;
    <<THE_END>>
    v_stage:='END'; v_step:='END';
    v_message:=o_result_code;
    v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,sysdate,'');    
  END BLOCK_TXN;

  procedure CHK_ADJ_AND_BLOCK (i_batch_key in varchar2, 
                                    i_reading_month in varchar2 ,
                                    i_reading_year in varchar2 ,
                                    o_result_code out varchar2,o_result_desc out varchar2) IS
    v_allot_order_count number :=0;
    v_process varchar2(50):='BLOCK_TXN.CHK_ADJ_AND_BLOCK';
    v_process_type varchar2(500):='PACKAGE';
    v_stage varchar2(500):='';
    v_step varchar2(500):='';
    v_message varchar2(500):='';
    v_created_by varchar2(100):= v_process;
    v_log_result  varchar2(500);
    v_reason varchar2(200):='';                                          
  BEGIN
    BEGIN  -- exception handling start
    
        o_result_code := 'FAILURE';
    
        v_stage:='INIT'; v_message:='START';
        v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_batch_key,'', '');
        -- input validation
        
        IF(i_batch_key is null or i_batch_key = '' ) OR (i_reading_month is null or i_reading_month = ''  ) OR (i_reading_year is null or i_reading_year = ''  )   then
            Raise_Application_Error (-20343, 'i_batch_key,i_reading_month,i_reading_year are mandatory  ');
        end if;
        
        for surplus in (select suplr_code from f_energy_adjustmet where batch_key = i_batch_key and reading_mnth=i_reading_month and reading_yr = i_reading_year)
        loop 
            BEGIN
                select count(*) into v_allot_order_count from f_energy_sale_order where  d_sell_comp_serv_number=surplus.suplr_code and month=i_reading_month and year = i_reading_year ;
                if(v_allot_order_count = 0) then
                    BLOCK_TXN(i_batch_key, surplus.suplr_code ,i_reading_month,i_reading_year,'ENERGY-ALLOCATION', o_result_code,o_result_desc);
                end if;
            exception
            when others then
                v_stage:='EXCEPTION';
                v_message := SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 200);
                v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_batch_key,sysdate,'');
            END;
        end loop;
         o_result_code := 'SUCCESS';
    exception
    when others then
        v_stage:='EXCEPTION';
        v_message := SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 200);
        v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_batch_key,sysdate,'');
    END;
    <<THE_END>>
    v_stage:='END'; v_step:='END';
    v_message:=o_result_code;
    v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_batch_key,sysdate,'');    
  END CHK_ADJ_AND_BLOCK;
  
  procedure UNBLOCK_TXN (i_service_number in varchar2,
                                    i_reading_month in varchar2 ,
                                    i_reading_year in varchar2 ,
                                    t_txn_type in varchar2 default 'ENERGY-ALLOCATION',
                                    o_result_code out varchar2,o_result_desc out varchar2) IS
    V_BLOCK_TXN_COUNT number := 0;
    v_org_id varchar2(100) := '';
    v_service_id varchar2(100) := '';
    V_REMARKS  varchar2(200) := '';
    v_process varchar2(50):='BLOCK_TXN.UNBLOCK_TXN';
    v_process_type varchar2(500):='PACKAGE';
    v_stage varchar2(500):='';
    v_step varchar2(500):='';
    v_message varchar2(500):='';
    v_created_by varchar2(100):= v_process;
    v_log_result  varchar2(500);
    v_reason varchar2(200):='';                                          
  BEGIN
    BEGIN  -- exception handling start
    
        o_result_code := 'SUCCESS';
    
        v_stage:='INIT'; v_message:='START';
        v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,'', '');
        -- input validation
        
        IF(i_service_number is null or i_service_number = ''  )  OR (i_reading_month is null or i_reading_month = ''  ) OR (i_reading_year is null or i_reading_year = ''  )  then
            Raise_Application_Error (-20343, 'i_service_number,i_reading_month,i_reading_year are mandatory  ');
        end if;
         
        v_stage:='PROCESS'; v_step:='fetching service info';
        select m_org_id,id into v_org_id,v_service_id from v_company_service where m_comp_serv_number = i_service_number;
         
         
         v_stage:='PROCESS'; v_step:='checking block-txn table';
         -- block the delete, if the service is blocked from doing transactions
         select count(*) into V_BLOCK_TXN_COUNT from t_block_txn where m_comp_service_number = i_service_number and month=i_reading_month and year =i_reading_year;
         
         v_message := t_txn_type||' should be blocked!';
         V_REMARKS := v_message;
         
         -- if there is entry, do update else insert into t_block_txn table
         if(V_BLOCK_TXN_COUNT > 0) THEN
             -- update block-status to block
              update T_BLOCK_TXN set STATUS='unblock', modified_date= sysdate,modified_by=v_created_by,REMARKS=V_REMARKS where M_COMP_SERVICE_NUMBER= i_service_number and year = i_reading_year and month= i_reading_month ;
         else
            -- insert a new block entry            
            Insert into T_BLOCK_TXN (ID,M_ORG_ID,M_COMP_SERVICE_ID,M_COMP_SERVICE_NUMBER,YEAR,MONTH,STATUS,CREATED_DT,CREATED_BY,REMARKS)
                values (T_BLOCK_TXN_SEQ.nextval,v_org_id,v_service_id,i_service_number,i_reading_year,i_reading_month,'unblock',sysdate,v_created_by,V_REMARKS);
            null;
         END IF;   
         o_result_code := 'TRUE';
         v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,sysdate,'');      
         commit;
        
    exception
    when others then
        v_stage:='EXCEPTION';
        v_message := v_step  || ' - ' || SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 200);
        o_result_code := 'FAILURE';
        o_result_code := v_message;
        v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,sysdate,'');
    END;
    <<THE_END>>
    v_stage:='END'; v_step:='END';
    v_message:=o_result_code;
    v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,sysdate,'');    
  END UNBLOCK_TXN;

END BLOCK_TXN;
