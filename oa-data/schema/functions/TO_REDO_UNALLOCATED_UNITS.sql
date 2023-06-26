CREATE OR REPLACE FUNCTION OPENACCESS."TO_REDO_UNALLOCATED_UNITS" 
(v_month IN VARCHAR2,
v_year in varchar2) RETURN VARCHAR2 AS
v_number VARCHAR2(100);
v_service_id VARCHAR2(50);
v_es_id VARCHAR2(50);
o_result_code VARCHAR2(200);
o_result_desc VARCHAR2(200);
v_result varchar(300):='SUCCESS';


BEGIN
        for ss in (select ms."number" as service_no,ms.id as service_id,ms.m_org_id as edc_code,og.name as edc_name,es.month as month 
                        from m_company_service ms
                        left join m_org og on og.id=ms.m_org_id
                        left join t_energy_sale es on es.seller_comp_serv_id=ms.id
                        left join t_es_usage_summary ss on ss.t_energy_sale_id=es.id
                        left join v_company_service cc on cc.id=ms.id
                        where (es.gc1+es.gc2+es.gc3+es.gc4+es.gc5)=0 and (ss.c1+ss.c2+ss.c3+ss.c4+ss.c5)>0 and es.month='09'
                        group by ss.t_energy_sale_id,ms."number",ms.id,ms.m_org_id,es.month,og.name)
        LOOP
        select ms."number",ms.id into v_number,v_service_id from m_company_service ms where ms."number"=ss.service_no;
        DELETE_TXN.DELETE_BY_SERVICE('TO_REDO_UNALLOCATED_UNITS',v_number,v_month,v_year,'Y','N','N','N',o_result_code,o_result_desc);
        select es.id into v_es_id from t_energy_sale es where es.seller_comp_serv_id=v_service_id and month=v_month;
        v_result:=ENERGY_SALE_CONFIRMATION(v_es_id);

        END LOOP;

        RETURN 'SUCCESS';

        commit;

     END TO_REDO_UNALLOCATED_UNITS;

