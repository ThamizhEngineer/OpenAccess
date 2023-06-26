--------------------------------------------------------
--  File created - Monday-April-09-2018   
--------------------------------------------------------
DROP FUNCTION "OPENACCESS"."CALC_CHARGES";
--------------------------------------------------------
--  DDL for Table M_CHARGE_DEFN
--------------------------------------------------------

  CREATE TABLE "OPENACCESS"."M_CHARGE_DEFN" 
   (	"ID" VARCHAR2(50 BYTE), 
	"REMARKS" VARCHAR2(100 BYTE), 
	"CHARGE_CODE" VARCHAR2(50 BYTE), 
	"CHARGE_DESC" VARCHAR2(100 BYTE), 
	"CHARGE_TYPE_CODE" VARCHAR2(50 BYTE), 
	"UNIT_CHARGE" VARCHAR2(50 BYTE), 
	"FORMULA" VARCHAR2(100 BYTE), 
	"CREATED_DATE" DATE DEFAULT sysdate, 
	"ENABLED" CHAR(1 BYTE) DEFAULT 'Y'
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Function CALC_CHARGES
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "OPENACCESS"."CALC_CHARGES" (
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
  v_exception_code VARCHAR2(150);
  v_exception_msg  VARCHAR2(150);
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
    --SELECT nvl(max(id),0) into v_id  FROM error_table;
    --INSERT INTO ERROR_TABLE  VALUES (v_id,'DB-CALC_CHARGES', v_exception_code || ' - ' || v_exception_msg ,'');
  END;
RETURN v_evaluated;
END CALC_CHARGES;


/
