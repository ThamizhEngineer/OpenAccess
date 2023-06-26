CREATE OR REPLACE FUNCTION OPENACCESS."TMP_SECTION_UPDATE" 
RETURN VARCHAR2 AS 
v_cursor sys_refcursor ;
v_section_name varchar2(100);
v_tmp_section TMP_M_SECTION%ROWTYPE;
v_seq varchar2(100);
BEGIN
OPEN v_cursor for SELECT distinct section_name from tmp_m_section;
    LOOP
    FETCH v_cursor INTO v_section_name;
    EXIT WHEN v_cursor%NOTFOUND;
    SELECT * into v_tmp_section from tmp_m_section where section_name=v_section_name and rownum=1;
    v_seq:=SECTION_SEQ.NEXTVAL;
         -- dbms_output.put_line(v_section_name);
         -- dbms_output.put_line(v_tmp_section.M_ORG_ID);
             INSERT INTO m_section (ID, SECTION_NAME, M_ORG_ID, REMARKS, CREATED_BY, CREATED_DATE, SECTION_CODE) 
             VALUES (v_seq,v_section_name,v_tmp_section.M_ORG_ID, 'TMP_SECTION_UPDATE','TMP_SECTION_UPDATE' ,sysdate,v_tmp_section.M_ORG_ID||v_seq);
 END LOOP;
  RETURN NULL;
END TMP_SECTION_UPDATE;

