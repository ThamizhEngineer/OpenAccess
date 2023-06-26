CREATE OR REPLACE procedure OPENACCESS.DELETE_TXN_PREV (i_remarks in varchar2,i_service_number IN VARCHAR2,i_reading_month in VARCHAR2,i_reading_year in VARCHAR2,
                              i_del_ledger in CHAR default 'N',
                              i_del_es in CHAR default 'N',
                              i_del_gs in CHAR default 'N',
                              i_del_mr in CHAR default 'N', o_result_code out varchar2, o_result_desc out varchar2)
    is

    v_del_ledger CHAR(1) := 'N';
    v_del_es CHAR(1) := 'N';
    v_del_gs CHAR(1) := 'N';
    v_del_mr CHAR(1) := 'N';    
    v_created_By  varchar2(50):= 'admin';
    v_status varchar2(50);
    v_exception_code  NUMBER;
    v_exception_msg  VARCHAR2(200);
    v_log_result varchar(300):='SUCCESS';
    v_deleted_ledger char;
    v_deleted_es char;
    v_deleted_gs char;
   v_billing_month varchar2(20);
  v_billing_year varchar2(20);
  v_deleted_mr char;
    v_tesid varchar2(50);
    v_service_id varchar2(50);
    v_company_id varchar2(50);
    v_master_count number;
    v_banking_balance t_banking_balance%ROWTYPE;
    v_banking_count number:=0;
    V_BLOCKED_COUNT number:=0;
    V_ADJ_COUNT number:=0;
    v_delete_txn delete_txn_log%ROWTYPE;
    v_org_id varchar2(50);
BEGIN
    BEGIN
      v_log_result := log_activity('FUNCTION','DELETE_TXN_BY_SERVICE_NO','START','Start - i_service_number-'||i_service_number||',month - '||i_reading_month||',year - '||i_reading_year,'','', sysdate,i_remarks);

      
     
     
      if(i_del_ledger is not null and i_del_ledger='Y') then v_del_ledger := 'Y'; end if;
      if(i_del_es is not null and i_del_es='Y') then v_del_es := 'Y'; end if;
      if(i_del_gs is not null and i_del_gs='Y') then v_del_gs := 'Y'; end if;
      if(i_del_mr is not null and i_del_mr='Y') then v_del_mr := 'Y'; end if;
      
      -- input validation
     
         select to_char(extract(YEAR from trunc(add_months(trunc(sysdate,'MM'),-1)))) YEAR INTO v_billing_year 
        from dual; 
       
       select to_char(extract(month from trunc(add_months(trunc(sysdate,'MM'),-1)))) month INTO v_billing_month  
        from dual; 
     
     
     
      if(v_del_mr='Y'  and (v_del_ledger = 'N' or v_del_es = 'N' or v_del_gs = 'N')) then
        Raise_Application_Error (-20343, 'Input data issue - Please set the params(v_del_ledger,v_del_es,v_del_gs) to Y ');
      end if;

      if(v_del_gs='Y'  and (v_del_ledger = 'N' or v_del_es = 'N' )) then
        Raise_Application_Error (-20343, 'Input data issue - Please set the params(v_del_ledger,v_del_es) to Y  ');
      end if;

      if(v_del_es='Y'  and (v_del_ledger = 'N' )) then
        Raise_Application_Error (-20343, 'Input data issue - Please set the params(v_del_ledger) to Y  ');
      end if;

      if(i_service_number is null or i_service_number = '' ) then
        Raise_Application_Error (-20343, 'i_service_number is mandatory  ');
      end if;

      if(i_reading_month is null or i_reading_month = '' or i_reading_year is null or i_reading_year = '' ) then
        Raise_Application_Error (-20343, 'i_reading_month, i_reading_year are madatory  ');
      end if;
     
     

      -- fetch master keys
      select count(id) into v_master_count from V_COMPANY_SERVICE where "number" =i_service_number ;


      if(v_master_count = 0 ) then
        Raise_Application_Error (-20343, 'The input i_service_number '||i_service_number||' is not available in the system ');
      ELSIF(v_master_count > 1 ) then
        Raise_Application_Error (-20343, 'The input i_service_number '||i_service_number||' is duplicated in the system ');
      end if;

      select id,m_company_id  into v_service_id,v_company_id  from V_COMPANY_SERVICE where "number" =i_service_number ;

      if(v_del_es='Y'  or v_del_ledger = 'Y' ) then 
         BLOCK_TXN.SHOULD_BLOCK_TXN( i_service_number ,i_reading_month,i_reading_year,'ENERGY-ALLOCATION', o_result_code,o_result_desc);         
         if(o_result_code='TRUE') THEN
             v_log_result := log_activity('PROCEDURE','DELETE_TXN.PROCESS_INT_DELETE_TXN','Issue -  i_service_number-'||i_service_number||',month - '||i_reading_month||',year - '||i_reading_year ,'FAILURE', 'TRANSACTION is blocked','admin', sysdate,i_remarks);
             Raise_Application_Error (-20343, 'Transaction is blocked! ');
         END IF;
        -- select count(id) into V_ADJ_COUNT from f_energy_adjustmet where suplr_code=i_service_number and reading_mnth=i_reading_month and reading_yr = i_reading_year;
         --if(V_ADJ_COUNT>0) THEN
           --  v_log_result := log_activity('PROCEDURE','DELETE_TXN.PROCESS_INT_DELETE_TXN','Issue -  i_service_number-'||i_service_number||',month - '||i_reading_month||',year - '||i_reading_year ,'FAILURE', 'DELETION blocked as adjustment records exists for -'||i_service_number,'admin', sysdate,i_remarks);
           --  Raise_Application_Error (-20343,  'DELETION blocked as adjustment records exists for -'||i_service_number);
      --   END IF;
      end if;


      if(v_del_ledger='Y') then  
        --DELETE from f_energy_ledger where m_company_service_id = v_service_id and month=i_reading_month and year =i_reading_year;
        for i in (select id from f_energy_sale_order where (seller_comp_serv_id = v_service_id or d_sell_comp_serv_number= i_service_number) and month=i_reading_month and year =i_reading_year)
        LOOP
        
          
          delete from F_ENERGY_CHARGES where f_energy_sale_order_id = i.id;
          DELETE from f_energy_ledger where f_energy_sale_order_id = i.id;
          delete from F_ENERGY_SALE_ORDER_LINES where f_energy_sale_order_id = i.id;
          select t_energy_sale_id into v_tesid  from f_energy_sale_order where id=i.id and rownum=1;
          delete from t_job_hdr where t_energy_sale_id=v_tesid;
          delete from f_energy_sale_order where id = i.id;
          --Changes may be needed


          update t_energy_sale set status_code='CREATED' where ID=v_tesid;
          --
          
          excess_units.reset_balances_to_open_bal(i_service_number,i_reading_month ,i_reading_year,o_result_code,o_result_desc );
        --  select count(*) into v_banking_count from T_BANKING_BALANCE where m_company_id=v_company_id and month=i_reading_month and year =i_reading_year;
       --   if v_banking_count = 1 then
--            excess_units.reset_balances_to_open_bal( i_service_number ,i_reading_month ,i_reading_year, o_result , o_reason );
        --    select * into v_banking_balance from T_BANKING_BALANCE where m_company_id=v_company_id and month=i_reading_month and year =i_reading_year;
         --   update T_BANKING_BALANCE set  modified_dt=sysdate,modified_by='DELETE-TXN',remarks='DELETE-TXN',CURR_C1=v_banking_balance.c1,CURR_C2=v_banking_balance.c2,CURR_C3=v_banking_balance.c3,CURR_C4=v_banking_balance.c4,CURR_C5=v_banking_balance.c5 where m_company_id=v_company_id and month=i_reading_month and year =i_reading_year;
         -- end if;

        end loop;
        v_deleted_ledger := 'Y';
        v_log_result := log_activity('FUNCTION','DELETE_TXN_BY_SERVICE_NO','Deleted Ledger, Energy Sale Order(Allotment Order)',i_service_number,'','', sysdate,i_remarks);
      end if;

      if(v_del_es='Y') then  
        for i in (select id from T_ENERGY_SALE where seller_comp_serv_id = v_service_id and month=i_reading_month and year =i_reading_year)
        loop
          for j in (select id FROM T_ES_MULTIADD_HEADER WHERE T_ES_ID = i.id)
          LOOP
          
            INSERT INTO T_ES_MULTIADD_LINE_HIS SELECT T_ES_MULTIADD_LINE.*, 'CREATED BY DELETE TRANSACTION' HIS_REMARKS,
            sysdate HIS_CREATED_DATE,DEL_HIS_ID_SEQ.NEXTVAL HIS_ID FROM T_ES_MULTIADD_LINE 
            where T_ES_MULTIADD_HEADER_ID = j.id;
          dbms_output.put_line('CAME IN');
          delete from T_ES_MULTIADD_LINE where T_ES_MULTIADD_HEADER_ID = j.id;
         
          end loop;
         
            INSERT INTO T_ES_MULTIADD_HEADER_HIS SELECT DEL_HIS_ID_SEQ.NEXTVAL HIS_ID,sysdate HIS_CREATED_DATE,
           'CREATED BY DELETE TRANSACTION' HIS_REMARKS, T_ES_MULTIADD_HEADER.* FROM T_ES_MULTIADD_HEADER 
            WHERE T_ES_ID=i.id;
          delete from T_ES_MULTIADD_HEADER WHERE T_ES_ID=i.id;
         
           INSERT INTO T_ES_CHARGE_HIS SELECT DEL_HIS_ID_SEQ.NEXTVAL HIS_ID,sysdate HIS_CREATED_DATE,
           'CREATED BY DELETE TRANSACTION' HIS_REMARKS, T_ES_CHARGE.* FROM T_ES_CHARGE 
            where t_energy_sale_id = i.id;
         delete from T_ES_CHARGE where t_energy_sale_id = i.id;
        
        INSERT INTO T_ES_USAGE_DETAIL_HIS SELECT DEL_HIS_ID_SEQ.NEXTVAL HIS_ID,sysdate HIS_CREATED_DATE,
           'CREATED BY DELETE TRANSACTION' HIS_REMARKS, T_ES_USAGE_DETAIL.* FROM T_ES_USAGE_DETAIL 
             where t_energy_sale_id = i.id;
         delete from T_ES_USAGE_DETAIL where t_energy_sale_id = i.id;
        
        INSERT INTO T_ES_USAGE_SUMMARY_HIS SELECT DEL_HIS_ID_SEQ.NEXTVAL HIS_ID,sysdate HIS_CREATED_DATE,
           'CREATED BY DELETE TRANSACTION' HIS_REMARKS, T_ES_USAGE_SUMMARY.* FROM T_ES_USAGE_SUMMARY 
             where t_energy_sale_id = i.id;
          delete from T_ES_USAGE_SUMMARY where t_energy_sale_id = i.id;
         
          INSERT INTO T_ENERGY_SALE_HIS SELECT T_ENERGY_SALE.*,
         'CREATED BY DELETE TRANSACTION' HIS_REMARKS,sysdate HIS_CREATED_DATE,DEL_HIS_ID_SEQ.NEXTVAL HIS_ID
         FROM T_ENERGY_SALE 
           where id = i.id;
          delete from T_ENERGY_SALE where id = i.id;
         dbms_output.put_line('CAME IN');
        
          
          update T_GEN_STMT set STATUS_CODE='CREATED' where M_COMPANY_SERVICE_ID=v_service_id;
          
         excess_units.reset_balances_to_open_bal(i_service_number,i_reading_month ,i_reading_year,o_result_code,o_result_desc );
          --select count(*) into v_banking_count from T_BANKING_BALANCE where m_company_id=v_company_id and month=i_reading_month and year =i_reading_year;
          --if v_banking_count = 1 then
            -- excess_units.delete_balances( i_service_number ,i_reading_month ,i_reading_year, o_result , o_reason );
--            select * into v_banking_balance from T_BANKING_BALANCE where m_company_id=v_company_id and month=i_reading_month and year =i_reading_year;
  --          update T_BANKING_BALANCE set modified_dt=sysdate,modified_by='DELETE-TXN',remarks='DELETE-TXN',CURR_C1=v_banking_balance.c1,CURR_C2=v_banking_balance.c2,CURR_C3=v_banking_balance.c3,CURR_C4=v_banking_balance.c4,CURR_C5=v_banking_balance.c5 where m_company_id=v_company_id and month=i_reading_month and year =i_reading_year;
    --      end if;


        end loop;
        v_deleted_es := 'Y';
        v_log_result := log_activity('FUNCTION','DELETE_TXN_BY_SERVICE_NO','Deleted Energy Sale(Energy Allotment)',i_service_number,'','', sysdate,i_remarks);
      end if;

      if(v_del_gs='Y'  ) then  
        for i in (select id from T_GEN_STMT where M_COMPANY_SERVICE_ID = v_service_id and stmt_month=i_reading_month and stmt_year =i_reading_year)
        LOOP
        
          INSERT INTO T_GEN_STMT_CHARGE_HIS SELECT DEL_HIS_ID_SEQ.NEXTVAL HIS_ID,sysdate HIS_CREATED_DATE,
           'CREATED BY DELETE TRANSACTION' HIS_REMARKS, T_GEN_STMT_CHARGE.* FROM T_GEN_STMT_CHARGE 
             where  t_gen_stmt_id = i.id;
            delete from T_GEN_STMT_CHARGE where t_gen_stmt_id = i.id;
           
           INSERT INTO T_GEN_STMT_SLOT_HIS SELECT DEL_HIS_ID_SEQ.NEXTVAL HIS_ID,sysdate HIS_CREATED_DATE,
           'CREATED BY DELETE TRANSACTION' HIS_REMARKS, T_GEN_STMT_SLOT.* FROM T_GEN_STMT_SLOT 
            where  t_gen_stmt_id = i.id;
          delete from T_GEN_STMT_SLOT where t_gen_stmt_id = i.id;
         
          INSERT INTO T_GEN_STMT_HIS SELECT DEL_HIS_ID_SEQ.NEXTVAL HIS_ID,sysdate HIS_CREATED_DATE,
           'CREATED BY DELETE TRANSACTION' HIS_REMARKS, T_GEN_STMT.* FROM T_GEN_STMT 
            where  id = i.id;
          delete from T_GEN_STMT where id = i.id;
          update t_meter_reading_hdr set m_gen_stmt_id = null , GS_BATCH_ID = null where m_gen_stmt_id = i.id;
          excess_units.delete_balances(i_service_number,i_reading_month ,i_reading_year,o_result_code,o_result_desc );
            --select count(*) into v_banking_count from T_BANKING_BALANCE where m_company_id=v_company_id and month=i_reading_month and year =i_reading_year;
          --if v_banking_count = 1 then
--            select * into v_banking_balance from T_BANKING_BALANCE where m_company_id=v_company_id and month=i_reading_month and year =i_reading_year;
--            update T_BANKING_BALANCE set modified_dt=sysdate,modified_by='DELETE-TXN',remarks='DELETE-TXN',CURR_C1=v_banking_balance.c1,CURR_C2=v_banking_balance.c2,CURR_C3=v_banking_balance.c3,CURR_C4=v_banking_balance.c4,CURR_C5=v_banking_balance.c5 where m_company_id=v_company_id and month=i_reading_month and year =i_reading_year;
          --DELETE FROM T_BANKING_BALANCE where m_company_id=v_company_id and month=i_reading_month and year =i_reading_year;
          --end if;
        end loop;
        v_deleted_gs := 'Y';
        v_log_result := log_activity('FUNCTION','DELETE_TXN_BY_SERVICE_NO','Deleted Generation Statement',i_service_number,'','', sysdate,i_remarks);
      end if;

      if(v_del_mr='Y' ) then  
        for i in (select mr.id from t_meter_reading_hdr mr join M_COMPANY_meter m on m.id = mr.M_COMPANY_METER_ID where m.M_COMPANY_SERVICE_ID = v_service_id and mr.reading_month=i_reading_month and mr.reading_year =i_reading_year)
        LOOP
        
          INSERT INTO t_meter_reading_slot_his SELECT DEL_HIS_ID_SEQ.NEXTVAL HIS_ID,sysdate HIS_CREATED_DATE,
           'CREATED BY DELETE TRANSACTION' HIS_REMARKS, t_meter_reading_slot.* FROM t_meter_reading_slot 
            where t_meter_reading_hdr_id = i.id;
            delete from t_meter_reading_slot where t_meter_reading_hdr_id = i.id;
           
           INSERT INTO t_meter_reading_hdr_his SELECT DEL_HIS_ID_SEQ.NEXTVAL HIS_ID,sysdate HIS_CREATED_DATE,
           'CREATED BY DELETE TRANSACTION' HIS_REMARKS, t_meter_reading_hdr.* FROM t_meter_reading_hdr 
            where id = i.id;
            delete from t_meter_reading_hdr where id = i.id;  
           
        end loop;
        v_deleted_mr := 'Y';
        v_log_result := log_activity('FUNCTION','DELETE_TXN_BY_SERVICE_NO','Deleted Meter Reading',i_service_number,'','', sysdate,i_remarks);
      end if;

         --INSERT STARTS HERE FOR delete_txn_log TABLE
         SELECT M_ORG_ID INTO v_org_id FROM V_COMPANY_SERVICE where "number" =i_service_number;
         v_delete_txn.ID :=delete_txn_log_seq.NEXTVAL;
         v_delete_txn.SERVICE_NO :=i_service_number;
         v_delete_txn.MONTH :=i_reading_month;
         v_delete_txn.YEAR :=i_reading_year;
         v_delete_txn.remarks :=i_remarks;
         v_delete_txn.CREATED_DATE :=sysdate;
         v_delete_txn.CREATED_BY :=v_created_By;
         v_delete_txn.M_ORG_ID :=v_org_id;
         if (v_del_mr='Y') then
            v_delete_txn.READING :='Y';
            else
            v_delete_txn.READING :='N';
        end if;
        if(v_del_gs='Y') then
            v_delete_txn.STATEMENT:='Y';
            else
            v_delete_txn.STATEMENT:='N';
        end if;
        if(v_del_es='Y') then
            v_delete_txn.ALLOTMENT:='Y';
            else
            v_delete_txn.ALLOTMENT:='N';
        end if;
        if(v_del_ledger='Y') then
            v_delete_txn.LEDGER:='Y';
            else
            v_delete_txn.LEDGER:='N';
        end if;
        insert into delete_txn_log values v_delete_txn;
        COMMIT;
         ---INSERT ENDS HERE FOR delete_txn_log TABLE
      o_result_code :='SUCCESS';
      update delete_txn_log set result=o_result_code where service_no=i_service_number and remarks=i_remarks and MONTH=i_reading_month and YEAR=i_reading_year;
    exception
        when others then
          v_exception_code := SQLCODE;
          v_exception_msg := SUBSTR(SQLERRM, 1, 200);
          o_result_code := 'FAILURE';
          o_result_desc := v_exception_code || ' - ' || v_exception_msg;
          -- dbms_output.put_line(v_reason);
          v_log_result := log_activity('FUNCTION','DELETE_TXN_BY_SERVICE_NO','EXCEPTION',o_result_desc,'','', sysdate,i_remarks);
    END;
    update INT_DELETE_TXN set PROCESSED='Y',DELETE_REMARKS=o_result_code || ' - ' || o_result_desc,deleted_ledger=v_deleted_ledger, deleted_es=v_deleted_es,deleted_gs=v_deleted_gs,deleted_mr=v_deleted_mr where remarks = i_remarks and GEN_SERVICE_NUMBER = i_service_number;
    commit;
    v_log_result := log_activity('FUNCTION','DELETE_TXN_BY_SERVICE_NO','END',i_service_number,o_result_code || ' - ' || o_result_desc,'', sysdate,i_remarks);

 
END DELETE_TXN_PREV;

