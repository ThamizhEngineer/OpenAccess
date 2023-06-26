--------------------------------------------------------
--  File created - Monday-April-09-2018   
--------------------------------------------------------
DROP FUNCTION "OPENACCESS"."PROCESS_IMPORT_SELLERS";
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
--  DDL for View V_ORG
--------------------------------------------------------

  CREATE OR REPLACE FORCE EDITIONABLE VIEW "OPENACCESS"."V_ORG" ("ORG_ID", "ORG_CODE", "ORG_NAME", "ORG_TYPE_CODE", "ORG_PARENT_CODE", "ORG_ADDRESS", "ORG_LANDLINE", "ORG_MOBILE", "ORG_EMAIL", "PARENT_ORG_ID", "PARENT_ORG_CODE", "PARENT_ORG_NAME", "PARENT_ORG_TYPE_CODE", "PARENT_ORG_PARENT_CODE", "PARENT_ORG_ADDRESS", "PARENT_ORG_LANDLINE", "PARENT_ORG_MOBILE", "PARENT_ORG_EMAIL") AS 
  SELECT ORG.ID as ORG_ID,ORG.CODE as ORG_CODE,ORG.NAME as ORG_NAME,ORG.TYPE_CODE as ORG_TYPE_CODE,ORG.PARENT_CODE as ORG_PARENT_CODE,ORG.ADDRESS as ORG_ADDRESS,ORG.LANDLINE as ORG_LANDLINE,ORG.MOBILE as ORG_MOBILE,ORG.EMAIL as ORG_EMAIL,
PARENTORG.ID as PARENT_ORG_ID,PARENTORG.CODE as PARENT_ORG_CODE,PARENTORG.NAME as PARENT_ORG_NAME,PARENTORG.TYPE_CODE as PARENT_ORG_TYPE_CODE,PARENTORG.PARENT_CODE as PARENT_ORG_PARENT_CODE,PARENTORG.ADDRESS as PARENT_ORG_ADDRESS,PARENTORG.LANDLINE as PARENT_ORG_LANDLINE,PARENTORG.MOBILE as PARENT_ORG_MOBILE,PARENTORG.EMAIL as PARENT_ORG_EMAIL
FROM M_ORG ORG
LEFT JOIN M_ORG PARENTORG ON ORG.PARENT_CODE =  PARENTORG.CODE
;
--------------------------------------------------------
--  DDL for Table IMPORT_SELLERS
--------------------------------------------------------

  CREATE TABLE "OPENACCESS"."IMPORT_SELLERS" 
   (	"WEG_SC_NO" VARCHAR2(100 BYTE), 
	"WEG_DEVELOPER_NAME" VARCHAR2(100 BYTE), 
	"EDC_NAME" VARCHAR2(100 BYTE), 
	"M_ORG_ID" VARCHAR2(100 BYTE), 
	"WEG_MAKE" VARCHAR2(100 BYTE), 
	"GEN_MAKE_NAME_OA" VARCHAR2(100 BYTE), 
	"GEN_MAKE_CODE" VARCHAR2(100 BYTE), 
	"WEG_DETAILS_KW" VARCHAR2(100 BYTE), 
	"NO_OF_GEN_UNITS" VARCHAR2(100 BYTE), 
	"GEN_UNIT_CAPACITY" VARCHAR2(100 BYTE), 
	"TOTAL_CAPAITY_KW" VARCHAR2(100 BYTE), 
	"PT_RATIO" VARCHAR2(100 BYTE), 
	"CT_RATIO" VARCHAR2(100 BYTE), 
	"MF" VARCHAR2(100 BYTE), 
	"SF_NO" VARCHAR2(400 BYTE), 
	"VILLAGE" VARCHAR2(100 BYTE), 
	"FEEDING_SS" VARCHAR2(100 BYTE), 
	"SS_NAME_IN_OA" VARCHAR2(100 BYTE), 
	"M_SUBSTATION_ID" VARCHAR2(100 BYTE), 
	"NATURE_OF_BOARD" VARCHAR2(100 BYTE), 
	"FEEDER_NAME_WITH_VOLTAGE" VARCHAR2(100 BYTE), 
	"FEEDER_NAME_IN_OA" VARCHAR2(100 BYTE), 
	"M_FEEDER_ID" VARCHAR2(100 BYTE), 
	"METER_MAKE" VARCHAR2(100 BYTE), 
	"METER_MAKE_CODE" VARCHAR2(100 BYTE), 
	"METER_NUMBER" VARCHAR2(100 BYTE), 
	"ABT_METER_TYPE" VARCHAR2(100 BYTE), 
	"IS_ABT" VARCHAR2(100 BYTE), 
	"MODEM_FIXED" VARCHAR2(100 BYTE), 
	"MODEM_NO" VARCHAR2(100 BYTE), 
	"PURPOSE" VARCHAR2(100 BYTE), 
	"COD" VARCHAR2(100 BYTE), 
	"REMARKS" VARCHAR2(200 BYTE), 
	"VOLTAGE_CODE" VARCHAR2(50 BYTE), 
	"IMPORTED" VARCHAR2(100 BYTE), 
	"COMMISSION_DATE" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table M_FEEDER
--------------------------------------------------------

  CREATE TABLE "OPENACCESS"."M_FEEDER" 
   (	"ID" VARCHAR2(50 BYTE), 
	"CODE" VARCHAR2(50 BYTE), 
	"NAME" VARCHAR2(50 BYTE), 
	"VOLTAGE" VARCHAR2(25 BYTE), 
	"M_SUBSTATION_ID" VARCHAR2(50 BYTE), 
	"REMARKS" VARCHAR2(100 BYTE), 
	"ENABLED" CHAR(1 BYTE) DEFAULT 'Y', 
	"SS_NAME" VARCHAR2(200 BYTE), 
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
--  DDL for Table M_SIGNUP
--------------------------------------------------------

  CREATE TABLE "OPENACCESS"."M_SIGNUP" 
   (	"ID" VARCHAR2(50 BYTE), 
	"COMPANY_NAME" VARCHAR2(100 BYTE), 
	"PURPOSE" VARCHAR2(50 BYTE), 
	"REGISTRATION_NO" VARCHAR2(50 BYTE), 
	"REGISTRATION_DATE" DATE, 
	"COMMISSION_DATE" DATE, 
	"VOLTAGE" VARCHAR2(50 BYTE), 
	"HTSC_NUMBER_OLD" VARCHAR2(100 BYTE), 
	"TARIFF" VARCHAR2(50 BYTE), 
	"SANCTIONED_QUANTUM" VARCHAR2(25 BYTE), 
	"TOTAL_CAPACITY" VARCHAR2(25 BYTE), 
	"IS_CAPTIVE" CHAR(1 BYTE), 
	"USER_NAME" VARCHAR2(100 BYTE), 
	"USER_ID" VARCHAR2(100 BYTE), 
	"PASSWORD" VARCHAR2(100 BYTE), 
	"POWERPLANT_TYPE" VARCHAR2(50 BYTE), 
	"POWERPLANT_NAME" VARCHAR2(100 BYTE), 
	"FUEL" VARCHAR2(50 BYTE), 
	"NO_OF_GENERATOR" NUMBER(2,0), 
	"GENERATOR_TYPE" VARCHAR2(50 BYTE), 
	"GENERATOR_MODEL" VARCHAR2(50 BYTE), 
	"IS_REC" CHAR(1 BYTE), 
	"WIND_PASS_CODE" VARCHAR2(50 BYTE), 
	"LOCATION" VARCHAR2(500 BYTE), 
	"ADDRESS_LINE" VARCHAR2(500 BYTE), 
	"VILLAGE" VARCHAR2(100 BYTE), 
	"TALUK_CODE" VARCHAR2(50 BYTE), 
	"CITY" VARCHAR2(100 BYTE), 
	"DISTRICT_CODE" VARCHAR2(50 BYTE), 
	"STATE_CODE" VARCHAR2(50 BYTE), 
	"M_ORG_ID" VARCHAR2(50 BYTE), 
	"M_SUBSTATION_ID" VARCHAR2(50 BYTE), 
	"M_FEEDER_ID" VARCHAR2(50 BYTE), 
	"IS_COMPLETE" CHAR(1 BYTE), 
	"COMPANY_ADDRESS" VARCHAR2(1000 BYTE), 
	"APPROVAL_DT" DATE, 
	"PLANT_CLASS_TYPE_CODE" VARCHAR2(50 BYTE), 
	"BUYER_TYPE_CODE" VARCHAR2(50 BYTE), 
	"GENERATOR_CAPACITY" VARCHAR2(20 BYTE), 
	"METER_NUMBER" VARCHAR2(50 BYTE), 
	"METER_MAKE_CODE" VARCHAR2(50 BYTE), 
	"ACCURACY_CLASS_TYPE_CODE" VARCHAR2(50 BYTE), 
	"IS_ABT_METER" CHAR(1 BYTE), 
	"MULTIPLICATION_FACTOR" VARCHAR2(20 BYTE), 
	"APPLICATION_DT" DATE, 
	"CREATED_BY" VARCHAR2(50 BYTE), 
	"CREATED_DATE" DATE DEFAULT sysdate, 
	"MODIFIED_BY" VARCHAR2(50 BYTE), 
	"MODIFIED_DATE" DATE, 
	"REMARKS" VARCHAR2(300 BYTE), 
	"CONSUMER_HT_NUMBER" VARCHAR2(50 BYTE), 
	"TRADE_START_DATE" DATE, 
	"TRADE_END_DATE" DATE, 
	"TRADER_SERVICE_ID" VARCHAR2(50 BYTE), 
	"MODEM_NO" VARCHAR2(100 BYTE), 
	"GU_MODEL1" VARCHAR2(100 BYTE), 
	"GU_CAPACITY1" VARCHAR2(100 BYTE), 
	"NO_OF_GU1" VARCHAR2(100 BYTE), 
	"GU_MODEL2" VARCHAR2(100 BYTE), 
	"GU_CAPACITY2" VARCHAR2(100 BYTE), 
	"NO_OF_GU2" VARCHAR2(100 BYTE), 
	"GU_MODEL3" VARCHAR2(100 BYTE), 
	"GU_CAPACITY3" VARCHAR2(100 BYTE), 
	"NO_OF_GU3" VARCHAR2(100 BYTE), 
	"GU_MODEL4" VARCHAR2(100 BYTE), 
	"GU_CAPACITY4" VARCHAR2(100 BYTE), 
	"NO_OF_GU4" VARCHAR2(100 BYTE), 
	"GU_MODEL5" VARCHAR2(100 BYTE), 
	"GU_CAPACITY5" VARCHAR2(100 BYTE), 
	"NO_OF_GU5" VARCHAR2(100 BYTE), 
	"GU_MODEL6" VARCHAR2(100 BYTE), 
	"GU_CAPACITY6" VARCHAR2(100 BYTE), 
	"NO_OF_GU6" VARCHAR2(100 BYTE), 
	"METER_CT_1" VARCHAR2(100 BYTE), 
	"METER_CT_2" VARCHAR2(100 BYTE), 
	"METER_CT_3" VARCHAR2(100 BYTE), 
	"METER_BT_1" VARCHAR2(100 BYTE), 
	"METER_BT_2" VARCHAR2(100 BYTE), 
	"METER_BT_3" VARCHAR2(100 BYTE), 
	"SURPLUS_ENERGY_CODE" VARCHAR2(100 BYTE), 
	"ENABLED" CHAR(1 BYTE) DEFAULT 'Y', 
	"TOTAL_GENERATOR_UNIT" VARCHAR2(50 BYTE), 
	"METER_PT_RATIO" VARCHAR2(100 BYTE), 
	"METER_CT_RATIO" VARCHAR2(100 BYTE), 
	"TURBINE_SL_NO" VARCHAR2(100 BYTE), 
	"TURBINE_ROTOR_DIA" VARCHAR2(100 BYTE), 
	"TURBINE_HUB_HEIGHT" VARCHAR2(100 BYTE), 
	"IS_DLMS_METER" VARCHAR2(50 BYTE), 
	"HTSC_NUMBER" VARCHAR2(100 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table M_SUBSTATION
--------------------------------------------------------

  CREATE TABLE "OPENACCESS"."M_SUBSTATION" 
   (	"ID" VARCHAR2(50 BYTE), 
	"CODE" VARCHAR2(50 BYTE), 
	"NAME" VARCHAR2(50 BYTE), 
	"REMARKS" VARCHAR2(100 BYTE), 
	"M_ORG_ID" VARCHAR2(50 BYTE), 
	"CREATED_BY" VARCHAR2(50 BYTE), 
	"CREATED_DATE" DATE DEFAULT sysdate, 
	"MODIFIED_BY" VARCHAR2(50 BYTE), 
	"MODIFIED_DATE" DATE, 
	"ENABLED" CHAR(1 BYTE) DEFAULT 'Y'
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Sequence M_SIGNUP_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OPENACCESS"."M_SIGNUP_SEQ"  MINVALUE 10000 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 36881 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Function PROCESS_IMPORT_SELLERS
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "OPENACCESS"."PROCESS_IMPORT_SELLERS" 
(
  v_REMARKS in varchar2
) RETURN VARCHAR2 AS 
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
/*
*/
update import_sellers set is_abt=null,m_org_id=null, m_substation_id=null,m_feeder_id=null,voltage_code=null,gen_make_code=null,no_of_gen_units=null, gen_unit_capacity=null, commission_date=null where remarks = v_remarks;
delete from import_sellers where edc_name in (' EDC_Name') and remarks = v_remarks;
delete from import_sellers where weg_sc_no is null and remarks = v_remarks;
update import_sellers set edc_name = 'DINDIGUL' where edc_name = 'DINDIGUL EDC' and remarks = v_remarks;
/*update import_sellers set edc_name = 'THENI' where edc_name = 'THENI EDC' and remarks = v_remarks;
update import_sellers set edc_name = 'COIMBATORE (SOUTH)' where edc_name = 'COIMBATORE (SOUTH) EDC' and remarks = v_remarks; 
update import_sellers set edc_name = 'KANYA KUMARI' where edc_name = 'KANYAKUMARI' and remarks = v_remarks;
update import_sellers set edc_name = 'TUTICORIN' where edc_name = 'Tuticorin EDC' and remarks = v_remarks;
update import_sellers set edc_name = 'TIRUNELVELI' where edc_name = 'Tirnelveli EDC' and remarks = v_remarks;*/
update import_sellers set is_abt ='Y' where abt_meter_type like 'ABT%' and remarks = v_remarks;
update import_sellers set no_of_gen_units = substr(weg_details_kw,0,instr(weg_details_kw,'*')-1) , gen_unit_capacity = substr(weg_details_kw,instr(weg_details_kw,'*')+1,length(weg_details_kw)) where remarks = v_remarks;


update import_sellers set cod=trim(cod);

/*update import_sellers set cod='29.09.09' where cod='29.09.0.9';
update import_sellers set cod='17.08.11' where cod='17.0.8.11';
update import_sellers set cod='02.09.11' where cod='2.09.11';
update import_sellers set cod='02.09.10' where cod='02.09.10.';
update import_sellers set cod='1.01.11' where cod='1.0.11';
update import_sellers set cod='28.01.06' where cod='28.0.06';
update import_sellers set cod='18.01.08' where cod='18.0.08';
update import_sellers set cod='02.12.13' where cod='02.13.12';*/
update import_sellers set cod=null where cod='s';
/*update import_sellers set cod='09/30/1997' where cod='31/09/1997';*/
update import_sellers set commission_date = to_date(replace(cod,'.','-'),'dd-mm-yyyy')  where instr(cod,'.') >0 and remarks = v_remarks;
update import_sellers set commission_date = to_date(cod,'dd-mm-yyyy')  where instr(cod,'-') >0 and remarks = v_remarks;
update import_sellers set commission_date =  to_date(replace(cod,'/','-'),'mm-dd-yyyy')  where 
  commission_date is  null and  instr(cod,'.') =0 and to_number(substr(cod,0,2)) =3 and to_number(substr(cod, instr(cod,'/')+1, instr(cod,'/',1,2)-  instr(cod,'/',1,1)-1)) >28 and remarks = v_remarks;
update import_sellers set commission_date =  to_date(replace(cod,'/','-'),'mm-dd-yyyy')  where 
  commission_date is  null and cod is not null;
--update org-id
FOR i IN ( select distinct i.edc_name, o.org_id from import_sellers i inner join v_org o on o.org_name = i.edc_name) 
LOOP
  update import_sellers set m_org_id = i.org_id where edc_name = i.edc_name and remarks = v_remarks;
END LOOP;

--update ss-id
FOR j IN ( select distinct i.SS_NAME_IN_OA, s.id  from import_sellers i inner join m_substation s on i.SS_NAME_IN_OA = s.name) 
LOOP
  update import_sellers set m_substation_id = j.id where SS_NAME_IN_OA = j.SS_NAME_IN_OA and remarks = v_remarks;
END LOOP;

--update feeder-id and voltage-code
FOR k IN (select distinct i.FEEDER_NAME_IN_OA, f.id,  v.value_code  from import_sellers i inner join m_feeder f on i.FEEDER_NAME_IN_OA = f.name
inner join v_codes v on list_code = 'VOLTAGE_CODE' and f.voltage = v.value_desc) 
LOOP
  update import_sellers set m_feeder_id = k.id, voltage_code=k.value_code where feeder_NAME_IN_OA = k.feeder_NAME_IN_OA and remarks = v_remarks;
END LOOP;

--update generator make code
FOR l IN ( select value_code, value_desc FROM v_codes WHERE list_code = 'GENERATOR_MAKE_CODE') 
LOOP
	UPDATE import_sellers SET  gen_make_code = l.VALUE_CODE WHERE gen_make_name_oa= l.VALUE_DESC and remarks = v_remarks;
END LOOP;

--update meter make code
FOR m IN ( select value_code, value_desc FROM v_codes WHERE list_code = 'METER_MAKE_CODE') 
LOOP
	UPDATE import_sellers SET  meter_make_code = m.VALUE_CODE WHERE meter_make= m.VALUE_DESC and remarks = v_remarks;
END LOOP;


--insert into m_signup table

insert into m_signup(ID,COMPANY_NAME,PURPOSE,VOLTAGE,HTSC_NUMBER,SANCTIONED_QUANTUM,TOTAL_CAPACITY,M_ORG_ID,IS_COMPLETE,VILLAGE,CREATED_BY,CREATED_DATE,REMARKS,ENABLED,
COMMISSION_DATE,GU_MODEL1,GU_CAPACITY1,NO_OF_GU1,METER_NUMBER,METER_MAKE_CODE,IS_ABT_METER,MULTIPLICATION_FACTOR,MODEM_NO,M_SUBSTATION_ID,M_FEEDER_ID,IS_CAPTIVE,FUEL)
(select M_SIGNUP_SEQ.nextval ID, WEG_DEVELOPER_NAME COMPANY_NAME, '02' PURPOSE, VOLTAGE_CODE VOLTAGE,WEG_SC_NO HTSC_NUMBER,TOTAL_CAPAITY_KW SANCTIONED_QUANTUM, 
TOTAL_CAPAITY_KW TOTAL_CAPACITY, M_ORG_ID M_ORG_ID, 'N' IS_COMPLETE, VILLAGE, 'admin' CREATED_BY,sysdate created_date, REMARKS REMARKS, 'Y' enabled, 
COMMISSION_DATE, GEN_MAKE_CODE GU_MODEL1,GEN_UNIT_CAPACITY GU_CAPACITY1, NO_OF_GEN_UNITS NO_OF_GU1,
METER_NUMBER, METER_MAKE_CODE, IS_ABT IS_ABT_METER,MF  MULTIPLICATION_FACTOR, MODEM_NO,  M_SUBSTATION_ID, M_FEEDER_ID, 'N' IS_CAPTIVE,
'02' FUEL from import_sellers where remarks = v_remarks);

--update m_signup set htsc_number = substr(htsc_number,0, instr(htsc_number,'-')-1)||'-'|| trim(substr(htsc_number, instr(htsc_number,'-')+1,length(htsc_number))) where  remarks='SELLER-tin-tut-kk-weg-20-dec-2017'; 


/*
--query to find issues with dates
select cod, substr(cod,0,2), substr(cod, instr(cod,'/')+1, instr(cod,'/',1,2)-  instr(cod,'/',1,1)-1) day
,substr(cod,0,2)||'-28-'|| to_number(substr(cod, instr(cod,'/',1,2)+1,length(cod))) new_date
,to_number(substr(cod, instr(cod,'/',1,2)+1,length(cod))) year
, to_date(replace(cod,'/','-'),'mm-dd-yyyy') 
from import_Sellers 
where 1=1
--and instr(cod,'.') =0
and commission_date is  null
and cod is not null
--and rownum <110
--and length(cod) >11
--and to_number(substr(cod, instr(cod,'.')+1, instr(cod,'.',1,2)-  instr(cod,'.',1,1)-1)) >12
--and to_number(substr(cod,0,2)) >12
--and to_number(substr(cod,0,2)) =3 and to_number(substr(cod, instr(cod,'/')+1, instr(cod,'/',1,2)-  instr(cod,'/',1,1)-1)) >28
--and IS_LEAP_YEAR(to_number(substr(cod, instr(cod,'/',1,2)+1,length(cod)))) = 'FALSE'
;
*/
/*
FOR j IN ( select distinct f.id, f.name from m_feeder f join  temp_technical_master t   on t.wg_feedname = f.name) 
LOOP
  update temp_technical_master set m_feeder_id = j.id where wg_feedname = j.name;
  update temp_commercial_master set m_feeder_id = j.id where wg_feedname = j.name;
END LOOP; 


update temp_technical_master set wg_htscno = substr(wg_htscno, 1, instr(wg_htscno, '.') -1);
update temp_commercial_master set wg_htscno = substr(wg_htscno, 1, instr(wg_htscno, '.') -1);
update temp_commercial_master set wg_circle = substr(wg_circle, 1, instr(wg_circle, '.') -1);
update temp_technical_master set circlecode = substr(circlecode, 1, instr(circlecode, '.') -1);
UPDATE TEMP_TECHNICAL_MASTER SET wg_Cod = to_char(to_Date(wg_Cod,'mm/dd/yyyy'),'yyyy-mm-dd');
update temp_technical_master set WG_CAP = substr(WG_CAP, 1, instr(WG_CAP, '.') -1);
update temp_technical_master set WG_ABTYN = 'Y' WHERE WG_ABTYN = 'YES';
update temp_technical_master set WG_ABTYN = 'N' WHERE WG_ABTYN = 'NO';
update temp_technical_master set WG_ACCLASS = '01' WHERE WG_ACCLASS = '0.2 CLASS';
update temp_technical_master set WG_ACCLASS = '02' WHERE WG_ACCLASS = '0.5 CLASS';
update temp_technical_master set WG_REC = 'Y' WHERE WG_REC = 'REC';
update temp_technical_master set WG_REC = 'N' WHERE WG_REC = 'Non-REC';
UPDATE TEMP_TECHNICAL_MASTER SET WG_ENTDATE = to_char(to_Date(WG_ENTDATE,'mm/dd/yyyy'),'yyyy-mm-dd');

UPDATE TEMP_COMMERCIAL_MASTER SET WG_DOA = to_char(SYSDATE-61,'mm/dd/yyyy') WHERE substr(WG_DOA, 1,1) = '-';
UPDATE TEMP_COMMERCIAL_MASTER SET WG_DOA = to_char(to_Date(WG_DOA,'mm/dd/yyyy'),'yyyy-mm-dd');


update TEMP_COMMERCIAL_MASTER set WG_CATEGORY = '02' WHERE WG_CATEGORY = 'Sale to TANGEDCO';
update TEMP_COMMERCIAL_MASTER set WG_CATEGORY = '04' WHERE WG_CATEGORY = 'Wheeling to third party';
update TEMP_COMMERCIAL_MASTER set WG_CATEGORY = '03' WHERE WG_CATEGORY = 'Wheeling for Captive Use';
update TEMP_COMMERCIAL_MASTER set WG_CATEGORY = '03,02' WHERE WG_CATEGORY = 'Wheeling for Captive use and balance monthly sales to TANGEDCO';
UPDATE TEMP_COMMERCIAL_MASTER SET WG_ENTDATE = to_char(to_Date(WG_ENTDATE,'mm/dd/yyyy'),'yyyy-mm-dd');


FOR i IN ( select distinct s.id,  s.name from m_substation s join  temp_technical_master t   on t.wg_ssname = s.name) 
LOOP
  update temp_technical_master set m_substation_id = i.id where wg_ssname = i.name;
  update temp_commercial_master set m_substation_id = i.id where wg_ssname = i.name;
END LOOP;

FOR j IN ( select distinct f.id, f.name from m_feeder f join  temp_technical_master t   on t.wg_feedname = f.name) 
LOOP
  update temp_technical_master set m_feeder_id = j.id where wg_feedname = j.name;
  update temp_commercial_master set m_feeder_id = j.id where wg_feedname = j.name;
END LOOP; 


--update wind pass code
FOR i IN ( select value_code, value_desc FROM v_codes WHERE list_code = 'WIND_PASS_CODE') 
LOOP
	UPDATE TEMP_TECHNICAL_MASTER SET  WG_PASS = i.VALUE_CODE WHERE WG_PASS= i.VALUE_DESC ;
END LOOP;

--update generator make code
FOR i IN ( select value_code, value_desc FROM v_codes WHERE list_code = 'GENERATOR_MAKE_CODE') 
LOOP
	UPDATE TEMP_TECHNICAL_MASTER SET  WG_MAKE = i.VALUE_CODE WHERE WG_MAKE= i.VALUE_DESC ;
END LOOP;


	--update voltage code
FOR i IN ( select value_code, value_desc FROM v_codes WHERE list_code = 'VOLTAGE_CODE') 
LOOP
	UPDATE TEMP_TECHNICAL_MASTER SET  WG_INCVOL = i.VALUE_CODE WHERE WG_INCVOL= i.VALUE_DESC ;
END LOOP;


	--update TALUK code
FOR i IN ( select value_code, value_desc FROM v_codes WHERE list_code = 'TALUK_CODE') 
LOOP
	UPDATE TEMP_TECHNICAL_MASTER SET  WG_TALUK = i.VALUE_CODE WHERE WG_TALUK= i.VALUE_DESC ;
END LOOP;
COMMIT;

   --update PLANT CLASS TYPE CODE
FOR i IN ( select value_code, value_desc FROM v_codes WHERE list_code = 'PLANT_CLASS_TYPE_CODE') 
LOOP
	UPDATE TEMP_COMMERCIAL_MASTER SET  WG_CLASS = i.VALUE_CODE WHERE WG_CLASS= i.VALUE_DESC ;
END LOOP;
COMMIT;




update temp_technical_master set is_enabled = 'Y', remarks='beta data-migration' where m_company_id is not null and length(wg_meterno) > 3;

FOR i IN (select m_company_id from temp_technical_master where  m_company_id is not null and is_enabled = 'Y') 
LOOP
	UPDATE TEMP_COMMERCIAL_MASTER SET is_enabled = 'Y', remarks='beta data-migration' WHERE m_company_id= i.m_company_id ;
END LOOP;
COMMIT;

*/
  return V_RESULT; 

END PROCESS_IMPORT_SELLERS;


/
