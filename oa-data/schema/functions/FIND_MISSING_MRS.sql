CREATE OR REPLACE FUNCTION OPENACCESS."FIND_MISSING_MRS" 
(
  v_month in varchar2, v_year in varchar2
) RETURN VARCHAR2 AS
v_unresolved_count number;
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
v_conversion number:=0.001;

BEGIN
-- begin for exception handling
BEGIN

   v_log_result := log_activity('PROCEDURE','FIND_MISSING_MRS','START','Start v_month-'||v_month||'v_year-'||v_year,'','', sysdate,v_month,v_year);

  delete from TEMP_MISSING_MR_SERVICES where READING_MONTH =v_month and READING_YEAR = v_year ;
  commit;
  insert into TEMP_MISSING_MR_SERVICES
  select circlecode,serviceno, meterno,'Y','',v_month,v_year from (
  SELECT  distinct substr( "number",6,3)  circlecode, "number" serviceno, c.METER_NUMBER meterno
   FROM V_COMPANY_SERVICE c
   WHERE
   COMPANY_IS_INTERNAL = 'N' AND COMP_SER_TYPE_CODE = '03'
   and  length("number")=12
   minus
   SELECT  distinct substr( "number",6,3)  circlecode, "number" serviceno, c.METER_NUMBER meterno
   FROM V_COMPANY_SERVICE c,T_METER_READING_HDR a
   WHERE
   COMPANY_IS_INTERNAL = 'N' AND COMP_SER_TYPE_CODE = '03'
   and  length("number")=12
   and C.M_COMPANY_METER_ID=A.M_COMPANY_METER_ID
   and READING_MONTH =v_month and READING_YEAR = v_year
   );

  -- records not in AMR
  FOR i IN ( select serviceno  from TEMP_MISSING_MR_SERVICES where READING_MONTH =v_month and READING_YEAR = v_year
               minus
                 select distinct a.serviceno  from UV_TBL_HISTORY_SLOT a  where  TO_CHAR(INITIAL_READING_DATE,'MM') =v_month and  TO_CHAR(INITIAL_READING_DATE,'YYYY') =  v_year
            )
  LOOP
    update TEMP_MISSING_MR_SERVICES set in_amr = 'N', remarks='Service Number not in AMR' where remarks is null and  serviceno = i.serviceno  and  READING_MONTH =v_month and READING_YEAR = v_year;
  END LOOP;


  -- records not in AMR during the given period
  FOR i IN ( select serviceno from UV_TBL_HISTORY_SLOT where serviceno in 
              (select serviceno from TEMP_MISSING_MR_SERVICES miss
              where miss.remarks='Service Number not in AMR'  and miss.READING_MONTH =v_month and miss.READING_YEAR = v_year) 
            )
  LOOP
    update TEMP_MISSING_MR_SERVICES set in_amr = 'N', remarks='Service Number not in AMR for '||v_month||'-'||v_year where  serviceno = i.serviceno  and  READING_MONTH =v_month and READING_YEAR = v_year;
  END LOOP;

 -- records  in AMR but meter-no doesnt match
  FOR i IN ( select distinct  l.service_no, l.remarks from imp_mr_lines l
              inner join imp_mr_header h on l.imp_mr_header_id = h.id
              where 1=1
              --and l.remarks is not null
              and nvl(l.remarks,'')  in ('Wrong ServiceNo-MeterNo combo','Unknown Service-no')
              and l.status_code in ('CLEANSED','ERROR','IMPORTED')
              and h.status in ('IMPORT-ERROR', 'COMPLETED')
              and READING_MONTH = v_month  and READING_YEAR =  v_year
              and nvl(service_no,'')   in (
               select serviceno from  TEMP_MISSING_MR_SERVICES where remarks is null and  READING_MONTH =v_month and READING_YEAR = v_year )
            )
  LOOP
    update TEMP_MISSING_MR_SERVICES set   remarks=i.remarks where  remarks is null and serviceno = i.service_no  and  READING_MONTH =v_month and READING_YEAR = v_year;
  END LOOP;

  -- records  in AMR but unit values in imp_mr_lines is null
  FOR i IN ( select distinct  l.service_no, nvl(l.remarks,'') remarks from imp_mr_lines l
              where 1=1
               and TO_CHAR(to_date(l.INIT_READING_DT,'YYYY-MM-DD'),'MM') =v_month and  TO_CHAR(to_date(l.INIT_READING_DT,'YYYY-MM-DD'),'YYYY') =  v_year
             and (   EXP_FINAL_S1 is null or EXP_FINAL_S2 is null or EXP_FINAL_S3 is null or EXP_FINAL_S4 is null or EXP_FINAL_S5 is null
                     or   IMP_FINAL_S1 is null or IMP_FINAL_S2 is null or IMP_FINAL_S3 is null or IMP_FINAL_S4 is null or IMP_FINAL_S5 is null  )
            )
  LOOP
    update TEMP_MISSING_MR_SERVICES set   remarks='One of the INIT values is null' where remarks is null and serviceno = i.service_no  and  READING_MONTH =v_month and READING_YEAR = v_year;
  END LOOP;

   -- records  in AMR but dont know
  FOR i IN ( select distinct  l.service_no, l.remarks from imp_mr_lines l
              where 1=1
              --and l.remarks is not null
             -- and l.status_code in ('CLEANSED','ERROR','IMPORTED')
              and READING_MONTH = v_month  and READING_YEAR =  v_year
              and nvl(service_no,'')   in (
               select serviceno from  TEMP_MISSING_MR_SERVICES where remarks is null and  READING_MONTH =v_month and READING_YEAR = v_year )
            )
  LOOP
    update TEMP_MISSING_MR_SERVICES set   remarks=i.remarks where  remarks is null and serviceno = i.service_no  and  READING_MONTH =v_month and READING_YEAR = v_year;
  END LOOP;

  -- records  in AMR but unit values in AMR'sview is null
  FOR i IN ( select distinct  l.serviceno  from UV_TBL_HISTORY_SLOT l
              where 1=1
               and TO_CHAR( l.INITIAL_READING_DATE ,'MM') =v_month and  TO_CHAR( l.INITIAL_READING_DATE,'YYYY') =  v_year
             and (   EXP_FINAL_S1 is null or EXP_FINAL_S2 is null or EXP_FINAL_S3 is null or EXP_FINAL_S4 is null or EXP_FINAL_S5 is null
                     or   IMP_FINAL_S1 is null or IMP_FINAL_S2 is null or IMP_FINAL_S3 is null or IMP_FINAL_S4 is null or IMP_FINAL_S5 is null  )
            )
  LOOP
    update TEMP_MISSING_MR_SERVICES set   remarks='One of the unit values is null' where remarks is null and serviceno = i.serviceno  and  READING_MONTH =v_month and READING_YEAR = v_year;
  END LOOP;


  select count(*) into v_unresolved_count from TEMP_MISSING_MR_SERVICES where  READING_MONTH =v_month and READING_YEAR = v_year and remarks is null;
  -- dbms_output.put_line('unresolved services-'||v_unresolved_count);

  update TEMP_MISSING_MR_SERVICES set   remarks = 'UNRESOLVED' where remarks is null;
  v_log_result := log_activity('PROCEDURE','FIND_MISSING_MRS','END','unresolved services-'||v_unresolved_count,'','', sysdate,v_month,v_year);
  v_log_result := log_activity('PROCEDURE','FIND_MISSING_MRS','END','process complete','','', sysdate,v_month,v_year);
exception
	  when others then
	    v_exception_code := SQLCODE;
	    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
	    v_result := 'FAILURE';
	    v_reason := v_exception_code || ' - ' || v_exception_msg;
      -- -- dbms_output.put_line(v_reason);
        v_log_result := log_activity('PROCEDURE','FIND_MISSING_MRS','EXCEPTION',v_reason,'','', sysdate,v_month,v_year);
END;
commit;
return V_RESULT || ' - ' || v_reason;

END FIND_MISSING_MRS;

