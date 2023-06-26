--------------------------------------------------------
--  File created - Monday-April-09-2018   
--------------------------------------------------------
DROP FUNCTION "OPENACCESS"."IMPORT_MR";
DROP FUNCTION "OPENACCESS"."CLEANSE_MRI";
DROP FUNCTION "OPENACCESS"."LOG_ACTIVITY";
--------------------------------------------------------
--  DDL for Table IMP_MR_HEADER
--------------------------------------------------------

  CREATE TABLE "OPENACCESS"."IMP_MR_HEADER" 
   (	"ID" VARCHAR2(50 BYTE), 
	"IMPORT_DT" DATE, 
	"FROM_DT" DATE, 
	"TO_DT" DATE, 
	"STATUS" VARCHAR2(50 BYTE), 
	"REMARKS" VARCHAR2(500 BYTE), 
	"ERROR" VARCHAR2(500 BYTE), 
	"MR_SOURCE_CODE" VARCHAR2(50 BYTE), 
	"CREATED_BY" VARCHAR2(50 BYTE), 
	"CREATED_DATE" DATE DEFAULT sysdate, 
	"MODIFIED_BY" VARCHAR2(50 BYTE), 
	"MODIFIED_DATE" DATE, 
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
--  DDL for Table IMP_MR_LINES
--------------------------------------------------------

  CREATE TABLE "OPENACCESS"."IMP_MR_LINES" 
   (	"ID" VARCHAR2(50 BYTE), 
	"STATUS_CODE" VARCHAR2(50 BYTE), 
	"REMARKS" VARCHAR2(500 BYTE), 
	"IMP_MR_HEADER_ID" VARCHAR2(50 BYTE), 
	"METER_NO" VARCHAR2(100 BYTE), 
	"MF" VARCHAR2(50 BYTE), 
	"SERVICE_NO" VARCHAR2(50 BYTE), 
	"SYS_DT" VARCHAR2(50 BYTE), 
	"INIT_READING_DT" VARCHAR2(50 BYTE), 
	"FINAL_READING_DT" VARCHAR2(50 BYTE), 
	"IMP_INIT_S1" VARCHAR2(50 BYTE), 
	"IMP_INIT_S2" VARCHAR2(50 BYTE), 
	"IMP_INIT_S3" VARCHAR2(50 BYTE), 
	"IMP_INIT_S4" VARCHAR2(50 BYTE), 
	"IMP_INIT_S5" VARCHAR2(50 BYTE), 
	"IMP_FINAL_S1" VARCHAR2(50 BYTE), 
	"IMP_FINAL_S2" VARCHAR2(50 BYTE), 
	"IMP_FINAL_S3" VARCHAR2(50 BYTE), 
	"IMP_FINAL_S4" VARCHAR2(50 BYTE), 
	"IMP_FINAL_S5" VARCHAR2(50 BYTE), 
	"EXP_INIT_S1" VARCHAR2(50 BYTE), 
	"EXP_INIT_S2" VARCHAR2(50 BYTE), 
	"EXP_INIT_S3" VARCHAR2(50 BYTE), 
	"EXP_INIT_S4" VARCHAR2(50 BYTE), 
	"EXP_INIT_S5" VARCHAR2(50 BYTE), 
	"EXP_FINAL_S1" VARCHAR2(50 BYTE), 
	"EXP_FINAL_S2" VARCHAR2(50 BYTE), 
	"EXP_FINAL_S3" VARCHAR2(50 BYTE), 
	"EXP_FINAL_S4" VARCHAR2(50 BYTE), 
	"EXP_FINAL_S5" VARCHAR2(50 BYTE), 
	"CREATED_BY" VARCHAR2(50 BYTE), 
	"CREATED_DATE" DATE DEFAULT sysdate, 
	"MODIFIED_BY" VARCHAR2(50 BYTE), 
	"MODIFIED_DATE" DATE, 
	"READING_MONTH" VARCHAR2(50 BYTE), 
	"READING_YEAR" VARCHAR2(50 BYTE), 
	"IMP_RKVAH_INIT" VARCHAR2(50 BYTE), 
	"IMP_RKVAH_FINAL" VARCHAR2(50 BYTE), 
	"EXP_RKVAH_INIT" VARCHAR2(50 BYTE), 
	"EXP_RKVAH_FINAL" VARCHAR2(50 BYTE), 
	"IMP_KVAH_INIT" VARCHAR2(50 BYTE), 
	"IMP_KVAH_FINAL" VARCHAR2(50 BYTE), 
	"EXP_KVAH_INIT" VARCHAR2(50 BYTE), 
	"EXP_KVAH_FINAL" VARCHAR2(50 BYTE), 
	"REF_NO" VARCHAR2(100 BYTE), 
	"ENABLED" CHAR(1 BYTE) DEFAULT 'Y'
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
--  DDL for Sequence T_METER_READING_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OPENACCESS"."T_METER_READING_SEQ"  MINVALUE 1 MAXVALUE 10000000 INCREMENT BY 1 START WITH 721 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence T_METER_READING_SLOT_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OPENACCESS"."T_METER_READING_SLOT_SEQ"  MINVALUE 0 MAXVALUE 1000000 INCREMENT BY 1 START WITH 3385 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Function IMPORT_MR
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "OPENACCESS"."IMPORT_MR" 
(
  V_BATCH_ID IN VARCHAR2 
) RETURN VARCHAR2 AS 
v_mrh T_METER_READING_HDR%ROWTYPE;
v_mr_c1 T_METER_READING_SLOT%ROWTYPE;
v_mr_c2 T_METER_READING_SLOT%ROWTYPE;
v_mr_c3 T_METER_READING_SLOT%ROWTYPE;
v_mr_c4 T_METER_READING_SLOT%ROWTYPE;
v_mr_c5 T_METER_READING_SLOT%ROWTYPE;
v_meter_id VARCHAR2(100);
v_imported BOOLEAN:=FALSE;
v_created_Date DATE := SYSDATE;
v_created_By  varchar2(50):= 'admin';
v_status varchar2(50);
v_reason varchar2(200);
v_exception_code  NUMBER;
v_exception_msg  VARCHAR2(200);
v_result varchar(300):='SUCCESS';
v_log_result VARCHAR2(50):='';

BEGIN

	BEGIN
    v_log_result := log_activity('PROCEDURE','IMPORT_MR','START','Start - '||V_BATCH_ID,V_RESULT,v_created_By, sysdate,V_BATCH_ID);

	  select status into v_status from imp_mr_header where id =v_batch_id;

        v_log_result := log_activity('PROCEDURE','IMPORT_MR','v_status',v_status,null,v_created_By, sysdate,V_BATCH_ID);

	  -- if the batch is already processing, then stop flow
	  if(v_status = 'PROCESSING') then
	    v_reason := 'Batch is already in processing. please wait';
	    V_RESULT := 'FAILURE';
	    GOTO THE_END;
	  end if;

	  -- set the batch to PROCESSING
	  update imp_mr_header set status='PROCESSING' where id = v_batch_id;

    v_log_result := log_activity('PROCEDURE','IMPORT_MR','PROCESSING',null,null,v_created_By, sysdate,V_BATCH_ID);

	  -- cleanup the imported meter lines, by ignoring dirty data
	  v_result := CLEANSE_MRI(v_batch_id);

    v_log_result := log_activity('PROCEDURE','IMPORT_MR','CLEANSE_MRI',v_result,null,v_created_By, sysdate,V_BATCH_ID);

	 if(substr(v_result,0,7) = 'FAILURE') THEN 
		 --dbms_output.put_line('cleanse result - '||v_result); 
	 	GOTO THE_END; 
	 END IF;

	 --loop through clean imported meter entries
	 FOR mri IN (SELECT mri.* FROM IMP_MR_lines mri WHERE mri.IMP_MR_HEADER_ID = v_batch_id and mri.status_code = 'CLEANSED')
	 LOOP
   begin

	  	-- get meter-id
	  	SELECT DISTINCT meter.id INTO v_meter_id FROM M_COMPANY_METER  meter
	  		JOIN M_COMPANY_SERVICE ser ON ser."number" = mri.SERVICE_NO AND meter.M_COMPANY_SERVICE_ID = ser.id WHERE METER_NUMBER = mri.meter_no;
	 	--setting meter-header record
	 	v_mrh.id := T_METER_READING_SEQ.nextval;
		v_mrh.M_COMPANY_METER_ID := v_meter_id;
		v_mrh.STATUS_CODE := 'CREATED';
		v_mrh.IMP_BATCH_ID :=  mri.IMP_MR_HEADER_ID;
		v_mrh.sys_dt := v_created_Date;
		v_mrh.mf :=  mri.mf;
		v_mrh.READING_MONTH := mri.READING_MONTH;
		v_mrh.READING_YEAR := mri.READING_YEAR;
		v_mrh.INIT_READING_DT := to_date(mri.INIT_READING_DT,'yyyy-mm-dd');
		v_mrh.FINAL_READING_DT := to_date(mri.FINAL_READING_DT,'yyyy-mm-dd');
		v_mrh.EXP_RKVAH_FINAL := mri.EXP_RKVAH_FINAL; v_mrh.EXP_RKVAH_INIT := mri.EXP_RKVAH_INIT;
		v_mrh.IMP_RKVAH_FINAL := mri.IMP_RKVAH_FINAL; v_mrh.IMP_RKVAH_INIT := mri.IMP_RKVAH_INIT;		
		v_mrh.EXP_KVAH_FINAL := mri.EXP_KVAH_FINAL; v_mrh.EXP_KVAH_INIT := mri.EXP_KVAH_INIT;
		v_mrh.IMP_KVAH_FINAL := mri.IMP_KVAH_FINAL; v_mrh.IMP_KVAH_INIT := mri.IMP_KVAH_INIT;
		v_mrh.RKVAH_DIFF := nvl(mri.IMP_RKVAH_FINAL,0) - nvl(mri.IMP_RKVAH_INIT, 0);
		v_mrh.RKVAH_UNITS := v_mrh.RKVAH_DIFF*mri.mf;
		v_mrh.KVAH_DIFF := nvl(mri.EXP_KVAH_FINAL - mri.EXP_KVAH_INIT - (mri.IMP_KVAH_FINAL - mri.IMP_KVAH_INIT), 0);
		v_mrh.KVAH_UNITS := v_mrh.KVAH_DIFF*mri.mf;
		--v_mrh.TOTAL_IMPORT_GEN := ;
		--v_mrh.TOTAL_EXPORT_GEN := ;
		v_mrh.CREATED_BY := v_created_By;
		v_mrh.CREATED_DATE := v_created_Date;
		INSERT INTO T_METER_READING_HDR VALUES v_mrh;

		--setting meter-reading for slot1
		v_mr_c1.ID := T_METER_READING_SLOT_SEQ.nextval;
		v_mr_c1.T_METER_READING_HDR_ID :=  v_mrh.id;
		v_mr_c1.SLOT_CODE := 'C1' ;
		v_mr_c1.IMP_INIT := mri.IMP_INIT_S1;
		v_mr_c1.IMP_FINAL := mri.IMP_FINAL_S1;
		v_mr_c1.EXP_INIT := mri.EXP_INIT_S1;
		v_mr_c1.EXP_FINAL := mri.EXP_FINAL_S1;
		v_mr_c1.IMP_DIFF := mri.IMP_FINAL_S1 - mri.IMP_INIT_S1;
		v_mr_c1.EXP_DIFF := mri.EXP_FINAL_S1 - mri.EXP_INIT_S1;
		v_mr_c1.IMP_UNITS := v_mr_c1.IMP_DIFF * mri.mf;
		v_mr_c1.EXP_UNITS := v_mr_c1.EXP_DIFF * mri.mf;
		v_mr_c1.net_units := v_mr_c1.EXP_UNITS - v_mr_c1.IMP_UNITS ;
		if(v_mr_c1.net_units <0) THEN v_mr_c1.net_units := 0; END IF;
		v_mr_c1.CREATED_BY := v_created_By;
		v_mr_c1.CREATED_DATE := v_created_Date;
		INSERT INTO T_METER_READING_SLOT VALUES v_mr_c1;

		--setting meter-reading for slot2
		v_mr_c2.ID := T_METER_READING_SLOT_SEQ.nextval;
		v_mr_c2.T_METER_READING_HDR_ID :=  v_mrh.id;
		v_mr_c2.SLOT_CODE := 'C2' ;
		v_mr_c2.IMP_INIT := mri.IMP_INIT_S2;
		v_mr_c2.IMP_FINAL := mri.IMP_FINAL_S2;
		v_mr_c2.EXP_INIT := mri.EXP_INIT_S2;
		v_mr_c2.EXP_FINAL := mri.EXP_FINAL_S2;
		v_mr_c2.IMP_DIFF := mri.IMP_FINAL_S2 - mri.IMP_INIT_S2;
		v_mr_c2.EXP_DIFF := mri.EXP_FINAL_S2 - mri.EXP_INIT_S2;
		v_mr_c2.IMP_UNITS := v_mr_c2.IMP_DIFF * mri.mf;
		v_mr_c2.EXP_UNITS := v_mr_c2.EXP_DIFF * mri.mf;
		v_mr_c2.net_units := v_mr_c2.EXP_UNITS - v_mr_c2.IMP_UNITS ;
		if(v_mr_c2.net_units <0) THEN v_mr_c2.net_units := 0; END IF;
		v_mr_c2.CREATED_BY := v_created_By;
		v_mr_c2.CREATED_DATE := v_created_Date;
		INSERT INTO T_METER_READING_SLOT VALUES v_mr_c2;

		--setting meter-reading for slot3
		v_mr_c3.ID := T_METER_READING_SLOT_SEQ.nextval;
		v_mr_c3.T_METER_READING_HDR_ID :=  v_mrh.id;
		v_mr_c3.SLOT_CODE := 'C3' ;
		v_mr_c3.IMP_INIT := mri.IMP_INIT_S3;
		v_mr_c3.IMP_FINAL := mri.IMP_FINAL_S3;
		v_mr_c3.EXP_INIT := mri.EXP_INIT_S3;
		v_mr_c3.EXP_FINAL := mri.EXP_FINAL_S3;
		v_mr_c3.IMP_DIFF := mri.IMP_FINAL_S3 - mri.IMP_INIT_S3;
		v_mr_c3.EXP_DIFF := mri.EXP_FINAL_S3 - mri.EXP_INIT_S3;
		v_mr_c3.IMP_UNITS := v_mr_c3.IMP_DIFF * mri.mf;
		v_mr_c3.EXP_UNITS := v_mr_c3.EXP_DIFF * mri.mf;
		v_mr_c3.net_units := v_mr_c3.EXP_UNITS - v_mr_c3.IMP_UNITS ;
		if(v_mr_c3.net_units <0) THEN v_mr_c3.net_units := 0; END IF;
		v_mr_c3.CREATED_BY := v_created_By;
		v_mr_c3.CREATED_DATE := v_created_Date;
		INSERT INTO T_METER_READING_SLOT VALUES v_mr_c3;

		--setting meter-reading for slot4
		v_mr_c4.ID := T_METER_READING_SLOT_SEQ.nextval;
		v_mr_c4.T_METER_READING_HDR_ID :=  v_mrh.id;
		v_mr_c4.SLOT_CODE := 'C4' ;
		v_mr_c4.IMP_INIT := mri.IMP_INIT_S4;
		v_mr_c4.IMP_FINAL := mri.IMP_FINAL_S4;
		v_mr_c4.EXP_INIT := mri.EXP_INIT_S4;
		v_mr_c4.EXP_FINAL := mri.EXP_FINAL_S4;
		v_mr_c4.IMP_DIFF := mri.IMP_FINAL_S4 - mri.IMP_INIT_S4;
		v_mr_c4.EXP_DIFF := mri.EXP_FINAL_S4 - mri.EXP_INIT_S4;
		v_mr_c4.IMP_UNITS := v_mr_c4.IMP_DIFF * mri.mf;
		v_mr_c4.EXP_UNITS := v_mr_c4.EXP_DIFF * mri.mf;
		v_mr_c4.net_units := v_mr_c4.EXP_UNITS - v_mr_c4.IMP_UNITS ;
		if(v_mr_c4.net_units <0) THEN v_mr_c4.net_units := 0; END IF;
		v_mr_c4.CREATED_BY := v_created_By;
		v_mr_c4.CREATED_DATE := v_created_Date;
		INSERT INTO T_METER_READING_SLOT VALUES v_mr_c4;

		--setting meter-reading for slot5
		v_mr_c5.ID := T_METER_READING_SLOT_SEQ.nextval;
		v_mr_c5.T_METER_READING_HDR_ID :=  v_mrh.id;
		v_mr_c5.SLOT_CODE := 'C5' ;
		v_mr_c5.IMP_INIT := mri.IMP_INIT_S5;
		v_mr_c5.IMP_FINAL := mri.IMP_FINAL_S5;
		v_mr_c5.EXP_INIT := mri.EXP_INIT_S5;
		v_mr_c5.EXP_FINAL := mri.EXP_FINAL_S5;
		v_mr_c5.IMP_DIFF := mri.IMP_FINAL_S5 - mri.IMP_INIT_S5;
		v_mr_c5.EXP_DIFF := mri.EXP_FINAL_S5 - mri.EXP_INIT_S5;
		v_mr_c5.IMP_UNITS := v_mr_c5.IMP_DIFF * mri.mf;
		v_mr_c5.EXP_UNITS := v_mr_c5.EXP_DIFF * mri.mf;
		v_mr_c5.net_units := v_mr_c5.EXP_UNITS - v_mr_c5.IMP_UNITS ;
		if(v_mr_c5.net_units <0) THEN v_mr_c5.net_units := 0; END IF;
		v_mr_c5.CREATED_BY := v_created_By;
		v_mr_c5.CREATED_DATE := v_created_Date;
		INSERT INTO T_METER_READING_SLOT VALUES v_mr_c5;

		UPDATE T_METER_READING_HDR SET 			
				total_import_gen = v_mr_c1.IMP_UNITS + v_mr_c2.IMP_UNITS + v_mr_c3.IMP_UNITS + v_mr_c4.IMP_UNITS + v_mr_c5.IMP_UNITS,				
				total_export_gen = v_mr_c1.EXP_UNITS + v_mr_c2.EXP_UNITS + v_mr_c3.EXP_UNITS + v_mr_c4.EXP_UNITS + v_mr_c5.EXP_UNITS,				
				net_gen_units = v_mr_c1.NET_UNITS + v_mr_c2.NET_UNITS + v_mr_c3.NET_UNITS + v_mr_c4.NET_UNITS + v_mr_c5.NET_UNITS
				WHERE id = v_mrh.id;

		v_imported := TRUE;

    exception
	  when others then
	    v_exception_code := SQLCODE;
	    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
	    v_result := 'FAILURE';
	    v_reason := v_exception_code || ' - ' || v_exception_msg;
	    v_log_result := log_activity('PROCEDURE','IMPORT_MR','CLEANSED-MRI-LOOP','End - '||V_BATCH_ID,v_result || ' - ' || v_reason,v_created_By, sysdate,V_BATCH_ID);
	   -- dbms_output.put_line('IMPORT_MR - '||v_reason);
	END;
	 END LOOP;

	exception
	  when others then
	    v_exception_code := SQLCODE;
	    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
	    v_result := 'FAILURE';
	    v_reason := v_exception_code || ' - ' || v_exception_msg;
     v_log_result := log_activity('PROCEDURE','IMPORT_MR','EXCEPTION',v_reason,V_RESULT,v_created_By, sysdate,V_BATCH_ID);
	   -- dbms_output.put_line('IMPORT_MR - '||v_reason);
	END;
   <<THE_END>>

  IF (NOT v_imported) THEN
  	v_result := 'FAILURE';
  	v_reason := 'No clean meter reading available to process';
  END IF;

  IF V_RESULT = 'SUCCESS' THEN
    update imp_mr_header set status='COMPLETED', REMARKS = '' where id = v_batch_id;
  ELSE
    update imp_mr_header set status='IMPORT-ERROR', REMARKS=v_reason where id = v_batch_id;
    v_result := v_result || ' - ' || v_reason;
  END IF;

  COMMIT;

 v_log_result := log_activity('PROCEDURE','IMPORT_MR','END','End - '||V_BATCH_ID,V_RESULT,v_created_By, sysdate,V_BATCH_ID);

  return v_result; 

END IMPORT_MR;

/
--------------------------------------------------------
--  DDL for Function CLEANSE_MRI
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "OPENACCESS"."CLEANSE_MRI" 
(
  V_BATCH_ID IN VARCHAR2 
) RETURN VARCHAR2 AS 
v_count_mr NUMBER; 
v_count_mri NUMBER;
v_count_mr1 NUMBER; 
v_count_mri1 NUMBER;
v_mr_id varchar2(100);
v_mri_id varchar2(100);
v_mri_rec IMP_MR_LINES%ROWTYPE; 
v_source varchar2(50);
v_is_complete varchar2(50);
v_status varchar2(50);
v_reason varchar2(200);
v_exception_code  NUMBER;
v_exception_msg  VARCHAR2(250);
v_result varchar(300):='SUCCESS';
v_log_result varchar(300);
BEGIN
	BEGIN
	    v_log_result := log_activity('PROCEDURE','CLEANSE_MRI','start',null,null,'', sysdate,V_BATCH_ID);

	SELECT mr_source_code INTO v_source FROM IMP_MR_HEADER WHERE ID = V_BATCH_ID;	

	----dbms_output.put_line('In cleanse function');

	UPDATE IMP_MR_LINES SET STATUS_CODE = 'CREATED' WHERE IMP_MR_HEADER_ID = V_BATCH_ID;

--	--dbms_output.put_line('In cleanse function');

	--if source="01" (mdms), then change the date formats to 'YYYY-MM-DD' and strip time from it
/*
	if(v_source = '01') THEN
		UPDATE IMP_MR_LINES SET sys_dt = TO_CHAR(to_date(sys_dt,'mm/dd/yyyy'),'yyyy-mm-dd'), INIT_READING_DT=TO_CHAR(to_date(INIT_READING_DT,'mm/dd/yyyy'),'yyyy-mm-dd') , FINAL_READING_DT=TO_CHAR(to_date(FINAL_READING_DT,'mm/dd/yyyy'),'yyyy-mm-dd')
		WHERE  IMP_MR_HEADER_ID = V_BATCH_ID;

		UPDATE IMP_MR_LINES SET READING_MONTH = SUBSTR(final_READING_DT,6,2),READING_YEAR = SUBSTR(final_READING_DT,0,4)
		WHERE  IMP_MR_HEADER_ID = V_BATCH_ID;

	ELSE 	*/
		UPDATE IMP_MR_LINES SET sys_dt = TO_CHAR(SYSDATE,'yyyy-mm-dd'),  INIT_READING_DT = (READING_YEAR||'-'||READING_MONTH||'-'||'01' ) ,
		FINAL_READING_DT = to_char(LAST_DAY( to_date((READING_YEAR||'-'||READING_MONTH||'-'||'01' ),'YYYY-MM-DD')),'YYYY-MM-DD')
		WHERE IMP_MR_HEADER_ID = V_BATCH_ID;

	--END IF;

	----dbms_output.put_line('Date format changed');

   -- cleanup the imported meter lines, by ignoring dirty data
	  v_result := MRI_IDENTIFY_ERRORS(v_batch_id);
      v_log_result := log_activity('PROCEDURE','CLEANSE_MRI','identifying errors',V_RESULT,V_RESULT,'', sysdate,V_BATCH_ID);

    if(substr(v_result,0,7) = 'FAILURE') THEN 
		 --dbms_output.put_line('identifying errors - '||v_result); 
	 	GOTO THE_END; 
	 END IF;


	dbms_output.put_line('hi - ');  

	FOR mril IN (SELECT  line.id, line.IMP_MR_HEADER_ID ,line.SERVICE_NO,line.METER_NO, line.READING_MONTH, line.READING_YEAR 
			FROM IMP_MR_lines line 	
			WHERE 1=1 
			and line.IMP_MR_HEADER_ID = v_batch_id
			and (nvl(to_number(IMP_INIT_S1),0) = 0 and nvl(to_number(IMP_INIT_S2),0) = 0 and nvl(to_number(IMP_INIT_S3),0) = 0 and nvl(to_number(IMP_INIT_S4),0) = 0 and nvl(to_number(IMP_INIT_S5),0) = 0 and 
				nvl(to_number(EXP_INIT_S1),0) = 0 and nvl(to_number(EXP_INIT_S2),0) = 0 and nvl(to_number(EXP_INIT_S3),0) = 0 and nvl(to_number(EXP_INIT_S4),0) = 0 and nvl(to_number(EXP_INIT_S5),0) = 0 
				--and nvl(to_number(IMP_RKVAH_INIT ),0) = 0 and nvl(to_number(v_mri_rec.EXP_RKVAH_INIT),0) = 0 and nvl(to_number(v_mri_rec.IMP_KVAH_INIT),0) = 0 and nvl(to_number(v_mri_rec.EXP_KVAH_INIT)=0
				)ORDER BY line.SERVICE_NO,line.METER_NO,line.INIT_READING_DT)
	LOOP
    v_count_mr :=0;
    v_count_mri :=0;
    v_count_mr1 :=0;
    v_count_mri1 :=0;

	dbms_output.put_line('hi - ');  
		SELECT * INTO v_mri_rec FROM IMP_MR_lines WHERE id = mril.id;

	dbms_output.put_line('hi - ');  
		SELECT count( mr.id )INTO v_count_mr FROM T_METER_READING_HDR mr 	
						JOIN M_COMPANY_METER meter ON meter.id = mr.M_COMPANY_METER_ID
						JOIN M_COMPANY_SERVICE ser ON meter.M_COMPANY_SERVICE_ID = ser.id 
						WHERE ser."number" = mril.SERVICE_NO AND meter.METER_NUMBER = mril.meter_NO
						AND mr.READING_MONTH = TO_CHAR(to_date((mril.READING_YEAR||'-'||mril.READING_MONTH||'-'||'01' ),'YYYY-MM-DD')-1,'MM')
						AND mr.READING_YEAR = TO_CHAR(to_date((mril.READING_YEAR||'-'||mril.READING_MONTH||'-'||'01' ),'YYYY-MM-DD')-1,'YYYY');

	dbms_output.put_line('hi - ');  
		SELECT count(*) INTO v_count_mri FROM  IMP_MR_lines where IMP_MR_HEADER_ID = v_batch_id AND SERVICE_NO = mril.SERVICE_NO AND meter_no = mril.meter_no 
				AND READING_MONTH = TO_CHAR(to_date((mril.READING_YEAR||'-'||mril.READING_MONTH||'-'||'01' ),'YYYY-MM-DD')-1,'MM')
				AND READING_YEAR = TO_CHAR(to_date((mril.READING_YEAR||'-'||mril.READING_MONTH||'-'||'01' ),'YYYY-MM-DD')-1,'YYYY') ;

	dbms_output.put_line('hi - ');  
		SELECT count( mr.id )INTO v_count_mr1 FROM T_METER_READING_HDR mr 	
						JOIN M_COMPANY_METER meter ON meter.id = mr.M_COMPANY_METER_ID
						JOIN M_COMPANY_SERVICE ser ON meter.M_COMPANY_SERVICE_ID = ser.id 
						WHERE ser."number" = mril.SERVICE_NO AND meter.METER_NUMBER = mril.meter_NO
						AND mr.FINAL_READING_DT  < to_date((mril.READING_YEAR||'-'||mril.READING_MONTH||'-'||'01' ),'YYYY-MM-DD');

	dbms_output.put_line('hi - ');  
		SELECT count(*) INTO v_count_mri1 FROM  IMP_MR_lines mr where IMP_MR_HEADER_ID = v_batch_id AND SERVICE_NO = mril.SERVICE_NO AND meter_no = mril.meter_no 
				AND to_date(mr.FINAL_READING_DT,'YYYY-MM-DD')	  < to_date((mril.READING_YEAR||'-'||mril.READING_MONTH||'-'||'01' ),'YYYY-MM-DD');


	dbms_output.put_line('v_count_mr - '||v_count_mr); 
	dbms_output.put_line('v_count_mri - '||v_count_mri); 
	dbms_output.put_line('v_count_mr1 - '||v_count_mr1); 
	dbms_output.put_line('v_count_mri1 - '||v_count_mri1); 
	dbms_output.put_line('mril.READING_YEAR - '||mril.READING_YEAR); 
	dbms_output.put_line('mril.READING_MONTH - '||mril.READING_MONTH); 
	dbms_output.put_line('mril.meter_NO - '||mril.meter_NO); 
	dbms_output.put_line('mril.SERVICE_NO - '||mril.SERVICE_NO); 

		if(v_count_mr > 0)THEN
			SELECT  mr.id INTO v_mr_id FROM T_METER_READING_HDR mr 	
			JOIN M_COMPANY_METER meter ON meter.id = mr.M_COMPANY_METER_ID
			JOIN M_COMPANY_SERVICE ser ON meter.M_COMPANY_SERVICE_ID = ser.id 
			WHERE ser."number" = mril.SERVICE_NO AND meter.METER_NUMBER = mril.meter_NO
			AND mr.READING_MONTH = TO_CHAR(to_date((mril.READING_YEAR||'-'||mril.READING_MONTH||'-'||'01' ),'YYYY-MM-DD')-1,'MM')
			AND mr.READING_YEAR = TO_CHAR(to_date((mril.READING_YEAR||'-'||mril.READING_MONTH||'-'||'01' ),'YYYY-MM-DD')-1,'YYYY');

			SELECT nvl(IMP_FINAL,0),nvl(EXP_FINAL,0) INTO v_mri_rec.IMP_INIT_S1, v_mri_rec.EXP_INIT_S1  FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mr_id AND SLOT_CODE='C1';
			SELECT nvl(IMP_FINAL,0),nvl(EXP_FINAL,0) INTO v_mri_rec.IMP_INIT_S2, v_mri_rec.EXP_INIT_S2  FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mr_id AND SLOT_CODE='C2';
			SELECT nvl(IMP_FINAL,0),nvl(EXP_FINAL,0) INTO v_mri_rec.IMP_INIT_S3, v_mri_rec.EXP_INIT_S3  FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mr_id AND SLOT_CODE='C3';
			SELECT nvl(IMP_FINAL,0),nvl(EXP_FINAL,0) INTO v_mri_rec.IMP_INIT_S4, v_mri_rec.EXP_INIT_S4  FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mr_id AND SLOT_CODE='C4';
			SELECT nvl(IMP_FINAL,0),nvl(EXP_FINAL,0) INTO v_mri_rec.IMP_INIT_S5, v_mri_rec.EXP_INIT_S5  FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mr_id AND SLOT_CODE='C5';

      select nvl(IMP_RKVAH_FINAL,0),nvl(EXP_RKVAH_FINAL,0) INTO v_mri_rec.IMP_RKVAH_INIT, v_mri_rec.EXP_RKVAH_INIT  from T_METER_READING_HDR where id = v_mr_id;
		ELSIF (v_count_mri > 0) THEN

			--dbms_output.put_line('in mri ');
			SELECT  id INTO  v_mri_id FROM  IMP_MR_lines where IMP_MR_HEADER_ID = v_batch_id AND SERVICE_NO = mril.SERVICE_NO AND meter_no = mril.meter_no 
			AND READING_MONTH = TO_CHAR(to_date((mril.READING_YEAR||'-'||mril.READING_MONTH||'-'||'01' ),'YYYY-MM-DD')-1,'MM')
			AND READING_YEAR = TO_CHAR(to_date((mril.READING_YEAR||'-'||mril.READING_MONTH||'-'||'01' ),'YYYY-MM-DD')-1,'YYYY');

			--dbms_output.put_line( 'hi'||v_mri_id);
			--dbms_output.put_line('1 ');		
			SELECT nvl(IMP_FINAL_S1,0), nvl(IMP_FINAL_S2,0), nvl(IMP_FINAL_S3,0), nvl(IMP_FINAL_S4,0), nvl(IMP_FINAL_S5,0),
					nvl(EXP_FINAL_S1,0), nvl(EXP_FINAL_S2,0), nvl(EXP_FINAL_S3,0), nvl(EXP_FINAL_S4,0), nvl(EXP_FINAL_S5,0) 
				INTO 
					v_mri_rec.IMP_INIT_S1, v_mri_rec.IMP_INIT_S2, v_mri_rec.IMP_INIT_S3, v_mri_rec.IMP_INIT_S4, v_mri_rec.IMP_INIT_S5,
					v_mri_rec.EXP_INIT_S1, v_mri_rec.EXP_INIT_S2, v_mri_rec.EXP_INIT_S3, v_mri_rec.EXP_INIT_S4, v_mri_rec.EXP_INIT_S5
				FROM  IMP_MR_lines where id = v_mri_id;

			--dbms_output.put_line('2 '||v_mri_id);		
      select nvl(IMP_RKVAH_FINAL,0),nvl(EXP_RKVAH_FINAL,0) INTO v_mri_rec.IMP_RKVAH_INIT, v_mri_rec.EXP_RKVAH_INIT  from IMP_MR_lines where id = v_mri_id;

			--dbms_output.put_line('3 ');		
		elsif(v_count_mr1 > 0)THEN

	dbms_output.put_line('hi - ');  
			SELECT  id INTO v_mr_id from(
			SELECT mr.id FROM T_METER_READING_HDR mr 	
			JOIN M_COMPANY_METER meter ON meter.id = mr.M_COMPANY_METER_ID
			JOIN M_COMPANY_SERVICE ser ON meter.M_COMPANY_SERVICE_ID = ser.id 
			WHERE ser."number" = mril.SERVICE_NO AND meter.METER_NUMBER = mril.meter_NO
			AND mr.FINAL_READING_DT  < to_date((mril.READING_YEAR||'-'||mril.READING_MONTH||'-'||'01' ),'YYYY-MM-DD')
			ORDER BY mr.FINAL_READING_DT desc) WHERE  rownum =1;

			SELECT nvl(IMP_FINAL,0),nvl(EXP_FINAL,0) INTO v_mri_rec.IMP_INIT_S1, v_mri_rec.EXP_INIT_S1  FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mr_id AND SLOT_CODE='C1';
			SELECT nvl(IMP_FINAL,0),nvl(EXP_FINAL,0) INTO v_mri_rec.IMP_INIT_S2, v_mri_rec.EXP_INIT_S2  FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mr_id AND SLOT_CODE='C2';
			SELECT nvl(IMP_FINAL,0),nvl(EXP_FINAL,0) INTO v_mri_rec.IMP_INIT_S3, v_mri_rec.EXP_INIT_S3  FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mr_id AND SLOT_CODE='C3';
			SELECT nvl(IMP_FINAL,0),nvl(EXP_FINAL,0) INTO v_mri_rec.IMP_INIT_S4, v_mri_rec.EXP_INIT_S4  FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mr_id AND SLOT_CODE='C4';
			SELECT nvl(IMP_FINAL,0),nvl(EXP_FINAL,0) INTO v_mri_rec.IMP_INIT_S5, v_mri_rec.EXP_INIT_S5  FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mr_id AND SLOT_CODE='C5';

      select nvl(IMP_RKVAH_FINAL,0),nvl(EXP_RKVAH_FINAL,0) INTO v_mri_rec.IMP_RKVAH_INIT, v_mri_rec.EXP_RKVAH_INIT  from T_METER_READING_HDR where id = v_mr_id;
		ELSIF (v_count_mri1 > 0) THEN

			--dbms_output.put_line('in mri ');
			SELECT  id INTO  v_mri_id from(
			SELECT mr.id FROM  IMP_MR_lines mr where IMP_MR_HEADER_ID = v_batch_id AND SERVICE_NO = mril.SERVICE_NO AND meter_no = mril.meter_no 
			AND to_date(mr.FINAL_READING_DT,'YYYY-MM-DD')  < to_date((mril.READING_YEAR||'-'||mril.READING_MONTH||'-'||'01' ),'YYYY-MM-DD')
			ORDER BY mr.FINAL_READING_DT DESC) WHERE  rownum = 1;



			SELECT nvl(IMP_FINAL_S1,0), nvl(IMP_FINAL_S2,0), nvl(IMP_FINAL_S3,0), nvl(IMP_FINAL_S4,0), nvl(IMP_FINAL_S5,0),
					nvl(EXP_FINAL_S1,0), nvl(EXP_FINAL_S2,0), nvl(EXP_FINAL_S3,0), nvl(EXP_FINAL_S4,0), nvl(EXP_FINAL_S5,0),
          nvl(IMP_RKVAH_FINAL,0),nvl(EXP_RKVAH_FINAL,0)
				INTO 
					v_mri_rec.IMP_INIT_S1, v_mri_rec.IMP_INIT_S2, v_mri_rec.IMP_INIT_S3, v_mri_rec.IMP_INIT_S4, v_mri_rec.IMP_INIT_S5,
					v_mri_rec.EXP_INIT_S1, v_mri_rec.EXP_INIT_S2, v_mri_rec.EXP_INIT_S3, v_mri_rec.EXP_INIT_S4, v_mri_rec.EXP_INIT_S5,
          v_mri_rec.IMP_RKVAH_INIT, v_mri_rec.EXP_RKVAH_INIT 
				FROM  IMP_MR_lines where id = v_mri_id;

      select nvl(IMP_RKVAH_FINAL,0),nvl(EXP_RKVAH_FINAL,0) INTO v_mri_rec.IMP_RKVAH_INIT, v_mri_rec.EXP_RKVAH_INIT  from T_METER_READING_HDR where id = v_mri_id;
		ELSE
			SELECT 0,0,0,0,0,0,0,0,0,0,0,0 INTO v_mri_rec.IMP_INIT_S1, v_mri_rec.IMP_INIT_S2, v_mri_rec.IMP_INIT_S3, v_mri_rec.IMP_INIT_S4, v_mri_rec.IMP_INIT_S5,
					v_mri_rec.EXP_INIT_S1, v_mri_rec.EXP_INIT_S2, v_mri_rec.EXP_INIT_S3, v_mri_rec.EXP_INIT_S4, v_mri_rec.EXP_INIT_S5,v_mri_rec.IMP_RKVAH_INIT, v_mri_rec.EXP_RKVAH_INIT   FROM dual;
		END IF;

		UPDATE IMP_MR_lines SET ROW = v_mri_rec WHERE id = v_mri_rec.id;
		--TO_CHAR(to_date((mri.READING_YEAR||'-'||mri.READING_MONTH||'-'||'01' ),'YYYY-MM-DD')-1,'MM') 
	END LOOP;

	--Identifying the previous meter-reading to populate the initial-values

	UPDATE IMP_MR_LINES SET STATUS_CODE = 'CLEANSED', remarks = ''
	WHERE  IMP_MR_HEADER_ID = V_BATCH_ID AND STATUS_CODE != 'ERROR';

	exception
	  when others then
	    v_exception_code := SQLCODE;
	    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
	    v_result := 'FAILURE';
	    v_reason := v_exception_code || ' - ' || v_exception_msg;
      v_log_result := log_activity('PROCEDURE','CLEANSE_MRI','EXCEPTION',v_reason,v_result,'', sysdate,V_BATCH_ID);

	    --dbms_output.put_line('CLEANSE_MRI - '||v_reason);
	END;
   <<THE_END>>

  IF V_RESULT = 'SUCCESS' THEN
    COMMIT;
  else
    v_result := v_result || ' - ' || v_reason;
  END IF;

	    v_log_result := log_activity('PROCEDURE','CLEANSE_MRI','End',v_result,v_result,'', sysdate,V_BATCH_ID);
  return v_result; 
END CLEANSE_MRI;

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
