CREATE OR REPLACE FUNCTION OPENACCESS."TMP_UPDATE_SURPLUSLOSS" 
(
  I_MONTH IN VARCHAR2 
, I_YEAR IN VARCHAR2 
) RETURN VARCHAR2 AS 

    v_cursor sys_refcursor ;
    v_total_loss VARCHAR2(200);
    v_drawl_code VARCHAR2(200); 
    v_inj_code VARCHAR2(200); 
    v_energy_adj F_ENERGY_ADJUSTMET%ROWTYPE;

    v_trans_loss VARCHAR2(200); 
    v_dis_loss VARCHAR2(200); 
    v_inj_units1 VARCHAR2(200); 
    v_inj_units2 VARCHAR2(200); 
    v_inj_units3 VARCHAR2(200); 
    v_inj_units4 VARCHAR2(200); 
    v_inj_units5 VARCHAR2(200); 

    v_log_result varchar(300):='SUCCESS';
    v_exception_code VARCHAR2(150);
    v_exception_msg  VARCHAR2(150);
    v_reason VARCHAR2(300);
    o_result_code VARCHAR2(100);
    o_result_desc  VARCHAR2(200);
BEGIN
BEGIN
    v_log_result := log_activity('FUNCTION','TMP_UPDATE_SURPLUSLOSS','START','Start - '||I_MONTH,'','', sysdate,I_YEAR);

    OPEN v_cursor for select * from F_ENERGY_ADJUSTMET where READING_MNTH=I_MONTH and READING_YR=I_YEAR;
    LOOP
    FETCH v_cursor INTO v_energy_adj;
    EXIT WHEN v_cursor%NOTFOUND;

      SELECT voltage_code INTO v_inj_code FROM m_company_service WHERE "number"=v_energy_adj.SUPLR_CODE;
      SELECT voltage_code INTO v_drawl_code FROM m_company_service WHERE "number"=v_energy_adj.SERVICE_NO;

      v_total_loss :=SURPLUS_ENERGY_LOSS_CALC(v_inj_code,v_drawl_code,v_energy_adj.C1,v_trans_loss,v_dis_loss,v_total_loss,v_inj_units1);
      v_total_loss :=SURPLUS_ENERGY_LOSS_CALC(v_inj_code,v_drawl_code,v_energy_adj.C2,v_trans_loss,v_dis_loss,v_total_loss,v_inj_units2);
      v_total_loss :=SURPLUS_ENERGY_LOSS_CALC(v_inj_code,v_drawl_code,v_energy_adj.C3,v_trans_loss,v_dis_loss,v_total_loss,v_inj_units3);
      v_total_loss :=SURPLUS_ENERGY_LOSS_CALC(v_inj_code,v_drawl_code,v_energy_adj.C4,v_trans_loss,v_dis_loss,v_total_loss,v_inj_units4);
      v_total_loss :=SURPLUS_ENERGY_LOSS_CALC(v_inj_code,v_drawl_code,v_energy_adj.C5,v_trans_loss,v_dis_loss,v_total_loss,v_inj_units5);
      UPDATE F_ENERGY_ADJUSTMET SET C1_WITHLOSS=round(nvl(v_inj_units1,0),0),C2_WITHLOSS=round(nvl(v_inj_units2,0),0),C3_WITHLOSS=round(nvl(v_inj_units3,0),0),C4_WITHLOSS=round(nvl(v_inj_units4,0),0),C5_WITHLOSS=round(nvl(v_inj_units5,0),0)
      WHERE ID = v_energy_adj.ID;

    END LOOP;
        exception
        when others then
          v_exception_code := SQLCODE;
          v_exception_msg := SUBSTR(SQLERRM, 1, 200);
          o_result_code := 'FAILURE';
          o_result_desc := v_exception_code || ' - ' || v_exception_msg;
          -- -- dbms_output.put_line(o_result_desc);
        v_log_result := log_activity('FUNCTION','TMP_UPDATE_SURPLUSLOSS','EXCEPTION',o_result_desc,I_MONTH,'', sysdate,I_YEAR);
       END;
    COMMIT;
    v_log_result := log_activity('FUNCTION','TMP_UPDATE_SURPLUSLOSS','End','End','','', sysdate,I_MONTH);
  RETURN o_result_desc;
END TMP_UPDATE_SURPLUSLOSS;

