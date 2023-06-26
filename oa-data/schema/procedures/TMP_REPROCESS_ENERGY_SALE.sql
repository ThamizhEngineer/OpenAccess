CREATE OR REPLACE PROCEDURE OPENACCESS.TMP_REPROCESS_ENERGY_SALE is
v_result VARCHAR2(100);
 v_log_result varchar(300):='SUCCESS';
BEGIN 
v_log_result := log_activity('PROCEDURE','TMP_REPROCESS_ENERGY_SALE','START','', '','admin', sysdate,'');
    for ES in (SELECT ES.ID
FROM T_ENERGY_SALE es
left join M_COMPANY_SERVICE s on es.SELLER_COMP_SERV_ID=s.id
WHERE es.MONTH='05' and  es.BC1+es.BC2+es.BC3+es.BC4+es.BC5>0 and es.STATUS_CODE='CREATED' )
  --  for ES in (select ID from t_energy_sale where PROCESS_REMARKS is not null and status_code='CREATED' and created_date < (SYSDATE - 2/24) )
  loop
--    update t_energy_sale set status_code='CREATED' , PROCESS_REMARKS='REPROCESSING' where ID=ES.id;
    v_result := ENERGY_SALE_CONFIRMATION(ES.id);
      v_log_result := log_activity('PROCEDURE','TMP_REPROCESS_ENERGY_SALE','LOOP','', '','admin', sysdate,ES.id);
  end loop;
  v_log_result := log_activity('PROCEDURE','TMP_REPROCESS_ENERGY_SALE','END','', '','admin', sysdate,'');
END TMP_REPROCESS_ENERGY_SALE;