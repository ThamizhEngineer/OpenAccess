CREATE OR REPLACE FUNCTION OPENACCESS."ENERGY_SALE_CONFIRMATION" 
(V_ES_ID VARCHAR2) RETURN VARCHAR2 
AS   
    v_process_name  VARCHAR2(500):= 'ENERGY_SALE_CONFIRMATION';
     V_SELLER_COMP_SERVICE V_COMPANY_SERVICE%ROWTYPE;
    V_BUYER_COMP_SERVICE V_COMPANY_SERVICE%ROWTYPE;
    V_ES T_ENERGY_SALE%ROWTYPE;
    V_ES_USAGESUMMARY T_ES_USAGE_SUMMARY%ROWTYPE;
    V_GS T_GEN_STMT%ROWTYPE;
    V_ES_ORDER F_ENERGY_SALE_ORDER%ROWTYPE;
    V_ES_ORDERLINE F_ENERGY_SALE_ORDER_LINES%ROWTYPE;
    V_ES_ORDERLINE_LEDGER F_ENERGY_SALE_ORDER_LINES%ROWTYPE;
    V_ENERGY_CHARGE F_ENERGY_CHARGES%ROWTYPE;
    V_GEN_STMT_ENERGY_CHARGE F_ENERGY_CHARGES%ROWTYPE;
    V_ES_CHARGE T_ES_CHARGE%ROWTYPE;
    V_GS_CHARGE T_GEN_STMT_CHARGE%ROWTYPE;
    V_NEW_SERV_EX_BANKING T_NEW_SERVICE_EXCESS_BANKING%ROWTYPE;
   -- V_RESULT VARCHAR(300):='SUCCESS';
  --  V_REASON VARCHAR2(200); 
   -- V_EXCEPTION_CODE VARCHAR2(150);
   -- V_EXCEPTION_MSG  VARCHAR2(150);
    V_ES_USAGESUMMARY_CURSOR sys_refcursor ;
    V_ES_ORDERLINE_CURSOR sys_refcursor ;
    V_GC_CHARGE_CURSOR sys_refcursor ;
    V_ES_CHARGE_CURSOR sys_refcursor ;
    CHARGE_COMP_SERVICE_ID VARCHAR2(200); 
    CHARGE_CODE VARCHAR2(200); 
    CHARGE_DESC VARCHAR2(200); 
    TOTAL_CHARGE VARCHAR2(200); 
    V_ENERGY_LEDGER F_ENERGY_LEDGER%ROWTYPE;
    V_ENERGY_LEDGER_BANK F_ENERGY_LEDGER%ROWTYPE;
    V_ENERGY_LEDGER_BUYER F_ENERGY_LEDGER%ROWTYPE;
    V_ENERGY_LEDGER_SELLER_TL F_ENERGY_LEDGER%ROWTYPE;
    V_ENERGY_LEDGER_SELLER_DL F_ENERGY_LEDGER%ROWTYPE;
    V_LOSS_CAL_BUYER_TRANS VARCHAR2(200);
    V_LOSS_CAL_BUYER_DIST VARCHAR2(200);
    V_LOSS_CAL_BUYER_TOTAL VARCHAR2(200); 
    V_LOSS_CAL_BUYER_DRAWAL1 VARCHAR2(200); 
    V_LOSS_CAL_BUYER_DRAWAL2 VARCHAR2(200); 
    V_LOSS_CAL_BUYER_DRAWAL3 VARCHAR2(200); 
    V_LOSS_CAL_BUYER_DRAWAL4 VARCHAR2(200); 
    V_LOSS_CAL_BUYER_DRAWAL5 VARCHAR2(200); 
    V_LOSS_CAL_BUYER_DRAWALOUT VARCHAR2(200); 

    V_LOSS_CAL_SELLER_TL_TOTAL VARCHAR2(200); 
    V_LOSS_CAL_SELLER_TL_DRAWAL VARCHAR2(200); 
    V_LOSS_CAL_SELLER_TL_DIST VARCHAR2(200);
    V_LOSS_CAL_SELLER_TL_TRANS1 VARCHAR2(200);   
    V_LOSS_CAL_SELLER_TL_TRANS2 VARCHAR2(200); 
    V_LOSS_CAL_SELLER_TL_TRANS3 VARCHAR2(200); 
    V_LOSS_CAL_SELLER_TL_TRANS4 VARCHAR2(200); 
    V_LOSS_CAL_SELLER_TL_TRANS5 VARCHAR2(200); 
    V_LOSS_CAL_SELLER_TL_TRANSOUT VARCHAR2(200); 

    V_LOSS_CAL_SELLER_DL_TOTAL VARCHAR2(200); 
    V_LOSS_CAL_SELLER_DL_DRAWAL VARCHAR2(200); 
    V_LOSS_CAL_SELLER_DL_TRANS VARCHAR2(200);
    V_LOSS_CAL_SELLER_DL_DIST1 VARCHAR2(200);
    V_LOSS_CAL_SELLER_DL_DIST2 VARCHAR2(200); 
    V_LOSS_CAL_SELLER_DL_DIST3 VARCHAR2(200); 
    V_LOSS_CAL_SELLER_DL_DIST4 VARCHAR2(200); 
    V_LOSS_CAL_SELLER_DL_DIST5 VARCHAR2(200); 
    V_LOSS_CAL_SELLER_DL_DISTOUT VARCHAR2(200); 
    v_result varchar(300):='SUCCESS';
    v_log_result varchar(300):='SUCCESS';
    v_exception_code VARCHAR2(150);
    v_exception_msg  VARCHAR2(150);
    v_reason VARCHAR2(300);
    v_excess_es VARCHAR2(300);
    v_excess_es1 VARCHAR2(300);
    v_excess_es2 VARCHAR2(300);
  ------------------------------------------------  
    v_sale_order_count number:=0;
    v_alloc_cons_count number :=0;
    v_charge_count number:=0;
    v_commission_date  DATE;
    v_adj_priority  VARCHAR2(150);
    --Dates
    v_date_47  DATE;v_date44_1 DATE;v_date44_2 DATE;
    v_date_46_1 date;v_date_46_2 date;v_date_45_1 date;v_date_45_2 date;
    v_date_43_1 date;v_date_43_2 date;v_date_42_1 date;v_date_42_2 date;
    v_date_41_1 date;v_date_41_2 date;
  ------------------------------------------------
    V_BB T_BANKING_BALANCE%ROWTYPE;
    V_C1 NUMBER:=0;
    V_C2 NUMBER:=0;
    V_C3 NUMBER:=0;
    V_C4 NUMBER:=0;
    V_C5 NUMBER:=0;
    V_UNALLOCATED_GEN_FLAG boolean:=false;
    V_ALLOCATED_BANK_FLAG boolean:=false;

    V_NEW_C1 NUMBER:=0;
    V_NEW_C2 NUMBER:=0;
    V_NEW_C3 NUMBER:=0;
    V_NEW_C4 NUMBER:=0;
    V_NEW_C5 NUMBER:=0;
    V_NEW_SERV_EXCESS_FLAG boolean:=false;
BEGIN  
   BEGIN
     SELECT * INTO V_ES FROM T_ENERGY_SALE WHERE ID = V_ES_ID;
       -- -- dbms_output.put_line(V_ES.STATUS_CODE);


       IF (V_ES.STATUS_CODE='CREATED' ) THEN
         update T_ENERGY_SALE set status_code = 'PROCESSING' where id = V_ES_ID;
         commit;

         SELECT * INTO V_SELLER_COMP_SERVICE FROM V_COMPANY_SERVICE WHERE ID=V_ES.SELLER_COMP_SERV_ID;
v_log_result := log_activity('PROCEDURE','ENERGY_SALE_CONFIRMATION','V_ES.STATUS_CODE',V_ES.STATUS_CODE, v_reason,'admin', sysdate,v_es_id);
         -----------------------------------Commsion date wise prioroty-----------------------------------      
    IF(V_SELLER_COMP_SERVICE.COMMISSION_DATE is not null) THEN

        v_commission_date := V_SELLER_COMP_SERVICE.COMMISSION_DATE;
        v_date_47 := to_date('15/05/2006','dd-mm-yyyy');v_date_46_1 := to_date('14/05/2006','dd-mm-yyyy');v_date_45_2 := to_date('20/03/2009','dd-mm-yyyy');
        v_date44_1 := to_date('31/07/2012','dd-mm-yyyy');v_date_46_2 := to_date('19/03/2009','dd-mm-yyyy');v_date_43_1 := to_date('30/03/2016','dd-mm-yyyy');
        v_date44_2 := to_date('01/04/2009','dd-mm-yyyy');v_date_45_1 := to_date('31/03/2009','dd-mm-yyyy');v_date_43_2 := to_date('01/08/2012','dd-mm-yyyy');
        v_date_42_1 := to_date('31/03/2018','dd-mm-yyyy');v_date_42_2 := to_date('31/03/2016','dd-mm-yyyy');v_date_41_1 := to_date('01/04/2018','dd-mm-yyyy');

        if(v_commission_date <= v_date_47) then
         v_adj_priority:='57';
        ELSIF(v_commission_date BETWEEN v_date_46_1 AND v_date_46_2) then
         v_adj_priority:='56';
        ELSIF(v_commission_date BETWEEN v_date_45_2 AND v_date_45_1) then
         v_adj_priority:='55'; 
        ELSIF(v_commission_date BETWEEN v_date44_2 AND v_date44_1) then
         v_adj_priority:='54';
        ELSIF(v_commission_date BETWEEN v_date_43_2 AND v_date_43_1) then
         v_adj_priority:='53'; 
        ELSIF(v_commission_date BETWEEN v_date_42_2 AND  v_date_42_1) then
         v_adj_priority:='52';
        ELSIF(v_commission_date >= v_date_41_1) then
         v_adj_priority:='51'; 

         end if;
             v_log_result := log_activity('PROCEDURE','ENERGY_SALE_CONFIRMATION',v_date_47,'COMM-DATE - '||v_commission_date,v_adj_priority,'admin', sysdate,v_es_id);

       END IF; 

       ------------------------------------------------------------------------------------------------------------


         SELECT V_ES.ID,V_ES.SELLER_COMP_SERV_ID,V_ES.SELLER_END_ORG_ID,V_ES.MONTH,V_ES.YEAR,V_ES.INJECTING_VOLTAGE_CODE,V_ES.FROM_DT,V_ES.TO_DT,V_ES.LOSS,
         V_ES.MULTIPLE_BUYERS,V_ES.USAGE_DETAIL_AVAIL,V_ES.SIMPLE_ENERGY_SALE,V_ES.C1,V_ES.C2,V_ES.C3,V_ES.C4,V_ES.C5,V_ES.NET_GENERATION,V_ES.BC1,V_ES.BC2,V_ES.BC3,V_ES.BC4,V_ES.BC5,V_ES.TOTAL_BANK_UNITS_USED,V_ES.NET_ALLOCATION,
             V_SELLER_COMP_SERVICE.M_COMPANY_NAME,V_SELLER_COMP_SERVICE.M_COMPANY_CODE,V_SELLER_COMP_SERVICE."number",V_SELLER_COMP_SERVICE.M_ORG_NAME,V_SELLER_COMP_SERVICE.M_ORG_CODE,V_ES.GC1,V_ES.GC2,V_ES.GC3,V_ES.GC4,V_ES.GC5,V_SELLER_COMP_SERVICE.BANKING_SERVICE_ID,V_SELLER_COMP_SERVICE.BANKING_SERVICE_NUMBER,V_SELLER_COMP_SERVICE.M_COMPANY_ID,V_ES.ALLOW_LOWER_SLOT_ADMT, v_process_name, sysdate INTO 
         V_ES_ORDER.T_ENERGY_SALE_ID,V_ES_ORDER.SELLER_COMP_SERV_ID,V_ES_ORDER.SELLER_END_ORG_ID,V_ES_ORDER.MONTH,V_ES_ORDER.YEAR,V_ES_ORDER.INJECTING_VOLTAGE_CODE,V_ES_ORDER.FROM_DT,V_ES_ORDER.TO_DT,V_ES_ORDER.LOSS,
         V_ES_ORDER.MULTIPLE_BUYERS,V_ES_ORDER.USAGE_DETAIL_AVAIL,V_ES_ORDER.SIMPLE_ENERGY_SALE,V_ES_ORDER.TOTAL_C1,V_ES_ORDER.TOTAL_C2,V_ES_ORDER.TOTAL_C3,V_ES_ORDER.TOTAL_C4,V_ES_ORDER.TOTAL_C5,V_ES_ORDER.TOTAL_GEN_UNITS_SOLD,V_ES_ORDER.TOTAL_BC1,V_ES_ORDER.TOTAL_BC2,V_ES_ORDER.TOTAL_BC3,V_ES_ORDER.TOTAL_BC4,V_ES_ORDER.TOTAL_BC5,V_ES_ORDER.TOTAL_BANKING_UNITS_SOLD,V_ES_ORDER.TOTAL_UNITS_SOLD,
         V_ES_ORDER.D_SELL_COMP_NAME,V_ES_ORDER.D_SELL_COMP_CODE,V_ES_ORDER.D_SELL_COMP_SERV_NUMBER,V_ES_ORDER.D_SELL_ORG_NAME,V_ES_ORDER.D_SELL_ORG_CODE,V_ES_ORDER.TOTAL_GC1,V_ES_ORDER.TOTAL_GC2,V_ES_ORDER.TOTAL_GC3,V_ES_ORDER.TOTAL_GC4,V_ES_ORDER.TOTAL_GC5,V_ES_ORDER.BANKING_SERVICE_ID,V_ES_ORDER.BANKING_SERVICE_NUMBER,V_ES_ORDER.SELLER_COMP_ID,V_ES_ORDER.ALLOW_LOWER_SLOT_ADMT, V_ES_ORDER.created_by, V_ES_ORDER.CREATED_DATE     FROM DUAL;

        -- check if sale-order already exists for the energysale
         select count(*) into v_sale_order_count from F_ENERGY_SALE_ORDER 
               where T_ENERGY_SALE_ID = V_ES_ORDER.T_ENERGY_SALE_ID;
v_log_result := log_activity('PROCEDURE','ENERGY_SALE_CONFIRMATION','v_sale_order_count',v_sale_order_count, v_reason,'admin', sysdate,v_es_id);
         --avoid duplicate saleorder                                                                                                                        
         if(v_sale_order_count=0) then
             V_ES_ORDER.ID := F_ENERGY_SALE_ORDER_SEQ.NEXTVAL; 
             INSERT INTO OPENACCESS.F_ENERGY_SALE_ORDER VALUES V_ES_ORDER;  
             v_log_result := log_activity('PROCEDURE','ENERGY_SALE_CONFIRMATION','  V_ES_ORDER.ID ',  V_ES_ORDER.ID , v_reason,'admin', sysdate,v_es_id);
        else
             v_result:='FAILURE';
             v_reason:='Energy Sale Order already exists for '||v_es_id;
             v_log_result := log_activity('PROCEDURE','ENERGY_SALE_CONFIRMATION','Issue',v_result, v_reason,'admin', sysdate,v_es_id);
             GOTO THE_END;
        end if;

            OPEN V_ES_USAGESUMMARY_CURSOR for SELECT * FROM T_ES_USAGE_SUMMARY WHERE T_ENERGY_SALE_ID = V_ES_ID and BUYER_COMP_SERV_ID is not null;

            LOOP
            FETCH V_ES_USAGESUMMARY_CURSOR INTO V_ES_USAGESUMMARY;

            SELECT * INTO V_BUYER_COMP_SERVICE FROM V_COMPANY_SERVICE WHERE ID=V_ES_USAGESUMMARY.BUYER_COMP_SERV_ID;

            EXIT WHEN V_ES_USAGESUMMARY_CURSOR%NOTFOUND;
            v_alloc_cons_count := v_alloc_cons_count +1;

            V_ES_ORDERLINE.ID := F_ENERGY_SALE_ORDER_LINE_SEQ.NEXTVAL;  
             -- -- dbms_output.put_line('V_ES_ORDERLINE'||V_ES_ORDERLINE.ID);
             
           --  trim(v_es_orderline.c1);
           --trim(v_es_orderline.c1);
           SELECT V_ES_ID,V_ES_ORDER.ID,V_ES_USAGESUMMARY.BUYER_END_ORG_ID,V_BUYER_COMP_SERVICE.M_SUBSTATION_ID,V_ES_USAGESUMMARY.BUYER_COMP_SERV_ID,trim(V_ES_USAGESUMMARY.C1),trim(V_ES_USAGESUMMARY.C2),trim(V_ES_USAGESUMMARY.C3),trim(V_ES_USAGESUMMARY.C4),trim(V_ES_USAGESUMMARY.C5),V_ES_USAGESUMMARY.TOTAL,V_BUYER_COMP_SERVICE.M_COMPANY_NAME,V_BUYER_COMP_SERVICE.M_COMPANY_CODE,
            V_BUYER_COMP_SERVICE."number",V_BUYER_COMP_SERVICE.M_ORG_NAME,V_BUYER_COMP_SERVICE.M_ORG_CODE,V_BUYER_COMP_SERVICE.M_SUBSTATION_NAME,V_BUYER_COMP_SERVICE.M_COMPANY_ID,V_BUYER_COMP_SERVICE.VOLTAGE_CODE,V_ES_USAGESUMMARY.UNIT_COST,V_ES_USAGESUMMARY.TOTAL_AMOUNT_PAYABLE,V_ES_USAGESUMMARY.TOTAL_AMOUNT_CHARGABLE,V_ES_USAGESUMMARY.NET_AMOUNT_PAYABLE INTO 
            V_ES_ORDERLINE.T_ENERGY_SALE_ID,V_ES_ORDERLINE.F_ENERGY_SALE_ORDER_ID,V_ES_ORDERLINE.BUYER_END_ORG_ID,V_ES_ORDERLINE.BUYER_END_SS_ID,V_ES_ORDERLINE.BUYER_COMP_SERV_ID,
            V_ES_ORDERLINE.C1,V_ES_ORDERLINE.C2,V_ES_ORDERLINE.C3,V_ES_ORDERLINE.C4,V_ES_ORDERLINE.C5,V_ES_ORDERLINE.TOTAL_UNITS_SOLD,V_ES_ORDERLINE.D_BUYER_COMP_NAME,V_ES_ORDERLINE.D_BUYER_COMP_CODE,V_ES_ORDERLINE.D_BUYER_COMP_SERV_NAME,V_ES_ORDERLINE.D_BUYER_ORG_NAME,V_ES_ORDERLINE.D_BUYER_ORG_CODE,V_ES_ORDERLINE.D_BUYER_SS_NAME,V_ES_ORDERLINE.BUYER_COMP_ID,V_ES_ORDERLINE.D_DRAWAL_VOLTAGE_CODE,V_ES_ORDERLINE.UNIT_COST,V_ES_ORDERLINE.TOTAL_AMOUNT_PAYABLE,V_ES_ORDERLINE.TOTAL_AMOUNT_CHARGABLE,V_ES_ORDERLINE.NET_AMOUNT_PAYABLE FROM DUAL;         

            IF V_ES.IS_STB='Y' THEN
              V_ES_ORDERLINE.D_DRAWAL_VOLTAGE_CODE := V_ES_ORDER.INJECTING_VOLTAGE_CODE;
            END IF;
v_log_result := log_activity('PROCEDURE','ENERGY_SALE_CONFIRMATION','V_ES_ORDERLINE.ID ',V_ES_ORDERLINE.ID , v_reason,'admin', sysdate,v_es_id);
           INSERT INTO F_ENERGY_SALE_ORDER_LINES VALUES V_ES_ORDERLINE;  

            END LOOP;


            OPEN V_ES_CHARGE_CURSOR for SELECT ESCHARGE.M_COMP_SERV_ID,ESCHARGE.CHARGE_CODE,ESCHARGE.TOTAL_CHARGE,CHARGEDEFN.CHARGE_DESC FROM T_ES_CHARGE ESCHARGE  LEFT JOIN M_CHARGE_DEFN CHARGEDEFN ON ESCHARGE.CHARGE_CODE = CHARGEDEFN.CHARGE_CODE WHERE T_ENERGY_SALE_ID = V_ES_ID;

            LOOP
             FETCH V_ES_CHARGE_CURSOR INTO CHARGE_COMP_SERVICE_ID,CHARGE_CODE,TOTAL_CHARGE,CHARGE_DESC;
             EXIT WHEN V_ES_CHARGE_CURSOR%NOTFOUND;
             SELECT CHARGE_COMP_SERVICE_ID,CHARGE_CODE,TRANSLATE(TOTAL_CHARGE,'[~!@#$%^&*()_+=\\{}[\]:”;’<,>.\/?]','_'),CHARGE_DESC,V_ES_ORDER.ID INTO
             V_ENERGY_CHARGE.M_COMPANY_SERVICE_ID,V_ENERGY_CHARGE.CHARGE_CODE,V_ENERGY_CHARGE.TOTAL_CHARGES,V_ENERGY_CHARGE.CHARGE_DESC,V_ENERGY_CHARGE.F_ENERGY_SALE_ORDER_ID FROM DUAL;

         
             --check if a same charge already exists for the same saleorder,service-id
             select count(*) into v_charge_count from F_ENERGY_CHARGES where F_ENERGY_SALE_ORDER_ID=V_ENERGY_CHARGE.F_ENERGY_SALE_ORDER_ID and M_COMPANY_SERVICE_ID=V_ENERGY_CHARGE.M_COMPANY_SERVICE_ID 
             and CHARGE_CODE = V_ENERGY_CHARGE.CHARGE_CODE;
--v_log_result := log_activity('PROCEDURE','ENERGY_SALE_CONFIRMATION','v_charge_count ',v_charge_count , v_reason,'admin', sysdate,v_es_id);
             -- avoid duplicate
             if(v_charge_count=0) then
                V_ENERGY_CHARGE.ID := F_ENERGY_CHARGES_SEQ.NEXTVAL;  
                INSERT INTO F_ENERGY_CHARGES VALUES V_ENERGY_CHARGE; 
             end if;
            END LOOP;




              SELECT V_ES_ORDER.SELLER_END_ORG_ID,V_ES_ORDER.SELLER_COMP_ID,V_ES_ORDER.SELLER_COMP_SERV_ID,V_ES_ORDER.MONTH,V_ES_ORDER.YEAR,V_ES_ORDER.FROM_DT,V_ES_ORDER.TO_DT,V_ES_ORDER.TOTAL_GC1,
            V_ES_ORDER.TOTAL_GC2,V_ES_ORDER.TOTAL_GC3,V_ES_ORDER.TOTAL_GC4,V_ES_ORDER.TOTAL_GC5,V_ES_ORDER.TOTAL_GEN_UNITS_SOLD,V_ES_ORDER.ID,V_ES_ORDER.D_SELL_COMP_NAME,V_ES_ORDER.D_SELL_COMP_CODE,V_ES_ORDER.D_SELL_COMP_SERV_NUMBER,V_ES_ORDER.D_SELL_ORG_NAME,V_ES_ORDER.D_SELL_ORG_CODE,V_SELLER_COMP_SERVICE.COMMISSION_DATE,v_adj_priority,V_ES_ORDER.ALLOW_LOWER_SLOT_ADMT,V_SELLER_COMP_SERVICE.IS_REC INTO
            V_ENERGY_LEDGER.M_ORG_ID,V_ENERGY_LEDGER.M_COMPANY_ID,V_ENERGY_LEDGER.M_COMPANY_SERVICE_ID,V_ENERGY_LEDGER.MONTH,V_ENERGY_LEDGER.YEAR,V_ENERGY_LEDGER.FROM_DT,V_ENERGY_LEDGER.TO_DT,V_ENERGY_LEDGER.C1,
            V_ENERGY_LEDGER.C2,V_ENERGY_LEDGER.C3,V_ENERGY_LEDGER.C4,V_ENERGY_LEDGER.C5,V_ENERGY_LEDGER.ENERGY_IN,V_ENERGY_LEDGER.F_ENERGY_SALE_ORDER_ID,V_ENERGY_LEDGER.D_COMP_NAME,V_ENERGY_LEDGER.D_COMP_CODE,V_ENERGY_LEDGER.D_COMP_SERV_NUMBER,V_ENERGY_LEDGER.D_ORG_NAME,V_ENERGY_LEDGER.D_ORG_CODE,V_ENERGY_LEDGER.D_COMMISSION_DATE,V_ENERGY_LEDGER.D_ADJUSTMENT_PRIORITY,V_ENERGY_LEDGER.ALLOW_LOWER_SLOT_ADMT, V_ENERGY_LEDGER_BANK.D_IS_REC  FROM DUAL;

            V_ENERGY_LEDGER.LEDGER_DATE:= SYSDATE;
            V_ENERGY_LEDGER.SERVICE_TYPE_CODE:= '03';

            V_ENERGY_LEDGER.ID:=F_ENERGY_LEDGER_SEQ.NEXTVAL;
--            DBMS.OUTPUT.PUT_LINE(V_ENERGY_LEDGER.ALLOW_LOWER_SLOT_ADMT);
            INSERT INTO F_ENERGY_LEDGER VALUES V_ENERGY_LEDGER;
--v_log_result := log_activity('PROCEDURE','ENERGY_SALE_CONFIRMATION','v_charge_count ',v_charge_count , v_reason,'admin', sysdate,v_es_id);
            IF V_ES.TOTAL_BANK_UNITS_USED!=NULL AND V_ES.TOTAL_BANK_UNITS_USED>0 THEN

                SELECT V_ES_ORDER.SELLER_END_ORG_ID,V_ES_ORDER.SELLER_COMP_ID,V_ES_ORDER.SELLER_COMP_SERV_ID,V_ES_ORDER.MONTH,V_ES_ORDER.YEAR,V_ES_ORDER.FROM_DT,V_ES_ORDER.TO_DT,V_ES_ORDER.TOTAL_GC1,
                V_ES_ORDER.TOTAL_GC2,V_ES_ORDER.TOTAL_GC3,V_ES_ORDER.TOTAL_GC4,V_ES_ORDER.TOTAL_GC5,V_ES_ORDER.TOTAL_BANKING_UNITS_SOLD,V_ES_ORDER.ID,V_ES_ORDER.D_SELL_COMP_NAME,V_ES_ORDER.D_SELL_COMP_CODE,V_ES_ORDER.D_SELL_COMP_SERV_NUMBER,V_ES_ORDER.D_SELL_ORG_NAME,V_ES_ORDER.D_SELL_ORG_CODE,V_SELLER_COMP_SERVICE.COMMISSION_DATE,v_adj_priority,V_ES_ORDER.ALLOW_LOWER_SLOT_ADMT,V_SELLER_COMP_SERVICE.IS_REC INTO
                V_ENERGY_LEDGER_BANK.M_ORG_ID,V_ENERGY_LEDGER_BANK.M_COMPANY_ID,V_ENERGY_LEDGER_BANK.M_COMPANY_SERVICE_ID,V_ENERGY_LEDGER_BANK.MONTH,V_ENERGY_LEDGER_BANK.YEAR,V_ENERGY_LEDGER_BANK.FROM_DT,V_ENERGY_LEDGER_BANK.TO_DT,V_ENERGY_LEDGER_BANK.C1,
                V_ENERGY_LEDGER_BANK.C2,V_ENERGY_LEDGER_BANK.C3,V_ENERGY_LEDGER_BANK.C4,V_ENERGY_LEDGER_BANK.C5,V_ENERGY_LEDGER_BANK.ENERGY_IN,V_ENERGY_LEDGER_BANK.F_ENERGY_SALE_ORDER_ID,V_ENERGY_LEDGER_BANK.D_COMP_NAME,V_ENERGY_LEDGER_BANK.D_COMP_CODE,V_ENERGY_LEDGER_BANK.D_COMP_SERV_NUMBER,V_ENERGY_LEDGER_BANK.D_ORG_NAME,V_ENERGY_LEDGER_BANK.D_ORG_CODE,V_ENERGY_LEDGER_BANK.D_COMMISSION_DATE,V_ENERGY_LEDGER_BANK.D_ADJUSTMENT_PRIORITY,V_ENERGY_LEDGER_BANK.ALLOW_LOWER_SLOT_ADMT, V_ENERGY_LEDGER_BANK.D_IS_REC  FROM DUAL;

                V_ENERGY_LEDGER_BANK.LEDGER_DATE:= SYSDATE;
                V_ENERGY_LEDGER_BANK.SERVICE_TYPE_CODE:= '01';



                V_ENERGY_LEDGER_BANK.ID:=F_ENERGY_LEDGER_SEQ.NEXTVAL;
                INSERT INTO F_ENERGY_LEDGER VALUES V_ENERGY_LEDGER_BANK;

            END IF;


--            OPEN V_ES_ORDERLINE_CURSOR for SELECT * FROM F_ENERGY_SALE_ORDER_LINES WHERE F_ENERGY_SALE_ORDER_ID = '69';
            OPEN V_ES_ORDERLINE_CURSOR for SELECT * FROM F_ENERGY_SALE_ORDER_LINES WHERE T_ENERGY_SALE_ID = V_ES_ID;

            LOOP
            FETCH V_ES_ORDERLINE_CURSOR INTO V_ES_ORDERLINE_LEDGER;

            EXIT WHEN V_ES_ORDERLINE_CURSOR%NOTFOUND;



            V_ENERGY_LEDGER_BUYER.LEDGER_DATE:= SYSDATE;
            V_ENERGY_LEDGER_BUYER.SERVICE_TYPE_CODE:= '02';

            V_LOSS_CAL_BUYER_TOTAL :=LOSS_CALCULATION(V_ES_ORDER.INJECTING_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.D_DRAWAL_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.C1,V_LOSS_CAL_BUYER_TRANS,V_LOSS_CAL_BUYER_DIST,V_LOSS_CAL_BUYER_TOTAL,V_LOSS_CAL_BUYER_DRAWAL1);
            V_LOSS_CAL_BUYER_TOTAL :=LOSS_CALCULATION(V_ES_ORDER.INJECTING_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.D_DRAWAL_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.C2,V_LOSS_CAL_BUYER_TRANS,V_LOSS_CAL_BUYER_DIST,V_LOSS_CAL_BUYER_TOTAL,V_LOSS_CAL_BUYER_DRAWAL2);
            V_LOSS_CAL_BUYER_TOTAL :=LOSS_CALCULATION(V_ES_ORDER.INJECTING_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.D_DRAWAL_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.C3,V_LOSS_CAL_BUYER_TRANS,V_LOSS_CAL_BUYER_DIST,V_LOSS_CAL_BUYER_TOTAL,V_LOSS_CAL_BUYER_DRAWAL3);
            V_LOSS_CAL_BUYER_TOTAL :=LOSS_CALCULATION(V_ES_ORDER.INJECTING_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.D_DRAWAL_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.C4,V_LOSS_CAL_BUYER_TRANS,V_LOSS_CAL_BUYER_DIST,V_LOSS_CAL_BUYER_TOTAL,V_LOSS_CAL_BUYER_DRAWAL4);
            V_LOSS_CAL_BUYER_TOTAL :=LOSS_CALCULATION(V_ES_ORDER.INJECTING_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.D_DRAWAL_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.C5,V_LOSS_CAL_BUYER_TRANS,V_LOSS_CAL_BUYER_DIST,V_LOSS_CAL_BUYER_TOTAL,V_LOSS_CAL_BUYER_DRAWAL5);
            V_LOSS_CAL_BUYER_TOTAL :=LOSS_CALCULATION(V_ES_ORDER.INJECTING_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.D_DRAWAL_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.TOTAL_UNITS_SOLD,V_LOSS_CAL_BUYER_TRANS,V_LOSS_CAL_BUYER_DIST,V_LOSS_CAL_BUYER_TOTAL,V_LOSS_CAL_BUYER_DRAWALOUT);
--           -- dbms_output.put_line('V_LOSS_CALCULATION_TOTAL'||V_LOSS_CAL_BUYER_TOTAL);
--           -- dbms_output.put_line('V_LOSS_CALCULATION_DIST'||V_LOSS_CAL_BUYER_DIST);
--           -- dbms_output.put_line('V_LOSS_CALCULATION_TRANS'||V_LOSS_CAL_BUYER_TRANS);
--           -- dbms_output.put_line('V_LOSS_CALCULATION_DRAWAL'||V_LOSS_CAL_BUYER_DRAWAL1||'-------'||V_LOSS_CAL_BUYER_DRAWAL2||'-------'||V_LOSS_CAL_BUYER_DRAWAL3||'-------'||V_LOSS_CAL_BUYER_DRAWAL4||'-------'||V_LOSS_CAL_BUYER_DRAWAL5||'-------'||V_LOSS_CAL_BUYER_DRAWALIN);
          SELECT V_ES_ORDERLINE_LEDGER.BUYER_END_ORG_ID,V_ES_ORDERLINE_LEDGER.BUYER_COMP_ID,V_ES_ORDERLINE_LEDGER.BUYER_COMP_SERV_ID,V_ES_ORDER.MONTH,V_ES_ORDER.YEAR,V_ES_ORDER.FROM_DT,V_ES_ORDER.TO_DT,
            V_LOSS_CAL_BUYER_DRAWAL1,V_LOSS_CAL_BUYER_DRAWAL2,V_LOSS_CAL_BUYER_DRAWAL3,V_LOSS_CAL_BUYER_DRAWAL4,V_LOSS_CAL_BUYER_DRAWAL5,V_LOSS_CAL_BUYER_DRAWALOUT,V_ES_ORDER.ID,V_ES_ORDERLINE_LEDGER.D_BUYER_COMP_NAME,V_ES_ORDERLINE_LEDGER.D_BUYER_COMP_CODE,V_ES_ORDERLINE_LEDGER.D_BUYER_COMP_SERV_NAME,V_ES_ORDERLINE_LEDGER.D_BUYER_ORG_NAME,V_ES_ORDERLINE_LEDGER.D_BUYER_ORG_CODE,V_ES_ORDERLINE_LEDGER.ID,V_SELLER_COMP_SERVICE.COMMISSION_DATE,v_adj_priority,V_SELLER_COMP_SERVICE.IS_REC,V_ES_ORDER.ALLOW_LOWER_SLOT_ADMT INTO 
            V_ENERGY_LEDGER_BUYER.M_ORG_ID,V_ENERGY_LEDGER_BUYER.M_COMPANY_ID,V_ENERGY_LEDGER_BUYER.M_COMPANY_SERVICE_ID,V_ENERGY_LEDGER_BUYER.MONTH,V_ENERGY_LEDGER_BUYER.YEAR,V_ENERGY_LEDGER_BUYER.FROM_DT,V_ENERGY_LEDGER_BUYER.TO_DT,V_ENERGY_LEDGER_BUYER.C1,
            V_ENERGY_LEDGER_BUYER.C2,V_ENERGY_LEDGER_BUYER.C3,V_ENERGY_LEDGER_BUYER.C4,V_ENERGY_LEDGER_BUYER.C5,V_ENERGY_LEDGER_BUYER.ENERGY_OUT,V_ENERGY_LEDGER_BUYER.F_ENERGY_SALE_ORDER_ID,V_ENERGY_LEDGER_BUYER.D_COMP_NAME,V_ENERGY_LEDGER_BUYER.D_COMP_CODE,V_ENERGY_LEDGER_BUYER.D_COMP_SERV_NUMBER,V_ENERGY_LEDGER_BUYER.D_ORG_NAME,V_ENERGY_LEDGER_BUYER.D_ORG_CODE,V_ENERGY_LEDGER_BUYER.F_ENERGY_SALE_ORDER_LINES_ID,V_ENERGY_LEDGER_BUYER.D_COMMISSION_DATE,V_ENERGY_LEDGER_BUYER.D_ADJUSTMENT_PRIORITY,V_ENERGY_LEDGER_BUYER.D_IS_REC,V_ENERGY_LEDGER_BUYER.ALLOW_LOWER_SLOT_ADMT FROM DUAL;


            V_ENERGY_LEDGER_BUYER.ID:=F_ENERGY_LEDGER_SEQ.NEXTVAL;
            INSERT INTO F_ENERGY_LEDGER VALUES V_ENERGY_LEDGER_BUYER;


             V_ENERGY_LEDGER_SELLER_TL.LEDGER_DATE:= SYSDATE;
            V_ENERGY_LEDGER_SELLER_TL.SERVICE_TYPE_CODE:= '04';

            V_LOSS_CAL_SELLER_TL_TOTAL :=LOSS_CALCULATION(V_ES_ORDER.INJECTING_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.D_DRAWAL_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.C1,V_LOSS_CAL_SELLER_TL_TRANS1,V_LOSS_CAL_SELLER_TL_DIST,V_LOSS_CAL_SELLER_TL_TOTAL,V_LOSS_CAL_SELLER_TL_DRAWAL);
            V_LOSS_CAL_SELLER_TL_TOTAL :=LOSS_CALCULATION(V_ES_ORDER.INJECTING_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.D_DRAWAL_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.C2,V_LOSS_CAL_SELLER_TL_TRANS2,V_LOSS_CAL_SELLER_TL_DIST,V_LOSS_CAL_SELLER_TL_TOTAL,V_LOSS_CAL_SELLER_TL_DRAWAL);
            V_LOSS_CAL_SELLER_TL_TOTAL :=LOSS_CALCULATION(V_ES_ORDER.INJECTING_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.D_DRAWAL_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.C3,V_LOSS_CAL_SELLER_TL_TRANS3,V_LOSS_CAL_SELLER_TL_DIST,V_LOSS_CAL_SELLER_TL_TOTAL,V_LOSS_CAL_SELLER_TL_DRAWAL);
            V_LOSS_CAL_SELLER_TL_TOTAL :=LOSS_CALCULATION(V_ES_ORDER.INJECTING_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.D_DRAWAL_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.C4,V_LOSS_CAL_SELLER_TL_TRANS4,V_LOSS_CAL_SELLER_TL_DIST,V_LOSS_CAL_SELLER_TL_TOTAL,V_LOSS_CAL_SELLER_TL_DRAWAL);
            V_LOSS_CAL_SELLER_TL_TOTAL :=LOSS_CALCULATION(V_ES_ORDER.INJECTING_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.D_DRAWAL_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.C5,V_LOSS_CAL_SELLER_TL_TRANS5,V_LOSS_CAL_SELLER_TL_DIST,V_LOSS_CAL_SELLER_TL_TOTAL,V_LOSS_CAL_SELLER_TL_DRAWAL);
            V_LOSS_CAL_SELLER_TL_TOTAL :=LOSS_CALCULATION(V_ES_ORDER.INJECTING_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.D_DRAWAL_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.GEN_UNITS_SOLD,V_LOSS_CAL_SELLER_TL_TRANSOUT,V_LOSS_CAL_SELLER_TL_DIST,V_LOSS_CAL_SELLER_TL_TOTAL,V_LOSS_CAL_SELLER_TL_DRAWAL);
--           -- dbms_output.put_line('V_LOSS_CALCULATION_TOTAL'||V_LOSS_CAL_SELLER_TL_TOTAL);
--           -- dbms_output.put_line('V_LOSS_CALCULATION_DIST'||V_LOSS_CAL_SELLER_TL_DIST);
--           -- dbms_output.put_line('V_LOSS_CALCULATION_TRANS'||V_LOSS_CAL_SELLER_TL_TRANS);
--           -- dbms_output.put_line('V_LOSS_CALCULATION_DRAWAL'||V_LOSS_CAL_SELLER_TL_DRAWAL1||'-------'||V_LOSS_CAL_SELLER_TL_DRAWAL2||'-------'||V_LOSS_CAL_SELLER_TL_DRAWAL3||'-------'||V_LOSS_CAL_SELLER_TL_DRAWAL4||'-------'||V_LOSS_CAL_SELLER_TL_DRAWAL5||'-------'||V_LOSS_CAL_SELLER_TL_DRAWALIN);
            SELECT V_ES_ORDERLINE_LEDGER.BUYER_END_ORG_ID,V_ES_ORDERLINE_LEDGER.BUYER_COMP_ID,V_ES_ORDERLINE_LEDGER.BUYER_COMP_SERV_ID,V_ES_ORDER.MONTH,V_ES_ORDER.YEAR,V_ES_ORDER.FROM_DT,V_ES_ORDER.TO_DT,
            V_LOSS_CAL_SELLER_TL_TRANS1,V_LOSS_CAL_SELLER_TL_TRANS2,V_LOSS_CAL_SELLER_TL_TRANS3,V_LOSS_CAL_SELLER_TL_TRANS4,V_LOSS_CAL_SELLER_TL_TRANS5,V_LOSS_CAL_SELLER_TL_TRANSOUT,V_ES_ORDER.ID,V_ES_ORDERLINE_LEDGER.D_BUYER_COMP_NAME,V_ES_ORDERLINE_LEDGER.D_BUYER_COMP_CODE,V_ES_ORDERLINE_LEDGER.D_BUYER_COMP_SERV_NAME,V_ES_ORDERLINE_LEDGER.D_BUYER_ORG_NAME,V_ES_ORDERLINE_LEDGER.D_BUYER_ORG_CODE,V_ES_ORDERLINE_LEDGER.ID,V_SELLER_COMP_SERVICE.COMMISSION_DATE,v_adj_priority  INTO 
           V_ENERGY_LEDGER_SELLER_TL.M_ORG_ID,V_ENERGY_LEDGER_SELLER_TL.M_COMPANY_ID,V_ENERGY_LEDGER_SELLER_TL.M_COMPANY_SERVICE_ID,V_ENERGY_LEDGER_SELLER_TL.MONTH,V_ENERGY_LEDGER_SELLER_TL.YEAR,V_ENERGY_LEDGER_SELLER_TL.FROM_DT,V_ENERGY_LEDGER_SELLER_TL.TO_DT,V_ENERGY_LEDGER_SELLER_TL.C1,
            V_ENERGY_LEDGER_SELLER_TL.C2,V_ENERGY_LEDGER_SELLER_TL.C3,V_ENERGY_LEDGER_SELLER_TL.C4,V_ENERGY_LEDGER_SELLER_TL.C5,V_ENERGY_LEDGER_SELLER_TL.ENERGY_OUT,V_ENERGY_LEDGER_SELLER_TL.F_ENERGY_SALE_ORDER_ID,V_ENERGY_LEDGER_SELLER_TL.D_COMP_NAME,V_ENERGY_LEDGER_SELLER_TL.D_COMP_CODE,V_ENERGY_LEDGER_SELLER_TL.D_COMP_SERV_NUMBER,V_ENERGY_LEDGER_SELLER_TL.D_ORG_NAME,V_ENERGY_LEDGER_SELLER_TL.D_ORG_CODE,V_ENERGY_LEDGER_SELLER_TL.F_ENERGY_SALE_ORDER_LINES_ID,V_ENERGY_LEDGER_SELLER_TL.D_COMMISSION_DATE,V_ENERGY_LEDGER_SELLER_TL.D_ADJUSTMENT_PRIORITY FROM DUAL;

            V_ENERGY_LEDGER_SELLER_TL.ID:=F_ENERGY_LEDGER_SEQ.NEXTVAL;
            INSERT INTO F_ENERGY_LEDGER VALUES V_ENERGY_LEDGER_SELLER_TL;




            V_ENERGY_LEDGER_SELLER_DL.LEDGER_DATE:= SYSDATE;
            V_ENERGY_LEDGER_SELLER_DL.SERVICE_TYPE_CODE:= '05';

            V_LOSS_CAL_SELLER_DL_TOTAL :=LOSS_CALCULATION(V_ES_ORDER.INJECTING_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.D_DRAWAL_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.C1,V_LOSS_CAL_SELLER_DL_TRANS,V_LOSS_CAL_SELLER_DL_DIST1,V_LOSS_CAL_SELLER_DL_TOTAL,V_LOSS_CAL_SELLER_DL_DRAWAL);
            V_LOSS_CAL_SELLER_DL_TOTAL :=LOSS_CALCULATION(V_ES_ORDER.INJECTING_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.D_DRAWAL_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.C2,V_LOSS_CAL_SELLER_DL_TRANS,V_LOSS_CAL_SELLER_DL_DIST2,V_LOSS_CAL_SELLER_DL_TOTAL,V_LOSS_CAL_SELLER_DL_DRAWAL);
            V_LOSS_CAL_SELLER_DL_TOTAL :=LOSS_CALCULATION(V_ES_ORDER.INJECTING_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.D_DRAWAL_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.C3,V_LOSS_CAL_SELLER_DL_TRANS,V_LOSS_CAL_SELLER_DL_DIST3,V_LOSS_CAL_SELLER_DL_TOTAL,V_LOSS_CAL_SELLER_DL_DRAWAL);
            V_LOSS_CAL_SELLER_DL_TOTAL :=LOSS_CALCULATION(V_ES_ORDER.INJECTING_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.D_DRAWAL_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.C4,V_LOSS_CAL_SELLER_DL_TRANS,V_LOSS_CAL_SELLER_DL_DIST4,V_LOSS_CAL_SELLER_DL_TOTAL,V_LOSS_CAL_SELLER_DL_DRAWAL);
            V_LOSS_CAL_SELLER_DL_TOTAL :=LOSS_CALCULATION(V_ES_ORDER.INJECTING_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.D_DRAWAL_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.C5,V_LOSS_CAL_SELLER_DL_TRANS,V_LOSS_CAL_SELLER_DL_DIST5,V_LOSS_CAL_SELLER_DL_TOTAL,V_LOSS_CAL_SELLER_DL_DRAWAL);
            V_LOSS_CAL_SELLER_DL_TOTAL :=LOSS_CALCULATION(V_ES_ORDER.INJECTING_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.D_DRAWAL_VOLTAGE_CODE,V_ES_ORDERLINE_LEDGER.GEN_UNITS_SOLD,V_LOSS_CAL_SELLER_DL_TRANS,V_LOSS_CAL_SELLER_DL_DISTOUT,V_LOSS_CAL_SELLER_DL_TOTAL,V_LOSS_CAL_SELLER_DL_DRAWAL);
--           -- dbms_output.put_line('V_LOSS_CALCULATION_TOTAL'||V_LOSS_CAL_SELLER_DL_TOTAL);
--           -- dbms_output.put_line('V_LOSS_CALCULATION_DIST'||V_LOSS_CAL_SELLER_DL_DIST);
--           -- dbms_output.put_line('V_LOSS_CALCULATION_TRANS'||V_LOSS_CAL_SELLER_DL_TRANS);
--           -- dbms_output.put_line('V_LOSS_CALCULATION_DRAWAL'||V_LOSS_CAL_SELLER_DL_DRAWAL1||'-------'||V_LOSS_CAL_SELLER_DL_DRAWAL2||'-------'||V_LOSS_CAL_SELLER_DL_DRAWAL3||'-------'||V_LOSS_CAL_SELLER_DL_DRAWAL4||'-------'||V_LOSS_CAL_SELLER_DL_DRAWAL5||'-------'||V_LOSS_CAL_SELLER_DL_DRAWALIN);
            SELECT V_ES_ORDERLINE_LEDGER.BUYER_END_ORG_ID,V_ES_ORDERLINE_LEDGER.BUYER_COMP_ID,V_ES_ORDERLINE_LEDGER.BUYER_COMP_SERV_ID,V_ES_ORDER.MONTH,V_ES_ORDER.YEAR,V_ES_ORDER.FROM_DT,V_ES_ORDER.TO_DT,
            V_LOSS_CAL_SELLER_DL_DIST1,V_LOSS_CAL_SELLER_DL_DIST2,V_LOSS_CAL_SELLER_DL_DIST3,V_LOSS_CAL_SELLER_DL_DIST4,V_LOSS_CAL_SELLER_DL_DIST5,V_LOSS_CAL_SELLER_DL_DISTOUT,V_ES_ORDER.ID,V_ES_ORDERLINE_LEDGER.D_BUYER_COMP_NAME,V_ES_ORDERLINE_LEDGER.D_BUYER_COMP_CODE,V_ES_ORDERLINE_LEDGER.D_BUYER_COMP_SERV_NAME,V_ES_ORDERLINE_LEDGER.D_BUYER_ORG_NAME,V_ES_ORDERLINE_LEDGER.D_BUYER_ORG_CODE,V_ES_ORDERLINE_LEDGER.ID,V_SELLER_COMP_SERVICE.COMMISSION_DATE,v_adj_priority  INTO 
           V_ENERGY_LEDGER_SELLER_DL.M_ORG_ID,V_ENERGY_LEDGER_SELLER_DL.M_COMPANY_ID,V_ENERGY_LEDGER_SELLER_DL.M_COMPANY_SERVICE_ID,V_ENERGY_LEDGER_SELLER_DL.MONTH,V_ENERGY_LEDGER_SELLER_DL.YEAR,V_ENERGY_LEDGER_SELLER_DL.FROM_DT,V_ENERGY_LEDGER_SELLER_DL.TO_DT,V_ENERGY_LEDGER_SELLER_DL.C1,
            V_ENERGY_LEDGER_SELLER_DL.C2,V_ENERGY_LEDGER_SELLER_DL.C3,V_ENERGY_LEDGER_SELLER_DL.C4,V_ENERGY_LEDGER_SELLER_DL.C5,V_ENERGY_LEDGER_SELLER_DL.ENERGY_OUT,V_ENERGY_LEDGER_SELLER_DL.F_ENERGY_SALE_ORDER_ID,V_ENERGY_LEDGER_SELLER_DL.D_COMP_NAME,V_ENERGY_LEDGER_SELLER_DL.D_COMP_CODE,V_ENERGY_LEDGER_SELLER_DL.D_COMP_SERV_NUMBER,V_ENERGY_LEDGER_SELLER_DL.D_ORG_NAME,V_ENERGY_LEDGER_SELLER_DL.D_ORG_CODE,V_ENERGY_LEDGER_SELLER_DL.F_ENERGY_SALE_ORDER_LINES_ID,V_ENERGY_LEDGER_SELLER_DL.D_COMMISSION_DATE,V_ENERGY_LEDGER_SELLER_DL.D_ADJUSTMENT_PRIORITY FROM DUAL;

            V_ENERGY_LEDGER_SELLER_DL.ID:=F_ENERGY_LEDGER_SEQ.NEXTVAL;
            INSERT INTO F_ENERGY_LEDGER VALUES V_ENERGY_LEDGER_SELLER_DL;

            END LOOP;


           UPDATE T_ENERGY_SALE SET STATUS_CODE='APPROVED' WHERE ID=V_ES_ID;
--          v_excess_es := EXCESS_ES_BANKING_BALANCE(V_ES_ID);

            excess_units_source.update_excess_from_ea(V_ES_ID, V_RESULT , V_REASON);
--      calculation for unallocated gen units
--            if v_commission_date< to_date('01-04-2018','DD-MM-YYYY') then
--                BANKING_BALANCE.confirm_energy_sale_event(V_ES_ID,v_excess_es,v_excess_es1);
--            ELSIF v_commission_date> to_date('01-04-2018','DD-MM-YYYY') THEN
--                  IF TO_NUMBER(nvl(V_ES.AVAIL_GC1,0))  > TO_NUMBER(nvl(V_ES.GC1,0)) THEN
--                    V_NEW_C1 := (TO_NUMBER(nvl(V_ES.AVAIL_GC1,0)) - TO_NUMBER(nvl(V_ES.GC1,0)));
----                    V_NEW_C1 :=(V_NEW_C1 *0.75);  
--                    V_NEW_SERV_EXCESS_FLAG:=true;
--                END IF;
--                
--                IF TO_NUMBER(nvl(V_ES.AVAIL_GC2,0))  > TO_NUMBER(nvl(V_ES.GC2,0)) THEN
--                    V_NEW_C2 := (TO_NUMBER(nvl(V_ES.AVAIL_GC2,0)) - TO_NUMBER(nvl(V_ES.GC2,0)));
----                    V_NEW_C2 :=(V_NEW_C2 *0.75); 
--                    V_NEW_SERV_EXCESS_FLAG:=true;
--                END IF;
--                
--                IF TO_NUMBER(nvl(V_ES.AVAIL_GC3,0))  > TO_NUMBER(nvl(V_ES.GC3,0)) THEN
--                    V_NEW_C3 := (TO_NUMBER(nvl(V_ES.AVAIL_GC3,0)) - TO_NUMBER(nvl(V_ES.GC3,0)));
----                    V_NEW_C3 :=(V_NEW_C3 *0.75); 
--                    V_NEW_SERV_EXCESS_FLAG:=true;
--                END IF;
--                
--                IF TO_NUMBER(nvl(V_ES.AVAIL_GC4,0))  > TO_NUMBER(nvl(V_ES.GC4,0)) THEN
--                    V_NEW_C4 := (TO_NUMBER(nvl(V_ES.AVAIL_GC4,0)) - TO_NUMBER(nvl(V_ES.GC4,0)));
----                    V_NEW_C4 :=(V_NEW_C4 *0.75); 
--                    V_NEW_SERV_EXCESS_FLAG:=true;
--                END IF;
--                
--                IF TO_NUMBER(nvl(V_ES.AVAIL_GC5,0))  > TO_NUMBER(nvl(V_ES.GC5,0)) THEN
--                    V_NEW_C5 := (TO_NUMBER(nvl(V_ES.AVAIL_GC5,0)) - TO_NUMBER(nvl(V_ES.GC5,0)));
----                    V_NEW_C5 :=(V_NEW_C5 *0.75); 
--                    V_NEW_SERV_EXCESS_FLAG:=true;
--                END IF;
--                IF V_NEW_SERV_EXCESS_FLAG THEN
--                    select V_SELLER_COMP_SERVICE.M_COMPANY_ID,V_SELLER_COMP_SERVICE.BANKING_SERVICE_ID,V_NEW_C1,V_NEW_C2,V_NEW_C3,V_NEW_C4,V_NEW_C5,V_NEW_C1,V_NEW_C2,V_NEW_C3,V_NEW_C4,V_NEW_C5
--                    into V_NEW_SERV_EX_BANKING.M_COMPANY_ID,V_NEW_SERV_EX_BANKING.BANKING_SERVICE_ID,V_NEW_SERV_EX_BANKING.C1,V_NEW_SERV_EX_BANKING.C2,V_NEW_SERV_EX_BANKING.C3,V_NEW_SERV_EX_BANKING.C4,V_NEW_SERV_EX_BANKING.C5,V_NEW_SERV_EX_BANKING.CURR_C1,V_NEW_SERV_EX_BANKING.CURR_C2,V_NEW_SERV_EX_BANKING.CURR_C3,V_NEW_SERV_EX_BANKING.CURR_C4,V_NEW_SERV_EX_BANKING.CURR_C5 from dual;
--                    V_NEW_SERV_EX_BANKING.enabled:='Y';
--                    V_NEW_SERV_EX_BANKING.CALCULATED:='Y';
--                    V_NEW_SERV_EX_BANKING.month:=V_ES.MONTH;
--                    V_NEW_SERV_EX_BANKING.year:=V_ES.YEAR;
--                    V_NEW_SERV_EX_BANKING.CREATED_BY:='CONFIRM_ENERGY_SALE_EVENT'; 
--                    V_NEW_SERV_EX_BANKING.CREATED_DT:=sysdate; 
--                    V_NEW_SERV_EX_BANKING.id:=T_NEW_SERVICE_EXCESS_BANKING_SEQ.NEXTVAL;
--                    insert into T_NEW_SERVICE_EXCESS_BANKING VALUES V_NEW_SERV_EX_BANKING;
--                END IF;
--                 END IF;

       elsif( V_ES.status_code = 'PROCESSING') then
        V_REASON    := 'Another ENERGY SALE ORDER already in process';
        V_RESULT    := 'FAILURE';
        GOTO THE_END;
       
      elsif(V_ES.status_code = 'APPROVED')THEN
       ENERGY_ALLOTMENT_RECONCILIATION.ENERGY_ALLOTMENT_RECONCILIATION_PRCE (V_ES_ID);
      ELSE 
        V_REASON    := 'CANNOT UPDATE ALREADY APPROVED ENERGY SALE';
        V_RESULT    := 'FAILURE';
        GOTO THE_END;
       END IF;

      SELECT * INTO V_ES FROM T_ENERGY_SALE WHERE ID = V_ES_ID;
       if(V_ES.status_code = 'APPROVED')THEN
       ENERGY_ALLOTMENT_RECONCILIATION.ENERGY_ALLOTMENT_RECONCILIATION_PRCE (V_ES_ID);
       END IF; 	


      EXCEPTION
              WHEN OTHERS THEN
                  v_exception_code := SQLCODE;
                  v_exception_msg  := SUBSTR(SQLERRM, 1, 100); 
                  v_result := 'FAILURE';
                  v_reason := v_exception_code || ' - ' || v_exception_msg;
                  v_log_result := log_activity('PROCEDURE','ENERGY_SALE_CONFIRMATION','EH','Isse in ENERGY_SALE_CONFIRMATION - '||v_reason,v_result,'admin', sysdate,v_es_id);
                  select status_code into V_ES.status_code from T_ENERGY_SALE where id = V_ES_ID;
                  if ( V_ES.status_code = 'PROCESSING') then 
                    update T_ENERGY_SALE set status_code = 'CREATED',PROCESS_REMARKS=v_reason where id = V_ES_ID;
                  end if;

    END;
    select status_code into V_ES.status_code from T_ENERGY_SALE where id = V_ES_ID;

    --delete any orphan energy sale order without any consumers
    -- dont run this for records already approved
    if(V_ES.status_code='CREATED' and v_alloc_cons_count =0) then
        v_result :='FAILURE';
        V_REASON := 'No Consumers allocated in this energy-sale';
        if (V_ES_ORDER.ID is not null) then
            delete f_energy_sale_order where id =  V_ES_ORDER.ID;
        end if;
        update T_ENERGY_SALE set status_code = 'CREATED',PROCESS_REMARKS=v_reason where id = V_ES_ID;
    end if;
    commit;
    <<THE_END>>
   v_log_result := log_activity('PROCEDURE','ENERGY_SALE_CONFIRMATION','End',v_result, v_reason,'admin', sysdate,v_es_id);

    IF V_RESULT = 'SUCCESS' THEN
      null;
    ELSE
      v_result := v_result || ' - ' || v_reason;
    end if;
RETURN V_RESULT;
END ENERGY_SALE_CONFIRMATION;
