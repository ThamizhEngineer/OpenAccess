CREATE OR REPLACE PROCEDURE OPENACCESS."R_UPDATE_MNS_STATUS" AS 
--procedure created to update status in m_powerplant table based on mail sent by AEE/IT/OA . 
--the previous status has been stored as backup in r_to_upd_mns_list_05
BEGIN
declare
v_old_st varchar2(5);
begin
FOR loop_upd IN (SELECT gen_no FROM r_to_upd_mns_list_05  )
	LOOP
select status into v_old_st from m_powerplant p, m_company_service c 
where m_service_id = c.id and "number" =loop_upd.gen_no;
 update r_to_upd_mns_list_05 set OLD_STATUS=v_old_st where gen_no=loop_upd.gen_no;

--rollbACK DONE BASED ON REQUEST by AEE/IT/OA
--UPDATE M_Powerplant SET status=loop_upd.old_status where 
--m_service_id in (select id from m_company_service where "number" =loop_upd.gen_no);

 --after updation this cmd run in editor window
-- UPDATE M_Powerplant SET status='MNS' where 
--m_service_id in (select id from m_company_service where "number" in (select gen_no from r_to_upd_mns_list_05));

 		END LOOP;
end;
END R_UPDATE_MNS_STATUS;

