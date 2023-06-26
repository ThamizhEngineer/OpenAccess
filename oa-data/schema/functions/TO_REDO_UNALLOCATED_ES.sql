CREATE OR REPLACE FUNCTION OPENACCESS."TO_REDO_UNALLOCATED_ES" 

(v_remarks in varchar2,
v_month IN VARCHAR2,
v_year in varchar2) RETURN VARCHAR2 AS

v_id varchar2(50);
v_number varchar2(50);
v_comp_id varchar2(50);
v_es_id varchar2(50);
o_result_code VARCHAR2(200);
o_result_desc VARCHAR2(200);
v_result varchar(300):='SUCCESS';

BEGIN

    for dd in (select GEN_SERVICE_NUMBER from int_delete_txn where remarks=v_remarks)
        loop
        select id,"number",m_company_id into v_id,v_number,v_comp_id from m_company_service where "number"=dd.GEN_SERVICE_NUMBER;
        DELETE_TXN.DELETE_BY_SERVICE(v_remarks,v_number,v_month,v_year,'Y','N','N','N',o_result_code,o_result_desc);
        select id into v_es_id from t_energy_sale where seller_comp_serv_id=v_id and month=v_month and year=v_year;
        v_result:=ENERGY_SALE_CONFIRMATION(v_es_id);
        end loop;
        commit;

  RETURN v_result;
END TO_REDO_UNALLOCATED_ES;

