CREATE OR REPLACE FUNCTION OPENACCESS."LOL" RETURN VARCHAR2 AS 
v_status varchar(50);
v_number varchar(50);
o_Result_Code varchar(150);
o_Result_desc varchar(200);
v_comp_id varchar(50);
v_seller_comp_id varchar(50);
v_Es_id varchar(100);
v_gen_stmt_count number:=0;
v_commission_date Date;
    v_result varchar(300):='SUCCESS';
v_log_result VARCHAR2(50):='';
BEGIN
	v_log_result := log_activity('PROCEDURE','TMP','START','','','', sysdate,'');

--for hahaha in(select g.id from T_GEN_STMT g
--left join T_GEN_STMT_CHARGE ch on g.id=ch.T_GEN_STMT_ID
--where g.STMT_MONTH='06' having count(ch.id)=0 
--group by g.id)
--loop
--v_result:=CALC_GS_CHARGES(hahaha.id);
--update T_GEN_STMT set remarks='WITHOUTCHARGES' WHERE ID=hahaha.ID;
--end loop;

--        for approved in(select seller_comp_serv_id FROM t_energy_sale where status_code='APPROVED' AND t_gen_stmt_id in (select id from t_gen_stmt where stmt_month='06' and disp_service_number in
--        (select DISTINCT suplr_code from missing_int_adj_units_0306)))
--        loop
--        
--        select "number" into v_number from m_company_service where id=approved.seller_comp_serv_id;
--        update missing_int_adj_units_0306 set ES_STATUS='APPROVED' WHERE suplr_code=v_number;
--        end loop;


--        for hehehe in(select ID,DISP_SERVICE_NUMBER from t_gen_stmt  where TYPE_OF_SS = 'SECTION 10(1)SS' and STMT_MONTH='06')
--        loop
--        
--        select id into v_seller_comp_id from m_company_service where "number"=hehehe.DISP_SERVICE_NUMBER;
--         select count(*) into v_gen_stmt_count from t_energy_sale where T_GEN_STMT_ID=hehehe.id and month='06' and seller_comp_serv_id=v_seller_comp_id ;
--         if v_gen_stmt_count =1 then 
--        select id into v_Es_id from t_energy_sale where T_GEN_STMT_ID=hehehe.id and seller_comp_serv_id=v_seller_comp_id and month='06';
----        update t_energy_sale set status_code='CREATED' WHERE ID=v_Es_id;
----        DELETE_TXN.DELETE_BY_SERVICE('TENONESS_RERUB',hehehe.DISP_SERVICE_NUMBER,'06','2019','Y','N','N','N',o_Result_Code,o_Result_desc);
--        if v_Es_id!='63600' OR v_Es_id!='63692' OR v_Es_id!='63871' OR v_Es_id!='63886'  then
--        v_result:= ENERGY_SALE_CONFIRMATION(v_Es_id);
--        end if;
--    end if;
--        end loop;

--          for hahaha in(select M_COMPANY_ID from T_BANKING_BALANCE  where month='05' and (nvl(CURR_C1,0)+nvl(CURR_C3,0)+nvl(CURR_C3,0)+nvl(CURR_C4,0)+nvl(CURR_C5,0)+nvl(SURPLUS_C1,0)+nvl(SURPLUS_C2,0)+nvl(SURPLUS_C3,0)+nvl(SURPLUS_C4,0)+nvl(SURPLUS_C5,0))>0 and  M_COMPANY_ID not in (select M_COMPANY_ID from T_BANKING_BALANCE where month='06'))
--          loop
--          select id,"number" into v_seller_comp_id,v_number from v_company_service where M_COMPANY_ID=hahaha.M_COMPANY_ID;
----          delete from t_banking_balance where m_company_id=v_comp_id and month='06';
--         if v_seller_comp_id is not null then
--                   select COMMISSION_DATE into v_commission_date from M_POWERPLANT where M_SERVICE_ID = v_seller_comp_id;
--                   end if;
--          if v_commission_date is not null and v_commission_date< to_date('01-04-2018','DD-MM-YYYY') then
--          	v_log_result := log_activity('PROCEDURE','LOL','LOOP','M_COMPANY_ID'||hahaha.M_COMPANY_ID,'v_number'||v_number,'', sysdate,'');
--                  BANKING_BALANCE.open_balance (v_number,'06','2019',o_Result_Code,o_Result_desc);
--                  end if;
--          end loop;
	v_log_result := log_activity('PROCEDURE','TMP','END','','','', sysdate,'');
  RETURN NULL;
END LOL;

