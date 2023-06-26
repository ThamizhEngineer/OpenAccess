CREATE OR REPLACE PROCEDURE OPENACCESS."METER_CHANGE_TABLE_INS" (v_r_month varchar2,v_r_year varchar2,ten_1_ss number) AS 
--create or replace PROCEDURE METER_CHANGE_TABLE_INS(v_sc_no varchar2,v_r_month varchar2,v_r_year varchar2,ten_1_ss number) AS 

--this procedure is to merge data from three tables to aid in meter change
--1.T_METER_READING_HDR
--2.T_METER_READING_SLOT
--3.T_HIS_MASTER_METER_CHANGE(This table comes from AMR)

--two sub procedures - METER_CHANGE_TABLE_INS_SUB & METER_CHANGE_COMPUTE are called in this procedure
--The procedure requires following parameters :
--v_r_month varchar2 : reading month
--v_r_year varchar2 : reading year
--ten_1_ss number : 0- not 10_1, 1- means its a 10_1_ss ; 


BEGIN
DECLARE 

  v_log_result VARCHAR2(100);
 -- v_meter_id varchar2(50) := '303830';v_new_meter_no varchar2(50) := 'HT2180034';
v_new_meter_no varchar2(50) := ''; v_m_company_meter_id varchar2(50) := '';
  v_r_prev_month varchar2(50):='';  v_r_prev_year varchar2(50):=''; 
  v_result_code  varchar2(50):='FAILURE';
v_sc_no varchar2(15):='';
  v_modifydate date:='';v_oldmeterno varchar2(50):=''; v_oldikwhtod1 varchar2(50):='';v_oldikwhtod2 varchar2(50):='';
  v_oldikwhtod3 varchar2(50):='';v_oldikwhtod4 varchar2(50):='';v_oldikwhtod5 varchar2(50):='';v_oldekwhtod1 varchar2(50):='';
  v_oletkwhtod2 varchar2(50):='';v_oldekwhtod3 varchar2(50):='';v_oldekwhtod4 varchar2(50):='';
  v_oldekwhtod5 varchar2(50):='';v_oldikvah varchar2(50):='';v_oldkvarh varchar2(50):='';v_oldmf  varchar2(50):='';
  v_newmeterno varchar2(50):=''; v_newikwhtod1 varchar2(50):='';v_newikwhtod2 varchar2(50):='';
  v_newikwhtod3 varchar2(50):='';v_newikwhtod4 varchar2(50):='';v_newikwhtod5 varchar2(50):='';v_newekwhtod1 varchar2(50):='';
  v_newekwhtod2 varchar2(50):='';v_newekwhtod3 varchar2(50):='';v_newekwhtod4 varchar2(50):='';
  v_newekwhtod5 varchar2(50):='';v_newikvah varchar2(50):='';v_newkvarh varchar2(50):='';v_newmf  varchar2(50):='';
v_loss_pc varchar2(50):='';

  begin

v_r_prev_month := v_r_month - 1;
if(v_r_prev_month = '0') then
    v_r_prev_month := '12';
    v_r_prev_year := v_r_year - 1;
else
    v_r_prev_year := v_r_year ;
end if;

 v_r_prev_month:=lpad(v_r_prev_month,2,0);


execute immediate 'truncate table r_meter_change';   
execute immediate 'truncate table r_meter_change_int';  
execute immediate 'truncate table r_int_meter_change';   

FOR loop_meter IN (SELECT "number",m_company_meter_id,meter_number FROM  v_company_service WHERE  "number" in ('079204720837'))
LOOP
  v_log_result := log_activity('PROCEDURE','METER_CHANGE_TABLE_INS','START','','','','', sysdate,'');

  v_sc_no := loop_meter."number";
  v_new_meter_no := loop_meter.meter_number;
  v_m_company_meter_id := loop_meter.m_company_meter_id;

--sys.dbms_output.put_line('SELECT "number",m_company_meter_id,meter_number FROM  v_company_service WHERE  "number" =v_sc_no');  
--SYS dbms_output.put_line('meter change ins');  
SYS.dbms_output.put_line('v_new_meter_no'||v_new_meter_no);
--SYS.-- dbms_output.put_line('v_r_month'||v_r_month);
--SYS.-- dbms_output.put_line('v_r_prev_month'||v_r_year);
--SYS.-- dbms_output.put_line('v_r_prev_year'||v_r_year);


 select modifydate,oldmeterno,oldikwhtod1,oldikwhtod2,oldikwhtod3,oldikwhtod4,oldikwhtod5,oldekwhtod1,oletkwhtod2,oldekwhtod3,oldekwhtod4,oldekwhtod5,
 oldikvah,oldq1kvarh+oldq2kvarh,oldmf,
 newmeterno, newikwhtod1,newikwhtod2,newikwhtod3,newikwhtod4,newikwhtod5,newekwhtod1,newekwhtod2,newekwhtod3,newekwhtod4,newekwhtod5,newikvah,newq1kvarh+newq2kvarh,newmf
 INTO 
 v_modifydate,v_oldmeterno,v_oldikwhtod1,v_oldikwhtod2,v_oldikwhtod3,v_oldikwhtod4,v_oldikwhtod5,v_oldekwhtod1,v_oletkwhtod2,v_oldekwhtod3,v_oldekwhtod4
 ,v_oldekwhtod5, v_oldikvah,v_oldkvarh,v_oldmf,
 v_newmeterno, v_newikwhtod1,v_newikwhtod2,v_newikwhtod3,v_newikwhtod4,v_newikwhtod5,v_newekwhtod1,v_newekwhtod2,v_newekwhtod3,v_newekwhtod4,v_newekwhtod5,
 v_newikvah,v_newkvarh,v_newmf
 FROM r_meter_change_from_amr WHERE newmeterno=v_new_meter_no;

-- sys.dbms_output.put_line('insert into r_meter_change values('||v_modifydate||','||v_oldmeterno||','||v_oldikwhtod1||','||v_oldikwhtod2||','||v_oldikwhtod3||','||v_oldikwhtod4||','||v_oldikwhtod5||','||v_oldekwhtod1||','||v_oletkwhtod2||','||v_oldekwhtod3||','||v_oldekwhtod4
--||','||v_oldekwhtod5||','||v_oldikvah||','||v_oldkvarh||','||v_oldmf||','||v_sc_no||'');
--  SYS.-- dbms_output.put_line('meter change ins : v_oldmeterno'||v_oldmeterno);
   insert into r_meter_change values(v_modifydate,v_oldmeterno,v_oldikwhtod1,v_oldikwhtod2,v_oldikwhtod3,v_oldikwhtod4,v_oldikwhtod5,v_oldekwhtod1,v_oletkwhtod2,v_oldekwhtod3,v_oldekwhtod4
 ,v_oldekwhtod5,v_oldikvah,v_oldkvarh,v_oldmf,v_sc_no);

  insert into r_meter_change values(v_modifydate,v_newmeterno,v_newikwhtod1,v_newikwhtod2,v_newikwhtod3,v_newikwhtod4,v_newikwhtod5,v_newekwhtod1,v_newekwhtod2,v_newekwhtod3,v_newekwhtod4
 ,v_newekwhtod5,v_newikvah,v_newkvarh,v_newmf,v_sc_no);

---entering reading particulars for OLD AND NEW METER----------
    meter_change_table_ins_sub(v_m_company_meter_id,v_oldmeterno,v_r_prev_month,v_r_prev_year,v_sc_no);
    meter_change_table_ins_sub(v_m_company_meter_id,v_newmeterno,v_r_month,v_r_year,v_sc_no);

           if(ten_1_ss = '1') then
       select loss_percent into v_loss_pc from t_substation_loss where m_substation_id=(select m_substation_id from v_company_service where "number"=v_sc_no) and month=v_r_month
       and year=v_r_year;
       end if;
    Meter_change_compute(v_sc_no,v_r_month,v_r_year,v_loss_pc);



   END LOOP; 

     end;
END METER_CHANGE_TABLE_INS;