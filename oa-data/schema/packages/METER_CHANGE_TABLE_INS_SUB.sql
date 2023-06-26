CREATE OR REPLACE PROCEDURE OPENACCESS."METER_CHANGE_TABLE_INS_SUB" (v_m_company_meter_id varchar2,v_meter_no varchar2,v_r_month varchar2,v_r_year varchar2,v_sc_no varchar2 ) AS 
BEGIN
--makes use of an intermediate array for merging slot data 079204721346	295443	190037880
--this proc is called from METER_CHANGE_TABLE_INS proc
DECLARE
type type1 is table of VARCHAR2(50 BYTE);
  type data_type is table of type1;
  slot_array data_type;
    v_m_reading_hdr_id varchar2(50):='';      i number;      v_mf_ins varchar2(50):='';
    v_mf varchar2(50):='';        v_fin_rdg_date date:='';          v_imp_kvah_final varchar2(100):='';
    v_imp_rkvah_final varchar2(100):=''; 

BEGIN
SYS.dbms_output.put_line('METER_CHANGE_TABLE_INS_SUB');  
SYS.dbms_output.put_line('v_m_company_meter_id'||v_m_company_meter_id);
SYS.dbms_output.put_line('v_meter_no'||v_meter_no);
SYS.dbms_output.put_line('v_r_month'||v_r_month);
SYS.dbms_output.put_line('v_r_year'||v_r_year);

select id into v_m_reading_hdr_id from t_meter_reading_hdr where  m_company_meter_id=v_m_company_meter_id 
 and reading_month=v_r_month and reading_year=v_r_year;

   slot_array := data_type();
  slot_array.extend(5);
  for i in 1..5 loop
    slot_array(i) := type1();
    slot_array(i).extend(4);
    for j in 1..4 loop
         slot_array(i)(j) := 0; 
       end loop;
    end loop;

   i := 1;
  --SLOT DATA IMPORTED INTO AN ARRAY - 
   --fetch slot data and populate an 5x4 array 
  for slt_var in (select slot_code,imp_final,exp_final from T_METER_READING_HDR mrh,T_METER_READING_SLOT mrs
  where mrh.id=mrs.T_METER_READING_HDR_ID
  and mrs.T_METER_READING_HDR_ID=v_m_reading_hdr_id order by slot_code)loop
    slot_array(i).extend(2);
    slot_array(i)(1) := round(slt_var.imp_final,2); 
    slot_array(i)(2) := round(slt_var.exp_final,2);
--   SYS.-- dbms_output.put_line(slot_array(i)(1));
--    SYS.-- dbms_output.put_line(slot_array(i)(2));
         i := i+1;
     end loop;

     --===========================INSERTION OF FIRST & LAST RECORD(FINAL READING DATA OF OLD & NEW)=========================================
-- for i in 1..5 loop
--    for j in 1..4 loop
--        SYS.-- dbms_output.put_line('slot array i: ' ||i ||' j : '||j  ||' val : '||slot_array(i)(j));
--         end loop;
--    end loop;

 select mf,final_reading_dt,round(imp_kvah_final,2),round(imp_rkvah_final,2) 
      into v_mf,v_fin_rdg_date,v_imp_kvah_final,v_imp_rkvah_final 
      from t_meter_reading_hdr where m_company_meter_id=v_m_company_meter_id and id=v_m_reading_hdr_id;


       insert into r_meter_change values(v_fin_rdg_date,v_meter_no,slot_array(1)(1),slot_array(2)(1),slot_array(3)(1),slot_array(4)(1),slot_array(5)(1),
      slot_array(1)(2),slot_array(2)(2),slot_array(3)(2),slot_array(4)(2),slot_array(5)(2),v_imp_kvah_final,v_imp_rkvah_final,v_mf,v_sc_no);
 end;     
END METER_CHANGE_TABLE_INS_SUB;