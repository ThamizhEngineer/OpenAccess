CREATE OR REPLACE PROCEDURE OPENACCESS."TEMP_MIGRATION_IMPORT1" is

v_company_id  VARCHAR2(50);
	v_service_id VARCHAR2(50);
	v_meter_id VARCHAR2(50);
	v_banking_service_id VARCHAR2(50);
	v_powerplant_id VARCHAR2(50);
	v_is_enabled CHAR(1):='Y';
	v_remarks VARCHAR2(200):='beta data-migration';

	v_buyer_company_id VARCHAR2(50);
	v_buyer_service_id VARCHAR2(50);
BEGIN
/*
	 *

	FOR  i IN (
	SELECT  c.wg_ssname, c.wg_feedname,c.M_ORG_ID, c.wg_htscno,
		(CASE WHEN length(t.WG_METERNO) < 3  THEN null ELSE t.WG_METERNO END) AS meter_no
		FROM TEMP_COMMERCIAL_MASTER c JOIN
		temp_technical_master t ON t.wg_ssname = c.wg_ssname AND t.wg_feedname=c.wg_feedname AND t.wg_htscno = c.wg_htscno
		 ORDER BY c.M_SUBSTATION_ID, c.M_FEEDER_ID,c.M_ORG_ID, c.wg_htscno
	)
	LOOP
		SELECT 'COM-'||m_company_seq.nextval INTO v_company_id FROM dual;
		SELECT 'SR-'||m_company_serv_seq.nextval INTO v_service_id FROM dual;
		SELECT round(dbms_random.value() * 8) + 1  INTO v_meter_id FROM dual;
		--SELECT 'BG-'||m_company_serv_seq.nextval INTO v_banking_service_id FROM dual;
		--SELECT 'PP-'||m_powerplant_seq.nextval INTO v_powerplant_id FROM dual;

		IF (i.meter_no IS null) THEN
			v_is_enabled := 'N';
			v_remarks := 'beta data-migration ,No Meter';
	    else
	      	v_is_enabled := 'Y';
			v_remarks := 'beta data-migration';
		END IF;

		UPDATE temp_commercial_master SET M_COMPANY_ID = v_company_id, M_SERVICE_ID=v_service_id , is_enabled=v_is_enabled, remarks= v_remarks, MODIFIED_DATE = SYSDATE
		WHERE  wg_htscno = i.wg_htscno AND wg_ssname = i.wg_ssname AND wg_feedname = i.wg_feedname;

		UPDATE temp_technical_master SET M_COMPANY_ID = v_company_id, M_SERVICE_ID=v_service_id , is_enabled=v_is_enabled, remarks= v_remarks , MODIFIED_DATE = SYSDATE
		WHERE wg_htscno = i.wg_htscno AND wg_ssname = i.wg_ssname AND wg_feedname = i.wg_feedname;

		UPDATE TEMP_HT_ADJUSTMENT_MASTER SET BUYER_COMPANY_ID = v_company_id, BUY_SERVICE_ID=v_service_id , is_enabled=v_is_enabled , remarks= v_remarks, MODIFIED_DATE = SYSDATE
		WHERE wfwind_gencircle = i.M_ORG_ID AND wfwind_genservice = i.wg_htscno;

		UPDATE TEMP_HT_ADJUSTMENT_MASTER SET SELLER_COMPANY_ID = v_company_id, SELL_SERVICE_ID=v_service_id
		WHERE wfwind_edccircle = i.M_ORG_ID AND wfwind_edcservice = i.wg_htscno;

	END LOOP;


INSERT INTO M_COMPANY (id, code, name, TYPE, IS_CAPTIVE, ENABLED, CREATED_BY, CREATED_DATE, REMARKS)
SELECT c.M_COMPANY_ID AS id, c.M_COMPANY_ID AS code, nvl(t.WG_NAMETIEUP,'Empty') AS name, '05' AS TYPE,
DECODE(SUBSTR(c.WG_CATEGORY,1,2),'03','Y','N') AS IS_CAPTIVE, c.is_enabled enabled, 'admin' AS CREATED_BY,to_date('2017-08-24','yyyy-mm-dd') AS CREATED_DATE,
c.remarks
FROM TEMP_commercial_MASTER c JOIN TEMP_TECHNICAL_MASTER t ON c.WG_CIRCLE = t.CIRCLECODE AND c.WG_SSNAME = t.WG_SSNAME AND c.WG_FEEDNAME = t.WG_FEEDNAME AND c.WG_HTSCNO = t.WG_HTSCNO;


INSERT INTO M_COMPANY_SERVICE(id, TYPE, "number", M_COMPANY_ID, M_ORG_ID, M_SUBSTATION_ID, M_FEEDER_ID, REF_NUMBER, VOLTAGE_CODE, TOTAL_CAPACITY, ENABLED, CREATED_BY, CREATED_DATE, remarks)
SELECT c.M_SERVICE_ID AS id, '03' as "TYPE",c.M_SERVICE_ID  "NUMBER", c.M_COMPANY_ID, c.M_ORG_ID, t.M_SUBSTATION_ID, t.M_FEEDER_ID, NULL "REF_NUMBER",t.WG_INCVOL AS voltage_code ,to_number(t.WG_NOUNITS)*to_number(t.WG_CAP) AS total_capacity,
c.is_enabled enabled, 'admin' AS CREATED_BY,to_date('2017-08-24','yyyy-mm-dd') AS CREATED_DATE,c.remarks
FROM TEMP_commercial_MASTER c JOIN TEMP_TECHNICAL_MASTER t ON c.WG_CIRCLE = t.CIRCLECODE AND c.WG_SSNAME = t.WG_SSNAME AND c.WG_FEEDNAME = t.WG_FEEDNAME AND c.WG_HTSCNO = t.WG_HTSCNO;


INSERT INTO M_COMPANY_SERVICE(id, TYPE, "number", M_COMPANY_ID,  ENABLED, CREATED_BY, CREATED_DATE, remarks)
SELECT 'BG-'||m_company_serv_seq.nextval  AS id, '01' as "TYPE",'BG-'||m_company_serv_seq.currval  "NUMBER", c.M_COMPANY_ID,
c.is_enabled enabled, 'admin' AS CREATED_BY,to_date('2017-08-24','yyyy-mm-dd') AS CREATED_DATE,c.remarks
FROM TEMP_commercial_MASTER c JOIN TEMP_TECHNICAL_MASTER t ON c.WG_CIRCLE = t.CIRCLECODE AND c.WG_SSNAME = t.WG_SSNAME AND c.WG_FEEDNAME = t.WG_FEEDNAME AND c.WG_HTSCNO = t.WG_HTSCNO;

INSERT INTO M_COMPANY_METER (id, M_COMPANY_SERVICE_ID, METER_NUMBER, METER_MAKE_CODE, ACCURACY_CLASS_CODE, IS_ABTMETER, MF,CREATED_BY, CREATED_DATE, REMARKS)
SELECT rownum id, c.M_SERVICE_ID M_COMPANY_SERVICE_ID, (CASE WHEN length(t.WG_METERNO) < 3  THEN 'Empty' ELSE t.WG_METERNO END)  METER_NUMBER,
t.WG_MAKE meter_make_code, t.WG_ACCLASS accuracy_class_code, t.WG_ABTYN is_abtmeter, NULL mf, 'admin' AS CREATED_BY,to_date('2017-08-24','yyyy-mm-dd') AS CREATED_DATE, c.remarks
FROM TEMP_commercial_MASTER c JOIN TEMP_TECHNICAL_MASTER t ON c.WG_CIRCLE = t.CIRCLECODE AND c.WG_SSNAME = t.WG_SSNAME AND c.WG_FEEDNAME = t.WG_FEEDNAME AND c.WG_HTSCNO = t.WG_HTSCNO;




 FOR  i IN (
	SELECT 'PP-'||m_powerplant_seq.nextval  AS id,'PP-'||m_powerplant_seq.currval  code, 'PP-'||m_powerplant_seq.currval||' - '||t.WG_NAMETIEUP name,
	'02' plant_type_Code, '02' fuel_type_code, c.M_SERVICE_ID, c.M_ORG_ID, to_number(t.WG_NOUNITS)*to_number(t.WG_CAP) AS total_capacity, t.M_SUBSTATION_ID, t.WG_INCVOL VOLTAGE_CODE,
	to_date(t.wg_cod, 'yyyy-mm-dd') COMMISSION_DATE, t.WG_PASS wind_pass_code, 'Y' enabled, 'Active' status, 'admin' AS CREATED_BY,to_date('2017-08-24','yyyy-mm-dd') AS CREATED_DATE, c.remarks ,
	t.WG_NOUNITS no_of_units, t.WG_MAKE MAKE_CODE, t.WG_CAP capacity, t.CIRCLECODE , t.WG_SSNAME, t.WG_FEEDNAME, t.WG_HTSCNO
	FROM TEMP_commercial_MASTER c JOIN TEMP_TECHNICAL_MASTER t ON c.WG_CIRCLE = t.CIRCLECODE AND c.WG_SSNAME = t.WG_SSNAME AND c.WG_FEEDNAME = t.WG_FEEDNAME AND c.WG_HTSCNO = t.WG_HTSCNO
	)
 LOOP
  UPDATE TEMP_TECHNICAL_MASTER SET m_powerplant_id = i.id where circlecode = i.CIRCLECODE AND WG_SSNAME = i.WG_SSNAME AND WG_FEEDNAME = i.WG_FEEDNAME AND WG_HTSCNO = i.WG_HTSCNO;

  INSERT INTO M_POWERPLANT(id, code, name, PLANT_TYPE_CODE, FUEL_TYPE_CODE, M_SERVICE_ID, M_ORG_ID, TOTAL_CAPACITY, M_SUBSTATION_ID, INTERFACE_VOLTAGE_FREQUENCY,
 COMMISSION_DATE, WIND_PASS_CODE,  ENABLED, STATUS, CREATED_BY, CREATED_DATE, REMARKS)
VALUES (i.id, i.code, i.name, i.PLANT_TYPE_CODE, i.FUEL_TYPE_CODE, i.M_SERVICE_ID, i.M_ORG_ID, i.TOTAL_CAPACITY, i.M_SUBSTATION_ID, i.VOLTAGE_CODE,
 i.COMMISSION_DATE, i.WIND_PASS_CODE,  i.ENABLED, i.STATUS, i.CREATED_BY, i.CREATED_DATE, i.REMARKS);

 INSERT INTO M_GENERATOR(id, M_POWERPLANT_ID, NO_OF_UNITS, MAKE_CODE, CAPACITY, VOLTAGE_CODE, ENABLED,  CREATED_BY, CREATED_DATE,REMARKS )
 VALUES (i.id, i.id, i.NO_OF_UNITS, i.MAKE_CODE, i.CAPACITY, i.VOLTAGE_CODE, i.ENABLED, i.CREATED_BY, i.CREATED_DATE, i.REMARKS);


 end loop;


UPDATE TEMP_HT_ADJUSTMENT_MASTER h
SET (SELLER_COMPANY_ID, SELL_SERVICE_ID) = (SELECT m_company_id, m_service_id  FROM TEMP_COMMERCIAL_MASTER c WHERE h.WFWIND_GENCIRCLE = c.M_ORG_ID AND h.WFWIND_GENSERVICE = c.WG_HTSCNO)
WHERE EXISTS(
	SELECT 1 FROM TEMP_COMMERCIAL_MASTER c WHERE h.WFWIND_GENCIRCLE = c.M_ORG_ID AND h.WFWIND_GENSERVICE = c.WG_HTSCNO);

	UPDATE TEMP_HT_ADJUSTMENT_MASTER h
SET (BUYER_COMPANY_ID, BUY_SERVICE_ID) = (SELECT m_company_id, m_service_id  FROM TEMP_COMMERCIAL_MASTER c WHERE h.WFWIND_EDCCIRCLE = c.M_ORG_ID AND h.WFWIND_EDCSERVICE = c.WG_HTSCNO)
WHERE EXISTS(
	SELECT 1 FROM TEMP_COMMERCIAL_MASTER c WHERE h.WFWIND_EDCCIRCLE = c.M_ORG_ID AND h.WFWIND_EDCSERVICE = c.WG_HTSCNO);


INSERT INTO OPENACCESS.M_TRADE_RELATIONSHIP
(ID, QUANTUM, FROM_DATE, TO_DATE, STATUS_CODE, M_SELLER_COMPANY_ID, M_SELLER_COMP_SERVICE_ID, M_BUYER_COMPANY_ID, M_BUYER_COMP_SERVICE_ID, REFERENCENUMBER, CREATED_BY, CREATED_DATE, REMARKS)
SELECT rownum id, NULL quantum, to_date(c.WG_DOA,'yyyy-mm-dd') FROM_DATE, NULL to_date, decode(c.IS_ENABLED, 'N', '01', '02') status_code,
h.SELLER_COMPANY_ID M_SELLER_COMPANY_ID, h.SELL_SERVICE_ID M_SELLER_COMP_SERVICE_ID, h.BUYER_COMPANY_ID M_BUYER_COMPANY_ID, h.BUY_SERVICE_ID M_BUYER_COMP_SERVICE_ID, NULL REFERENCENUMBER,
 'admin' AS CREATED_BY,to_date('2017-08-24','yyyy-mm-dd') AS CREATED_DATE, c.remarks
FROM TEMP_HT_ADJUSTMENT_MASTER h, TEMP_COMMERCIAL_MASTER c WHERE h.WFWIND_genCIRCLE = c.M_ORG_ID AND h.WFWIND_genSERVICE = c.WG_HTSCNO;

 */
	NULL;

END TEMP_MIGRATION_IMPORT1;



