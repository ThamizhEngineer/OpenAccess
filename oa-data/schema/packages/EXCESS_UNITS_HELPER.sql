CREATE OR REPLACE PACKAGE EXCESS_UNITS_HELPER AS 

  PROCEDURE CREATE_NEW_OBJ (i_excess_unit_type in varchar2, i_service_number in varchar2,i_reading_month in varchar2,i_reading_year in varchar2, o_exs_bal out V_EXS_BALANCE%ROWTYPE);
  PROCEDURE CREATE_OBJ_FROM_PREV_MONTH (i_excess_unit_type in varchar2,i_reading_month in varchar2,i_reading_year in varchar2, i_prev_bal in V_EXS_BALANCE%ROWTYPE, o_curr_bal out V_EXS_BALANCE%ROWTYPE);
  PROCEDURE SET_OBJ (i_obj in V_EXS_BALANCE%ROWTYPE,  o_banking_bal out T_EXS_BANKING_BALANCE%ROWTYPE, o_surplus_stb_bal out T_EXS_SURPLUS_STB_BALANCE%ROWTYPE, o_lapsed_bal out T_EXS_LAPSED_BALANCE%ROWTYPE);
  PROCEDURE SAVE_TO_DB (i_excess_unit_type in varchar2,i_balance_exists in boolean,  i_banking_bal IN T_EXS_BANKING_BALANCE%ROWTYPE, i_surplus_stb_bal IN T_EXS_SURPLUS_STB_BALANCE%ROWTYPE, i_lapsed_bal in T_EXS_LAPSED_BALANCE%ROWTYPE  );
  procedure OPEN_BALANCE (i_excess_unit_type in varchar2, i_service_number in varchar2,i_reading_month in varchar2,i_reading_year in varchar2, o_result out varchar2, o_reason out varchar2);
END EXCESS_UNITS_HELPER;



CREATE OR REPLACE PACKAGE BODY EXCESS_UNITS_HELPER AS

PROCEDURE CREATE_NEW_OBJ (i_excess_unit_type in varchar2, i_service_number in varchar2,i_reading_month in varchar2,i_reading_year in varchar2, o_exs_bal out 
V_EXS_BALANCE%ROWTYPE)
  is
    v_process varchar2(50):='EXCESS_UNITS_HELPER.CREATE_NEW_OBJ';
    V_NEW_ID varchar2(50);
BEGIN
    -- create id
    CASE i_excess_unit_type
        WHEN 'BANKING' THEN
			V_NEW_ID := T_EXS_BANKING_BALANCE_SEQ.nextval;
        WHEN 'SURPLUS-STB' THEN
			V_NEW_ID := T_EXS_SURPLUS_STB_BALANCE_SEQ.nextval;
        WHEN 'LAPSED' THEN
			V_NEW_ID := T_EXS_LAPSED_BALANCE_SEQ.nextval;
	END CASE ;
    	-- set basic values 
	select  V_NEW_ID, i_excess_unit_type , i_reading_month ,i_reading_year ,  i_service_number, '0', '0', '0', '0', '0', '0', v_process, sysdate,v_process, sysdate, 'Y'
		into o_exs_bal.ID, o_exs_bal.excess_unit_type,o_exs_bal.reading_month,o_exs_bal.reading_year, o_exs_bal.M_COMPANY_SERVICE_NUM, 
		o_exs_bal.open_c1, o_exs_bal.open_c2, o_exs_bal.open_c3, o_exs_bal.open_c4, o_exs_bal.open_c5, o_exs_bal.open_total_units,o_exs_bal.open_update_by,o_exs_bal.open_update_dt, o_exs_bal.CREATED_BY, o_exs_bal.CREATED_DT, o_exs_bal.enabled  FROM dual;
	 
	-- set service values
	SELECT SER.M_COMPANY_ID,SER.M_COMPANY_NAME,SER.ID , SER.BANKING_SERVICE_ID,SER.BANKING_SERVICE_NUMBER,SER.FUEL_TYPE_CODE    
		into o_exs_bal.M_COMPANY_ID, o_exs_bal.M_COMPANY_NAME, o_exs_bal.M_COMPANY_SERVICE_ID, o_exs_bal.BANKING_SERVICE_ID, o_exs_bal.BANKING_SERVICE_NUM, o_exs_bal.FUEL_TYPE_CODE
		from V_COMPANY_SERVICE SER where SER."number" = i_service_number; 
 
END CREATE_NEW_OBJ;

PROCEDURE CREATE_OBJ_FROM_PREV_MONTH(i_excess_unit_type in varchar2, i_reading_month in varchar2,i_reading_year in varchar2, i_prev_bal in V_EXS_BALANCE%ROWTYPE, o_curr_bal out V_EXS_BALANCE%ROWTYPE)
  is
    v_process varchar2(50):='EXCESS_UNITS_HELPER.CREATE_OBJ_FROM_PREV_MONTH';
    V_NEW_ID varchar2(50);

BEGIN
    -- create id
    CASE i_excess_unit_type
        WHEN 'BANKING' THEN
			V_NEW_ID := T_EXS_BANKING_BALANCE_SEQ.nextval;
        WHEN 'SURPLUS-STB' THEN
			V_NEW_ID := T_EXS_SURPLUS_STB_BALANCE_SEQ.nextval;
        WHEN 'LAPSED' THEN
			V_NEW_ID := T_EXS_LAPSED_BALANCE_SEQ.nextval;
	END CASE;

	-- set basic values 
	select  V_NEW_ID, i_excess_unit_type , i_reading_month ,i_reading_year ,i_prev_bal.M_COMPANY_SERVICE_NUM, greatest(ROUND(NVL(i_prev_bal.CURR_C1,0),5),0) OPEN_C1,greatest(ROUND(NVL(i_prev_bal.CURR_C2,0),5),0) OPEN_C2,greatest(ROUND(NVL(i_prev_bal.CURR_C3,0),5),0) OPEN_C3,greatest(ROUND(NVL(i_prev_bal.CURR_C4,0),5),0) OPEN_C4,greatest(ROUND(NVL(i_prev_bal.CURR_C5,0),5),0) OPEN_C5,v_process, sysdate, v_process, sysdate, 'Y'
		into o_curr_bal.ID, o_curr_bal.excess_unit_type,o_curr_bal.reading_month,o_curr_bal.reading_year,o_curr_bal.M_COMPANY_SERVICE_NUM, 
		o_curr_bal.open_c1, o_curr_bal.open_c2, o_curr_bal.open_c3, o_curr_bal.open_c4, o_curr_bal.open_c5,o_curr_bal.open_update_by, o_curr_bal.OPEN_UPDATE_DT, o_curr_bal.CREATED_BY, o_curr_bal.CREATED_DT, o_curr_bal.enabled  FROM dual;
    select o_curr_bal.open_c1+ o_curr_bal.open_c2+ o_curr_bal.open_c3+ o_curr_bal.open_c4+ o_curr_bal.open_c5 into o_curr_bal.open_total_units from dual;
	-- set service values
	SELECT SER.M_COMPANY_ID,SER.M_COMPANY_NAME,SER.ID , SER.BANKING_SERVICE_ID,SER.BANKING_SERVICE_NUMBER,SER.FUEL_TYPE_CODE   
		into o_curr_bal.M_COMPANY_ID, o_curr_bal.M_COMPANY_NAME, o_curr_bal.M_COMPANY_SERVICE_ID, o_curr_bal.BANKING_SERVICE_ID, o_curr_bal.BANKING_SERVICE_NUM, o_curr_bal.FUEL_TYPE_CODE
		from V_COMPANY_SERVICE SER where SER."number" = o_curr_bal.M_COMPANY_SERVICE_NUM ;
        
END CREATE_OBJ_FROM_PREV_MONTH;
PROCEDURE SET_OBJ (i_obj in V_EXS_BALANCE%ROWTYPE,  o_banking_bal out T_EXS_BANKING_BALANCE%ROWTYPE, o_surplus_stb_bal out T_EXS_SURPLUS_STB_BALANCE%ROWTYPE, o_lapsed_bal out T_EXS_LAPSED_BALANCE%ROWTYPE)
  is
BEGIN 
     CASE i_obj.excess_unit_type
        WHEN 'BANKING' THEN
            select i_obj.ID,i_obj.READING_MONTH,i_obj.READING_YEAR,i_obj.M_COMPANY_ID,i_obj.M_COMPANY_NAME,i_obj.M_COMPANY_SERVICE_ID,i_obj.M_COMPANY_SERVICE_NUM,i_obj.BANKING_SERVICE_ID,i_obj.BANKING_SERVICE_NUM,
            i_obj.OPEN_C1,i_obj.OPEN_C2,i_obj.OPEN_C3,i_obj.OPEN_C4,i_obj.OPEN_C5,i_obj.OPEN_TOTAL_UNITS,i_obj.OPEN_REMARKS,i_obj.OPEN_UPDATE_DT,
            i_obj.INCR_EA1_C1,i_obj.INCR_EA1_C2,i_obj.INCR_EA1_C3,i_obj.INCR_EA1_C4,i_obj.INCR_EA1_C5,i_obj.INCR_EA1_TOTAL_UNITS,i_obj.INCR_EA1_REMARKS,i_obj.INCR_EA1_SRC_ID,i_obj.INCR_EA1_UPDATE_DT,
            i_obj.DECR_EA1_C1,i_obj.DECR_EA1_C2,i_obj.DECR_EA1_C3,i_obj.DECR_EA1_C4,i_obj.DECR_EA1_C5,i_obj.DECR_EA1_TOTAL_UNITS,i_obj.DECR_EA1_REMARKS,i_obj.DECR_EA1_SRC_ID,i_obj.DECR_EA1_UPDATE_DT,
            i_obj.INCR_HT_C1,i_obj.INCR_HT_C2,i_obj.INCR_HT_C3,i_obj.INCR_HT_C4,i_obj.INCR_HT_C5,i_obj.INCR_HT_TOTAL_UNITS,i_obj.INCR_HT_REMARKS,i_obj.INCR_HT_SRC_ID,i_obj.INCR_HT_UPDATE_DT,
            i_obj.CURR_C1,i_obj.CURR_C2,i_obj.CURR_C3,i_obj.CURR_C4,i_obj.CURR_C5,i_obj.CURR_TOTAL_UNITS,i_obj.CURR_REMARKS,i_obj.CURR_UPDATE_DT,
            i_obj.REMARKS,i_obj.CREATED_BY,i_obj.CREATED_DT,i_obj.MODIFIED_BY,i_obj.MODIFIED_DT,i_obj.ENABLED,i_obj.FUEL_TYPE_CODE
            into 
            o_banking_bal.ID,o_banking_bal.READING_MONTH,o_banking_bal.READING_YEAR,o_banking_bal.M_COMPANY_ID,o_banking_bal.M_COMPANY_NAME,o_banking_bal.M_COMPANY_SERVICE_ID,o_banking_bal.M_COMPANY_SERVICE_NUM,o_banking_bal.BANKING_SERVICE_ID,o_banking_bal.BANKING_SERVICE_NUM,
            o_banking_bal.OPEN_C1,o_banking_bal.OPEN_C2,o_banking_bal.OPEN_C3,o_banking_bal.OPEN_C4,o_banking_bal.OPEN_C5,o_banking_bal.OPEN_TOTAL_UNITS,o_banking_bal.OPEN_REMARKS,o_banking_bal.OPEN_UPDATE_DT,
            o_banking_bal.INCR_EA1_C1,o_banking_bal.INCR_EA1_C2,o_banking_bal.INCR_EA1_C3,o_banking_bal.INCR_EA1_C4,o_banking_bal.INCR_EA1_C5,o_banking_bal.INCR_EA1_TOTAL_UNITS,o_banking_bal.INCR_EA1_REMARKS,o_banking_bal.INCR_EA1_SRC_ID,o_banking_bal.INCR_EA1_UPDATE_DT,
            o_banking_bal.DECR_EA1_C1,o_banking_bal.DECR_EA1_C2,o_banking_bal.DECR_EA1_C3,o_banking_bal.DECR_EA1_C4,o_banking_bal.DECR_EA1_C5,o_banking_bal.DECR_EA1_TOTAL_UNITS,o_banking_bal.DECR_EA1_REMARKS,o_banking_bal.DECR_EA1_SRC_ID,o_banking_bal.DECR_EA1_UPDATE_DT,
            o_banking_bal.INCR_HT_C1,o_banking_bal.INCR_HT_C2,o_banking_bal.INCR_HT_C3,o_banking_bal.INCR_HT_C4,o_banking_bal.INCR_HT_C5,o_banking_bal.INCR_HT_TOTAL_UNITS,o_banking_bal.INCR_HT_REMARKS,o_banking_bal.INCR_HT_SRC_ID,o_banking_bal.INCR_HT_UPDATE_DT,
            o_banking_bal.CURR_C1,o_banking_bal.CURR_C2,o_banking_bal.CURR_C3,o_banking_bal.CURR_C4,o_banking_bal.CURR_C5,o_banking_bal.CURR_TOTAL_UNITS,o_banking_bal.CURR_REMARKS,o_banking_bal.CURR_UPDATE_DT,
            o_banking_bal.REMARKS,o_banking_bal.CREATED_BY,o_banking_bal.CREATED_DT,o_banking_bal.MODIFIED_BY,o_banking_bal.MODIFIED_DT,o_banking_bal.ENABLED,o_banking_bal.FUEL_TYPE_CODE
            from dual;
        WHEN  'SURPLUS-STB' THEN
            select i_obj.ID,i_obj.READING_MONTH,i_obj.READING_YEAR,i_obj.M_COMPANY_ID,i_obj.M_COMPANY_NAME,i_obj.M_COMPANY_SERVICE_ID,i_obj.M_COMPANY_SERVICE_NUM,i_obj.BANKING_SERVICE_ID,i_obj.BANKING_SERVICE_NUM,
            i_obj.OPEN_C1,i_obj.OPEN_C2,i_obj.OPEN_C3,i_obj.OPEN_C4,i_obj.OPEN_C5,i_obj.OPEN_TOTAL_UNITS,i_obj.OPEN_REMARKS,i_obj.OPEN_UPDATE_DT,
            i_obj.INCR_EA1_C1,i_obj.INCR_EA1_C2,i_obj.INCR_EA1_C3,i_obj.INCR_EA1_C4,i_obj.INCR_EA1_C5,i_obj.INCR_EA1_TOTAL_UNITS,i_obj.INCR_EA1_REMARKS,i_obj.INCR_EA1_SRC_ID,i_obj.INCR_EA1_UPDATE_DT,
            i_obj.DECR_EA1_C1,i_obj.DECR_EA1_C2,i_obj.DECR_EA1_C3,i_obj.DECR_EA1_C4,i_obj.DECR_EA1_C5,i_obj.DECR_EA1_TOTAL_UNITS,i_obj.DECR_EA1_REMARKS,i_obj.DECR_EA1_SRC_ID,i_obj.DECR_EA1_UPDATE_DT,
            i_obj.INCR_HT_C1,i_obj.INCR_HT_C2,i_obj.INCR_HT_C3,i_obj.INCR_HT_C4,i_obj.INCR_HT_C5,i_obj.INCR_HT_TOTAL_UNITS,i_obj.INCR_HT_REMARKS,i_obj.INCR_HT_SRC_ID,i_obj.INCR_HT_UPDATE_DT,
            i_obj.CURR_C1,i_obj.CURR_C2,i_obj.CURR_C3,i_obj.CURR_C4,i_obj.CURR_C5,i_obj.CURR_TOTAL_UNITS,i_obj.CURR_REMARKS,i_obj.CURR_UPDATE_DT,
            i_obj.REMARKS,i_obj.CREATED_BY,i_obj.CREATED_DT,i_obj.MODIFIED_BY,i_obj.MODIFIED_DT,i_obj.ENABLED,i_obj.FUEL_TYPE_CODE
            into 
            o_surplus_stb_bal.ID,o_surplus_stb_bal.READING_MONTH,o_surplus_stb_bal.READING_YEAR,o_surplus_stb_bal.M_COMPANY_ID,o_surplus_stb_bal.M_COMPANY_NAME,o_surplus_stb_bal.M_COMPANY_SERVICE_ID,o_surplus_stb_bal.M_COMPANY_SERVICE_NUM,o_surplus_stb_bal.BANKING_SERVICE_ID,o_surplus_stb_bal.BANKING_SERVICE_NUM,
            o_surplus_stb_bal.OPEN_C1,o_surplus_stb_bal.OPEN_C2,o_surplus_stb_bal.OPEN_C3,o_surplus_stb_bal.OPEN_C4,o_surplus_stb_bal.OPEN_C5,o_surplus_stb_bal.OPEN_TOTAL_UNITS,o_surplus_stb_bal.OPEN_REMARKS,o_surplus_stb_bal.OPEN_UPDATE_DT,
            o_surplus_stb_bal.INCR_EA1_C1,o_surplus_stb_bal.INCR_EA1_C2,o_surplus_stb_bal.INCR_EA1_C3,o_surplus_stb_bal.INCR_EA1_C4,o_surplus_stb_bal.INCR_EA1_C5,o_surplus_stb_bal.INCR_EA1_TOTAL_UNITS,o_surplus_stb_bal.INCR_EA1_REMARKS,o_surplus_stb_bal.INCR_EA1_SRC_ID,o_surplus_stb_bal.INCR_EA1_UPDATE_DT,
            o_surplus_stb_bal.DECR_EA1_C1,o_surplus_stb_bal.DECR_EA1_C2,o_surplus_stb_bal.DECR_EA1_C3,o_surplus_stb_bal.DECR_EA1_C4,o_surplus_stb_bal.DECR_EA1_C5,o_surplus_stb_bal.DECR_EA1_TOTAL_UNITS,o_surplus_stb_bal.DECR_EA1_REMARKS,o_surplus_stb_bal.DECR_EA1_SRC_ID,o_surplus_stb_bal.DECR_EA1_UPDATE_DT,
            o_surplus_stb_bal.INCR_HT_C1,o_surplus_stb_bal.INCR_HT_C2,o_surplus_stb_bal.INCR_HT_C3,o_surplus_stb_bal.INCR_HT_C4,o_surplus_stb_bal.INCR_HT_C5,o_surplus_stb_bal.INCR_HT_TOTAL_UNITS,o_surplus_stb_bal.INCR_HT_REMARKS,o_surplus_stb_bal.INCR_HT_SRC_ID,o_surplus_stb_bal.INCR_HT_UPDATE_DT,
            o_surplus_stb_bal.CURR_C1,o_surplus_stb_bal.CURR_C2,o_surplus_stb_bal.CURR_C3,o_surplus_stb_bal.CURR_C4,o_surplus_stb_bal.CURR_C5,o_surplus_stb_bal.CURR_TOTAL_UNITS,o_surplus_stb_bal.CURR_REMARKS,o_surplus_stb_bal.CURR_UPDATE_DT,
            o_surplus_stb_bal.REMARKS,o_surplus_stb_bal.CREATED_BY,o_surplus_stb_bal.CREATED_DT,o_surplus_stb_bal.MODIFIED_BY,o_surplus_stb_bal.MODIFIED_DT,o_surplus_stb_bal.ENABLED,o_surplus_stb_bal.FUEL_TYPE_CODE
            from dual;
        WHEN  'LAPSED' THEN
            select i_obj.ID,i_obj.READING_MONTH,i_obj.READING_YEAR,i_obj.M_COMPANY_ID,i_obj.M_COMPANY_NAME,i_obj.M_COMPANY_SERVICE_ID,i_obj.M_COMPANY_SERVICE_NUM,i_obj.BANKING_SERVICE_ID,i_obj.BANKING_SERVICE_NUM,
            i_obj.OPEN_C1,i_obj.OPEN_C2,i_obj.OPEN_C3,i_obj.OPEN_C4,i_obj.OPEN_C5,i_obj.OPEN_TOTAL_UNITS,i_obj.OPEN_REMARKS,i_obj.OPEN_UPDATE_DT,
            i_obj.INCR_EA1_C1,i_obj.INCR_EA1_C2,i_obj.INCR_EA1_C3,i_obj.INCR_EA1_C4,i_obj.INCR_EA1_C5,i_obj.INCR_EA1_TOTAL_UNITS,i_obj.INCR_EA1_REMARKS,i_obj.INCR_EA1_SRC_ID,i_obj.INCR_EA1_UPDATE_DT,
            i_obj.DECR_EA1_C1,i_obj.DECR_EA1_C2,i_obj.DECR_EA1_C3,i_obj.DECR_EA1_C4,i_obj.DECR_EA1_C5,i_obj.DECR_EA1_TOTAL_UNITS,i_obj.DECR_EA1_REMARKS,i_obj.DECR_EA1_SRC_ID,i_obj.DECR_EA1_UPDATE_DT,
            i_obj.INCR_HT_C1,i_obj.INCR_HT_C2,i_obj.INCR_HT_C3,i_obj.INCR_HT_C4,i_obj.INCR_HT_C5,i_obj.INCR_HT_TOTAL_UNITS,i_obj.INCR_HT_REMARKS,i_obj.INCR_HT_SRC_ID,i_obj.INCR_HT_UPDATE_DT,
            i_obj.CURR_C1,i_obj.CURR_C2,i_obj.CURR_C3,i_obj.CURR_C4,i_obj.CURR_C5,i_obj.CURR_TOTAL_UNITS,i_obj.CURR_REMARKS,i_obj.CURR_UPDATE_DT,
            i_obj.REMARKS,i_obj.CREATED_BY,i_obj.CREATED_DT,i_obj.MODIFIED_BY,i_obj.MODIFIED_DT,i_obj.ENABLED,i_obj.FUEL_TYPE_CODE
            into 
            o_lapsed_bal.ID,o_lapsed_bal.READING_MONTH,o_lapsed_bal.READING_YEAR,o_lapsed_bal.M_COMPANY_ID,o_lapsed_bal.M_COMPANY_NAME,o_lapsed_bal.M_COMPANY_SERVICE_ID,o_lapsed_bal.M_COMPANY_SERVICE_NUM,o_lapsed_bal.BANKING_SERVICE_ID,o_lapsed_bal.BANKING_SERVICE_NUM,
            o_lapsed_bal.OPEN_C1,o_lapsed_bal.OPEN_C2,o_lapsed_bal.OPEN_C3,o_lapsed_bal.OPEN_C4,o_lapsed_bal.OPEN_C5,o_lapsed_bal.OPEN_TOTAL_UNITS,o_lapsed_bal.OPEN_REMARKS,o_lapsed_bal.OPEN_UPDATE_DT,
            o_lapsed_bal.INCR_EA1_C1,o_lapsed_bal.INCR_EA1_C2,o_lapsed_bal.INCR_EA1_C3,o_lapsed_bal.INCR_EA1_C4,o_lapsed_bal.INCR_EA1_C5,o_lapsed_bal.INCR_EA1_TOTAL_UNITS,o_lapsed_bal.INCR_EA1_REMARKS,o_lapsed_bal.INCR_EA1_SRC_ID,o_lapsed_bal.INCR_EA1_UPDATE_DT,
            o_lapsed_bal.DECR_EA1_C1,o_lapsed_bal.DECR_EA1_C2,o_lapsed_bal.DECR_EA1_C3,o_lapsed_bal.DECR_EA1_C4,o_lapsed_bal.DECR_EA1_C5,o_lapsed_bal.DECR_EA1_TOTAL_UNITS,o_lapsed_bal.DECR_EA1_REMARKS,o_lapsed_bal.DECR_EA1_SRC_ID,o_lapsed_bal.DECR_EA1_UPDATE_DT,
            o_lapsed_bal.INCR_HT_C1,o_lapsed_bal.INCR_HT_C2,o_lapsed_bal.INCR_HT_C3,o_lapsed_bal.INCR_HT_C4,o_lapsed_bal.INCR_HT_C5,o_lapsed_bal.INCR_HT_TOTAL_UNITS,o_lapsed_bal.INCR_HT_REMARKS,o_lapsed_bal.INCR_HT_SRC_ID,o_lapsed_bal.INCR_HT_UPDATE_DT,
            o_lapsed_bal.CURR_C1,o_lapsed_bal.CURR_C2,o_lapsed_bal.CURR_C3,o_lapsed_bal.CURR_C4,o_lapsed_bal.CURR_C5,o_lapsed_bal.CURR_TOTAL_UNITS,o_lapsed_bal.CURR_REMARKS,o_lapsed_bal.CURR_UPDATE_DT,
            o_lapsed_bal.REMARKS,o_lapsed_bal.CREATED_BY,o_lapsed_bal.CREATED_DT,o_lapsed_bal.MODIFIED_BY,o_lapsed_bal.MODIFIED_DT,o_lapsed_bal.ENABLED,o_lapsed_bal.FUEL_TYPE_CODE
            from dual;
    END CASE;
END SET_OBJ;
PROCEDURE SAVE_TO_DB (i_excess_unit_type in varchar2,i_balance_exists in boolean,  i_banking_bal IN T_EXS_BANKING_BALANCE%ROWTYPE, i_surplus_stb_bal IN T_EXS_SURPLUS_STB_BALANCE%ROWTYPE, i_lapsed_bal in T_EXS_LAPSED_BALANCE%ROWTYPE  )
  is
BEGIN 
        CASE i_excess_unit_type
            WHEN 'BANKING' THEN
                if (i_balance_exists)then
                    update T_EXS_BANKING_BALANCE set row = i_banking_bal where id=i_banking_bal.id;
                else
                    insert into T_EXS_BANKING_BALANCE VALUES i_banking_bal;
                end if;
            WHEN 'SURPLUS-STB' THEN
                if (i_balance_exists)then
                    update T_EXS_SURPLUS_STB_BALANCE set row = i_surplus_stb_bal where id=i_surplus_stb_bal.id;
                else
                    insert into T_EXS_SURPLUS_STB_BALANCE VALUES i_surplus_stb_bal;
                end if;
            WHEN 'LAPSED' THEN
                if (i_balance_exists)then
                    update T_EXS_LAPSED_BALANCE set row = i_lapsed_bal where id=i_lapsed_bal.id;
                else
                    insert into T_EXS_LAPSED_BALANCE VALUES i_lapsed_bal;
                end if;
        END CASE;
        
END SAVE_TO_DB;
procedure OPEN_BALANCE (i_excess_unit_type in varchar2, i_service_number in varchar2,i_reading_month in varchar2,i_reading_year in varchar2, o_result out varchar2, o_reason out varchar2)
 is
    v_process varchar2(50):='EXCESS_UNITS_HELPER.OPEN_BALANCE';
    v_process_type varchar2(50):='PACKAGE';
    v_stage varchar2(500):='';
    v_step varchar2(500):='';
    v_message varchar2(500):=''; 
    v_log_result  varchar2(500);
    v_count number;
	v_prev_reading_month varchar2(10):='';
	v_prev_reading_year varchar2(10):='';
	v_excess_unit_type varchar2(50):='';
    v_excess_reason_code varchar2(100):='';
    v_create_empty_record boolean := false;
	v_prev_bal V_EXS_BALANCE%ROWTYPE;
	v_curr_bal V_EXS_BALANCE%ROWTYPE;
	v_banking_bal T_EXS_BANKING_BALANCE%ROWTYPE;
	v_surplus_stb_bal T_EXS_SURPLUS_STB_BALANCE%ROWTYPE;
	v_lapsed_bal T_EXS_LAPSED_BALANCE%ROWTYPE;
 BEGIN
    BEGIN
    
		v_stage:='INIT'; v_step:=i_excess_unit_type|| '-START'; v_message:=''; 
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
        
		v_prev_reading_month := to_char(to_date( '01-'||i_reading_month||'-'||i_reading_year,'dd-mm-yyyy')-1,'mm');
		v_prev_reading_year := to_char(to_date( '01-'||i_reading_month||'-'||i_reading_year,'dd-mm-yyyy')-1,'yyyy');
		
        v_stage:='PROCESS'; v_step:=i_excess_unit_type|| '-check if balance already exists for current month '; v_message:=''; 
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
        
       -- check if balance already exists for current month 
        select count(*) into v_count from V_EXS_BALANCE where EXCESS_UNIT_TYPE=i_excess_unit_type and READING_MONTH = i_reading_month and READING_YEAR = i_reading_year and M_COMPANY_SERVICE_NUM = i_service_number ;
        
        -- exit if balance already exists for current month
        if(v_count > 0) then
            o_result:='FAILURE';
            o_reason:='balance already exists for '||i_excess_unit_type;
            GOTO THE_END;
        end if;
       
       v_stage:='PROCESS'; v_step:=i_excess_unit_type|| '-checking  previous month balance'; v_message:=''; 
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
       -- check if balance exists for previous month 
        select count(*) into v_count from V_EXS_BALANCE where EXCESS_UNIT_TYPE=i_excess_unit_type and READING_MONTH = v_prev_reading_month and READING_YEAR = v_prev_reading_year and M_COMPANY_SERVICE_NUM = i_service_number ;
        
        
       v_stage:='PROCESS'; v_step:=i_excess_unit_type|| '-checking  previous month balance'; v_message:=' balancecount-'||v_count; 
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
        
        -- find excess-unit-reason
        EXCESS_UNITS.FIND_TYPE( i_service_number => i_service_number, O_TYPE => v_excess_unit_type,O_REASON => O_REASON);
        v_excess_reason_code := SUBSTR(O_REASON, 0, trim(INSTR(O_REASON, '-')-1));
        
        if (
                -- fetch the previous month's record 
                i_reading_month='04' 
                -- reset balance for 20+years old gen
                or (v_excess_reason_code='004' and i_excess_unit_type in ('BANKING','SURPLUS-STB') )
                -- previous balance already exists 
                or (v_excess_reason_code!='004' and v_count <= 0 )
            )then
            v_create_empty_record := true;
        else
            v_create_empty_record := false;
        end if;

            
        if(v_create_empty_record) then
            CREATE_NEW_OBJ(i_excess_unit_type, i_service_number, i_reading_month, i_reading_year, v_curr_bal);
        else        
            select * into v_prev_bal from V_EXS_BALANCE where EXCESS_UNIT_TYPE=i_excess_unit_type and READING_MONTH = v_prev_reading_month and READING_YEAR = v_prev_reading_year and M_COMPANY_SERVICE_NUM = i_service_number ;
            CREATE_OBJ_FROM_PREV_MONTH(i_excess_unit_type, i_reading_month, i_reading_year, v_prev_bal, v_curr_bal);
        end if;
        
        v_stage:='PROCESS'; v_step:=i_excess_unit_type|| '-created balance object'; v_message:='id-'||v_curr_bal.id; 
        v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
            
        SET_OBJ (v_curr_bal, v_banking_bal ,v_surplus_stb_bal ,v_lapsed_bal); 
        
        v_stage:='PROCESS'; v_step:=i_excess_unit_type|| '-set to specific object'; v_message:='id-'||v_curr_bal.id; 
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
        
        CASE i_excess_unit_type
            WHEN 'BANKING' THEN
                insert into T_EXS_BANKING_BALANCE VALUES v_banking_bal;
            WHEN 'SURPLUS-STB' THEN
                insert into T_EXS_SURPLUS_STB_BALANCE VALUES v_surplus_stb_bal;
            WHEN 'LAPSED' THEN
                insert into T_EXS_LAPSED_BALANCE VALUES v_lapsed_bal;
        END CASE;
            
        v_stage:='PROCESS'; v_step:=i_excess_unit_type|| '-inserted into balance table'; v_message:='id-'||v_curr_bal.id; 
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
    exception
    when others then
        v_stage:='EXCEPTION';
        v_message := SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 100);
        v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
    END;
    <<THE_END>>
    commit;
    v_stage:='END';
    v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,i_service_number,i_reading_month, i_reading_year);
end OPEN_BALANCE;
END EXCESS_UNITS_HELPER;
