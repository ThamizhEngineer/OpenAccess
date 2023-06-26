CREATE OR REPLACE FUNCTION OPENACCESS."TMP_METER_CHANGE" 
(
i_remarks varchar2
) RETURN VARCHAR2 AS 

v_id varchar2(20);
v_m_id varchar2(20);
v_s_id varchar2(20);
v_number varchar2(20);
v_MONTH varchar2(20);
v_YEAR varchar2(20);
v_result varchar2(50);
o_res_code varchar2(50);
o_res_desc varchar2(200);

BEGIN

     for im in(select * from int_meter_change where REMARKS=i_remarks)
     loop

  --   if im.reading_month != to_char( sysdate, 'mm' ) and im.reading_year != to_char( sysdate, 'yyyy' ) then

--          if im.reading_month = to_char( sysdate, 'mm' ) - 1 then

     select id,"number" into v_m_id,v_number from m_company_service where "number"=im.service_no;

     select id into v_id from m_company_meter where M_COMPANY_SERVICE_ID=v_m_id;

     select id,READING_MONTH,READING_YEAR into v_s_id,v_MONTH,v_YEAR from t_meter_reading_hdr where READING_MONTH = im.reading_month and READING_YEAR = im.reading_year and M_COMPANY_METER_ID = v_id; 


     update t_meter_reading_hdr set TOTAL_IMPORT_GEN=trim(im.total_import_units), TOTAL_EXPORT_GEN=trim(im.total_export_units), RKVAH_UNITS=trim(im.rkvah_units), NET_GEN_UNITS=trim(im.total_net_units)
     where READING_MONTH = im.reading_month and M_COMPANY_METER_ID = v_id;

     update t_meter_reading_slot set IMP_UNITS=trim(im.IMP_C1_UNITS), EXP_UNITS=trim(im.EXP_C1_UNITS), NET_UNITS=trim(im.NET_C1_UNITS) where T_METER_READING_HDR_ID = v_s_id and slot_code='C1';

     update t_meter_reading_slot set IMP_UNITS=trim(im.IMP_C2_UNITS), EXP_UNITS=trim(im.EXP_C2_UNITS), NET_UNITS=trim(im.NET_C2_UNITS) where T_METER_READING_HDR_ID = v_s_id and slot_code='C2';

     update t_meter_reading_slot set IMP_UNITS=trim(im.IMP_C3_UNITS), EXP_UNITS=trim(im.EXP_C3_UNITS), NET_UNITS=trim(im.NET_C3_UNITS) where T_METER_READING_HDR_ID = v_s_id and slot_code='C3';

     update t_meter_reading_slot set IMP_UNITS=trim(im.IMP_C4_UNITS), EXP_UNITS=trim(im.EXP_C4_UNITS), NET_UNITS=trim(im.NET_C4_UNITS) where T_METER_READING_HDR_ID = v_s_id and slot_code='C4';

     update t_meter_reading_slot set IMP_UNITS=trim(im.IMP_C5_UNITS), EXP_UNITS=trim(im.EXP_C5_UNITS), NET_UNITS=trim(im.NET_C5_UNITS) where T_METER_READING_HDR_ID = v_s_id and slot_code='C5';

     update m_company_meter set METER_NUMBER=trim(im.NEW_METER_NO) where M_COMPANY_SERVICE_ID=v_m_id and METER_NUMBER=trim(im.OLD_METER_NO);


     DELETE_TXN.DELETE_BY_SERVICE('Meter_change',v_number,v_MONTH,v_YEAR,'Y','Y','Y','N',o_res_code,o_res_desc);

     update int_meter_change set IMPORT_REMARKS='success' where service_no=v_number;

    -- else 

  --   update int_meter_change set IMPORT_REMARKS='failure' where service_no=v_number;

   ---  return 'download meter reading';

   --  end if;

     END LOOP;

     v_result:=CREATE_GS('',v_MONTH,v_YEAR);

  RETURN 'success';

END ;