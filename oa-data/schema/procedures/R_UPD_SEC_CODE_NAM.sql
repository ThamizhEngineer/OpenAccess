CREATE OR REPLACE PROCEDURE OPENACCESS."R_UPD_SEC_CODE_NAM" AS 
BEGIN
  FOR loop_upd IN (SELECT gen_no,section FROM r_to_upd_sec where substr(gen_no,6,3)='432'  )
	LOOP

-- update m_company_service set m_section_name=upper( loop_upd.section_name) 
--where m_section_name is null and m_section_id=loop_upd.id;

update m_company_Service set m_section_name=upper(loop_upd.section) where m_section_name is null and "number"=loop_upd.gen_no;
 		END LOOP;
END R_UPD_SEC_CODE_NAM;

