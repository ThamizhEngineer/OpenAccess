CREATE OR REPLACE FUNCTION OPENACCESS."PROCESS_IMP_BANKING_BALANCE" 
(
  I_REMARKS IN VARCHAR2 ,
  I_OVERWRITE IN CHAR DEFAULT 'N' 
) RETURN VARCHAR2 AS 

V_OVERWRITE CHAR(1) := 'N';
v_created_By  varchar2(50):= 'admin';
v_status varchar2(50);
v_reason varchar2(200):='';
v_exception_code  NUMBER;
v_exception_msg  VARCHAR2(200);
v_result varchar(300):='SUCCESS';
v_log_result varchar(300):='SUCCESS';
v_imported BOOLEAN;
v_imported_count number:=0;
v_total_count number:=0;

BEGIN

-- begin for exception handling
BEGIN

   v_log_result := log_activity('FUNCTION','PROCESS_IMP_BANKING_BALANCE','START','Start - '||I_REMARKS,'','', sysdate,I_REMARKS);

  if(I_OVERWRITE is not null and I_OVERWRITE='Y') then V_OVERWRITE := 'Y'; end if; 

  select count(*) into v_total_count from IMP_BANKING_BALANCE where remarks = I_REMARKS;
  if(v_total_count=0) then
      Raise_Application_Error (-20343, 'No records to process');
  end if;

  -- reset values
  update IMP_BANKING_BALANCE set GEN_SERVICE_NUMBER = trim(GEN_SERVICE_NUMBER), M_COMPANY_ID = null, BANKING_SERVICE_ID = null,CLEAN_REC = null,IMPORTED = null, IMPORT_REMARKS=null, created_by = v_created_By, created_dt=sysdate where remarks = I_REMARKS;

  v_log_result := log_activity('FUNCTION','PROCESS_IMP_BANKING_BALANCE','Init Done',''||I_REMARKS,'','', sysdate,I_REMARKS);

  --update company-id, banking-service-id,company-service-id
  FOR i IN ( select IMP.GEN_SERVICE_NUMBER, SER.M_COMPANY_ID, SER.BANKING_SERVICE_ID,SER.ID  from IMP_BANKING_BALANCE IMP inner join v_company_service SER on SER."number" = IMP.GEN_SERVICE_NUMBER where IMP.remarks =I_REMARKS)
  LOOP
    update IMP_BANKING_BALANCE set BANKING_SERVICE_ID = i.BANKING_SERVICE_ID,  M_COMPANY_ID = i.M_COMPANY_ID ,M_COMPANY_SERVICE_ID=i.ID, CLEAN_REC ='Y', ENABLED='Y' where GEN_SERVICE_NUMBER = i.GEN_SERVICE_NUMBER and remarks = I_REMARKS;
  END LOOP;


  --  code cleansing start
  update IMP_BANKING_BALANCE set CLEAN_REC = 'N', IMPORT_REMARKS='ERROR - GEN_SERVICE_NUMBER not in system' where  remarks = I_REMARKS and CLEAN_REC is null;

  FOR i IN ( select IMP.GEN_SERVICE_NUMBER,bal.BANKING_SERVICE_ID, bal.month,bal.year from IMP_BANKING_BALANCE IMP inner join t_banking_balance bal on bal.BANKING_SERVICE_ID = IMP.BANKING_SERVICE_ID and to_number(bal.month) = to_number(imp.month) and  to_number(bal.year) = to_number(imp.year)
    where IMP.remarks =I_REMARKS and nvl(CLEAN_REC,'') ='Y')
  LOOP
    IF (V_OVERWRITE = 'Y') THEN
      DELETE t_banking_balance where BANKING_SERVICE_ID=i.BANKING_SERVICE_ID and  to_number(MONTH)=to_number(i.MONTH) and to_number(YEAR)=to_number(i.YEAR);
    ELSE
      update IMP_BANKING_BALANCE set CLEAN_REC = 'N', IMPORT_REMARKS='ERROR - Banking Balance already exists' where  remarks = I_REMARKS and GEN_SERVICE_NUMBER = i.GEN_SERVICE_NUMBER;
    END IF;

  END LOOP;




  -- CODE FOR CHECKING TRADERELATIONSHIP WITH TNEB EXIST
   FOR i IN ( SELECT count(*) total,trade.M_SELLER_COMPANY_ID  FROM IMP_BANKING_BALANCE IMP inner join M_TRADE_RELATIONSHIP trade on TRADE.M_SELLER_COMPANY_ID=imp.M_COMPANY_ID and to_date(to_char(lpad(IMP.month,2,'0')||'-'||IMP.year),'MM-YYYY') BETWEEN TRADE.FROM_DATE AND TRADE.TO_DATE 
and trade.M_BUYER_COMPANY_ID='TNEB' 
    where IMP.remarks =I_REMARKS and nvl(CLEAN_REC,'') ='Y'  GROUP BY trade.M_SELLER_COMPANY_ID )
  LOOP
  IF to_number(i.total)>0 then
   update IMP_BANKING_BALANCE set CLEAN_REC = 'N', IMPORT_REMARKS='ERROR - TradeRelationship with TNEB already exists' where  remarks = I_REMARKS and M_COMPANY_ID=i.M_SELLER_COMPANY_ID;
   end IF;
  END LOOP;

    v_log_result := log_activity('FUNCTION','PROCESS_IMP_BANKING_BALANCE','Cleansing Complete',''||I_REMARKS,'','', sysdate,I_REMARKS);

  insert into t_banking_balance (id, remarks, MONTH, YEAR,C1,C2,C3,C4,C5,CREATED_BY,CREATED_DT, ENABLED,M_COMPANY_ID, BANKING_SERVICE_ID,CURR_C1,CURR_C2,CURR_C3,CURR_C4,CURR_C5,CALCULATED)
    (SELECT T_BANKING_BALANCE_SEQ.nextval ID, remarks, lpad(month,2,'0'), year,C1,C2,C3,C4,C5,CREATED_BY,CREATED_DT, ENABLED,M_COMPANY_ID, BANKING_SERVICE_ID,C1,C2,C3,C4,C5,'N'
      FROM IMP_BANKING_BALANCE
      WHERE remarks = I_REMARKS
      AND CLEAN_REC = 'Y'
    );

  v_imported_count :=   sql%Rowcount;
  if(v_imported_count>0) then
    update IMP_BANKING_BALANCE set IMPORTED = 'Y', IMPORT_REMARKS='IMPORTED' where  remarks = I_REMARKS and CLEAN_REC = 'Y';
    v_log_result := log_activity('FUNCTION','PROCESS_IMP_BANKING_BALANCE','PROCESS','data imported complete','success_records -' ||v_imported_count,'', sysdate,I_REMARKS);
  else
    v_log_result := log_activity('FUNCTION','PROCESS_IMP_BANKING_BALANCE','PROCESS','no data imported','error_records -' ||(v_total_count-v_imported_count),'', sysdate,I_REMARKS);
    V_RESULT:= 'FAILURE';
    v_reason := 'no clean data to import';

  end if;
  commit;
exception
	  when others then
	    v_exception_code := SQLCODE;
	    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
	    v_result := 'FAILURE';
	    v_reason := v_exception_code || ' - ' || v_exception_msg;
      -- -- dbms_output.put_line(v_reason);
        v_log_result := log_activity('FUNCTION','PROCESS_IMP_BANKING_BALANCE','EXCEPTION',v_reason,'','', sysdate,I_REMARKS);
END;

v_log_result := log_activity('FUNCTION','PROCESS_IMP_BANKING_BALANCE','END','Import complete','','', sysdate,I_REMARKS);

return V_RESULT || ' - ' || v_reason;

END PROCESS_IMP_BANKING_BALANCE;

