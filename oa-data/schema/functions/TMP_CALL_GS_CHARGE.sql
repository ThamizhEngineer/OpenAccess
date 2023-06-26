CREATE OR REPLACE FUNCTION OPENACCESS."TMP_CALL_GS_CHARGE" RETURN VARCHAR2 AS 

v_gs_id VARCHAR2(50);
 v_gs number := 0;

BEGIN 
        for ss in (select distinct v_gs_id from Dummy1 where reading_month='01'and reading_year='2020')

        LOOP 
             v_gs:= calc_gs_charges(v_gs_id) ;

             UPDATE T_GEN_STMT SET REMARKS ='CMRI1' WHERE STMT_MONTH='01' AND STMT_YEAR='2020' AND  ID = V_GS_ID;

        END LOOP;

        RETURN 'SUCCESS';

        commit;

END TMP_CALL_GS_CHARGE;

