CREATE OR REPLACE PROCEDURE OPENACCESS.R_UPDATE_METER_NO AS 
BEGIN
FOR loop_upd IN (SELECT oldmeterno,newmeterno FROM r_meter_change_from_amr )
	LOOP
--    update m_company_meter set meter_number=loop_upd.newmeterno , remarks=concat('MC-28-03-20',loop_upd.oldmeterno)
--where meter_number=loop_upd.oldmeterno;
 update m_company_meter set meter_number=loop_upd.newmeterno , remarks=concat('MYMC-05-04-22-old :',loop_upd.oldmeterno)
where meter_number=loop_upd.oldmeterno;
--update m_company_meter set meter_number=loop_upd.oldmeterno 
--where meter_number=loop_upd.newmeterno and remarks is NULL;
--change the date and save and run

 		END LOOP;
END R_UPDATE_METER_NO;