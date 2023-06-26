create or replace FUNCTION            "PROCESS_IMPORT_SELLERS"
(
  v_REMARKS in varchar2
) RETURN VARCHAR2 AS
v_flow_type_code varchar2(50);
v_esi_status_code varchar2(50);
v_signup_count number:=0;
v_signup_tr_count number:=0;
--v_created_Date DATE := SYSDATE;
v_created_By  varchar2(50):= 'admin';
v_status varchar2(50);
v_reason varchar2(200):='';
v_exception_code  NUMBER;
v_exception_msg  VARCHAR2(200);
v_result varchar(300):='SUCCESS';
v_log_result varchar(300):='SUCCESS';
v_signup_import_result varchar(300):='SUCCESS';
v_imported BOOLEAN;
v_signup M_SIGNUP%ROWTYPE;
v_itr import_trade_rel%ROWTYPE;
v_conversion number:=0.001;
process_san_qun_result varchar2(50):= '';
v_process_name varchar2(200):='PROCESS_IMPORT_SELLERS';
v_import_remarks  VARCHAR2(1000):=null;
v_is_captive varchar2(10);
v_import_row_count number;
BEGIN
-- begin for exception handling
BEGIN

   v_log_result := log_activity('PROCEDURE','PROCESS_IMPORT_SELLERS','START','Start - '||v_REMARKS,'','', sysdate,v_REMARKS);
   
   select count(*) into v_import_row_count from  import_sellers where remarks = v_remarks;
   if(v_import_row_count <= 0) then
	    v_reason := 'No rows in import table for the given remark - '||v_remarks;
	    V_RESULT := 'FAILURE';
	    GOTO THE_END;
    end if;
   
  -- reset values
  update import_sellers set m_org_id=null, m_substation_id=null,m_feeder_id=null,gen_make_code=null,commission_date=null, CLEAN_REC='Y', IMPORT_REMARKS='' where remarks = v_remarks;
  UPDATE import_trade_rel SET  import_remarks =null WHERE   remarks = v_remarks;

  v_log_result := log_activity('PROCEDURE','PROCESS_IMPORT_SELLERS','Init Done',''||v_REMARKS,'','', sysdate,v_REMARKS);
  update import_sellers set commission_date =  to_date(trim(commission_date_str),'dd/mm/yyyy')  where commission_date is  null and commission_date_str is not null and remarks = v_remarks;



  --update org-id
  FOR i IN ( select distinct i.edc_name, o.org_id from import_sellers i inner join v_org o on o.org_name = i.edc_name where i.remarks = v_remarks)
  LOOP
    update import_sellers set m_org_id = i.org_id where trim(edc_name) = i.edc_name and remarks = v_remarks;
  END LOOP;

  v_log_result := log_activity('PROCEDURE','PROCESS_IMPORT_SELLERS','Org lookup Done',''||v_REMARKS,'','', sysdate,v_REMARKS);

  --update ss-id
  FOR j IN ( select distinct i.SS_NAME_IN_OA, s.id  from import_sellers i inner join m_substation s on i.SS_NAME_IN_OA = s.name where i.remarks = v_remarks)
  LOOP
    update import_sellers set m_substation_id = j.id where trim(SS_NAME_IN_OA) = j.SS_NAME_IN_OA and remarks = v_remarks;
  END LOOP;

  v_log_result := log_activity('PROCEDURE','PROCESS_IMPORT_SELLERS','SS lookup Done',''||v_REMARKS,'','', sysdate,v_REMARKS);
  --update feeder-id and voltage-code
  FOR k IN (select distinct i.FEEDER_NAME_IN_OA, f.id, f.voltage_code from import_sellers i inner join m_feeder f on i.FEEDER_NAME_IN_OA = f.name where i.remarks = v_remarks)
  LOOP
  --, voltage_code=k.voltage_code  -- not mapping voltage from feeder as instructed by aruna madam
    update import_sellers set m_feeder_id = k.id where trim(feeder_NAME_IN_OA) = k.feeder_NAME_IN_OA and remarks = v_remarks;
  END LOOP;

  v_log_result := log_activity('PROCEDURE','PROCESS_IMPORT_SELLERS','Feeder lookup Done',''||v_REMARKS,'','', sysdate,v_REMARKS);

  --update voltage code
  FOR l IN ( select value_code, value_desc FROM v_codes WHERE list_code = 'VOLTAGE_CODE')
  LOOP
    UPDATE import_sellers SET  VOLTAGE_code = l.VALUE_code WHERE trim(INJECTION_VOLTAGE)= l.VALUE_DESC and remarks = v_remarks and INJECTION_VOLTAGE is not null;
  END LOOP;
  v_log_result := log_activity('PROCEDURE','PROCESS_IMPORT_SELLERS','Voltage lookup Done',''||v_REMARKS,'','', sysdate,v_REMARKS);

  --update generator make code
  FOR q IN ( select value_code, value_desc FROM v_codes WHERE list_code = 'GENERATOR_MAKE_CODE')
  LOOP
    UPDATE import_sellers SET  gen_make_code = q.VALUE_code WHERE trim(gen_make_name_oa)= q.VALUE_DESC and remarks = v_remarks;
  END LOOP;
  v_log_result := log_activity('PROCEDURE','PROCESS_IMPORT_SELLERS','Gen Make lookup Done',''||v_REMARKS,'','', sysdate,v_REMARKS);
  --update meter make code
  FOR m IN ( select value_code, value_desc FROM v_codes WHERE list_code = 'METER_MAKE_CODE')
  LOOP
    UPDATE import_sellers SET  meter_make_code = m.VALUE_code WHERE trim(meter_make)= m.VALUE_DESC and remarks = v_remarks;
  END LOOP;
  v_log_result := log_activity('PROCEDURE','PROCESS_IMPORT_SELLERS','Meter Make lookup Done',''||v_REMARKS,'','', sysdate,v_REMARKS);
  --update Wind pass  code
  FOR n IN ( select value_code, value_desc FROM v_codes WHERE list_code = 'WIND_PASS_CODE')
  LOOP
    UPDATE import_sellers SET  wind_pass_code = n.VALUE_code WHERE trim(wind_pass_name)= n.VALUE_DESC and remarks = v_remarks;
  END LOOP;
  v_log_result := log_activity('PROCEDURE','PROCESS_IMPORT_SELLERS','Wind Pass  lookup Done',''||v_REMARKS,'','', sysdate,v_REMARKS);

  --update WEG group code
  FOR p IN ( select value_code, value_desc FROM v_codes WHERE list_code = 'PLANT_CLASS_TYPE_CODE')
  LOOP
    UPDATE import_sellers SET  weg_group_code = p.VALUE_code WHERE  trim(weg_group_name) = p.VALUE_DESC and remarks = v_remarks;
  END LOOP;
  v_log_result := log_activity('PROCEDURE','PROCESS_IMPORT_SELLERS','Weg Group  lookup Done',''||v_REMARKS,'','', sysdate,v_REMARKS);

   --update Fuel code
  FOR r IN ( select fuel_code, fuel_name FROM m_fuel)
  LOOP
  --- UPDATE import_sellers SET  fuel_code =r.fuel_code WHERE  upper(trim(fuel)) = upper(r.fuel_name) and remarks = v_remarks and 
  --- substr(GENERATOR_SERVICE_NO_NEW,3,3) in  ('910','911','912'); ---MY
  
    UPDATE import_sellers SET  fuel_code =r.fuel_code WHERE  upper(trim(fuel)) = upper(r.fuel_name) and remarks = v_remarks;---original
  END LOOP;
  
  v_log_result := log_activity('PROCEDURE','PROCESS_IMPORT_SELLERS','Fuel code lookup Done',''||v_REMARKS,'','', sysdate,v_REMARKS);

  --  code cleansing start
  update import_sellers set CLEAN_REC = 'N', IMPORT_REMARKS='Valid Fuel is mandatory' where  remarks = v_remarks and nvl(CLEAN_REC,'') <> 'N' and fuel_code is null;
  update import_sellers set CLEAN_REC = 'N', IMPORT_REMARKS='EDCNAME not provided or given name doesnt exist in system' where  remarks = v_remarks and nvl(CLEAN_REC,'') <> 'N' and m_org_id is null;
  update import_sellers set CLEAN_REC = 'N', IMPORT_REMARKS='GENERATOR_SERVICE_NO_NEW not provided' where  remarks = v_remarks and  nvl(CLEAN_REC,'') <> 'N' and (GENERATOR_SERVICE_NO_NEW is null or GENERATOR_SERVICE_NO_NEW ='');
  update import_sellers set CLEAN_REC = 'N', IMPORT_REMARKS='INJECTION_VOLTAGE or VOLTAGE_code of feeder is null. cannot be imported' where  remarks = v_remarks and  nvl(CLEAN_REC,'') <> 'N' and (VOLTAGE_code is null or VOLTAGE_code ='');

  update import_sellers set CLEAN_REC = 'N', IMPORT_REMARKS='FlowTypeCode in import_sellers is mandatory. expected - IS-CAPTIVE, STB, THIRD-PARTY' where  remarks = v_remarks and  nvl(CLEAN_REC,'') <> 'N' and (flow_type_Code is null or FLOW_TYPE_CODE not in('IS-CAPTIVE','STB','THIRD-PARTY'));


  FOR s1 IN(select sig.HTSC_NUMBER  from M_SIGNUP sig join import_sellers sel on sig.HTSC_NUMBER = sel.GENERATOR_SERVICE_NO_NEW where  sel.remarks = v_remarks)
  loop
    update import_sellers set CLEAN_REC = 'N', IMPORT_REMARKS='Service Already exists in signup. to import clear that entry from signup' where  nvl(CLEAN_REC,'') <> 'N' and GENERATOR_SERVICE_NO_NEW  = s1.HTSC_NUMBER and remarks = v_remarks;
  end loop;


  FOR s2 IN(select  serv."number" gen_serv_no from v_company_Service serv join import_sellers sel on serv."number" = sel.GENERATOR_SERVICE_NO_NEW where  sel.remarks = v_remarks)
  loop
    update import_sellers set CLEAN_REC = 'N', IMPORT_REMARKS='Service Already exists in the system. cannot import this service'
    where  nvl(CLEAN_REC,'') <> 'N' and GENERATOR_SERVICE_NO_NEW = s2.gen_serv_no and remarks = v_remarks;
  end loop;

  v_log_result := log_activity('PROCEDURE','PROCESS_IMPORT_SELLERS','PROCESS','Validating Trade Relationships ','','', sysdate,v_REMARKS);

 --validating trade relationships
    FOR v_itr IN ( SELECT SELLER_COMPANY_SERVICE_NO,BUYER_COMPANY_SERVICE_NO, FLOW_TYPE_CODE, INTERVAL_TYPE_CODE,SHARE_PERCENT, QUANTUM, c1,c2,c3,c4, c5, PEAK_UNITS, OFF_PEAK_UNITS 
                    FROM IMPORT_TRADE_REL WHERE remarks = v_remarks)
    LOOP
        v_import_remarks := null;
        if(v_itr.FLOW_TYPE_CODE is null) then
            v_import_remarks := 'FlowTypeCode is mandatory for TradeRelationship';
        else
           if(v_itr.FLOW_TYPE_CODE not in('IS-CAPTIVE','STB','THIRD-PARTY')) then
                v_import_remarks := 'FlowTypeCode in TradeRelationship is not supported. expected - IS-CAPTIVE, STB, THIRD-PARTY';
           end if;
        end if;
        
        if(v_itr.INTERVAL_TYPE_CODE is null) then
            v_import_remarks := 'IntervalTypeCode is mandatory for TradeRelationship';
        else
           if(v_itr.INTERVAL_TYPE_CODE ='01' and (v_itr.peak_units is null and v_itr.off_peak_units is null)) then
                v_import_remarks := 'slots in TradeRelationship are not set';
           elsif(v_itr.INTERVAL_TYPE_CODE ='02' and (v_itr.c1 is null and v_itr.c2 is null and v_itr.c3 is null and v_itr.c4 is null and v_itr.c5 is null)) then
                v_import_remarks := 'slots in TradeRelationship are not set';
           ELSIF(v_itr.INTERVAL_TYPE_CODE ='04' and (v_itr.share_percent is null or v_itr.quantum is null)) then
                v_import_remarks := 'share percentage and quantum in TradeRelationship are not set';

           end if;
        end if;
         -- v_log_result := log_activity('PROCEDURE','PROCESS_IMPORT_SELLERS','PROCESS','v_import_remarks-'||v_import_remarks,'','', sysdate,v_REMARKS);

        if(v_import_remarks is not null) then
            UPDATE import_trade_rel SET  import_remarks = v_import_remarks 
            WHERE  SELLER_COMPANY_SERVICE_NO=v_itr.SELLER_COMPANY_SERVICE_NO and BUYER_COMPANY_SERVICE_NO = v_itr.BUYER_COMPANY_SERVICE_NO and remarks = v_remarks;
        
            update import_sellers set CLEAN_REC = 'N', IMPORT_REMARKS=v_import_remarks where  remarks = v_remarks and generator_service_no_new =v_itr.SELLER_COMPANY_SERVICE_NO and nvl(CLEAN_REC,'') <> 'N';
        end if;
    END LOOP;
    
  --  code cleansing end
  v_log_result := log_activity('PROCEDURE','PROCESS_IMPORT_SELLERS','PROCESS','lookup codes and data cleansing completed ','','', sysdate,v_REMARKS);
              -- dbms_output.put_line('code cleansing completed') ;

  --insert into m_signup table
  INSERT INTO M_SIGNUP
    (
      ID , COMPANY_NAME,PURPOSE ,COMMISSION_DATE ,VOLTAGE,HTSC_NUMBER_OLD ,HTSC_NUMBER ,SANCTIONED_QUANTUM ,TOTAL_CAPACITY ,IS_CAPTIVE ,
      FUEL,NO_OF_GENERATOR ,IS_REC ,WIND_PASS_code ,ADDRESS_LINE,
      VILLAGE ,M_ORG_ID ,M_SUBSTATION_ID ,M_FEEDER_ID ,IS_COMPLETE ,PLANT_CLASS_TYPE_code ,GENERATOR_CAPACITY ,METER_NUMBER ,METER_MAKE_code ,ACCURACY_CLASS_TYPE_code ,IS_ABT_METER ,MULTIPLICATION_FACTOR ,
      CREATED_BY ,CREATED_DATE ,REMARKS ,MODEM_NO ,GU_MODEL1 ,GU_CAPACITY1 ,NO_OF_GU1 ,ENABLED ,TURBINE_SL_NO ,TURBINE_ROTOR_DIA, TURBINE_HUB_HEIGHT, IS_DLMS_METER ,METER_CT_RATIO ,METER_PT_RATIO ,TOTAL_GENERATOR_UNIT, SURPLUS_ENERGY_CODE, ACCELERATED_DEPRECIATION, import_remarks,NATURE_OF_BOARD, flow_type_code
    )
    (SELECT M_SIGNUP_SEQ.nextval ID, COMPANY_NAME COMPANY_NAME, '02' PURPOSE,COMMISSION_DATE,VOLTAGE_code VOLTAGE,GENERATOR_SERVICE_NO_OLD HTSC_NUMBER_OLD ,GENERATOR_SERVICE_NO_NEW HTSC_NUMBER,TOTAL_CAPACITY_KW SANCTIONED_QUANTUM ,TOTAL_CAPACITY_KW TOTAL_CAPACITY, DEcode(flow_type_code,'IS-CAPTIVE','Y','N') IS_CAPTIVE,
    fuel_code  FUEL,
    NO_OF_GEN_UNITS NO_OF_GENERATOR,IS_REC IS_REC,WIND_PASS_code,SF_NO ADDRESS_LINE,
        VILLAGE VILLAGE ,M_ORG_ID M_ORG_ID,M_SUBSTATION_ID,M_FEEDER_ID,'N' IS_COMPLETE ,WEG_GROUP_code ,GEN_UNIT_CAPACITY GENERATOR_CAPACITY,METER_NUMBER METER_NUMBER,METER_MAKE_code,ACCURACY_CLASS,IS_ABT IS_ABT_METER,MF MULTIPLICATION_FACTOR,
        v_process_name CREATED_BY,sysdate created_date,REMARKS REMARKS,MODEM_NO MODEM_NO,GEN_MAKE_code GU_MODEL1,
        GEN_UNIT_CAPACITY GU_CAPACITY1,NO_OF_GEN_UNITS NO_OF_GU1,'Y' enabled,TURBINE_SL_NO TURBINE_SL_NO,
        TURBINE_ROTOR_DIA TURBINE_ROTOR_DIA ,TURBINE_HUB_HEIGHT TURBINE_HUB_HEIGHT ,IS_DLMS,CT_RATIO METER_CT_RATIO ,
        PT_RATIO METER_PT_RATIO ,GEN_UNIT_CAPACITY TOTAL_GENERATOR_UNIT,SURPLUS_ENERGY,ACCELERATED_DEPRECIATION,v_remarks,
        NATURE_OF_BOARD, flow_type_code
      FROM import_sellers
      WHERE remarks = v_remarks
      AND CLEAN_REC = 'Y'
    );

    v_signup_count :=   sql%Rowcount;
      v_log_result := log_activity('PROCEDURE','PROCESS_IMPORT_SELLERS','PROCESS','insert into m_signup completed','signup_count -' ||v_signup_count,'', sysdate,v_REMARKS);

  --- import signup traderelationship
  if(v_signup_count>0) then

    

   --update Interval type code
    FOR p IN ( select value_code, value_desc FROM v_codes WHERE list_code = 'INTERVAL_TYPE_CODE')
    LOOP
      UPDATE import_trade_rel SET  INTERVAL_TYPE_code = p.VALUE_code WHERE  trim(INTERVAL_TYPE_NAME) = p.VALUE_DESC and remarks = v_remarks;
    END LOOP;


    FOR signup IN(select * into v_signup from M_SIGNUP where remarks = v_remarks)

    LOOP
      BEGIN
        update import_trade_rel set M_SIGNUP_ID = signup.id, id = signup.id ||rownum where 
        remarks = v_remarks and seller_company_service_no = signup.HTSC_NUMBER;

        -- updating the service_id, company id of the consumers in import-trade-rel
        UPDATE import_trade_rel t1
         SET (t1.M_BUYER_COMP_SERVICE_ID, t1.M_BUYER_COMPANY_ID) = (SELECT t2.id, t2.M_COMPANY_ID
                               FROM  v_company_service t2
                              WHERE t1.BUYER_COMPANY_SERVICE_NO = t2."number")
        WHERE EXISTS (
          SELECT 1
            FROM v_company_service t2
           WHERE t1.BUYER_COMPANY_SERVICE_NO = t2."number" );

           -- PROCESS_SANC_QUAN_FOR_FF(v_remarks, process_san_qun_result);

        update import_trade_rel set from_date=to_date(from_date_str,'dd/mm/yyyy') , to_date=to_date(to_date_str,'dd/mm/yyyy'), 
        quantum = (signup.SANCTIONED_QUANTUM/to_number(SHARE_PERCENT)*100)*v_conversion where  remarks = v_remarks  ;

---(( insert into t_banking_balance (id, remarks, MONTH, YEAR,C1,C2,C3,C4,C5,CREATED_BY,CREATED_DT, ENABLED,M_COMPANY_ID, 
--BANKING_SERVICE_ID,CURR_C1,CURR_C2,CURR_C3,CURR_C4,CURR_C5,CALCULATED)
    -----    (SELECT T_BANKING_BALANCE_SEQ.nextval ID, 'BK_TO_UPDATE_CURC1', v_month, v_year,v_c1,v_c2,v_c3,v_c4,v_c5,
   ----     'admin',sysdate, 'Y',v_comp_id, v_banking_service_id,v_c1,v_c2,v_c3,v_c4,v_c5,'N'
      ---------    FROM dual       );))---



        insert into M_SIGNUP_TRADE_REL(ID,M_SIGNUP_ID,QUANTUM,FROM_DATE,TO_DATE,STATUS_CODE,M_BUYER_COMPANY_ID,
            M_BUYER_COMP_SERVICE_ID, REMARKS,SHARE_PERCENT,CREATED_BY,CREATED_DATE,C1,C2,C3,C4,C5,ENABLED,PEAK_UNITS,OFF_PEAK_UNITS,INTERVAL_TYPE_CODE,IMPORT_REMARKS, FLOW_TYPE_CODE)
        (select ID,M_SIGNUP_ID,QUANTUM,FROM_DATE,TO_DATE,STATUS_code,M_BUYER_COMPANY_ID,
             M_BUYER_COMP_SERVICE_ID,REMARKS,SHARE_PERCENT,v_process_name,sysdate,C1,C2,C3,C4,C5,ENABLED,PEAK_UNITS,OFF_PEAK_UNITS,INTERVAL_TYPE_code, v_remarks AS import_remarks, FLOW_TYPE_CODE
          from import_TRADE_REL where  remarks = v_remarks and seller_company_service_no = signup.HTSC_NUMBER and
          M_BUYER_COMP_SERVICE_ID is not null);

        v_signup_tr_count :=  v_signup_tr_count + sql%Rowcount;
        v_log_result := log_activity('PROCEDURE','PROCESS_IMPORT_SELLERS','PROCESS','traderelationship completed',signup.HTSC_NUMBER,sql%Rowcount, sysdate,v_REMARKS);
      exception
        when others then
          v_exception_code := SQLCODE;
          v_exception_msg := SUBSTR(SQLERRM, 1, 200);
          v_result := 'FAILURE';
          v_reason := v_exception_code || ' - ' || v_exception_msg;
          -- dbms_output.put_line(v_reason);
            v_log_result := log_activity('PROCEDURE','PROCESS_IMPORT_SELLERS','EXCEPTION IN TR',v_reason,'','', sysdate,v_REMARKS);
    END;

    END LOOP;
  end if;

     v_log_result := log_activity('PROCEDURE','PROCESS_IMPORT_SELLERS','RESULT','signups created - '||v_signup_count,'trs created - '||v_signup_tr_count,'', sysdate,v_REMARKS);


  FOR e IN(select  GENERATOR_SERVICE_NO_NEW, import_remarks from  import_sellers sel where  sel.remarks = v_remarks and nvl(CLEAN_REC,'') = 'N')
  loop
      v_log_result := log_activity('PROCEDURE','PROCESS_IMPORT_SELLERS','IMPORT_ERROR',e.GENERATOR_SERVICE_NO_NEW,e.import_remarks,'', sysdate,v_REMARKS);
  end loop;
  commit;

  if(v_signup_count>0) then
    v_log_result := log_activity('PROCEDURE','PROCESS_IMPORT_SELLERS','RESULT','Calling IMPORT_FROM_SIGNUP','','', sysdate,v_REMARKS);
    v_signup_import_result := IMPORT_FROM_SIGNUP(v_remarks);
  end if;
exception
	  when others then
	    v_exception_code := SQLCODE;
	    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
	    v_result := 'FAILURE';
	    v_reason := v_exception_code || ' - ' || v_exception_msg;
      -- dbms_output.put_line(v_reason);
        v_log_result := log_activity('PROCEDURE','PROCESS_IMPORT_SELLERS','EXCEPTION',v_reason,'','', sysdate,v_REMARKS);
END;
<<THE_END>>
   
v_log_result := log_activity('PROCEDURE','PROCESS_IMPORT_SELLERS','END','Import complete',V_RESULT,v_reason, sysdate,v_REMARKS);

return V_RESULT || ' - ' || v_reason;

END PROCESS_IMPORT_SELLERS;