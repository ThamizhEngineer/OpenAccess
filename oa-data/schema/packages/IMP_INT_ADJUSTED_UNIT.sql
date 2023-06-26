CREATE OR REPLACE PACKAGE "IMP_INT_ADJUSTED_UNIT" 
AS
-- Package header
  PROCEDURE PROCESS_INT_ADJUSTED_UNIT(i_batch_key in varchar2,i_month in varchar2,i_year in varchar2 , o_result_code out varchar2, o_result_desc out varchar2);
  PROCEDURE INC_SURPLUS_UNIT(i_batch_key in varchar2, o_result_code out varchar2, o_result_desc out varchar2);

END IMP_INT_ADJUSTED_UNIT;


CREATE OR REPLACE PACKAGE BODY "IMP_INT_ADJUSTED_UNIT" 
AS
-- Package body
  
   PROCEDURE PROCESS_INT_ADJUSTED_UNIT(i_batch_key in varchar2,i_month in varchar2,i_year in varchar2 ,o_result_code out varchar2, o_result_desc out varchar2) IS
    v_process varchar2(50):='IMP_INT_ADJUSTED_UNIT.PROCESS_INT_ADJUSTED_UNIT';
    v_process_type varchar2(50):='PACKAGE';
	v_exception_code  NUMBER;
    v_exception_msg  VARCHAR2(200);
    v_log_result varchar(300):='SUCCESS';
    v_intAdjUnit INT_ADJUSTED_UNIT%ROWTYPE;
    v_sellerComService V_COMPANY_SERVICE%ROWTYPE;
    v_buyerComService V_COMPANY_SERVICE%ROWTYPE;
  	v_intAdjCursor sys_refcursor ;
    v_intAdj_charge_Cursor sys_refcursor ;
  	v_sellerCount  VARCHAR2(50);
  	v_buyerCount  VARCHAR2(50);
    v_esOrderCount  VARCHAR2(50);
    v_esOrderLineCount  VARCHAR2(50);
    v_energy_adj_count number;
    v_finalintAdjUnit INT_ADJUSTED_UNIT%ROWTYPE;
    v_intAdjCharge  INT_ADJUSTED_CHARGE%ROWTYPE;
    v_energy_adjustment F_ENERGY_ADJUSTMET%ROWTYPE;
    v_row_count number;

   	v_result1  VARCHAR2(100);
  	v_result2  VARCHAR2(100);

    v_esOrderId  VARCHAR2(50);
    v_esOrderLineId  VARCHAR2(50);


    BEGIN
    v_log_result := log_activity(v_process_type, v_process,'START','Batch-Key - '||i_batch_key,'i_month-'||i_month,'i_year'||i_year, sysdate,i_batch_key);

   	    -- begin for exception handling
    BEGIN
        update INT_ADJUSTED_UNIT set is_clean='', result_desc='',BUYER_COMPANY_ID='', BUYER_COMPANY_SERVICE_ID='',
        SELLER_COMPANY_ID='', SELLER_COMPANY_SERVICE_ID='' WHERE BATCH_KEY = i_batch_key ;
        
        update INT_ADJUSTED_UNIT set is_clean='N', result_desc='ReadingDt is mandatory'  WHERE BATCH_KEY = i_batch_key AND (READING_DT IS NULL) ;
        
        -- update buyer info
        select count(i.id) into v_row_count from INT_ADJUSTED_UNIT i inner join V_COMPANY_SERVICE b on i.SERVICE_NO = b."number" where i.BATCH_KEY =i_batch_key and nvl(i.is_clean,'-')='-';
        if(v_row_count >0) then  -- this reduntant condition is to avoid 'no data found' error
            -- update buyer information in interface table
            UPDATE INT_ADJUSTED_UNIT i SET ( BUYER_COMPANY_ID, BUYER_COMPANY_SERVICE_ID) 
                        = (SELECT b.M_COMPANY_ID BUYER_COMPANY_ID, b.ID BUYER_COMPANY_SERVICE_ID  FROM V_COMPANY_SERVICE b WHERE  i.SERVICE_NO = b."number")
            WHERE EXISTS (
                    SELECT 1 FROM V_COMPANY_SERVICE b WHERE  i.SERVICE_NO = b."number" )
                  AND i.BATCH_KEY =i_batch_key and nvl(is_clean,'-')='-';
        else                  
            -- mark unknown buyers
            UPDATE INT_ADJUSTED_UNIT set is_clean='N', result_desc='Unknown Buyers'  where BATCH_KEY =i_batch_key  and nvl(is_clean,'-')='-' and BUYER_COMPANY_SERVICE_ID ='' ;
         end if;  
         
         -- update seller info
        select count(i.id) into v_row_count from INT_ADJUSTED_UNIT i inner join V_COMPANY_SERVICE s on i.SUPLR_CODE = s."number" where i.BATCH_KEY =i_batch_key and nvl(i.is_clean,'-')='-';
        if(v_row_count >0) then  -- this reduntant condition is to avoid 'no data found' error
            -- update seller information in interface table
            UPDATE INT_ADJUSTED_UNIT i SET ( SELLER_COMPANY_ID, SELLER_COMPANY_SERVICE_ID, FUEL_TYPE_CODE, SELLER_ORG_ID) 
                        = (SELECT s.M_COMPANY_ID SELLER_COMPANY_ID, s.ID SELLER_COMPANY_SERVICE_ID, s.FUEL_TYPE_CODE, s.m_org_id SELLER_ORG_ID  FROM V_COMPANY_SERVICE s WHERE  i.SUPLR_CODE = s."number")
            WHERE EXISTS (
                    SELECT 1 FROM V_COMPANY_SERVICE s WHERE  i.SUPLR_CODE = s."number" )
                  AND i.BATCH_KEY =i_batch_key and nvl(is_clean,'-')='-';
         else
            -- mark unknown sellers
            UPDATE INT_ADJUSTED_UNIT set is_clean='N', result_desc='Unknown Sellers'  where BATCH_KEY =i_batch_key  and nvl(is_clean,'-')='-' and SELLER_COMPANY_SERVICE_ID ='' ;
        end if;
        
        --update saleorder info
        select count(i.id) into v_row_count from INT_ADJUSTED_UNIT i inner join f_energy_sale_order so on i.SUPLR_CODE = so.d_sell_comp_serv_number  where i.BATCH_KEY =i_batch_key and nvl(i.is_clean,'-')='-'  AND so.month=i_month and so.year=i_year;
        if(v_row_count >0) then  -- this reduntant condition is to avoid 'no data found' error
            -- update saleorder id in interface table
            UPDATE INT_ADJUSTED_UNIT i
               SET (F_ENERGY_SALE_ORDER_ID) = (SELECT so.ID F_ENERGY_SALE_ORDER_ID FROM f_energy_sale_order so
                                    WHERE  i.SUPLR_CODE = so.d_sell_comp_serv_number AND so.month=i_month and so.year=i_year)
             WHERE EXISTS (
                SELECT 1
                  FROM f_energy_sale_order so
                 WHERE  i.SUPLR_CODE = so.d_sell_comp_serv_number AND so.month=i_month and so.year=i_year )
                 AND i.BATCH_KEY =i_batch_key and nvl(is_clean,'-')='-';      
            
            select count(i.id) into v_row_count from INT_ADJUSTED_UNIT i inner join F_ENERGY_SALE_ORDER_LINES sol on i.F_ENERGY_SALE_ORDER_ID = sol.id and  i.service_no = sol.d_buyer_comp_serv_name
                where i.BATCH_KEY =i_batch_key and nvl(i.is_clean,'-')='-'  ;
            if(v_row_count >0) then  -- this reduntant condition is to avoid 'no data found' error
                -- update saleorder lineid   in interface table
                UPDATE INT_ADJUSTED_UNIT i
                   SET (F_ENERGY_SALE_ORDER_line_id) = (SELECT sol.ID F_ENERGY_SALE_ORDER_line_id FROM F_ENERGY_SALE_ORDER_LINES sol
                                        WHERE  i.F_ENERGY_SALE_ORDER_ID = sol.id and  i.service_no = sol.d_buyer_comp_serv_name )
                 WHERE EXISTS (
                    SELECT 1
                      FROM F_ENERGY_SALE_ORDER_LINES sol
                     WHERE i.F_ENERGY_SALE_ORDER_ID = sol.id and  i.service_no = sol.d_buyer_comp_serv_name )
                     AND i.BATCH_KEY =i_batch_key and nvl(is_clean,'-')='-';    
            else
                -- mark No Energy allotment for the supplier
                UPDATE INT_ADJUSTED_UNIT set is_clean='N', result_desc='No Record of Energy Allotment for the supplier('||SUPLR_CODE||')  in OA' where BATCH_KEY =i_batch_key  and nvl(is_clean,'-')='-' and F_ENERGY_SALE_ORDER_line_id = '' ;
            end if;

        else
            -- mark No Energy allotment for the supplier
            UPDATE INT_ADJUSTED_UNIT set is_clean='N', result_desc='No Record of Energy Allotment for the supplier('||SUPLR_CODE||')  in OA' where BATCH_KEY =i_batch_key  and nvl(is_clean,'-')='-' and F_ENERGY_SALE_ORDER_ID ='' ;        
        end if;
        
        
        
        UPDATE INT_ADJUSTED_UNIT set is_clean='Y' where BATCH_KEY =i_batch_key  and nvl(is_clean,'-')='-' ;
        
        v_log_result := log_activity(v_process_type, v_process,'Cleanse Complete','','', '',i_batch_key);

        for cur in (SELECT * FROM INT_ADJUSTED_UNIT WHERE BATCH_KEY=i_batch_key and is_clean='Y' )
        LOOP
        BEGIN
        
            SELECT count(*) INTO v_energy_adj_count from F_ENERGY_ADJUSTMET WHERE suplr_code=cur.SUPLR_CODE AND service_no=cur.SERVICE_NO AND reading_mnth=cur.reading_mnth AND reading_yr=cur.reading_yr;
            if(v_energy_adj_count >0)then
                v_log_result := log_activity(v_process_type, v_process,'updating energy adjustment','','',cur.SERVICE_NO, cur.SUPLR_CODE,i_batch_key);
                update F_ENERGY_ADJUSTMET set BATCH_KEY=cur.BATCH_KEY,READING_DT=cur.READING_DT ,C24=cur.C24 , C1=cur.C1, C2=cur.C2, C3=cur.C3, C4=cur.C4, C5=cur.C5, C1_WITHLOSS=cur.C1_WITHLOSS , C2_WITHLOSS= cur.C2_WITHLOSS , C3_WITHLOSS=cur.C3_WITHLOSS, C4_WITHLOSS=cur.C4_WITHLOSS, C5_WITHLOSS=cur.C5_WITHLOSS, ADJUSTED_C1=cur.ADJUSTED_C1, ADJUSTED_C2=cur.ADJUSTED_C2, ADJUSTED_C3=cur.ADJUSTED_C3, ADJUSTED_C4=cur.ADJUSTED_C4, ADJUSTED_C5=cur.ADJUSTED_C5,
                 modified_by=v_process, modified_dt=sysdate
                    WHERE  suplr_code=cur.SUPLR_CODE AND service_no=cur.SERVICE_NO AND reading_mnth=cur.reading_mnth AND reading_yr=cur.reading_yr;
            else
              v_log_result := log_activity(v_process_type, v_process,'inserting energy adjustment','','',cur.SERVICE_NO, cur.SUPLR_CODE,i_batch_key);
              INSERT INTO F_ENERGY_ADJUSTMET
                  (ID, BATCH_KEY, SERVICE_NO, SUPLR_CODE, READING_DT, READING_MNTH, READING_YR, C24, C1, C2, C3, C4, C5, CREATED_BY, CREATED_DT, MODIFIED_BY, MODIFIED_DT, BUYER_COMPANY_ID, BUYER_COMPANY_SERVICE_ID, SELLER_COMPANY_ID, SELLER_COMPANY_SERVICE_ID, F_ENERGY_SALE_ORDER_ID, F_ENERGY_SALE_ORDER_LINE_ID, FUEL_TYPE_CODE, IMPORT_REMARKS, "SOURCE", IMPORTED, SUPLR_NAME, DELETE_FLAG, SUPLR_TYPE, C1_WITHLOSS, C2_WITHLOSS, C3_WITHLOSS, C4_WITHLOSS, C5_WITHLOSS, ADJUSTED_C1, ADJUSTED_C2, ADJUSTED_C3, ADJUSTED_C4, ADJUSTED_C5, IS_CLEAN,SELLER_ORG_ID)
                  VALUES(F_ENERGY_ADJUTMENT_SEQ.NEXTVAL,cur.BATCH_KEY,cur.SERVICE_NO,cur.SUPLR_CODE,cur.READING_DT,cur.READING_MNTH,cur.READING_YR,  cur.C24, cur.C1, cur.C2, cur.C3, cur.C4, cur.C5, v_process, sysdate, '', '',cur.BUYER_COMPANY_ID,cur.BUYER_COMPANY_SERVICE_ID,cur.SELLER_COMPANY_ID,cur.SELLER_COMPANY_SERVICE_ID,cur.F_ENERGY_SALE_ORDER_ID,cur.F_ENERGY_SALE_ORDER_LINE_ID,cur.FUEL_TYPE_CODE,cur.IMPORT_REMARKS,cur."SOURCE",cur.IMPORTED,cur.SUPLR_NAME,cur.DELETE_FLAG,cur.SUPLR_TYPE,cur.C1_WITHLOSS,cur.C2_WITHLOSS,cur.C3_WITHLOSS,cur.C4_WITHLOSS,cur.C5_WITHLOSS,cur.ADJUSTED_C1,cur.ADJUSTED_C2,cur.ADJUSTED_C3,cur.ADJUSTED_C4,cur.ADJUSTED_C5,cur.IS_CLEAN,cur.SELLER_ORG_ID);
              OPEN v_intAdj_charge_Cursor for SELECT * FROM int_adjusted_charge WHERE energy_adjusted_data_id=v_finalintAdjUnit.id;
              LOOP
                    FETCH v_intAdj_charge_Cursor INTO v_intAdjCharge;
                      EXIT WHEN v_intAdj_charge_Cursor%NOTFOUND;
                    SELECT * INTO v_energy_adjustment from F_ENERGY_ADJUSTMET WHERE suplr_code=v_finalintAdjUnit.SUPLR_CODE AND service_no=v_finalintAdjUnit.SERVICE_NO AND reading_mnth=v_finalintAdjUnit.reading_mnth AND reading_yr=v_finalintAdjUnit.reading_yr;
    
                    INSERT INTO F_ENERGY_ADJUSTED_CHARGE
                      (ID,F_ENERGY_ADJUSTED_ID,CHARGE_CODE,CHARGE_DESC,CHARGE_AMOUNT)VALUES(F_ENERGY_ADJUSTED_CHARGE_SEQ.NEXTVAL, v_energy_adjustment.ID, v_intAdjCharge.CHARGE_CODE, v_intAdjCharge.CHARGE_DESC, v_intAdjCharge.CHARGE_AMOUNT);
               END LOOP;
            end if;
        exception
        when others then
            v_exception_code := SQLCODE;
            v_exception_msg := SUBSTR(SQLERRM, 1, 200);
            o_result_code := 'FAILURE';
            o_result_desc := v_exception_code || ' - ' || v_exception_msg;
            v_log_result := log_activity(v_process_type, v_process,'EXCEPTION',o_result_desc,'Loop-exception',v_intAdjUnit.SERVICE_NO, v_intAdjUnit.SUPLR_CODE,i_batch_key);
            UPDATE INT_ADJUSTED_UNIT set is_clean='N',result_desc=o_result_desc  where BATCH_KEY =i_batch_key  and id = cur.id ;
        END;
        end loop;
        INC_SURPLUS_UNIT(i_batch_key,o_result_code, o_result_desc);


	exception
    when others then
      v_exception_code := SQLCODE;
      v_exception_msg := SUBSTR(SQLERRM, 1, 200);
      o_result_code := 'FAILURE';
      o_result_desc := v_exception_code || ' - ' || v_exception_msg;
      v_log_result := log_activity(v_process_type, v_process,'EXCEPTION',o_result_desc,'','', sysdate,i_batch_key);
    --UPDATE INT_ADJUSTED_UNIT set is_clean='N',result_desc=o_result_desc  where BATCH_KEY =i_batch_key  and id = cur.id ;

    END;

    v_log_result := log_activity(v_process_type, v_process,'END',o_result_code, o_result_desc,'', '',i_batch_key);
    COMMIT;


  END PROCESS_INT_ADJUSTED_UNIT;

   PROCEDURE INC_SURPLUS_UNIT(i_batch_key in varchar2, o_result_code out varchar2, o_result_desc out varchar2) AS
    v_process varchar2(50):='IMP_INT_ADJUSTED_UNIT.INC_SURPLUS_UNIT';
    v_process_type varchar2(50):='PACKAGE';
    v_htsurcursor sys_refcursor ;
  	v_adj_unit F_ENERGY_ADJUSTMET%ROWTYPE;
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
    v_count number;

  BEGIN
BEGIN
    v_log_result := log_activity(v_process_type,v_process,'START','Start - '||i_batch_key,'','', sysdate,i_batch_key);

    UPDATE F_ENERGY_ADJUSTMET set READING_MNTH=lpad(READING_MNTH,2,'0') WHERE BATCH_KEY=i_batch_key;

    v_log_result := log_activity(v_process_type,v_process,'Cleanse','Complete','','', sysdate,i_batch_key);

    OPEN v_htsurcursor for select * from F_ENERGY_ADJUSTMET where BATCH_KEY=i_batch_key;
    LOOP
    FETCH v_htsurcursor INTO v_adj_unit;
    EXIT WHEN v_htsurcursor%NOTFOUND;

      SELECT voltage_code INTO v_inj_code FROM m_company_service WHERE "number"=v_adj_unit.SUPLR_CODE;
      SELECT voltage_code INTO v_drawl_code FROM m_company_service WHERE "number"=v_adj_unit.SERVICE_NO;

      v_total_loss :=SURPLUS_ENERGY_LOSS_CALC(v_inj_code,v_drawl_code,v_adj_unit.C1,v_trans_loss,v_dis_loss,v_total_loss,v_inj_units1);
      v_total_loss :=SURPLUS_ENERGY_LOSS_CALC(v_inj_code,v_drawl_code,v_adj_unit.C2,v_trans_loss,v_dis_loss,v_total_loss,v_inj_units2);
      v_total_loss :=SURPLUS_ENERGY_LOSS_CALC(v_inj_code,v_drawl_code,v_adj_unit.C3,v_trans_loss,v_dis_loss,v_total_loss,v_inj_units3);
      v_total_loss :=SURPLUS_ENERGY_LOSS_CALC(v_inj_code,v_drawl_code,v_adj_unit.C4,v_trans_loss,v_dis_loss,v_total_loss,v_inj_units4);
      v_total_loss :=SURPLUS_ENERGY_LOSS_CALC(v_inj_code,v_drawl_code,v_adj_unit.C5,v_trans_loss,v_dis_loss,v_total_loss,v_inj_units5);


      UPDATE F_ENERGY_ADJUSTMET SET C1_WITHLOSS=round(nvl(v_inj_units1,0),0),C2_WITHLOSS=round(nvl(v_inj_units2,0),0),C3_WITHLOSS=round(nvl(v_inj_units3,0),0),C4_WITHLOSS=round(nvl(v_inj_units4,0),0),C5_WITHLOSS=round(nvl(v_inj_units5,0),0),LOSS_PERCENT=v_total_loss
      WHERE ID = v_adj_unit.ID;

    END LOOP;

    v_log_result := log_activity(v_process_type,v_process,'Completed Loss Calculation','','','', sysdate,i_batch_key);
    
    v_count :=0;
    for serv in ( select distinct suplr_code, reading_mnth, reading_yr from f_energy_adjustmet where BATCH_KEY=i_batch_key)
    LOOP
        excess_units_source.update_excess_from_ht(serv.suplr_code, serv.reading_mnth,serv.reading_yr ,v_log_result  , v_reason);
        v_count := v_count +1;
    END LOOP;
    
    v_log_result := log_activity(v_process_type,v_process,'Updated Balance in OA','','','', sysdate,i_batch_key);


    exception
    when others then
      v_exception_code := SQLCODE;
      v_exception_msg := SUBSTR(SQLERRM, 1, 200);
      o_result_code := 'FAILURE';
      o_result_desc := v_exception_code || ' - ' || v_exception_msg;
      -- dbms_output.put_line(o_result_desc);
      v_log_result := log_activity(v_process_type,v_process,'EXCEPTION',o_result_desc,i_batch_key,'', sysdate,i_batch_key);
    END;
    COMMIT;
    v_log_result := log_activity(v_process_type,v_process,'End','End','','', sysdate,i_batch_key);

  END INC_SURPLUS_UNIT;


END IMP_INT_ADJUSTED_UNIT;
