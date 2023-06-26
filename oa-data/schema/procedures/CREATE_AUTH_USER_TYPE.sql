CREATE OR REPLACE PROCEDURE OPENACCESS."CREATE_AUTH_USER_TYPE" (user_type_in_table VARCHAR2) IS
v_id  VARCHAR2(50);
v_user_type_code  VARCHAR2(50);
v_rowcount NUMBER(28);

   TYPE cur_cur IS REF CURSOR;
   v_cur_cur cur_cur;
sql_txt VARCHAR2(299);
BEGIN

	FOR loop_user_type IN (SELECT id, user_type_code FROM AUTH_FEATURE)
	LOOP
		IF(loop_user_type.user_type_code IS NULL or loop_user_type.user_type_code=''OR loop_user_type.user_type_code='(null)')THEN
			CONTINUE;
		END IF;
		sql_txt:=
		'select regexp_substr('''||loop_user_type.user_type_code||''','''||'[^,]+'||''', 1, level) user_type_code from dual'
		||' connect by regexp_substr('''||loop_user_type.user_type_code||''','''||'[^,]+'||''', 1, level) is not NULL';

		OPEN v_cur_cur FOR sql_txt;
			LOOP
				EXIT WHEN v_cur_cur%NOTFOUND;
				v_rowcount := v_cur_cur%ROWCOUNT;
				FETCH v_cur_cur INTO v_user_type_code;

		INSERT INTO AUTH_USER_TYPE_MAP (AUTH_USER_TYPE_CODE, AUTH_FEATURE_ID, ID) VALUES(v_user_type_code, loop_user_type.id,AUTH_USER_TYPE_MAP_SEQ.NEXTVAL );

			END LOOP;
		CLOSE v_cur_cur;
	 END LOOP loop_user_type;

END CREATE_AUTH_USER_TYPE;