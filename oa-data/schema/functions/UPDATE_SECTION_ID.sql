CREATE OR REPLACE FUNCTION OPENACCESS."UPDATE_SECTION_ID" 
RETURN VARCHAR2 AS 
v_cursor sys_refcursor ;
v_cursor2 sys_refcursor ;
v_section_name varchar2(100);
v_section_id varchar2(100);
v_tmp_m_section TMP_M_SECTION%ROWTYPE; 

BEGIN
--1st
    OPEN v_cursor for SELECT distinct section_name from tmp_m_section;
    LOOP
    FETCH v_cursor INTO v_section_name;
    EXIT WHEN v_cursor%NOTFOUND;
             -- dbms_output.put_line(v_section_name);

        select id into v_section_id from m_section WHERE SECTION_NAME=v_section_name;
         update tmp_m_section set section_id=v_section_id where section_name =v_section_name;

    END LOOP;
--2nd    
    OPEN v_cursor2 for SELECT * from tmp_m_section;
    LOOP
    FETCH v_cursor2 INTO v_tmp_m_section;
    EXIT WHEN v_cursor2%NOTFOUND;
    update m_company_service set M_SECTION_ID=v_tmp_m_section.SECTION_ID where "number"=v_tmp_m_section.SERVICE_NUMBER;
    END LOOP;

  RETURN 'success';
END UPDATE_SECTION_ID;

