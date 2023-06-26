CREATE OR REPLACE FUNCTION OPENACCESS."UPDATE_BUYER_VOLTGE" RETURN VARCHAR2 AS 
v_serviceView_cur sys_refcursor ;
v_voltage_code varchar2(50);
service_number varchar2(50);
master_service_count varchar2(50);
v_serviceMasView SERVICEMASVIEW%ROWTYPE;
--------------
v_log_result  varchar2(50);
v_created_by varchar2(100):='Admin';
v_result_code varchar2(100);
v_result_desc varchar2(300);
v_reason varchar2(200):='';
v_exception_code  NUMBER;
v_exception_msg  VARCHAR2(200);
v_result VARCHAR(200):='SUCCESS';
v_error_message  VARCHAR2(200);

BEGIN

--To run this procedure
-- 1. Get ht SERVICE_NO,VOLTAGE,CONSUMER_NAME data in excel fromat from HT
-- 2. Upload the excel in SERVICEMASVIEW
-- 3. run this fumction
-- 4. Check for errors in activity log

    BEGIN --Exception starts here
    v_log_result := log_activity('PROCEDURE','UPDATE_BUYER_VOLTGE','START','UPDATING STARTS','START',v_created_by, sysdate,'');


    OPEN v_serviceView_cur for select * from SERVICEMASVIEW;
    LOOP 

    BEGIN --Exception starts here
    v_log_result := log_activity('PROCEDURE','-in loop','START','UPDATING STARTS','START',v_created_by, sysdate,'');
    FETCH v_serviceView_cur INTO v_serviceMasView;
    EXIT WHEN v_serviceView_cur%NOTFOUND;

    select codes.value_code into v_voltage_code from servicemasview ser left join v_codes codes on ser.voltage=codes.value_desc and codes.list_Code='VOLTAGE_CODE' where ser.SERVICE_NO=v_serviceMasView.SERVICE_NO;
    update SERVICEMASVIEW s set s.VOLTAGE_CODE=v_voltage_code where s.SERVICE_NO=v_serviceMasView.SERVICE_NO;    
    COMMIT;


    select count(*) into master_service_count from M_COMPANY_SERVICE where "number"=v_serviceMasView.SERVICE_NO;

    if(master_service_count=1)then
    update M_COMPANY_SERVICE set VOLTAGE_CODE=v_voltage_code where "number"=v_serviceMasView.SERVICE_NO;
    update m_company set name=v_serviceMasView.CONSUMER_NAME where id=(select m_company_id from m_company_service where "number"=v_serviceMasView.SERVICE_NO);
    v_error_message:='service exits and will be updated';

    ELSIF(master_service_count > 1)then
    v_error_message:='service duplicate exits';
    ELSIF(master_service_count=0)then
    v_error_message:='service does not exist';
    end if;

    EXCEPTION
      WHEN OTHERS THEN 
        v_exception_code := SQLCODE;
        v_exception_msg := SUBSTR(SQLERRM, 1, 200);
        v_result := 'FAILURE';
        v_result_desc := v_exception_code || ' - ' || v_exception_msg;
      END;
            v_log_result := log_activity('PROCEDURE','end loop-',v_error_message||'-'||master_service_count,v_result_code,v_result_desc,v_created_by, sysdate,v_serviceMasView.SERVICE_NO);

    END LOOP;

    EXCEPTION
      WHEN OTHERS THEN 
        v_exception_code := SQLCODE;
        v_exception_msg := SUBSTR(SQLERRM, 1, 200);
        v_result := 'FAILURE';
        v_result_desc := v_exception_code || ' - ' || v_exception_msg;
      END;
    COMMIT;
      v_log_result := log_activity('PROCEDURE','UPDATE_BUYER_VOLTGE','RESULT',v_result_code,v_result_desc,v_created_by, sysdate,'');
  RETURN 'success';
END UPDATE_BUYER_VOLTGE;

