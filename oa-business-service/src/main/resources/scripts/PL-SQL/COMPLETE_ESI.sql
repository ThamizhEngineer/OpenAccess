--------------------------------------------------------
--  File created - Monday-April-09-2018   
--------------------------------------------------------
DROP FUNCTION "OPENACCESS"."COMPLETE_ESI";
DROP FUNCTION "OPENACCESS"."LOG_ACTIVITY";
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
--  DDL for Table M_TRADE_RELATIONSHIP
--------------------------------------------------------

  CREATE TABLE "OPENACCESS"."M_TRADE_RELATIONSHIP" 
   (	"ID" VARCHAR2(50 BYTE), 
	"QUANTUM" VARCHAR2(50 BYTE), 
	"FROM_DATE" DATE, 
	"TO_DATE" DATE, 
	"STATUS_CODE" VARCHAR2(50 BYTE), 
	"M_SELLER_COMPANY_ID" VARCHAR2(50 BYTE), 
	"M_SELLER_COMP_SERVICE_ID" VARCHAR2(50 BYTE), 
	"M_BUYER_COMPANY_ID" VARCHAR2(50 BYTE), 
	"M_BUYER_COMP_SERVICE_ID" VARCHAR2(50 BYTE), 
	"REFERENCENUMBER" VARCHAR2(200 BYTE), 
	"REMARKS" VARCHAR2(300 BYTE), 
	"CREATED_BY" VARCHAR2(50 BYTE), 
	"CREATED_DATE" DATE DEFAULT sysdate, 
	"MODIFIED_BY" VARCHAR2(50 BYTE), 
	"MODIFIED_DATE" DATE, 
	"C1" VARCHAR2(50 BYTE), 
	"C2" VARCHAR2(50 BYTE), 
	"C3" VARCHAR2(50 BYTE), 
	"C4" VARCHAR2(50 BYTE), 
	"C5" VARCHAR2(50 BYTE), 
	"IS_CAPTIVE" CHAR(1 BYTE), 
	"ENABLED" CHAR(1 BYTE) DEFAULT 'Y', 
	"PEAK_UNITS" VARCHAR2(100 BYTE), 
	"OFF_PEAK_UNITS" VARCHAR2(100 BYTE), 
	"INTERVAL_TYPE_CODE" VARCHAR2(50 BYTE), 
	"SHARE_PERCENT" VARCHAR2(100 BYTE), 
	"TRADE_RELATIONSHIP_SOURCE_CODE" VARCHAR2(50 BYTE) DEFAULT 'OAA'
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table T_ES_INTENT
--------------------------------------------------------

  CREATE TABLE "OPENACCESS"."T_ES_INTENT" 
   (	"ID" VARCHAR2(50 BYTE), 
	"SELLER_COMP_SERV_ID" VARCHAR2(50 BYTE), 
	"AGMT_PERIOD_CODE" VARCHAR2(50 BYTE), 
	"FROM_DT" DATE, 
	"TO_DT" DATE, 
	"IS_CAPTIVE" CHAR(1 BYTE), 
	"STATUS_CODE" VARCHAR2(50 BYTE), 
	"FROM_MONTH" VARCHAR2(100 BYTE), 
	"FROM_YEAR" VARCHAR2(100 BYTE), 
	"TO_MONTH" VARCHAR2(100 BYTE), 
	"TO_YEAR" VARCHAR2(100 BYTE), 
	"CREATED_DATE" DATE DEFAULT sysdate, 
	"ENABLED" CHAR(1 BYTE) DEFAULT 'Y', 
	"T_EWA_ID" VARCHAR2(50 BYTE), 
	"CODE" VARCHAR2(50 BYTE), 
	"FLOW_TYPE_CODE" VARCHAR2(50 BYTE), 
	"T_INPRINCIPLE_APPLN_ID" VARCHAR2(50 BYTE), 
	"T_NOC_GENERATOR_ID" VARCHAR2(50 BYTE), 
	"PROPOSED_CAPACITY" VARCHAR2(50 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table T_ES_INTENT_LINE
--------------------------------------------------------

  CREATE TABLE "OPENACCESS"."T_ES_INTENT_LINE" 
   (	"ID" VARCHAR2(50 BYTE), 
	"T_EST_INTENT_ID" VARCHAR2(50 BYTE), 
	"BUYER_COMP_SERV_ID" VARCHAR2(50 BYTE), 
	"STATUS_CODE" VARCHAR2(50 BYTE), 
	"T_NOC_ID" VARCHAR2(50 BYTE), 
	"T_CONSENT_ID" VARCHAR2(50 BYTE), 
	"T_OA_AGMT_ID" VARCHAR2(50 BYTE), 
	"PROPOSED_QUANTUM" VARCHAR2(100 BYTE), 
	"CREATED_DATE" DATE DEFAULT sysdate, 
	"ENABLED" CHAR(1 BYTE) DEFAULT 'Y', 
	"T_EPA_ID" VARCHAR2(50 BYTE), 
	"M_TRADE_RELATIONSHIP_ID" VARCHAR2(50 BYTE), 
	"IS_CAPTIVE" CHAR(1 BYTE), 
	"T_STANDING_CLEARENCE_ID" VARCHAR2(50 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table T_EWA
--------------------------------------------------------

  CREATE TABLE "OPENACCESS"."T_EWA" 
   (	"ID" VARCHAR2(50 BYTE), 
	"VERSION" VARCHAR2(50 BYTE), 
	"SELLER_COMP_SERV_ID" VARCHAR2(50 BYTE), 
	"SELLER_END_ORG_ID" VARCHAR2(100 BYTE), 
	"INJECTION_VOLTAGE_CODE" VARCHAR2(100 BYTE), 
	"TOTAL_PROPOSED_UNITS" VARCHAR2(50 BYTE), 
	"TOAL_APPROVED_UNITS" VARCHAR2(50 BYTE), 
	"AGMT_PERIOD_CODE" VARCHAR2(50 BYTE), 
	"FROM_DT" DATE, 
	"TO_DT" DATE, 
	"TOTAL_INJECTION_PEAK_UNITS" VARCHAR2(50 BYTE), 
	"TOTAL_INJECTION_OFF_PEAK_UNITS" VARCHAR2(50 BYTE), 
	"APPLIED_DT" DATE, 
	"APPROVED_DT" DATE, 
	"STATUS_CODE" VARCHAR2(50 BYTE), 
	"EWA_APPROVAL_NUMBER" VARCHAR2(50 BYTE), 
	"REMARKS" VARCHAR2(100 BYTE), 
	"CREATED_BY" VARCHAR2(50 BYTE), 
	"CREATED_DT" DATE, 
	"MODIFIED_BY" VARCHAR2(50 BYTE), 
	"MODIFIED_DT" DATE, 
	"T_ES_INTENT_ID" VARCHAR2(50 BYTE), 
	"TOTAL_DRAWAL_PEAK_UNITS" VARCHAR2(100 BYTE), 
	"TOTAL_DRAWAL_OFF_PEAK_UNITS" VARCHAR2(100 BYTE), 
	"SELLER_IS_CAPTIVE" CHAR(1 BYTE), 
	"AGREEMENT_DT" DATE, 
	"CREATED_DATE" DATE DEFAULT sysdate, 
	"ENABLED" CHAR(1 BYTE) DEFAULT 'Y', 
	"CODE" VARCHAR2(50 BYTE), 
	"FLOW_TYPE_CODE" VARCHAR2(50 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table T_EWA_LINE
--------------------------------------------------------

  CREATE TABLE "OPENACCESS"."T_EWA_LINE" 
   (	"ID" VARCHAR2(50 BYTE), 
	"T_EWA_ID" VARCHAR2(50 BYTE), 
	"APPLIED_DT" DATE, 
	"APPROVED_DT" DATE, 
	"BUYER_COMP_SERV_ID" VARCHAR2(50 BYTE), 
	"BUYER_END_ORG_ID" VARCHAR2(100 BYTE), 
	"DRAWAL_VOLTAGE_CODE" VARCHAR2(50 BYTE), 
	"INJECTION_PEAK_UNITS" VARCHAR2(50 BYTE), 
	"INJECTION_OFF_PEAK_UNITS" VARCHAR2(50 BYTE), 
	"STATUS_CODE" VARCHAR2(50 BYTE), 
	"REMARKS" VARCHAR2(100 BYTE), 
	"CREATED_BY" VARCHAR2(50 BYTE), 
	"CREATED_DT" DATE, 
	"MODIFIED_BY" VARCHAR2(50 BYTE), 
	"MODIFIED_DT" DATE, 
	"DRAWAL_PEAK_UNITS" VARCHAR2(100 BYTE), 
	"DRAWAL_OFF_PEAK_UNITS" VARCHAR2(100 BYTE), 
	"EWA_LINE_CHANGE_CODE" VARCHAR2(100 BYTE), 
	"CREATED_DATE" DATE DEFAULT sysdate, 
	"ENABLED" CHAR(1 BYTE) DEFAULT 'Y', 
	"PROPOSED_UNITS" VARCHAR2(50 BYTE), 
	"APPROVED_UNITS" VARCHAR2(50 BYTE), 
	"IS_CAPTIVE" CHAR(1 BYTE), 
	"SHARE_PERCENTAGE" FLOAT(126), 
	"INTERVAL_TYPE_CODE" VARCHAR2(50 BYTE) DEFAULT '01'
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Sequence M_TRADE_RELATIONSHIP_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OPENACCESS"."M_TRADE_RELATIONSHIP_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10401 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Function COMPLETE_ESI
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "OPENACCESS"."COMPLETE_ESI" 
(
  v_es_intent_id in varchar2
) RETURN VARCHAR2 AS 
/**
For not-completed ESI flow of type-WEG,
      will create trade-relationship for each ewa-line
      update the esi-line with the tr-id
      update the ESI status to COMPLETED

*/


v_tr M_TRADE_RELATIONSHIP%ROWTYPE;
v_flow_type_code varchar2(50);
v_esi_status_code varchar2(50);
v_no_records BOOLEAN:=TRUE;
--v_created_Date DATE := SYSDATE;
v_created_By  varchar2(50):= 'admin';
v_status varchar2(50);
v_reason varchar2(200):='';
v_exception_code  NUMBER;
v_exception_msg  VARCHAR2(200);
v_result varchar(300):='SUCCESS';
v_log_result varchar(300):='SUCCESS';
v_imported BOOLEAN;

BEGIN

	BEGIN		

    v_log_result := log_activity('PROCEDURE','complete_esi','START',v_reason,v_result,v_created_By, sysdate,v_es_intent_id);

    select flow_type_code, STATUS_CODE into v_flow_type_code, v_esi_status_code from t_es_intent WHERE ID = v_es_intent_id;

    if(v_flow_type_code = 'WEG' and v_esi_status_code != 'COMPLETED') then
        FOR ewal IN (select sell.M_COMPANY_ID M_SELLER_COMPANY_ID, ewa.SELLER_COMP_SERV_ID M_SELLER_COMP_SERVICE_ID,buy.M_COMPANY_ID M_BUYER_COMPANY_ID,
                          ewal.buyer_comp_serv_id M_BUYER_COMP_SERVICE_ID, ewa.from_dt from_date, ewa.to_dt to_date, ewal.ID REFERENCENUMBER ,ewal.DRAWAL_PEAK_UNITS PEAK_UNITS,
                          ewal.DRAWAL_OFF_PEAK_UNITS OFF_PEAK_UNITS, ewal.approved_units quantum, ewal.interval_type_code, ewal.share_percentage SHARE_PERCENT,ewal.IS_CAPTIVE
                           from t_ewa_line ewal 
                           left  join t_ewa ewa on ewal.t_ewa_id = ewa.id
                           left join M_COMPANY_SERVICE sell on ewa.SELLER_COMP_SERV_ID = sell.id
                           left join M_COMPANY_SERVICE buy on ewal.buyer_comp_serv_id = buy.id
                           where ewa.T_ES_INTENT_ID =v_es_intent_id
                         )
      LOOP
        begin
        v_tr.id := M_TRADE_RELATIONSHIP_SEQ.nextval;
        v_tr.M_SELLER_COMPANY_ID := ewal.M_SELLER_COMPANY_ID;
        v_tr.M_SELLER_COMP_SERVICE_ID := ewal.M_SELLER_COMP_SERVICE_ID;
        v_tr.M_BUYER_COMPANY_ID := ewal.M_BUYER_COMPANY_ID;
        v_tr.M_BUYER_COMP_SERVICE_ID := ewal.M_BUYER_COMP_SERVICE_ID;
        v_tr.FROM_DATE := ewal.FROM_DATE;
        v_tr.TO_DATE := ewal.TO_DATE;
        v_tr.REFERENCENUMBER := ewal.REFERENCENUMBER;
        v_tr.PEAK_UNITS := ewal.PEAK_UNITS;
        v_tr.OFF_PEAK_UNITS := ewal.OFF_PEAK_UNITS;
        v_tr.QUANTUM := ewal.QUANTUM;
        v_tr.INTERVAL_TYPE_CODE := ewal.INTERVAL_TYPE_CODE;
        v_tr.SHARE_PERCENT := ewal.SHARE_PERCENT;
        v_tr.TRADE_RELATIONSHIP_SOURCE_CODE := 'EWA';
        v_tr.IS_CAPTIVE := ewal.IS_CAPTIVE;
        v_tr.ENABLED := 'Y';
        v_tr.STATUS_CODE := 'ACTIVATED';
        v_tr.CREATED_BY := v_created_by;
        v_tr.CREATED_DATE := sysdate;

        insert into M_TRADE_RELATIONSHIP values v_tr;
        update t_es_intent_line set m_trade_relationship_id= v_tr.id , status_code ='COMPLETED' where T_EST_INTENT_ID = v_es_intent_id and  BUYER_COMP_SERV_ID = v_tr.M_BUYER_COMP_SERVICE_ID;

        if(v_no_records) THEN
            v_no_records := FALSE;
        END IF;
      exception
      when others then
        v_exception_code := SQLCODE;
        v_exception_msg := SUBSTR(SQLERRM, 1, 200);
        v_result := 'FAILURE';
        v_reason := v_exception_code || ' - ' || v_exception_msg;
        dbms_output.put_line(v_reason);

        v_log_result := log_activity('PROCEDURE','complete_esi','EH-LOOP',v_reason,v_result,v_created_By, sysdate,v_es_intent_id);
      END;
      end loop;


      if(not v_no_records)then
        update t_es_intent SET status_code ='COMPLETED' where ID = v_es_intent_id;
      end if;
    else
      if (v_esi_status_code = 'COMPLETED') THEN 
          v_reason:='ESI already completed';
        else  
          v_reason:='Only flows of type-WEG is handled';
      end if;
    end if;	
		if(v_no_records) THEN
			v_result := 'FAILURE';
	    if(v_reason='' OR V_REASON IS NULL) then v_reason := 'No records to process'; end if;
		END IF;


	exception
	  when others then
	    v_exception_code := SQLCODE;
	    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
	    v_result := 'FAILURE';
	    v_reason := v_exception_code || ' - ' || v_exception_msg;
	    dbms_output.put_line(v_reason);

      v_log_result := log_activity('PROCEDURE','complete_esi','EH',v_reason,v_result,v_created_By, sysdate,v_es_intent_id);
	END;
   <<THE_END>>

  v_log_result := log_activity('PROCEDURE','complete_esi','END',v_reason,v_result,v_created_By, sysdate,v_es_intent_id);

  COMMIT;

  return V_RESULT; 

END complete_esi;


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
-- Unable to render SYNONYM DDL for object PUBLIC.DBMS_OUTPUT with DBMS_METADATA attempting internal generator.
CREATE PUBLIC SYNONYM DBMS_OUTPUT FOR SYS.DBMS_OUTPUT
