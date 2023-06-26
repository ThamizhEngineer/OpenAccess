CREATE OR REPLACE PROCEDURE OPENACCESS."CLEAN_IMPORT_SELLERS" is
v_flow_type_code varchar2(50);
v_esi_status_code varchar2(50);
v_signup_count number:=0;
v_signup_tr_count number:=0;
--v_created_Date DATE := SYSDATE;
v_created_By  varchar2(50):= 'admin';
v_status varchar2(50);
v_reason varchar2(200):='';
v_exception_code  NUMBER;
v_exception_msg  VARCHAR2(200);
v_result varchar(300):='SUCCESS';
v_log_result varchar(300):='SUCCESS';
v_signup_import_result varchar(300):='SUCCESS';
v_imported BOOLEAN;
v_signup M_SIGNUP%ROWTYPE;
v_conversion number:=0.001;

BEGIN
    update imp_temp1 set commission_date =  to_date(trim(commission_date_str),'dd/mm/yyyy')  where commission_date is  null and commission_date_str is not null  ;

  FOR i IN ( select distinct i.edc_name, o.org_id from imp_temp1 i inner join v_org o on o.org_name = i.edc_name )
  LOOP
    update imp_temp1 set m_org_id = i.org_id where trim(edc_name) = i.edc_name ;
  END LOOP;

  FOR j IN ( select distinct i.SS_NAME_IN_OA, s.id  from imp_temp1 i inner join m_substation s on i.SS_NAME_IN_OA = s.name )
  LOOP
    update imp_temp1 set m_substation_id = j.id where trim(SS_NAME_IN_OA) = j.SS_NAME_IN_OA ;
  END LOOP;

  --update feeder-id and voltage-code
  FOR k IN (select distinct i.FEEDER_NAME_IN_OA, f.id, f.voltage_code from imp_temp1 i inner join m_feeder f on i.FEEDER_NAME_IN_OA = f.name )
  LOOP
  --, voltage_code=k.voltage_code  -- not mapping voltage from feeder as instructed by aruna madam
    update imp_temp1 set m_feeder_id = k.id where trim(feeder_NAME_IN_OA) = k.feeder_NAME_IN_OA ;
  END LOOP;

  --update voltage code
  FOR l IN ( select value_code, value_desc FROM v_codes WHERE list_code = 'VOLTAGE_CODE')
  LOOP
    UPDATE imp_temp1 SET  VOLTAGE_code = l.VALUE_code WHERE trim(INJECTION_VOLTAGE)= l.VALUE_DESC  and INJECTION_VOLTAGE is not null;
  END LOOP;

  --update generator make code
  FOR l IN ( select value_code, value_desc FROM v_codes WHERE list_code = 'GENERATOR_MAKE_CODE')
  LOOP
    UPDATE imp_temp1 SET  gen_make_code = l.VALUE_code WHERE trim(gen_make_name_oa)= l.VALUE_DESC ;
  END LOOP;

  --update meter make code
  FOR m IN ( select value_code, value_desc FROM v_codes WHERE list_code = 'METER_MAKE_CODE')
  LOOP
    UPDATE imp_temp1 SET  meter_make_code = m.VALUE_code WHERE trim(meter_make)= m.VALUE_DESC ;
  END LOOP;

  --update Wind pass  code
  FOR n IN ( select value_code, value_desc FROM v_codes WHERE list_code = 'WIND_PASS_CODE')
  LOOP
    UPDATE imp_temp1 SET  wind_pass_code = n.VALUE_code WHERE trim(wind_pass_name)= n.VALUE_DESC ;
  END LOOP;


  --update WEG group code
  FOR p IN ( select value_code, value_desc FROM v_codes WHERE list_code = 'PLANT_CLASS_TYPE_CODE')
  LOOP
    UPDATE imp_temp1 SET  weg_group_code = p.VALUE_code WHERE  trim(weg_group_name) = p.VALUE_DESC ;
  END LOOP;

        -- dbms_output.put_line('imp_temp1 completed') ;

commit;

  FOR s1 IN(select sig.HTSC_NUMBER  from M_SIGNUP sig join imp_temp1 sel on sig.HTSC_NUMBER = sel.GENERATOR_SERVICE_NO_NEW )
  loop
    update imp_temp1 set  IMPORT_REMARKS='Service Already exists in signup. to import clear that entry from signup' where CLEAN_REC <> 'N' and GENERATOR_SERVICE_NO_NEW  = s1.HTSC_NUMBER;
  end loop;

  FOR s2 IN(select  serv."number" gen_serv_no from v_company_Service serv join imp_temp1 sel on serv."number" = sel.GENERATOR_SERVICE_NO_NEW )
  loop
    update imp_temp1 set CLEAN_REC = 'N', IMPORT_REMARKS='Service Already exists in the system. cannot import this service' where CLEAN_REC <> 'N' and GENERATOR_SERVICE_NO_NEW = s2.gen_serv_no;
  end loop;

  --  code cleansing end
              -- dbms_output.put_line('code cleansing completed') ;


      -- updating the service_id, company id of the consumers in import-trade-rel
      UPDATE imp_temp2 t1
       SET (t1.M_BUYER_COMP_SERVICE_ID, t1.M_BUYER_COMPANY_ID) = (SELECT t2.id, t2.M_COMPANY_ID
                             FROM  v_company_service t2
                            WHERE t1.BUYER_COMPANY_SERVICE_NO = t2."number")
      WHERE EXISTS (
        SELECT 1
          FROM v_company_service t2
         WHERE t1.BUYER_COMPANY_SERVICE_NO = t2."number" );

      update imp_temp2 set from_date=to_date(from_date_str,'dd/mm/yyyy') , to_date=to_date(to_date_str,'dd/mm/yyyy') ;

commit;


END;

