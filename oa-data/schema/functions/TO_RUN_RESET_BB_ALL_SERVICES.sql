CREATE OR REPLACE FUNCTION OPENACCESS."TO_RUN_RESET_BB_ALL_SERVICES" RETURN VARCHAR2 AS

v_number VARCHAR2(50);
o_result_code VARCHAR2(200);
o_result_desc VARCHAR2(200);

BEGIN

           -- for bb in (select m_company_id from m_company_service where "number" in 
           --                 (select gen_num from to_update_list))
--            for bb in (select M_COMPANY_ID from temp_banking_solution where month='09' and year='2019' and remarks='BANKING_NOT_IN_OCT')
            for bb in (select "number" service_number from v_company_service where comp_ser_type_code = '03') --only generators
                LOOP
                 --select ms."number" into v_number from M_COMPANY_SERVICE ms where ms.M_COMPANY_ID=bb.M_COMPANY_ID;

                 BANKING_BALANCE.RESET_FOR_YEAREND(bb.service_number,'04','2020',o_result_code,o_result_desc);
                -- -- dbms_output.put_line(bb.service_number||'-'||o_result_code||'-'||o_result_desc);
                insert into TEMP_RESULTS_PLS_FLUSH values(bb.service_number,o_result_code,o_result_desc);
                END LOOP;

                RETURN 'SUCCESS';

        commit;

         END TO_RUN_RESET_BB_ALL_SERVICES;

