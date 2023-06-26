CREATE OR REPLACE FUNCTION OPENACCESS."CREATE_GS" 
(
  v_imp_batch_id in varchar2,
  v_month IN VARCHAR2,
  v_year in varchar2
) RETURN VARCHAR2 AS

v_batch_based boolean:=false;
v_duration_based boolean:=false;
v_process_id  VARCHAR2(50);
v_records_processed number:=0;
v_created_Date DATE := SYSDATE;
v_created_By  varchar2(50):= 'admin';
v_status varchar2(50);
v_reason varchar2(200);
v_exception_code  NUMBER;
v_exception_msg  VARCHAR2(200);
v_result varchar(300):='SUCCESS';
v_log_result varchar(300):='SUCCESS';
v_mr_id varchar2(200);
v_mr_hdr_init_rdr_date varchar2(50);
v_mr_hdr_gen_stmt_id varchar2(200);
v_seller_service_id varchar2(200);
V_TRADE_REL_COUNT varchar2(50);
v_create_stb_result varchar2(50);
v_service_number varchar2(50);
v_ss_type varchar2(50);
O_RESULT_CODE VARCHAR2(200);
O_RESULT_DESC VARCHAR2(200);
v_prev_month varchar2(10);
v_current_month varchar2(10);
v_commission_date Date;
v_prev_reading_month varchar2(10);
v_prev_reading_year varchar2(10);
BEGIN

	BEGIN

      v_log_result := log_activity('PROCEDURE','create_gs','Start',v_result,v_reason,v_created_By, sysdate,v_imp_batch_id, v_month, v_year);
      select to_char(sysdate,'mm') into v_current_month from dual;
--     v_prev_month := to_char(to_date( '01-'||v_month||'-'||v_year,'dd-mm-yyyy')-1,'mm');

    -- this flag might be of better use in future
    if (v_imp_batch_id is null or v_imp_batch_id = '')then
      if (v_month is null or v_month = '' or v_year is null or v_year = '')
      then
        Raise_Application_Error (-20343, 'Month and Year is mandatory  ');
      else 
        v_duration_based:= true;
      end if;

    else
      v_batch_based := true;
    end if;
    v_process_id := T_PROCESS_GS_SEQ.nextval;
    INSERT INTO T_PROCESS_GS (ID,SYS_DT,STATUS,START_DT,END_DT,REMARKS)
      VALUES (v_process_id,v_created_Date,'PROCESSING',v_created_Date,NULL,NULL) ;
-- v_log_result := log_activity('PROCEDURE','create_gs','test-start',v_result,v_reason,v_created_By, sysdate,v_imp_batch_id, v_month,v_year);
    -- dbms_output.put_line('next1');

    if(v_duration_based) then
      FOR mr IN (SELECT mh.id, m_company_meter_id,READING_MONTH,READING_YEAR FROM T_METER_READING_HDR mh  WHERE mh.M_GEN_STMT_ID IS NULL AND mh.GS_BATCH_ID IS NULL
                      AND READING_MONTH = v_month AND READING_YEAR = v_year and M_COMPANY_METER_ID!='262208' )
      LOOP
      BEGIN
            select "number",TYPE_OF_SS,id into v_service_number,v_ss_type,v_seller_service_id from v_company_service where m_company_meter_id=mr.m_company_meter_id and enabled='Y'; 
    --         v_log_result := log_activity('PROCEDURE','create_gs-inside',v_seller_service_id,v_result,v_reason,v_created_By, sysdate,v_imp_batch_id, v_month,v_year);
          v_log_result := log_activity('PROCEDURE','create_gs','IN-LOOP',mr.m_company_meter_id,'','', sysdate,v_imp_batch_id, v_month, v_year);
        -- dbms_output.put_line('next2');
     
            IF(v_ss_type!='SECTION 10(1)SS')THEN --CONDITION FOR 10(1) SS
    
                v_prev_reading_month := to_char(to_date( '01-'||v_month ||'-'||v_year,'dd-mm-yyyy')-1,'mm');
                v_prev_reading_year := to_char(to_date( '01-'||v_month ||'-'||v_year,'dd-mm-yyyy')-1,'yyyy');
                excess_units_source.update_excess_from_ht(v_service_number,v_prev_reading_month,v_prev_reading_year,O_RESULT_CODE,O_RESULT_DESC  );
                excess_units.open_balances(v_service_number,mr.READING_MONTH,mr.READING_YEAR,O_RESULT_CODE,O_RESULT_DESC  );
         --  v_log_result := log_activity('PROCEDURE','create_gs-inside',v_seller_service_id,v_result,v_reason,v_created_By, sysdate,v_imp_batch_id, v_month,v_year);
    
    -- 
    --        if v_current_month='05' then 
    --                  BANKING_BALANCE.reset_for_yearend(v_service_number,v_month ,v_year,O_RESULT_CODE,O_RESULT_DESC);  
    --            else
                    --IMP_INT_ADJUSTED_UNIT.UPDATE_SURPLUS_BANKING(v_service_number,'04' ,'2019',O_RESULT_CODE,O_RESULT_DESC);
                   --  if v_commission_date is not null and v_commission_date< to_date('01-04-2018','DD-MM-YYYY') then
                   --      BANKING_BALANCE.open_balance(v_service_number,v_month,v_year,O_RESULT_CODE,O_RESULT_DESC);  
                   --  end if;
    --          end if;
             -- */
            v_result := create_gs_from_mr( v_process_id,mr.id);
            if (v_result='SUCCESS') then
                v_records_processed := v_records_processed +1;
            end if;
            END IF; --CONDITION FOR 10(1) SS ENDS
       exception
	   when others then
	    v_exception_code := SQLCODE;
	    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
	    v_result := 'FAILURE';
	    v_reason := v_exception_code || ' - ' || v_exception_msg;
--	    -- dbms_output.put_line(v_reason);
        v_log_result := log_activity('PROCEDURE','create_gs','EH-v_duration_based',v_reason,v_result,v_created_By, sysdate,v_imp_batch_id);
      END;     
      END LOOP;
    else
      FOR mr IN (SELECT mh.id,mh.INIT_READING_DT,mh.M_GEN_STMT_ID GEN_STMT_ID,mh.M_COMPANY_METER_ID,mh.READING_MONTH,mh.READING_YEAR
                    FROM T_METER_READING_HDR mh 
                    WHERE mh.M_GEN_STMT_ID IS NULL AND mh.GS_BATCH_ID IS NULL
                    and nvl(mh.MERGE_WITH_NEXT_BILLING,'N') = 'N' 
                    and mh.imp_batch_id = v_imp_batch_id)

      LOOP
      BEGIN
          select "number",TYPE_OF_SS,id  into v_service_number,v_ss_type,v_seller_service_id from v_company_service where m_company_meter_id=mr.m_company_meter_id and enabled='Y';
--           v_log_result := log_activity('PROCEDURE','create_gs-inside-2',v_seller_service_id,v_result,v_reason,v_created_By, sysdate,v_imp_batch_id, v_month,v_year);
         v_log_result := log_activity('PROCEDURE','create_gs','IN-LOOP','NUMBER-'||v_service_number,'SERVICE_ID-'||v_seller_service_id,'', sysdate,v_imp_batch_id, v_month, v_year);

--            v_log_result := log_activity('PROCEDURE','create_gs-inside-3',v_seller_service_id,v_commission_date,v_reason,v_created_By, sysdate,v_imp_batch_id, v_month,v_year);
          IF(v_ss_type!='SECTION 10(1)SS')THEN --CONDITION FOR 10(1) SS

            v_prev_reading_month := to_char(to_date( '01-'||mr.READING_MONTH ||'-'||mr.READING_YEAR,'dd-mm-yyyy')-1,'mm');
            v_prev_reading_year := to_char(to_date( '01-'||mr.READING_MONTH ||'-'||mr.READING_YEAR,'dd-mm-yyyy')-1,'yyyy');
            excess_units_source.update_excess_from_ht(v_service_number,v_prev_reading_month,v_prev_reading_year,O_RESULT_CODE,O_RESULT_DESC  );
            excess_units.open_balances(v_service_number,mr.READING_MONTH,mr.READING_YEAR,O_RESULT_CODE,O_RESULT_DESC  );
  --       if v_current_month='05' then 
  --                BANKING_BALANCE.reset_for_yearend(v_service_number,mr.READING_MONTH ,mr.READING_YEAR,O_RESULT_CODE,O_RESULT_DESC);  
   --         else
               -- IMP_INT_ADJUSTED_UNIT.UPDATE_SURPLUS_BANKING(v_service_number,'04' ,'2019',O_RESULT_CODE,O_RESULT_DESC);
               --   if v_commission_date is not null and v_commission_date< to_date('01-04-2018','DD-MM-YYYY') then
               --      BANKING_BALANCE.open_balance(v_service_number,mr.READING_MONTH ,mr.READING_YEAR,O_RESULT_CODE,O_RESULT_DESC);  
               --    end if;
           -- v_log_result := log_activity('PROCEDURE','create_gs','IN-LOOP-commision','NUMBER-'||v_service_number,'SERVICE_ID-'||v_seller_service_id,v_commission_date, sysdate,v_imp_batch_id, 'ss-'||v_ss_type, v_year);

     --     end if;
        v_log_result := log_activity('PROCEDURE','create_gs','IN-LOOP-ss','NUMBER-'||v_service_number,'serviceId/process-d'||v_seller_service_id||'-'||v_process_id,v_commission_date, sysdate,v_imp_batch_id, 'ss-'||v_ss_type, v_year);

          v_result:= create_gs_from_mr(v_process_id,mr.id);

          if (v_result='SUCCESS') then
            v_records_processed := v_records_processed +1;
          end if;
          END IF; --CONDITION FOR 10(1) SS ENDS
       exception
	   when others then
	    v_exception_code := SQLCODE;
	    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
	    v_result := 'FAILURE';
	    v_reason := v_exception_code || ' - ' || v_exception_msg;
--	    -- dbms_output.put_line(v_reason);
        v_log_result := log_activity('PROCEDURE','create_gs','EH-v_duration_based',v_reason,v_result,v_created_By, sysdate,v_imp_batch_id);
      END;  
      END LOOP;

	end if;

  if(v_records_processed > 0)then
    for new_mr in (select mh.id,mh.INIT_READING_DT, mh.M_GEN_STMT_ID, meter.M_COMPANY_SERVICE_ID from t_meter_reading_hdr mh LEFT JOIN M_COMPANY_METER meter on mh.M_COMPANY_METER_ID = meter.id  where mh.gs_batch_id = v_process_id)
    loop
      SELECT COUNT(*) INTO V_TRADE_REL_COUNT FROM M_TRADE_RELATIONSHIP TRADE WHERE M_SELLER_COMP_SERVICE_ID = new_mr.M_COMPANY_SERVICE_ID AND FLOW_TYPE_CODE = 'STB' AND new_mr.INIT_READING_DT BETWEEN TRADE.FROM_DATE AND TRADE.TO_DATE;
      IF V_TRADE_REL_COUNT>0 THEN
        v_create_stb_result := CREATE_STB(new_mr.M_GEN_STMT_ID);
      END IF;
    end loop;
  end if;

	exception
	  when others then
	    v_exception_code := SQLCODE;
	    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
	    v_result := 'FAILURE';
	    v_reason := v_exception_code || ' - ' || v_exception_msg;
--	    -- dbms_output.put_line(v_reason);
      v_log_result := log_activity('PROCEDURE','create_gs','EH',v_reason,v_result,v_created_By, sysdate,v_imp_batch_id);
	END;
   <<THE_END>>

    update T_PROCESS_GS set status='COMPLETED', remarks='PROCESSED-RECORD-COUNT:'||v_records_processed, end_dt=sysdate where id = v_process_id;
    v_log_result := log_activity('PROCEDURE','create_gs','End',v_result,v_reason,v_created_By, sysdate,v_imp_batch_id, v_month,v_year);
    COMMIT;

    return v_result;
END create_gs;