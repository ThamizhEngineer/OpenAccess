

CREATE OR REPLACE PACKAGE BANKING_BALANCE AS 

  /* TODO enter package declarations (types, exceptions, methods etc) here */ 
  procedure process_import_table (i_remarks in varchar2, i_overwrite in char default 'N', o_result_code out varchar2, o_result_desc out varchar2);
  procedure open_balance (i_service_no in varchar2, i_reading_month in varchar2, i_reading_year in varchar2, o_result_code out varchar2, o_result_desc out varchar2);
  procedure confirm_energy_sale_event(V_ES_ID in varchar2, o_result_code out varchar2, o_result_desc out varchar2);
  procedure increment_surplus_units(i_ht_surplus_units_id in varchar2, o_result_code out varchar2, o_result_desc out varchar2);
  procedure reset_for_yearend(i_service_no in varchar2, i_reading_month in varchar2, i_reading_year in varchar2,o_result_code out varchar2, o_result_desc out varchar2);

END BANKING_BALANCE;


CREATE OR REPLACE PACKAGE BODY "BANKING_BALANCE" AS

  procedure process_import_table (i_remarks in varchar2, i_overwrite in CHAR default 'N', o_result_code out varchar2, o_result_desc out varchar2) IS
  
    V_OVERWRITE CHAR(1) := 'N';
    v_created_By  varchar2(50):= 'BANKING_BALANCE.PROCESS_IMPORT_TABLE';
    v_exception_code  NUMBER;
    v_exception_msg  VARCHAR2(200);
    v_log_result varchar(300):='SUCCESS';
    v_imported_count number:=0;
    v_total_count number:=0;

  BEGIN

    -- begin for exception handling
    BEGIN

      v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.PROCESS_IMPORT_TABLE','START','Start - '||i_remarks,'','', sysdate,i_remarks);

     if(I_OVERWRITE is not null and I_OVERWRITE='Y') then V_OVERWRITE := 'Y'; end if; 

      o_result_code:='SUCCESS';
      o_result_desc:='';

      select count(*) into v_total_count from IMP_BANKING_BALANCE where remarks = i_remarks;
      if(v_total_count=0) then
          Raise_Application_Error (-20343, 'No records to process');
      end if;

      -- reset values
      update IMP_BANKING_BALANCE set GEN_SERVICE_NUMBER = trim(GEN_SERVICE_NUMBER), M_COMPANY_ID = null, BANKING_SERVICE_ID = null,CLEAN_REC = null,IMPORTED = null, IMPORT_REMARKS=null, modified_by = v_created_By, modified_dt=sysdate where remarks = i_remarks;

      v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.PROCESS_IMPORT_TABLE','Init Done',''||i_remarks,'','', sysdate,i_remarks);

      --update company-id, banking-service-id,company-service-id
      FOR i IN ( select IMP.GEN_SERVICE_NUMBER, SER.M_COMPANY_ID, SER.BANKING_SERVICE_ID,SER.ID  from IMP_BANKING_BALANCE IMP inner join v_company_service SER on SER."number" = IMP.GEN_SERVICE_NUMBER where IMP.remarks =i_remarks)
      LOOP
        update IMP_BANKING_BALANCE set BANKING_SERVICE_ID = i.BANKING_SERVICE_ID,  M_COMPANY_ID = i.M_COMPANY_ID ,M_COMPANY_SERVICE_ID=i.ID, CLEAN_REC ='Y', ENABLED='Y', modified_by = v_created_By, modified_dt=sysdate where GEN_SERVICE_NUMBER = i.GEN_SERVICE_NUMBER and remarks = i_remarks;
      END LOOP;


      --  code cleansing start
      update IMP_BANKING_BALANCE set CLEAN_REC = 'N', IMPORT_REMARKS='ERROR - GEN_SERVICE_NUMBER not in system' where  remarks = i_remarks and CLEAN_REC is null;

      FOR i IN ( select IMP.GEN_SERVICE_NUMBER,bal.BANKING_SERVICE_ID, bal.month,bal.year from IMP_BANKING_BALANCE IMP inner join t_banking_balance bal on bal.BANKING_SERVICE_ID = IMP.BANKING_SERVICE_ID and to_number(bal.month) = to_number(imp.month) and  to_number(bal.year) = to_number(imp.year)
        where IMP.remarks =i_remarks and nvl(CLEAN_REC,'') ='Y')
      LOOP
        IF (V_OVERWRITE='Y') THEN
          DELETE t_banking_balance where BANKING_SERVICE_ID=i.BANKING_SERVICE_ID and  to_number(MONTH)=to_number(i.MONTH) and to_number(YEAR)=to_number(i.YEAR);
        ELSE
          update IMP_BANKING_BALANCE set CLEAN_REC = 'N', IMPORT_REMARKS='ERROR - Banking Balance already exists',modified_by = v_created_By, modified_dt=sysdate  where  remarks = i_remarks and GEN_SERVICE_NUMBER = i.GEN_SERVICE_NUMBER;
        END IF;

      END LOOP;


      -- CODE FOR CHECKING TRADERELATIONSHIP WITH TNEB EXIST
       FOR i IN ( SELECT count(*) total,trade.M_SELLER_COMPANY_ID  FROM IMP_BANKING_BALANCE IMP inner join M_TRADE_RELATIONSHIP trade on TRADE.M_SELLER_COMPANY_ID=imp.M_COMPANY_ID and to_date(to_char(lpad(IMP.month,2,'0')||'-'||IMP.year),'MM-YYYY') BETWEEN TRADE.FROM_DATE AND TRADE.TO_DATE 
        and trade.M_BUYER_COMPANY_ID='TNEB' 
        where IMP.remarks =i_remarks and nvl(CLEAN_REC,'') ='Y'  GROUP BY trade.M_SELLER_COMPANY_ID )
      LOOP
      IF to_number(i.total)>0 then
       update IMP_BANKING_BALANCE set CLEAN_REC = 'N', IMPORT_REMARKS='ERROR - TradeRelationship with TNEB already exists',modified_by = v_created_By, modified_dt=sysdate  where  remarks = i_remarks and M_COMPANY_ID=i.M_SELLER_COMPANY_ID;
       end IF;
      END LOOP;

      v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.PROCESS_IMPORT_TABLE','Cleansing Complete',''||i_remarks,'','', sysdate,i_remarks);

      insert into t_banking_balance (id, remarks, MONTH, YEAR,C1,C2,C3,C4,C5,CREATED_BY,CREATED_DT, ENABLED,M_COMPANY_ID, BANKING_SERVICE_ID,CURR_C1,CURR_C2,CURR_C3,CURR_C4,CURR_C5,CALCULATED)
        (SELECT T_BANKING_BALANCE_SEQ.nextval ID, remarks, lpad(month,2,'0'), year,C1,C2,C3,C4,C5,CREATED_BY,CREATED_DT, ENABLED,M_COMPANY_ID, BANKING_SERVICE_ID,round(C1,0),round(C2,0),round(C3,0),round(C4,0),round(C5,0),'N'
          FROM IMP_BANKING_BALANCE
          WHERE remarks = i_remarks
          AND CLEAN_REC = 'Y'
        );

      v_imported_count :=   sql%Rowcount;
      if(v_imported_count>0) then
        update IMP_BANKING_BALANCE set IMPORTED = 'Y', IMPORT_REMARKS='IMPORTED',modified_by = v_created_By, modified_dt=sysdate  where  remarks = i_remarks and CLEAN_REC = 'Y';
        v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.PROCESS_IMPORT_TABLE','PROCESS','data imported complete','success_records -' ||v_imported_count,'', sysdate,i_remarks);
      else
        v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.PROCESS_IMPORT_TABLE','PROCESS','no data imported','error_records -' ||(v_total_count-v_imported_count),'', sysdate,i_remarks);
        o_result_code:= 'FAILURE';
        o_result_desc := 'no clean data to import';

      end if;
      commit;
    exception
        when others then
          v_exception_code := SQLCODE;
          v_exception_msg := SUBSTR(SQLERRM, 1, 200);
          o_result_code := 'FAILURE';
          o_result_desc := v_exception_code || ' - ' || v_exception_msg;
          -- dbms_output.put_line(o_result_desc);
          v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.PROCESS_IMPORT_TABLE','EXCEPTION',o_result_desc,'','', sysdate,i_remarks);
    END;

    v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.PROCESS_IMPORT_TABLE','END','Import complete','','', sysdate,i_remarks);

  END process_import_table;


  procedure open_balance (i_service_no in varchar2, i_reading_month in varchar2, i_reading_year in varchar2, o_result_code out varchar2, o_result_desc out varchar2)  IS
    v_created_By  varchar2(50):= 'BANKING_BALANCE.OPEN_BALANCE';
    v_exception_code  NUMBER;
    v_exception_msg  VARCHAR2(200);
    v_log_result varchar(300):='SUCCESS';
    v_bb T_BANKING_BALANCE%ROWTYPE;
    v_bb_new T_BANKING_BALANCE%ROWTYPE;
    v_serv_rec V_COMPANY_SERVICE%ROWTYPE;
    v_prev_month varchar2(10);
    v_prev_year varchar2(10);
    v_prev_month_bb_count number:=0;
    v_curr_month_bb_count number:=0;
    v_commission_date Date;
  BEGIN

    -- begin for exception handling
    BEGIN

      v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.OPEN_BALANCE','START','Start - i_service_no-'||i_service_no||',i_reading_month-'||i_reading_month||',i_reading_year-'||i_reading_year,'','', sysdate,i_service_no);

      o_result_code:='SUCCESS';
      o_result_desc:='';

      select * into  v_serv_rec from v_company_service where "number"=i_service_no fetch first 1 rows only;      
      select count(*) into v_curr_month_bb_count from t_banking_balance where M_COMPANY_ID = v_serv_rec.m_company_id and BANKING_SERVICE_ID = v_serv_rec.BANKING_SERVICE_ID and month = i_reading_month and year = i_reading_year  fetch first 1 rows only;

      if(v_serv_rec.excess_unit_type <> 'BANKING') then
        o_result_code:='FAILURE';
        o_result_desc:='Excess_unit_type -'||v_serv_rec.excess_unit_type||' Banking Balance not applicable for  i_service_no-'||i_service_no||',i_reading_month-'||i_reading_month||',i_reading_year-'||i_reading_year;
        v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.OPEN_BALANCE','Balance Check',o_result_desc,'','', sysdate,i_service_no);
      elsif(v_curr_month_bb_count > 0) then        
        o_result_code:='FAILURE';
        o_result_desc:='Banking Balance Already exists for  i_service_no-'||i_service_no||',i_reading_month-'||i_reading_month||',i_reading_year-'||i_reading_year;
        v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.OPEN_BALANCE','Balance Check',o_result_desc,'','', sysdate,i_service_no);
        GOTO THE_END;
      else
      -- here excess_unit_type is BANKING
        -- create new balance record
         /*
         select COMMISSION_DATE into v_commission_date from M_POWERPLANT where M_SERVICE_ID = v_serv_rec.id;
          if v_commission_date is not null and v_commission_date> to_date('01-04-2018','DD-MM-YYYY') then
             GOTO THE_END;
          end if;
          */
        v_bb_new.M_COMPANY_ID := v_serv_rec.M_COMPANY_ID;
        v_bb_new.BANKING_SERVICE_ID := v_serv_rec.BANKING_SERVICE_ID;
        v_bb_new.enabled:='Y';
        v_bb_new.CALCULATED:='N';
        v_bb_new.month:=i_reading_month;
        v_bb_new.year:=i_reading_year;
        v_bb_new.CREATED_BY:=v_created_By;
        v_bb_new.CREATED_DATE:=sysdate;
        v_bb_new.id:=T_BANKING_BALANCE_SEQ.NEXTVAL;

        -- fetch previous month record to get closing balance
        v_prev_month := to_char(to_date( '01-'||i_reading_month||'-'||i_reading_year,'dd-mm-yyyy')-1,'mm');
        v_prev_year := to_char(to_date( '01-'||i_reading_month||'-'||i_reading_year,'dd-mm-yyyy')-1,'yyyy');

        select count(*) into v_prev_month_bb_count from t_banking_balance where M_COMPANY_ID = v_serv_rec.m_company_id and BANKING_SERVICE_ID = v_serv_rec.BANKING_SERVICE_ID and month = v_prev_month and year = v_prev_year  fetch first 1 rows only;
        if(v_prev_month_bb_count >0) then
          -- create New Balance record based on prev month balance

          select * into v_bb from t_banking_balance where M_COMPANY_ID = v_serv_rec.m_company_id and BANKING_SERVICE_ID = v_serv_rec.BANKING_SERVICE_ID and month = v_prev_month and year = v_prev_year  fetch first 1 rows only;

             select  nvl(v_bb.CURR_C1,0)+nvl(v_bb.SURPLUS_C1,0),nvl(v_bb.CURR_C2,0)+nvl(v_bb.SURPLUS_C2,0) ,nvl(v_bb.CURR_C3,0)+nvl(v_bb.SURPLUS_C3,0) ,nvl(v_bb.CURR_C4,0)+nvl(v_bb.SURPLUS_C4,0) ,nvl(v_bb.CURR_C5,0)+nvl(v_bb.SURPLUS_C5,0) ,
             round(nvl(v_bb.CURR_C1,0),0)+round(nvl(v_bb.SURPLUS_C1,0),0) ,round(nvl(v_bb.CURR_C2,0),0)+round(nvl(v_bb.SURPLUS_C2,0),0) ,round(nvl(v_bb.CURR_C3,0),0)+round(nvl(v_bb.SURPLUS_C3,0),0) ,round(nvl(v_bb.CURR_C4,0),0)+round(nvl(v_bb.SURPLUS_C4,0),0) ,round(nvl(v_bb.CURR_C5,0),0)+round(nvl(v_bb.SURPLUS_C5,0),0) 
             into  v_bb_new.C1,v_bb_new.C2,v_bb_new.C3,v_bb_new.C4,v_bb_new.C5,v_bb_new.CURR_C1,v_bb_new.CURR_C2,v_bb_new.CURR_C3,v_bb_new.CURR_C4,v_bb_new.CURR_C5 from dual;

          insert into t_banking_balance values v_bb_new;
          v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.OPEN_BALANCE','Process','New Balance record created based on prev month balance. i_service_no-'||i_service_no||',i_reading_month-'||i_reading_month||',i_reading_year-'||i_reading_year,'','', sysdate,i_service_no);

        else
          -- create a new Balance record with empty bb values

          select  '0','0','0','0','0', '0','0','0','0','0'
          into  v_bb_new.C1,v_bb_new.C2,v_bb_new.C3,v_bb_new.C4,v_bb_new.C5,v_bb_new.CURR_C1,v_bb_new.CURR_C2,v_bb_new.CURR_C3,v_bb_new.CURR_C4,v_bb_new.CURR_C5 from dual;

          insert into t_banking_balance values v_bb_new;
          v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.OPEN_BALANCE','Process','New Balance record created with empty bb values. i_service_no-'||i_service_no||',i_reading_month-'||i_reading_month||',i_reading_year-'||i_reading_year,'','', sysdate,i_service_no);

        end if;    -- if for v_prev_month_bb_count
      end if;  -- if for v_curr_month_bb_count

      commit;
    exception
        when others then
          v_exception_code := SQLCODE;
          v_exception_msg := SUBSTR(SQLERRM, 1, 200);
          o_result_code := 'FAILURE';
          o_result_desc := v_exception_code || ' - ' || v_exception_msg;
          -- dbms_output.put_line(o_result_desc);
          v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.OPEN_BALANCE','EXCEPTION',o_result_desc,'','', sysdate,i_service_no);
    END;
    <<THE_END>>
    v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.OPEN_BALANCE','END','Complete','','', sysdate,i_service_no);

  END OPEN_BALANCE;


  procedure confirm_energy_sale_event(V_ES_ID in varchar2, o_result_code out varchar2, o_result_desc out varchar2)  IS

    v_created_By  varchar2(50):= 'BANKING_BALANCE.CONFIRM_ENERGY_SALE_EVENT';

    V_COMP_SERVICE V_COMPANY_SERVICE%ROWTYPE;

    V_ES T_ENERGY_SALE%ROWTYPE;

    v_result varchar(300):='SUCCESS';
    v_log_result varchar(300):='SUCCESS';
    v_exception_code VARCHAR2(150);
    v_exception_msg  VARCHAR2(150);
    v_reason VARCHAR2(300);
    v_excess_es VARCHAR2(300);
  ------------------------------------------------  

  ------------------------------------------------
    V_BB T_BANKING_BALANCE%ROWTYPE;
    V_C1 NUMBER:=0;
    V_C2 NUMBER:=0;
    V_C3 NUMBER:=0;
    V_C4 NUMBER:=0;
    V_C5 NUMBER:=0;
    V_COUNT NUMBER:=0;
    V_UNALLOCATED_GEN_FLAG boolean:=false;
    V_ALLOCATED_BANK_FLAG boolean:=false;

  BEGIN

    -- begin for exception handling
    BEGIN

      v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.CONFIRM_ENERGY_SALE_EVENT','START','Start - i_t_es_id-'||V_ES_ID,'','', sysdate,V_ES_ID);

      o_result_code:='SUCCESS';
      o_result_desc:='';


           SELECT * INTO V_ES FROM T_ENERGY_SALE WHERE ID = V_ES_ID;

--       DBMS_OUTPUT.PUT_LINE(V_ES.STATUS_CODE);

       IF V_ES.STATUS_CODE='APPROVED' THEN

            SELECT * INTO V_COMP_SERVICE FROM V_COMPANY_SERVICE WHERE ID=V_ES.SELLER_COMP_SERV_ID;

            if(V_COMP_SERVICE.excess_unit_type<>'BANKING') then

                -- o_result_code:='FAILURE';
                o_result_desc:='Excess_unit_type -'||V_COMP_SERVICE.excess_unit_type||' Banking Balance not applicable for  i_service_no-'||V_COMP_SERVICE."number";
                v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.OPEN_BALANCE','Balance Check',o_result_desc,'','', sysdate,V_COMP_SERVICE."number");
                goto THE_END;
            end if;
            --      calculation for unallocated gen units
            select count(*) into V_COUNT from t_banking_balance where M_COMPANY_ID = V_COMP_SERVICE.m_company_id and month = v_es.month and year = v_es.year;

--         DBMS_OUTPUT.PUT_LINE(V_COUNT);
            if V_COUNT>0 then
                select * into V_BB from t_banking_balance where M_COMPANY_ID = V_COMP_SERVICE.m_company_id and month = v_es.month and year = v_es.year fetch first 1 rows only;
                IF TO_NUMBER(nvl(V_ES.AVAIL_GC1,0))  > TO_NUMBER(nvl(V_ES.GC1,0)) THEN
                    V_C1 := (TO_NUMBER(nvl(V_ES.AVAIL_GC1,0)) - TO_NUMBER(nvl(V_ES.GC1,0)));
                    V_C1 :=(V_C1)-(V_C1 *0.14);  
                    V_UNALLOCATED_GEN_FLAG:=true;
                END IF;

                IF TO_NUMBER(nvl(V_ES.AVAIL_GC2,0))  > TO_NUMBER(nvl(V_ES.GC2,0)) THEN
                    V_C2 := (TO_NUMBER(nvl(V_ES.AVAIL_GC2,0)) - TO_NUMBER(nvl(V_ES.GC2,0)));
                    V_C2 :=(V_C2)-(V_C2 *0.14); 
                    V_UNALLOCATED_GEN_FLAG:=true;
                END IF;

                IF TO_NUMBER(nvl(V_ES.AVAIL_GC3,0))  > TO_NUMBER(nvl(V_ES.GC3,0)) THEN
                    V_C3 := (TO_NUMBER(nvl(V_ES.AVAIL_GC3,0)) - TO_NUMBER(nvl(V_ES.GC3,0)));
                    V_C3 :=(V_C3)-(V_C3 *0.14); 
                    V_UNALLOCATED_GEN_FLAG:=true;
                END IF;

                IF TO_NUMBER(nvl(V_ES.AVAIL_GC4,0))  > TO_NUMBER(nvl(V_ES.GC4,0)) THEN
                    V_C4 := (TO_NUMBER(nvl(V_ES.AVAIL_GC4,0)) - TO_NUMBER(nvl(V_ES.GC4,0)));
                    V_C4 :=(V_C4)-(V_C4 *0.14); 
                    V_UNALLOCATED_GEN_FLAG:=true;
                END IF;

                IF TO_NUMBER(nvl(V_ES.AVAIL_GC5,0))  > TO_NUMBER(nvl(V_ES.GC5,0)) THEN
                    V_C5 := (TO_NUMBER(nvl(V_ES.AVAIL_GC5,0)) - TO_NUMBER(nvl(V_ES.GC5,0)));
                    V_C5 :=(V_C5)-(V_C5 *0.14); 
                    V_UNALLOCATED_GEN_FLAG:=true;
                END IF;

                IF V_UNALLOCATED_GEN_FLAG THEN
                   V_BB.CURR_C1 := TO_NUMBER(nvl(V_BB.CURR_C1,0))+TO_NUMBER(V_C1);
                   V_BB.CURR_C2 := TO_NUMBER(nvl(V_BB.CURR_C2,0))+TO_NUMBER(V_C2);
                   V_BB.CURR_C3 := TO_NUMBER(nvl(V_BB.CURR_C3,0))+TO_NUMBER(V_C3);
                   V_BB.CURR_C4 := TO_NUMBER(nvl(V_BB.CURR_C4,0))+TO_NUMBER(V_C4);
                   V_BB.CURR_C5 := TO_NUMBER(nvl(V_BB.CURR_C5,0))+TO_NUMBER(V_C5);
--                      DBMS_OUTPUT.PUT_LINE('--V_BB.CURR_C1-----'||V_BB.CURR_C1); 
                   UPDATE T_BANKING_BALANCE SET CURR_C1=round(V_BB.CURR_C1,0) ,CURR_C2=round(V_BB.CURR_C2,0),CURR_C3=round(V_BB.CURR_C3,0),CURR_C4=round(V_BB.CURR_C4,0),CURR_C5=round(V_BB.CURR_C5,0),CALCULATED='Y' ,
                            modified_by=v_created_By, modified_dt=sysdate 
                   where M_COMPANY_ID = V_COMP_SERVICE.m_company_id and month = v_es.month and year = v_es.year;

                END IF;

                IF TO_NUMBER(nvl(V_ES.BC1,0))>0 THEN
                    V_BB.CURR_C1 := TO_NUMBER(nvl(V_BB.CURR_C1,0))-TO_NUMBER(nvl(V_ES.BC1,0));                
                    V_ALLOCATED_BANK_FLAG:=true;
                END IF;

                 IF TO_NUMBER(nvl(V_ES.BC2,0))>0 THEN
                    V_BB.CURR_C2 := TO_NUMBER(nvl(V_BB.CURR_C2,0))-TO_NUMBER(nvl(V_ES.BC2,0));                
                    V_ALLOCATED_BANK_FLAG:=true;
                END IF;

                 IF TO_NUMBER(nvl(V_ES.BC3,0))>0 THEN
                    V_BB.CURR_C3 := TO_NUMBER(nvl(V_BB.CURR_C3,0))-TO_NUMBER(nvl(V_ES.BC3,0));                
                    V_ALLOCATED_BANK_FLAG:=true;
                END IF;

                 IF TO_NUMBER(nvl(V_ES.BC4,0))>0 THEN
                    V_BB.CURR_C4 := TO_NUMBER(nvl(V_BB.CURR_C4,0))-TO_NUMBER(nvl(V_ES.BC4,0));                
                    V_ALLOCATED_BANK_FLAG:=true;
                END IF;

                 IF TO_NUMBER(nvl(V_ES.BC5,0))>0 THEN
                    V_BB.CURR_C5 := TO_NUMBER(nvl(V_BB.CURR_C5,0))-TO_NUMBER(nvl(V_ES.BC5,0));                
                    V_ALLOCATED_BANK_FLAG:=true;
                END IF;

                IF V_ALLOCATED_BANK_FLAG THEN
                    --if negative, make balances as empty
                    select decode(sign(V_BB.CURR_C1),1,V_BB.CURR_C1,0), decode(sign(V_BB.CURR_C2),1,V_BB.CURR_C2,0), decode(sign(V_BB.CURR_C3),1,V_BB.CURR_C3,0), decode(sign(V_BB.CURR_C4),1,V_BB.CURR_C4,0), decode(sign(V_BB.CURR_C5),1,V_BB.CURR_C5,0)
                   into V_BB.CURR_C1, V_BB.CURR_C2, V_BB.CURR_C3, V_BB.CURR_C4, V_BB.CURR_C5 from dual;

                   UPDATE T_BANKING_BALANCE SET CURR_C1=round(V_BB.CURR_C1,0) ,CURR_C2=round(V_BB.CURR_C2,0),CURR_C3=round(V_BB.CURR_C3,0),CURR_C4=round(V_BB.CURR_C4,0),CURR_C5=round(V_BB.CURR_C5,0),CALCULATED='Y' ,
                   modified_by=v_created_By, modified_dt=sysdate 
                   where M_COMPANY_ID = V_COMP_SERVICE.m_company_id and month = v_es.month and year = v_es.year;

                END IF;
                v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.CONFIRM_ENERGY_SALE_EVENT','UPDATED BB','CONFIRM_ENERGY_SALE_EVENT complete','','', sysdate,V_ES_ID);
          else

                IF TO_NUMBER(nvl(V_ES.AVAIL_GC1,0))  > TO_NUMBER(nvl(V_ES.GC1,0)) THEN
    --                  DBMS_OUTPUT.PUT_LINE(V_ES.AVAIL_GC1 ||'-------'||V_ES.GC1);
                        V_C1 := (TO_NUMBER(nvl(V_ES.AVAIL_GC1,0)) - TO_NUMBER(nvl(V_ES.GC1,0)));
                        V_C1 :=(V_C1)-(V_C1 *0.14);  
--                        DBMS_OUTPUT.PUT_LINE('--V_C1-----'||V_C1); 
                        V_UNALLOCATED_GEN_FLAG:=true;
                    END IF;

                    IF TO_NUMBER(nvl(V_ES.AVAIL_GC2,0))  > TO_NUMBER(nvl(V_ES.GC2,0)) THEN
                        V_C2 := (TO_NUMBER(nvl(V_ES.AVAIL_GC2,0)) - TO_NUMBER(nvl(V_ES.GC2,0)));
                        V_C2 :=(V_C2)-(V_C2 *0.14); 
                        V_UNALLOCATED_GEN_FLAG:=true;
                    END IF;

                    IF TO_NUMBER(nvl(V_ES.AVAIL_GC3,0))  > TO_NUMBER(nvl(V_ES.GC3,0)) THEN
                        V_C3 := (TO_NUMBER(nvl(V_ES.AVAIL_GC3,0)) - TO_NUMBER(nvl(V_ES.GC3,0)));
                        V_C3 :=(V_C3)-(V_C3 *0.14); 
                        V_UNALLOCATED_GEN_FLAG:=true;
                    END IF;

                    IF TO_NUMBER(nvl(V_ES.AVAIL_GC4,0))  > TO_NUMBER(nvl(V_ES.GC4,0)) THEN
                        V_C4 := (TO_NUMBER(nvl(V_ES.AVAIL_GC4,0)) - TO_NUMBER(nvl(V_ES.GC4,0)));
                        V_C4 :=(V_C4)-(V_C4 *0.14); 
                        V_UNALLOCATED_GEN_FLAG:=true;
                    END IF;

                    IF TO_NUMBER(nvl(V_ES.AVAIL_GC5,0))  > TO_NUMBER(nvl(V_ES.GC5,0)) THEN
                        V_C5 := (TO_NUMBER(nvl(V_ES.AVAIL_GC5,0)) - TO_NUMBER(nvl(V_ES.GC5,0)));
                        V_C5 :=(V_C5)-(V_C5 *0.14); 
                        V_UNALLOCATED_GEN_FLAG:=true;                       

                    END IF;
                    IF V_UNALLOCATED_GEN_FLAG THEN
                    select V_COMP_SERVICE.M_COMPANY_ID,V_COMP_SERVICE.BANKING_SERVICE_ID,V_C1,V_C2,V_C3,V_C4,V_C5,round(V_C1,0),round(V_C2,0),round(V_C3,0),round(V_C4,0),round(V_C5,0)
                    into v_bb.M_COMPANY_ID,v_bb.BANKING_SERVICE_ID,v_bb.C1,v_bb.C2,v_bb.C3,v_bb.C4,v_bb.C5,v_bb.CURR_C1,v_bb.CURR_C2,v_bb.CURR_C3,v_bb.CURR_C4,v_bb.CURR_C5 from dual;
                    v_bb.enabled:='Y';
                    v_bb.CALCULATED:='Y';
                    v_bb.month:=V_ES.MONTH;
                    v_bb.year:=V_ES.YEAR;
                    v_bb.CREATED_BY:=v_created_By; 
                    v_bb.CREATED_DT:=sysdate; 
                    v_bb.id:=T_BANKING_BALANCE_SEQ.NEXTVAL;
                    insert into t_banking_balance VALUES v_bb;
                    END IF;
                     v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.CONFIRM_ENERGY_SALE_EVENT','INSERT BB','CONFIRM_ENERGY_SALE_EVENT complete','','', sysdate,V_ES_ID);

          end if;

            v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.CONFIRM_ENERGY_SALE_EVENT','END','CONFIRM_ENERGY_SALE_EVENT complete','','', sysdate,V_ES_ID);
       ELSE -- else for V_ES.STATUS_CODE='APPROVED'
        o_result_desc    := 'BANKING_BAL - CANNOT PROCESS ENERGY SALE THAT IS NOT APPROVED';
        o_result_code    := 'FAILURE';
        v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.CONFIRM_ENERGY_SALE_EVENT','PROCESS',o_result_desc,'','', sysdate,V_ES_ID);
       END IF; -- end if for V_ES.STATUS_CODE='APPROVED'


      -- commit;
    exception
        when others then
          v_exception_code := SQLCODE;
          v_exception_msg := SUBSTR(SQLERRM, 1, 200);
          o_result_code := 'FAILURE';
          o_result_desc := v_exception_code || ' - ' || v_exception_msg;
          -- dbms_output.put_line(o_result_desc);
          v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.CONFIRM_ENERGY_SALE_EVENT','EXCEPTION',o_result_desc,'','', sysdate,V_ES_ID);
    END;
    <<THE_END>>
    v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.CONFIRM_ENERGY_SALE_EVENT','END','CONFIRM_ENERGY_SALE_EVENT complete','','', sysdate,V_ES_ID);


  END CONFIRM_ENERGY_SALE_EVENT;

  procedure increment_surplus_units(i_ht_surplus_units_id in varchar2, o_result_code out varchar2, o_result_desc out varchar2) AS
  v_created_By VARCHAR2(200):='BANKING_BALANCE.INCREMENT_SURPLUS_UNITS';
  v_htsurcursor sys_refcursor ;
  v_int_surplus_unit INT_SURPLUS_UNIT%ROWTYPE;
    v_total_loss VARCHAR2(200);
    v_drawl_code VARCHAR2(200); 
    v_inj_code VARCHAR2(200); 
    v_inj_units number; 
    v_trans_loss VARCHAR2(200); 
    v_dis_loss VARCHAR2(200); 
    v_drawl_units  number; 
    v_comp_id VARCHAR2(200); 
        v_bank_service_id VARCHAR2(200); 

    v_banking_count VARCHAR2(50); 
        v_inj_units1 VARCHAR2(200); 
    v_inj_units2 VARCHAR2(200); 
    v_inj_units3 VARCHAR2(200); 
    v_inj_units4 VARCHAR2(200); 
    v_inj_units5 VARCHAR2(200); 
    v_log_result varchar(300):='SUCCESS';
    v_exception_code VARCHAR2(150);
    v_exception_msg  VARCHAR2(150);
    v_reason VARCHAR2(300);
    v_excess_unit_type varchar2(100);



  BEGIN
    -- TODO: Implementation required for procedure BANKING_BALANCE.increment_surplus_units
                   -- DBMS_OUTPUT.PUT_LINE('start');

    v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.INCRMENT_SURPLUS_UNITS','START','Start - '||i_ht_surplus_units_id,'','', sysdate,i_ht_surplus_units_id);
     update int_surplus_unit set reading_mnth=lpad(reading_mnth,2,'0') where batch_key=i_ht_surplus_units_id;
     v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.INCRMENT_SURPLUS_UNITS','Cleanse','Complete','','', sysdate,i_ht_surplus_units_id);
    OPEN v_htsurcursor for select * from INT_SURPLUS_UNIT where BATCH_KEY=i_ht_surplus_units_id;-- and suplr_code='059224760427';
    LOOP
    FETCH v_htsurcursor INTO v_int_surplus_unit;
    EXIT WHEN v_htsurcursor%NOTFOUND;

      select voltage_code into v_inj_code from m_company_service where "number"=v_int_surplus_unit.SUPLR_CODE;
      select voltage_code into v_drawl_code from m_company_service where "number"=v_int_surplus_unit.SERVICE_NO;

      v_total_loss :=SURPLUS_ENERGY_LOSS_CALC(v_inj_code,v_drawl_code,v_int_surplus_unit.C1,v_trans_loss,v_dis_loss,v_total_loss,v_inj_units1);
      v_total_loss :=SURPLUS_ENERGY_LOSS_CALC(v_inj_code,v_drawl_code,v_int_surplus_unit.C2,v_trans_loss,v_dis_loss,v_total_loss,v_inj_units2);
      v_total_loss :=SURPLUS_ENERGY_LOSS_CALC(v_inj_code,v_drawl_code,v_int_surplus_unit.C3,v_trans_loss,v_dis_loss,v_total_loss,v_inj_units3);
      v_total_loss :=SURPLUS_ENERGY_LOSS_CALC(v_inj_code,v_drawl_code,v_int_surplus_unit.C4,v_trans_loss,v_dis_loss,v_total_loss,v_inj_units4);
      v_total_loss :=SURPLUS_ENERGY_LOSS_CALC(v_inj_code,v_drawl_code,v_int_surplus_unit.C5,v_trans_loss,v_dis_loss,v_total_loss,v_inj_units5);

     -- select M_COMPANY_ID into v_comp_id from m_company_service where "number"=v_int_surplus_unit.SUPLR_CODE;
     -- select BANKING_SERVICE_ID into v_bank_service_id from m_company_service where "number"=v_int_surplus_unit.SUPLR_CODE;

      UPDATE INT_SURPLUS_UNIT SET C1_WITHLOSS=round(nvl(v_inj_units1,0),0),C2_WITHLOSS=round(nvl(v_inj_units2,0),0),C3_WITHLOSS=round(nvl(v_inj_units3,0),0),C4_WITHLOSS=round(nvl(v_inj_units4,0),0),C5_WITHLOSS=round(nvl(v_inj_units5,0),0)
      WHERE ID = v_int_surplus_unit.ID;
                       --   DBMS_OUTPUT.PUT_LINE(' v_comp_id--'||v_comp_id);

    END LOOP;

    v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.INCRMENT_SURPLUS_UNITS','Completed Loss Calculation','','','', sysdate,i_ht_surplus_units_id);
    FOR supplier in (  select ser."number" SUPLR_CODE, ser.M_COMPANY_ID,ser.BANKING_SERVICE_ID, READING_MNTH, READING_YR,sum(s.C1_WITHLOSS) c1_wl, sum(s.C2_WITHLOSS) c2_wl,sum(s.C3_WITHLOSS) c3_wl, sum(s.C4_WITHLOSS) c4_wl,sum(s.C5_WITHLOSS) c5_wl 
                        from  INT_SURPLUS_UNIT s join m_company_service ser on ser."number"=s.suplr_code  where batch_key=i_ht_surplus_units_id 
                     --and ser."number"='059224760427'
                        group by ser."number" ,ser.M_COMPANY_ID,ser.BANKING_SERVICE_ID,READING_MNTH, READING_YR)
    loop
      begin

         select excess_unit_type into v_excess_unit_type from v_company_service where "number"=supplier.SUPLR_CODE;
         if(v_excess_unit_type<>'BANKING') then
             UPDATE INT_SURPLUS_UNIT SET imported='N', import_remarks='Excess_unit_type -'||v_excess_unit_type||' Banking Balance not applicable for  suppler_code-'||supplier.SUPLR_CODE||',reading_month-'||supplier.reading_mnth||',reading_year-'||supplier.reading_yr where batch_key=i_ht_surplus_units_id and SUPLR_CODE = supplier.SUPLR_CODE;

            v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.INCRMENT_SURPLUS_UNITS','Update Banking Balance','Excess_unit_type -'||v_excess_unit_type||' Banking Balance not applicable for  suppler_code-'||supplier.SUPLR_CODE||',reading_month-'||supplier.reading_mnth||',reading_year-'||supplier.reading_yr,'','', sysdate,supplier.SUPLR_CODE);
         else

          select count(*) into v_banking_count from T_BANKING_BALANCE where M_COMPANY_ID = supplier.M_COMPANY_ID and BANKING_SERVICE_ID=supplier.BANKING_SERVICE_ID and MONTH=supplier.READING_MNTH AND YEAR=supplier.READING_YR;
                    --  DBMS_OUTPUT.PUT_LINE(' v_banking_count--'||v_banking_count);


          if(v_banking_count>0) then 
               v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.INCRMENT_SURPLUS_UNITS','Update Banking Balance',supplier.BANKING_SERVICE_ID,'','', sysdate,i_ht_surplus_units_id);
               update T_BANKING_BALANCE set curr_c1=round(nvl(curr_c1,0),0)+round(supplier.c1_wl,0),curr_C2=round(nvl(curr_c2,0),0)+round(supplier.c2_wl,0),curr_C3=round(nvl(curr_c3,0),0)+round(supplier.c3_wl,0),curr_C4=round(nvl(curr_c4,0),0)+round(supplier.c4_wl,0),curr_C5=round(nvl(curr_c5,0),0)+round(supplier.c5_wl,0),
               modified_by=v_created_By, modified_dt =sysdate where (M_COMPANY_ID = supplier.M_COMPANY_ID or BANKING_SERVICE_ID=supplier.BANKING_SERVICE_ID)  AND MONTH=supplier.READING_MNTH AND YEAR=supplier.READING_YR;

          else 
               v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.INCRMENT_SURPLUS_UNITS','Insert Banking Balance',supplier.BANKING_SERVICE_ID,'','', sysdate,i_ht_surplus_units_id);
                insert into T_BANKING_BALANCE (ID,M_COMPANY_ID,BANKING_SERVICE_ID,C1,C2,C3,C4,C5,REMARKS,MONTH,YEAR,curr_C1,curr_C2,curr_C3,curr_C4,curr_C5, CREATED_BY,CREATED_DT)
                  values (T_BANKING_BALANCE_SEQ.NEXTVAL,supplier.M_COMPANY_ID,supplier.BANKING_SERVICE_ID,nvl(supplier.c1_wl,0),nvl(supplier.c2_wl,0),nvl(supplier.c3_wl,0),nvl(supplier.c4_wl,0),nvl(supplier.c5_wl,0),i_ht_surplus_units_id,supplier.READING_MNTH,supplier.READING_YR,round(nvl(supplier.c1_wl,0),0),round(nvl(supplier.c2_wl,0),0),round(nvl(supplier.c3_wl,0),0),round(nvl(supplier.c4_wl,0),0),round(nvl(supplier.c5_wl,0),0),v_created_By,sysdate);
          END IF;

        end if;
--           if(v_banking_count>0) then 
--               v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.INCRMENT_SURPLUS_UNITS','Update Banking Balance',supplier.BANKING_SERVICE_ID,'','', sysdate,i_ht_surplus_units_id);
--               update T_BANKING_BALANCE set SURPLUS_C1=round(supplier.c1_wl,0),SURPLUS_C2=round(supplier.c2_wl,0),SURPLUS_C3=round(supplier.c3_wl,0),SURPLUS_C4=round(supplier.c4_wl,0),SURPLUS_C5=round(supplier.c5_wl,0),
--               modified_by='BANKING_BALANCE.INCRMENT_SURPLUS_UNITS', modified_dt =sysdate where (M_COMPANY_ID = supplier.M_COMPANY_ID or BANKING_SERVICE_ID=supplier.BANKING_SERVICE_ID)  AND MONTH=supplier.READING_MNTH AND YEAR=supplier.READING_YR;
--  
--          else 
--               v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.INCRMENT_SURPLUS_UNITS','Insert Banking Balance',supplier.BANKING_SERVICE_ID,'','', sysdate,i_ht_surplus_units_id);
--                insert into T_BANKING_BALANCE (ID,M_COMPANY_ID,BANKING_SERVICE_ID,C1,C2,C3,C4,C5,REMARKS,MONTH,YEAR,curr_C1,curr_C2,curr_C3,curr_C4,curr_C5,SURPLUS_C1,SURPLUS_C2,SURPLUS_C3,SURPLUS_C4,SURPLUS_C5, CREATED_BY,CREATED_DT)
--                  values (T_BANKING_BALANCE_SEQ.NEXTVAL,supplier.M_COMPANY_ID,supplier.BANKING_SERVICE_ID,round(nvl(supplier.c1_wl,0),0),round(nvl(supplier.c2_wl,0),0),round(nvl(supplier.c3_wl,0),0),round(nvl(supplier.c4_wl,0),0),round(nvl(supplier.c5_wl,0),0),i_ht_surplus_units_id,supplier.READING_MNTH,supplier.READING_YR,round(nvl(supplier.c1_wl,0),0),round(nvl(supplier.c2_wl,0),0),round(nvl(supplier.c3_wl,0),0),round(nvl(supplier.c4_wl,0),0),round(nvl(supplier.c5_wl,0),0),round(nvl(supplier.c1_wl,0),0),round(nvl(supplier.c2_wl,0),0),round(nvl(supplier.c3_wl,0),0),round(nvl(supplier.c4_wl,0),0),round(nvl(supplier.c5_wl,0),0),'BANKING_BALANCE.INCRMENT_SURPLUS_UNITS',sysdate);
--          END IF;
       exception
        when others then
          v_exception_code := SQLCODE;
          v_exception_msg := SUBSTR(SQLERRM, 1, 200);
          o_result_code := 'FAILURE';
          o_result_desc := v_exception_code || ' - ' || v_exception_msg;
          -- dbms_output.put_line(o_result_desc);
          v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.INCRMENT_SURPLUS_UNITS','EXCEPTION',o_result_desc,supplier.BANKING_SERVICE_ID,'', sysdate,i_ht_surplus_units_id);
           UPDATE INT_SURPLUS_UNIT SET imported='N', import_remarks=o_result_desc where batch_key=i_ht_surplus_units_id and SUPLR_CODE = supplier.SUPLR_CODE;
       END;
    END LOOP;

     v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.INCRMENT_SURPLUS_UNITS','End','End','','', sysdate,i_ht_surplus_units_id);
  END increment_surplus_units;

  procedure reset_for_yearend( i_service_no in varchar2,i_reading_month in varchar2, i_reading_year in varchar2,o_result_code out varchar2, o_result_desc out varchar2) AS
     v_created_By  VARCHAR2(50):= 'BANKING_BALANCE.RESET_FOR_YEAREND';
    v_exception_code  NUMBER;
    v_exception_msg  VARCHAR2(200);
    v_log_result varchar(300):='SUCCESS';
    v_prev_month varchar2(10);
    v_prev_year varchar2(10);
    v_current_month varchar2(10);
    v_servicecursor sys_refcursor ;
    v_bb_new T_BANKING_BALANCE%ROWTYPE;
    v_seb_new T_NEW_SERVICE_EXCESS_BANKING%ROWTYPE;
    v_comp_service v_company_service%ROWTYPE;
    v_count number;
    v_excess_units_type varchar2(200);
  BEGIN
    -- TODO: Implementation required for procedure BANKING_BALANCE.reset_for_yearend
    BEGIN
     -- fetch previous month record to get closing balance]
     v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.reset_for_yearend','start - '||i_reading_month||'-'||i_reading_year,o_result_desc,'','', sysdate,i_service_no);
   --  select to_char(sysdate,'mm') into v_current_month from dual;
   --  v_prev_month := to_char(to_date( '01-'||i_reading_month||'-'||i_reading_year,'dd-mm-yyyy')-1,'mm');
   --  v_prev_year := to_char(to_date( '01-'||i_reading_month||'-'||i_reading_year,'dd-mm-yyyy')-1,'yyyy');
     if i_reading_month='04' then  

         v_excess_units_type:=excess_units.FIND_TYPE_FN(i_service_no);

		 IF (v_excess_units_type='BANKING' OR v_excess_units_type = 'SURPLUS-EXCESS') THEN

		 	SELECT * INTO v_comp_service FROM V_COMPANY_SERVICE where "number" =i_service_no;
			if(v_excess_units_type='BANKING') THEN
				-- create balance in banking
				v_bb_new.M_COMPANY_ID := v_comp_service.M_COMPANY_ID;
	            v_bb_new.BANKING_SERVICE_ID := v_comp_service.BANKING_SERVICE_ID;
	            v_bb_new.enabled:='Y';
	            v_bb_new.CALCULATED:='Y';
	            v_bb_new.month:=i_reading_month;
	            v_bb_new.year:=i_reading_year;
	            v_bb_new.CREATED_DATE:=sysdate;
	            v_bb_new.CREATED_BY:=v_created_By;
	            v_bb_new.id:=T_BANKING_BALANCE_SEQ.NEXTVAL;
	            select count(*) into v_count from t_banking_balance where month=i_reading_month and year=i_reading_year and M_COMPANY_ID=v_comp_service.M_COMPANY_ID;
	            if v_count=0 then
		            select  '0','0','0','0','0', '0','0','0','0','0', '0','0','0','0','0'
		            into  v_bb_new.C1,v_bb_new.C2,v_bb_new.C3,v_bb_new.C4,v_bb_new.C5,v_bb_new.CURR_C1,v_bb_new.CURR_C2,v_bb_new.CURR_C3,v_bb_new.CURR_C4,v_bb_new.CURR_C5,v_bb_new.SURPLUS_C1,v_bb_new.SURPLUS_C2,v_bb_new.SURPLUS_C3,v_bb_new.SURPLUS_C4,v_bb_new.SURPLUS_C5 from dual;
		            insert into t_banking_balance values v_bb_new;
		        else
		            update t_banking_balance set C1='0',C2='0',C3='0',C4='0',C5='0',CURR_C1='0',CURR_C2='0',CURR_C3='0',CURR_C4='0',CURR_C5='0',SURPLUS_C1='0',SURPLUS_C2='0',SURPLUS_C3='0',SURPLUS_C4='0',SURPLUS_C5='0',modified_by=v_created_By,modified_dt=sysdate WHERE month=i_reading_month and year=i_reading_year and M_COMPANY_ID=v_comp_service.M_COMPANY_ID;            
	            end if;
			ELSE
			-- create balance in surplus-excess (i.e) in T_NEW_SERVICE_EXCESS_BANKING
				v_seb_new.M_COMPANY_ID := v_comp_service.M_COMPANY_ID;
	            v_seb_new.BANKING_SERVICE_ID := v_comp_service.BANKING_SERVICE_ID;
	            v_seb_new.enabled:='Y';
	            v_seb_new.CALCULATED:='Y';
	            v_seb_new.month:=i_reading_month;
	            v_seb_new.year:=i_reading_year;
	            v_seb_new.CREATED_DATE:=sysdate;
	            v_seb_new.CREATED_BY:=v_created_By;
	            v_seb_new.id:=T_BANKING_BALANCE_SEQ.NEXTVAL;
	            select count(*) into v_count from T_NEW_SERVICE_EXCESS_BANKING where month=i_reading_month and year=i_reading_year and M_COMPANY_ID=v_comp_service.M_COMPANY_ID;
	            if v_count=0 then
		            select  '0','0','0','0','0', '0','0','0','0','0', '0','0','0','0','0'
		            into  v_seb_new.C1,v_seb_new.C2,v_seb_new.C3,v_seb_new.C4,v_seb_new.C5,v_seb_new.CURR_C1,v_seb_new.CURR_C2,v_seb_new.CURR_C3,v_seb_new.CURR_C4,v_seb_new.CURR_C5,v_seb_new.SURPLUS_C1,v_seb_new.SURPLUS_C2,v_seb_new.SURPLUS_C3,v_seb_new.SURPLUS_C4,v_seb_new.SURPLUS_C5 from dual;
		            insert into T_NEW_SERVICE_EXCESS_BANKING values v_seb_new;
		        else
		            update T_NEW_SERVICE_EXCESS_BANKING set C1='0',C2='0',C3='0',C4='0',C5='0',CURR_C1='0',CURR_C2='0',CURR_C3='0',CURR_C4='0',CURR_C5='0',SURPLUS_C1='0',SURPLUS_C2='0',SURPLUS_C3='0',SURPLUS_C4='0',SURPLUS_C5='0',modified_by=v_created_By,modified_dt=sysdate WHERE month=i_reading_month and year=i_reading_year and M_COMPANY_ID=v_comp_service.M_COMPANY_ID;            
	            end if;
			END IF;

            o_result_code := 'SUCCESS';
            o_result_desc:=i_service_no||'-'||v_excess_units_type;
		 ELSE
		          v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.reset_for_yearend','Cannot reset banking for service'||i_service_no||' as excess-units-type is '||v_excess_units_type||' for '||i_reading_month||'-'||i_reading_year,o_result_desc,'','', sysdate,i_service_no);
                  o_result_code := 'FAILURE';
                  o_result_desc := 'Cannot reset banking for service'||i_service_no||' as excess-units-type is '||v_excess_units_type;


		 END if;
     ELSE
         v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.reset_for_yearend','Cannot reset service'||i_service_no||' for '||i_reading_month||'-'||i_reading_year,o_result_desc,'','', sysdate,i_service_no);

     end if;

          v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.reset_for_yearend','End - '||i_service_no||'-'||i_reading_month||'-'||i_reading_year,o_result_desc,'','', sysdate,i_service_no);
     exception
     when others then
          v_exception_code := SQLCODE;
          v_exception_msg := SUBSTR(SQLERRM, 1, 200);
          o_result_code := 'FAILURE';
          o_result_desc := v_exception_code || ' - ' || v_exception_msg;
          -- dbms_output.put_line(o_result_desc);
          v_log_result := log_activity('PROCEDURE','BANKING_BALANCE.reset_for_yearend','EXCEPTION',o_result_desc,'','', sysdate,'');

       END;            

  END reset_for_yearend;

END BANKING_BALANCE;
