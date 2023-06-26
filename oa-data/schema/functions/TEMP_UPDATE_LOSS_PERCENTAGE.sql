CREATE OR REPLACE FUNCTION OPENACCESS."TEMP_UPDATE_LOSS_PERCENTAGE" RETURN VARCHAR2 AS 
v_cursor sys_refcursor;
v_f_eng_adj F_ENERGY_ADJUSTMET%ROWTYPE;
v_buyer_drw_code varchar2(50);
v_seller_inj_code varchar2(50);
v_loss_percent varchar2(50);

BEGIN
    OPEN v_cursor for select * from F_ENERGY_ADJUSTMET; 
    LOOP
    FETCH v_cursor INTO v_f_eng_adj;
    EXIT WHEN v_cursor%NOTFOUND;
    SELECT voltage_code into v_buyer_drw_code from m_company_service where "number"=v_f_eng_adj.SERVICE_NO;
    SELECT voltage_code into v_seller_inj_code from m_company_service where "number"=v_f_eng_adj.SUPLR_CODE;

    SELECT TOTAL_LOSS_PERCENT INTO v_loss_percent FROM M_LOSS_CALC_CHART WHERE INJECTION_VOLTAGE_CODE=v_seller_inj_code AND DRAWAL_VOLTAGE_CODE=v_buyer_drw_code;
    update F_ENERGY_ADJUSTMET set LOSS_PERCENT=v_loss_percent WHERE SERVICE_NO=v_f_eng_adj.SERVICE_NO AND SUPLR_CODE=v_f_eng_adj.SUPLR_CODE;

    END LOOP;
  RETURN 'SUCCESS';
END TEMP_UPDATE_LOSS_PERCENTAGE;

