CREATE OR REPLACE FUNCTION OPENACCESS."DELETE_MASTER_LEVEL" 
(
  v_service_number IN VARCHAR2 
) RETURN VARCHAR2 AS 
v_service_id varchar2(50);
v_meter_id varchar2(50);
v_meter_hdr_count varchar2(50);
v_meter_slot varchar2(50);
v_meter_cursor sys_refcursor;
v_t_meter_hdr_id varchar2(50);
v_gen_stmt_count varchar2(50);
v_gen_stmt_cursor sys_refcursor;
v_gen_stmt_id varchar2(50);
v_energy_sale_count varchar2(50);
v_energy_sale_id varchar2(50);
v_es_charge_count varchar2(50);
v_energy_sale_order_count varchar2(50);
v_energy_sale_order_id varchar2(50);
v_energy_charge_count varchar2(50);
v_energy_sale_order_lines_count varchar2(50);
v_energy_ledger_count varchar2(50);

BEGIN

SELECT  ID INTO v_service_id FROM M_COMPANY_SERVICE WHERE "number" =v_service_number;


SELECT id into v_energy_sale_id from T_ENERGY_SALE where SELLER_COMP_SERV_ID=v_service_id;

SELECT id into v_energy_sale_order_id from F_ENERGY_SALE_ORDER where T_ENERGY_SALE_ID=v_energy_sale_id;

SELECT COUNT(*) INTO v_energy_charge_count FROM F_ENERGY_CHARGES WHERE F_ENERGY_SALE_ORDER_ID=v_energy_sale_order_id;
    IF(v_energy_charge_count >'0') THEN 
    DELETE FROM F_ENERGY_CHARGES WHERE F_ENERGY_SALE_ORDER_ID=v_energy_sale_order_id;
    END IF;

SELECT COUNT(*) INTO v_energy_sale_order_lines_count FROM F_ENERGY_SALE_ORDER_LINES WHERE F_ENERGY_SALE_ORDER_ID=v_energy_sale_order_id;
    IF(v_energy_sale_order_lines_count >'0') THEN 
    DELETE FROM F_ENERGY_SALE_ORDER_LINES WHERE F_ENERGY_SALE_ORDER_ID=v_energy_sale_order_id;
    END IF;

SELECT COUNT(*) INTO v_energy_ledger_count FROM F_ENERGY_LEDGER WHERE F_ENERGY_SALE_ORDER_ID=v_energy_sale_order_id;
    IF(v_energy_ledger_count >'0') THEN 
    DELETE FROM F_ENERGY_LEDGER WHERE F_ENERGY_SALE_ORDER_ID=v_energy_sale_order_id;
    END IF;

RETURN 'success';
END DELETE_MASTER_LEVEL;