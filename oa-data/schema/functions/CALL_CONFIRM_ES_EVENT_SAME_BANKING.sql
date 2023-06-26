CREATE OR REPLACE FUNCTION OPENACCESS."CALL_CONFIRM_ES_EVENT_SAME_BANKING" RETURN VARCHAR2 AS 
v_bb_cur sys_refcursor ;
v_es_id varchar2(50);
master_service_count varchar2(50);
v_comp_id varchar2(50);
v_banking_service_id varchar2(50);
v_c1 varchar2(50);
v_c2 varchar2(50);
v_c3 varchar2(50);
v_c4 varchar2(50);
v_c5 varchar2(50);
v_bb_count number;
v_bb t_banking_balance%ROWTYPE;
v_month varchar2(10):='02';
v_year varchar2(10):='2019';
v_result varchar2(50);
V_ES_ID varchar2(50);
v_excess_es1 VARCHAR2(300);
        v_excess_es VARCHAR2(300);
    v_id VARCHAR2(300);

BEGIN

      FOR line in(select id from t_energy_sale where seller_comp_serv_id in (select id from m_company_service where m_company_id in
                    (select m_company_id from bk_banking_same_c1)) and month='02')
                  LOOP
                            v_id:=line.id;
                  BANKING_BALANCE.confirm_energy_sale_event(v_id,v_excess_es,v_excess_es1);

                  END LOOP;

 RETURN 'success';
END call_confirm_es_event_same_banking;

