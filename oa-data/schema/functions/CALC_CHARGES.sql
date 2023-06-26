CREATE OR REPLACE FUNCTION OPENACCESS."CALC_CHARGES" (
    V_CHARGE_CODE IN VARCHAR2 ,
    PARAM1        IN VARCHAR2 ,
    PARAM2        IN VARCHAR2 ,
    PARAM3        IN VARCHAR2 )
  RETURN NUMBER
AS
  v_evaluated      NUMBER;
  v_id             NUMBER;
  v_formula        VARCHAR2(150);
  v_charge_type    VARCHAR2(150);
  v_unit_charge    VARCHAR2(150);
  v_result varchar(300):='SUCCESS';
  v_log_result varchar(300):='SUCCESS';
  v_exception_code VARCHAR2(150);
  v_exception_msg  VARCHAR2(150);
  v_reason VARCHAR2(300);
BEGIN
  BEGIN
    SELECT charge_type_code,unit_charge,formula INTO v_charge_type,v_unit_charge,v_formula FROM M_CHARGE_DEFN WHERE CHARGE_CODE = V_CHARGE_CODE;

    IF(TRIM(v_charge_type) = 'FORMULA')THEN
      IF(PARAM1   IS NOT NULL AND v_formula LIKE '%{$1}%')THEN
        v_formula := REPLACE(v_formula,'{$1}',PARAM1);
      END IF;
      IF(PARAM2   IS NOT NULL AND v_formula LIKE '%{$2}%')THEN
        v_formula := REPLACE(v_formula,'{$2}',PARAM2);
      END IF;
      IF(PARAM3   IS NOT NULL AND v_formula LIKE '%{$3}%')THEN
        v_formula := REPLACE(v_formula,'{$3}',PARAM3);
      END IF;
      EXECUTE immediate 'select '||v_formula||' from dual' INTO v_evaluated;
    ELSIF(TRIM(v_charge_type) = 'LUMP_SUM') THEN
      v_evaluated      := v_unit_charge;
    ELSE
      v_evaluated := v_unit_charge*PARAM1;
    END IF;
  EXCEPTION
  WHEN OTHERS THEN
    v_exception_code := SQLCODE;
    v_exception_msg  := SUBSTR(SQLERRM, 1, 100);
    v_result := 'FAILURE';
    v_reason := v_exception_code || ' - ' || v_exception_msg;
    v_log_result := log_activity('PROCEDURE','CALC_CHARGES','EH','Isse in CALC_CHARGES - '||v_reason,V_CHARGE_CODE,'admin', sysdate,PARAM1,PARAM2,PARAM3);
    --SELECT nvl(max(id),0) into v_id  FROM error_table;
    --INSERT INTO ERROR_TABLE  VALUES (v_id,'DB-CALC_CHARGES', v_exception_code || ' - ' || v_exception_msg ,'');


  END;
RETURN v_evaluated;
END CALC_CHARGES;

