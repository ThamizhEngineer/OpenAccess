CREATE OR REPLACE FUNCTION OPENACCESS."TEMP_GAND" RETURN VARCHAR2 AS 
    V_TRADE_REL_COUNT VARCHAR2(150);
v_create_stb_result varchar2(50);
    v_log_result varchar(300):='SUCCESS';

BEGIN
         v_log_result := log_activity('PROCEDURE','TEMP_GAND','Start','','','', sysdate,'', '', '');

 for new_mr in (select mh.id,mh.INIT_READING_DT, mh.M_GEN_STMT_ID, meter.M_COMPANY_SERVICE_ID from t_meter_reading_hdr mh LEFT JOIN M_COMPANY_METER meter on mh.M_COMPANY_METER_ID = meter.id  where mh.gs_batch_id IN ('11172','11167'))
    loop
      v_log_result := log_activity('PROCEDURE','TEMP_GAND','LOOP','','','', sysdate,'', '', '');
      SELECT COUNT(*) INTO V_TRADE_REL_COUNT FROM M_TRADE_RELATIONSHIP TRADE WHERE M_SELLER_COMP_SERVICE_ID = new_mr.M_COMPANY_SERVICE_ID AND M_BUYER_COMPANY_ID = 'TNEB' AND new_mr.INIT_READING_DT BETWEEN TRADE.FROM_DATE AND TRADE.TO_DATE;
           v_log_result := log_activity('PROCEDURE','V_TRADE_REL_COUNT',V_TRADE_REL_COUNT,'','','', sysdate,'', '', '');
      IF V_TRADE_REL_COUNT>0 THEN
        v_create_stb_result := CREATE_STB(new_mr.M_GEN_STMT_ID);
      END IF;
    end loop;
  RETURN NULL;
END TEMP_GAND;

