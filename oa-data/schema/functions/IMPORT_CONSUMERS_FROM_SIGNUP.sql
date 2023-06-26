CREATE OR REPLACE FUNCTION OPENACCESS."IMPORT_CONSUMERS_FROM_SIGNUP" (
    v_remarks IN VARCHAR2 )
  RETURN VARCHAR2
AS
  v_is_complete      VARCHAR2(50);
  v_incomplete_count NUMBER;
  v_status           VARCHAR2(50);
  v_reason           VARCHAR2(200);
  v_exception_code   NUMBER;
  v_exception_msg    VARCHAR2(64);
  v_result           VARCHAR(200):='SUCCESS';
BEGIN
  BEGIN
    IF(v_remarks IS NULL OR v_remarks = '') THEN
      v_reason   := 'Remarks is mandatory';
      V_RESULT   := 'FAILURE';
      GOTO THE_END;
    ELSE
      SELECT COUNT(id) INTO v_incomplete_count FROM m_SIGNUP WHERE NVL(REMARKS,'') = v_remarks AND is_complete  = 'N';
      IF(v_incomplete_count = 0)THEN
        v_reason           := 'No Records to import with the given remark';
        V_RESULT           := 'FAILURE';
        GOTO THE_END;
      ELSE
        FOR signup IN (SELECT id FROM m_SIGNUP WHERE NVL(REMARKS,'')= v_remarks )
        LOOP
          BEGIN
            v_result:=CONFIRM_SIGNUP(signup.id);
          EXCEPTION
          WHEN OTHERS THEN
            v_exception_code := SQLCODE;
            v_exception_msg  := SUBSTR(SQLERRM, 1, 100);
            v_result         := 'FAILURE' || ' - ' || v_exception_code || ' - ' || v_exception_msg;
            -- dbms_output.put_line('Isse in loop-'||v_result);
          END;
        END LOOP;
      END IF;
    END IF;
  EXCEPTION
  WHEN OTHERS THEN
    v_exception_code := SQLCODE;
    v_exception_msg  := SUBSTR(SQLERRM, 1, 64);
    v_result         := 'FAILURE';
    v_reason         := v_exception_code || ' - ' || v_exception_msg;
  END;
<<THE_END>>
IF V_RESULT = 'SUCCESS' THEN
  COMMIT;
ELSE
  v_result := v_result || ' - ' || v_reason;
END IF;
RETURN v_result;
END IMPORT_CONSUMERS_FROM_SIGNUP;

