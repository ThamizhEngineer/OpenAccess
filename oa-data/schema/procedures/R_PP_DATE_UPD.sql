CREATE OR REPLACE procedure OPENACCESS.r_pp_date_upd 
is
wgrp varchar2(10):=null;

begin
--for i in (select pp.m_service_id,pp.COMMISSION_DATE from M_POWERPLANT pp,m_company_service cs 
--where pp.m_service_id=cs.id and cs.flow_type_code ='STB')  
for i in (select pp.m_service_id,pp.COMMISSION_DATE from M_POWERPLANT pp,m_company_service cs 
where pp.m_service_id=cs.id and cs.flow_type_code !='STB' 
AND pp.commission_date >= '01-04-18' AND pp.commission_date <= '31-03-21'
)  
loop
--SYS.dbms_output.put_line('service id  & year : '||i.m_service_id ||'*'||i.COMMISSION_DATE);
--select weg_group_code into wgrp from m_tariff
--where i.commission_date between from_date and to_date;
select weg_group_code into wgrp from r_tariff_wind
where i.commission_date between from_date and to_date  ;
--insert into r_weg_code_update values (i.m_service_id,i.COMMISSION_DATE,wgrp);

update m_powerplant set PLANT_CLASS_TYPE_CODE=wgrp where m_service_id=i.m_service_id ;
end loop;
end;