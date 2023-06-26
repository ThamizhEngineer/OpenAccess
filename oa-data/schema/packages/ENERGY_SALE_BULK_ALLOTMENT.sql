CREATE OR REPLACE PACKAGE energy_sale_bulk_allotment AS 

  /* TODO enter package declarations (types, exceptions, methods etc) here */ 
  procedure clear(i_energy_sale_id in varchar2, i_overwrite in char default 'N', o_result_code out varchar2, o_result_desc out varchar2);
  procedure init(i_energy_sale_id in varchar2, o_result_code out varchar2, o_result_desc out varchar2, o_es_multi_add_header_id out varchar2);
  procedure validate(i_energy_sale_id in varchar2,i_es_multi_add_header_id in varchar2, o_result_code out varchar2, o_result_desc out varchar2);
  procedure import(i_energy_sale_id in varchar2,i_es_multi_add_header_id in varchar2,  o_result_code out varchar2, o_result_desc out varchar2);

END energy_sale_bulk_allotment;



CREATE OR REPLACE PACKAGE BODY ENERGY_SALE_BULK_ALLOTMENT AS

  procedure clear(i_energy_sale_id in varchar2, i_overwrite in char default 'N', o_result_code out varchar2, o_result_desc out varchar2) is
    v_overwrite char:='N';
    v_created_by varchar2(100):='admin';
    v_log_result  varchar2(50);
    v_reason varchar2(200):='';
    v_exception_code  NUMBER;
    v_exception_msg  VARCHAR2(200);  
    v_total_count number:=0;
    v_es_multiadd_hdr T_ES_MULTIADD_HEADER%ROWTYPE;
    v_es_multiadd_line T_ES_MULTIADD_LINE%ROWTYPE;
    v_es_multiadd_line_id number:=0;

  BEGIN
    BEGIN

      v_log_result := log_activity('PROCEDURE','ENERGY_SALE_BULK_ALLOTMENT.CLEAR','START','Start - '||i_energy_sale_id,'','', sysdate,i_energy_sale_id);

      if(I_OVERWRITE is not null and I_OVERWRITE='Y') then v_overwrite := 'Y'; end if; 

      o_result_code:='';o_result_desc:='';



      select count(*) into v_total_count from T_ES_MULTIADD_HEADER where T_ES_ID=i_energy_sale_id and IS_COMPLETED!='Y';
--      dbms_output.put_line(v_total_count);
      if(v_total_count>0) then
        if nvl(v_overwrite,'N')='N' then
            o_result_code := 'FAILURE';
            o_result_desc := 'Another Transaction is in progress,Please wait or run clear() with i_overwrite=Y';
            GOTO THE_END;
        else
         select * into v_es_multiadd_hdr from T_ES_MULTIADD_HEADER where T_ES_ID=i_energy_sale_id and IS_COMPLETED!='Y';
         update T_ES_MULTIADD_HEADER set T_ES_ID=i_energy_sale_id,is_completed='N',REMARKS='', error_message='' where id=v_es_multiadd_hdr.id;

        end if;

      end if;

     FOR line IN ( select * into v_es_multiadd_line from T_ES_MULTIADD_LINE where T_ES_MULTIADD_HEADER_ID=v_es_multiadd_hdr.id)
     LOOP
        v_es_multiadd_line_id:=NULL;
        update T_ES_MULTIADD_LINE set id=v_es_multiadd_line_id ,is_clean='N' ,import_remarks='Orphan records' where id=line.id;

     END LOOP;


      <<THE_END>>
      commit;
    exception
        when others then
          v_exception_code := SQLCODE;
          v_exception_msg := SUBSTR(SQLERRM, 1, 200);
          o_result_code := 'FAILURE';
          o_result_desc := v_exception_code || ' - ' || v_exception_msg;
          -- dbms_output.put_line(o_result_desc);
          v_log_result := log_activity('PROCEDURE','ENERGY_SALE_BULK_ALLOTMENT.CLEAR','EXCEPTION',o_result_desc,'','', sysdate,i_energy_sale_id);
    END;

    v_log_result := log_activity('PROCEDURE','ENERGY_SALE_BULK_ALLOTMENT.CLEAR','END','Cleared earlier transactions and related orphan records','','', sysdate,i_energy_sale_id);

  END clear;

  procedure init(i_energy_sale_id in varchar2, o_result_code out varchar2, o_result_desc out varchar2, o_es_multi_add_header_id out varchar2) AS
   v_created_by varchar2(100):='admin';
   v_log_result  varchar2(50);
   v_reason varchar2(200):='';
   v_exception_code  NUMBER;
   v_exception_msg  VARCHAR2(200);  
   v_es_multiadd_hdr T_ES_MULTIADD_HEADER%ROWTYPE;
   v_es T_ENERGY_SALE%ROWTYPE;
  BEGIN
    BEGIN
        -- TODO: Implementation required for procedure ENERGY_SALE_BULK_ALLOTMENT.init
        v_log_result := log_activity('PROCEDURE','ENERGY_SALE_BULK_ALLOTMENT.INIT','START','Start - '||i_energy_sale_id,'','', sysdate,i_energy_sale_id);

        select * into v_es from t_energy_sale es where es.id=i_energy_sale_id; 
        v_es_multiadd_hdr.T_ES_ID :=i_energy_sale_id;
        v_es_multiadd_hdr.T_GEN_STMT_ID := v_es.T_GEN_STMT_ID;
        v_es_multiadd_hdr.SELLER_COMP_SERV_ID := v_es.SELLER_COMP_SERV_ID;
        v_es_multiadd_hdr.FROM_DT:= v_es.FROM_DT;
        v_es_multiadd_hdr.TO_DT:= v_es.TO_DT;
        v_es_multiadd_hdr.id :=T_ES_MULTIADD_HEADER_SEQ.NEXTVAL;
        o_es_multi_add_header_id:= v_es_multiadd_hdr.id;
        insert into T_ES_MULTIADD_HEADER values v_es_multiadd_hdr;
        update T_ES_MULTIADD_LINE set T_ES_MULTIADD_HEADER_ID = v_es_multiadd_hdr.id where T_ES_MULTIADD_HEADER_ID IS NULL;

   commit;
    exception
        when others then
          v_exception_code := SQLCODE;
          v_exception_msg := SUBSTR(SQLERRM, 1, 200);
          o_result_code := 'FAILURE';
          o_result_desc := v_exception_code || ' - ' || v_exception_msg;
          -- dbms_output.put_line(o_result_desc);
          v_log_result := log_activity('PROCEDURE','ENERGY_SALE_BULK_ALLOTMENT.INIT','EXCEPTION',o_result_desc,'','', sysdate,i_energy_sale_id);
    END;

    v_log_result := log_activity('PROCEDURE','ENERGY_SALE_BULK_ALLOTMENT.INIT','END','Cleared earlier transactions and related orphan records - '||o_result_code,'','', sysdate,i_energy_sale_id);

  END init;

  procedure validate(i_energy_sale_id in varchar2, i_es_multi_add_header_id in varchar2,o_result_code out varchar2, o_result_desc out varchar2) AS
   v_created_by varchar2(100):='admin';
   v_log_result  varchar2(50);
   v_reason varchar2(200):='';
   v_exception_code  NUMBER;
   v_exception_msg  VARCHAR2(200);  
   v_es_multiadd_hdr T_ES_MULTIADD_HEADER%ROWTYPE;
   v_es_multiadd_line T_ES_MULTIADD_LINE%ROWTYPE;
   v_es T_ENERGY_SALE%ROWTYPE;
   v_fuel_group varchar2(50);
   v_seller_num varchar2(50);
   v_comp_serv v_company_service%ROWTYPE;
   v_tr M_TRADE_RELATIONSHIP%ROWTYPE;
   v_es_usage_sum_count number:=0;
   v_serv_no_count number:=0;
   v_traderel_count number:=0;
   v_is_clean_flag boolean;
   v_total_count number:=0;
   v_success_count number:=0;
   v_error_count number:=0;
  BEGIN
      BEGIN
    -- TODO: Implementation required for procedure ENERGY_SALE_BULK_ALLOTMENT.validate
        v_log_result := log_activity('PROCEDURE','ENERGY_SALE_BULK_ALLOTMENT.VALIDATE','START','Start - '||i_energy_sale_id,'','', i_es_multi_add_header_id,i_energy_sale_id);

        select * into v_es from t_energy_sale es where es.id=i_energy_sale_id; 
        select "number", fuel_group_name into v_seller_num, v_fuel_group from v_company_service serv where id = v_es.seller_comp_serv_id;
        select * into v_es_multiadd_hdr from T_ES_MULTIADD_HEADER es where es.id=i_es_multi_add_header_id; 
        update t_es_multiadd_line set is_clean='N', import_remarks='Consumer Service Number is empty' where T_ES_MULTIADD_HEADER_ID=i_es_multi_add_header_id and M_COMP_SERV_NUMBER is null;
        FOR line IN ( select * into v_es_multiadd_line from T_ES_MULTIADD_LINE where T_ES_MULTIADD_HEADER_ID=v_es_multiadd_hdr.id)
        LOOP
        BEGIN
            v_is_clean_flag:=true; 
            v_total_count:=v_total_count+1;

            -- finding buyer company service id
            select count(*) into v_serv_no_count from v_company_service serv where "number" =line.M_COMP_SERV_NUMBER;
            v_log_result := log_activity('PROCEDURE','ENERGY_SALE_BULK_ALLOTMENT.VALIDATE','v_serv_no_count- '||v_serv_no_count,line.M_COMP_SERV_NUMBER,'','', v_seller_num,i_energy_sale_id);
            if v_serv_no_count >0 then
                select * into v_comp_serv from v_company_service serv where "number" =line.M_COMP_SERV_NUMBER  and rownum=1;
                line.M_COMP_SERV_ID:=v_comp_serv.id;
            else 
                line.is_clean:='N';
                line.import_remarks:='Consumer Service Number is invalid';
                v_is_clean_flag:=false;
                v_error_count:=v_error_count+1;
                goto loop_end;

            end if;


            -- finding trade relationship
            select count(*)into v_traderel_count from m_trade_relationship trade where trade.M_SELLER_COMP_SERVICE_ID =v_es_multiadd_hdr.SELLER_COMP_SERV_ID and M_BUYER_COMP_SERVICE_ID=line.M_COMP_SERV_ID
            and to_date(v_es_multiadd_hdr.from_dt,'dd-mm-yyyy') between to_date(from_date,'dd-mm-yyyy') and to_date(TO_DATE,'dd-mm-yyyy') ;

            -- v_log_result := log_activity('PROCEDURE','ENERGY_SALE_BULK_ALLOTMENT.VALIDATE','v_traderel_count','Start - '||v_traderel_count,'','', v_seller_num,i_energy_sale_id);
            if v_traderel_count >0 then
                if v_traderel_count >1 then
                    v_log_result := log_activity('PROCEDURE','ENERGY_SALE_BULK_ALLOTMENT.VALIDATE','Multiple TradeRels for same consumer - '||v_traderel_count,v_es_multiadd_hdr.SELLER_COMP_SERV_ID,'',line.M_COMP_SERV_ID, v_seller_num,i_energy_sale_id);
                end if;
                select * into v_tr from m_trade_relationship trade where trade.M_SELLER_COMP_SERVICE_ID =v_es_multiadd_hdr.SELLER_COMP_SERV_ID and M_BUYER_COMP_SERVICE_ID=line.M_COMP_SERV_ID
                    and to_date(v_es_multiadd_hdr.from_dt,'dd-mm-yyyy') between to_date(from_date,'dd-mm-yyyy') and to_date(TO_DATE,'dd-mm-yyyy') and rownum =1;
                line.M_TRADEREL_ID:=v_tr.id;
            else 
                line.is_clean:='N';
                line.import_remarks:='No Agreement with this consumer';
                v_is_clean_flag:=false;
                v_error_count:=v_error_count+1;
                goto loop_end;
            end if;


            if(v_fuel_group = 'FF' and v_tr.interval_type_code='02') then
                -- If quantum in slots of Allocated is greater than quantum in slots of TRs, then its error
                null;
                if (to_number(nvl(line.c1,0)) > to_number(nvl(v_tr.c1,0)) or to_number(nvl(line.c2,0)) > to_number(nvl(v_tr.c2,0)) or to_number(nvl(line.c3,0)) > to_number(nvl(v_tr.c3,0))
                    or to_number(nvl(line.c4,0)) > to_number(nvl(v_tr.c4,0)) or to_number(nvl(line.c5,0)) > to_number(nvl(v_tr.c5,0)) ) then
                    line.is_clean:='N';
                    line.import_remarks:='Allocated quantum in slot is more than quantum configured in TradeRel';
                    v_is_clean_flag:=false;
                    v_error_count:=v_error_count+1;
                    goto loop_end;
                end if;
            end if;

             --finding if buyer already add in esUsageSummary
             -- v_log_result := log_activity('PROCEDURE','ENERGY_SALE_BULK_ALLOTMENT.VALIDATE','111','111 - ','','', sysdate,line.M_COMP_SERV_ID);
            select count(*) into v_es_usage_sum_count from t_es_usage_summary  where T_ENERGY_SALE_ID =i_energy_sale_id and BUYER_COMP_SERV_ID=line.M_COMP_SERV_ID and M_TRADE_RELATIONSHIP_ID=line.M_TRADEREL_ID;

          --   v_log_result := log_activity('PROCEDURE','ENERGY_SALE_BULK_ALLOTMENT.VALIDATE','v_es_usage_sum_count','Start - '||v_es_usage_sum_count,'','', sysdate,line.M_COMP_SERV_ID);
            if v_es_usage_sum_count =1 then
               select id into line.t_es_usage_summary_id from t_es_usage_summary  where T_ENERGY_SALE_ID =i_energy_sale_id and BUYER_COMP_SERV_ID=line.M_COMP_SERV_ID and M_TRADE_RELATIONSHIP_ID=line.M_TRADEREL_ID;
            end if;
            if v_is_clean_flag then
                line.is_clean:='Y';
                line.import_remarks:='Clean record';
                v_success_count:=v_success_count+1;
                v_es_multiadd_hdr.total_c1:=trim(to_number(nvl(v_es_multiadd_hdr.total_c1,0)))+trim(to_number(nvl(line.c1,0)));
                v_es_multiadd_hdr.total_c2:=trim(to_number(nvl(v_es_multiadd_hdr.total_c2,0)))+trim(to_number(nvl(line.c2,0)));
                v_es_multiadd_hdr.total_c3:=trim(to_number(nvl(v_es_multiadd_hdr.total_c3,0)))+trim(to_number(nvl(line.c3,0)));
                v_es_multiadd_hdr.total_c4:=trim(to_number(nvl(v_es_multiadd_hdr.total_c4,0)))+trim(to_number(nvl(line.c4,0)));
                v_es_multiadd_hdr.total_c5:=trim(to_number(nvl(v_es_multiadd_hdr.total_c5,0)))+trim(to_number(nvl(line.c5,0)));
                if(v_fuel_group <> 'FF') then
                    v_es_multiadd_hdr.total_c001:=trim(to_number(nvl(v_es_multiadd_hdr.total_c001,0)))+trim(to_number(nvl(line.c001,0)));
                    v_es_multiadd_hdr.total_c002:=trim(to_number(nvl(v_es_multiadd_hdr.total_c002,0)))+trim(to_number(nvl(line.c002,0)));
                    v_es_multiadd_hdr.total_c003:=trim(to_number(nvl(v_es_multiadd_hdr.total_c003,0)))+trim(to_number(nvl(line.c003,0)));
                    v_es_multiadd_hdr.total_c004:=trim(to_number(nvl(v_es_multiadd_hdr.total_c004,0)))+trim(to_number(nvl(line.c004,0)));
                    v_es_multiadd_hdr.total_c005:=trim(to_number(nvl(v_es_multiadd_hdr.total_c005,0)))+trim(to_number(nvl(line.c005,0)));
                    v_es_multiadd_hdr.total_c006:=trim(to_number(nvl(v_es_multiadd_hdr.total_c006,0)))+trim(to_number(nvl(line.c006,0)));
                    v_es_multiadd_hdr.total_c007:=trim(to_number(nvl(v_es_multiadd_hdr.total_c007,0)))+trim(to_number(nvl(line.c007,0)));
                    v_es_multiadd_hdr.total_c008:=trim(to_number(nvl(v_es_multiadd_hdr.total_c008,0)))+trim(to_number(nvl(line.c008,0)));
                    v_es_multiadd_hdr.total_c009:=trim(to_number(nvl(v_es_multiadd_hdr.total_c009,0)))+trim(to_number(nvl(line.c009,0)));
                end if;
            end if;
            exception
            when others then
              line.is_clean:='N';
              line.import_remarks:= v_exception_code || ' - ' || SUBSTR(SQLERRM, 1, 200);
              v_is_clean_flag:=false;
              v_error_count:=v_error_count+1;
            dbms_output.put_line(o_result_desc); 

              v_log_result := log_activity('PROCEDURE','ENERGY_SALE_BULK_ALLOTMENT.VALIDATE','EXCEPTION', line.import_remarks,'',line.M_COMP_SERV_ID, v_seller_num,i_energy_sale_id);
            END; 
             <<LOOP_END>>
            update t_es_multiadd_line set M_TRADEREL_ID= line.M_TRADEREL_ID , M_COMP_SERV_ID=line.M_COMP_SERV_ID,is_clean=line.is_clean, import_remarks=line.import_remarks,t_es_usage_summary_id=line.t_es_usage_summary_id  where id=line.id;

        END LOOP;

        v_log_result := log_activity('PROCEDURE','ENERGY_SALE_BULK_ALLOTMENT.VALIDATE','TOTAL_COUNT',v_total_count,'','', v_seller_num,i_energy_sale_id);

 /*     COMMENTING this section - not sure of purpose in wrongly declaring over-allocation
        if to_number(nvl(v_es_multiadd_hdr.total_c1,0)) >  to_number(nvl(v_es.c1,0)) then
         v_log_result := log_activity('PROCEDURE','ENERGY_SALE_BULK_ALLOTMENT.VALIDATE',' v_es_multiadd_hdr.ERROR_MESSAGE in if',  v_es_multiadd_hdr.ERROR_MESSAGE,'1','', v_seller_num,i_energy_sale_id);
         v_es_multiadd_hdr.ERROR_MESSAGE:=nvl(v_es_multiadd_hdr.ERROR_MESSAGE,'')||'-- Over Allocation on C1';
         v_log_result := log_activity('PROCEDURE','ENERGY_SALE_BULK_ALLOTMENT.VALIDATE',' v_es_multiadd_hdr.ERROR_MESSAGE',  v_es_multiadd_hdr.ERROR_MESSAGE,'1','', v_seller_num,i_energy_sale_id);
        end if;
        if to_number(nvl(v_es_multiadd_hdr.total_c2,0)) >  to_number(nvl(v_es.c2,0)) then
         v_es_multiadd_hdr.ERROR_MESSAGE:=nvl(v_es_multiadd_hdr.ERROR_MESSAGE,'')||'-- Over Allocation on C2';
         v_log_result := log_activity('PROCEDURE','ENERGY_SALE_BULK_ALLOTMENT.VALIDATE',' v_es_multiadd_hdr.ERROR_MESSAGE',  v_es_multiadd_hdr.ERROR_MESSAGE,'2','', v_seller_num,i_energy_sale_id);
        end if;
        if to_number(nvl(v_es_multiadd_hdr.total_c3,0)) >  to_number(nvl(v_es.c3,0)) then
         v_es_multiadd_hdr.ERROR_MESSAGE:=nvl(v_es_multiadd_hdr.ERROR_MESSAGE,'')||'-- Over Allocation on C3';
         v_log_result := log_activity('PROCEDURE','ENERGY_SALE_BULK_ALLOTMENT.VALIDATE',' v_es_multiadd_hdr.ERROR_MESSAGE',  v_es_multiadd_hdr.ERROR_MESSAGE,'3','', v_seller_num,i_energy_sale_id);
        end if;
        if to_number(nvl(v_es_multiadd_hdr.total_c4,0)) >  to_number(nvl(v_es.c4,0)) then
         v_es_multiadd_hdr.ERROR_MESSAGE:=nvl(v_es_multiadd_hdr.ERROR_MESSAGE,'')||'-- Over Allocation on C4';
         v_log_result := log_activity('PROCEDURE','ENERGY_SALE_BULK_ALLOTMENT.VALIDATE',' v_es_multiadd_hdr.ERROR_MESSAGE',  v_es_multiadd_hdr.ERROR_MESSAGE,'4','', v_seller_num,i_energy_sale_id);
        end if;
        if to_number(nvl(v_es_multiadd_hdr.total_c5,0)) >  to_number(nvl(v_es.c5,0)) then
         v_es_multiadd_hdr.ERROR_MESSAGE:=nvl(v_es_multiadd_hdr.ERROR_MESSAGE,'')||'-- Over Allocation on C5';
         v_log_result := log_activity('PROCEDURE','ENERGY_SALE_BULK_ALLOTMENT.VALIDATE',' v_es_multiadd_hdr.ERROR_MESSAGE',  v_es_multiadd_hdr.ERROR_MESSAGE,'5','', v_seller_num,i_energy_sale_id);
        end if;

        */
        <<THE_END>>

        update t_es_multiadd_header set TOTAL_COUNT = v_total_count,SUCCESS_COUNT=v_success_count,ERROR_COUNT=v_error_count,total_c1=v_es_multiadd_hdr.total_c1,total_c2=v_es_multiadd_hdr.total_c2,
            total_c3=v_es_multiadd_hdr.total_c3,total_c4=v_es_multiadd_hdr.total_c4,total_c5=v_es_multiadd_hdr.total_c5,total_c001=v_es_multiadd_hdr.total_c001,total_c002=v_es_multiadd_hdr.total_c002,
            total_c003=v_es_multiadd_hdr.total_c003,total_c004=v_es_multiadd_hdr.total_c004,total_c005=v_es_multiadd_hdr.total_c005,total_c006=v_es_multiadd_hdr.total_c006,total_c007=v_es_multiadd_hdr.total_c007,
            total_c008=v_es_multiadd_hdr.total_c008, ERROR_MESSAGE = v_es_multiadd_hdr.ERROR_MESSAGE where id = i_es_multi_add_header_id;
        commit;
    exception
        when others then
          v_exception_code := SQLCODE;
          v_exception_msg := SUBSTR(SQLERRM, 1, 200);
          o_result_code := 'FAILURE';
          o_result_desc := v_exception_code || ' - ' || v_exception_msg;
            dbms_output.put_line(o_result_desc); 
          v_log_result := log_activity('PROCEDURE','ENERGY_SALE_BULK_ALLOTMENT.VALIDATE','EXCEPTION',o_result_desc,'','', v_seller_num,i_energy_sale_id);
    END;

    v_log_result := log_activity('PROCEDURE','ENERGY_SALE_BULK_ALLOTMENT.VALIDATE','END','','','', v_seller_num,i_energy_sale_id);

  END validate;

  procedure import(i_energy_sale_id in varchar2,i_es_multi_add_header_id in varchar2,  o_result_code out varchar2, o_result_desc out varchar2) AS
   v_created_by varchar2(100):='admin';
   v_log_result  varchar2(50);
   v_reason varchar2(200):='';
   v_exception_code  NUMBER;
   v_exception_msg  VARCHAR2(200);  
   v_es_usage_sum_total  NUMBER:=0;
   v_es_multiadd_hdr T_ES_MULTIADD_HEADER%ROWTYPE;
   v_es_multiadd_line T_ES_MULTIADD_LINE%ROWTYPE;
   v_es T_ENERGY_SALE%ROWTYPE;
   v_es_summary T_ES_USAGE_SUMMARY%ROWTYPE;
   v_es_charge T_ES_CHARGE%ROWTYPE;

  BEGIN
     BEGIN
    -- TODO: Implementation required for procedure ENERGY_SALE_BULK_ALLOTMENT.import
     v_log_result := log_activity('PROCEDURE','ENERGY_SALE_BULK_ALLOTMENT.import','START','Start - '||i_energy_sale_id,'','', sysdate,i_energy_sale_id);
     select * into v_es_multiadd_hdr from t_es_multiadd_header where id=i_es_multi_add_header_id; 
      FOR line IN ( select * into v_es_multiadd_line from T_ES_MULTIADD_LINE where T_ES_MULTIADD_HEADER_ID=v_es_multiadd_hdr.id and is_clean='Y')
          LOOP


         v_es_usage_sum_total:=(to_number(nvl(line.C1,0))+to_number(nvl(line.C2,0))+to_number(nvl(line.C3,0))+to_number(nvl(line.C4,0))+to_number(nvl(line.C5,0)));

         
         update T_ES_USAGE_SUMMARY set c1=trim(round(line.C1,0)) , c2=trim(round(line.C2,0)), c3=trim(round(line.C3,0)), c4=trim(round(line.C4,0)), c5=trim(round(line.C5,0)),total =v_es_usage_sum_total where id=line.t_es_usage_summary_id;
         -- null checked
        -- update T_ES_USAGE_SUMMARY set c1=trim(round(nvl(line.C1,0),0)) , c2=trim(round(nvl(line.C2,0),0)), c3=trim(round(nvl(line.C3,0),0)), c4=trim(round(nvl(line.C4,0),0)), c5=trim(round(nvl(line.C5,0),0)),total =v_es_usage_sum_total where id=line.t_es_usage_summary_id;
         
         if line.c001 is not null then

             update t_es_charge set TOTAL_CHARGE=trim(line.c001) where T_ENERGY_SALE_ID=i_energy_sale_id and M_COMP_SERV_ID = line.M_COMP_SERV_ID  and  CHARGE_CODE= 'C001';
         end if;

         if line.c002 is not null then
             update t_es_charge set TOTAL_CHARGE=trim(line.c002) where T_ENERGY_SALE_ID=i_energy_sale_id and M_COMP_SERV_ID = line.M_COMP_SERV_ID  and  CHARGE_CODE= 'C002';
         end if;

          if line.c003 is not null then
             update t_es_charge set TOTAL_CHARGE=trim(line.c003) where T_ENERGY_SALE_ID=i_energy_sale_id and M_COMP_SERV_ID = line.M_COMP_SERV_ID  and  CHARGE_CODE= 'C003';
         end if;

          if line.c004 is not null then
            update t_es_charge set TOTAL_CHARGE=trim(line.c004) where T_ENERGY_SALE_ID=i_energy_sale_id and M_COMP_SERV_ID = line.M_COMP_SERV_ID  and  CHARGE_CODE= 'C004';
         end if;

          if line.c005 is not null then
             update t_es_charge set TOTAL_CHARGE=trim(line.c005) where T_ENERGY_SALE_ID=i_energy_sale_id and M_COMP_SERV_ID = line.M_COMP_SERV_ID  and  CHARGE_CODE= 'C005';
         end if;

          if line.c006 is not null then
             update t_es_charge set TOTAL_CHARGE=trim(line.c006) where T_ENERGY_SALE_ID=i_energy_sale_id and M_COMP_SERV_ID = line.M_COMP_SERV_ID  and  CHARGE_CODE= 'C006';
         end if;

          if line.c007 is not null then
             update t_es_charge set TOTAL_CHARGE=trim(line.c007) where T_ENERGY_SALE_ID=i_energy_sale_id and M_COMP_SERV_ID = line.M_COMP_SERV_ID  and  CHARGE_CODE= 'C007';
         end if;

          if line.c008 is not null then
            update t_es_charge set TOTAL_CHARGE=trim(line.c008) where T_ENERGY_SALE_ID=i_energy_sale_id and M_COMP_SERV_ID = line.M_COMP_SERV_ID  and  CHARGE_CODE= 'C008';
         end if;

      if line.c009 is not null then
            update t_es_charge set TOTAL_CHARGE=trim(line.c009) where T_ENERGY_SALE_ID=i_energy_sale_id and M_COMP_SERV_ID = line.M_COMP_SERV_ID  and  CHARGE_CODE= 'C009';
         end if;
        END LOOP;
     UPDATE T_ES_MULTIADD_HEADER set is_completed='Y' where id= i_es_multi_add_header_id;
     commit;
    exception
        when others then
          v_exception_code := SQLCODE;
          v_exception_msg := SUBSTR(SQLERRM, 1, 200);
          o_result_code := 'FAILURE';
          o_result_desc := v_exception_code || ' - ' || v_exception_msg;
          -- dbms_output.put_line(o_result_desc);
          v_log_result := log_activity('PROCEDURE','ENERGY_SALE_BULK_ALLOTMENT.import','EXCEPTION',o_result_desc,'','', sysdate,i_energy_sale_id);
    END;

    v_log_result := log_activity('PROCEDURE','ENERGY_SALE_BULK_ALLOTMENT.import','END','Cleared earlier transactions and related orphan records','','', sysdate,i_energy_sale_id);

  END import;

END ENERGY_SALE_BULK_ALLOTMENT;
