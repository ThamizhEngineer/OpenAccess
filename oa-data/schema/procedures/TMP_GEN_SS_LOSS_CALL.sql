CREATE OR REPLACE PROCEDURE OPENACCESS."TMP_GEN_SS_LOSS_CALL" is
v_result VARCHAR2(100);
O_RESULT_CODE VARCHAR2(200);
O_RESULT_DESC VARCHAR2(200);
BEGIN 
    for ss_loss in (select * from tmp_gen_ss_loss)

  loop
   GENERATION_STATEMENT.CREATE_WITH_SS_LOSS(ss_loss.M_SUBSTATION_ID,ss_loss.M_SERVICE_NUMBER,ss_loss.M_ORG_ID , ss_loss.MONTH, ss_loss.YEAR,O_RESULT_CODE, O_RESULT_DESC );
  end loop;
END tmp_gen_ss_loss_call;

