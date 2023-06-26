CREATE OR REPLACE FUNCTION OPENACCESS."UPDATE_USER_NAME" 
(v_service_no in varchar2
)RETURN VARCHAR2 AS 
v_service_count varchar2(50);
v_comp_service_id varchar2(50);
v_comp_id varchar2(50);
v_comp_name varchar2(50);
v_org_id varchar2(50);
v_user_name varchar2(50);
v_user_count varchar2(50);



BEGIN
select count(*) into v_user_count from auth_user where user_name=v_service_no;
if(v_user_count=0)then

      select count(*) into v_service_count from v_company_service where "number"=v_service_no; 
        if(v_service_count > 0) then    

            select id,"number",M_COMPANY_ID,M_COMPANY_NAME ,M_ORG_ID into v_comp_service_id,v_user_name,v_comp_id,v_comp_name,v_org_id from v_company_service where "number"=v_service_no;
            insert into AUTH_USER(ID,FIRST_NAME,LAST_NAME,USER_NAME,PASSWORD,USER_TYPE_CODE,IS_SUPER_USER,SYSTEM_KEY_CODE,SYSTEM_REF_KEY,EDC_CODE,COMPANY_ID,
            ORG_ID,COMPANY_SERVICE_ID, IMPORT_REMARKS)
            values(USER_ID_SEQ.NEXTVAL,v_user_name,v_comp_name,v_user_name,'tneb','GEN','N','OA',v_comp_service_id,v_org_id,
            v_comp_id,v_org_id,v_comp_service_id,'USER_ADDED_BY_PROC');
      else
            -- dbms_output.put_line('Failed');
            null;
       end if;

else
        -- dbms_output.put_line('user name exits');
        null;
end if;
COMMIT;

  RETURN 'success';
END UPDATE_USER_NAME;