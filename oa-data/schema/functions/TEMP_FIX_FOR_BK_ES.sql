CREATE OR REPLACE FUNCTION OPENACCESS."TEMP_FIX_FOR_BK_ES" 
(
  I_SERVICE_NUMBER IN VARCHAR2

) RETURN VARCHAR2 AS 
v_company_servicess V_COMPANY_SERVICE%ROWTYPE;
v_banking_count number:=0;
v_created_Date DATE := SYSDATE;
o_result_code VARCHAR2(100);
o_result_desc VARCHAR2(100);
v_es_id VARCHAR2(100);
v_es_count number:=0;
v_c1 VARCHAR2(100);v_c2 VARCHAR2(100);
v_c3 VARCHAR2(100);v_c4 VARCHAR2(100);
v_c5 VARCHAR2(100);

BEGIN
SELECT * INTO v_company_servicess FROM V_COMPANY_SERVICE WHERE "number"=I_SERVICE_NUMBER;
SELECT COUNT(*) INTO v_banking_count FROM T_BANKING_BALANCE WHERE M_COMPANY_ID=v_company_servicess.M_COMPANY_ID AND MONTH='03';
IF v_banking_count=1 THEN 
DELETE FROM T_BANKING_BALANCE WHERE M_COMPANY_ID=v_company_servicess.M_COMPANY_ID AND MONTH='03';
SELECT C1,C2,C3,C4,C5 INTO v_c1,v_c2,v_c3,v_c4,v_c5 FROM T_BANKING_BALANCE WHERE M_COMPANY_ID=v_company_servicess.M_COMPANY_ID AND MONTH='02';
UPDATE T_BANKING_BALANCE SET curr_c1=v_c1,curr_c2=v_c2,curr_c3=v_c3,curr_c4=v_c4,curr_c5=v_c5 WHERE M_COMPANY_ID=v_company_servicess.M_COMPANY_ID AND MONTH='02';
DELETE_TXN.DELETE_BY_SERVICE('TEMP_FIX_FOR_BK_ES'||v_created_Date,I_SERVICE_NUMBER,'03','2019','Y','Y','Y','N',o_result_code,o_result_desc);
SELECT count(*) INTO v_es_count FROM T_ENERGY_SALE WHERE SELLER_COMP_SERV_ID=v_company_servicess.ID AND MONTH='02';

IF v_es_count=1 THEN
SELECT ID INTO v_es_id FROM T_ENERGY_SALE WHERE SELLER_COMP_SERV_ID=v_company_servicess.ID AND MONTH='02';
BANKING_BALANCE.confirm_energy_sale_event(v_es_id,o_result_code,o_result_desc);
END IF;
END IF;
RETURN 'SUCCESS';
END TEMP_FIX_FOR_BK_ES;

