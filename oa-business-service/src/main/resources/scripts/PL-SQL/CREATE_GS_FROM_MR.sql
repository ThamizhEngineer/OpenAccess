--------------------------------------------------------
--  File created - Monday-April-09-2018   
--------------------------------------------------------
DROP FUNCTION "OPENACCESS"."CREATE_GS_FROM_MR";
DROP FUNCTION "OPENACCESS"."CALC_GS_CHARGES";
DROP FUNCTION "OPENACCESS"."LOG_ACTIVITY";
--------------------------------------------------------
--  DDL for View V_CODES
--------------------------------------------------------

  CREATE OR REPLACE FORCE EDITIONABLE VIEW "OPENACCESS"."V_CODES" ("LIST_CODE", "LIST_NAME", "VALUE_CODE", "VALUE_DESC", "ENABLED", "ATTRIB1", "ATTRIB2", "ATTRIB3") AS 
  SELECT m_lov_header.code    AS list_code,
  m_lov_header.name         AS list_name,  
  m_lov_detail.code         AS value_code,
  m_lov_detail.value_desc AS value_desc,
  m_lov_detail.enabled,
  m_lov_detail.attrib1,
  m_lov_detail.attrib2,
  m_lov_detail.attrib3
FROM m_lov_header
INNER JOIN m_lov_detail
ON m_lov_header.code = m_lov_detail.m_lov_code
order by m_lov_header.code,m_lov_detail.code
;
--------------------------------------------------------
--  DDL for Table M_COMPANY
--------------------------------------------------------

  CREATE TABLE "OPENACCESS"."M_COMPANY" 
   (	"ID" VARCHAR2(50 BYTE), 
	"CODE" VARCHAR2(50 BYTE), 
	"NAME" VARCHAR2(100 BYTE), 
	"COMPANY_TYPE_CODE" VARCHAR2(50 BYTE), 
	"REGISTRATION_NO" VARCHAR2(50 BYTE), 
	"REGISTRATION_DATE" DATE, 
	"COB_DATE" DATE, 
	"INCORP_PLACE" VARCHAR2(50 BYTE), 
	"IS_CAPTIVE" VARCHAR2(25 BYTE), 
	"CAPTIVE_PURPOSE" VARCHAR2(100 BYTE), 
	"PAN" VARCHAR2(50 BYTE), 
	"TAN" VARCHAR2(50 BYTE), 
	"CST" VARCHAR2(50 BYTE), 
	"GST" VARCHAR2(50 BYTE), 
	"ENABLED" CHAR(1 BYTE) DEFAULT 'Y', 
	"CREATED_BY" VARCHAR2(50 BYTE), 
	"CREATED_DATE" DATE DEFAULT sysdate, 
	"MODIFIED_BY" VARCHAR2(50 BYTE), 
	"MODIFIED_DATE" DATE, 
	"REMARKS" VARCHAR2(300 BYTE), 
	"TYPE" VARCHAR2(100 BYTE), 
	"IS_INTERNAL" CHAR(1 BYTE), 
	"BANKING_SERVICE_ID" VARCHAR2(100 BYTE), 
	"UNADJUSTED_SERVICE_ID" VARCHAR2(100 BYTE), 
	"BANKING_SERVICE_NUMBER" VARCHAR2(100 BYTE), 
	"UNADJUSTED_SERVICE_NUMBER" VARCHAR2(100 BYTE), 
	"IS_BUYER" CHAR(1 BYTE), 
	"IS_SELLER" CHAR(1 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table M_COMPANY_METER
--------------------------------------------------------

  CREATE TABLE "OPENACCESS"."M_COMPANY_METER" 
   (	"ID" VARCHAR2(50 BYTE), 
	"M_COMPANY_SERVICE_ID" VARCHAR2(50 BYTE), 
	"METER_NUMBER" VARCHAR2(100 BYTE), 
	"METER_MAKE_CODE" VARCHAR2(50 BYTE), 
	"ACCURACY_CLASS_CODE" VARCHAR2(50 BYTE), 
	"IS_ABTMETER" CHAR(1 BYTE), 
	"MF" VARCHAR2(50 BYTE), 
	"REMARKS" VARCHAR2(300 BYTE), 
	"CREATED_BY" VARCHAR2(50 BYTE), 
	"CREATED_DATE" DATE DEFAULT sysdate, 
	"MODIFIED_BY" VARCHAR2(50 BYTE), 
	"MODIFIED_DATE" DATE, 
	"MODEM_NUMBER" VARCHAR2(100 BYTE), 
	"ENABLED" CHAR(1 BYTE) DEFAULT 'Y', 
	"METER_CT1" VARCHAR2(50 BYTE), 
	"METER_CT2" VARCHAR2(50 BYTE), 
	"METER_CT3" VARCHAR2(50 BYTE), 
	"METER_PT1" VARCHAR2(50 BYTE), 
	"METER_PT2" VARCHAR2(50 BYTE), 
	"METER_PT3" VARCHAR2(50 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table M_COMPANY_SERVICE
--------------------------------------------------------

  CREATE TABLE "OPENACCESS"."M_COMPANY_SERVICE" 
   (	"ID" VARCHAR2(50 BYTE), 
	"COMP_SER_TYPE_CODE" VARCHAR2(25 BYTE), 
	"number" VARCHAR2(50 BYTE), 
	"M_COMPANY_ID" VARCHAR2(50 BYTE), 
	"M_ORG_ID" VARCHAR2(50 BYTE), 
	"CAPACITY" VARCHAR2(50 BYTE), 
	"M_SUBSTATION_ID" VARCHAR2(50 BYTE), 
	"M_FEEDER_ID" VARCHAR2(50 BYTE), 
	"REF_NUMBER" VARCHAR2(50 BYTE), 
	"VOLTAGE_CODE" VARCHAR2(50 BYTE), 
	"TARIFF" VARCHAR2(50 BYTE), 
	"TOTAL_CAPACITY" VARCHAR2(50 BYTE), 
	"ENABLED" CHAR(1 BYTE) DEFAULT 'Y', 
	"REMARKS" VARCHAR2(300 BYTE), 
	"CREATED_BY" VARCHAR2(50 BYTE), 
	"CREATED_DATE" DATE DEFAULT sysdate, 
	"MODIFIED_BY" VARCHAR2(50 BYTE), 
	"MODIFIED_DATE" DATE, 
	"TYPE" VARCHAR2(100 BYTE), 
	"BANKING_SERVICE_ID" VARCHAR2(100 BYTE), 
	"BANKING_SERVICE_NUMBER" VARCHAR2(100 BYTE), 
	"TL_SERVICE_ID" VARCHAR2(100 BYTE), 
	"TL_SERVICE_NUMBER" VARCHAR2(100 BYTE), 
	"DL_SERVICE_ID" VARCHAR2(100 BYTE), 
	"DL_SERVICE_NUMBER" VARCHAR2(100 BYTE), 
	"UNADJUSTED_SERVICE_ID" VARCHAR2(100 BYTE), 
	"UNADJUSTED_SERVICE_NUMBER" VARCHAR2(100 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table M_GENERATOR
--------------------------------------------------------

  CREATE TABLE "OPENACCESS"."M_GENERATOR" 
   (	"ID" VARCHAR2(50 BYTE), 
	"M_POWERPLANT_ID" VARCHAR2(50 BYTE), 
	"NAME" VARCHAR2(100 BYTE), 
	"MAKE_CODE" VARCHAR2(50 BYTE), 
	"SERIAL_NO" VARCHAR2(1000 BYTE), 
	"ROTOR_DIA" VARCHAR2(25 BYTE), 
	"HUB_HEIGHT" VARCHAR2(25 BYTE), 
	"CAPACITY" VARCHAR2(25 BYTE), 
	"REFERENCE_ID" VARCHAR2(50 BYTE), 
	"VOLTAGE_CODE" VARCHAR2(25 BYTE), 
	"ENABLED" CHAR(1 BYTE) DEFAULT 'Y', 
	"REMARKS" VARCHAR2(300 BYTE), 
	"CREATED_BY" VARCHAR2(50 BYTE), 
	"CREATED_DATE" DATE DEFAULT sysdate, 
	"MODIFIED_BY" VARCHAR2(50 BYTE), 
	"MODIFIED_DATE" DATE, 
	"NO_OF_UNITS" VARCHAR2(25 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table M_ORG
--------------------------------------------------------

  CREATE TABLE "OPENACCESS"."M_ORG" 
   (	"ID" VARCHAR2(50 BYTE), 
	"CODE" VARCHAR2(50 BYTE), 
	"NAME" VARCHAR2(50 BYTE), 
	"TYPE_CODE" VARCHAR2(50 BYTE), 
	"PARENT_CODE" VARCHAR2(50 BYTE), 
	"ADDRESS" VARCHAR2(200 BYTE), 
	"LANDLINE" VARCHAR2(25 BYTE), 
	"MOBILE" VARCHAR2(25 BYTE), 
	"EMAIL" VARCHAR2(25 BYTE), 
	"REMARKS" VARCHAR2(300 BYTE), 
	"CREATED_BY" VARCHAR2(50 BYTE), 
	"CREATED_DATE" DATE DEFAULT sysdate, 
	"MODIFIED_BY" VARCHAR2(50 BYTE), 
	"MODIFIED_DATE" DATE, 
	"ENABLED" CHAR(1 BYTE) DEFAULT 'Y', 
	"NCES_DIVISION_CODE" VARCHAR2(50 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table M_POWERPLANT
--------------------------------------------------------

  CREATE TABLE "OPENACCESS"."M_POWERPLANT" 
   (	"ID" VARCHAR2(50 BYTE), 
	"IS_PRIMARY" CHAR(1 BYTE), 
	"VERSION" NUMBER(2,0), 
	"CODE" VARCHAR2(50 BYTE), 
	"NAME" VARCHAR2(100 BYTE), 
	"PLANT_TYPE_CODE" VARCHAR2(50 BYTE), 
	"FUEL_TYPE_CODE" VARCHAR2(50 BYTE), 
	"M_SERVICE_ID" VARCHAR2(50 BYTE), 
	"M_ORG_ID" VARCHAR2(50 BYTE), 
	"T_GRID_CONN_APPLN_ID" VARCHAR2(50 BYTE), 
	"TOTAL_CAPACITY" VARCHAR2(25 BYTE), 
	"M_SUBSTATION_ID" VARCHAR2(50 BYTE), 
	"INTERFACE_VOLTAGE_PHASE" VARCHAR2(25 BYTE), 
	"INTERFACE_VOLTAGE_FREQUENCY" VARCHAR2(25 BYTE), 
	"COMMISSION_DATE" DATE, 
	"PURPOSE" VARCHAR2(500 BYTE), 
	"ENABLED" CHAR(1 BYTE) DEFAULT 'Y', 
	"STATUS" VARCHAR2(25 BYTE), 
	"LINE1" VARCHAR2(100 BYTE), 
	"CITY" VARCHAR2(50 BYTE), 
	"STATE_CODE" VARCHAR2(50 BYTE), 
	"PINCODE" VARCHAR2(50 BYTE), 
	"VILLAGE" VARCHAR2(50 BYTE), 
	"TALUK_CODE" VARCHAR2(50 BYTE), 
	"DISTRICT_CODE" VARCHAR2(50 BYTE), 
	"PLS_SF_NO" VARCHAR2(50 BYTE), 
	"PL_VILLAGE" VARCHAR2(50 BYTE), 
	"PL_TOWN" VARCHAR2(50 BYTE), 
	"PL_TALUK_CODE" VARCHAR2(50 BYTE), 
	"PL_DISTRICT_CODE" VARCHAR2(50 BYTE), 
	"WIND_PASS_CODE" VARCHAR2(50 BYTE), 
	"WIND_ZONE_AREA_CODE" VARCHAR2(50 BYTE), 
	"APPLICATION_DT" DATE, 
	"APPROVAL_DT" DATE, 
	"REMARKS" VARCHAR2(300 BYTE), 
	"CREATED_BY" VARCHAR2(50 BYTE), 
	"CREATED_DATE" DATE DEFAULT sysdate, 
	"MODIFIED_BY" VARCHAR2(50 BYTE), 
	"MODIFIED_DATE" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table T_BANKING_BALANCE
--------------------------------------------------------

  CREATE TABLE "OPENACCESS"."T_BANKING_BALANCE" 
   (	"ID" VARCHAR2(50 BYTE), 
	"M_COMPANY_ID" VARCHAR2(50 BYTE), 
	"BANKING_SERVICE_ID" VARCHAR2(50 BYTE), 
	"C1" VARCHAR2(50 BYTE) DEFAULT 0, 
	"C2" VARCHAR2(50 BYTE) DEFAULT 0, 
	"C3" VARCHAR2(50 BYTE) DEFAULT 0, 
	"C4" VARCHAR2(50 BYTE) DEFAULT 0, 
	"C5" VARCHAR2(50 BYTE) DEFAULT 0, 
	"REMARKS" VARCHAR2(500 BYTE), 
	"CREATED_BY" VARCHAR2(100 BYTE), 
	"CREATED_DT" DATE, 
	"MODIFIED_BY" VARCHAR2(100 BYTE), 
	"MODIFIED_DT" DATE, 
	"CREATED_DATE" DATE DEFAULT sysdate, 
	"ENABLED" CHAR(1 BYTE) DEFAULT 'Y', 
	"MONTH" VARCHAR2(50 BYTE), 
	"YEAR" VARCHAR2(50 BYTE)
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
--  DDL for Table T_GEN_STMT_SLOT
--------------------------------------------------------

  CREATE TABLE "OPENACCESS"."T_GEN_STMT_SLOT" 
   (	"ID" VARCHAR2(50 BYTE), 
	"REMARKS" VARCHAR2(500 BYTE), 
	"REF_NUMBER" VARCHAR2(100 BYTE), 
	"T_GEN_STMT_ID" VARCHAR2(50 BYTE), 
	"SLOT_CODE" VARCHAR2(50 BYTE), 
	"IMP_INIT" VARCHAR2(50 BYTE), 
	"IMP_FINAL" VARCHAR2(50 BYTE), 
	"IMP_DIFF" VARCHAR2(50 BYTE), 
	"IMP_UNITS" VARCHAR2(50 BYTE), 
	"EXP_INIT" VARCHAR2(50 BYTE), 
	"EXP_FINAL" VARCHAR2(50 BYTE), 
	"EXP_DIFF" VARCHAR2(50 BYTE), 
	"EXP_UNITS" VARCHAR2(50 BYTE), 
	"BANKED_BALANCE" VARCHAR2(50 BYTE), 
	"NET_UNITS" VARCHAR2(100 BYTE), 
	"CREATED_BY" VARCHAR2(100 BYTE), 
	"CREATED_DT" DATE, 
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
--  DDL for Table T_METER_READING_HDR
--------------------------------------------------------

  CREATE TABLE "OPENACCESS"."T_METER_READING_HDR" 
   (	"ID" VARCHAR2(50 BYTE), 
	"M_COMPANY_METER_ID" VARCHAR2(50 BYTE), 
	"STATUS_CODE" VARCHAR2(50 BYTE), 
	"REMARKS" VARCHAR2(500 BYTE), 
	"IMP_BATCH_ID" VARCHAR2(100 BYTE), 
	"MF" VARCHAR2(50 BYTE), 
	"SYS_DT" DATE, 
	"READING_MONTH" VARCHAR2(50 BYTE), 
	"READING_YEAR" VARCHAR2(50 BYTE), 
	"INIT_READING_DT" DATE, 
	"FINAL_READING_DT" DATE, 
	"TOTAL_IMPORT_GEN" VARCHAR2(50 BYTE), 
	"TOTAL_EXPORT_GEN" VARCHAR2(50 BYTE), 
	"CREATED_BY" VARCHAR2(50 BYTE), 
	"CREATED_DATE" DATE DEFAULT sysdate, 
	"MODIFIED_BY" VARCHAR2(50 BYTE), 
	"MODIFIED_DATE" DATE, 
	"RKVAH_DIFF" VARCHAR2(100 BYTE), 
	"RKVAH_UNITS" VARCHAR2(100 BYTE), 
	"KVAH_DIFF" VARCHAR2(100 BYTE), 
	"KVAH_UNITS" VARCHAR2(100 BYTE), 
	"IMP_RKVAH_INIT" VARCHAR2(100 BYTE), 
	"EXP_RKVAH_INIT" VARCHAR2(100 BYTE), 
	"IMP_RKVAH_FINAL" VARCHAR2(100 BYTE), 
	"EXP_RKVAH_FINAL" VARCHAR2(100 BYTE), 
	"IMP_KVAH_INIT" VARCHAR2(100 BYTE), 
	"EXP_KVAH_INIT" VARCHAR2(100 BYTE), 
	"IMP_KVAH_FINAL" VARCHAR2(100 BYTE), 
	"EXP_KVAH_FINAL" VARCHAR2(100 BYTE), 
	"NET_GEN_UNITS" VARCHAR2(100 BYTE), 
	"GS_BATCH_ID" VARCHAR2(100 BYTE), 
	"M_GEN_STMT_ID" VARCHAR2(100 BYTE), 
	"ENABLED" CHAR(1 BYTE) DEFAULT 'Y', 
	"CODE" VARCHAR2(50 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table T_METER_READING_SLOT
--------------------------------------------------------

  CREATE TABLE "OPENACCESS"."T_METER_READING_SLOT" 
   (	"ID" VARCHAR2(50 BYTE), 
	"T_METER_READING_HDR_ID" VARCHAR2(50 BYTE), 
	"REMARKS" VARCHAR2(500 BYTE), 
	"REF_NUMBER" VARCHAR2(100 BYTE), 
	"SLOT_CODE" VARCHAR2(50 BYTE), 
	"IMP_INIT" VARCHAR2(50 BYTE), 
	"IMP_FINAL" VARCHAR2(50 BYTE), 
	"IMP_DIFF" VARCHAR2(50 BYTE), 
	"IMP_UNITS" VARCHAR2(50 BYTE), 
	"EXP_INIT" VARCHAR2(50 BYTE), 
	"EXP_FINAL" VARCHAR2(50 BYTE), 
	"EXP_DIFF" VARCHAR2(50 BYTE), 
	"EXP_UNITS" VARCHAR2(50 BYTE), 
	"CREATED_BY" VARCHAR2(50 BYTE), 
	"CREATED_DATE" DATE DEFAULT sysdate, 
	"MODIFIED_BY" VARCHAR2(50 BYTE), 
	"MODIFIED_DATE" DATE, 
	"NET_UNITS" VARCHAR2(100 BYTE), 
	"ENABLED" CHAR(1 BYTE) DEFAULT 'Y'
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Sequence T_GEN_STMT_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OPENACCESS"."T_GEN_STMT_SEQ"  MINVALUE 0 MAXVALUE 10000000 INCREMENT BY 1 START WITH 755 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence T_GEN_STMT_SLOT_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OPENACCESS"."T_GEN_STMT_SLOT_SEQ"  MINVALUE 0 MAXVALUE 1000000 INCREMENT BY 1 START WITH 3675 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Function CREATE_GS_FROM_MR
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "OPENACCESS"."CREATE_GS_FROM_MR" 
(
  v_gs_process_id in varchar2,
  v_mr_id in varchar2
) RETURN VARCHAR2 AS 
 
v_process_id  VARCHAR2(50); 
v_gen_count  NUMBER:=0;
v_mrh T_METER_READING_HDR%ROWTYPE;
v_mr_c1 T_METER_READING_SLOT%ROWTYPE;
v_mr_c2 T_METER_READING_SLOT%ROWTYPE;
v_mr_c3 T_METER_READING_SLOT%ROWTYPE;
v_mr_c4 T_METER_READING_SLOT%ROWTYPE;
v_mr_c5 T_METER_READING_SLOT%ROWTYPE;
v_gs T_GEN_STMT%ROWTYPE;
v_gs_c1 T_GEN_STMT_SLOT%ROWTYPE;
v_gs_c2 T_GEN_STMT_SLOT%ROWTYPE;
v_gs_c3 T_GEN_STMT_SLOT%ROWTYPE;
v_gs_c4 T_GEN_STMT_SLOT%ROWTYPE;
v_gs_c5 T_GEN_STMT_SLOT%ROWTYPE;
v_no_records BOOLEAN:=TRUE;
v_created_Date DATE := SYSDATE;
v_created_By  varchar2(50):= 'admin';
v_status varchar2(50);
v_reason varchar2(200):='';
v_exception_code  NUMBER;
v_exception_msg  VARCHAR2(200);
v_result varchar(300):='';
v_log_result varchar(300):='SUCCESS';
v_imported BOOLEAN;

BEGIN
	
	BEGIN		
        v_log_result := log_activity('PROCEDURE','create_gs_from_mr','Start',v_reason,v_result,v_created_By, sysdate,v_mr_id,v_gs_process_id);
    
		FOR meter IN (SELECT mh.M_COMPANY_METER_ID, READING_MONTH, READING_YEAR,
						nvl(bg.C1,0) bc1, nvl(bg.C2,0) bc2, nvl(bg.C3,0) bc3,nvl(bg.C4,0) bc4,nvl(bg.C5,0) bc5,  
						cs.M_ORG_ID, c.ID M_COMPANY_ID,cm.M_COMPANY_SERVICE_ID, c.NAME DISP_COMPANY_NAME, cs."number" DISP_SERVICE_NUMBER, cs.VOLTAGE_CODE INJECTING_VOLTAGE_CODE, o.NAME DISP_ORG_NAME,
						pp.FUEL_TYPE_CODE DISP_FUEL_TYPE_CODE, fuel_codes.VALUE_DESC  DISP_FUEL_TYPE_NAME
						FROM T_METER_READING_HDR mh 
						INNER JOIN M_COMPANY_METER cm ON mh.M_COMPANY_METER_ID = cm.ID
						INNER JOIN M_COMPANY_SERVICE cs ON cm.M_COMPANY_SERVICE_ID = cs.id
						INNER JOIN M_COMPANY c ON cs.M_COMPANY_ID = c.id
						INNER JOIN M_ORG o ON cs.M_ORG_ID = o.id
                        LEFT JOIN T_BANKING_BALANCE bg ON bg.BANKING_SERVICE_ID = cs.BANKING_SERVICE_ID and to_number(bg.month)= to_number(mh.READING_MONTH) and to_number(bg.year)= to_number(mh.READING_YEAR)
                        LEFT JOIN M_POWERPLANT pp ON cs.id = pp.M_SERVICE_ID
                        LEFT JOIN V_CODES fuel_codes ON pp.FUEL_TYPE_CODE = fuel_codes.VALUE_CODE AND fuel_codes.list_code = 'FUEL_TYPE_CODE'				
						WHERE mh.M_GEN_STMT_ID IS NULL AND mh.GS_BATCH_ID IS NULL 
            			and mh.id = v_mr_id
						 )
		LOOP
			BEGIN
				-- as there is meter-readings to process, we have to set the flag accordingly and start the generation process
				if(v_no_records) THEN
					v_no_records := FALSE;
					
				END IF;
				
        
				--dbms_output.put_line('meter.M_COMPANY_METER_ID - '||meter.M_COMPANY_METER_ID);
				--dbms_output.put_line('meter.M_COMPANY_SERVICE_ID - '||meter.M_COMPANY_SERVICE_ID);
				--dbms_output.put_line('meter.READING_Year - '||meter.READING_Year);
				--dbms_output.put_line('meter.READING_MONTH - '||meter.READING_MONTH);
				
				-- to find generator capacity
				SELECT count(*) INTO v_gen_count FROM M_GENERATOR g JOIN M_POWERPLANT p ON g.M_POWERPLANT_ID = p.ID WHERE p.M_SERVICE_ID = meter.M_COMPANY_SERVICE_ID ;
	 			
                
				--dbms_output.put_line('v_gen_count- '||v_gen_count);
                
				if(v_gen_count =0) THEN
					-- setup issue - generator not configured properly for this service
					v_log_result := log_activity('PROCEDURE','PROCESS_GEN_STMT','ISSUE','Setup Issue - '||v_process_id||' - No generator configured for service-id-->'||meter.M_COMPANY_SERVICE_ID,null,v_created_By, v_created_Date);
					CONTINUE;
				ELSE	
          -- TODO - Sum() should be revisited
					SELECT sum(nvl(CAPACITY,0))  INTO v_gs.MACHINE_CAPACITY FROM M_GENERATOR g JOIN M_POWERPLANT p ON g.M_POWERPLANT_ID = p.ID WHERE p.M_SERVICE_ID = meter.M_COMPANY_SERVICE_ID ;
				END IF;
				
				
				--dbms_output.put_line('v_gs.MACHINE_CAPACITY - '||v_gs.MACHINE_CAPACITY);
				
				SELECT * INTO v_mrh FROM T_METER_READING_HDR WHERE  M_COMPANY_METER_ID = meter.M_COMPANY_METER_ID AND READING_MONTH = meter.READING_MONTH AND READING_Year = meter.READING_Year;
				
				
				--dbms_output.put_line('v_mrh.id - '||v_mrh.id);
				
				SELECT * INTO v_mr_c1 FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mrh.id AND SLOT_CODE = 'C1';
				SELECT * INTO v_mr_c2 FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mrh.id AND SLOT_CODE = 'C2';
				SELECT * INTO v_mr_c3 FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mrh.id AND SLOT_CODE = 'C3';
				SELECT * INTO v_mr_c4 FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mrh.id AND SLOT_CODE = 'C4';
				SELECT * INTO v_mr_c5 FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mrh.id AND SLOT_CODE = 'C5';
				
				
				--dbms_output.put_line('all slots- '||v_mrh.id);
				-- intialise PK, FKs for GenStmt and GenSlots
				v_gs.id := T_GEN_STMT_SEQ.nextval;
				v_gs_c1.id := T_GEN_STMT_SLOT_SEQ.nextval;v_gs_c1.T_GEN_STMT_ID := v_gs.id;
				v_gs_c2.id := T_GEN_STMT_SLOT_SEQ.nextval;v_gs_c2.T_GEN_STMT_ID := v_gs.id;
				v_gs_c3.id := T_GEN_STMT_SLOT_SEQ.nextval;v_gs_c3.T_GEN_STMT_ID := v_gs.id;
				v_gs_c4.id := T_GEN_STMT_SLOT_SEQ.nextval;v_gs_c4.T_GEN_STMT_ID := v_gs.id;
				v_gs_c5.id := T_GEN_STMT_SLOT_SEQ.nextval;v_gs_c5.T_GEN_STMT_ID := v_gs.id;
				
				-- set values in gen stmt
				v_gs.STATUS_CODE := 'CREATED';
				v_gs.M_COMPANY_METER_ID := v_mrh.M_COMPANY_METER_ID ;
				v_gs.T_MR_IDS := v_mrh.M_COMPANY_METER_ID ;
				v_gs.REF_NUMBER := v_gs_process_id;
        --v_gs.gs_batch_id := v_process_id;
				v_gs.mf := v_mrh.mf;
				v_gs.STMT_GEN_DATE := v_created_Date ;
				v_gs.STMT_MONTH :=  v_mrh.READING_MONTH;
				v_gs.STMT_YEAR := v_mrh.READING_YEAR;
				v_gs.INIT_STMT_DT := v_mrh.INIT_READING_DT;
				v_gs.FINAL_STMT_DT := v_mrh.FINAL_READING_DT;
				-- RKVAH_INIT, RKVAH_FINAL, KVAH_INIT, KVAH_FINAL are not relevant currently
				v_gs.KVAH_DIFF := v_mrh.KVAH_DIFF;
				v_gs.KVAH_UNITS := v_mrh.KVAH_UNITS;
				v_gs.RKVAH_DIFF := v_mrh.RKVAH_DIFF;
				v_gs.RKVAH_UNITS := v_mrh.RKVAH_UNITS;
				v_gs.TOTAL_EXPORT_GEN := v_mr_c1.EXP_UNITS+v_mr_c2.EXP_UNITS+v_mr_c3.EXP_UNITS+v_mr_c4.EXP_UNITS+v_mr_c5.EXP_UNITS;
				v_gs.TOTAL_IMPORT_GEN := v_mr_c1.IMP_UNITS+v_mr_c2.IMP_UNITS+v_mr_c3.IMP_UNITS+v_mr_c4.IMP_UNITS+v_mr_c5.IMP_UNITS;
				v_gs.M_ORG_ID := meter.M_ORG_ID;
				v_gs.M_COMPANY_ID := meter.M_COMPANY_ID;
				v_gs.M_COMPANY_SERVICE_ID := meter.M_COMPANY_SERVICE_ID;
				v_gs.DISP_COMPANY_NAME := meter.DISP_COMPANY_NAME;
				v_gs.DISP_SERVICE_NUMBER := meter.DISP_SERVICE_NUMBER;
				v_gs.INJECTING_VOLTAGE_CODE := meter.INJECTING_VOLTAGE_CODE;
				v_gs.DISP_ORG_NAME := meter.DISP_ORG_NAME; 
				v_gs.net_generation := v_mr_c1.NET_UNITS+v_mr_c2.NET_UNITS+v_mr_c3.NET_UNITS+v_mr_c4.NET_UNITS+v_mr_c5.NET_UNITS;
				v_gs.C1 := v_mr_c1.NET_UNITS;
				v_gs.C2 := v_mr_c2.NET_UNITS;
				v_gs.C3 := v_mr_c3.NET_UNITS;
				v_gs.C4 := v_mr_c4.NET_UNITS;
				v_gs.C5 := v_mr_c5.NET_UNITS;
				v_gs.disp_fuel_type_code := meter.disp_fuel_type_code;
				v_gs.disp_fuel_type_name := meter.disp_fuel_type_name;
				v_gs.created_by := v_created_By;
				v_gs.created_dt := SYSDATE;
				v_gs.enabled := 'Y';
				v_gs.TOTAL_CHARGED_AMOUNT := 0; --actual value calculated by calc_gs_charges() 
				
				if(to_number(v_gs.TOTAL_EXPORT_GEN)*0.01 >= to_number(v_gs.RKVAH_UNITS)) THEN
					v_gs.penalty_rate := '0.25';
				ELSE
					v_gs.penalty_rate := '0.50';
				END IF;
				
				
				--dbms_output.put_line('gen stmt - values set');
				
				SELECT v_mr_c1.SLOT_CODE,v_mr_c1.IMP_INIT, v_mr_c1.IMP_FINAL, v_mr_c1.IMP_DIFF, v_mr_c1.IMP_UNITS, v_mr_c1.EXP_INIT, v_mr_c1.EXP_FINAL, v_mr_c1.EXP_DIFF, v_mr_c1.EXP_UNITS, meter.bc1, v_mr_c1.NET_UNITS, 'Y',sysdate
				INTO  v_gs_c1.SLOT_CODE,v_gs_c1.IMP_INIT, v_gs_c1.IMP_FINAL, v_gs_c1.IMP_DIFF, v_gs_c1.IMP_UNITS, v_gs_c1.EXP_INIT, v_gs_c1.EXP_FINAL, v_gs_c1.EXP_DIFF, v_gs_c1.EXP_UNITS, v_gs_c1.BANKED_BALANCE, v_gs_c1.NET_UNITS, v_gs_c1.enabled, v_gs_c1.created_Date
				FROM dual;
							
				SELECT  v_mr_c2.SLOT_CODE,v_mr_c2.IMP_INIT, v_mr_c2.IMP_FINAL, v_mr_c2.IMP_DIFF, v_mr_c2.IMP_UNITS, v_mr_c2.EXP_INIT, v_mr_c2.EXP_FINAL, v_mr_c2.EXP_DIFF, v_mr_c2.EXP_UNITS, meter.bc2, v_mr_c2.NET_UNITS, 'Y',sysdate
				INTO  v_gs_c2.SLOT_CODE,v_gs_c2.IMP_INIT, v_gs_c2.IMP_FINAL, v_gs_c2.IMP_DIFF, v_gs_c2.IMP_UNITS, v_gs_c2.EXP_INIT, v_gs_c2.EXP_FINAL, v_gs_c2.EXP_DIFF, v_gs_c2.EXP_UNITS, v_gs_c2.BANKED_BALANCE, v_gs_c2.NET_UNITS, v_gs_c2.enabled, v_gs_c2.created_Date
				FROM dual;
				
				SELECT  v_mr_c3.SLOT_CODE,v_mr_c3.IMP_INIT, v_mr_c3.IMP_FINAL, v_mr_c3.IMP_DIFF, v_mr_c3.IMP_UNITS, v_mr_c3.EXP_INIT, v_mr_c3.EXP_FINAL, v_mr_c3.EXP_DIFF, v_mr_c3.EXP_UNITS, meter.bc3, v_mr_c3.NET_UNITS, 'Y',sysdate
				INTO  v_gs_c3.SLOT_CODE,v_gs_c3.IMP_INIT, v_gs_c3.IMP_FINAL, v_gs_c3.IMP_DIFF, v_gs_c3.IMP_UNITS, v_gs_c3.EXP_INIT, v_gs_c3.EXP_FINAL, v_gs_c3.EXP_DIFF, v_gs_c3.EXP_UNITS, v_gs_c3.BANKED_BALANCE, v_gs_c3.NET_UNITS, v_gs_c3.enabled, v_gs_c3.created_Date
				FROM dual;
				
				SELECT  v_mr_c4.SLOT_CODE,v_mr_c4.IMP_INIT, v_mr_c4.IMP_FINAL, v_mr_c4.IMP_DIFF, v_mr_c4.IMP_UNITS, v_mr_c4.EXP_INIT, v_mr_c4.EXP_FINAL, v_mr_c4.EXP_DIFF, v_mr_c4.EXP_UNITS, meter.bc4, v_mr_c4.NET_UNITS, 'Y',sysdate
				INTO  v_gs_c4.SLOT_CODE,v_gs_c4.IMP_INIT, v_gs_c4.IMP_FINAL, v_gs_c4.IMP_DIFF, v_gs_c4.IMP_UNITS, v_gs_c4.EXP_INIT, v_gs_c4.EXP_FINAL, v_gs_c4.EXP_DIFF, v_gs_c4.EXP_UNITS, v_gs_c4.BANKED_BALANCE, v_gs_c4.NET_UNITS, v_gs_c4.enabled, v_gs_c4.created_Date
				FROM dual;
				
				SELECT  v_mr_c5.SLOT_CODE,v_mr_c5.IMP_INIT, v_mr_c5.IMP_FINAL, v_mr_c5.IMP_DIFF, v_mr_c5.IMP_UNITS, v_mr_c5.EXP_INIT, v_mr_c5.EXP_FINAL, v_mr_c5.EXP_DIFF, v_mr_c5.EXP_UNITS, meter.bc5, v_mr_c5.NET_UNITS, 'Y',sysdate
				INTO  v_gs_c5.SLOT_CODE,v_gs_c5.IMP_INIT, v_gs_c5.IMP_FINAL, v_gs_c5.IMP_DIFF, v_gs_c5.IMP_UNITS, v_gs_c5.EXP_INIT, v_gs_c5.EXP_FINAL, v_gs_c5.EXP_DIFF, v_gs_c5.EXP_UNITS, v_gs_c5.BANKED_BALANCE, v_gs_c5.NET_UNITS, v_gs_c5.enabled, v_gs_c1.created_Date
				FROM dual;
						
				
				--dbms_output.put_line('gen stmt slots - insert');
				
				INSERT INTO T_GEN_STMT VALUES  v_gs;
				INSERT INTO T_GEN_STMT_SLOT VALUES  v_gs_c1;
				INSERT INTO T_GEN_STMT_SLOT VALUES  v_gs_c2;
				INSERT INTO T_GEN_STMT_SLOT VALUES  v_gs_c3;
				INSERT INTO T_GEN_STMT_SLOT VALUES  v_gs_c4;
				INSERT INTO T_GEN_STMT_SLOT VALUES  v_gs_c5;
				
				--dbms_output.put_line('gen stmt slots - calling charges');
				
				v_result := calc_gs_charges(v_gs.id);
				--dbms_output.put_line('gen stmt slots - after charges');
      
        update t_meter_reading_hdr set gs_batch_id = v_gs_process_id, M_GEN_STMT_ID = v_gs.id where id = v_mr_id;
        
				COMMIT;
			exception
			  when others then
			    v_exception_code := SQLCODE;
			    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
			    v_result := 'FAILURE';
			    v_reason := v_exception_code || ' - ' || v_exception_msg;
                v_log_result := log_activity('PROCEDURE','create_gs_from_mr','EH','Error while processing each meter-reading - '||v_reason,v_result,v_created_By, sysdate,v_mr_id,v_gs_process_id);
			END;
		END LOOP;
		
		
       -- --dbms_output.put_line('all slots- '||v_mrh.id);
		if(v_no_records) THEN
			v_result := 'FAILURE';
            if(v_reason='') then v_reason := 'No records to process'; end if;
		END IF;
        
        if( V_RESULT != '') THEN
			v_result := 'SUCCESS'; 
		END IF; 
	
	exception
	  when others then
	    v_exception_code := SQLCODE;
	    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
	    v_result := 'FAILURE';
	    v_reason := v_exception_code || ' - ' || v_exception_msg;
	   -- --dbms_output.put_line(v_reason);
      
        v_log_result := log_activity('PROCEDURE','create_gs_from_mr','EH',v_result,v_reason,v_created_By, sysdate,v_mr_id,v_gs_process_id);
	END;
   <<THE_END>>
	

      v_log_result := log_activity('PROCEDURE','create_gs_from_mr','End',v_result,v_reason,v_created_By, sysdate,v_mr_id,v_gs_process_id);
      
  COMMIT;
  
  return V_RESULT; 
  
END create_gs_from_mr;

/
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
--  DDL for Function LOG_ACTIVITY
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "OPENACCESS"."LOG_ACTIVITY" 
(
  v_type IN VARCHAR2,
  v_process IN VARCHAR2,
  v_activity IN VARCHAR2,
  v_message IN VARCHAR2,
  v_result IN VARCHAR2,
  v_created_by IN VARCHAR2,
  v_created_dt IN date,
  v_att1 in varchar2 default null,
  v_att2 in varchar2 default null,
  v_att3 in varchar2 default null
) RETURN VARCHAR2 AS 
BEGIN
	BEGIN
			INSERT INTO OPENACCESS.T_ACTIVITY_LOG (ID,PROCESS_TYPE,PROCESS_NAME,ACTIVITY_NAME,MESSAGE,"result",CREATED_BY,CREATED_DT, att1, att2, att3)
			VALUES (t_activity_log_seq.nextval,v_type,v_process,v_activity,v_message,v_result,v_created_by,sysdate,v_att1,v_att2,v_att3) ;
			COMMIT;
	exception
	  when others then
		dbms_output.put_line(SUBSTR(SQLERRM, 1, 200));
	END;
  return 'SUCCESS'; 
END log_activity;


/
-- Unable to render SYNONYM DDL for object PUBLIC.DUAL with DBMS_METADATA attempting internal generator.
CREATE PUBLIC SYNONYM DUAL FOR SYS."DUAL"
