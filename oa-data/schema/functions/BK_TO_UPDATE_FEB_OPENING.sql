CREATE OR REPLACE FUNCTION OPENACCESS."BK_TO_UPDATE_FEB_OPENING" RETURN VARCHAR2 AS 
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
BEGIN

      FOR line IN (select m_company_id from bk_banking_closing)
                  LOOP

                  update T_BANKING_BALANCE set CURR_C1=c1,CURR_C2=c2,CURR_C3=c3,CURR_C4=c4,CURR_C5=c5 where m_company_id= line.m_company_id and month='02' and year='2019';

                  END LOOP;

 RETURN 'success';
END BK_TO_UPDATE_FEB_OPENING;

