CREATE OR REPLACE PROCEDURE OPENACCESS."TEMP_MIGRATION_FILL_IDS" is
BEGIN

/*
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



*/


update temp_technical_master set is_enabled = 'Y', remarks='beta data-migration' where m_company_id is not null and length(wg_meterno) > 3;

FOR i IN (select m_company_id from temp_technical_master where  m_company_id is not null and is_enabled = 'Y')
LOOP
	UPDATE TEMP_COMMERCIAL_MASTER SET is_enabled = 'Y', remarks='beta data-migration' WHERE m_company_id= i.m_company_id ;
END LOOP;
COMMIT;


END TEMP_MIGRATION_FILL_IDS;


