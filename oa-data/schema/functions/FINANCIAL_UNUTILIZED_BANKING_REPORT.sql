CREATE OR REPLACE FUNCTION OPENACCESS."FINANCIAL_UNUTILIZED_BANKING_REPORT" 
(
  V_MONTH IN VARCHAR2,
  V_YEAR IN VARCHAR2
) RETURN VARCHAR2 AS

v_total varchar(300);
v_log_result varchar(300):='SUCCESS';
v_exception_code VARCHAR2(150);
v_exception_msg  VARCHAR2(150);
v_reason VARCHAR2(300);
o_result_desc VARCHAR2(300);
o_result_code VARCHAR2(300);

v_table FINANCIAL_UNUTILIZED_BANKING%ROWTYPE;

BEGIN
BEGIN
    v_log_result := log_activity('FUNCTION','FINANCIAL_UNUTILIZED_BANKING_REPORT','START',V_MONTH||'-'||V_YEAR,'','', sysdate,'');

SELECT COUNT(*) INTO v_total FROM FINANCIAL_UNUTILIZED_BANKING WHERE ST_MONTH=V_MONTH AND ST_YEAR=V_YEAR;

      for bb in  ( select ROW_NUMBER() OVER (ORDER BY s."number") AS ROW_NO,s."number" as service_number,s.m_company_name,s.m_org_name,bb.remarks ,s.m_org_id,bb.curr_c1,bb.curr_c2,bb.curr_c3,bb.curr_c4,bb.curr_c5,
 bb.month month,bb.year year,pp.plant_class_type_code weg_group_code ,co.value_desc weg_group_desc,tr.rate tr_rate
 from t_banking_balance bb
 left join v_company_Service s on bb.m_Company_id=s.m_company_id
 left join m_powerplant pp on s.id=pp.m_service_id
 left join v_codes co on pp.plant_class_type_code=co.value_code and co.list_code='PLANT_CLASS_TYPE_CODE'
 left join m_tariff tr on tr.weg_group_code=pp.plant_class_type_code
 where bb.month=V_MONTH and bb.year=V_YEAR and (bb.curr_c1+ bb.curr_c2+ bb.curr_c3+ bb.curr_c4+bb.curr_c5)>0)


     loop 
        v_table.ID:=FINANCIAL_UNUTILIZED_BANKING_SEQ.NEXTVAL;
        v_table.SNO:=bb.ROW_NO;
        v_table.SERVICE_NUMBER:=bb.service_number;
        v_table.EDC_NO:=bb.m_org_id;
        v_table.EDC_NAME:=bb.m_org_name;
        v_table.C1:=trunc(bb.curr_c1);
        v_table.C2:=trunc(bb.curr_c2);
        v_table.C3:=trunc(bb.curr_c3);
        v_table.C4:=trunc(bb.curr_c4);
        v_table.C5:=trunc(bb.curr_c5);
        v_table.ST_MONTH:=V_MONTH;
        v_table.SERVICE_NAME:=bb.m_company_name;
        v_table.ST_YEAR:=V_YEAR;
        v_table.RATE:=bb.tr_rate;
        v_table.WEG_GROUP_CODE:=bb.weg_group_code;
        v_table.WEG_GROUP_DESC:=bb.weg_group_desc;
        v_table.ST_TOTAL := v_table.C1 + v_table.C2 + v_table.C3 + v_table.C4 + v_table.C5;
        v_table.UNUTILISED_ENERGY := ROUND(v_table.ST_TOTAL*0.75);
        v_table.AMOUNT:=ROUND(v_table.UNUTILISED_ENERGY*bb.tr_rate,2);

        IF v_total=0 THEN
        INSERT INTO FINANCIAL_UNUTILIZED_BANKING VALUES v_table;
        ELSE

       UPDATE FINANCIAL_UNUTILIZED_BANKING SET EDC_NO = bb.m_org_id,EDC_NAME = bb.m_org_name, SERVICE_NAME = bb.m_company_name, C1 = trunc(bb.curr_c1),C2 = trunc(bb.curr_c2),C3 = trunc(bb.curr_c3),C4 = trunc(bb.curr_c4),C5 = trunc(bb.curr_c5),
       RATE = bb.tr_rate,WEG_GROUP_CODE = bb.weg_group_code,WEG_GROUP_DESC = bb.weg_group_desc,ST_TOTAL=v_table.C1 + v_table.C2 + v_table.C3 + v_table.C4 + v_table.C5,  
       UNUTILISED_ENERGY = ROUND(v_table.ST_TOTAL*0.75),AMOUNT = ROUND(v_table.UNUTILISED_ENERGY*bb.tr_rate,2)
       WHERE SERVICE_NUMBER = bb.service_number AND ST_MONTH = V_MONTH AND ST_YEAR = V_YEAR;

        END IF;

        end loop;
         COMMIT;

        exception
    when others then
        v_exception_code := SQLCODE;
        v_exception_msg := SUBSTR(SQLERRM, 1, 200);
        o_result_code := 'FAILURE';
       END;
    COMMIT;

 RETURN 'SUCCESS';
        COMMIT;


END FINANCIAL_UNUTILIZED_BANKING_REPORT;

