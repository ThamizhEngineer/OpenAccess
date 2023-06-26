CREATE OR REPLACE FUNCTION OPENACCESS."BK_TO_UPDATE_CURC1" RETURN VARCHAR2 AS 
v_voltage_cur sys_refcursor ;
v_service_number varchar2(50);
serNo varchar2(50);
master_service_count varchar2(50);
v_comp_id varchar2(50);
v_banking_service_id varchar2(50);
v_c1 varchar2(50);
v_c2 varchar2(50);
v_c3 varchar2(50);
v_c4 varchar2(50);
v_c5 varchar2(50);
v_bb_count varchar2(50);
v_month varchar2(10):='02';
v_year varchar2(10):='2019';
BEGIN

  OPEN v_voltage_cur FOR SELECT SERVICE_NO FROM BK_UPD_CURVALUES;
  LOOP
  FETCH v_voltage_cur INTO v_service_number;
  SELECT COUNT(*) INTO master_service_count FROM M_COMPANY_SERVICE WHERE "number"=v_service_number;
  -- dbms_output.put_line('v_service_number '||v_service_number);
  -- dbms_output.put_line('master_service_count '||master_service_count);
  IF(master_service_count>0)THEN
  SELECT M_COMPANY_ID,banking_service_id INTO v_comp_id,v_banking_service_id FROM M_COMPANY_SERVICE WHERE "number"=v_service_number  fetch first 1 rows only;
  SELECT C1,C2,C3,C4,C5 INTO v_c1,v_c2,v_c3,v_c4,v_c5 FROM BK_UPD_CURVALUES WHERE SERVICE_NO=v_service_number  fetch first 1 rows only;
  select count(*) into v_bb_count from T_BANKING_BALANCE WHERE M_COMPANY_ID=v_comp_id;
  -- dbms_output.put_line('v_bb_count '||v_bb_count);
  if(v_bb_count >0) then
   ---UPDATE T_BANKING_BALANCE SET CURR_C1=nvl(curr_c1,0)+v_c1,CURR_C2=nvl(curr_c2,0)+v_c2,CURR_C3=nvl(curr_c3,0)+v_c3,CURR_C4=nvl(curr_c4,0)+v_c4,CURR_C5=nvl(curr_c5,0)+v_c5 WHERE banking_service_id=v_banking_service_id and MONTH=v_month and YEAR = v_year;
    UPDATE T_BANKING_BALANCE SET CURR_C1=v_c1,CURR_C2=v_c2,CURR_C3=v_c3,CURR_C4=v_c4,CURR_C5=v_c5 WHERE banking_service_id=v_banking_service_id and MONTH=v_month and YEAR = v_year;
  else
    insert into t_banking_balance (id, remarks, MONTH, YEAR,C1,C2,C3,C4,C5,CREATED_BY,CREATED_DT, ENABLED,M_COMPANY_ID, BANKING_SERVICE_ID,CURR_C1,CURR_C2,CURR_C3,CURR_C4,CURR_C5,CALCULATED)
        (SELECT T_BANKING_BALANCE_SEQ.nextval ID, 'BK_TO_UPDATE_CURC1', v_month, v_year,v_c1,v_c2,v_c3,v_c4,v_c5,'admin',sysdate, 'Y',v_comp_id, v_banking_service_id,v_c1,v_c2,v_c3,v_c4,v_c5,'N'
          FROM dual
        );
   end if;

  END IF;
  EXIT WHEN v_voltage_cur%NOTFOUND;
  END LOOP;
  COMMIT;
 RETURN 'success';
END BK_TO_UPDATE_CURC1;

