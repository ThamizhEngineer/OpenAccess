--------------------------------------------------------
--  File created - Monday-April-09-2018   
--------------------------------------------------------
DROP FUNCTION "OPENACCESS"."CALC_GS_CHARGES";
DROP FUNCTION "OPENACCESS"."CALC_CHARGES";
--------------------------------------------------------
--  DDL for Table M_CHARGES_MAP
--------------------------------------------------------

  CREATE TABLE "OPENACCESS"."M_CHARGES_MAP" 
   (	"ID" VARCHAR2(50 BYTE), 
	"CONTEXT" VARCHAR2(100 BYTE), 
	"M_CHARGE_ID" VARCHAR2(50 BYTE), 
	"REMARKS" VARCHAR2(100 BYTE), 
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
--  DDL for Table T_GEN_STMT
--------------------------------------------------------

  CREATE TABLE "OPENACCESS"."T_GEN_STMT" 
   (	"ID" VARCHAR2(50 BYTE), 
	"STATUS_CODE" VARCHAR2(50 BYTE), 
	"REMARKS" VARCHAR2(500 BYTE), 
	"M_COMPANY_METER_ID" VARCHAR2(50 BYTE), 
	"REF_NUMBER" VARCHAR2(100 BYTE), 
	"T_MR_IDS" VARCHAR2(100 BYTE), 
	"MF" VARCHAR2(50 BYTE), 
	"MACHINE_CAPACITY" VARCHAR2(50 BYTE), 
	"STMT_GEN_DATE" DATE, 
	"STMT_MONTH" VARCHAR2(50 BYTE), 
	"STMT_YEAR" VARCHAR2(50 BYTE), 
	"INIT_STMT_DT" DATE, 
	"FINAL_STMT_DT" DATE, 
	"RKVAH_INIT" VARCHAR2(50 BYTE), 
	"RKVAH_FINAL" VARCHAR2(50 BYTE), 
	"RKVAH_DIFF" VARCHAR2(50 BYTE), 
	"RKVAH_UNITS" VARCHAR2(50 BYTE), 
	"KVAH_INIT" VARCHAR2(50 BYTE), 
	"KVAH_FINAL" VARCHAR2(50 BYTE), 
	"KVAH_DIFF" VARCHAR2(50 BYTE), 
	"KVAH_UNITS" VARCHAR2(50 BYTE), 
	"TOTAL_IMPORT_GEN" VARCHAR2(50 BYTE), 
	"TOTAL_EXPORT_GEN" VARCHAR2(50 BYTE), 
	"M_ORG_ID" VARCHAR2(50 BYTE), 
	"M_COMPANY_ID" VARCHAR2(50 BYTE), 
	"M_COMPANY_SERVICE_ID" VARCHAR2(50 BYTE), 
	"DISP_COMPANY_NAME" VARCHAR2(100 BYTE), 
	"DISP_SERVICE_NUMBER" VARCHAR2(50 BYTE), 
	"INJECTING_VOLTAGE_CODE" VARCHAR2(50 BYTE), 
	"DISP_ORG_NAME" VARCHAR2(100 BYTE), 
	"POWER_FACTOR" VARCHAR2(50 BYTE), 
	"NET_GENERATION" VARCHAR2(50 BYTE), 
	"TOTAL_CHARGED_AMOUNT" VARCHAR2(50 BYTE), 
	"C1" VARCHAR2(50 BYTE), 
	"C2" VARCHAR2(50 BYTE), 
	"C3" VARCHAR2(50 BYTE), 
	"C4" VARCHAR2(50 BYTE), 
	"C5" VARCHAR2(50 BYTE), 
	"CREATED_BY" VARCHAR2(100 BYTE), 
	"CREATED_DT" DATE, 
	"MODIFIED_BY" VARCHAR2(100 BYTE), 
	"MODIFIED_DT" DATE, 
	"PENALTY_RATE" VARCHAR2(100 BYTE), 
	"COMMISSION_DATE" DATE, 
	"CREATED_DATE" DATE DEFAULT sysdate, 
	"ENABLED" CHAR(1 BYTE) DEFAULT 'Y', 
	"FILE_NAME" VARCHAR2(50 BYTE), 
	"DISP_FUEL_TYPE_CODE" VARCHAR2(100 BYTE), 
	"DISP_FUEL_TYPE_NAME" VARCHAR2(100 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table T_GEN_STMT_CHARGE
--------------------------------------------------------

  CREATE TABLE "OPENACCESS"."T_GEN_STMT_CHARGE" 
   (	"ID" VARCHAR2(50 BYTE), 
	"REMARKS" VARCHAR2(100 BYTE), 
	"T_GEN_STMT_ID" VARCHAR2(50 BYTE), 
	"CHARGE_CODE" VARCHAR2(50 BYTE), 
	"CHARGE_DESC" VARCHAR2(100 BYTE), 
	"CHARGE_TYPE_CODE" VARCHAR2(50 BYTE), 
	"UNIT_CHARGE" VARCHAR2(50 BYTE), 
	"TOTAL_CHARGES" VARCHAR2(50 BYTE), 
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
--  DDL for Sequence T_GEN_STMT_CHARGE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OPENACCESS"."T_GEN_STMT_CHARGE_SEQ"  MINVALUE 0 MAXVALUE 10000000 INCREMENT BY 1 START WITH 4666 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Function CALC_GS_CHARGES
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "OPENACCESS"."CALC_GS_CHARGES" (
    v_gs_id IN VARCHAR2 )
  RETURN VARCHAR2
AS
  v_evaluated      NUMBER;
  v_id             NUMBER;
  v_no_of_days number:=0;
  v_machine_capacity number:=0;
  v_penalty_rate number:=0;
  v_rkvah_inits number:=0;
  v_total_charges number:=0;
  v_grand_total_charges number:=0;
  v_formula        VARCHAR2(150);
  v_charge_type    VARCHAR2(150);
  v_unit_charge    VARCHAR2(150);
  v_charge         VARCHAR2(150);
  v_net_generation VARCHAR2(150);
  v_result varchar(300):='SUCCESS';
  v_exception_code VARCHAR2(150);
  v_exception_msg  VARCHAR2(150);
BEGIN
  BEGIN

	  SELECT gs.machine_capacity, (gs.FINAL_STMT_DT - gs.INIT_STMT_DT)+1 no_of_days, gs.penalty_rate, gs.RKVAH_UNITS ,gs.NET_GENERATION
    INTO v_machine_capacity,v_no_of_days,v_penalty_rate, v_rkvah_inits,v_net_generation
    FROM T_GEN_STMT gs where id = v_gs_id;

		FOR meter IN (SELECT CHARGE_CODE, CHARGE_DESC, CHARGE_TYPE_CODE, UNIT_CHARGE FROM M_CHARGE_defn d JOIN  M_CHARGES_MAP m ON d.id = m.M_CHARGE_ID AND CONTEXT = 'GENERATOR_STATEMENT' )
		LOOP
      BEGIN
        if(meter.charge_code = 'C001') then
            v_total_charges:= to_number(CALC_CHARGES(meter.charge_code,'','',''));
        ELSIF(meter.charge_code = 'C002') then
             v_total_charges:= to_number(CALC_CHARGES(meter.charge_code,v_machine_capacity,'',''));
        ELSIF(meter.charge_code = 'C003') then
            v_total_charges:= to_number(CALC_CHARGES(meter.charge_code,v_machine_capacity,v_no_of_days,''));
        ELSIF(meter.charge_code = 'C004') then
            v_total_charges:= to_number(CALC_CHARGES(meter.charge_code,v_machine_capacity,v_no_of_days,''));
        ELSIF(meter.charge_code = 'C005') then        
            IF((to_number(v_net_generation)*0.1)>=v_rkvah_inits) then
                v_penalty_rate:= 0.25;
            ELSE
                v_penalty_rate:=0.5;   
            end if;

            v_total_charges:= to_number(CALC_CHARGES(meter.charge_code,v_penalty_rate,v_rkvah_inits,''));
        ELSIF(meter.charge_code = 'C006') then
            v_total_charges:= to_number(CALC_CHARGES(meter.charge_code,v_charge,v_net_generation,''));
        ELSIF(meter.charge_code = 'C007') then
            v_total_charges:= to_number(CALC_CHARGES(meter.charge_code,v_no_of_days,'',''));
        else
          continue;
        end if;
        INSERT INTO  T_GEN_STMT_CHARGE
        (ID, T_GEN_STMT_ID, CHARGE_CODE, CHARGE_DESC, CHARGE_TYPE_CODE, UNIT_CHARGE, TOTAL_CHARGES)
        VALUES(T_GEN_STMT_CHARGE_SEQ.nextval, v_gs_id, meter.charge_code,meter.charge_desc,meter.CHARGE_TYPE_CODE, meter.UNIT_CHARGE, ROUND(v_total_charges));
        v_grand_total_charges :=v_grand_total_charges+v_total_charges;
      EXCEPTION
      WHEN OTHERS THEN
        v_exception_code := SQLCODE;
        v_exception_msg  := SUBSTR(SQLERRM, 1, 100);
        v_result := 'FAILURE' || ' - ' || v_exception_code || ' - ' || v_exception_msg;
        dbms_output.put_line('Isse in loop-'||v_result);
        dbms_output.put_line('charge_code-'||meter.charge_code);
      END;      

    END LOOP;

      update t_gen_stmt set TOTAL_CHARGED_AMOUNT = v_grand_total_charges where id = v_gs_id;

  EXCEPTION
  WHEN OTHERS THEN
    v_exception_code := SQLCODE;
    v_exception_msg  := SUBSTR(SQLERRM, 1, 100);
    v_result := 'FAILURE' || ' - ' || v_exception_code || ' - ' || v_exception_msg;
    dbms_output.put_line('Isse in CALC_GS_CHARGES-'||v_result);
    --SELECT nvl(max(id),0) into v_id  FROM error_table;
    --INSERT INTO ERROR_TABLE  VALUES (v_id,'DB-CALC_CHARGES', v_exception_code || ' - ' || v_exception_msg ,'');
  END;
RETURN v_result;
END CALC_GS_CHARGES;

/
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
-- Unable to render SYNONYM DDL for object PUBLIC.DBMS_OUTPUT with DBMS_METADATA attempting internal generator.
CREATE PUBLIC SYNONYM DBMS_OUTPUT FOR SYS.DBMS_OUTPUT
