CREATE OR REPLACE FUNCTION OPENACCESS."R_DELETE_ALL_GS_ES_MR" 
(
  V_SERVICE_NUMBER IN VARCHAR2 
) RETURN VARCHAR2 AS 
--@Author : Rini
--After this function successfully runs, DELETE_MASTER_WITH_SER_NUMBER can be run
v_process varchar2(50):='R_DELETE_ALL_GS_ES_MR';
v_process_type varchar2(50):='FUNCTION';
v_result_code  varchar2(50):='FAILURE';
v_result_desc  varchar2(300):='';
v_log_result   varchar2(300):='';
v_master_cursor sys_refcursor ;
--service_num varchar2(100);

v_gen_stmt_count varchar2(50);
v_mr_hdr_count varchar2(50); 

v_seller_company_service_id varchar2(50);
v_meter_id varchar2(50);
v_mr_hdr_id varchar2(50);
v_gen_stmt_id varchar2(50);
v_es_id varchar2(50);
v_es_order_id varchar2(50);
v_m_company_id varchar2(50);


v_gen_st_cursor sys_refcursor;
v_es_cursor sys_refcursor;
v_es_order_cursor sys_refcursor;
v_mr_hdr_cursor sys_refcursor;
BEGIN
BEGIN    
    v_log_result := log_activity(v_process_type,v_process,'Start','','','', '',V_SERVICE_NUMBER);

        SELECT ID,m_company_id INTO v_seller_company_service_id ,v_m_company_id FROM M_COMPANY_SERVICE WHERE "number"=V_SERVICE_NUMBER;
     ----Deletion of GS & ES starts here---  
select count(*) into  v_gen_stmt_count from t_gen_stmt where disp_service_number=V_SERVICE_NUMBER;

      IF(v_gen_stmt_count>0) THEN
      OPEN v_gen_st_cursor for select ID from t_gen_stmt where disp_service_number= V_SERVICE_NUMBER;
        LOOP 
        FETCH v_gen_st_cursor INTO v_gen_stmt_id;
--         v_log_result := log_activity(v_process_type,v_process,'DEL GS-gs-id',v_gen_stmt_id,'','', '',V_SERVICE_NUMBER);

        EXIT WHEN v_gen_st_cursor%NOTFOUND;
            OPEN v_es_cursor for select id from t_energy_sale where t_Gen_stmt_id =v_gen_stmt_id;
            LOOP 
            FETCH v_es_cursor INTO v_es_id;
            EXIT WHEN v_es_cursor%NOTFOUND;
--              v_log_result := log_activity(v_process_type,v_process,'DEL ES-es-id',v_es_id,'','', '',V_SERVICE_NUMBER);

            delete from T_ES_USAGE_SUMMARY where t_energy_sale_id = v_es_id;
            delete from t_es_charge where t_energy_sale_id = v_es_id;
               OPEN v_es_order_cursor for SELECT id from f_energy_sale_order where t_energy_sale_id =v_es_id;
                LOOP 
                FETCH v_es_order_cursor INTO v_es_order_id;
                EXIT WHEN v_es_order_cursor%NOTFOUND;
                delete  from f_energy_ledger where f_energy_sale_order_id =  v_es_order_id;
                delete from f_energy_sale_order_lines where f_energy_sale_order_id =  v_es_order_id;
                delete from f_energy_Charges where f_energy_sale_order_id =  v_es_order_id;
                END LOOP;
             delete from f_energy_sale_order where t_energy_sale_id =v_es_id;
            END LOOP;
            delete from t_energy_sale where t_Gen_stmt_id =v_gen_stmt_id;
            delete from T_GEN_STMT_CHARGE where t_gen_stmt_id =v_gen_stmt_id;
            delete from t_gen_stmt_slot where t_gen_stmt_id =v_gen_stmt_id;  


        END LOOP;
        delete from t_gen_stmt where disp_service_number=V_SERVICE_NUMBER;
          v_log_result := log_activity(v_process_type,v_process,'GS-DELETION','GS DELETED','SUCCESS','', '',V_SERVICE_NUMBER);
        ELSE
        v_log_result := log_activity(v_process_type,v_process,'GS-check','GS DOES NOT EXIST','','', '',V_SERVICE_NUMBER);

    END IF;    

        ----GS & ES have been deleted---

        select id into v_meter_id from m_company_meter where m_company_service_id=v_seller_company_service_id;
        ------METER READING HEADER AND METER READING SLOT DATA STARTS--
       select count(*) into  v_mr_hdr_count from t_meter_reading_hdr where  m_company_meter_id=v_meter_id;
      IF(v_mr_hdr_count>0) THEN
         OPEN v_mr_hdr_cursor for  select id from t_meter_reading_hdr where  m_company_meter_id=v_meter_id;
            LOOP 
            FETCH v_mr_hdr_cursor INTO v_mr_hdr_id;
            EXIT WHEN v_mr_hdr_cursor%NOTFOUND;
             delete From t_meter_reading_slot where T_METER_READING_HDR_ID = v_mr_hdr_id;
            END LOOP;
            delete from t_meter_reading_hdr where  m_company_meter_id=v_meter_id;
               v_log_result := log_activity(v_process_type,v_process,'MR_DELETION','MR DELETED','SUCCESS','', '',V_SERVICE_NUMBER);
            ELSE
        v_log_result := log_activity(v_process_type,v_process,'MR_HDR_CHECK','MR DOES NOT EXIST','','', '',V_SERVICE_NUMBER);

    END IF;        
            ------METER READING HEADER AND METER READING SLOT DATA DELETED--

          delete From t_banking_balance where m_company_id=v_m_company_id;
          delete from t_new_service_excess_banking where m_Company_id=v_m_company_id;


    COMMIT;
    v_result_code :='SUCCESS';
    v_result_desc := '';

    v_log_result := log_activity(v_process_type,v_process,'Complete','calling service refresh',v_result_code,'', '',V_SERVICE_NUMBER);

    view_refresh.COMP_SERV_REFRESH(V_SERVICE_NUMBER,v_result_code, v_result_desc);


exception
  when others then
    v_result_code := 'FAILURE';
    v_result_desc := SQLCODE || ' - ' || SUBSTR(SQLERRM, 1, 200);
    v_log_result := log_activity(v_process_type,v_process,'EH','Exception - '||v_result_desc,v_result_code,v_result_desc, sysdate,V_SERVICE_NUMBER);
END;
<<THE_END>>
    v_log_result := log_activity(v_process_type,v_process,'End',v_result_desc,v_result_code,'', '',V_SERVICE_NUMBER);
  RETURN 'success';
END R_DELETE_ALL_GS_ES_MR;