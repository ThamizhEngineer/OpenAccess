--------------------------------------------------------
--  File created - Monday-April-09-2018   
--------------------------------------------------------
DROP FUNCTION "OPENACCESS"."IS_LEAP_YEAR";
--------------------------------------------------------
--  DDL for Function IS_LEAP_YEAR
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "OPENACCESS"."IS_LEAP_YEAR" (nYr in number) return VARCHAR2 is 
v_day varchar2(2); 
begin 
  select to_char(last_day(to_date( '01-FEB-'|| to_char(nYr), 'DD-MON-YYYY')), 'DD') into v_day from dual; 
  if v_day = '29' then -- if v_day = 29 then it must be a leap year, return TRUE 
    return 'TRUE'; 
  else 
    return 'FALSE';  -- otherwise year is not a leap year, return false 
  end if; 
end;


/
-- Unable to render SYNONYM DDL for object PUBLIC.DUAL with DBMS_METADATA attempting internal generator.
CREATE PUBLIC SYNONYM DUAL FOR SYS."DUAL"
