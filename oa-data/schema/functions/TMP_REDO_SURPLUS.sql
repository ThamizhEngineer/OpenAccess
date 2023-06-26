CREATE OR REPLACE FUNCTION OPENACCESS."TMP_REDO_SURPLUS" 
(
  I_MONTH IN VARCHAR2 
, I_YEAR IN VARCHAR2 
) RETURN VARCHAR2 AS 

v_count number;
v_count2 number;
  v_log_result varchar(300):='SUCCESS';

BEGIN

v_log_result := log_activity('FUNCTION','TMP_REDO_SURPLUS','START','','','ADMIN', sysdate,'');

FOR  s in (select * from missing_int_adj_units_0306)

loop

select count(*) into v_count from int_adjusted_unit where SERVICE_NO=s.SERVICE_NO and reading_mnth='00' and suplr_code=s.SUPLR_CODE;
v_log_result := log_activity('FUNCTION','TMP_REDO_SURPLUS','int_count-'||v_count,'LOOP', '','ADMIN', sysdate,'');


if v_count>0 then
delete from int_adjusted_unit where SERVICE_NO=s.SERVICE_NO and reading_mnth='00' and suplr_code=s.SUPLR_CODE;
end if;


select count(*) into v_count2 from f_energy_adjustmet where SERVICE_NO=s.SERVICE_NO and reading_mnth='009' and suplr_code=s.SUPLR_CODE;
v_log_result := log_activity('FUNCTION','TMP_REDO_SURPLUS','ENERGY_count-'||v_count2,'LOOP', '','ADMIN', sysdate,'');

if v_count2>0 then
delete from f_energy_adjustmet where SERVICE_NO=s.SERVICE_NO and reading_mnth='09' and suplr_code=s.SUPLR_CODE;
end if;

end loop;
v_log_result := log_activity('FUNCTION','TMP_REDO_SURPLUS','END','','','ADMIN', sysdate,'');

  RETURN NULL;
END TMP_REDO_SURPLUS;

