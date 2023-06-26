CREATE OR REPLACE PROCEDURE OPENACCESS."TMP_UPDATE_T_BANKING_SERVICE" 
IS
  v_result          VARCHAR2(100);
  v_total_loss      VARCHAR2(200);
  v_drawl_code      VARCHAR2(200);
  v_inj_code        VARCHAR2(200);
  v_inj_units       NUMBER;
  v_trans_loss      VARCHAR2(200);
  v_dis_loss        VARCHAR2(200);
  v_drawl_units     NUMBER;
  v_comp_id         VARCHAR2(200);
  v_bank_service_id VARCHAR2(200);
  v_banking_count   VARCHAR2(50);
  v_inj_units1      VARCHAR2(200);
  v_inj_units2      VARCHAR2(200);
  v_inj_units3      VARCHAR2(200);
  v_inj_units4      VARCHAR2(200);
  v_inj_units5      VARCHAR2(200);
  v_log_result      VARCHAR(300):='SUCCESS';
  v_exception_code  VARCHAR2(150);
  v_exception_msg   VARCHAR2(150);
  v_reason          VARCHAR2(300);
BEGIN
  FOR v_adj_unit IN
  (SELECT id ,
    m_company_id ,
    banking_service_id ,
    c1 ,
    c2 ,
    c3 ,
    c4 ,
    c5 ,
    remarks ,
    created_by ,
    created_dt ,
    modified_by ,
    modified_dt ,
    created_date ,
    enabled ,
    MONTH ,
    YEAR ,
    curr_c1 ,
    curr_c2 ,
    curr_c3 ,
    curr_c4 ,
    curr_c5 ,
    calculated
  FROM T_BANKING_BALANCE
  WHERE M_COMPANY_ID IN
    (SELECT M_COMPANY_ID
    FROM M_COMPANY_SERVICE
    WHERE "number" IN
      (SELECT '0'||SUPLR_CODE FROM missing_int_adj_units_0306
      )
    )
  AND MONTH = '06'
  )
  LOOP
    -- dbms_output.put_line('m_company_id--> '||V_ADJ_UNIT.m_company_id);
    UPDATE t_banking_balance
    SET C1 =
      (SELECT SUR1_TOT FROM missing_int_adj_units_0306
      ),
      C2 =
      (SELECT SUR2_TOT FROM missing_int_adj_units_0306
      ),
      C3 =
      (SELECT SUR3_TOT FROM missing_int_adj_units_0306
      ),
      C4 =
      (SELECT SUR4_TOT FROM missing_int_adj_units_0306
      ),
      C5 =
      (SELECT SUR5_TOT FROM missing_int_adj_units_0306
      ) WHERE m_company_id = V_ADJ_UNIT.m_company_id;
  END LOOP;
END TMP_UPDATE_T_BANKING_SERVICE;

