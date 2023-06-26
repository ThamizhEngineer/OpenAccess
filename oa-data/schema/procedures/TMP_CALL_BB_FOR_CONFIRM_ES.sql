CREATE OR REPLACE PROCEDURE OPENACCESS."TMP_CALL_BB_FOR_CONFIRM_ES" is
v_result VARCHAR2(100);

O_RESULT_CODE VARCHAR2(200);
O_RESULT_DESC VARCHAR2(200);
BEGIN 

  for service in (select  distinct ser.id,ser.banking_service_id  from T_BANKING_BALANCE bb 
                          inner join v_company_service ser on bb.banking_Service_id=ser.banking_Service_id)
  loop
   update T_BANKING_BALANCE set curr_c1 = c1, curr_c2=c2, curr_c3=c3, curr_c4=c4, curr_c5=c5  where banking_service_id = service.banking_service_id;
   for pendin_es in (select id from t_energy_sale es where seller_comp_serv_id = service.id
                    fetch first 1 rows only)
    loop
           BANKING_BALANCE.confirm_energy_sale_event(pendin_es.id,O_RESULT_CODE,O_RESULT_DESC);
    end loop;
  end loop;

  commit;

END TMP_CALL_BB_FOR_CONFIRM_ES;

