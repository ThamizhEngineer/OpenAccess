CREATE OR REPLACE PROCEDURE OPENACCESS."TMP_INT_ADJ_UNIT_03_06_19" is
v_result VARCHAR2(100);
v_log_result VARCHAR2(100);
O_RESULT_CODE VARCHAR2(200);
O_RESULT_DESC VARCHAR2(200);

BEGIN 
v_log_result := log_activity('PROCEDURE','TMP_INT_ADJ_UNIT_03_06_19','start',o_result_desc,'','', sysdate,'');
  FOR dummy IN (SELECT * FROM DUMMY_INT_ADJUSTED_UNIT)
   loop
   IMP_INT_ADJUSTED_UNIT.PROCESS_INT_ADJUSTED_UNIT(dummy.BATCH_KEY,dummy.READING_MNTH,dummy.READING_YR,O_RESULT_CODE,O_RESULT_DESC);


--   -- dbms_output.put_line(LINE.m_company_id);
  END LOOP;

 v_log_result := log_activity('PROCEDURE','TMP_INT_ADJ_UNIT_03_06_19','end',o_result_desc,'','', sysdate,'');
END TMP_INT_ADJ_UNIT_03_06_19;

