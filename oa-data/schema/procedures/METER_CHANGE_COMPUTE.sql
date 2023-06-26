CREATE OR REPLACE PROCEDURE OPENACCESS."METER_CHANGE_COMPUTE" (v_sc_no varchar2,v_r_month varchar2,v_r_year varchar2,v_loss_pc number) AS 
BEGIN
--this proc is called from METER_CHANGE_TABLE_INS proc
--TO compute consumption units from the readings stored in R_METER_CHANGE and store into an
--intermediate table R_METER_CHANGE_INT
--and final result table R_INT_METER_CHANGE.
 DECLARE 
 v_old_meter varchar2(50);v_new_meter varchar2(50);
 --v_sc_no varchar2(50):='0039224341047'; 
 --v_r_month varchar2(50):='01';  v_r_year varchar2(50):='2020';

 v_meterno varchar2(50):=''; v_ikwhc1_diff varchar2(50):='';v_ikwhc2_diff varchar2(50):='';
  v_ikwhc3_diff varchar2(50):='';v_ikwhc4_diff varchar2(50):='';v_ikwhc5_diff varchar2(50):='';
  v_ekwhc1_diff varchar2(50):='';
  v_ekwhc2_diff varchar2(50):='';v_ekwhc3_diff varchar2(50):='';v_ekwhc4_diff varchar2(50):='';
  v_ekwhc5_diff varchar2(50):='';v_ikvah_diff varchar2(50):='';v_kvarh_diff varchar2(50):='';v_mf  varchar2(50):='';

  v_ekwhc1_units varchar2(50):='';
  v_ekwhc2_units varchar2(50):='';v_ekwhc3_units varchar2(50):='';v_ekwhc4_units varchar2(50):='';
  v_ekwhc5_units varchar2(50):='';v_ikvah_units varchar2(50):='';v_kvarh_units varchar2(50):='';

     v_ikwhc1_units varchar2(50):='';
  v_ikwhc2_units varchar2(50):='';v_ikwhc3_units varchar2(50):='';v_ikwhc4_units varchar2(50):='';
  v_ikwhc5_units varchar2(50):='';

  v_exp_units varchar2(50):='';  v_imp_units varchar2(50):='';
   v_net_units varchar2(50):='';

    v_net_c1_units varchar2(50):='';
  v_net_c2_units varchar2(50):='';v_net_c3_units varchar2(50):='';v_net_c4_units varchar2(50):='';
  v_net_c5_units varchar2(50):='';

  v_rdg_date date:='';
  v_remarks varchar2(100);

  v_loss_frac number;

begin


--SYS.-- dbms_output.put_line('v_r_month'||v_sc_no);
--SYS.-- dbms_output.put_line('v_r_month'||v_r_month);
--SYS.-- dbms_output.put_line('v_r_year'||v_r_year);

select oldmeterno,newmeterno
into v_old_meter,v_new_meter
from r_meter_change_from_amr where newmeterno=
(SELECT meter_number FROM  v_company_service WHERE  "number" IN (v_sc_no))
;

--bad design of code -  to remove loop and change it
 FOR loop_mtr IN (
 select T1.reading_date,T1.m_company_meter_id,
round(t2.ikwhc1-t1.ikwhc1,3) as ikwhc1_diff ,round(t2.ikwhc2-t1.ikwhc2,3) as ikwhc2_diff,round(t2.ikwhc3-t1.ikwhc3,3) as ikwhc3_diff,round(t2.ikwhc4-t1.ikwhc4,3) as ikwhc4_diff,
round(t2.ikwhc5-t1.ikwhc5,3) as ikwhc5_diff,
round(t2.ekwhc1-t1.ekwhc1,3) as ekwhc1_diff,round(t2.ekwhc2-t1.ekwhc2,3)  as ekwhc2_diff,round(t2.ekwhc3-t1.ekwhc3,3) as ekwhc3_diff,
round(t2.ekwhc4-t1.ekwhc4,3) as ekwhc4_diff,
round(t2.ekwhc5-t1.ekwhc5,3) as ekwhc5_diff,
round(t2.kvah-t1.kvah,3) as kvah_diff,round(t2.kvarh-t1.kvarh,3) as kvarh_diff,t1.mf 
from r_meter_change T1
inner join r_meter_change T2
on T1.m_company_meter_id=T2.m_company_meter_id
and T2.reading_date>T1.reading_date and T1.m_company_meter_id in (v_old_meter,v_new_meter) and T1.sc_no = v_sc_no) loop
-- and T1.m_company_meter_id in (v_old_meter,v_new_meter)
--T1.m_company_meter_id in (v_new_meter)

v_ekwhc1_units := loop_mtr.ekwhc1_diff * loop_mtr.mf; v_ekwhc2_units := loop_mtr.ekwhc2_diff * loop_mtr.mf;v_ekwhc3_units := loop_mtr.ekwhc3_diff * loop_mtr.mf;
v_ekwhc4_units := loop_mtr.ekwhc4_diff * loop_mtr.mf;v_ekwhc5_units := loop_mtr.ekwhc5_diff * loop_mtr.mf;

--loss is applicable only for Exported units for 10_1 SS case
if(v_loss_pc>0) then
v_loss_frac:=v_loss_pc/100;
v_ekwhc1_units := v_ekwhc1_units - v_loss_frac*v_ekwhc1_units ; 
v_ekwhc2_units := v_ekwhc2_units - v_loss_frac*v_ekwhc2_units ; 
v_ekwhc3_units := v_ekwhc3_units - v_loss_frac*v_ekwhc3_units ; 
v_ekwhc4_units := v_ekwhc4_units - v_loss_frac*v_ekwhc4_units ; 
v_ekwhc5_units := v_ekwhc5_units - v_loss_frac*v_ekwhc5_units ;
end if;

v_ikwhc1_units := loop_mtr.ikwhc1_diff * loop_mtr.mf;v_ikwhc2_units := loop_mtr.ikwhc2_diff * loop_mtr.mf;v_ikwhc3_units := loop_mtr.ikwhc3_diff * loop_mtr.mf;
v_ikwhc4_units := loop_mtr.ikwhc4_diff * loop_mtr.mf;v_ikwhc5_units := loop_mtr.ikwhc5_diff * loop_mtr.mf;

--loop_mtr.imp_units :=loop_mtr.ikwhc1_units+loop_mtr.ikwhc2_units+loop_mtr.ikwhc3_units+loop_mtr.ikwhc4_units+loop_mtr.ikwhc5_units;

v_kvarh_units := loop_mtr.kvarh_diff *loop_mtr.mf;
v_ikvah_units := loop_mtr.kvah_diff * loop_mtr.mf;

--loop_mtr.net_units := loop_mtr.exp_units-loop_mtr.imp_units;

-- dbms_output.put_line('insert into r_meter_change_int values(loop_mtr.rdg_date,loop_mtr.meterno,loop_mtr.ikwhc1_units,loop_mtr.ikwhc2_units,loop_mtr.ikwhc3_units ,loop_mtr.ikwhc4_units,
--  loop_mtr.ikwhc5_units, loop_mtr.ekwhc1_units , loop_mtr.ekwhc2_units,loop_mtr.ekwhc3_units ,loop_mtr.ekwhc4_units,
--  loop_mtr.ekwhc5_units,loop_mtr.ikvah_units,loop_mtr.kvarh_units,loop_mtr.mf' );

insert into r_meter_change_int values(loop_mtr.reading_date,loop_mtr.m_company_meter_id,v_ikwhc1_units,v_ikwhc2_units,v_ikwhc3_units ,v_ikwhc4_units,
  v_ikwhc5_units, v_ekwhc1_units , v_ekwhc2_units,v_ekwhc3_units ,v_ekwhc4_units,
  v_ekwhc5_units,v_ikvah_units,v_kvarh_units,loop_mtr.mf,v_sc_no );

    end loop;

  --=========computing sum of old and new meter======= 
select sum(ikwhc1),
sum(ikwhc2),sum(ikwhc3),sum(ikwhc4),sum(ikwhc5),
sum(ekwhc1),sum(ekwhc2)  ,sum(ekwhc3),sum(ekwhc4),sum(ekwhc5),
sum(kvah) ,sum(kvarh) , sum(mf) 
into 
v_ikwhc1_units,v_ikwhc2_units,v_ikwhc3_units ,v_ikwhc4_units,
  v_ikwhc5_units, v_ekwhc1_units , v_ekwhc2_units,v_ekwhc3_units ,v_ekwhc4_units,
  v_ekwhc5_units,v_ikvah_units,v_kvarh_units,v_mf
  from r_meter_change_int
where m_company_meter_id in (v_old_meter,v_new_meter)  ;



    v_imp_units :=v_ikwhc1_units+v_ikwhc2_units+v_ikwhc3_units+v_ikwhc4_units+v_ikwhc5_units;
v_exp_units :=v_ekwhc1_units+v_ekwhc2_units+v_ekwhc3_units+v_ekwhc4_units+v_ekwhc5_units;
v_net_units := v_exp_units-v_imp_units;

if(v_net_units < 0) then
    v_net_units :=0;
end if;

  v_net_c1_units := v_ekwhc1_units-v_ikwhc1_units;
  v_net_c2_units :=v_ekwhc2_units-v_ikwhc2_units;
  v_net_c3_units  := v_ekwhc3_units-v_ikwhc3_units;v_net_c4_units  :=v_ekwhc4_units-v_ikwhc4_units;
  v_net_c5_units :=v_ekwhc5_units-v_ikwhc5_units;
  v_remarks:='MC-'||v_sc_no||'-'||sysdate;
  if (v_loss_pc > 0) then
  v_remarks:='MC-'||v_sc_no||'-loss-'||v_loss_pc||'-'||sysdate;
  end if;


INSERT INTO r_int_meter_change VALUES (    'id',    v_sc_no,    v_old_meter,    v_new_meter,    v_r_month, 
v_r_year,    v_imp_units,    v_exp_units,    v_kvarh_units,    v_net_units, 
v_remarks,    v_ikwhc1_units,    v_ikwhc2_units,    v_ikwhc3_units,
v_ikwhc4_units,    v_ikwhc5_units,    v_ekwhc1_units,    v_ekwhc2_units,    v_ekwhc3_units,    v_ekwhc4_units,
v_ekwhc5_units,    v_net_c1_units,    v_net_c2_units,    v_net_c3_units,    v_net_c4_units,    v_net_c5_units,
'',    'PROC METER_CHANGE_TABLE_INS ',    sysdate,    '',    '');


end;
END METER_CHANGE_COMPUTE;