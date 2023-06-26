CREATE OR REPLACE FUNCTION OPENACCESS."UPDATE_QUANTUM" RETURN VARCHAR2 AS 
v_trade_cursor sys_refcursor ;
service_id varchar2(50);
v_conversion number:=0.001;
share_prcent number:=100;

v_result number:=0;


BEGIN

	OPEN v_trade_cursor for select id from m_company_service where flow_type_code='WEG-THIRD-PARTY'; 
    LOOP 
    FETCH v_trade_cursor INTO service_id;
    select (ser.CAPACITY/(share_prcent)*100)*v_conversion into v_result from M_COMPANY_SERVICE ser where ser.id=service_id;
    update M_TRADE_RELATIONSHIP set QUANTUM=v_result where M_SELLER_COMP_SERVICE_ID=service_id;
	EXIT WHEN v_trade_cursor%NOTFOUND;

	END LOOP;
  RETURN NULL;
END UPDATE_QUANTUM;

