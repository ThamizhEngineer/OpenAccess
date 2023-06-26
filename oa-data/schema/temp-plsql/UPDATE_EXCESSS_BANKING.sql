create or replace FUNCTION  "UPDATE_EXCESSS_BANKING" RETURN VARCHAR2 AS
v_c1 varchar2(50);
v_c2 varchar2(50);
v_c3 varchar2(50);
v_c4 varchar2(50);
v_c5 varchar2(50);
v_total_units varchar2(50);
v_gen_stmt_id varchar2(50);
v_service_id varchar2(50);
v_es_id varchar2(50);
    BEGIN
     FOR data IN (select m_company_service_num from t_exs_banking_balance where open_total_units>0 and reading_month='07' and reading_year='2020' and m_company_service_num in
        (select "number" from v_company_service where excess_unit_type_reason='Wind and ThirdParty'))
    LOOP
        -- Banking table part
        select open_c1,open_c2,open_c3,open_c4,open_c5,open_total_units into v_c1,v_c2,v_c3,v_c4,v_c5,v_total_units from t_exs_banking_balance where m_company_service_num=data.m_company_service_num and reading_month='07' and reading_year='2020';
        update t_exs_surplus_stb_balance set open_c1=v_c1,open_c2=v_c2,open_c3=v_c3,open_c4=v_c4,open_c5=v_c5,open_total_units=v_total_units where m_company_service_num=data.m_company_service_num and reading_month='07' and reading_year='2020';
        update t_exs_banking_balance set open_c1=0,open_c2=0,open_c3=0,open_c4=0,open_c5=0,open_total_units=0 where m_company_service_num=data.m_company_service_num and reading_month='07' and reading_year='2020';
        -- gen stmt part
        select id,m_company_service_id into v_gen_stmt_id,v_service_id from t_gen_stmt where disp_service_number=data.m_company_service_num and stmt_month='07' and stmt_year='2020';
        dbms_output.put_line('v_gen_stmt_id-' || v_gen_stmt_id);
        update t_gen_stmt_slot set banked_balance=0 where t_gen_stmt_id=v_gen_stmt_id and slot_code='C1';
        update t_gen_stmt_slot set banked_balance=0 where t_gen_stmt_id=v_gen_stmt_id and slot_code='C2';
        update t_gen_stmt_slot set banked_balance=0 where t_gen_stmt_id=v_gen_stmt_id and slot_code='C3';
        update t_gen_stmt_slot set banked_balance=0 where t_gen_stmt_id=v_gen_stmt_id and slot_code='C4';
        update t_gen_stmt_slot set banked_balance=0 where t_gen_stmt_id=v_gen_stmt_id and slot_code='C5';

        -- energy sale part
        dbms_output.put_line('service_id-' || v_service_id);
        select id into v_es_id from t_energy_sale where seller_comp_serv_id=v_service_id and month='07' and year='2020';
        update t_energy_sale set avail_bc1=0,avail_bc2=0,avail_bc3=0,avail_bc4=0,avail_bc5=0,total_bank_units_used=0 where id=v_es_id;

    END LOOP;
    RETURN 'SUCCESS';
END UPDATE_EXCESSS_BANKING;