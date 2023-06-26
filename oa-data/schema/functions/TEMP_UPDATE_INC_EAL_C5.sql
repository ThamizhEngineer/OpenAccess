CREATE OR REPLACE FUNCTION OPENACCESS."TEMP_UPDATE_INC_EAL_C5" RETURN VARCHAR2 AS
v_result varchar(300):='SUCCESS';
v_reason VARCHAR2(300);
BEGIN
    FOR line IN (SELECT nvl(incr_ea1_src_id,INCR_EA1_remarks) es_id FROM t_EXS_banking_BALANCE WHERE
                    READING_YEAR='2020' AND READING_MONTH = '06')
            LOOP
            excess_units_source.update_excess_from_ea(line.es_id, V_RESULT , V_REASON);
            END LOOP;
            COMMIT;
RETURN 'success';
END TEMP_UPDATE_INC_EAL_C5;