CREATE OR REPLACE FUNCTION OPENACCESS."PROGRESS_REPORT_RR" 
(
  V_MONTH IN VARCHAR2,
  V_YEAR IN VARCHAR2,
  V_TYPE IN VARCHAR2 DEFAULT 'OVERALL',
  V_ORG_ID IN VARCHAR2,
  V_FUEL_TYPE_CODE IN VARCHAR2
) RETURN VARCHAR2 AS

--v_STMT_MONTH VARCHAR2;
--v_STMT_YEAR VARCHAR2;
v_total_import_gen_IS_CAPTIVE NUMBER;
v_TOTAL_EXPORT_GEN_IS_CAPTIVE NUMBER;
v_NET_GENERATION_IS_CAPTIVE NUMBER;
v_total_import_gen_STB NUMBER;
v_TOTAL_EXPORT_GEN_STB NUMBER;
v_NET_GENERATION_STB NUMBER;
v_total_import_gen_THIRD_PARTY NUMBER;
v_TOTAL_EXPORT_GEN_THIRD_PARTY NUMBER;
v_NET_GENERATION_THIRD_PARTY NUMBER;
v_BANKED_UNITS NUMBER;
v_FUEL_NAME VARCHAR2(20);
--v_TOTAL_IMPORT_GEN NUMBER;
--v_TOTAL_EXPORT_GEN NUMBER;
--v_NET_GENERATION NUMBER;
--v_FLOW_TYPE_CODE NUMBER;
--v_STMT_MONTH NUMBER;
--v_STMT_YEAR NUMBER;

v_total_services NUMBER;
v_total_statement NUMBER;
v_captive_statement NUMBER;
v_stb_statement NUMBER;
v_weg_third_statement NUMBER;
v_valid_captive NUMBER;

v_valid_third_party NUMBER;
v_valid_stb NUMBER;

v_invalid_captive NUMBER;
v_invalid_third_party NUMBER;
v_invalid_stb NUMBER;

v_total_invalid NUMBER;

v_allotment_created NUMBER;
v_allotment_completed NUMBER;
v_mtr_read_dwnld NUMBER;
v_manual_reading NUMBER;
v_progress_count NUMBER;

v_log_result varchar(300):='SUCCESS';
v_exception_code VARCHAR2(150);
v_exception_msg  VARCHAR2(150);
v_reason VARCHAR2(300);
o_result_desc VARCHAR2(300);
o_result_code VARCHAR2(300);



BEGIN
BEGIN
    v_log_result := log_activity('FUNCTION','PROGRESS_REPORT_RR','START',V_MONTH||'-'||V_YEAR,'','', sysdate,'');


  -----------------------------------------------------------------------------------------------------------------------  
    IF V_TYPE='OVERALL' AND V_ORG_ID IS NULL THEN


        SELECT COUNT(*) INTO v_total_services FROM m_company_service WHERE comp_ser_type_code='03' AND m_org_id in ('432','434','439','450','470','472','474','476','478') and FUEL_TYPE_CODE=V_FUEL_TYPE_CODE;

        SELECT COUNT(*) INTO v_total_statement FROM t_gen_stmt WHERE STMT_MONTH=V_MONTH AND STMT_YEAR=V_YEAR and DISP_FUEL_TYPE_CODE=V_FUEL_TYPE_CODE;

        SELECT COUNT(*) INTO v_captive_statement FROM t_gen_stmt WHERE IS_CAPTIVE='Y' AND STMT_MONTH=V_MONTH AND STMT_YEAR=V_YEAR and DISP_FUEL_TYPE_CODE=V_FUEL_TYPE_CODE;

        SELECT COUNT(*) INTO v_stb_statement FROM t_gen_stmt WHERE IS_STB='Y' AND STMT_MONTH=V_MONTH AND STMT_YEAR=V_YEAR and DISP_FUEL_TYPE_CODE=V_FUEL_TYPE_CODE;

        SELECT COUNT(*) INTO v_weg_third_statement FROM t_gen_stmt WHERE FLOW_TYPE_CODE='THIRD-PARTY' AND STMT_MONTH=V_MONTH AND STMT_YEAR=V_YEAR and DISP_FUEL_TYPE_CODE=V_FUEL_TYPE_CODE;

        SELECT COUNT(*) INTO v_allotment_created FROM t_energy_sale WHERE STATUS_CODE='CREATED' AND MONTH=V_MONTH AND YEAR=V_YEAR and T_GEN_STMT_ID in (select id from t_gen_stmt where DISP_FUEL_TYPE_CODE=V_FUEL_TYPE_CODE);

        SELECT COUNT(*) INTO v_allotment_completed FROM t_energy_sale WHERE STATUS_CODE='APPROVED' AND MONTH=V_MONTH AND YEAR=V_YEAR and T_GEN_STMT_ID in (select id from t_gen_stmt where DISP_FUEL_TYPE_CODE=V_FUEL_TYPE_CODE);

        SELECT COUNT(*) INTO v_mtr_read_dwnld FROM t_meter_reading_hdr hd LEFT JOIN imp_mr_header hr on hr.id=hd.imp_batch_id left join t_gen_stmt gs on hd.M_GEN_STMT_ID=gs.id 
        where hd.reading_month=V_MONTH AND hd.reading_year=V_YEAR AND hr.mr_source_code='01' and gs.DISP_FUEL_TYPE_CODE=V_FUEL_TYPE_CODE;

        SELECT COUNT(*) INTO v_manual_reading FROM t_meter_reading_hdr hd LEFT JOIN imp_mr_header hr on hr.id=hd.imp_batch_id left join t_gen_stmt gs on hd.M_GEN_STMT_ID=gs.id
        where hd.reading_month=V_MONTH AND hd.reading_year=V_YEAR AND hr.mr_source_code='02' and gs.DISP_FUEL_TYPE_CODE=V_FUEL_TYPE_CODE; 

        SELECT COUNT(*) INTO v_progress_count FROM PROGRESS_REPORT WHERE MONTH=V_MONTH AND YEAR=V_YEAR AND TYPE=V_TYPE and FUEL_TYPE_CODE=V_FUEL_TYPE_CODE;


        SELECT COUNT(*) INTO v_valid_captive FROM v_company_service cs LEFT JOIN m_powerplant ts on ts.M_SERVICE_ID = cs.id WHERE  cs.flow_type_code= 'IS-CAPTIVE' AND ts.status='LIVE' and cs.FUEL_TYPE_CODE=V_FUEL_TYPE_CODE; 
        SELECT COUNT(*) INTO v_invalid_captive FROM v_company_service cs LEFT JOIN m_powerplant ts on ts.M_SERVICE_ID = cs.id WHERE cs.flow_type_code= 'IS-CAPTIVE' AND ts.status!='LIVE' and cs.FUEL_TYPE_CODE=V_FUEL_TYPE_CODE;

        SELECT COUNT(*) INTO v_valid_third_party FROM v_company_service cs LEFT JOIN m_powerplant ts on ts.M_SERVICE_ID = cs.id WHERE cs.flow_type_code= 'THIRD-PARTY' AND ts.status='LIVE' and cs.FUEL_TYPE_CODE=V_FUEL_TYPE_CODE; 
        SELECT COUNT(*) INTO v_invalid_third_party FROM v_company_service cs LEFT JOIN m_powerplant ts on ts.M_SERVICE_ID = cs.id WHERE cs.flow_type_code= 'THIRD-PARTY' AND ts.status!='LIVE' and cs.FUEL_TYPE_CODE=V_FUEL_TYPE_CODE;

        SELECT COUNT(*) INTO v_valid_stb FROM v_company_service cs LEFT JOIN m_powerplant ts on ts.M_SERVICE_ID = cs.id WHERE cs.flow_type_code= 'STB' AND ts.status='LIVE' and cs.FUEL_TYPE_CODE=V_FUEL_TYPE_CODE; 
        SELECT COUNT(*) INTO v_invalid_stb FROM v_company_service cs LEFT JOIN m_powerplant ts on ts.M_SERVICE_ID = cs.id WHERE cs.flow_type_code= 'STB' AND ts.status!='LIVE' and cs.FUEL_TYPE_CODE=V_FUEL_TYPE_CODE;

        select COUNT(*) INTO v_total_invalid FROM m_powerplant WHERE status!='LIVE' and FUEL_TYPE_CODE=V_FUEL_TYPE_CODE; 

       select sum(TOTAL_IMPORT_GEN),sum(TOTAL_EXPORT_GEN),sum(NET_GENERATION) into v_total_import_gen_IS_CAPTIVE,v_TOTAL_EXPORT_GEN_IS_CAPTIVE,v_NET_GENERATION_IS_CAPTIVE  from t_gen_stmt where FLOW_TYPE_CODE= 'IS-CAPTIVE'and STMT_MONTH=V_MONTH and STMT_YEAR=V_YEAR and DISP_FUEL_TYPE_CODE=V_FUEL_TYPE_CODE;
       select sum(TOTAL_IMPORT_GEN),sum(TOTAL_EXPORT_GEN),sum(NET_GENERATION) into v_total_import_gen_STB,v_TOTAL_EXPORT_GEN_STB,v_NET_GENERATION_STB from t_gen_stmt where FLOW_TYPE_CODE= 'STB' and STMT_MONTH=V_MONTH and STMT_YEAR=V_YEAR and DISP_FUEL_TYPE_CODE=V_FUEL_TYPE_CODE;
       select sum(TOTAL_IMPORT_GEN),sum(TOTAL_EXPORT_GEN),sum(NET_GENERATION) into v_total_import_gen_THIRD_PARTY,v_TOTAL_EXPORT_GEN_THIRD_PARTY,v_NET_GENERATION_THIRD_PARTY from t_gen_stmt where FLOW_TYPE_CODE= 'THIRD-PARTY' and STMT_MONTH=V_MONTH and STMT_YEAR=V_YEAR and DISP_FUEL_TYPE_CODE=V_FUEL_TYPE_CODE;


        select FUEL_NAME into v_FUEL_NAME from m_fuel where FUEL_CODE =V_FUEL_TYPE_CODE;

--       select round(sum(BANKED_BALANCE),2) into v_BANKED_UNITS from t_gen_stmt_slot where T_GEN_STMT_ID in (select id from t_gen_stmt where DISP_FUEL_TYPE_CODE=V_FUEL_TYPE_CODE);

       select to_char(sum(cast(regexp_replace(BANKED_BALANCE,'[^0-9]+','') as number))) into v_BANKED_UNITS from t_gen_stmt_slot where T_GEN_STMT_ID in (select id from t_gen_stmt where DISP_FUEL_TYPE_CODE=V_FUEL_TYPE_CODE and STMT_MONTH=V_MONTH and STMT_YEAR=V_YEAR);


        IF v_progress_count=0 THEN

        INSERT INTO PROGRESS_REPORT
        (ID,TOTAL_SERVICES,VALID_CAPTIVE_SERVICES,VALID_STB_SERVICES,VALID_WEG_THIRD_PARTY_SERVICES,TOTAL_INVALID_SERVICES,INVALID_CAPTIVE_SERVICES,INVALID_STB_SERVICES,
        INVALID_WEG_THIRD_PARTY_SERVICES,TOTAL_STATEMENTS,CAPTIVE_STATEMENTS,STB_STATEMENTS,WEG_THIRD_PARTY_STATEMENTS,ALLOTMENTS_CREATED,ALLOTMENTS_COMPLETED,METER_READINGS_DOWNLOADED,
        MANUAL_MR_READING,CREATED_DATE,MONTH,YEAR,TYPE,M_ORG_ID,TOTAL_IMPORT_IS_CAPTIVE,TOTAL_EXPORT_IS_CAPTIVE,NET_GENERATION_IS_CAPTIVE,TOTAL_IMPORT_STB,TOTAL_EXPORT_STB,NET_GENERATION_STB,
        TOTAL_IMPORT_THIRD_PARTY,TOTAL_EXPORT_THIRD_PARTY,NET_GENERATION_THIRD_PARTY,FUEL_TYPE_CODE,FUEL_TYPE_NAME,BANKED_UNITS) 
        VALUES (PROGRESS_REPORT_RR_SEQ.NEXTVAL, v_total_services ,v_valid_captive,v_valid_stb,v_valid_third_party,v_total_invalid,v_invalid_captive,v_invalid_stb,v_invalid_third_party, v_total_statement, v_captive_statement, v_stb_statement, v_weg_third_statement,
        v_allotment_created, v_allotment_completed, v_mtr_read_dwnld,v_manual_reading,sysdate,V_MONTH, V_YEAR, V_TYPE,V_ORG_ID,v_total_import_gen_IS_CAPTIVE,v_TOTAL_EXPORT_GEN_IS_CAPTIVE,v_NET_GENERATION_IS_CAPTIVE,
        v_total_import_gen_STB,v_TOTAL_EXPORT_GEN_STB,v_NET_GENERATION_STB,v_total_import_gen_THIRD_PARTY,v_TOTAL_EXPORT_GEN_THIRD_PARTY,v_NET_GENERATION_THIRD_PARTY,V_FUEL_TYPE_CODE,v_FUEL_NAME,v_BANKED_UNITS);

        ELSIF v_progress_count=1 THEN
        UPDATE PROGRESS_REPORT
        SET TOTAL_SERVICES=v_total_services, VALID_CAPTIVE_SERVICES=v_valid_captive, VALID_STB_SERVICES=v_valid_stb, VALID_WEG_THIRD_PARTY_SERVICES=v_valid_third_party, TOTAL_INVALID_SERVICES=v_total_invalid, INVALID_CAPTIVE_SERVICES=v_invalid_captive,
        INVALID_STB_SERVICES=v_invalid_stb, INVALID_WEG_THIRD_PARTY_SERVICES=v_invalid_third_party, TOTAL_STATEMENTS=v_total_statement, CAPTIVE_STATEMENTS=v_captive_statement, STB_STATEMENTS=v_stb_statement,
        WEG_THIRD_PARTY_STATEMENTS=v_weg_third_statement, ALLOTMENTS_CREATED=v_allotment_created,
        ALLOTMENTS_COMPLETED=v_allotment_completed, METER_READINGS_DOWNLOADED=v_mtr_read_dwnld, MANUAL_MR_READING=v_manual_reading,MODIFIED_DATE=SYSDATE, "MONTH"=V_MONTH, "YEAR"=V_YEAR, TYPE=V_TYPE,
         TOTAL_IMPORT_IS_CAPTIVE=v_total_import_gen_IS_CAPTIVE,TOTAL_EXPORT_IS_CAPTIVE=v_TOTAL_EXPORT_GEN_IS_CAPTIVE,
        NET_GENERATION_IS_CAPTIVE=v_NET_GENERATION_IS_CAPTIVE,TOTAL_IMPORT_STB=v_total_import_gen_STB,TOTAL_EXPORT_STB=v_TOTAL_EXPORT_GEN_STB,
        NET_GENERATION_STB=v_NET_GENERATION_STB,TOTAL_IMPORT_THIRD_PARTY=v_total_import_gen_THIRD_PARTY,TOTAL_EXPORT_THIRD_PARTY=v_TOTAL_EXPORT_GEN_THIRD_PARTY,
        NET_GENERATION_THIRD_PARTY=v_NET_GENERATION_THIRD_PARTY,FUEL_TYPE_CODE=V_FUEL_TYPE_CODE,FUEL_TYPE_NAME=v_FUEL_NAME,BANKED_UNITS=v_BANKED_UNITS
        WHERE "MONTH"=V_MONTH AND "YEAR"=V_YEAR AND TYPE=V_TYPE and FUEL_TYPE_CODE=V_FUEL_TYPE_CODE;
        END IF;
        COMMIT;

    END IF;    


    -----------------------------------------------------------------------------------------------------------------------
    IF V_TYPE='EDC' AND V_ORG_ID IS NOT NULL THEN

        SELECT COUNT(*) INTO v_total_services FROM m_company_service WHERE comp_ser_type_code='03' AND m_org_id = V_ORG_ID;
        SELECT COUNT(*) INTO v_total_statement FROM t_gen_stmt WHERE STMT_MONTH=V_MONTH AND STMT_YEAR=V_YEAR and M_ORG_ID=V_ORG_ID;
        SELECT COUNT(*) INTO v_captive_statement FROM t_gen_stmt WHERE IS_CAPTIVE='Y' AND STMT_MONTH=V_MONTH AND STMT_YEAR=V_YEAR and M_ORG_ID=V_ORG_ID;
        SELECT COUNT(*) INTO v_stb_statement FROM t_gen_stmt WHERE IS_STB='Y' AND  STMT_MONTH=V_MONTH AND STMT_YEAR=V_YEAR and M_ORG_ID=V_ORG_ID;          
        SELECT COUNT(*) INTO v_weg_third_statement FROM t_gen_stmt WHERE FLOW_TYPE_CODE='WEG-THIRD-PARTY' AND STMT_MONTH=V_MONTH AND STMT_YEAR=V_YEAR and M_ORG_ID=V_ORG_ID;
        SELECT COUNT(*) INTO v_allotment_created FROM t_energy_sale WHERE STATUS_CODE='CREATED' AND MONTH=V_MONTH AND YEAR=V_YEAR AND SELLER_END_ORG_ID=V_ORG_ID;
        SELECT COUNT(*) INTO v_allotment_completed FROM t_energy_sale WHERE STATUS_CODE='APPROVED' AND MONTH=V_MONTH AND YEAR=V_YEAR AND SELLER_END_ORG_ID=V_ORG_ID;        


        SELECT COUNT(*) INTO v_mtr_read_dwnld
        FROM t_meter_reading_hdr hd 
        LEFT JOIN imp_mr_header hr on hr.id=hd.imp_batch_id
        LEFT JOIN V_COMPANY_SERVICE s on hd.M_COMPANY_METER_ID=s.M_COMPANY_METER_ID
        where hd.reading_month=V_MONTH AND hd.reading_year=V_YEAR AND hr.mr_source_code='01' AND s.M_ORG_ID=V_ORG_ID;      

        SELECT COUNT(*) INTO v_manual_reading
        FROM t_meter_reading_hdr hd
        LEFT JOIN imp_mr_header hr on hr.id=hd.imp_batch_id
        LEFT JOIN V_COMPANY_SERVICE s on hd.M_COMPANY_METER_ID=s.M_COMPANY_METER_ID
        where hd.reading_month=V_MONTH AND hd.reading_year=V_YEAR AND hr.mr_source_code='02' and s.M_ORG_ID=V_ORG_ID; 

        SELECT COUNT(*) INTO v_progress_count FROM PROGRESS_REPORT WHERE MONTH=V_MONTH AND YEAR=V_YEAR AND TYPE=V_TYPE AND M_ORG_ID=V_ORG_ID;

        SELECT COUNT(*) INTO v_valid_captive
        FROM v_company_service cs
        LEFT JOIN m_powerplant ts on ts.M_SERVICE_ID = cs.id 
        WHERE  cs.flow_type_code= 'IS-CAPTIVE' AND ts.status='LIVE' and cs.M_ORG_ID=V_ORG_ID; 

        SELECT COUNT(*) INTO v_invalid_captive
        FROM v_company_service cs 
        LEFT JOIN m_powerplant ts on ts.M_SERVICE_ID = cs.id 
        WHERE cs.flow_type_code= 'IS-CAPTIVE' AND ts.status!='LIVE' and cs.M_ORG_ID=V_ORG_ID; 

        SELECT COUNT(*) INTO v_valid_third_party
        FROM v_company_service cs 
        LEFT JOIN m_powerplant ts on ts.M_SERVICE_ID = cs.id
        WHERE cs.flow_type_code= 'WEG-THIRD-PARTY' AND ts.status='LIVE' and cs.M_ORG_ID=V_ORG_ID; 


        SELECT COUNT(*) INTO v_invalid_third_party
        FROM v_company_service cs 
        LEFT JOIN m_powerplant ts on ts.M_SERVICE_ID = cs.id 
        WHERE cs.flow_type_code= 'WEG-THIRD-PARTY' AND ts.status!='LIVE' and cs.M_ORG_ID=V_ORG_ID; 



        SELECT COUNT(*) INTO v_valid_stb
        FROM v_company_service cs 
        LEFT JOIN m_powerplant ts on ts.M_SERVICE_ID = cs.id 
        WHERE cs.flow_type_code= 'STB' AND ts.status='LIVE' and cs.M_ORG_ID=V_ORG_ID;


        SELECT COUNT(*) INTO v_invalid_stb
        FROM v_company_service cs 
        LEFT JOIN m_powerplant ts on ts.M_SERVICE_ID = cs.id
        WHERE cs.flow_type_code= 'STB' AND ts.status!='LIVE' and cs.M_ORG_ID=V_ORG_ID;

        select COUNT(*) INTO v_total_invalid FROM m_powerplant WHERE status!='LIVE' and M_ORG_ID=V_ORG_ID;


        IF v_progress_count=0 THEN
        INSERT INTO PROGRESS_REPORT
        (ID,TOTAL_SERVICES,VALID_CAPTIVE_SERVICES,VALID_STB_SERVICES,VALID_WEG_THIRD_PARTY_SERVICES,TOTAL_INVALID_SERVICES,INVALID_CAPTIVE_SERVICES,INVALID_STB_SERVICES,
        INVALID_WEG_THIRD_PARTY_SERVICES,TOTAL_STATEMENTS,CAPTIVE_STATEMENTS,STB_STATEMENTS,WEG_THIRD_PARTY_STATEMENTS,ALLOTMENTS_CREATED,ALLOTMENTS_COMPLETED,METER_READINGS_DOWNLOADED,
        MANUAL_MR_READING,CREATED_DATE,MONTH,YEAR,TYPE) 
        VALUES (PROGRESS_REPORT_RR_SEQ.NEXTVAL, v_total_services ,v_valid_captive,v_valid_stb,v_valid_third_party,v_total_invalid,v_invalid_captive,v_invalid_stb,v_invalid_third_party, v_total_statement, v_captive_statement, v_stb_statement, v_weg_third_statement,
        v_allotment_created, v_allotment_completed, v_mtr_read_dwnld,v_manual_reading,sysdate,V_MONTH, V_YEAR,V_TYPE);

        ELSIF v_progress_count=1 THEN
        UPDATE PROGRESS_REPORT
        SET TOTAL_SERVICES=v_total_services, VALID_CAPTIVE_SERVICES=v_valid_captive, VALID_STB_SERVICES=v_valid_stb, VALID_WEG_THIRD_PARTY_SERVICES=v_valid_third_party, TOTAL_INVALID_SERVICES=v_total_invalid, INVALID_CAPTIVE_SERVICES=v_invalid_captive,
        INVALID_STB_SERVICES=v_invalid_stb, INVALID_WEG_THIRD_PARTY_SERVICES=v_invalid_third_party, TOTAL_STATEMENTS=v_total_statement, CAPTIVE_STATEMENTS=v_captive_statement, STB_STATEMENTS=v_stb_statement,
        WEG_THIRD_PARTY_STATEMENTS=v_weg_third_statement, ALLOTMENTS_CREATED=v_allotment_created,
        ALLOTMENTS_COMPLETED=v_allotment_completed, METER_READINGS_DOWNLOADED=v_mtr_read_dwnld, MANUAL_MR_READING=v_manual_reading,MODIFIED_DATE=SYSDATE, "MONTH"=V_MONTH, "YEAR"=V_YEAR, TYPE=V_TYPE, M_ORG_ID=V_ORG_ID
        WHERE "MONTH"=V_MONTH AND "YEAR"=V_YEAR AND TYPE=V_TYPE AND M_ORG_ID=V_ORG_ID;
        END IF;
        COMMIT;


    END IF;
   -----------------------------------------------------------------------------------------------------------------------
    exception
    when others then
        v_exception_code := SQLCODE;
        v_exception_msg := SUBSTR(SQLERRM, 1, 200);
        o_result_code := 'FAILURE';
        o_result_desc := v_exception_code || ' - ' || v_exception_msg;
        v_log_result := log_activity('FUNCTION','PROGRESS_REPORT_RR','EXCEPTION',o_result_desc,V_MONTH||'-'||V_YEAR,'', sysdate,'');
       END;
    COMMIT;
        v_log_result := log_activity('FUNCTION','PROGRESS_REPORT_RR','END',V_MONTH||'-'||V_YEAR,'','', sysdate,'');   


         RETURN 'SUCCESS';

END PROGRESS_REPORT_RR;

