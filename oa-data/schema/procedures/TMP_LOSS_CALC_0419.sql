CREATE OR REPLACE PROCEDURE OPENACCESS."TMP_LOSS_CALC_0419" 
IS
  v_result VARCHAR2(100);

    v_total_loss VARCHAR2(200);
    v_drawl_code VARCHAR2(200); 
    v_inj_code VARCHAR2(200); 
    v_inj_units number; 
    v_trans_loss VARCHAR2(200); 
    v_dis_loss VARCHAR2(200); 
    v_drawl_units  number; 
    v_comp_id VARCHAR2(200); 
    v_bank_service_id VARCHAR2(200); 
    v_banking_count VARCHAR2(50); 
    v_inj_units1 VARCHAR2(200); 
    v_inj_units2 VARCHAR2(200); 
    v_inj_units3 VARCHAR2(200); 
    v_inj_units4 VARCHAR2(200); 
    v_inj_units5 VARCHAR2(200); 
    v_log_result varchar(300):='SUCCESS';
    v_exception_code VARCHAR2(150);
    v_exception_msg  VARCHAR2(150);
    v_reason VARCHAR2(300);

BEGIN

  FOR v_adj_unit IN
  (SELECT service_no ,
    reading_mnth ,
    reading_yr ,
    c1_tot ,
    c2_tot ,
    c3_tot ,
    c4_tot ,
    c5_tot ,
    c24_tot ,
    suplr_code ,
    suplr_type,
    sur1_tot,
    sur2_tot,
    sur3_tot,
    sur4_tot,
    sur5_tot,
    sur24_tot
  FROM missing_int_adj_units_0306 where SERVICE_NO='49094220227' AND SUPLR_CODE='79244724394' AND READING_MNTH='4'
  )
  LOOP
    -- dbms_output.put_line('SERVICE_NO--> '||V_ADJ_UNIT.SERVICE_NO||' SUPLR_CODE---> '||V_ADJ_UNIT.SUPLR_CODE);

    SELECT voltage_code
    INTO v_inj_code
    FROM m_company_service
    WHERE "number"='0'||v_adj_unit.SUPLR_CODE;

    SELECT voltage_code
    INTO v_drawl_code
    FROM m_company_service
    WHERE "number"='0'||v_adj_unit.SERVICE_NO;

    v_total_loss :=SURPLUS_ENERGY_LOSS_CALC(v_inj_code,v_drawl_code,v_adj_unit.c1_tot,v_trans_loss,v_dis_loss,v_total_loss,v_inj_units1);
    v_total_loss :=SURPLUS_ENERGY_LOSS_CALC(v_inj_code,v_drawl_code,v_adj_unit.c2_tot,v_trans_loss,v_dis_loss,v_total_loss,v_inj_units2);
    v_total_loss :=SURPLUS_ENERGY_LOSS_CALC(v_inj_code,v_drawl_code,v_adj_unit.c3_tot,v_trans_loss,v_dis_loss,v_total_loss,v_inj_units3);
    v_total_loss :=SURPLUS_ENERGY_LOSS_CALC(v_inj_code,v_drawl_code,v_adj_unit.c4_tot,v_trans_loss,v_dis_loss,v_total_loss,v_inj_units4);
    v_total_loss :=SURPLUS_ENERGY_LOSS_CALC(v_inj_code,v_drawl_code,v_adj_unit.c5_tot,v_trans_loss,v_dis_loss,v_total_loss,v_inj_units5);

    UPDATE missing_int_adj_units_0306
    SET sur1_tot     = ROUND(NVL(v_inj_units1,0),0),
      sur2_tot       =ROUND(NVL(v_inj_units2,0),0),
      sur3_tot       =ROUND(NVL(v_inj_units3,0),0),
      sur4_tot       =ROUND(NVL(v_inj_units4,0),0),
      sur5_tot       =ROUND(NVL(v_inj_units5,0),0)
    WHERE SUPLR_CODE = v_adj_unit.SUPLR_CODE
    AND SERVICE_NO   = v_adj_unit.SERVICE_NO;
  END LOOP;
END TMP_LOSS_CALC_0419;

