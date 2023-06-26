CREATE OR REPLACE FUNCTION OPENACCESS."DELETE_COMMERCIALS" 
(
  V_SERVICE_NUMBER IN VARCHAR2,
    V_REMARKS IN VARCHAR2 

) RETURN VARCHAR2 AS

v_seller_company_service_id varchar2(50);
v_f_agreement_id varchar2(50);
v_f_agreement_count varchar2(50);
v_t_oaa_count varchar2(50);
v_ewa_id varchar2(50);
v_ewa_count varchar2(50);
v_epa_id varchar2(50);
v_epa_count varchar2(50);
v_agreement_cursor sys_refcursor ;
v_ewa_cursor sys_refcursor ;
v_created_by varchar2(100):='Admin';
v_result VARCHAR(200):='SUCCESS';
v_result_code varchar2(100);
v_result_desc varchar2(300);
v_exception_code  NUMBER;
v_exception_msg  VARCHAR2(200);
v_log_result  varchar2(50);
v_trade_count varchar2(50);
v_company_ser_details v_company_service%rowtype;
v_company_ser_buyer_details v_company_service%rowtype;
UTILITYCHANGECOUNT NUMBER(10) :=0;
v_trade_details IMPORT_TRADE_REL%rowtype;


BEGIN

    BEGIN --EXCEPTION STARTS HERE
    v_log_result := log_activity('PROCEDURE','DELETE_COMMERCIALS','START','FOR SERVICE- '||V_SERVICE_NUMBER,'STARTED',v_created_by, sysdate,'');

	--Getting servive ID from service number
	SELECT ID INTO v_seller_company_service_id FROM M_COMPANY_SERVICE WHERE "number"=V_SERVICE_NUMBER;

    v_log_result := log_activity('PROCEDURE','DELETE_COMMERCIALS','001','FOR SERVICE- '||v_seller_company_service_id,'PROCESSING',v_created_by, sysdate,'');

	--Finding and deleting records

	--f_agreement and f_agreement_lines
	SELECT COUNT(*) INTO v_f_agreement_count FROM F_AGREEMENT WHERE SELLER_COMP_SERV_ID=v_seller_company_service_id;
	IF(v_f_agreement_count >= '1') THEN
--    -- dbms_output.put_line('AGREEMENT EXISTS FOR DELETION -' || v_f_agreement_count);
	OPEN v_agreement_cursor for SELECT ID FROM F_AGREEMENT WHERE SELLER_COMP_SERV_ID = v_seller_company_service_id;
    LOOP 
    FETCH v_agreement_cursor INTO v_f_agreement_id;
	EXIT WHEN v_agreement_cursor%NOTFOUND;
	DELETE FROM F_AGREEMENT_LINE WHERE F_AGREEMENT_ID=v_f_agreement_id;
	END LOOP;
	DELETE FROM F_AGREEMENT WHERE SELLER_COMP_SERV_ID = v_seller_company_service_id;
--    ELSE
--    -- dbms_output.put_line('Agreement EXISTS IS -' || v_f_agreement_count || '-NO DELETION');
	END IF;

    v_log_result := log_activity('PROCEDURE','DELETE_COMMERCIALS','002','FOR SERVICE- '||v_seller_company_service_id,'PROCESSING',v_created_by, sysdate,'');

	--t_oaa
	SELECT COUNT(*) INTO v_t_oaa_count FROM T_OAA WHERE SELLER_COMP_SERV_ID=v_seller_company_service_id;
	IF(v_t_oaa_count >='1') THEN 
--    -- dbms_output.put_line('OAA EXISTS FOR DELETION -' || v_t_oaa_count);
	DELETE FROM T_OAA WHERE SELLER_COMP_SERV_ID=v_seller_company_service_id;
--    ELSE
--    -- dbms_output.put_line('OAA EXISTS IS -' || v_t_oaa_count || '-NO DELETION');
	END IF;
    v_log_result := log_activity('PROCEDURE','DELETE_COMMERCIALS','003','FOR SERVICE- '||v_seller_company_service_id,'PROCESSING',v_created_by, sysdate,'');

	--t_ewa and t_ewa_line
	SELECT COUNT(*) INTO v_ewa_count FROM T_EWA WHERE SELLER_COMP_SERV_ID=v_seller_company_service_id;
	IF(v_ewa_count>='1') THEN
--    -- dbms_output.put_line('EWA EXISTS FOR DELETION -' || v_ewa_count);
    --EWA LOOP STARTS
    OPEN v_ewa_cursor FOR SELECT ID FROM T_EWA WHERE SELLER_COMP_SERV_ID=v_seller_company_service_id;
    LOOP
    FETCH v_ewa_cursor INTO v_ewa_id;
    EXIT WHEN v_ewa_cursor%NOTFOUND;
    DELETE FROM T_EWA_LINE WHERE T_EWA_ID=v_ewa_id;
    END LOOP;
	DELETE FROM T_EWA WHERE SELLER_COMP_SERV_ID=v_seller_company_service_id;
--    ELSE
--    -- dbms_output.put_line('EWA EXISTS IS -' || v_ewa_count || '-NO DELETION');
	END IF;
    v_log_result := log_activity('PROCEDURE','DELETE_COMMERCIALS','004','FOR SERVICE- '||v_seller_company_service_id,'PROCESSING',v_created_by, sysdate,'');

	--t_epa and t_epa_line
	SELECT COUNT(*) INTO v_epa_count FROM T_EPA WHERE SELLER_COMP_SERV_ID=v_seller_company_service_id;
	IF(v_epa_count>=1) THEN
--    -- dbms_output.put_line('EPA EXISTS FOR DELETION -' || v_epa_count);
    FOR epa IN (SELEct id FROM T_EPA WHERE SELLER_COMP_SERV_ID=v_seller_company_service_id)
    loop
        DELETE FROM T_EPA_LINES WHERE T_EPA_ID=epa.id;
        DELETE FROM T_EPA WHERE id = epa.id;
    end loop;
     
	
--    ELSE
--    -- dbms_output.put_line('EPA EXISTS IS -' || v_epa_count || '-NO DELETION');
	END IF;

        v_log_result := log_activity('PROCEDURE','DELETE_COMMERCIALS','005','FOR SERVICE- '||v_seller_company_service_id,'PROCESSING',v_created_by, sysdate,'');

    select count(*) into v_trade_count from M_TRADE_RELATIONSHIP where M_SELLER_COMP_SERVICE_ID=v_seller_company_service_id;
    if(v_trade_count>='1')THEN
    
    ----------------CODE WRITTEN FOR CAPTURING THE HISTORY OF DELETION------------------------
    SELECT * INTO v_company_ser_details FROM V_COMPANY_SERVICE vcs WHERE id=v_seller_company_service_id;
   -------------------TO GET DISTINCT DATA--------------------------------------------
   dbms_output.put_line('started');
   SELECT * INTO v_trade_details FROM IMPORT_TRADE_REL  WHERE IMPORT_REMARKS = V_REMARKS AND rownum=1;
   dbms_output.put_line('problem completed'||v_trade_details.FROM_DATE_STR||'--'||v_trade_details.FROM_DATE_STR);
----------------------------------------------------------------
   FOR i IN (SELECT * FROM M_TRADE_RELATIONSHIP mtr where 
   M_SELLER_COMP_SERVICE_ID=v_seller_company_service_id)LOOP

	   SELECT * INTO v_company_ser_buyer_details FROM V_COMPANY_SERVICE vcs WHERE 
	   id = i.M_BUYER_COMP_SERVICE_ID;
	  
	 SELECT count(*) INTO UTILITYCHANGECOUNT FROM M_TRADE_HIS  WHERE 
	 M_COMP_SERVICE_NO =v_company_ser_details."number"  AND 
     DELETED_BUYER_COMP_SERV_NUMBER = v_company_ser_buyer_details."number" 
     AND TRD_MONTH = CONCAT('0',EXTRACT (MONTH FROM TO_DATE(v_trade_details.FROM_DATE_STR,'DD/MM/YYYY')))
	 AND TRD_YEAR = EXTRACT (YEAR FROM TO_DATE(v_trade_details.FROM_DATE_STR,'DD/MM/YYYY'));
	
	IF UTILITYCHANGECOUNT = 0 THEN  
     INSERT INTO OPENACCESS.M_TRADE_HIS
     (M_COMPANY_SERV_ID, TRD_MONTH, TRD_YEAR, ADDED_BUYER_COM_SERV_NUMBER, 
     DELETED_BUYER_COMP_SERV_NUMBER, M_COMP_SERVICE_NO, ADDED_BUYER_NAME, ADDED_BUYER_SERV_NO, 
     DELETED_BUYER_NAME, DELETED_BUYER_SERV_NO)
     VALUES(v_company_ser_details.ID,CONCAT('0',EXTRACT (MONTH FROM TO_DATE(v_trade_details.FROM_DATE_STR,'DD/MM/YYYY'))), 
     CONCAT('0',EXTRACT (YEAR FROM TO_DATE(v_trade_details.FROM_DATE_STR,'DD/MM/YYYY'))) ,NULL,
     v_company_ser_buyer_details."number",v_company_ser_details."number",null,null,
     v_company_ser_buyer_details.M_COMPANY_NAME,v_company_ser_buyer_details.ID);
    
    VIEW_REFRESH_HISTORY.COMP_SERV_REFRESH_HISTORY_ALL(V_SERVICE_NUMBER,'N','N','Y','N'); 
     END IF;
    END LOOP;
   -------------------------------------END OF CODE FOR HISTORY CAPTURE----------------------
   
    delete from M_TRADE_RELATIONSHIP where M_SELLER_COMP_SERVICE_ID=v_seller_company_service_id;
    end if;
    v_log_result := log_activity('PROCEDURE','DELETE_COMMERCIALS','006','FOR SERVICE- '||v_seller_company_service_id,'PROCESSING',v_created_by, sysdate,'');
    v_log_result := log_activity('PROCEDURE','DELETE_COMMERCIALS','007','FOR SERVICE- '||V_SERVICE_NUMBER,'PROCESSING',v_created_by, sysdate,'');


    EXCEPTION
    WHEN OTHERS THEN 
        v_exception_code := SQLCODE;
        v_exception_msg := SUBSTR(SQLERRM, 1, 200);
        v_result := 'FAILURE';
        v_result_desc := v_exception_code || ' - ' || v_exception_msg;
        -- dbms_output.put_line(v_result_desc);
    END;
    <<THE_END>>
    COMMIT;
    v_log_result := log_activity('PROCEDURE','DELETE_COMMERCIALS',v_result,v_result_code,v_result_desc,v_created_by, sysdate,V_SERVICE_NUMBER);

 RETURN v_result;
END DELETE_COMMERCIALS;