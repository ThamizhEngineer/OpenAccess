CREATE OR REPLACE PROCEDURE OPENACCESS."TMP_OPEN_BB_03_06_19" is
v_result VARCHAR2(100);
v_log_result VARCHAR2(100);
O_RESULT_CODE VARCHAR2(200);
O_RESULT_DESC VARCHAR2(200);
v_banking_service_id varchar(50);
v_allotment_count number:=0;
v_curr_month_bb_count number:=0;
v_bb_new T_BANKING_BALANCE%ROWTYPE;
BEGIN 
 v_log_result := log_activity('PROCEDURE','OPEN_BB_04_06_19_SS_LOSS','START',o_result_desc,'','', sysdate,'');
  FOR LINE IN (SELECT * FROM BK_BB_SS_LOSS_04062019_1)
   loop
    select count(*) into v_curr_month_bb_count from t_banking_balance where M_COMPANY_ID = LINE.m_company_id and BANKING_SERVICE_ID = LINE.BANKING_SERVICE_ID and month = '05' and year = '2019'  fetch first 1 rows only;
     if(v_curr_month_bb_count = 0) then 
      v_log_result := log_activity('PROCEDURE','OPEN_BB_04_06_19_SS_LOSS','INSERTED','LINE.m_company_id-'||LINE.m_company_id||',05'',2019','','', sysdate,'');
        v_bb_new.M_COMPANY_ID := LINE.M_COMPANY_ID;
        v_bb_new.BANKING_SERVICE_ID := LINE.BANKING_SERVICE_ID;
        v_bb_new.enabled:='Y';
        v_bb_new.CALCULATED:='N';
        v_bb_new.month:='05';
        v_bb_new.year:='2019';
        v_bb_new.remarks:='MANUAL-UPDATE-SS_LOSS';
        v_bb_new.CREATED_BY:='BK_BB_SS_LOSS';
        v_bb_new.CREATED_DATE:=sysdate;
        v_bb_new.id:=T_BANKING_BALANCE_SEQ.NEXTVAL;

          select  LINE.CURR_C1,LINE.CURR_C2,LINE.CURR_C3,LINE.CURR_C4,LINE.CURR_C5,LINE.CURR_C1,LINE.CURR_C2,LINE.CURR_C3,LINE.CURR_C4,LINE.CURR_C5
          into  v_bb_new.C1,v_bb_new.C2,v_bb_new.C3,v_bb_new.C4,v_bb_new.C5,v_bb_new.CURR_C1,v_bb_new.CURR_C2,v_bb_new.CURR_C3,v_bb_new.CURR_C4,v_bb_new.CURR_C5 from dual;

          insert into t_banking_balance values v_bb_new;

    else
     v_log_result := log_activity('PROCEDURE','OPEN_BB_04_06_19_SS_LOSS','NOT INSERTED','LINE.m_company_id-'||LINE.m_company_id||',05'',2019','','', sysdate,'');
     end if;
--   -- dbms_output.put_line(LINE.m_company_id);
  END LOOP;

 v_log_result := log_activity('PROCEDURE','OPEN_BB_04_06_19_SS_LOSS','END',o_result_desc,'','', sysdate,'');
END TMP_OPEN_BB_03_06_19;

