CREATE OR REPLACE FUNCTION OPENACCESS."UPDATE_925_SERVICES" RETURN VARCHAR2 AS 
v_service_count varchar2(50);
v_cursor sys_refcursor ;
v_company_id varchar2(50);

BEGIN
select count(*) into v_service_count from t_banking_balance where month='05' and m_company_id in (select m_Company_id from v_company_Service where commission_date>to_date('01-04-2018','DD-MM-YYYY'));

OPEN v_cursor for select m_company_id from t_banking_balance where month='05' and m_company_id in (select m_Company_id from v_company_Service where commission_date>to_date('01-04-2018','DD-MM-YYYY'));
    LOOP
    FETCH v_cursor INTO v_company_id;

    update t_banking_balance set c1=0,c2=0,c3=0,c4=0,c5=0,curr_c1=0,curr_c2=0,curr_c3=0,curr_c4=0,curr_c5=0 where m_company_id=v_company_id and month='05' and year='2019';

  EXIT WHEN v_cursor%NOTFOUND;
END LOOP;

COMMIT;

  RETURN v_service_count;
END UPDATE_925_SERVICES;

