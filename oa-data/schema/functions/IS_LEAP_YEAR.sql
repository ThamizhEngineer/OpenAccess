CREATE OR REPLACE FUNCTION OPENACCESS."IS_LEAP_YEAR" (nYr in number) return VARCHAR2 is
v_day varchar2(2);
begin
  select to_char(last_day(to_date( '01-FEB-'|| to_char(nYr), 'DD-MON-YYYY')), 'DD') into v_day from dual;
  if v_day = '29' then -- if v_day = 29 then it must be a leap year, return TRUE
    return 'TRUE';
  else
    return 'FALSE';  -- otherwise year is not a leap year, return false
  end if;
end;


