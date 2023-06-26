CREATE SEQUENCE  m_tariff_seq MINVALUE 10000 MAXVALUE 99999999999 INCREMENT BY 1 START WITH 10020 CACHE 20 NOORDER  NOCYCLE ;

CREATE SEQUENCE  m_company_seq MINVALUE 10000 MAXVALUE 99999999999 INCREMENT BY 1 START WITH 10020 CACHE 20 NOORDER  NOCYCLE ;

CREATE SEQUENCE  m_company_employee_seq MINVALUE 10000 MAXVALUE 99999999999 INCREMENT BY 1 START WITH 10020 CACHE 20 NOORDER  NOCYCLE ;

CREATE SEQUENCE  m_company_location_seq MINVALUE 10000 MAXVALUE 99999999999 INCREMENT BY 1 START WITH 10020 CACHE 20 NOORDER  NOCYCLE ;

CREATE SEQUENCE  m_company_shareholders_seq MINVALUE 10000 MAXVALUE 99999999999 INCREMENT BY 1 START WITH 10020 CACHE 20 NOORDER  NOCYCLE ;

CREATE SEQUENCE  m_feeder_seq MINVALUE 10000 MAXVALUE 99999999999 INCREMENT BY 1 START WITH 10020 CACHE 20 NOORDER  NOCYCLE ;

CREATE SEQUENCE  m_powerplant_seq MINVALUE 10000 MAXVALUE 99999999999 INCREMENT BY 1 START WITH 10020 CACHE 20 NOORDER  NOCYCLE ;

CREATE SEQUENCE  m_generator_seq MINVALUE 10000 MAXVALUE 99999999999 INCREMENT BY 1 START WITH 10020 CACHE 20 NOORDER  NOCYCLE ;

CREATE SEQUENCE  m_lov_detail_seq MINVALUE 10000 MAXVALUE 99999999999 INCREMENT BY 1 START WITH 10020 CACHE 20 NOORDER  NOCYCLE ;

CREATE SEQUENCE  m_lov_header_seq MINVALUE 10000 MAXVALUE 99999999999 INCREMENT BY 1 START WITH 10020 CACHE 20 NOORDER  NOCYCLE ;

CREATE SEQUENCE  m_org_seq MINVALUE 10000 MAXVALUE 99999999999 INCREMENT BY 1 START WITH 10020 CACHE 20 NOORDER  NOCYCLE ;

CREATE SEQUENCE  m_service_seq MINVALUE 10000 MAXVALUE 99999999999 INCREMENT BY 1 START WITH 10020 CACHE 20 NOORDER  NOCYCLE ;

CREATE SEQUENCE  m_substation_seq MINVALUE 10000 MAXVALUE 99999999999 INCREMENT BY 1 START WITH 10020 CACHE 20 NOORDER  NOCYCLE ;

CREATE SEQUENCE  m_user_seq MINVALUE 10000 MAXVALUE 99999999999 INCREMENT BY 1 START WITH 10020 CACHE 20 NOORDER  NOCYCLE ;

CREATE SEQUENCE  t_grid_conn_appln_seq MINVALUE 10000 MAXVALUE 99999999999 INCREMENT BY 1 START WITH 10020 CACHE 20 NOORDER  NOCYCLE ;




--------------------------------------------------------
--  DDL for View V_GENERATOR
--------------------------------------------------------

  CREATE VIEW V_GENERATOR
AS 
select ID,IS_PRIMARY,VERSION,CODE,NAME,PLANT_TYPE_CODE,'' PLANT_TYPE_NAME,FUEL_TYPE_CODE, '' FUEL_TYPE_NAME,
M_SERVICE_ID,'' SERVICE_NUMBER,'' M_COMPANY_ID,  '' COMPANY_CODE,'' COMPANY_NAME, M_ORG_ID,'' ORG_CODE,'' ORG_NAME, T_GRID_CONN_APPLN_ID,TOTAL_CAPACITY,'' M_SUBSTATION_ID, '' SUBSTATION_CODE,'' SUBSTATION_NAME,  '' SUBSTATION_VOLTAGE ,INTERFACE_VOLTAGE_PHASE,INTERFACE_VOLTAGE_FREQUENCY,
COMMISSION_DATE,PURPOSE,ENABLED,STATUS,LINE1,CITY,STATE_CODE,'' STATE_DESC,PINCODE,VILLAGE,TALUK_CODE,'' TALUK_NAME,DISTRICT_CODE ,'' DISTRICT_NAME,PLS_SF_NO,PL_VILLAGE,PL_TOWN,PL_TALUK_CODE,'' PL_TALUK_NAME,PL_DISTRICT_CODE , '' PL_DISTRICT_NAME,WIND_PASS_CODE, '' WIND_PASS_DESC,WIND_ZONE_AREA_CODE, '' WIND_ZONE_AREA_DESC
from m_generator;

/*
 * 
 
 --------------------------------------------------------
--  DDL for View V_GENERATOR
--------------------------------------------------------

  CREATE OR REPLACE FORCE VIEW "OPEN_ACCESS"."V_GENERATOR" ("ID", "IS_PRIMARY", "VERSION", "CODE", "NAME", "PLANT_TYPE_CODE", "PLANT_TYPE_NAME", "FUEL_TYPE_CODE", "FUEL_TYPE_NAME", "M_SERVICE_ID", "SERVICE_NUMBER", "M_COMPANY_ID", "COMPANY_CODE", "COMPANY_NAME", "M_ORG_ID", "ORG_CODE", "ORG_NAME", "T_GRID_CONN_APPLN_ID", "TOTAL_CAPACITY", "M_SUBSTATION_ID", "SUBSTATION_CODE", "SUBSTATION_NAME", "SUBSTATION_VOLTAGE", "INTERFACE_VOLTAGE_PHASE", "INTERFACE_VOLTAGE_FREQUENCY", "COMMISSION_DATE", "PURPOSE", "ENABLED", "STATUS", "LINE1", "CITY", "STATE_CODE", "STATE_DESC", "PINCODE", "VILLAGE", "TALUK_CODE", "TALUK_NAME", "DISTRICT_CODE", "DISTRICT_NAME", "PLS_SF_NO", "PL_VILLAGE", "PL_TOWN", "PL_TALUK_CODE", "PL_TALUK_NAME", "PL_DISTRICT_CODE", "PL_DISTRICT_NAME", "WIND_PASS_CODE", "WIND_PASS_DESC", "WIND_ZONE_AREA_CODE", "WIND_ZONE_AREA_DESC") AS 
  select id ID,'' IS_PRIMARY,'' VERSION,'' CODE,'' NAME,'' PLANT_TYPE_CODE,'' PLANT_TYPE_NAME,'' FUEL_TYPE_CODE, 'Wind' FUEL_TYPE_NAME,
'' M_SERVICE_ID,WG_HTSCNO SERVICE_NUMBER,'' M_COMPANY_ID,  '' COMPANY_CODE,WG_NAMETIEUP COMPANY_NAME,''  M_ORG_ID,CIRCLECODE ORG_CODE,'' ORG_NAME,''  T_GRID_CONN_APPLN_ID,WG_CAP TOTAL_CAPACITY,'' M_SUBSTATION_ID, '' SUBSTATION_CODE,WG_SSNAME SUBSTATION_NAME,  '' SUBSTATION_VOLTAGE ,'' INTERFACE_VOLTAGE_PHASE,'' INTERFACE_VOLTAGE_FREQUENCY,
WG_COD COMMISSION_DATE,'' PURPOSE,'' ENABLED,'' STATUS,'' LINE1,'' CITY,'' STATE_CODE,'' STATE_DESC,'' PINCODE,'' VILLAGE,'' TALUK_CODE,WG_TALUK TALUK_NAME,'' DISTRICT_CODE ,'' DISTRICT_NAME,'' PLS_SF_NO,'' PL_VILLAGE,'' PL_TOWN,'' PL_TALUK_CODE,'' PL_TALUK_NAME,'' PL_DISTRICT_CODE , '' PL_DISTRICT_NAME,'' WIND_PASS_CODE, WG_PASS WIND_PASS_DESC,'' WIND_ZONE_AREA_CODE, '' WIND_ZONE_AREA_DESC
from temp_weg;


 */

--------------------------------------------------------
--  DDL for View V_CODES
--------------------------------------------------------

  CREATE OR REPLACE FORCE VIEW V_CODES ("LIST_CODE", "LIST_NAME", "VALUE_CODE", "VALUE_DESC", "ENABLED", "ATTRIB1", "ATTRIB2", "ATTRIB3") AS 
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
order by m_lov_header.code,m_lov_detail.code;

CREATE INDEX M_FEEDER_INDEX ON M_FEEDER (NAME, VOLTAGE, M_SUBSTATION_ID);

CREATE OR REPLACE VIEW V_SIGNUP
AS 
select signup.ID, signup.COMPANY_NAME,signup.PURPOSE AS PURPOSE_CODE,signup.REGISTRATION_NO,signup.REGISTRATION_DATE,signup.COMMISSION_DATE,signup.VOLTAGE as VOLTAGE_CODE,
signup.HTSC_NUMBER,signup.TARIFF,signup.TOTAL_CAPACITY,signup.IS_CAPTIVE,signup.USER_NAME,signup.USER_ID,signup.PASSWORD,signup.POWERPLANT_TYPE AS POWERPLANT_TYPE_CODE,
signup.POWERPLANT_NAME,signup.FUEL AS FUEL_CODE,signup.NO_OF_GENERATOR,signup.GENERATOR_TYPE AS GENERATOR_TYPE_CODE ,signup.GENERATOR_MODEL AS GENERATOR_MODEL_CODE,signup.IS_REC,signup.WIND_PASS_CODE,signup.LOCATION,
signup.ADDRESS_LINE,signup.VILLAGE,signup.TALUK_CODE ,signup.CITY,signup.DISTRICT_CODE,signup.STATE_CODE,signup.M_ORG_ID,signup.M_SUBSTATION_ID,
signup.M_FEEDER_ID,signup.IS_COMPLETE,signup.COMPANY_ADDRESS,signup.APPROVAL_DT,signup.PLANT_CLASS_TYPE_CODE,signup.BUYER_TYPE_CODE,signup.GENERATOR_CAPACITY,
signup.METER_NUMBER,signup.METER_MAKE_CODE,signup.ACCURACY_CLASS_TYPE_CODE,signup.IS_ABT_METER,signup.MULTIPLICATION_FACTOR,signup.APPLICATION_DT,
voltagecodes.Value_Desc as VOLTAGE_NAME, plantcodes.Value_Desc as POWERPLANT_TYPE_NAME,
fuelcodes.Value_Desc as FUEL_NAME, decode(generatormodelcodes.VALUE_CODE, null,'',generatormodelcodes.VALUE_CODE || '-' || generatormodelcodes.Value_Desc) as GENERATOR_MODEL_NAME ,
talukcodes.Value_Desc as TALUK_NAME, districtcodes.VALUE_DESC as DISTRICT_NAME, statecodes.Value_Desc as STATE_NAME, 
org.name as m_org_name, substation.name m_substation_name,feeder.name m_feeder_name,
plantclasstypecodes.Value_Desc as PLANT_CLASS_TYPE_NAME, buyertypecodes.Value_Desc as BUYER_TYPE_NAME,
metermakecodes.Value_Desc as METER_MAKE_NAME,accuracyclasstypecodes.Value_Desc as ACCURACY_CLASS_TYPE_NAME, purposecodes.Value_Desc as PURPOSE_NAME
from m_signup signup
left join v_codes fuelcodes on Signup.Fuel= fuelcodes.Value_Code    AND  fuelcodes.list_code = 'FUEL_TYPE_CODE'
left join v_codes voltagecodes on signup.VOLTAGE= voltagecodes.Value_Code    AND  voltagecodes.list_code = 'VOLTAGE_CODE'
left join v_codes plantcodes on signup.POWERPLANT_TYPE= plantcodes.Value_Code    AND  plantcodes.list_code = 'PLANT_TYPE_CODE'
left join v_codes generatormodelcodes on signup.GENERATOR_MODEL= generatormodelcodes.Value_Code    AND  generatormodelcodes.list_code = 'GENERATOR_MAKE_CODE'
left join v_codes talukcodes on Signup.Taluk_Code = talukcodes.Value_Code AND  talukcodes.list_code = 'TALUK_CODE'
left join v_codes districtcodes on Signup.District_Code = districtcodes.Value_Code AND  districtcodes.list_code = 'DISTRICT_CODE'
left join v_codes statecodes on Signup.State_Code = statecodes.Value_Code AND  statecodes.list_code = 'STATE_CODE'
left join m_org org on signup.m_org_id=org.id
left join m_substation substation on signup.m_substation_id = substation.id  
left join m_feeder feeder on signup.m_feeder_id=feeder.id
left join v_codes plantclasstypecodes on signup.plant_class_type_code = plantclasstypecodes.Value_Code  AND  plantclasstypecodes.list_code = 'PLANT_CLASS_TYPE_CODE'
left join v_codes buyertypecodes on signup.BUYER_TYPE_CODE = buyertypecodes.Value_Code  AND  buyertypecodes.list_code = 'BUYER_TYPE_CODE' 
left join v_codes metermakecodes on signup.METER_MAKE_CODE = metermakecodes.Value_Code  AND  metermakecodes.list_code = 'METER_MAKE_CODE'
left join v_codes accuracyclasstypecodes on signup.ACCURACY_CLASS_TYPE_CODE = accuracyclasstypecodes.Value_Code  AND  accuracyclasstypecodes.list_code = 'ACCURACY_CLASS_TYPE_CODE' 
left join v_codes purposecodes on signup.PURPOSE = purposecodes.Value_Code  AND  purposecodes.list_code = 'PURPOSE_CODE';

CREATE INDEX M_SIGNUP_IDX ON M_SIGNUP (PURPOSE, FUEL, HTSC_NUMBER, COMPANY_NAME, M_ORG_ID, M_SUBSTATION_ID, M_FEEDER_ID);






