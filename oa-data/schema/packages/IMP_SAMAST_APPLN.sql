CREATE OR REPLACE PACKAGE BODY OPENACCESS.IMP_SAMAST_APPLN AS



  PROCEDURE CLEANSE(I_BATCH_KEY IN VARCHAR2, I_APPLN_NO IN VARCHAR2, I_IMP_TYPE IN VARCHAR,O_IS_CLEAN  OUT VARCHAR2, O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2) is
	v_process varchar2(50):='IMP_SAMAST_APPLN.CLEANSE';
	v_process_type varchar2(500):='PROCEDURE';
	v_stage varchar2(500):='';
	v_step varchar2(500):='';
	v_message varchar2(500):='';
    v_log_result  varchar2(50);
    v_process_id  VARCHAR2(50);
    v_row_count number:=0;
    v_row_count1 number:=0;
    v_row_count2 number:=0;
    v_records_processed number:=0; 
	v_application_status varchar2(50):='';
    v_edc_status varchar2(50):='';
    v_app_category varchar2(200):='';
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

           SELECT upper(edc_status), upper(app_category) INTO v_edc_status, v_app_category FROM int_samast_appln where batch_key=I_BATCH_KEY AND appl_no = I_APPLN_NO AND rownum =1 ;
           IF(v_app_category like 'INTER STATE%') THEN Raise_Application_Error (-20343, 'APPLICATION is of category InterState. Hence ignored.'); END IF; 
           IF(v_app_category like 'INTRA STATE%' AND v_app_category like '%TANGEDCO')  THEN 
                O_RESULT_CODE:='SUCCESS';O_RESULT_DESC := 'NOC-NOT-NEEDED';
                goto THE_END;
           END IF; 
           IF(v_edc_status !='WAITING') THEN Raise_Application_Error (-20343, 'APPLICATION not in WAITING status'); END IF; 
           SELECT count(id) INTO v_row_count FROM EXT_SAMAST_APPLN esa WHERE appl_no = I_APPLN_NO;
           IF(v_row_count > 0) THEN Raise_Application_Error (-20343, 'ALREADY EXISTS IN APPLICATION TABLE'); END IF;          
        ELSIF (I_IMP_TYPE = 'APPROVAL')then
           select  upper(application_status) into v_application_status FROM int_samast_appln where batch_key=I_BATCH_KEY AND appl_no = I_APPLN_NO AND rownum =1;           
           IF(v_application_status !='ACCEPTED') THEN Raise_Application_Error (-20343, 'APPROVAL not in ACCEPTED status'); END IF; 
           SELECT count(id) INTO v_row_count FROM EXT_SAMAST_APPLN_APPROVAL esa WHERE appl_no = I_APPLN_NO;
           IF(v_row_count > 0) THEN Raise_Application_Error (-20343, 'ALREADY EXISTS IN APPROVAL TABLE'); END IF;         
        end if;


		for appLine in(select trim(nvl(BUYER_EDC_CODE,'X')) BUYER_EDC_CODE,trim(nvl(SELLER_EDC_CODE,'X'))  SELLER_EDC_CODE, trim(nvl(eos_gc_approval_number,'X')) eos_gc_approval_number,trim(nvl(eob_ht_consumer_number,'X')) eob_ht_consumer_number, appl_ref_no from int_samast_appln where batch_key=I_BATCH_KEY AND appl_no = I_APPLN_NO  )
        loop
            v_stage:='CLEANSE'; v_step:=''; v_message:= appLine.BUYER_EDC_CODE||'-'||appLine.SELLER_EDC_CODE||'-'; 
            v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,I_BATCH_KEY,I_IMP_TYPE, I_APPLN_NO);
            
            select count(*) into v_row_count1 from v_org where upper(org_code) = upper(appLine.BUYER_EDC_CODE);
            select count(*) into v_row_count2 from v_org where upper(org_code) = upper(appLine.SELLER_EDC_CODE);
            if(v_row_count1+v_row_count2 = 0) then
                Raise_Application_Error (-20343, 'Unknown Buyer/Seller EDC(BUYER_EDC_CODE:'||appLine.BUYER_EDC_CODE||', SELLER_EDC_CODE:'||appLine.SELLER_EDC_CODE||') in appl_ref_no-'||appLine.appl_ref_no); 
            end if;
            if(v_row_count1>=1) then 
                if(appline.eob_ht_consumer_number<=1) then --buyer-number is  empty
                    Raise_Application_Error (-20343, 'Empty Buyer Number(eob_ht_consumer_number) in appl_ref_no-'||appLine.appl_ref_no);
                else
                    select count(*) into v_row_count from v_company_service where m_comp_serv_number = appLine.eob_ht_consumer_number;
                    IF(v_row_count = 0) THEN Raise_Application_Error (-20343, 'Unknown Buyer Number(eob_ht_consumer_number:'||appLine.eob_ht_consumer_number||') in appl_ref_no-'||appLine.appl_ref_no); END IF;                                
                end if;
            end if; 
            if(v_row_count2>=1) then 
                if(appline.eos_gc_approval_number<=1) then --seller-number is  empty
                    Raise_Application_Error (-20343, 'Empty Seller Number(eos_gc_approval_number) in appl_ref_no-'||appLine.appl_ref_no);
                else
                    select count(*) into v_row_count from v_company_service where m_comp_serv_number = appLine.eos_gc_approval_number;
                    IF(v_row_count = 0) THEN Raise_Application_Error (-20343, 'Unknown Seller Number(eos_gc_approval_number:'||appLine.eos_gc_approval_number||') in appl_ref_no-'||appLine.appl_ref_no); END IF;                                
                end if;
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
    v_success_rec_count number:=0;
    v_failure_rec_count number:=0;
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

    select count(is_clean) into v_success_rec_count from int_samast_appln where batch_key =I_BATCH_KEY and is_clean='Y' ;
    select count(is_clean) into v_failure_rec_count from int_samast_appln where batch_key =I_BATCH_KEY and is_clean='N' ;
    
   	v_stage:='End'; v_message:='(imported-applns:'||v_success_app_count||',ignored-applns:'||v_failure_app_count||',imported-records:'||v_success_rec_count||', ignored-records:'||v_failure_rec_count||')'; 
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
        elsif(v_result_desc = 'NOC-NOT-NEEDED') then
            v_stage:='PROCESS'; v_step:=''; v_message:='NOCs not generated, as its not needed'; 
            v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,I_BATCH_KEY,I_IMP_TYPE, I_APPLN_NO);
        elsif(v_is_clean = 'Y' and i_imp_type='APPLICATION') then
            select trim(nvl(SELLER_EDC_CODE,'')), trim(eos_gc_approval_number) into v_seller_edc,v_seller_no from int_samast_appln where batch_key=I_BATCH_KEY AND appl_no = I_APPLN_NO and is_clean ='Y' AND rownum =1;
            v_stage:='PROCESS'; v_step:=''; v_message:=v_seller_edc; 
        	v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,I_BATCH_KEY,I_IMP_TYPE, I_APPLN_NO);

            if(length(v_seller_edc)>0) then
               v_stage:='PROCESS'; v_step:=''; v_message:='calling seller noc'; 
                v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,I_BATCH_KEY,I_IMP_TYPE, I_APPLN_NO);
                imp_samast_appln_helper.CREATE_SELLER_NOC(i_batch_key, i_appln_no,v_seller_no,O_RESULT_CODE, O_RESULT_DESC);
            end if;
            for appLine in (select trim(eob_ht_consumer_number) eob_ht_consumer_number,appl_ref_no from int_samast_appln where is_clean ='Y' and  batch_key=I_BATCH_KEY AND appl_no = I_APPLN_NO and length(trim(nvl(BUYER_EDC_CODE,'')))>0)
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
    else
        update int_samast_appln set is_clean = 'N' , process_remarks=v_message  where batch_key=i_batch_key and APPL_REF_NO = i_appln_no;
    end if;

   	v_stage:='End'; v_message:=''; 
    <<THE_END>>
	v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,I_BATCH_KEY ||' - '||I_APPLN_NO ||' - '||I_IMP_TYPE,O_RESULT_CODE, O_RESULT_DESC);
    COMMIT;

  END PROCESS_APP;

END IMP_SAMAST_APPLN;