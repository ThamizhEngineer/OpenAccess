CREATE OR REPLACE PROCEDURE OPENACCESS."R_UPDATE_BANK_BAL" AS 
BEGIN
FOR loop_upd IN (SELECT GEN_NUM,C1,C2,C3,C4,C5 FROM TO_UPDATE_LIST)
	LOOP
      update t_banking_balance set c1=LOOP_UPD.C1,c2=LOOP_UPD.C2,c3=LOOP_UPD.C3,c4=LOOP_UPD.C4,
      c5=LOOP_UPD.C5, REMARKS='BANKING-CORRECTED-MAIL-REV DT-1.2.20',
      curr_c1=LOOP_UPD.C1,curr_c2=LOOP_UPD.C2,curr_c3=LOOP_UPD.C3,curr_c4=LOOP_UPD.C4,curr_c5=LOOP_UPD.C5
      where 
      M_COMPANY_ID in
      (select M_COMPANY_ID from m_company_service where "number" =loop_upd.gen_num )
      and t_banking_balance.MONTH='01' and t_banking_balance.YEAR='2020';
 		END LOOP;
END R_UPDATE_BANK_BAL;

