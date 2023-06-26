--------------------------------------------------------
--  File created - Monday-April-09-2018   
--------------------------------------------------------
DROP FUNCTION "OPENACCESS"."LOG_ACTIVITY";
--------------------------------------------------------
--  DDL for Table T_ACTIVITY_LOG
--------------------------------------------------------

  CREATE TABLE "OPENACCESS"."T_ACTIVITY_LOG" 
   (	"ID" VARCHAR2(100 BYTE), 
	"PROCESS_TYPE" VARCHAR2(100 BYTE), 
	"PROCESS_NAME" VARCHAR2(100 BYTE), 
	"ACTIVITY_NAME" VARCHAR2(100 BYTE), 
	"MESSAGE" VARCHAR2(500 BYTE), 
	"result" VARCHAR2(100 BYTE), 
	"CREATED_BY" VARCHAR2(100 BYTE), 
	"CREATED_DT" DATE, 
	"ATT1" VARCHAR2(100 BYTE), 
	"ATT2" VARCHAR2(100 BYTE), 
	"ATT3" VARCHAR2(100 BYTE), 
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
--  DDL for Sequence T_ACTIVITY_LOG_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OPENACCESS"."T_ACTIVITY_LOG_SEQ"  MINVALUE 0 MAXVALUE 100000000 INCREMENT BY 1 START WITH 41864 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
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
-- Unable to render SYNONYM DDL for object PUBLIC.DBMS_OUTPUT with DBMS_METADATA attempting internal generator.
CREATE PUBLIC SYNONYM DBMS_OUTPUT FOR SYS.DBMS_OUTPUT
