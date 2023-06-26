CREATE OR REPLACE PROCEDURE OPENACCESS."PP_DATE_UPD" 
is
wgrp varchar2(10):=null;
begin
--for i in (select pp.m_service_id,pp.COMMISSION_DATE from M_POWERPLANT pp,m_company_service cs 
--where pp.m_service_id=cs.id and cs.flow_type_code ='STB')  
for i in (select pp.m_service_id,pp.COMMISSION_DATE from M_POWERPLANT pp,m_company_service cs 
where pp.m_service_id=cs.id and cs.flow_type_code !='STB' and pp.commission_date > '01-01-12'
)  
loop
--select weg_group_code into wgrp from m_tariff
--where i.commission_date between from_date and to_date;
select weg_group_code into wgrp from m_tariff
where i.commission_date between from_date and to_date and lower(type) like '%wind%' and remarks = 'WITHOUT AD';
insert into r_weg_code_update values (i.m_service_id,i.COMMISSION_DATE,wgrp);
--update m_powerplant set PLANT_CLASS_TYPE_CODE=wgrp where m_service_id=i.m_service_id ;
end loop;
end;

