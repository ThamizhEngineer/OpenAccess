CREATE OR REPLACE PROCEDURE OPENACCESS."UPDATE_VOLTAGE_INPP" AS 
BEGIN

 /*
    for pp in (select pp.id,  trim(ser.fuel_type_code) fuel_type_code from m_powerplant pp join m_company_service ser on pp.m_service_id = ser.id
                    where pp.fuel_type_code is null or pp.fuel_type_code <>ser.fuel_type_code)
    loop
        update m_powerplant set fuel_type_code = pp.fuel_type_code, modified_by='abul-voltage-update', modified_date=sysdate where id=pp.id;
    end loop;
    */
    null;
END UPDATE_VOLTAGE_INPP;

