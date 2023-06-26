CREATE OR REPLACE FUNCTION OPENACCESS."TEMP_SETUP_EXCESS_BAL" 
 RETURN VARCHAR2 AS 
v_cursor sys_refcursor ;
v_number varchar2(50);
o_res_code varchar2(100);
o_res_desc varchar2(200);
    v_log_result varchar(300):='SUCCESS';
    v_exception_code VARCHAR2(150);
    v_exception_msg  VARCHAR2(150);
    v_reason VARCHAR2(300);
o_result_code varchar2(100);
o_result_desc varchar2(200);

BEGIN
  BEGIN

	--insert from banking_src
	for banking_src in (select id from T_EXS_BANKING_BALANCE_SRC_29_JUN where IMPORT_RESULT<>'Y' )
	LOOP
	begin
      INSERT INTO T_EXS_BANKING_BALANCE(ID, READING_MONTH, READING_YEAR, M_COMPANY_ID,M_COMPANY_NAME,M_COMPANY_SERVICE_ID,M_COMPANY_SERVICE_NUM,BANKING_SERVICE_ID,BANKING_SERVICE_NUM,OPEN_C1,OPEN_C2,OPEN_C3,OPEN_C4,OPEN_C5,OPEN_TOTAL_UNITS,
								INCR_EA1_C1, INCR_EA1_C2, INCR_EA1_C3, INCR_EA1_C4, INCR_EA1_C5, INCR_EA1_TOTAL_UNITS,DECR_EA1_C1, DECR_EA1_C2, DECR_EA1_C3, DECR_EA1_C4, DECR_EA1_C5, DECR_EA1_TOTAL_UNITS,INCR_HT_C1, INCR_HT_C2, INCR_HT_C3, INCR_HT_C4, INCR_HT_C5, INCR_HT_TOTAL_UNITS,
								CURR_C1,CURR_C2,CURR_C3,CURR_C4,CURR_C5,CURR_TOTAL_UNITS,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT,ENABLED)
        select ID,READING_MONTH,READING_YEAR,M_COMPANY_ID,M_COMPANY_NAME,M_COMPANY_SERVICE_ID,M_COMPANY_SERVICE_NUM,BANKING_SERVICE_ID,BANKING_SERVICE_NUM,OPEN_C1,OPEN_C2,OPEN_C3,OPEN_C4,OPEN_C5,OPEN_TOTAL_UNITS,
								INCR_EA1_C1, INCR_EA1_C2, INCR_EA1_C3, INCR_EA1_C4, INCR_EA1_C5, INCR_EA1_TOTAL_UNITS,DECR_EA1_C1, DECR_EA1_C2, DECR_EA1_C3, DECR_EA1_C4, DECR_EA1_C5, DECR_EA1_TOTAL_UNITS,INCR_HT_C1, INCR_HT_C2, INCR_HT_C3, INCR_HT_C4, INCR_HT_C5, INCR_HT_TOTAL_UNITS,
								CURR_C1,CURR_C2,CURR_C3,CURR_C4,CURR_C5,CURR_TOTAL_UNITS,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT,ENABLED
								from T_EXS_BANKING_BALANCE_SRC_29_JUN where id=banking_src.id;
      update T_EXS_BANKING_BALANCE_SRC_29_JUN set import_result='Y' where id=banking_src.id;
    exception
        when others then
          v_exception_code := SQLCODE;
          v_exception_msg := SUBSTR(SQLERRM, 1, 200);
          o_result_code := 'FAILURE';
          o_result_desc := v_exception_code || ' - ' || v_exception_msg;
          ---- dbms_output.put_line(o_result_desc);
          update T_EXS_BANKING_BALANCE_SRC_29_JUN set import_result=v_exception_msg where id=banking_src.id;
       END;
     END LOOP;

	--insert from banking_src
	for surplus_stb_src in (select id from T_EXS_SURPLUS_STB_SRC_29_JUN where IMPORT_RESULT<>'Y' )
	LOOP
	begin
	INSERT INTO T_EXS_SURPLUS_STB_BALANCE(ID, READING_MONTH, READING_YEAR, M_COMPANY_ID,M_COMPANY_NAME,M_COMPANY_SERVICE_ID,M_COMPANY_SERVICE_NUM,BANKING_SERVICE_ID,BANKING_SERVICE_NUM,OPEN_C1,OPEN_C2,OPEN_C3,OPEN_C4,OPEN_C5,OPEN_TOTAL_UNITS,
								INCR_EA1_C1, INCR_EA1_C2, INCR_EA1_C3, INCR_EA1_C4, INCR_EA1_C5, INCR_EA1_TOTAL_UNITS,DECR_EA1_C1, DECR_EA1_C2, DECR_EA1_C3, DECR_EA1_C4, DECR_EA1_C5, DECR_EA1_TOTAL_UNITS,INCR_HT_C1, INCR_HT_C2, INCR_HT_C3, INCR_HT_C4, INCR_HT_C5, INCR_HT_TOTAL_UNITS,
								CURR_C1,CURR_C2,CURR_C3,CURR_C4,CURR_C5,CURR_TOTAL_UNITS,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT,ENABLED)
        select ID,READING_MONTH,READING_YEAR,M_COMPANY_ID,M_COMPANY_NAME,M_COMPANY_SERVICE_ID,M_COMPANY_SERVICE_NUM,BANKING_SERVICE_ID,BANKING_SERVICE_NUM,OPEN_C1,OPEN_C2,OPEN_C3,OPEN_C4,OPEN_C5,OPEN_TOTAL_UNITS,
								INCR_EA1_C1, INCR_EA1_C2, INCR_EA1_C3, INCR_EA1_C4, INCR_EA1_C5, INCR_EA1_TOTAL_UNITS,DECR_EA1_C1, DECR_EA1_C2, DECR_EA1_C3, DECR_EA1_C4, DECR_EA1_C5, DECR_EA1_TOTAL_UNITS,INCR_HT_C1, INCR_HT_C2, INCR_HT_C3, INCR_HT_C4, INCR_HT_C5, INCR_HT_TOTAL_UNITS,
								CURR_C1,CURR_C2,CURR_C3,CURR_C4,CURR_C5,CURR_TOTAL_UNITS,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT,ENABLED
								from T_EXS_SURPLUS_STB_SRC_29_JUN where id=surplus_stb_src.id;
      update T_EXS_SURPLUS_STB_SRC_29_JUN set import_result='Y' where id=surplus_stb_src.id;

    exception
        when others then
          v_exception_code := SQLCODE;
          v_exception_msg := SUBSTR(SQLERRM, 1, 200);
          o_result_code := 'FAILURE';
          o_result_desc := v_exception_code || ' - ' || v_exception_msg;
          ---- dbms_output.put_line(o_result_desc);
          update T_EXS_SURPLUS_STB_SRC_29_JUN set import_result=v_exception_msg where id=surplus_stb_src.id;
       END;
     END LOOP;
  --Loop ends

         exception
        when others then
          v_exception_code := SQLCODE;
          v_exception_msg := SUBSTR(SQLERRM, 1, 200);
          o_result_code := 'FAILURE';
          o_result_desc := v_exception_code || ' - ' || v_exception_msg;

       END;
       COMMIT;
  RETURN NULL;
END TEMP_SETUP_EXCESS_BAL;

