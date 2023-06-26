CREATE OR REPLACE FUNCTION OPENACCESS."COMPLETE_ESI" 
(
  v_es_intent_id in varchar2
) RETURN VARCHAR2 AS
/**
For not-completed ESI flow of type-WEG,
      will create trade-relationship for each ewa-line
      update the esi-line with the tr-id
      update the ESI status to COMPLETED

*/


v_tr M_TRADE_RELATIONSHIP%ROWTYPE;
v_flow_type_code varchar2(50);
v_esi_status_code varchar2(50);
v_no_records BOOLEAN:=TRUE;
--v_created_Date DATE := SYSDATE;
v_created_By  varchar2(50):= 'admin';
v_status varchar2(50);
v_reason varchar2(200):='';
v_exception_code  NUMBER;
v_exception_msg  VARCHAR2(200);
v_result varchar(300):='SUCCESS';
v_log_result varchar(300):='SUCCESS';
v_imported BOOLEAN;

BEGIN

	BEGIN

    v_log_result := log_activity('PROCEDURE','complete_esi','START',v_reason,v_result,v_created_By, sysdate,v_es_intent_id);

    select flow_type_code, STATUS_CODE into v_flow_type_code, v_esi_status_code from t_es_intent WHERE ID = v_es_intent_id;

    if(v_flow_type_code = 'WEG' and v_esi_status_code != 'COMPLETED') then
        FOR ewal IN (select sell.M_COMPANY_ID M_SELLER_COMPANY_ID, ewa.SELLER_COMP_SERV_ID M_SELLER_COMP_SERVICE_ID,buy.M_COMPANY_ID M_BUYER_COMPANY_ID,
                          ewal.buyer_comp_serv_id M_BUYER_COMP_SERVICE_ID, ewa.from_dt from_date, ewa.to_dt to_date, ewal.ID REFERENCENUMBER ,ewal.DRAWAL_PEAK_UNITS PEAK_UNITS,
                          ewal.DRAWAL_OFF_PEAK_UNITS OFF_PEAK_UNITS, ewal.approved_units quantum, ewal.interval_type_code, ewal.share_percentage SHARE_PERCENT,ewal.IS_CAPTIVE
                           from t_ewa_line ewal
                           left  join t_ewa ewa on ewal.t_ewa_id = ewa.id
                           left join M_COMPANY_SERVICE sell on ewa.SELLER_COMP_SERV_ID = sell.id
                           left join M_COMPANY_SERVICE buy on ewal.buyer_comp_serv_id = buy.id
                           where ewa.T_ES_INTENT_ID =v_es_intent_id
                         )
      LOOP
        begin
        v_tr.id := M_TRADE_RELATIONSHIP_SEQ.nextval;
        v_tr.M_SELLER_COMPANY_ID := ewal.M_SELLER_COMPANY_ID;
        v_tr.M_SELLER_COMP_SERVICE_ID := ewal.M_SELLER_COMP_SERVICE_ID;
        v_tr.M_BUYER_COMPANY_ID := ewal.M_BUYER_COMPANY_ID;
        v_tr.M_BUYER_COMP_SERVICE_ID := ewal.M_BUYER_COMP_SERVICE_ID;
        v_tr.FROM_DATE := ewal.FROM_DATE;
        v_tr.TO_DATE := ewal.TO_DATE;
        v_tr.REFERENCENUMBER := ewal.REFERENCENUMBER;
        v_tr.PEAK_UNITS := ewal.PEAK_UNITS;
        v_tr.OFF_PEAK_UNITS := ewal.OFF_PEAK_UNITS;
        v_tr.QUANTUM := ewal.QUANTUM;
        v_tr.INTERVAL_TYPE_CODE := ewal.INTERVAL_TYPE_CODE;
        v_tr.SHARE_PERCENT := ewal.SHARE_PERCENT;
        v_tr.TRADE_RELATIONSHIP_SOURCE_CODE := 'EWA';
        v_tr.IS_CAPTIVE := ewal.IS_CAPTIVE;
        v_tr.ENABLED := 'Y';
        v_tr.STATUS_CODE := 'ACTIVATED';
        v_tr.CREATED_BY := v_created_by;
        v_tr.CREATED_DATE := sysdate;

        insert into M_TRADE_RELATIONSHIP values v_tr;
        update t_es_intent_line set m_trade_relationship_id= v_tr.id , status_code ='COMPLETED' where T_EST_INTENT_ID = v_es_intent_id and  BUYER_COMP_SERV_ID = v_tr.M_BUYER_COMP_SERVICE_ID;

        if(v_no_records) THEN
            v_no_records := FALSE;
        END IF;
      exception
      when others then
        v_exception_code := SQLCODE;
        v_exception_msg := SUBSTR(SQLERRM, 1, 200);
        v_result := 'FAILURE';
        v_reason := v_exception_code || ' - ' || v_exception_msg;
        -- dbms_output.put_line(v_reason);

        v_log_result := log_activity('PROCEDURE','complete_esi','EH-LOOP',v_reason,v_result,v_created_By, sysdate,v_es_intent_id);
      END;
      end loop;


      if(not v_no_records)then
        update t_es_intent SET status_code ='COMPLETED' where ID = v_es_intent_id;
      end if;
    else
      if (v_esi_status_code = 'COMPLETED') THEN
          v_reason:='ESI already completed';
        else
          v_reason:='Only flows of type-WEG is handled';
      end if;
    end if;
		if(v_no_records) THEN
			v_result := 'FAILURE';
	    if(v_reason='' OR V_REASON IS NULL) then v_reason := 'No records to process'; end if;
		END IF;


	exception
	  when others then
	    v_exception_code := SQLCODE;
	    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
	    v_result := 'FAILURE';
	    v_reason := v_exception_code || ' - ' || v_exception_msg;
	    -- dbms_output.put_line(v_reason);

      v_log_result := log_activity('PROCEDURE','complete_esi','EH',v_reason,v_result,v_created_By, sysdate,v_es_intent_id);
	END;
   <<THE_END>>

  v_log_result := log_activity('PROCEDURE','complete_esi','END',v_reason,v_result,v_created_By, sysdate,v_es_intent_id);

  COMMIT;

  return V_RESULT;

END complete_esi;