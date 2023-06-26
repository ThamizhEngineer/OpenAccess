CREATE OR REPLACE FUNCTION OPENACCESS."TEMP_BANKING_SOLUTION_FUNC" 
(v_remarks in varchar2,
v_month IN VARCHAR2,
v_year in varchar2) RETURN VARCHAR2 AS

v_service_no varchar2(50);
v_comp_id varchar2(50);
v_bb_serv_id varchar2(50);
v_c1 varchar2(50);
v_c2 varchar2(50);
v_c3 varchar2(50);
v_c4 varchar2(50);
v_c5 varchar2(50);
v_curr_c1 varchar2(50);
v_curr_c2 varchar2(50);
v_curr_c3 varchar2(50);
v_curr_c4 varchar2(50);
v_curr_c5 varchar2(50);
v_new_c1 number;
v_new_c2 number;
v_new_c3 number;
v_new_c4 number;
v_new_c5 number;
v_created_by varchar2(100):='admin';
v_up_new_c1 varchar2(50);
v_up_new_c2 varchar2(50);
v_up_new_c3 varchar2(50);
v_up_new_c4 varchar2(50);
v_up_new_c5 varchar2(50);
v_total number;

BEGIN

    FOR bb in (select * from TEMP_BANKING_SOLUTION where REMARKS=v_remarks and MONTH=v_month AND YEAR=v_year)
    LOOP
    select "number",M_COMPANY_ID,BANKING_SERVICE_ID into v_service_no,v_comp_id,v_bb_serv_id from m_company_service where "number"=bb.SERVICE_NO;

    update TEMP_BANKING_SOLUTION set M_COMPANY_ID=v_comp_id,BANKING_SERVICE_ID=v_bb_serv_id where SERVICE_NO=v_service_no and MONTH=v_month AND YEAR=v_year;

    select C1,C2,C3,C4,C5 into v_c1,v_c2,v_c3,v_c4,v_c5 from TEMP_BANKING_SOLUTION where REMARKS=v_remarks and MONTH=v_month AND YEAR=v_year AND SERVICE_NO=v_service_no;

    select CURR_C1,CURR_C2,CURR_C3,CURR_C4,CURR_C5 into v_curr_c1,v_curr_c2,v_curr_c3,v_curr_c4,v_curr_c5 from T_BANKING_BALANCE where M_COMPANY_ID=v_comp_id 
            and BANKING_SERVICE_ID=v_bb_serv_id and MONTH=v_month AND YEAR=v_year;

            --------validation and updation for newc1 in TEMP_BANKING_SOLUTION starts here-------------------------
            if(v_curr_c1) > 0 then
            v_new_c1 := v_curr_c1-v_c1;
            update TEMP_BANKING_SOLUTION set NEW_C1=to_char(v_new_c1),C1_REMARKS='C1-Success',CREATED_DATE=sysdate,CREATED_BY=v_created_by where M_COMPANY_ID=v_comp_id and MONTH=v_month AND YEAR=v_year;
            elsif(v_curr_c1='0') or (v_curr_c1 is null) then
            v_new_c1 := v_curr_c1-v_c1;
            update TEMP_BANKING_SOLUTION set NEW_C1=v_new_c1,C1_REMARKS='curr_c1 is 0 or null',CREATED_DATE=sysdate,CREATED_BY=v_created_by where M_COMPANY_ID=v_comp_id and MONTH=v_month AND YEAR=v_year;
            end if;

            if(v_curr_c2) > 0 then
            v_new_c2 := v_curr_c2-v_c2;
            update TEMP_BANKING_SOLUTION set NEW_C2=to_char(v_new_c2),C2_REMARKS='C2-Success',CREATED_DATE=sysdate,CREATED_BY=v_created_by where M_COMPANY_ID=v_comp_id and MONTH=v_month AND YEAR=v_year;
            elsif(v_curr_c2 ='0')  or (v_curr_c2 is null) then
            v_new_c2 := v_curr_c2-v_c2;
            update TEMP_BANKING_SOLUTION set NEW_C2=v_new_c2,C2_REMARKS='curr_c2 is 0 or null',CREATED_DATE=sysdate,CREATED_BY=v_created_by where M_COMPANY_ID=v_comp_id and MONTH=v_month AND YEAR=v_year;
            end if;

            if(v_curr_c3) > 0 then
            v_new_c3 := v_curr_c3-v_c3;
            update TEMP_BANKING_SOLUTION set NEW_C3=to_char(v_new_c3),C3_REMARKS='C3-Success',CREATED_DATE=sysdate,CREATED_BY=v_created_by where M_COMPANY_ID=v_comp_id and MONTH=v_month AND YEAR=v_year;
            elsif(v_curr_c3 ='0') or (v_curr_c3 is null) then
            v_new_c3 := v_curr_c3-v_c3;
            update TEMP_BANKING_SOLUTION set NEW_C3=v_new_c3,C3_REMARKS='curr_c3 is 0 or null',CREATED_DATE=sysdate,CREATED_BY=v_created_by where M_COMPANY_ID=v_comp_id and MONTH=v_month AND YEAR=v_year;
            end if;

            if(v_curr_c4) > 0 then
            v_new_c4 := v_curr_c4-v_c4;
            update TEMP_BANKING_SOLUTION set NEW_C4=to_char(v_new_c4),C4_REMARKS='C4-Success',CREATED_DATE=sysdate,CREATED_BY=v_created_by where M_COMPANY_ID=v_comp_id and MONTH=v_month AND YEAR=v_year;
            elsif(v_curr_c4 ='0') or (v_curr_c4 is null) then
            v_new_c4 := v_curr_c4-v_c4;
            update TEMP_BANKING_SOLUTION set NEW_C4=v_new_c4,C4_REMARKS='curr_c4 is 0 or null',CREATED_DATE=sysdate,CREATED_BY=v_created_by where M_COMPANY_ID=v_comp_id and MONTH=v_month AND YEAR=v_year;
            end if;

            if(v_curr_c5) > 0 then
            v_new_c5 := v_curr_c5-v_c5;
            update TEMP_BANKING_SOLUTION set NEW_C5=to_char(v_new_c5),C5_REMARKS='C5-Success',CREATED_DATE=sysdate,CREATED_BY=v_created_by where M_COMPANY_ID=v_comp_id and MONTH=v_month AND YEAR=v_year;
            elsif(v_curr_c5 ='0') or (v_curr_c5 is null) then
            v_new_c5 := v_curr_c5-v_c5;
            update TEMP_BANKING_SOLUTION set NEW_C5=v_new_c5,C5_REMARKS='curr_c5 is 0 or null',CREATED_DATE=sysdate,CREATED_BY=v_created_by where M_COMPANY_ID=v_comp_id and MONTH=v_month AND YEAR=v_year;
            end if;
            --------validation and updation for newc1 in TEMP_BANKING_SOLUTION ends here-------------------------

            select NEW_C1,NEW_C2,NEW_C3,NEW_C4,NEW_C5 into v_up_new_c1,v_up_new_c2,v_up_new_c3,v_up_new_c4,v_up_new_c5 from TEMP_BANKING_SOLUTION where REMARKS=v_remarks and MONTH=v_month AND YEAR=v_year AND SERVICE_NO=v_service_no;
            -- dbms_output.put_line('  v_up_new_c1  - '||  v_up_new_c1 );

            ---updation in banking balance starts---------------------
            if(v_up_new_c1)>=0 then
            update T_BANKING_BALANCE set CURR_C1=v_up_new_c1 where M_COMPANY_ID=v_comp_id and BANKING_SERVICE_ID=v_bb_serv_id and MONTH=v_month AND YEAR=v_year;
            else
            update T_BANKING_BALANCE set CURR_C1=decode(sign(v_up_new_c1),-1,0,v_up_new_c1) where M_COMPANY_ID=v_comp_id and BANKING_SERVICE_ID=v_bb_serv_id and MONTH=v_month AND YEAR=v_year;
            end if;

            if(v_up_new_c2)>=0 then
            update T_BANKING_BALANCE set CURR_C2=v_up_new_c2 where M_COMPANY_ID=v_comp_id and BANKING_SERVICE_ID=v_bb_serv_id and MONTH=v_month AND YEAR=v_year;
            else
            update T_BANKING_BALANCE set CURR_C2=decode(sign(v_up_new_c2),-1,0,v_up_new_c2) where M_COMPANY_ID=v_comp_id and BANKING_SERVICE_ID=v_bb_serv_id and MONTH=v_month AND YEAR=v_year;
            end if;

            if(v_up_new_c3)>=0 then
            update T_BANKING_BALANCE set CURR_C3=v_up_new_c3 where M_COMPANY_ID=v_comp_id and BANKING_SERVICE_ID=v_bb_serv_id and MONTH=v_month AND YEAR=v_year;
            else
            update T_BANKING_BALANCE set CURR_C3=decode(sign(v_up_new_c3),-1,0,v_up_new_c3) where M_COMPANY_ID=v_comp_id and BANKING_SERVICE_ID=v_bb_serv_id and MONTH=v_month AND YEAR=v_year;
            end if;

            if(v_up_new_c4)>=0 then
            update T_BANKING_BALANCE set CURR_C4=v_up_new_c4 where M_COMPANY_ID=v_comp_id and BANKING_SERVICE_ID=v_bb_serv_id and MONTH=v_month AND YEAR=v_year;
            else
            update T_BANKING_BALANCE set CURR_C4=decode(sign(v_up_new_c4),-1,0,v_up_new_c4) where M_COMPANY_ID=v_comp_id and BANKING_SERVICE_ID=v_bb_serv_id and MONTH=v_month AND YEAR=v_year;
            end if;

            if(v_up_new_c5)>=0 then
            update T_BANKING_BALANCE set CURR_C5=v_up_new_c5 where M_COMPANY_ID=v_comp_id and BANKING_SERVICE_ID=v_bb_serv_id and MONTH=v_month AND YEAR=v_year;
            else
            update T_BANKING_BALANCE set CURR_C5=decode(sign(v_up_new_c5),-1,0,v_up_new_c5) where M_COMPANY_ID=v_comp_id and BANKING_SERVICE_ID=v_bb_serv_id and MONTH=v_month AND YEAR=v_year;
            end if;

            v_total:=(v_up_new_c1+v_up_new_c2+v_up_new_c3+v_up_new_c4+v_up_new_c5);
            -- dbms_output.put_line('  v_total  - '||  v_total );

            if (v_total)>=0 then
            update T_BANKING_BALANCE set REMARKS='Alloted units 0 so remaining excess banking in current month'||to_char(decode(sign(v_new_c1),1,0,v_new_c1)+decode(sign(v_new_c2),1,0,v_new_c2)+decode(sign(v_new_c3),1,0,v_new_c3)+decode(sign(v_new_c4),1,0,v_new_c4)+decode(sign(v_new_c5),1,0,v_new_c5)) 
            where M_COMPANY_ID=v_comp_id and BANKING_SERVICE_ID=v_bb_serv_id and MONTH=v_month AND YEAR=v_year;
            else 
            update T_BANKING_BALANCE set REMARKS='added_negative_charges'||to_char(decode(sign(v_new_c1),1,0,v_new_c1)+decode(sign(v_new_c2),1,0,v_new_c2)+decode(sign(v_new_c3),1,0,v_new_c3)+decode(sign(v_new_c4),1,0,v_new_c4)+decode(sign(v_new_c5),1,0,v_new_c5))
            where M_COMPANY_ID=v_comp_id and BANKING_SERVICE_ID=v_bb_serv_id and MONTH=v_month AND YEAR=v_year;
            end if;
            ---updation in banking balance ends here---------------------

    END LOOP;
    commit;

    RETURN 'success';
END TEMP_BANKING_SOLUTION_FUNC;

