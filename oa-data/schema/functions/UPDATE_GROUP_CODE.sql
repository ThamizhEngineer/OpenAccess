CREATE OR REPLACE FUNCTION OPENACCESS."UPDATE_GROUP_CODE" RETURN VARCHAR2 AS 

v_plant_class_type varchar(20);

BEGIN

    for gs in(SELECT * FROM t_gen_stmt where stmt_month='05' and plant_class_type_code is null)
    loop

    select plant_class_type_code into v_plant_class_type from m_powerplant where m_service_id=gs.M_COMPANY_SERVICE_ID;
    update t_gen_stmt set plant_class_type_code=v_plant_class_type where id=gs.id;
    end loop;

   RETURN NULL;

END UPDATE_GROUP_CODE;

