CREATE OR REPLACE PACKAGE BODY OPENACCESS.IMP_SAMAST_APPLN_HELPER AS

  PROCEDURE MERGE_EXT_RECORD(I_BATCH_KEY IN VARCHAR2,I_APPLN_NO IN VARCHAR2,  I_IMP_TYPE IN VARCHAR, O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2) is  
	v_process varchar2(50):='IMP_SAMAST_APPLN_HELPER.MERGE_EXT_RECORD';
	v_process_type varchar2(500):='PROCEDURE';
	v_stage varchar2(500):='';
	v_step varchar2(500):='';
	v_message varchar2(500):='';
    v_process_remarks varchar2(500):='';
    v_log_result  varchar2(50);
   	v_row_count NUMBER:=0;
  BEGIN
    BEGIN --EXCEPTION-HANDLER BLOCK STARTS HERE    
            /*
            if impType = APPLICATION
	            Check if atleast one record for the applNo in extSamastAppln 
	            if recordExists
	                    Update extSamastAppln with intSamastAppln where appRefNo matches             
	                    (note. The above is appRefNo and not appNo)             
	            Else insert extSamastAppln with intSamastAppln
	         else
	          	do the above for   extSamastApplnApproval
            */	    
		if (I_IMP_TYPE  = 'APPLICATION')then
			SELECT count(id) INTO v_row_count FROM EXT_SAMAST_APPLN esa WHERE appl_no = I_APPLN_NO;
			if(v_row_count>0) THEN  
				v_stage:='PROCESS'; v_message:='Data updated to EXT_SAMAST_APPLN';
				UPDATE EXT_SAMAST_APPLN a
				SET 	(a.ID, a.APP_ID, a.APPL_REF_NO, a.APPL_NO, a.APPL_DATE, a.APP_CATEGORY, a.CUSTOMER_NAME, a.ENTITY_INJ, a.ENTITY_INJ_EDC, a.ENTITY_DRL, a.ENTITY_DRL_EDC, a.PERIOD_START_DATE, a.PERIOD_END_DATE, a.QUANTUM, a.CATEGORY1, a.CATEGORY2, a.EOR_REG_TYPE, a.EOS_GC_APPROVAL_NUMBER, a.EOS_FEEDER_NAME_SELLER, a.EOS_VOL_LVL_FEEDER, a.EOS_INJ_SUBSTATION, a.EOS_VOL_LVL_SUBSTATION, a.EOC_LOSS_PER, a.EVACUATION_CAPACITY, a.EOS_UTIL_INJ_EMBED, a.EOS_UTIL_INJ_EMBED_LABEL, a.EOB_UTIL_DRAWAL_EMBED_LABEL, a.EOB_UTIL_DRA_EMBED, a.EOB_HT_CONSUMER_NUMBER, a.EOB_FEEDER_NAME_BUYER, a.EOB_VOL_LVL_FEEDER, a.EOB_DRA_SUBSTATION, a.EOB_VOL_LVL_SUBSTATION, a.EOB_DRAWAL_LIMIT, a.EOB_BUYER_TYPE, a.EOI_REG_NAME, a.EOI_REG_ADDRESS, a.EOI_REG_MOBILE1_NO, a.EDC_STATUS, a.APPLICATION_STATUS, a.APP_TYPE, a.BATCH_KEY, a.IS_CLEAN, a.INPUT_REMARKS, a.ENABLED,   a.MODIFIED_BY, a.MODIFIED_DT, seller_edc_code, buyer_edc_code,buyer_sldc_noc_id,seller_sldc_noc_id) = 
				 (SELECT a.ID, b.APP_ID, b.APPL_REF_NO, b.APPL_NO, b.APPL_DATE, b.APP_CATEGORY, b.CUSTOMER_NAME, b.ENTITY_INJ, b.ENTITY_INJ_EDC, b.ENTITY_DRL, b.ENTITY_DRL_EDC, b.PERIOD_START_DATE, b.PERIOD_END_DATE, b.QUANTUM, b.CATEGORY1, b.CATEGORY2, b.EOR_REG_TYPE, b.EOS_GC_APPROVAL_NUMBER, b.EOS_FEEDER_NAME_SELLER, b.EOS_VOL_LVL_FEEDER, b.EOS_INJ_SUBSTATION, b.EOS_VOL_LVL_SUBSTATION, b.EOC_LOSS_PER, b.EVACUATION_CAPACITY, b.EOS_UTIL_INJ_EMBED, b.EOS_UTIL_INJ_EMBED_LABEL, b.EOB_UTIL_DRAWAL_EMBED_LABEL, b.EOB_UTIL_DRA_EMBED, b.EOB_HT_CONSUMER_NUMBER, b.EOB_FEEDER_NAME_BUYER, b.EOB_VOL_LVL_FEEDER, b.EOB_DRA_SUBSTATION, b.EOB_VOL_LVL_SUBSTATION, b.EOB_DRAWAL_LIMIT, b.EOB_BUYER_TYPE, b.EOI_REG_NAME, b.EOI_REG_ADDRESS, b.EOI_REG_MOBILE1_NO, b.EDC_STATUS, b.APPLICATION_STATUS, b.APP_TYPE, b.BATCH_KEY, b.IS_CLEAN, b.INPUT_REMARKS, b.ENABLED,   v_process, sysdate,seller_edc_code, buyer_edc_code,buyer_sldc_noc_id,seller_sldc_noc_id
					FROM   INT_SAMAST_APPLN b WHERE  b.APPL_NO = a.APPL_NO  AND b.APPL_REF_NO = a.APPL_REF_NO AND b.BATCH_KEY = I_BATCH_KEY)
				WHERE  EXISTS (SELECT 1 FROM   INT_SAMAST_APPLN WHERE  APPL_NO = a.APPL_NO  AND APPL_REF_NO = a.APPL_REF_NO AND BATCH_KEY = I_BATCH_KEY);			
			ELSE
				v_stage:='PROCESS'; v_message:='Data inserted to EXT_SAMAST_APPLN';
				INSERT INTO EXT_SAMAST_APPLN (ID,    APP_ID, APPL_REF_NO, APPL_NO, APPL_DATE,         APP_CATEGORY, CUSTOMER_NAME, ENTITY_INJ, ENTITY_INJ_EDC,         ENTITY_DRL, ENTITY_DRL_EDC, PERIOD_START_DATE, PERIOD_END_DATE, QUANTUM, CATEGORY1, CATEGORY2, EOR_REG_TYPE,                 EOS_GC_APPROVAL_NUMBER, EOS_FEEDER_NAME_SELLER, EOS_VOL_LVL_FEEDER, EOS_INJ_SUBSTATION, EOS_VOL_LVL_SUBSTATION, EOC_LOSS_PER, EVACUATION_CAPACITY, EOS_UTIL_INJ_EMBED, EOS_UTIL_INJ_EMBED_LABEL, EOB_UTIL_DRAWAL_EMBED_LABEL, EOB_UTIL_DRA_EMBED, EOB_HT_CONSUMER_NUMBER, EOB_FEEDER_NAME_BUYER, EOB_VOL_LVL_FEEDER, EOB_DRA_SUBSTATION, EOB_VOL_LVL_SUBSTATION, EOB_DRAWAL_LIMIT, EOB_BUYER_TYPE, EOI_REG_NAME, EOI_REG_ADDRESS, EOI_REG_MOBILE1_NO, EDC_STATUS, APPLICATION_STATUS, APP_TYPE, BATCH_KEY, IS_CLEAN, INPUT_REMARKS, ENABLED, CREATED_BY, CREATED_DT, MODIFIED_BY, MODIFIED_DT,seller_edc_code, buyer_edc_code,buyer_sldc_noc_id,seller_sldc_noc_id)
				SELECT EXT_SAMAST_APPLN_SEQ.nextval, b.APP_ID, b.APPL_REF_NO, b.APPL_NO, b.APPL_DATE, b.APP_CATEGORY, b.CUSTOMER_NAME, b.ENTITY_INJ, b.ENTITY_INJ_EDC, b.ENTITY_DRL, b.ENTITY_DRL_EDC, b.PERIOD_START_DATE, b.PERIOD_END_DATE, b.QUANTUM, b.CATEGORY1, b.CATEGORY2, b.EOR_REG_TYPE, b.EOS_GC_APPROVAL_NUMBER, b.EOS_FEEDER_NAME_SELLER, b.EOS_VOL_LVL_FEEDER, b.EOS_INJ_SUBSTATION, b.EOS_VOL_LVL_SUBSTATION, b.EOC_LOSS_PER, b.EVACUATION_CAPACITY, b.EOS_UTIL_INJ_EMBED, b.EOS_UTIL_INJ_EMBED_LABEL, b.EOB_UTIL_DRAWAL_EMBED_LABEL, b.EOB_UTIL_DRA_EMBED, b.EOB_HT_CONSUMER_NUMBER, b.EOB_FEEDER_NAME_BUYER, b.EOB_VOL_LVL_FEEDER, b.EOB_DRA_SUBSTATION, b.EOB_VOL_LVL_SUBSTATION, b.EOB_DRAWAL_LIMIT, b.EOB_BUYER_TYPE, b.EOI_REG_NAME, b.EOI_REG_ADDRESS, b.EOI_REG_MOBILE1_NO, b.EDC_STATUS, b.APPLICATION_STATUS, b.APP_TYPE, b.BATCH_KEY, b.IS_CLEAN, b.INPUT_REMARKS, b.ENABLED, v_process, sysdate, null, null,seller_edc_code, buyer_edc_code,buyer_sldc_noc_id,seller_sldc_noc_id
				FROM INT_SAMAST_APPLN b WHERE  b.APPL_NO = I_APPLN_NO AND b.BATCH_KEY = I_BATCH_KEY;
			END IF; 
            select trim(nvl(process_remarks,'X')) into v_process_remarks  from INT_SAMAST_APPLN where APPL_NO = I_APPLN_NO AND BATCH_KEY = I_BATCH_KEY and rownum=1;
            if(v_process_remarks='NOC-NOT-NEEDED') then
                update EXT_SAMAST_APPLN set seller_noc_status = 'NOT_APPLICABLE', buyer_noc_status = 'NOT_APPLICABLE' WHERE APPL_NO = I_APPLN_NO AND BATCH_KEY = I_BATCH_KEY;
            end if;
        ELSE
			SELECT count(id) INTO v_row_count FROM EXT_SAMAST_APPLN_APPROVAL esaa WHERE appl_no = I_APPLN_NO;
			if(v_row_count>0) THEN  
				v_stage:='PROCESS'; v_message:='Data updated to EXT_SAMAST_APPLN_APPROVAL';
				UPDATE EXT_SAMAST_APPLN_APPROVAL a
				SET 	(a.ID, a.APP_ID, a.APPL_REF_NO, a.APPL_NO, a.APPL_DATE, a.APP_CATEGORY, a.CUSTOMER_NAME, a.ENTITY_INJ, a.ENTITY_INJ_EDC, a.ENTITY_DRL, a.ENTITY_DRL_EDC, a.PERIOD_START_DATE, a.PERIOD_END_DATE, a.QUANTUM, a.CATEGORY1, a.CATEGORY2, a.EOR_REG_TYPE, a.EOS_GC_APPROVAL_NUMBER, a.EOS_FEEDER_NAME_SELLER, a.EOS_VOL_LVL_FEEDER, a.EOS_INJ_SUBSTATION, a.EOS_VOL_LVL_SUBSTATION, a.EOC_LOSS_PER, a.EVACUATION_CAPACITY, a.EOS_UTIL_INJ_EMBED, a.EOS_UTIL_INJ_EMBED_LABEL, a.EOB_UTIL_DRAWAL_EMBED_LABEL, a.EOB_UTIL_DRA_EMBED, a.EOB_HT_CONSUMER_NUMBER, a.EOB_FEEDER_NAME_BUYER, a.EOB_VOL_LVL_FEEDER, a.EOB_DRA_SUBSTATION, a.EOB_VOL_LVL_SUBSTATION, a.EOB_DRAWAL_LIMIT, a.EOB_BUYER_TYPE, a.EOI_REG_NAME, a.EOI_REG_ADDRESS, a.EOI_REG_MOBILE1_NO, a.EDC_STATUS, a.APPLICATION_STATUS, a.APPROVAL_NO, a.APP_TYPE, a.BATCH_KEY, a.IS_CLEAN, a.INPUT_REMARKS, a.ENABLED,  a.MODIFIED_BY, a.MODIFIED_DT) = 
				(SELECT a.ID, b.APP_ID, b.APPL_REF_NO, b.APPL_NO, b.APPL_DATE, b.APP_CATEGORY, vcs.M_COMPANY_NAME , b.ENTITY_INJ, b.ENTITY_INJ_EDC, b.ENTITY_DRL, b.ENTITY_DRL_EDC, b.PERIOD_START_DATE, b.PERIOD_END_DATE, b.QUANTUM, b.CATEGORY1, b.CATEGORY2, b.EOR_REG_TYPE, b.EOS_GC_APPROVAL_NUMBER, b.EOS_FEEDER_NAME_SELLER, b.EOS_VOL_LVL_FEEDER, b.EOS_INJ_SUBSTATION, b.EOS_VOL_LVL_SUBSTATION, b.EOC_LOSS_PER, b.EVACUATION_CAPACITY, b.EOS_UTIL_INJ_EMBED, b.EOS_UTIL_INJ_EMBED_LABEL, b.EOB_UTIL_DRAWAL_EMBED_LABEL, b.EOB_UTIL_DRA_EMBED, b.EOB_HT_CONSUMER_NUMBER, b.EOB_FEEDER_NAME_BUYER, b.EOB_VOL_LVL_FEEDER, b.EOB_DRA_SUBSTATION, b.EOB_VOL_LVL_SUBSTATION, b.EOB_DRAWAL_LIMIT, b.EOB_BUYER_TYPE, b.EOI_REG_NAME, b.EOI_REG_ADDRESS, b.EOI_REG_MOBILE1_NO, b.EDC_STATUS, b.APPLICATION_STATUS, b.APPROVAL_NO, b.APP_TYPE, b.BATCH_KEY, b.IS_CLEAN, b.INPUT_REMARKS, b.ENABLED,   v_process, sysdate
					FROM   INT_SAMAST_APPLN b
					LEFT JOIN V_COMPANY_SERVICE vcs ON b.EOB_HT_CONSUMER_NUMBER =vcs."number" 
					WHERE  b.APPL_NO = a.APPL_NO  AND b.APPL_REF_NO = a.APPL_REF_NO AND b.BATCH_KEY = I_BATCH_KEY)
				WHERE  EXISTS (SELECT 1 FROM   INT_SAMAST_APPLN WHERE  APPL_NO = a.APPL_NO  AND APPL_REF_NO = a.APPL_REF_NO AND BATCH_KEY = I_BATCH_KEY);			
			ELSE
				v_stage:='PROCESS'; v_message:='Data inserted to EXT_SAMAST_APPLN_APPROVAL';
				INSERT INTO EXT_SAMAST_APPLN_APPROVAL (ID, APP_ID, APPL_REF_NO, APPL_NO, APPL_DATE, APP_CATEGORY, CUSTOMER_NAME, ENTITY_INJ, ENTITY_INJ_EDC, ENTITY_DRL, ENTITY_DRL_EDC, PERIOD_START_DATE, PERIOD_END_DATE, QUANTUM, CATEGORY1, CATEGORY2, EOR_REG_TYPE, EOS_GC_APPROVAL_NUMBER, EOS_FEEDER_NAME_SELLER, EOS_VOL_LVL_FEEDER, EOS_INJ_SUBSTATION, EOS_VOL_LVL_SUBSTATION, EOC_LOSS_PER, EVACUATION_CAPACITY, EOS_UTIL_INJ_EMBED, EOS_UTIL_INJ_EMBED_LABEL, EOB_UTIL_DRAWAL_EMBED_LABEL, EOB_UTIL_DRA_EMBED, EOB_HT_CONSUMER_NUMBER, EOB_FEEDER_NAME_BUYER, EOB_VOL_LVL_FEEDER, EOB_DRA_SUBSTATION, EOB_VOL_LVL_SUBSTATION, EOB_DRAWAL_LIMIT, EOB_BUYER_TYPE, EOI_REG_NAME, EOI_REG_ADDRESS, EOI_REG_MOBILE1_NO, EDC_STATUS, APPLICATION_STATUS, APPROVAL_NO, APP_TYPE, BATCH_KEY, IS_CLEAN, INPUT_REMARKS, ENABLED, CREATED_BY, CREATED_DT, MODIFIED_BY, MODIFIED_DT)
				SELECT EXT_SAMAST_APPLN_APPROVAL_SEQ.nextval, b.APP_ID, b.APPL_REF_NO, b.APPL_NO, b.APPL_DATE, b.APP_CATEGORY, vcs.M_COMPANY_NAME , b.ENTITY_INJ, b.ENTITY_INJ_EDC, b.ENTITY_DRL, b.ENTITY_DRL_EDC, b.PERIOD_START_DATE, b.PERIOD_END_DATE, b.QUANTUM, b.CATEGORY1, b.CATEGORY2, b.EOR_REG_TYPE, b.EOS_GC_APPROVAL_NUMBER, b.EOS_FEEDER_NAME_SELLER, b.EOS_VOL_LVL_FEEDER, b.EOS_INJ_SUBSTATION, b.EOS_VOL_LVL_SUBSTATION, b.EOC_LOSS_PER, b.EVACUATION_CAPACITY, b.EOS_UTIL_INJ_EMBED, b.EOS_UTIL_INJ_EMBED_LABEL, b.EOB_UTIL_DRAWAL_EMBED_LABEL, b.EOB_UTIL_DRA_EMBED, b.EOB_HT_CONSUMER_NUMBER, b.EOB_FEEDER_NAME_BUYER, b.EOB_VOL_LVL_FEEDER, b.EOB_DRA_SUBSTATION, b.EOB_VOL_LVL_SUBSTATION, b.EOB_DRAWAL_LIMIT, b.EOB_BUYER_TYPE, b.EOI_REG_NAME, b.EOI_REG_ADDRESS, b.EOI_REG_MOBILE1_NO, b.EDC_STATUS, b.APPLICATION_STATUS,b.APPROVAL_NO, b.APP_TYPE, b.BATCH_KEY, b.IS_CLEAN, b.INPUT_REMARKS, b.ENABLED, v_process, sysdate, null, null
				FROM INT_SAMAST_APPLN b 
				LEFT JOIN V_COMPANY_SERVICE vcs ON b.EOB_HT_CONSUMER_NUMBER =vcs."number" 
			WHERE  b.APPL_NO = I_APPLN_NO AND b.BATCH_KEY = I_BATCH_KEY;
			END IF; 

        end if; 
        O_RESULT_CODE:='SUCCESS';
        O_RESULT_DESC := v_message;         
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,I_BATCH_KEY,O_RESULT_CODE, O_RESULT_DESC);
    exception
        when others then
        v_stage:='EXCEPTION'; v_step:=''; 
		v_message := SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 200);
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,I_BATCH_KEY ||' - '||I_IMP_TYPE,O_RESULT_CODE, O_RESULT_DESC); 
    	O_RESULT_CODE:='FAILURE';
	    O_RESULT_DESC := v_message;
    END;--EXCEPTION ENDS HERE

   -- update T_PROCESS_GS set status='COMPLETED', remarks='PROCESSED-RECORD-COUNT:'||v_records_processed, end_dt=sysdate where id = v_process_id;

   	v_stage:='End'; v_message:=''; 
	v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,I_BATCH_KEY,O_RESULT_CODE, O_RESULT_DESC);
    COMMIT;
  END MERGE_EXT_RECORD;

  PROCEDURE CREATE_SELLER_NOC(I_BATCH_KEY IN VARCHAR2,I_APPLN_NO IN VARCHAR2,  I_SELLER_NO IN VARCHAR2, O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2) is  
	v_process varchar2(50):='IMP_SAMAST_APPLN_HELPER.CREATE_SELLER_NOC';
	v_process_type varchar2(500):='PROCEDURE';
	v_stage varchar2(500):='';
	v_step varchar2(500):='';
	v_message varchar2(500):='';
    v_log_result  varchar2(50);
   	v_row_count NUMBER:=0;
    v_pk varchar2(50):= '';
    v_period_start_date date;
    v_period_end_date date;
    v_fuel_group varchar2(100) :='';
  BEGIN
    BEGIN --EXCEPTION-HANDLER BLOCK STARTS HERE    

		v_stage:='INIT'; v_step:=''; v_message:='START'; 
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,I_BATCH_KEY,I_SELLER_NO, I_APPLN_NO);

        v_pk := t_sldc_noc_seq.nextval;

        Insert into T_SLDC_NOC (id, noc_purpose, NOC_ORIGIN,appln_type, app_category,category1, category2, COMP_SERV_NO , status,applied_no, APPLIED_DT,quantity,period_start_date,period_end_date, comms_adr,
        APPROV_PWR_EVAC_CAP ,comp_name,CUSTOMER_NAME ,FEEDER_WITH_VOLT_LEVEL ,SUBSTATION_WITH_VOLT_LEVEL ,cntrct_demand, enabled, created_by, created_dt  ) 
        select v_pk, 'Seller', EOR_REG_TYPE, app_type, app_category,category1, category2, I_SELLER_NO,'CREATED', appl_no, APPL_DATE,quantum,period_start_date,period_end_date, EOI_REG_NAME||' ,  Addr-'||EOI_REG_ADDRESS||',  MobNo-'||EOI_REG_MOBILE1_NO,
        EVACUATION_CAPACITY,ENTITY_INJ, CUSTOMER_NAME,EOS_FEEDER_NAME_SELLER ,EOS_INJ_SUBSTATION ,' ','Y',v_process, sysdate from int_samast_appln where batch_key=I_BATCH_KEY and appl_no = I_APPLN_NO and rownum = 1;
        UPDATE T_SLDC_NOC a
				SET ( a.COMP_SERV_ID, a.plant_adr,a.INSTALL_CAP_PLANT ,a.FUEL_TYPE ,a.COD_DT , a.ABY_METER_NAME ,a.METER_MF_NAME , a.region, a.edc, a.edc_id, a.modified_by, modified_dt,a.FUEL_GROUP_NAME) = 
				(SELECT  b.id, c.plant_addr,b.capacity, nvl(b.fuel_type_code,''),b.COMMISSION_DATE,b.METER_NUMBER,b.mf, e.parent_org_name, b.m_org_name, b.m_org_id,v_process,sysdate,b.FUEL_GROUP_NAME
					FROM   v_company_service b left join m_company_service c on  c.id=b.id left join M_POWERPLANT d on d.M_SERVICE_ID =b.id 
                    left join v_org e on e.org_id=b.M_ORG_ID WHERE  b.m_comp_serv_number = a.COMP_SERV_NO )
				WHERE  EXISTS (SELECT 1 FROM   v_company_service WHERE  a.id = v_pk and m_comp_serv_number =a.COMP_SERV_NO );     

        -- generating noc-code as part of a subquery was causing issues    
        update  T_SLDC_NOC a set noc_code = 'SEL-'||edc_id||'-'||t_sldc_noc_code_seq.nextval where  a.id =v_pk ;


        --------------- commitments--------------------
        select period_start_date,period_end_date  into v_period_start_date ,v_period_end_date from T_SLDC_NOC where id =v_pk and rownum=1 ;

        -- new commitments taken from the same table (int_samast_appln)
        Insert into T_SLDC_NOC_LINE (ID,T_SLDC_NOC_ID,COMP_SERV_NO,COMP_NAME,COMMIT_TYPE,AGGREMENT_TYPE,CUSTOMER_NAME,FROM_PERIOD,TO_PERIOD,QUANTUM,FLOW_TYPE,NO_OF_BUYER,IS_EXISTING,ENABLED,CREATED_BY,CREATED_DT ) 
        select T_SLDC_NOC_LINE_SEQ.nextval, v_pk,b.eob_ht_consumer_number, b.ENTITY_DRL, b.app_category, b.app_type, b.CUSTOMER_NAME,b.period_start_date, b.period_end_date, b.QUANTUM, b.category2, 0, 'N', 'Y', v_process, sysdate from int_samast_appln b where b.batch_key=I_BATCH_KEY and b.appl_no = I_APPLN_NO  and length(trim(nvl(b.ENTITY_DRL_EDC,'')))>0 ;

        -- existing commitments taken from the same table (ext_samast_appln_approval)
        Insert into T_SLDC_NOC_LINE (ID,T_SLDC_NOC_ID,COMP_SERV_NO,COMP_NAME,COMMIT_TYPE,AGGREMENT_TYPE,FROM_PERIOD,TO_PERIOD,QUANTUM,FLOW_TYPE,NO_OF_BUYER,IS_EXISTING,ENABLED,CREATED_BY,CREATED_DT ) 
          select T_SLDC_NOC_LINE_SEQ.nextval, v_pk,b.eob_ht_consumer_number, b.ENTITY_DRL, b.app_category, b.app_type, b.period_start_date, b.period_end_date, b.QUANTUM, b.category2, 0, 'Y', 'Y', v_process, sysdate 
    from ext_samast_appln_approval b 
    where b.EOS_GC_APPROVAL_NUMBER = I_SELLER_NO 
    and v_period_start_date between b.period_start_date and b.period_end_date or v_period_end_date between b.period_start_date and b.period_end_date  ;

        update INT_SAMAST_APPLN set seller_sldc_noc_id = v_pk where batch_key=I_BATCH_KEY and eos_gc_approval_number=I_SELLER_NO and appl_no = I_APPLN_NO;

        O_RESULT_CODE:='SUCCESS';
        O_RESULT_DESC := v_message;         
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,I_BATCH_KEY,O_RESULT_CODE, O_RESULT_DESC);
    exception
        when others then
        v_stage:='EXCEPTION'; v_step:=''; 
		v_message := SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 200);
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,I_BATCH_KEY ||' - '||I_SELLER_NO,O_RESULT_CODE, O_RESULT_DESC); 
    	O_RESULT_CODE:='FAILURE';
	    O_RESULT_DESC := v_message;
    END;--EXCEPTION ENDS HERE

   -- update T_PROCESS_GS set status='COMPLETED', remarks='PROCESSED-RECORD-COUNT:'||v_records_processed, end_dt=sysdate where id = v_process_id;

   	v_stage:='End'; v_message:=''; 
	v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,I_BATCH_KEY ||' - '||I_SELLER_NO,O_RESULT_CODE, O_RESULT_DESC);
    COMMIT;
  END CREATE_SELLER_NOC;

  PROCEDURE CREATE_BUYER_NOC(I_BATCH_KEY IN VARCHAR2,I_APPLN_NO IN VARCHAR2,  I_APPLN_REF_NO IN VARCHAR2, I_BUYER_NO IN VARCHAR2, O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2) is  
	v_process varchar2(50):='IMP_SAMAST_APPLN_HELPER.CREATE_BUYER_NOC';
	v_process_type varchar2(500):='PROCEDURE';
	v_stage varchar2(500):='';
	v_step varchar2(500):='';
	v_message varchar2(500):='';
    v_log_result  varchar2(50);
   	v_row_count NUMBER:=0;
    v_pk varchar2(50):= '';
    v_period_start_date date;
    v_period_end_date date;
    v_exist_total_quantum number;
    v_new_total_quantum number;
  BEGIN
    BEGIN --EXCEPTION-HANDLER BLOCK STARTS HERE    

		v_stage:='INIT'; v_step:=''; v_message:='START'; 
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,I_BATCH_KEY,I_BUYER_NO, I_APPLN_REF_NO);

        v_pk := t_sldc_noc_seq.nextval;

               Insert into T_SLDC_NOC (id, noc_purpose, NOC_ORIGIN,appln_type,APPL_REF_NO,app_category,CUSTOMER_NAME ,category1, category2, COMP_SERV_NO , status,applied_no, APPLIED_DT,quantity,period_start_date,period_end_date, comms_adr,comp_name,FEEDER_WITH_VOLT_LEVEL ,SUBSTATION_WITH_VOLT_LEVEL ,cntrct_demand,  enabled, created_by, created_dt  ) 
        select v_pk, 'Buyer', EOR_REG_TYPE, app_type,APPL_REF_NO,app_category,CUSTOMER_NAME,category1, category2, I_BUYER_NO,'CREATED', appl_no, APPL_DATE,quantum,period_start_date,period_end_date,EOI_REG_NAME||'\n Addr-'||EOI_REG_ADDRESS||'\n MobNo-'||EOI_REG_MOBILE1_NO,
        ENTITY_DRL, EOB_FEEDER_NAME_BUYER  ,EOB_DRA_SUBSTATION  ,' ','Y',v_process, sysdate from int_samast_appln where batch_key=I_BATCH_KEY and appl_ref_no = I_APPLN_REF_NO and rownum = 1;
        UPDATE T_SLDC_NOC a
				SET ( a.COMP_SERV_ID, a.APPROV_PWR_EVAC_CAP ,a.FUEL_TYPE ,a.COD_DT , a.ABY_METER_NAME ,a.METER_MF_NAME , a.region, a.edc, a.edc_id, a.modified_by, modified_dt,a.FUEL_GROUP_NAME) = 
				(SELECT  b.id,   b.capacity, nvl(b.fuel_type_code,''),b.COMMISSION_DATE,b.METER_NUMBER,b.mf, e.parent_org_name, b.m_org_name, b.m_org_id,v_process,sysdate,b.FUEL_GROUP_NAME 
					FROM   v_company_service b left join m_company_service c on  c.id=b.id left join M_POWERPLANT d on d.M_SERVICE_ID =b.id 
                    left join v_org e on e.org_id=b.M_ORG_ID WHERE  b.m_comp_serv_number = a.COMP_SERV_NO )
				WHERE  EXISTS (SELECT 1 FROM   v_company_service WHERE  a.id = v_pk and m_comp_serv_number =a.COMP_SERV_NO );     

        -- generating noc-code as part of a subquery was causing issues    
        update  T_SLDC_NOC a set noc_code = 'BUY-'||edc_id||'-'||t_sldc_noc_code_seq.nextval where  a.id =v_pk ;


        --------------- commitments--------------------
        select period_start_date,period_end_date  into v_period_start_date ,v_period_end_date from T_SLDC_NOC where id =v_pk and rownum=1 ;

        -- new commitments taken from the same table (int_samast_appln)
        Insert into T_SLDC_NOC_LINE (ID,T_SLDC_NOC_ID,COMP_SERV_NO,COMP_NAME,CUSTOMER_NAME ,COMMIT_TYPE,AGGREMENT_TYPE,FROM_PERIOD,TO_PERIOD,QUANTUM,FLOW_TYPE,NO_OF_BUYER,IS_EXISTING,ENABLED,CREATED_BY,CREATED_DT ) 
        select T_SLDC_NOC_LINE_SEQ.nextval, v_pk,b.eos_gc_approval_number, b.ENTITY_INJ,b.CUSTOMER_NAME,b.app_category, b.app_type, b.period_start_date, b.period_end_date, b.QUANTUM, b.category2, 0, 'N', 'Y', v_process, sysdate from int_samast_appln b where b.batch_key=I_BATCH_KEY and b.appl_no = I_APPLN_NO  and length(trim(nvl(b.ENTITY_INJ_EDC,'')))>0 ;

        -- existing commitments taken from the same table (ext_samast_appln_approval)
        -- start_date should be less than or equal to existing commitment's end-date
        Insert into T_SLDC_NOC_LINE (ID,T_SLDC_NOC_ID,COMP_SERV_NO,COMP_NAME,COMMIT_TYPE,AGGREMENT_TYPE,FROM_PERIOD,TO_PERIOD,QUANTUM,FLOW_TYPE,NO_OF_BUYER,IS_EXISTING,ENABLED,CREATED_BY,CREATED_DT ) 
        select T_SLDC_NOC_LINE_SEQ.nextval, v_pk,b.eos_gc_approval_number, b.ENTITY_INJ, b.app_category, b.app_type, b.period_start_date, b.period_end_date, b.QUANTUM, b.category2, 0, 'Y', 'Y', v_process, sysdate 
    from ext_samast_appln_approval b 
    where b.eob_ht_consumer_number = I_BUYER_NO
    and v_period_start_date between b.period_start_date and b.period_end_date or v_period_end_date between b.period_start_date and b.period_end_date  ;        /*
        select COALESCE( sum(nvl(quantum,0)), 0) into  v_exist_total_quantum from T_SLDC_NOC_LINE where  T_SLDC_NOC_ID= v_pk and IS_EXISTING='Y' group by T_SLDC_NOC_ID;
        select COALESCE( sum(nvl(quantum,0)), 0) into  v_new_total_quantum from T_SLDC_NOC_LINE where  T_SLDC_NOC_ID= v_pk   group by T_SLDC_NOC_ID;

        update  T_SLDC_NOC a set EXTG_TOTAL_COMMIT = v_exist_total_quantum, NEW_TOTAL_COMMIT = v_new_total_quantum where  id =v_pk ;
          */      
          
        v_stage:='DEBUG'; v_step:=''; v_message:=v_pk|| ' - ' ||I_BATCH_KEY|| ' - ' ||I_BUYER_NO|| ' - ' || I_APPLN_NO; 
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,I_BATCH_KEY,I_BUYER_NO, I_APPLN_REF_NO);
        update INT_SAMAST_APPLN set buyer_sldc_noc_id = v_pk where batch_key=I_BATCH_KEY and eob_ht_consumer_number=I_BUYER_NO and appl_no = I_APPLN_NO;

        O_RESULT_CODE:='SUCCESS';
        O_RESULT_DESC := v_message;         
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,I_BATCH_KEY,O_RESULT_CODE, O_RESULT_DESC);
    exception
        when others then
        v_stage:='EXCEPTION'; v_step:=''; 
		v_message := SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 200);
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,I_BATCH_KEY ||' - '||I_BUYER_NO,O_RESULT_CODE, O_RESULT_DESC); 
    	O_RESULT_CODE:='FAILURE';
	    O_RESULT_DESC := v_message;
    END;--EXCEPTION ENDS HERE

   -- update T_PROCESS_GS set status='COMPLETED', remarks='PROCESSED-RECORD-COUNT:'||v_records_processed, end_dt=sysdate where id = v_process_id;

   	v_stage:='End'; v_message:=''; 
	v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,I_BATCH_KEY ||' - '||I_BUYER_NO,O_RESULT_CODE, O_RESULT_DESC);
    COMMIT;
  END CREATE_BUYER_NOC;
END IMP_SAMAST_APPLN_HELPER;