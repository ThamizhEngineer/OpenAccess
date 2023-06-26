CREATE OR REPLACE FUNCTION OPENACCESS.refresh_unallocated_units RETURN VARCHAR2 AS
v_result varchar(300):='SUCCESS';
v_reason VARCHAR2(300);
BEGIN
    FOR line IN (SELECT nvl(incr_ea1_src_id,INCR_EA1_remarks) es_id FROM t_EXS_banking_BALANCE WHERE
                    READING_YEAR='2020' AND READING_MONTH = '06')
            LOOP
            if (line.es_id is not null) then
                excess_units_source.update_excess_from_ea(line.es_id, V_RESULT , V_REASON);
            end if;
            END LOOP;
            COMMIT;
RETURN 'success';
END refresh_unallocated_units;