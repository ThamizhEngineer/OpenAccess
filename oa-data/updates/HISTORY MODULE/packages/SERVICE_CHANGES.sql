CREATE OR REPLACE PACKAGE OPENACCESS.SERVICE_CHANGES
AS
PROCEDURE SERVICE_NAME_CHANGE(service_number IN varchar,
      old_name IN  varchar,
      new_name  IN varchar,
      changing_month IN  varchar,
      changing_year IN  varchar);
PROCEDURE SERVICE_MIGRATION_CHANGE(service_number IN varchar,
        migration_from in varchar2,
        migration_to IN varchar2,
        migration_month in varchar2,
        migration_year IN varchar2);
     
END SERVICE_CHANGES;

CREATE OR REPLACE PACKAGE BODY OPENACCESS.SERVICE_CHANGES
          AS PROCEDURE SERVICE_NAME_CHANGE(service_number IN varchar, old_name IN varchar, new_name IN varchar,changing_month IN varchar,changing_year IN varchar) IS

	service_count number(10) :=0;
    company_service v_company_service%rowtype;
    NAMECHANGECOUNT NUMBER :=0;
    v_exception_code  NUMBER;
    v_exception_msg  VARCHAR2(200);
    o_result_desc varchar2(200);
    o_result_code varchar2(200);
  BEGIN
   	BEGIN
	   	dbms_output.put_line('sdfsdafsdf--'||old_name||new_name||SERVICE_NUMBER);
	   IF  old_name is null or old_name = '' OR new_name is null OR new_name ='' OR SERVICE_NUMBER is NULL 
	   OR SERVICE_NUMBER='' THEN 
	   Raise_Application_Error (-20343, 'old name,newname,service_number cannot be NULL');
	   dbms_output.put_line('oldnamesdfgrr');
	   END IF;
	   dbms_output.put_line ('oldname--');
	   SELECT count(*) INTO service_count FROM V_COMPANY_SERVICE vcs WHERE M_COMPANY_NAME = old_name 
	   AND "number" =service_number;
	   SELECT * INTO company_service FROM V_COMPANY_SERVICE vcs WHERE M_COMPANY_NAME = old_name 
	   AND "number" =service_number;
	   IF service_count = 0 THEN
	   Raise_Application_Error (-20344, 'no entry for given service number and old name');
	   END IF;
	   IF service_count = 1 THEN
	   UPDATE M_COMPANY SET NAME = new_name WHERE id = (SELECT M_COMPANY_ID FROM v_company_service WHERE 
	   M_COMPANY_NAME = old_name AND "number" =service_number);
	  
	   SELECT count(*) INTO NAMECHANGECOUNT FROM NAME_CHANGE_HIS nch  WHERE M_COMP_SERVICE_NO = service_number  AND 
     NEW_NAME = new_name  AND OLD_NAME =old_name AND NC_MONTH =changing_month AND 
     NC_YEAR =changing_year;
	
	 IF NAMECHANGECOUNT = 0 THEN  
     INSERT INTO OPENACCESS.NAME_CHANGE_HIS
     (M_COMPANY_SERV_ID, M_COMP_SERVICE_NO, NC_MONTH, NC_YEAR, OLD_NAME, NEW_NAME)
     VALUES(company_service.ID,service_number,changing_month,changing_year,old_name,new_name);

     END IF;
    
   VIEW_REFRESH.comp_serv_refresh(service_number, o_result_code, o_result_desc);
   VIEW_REFRESH_HISTORY.COMP_SERV_REFRESH_HISTORY_ALL(service_number,'N','Y','N','N'); 
	  
	   dbms_output.put_line('SUCCESS');
	   END IF;
	   
	  
	  
      exception
      when others THEN
       v_exception_code := SQLCODE;
        v_exception_msg := SUBSTR(SQLERRM, 1, 200);
        o_result_desc := v_exception_code || ' - ' || v_exception_msg;
                dbms_output.put_line(o_result_desc);
               END;
              
    END SERVICE_NAME_CHANGE;
   
PROCEDURE SERVICE_MIGRATION_CHANGE(service_number IN varchar,
        migration_from in varchar2,
        migration_to IN varchar2,
        migration_month in varchar2,
        migration_year IN varchar2)is
        
        service_count number(10) :=0;
        company_service v_company_service%rowtype;
        MIGRATIONCOUNT NUMBER :=0;
       v_exception_code  NUMBER;
       v_exception_msg  VARCHAR2(200);
       o_result_desc varchar2(200);
       o_result_code varchar2(200);
       BEGIN
       	
	       BEGIN
	       IF service_number is NULL OR service_number = '' OR migration_from IS NULL OR migration_from ='' OR
	       migration_to IS NULL OR migration_to='' OR migration_month IS NULL OR migration_year IS null THEN 
	       Raise_Application_Error (-20343, 'Inputs cannot be null including service number');
	       END IF;
	      SELECT count(*) INTO service_count FROM V_COMPANY_SERVICE vcs WHERE FLOW_TYPE_CODE  = migration_from 
	      AND "number" =service_number;
	      SELECT * INTO company_service FROM V_COMPANY_SERVICE vcs WHERE FLOW_TYPE_CODE  = migration_from 
	      AND "number" =service_number;
	      IF service_count = 0 THEN
	      Raise_Application_Error (-20344, 'no entry for given service number and flow type code');
	      END IF;
	      IF service_count = 1 THEN
	      UPDATE M_COMPANY_SERVICE  SET FLOW_TYPE_CODE = migration_to WHERE FLOW_TYPE_CODE  = migration_from 
	      AND "number" =service_number;
	  
	      SELECT count(*) INTO MIGRATIONCOUNT FROM  SERVICE_MIGRATION_HIS smh  WHERE M_COMP_SERVICE_NO = service_number
	      AND MG_MONTH = migration_from AND MG_YEAR = migration_to;
	
	 IF MIGRATIONCOUNT = 0 THEN  
     INSERT INTO OPENACCESS.SERVICE_MIGRATION_HIS
     (M_COMPANY_SERV_ID, M_COMP_SERVICE_NO, MG_MONTH, MG_YEAR, MG_FROM, MG_TO)
     VALUES(company_service.ID,company_service."number",migration_month,migration_year,migration_from,migration_to);
    END IF;
    VIEW_REFRESH.comp_serv_refresh(service_number, o_result_code, o_result_desc);
    VIEW_REFRESH_HISTORY.COMP_SERV_REFRESH_HISTORY_ALL(service_number,'N','N','N','Y'); 
   END IF;
		       
		   exception
          when others THEN
          v_exception_code := SQLCODE;
          v_exception_msg := SUBSTR(SQLERRM, 1, 200);
          o_result_desc := v_exception_code || ' - ' || v_exception_msg;
          dbms_output.put_line(o_result_desc);
	       END;
	 
       END SERVICE_MIGRATION_CHANGE;
        
   
   
END SERVICE_CHANGES;
