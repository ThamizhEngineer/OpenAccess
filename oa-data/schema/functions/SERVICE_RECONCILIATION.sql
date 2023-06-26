CREATE OR REPLACE FUNCTION OPENACCESS."SERVICE_RECONCILIATION" 
(
  v_month in varchar,
  v_year in varchar
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
v_service_duplicated BOOLEAN :=false;
v_signup M_SIGNUP%ROWTYPE;
v_conversion number:=0.001;

BEGIN
-- begin for exception handling
BEGIN

   v_log_result := log_activity('PROCEDURE','IMPORT_SELLERS_RECONCILIATION','START','Start','','', sysdate);

   update IMPORT_SELLERS_RECONCILIATION set
   "s_in_signup"= null,    "signup_id"= null,"s_in_comp_serv"= null,"m_comp_serv_id"= null,"s_in_t_mr"= null,"t_mr_id"= null,
   "s_in_imp_mr"= null, "s_in_amr"= null, "sm_in_signup"= null, "sm_in_comp_meter"= null, "sm_in_t_mr"= null, "sm_in_imp_mr"= null, "sm_in_amr"= null,"id"=null ;

   update IMPORT_SELLERS_RECONCILIATION set "id" = rownum ;

   v_log_result := log_activity('PROCEDURE','IMPORT_SELLERS_RECONCILIATION','INIT','Init Complete','','', sysdate);
  commit;

    FOR i IN (select generator_service_no_new, count(generator_service_no_new) count1 from IMPORT_SELLERS_RECONCILIATION
            group by generator_service_no_new
            having count(generator_service_no_new) > 1
            )
    LOOP
     v_service_duplicated:= true;
     v_log_result := log_activity('PROCEDURE','IMPORT_SELLERS_RECONCILIATION','PROCESS','Duplicate Service',i.generator_service_no_new||'-'||i.count1,'', sysdate);
    end loop;

    if(v_service_duplicated) then
      v_log_result := log_activity('PROCEDURE','IMPORT_SELLERS_RECONCILIATION','PROCESS','Duplicate Services found. please fix them or remove duplicacy and try again','','', sysdate);
      V_RESULT := 'FAILURE';
      v_reason := 'Duplicate Services found. please fix them or remove duplicacy and try again';
      GOTO THE_END;
   else
      v_log_result := log_activity('PROCEDURE','IMPORT_SELLERS_RECONCILIATION','PROCESS','Duplicate check complete','','', sysdate);
    end if;

   /* check if the service-no is in oa's signup table and update reconciliation
    */
    update IMPORT_SELLERS_RECONCILIATION r set ("s_in_signup","signup_id"  ) = (select distinct 'Y', s.id from m_signup s where s.HTSC_NUMBER = r.GENERATOR_SERVICE_NO_NEW  );
    update IMPORT_SELLERS_RECONCILIATION set "s_in_signup" = 'N' where "s_in_signup" is null;

    v_log_result := log_activity('PROCEDURE','IMPORT_SELLERS_RECONCILIATION','PROCESS','Checked Service in oa signup table','','', sysdate);

     /* check if the service-no is in oa's comp_serv table and update reconciliation
    */
    update IMPORT_SELLERS_RECONCILIATION r set ("s_in_comp_serv","m_comp_serv_id"  ) = (select distinct 'Y', s.id from M_COMPANY_SERVICE s where s."number" = r.GENERATOR_SERVICE_NO_NEW );
    update IMPORT_SELLERS_RECONCILIATION set "s_in_comp_serv" = 'N' where "s_in_comp_serv" is null;

    v_log_result := log_activity('PROCEDURE','IMPORT_SELLERS_RECONCILIATION','PROCESS','Checked Service in oa service table','','', sysdate);

     /* check if the meter-reading exists for the service in oa and update reconciliation
    */
     update IMPORT_SELLERS_RECONCILIATION r set ("s_in_t_mr","t_mr_id"  ) = (select distinct  'Y', s.M_COMPANY_METER_ID from v_company_service s  join T_METER_READING_HDR m on m.M_COMPANY_METER_ID = s.M_COMPANY_METER_ID where s."number"=r.GENERATOR_SERVICE_NO_NEW);
     update IMPORT_SELLERS_RECONCILIATION set "s_in_t_mr" = 'N' where "s_in_t_mr" is null;

     v_log_result := log_activity('PROCEDURE','IMPORT_SELLERS_RECONCILIATION','PROCESS','Checked Service in oa meter reading table','','', sysdate);


     /* check if the gen-stmt exists for the service in oa and update reconciliation
    */
     update IMPORT_SELLERS_RECONCILIATION r set ("S_IN_T_GS","T_GS_ID"  ) = (select distinct  'Y', s.M_COMPANY_METER_ID from v_company_service s  join T_gen_stmt m on m.M_COMPANY_METER_ID = s.M_COMPANY_METER_ID where s."number"=r.GENERATOR_SERVICE_NO_NEW);
     update IMPORT_SELLERS_RECONCILIATION set "S_IN_T_GS" = 'N' where "S_IN_T_GS" is null;

     v_log_result := log_activity('PROCEDURE','IMPORT_SELLERS_RECONCILIATION','PROCESS','now checking Service in oa mr-staging table','','', sysdate);

    /* check if the meter-reading exists for the service in oa's mr-staging table and update reconciliation
    */
     update IMPORT_SELLERS_RECONCILIATION r set ("s_in_imp_mr"   ) = (select distinct  'Y'  from imp_mr_lines i   where i.service_no =r.GENERATOR_SERVICE_NO_NEW );
     update IMPORT_SELLERS_RECONCILIATION set "s_in_imp_mr" = 'N' where "s_in_imp_mr" is null;

     v_log_result := log_activity('PROCEDURE','IMPORT_SELLERS_RECONCILIATION','PROCESS','Services matched with all oa tables. now checking in amr','','', sysdate);

    /* check if the meter-reading exists for the service in amr and update reconciliation
    */
    update IMPORT_SELLERS_RECONCILIATION r set ("s_in_amr"  ) = (select distinct  'Y'  from uv_tbl_history_slot a where a.serviceno = r.GENERATOR_SERVICE_NO_NEW );
    update IMPORT_SELLERS_RECONCILIATION set "s_in_amr" = 'N' where "s_in_amr" is null;

    v_log_result := log_activity('PROCEDURE','IMPORT_SELLERS_RECONCILIATION','PROCESS','Services matched in amr. now matching service-meterno','','', sysdate);

    /* check if the service+meterno combination exists in the oa's  signup and update reconciliation
    */
    update IMPORT_SELLERS_RECONCILIATION r set ("sm_in_signup"  ) = (select distinct  'Y'  from m_signup s where s.HTSC_NUMBER = r.GENERATOR_SERVICE_NO_NEW and s.METER_NUMBER= r.METER_NUMBER);
    update IMPORT_SELLERS_RECONCILIATION set "sm_in_signup" = 'N' where "sm_in_signup" is null;

     v_log_result := log_activity('PROCEDURE','IMPORT_SELLERS_RECONCILIATION','PROCESS','Checked ServiceMeter in oa signup table','','', sysdate);

    /* check if the service+meterno combination exists in the oa's  comp serv and update reconciliation
    */
    update IMPORT_SELLERS_RECONCILIATION r set ("sm_in_comp_meter"  ) = (select distinct  'Y'  from v_company_service s where s."number" = r.GENERATOR_SERVICE_NO_NEW and s.METER_NUMBER= r.METER_NUMBER );
    update IMPORT_SELLERS_RECONCILIATION set "sm_in_comp_meter" = 'N' where "sm_in_comp_meter" is null;

      v_log_result := log_activity('PROCEDURE','IMPORT_SELLERS_RECONCILIATION','PROCESS','Checked ServiceMeter in oa service table','','', sysdate);
    /* check if the meter-reading exists for the service+meterno combination in oa  and update reconciliation
    */
     update IMPORT_SELLERS_RECONCILIATION r set ("sm_in_t_mr" ) = (select  distinct  'Y' from v_company_service s  join T_METER_READING_HDR m on m.M_COMPANY_METER_ID = s.M_COMPANY_METER_ID where s."number"=r.GENERATOR_SERVICE_NO_NEW and s.METER_NUMBER= r.METER_NUMBER  );
     update IMPORT_SELLERS_RECONCILIATION set "sm_in_t_mr" = 'N' where "sm_in_t_mr" is null;

     v_log_result := log_activity('PROCEDURE','IMPORT_SELLERS_RECONCILIATION','PROCESS','now checking ServiceMeter in oa mr-staging table','','', sysdate);

     /* check if the meter-reading exists for the service in oa's mr-staging table and update reconciliation
    */
     update IMPORT_SELLERS_RECONCILIATION r set ("sm_in_imp_mr"   ) = (select distinct  'Y'  from imp_mr_lines i   where i.service_no =r.GENERATOR_SERVICE_NO_NEW and  i.meter_no = r.METER_NUMBER );
     update IMPORT_SELLERS_RECONCILIATION set "sm_in_imp_mr" = 'N' where "sm_in_imp_mr" is null;

     v_log_result := log_activity('PROCEDURE','IMPORT_SELLERS_RECONCILIATION','PROCESS','ServiceMeter matched with all oa tables. now checking in amr','','', sysdate);

    /* check if the meter-reading exists for the service in amr and update reconciliation
    */
    update IMPORT_SELLERS_RECONCILIATION r set ("s_in_amr"  ) = (select  distinct 'Y'  from uv_tbl_history_slot a where a.serviceno = r.GENERATOR_SERVICE_NO_NEW and  a.meterno = r.METER_NUMBER );
    update IMPORT_SELLERS_RECONCILIATION set "s_in_amr" = 'N' where "s_in_amr" is null;

     v_log_result := log_activity('PROCEDURE','service_reconciliation','PROCESS','reconciliation complete. now checking mismatched services for each month','','', sysdate);

    -- run mismatch finding functions for each month
     v_log_result := FIND_MISSING_MRS(v_month,v_year);
     -- v_log_result := FIND_MISSING_MRS('05','2018');
     -- v_log_result := FIND_MISSING_MRS('06','2018');
     --v_log_result := FIND_MISSING_MRS('07','2018');
     --v_log_result := FIND_MISSING_MRS('08','2018');

    v_log_result := log_activity('PROCEDURE','service_reconciliation','END','process complete','','', sysdate);

exception
      when others then
        v_exception_code := SQLCODE;
        v_exception_msg := SUBSTR(SQLERRM, 1, 200);
        v_result := 'FAILURE';
        v_reason := v_exception_code || ' - ' || v_exception_msg;
      -- -- dbms_output.put_line(v_reason);
        v_log_result := log_activity('PROCEDURE','service_reconciliation','EXCEPTION',v_reason,'','', sysdate);
END;
<<THE_END>>
commit;
return V_RESULT || ' - ' || v_reason;

END service_reconciliation;

