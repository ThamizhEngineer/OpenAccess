-- adding circle-codes

ALTER TABLE EXT_SAMAST_APPLN
add ( 
IP_APPR_NO varchar(200),
SELLER_EDC_CODE varchar(200),
BUYER_EDC_CODE varchar(200),
APPROVAL_NO varchar(200)
);


ALTER TABLE EXT_SAMAST_APPLN_APPROVAL
add ( 
IP_APPR_NO varchar(200),
SELLER_EDC_CODE varchar(200),
BUYER_EDC_CODE varchar(200),
APPROVAL_NO varchar(200)
);

ALTER TABLE INT_SAMAST_APPLN
add ( 
IP_APPR_NO varchar(200),
SELLER_EDC_CODE varchar(200),
BUYER_EDC_CODE varchar(200),
APPROVAL_NO varchar(200)
);

-- also move definition and body of IMP_SAMAST_APPLN, IMP_SAMAST_APPLN_HELPER


create or replace PACKAGE IMP_SAMAST_APPLN AS 

PROCEDURE CLEANSE(I_BATCH_KEY IN VARCHAR2, I_APPLN_NO IN VARCHAR2, I_IMP_TYPE IN VARCHAR, O_IS_CLEAN  OUT VARCHAR2,O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2);
PROCEDURE PROCESS_BATCH(I_BATCH_KEY IN VARCHAR2, I_IMP_TYPE IN VARCHAR, O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2);
PROCEDURE PROCESS_APP(I_BATCH_KEY IN VARCHAR2, I_APPLN_NO IN VARCHAR2, I_IMP_TYPE IN VARCHAR, O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2);

END IMP_SAMAST_APPLN;

------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------
create or replace PACKAGE BODY IMP_SAMAST_APPLN AS



  PROCEDURE CLEANSE(I_BATCH_KEY IN VARCHAR2, I_APPLN_NO IN VARCHAR2, I_IMP_TYPE IN VARCHAR,O_IS_CLEAN  OUT VARCHAR2, O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2) is
	v_process varchar2(50):='IMP_SAMAST_APPLN.CLEANSE';
	v_process_type varchar2(500):='PROCEDURE';
	v_stage varchar2(500):='';
	v_step varchar2(500):='';
	v_message varchar2(500):='';
    v_log_result  varchar2(50);
    v_process_id  VARCHAR2(50);
    v_row_count number:=0;
    v_records_processed number:=0; 
	v_application_status varchar2(50):='';
    v_edc_status varchar2(50):='';
    v_is_clean char :='N';
    BEGIN
    BEGIN --EXCEPTION STARTS HERE
		v_stage:='INIT'; v_step:=''; v_message:='START'; 
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,I_BATCH_KEY,I_IMP_TYPE, I_APPLN_NO);
		if (I_BATCH_KEY is null or trim(I_BATCH_KEY) = '' or I_IMP_TYPE is null or trim(I_IMP_TYPE) = '')then
            Raise_Application_Error (-20343, 'Batch and ImpType are mandatory  '); 
        end if;
        if(I_IMP_TYPE not in('APPLICATION','APPROVAL')) then
            Raise_Application_Error (-20343, 'Unsupported ImpType - '||I_IMP_TYPE); 
        end if;
       if (I_IMP_TYPE = 'APPLICATION')then
          -- SELECT count(id) INTO v_row_count FROM int_samast_appln where batch_key=I_BATCH_KEY AND appl_no = I_APPLN_NO AND rownum =1 AND upper(edc_status) != 'WAITING';
          -- IF(v_row_count > 0) THEN Raise_Application_Error (-20343, 'APPLICATION processing already startd/completed so doesnt need processing'); END IF;
           
           SELECT upper(edc_status) INTO v_edc_status FROM int_samast_appln where batch_key=I_BATCH_KEY AND appl_no = I_APPLN_NO AND rownum =1 ;
           IF(v_edc_status !='WAITING') THEN Raise_Application_Error (-20343, 'APPLICATION not in WAITING status'); END IF; 
           SELECT count(id) INTO v_row_count FROM EXT_SAMAST_APPLN esa WHERE appl_no = I_APPLN_NO;
           IF(v_row_count > 0) THEN Raise_Application_Error (-20343, 'ALREADY EXISTS IN APPLICATION TABLE'); END IF;          
        ELSIF (I_IMP_TYPE = 'APPROVAL')then
           select  upper(application_status) into v_application_status FROM int_samast_appln where batch_key=I_BATCH_KEY AND appl_no = I_APPLN_NO AND rownum =1;           
           IF(v_application_status !='ACCEPTED') THEN Raise_Application_Error (-20343, 'APPROVAL not in ACCEPTED status'); END IF; 
           SELECT count(id) INTO v_row_count FROM EXT_SAMAST_APPLN_APPROVAL esa WHERE appl_no = I_APPLN_NO;
           IF(v_row_count > 0) THEN Raise_Application_Error (-20343, 'ALREADY EXISTS IN APPROVAL TABLE'); END IF;         
        end if;


		for appLine in(select coalesce(nvl(BUYER_EDC_CODE,'X'),'','X',BUYER_EDC_CODE ) BUYER_EDC_CODE,coalesce(nvl(SELLER_EDC_CODE,'X'),'','X',SELLER_EDC_CODE )  SELLER_EDC_CODE,eos_gc_approval_number,eob_ht_consumer_number, appl_ref_no from int_samast_appln where batch_key=I_BATCH_KEY AND appl_no = I_APPLN_NO  )
        loop
            
            if(length(trim(appline.BUYER_EDC_CODE))<=1) then --buyer-edc is not empty
                select count(*) into v_row_count from v_org where upper(org_code) = upper(appLine.BUYER_EDC_CODE);
                IF(v_row_count = 0) THEN Raise_Application_Error (-20343, 'Unknown Buyer EDC(BUYER_EDC_CODE:'||appLine.BUYER_EDC_CODE||') in appl_no-'||appLine.appl_ref_no); END IF;
            end if;
            if(length(trim(appline.SELLER_EDC_CODE))<=1)  then --seller-edc is not empty
                select count(*) into v_row_count from v_org where upper(org_code) = upper(appLine.SELLER_EDC_CODE);
                IF(v_row_count = 0) THEN Raise_Application_Error (-20343, 'Unknown Seller EDC(SELLER_EDC_CODE:'||appLine.SELLER_EDC_CODE||') in appl_no-'||appLine.appl_ref_no); END IF;
            end if;
            if(length(trim(nvl(appline.eos_gc_approval_number,'X')))<=1)  then --seller-number is not empty
                select count(*) into v_row_count from v_company_service where m_comp_serv_number = appLine.eos_gc_approval_number;
                IF(v_row_count = 0) THEN Raise_Application_Error (-20343, 'Unknown Seller Number(eos_gc_approval_number:'||appLine.eos_gc_approval_number||') in appl_no-'||appLine.appl_ref_no); END IF;
            end if;
            if(length(trim(nvl(appline.eob_ht_consumer_number,'X')))<=1) then --buyer-number is not empty
                select count(*) into v_row_count from v_company_service where m_comp_serv_number = appLine.eob_ht_consumer_number;
                IF(v_row_count = 0) THEN Raise_Application_Error (-20343, 'Unknown Buyer Number(eob_ht_consumer_number:'||appLine.eob_ht_consumer_number||') in appl_no-'||appLine.appl_ref_no); END IF;                                
            end if; 
        end loop; 

        O_RESULT_CODE:='SUCCESS';
        O_RESULT_DESC := '';
    exception
        when others then
        v_stage:='EXCEPTION'; v_step:=''; 
		v_message := SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 200);
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,I_BATCH_KEY ||' - '||I_APPLN_NO ||' - '||I_IMP_TYPE,O_RESULT_CODE, O_RESULT_DESC); 
    	O_RESULT_CODE:='FAILURE';
	    O_RESULT_DESC := v_message;
    END;--EXCEPTION ENDS HERE

	<<THE_END>>
    if(O_RESULT_CODE ='FAILURE') then v_is_clean:='N'; else v_is_clean:='Y'; end if;
    update int_samast_appln set is_clean=v_is_clean, process_remarks=O_RESULT_DESC, modified_by=v_process, modified_dt=sysdate
        where batch_key=I_BATCH_KEY AND appl_no = I_APPLN_NO ;
    O_IS_CLEAN :=v_is_clean;
    v_stage:='End'; v_message:='I_APPLN_NO-'||I_APPLN_NO; 
	v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,I_BATCH_KEY ||' - '||I_APPLN_NO ||' - '||I_IMP_TYPE,O_RESULT_CODE, O_RESULT_DESC);
    COMMIT;

  END CLEANSE;


  PROCEDURE PROCESS_BATCH(I_BATCH_KEY IN VARCHAR2, I_IMP_TYPE IN VARCHAR, O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2) IS

    v_process varchar2(50):='IMP_SAMAST_APPLN.PROCESS_BATCH';
	v_process_type varchar2(500):='PROCEDURE';
	v_stage varchar2(500):='';
	v_step varchar2(500):='';
	v_message varchar2(500):='';
    v_log_result  varchar2(50);
    v_process_id  VARCHAR2(50);
    v_records_processed number:=0; 
    v_result_code varchar2(50):='';
    v_result_desc varchar2(2000):='';
    v_ignore_reason varchar2(200):='';
    v_row_count number:=0;
    v_appl_ref_no varchar2(100):='';
    v_appl_no varchar2(100):='';
    v_success_app_count number:=0;
    v_failure_app_count number:=0;
    BEGIN
    BEGIN --EXCEPTION STARTS HERE

        O_RESULT_CODE:='';
        v_stage:='INIT'; v_step:=''; v_message:='START'; 
        v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,I_BATCH_KEY,I_IMP_TYPE, '');
        
        update int_samast_appln set is_clean = 'X' , process_remarks=v_ignore_reason  where batch_key=I_BATCH_KEY;
        
        if (I_BATCH_KEY is null or trim(I_BATCH_KEY) = '' or I_IMP_TYPE is null or trim(I_IMP_TYPE) = '')then
            Raise_Application_Error (-20343, 'Batch and ImpType are mandatory  '); 
        end if;
        if(I_IMP_TYPE not in('APPLICATION','APPROVAL')) then
            Raise_Application_Error (-20343, 'Unsupported ImpType - '||I_IMP_TYPE); 
        end if;

        for ignoreList in(select distinct APPL_REF_NO,edc_status, application_status  from int_samast_appln where batch_key=I_BATCH_KEY )
        loop
        begin 
            v_appl_ref_no:=ignoreList.APPL_REF_NO;
            v_ignore_reason := 'X';
            if( I_IMP_TYPE='APPLICATION') then
                if(upper(ignoreList.edc_status) <>'WAITING')then 
                    v_ignore_reason := 'edc_status is not WAITING';      
                end if;
            end if;
            IF(I_IMP_TYPE='APPROVAL') then
                if(upper(ignoreList.application_status)<>'ACCEPTED')then 
                    v_ignore_reason := 'application_status is not ACCEPTED';                
                end if;
            end if; 
            if(length(v_ignore_reason) > 1) then 
                v_failure_app_count := v_failure_app_count+1; 
                update int_samast_appln set is_clean = 'N' , process_remarks=v_ignore_reason  where batch_key=I_BATCH_KEY and APPL_REF_NO = ignoreList.APPL_REF_NO;
            end if;
        exception
            when others then
            v_stage:='ignoreList - EXCEPTION'; v_step:='APPL_REF_NO - '||v_appl_ref_no; 
            v_message := SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 200) ;
            v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,I_BATCH_KEY ||' - '||I_IMP_TYPE,O_RESULT_CODE, O_RESULT_DESC); 
        END;--OUTER-LOOP EXCEPTION ENDS HERE    
        end loop;  
        
       -- CLEANSE(I_BATCH_KEY, I_BATCH_KEY,I_IMP_TYPE, O_RESULT_CODE, O_RESULT_DESC);        
        for applist in(select distinct appl_no, appl_date from int_samast_appln where batch_key=I_BATCH_KEY and is_clean= 'X'
                                                        order by appl_date)
        loop
        begin 
            v_appl_no:=applist.appl_no;
            PROCESS_APP(I_BATCH_KEY , applist.appl_no, I_IMP_TYPE, v_result_code , v_result_desc);
            if(v_result_code='SUCCESS')then 
                v_success_app_count := v_success_app_count+1;
            else
                v_failure_app_count := v_failure_app_count+1;            
            end if;

        exception
            when others then
            v_stage:='applist - EXCEPTION'; v_step:='APPL_NO - '||v_appl_no; 
            v_message := SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 200) ;
            v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,I_BATCH_KEY ||' - '||I_IMP_TYPE,O_RESULT_CODE, O_RESULT_DESC); 
        END;--OUTER-LOOP EXCEPTION ENDS HERE    
        end loop;  

        O_RESULT_CODE:='SUCCESS';
        O_RESULT_DESC := '';
    exception
        when others then
        v_stage:='EXCEPTION'; v_step:=''; 
		v_message := SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 200);
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,I_BATCH_KEY ||' - '||I_IMP_TYPE,O_RESULT_CODE, O_RESULT_DESC); 
    	O_RESULT_CODE:='FAILURE';
	    O_RESULT_DESC := v_message;
    END;--EXCEPTION ENDS HERE

    
   	v_stage:='End'; v_message:='(success-count:'||v_success_app_count||', '||'failure-count:'||v_failure_app_count||')'; 
    update l_imp_samast_app set remarks=v_message where batch_key = I_BATCH_KEY;
    <<THE_END>>
    O_RESULT_DESC:= v_message;
	v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,I_BATCH_KEY,O_RESULT_CODE, O_RESULT_DESC);    
    COMMIT;

  END PROCESS_BATCH;

  PROCEDURE PROCESS_APP(I_BATCH_KEY IN VARCHAR2, I_APPLN_NO IN VARCHAR2, I_IMP_TYPE IN VARCHAR, O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2) IS
	v_process varchar2(50):='IMP_SAMAST_APPLN.PROCESS_APP';
	v_process_type varchar2(500):='PROCEDURE';
	v_stage varchar2(500):='';
	v_step varchar2(500):='';
	v_message varchar2(500):='';
    v_log_result  varchar2(50);
    v_process_id  VARCHAR2(50);
    v_records_processed number:=0; 
    v_row_count number:=0;
    v_is_clean varchar2(50):='';
    v_result_code varchar2(50):='';
    v_result_desc varchar2(2000):='';
    v_seller_no varchar2(50):='';
    v_seller_edc varchar2(100):='';
    v_buyer_no varchar2(50):='';
    v_buyer_edc varchar2(100):='';
    v_appl_ref_no varchar2(1000):='';
    BEGIN
    BEGIN --EXCEPTION HANDLER STARTS HERE
    	O_RESULT_CODE:='SUCCESS';
		v_stage:='INIT'; v_step:=''; v_message:='START'; 
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,I_BATCH_KEY,I_IMP_TYPE, I_APPLN_NO);

        CLEANSE(I_BATCH_KEY, I_APPLN_NO,I_IMP_TYPE,v_is_clean, v_result_code, v_result_desc);
        if(v_result_code = 'FAILURE')then 
            Raise_Application_Error (-20343, v_result_desc); 
        elsif(v_is_clean = 'Y' and i_imp_type='APPLICATION') then
            select trim(nvl(SELLER_EDC_CODE,'')), eos_gc_approval_number into v_seller_edc,v_seller_no from int_samast_appln where batch_key=I_BATCH_KEY AND appl_no = I_APPLN_NO and is_clean ='Y' AND rownum =1;
            v_stage:='PROCESS'; v_step:=''; v_message:=v_seller_edc; 
        	v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,I_BATCH_KEY,I_IMP_TYPE, I_APPLN_NO);

            if(length(v_seller_edc)>0) then
               v_stage:='PROCESS'; v_step:=''; v_message:='calling s noc'; 
                v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,I_BATCH_KEY,I_IMP_TYPE, I_APPLN_NO);
                imp_samast_appln_helper.CREATE_SELLER_NOC(i_batch_key, i_appln_no,v_seller_no,O_RESULT_CODE, O_RESULT_DESC);
            end if;
            for appLine in (select eob_ht_consumer_number,appl_ref_no from int_samast_appln where is_clean ='Y' and  batch_key=I_BATCH_KEY AND appl_no = I_APPLN_NO and length(trim(nvl(BUYER_EDC_CODE,'')))>0)
            loop
               imp_samast_appln_helper.CREATE_BUYER_NOC(i_batch_key, i_appln_no,appLine.appl_ref_no,appLine.eob_ht_consumer_number,O_RESULT_CODE, O_RESULT_DESC);
            end loop;
        end if; 
        O_RESULT_CODE:='SUCCESS';
        O_RESULT_DESC := '';

    exception
        when others then
        v_stage:='EXCEPTION'; v_step:=''; 
		v_message := SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 200);
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,I_BATCH_KEY ||' - '||I_APPLN_NO ||' - '||I_IMP_TYPE,O_RESULT_CODE, O_RESULT_DESC); 
    	O_RESULT_CODE:='FAILURE';
	    O_RESULT_DESC := v_message;
    END;--EXCEPTION ENDS HERE
    --if(O_RESULT_CODE ='SUCCESS' or v_message like '%ALREADY EXISTS%') then
    if(O_RESULT_CODE ='SUCCESS') then
     imp_samast_appln_helper.merge_ext_record(i_batch_key, i_appln_no, i_imp_type,O_RESULT_CODE, O_RESULT_DESC);
    end if;

   	v_stage:='End'; v_message:=''; 
    <<THE_END>>
	v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,I_BATCH_KEY ||' - '||I_APPLN_NO ||' - '||I_IMP_TYPE,O_RESULT_CODE, O_RESULT_DESC);
    COMMIT;

  END PROCESS_APP;

END IMP_SAMAST_APPLN;
------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------
create or replace PACKAGE IMP_SAMAST_APPLN_HELPER AS 

PROCEDURE MERGE_EXT_RECORD(I_BATCH_KEY IN VARCHAR2,I_APPLN_NO IN VARCHAR2,  I_IMP_TYPE IN VARCHAR, O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2);
PROCEDURE CREATE_SELLER_NOC(I_BATCH_KEY IN VARCHAR2,I_APPLN_NO IN VARCHAR2,  I_SELLER_NO IN VARCHAR2, O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2);
PROCEDURE CREATE_BUYER_NOC(I_BATCH_KEY IN VARCHAR2,I_APPLN_NO IN VARCHAR2, I_APPLN_REF_NO IN VARCHAR2,  I_BUYER_NO IN VARCHAR2, O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2);
END IMP_SAMAST_APPLN_HELPER;
------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------
create or replace PACKAGE BODY IMP_SAMAST_APPLN_HELPER AS

  PROCEDURE MERGE_EXT_RECORD(I_BATCH_KEY IN VARCHAR2,I_APPLN_NO IN VARCHAR2,  I_IMP_TYPE IN VARCHAR, O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2) is  
	v_process varchar2(50):='IMP_SAMAST_APPLN_HELPER.MERGE_EXT_RECORD';
	v_process_type varchar2(500):='PROCEDURE';
	v_stage varchar2(500):='';
	v_step varchar2(500):='';
	v_message varchar2(500):='';
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
				SET 	(a.ID, a.APP_ID, a.APPL_REF_NO, a.APPL_NO, a.APPL_DATE, a.APP_CATEGORY, a.CUSTOMER_NAME, a.ENTITY_INJ, a.ENTITY_INJ_EDC, a.ENTITY_DRL, a.ENTITY_DRL_EDC, a.PERIOD_START_DATE, a.PERIOD_END_DATE, a.QUANTUM, a.CATEGORY1, a.CATEGORY2, a.EOR_REG_TYPE, a.EOS_GC_APPROVAL_NUMBER, a.EOS_FEEDER_NAME_SELLER, a.EOS_VOL_LVL_FEEDER, a.EOS_INJ_SUBSTATION, a.EOS_VOL_LVL_SUBSTATION, a.EOC_LOSS_PER, a.EVACUATION_CAPACITY, a.EOS_UTIL_INJ_EMBED, a.EOS_UTIL_INJ_EMBED_LABEL, a.EOB_UTIL_DRAWAL_EMBED_LABEL, a.EOB_UTIL_DRA_EMBED, a.EOB_HT_CONSUMER_NUMBER, a.EOB_FEEDER_NAME_BUYER, a.EOB_VOL_LVL_FEEDER, a.EOB_DRA_SUBSTATION, a.EOB_VOL_LVL_SUBSTATION, a.EOB_DRAWAL_LIMIT, a.EOB_BUYER_TYPE, a.EOI_REG_NAME, a.EOI_REG_ADDRESS, a.EOI_REG_MOBILE1_NO, a.EDC_STATUS, a.APPLICATION_STATUS, a.APP_TYPE, a.BATCH_KEY, a.IS_CLEAN, a.INPUT_REMARKS, a.ENABLED,   a.MODIFIED_BY, a.MODIFIED_DT) = 
				(SELECT a.ID, b.APP_ID, b.APPL_REF_NO, b.APPL_NO, b.APPL_DATE, b.APP_CATEGORY, b.CUSTOMER_NAME, b.ENTITY_INJ, b.ENTITY_INJ_EDC, b.ENTITY_DRL, b.ENTITY_DRL_EDC, b.PERIOD_START_DATE, b.PERIOD_END_DATE, b.QUANTUM, b.CATEGORY1, b.CATEGORY2, b.EOR_REG_TYPE, b.EOS_GC_APPROVAL_NUMBER, b.EOS_FEEDER_NAME_SELLER, b.EOS_VOL_LVL_FEEDER, b.EOS_INJ_SUBSTATION, b.EOS_VOL_LVL_SUBSTATION, b.EOC_LOSS_PER, b.EVACUATION_CAPACITY, b.EOS_UTIL_INJ_EMBED, b.EOS_UTIL_INJ_EMBED_LABEL, b.EOB_UTIL_DRAWAL_EMBED_LABEL, b.EOB_UTIL_DRA_EMBED, b.EOB_HT_CONSUMER_NUMBER, b.EOB_FEEDER_NAME_BUYER, b.EOB_VOL_LVL_FEEDER, b.EOB_DRA_SUBSTATION, b.EOB_VOL_LVL_SUBSTATION, b.EOB_DRAWAL_LIMIT, b.EOB_BUYER_TYPE, b.EOI_REG_NAME, b.EOI_REG_ADDRESS, b.EOI_REG_MOBILE1_NO, b.EDC_STATUS, b.APPLICATION_STATUS, b.APP_TYPE, b.BATCH_KEY, b.IS_CLEAN, b.INPUT_REMARKS, b.ENABLED,   v_process, sysdate
					FROM   INT_SAMAST_APPLN b WHERE  b.APPL_NO = a.APPL_NO  AND b.APPL_REF_NO = a.APPL_REF_NO AND b.BATCH_KEY = I_BATCH_KEY)
				WHERE  EXISTS (SELECT 1 FROM   INT_SAMAST_APPLN WHERE  APPL_NO = a.APPL_NO  AND APPL_REF_NO = a.APPL_REF_NO AND BATCH_KEY = I_BATCH_KEY);			
			ELSE
				v_stage:='PROCESS'; v_message:='Data inserted to EXT_SAMAST_APPLN';
				INSERT INTO EXT_SAMAST_APPLN (ID, APP_ID, APPL_REF_NO, APPL_NO, APPL_DATE, APP_CATEGORY, CUSTOMER_NAME, ENTITY_INJ, ENTITY_INJ_EDC, ENTITY_DRL, ENTITY_DRL_EDC, PERIOD_START_DATE, PERIOD_END_DATE, QUANTUM, CATEGORY1, CATEGORY2, EOR_REG_TYPE, EOS_GC_APPROVAL_NUMBER, EOS_FEEDER_NAME_SELLER, EOS_VOL_LVL_FEEDER, EOS_INJ_SUBSTATION, EOS_VOL_LVL_SUBSTATION, EOC_LOSS_PER, EVACUATION_CAPACITY, EOS_UTIL_INJ_EMBED, EOS_UTIL_INJ_EMBED_LABEL, EOB_UTIL_DRAWAL_EMBED_LABEL, EOB_UTIL_DRA_EMBED, EOB_HT_CONSUMER_NUMBER, EOB_FEEDER_NAME_BUYER, EOB_VOL_LVL_FEEDER, EOB_DRA_SUBSTATION, EOB_VOL_LVL_SUBSTATION, EOB_DRAWAL_LIMIT, EOB_BUYER_TYPE, EOI_REG_NAME, EOI_REG_ADDRESS, EOI_REG_MOBILE1_NO, EDC_STATUS, APPLICATION_STATUS, APP_TYPE, BATCH_KEY, IS_CLEAN, INPUT_REMARKS, ENABLED, CREATED_BY, CREATED_DT, MODIFIED_BY, MODIFIED_DT)
				SELECT EXT_SAMAST_APPLN_SEQ.nextval, b.APP_ID, b.APPL_REF_NO, b.APPL_NO, b.APPL_DATE, b.APP_CATEGORY, b.CUSTOMER_NAME, b.ENTITY_INJ, b.ENTITY_INJ_EDC, b.ENTITY_DRL, b.ENTITY_DRL_EDC, b.PERIOD_START_DATE, b.PERIOD_END_DATE, b.QUANTUM, b.CATEGORY1, b.CATEGORY2, b.EOR_REG_TYPE, b.EOS_GC_APPROVAL_NUMBER, b.EOS_FEEDER_NAME_SELLER, b.EOS_VOL_LVL_FEEDER, b.EOS_INJ_SUBSTATION, b.EOS_VOL_LVL_SUBSTATION, b.EOC_LOSS_PER, b.EVACUATION_CAPACITY, b.EOS_UTIL_INJ_EMBED, b.EOS_UTIL_INJ_EMBED_LABEL, b.EOB_UTIL_DRAWAL_EMBED_LABEL, b.EOB_UTIL_DRA_EMBED, b.EOB_HT_CONSUMER_NUMBER, b.EOB_FEEDER_NAME_BUYER, b.EOB_VOL_LVL_FEEDER, b.EOB_DRA_SUBSTATION, b.EOB_VOL_LVL_SUBSTATION, b.EOB_DRAWAL_LIMIT, b.EOB_BUYER_TYPE, b.EOI_REG_NAME, b.EOI_REG_ADDRESS, b.EOI_REG_MOBILE1_NO, b.EDC_STATUS, b.APPLICATION_STATUS, b.APP_TYPE, b.BATCH_KEY, b.IS_CLEAN, b.INPUT_REMARKS, b.ENABLED, v_process, sysdate, null, null
				FROM INT_SAMAST_APPLN b WHERE  b.APPL_NO = I_APPLN_NO AND b.BATCH_KEY = I_BATCH_KEY;
			END IF; 

        ELSE
			SELECT count(id) INTO v_row_count FROM EXT_SAMAST_APPLN_APPROVAL esaa WHERE appl_no = I_APPLN_NO;
			if(v_row_count>0) THEN  
				v_stage:='PROCESS'; v_message:='Data updated to EXT_SAMAST_APPLN_APPROVAL';
				UPDATE EXT_SAMAST_APPLN_APPROVAL a
				SET 	(a.ID, a.APP_ID, a.APPL_REF_NO, a.APPL_NO, a.APPL_DATE, a.APP_CATEGORY, a.CUSTOMER_NAME, a.ENTITY_INJ, a.ENTITY_INJ_EDC, a.ENTITY_DRL, a.ENTITY_DRL_EDC, a.PERIOD_START_DATE, a.PERIOD_END_DATE, a.QUANTUM, a.CATEGORY1, a.CATEGORY2, a.EOR_REG_TYPE, a.EOS_GC_APPROVAL_NUMBER, a.EOS_FEEDER_NAME_SELLER, a.EOS_VOL_LVL_FEEDER, a.EOS_INJ_SUBSTATION, a.EOS_VOL_LVL_SUBSTATION, a.EOC_LOSS_PER, a.EVACUATION_CAPACITY, a.EOS_UTIL_INJ_EMBED, a.EOS_UTIL_INJ_EMBED_LABEL, a.EOB_UTIL_DRAWAL_EMBED_LABEL, a.EOB_UTIL_DRA_EMBED, a.EOB_HT_CONSUMER_NUMBER, a.EOB_FEEDER_NAME_BUYER, a.EOB_VOL_LVL_FEEDER, a.EOB_DRA_SUBSTATION, a.EOB_VOL_LVL_SUBSTATION, a.EOB_DRAWAL_LIMIT, a.EOB_BUYER_TYPE, a.EOI_REG_NAME, a.EOI_REG_ADDRESS, a.EOI_REG_MOBILE1_NO, a.EDC_STATUS, a.APPLICATION_STATUS, a.APP_TYPE, a.BATCH_KEY, a.IS_CLEAN, a.INPUT_REMARKS, a.ENABLED,  a.MODIFIED_BY, a.MODIFIED_DT) = 
				(SELECT a.ID, b.APP_ID, b.APPL_REF_NO, b.APPL_NO, b.APPL_DATE, b.APP_CATEGORY, b.CUSTOMER_NAME, b.ENTITY_INJ, b.ENTITY_INJ_EDC, b.ENTITY_DRL, b.ENTITY_DRL_EDC, b.PERIOD_START_DATE, b.PERIOD_END_DATE, b.QUANTUM, b.CATEGORY1, b.CATEGORY2, b.EOR_REG_TYPE, b.EOS_GC_APPROVAL_NUMBER, b.EOS_FEEDER_NAME_SELLER, b.EOS_VOL_LVL_FEEDER, b.EOS_INJ_SUBSTATION, b.EOS_VOL_LVL_SUBSTATION, b.EOC_LOSS_PER, b.EVACUATION_CAPACITY, b.EOS_UTIL_INJ_EMBED, b.EOS_UTIL_INJ_EMBED_LABEL, b.EOB_UTIL_DRAWAL_EMBED_LABEL, b.EOB_UTIL_DRA_EMBED, b.EOB_HT_CONSUMER_NUMBER, b.EOB_FEEDER_NAME_BUYER, b.EOB_VOL_LVL_FEEDER, b.EOB_DRA_SUBSTATION, b.EOB_VOL_LVL_SUBSTATION, b.EOB_DRAWAL_LIMIT, b.EOB_BUYER_TYPE, b.EOI_REG_NAME, b.EOI_REG_ADDRESS, b.EOI_REG_MOBILE1_NO, b.EDC_STATUS, b.APPLICATION_STATUS, b.APP_TYPE, b.BATCH_KEY, b.IS_CLEAN, b.INPUT_REMARKS, b.ENABLED,   v_process, sysdate
					FROM   INT_SAMAST_APPLN b WHERE  b.APPL_NO = a.APPL_NO  AND b.APPL_REF_NO = a.APPL_REF_NO AND b.BATCH_KEY = I_BATCH_KEY)
				WHERE  EXISTS (SELECT 1 FROM   INT_SAMAST_APPLN WHERE  APPL_NO = a.APPL_NO  AND APPL_REF_NO = a.APPL_REF_NO AND BATCH_KEY = I_BATCH_KEY);			
			ELSE
				v_stage:='PROCESS'; v_message:='Data inserted to EXT_SAMAST_APPLN_APPROVAL';
				INSERT INTO EXT_SAMAST_APPLN_APPROVAL (ID, APP_ID, APPL_REF_NO, APPL_NO, APPL_DATE, APP_CATEGORY, CUSTOMER_NAME, ENTITY_INJ, ENTITY_INJ_EDC, ENTITY_DRL, ENTITY_DRL_EDC, PERIOD_START_DATE, PERIOD_END_DATE, QUANTUM, CATEGORY1, CATEGORY2, EOR_REG_TYPE, EOS_GC_APPROVAL_NUMBER, EOS_FEEDER_NAME_SELLER, EOS_VOL_LVL_FEEDER, EOS_INJ_SUBSTATION, EOS_VOL_LVL_SUBSTATION, EOC_LOSS_PER, EVACUATION_CAPACITY, EOS_UTIL_INJ_EMBED, EOS_UTIL_INJ_EMBED_LABEL, EOB_UTIL_DRAWAL_EMBED_LABEL, EOB_UTIL_DRA_EMBED, EOB_HT_CONSUMER_NUMBER, EOB_FEEDER_NAME_BUYER, EOB_VOL_LVL_FEEDER, EOB_DRA_SUBSTATION, EOB_VOL_LVL_SUBSTATION, EOB_DRAWAL_LIMIT, EOB_BUYER_TYPE, EOI_REG_NAME, EOI_REG_ADDRESS, EOI_REG_MOBILE1_NO, EDC_STATUS, APPLICATION_STATUS, APP_TYPE, BATCH_KEY, IS_CLEAN, INPUT_REMARKS, ENABLED, CREATED_BY, CREATED_DT, MODIFIED_BY, MODIFIED_DT)
				SELECT EXT_SAMAST_APPLN_APPROVAL_SEQ.nextval, b.APP_ID, b.APPL_REF_NO, b.APPL_NO, b.APPL_DATE, b.APP_CATEGORY, b.CUSTOMER_NAME, b.ENTITY_INJ, b.ENTITY_INJ_EDC, b.ENTITY_DRL, b.ENTITY_DRL_EDC, b.PERIOD_START_DATE, b.PERIOD_END_DATE, b.QUANTUM, b.CATEGORY1, b.CATEGORY2, b.EOR_REG_TYPE, b.EOS_GC_APPROVAL_NUMBER, b.EOS_FEEDER_NAME_SELLER, b.EOS_VOL_LVL_FEEDER, b.EOS_INJ_SUBSTATION, b.EOS_VOL_LVL_SUBSTATION, b.EOC_LOSS_PER, b.EVACUATION_CAPACITY, b.EOS_UTIL_INJ_EMBED, b.EOS_UTIL_INJ_EMBED_LABEL, b.EOB_UTIL_DRAWAL_EMBED_LABEL, b.EOB_UTIL_DRA_EMBED, b.EOB_HT_CONSUMER_NUMBER, b.EOB_FEEDER_NAME_BUYER, b.EOB_VOL_LVL_FEEDER, b.EOB_DRA_SUBSTATION, b.EOB_VOL_LVL_SUBSTATION, b.EOB_DRAWAL_LIMIT, b.EOB_BUYER_TYPE, b.EOI_REG_NAME, b.EOI_REG_ADDRESS, b.EOI_REG_MOBILE1_NO, b.EDC_STATUS, b.APPLICATION_STATUS, b.APP_TYPE, b.BATCH_KEY, b.IS_CLEAN, b.INPUT_REMARKS, b.ENABLED, v_process, sysdate, null, null
				FROM INT_SAMAST_APPLN b WHERE  b.APPL_NO = I_APPLN_NO AND b.BATCH_KEY = I_BATCH_KEY;
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

        Insert into T_SLDC_NOC (id, noc_purpose, NOC_ORIGIN,appln_type,app_category,category1, category2, COMP_SERV_NO , status,applied_no, APPLIED_DT,quantity,period_start_date,period_end_date, comms_adr,
        INSTALL_CAP_PLANT ,comp_name, FEEDER_WITH_VOLT_LEVEL ,SUBSTATION_WITH_VOLT_LEVEL ,cntrct_demand, enabled, created_by, created_dt  ) 
        select v_pk, 'Seller', EOR_REG_TYPE, app_type,app_category,category1, category2, I_SELLER_NO,'CREATED', appl_no, APPL_DATE,quantum,period_start_date,period_end_date, EOI_REG_NAME||'\n Addr-'||EOI_REG_ADDRESS||'\n MobNo-'||EOI_REG_MOBILE1_NO,
        EVACUATION_CAPACITY,ENTITY_INJ,  EOS_FEEDER_NAME_SELLER ,EOS_INJ_SUBSTATION ,' ','Y',v_process, sysdate from int_samast_appln where batch_key=I_BATCH_KEY and appl_no = I_APPLN_NO and rownum = 1;

        UPDATE T_SLDC_NOC a
				SET ( a.COMP_SERV_ID, a.plant_adr,a.APPROV_PWR_EVAC_CAP ,a.FUEL_TYPE ,a.COD_DT , a.ABY_METER_NAME ,a.METER_MF_NAME , a.region, a.edc, a.edc_id, a.modified_by, modified_dt) = 
				(SELECT  b.id, c.plant_addr,b.capacity, nvl(b.fuel_type_code,''),b.COMMISSION_DATE,b.METER_NUMBER,b.mf, e.parent_org_name, b.m_org_name, b.m_org_id,v_process,sysdate
					FROM   v_company_service b left join m_company_service c on  c.id=b.id left join M_POWERPLANT d on d.M_SERVICE_ID =b.id 
                    left join v_org e on e.org_id=b.M_ORG_ID WHERE  b.m_comp_serv_number = a.COMP_SERV_NO )
				WHERE  EXISTS (SELECT 1 FROM   v_company_service WHERE  a.id = v_pk and m_comp_serv_number =a.COMP_SERV_NO );     

        -- generating noc-code as part of a subquery was causing issues    
        update  T_SLDC_NOC a set noc_code = 'SEL-'||edc_id||'-'||t_sldc_noc_code_seq.nextval where  a.id =v_pk ;


        --------------- commitments--------------------
        select period_start_date,period_end_date  into v_period_start_date ,v_period_end_date from T_SLDC_NOC where id =v_pk and rownum=1 ;

        -- new commitments taken from the same table (int_samast_appln)
        Insert into T_SLDC_NOC_LINE (ID,T_SLDC_NOC_ID,COMP_SERV_NO,COMP_NAME,COMMIT_TYPE,AGGREMENT_TYPE,FROM_PERIOD,TO_PERIOD,QUANTUM,FLOW_TYPE,NO_OF_BUYER,IS_EXISTING,ENABLED,CREATED_BY,CREATED_DT ) 
        select T_SLDC_NOC_LINE_SEQ.nextval, v_pk,b.eob_ht_consumer_number, b.ENTITY_DRL, b.app_category, b.app_type, b.period_start_date, b.period_end_date, b.QUANTUM, b.category2, 0, 'N', 'Y', v_process, sysdate from int_samast_appln b where b.batch_key=I_BATCH_KEY and b.appl_no = I_APPLN_NO  and length(trim(nvl(b.ENTITY_DRL_EDC,'')))>0 ;

        -- existing commitments taken from the same table (ext_samast_appln_approval)
        Insert into T_SLDC_NOC_LINE (ID,T_SLDC_NOC_ID,COMP_SERV_NO,COMP_NAME,COMMIT_TYPE,AGGREMENT_TYPE,FROM_PERIOD,TO_PERIOD,QUANTUM,FLOW_TYPE,NO_OF_BUYER,IS_EXISTING,ENABLED,CREATED_BY,CREATED_DT ) 
        select T_SLDC_NOC_LINE_SEQ.nextval, v_pk,b.eob_ht_consumer_number, b.ENTITY_DRL, b.app_category, b.app_type, b.period_start_date, b.period_end_date, b.QUANTUM, b.category2, 0, 'Y', 'Y', v_process, sysdate from ext_samast_appln_approval b where b.EOS_GC_APPROVAL_NUMBER = I_SELLER_NO and length(trim(nvl(b.ENTITY_DRL_EDC,'')))>0 and b.period_start_date >= v_period_start_date and b.period_end_date <= v_period_end_date  ;


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

        Insert into T_SLDC_NOC (id, noc_purpose, NOC_ORIGIN,appln_type,APPL_REF_NO,app_category,category1, category2, COMP_SERV_NO , status,applied_no, APPLIED_DT,quantity,period_start_date,period_end_date, comms_adr,comp_name,FEEDER_WITH_VOLT_LEVEL ,SUBSTATION_WITH_VOLT_LEVEL ,cntrct_demand,  enabled, created_by, created_dt  ) 
        select v_pk, 'Buyer', EOR_REG_TYPE, app_type,APPL_REF_NO,app_category,category1, category2, I_BUYER_NO,'CREATED', appl_no, APPL_DATE,quantum,period_start_date,period_end_date,EOI_REG_NAME||'\n Addr-'||EOI_REG_ADDRESS||'\n MobNo-'||EOI_REG_MOBILE1_NO,
        ENTITY_DRL, EOB_FEEDER_NAME_BUYER  ,EOB_DRA_SUBSTATION  ,' ','Y',v_process, sysdate from int_samast_appln where batch_key=I_BATCH_KEY and appl_ref_no = I_APPLN_REF_NO and rownum = 1;

        UPDATE T_SLDC_NOC a
				SET ( a.COMP_SERV_ID, a.APPROV_PWR_EVAC_CAP ,a.FUEL_TYPE ,a.COD_DT , a.ABY_METER_NAME ,a.METER_MF_NAME , a.region, a.edc, a.edc_id, a.modified_by, modified_dt) = 
				(SELECT  b.id,   b.capacity, nvl(b.fuel_type_code,''),b.COMMISSION_DATE,b.METER_NUMBER,b.mf, e.parent_org_name, b.m_org_name, b.m_org_id,v_process,sysdate
					FROM   v_company_service b left join m_company_service c on  c.id=b.id left join M_POWERPLANT d on d.M_SERVICE_ID =b.id 
                    left join v_org e on e.org_id=b.M_ORG_ID WHERE  b.m_comp_serv_number = a.COMP_SERV_NO )
				WHERE  EXISTS (SELECT 1 FROM   v_company_service WHERE  a.id = v_pk and m_comp_serv_number =a.COMP_SERV_NO );     

        -- generating noc-code as part of a subquery was causing issues    
        update  T_SLDC_NOC a set noc_code = 'BUY-'||edc_id||'-'||t_sldc_noc_code_seq.nextval where  a.id =v_pk ;


        --------------- commitments--------------------
        select period_start_date,period_end_date  into v_period_start_date ,v_period_end_date from T_SLDC_NOC where id =v_pk and rownum=1 ;

        -- new commitments taken from the same table (int_samast_appln)
        Insert into T_SLDC_NOC_LINE (ID,T_SLDC_NOC_ID,COMP_SERV_NO,COMP_NAME,COMMIT_TYPE,AGGREMENT_TYPE,FROM_PERIOD,TO_PERIOD,QUANTUM,FLOW_TYPE,NO_OF_BUYER,IS_EXISTING,ENABLED,CREATED_BY,CREATED_DT ) 
        select T_SLDC_NOC_LINE_SEQ.nextval, v_pk,b.eob_ht_consumer_number, b.ENTITY_INJ, b.app_category, b.app_type, b.period_start_date, b.period_end_date, b.QUANTUM, b.category2, 0, 'N', 'Y', v_process, sysdate from int_samast_appln b where b.batch_key=I_BATCH_KEY and b.appl_no = I_APPLN_NO  and length(trim(nvl(b.ENTITY_INJ_EDC,'')))>0 ;

        -- existing commitments taken from the same table (ext_samast_appln_approval)
        Insert into T_SLDC_NOC_LINE (ID,T_SLDC_NOC_ID,COMP_SERV_NO,COMP_NAME,COMMIT_TYPE,AGGREMENT_TYPE,FROM_PERIOD,TO_PERIOD,QUANTUM,FLOW_TYPE,NO_OF_BUYER,IS_EXISTING,ENABLED,CREATED_BY,CREATED_DT ) 
        select T_SLDC_NOC_LINE_SEQ.nextval, v_pk,b.EOS_GC_APPROVAL_NUMBER, b.ENTITY_INJ, b.app_category, b.app_type, b.period_start_date, b.period_end_date, b.QUANTUM, b.category2, 0, 'Y', 'Y', v_process, sysdate from ext_samast_appln_approval b where b.eob_ht_consumer_number = I_BUYER_NO and length(trim(nvl(b.ENTITY_INJ_EDC,'')))>0 and b.period_start_date >= v_period_start_date and b.period_end_date <= v_period_end_date  ;
        /*
        select COALESCE( sum(nvl(quantum,0)), 0) into  v_exist_total_quantum from T_SLDC_NOC_LINE where  T_SLDC_NOC_ID= v_pk and IS_EXISTING='Y' group by T_SLDC_NOC_ID;
        select COALESCE( sum(nvl(quantum,0)), 0) into  v_new_total_quantum from T_SLDC_NOC_LINE where  T_SLDC_NOC_ID= v_pk   group by T_SLDC_NOC_ID;

        update  T_SLDC_NOC a set EXTG_TOTAL_COMMIT = v_exist_total_quantum, NEW_TOTAL_COMMIT = v_new_total_quantum where  id =v_pk ;
          */      
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


