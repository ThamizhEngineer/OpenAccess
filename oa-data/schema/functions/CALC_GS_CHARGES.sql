CREATE OR REPLACE FUNCTION OPENACCESS."CALC_GS_CHARGES" (
    v_gs_id IN VARCHAR2
) RETURN VARCHAR2 AS

    v_flow_type_code        VARCHAR2(150);
    v_is_stb                VARCHAR2(150);
    v_evaluated             NUMBER;
    v_id                    NUMBER;
    v_no_of_days            NUMBER := 0;
    v_machine_capacity      NUMBER := 0;
    v_penalty_rate          NUMBER := 0;
    v_rkvah_inits           NUMBER := 0;
    v_total_charges         NUMBER := 0;
    v_grand_total_charges   NUMBER := 0;
    v_formula               VARCHAR2(150);
    v_charge_type           VARCHAR2(150);
    v_unit_charge           VARCHAR2(150);
    v_charge_code           VARCHAR2(150);
    v_charge                VARCHAR2(150) := '0';
    v_fuel_group             VARCHAR2(50);
    v_fuel_type_code        VARCHAR2(50);
    v_charge_percentage     NUMBER := 0;
    v_net_generation        VARCHAR2(150);
    v_result                VARCHAR(300) := 'SUCCESS';
    v_log_result            VARCHAR(300) := 'SUCCESS';
    v_exception_code        VARCHAR2(150);
    v_exception_msg         VARCHAR2(150);
    v_reason                VARCHAR2(300);
    v_cal                   NUMBER := 0;
    v_gen_iscaptive         VARCHAR2(50);
    v_gen_isstb_count       NUMBER;
    v_gen_sstype            VARCHAR2(50);
    v_is_rec                VARCHAR2(50);
    v_source_code           VARCHAR2(50);
    v_other_charges_count   NUMBER;
    v_service_id            VARCHAR2(50);
    v_month                 VARCHAR2(20);
    v_year                  VARCHAR2(20);
    v_c008total_charges     NUMBER;
BEGIN
    BEGIN
        v_log_result := log_activity('PROCEDURE', 'CALC_GS_CHARGES', 'START', 'Start - ', '', '', SYSDATE, '');

        SELECT gs.machine_capacity, ( gs.final_stmt_dt - gs.init_stmt_dt ) + 1 no_of_days, gs.penalty_rate, gs.rkvah_units, gs.net_generation, gs.disp_fuel_type_code, nvl(gs.is_stb, ''),
            gs.type_of_ss, gs.is_rec, gs.stmt_month, gs.stmt_year, gs.m_company_service_id, gs.mr_source_code, disp_fuel_type_group
        INTO
            v_machine_capacity, v_no_of_days, v_penalty_rate, v_rkvah_inits, v_net_generation, v_fuel_type_code, v_is_stb, v_gen_sstype, v_is_rec, v_month, v_year, v_service_id, v_source_code, v_fuel_group
        FROM t_gen_stmt gs WHERE id = v_gs_id;

        SELECT flow_type_code INTO v_flow_type_code FROM m_company_service WHERE id = v_service_id;

        IF ( v_flow_type_code = 'IS_CAPTIVE' ) THEN
            v_gen_iscaptive := 'Y';
        ELSE
            v_gen_iscaptive := 'N';
        END IF;

--    SELECT companygs.IS_CAPTIVE INTO v_gen_iscaptive FROM V_COMPANY_SERVICE companygs
--
--    LEFT JOIN T_GEN_STMT gengs ON companygs.M_COMPANY_ID = gengs.M_COMPANY_ID AND companygs.IS_CAPTIVE='Y' WHERE gengs.ID=v_gs_id;

        -- -- dbms_output.put_line('v_gen_iscaptive-' || v_gen_iscaptive);
        SELECT COUNT(compgs.is_captive) INTO v_gen_isstb_count FROM v_company_service compgs LEFT JOIN t_gen_stmt stbgs ON compgs.id = stbgs.m_company_service_id AND compgs.is_stb = 'Y'
        WHERE stbgs.id = v_gs_id;

        -- -- dbms_output.put_line('v_gen_isstb_count-' || v_gen_isstb_count);
        v_log_result := log_activity('PROCEDURE', 'CALC_GS_CHARGES', 'In Progress', 'v_gen_iscaptive - ' || v_gen_iscaptive, 'v_gen_isstb_count-'|| v_gen_isstb_count, '', SYSDATE, '');

        SELECT service.type_of_ss INTO v_gen_sstype FROM v_company_service service LEFT JOIN t_gen_stmt gengs3 ON service.id = gengs3.m_company_service_id WHERE gengs3.id = v_gs_id;

        -- -- dbms_output.put_line('v_gen_sstype-' || v_gen_sstype);
        v_log_result := log_activity('PROCEDURE', 'CALC_GS_CHARGES', 'In Progress', 'v_gen_sstype - ' || v_gen_sstype, '', '', SYSDATE, '');

        -- For gen other charges

        SELECT COUNT(*) INTO v_other_charges_count FROM t_gen_other_charges
        WHERE m_company_service_id = v_service_id AND month = v_month AND year = v_year AND charge_code = 'C008';

        IF ( v_gen_isstb_count ) > 0 THEN
            v_is_stb := 'Y';
        ELSE
            v_is_stb := 'N';
        END IF;

        IF ( v_gen_sstype = 'SECTION 10(1)SS' ) THEN
            v_gen_sstype := 'Y';
        ELSE
            v_gen_sstype := 'N';
        END IF;

        IF ( v_is_stb = 'Y' ) THEN
            v_is_stb := 'Y';
        ELSE
            v_is_stb := 'N';
        END IF;

        FOR charge IN (SELECT charge_code, charge_desc, charge_type_code, unit_charge, ff FROM v_charge where context = 'GENERATOR_STATEMENT') 
        LOOP
            BEGIN
                v_charge_code := charge.charge_code;

                if(v_fuel_group='FF') then
                    if(charge.ff='04') then 
                        -- TODO - logic to calculate charge for whole year and add to charges
                        continue;
                    ELSIF(charge.ff='N') then 
                        continue;
                    end if;
                end if;
--              v_log_result := log_activity('PROCEDURE','CALC_GS_CHARGES','v_charge_code','v_charge_code - ',v_charge_code,'', sysdate,'');
--              -- dbms_output.put_line('  v_charge_code  - '||  v_charge_code );
                IF ( v_charge_code IN ('C001','C002','C005','C006') OR v_is_rec = 'Y' ) THEN
                    v_charge_percentage := 100;
                ELSIF ( v_is_rec = 'N' AND v_fuel_type_code = 'WIND' ) THEN
                    v_charge_percentage := 50;
                ELSIF ( v_fuel_type_code = 'SOLAR' ) THEN
                    v_log_result := log_activity('PROCEDURE', 'CALC_GS_CHARGES', 'CHARGE_PERCENTAGE', 'FUEL_TYPE- '|| v_fuel_type_code|| '-IS_rEC-'|| v_is_rec, '', 'admin', SYSDATE, v_gs_id);

                    IF v_is_rec = 'N' THEN
                        v_charge_percentage := 50;
                    ELSIF v_is_rec = 'Y' THEN
                        v_charge_percentage := 100;
                    END IF;

--         elsif(v_is_rec = 'Y' and v_fuel_type_Code = '18') THEN
--
--            v_charge_percentage:= 100;

                ELSE
                    v_charge_percentage := 100;
                END IF;

                IF ( v_charge_code = 'C001' ) THEN
                    IF ( v_source_code = '02' OR v_source_code = '03' ) THEN
                        v_total_charges := ceil(to_number(calc_charges(v_charge_code, '', '', '')));
                        v_total_charges := ceil(to_number(300 + v_total_charges));
                        --   -- dbms_output.put_line('meter reading charges manual--');
                    ELSE
                        v_total_charges := ceil(to_number(calc_charges(v_charge_code, '', '', '')));
                        --      -- dbms_output.put_line('meter reading charges AMR--');
                    END IF;
                ELSIF ( v_charge_code = 'C002' ) THEN
                    IF ( v_gen_sstype = 'Y' OR v_fuel_type_code = 'SOLAR' ) THEN
                         ---- dbms_output.put_line('No o&M charges  for 10(1) ss');
                        CONTINUE; --  No o&M charges  for 10(1) ss
                    ELSE
                        v_total_charges := ceil(to_number(calc_charges(charge.charge_code, v_machine_capacity, v_no_of_days, '')));
                    END IF;
                ELSIF ( v_charge_code = 'C003' ) THEN
                    IF ( v_is_stb = 'Y' ) THEN
                        CONTINUE; --  No transmission charges for stb
                    ELSE
                        v_total_charges := ceil(to_number(calc_charges(v_charge_code, v_machine_capacity, v_no_of_days, '')));
                    END IF;
                ELSIF ( v_charge_code = 'C004' ) THEN
                    IF ( v_is_stb = 'Y' ) THEN
                        CONTINUE; --  No transmission charges for stb
                    ELSE
                        v_total_charges := ceil(to_number(calc_charges(v_charge_code, v_machine_capacity, v_no_of_days, '')));
                    END IF;
                ELSIF ( v_charge_code = 'C005' ) THEN
                    IF ( v_fuel_type_code = 'WIND' ) THEN
                        IF ( ( to_number(v_net_generation) * 0.1 ) >= v_rkvah_inits ) THEN
                            v_penalty_rate := 0.25;
                        ELSE
                            v_penalty_rate := 0.5;
                        END IF;
                    ELSIF ( v_fuel_type_code = 'SOLAR' ) THEN
                        v_penalty_rate := 0.155;
                    END IF;

                    v_total_charges := ceil(to_number(calc_charges(v_charge_code, v_penalty_rate, v_rkvah_inits, '')));

                    IF ( v_total_charges < 0 ) THEN
                        v_total_charges := 0;
                    ELSE
                        v_total_charges := v_total_charges;
                    END IF;

                ELSIF ( v_charge_code = 'C006' ) THEN
                    v_total_charges := ceil(to_number(calc_negative_charges(v_gs_id)));
--          -- dbms_output.put_line(' v_total_charges'|| v_total_charges);
                ELSIF ( v_charge_code = 'C007' ) THEN
                    IF ( v_is_stb = 'Y' ) THEN
                        CONTINUE; --  No transmission charges for stb
                    ELSE
                        v_total_charges := ceil(to_number(calc_charges(v_charge_code, v_no_of_days, '', '')));
                    END IF;

--    if v_other_charges_count=1 then
--        select to_number(TOTAL_CHARGES) into v_c008total_charges from t_gen_other_charges where M_COMPANY_SERVICE_ID=v_service_id and month=v_month and year=v_year and CHARGE_CODE='C008';
--        IF (v_charge_code = 'C008') then
--        v_total_charges:= v_c008total_charges;
--        END IF;  
--    end if;
                ELSIF ( v_charge_code = 'C008' ) THEN
--         v_log_result := log_activity('PROCEDURE','CALC_GS_CHARGES','v_charge_code','v_charge_code - ',v_charge_code,'', sysdate,'');
--          -- dbms_output.put_line('  v_charge_code  - '||  v_charge_code );
                    IF v_other_charges_count = 1 THEN
                        SELECT to_number(total_charges) INTO v_c008total_charges FROM t_gen_other_charges WHERE m_company_service_id = v_service_id AND month = v_month AND year = v_year AND charge_code = 'C008';
                        v_total_charges := v_c008total_charges;
--             v_log_result := log_activity('PROCEDURE','CALC_GS_CHARGES','v_total_charges','v_total_charges - ',v_total_charges,'', sysdate,'');
                    ELSE
                        v_total_charges := 0;
                    END IF;
--       ELSIF(v_charge_code='C009')  then
--          v_total_charges:= CEIL(to_number(CALC_CHARGES(v_charge_code,v_machine_capacity,'','')));
                ELSE
                    CONTINUE;
                END IF;

                IF v_charge_code != 'C008' THEN
                    v_total_charges := ceil(v_total_charges * v_charge_percentage / 100);
                END IF;

--         thermal charges update       
              if(v_fuel_group='FF') then
              IF ( v_charge_code = 'C003' ) THEN
                        v_total_charges := 0;
                         END IF;
              IF ( v_charge_code = 'C004' ) THEN
                        v_total_charges := 0;
                         END IF;            
                 IF ( v_charge_code = 'C007' ) THEN
                        v_total_charges := 0;
                         END IF;
                         IF ( v_charge_code = 'C008' ) THEN
                        v_total_charges := 0;
                         END IF;
                   end if;  
    --  -- dbms_output.put_line('  v_total_charges  - '||  v_total_charges );

                INSERT INTO t_gen_stmt_charge ( id, t_gen_stmt_id, charge_code, charge_desc, charge_type_code, unit_charge, total_charges ) VALUES (
                    t_gen_stmt_charge_seq.NEXTVAL, v_gs_id, charge.charge_code, charge.charge_desc, charge.charge_type_code, charge.unit_charge,v_total_charges);

                v_grand_total_charges := ceil((v_grand_total_charges + nvl(v_total_charges, 0)));
--        -- dbms_output.put_line(' v_grand_total_charges - '|| v_grand_total_charges);
            EXCEPTION
                WHEN OTHERS THEN
                    v_exception_code := sqlcode;
                    v_exception_msg := substr(sqlerrm, 1, 100);
                    v_result := 'FAILURE';
                    v_reason := v_exception_code || ' - '|| v_exception_msg;
                    v_log_result := log_activity('PROCEDURE', 'CALC_GS_CHARGES', 'EH-LOOP', 'Error while processing charge ('|| v_charge_code|| ')- '|| v_reason, v_result, 'admin', SYSDATE, v_gs_id);

            END;

            v_reason := 'SUCCESS'; -- resetting loop result
        END LOOP;

        UPDATE t_gen_stmt
        SET
            total_charged_amount = v_grand_total_charges
        WHERE
            id = v_gs_id;

    EXCEPTION
        WHEN OTHERS THEN
            v_exception_code := sqlcode;
            v_exception_msg := substr(sqlerrm, 1, 100);
            v_result := 'FAILURE';
            v_reason := v_exception_code|| ' - '|| v_exception_msg;
            v_log_result := log_activity('PROCEDURE', 'CALC_GS_CHARGES', 'EH', 'Issue in CALC_GS_CHARGES - ' || v_reason, v_result, 'admin', SYSDATE, v_gs_id);

    END;

    RETURN v_result;
END calc_gs_charges;