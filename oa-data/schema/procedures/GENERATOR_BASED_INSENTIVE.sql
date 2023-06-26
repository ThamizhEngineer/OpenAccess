CREATE OR REPLACE PROCEDURE OPENACCESS."GENERATOR_BASED_INSENTIVE" (service_number VARCHAR2,r_month VARCHAR2,r_year VARCHAR2)
AS 
BEGIN
declare
import_units_total VARCHAR2(200);
export_units_total VARCHAR2(200);
net_units_difference varchar2(200);
gen_id VARCHAR2(50);
m_com_meter_id varchar2(50);
tariff_r VARCHAR2(100);
tariff_net_amt VARCHAR2(100);
total_charges_sum VARCHAR2(200);
net_payables_cal VARCHAR2(200);

begin
select id into gen_id from t_gen_stmt where disp_service_number=service_number and stmt_month=r_month and stmt_year=r_year;
select m_company_meter_id into m_com_meter_id from t_gen_stmt where disp_service_number=service_number and stmt_month=r_month and stmt_year=r_year;
select total_import_gen into import_units_total from t_meter_reading_hdr where m_company_meter_id=m_com_meter_id and reading_month=r_month and reading_year=r_year;
select total_export_gen into export_units_total from t_meter_reading_hdr where m_company_meter_id=m_com_meter_id and reading_month=r_month and reading_year=r_year;
--select import_units_total into sum(imp_units) from t_gen_stmt_slot where t_gen_stmt_id=gen_id;
--select export_units_total into sum(exp_units) from t_gen_stmt_slot where t_gen_stmt_id=gen_id;
net_units_difference:=export_units_total-import_units_total;

update t_gen_stmt set net_generation=net_units_difference where id=gen_id;

update t_meter_reading_hdr set net_gen_units=net_units_difference where m_company_meter_id=m_com_meter_id and reading_month=r_month and 
reading_year=r_year;

select tariff_rate into tariff_r from t_gen_stmt where id=gen_id;

tariff_net_amt:=tariff_r*net_units_difference;

update t_gen_stmt set tariff_net_amount=tariff_net_amt where id=gen_id;

select sum(total_charges) into total_charges_sum from t_gen_stmt_charge where t_gen_stmt_id=gen_id AND CHARGE_CODE<>'C006';

net_payables_cal:=tariff_net_amt-total_charges_sum;

update t_gen_stmt set net_payable=net_payables_cal where id=gen_id;
update T_GEN_STMT_CHARGE set TOTAL_CHARGES ='0' where T_GEN_STMT_ID= gen_id and CHARGE_CODE='C006'; ---my

end;
END GENERATOR_BASED_INSENTIVE;