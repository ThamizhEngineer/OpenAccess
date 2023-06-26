CREATE OR REPLACE PROCEDURE OPENACCESS.R_UPDATE_SALE_ORDER_LINE_ID(r_mon varchar2, r_yr VARCHAR2) AS 
BEGIN
declare
esol_id varchar2(10);
--r_mon varchar2(10);
--r_yr varchar2(10);
--,gen varchar2
begin
FOR loop_upd IN (select service_no,suplr_code,f_energy_sale_order_id ,f_energy_sale_order_line_id
from f_energy_adjustmet
where 
reading_mnth=r_mon and reading_yr=r_yr and
-- suplr_code=gen and
f_energy_sale_order_line_id is null    )
	LOOP
SYS.dbms_output.put_line('MON/yr'|| r_mon||'/'|| r_yr||  'gener : '||  loop_upd.suplr_code || ' buyer  : ' || loop_upd.service_no    );  

    select esol.id into esol_id from f_energy_sale_order eso,
f_energy_sale_order_lines esol
where eso.id = loop_upd.f_energy_sale_order_id
and esol.f_energy_sale_order_id = eso.id and
esol.d_buyer_comp_serv_name  = loop_upd.service_no 
and eso.month=r_mon and eso.year=r_yr;

    update f_energy_adjustmet ea set ea.f_energy_sale_order_line_id = esol_id,
    import_remarks = concat('SOL upd-18-06-20-old :',loop_upd.f_energy_sale_order_line_id)
where ea.f_energy_sale_order_id = loop_upd.f_energy_sale_order_id and
ea.service_no=loop_upd.service_no and ea.suplr_code = loop_upd.suplr_code and
ea.reading_mnth=r_mon  and ea.reading_yr=r_yr;
SYS.dbms_output.put_line('MON/yr'|| r_mon||'/'|| r_yr||  'gener : '||  loop_upd.suplr_code || ' buyer  : ' || loop_upd.service_no  ||'line id : '  || esol_id  );  

 		END LOOP;
        end;
END R_UPDATE_SALE_ORDER_LINE_ID;