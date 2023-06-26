CREATE OR REPLACE FUNCTION OPENACCESS."CALL_ADJUST_ES_AVAILABLE_UNITS" RETURN VARCHAR2 AS 
v_bb_cur sys_refcursor ;
v_service_number varchar2(50);
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
BEGIN

      FOR line IN ( select id from T_ENERGY_SALE where  month='02' and year='2019' AND status_code='APPROVED' AND C1=0 AND C2=0 AND C3=0 AND C4=0 AND C5=0)
                  LOOP

                  v_result:=ADJUST_ENERGY_SALE_AVAILABLE_UNITS(line.id);

                  END LOOP;

 RETURN 'success';
END call_adjust_es_available_units;

