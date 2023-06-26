CREATE OR REPLACE PROCEDURE OPENACCESS."R_LOSS_CALC" (v_remarks varchar2) 
--select id into v_m_reading_hdr_id from t_meter_reading_hdr where  m_company_meter_id=v_m_company_meter_id 
-- and reading_month=v_r_month and reading_year=v_r_year;
AS 
BEGIN

DECLARE
    v_total_loss VARCHAR2(200); 
    v_drawl_code VARCHAR2(200); 
    v_inj_code VARCHAR2(200); 
    v_trans_loss VARCHAR2(200); 
    v_dis_loss VARCHAR2(200); 
    v_inj_units1 VARCHAR2(200); 
    v_inj_units2 VARCHAR2(200); 
    v_inj_units3 VARCHAR2(200); 
    v_inj_units4 VARCHAR2(200); 
    v_inj_units5 VARCHAR2(200); 
    v_service_no varchar2(15);
    v_supplier_no varchar2(20);
    v_c1 varchar2(10);v_c2 VARCHAR2(10);v_c3 VARCHAR(10);v_c4 VARCHAR(10);v_c5 VARCHAR(10);


-- SELECT COUNT(*) INTO v_sellerCount FROM M_COMPANY_SERVICE WHERE "number"=v_r_buyer;
-- SELECT COUNT(*) INTO v_buyerCount FROM M_COMPANY_SERVICE WHERE "number"=v_r_seller;
--SERVICE_NO
--SUPLR_CODE
 begin

 select SERVICE_NO,SUPLR_CODE,c1,c2,c3,c4,c5 into v_service_no,v_supplier_no,v_c1,v_c2,v_c3,v_c4,v_c5 from r_energy_adjustment 
 where  remarks=v_remarks;

   SELECT voltage_code INTO v_inj_code FROM m_company_service WHERE "number"=v_supplier_no;
   SELECT voltage_code INTO v_drawl_code FROM m_company_service WHERE "number"=v_service_no;

      v_total_loss :=SURPLUS_ENERGY_LOSS_CALC(v_inj_code,v_drawl_code,v_c1,v_trans_loss,v_dis_loss,v_total_loss,v_inj_units1);
      v_total_loss :=SURPLUS_ENERGY_LOSS_CALC(v_inj_code,v_drawl_code,v_c2,v_trans_loss,v_dis_loss,v_total_loss,v_inj_units2);
      v_total_loss :=SURPLUS_ENERGY_LOSS_CALC(v_inj_code,v_drawl_code,v_c3,v_trans_loss,v_dis_loss,v_total_loss,v_inj_units3);
      v_total_loss :=SURPLUS_ENERGY_LOSS_CALC(v_inj_code,v_drawl_code,v_c4,v_trans_loss,v_dis_loss,v_total_loss,v_inj_units4);
      v_total_loss :=SURPLUS_ENERGY_LOSS_CALC(v_inj_code,v_drawl_code,v_c5,v_trans_loss,v_dis_loss,v_total_loss,v_inj_units5);

v_inj_units1 := round(v_inj_units1); v_inj_units2 := round(v_inj_units2); v_inj_units3 := round(v_inj_units3); 
v_inj_units4 := round(v_inj_units4); v_inj_units5 := round(v_inj_units5); 
update r_energy_adjustment set c1_withloss=v_inj_units1,c2_withloss=v_inj_units2,c3_withloss=v_inj_units3,c4_withloss=v_inj_units4,
c5_withloss=v_inj_units5 where remarks=v_remarks;

  end;
END R_LOSS_CALC;

