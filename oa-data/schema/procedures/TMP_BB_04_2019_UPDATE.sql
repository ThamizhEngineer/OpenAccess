CREATE OR REPLACE PROCEDURE OPENACCESS.TMP_bb_04_2019_update is
v_result VARCHAR2(100);
v_log_result VARCHAR2(100);
O_RESULT_CODE VARCHAR2(200);
O_RESULT_DESC VARCHAR2(200);
v_banking_service_id varchar(50);
v_allotment_count number:=0;
v_curr_month_bb_count number:=0;
v_bb_new T_BANKING_BALANCE%ROWTYPE;
BEGIN 
 v_log_result := log_activity('PROCEDURE','TMP_bb_04_2019_update','START','','','','','', sysdate,'');
 
 for bb in (select * from t_banking_balance where month='05' and year='2019')
  loop
  
--  v_log_result := log_activity('PROCEDURE','TMP_bb_04_2019_update','End',o_result_desc,'','', sysdate,'');
  
-- update  t_banking_balance set remarks='tmp_bb_04_2019_update',curr_c1=bb.c1,curr_c2=bb.c2,curr_c3=bb.c3,curr_c4=bb.c4,curr_c5=bb.c5,modified_by='tmp_bb_04_2019_update',modified_dt=sysdate where m_company_id = bb.m_company_id and month='04' and year='2019';
  end loop;  
 
  
 v_log_result := log_activity('PROCEDURE','TMP_bb_04_2019_update','End',o_result_desc,'','', sysdate,'');
END TMP_bb_04_2019_update;