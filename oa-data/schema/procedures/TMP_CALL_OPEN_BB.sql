CREATE OR REPLACE PROCEDURE OPENACCESS.TMP_CALL_OPEN_BB is
v_result VARCHAR2(100);

O_RESULT_CODE VARCHAR2(200);
O_RESULT_DESC VARCHAR2(200);
v_banking_service_id varchar(50);
v_allotment_count number:=0;
BEGIN 


-------- create bb for the  service numbers for whom bb is not available for month= '02', and not allocated
--1) set curr_c1..c5 as 0 if negative
--2) delete any txn except mr for that service in month-02
--3) once loop completes, run create-gs

    --fetch service numbers for whom bb is not available for month= '02', and not allocated
  for service in ( 
                    select "number" d_sell_comp_serv_number from m_company_service where banking_service_id in 
                    (select banking_service_id from t_banking_balance where month = '01'
                    minus
                    select banking_service_id from t_banking_balance where month = '02')
                    minus
                    select d_sell_comp_serv_number from f_energy_sale_order where month='02' and d_sell_comp_serv_number in 
                    --select disp_service_number from t_gen_stmt where status_code='CREATED' and stmt_month='02' and disp_service_number in
                    (
                    select "number" from m_company_service where banking_service_id in 
                    (select banking_service_id from t_banking_balance where month = '01'
                    minus
                    select banking_service_id from t_banking_balance where month = '02')
                    )

                          )
  loop
    select banking_service_id into v_banking_service_id from m_company_service where "number"=service.d_sell_comp_serv_number fetch first 1 rows only;
    -- dbms_output.put_line('v_banking_service_id-->'||v_banking_service_id);
    update t_banking_balance set CURR_C1 = decode(sign(nvl(CURR_C1,0)),1,CURR_C1,0),CURR_C2 = decode(sign(nvl(CURR_C2,0)),1,CURR_C2,0),
		CURR_C3 = decode(sign(nvl(CURR_C3,0)),1,CURR_C3,0), CURR_C4 = decode(sign(nvl(CURR_C4,0)),1,CURR_C4,0), CURR_C5 = decode(sign(nvl(CURR_C5,0)),1,CURR_C5,0)
		where banking_service_id = v_banking_service_id and month='01';
    
    DELETE_TXN.DELETE_BY_SERVICE('call from TMP_CALL_OPEN_BB',service.d_sell_comp_serv_number,'02','2019','Y','Y','Y','N',O_RESULT_CODE,O_RESULT_DESC);
  end loop;

    O_RESULT_CODE := CREATE_GS(null,'02','2019');



-------- create bb for with curr_c1 = double of c1 
/***
	1.loop those records
		a. if alloted, skip (NOTE)
		b. set opening and curr to 0 for month-01
		c. delete bb for month 02 and this service
		c. delete txn except mr
	2. call create-gs
**/
/***

    --fetch service numbers for whom bb is not available for month= '02', and not allocated
  for service in ( 
                    select ser."number" d_sell_comp_serv_number from m_company_service ser join(
                    select id,banking_service_id,month,year, c1, c2,c3,c4,c5, curr_c1,curr_c2,curr_c3,curr_c4,curr_c5 from
                    (
                    select * from t_banking_balance where month = '01' and curr_c1 = c1*2 and c1 > 0
                    union
                    select * from t_banking_balance where month = '01' and curr_c3 = c3*2 and c3 > 0
                    union
                    select * from t_banking_balance where month = '01' and curr_c4 = c4*2 and c4 > 0
                    union
                    select * from t_banking_balance where month = '01' and curr_c5 = c5*2 and c5 > 0
                    ) 
                    where nvl(curr_c1,0) = nvl(c1*2,0)  and nvl(curr_c2,0) = nvl(c2*2,0)  and nvl(curr_c3,0) = nvl(c3*2,0)  and nvl(curr_c4,0) = nvl(c4*2,0)  and nvl(curr_c5,0) = nvl(c5*2,0)
                    order by  banking_service_id,month,year) a
                    on ser.banking_service_id=a.banking_service_id

                          )
  loop
    select count(*) into v_allotment_count from f_energy_sale_order where month='02' and d_sell_comp_serv_number = service.d_sell_comp_serv_number;
    if (v_allotment_count>0) then continue; end if;
    
    select banking_service_id into v_banking_service_id from m_company_service where "number"=service.d_sell_comp_serv_number fetch first 1 rows only;
    -- dbms_output.put_line('v_banking_service_id-->'||v_banking_service_id);
    update t_banking_balance set CURR_C1 = 0, CURR_C2 = 0,CURR_C3 = 0,CURR_C4 = 0,CURR_C5 = 0,
        C1 = 0, C2 = 0,C3 = 0,C4 = 0,C5 = 0
		where banking_service_id = v_banking_service_id and month='01';
    delete from t_banking_balance where  banking_service_id = v_banking_service_id and month='02';
    DELETE_TXN.DELETE_BY_SERVICE('call from TMP_CALL_OPEN_BB',service.d_sell_comp_serv_number,'02','2019','Y','Y','Y','N',O_RESULT_CODE,O_RESULT_DESC);
  end loop;

    O_RESULT_CODE := CREATE_GS(null,'02','2019');

**/
  commit;

END TMP_CALL_OPEN_BB;