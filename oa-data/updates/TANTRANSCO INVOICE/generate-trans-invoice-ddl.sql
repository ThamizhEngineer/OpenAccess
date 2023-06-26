create or replace PACKAGE TRANS_INVOICE AS 

  /* TODO enter package declarations (types, exceptions, methods etc) here */ 
    PROCEDURE PROCESS_INVOICE(I_MONTH IN VARCHAR2,I_YEAR IN VARCHAR2,I_ORG_ID IN VARCHAR2 default '%',i_process_id IN VARCHAR2 default '%',i_service_number IN VARCHAR2 default '%',i_overwrite IN VARCHAR2 default 'N', O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2);
    PROCEDURE GENERATE_INVOICE(I_MONTH IN VARCHAR2,I_YEAR IN VARCHAR2,I_GS_ID IN VARCHAR2 default '%',i_service_number IN VARCHAR2 default '%', O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2);
    PROCEDURE GENERATE_INVOICE_LINES(I_INVOICE_HDR_ID IN VARCHAR2,I_GS_ID IN VARCHAR2, O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2);
    PROCEDURE DELETE_INVOICE(I_MONTH IN VARCHAR2,I_YEAR IN VARCHAR2,i_service_number IN VARCHAR2 default '%', O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2);
END TRANS_INVOICE;

create or replace PACKAGE BODY TRANS_INVOICE AS

  PROCEDURE PROCESS_INVOICE(I_MONTH IN VARCHAR2,I_YEAR IN VARCHAR2,I_ORG_ID IN VARCHAR2 default '%',i_process_id IN VARCHAR2 default '%',i_service_number IN VARCHAR2 default '%',i_overwrite IN VARCHAR2 default 'N', O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2) IS
	v_process varchar2(50):='TRANS_INVOICE.PROCESS_INVOICE';
	v_process_type varchar2(500):='PROCEDURE';
	v_stage varchar2(500):='';
	v_step varchar2(500):='';
	v_message varchar2(500):='';
    v_log_result  varchar2(50);
   	v_row_count NUMBER:=0;
    V_gen_invoice_count NUMBER:=0;
    v_pk varchar2(50):= '';
    v_input varchar(500) :='';
    v_result  varchar2(50);
	v_desc varchar2(500):='';
  BEGIN
    BEGIN --EXCEPTION-HANDLER BLOCK STARTS HERE    
        v_input:='I_MONTH--'||I_MONTH||',I_YEAR--'||I_YEAR||',I_ORG_ID--'||I_ORG_ID||',i_process_id--'||i_process_id||',i_service_number--'||i_service_number||',i_overwrite--'||i_overwrite;
        
		v_stage:='INIT'; v_step:='START'; v_message:=v_input;
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,v_process,'','');
        
        for gs in (select  gs.id, gs.DISP_SERVICE_NUMBER, gs.stmt_year, gs.stmt_month
                            from t_gen_stmt gs where gs.stmt_year like I_YEAR and gs.stmt_month like I_MONTH and gs.m_org_id  like I_ORG_ID  and gs.ref_number  like i_process_id  and gs.disp_service_number like i_service_number)
        loop
        begin
            select  COUNT(ID) INTO v_row_count from t_invoice_hdr WHERE LINE_YEAR = gs.stmt_year AND LINE_MONTH = gs.stmt_month AND M_COMP_SERV_NO = gs.DISP_SERVICE_NUMBER ;
            IF(v_row_count > 0 and i_overwrite = 'Y') then
                DELETE_INVOICE(gs.stmt_month,gs.stmt_year,gs.DISP_SERVICE_NUMBER,v_result, v_desc); 
            end if;
            
            if(i_overwrite = 'Y' or ( v_row_count = 0  and i_overwrite = 'N')) then
                GENERATE_INVOICE(gs.stmt_month,gs.stmt_year,gs.id,gs.DISP_SERVICE_NUMBER ,v_result, v_desc); 
                if(v_result = 'SUCCESS') then
                    v_gen_invoice_count := V_gen_invoice_count+1;
                end if;
            end if;
                
        
        exception
            when others then
            v_stage:='EXCEPTION IN GS LOOP'; v_step:=''; 
            v_message := SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 200);
            v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_input,v_process,gs.id, gs.DISP_SERVICE_NUMBER);
            O_RESULT_CODE:='FAILURE';
            O_RESULT_DESC := v_message;
        END;--EXCEPTION ENDS HERE 
        end loop;
        
        O_RESULT_CODE:='SUCCESS';
        O_RESULT_DESC := '';         
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,v_process,O_RESULT_CODE, O_RESULT_DESC);
    exception
        when others then
        v_stage:='EXCEPTION'; v_step:=''; 
		v_message := SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 200);
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,v_process,O_RESULT_CODE, O_RESULT_DESC);
    	O_RESULT_CODE:='FAILURE';
	    O_RESULT_DESC := v_message;
    END;--EXCEPTION ENDS HERE

   	v_stage:='End'; v_message:='v_gen_invoice_count--'||v_gen_invoice_count; 
    o_result_desc := v_message;
	v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,v_process,O_RESULT_CODE, O_RESULT_DESC);
    COMMIT;
  END PROCESS_INVOICE;

  PROCEDURE GENERATE_INVOICE(I_MONTH IN VARCHAR2,I_YEAR IN VARCHAR2,I_GS_ID IN VARCHAR2 default '%',i_service_number IN VARCHAR2 default '%', O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2) IS
	v_process varchar2(50):='TRANS_INVOICE.GENERATE_INVOICE';
	v_process_type varchar2(500):='PROCEDURE';
	v_stage varchar2(500):='';
	v_step varchar2(500):='';
	v_message varchar2(500):='';
    v_log_result  varchar2(50);
   	v_row_count NUMBER:=0;
    v_pk varchar2(50):= '';
    v_input varchar(500) :='';
    v_result varchar(500) :='';
    v_desc varchar(500) :='';
    inv_hdr   t_invoice_hdr%ROWTYPE;
    TYPE lookup_type 
        IS TABLE OF VARCHAR2(100) 
        INDEX BY VARCHAR2(50);
    lookup lookup_type;

  BEGIN
    BEGIN --EXCEPTION-HANDLER BLOCK STARTS HERE    
        v_input:='I_MONTH--'||I_MONTH||',I_YEAR--'||I_YEAR||',I_GS_ID--'||I_GS_ID||',i_service_number--'||i_service_number;
        
		v_stage:='INIT'; v_step:='START'; v_message:=v_input;
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,v_process,'','');
        for row in (select value_code, value_desc from v_codes where list_code = 'TRANS_INV_CODE' and enabled = 'Y')
        loop
            lookup(row.value_code) := row.value_desc;
        end loop;
    
        v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,lookup('SUPGST'),v_process,'','');
        inv_hdr.id := t_invoice_hdr_id_seq.nextval;
        inv_hdr.INVCODE :=t_invoice_hdr_code_seq.nextval;
        inv_hdr.DOCID := inv_hdr.INVCODE;
        -- set key header values
        select gs.commission_date ,trunc(sysdate), gs.m_org_id ,gs.disp_org_name M_ORG_NAME, gs.id t_gen_stmt_id, gs.m_substation_id,ss.code m_substation_code, gs.m_substation_name, gs.m_company_id, gs.DISP_COMPANY_NAME CUSNAME, gs.m_company_service_id, gs.DISP_SERVICE_NUMBER M_COMP_SERV_NO, gs.STMT_MONTH LINE_MONTH, gs.STMT_YEAR LINE_YEAR, nvl(c.gst, 'GST-NOT-REGISTERED') CUSGST, cs.plant_addr CUSADDRESS, cs.contact_phone_no CUSPHNO, cs.contact_email CUSEMAIL, '' CUSPIN, v_process, sysdate, 'CREATED', v_process, sysdate, gs.flow_type_code, gs.disp_capacity, gs.disp_fuel_type_code
        into inv_hdr.COMMDT, inv_hdr.invoicedt, inv_hdr.m_org_id, inv_hdr.M_ORG_NAME,inv_hdr.t_gen_stmt_id,  inv_hdr.m_substation_id, inv_hdr.m_substation_code, inv_hdr.m_substation_name, inv_hdr.m_company_id , inv_hdr.CUSNAME, inv_hdr.M_COMP_SERV_ID, inv_hdr.M_COMP_SERV_NO, inv_hdr.LINE_MONTH, inv_hdr.LINE_YEAR, inv_hdr.CUSGST, inv_hdr.CUSADDRESS, inv_hdr.CUSPHNO,inv_hdr. CUSEMAIL, inv_hdr.CUSPIN, inv_hdr.CREATEDBY, inv_hdr.CREATEDDATE, inv_hdr.INVSTATUS, inv_hdr.INVCREATEDBY, inv_hdr.INVCREATEDDATE, inv_hdr.FLOW_TYPE, inv_hdr.CAPACITY , inv_hdr.FUEL_TYPE
        from t_gen_stmt gs inner join m_company c on c.id = gs.m_company_id inner join m_company_service cs on cs.id = gs.m_company_service_id inner join m_substation ss on ss.id=gs.m_substation_id where gs.id  = i_gs_id;

        select nvl(agmt_dt,'') agmtdt, nvl(agreement_type,'Not Registered') agmttype, floor(to_date - sysdate) AGMTDURATION 
        into inv_hdr.agmtdt , inv_hdr.agmttype , inv_hdr.AGMTDURATION  
        from m_trade_relationship where m_seller_comp_service_id= inv_hdr.M_COMP_SERV_ID and rownum =1;
        
        select init_reading_dt, final_reading_dt 
        into inv_hdr.invfromdt, inv_hdr.invtodt
        from t_meter_reading_hdr where m_gen_stmt_id = inv_hdr.t_gen_stmt_id;
        
        -- set lookup values
        select lookup('HSNSAC'), lookup('INV_DESC'), lookup('INV_TYPE_CODE'), lookup('QUANTITY'), lookup('REVERSECHRG'), lookup('SUPNAME'), lookup('SUPGST'), lookup('IGST'), lookup('CGST'), lookup('SGST'),
                lookup('SUPLIERLOCALITY'), lookup('SUPLRADDR1'), lookup('SUPLRADDR2'), lookup('SUPLREMAIL'), lookup('SUPLRPHONE'), lookup('SUPLRPINCODE'), lookup('SUPSTATE'), lookup('SUPSTATECODE'), lookup('TRANSACTIONTYPE'), 
                lookup('UQC'), lookup('DOCUMENTTYPE'), lookup('VIRTUAL_NO_PREFIX')||substr(inv_hdr.M_COMP_SERV_NO,length(inv_hdr.M_COMP_SERV_NO)-10+1), lookup('BANKNAME'),lookup('BANKBRANCH'),lookup('BANKIFSCCODE'),lookup('BANKACCNO'),lookup('SUPPAN'),lookup('CESS')
                into inv_hdr.HSNSAC, inv_hdr.DESCRIPTI0N, inv_hdr.INVOICETYPE, inv_hdr.QUANTITY, inv_hdr.REVERSECHRG, inv_hdr.SUPNAME, inv_hdr.SUPGST, inv_hdr.IGSTTAXPER, inv_hdr.CGSTTAXPER , inv_hdr.SGSTTAXPER, 
                inv_hdr.SUPLIERLOCALITY, inv_hdr.SUPLRADDR1, inv_hdr.SUPLRADDR2, inv_hdr.SUPLREMAIL, inv_hdr.SUPLRPHONE, inv_hdr.SUPLRPINCODE, inv_hdr.SUPSTATE, inv_hdr.SUPSTATECODE, inv_hdr.TRANSACTIONTYPE, 
                inv_hdr.UQC, inv_hdr.DOCUMENTTYPE, inv_hdr.VIRTUALACCOUNTNO, inv_hdr.BANKNAME , inv_hdr.BANKBRANCH , inv_hdr.BANKIFSCCODE , inv_hdr.BANKACCNO , inv_hdr.SUPPAN , inv_hdr.CESS from dual;
        
        insert into t_invoice_hdr values inv_hdr;
        GENERATE_INVOICE_LINES(inv_hdr.id,inv_hdr.t_gen_stmt_id,v_result, v_desc); 

        select sum(itembeforetaxamt), sum(igstaxamt),sum(cgstaxamt),sum(sgstaxamt),sum(itemtaxamt), sum(itemtotalamt) 
                into inv_hdr.subtotalforgst, inv_hdr.igsttaxamt, inv_hdr.cgsttaxamt, inv_hdr.sgsttaxamt, inv_hdr.totaltaxamt, inv_hdr.totalinvamt
                from t_invoice_line where t_inv_hdr_id=inv_hdr.id and hasgst='Y';
        select sum(itembeforetaxamt), sum(itemtotalamt) , inv_hdr.totalinvamt+sum(itemtotalamt) 
                into inv_hdr.subtotalforothers, inv_hdr.subtotalothers,inv_hdr.totalinvamt from t_invoice_line where t_inv_hdr_id=inv_hdr.id and hasgst='N';
        inv_hdr.invamtinwords := convert_number_words(inv_hdr.totalinvamt);
        UPDATE t_invoice_hdr SET ROW = inv_hdr WHERE id = inv_hdr.id;
        
        O_RESULT_CODE:='SUCCESS';
        O_RESULT_DESC := '';         
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,v_process,O_RESULT_CODE, O_RESULT_DESC);
    exception
        when others then
        v_stage:='EXCEPTION'; v_step:=''; 
		v_message := SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 200);
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,v_process,O_RESULT_CODE, O_RESULT_DESC);
    	O_RESULT_CODE:='FAILURE';
	    O_RESULT_DESC := v_message;
    END;--EXCEPTION ENDS HERE

   	v_stage:='End'; v_message:=''; 
	v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,v_process,O_RESULT_CODE, O_RESULT_DESC);
    COMMIT;
  END GENERATE_INVOICE;

  PROCEDURE GENERATE_INVOICE_LINES(I_INVOICE_HDR_ID IN VARCHAR2,I_GS_ID IN VARCHAR2, O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2) IS
	v_process varchar2(50):='TRANS_INVOICE.GENERATE_INVOICE_LINES';
	v_process_type varchar2(500):='PROCEDURE';
	v_stage varchar2(500):='';
	v_step varchar2(500):='';
	v_message varchar2(500):='';
    v_log_result  varchar2(50);
   	v_row_count NUMBER:=0;
    v_pk varchar2(50):= '';
    v_input varchar(500) :='';
    inv_hdr   t_invoice_hdr%ROWTYPE;
    v_charge_amt number :=0;
    v_igst_amt number :=0;
    v_cgst_amt number :=0;
    v_sgst_amt number :=0;
    v_tax_amt number :=0;
  BEGIN
    BEGIN --EXCEPTION-HANDLER BLOCK STARTS HERE    
        v_input:='I_INVOICE_HDR_ID--'||I_INVOICE_HDR_ID||',I_GS_ID--'||I_GS_ID;
        
		v_stage:='INIT'; v_step:='START'; v_message:=v_input;
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,v_process,'','');
        
        select * into inv_hdr from t_invoice_hdr where id=I_INVOICE_HDR_ID;
        for row in (select gsc.charge_code,gsc.charge_desc,c.has_gst, gsc.total_charges, gsc.total_charges_bak , 
                    case when gsc.total_charges >'0' then gsc.total_charges else  nvl(gsc.total_charges_bak,0) end actual_charge
                    from t_gen_stmt_charge gsc inner join v_charge c on c.charge_code = gsc.charge_code and c.context = 'TRANS_INVOICE' where t_gen_stmt_id=I_GS_ID)
        loop
            v_row_count :=v_row_count+1;
            v_charge_amt :=0; v_igst_amt:=0; v_cgst_amt:=0; v_sgst_amt:=0; v_tax_amt:=0;
            if(row.has_gst='Y') then
                v_charge_amt := row.actual_charge;
                v_igst_amt := v_charge_amt* to_number(inv_hdr.IGSTTAXPER);
                v_sgst_amt := v_charge_amt* to_number(inv_hdr.SGSTTAXPER);
                v_cgst_amt := v_charge_amt* to_number(inv_hdr.CGSTTAXPER);
                v_tax_amt:= v_igst_amt+v_cgst_amt+v_sgst_amt;
            else
                v_charge_amt := row.actual_charge;
            end if;
            
            Insert into t_invoice_line (LINEID,T_INV_HDR_ID,ITEMNO,CHARGECODE,ITEMNAME,ITEMCODE,ITEMDESC,  ITEMNOTES,ITEMUNITCODE,ITEMUNITNAME,ITEMRATE,ITEMBEFORETAXAMT,
                                HASGST,IGSTAXPER,CGSTAXPER,SGSTAXPER,IGSTAXAMT,CGSTAXAMT,SGSTAXAMT, ITEMTAXAMT,ITEMTOTALAMT,CREATEDBY,CREATEDDATE,ITEMUNITCOUNT) 
                            values (T_INVOICE_LINE_ID_SEQ.nextval,inv_hdr.id,v_row_count,row.charge_code,row.charge_desc,row.charge_code,row.charge_desc,'','','', row.actual_charge, v_charge_amt,
                                row.has_gst, inv_hdr.IGSTTAXPER, inv_hdr.CGSTTAXPER, inv_hdr.SGSTTAXPER,v_igst_amt, v_cgst_amt,v_sgst_amt,v_tax_amt, v_charge_amt+v_tax_amt,v_process,sysdate,1   );
            update t_gen_stmt_charge set total_charges = 0, total_charges_bak=row.actual_charge where t_gen_stmt_id=I_GS_ID and charge_code=row.charge_code;
            
        end loop;
        
        
        O_RESULT_CODE:='SUCCESS';
        O_RESULT_DESC := '';         
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,v_process,O_RESULT_CODE, O_RESULT_DESC);
    exception
        when others then
        v_stage:='EXCEPTION'; v_step:=''; 
		v_message := SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 200);
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,v_process,O_RESULT_CODE, O_RESULT_DESC);
    	O_RESULT_CODE:='FAILURE';
	    O_RESULT_DESC := v_message;
    END;--EXCEPTION ENDS HERE

   	v_stage:='End'; v_message:=''; 
	v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,v_process,O_RESULT_CODE, O_RESULT_DESC);
    COMMIT;
  END GENERATE_INVOICE_LINES;


  PROCEDURE DELETE_INVOICE(I_MONTH IN VARCHAR2,I_YEAR IN VARCHAR2,i_service_number IN VARCHAR2 default '%', O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2) IS
	v_process varchar2(50):='TRANS_INVOICE.DELETE_INVOICE';
	v_process_type varchar2(500):='PROCEDURE';
	v_stage varchar2(500):='';
	v_step varchar2(500):='';
	v_message varchar2(500):='';
    v_log_result  varchar2(50);
   	v_row_count NUMBER:=0;
    v_pk varchar2(50):= '';
    v_input varchar(500) :='';
  BEGIN
    BEGIN --EXCEPTION-HANDLER BLOCK STARTS HERE    
        v_input:='I_MONTH--'||I_MONTH||',I_YEAR--'||I_YEAR||',i_service_number--'||i_service_number;
        
		v_stage:='INIT'; v_step:='START'; v_message:=v_input;
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,v_process,'','');
        for row in (select id from t_invoice_hdr WHERE LINE_YEAR = I_YEAR AND LINE_MONTH = I_MONTH AND M_COMP_SERV_NO = i_service_number )
        loop
            delete from t_invoice_line where t_inv_hdr_id = row.id;
            delete from t_invoice_hdr where id = row.id;
            v_row_count :=v_row_count+1;
        end loop;
        
        -- v_pk := t_sldc_noc_seq.nextval;

        -- existing commitments taken from the same table (ext_samast_appln_approval)
        -- Insert into T_SLDC_NOC_LINE (ID,T_SLDC_NOC_ID,COMP_SERV_NO,COMP_NAME,COMMIT_TYPE,AGGREMENT_TYPE,FROM_PERIOD,TO_PERIOD,QUANTUM,FLOW_TYPE,NO_OF_BUYER,IS_EXISTING,ENABLED,CREATED_BY,CREATED_DT ) 
        -- select T_SLDC_NOC_LINE_SEQ.nextval, v_pk,b.EOS_GC_APPROVAL_NUMBER, b.ENTITY_INJ, b.app_category, b.app_type, b.period_start_date, b.period_end_date, b.QUANTUM, b.category2, 0, 'Y', 'Y', v_process, sysdate from ext_samast_appln_approval b where b.eob_ht_consumer_number = I_BUYER_NO and length(trim(nvl(b.ENTITY_INJ_EDC,'')))>0 and b.period_start_date >= v_period_start_date and b.period_end_date <= v_period_end_date  ;
        
        
        O_RESULT_CODE:='SUCCESS';
        O_RESULT_DESC := '';         
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,v_process,O_RESULT_CODE, O_RESULT_DESC);
    exception
        when others then
        v_stage:='EXCEPTION'; v_step:=''; 
		v_message := SQLCODE || ' - ' ||  SUBSTR(SQLERRM, 1, 200);
		v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,v_process,O_RESULT_CODE, O_RESULT_DESC);
    	O_RESULT_CODE:='FAILURE';
	    O_RESULT_DESC := v_message;
    END;--EXCEPTION ENDS HERE

   	v_stage:='End'; v_message:='deleted records-'||v_row_count; 
	v_log_result := log_activity(v_process_type,v_process,v_stage,v_step,v_message,v_process,O_RESULT_CODE, O_RESULT_DESC);
    COMMIT;
  END DELETE_INVOICE;

END TRANS_INVOICE;