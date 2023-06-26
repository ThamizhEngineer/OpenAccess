CREATE OR REPLACE FUNCTION OPENACCESS."TO_RUN_OPENIG_BALANCE" RETURN VARCHAR2 AS

v_number VARCHAR2(50);
o_result_code VARCHAR2(200);
o_result_desc VARCHAR2(200);

BEGIN

            for bb in (select M_COMPANY_ID from T_BANKING_BALANCE where month='04' and year='2020')
--            for bb in (select M_COMPANY_ID from temp_banking_solution where month='09' and year='2019' and remarks='BANKING_NOT_IN_OCT')
                LOOP
                 select ms."number" into v_number from M_COMPANY_SERVICE ms where ms.M_COMPANY_ID=bb.M_COMPANY_ID;

                BANKING_BALANCE.open_balance(v_number,'05','2020',o_result_code,o_result_desc);
                END LOOP;

                RETURN 'SUCCESS';

        commit;

         END TO_RUN_OPENIG_BALANCE;

