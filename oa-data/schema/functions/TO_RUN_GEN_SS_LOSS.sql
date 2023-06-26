CREATE OR REPLACE FUNCTION OPENACCESS."TO_RUN_GEN_SS_LOSS" RETURN VARCHAR2 AS

v_number VARCHAR2(50);
v_org VARCHAR2(50);
v_subs VARCHAR2(50);
o_result_code VARCHAR2(200);
o_result_desc VARCHAR2(200);

BEGIN
        for ss in (select GEN_SERVICE_NUMBER from int_delete_txn where remarks='TARRIF_CHANGE_30OCT' and  reading_month='09' and reading_year='2019')
        LOOP
        select ms."number",ms.m_org_id,ms.m_substation_id into v_number,v_org,v_subs from m_company_service ms where ms."number"=ss.gen_service_number;
        GENERATION_STATEMENT.CREATE_WITH_SS_LOSS(v_subs,v_number,v_org,'09','2019',o_result_code,o_result_desc);
        END LOOP;

        RETURN 'SUCCESS';

        commit;

     END TO_RUN_GEN_SS_LOSS;

