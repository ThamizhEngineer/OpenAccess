--------------------------------------------------------
--  File created - Monday-April-09-2018   
--------------------------------------------------------
DROP FUNCTION "OPENACCESS"."CLEANSE_MRI";
DROP FUNCTION "OPENACCESS"."LOG_ACTIVITY";
DROP FUNCTION "OPENACCESS"."MRI_IDENTIFY_ERRORS";
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
--------------------------------------------------------
--  DDL for Function MRI_IDENTIFY_ERRORS
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "OPENACCESS"."MRI_IDENTIFY_ERRORS" 
(
  V_BATCH_ID IN VARCHAR2 
) RETURN VARCHAR2 AS 

v_mri_rec IMP_MR_LINES%ROWTYPE;
v_status varchar2(50);
v_reason varchar2(200);
v_exception_code  NUMBER;
v_exception_msg  VARCHAR2(250);
v_result varchar(300):='SUCCESS';
v_LOG_result varchar(300);
BEGIN

	--if final ReadingDate <> InitialReadingDate and if Day of FinalReadingDate is 01, then update reduce the FinalReadingDate by one 
	UPDATE IMP_MR_LINES SET final_READING_DT= to_char(to_date(final_READING_DT,'yyyy-mm-dd') -1,'yyyy-mm-dd')
	WHERE IMP_MR_HEADER_ID =  V_BATCH_ID
	AND to_date(INIT_READING_DT,'yyyy-mm-dd') != to_date(final_READING_DT,'yyyy-mm-dd')
	AND SUBSTR(final_READING_DT,9,2) = '01';


	--dbms_output.put_line('Date stripped');

	/*
	--Ignore any record older than May-2017 - Mark as error with remarks
	UPDATE IMP_MR_LINES SET STATUS_CODE= 'ERROR', REMARKS = 'Too old records'
	WHERE IMP_MR_HEADER_ID =  V_BATCH_ID
	AND (final_READING_DT IS NOT NULL AND TO_NUMBER(SUBSTR(final_READING_DT,6,2)) < 5) ;

	dbms_output.put_line('Err1');
	*/

	--Mark all records as error with remarks if Final-ReadingDate < InitialReadingDate and not already marked as error with remarks
	UPDATE IMP_MR_LINES SET STATUS_CODE= 'ERROR', REMARKS = 'FinalReadingDate < InitialReadingDate '
	WHERE IMP_MR_HEADER_ID =  V_BATCH_ID
	AND STATUS_CODE != 'ERROR'
	AND (final_READING_DT IS NOT NULL AND  init_READING_DT IS NOT NULL AND to_date(final_READING_DT,'yyyy-mm-dd') < to_date(init_READING_DT,'yyyy-mm-dd'));


	--dbms_output.put_line('Err2');

	--If the Service-No doesnt exists in m_company_service, mark as error with remarks
	UPDATE IMP_MR_LINES SET STATUS_CODE= 'ERROR', REMARKS = 'Unknown Service-no'
	WHERE IMP_MR_HEADER_ID =  V_BATCH_ID
	AND STATUS_CODE != 'ERROR'
	AND SERVICE_NO NOT IN (SELECT "number" FROM M_COMPANY_SERVICE);

	--dbms_output.put_line('Err3');

	--If the Meter_no doesnt exists in m_company_meter, mark as error with remarks
	UPDATE IMP_MR_LINES SET STATUS_CODE= 'ERROR', REMARKS = 'Unknown Meter-no'
	WHERE IMP_MR_HEADER_ID =  V_BATCH_ID
	AND STATUS_CODE != 'ERROR'
	AND meter_no NOT IN (SELECT meter_number FROM M_COMPANY_meter);

	--dbms_output.put_line('Err4');

	--If the initial or final reading date is null, mark as error with remarks
	UPDATE IMP_MR_LINES SET STATUS_CODE= 'ERROR', REMARKS = 'Initial or Final Reading date is null'
	WHERE IMP_MR_HEADER_ID =  V_BATCH_ID
	AND STATUS_CODE != 'ERROR'
	AND (init_READING_DT IS NULL OR final_READING_DT IS NULL );
	-- make sure the readings from mri, doesnt exist in MeterReading table
	-- TODO 

	--dbms_output.put_line('Err5');

	 UPDATE IMP_MR_LINES SET STATUS_CODE= 'ERROR', REMARKS = 'Meter Reading Already Exists'
	 WHERE id IN (SELECT mri.id 
		FROM IMP_MR_lines mri 	
		 JOIN M_COMPANY_METER meter ON meter.METER_NUMBER = mri.METER_NO
		 JOIN M_COMPANY_SERVICE ser ON ser."number" = mri.SERVICE_NO AND meter.M_COMPANY_SERVICE_ID = ser.id 
		 JOIN T_METER_READING_HDR mrh ON meter.id = mrh.M_COMPANY_METER_ID AND mri.READING_MONTH = mrh.READING_MONTH AND mri.READING_YEAR = mrh.READING_YEAR
		WHERE mri.IMP_MR_HEADER_ID = v_batch_id
		and mri.status_code = 'CREATED');


	--dbms_output.put_line('Errors marked');

-- if dual entries entry exists for a meter in same month
-- Find MRI with multiple entries for same month if dual entries entry exists for a meter in same reading month-year in same batch
	FOR mul_entry IN (SELECT  line.IMP_MR_HEADER_ID ,line.SERVICE_NO,line.METER_NO, line.READING_MONTH, line.READING_YEAR,  line.mf,count(line.METER_NO) readings_per_meter 
			FROM IMP_MR_lines line 	
			LEFT JOIN IMP_MR_header head ON line.IMP_MR_HEADER_ID = head.id 
			WHERE line.IMP_MR_HEADER_ID = v_batch_id
			and line.status_code != 'ERROR'
			GROUP BY line.IMP_MR_HEADER_ID, line.SERVICE_NO,line.METER_NO, line.READING_MONTH, line.READING_YEAR, line.mf
			HAVING count(line.METER_NO) >1)
	LOOP
		v_mri_rec.id := IMP_MR_lines_SEQ.nextval;
		v_mri_rec.IMP_MR_HEADER_ID := mul_entry.IMP_MR_HEADER_ID;
		v_mri_rec.STATUS_CODE := 'CREATED';
    v_mri_rec.SERVICE_NO := mul_entry.SERVICE_NO;
		v_mri_rec.METER_NO := mul_entry.METER_NO;
		v_mri_rec.READING_MONTH := mul_entry.READING_MONTH;
		v_mri_rec.READING_YEAR := mul_entry.READING_YEAR;
		v_mri_rec.mf := mul_entry.mf;
		v_mri_rec.sys_dt := TO_CHAR(SYSDATE,'yyyy-mm-dd');
		v_mri_rec.INIT_READING_DT := (mul_entry.READING_YEAR||'-'||mul_entry.READING_MONTH||'-'||'01' );
		v_mri_rec.FINAL_READING_DT := to_char(LAST_DAY( to_date((mul_entry.READING_YEAR||'-'||mul_entry.READING_MONTH||'-'||'01' ),'YYYY-MM-DD')),'YYYY-MM-DD');

	 		--find the min values for all INIT values for the given batchNo, serviceNo, meterNo, month, year
		SELECT  min(to_number(nvl(IMP_INIT_S1,0))) IMP_INIT_S1,min(to_number(nvl(IMP_INIT_S2,0))) IMP_INIT_S2,min(to_number(nvl(IMP_INIT_S3,0))) IMP_INIT_S3,min(to_number(nvl(IMP_INIT_S4,0))) IMP_INIT_S4,min(to_number(nvl(IMP_INIT_S5,0))) IMP_INIT_S5,
				min(to_number(nvl(EXP_INIT_S1,0))) EXP_INIT_S1,min(to_number(nvl(EXP_INIT_S2,0))) EXP_INIT_S2,min(to_number(nvl(EXP_INIT_S3,0))) EXP_INIT_S3,min(to_number(nvl(EXP_INIT_S4,0))) EXP_INIT_S4,min(to_number(nvl(EXP_INIT_S5,0))) EXP_INIT_S5,
				 min(to_number(nvl(IMP_RKVAH_INIT,0))) IMP_RKVAH_INIT,min(to_number(nvl(EXP_RKVAH_INIT,0))) EXP_RKVAH_INIT,min(to_number(nvl(IMP_KVAH_INIT,0))) IMP_KVAH_INIT,min(to_number(nvl(EXP_KVAH_INIT,0))) EXP_KVAH_INIT
			 INTO v_mri_rec.IMP_INIT_S1, v_mri_rec.IMP_INIT_S2, v_mri_rec.IMP_INIT_S3, v_mri_rec.IMP_INIT_S4, v_mri_rec.IMP_INIT_S5,
					v_mri_rec.EXP_INIT_S1, v_mri_rec.EXP_INIT_S2, v_mri_rec.EXP_INIT_S3, v_mri_rec.EXP_INIT_S4, v_mri_rec.EXP_INIT_S5,
					v_mri_rec.IMP_RKVAH_INIT ,v_mri_rec.EXP_RKVAH_INIT,v_mri_rec.IMP_KVAH_INIT,v_mri_rec.EXP_KVAH_INIT
			FROM  IMP_MR_lines lines 
			WHERE lines.IMP_MR_HEADER_ID = v_batch_id AND lines.SERVICE_NO = mul_entry.service_no AND lines.METER_NO = mul_entry.meter_no 
			AND lines.READING_MONTH = mul_entry.READING_MONTH AND lines.READING_YEAR = mul_entry.READING_YEAR
      group by lines.IMP_MR_HEADER_ID,lines.SERVICE_NO ,lines.METER_NO,lines.READING_MONTH ,lines.READING_YEAR ;


		--find the max values for all final values for the given batchNo, serviceNo, meterNo, month, year
		SELECT  max(to_number(nvl(IMP_FINAL_S1,0))) IMP_FINAL_S1,max(to_number(nvl(IMP_FINAL_S2,0))) IMP_FINAL_S2,max(to_number(nvl(IMP_FINAL_S3,0))) IMP_FINAL_S3,max(to_number(nvl(IMP_FINAL_S4,0))) IMP_FINAL_S4,max(to_number(nvl(IMP_FINAL_S5,0))) IMP_FINAL_S5,
				max(to_number(nvl(EXP_FINAL_S1,0))) EXP_FINAL_S1,max(to_number(nvl(EXP_FINAL_S2,0))) EXP_FINAL_S2,max(to_number(nvl(EXP_FINAL_S3,0))) EXP_FINAL_S3,max(to_number(nvl(EXP_FINAL_S4,0))) EXP_FINAL_S4,max(to_number(nvl(EXP_FINAL_S5,0))) EXP_FINAL_S5,
				 max(to_number(nvl(IMP_RKVAH_FINAL,0))) IMP_RKVAH_FINAL,max(to_number(nvl(EXP_RKVAH_FINAL,0))) EXP_RKVAH_FINAL,max(to_number(nvl(IMP_KVAH_FINAL,0))) IMP_KVAH_FINAL,max(to_number(nvl(EXP_KVAH_FINAL,0))) EXP_KVAH_FINAL
			 INTO v_mri_rec.IMP_FINAL_S1, v_mri_rec.IMP_FINAL_S2, v_mri_rec.IMP_FINAL_S3, v_mri_rec.IMP_FINAL_S4, v_mri_rec.IMP_FINAL_S5,
					v_mri_rec.EXP_FINAL_S1, v_mri_rec.EXP_FINAL_S2, v_mri_rec.EXP_FINAL_S3, v_mri_rec.EXP_FINAL_S4, v_mri_rec.EXP_FINAL_S5,
					v_mri_rec.IMP_RKVAH_FINAL ,v_mri_rec.EXP_RKVAH_FINAL,v_mri_rec.IMP_KVAH_FINAL,v_mri_rec.EXP_KVAH_FINAL
			FROM  IMP_MR_lines lines 
			WHERE lines.IMP_MR_HEADER_ID = v_batch_id AND lines.SERVICE_NO = mul_entry.service_no AND lines.METER_NO = mul_entry.meter_no 
			AND lines.READING_MONTH = mul_entry.READING_MONTH AND lines.READING_YEAR = mul_entry.READING_YEAR
      group by lines.IMP_MR_HEADER_ID,lines.SERVICE_NO ,lines.METER_NO,lines.READING_MONTH ,lines.READING_YEAR ;

		INSERT INTO IMP_MR_LINES VALUES v_mri_rec; 

		UPDATE IMP_MR_LINES lines SET STATUS_CODE= 'ERROR', REMARKS = 'Multiple entries in same month', REF_NO = v_mri_rec.id
			WHERE IMP_MR_HEADER_ID =  V_BATCH_ID AND lines.SERVICE_NO = mul_entry.service_no AND lines.METER_NO = mul_entry.meter_no 
			AND lines.READING_MONTH = mul_entry.READING_MONTH AND lines.READING_YEAR = mul_entry.READING_YEAR
			AND id != v_mri_rec.id;

	END LOOP;


  RETURN 'SUCCESS';
	exception
	  when others then
	    v_exception_code := SQLCODE;
	    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
	   -- v_result := 'FAILURE';
	    v_reason := v_exception_code || ' - ' || v_exception_msg;
	    dbms_output.put_line('CLEANSE_MRI - '||v_reason);
      v_log_result := log_activity('PROCEDURE','MRI_IDENTIFY_ERRORS','EXCEPTION',v_reason,NULL,'', sysdate,V_BATCH_ID);

      return 'FAILURE';
END MRI_IDENTIFY_ERRORS;


/
-- Unable to render SYNONYM DDL for object PUBLIC.DBMS_OUTPUT with DBMS_METADATA attempting internal generator.
CREATE PUBLIC SYNONYM DBMS_OUTPUT FOR SYS.DBMS_OUTPUT
-- Unable to render SYNONYM DDL for object PUBLIC.DUAL with DBMS_METADATA attempting internal generator.
CREATE PUBLIC SYNONYM DUAL FOR SYS."DUAL"
