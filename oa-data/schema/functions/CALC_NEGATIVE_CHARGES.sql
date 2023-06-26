CREATE OR REPLACE FUNCTION OPENACCESS."CALC_NEGATIVE_CHARGES" (
    v_gs_id IN VARCHAR2 )
  RETURN VARCHAR2
  
  AS
    v_result varchar2(300);
 	v_reason VARCHAR2(300);
    v_total_exp_gen varchar2(300);
 	v_total_imp_gen varchar2(300);
	v_diff_total_gen varchar(300);
	v_c1_units varchar(300);
	v_c2_units varchar(300);
	v_c3_units varchar(300);
	v_c4_units varchar(300);
	v_c5_units varchar(300);
  v_c5_units_tax varchar(300);
  v_log_result varchar(300):='SUCCESS';
  v_exception_code VARCHAR2(150);
  v_exception_msg  VARCHAR2(150);
  v_slot_code VARCHAR(300);

  v_tax NUMBER;
BEGIN	
  BEGIN 
	SELECT gs.TOTAL_EXPORT_GEN INTO v_total_exp_gen FROM T_GEN_STMT gs WHERE gs.ID= v_gs_id ;
 	SELECT gs.TOTAL_IMPORT_GEN INTO v_total_imp_gen FROM T_GEN_STMT gs WHERE gs.ID= v_gs_id ;
        -- dbms_output.put_line(' v_total_exp_gen - '|| v_total_exp_gen);
        -- dbms_output.put_line(' v_total_imp_gen - '|| v_total_imp_gen);

--	if(TO_NUMBER(v_total_exp_gen) < TO_NUMBER(v_total_imp_gen))THEN --strat
        v_diff_total_gen := TO_NUMBER(v_total_exp_gen) - TO_NUMBER(v_total_imp_gen);
        -- dbms_output.put_line(' v_diff_total_gen - '|| v_diff_total_gen);
        FOR genSlot IN (SELECT gs.SLOT_CODE,gs.IMP_UNITS,gs.EXP_UNITS FROM T_GEN_STMT_SLOT gs WHERE gs.T_GEN_STMT_ID=v_gs_id)
        LOOP
        BEGIN
            v_slot_code :=genSlot.SLOT_CODE ;
            ----------------------------------------------------------
            if(genSlot.SLOT_CODE = 'C1') THEN --1
  	    -- dbms_output.put_line(' genSlot.EXP_UNITS - c1===='|| genSlot.EXP_UNITS);
          -- dbms_output.put_line(' genSlot.IMP_UNITS- c1===='|| genSlot.IMP_UNITS);
                if(TO_NUMBER(genSlot.EXP_UNITS) < TO_NUMBER(genSlot.IMP_UNITS))THEN --2

                v_c1_units :=((TO_NUMBER(genSlot.EXP_UNITS) - TO_NUMBER(genSlot.IMP_UNITS))*7.62);
                v_tax :=TO_NUMBER(v_c1_units)*0.05;
                v_c1_units:=TO_NUMBER(v_c1_units)+v_tax;
              -- dbms_output.put_line(' v_c1_units - '|| v_c1_units);
                ELSE
                v_c1_units :=0;
                END IF;	--2
            END IF;--1

            ----------------------------------------------------------
            if(genSlot.SLOT_CODE = 'C2') THEN
            if(TO_NUMBER(genSlot.EXP_UNITS) < TO_NUMBER(genSlot.IMP_UNITS))THEN
	        -- dbms_output.put_line(' genSlot.EXP_UNITS - c2===='|| genSlot.EXP_UNITS);
          -- dbms_output.put_line(' genSlot.IMP_UNITS- c2===='|| genSlot.IMP_UNITS);
            v_c2_units :=((TO_NUMBER(genSlot.EXP_UNITS) - TO_NUMBER(genSlot.IMP_UNITS))*7.62);
            v_tax :=TO_NUMBER(v_c2_units)*0.05;
            v_c2_units:=TO_NUMBER(v_c2_units)+v_tax;
            -- dbms_output.put_line(' v_c2_units - '|| v_c2_units);
            ELSE
            v_c2_units :=0;
            END IF;
            END IF;

             ----------------------------------------------------------

            if(genSlot.SLOT_CODE = 'C3') THEN
            if(TO_NUMBER(genSlot.EXP_UNITS) < TO_NUMBER(genSlot.IMP_UNITS))THEN
            -- dbms_output.put_line(' genSlot.EXP_UNITS - c3===='|| genSlot.EXP_UNITS);
            -- dbms_output.put_line(' genSlot.IMP_UNITS- c3===='|| genSlot.IMP_UNITS);
            v_c3_units :=((TO_NUMBER(genSlot.EXP_UNITS) - TO_NUMBER(genSlot.IMP_UNITS))*6.35);
            v_tax :=TO_NUMBER(v_c3_units)*0.05;
            v_c3_units:=TO_NUMBER(v_c3_units)+v_tax;
           --dbms_output.put_line(' v_c3_units - '|| v_c3_units);
                ELSE
            v_c3_units :=0;
          -- dbms_output.put_line(' v_c3_units - '|| v_c3_units);
            END IF;
            END IF;
              ----------------------------------------------------------

            if(genSlot.SLOT_CODE = 'C4') THEN
            if(TO_NUMBER(genSlot.EXP_UNITS) < TO_NUMBER(genSlot.IMP_UNITS))THEN

            v_c4_units :=((TO_NUMBER(genSlot.EXP_UNITS) - TO_NUMBER(genSlot.IMP_UNITS))*6.35);
            v_tax :=TO_NUMBER(v_c4_units)*0.05;
            v_c4_units:=TO_NUMBER(v_c4_units)+v_tax;
--          -- dbms_output.put_line(' v_c4_units - '|| v_c4_units);
            ELSE
            v_c4_units :=0;
            END IF;
            END IF;
          ----------------------------------------------------------

            if(genSlot.SLOT_CODE = 'C5') THEN
            if(TO_NUMBER(genSlot.EXP_UNITS) < TO_NUMBER(genSlot.IMP_UNITS))THEN
            -- dbms_output.put_line(' genSlot.EXP_UNITS - c5===='|| genSlot.EXP_UNITS);
            -- dbms_output.put_line(' genSlot.IMP_UNITS- c5===='|| genSlot.IMP_UNITS);
            v_c5_units :=((TO_NUMBER(genSlot.EXP_UNITS) - TO_NUMBER(genSlot.IMP_UNITS))*6.0325);
           dbms_output.put_line(' v_c5_units only- '|| v_c5_units);
             v_c5_units_tax :=(((TO_NUMBER(genSlot.EXP_UNITS) - TO_NUMBER(genSlot.IMP_UNITS))*0.3175));
          --dbms_output.put_line(' v_c5_units_tax - '|| v_c5_units_tax);
            v_c5_units := (TO_NUMBER(v_c5_units) + TO_NUMBER(v_c5_units_tax));
         -- dbms_output.put_line(' v_c5_units - '|| v_c5_units);
            ELSE
            v_c5_units :=0;
            END IF;
            END IF;
          ----------------------------------------------------------

	 EXCEPTION
  WHEN OTHERS THEN
    v_exception_code := SQLCODE;
    v_exception_msg  := SUBSTR(SQLERRM, 1, 100);
    v_result := 'FAILURE';
    v_reason := v_exception_code || ' - ' || v_exception_msg;
    v_log_result := log_activity('PROCEDURE','CALC_NEGATIVE_CHARGES','EH','Issue in CALC_NEGATIVE_CHARGES - '||v_reason,v_result,'admin', sysdate,v_gs_id);
  END;

    v_reason := 'SUCCESS'; -- resetting loop result

    END LOOP;
--    else
--      v_result := 0; 
--  -- dbms_output.put_line(' v_result'|| v_result);
--END IF; -- end
---- dbms_output.put_line(' v_c5_units - '|| v_c5_units);

v_result := (TO_NUMBER(v_c1_units ) + TO_NUMBER(v_c2_units )+ TO_NUMBER(v_c3_units )+ TO_NUMBER(v_c4_units )+ TO_NUMBER(v_c5_units ));
IF(v_result<0)THEN
v_result := (TO_NUMBER(v_c1_units ) + TO_NUMBER(v_c2_units )+ TO_NUMBER(v_c3_units )+ TO_NUMBER(v_c4_units )+ TO_NUMBER(v_c5_units ))*(-1);
---- dbms_output.put_line(' v_result - '|| v_result);
ELSE
v_result := (TO_NUMBER(v_c1_units ) + TO_NUMBER(v_c2_units )+ TO_NUMBER(v_c3_units )+ TO_NUMBER(v_c4_units )+ TO_NUMBER(v_c5_units ));
END IF;
---- dbms_output.put_line(' v_result - '|| v_result);

  EXCEPTION
  WHEN OTHERS THEN
    v_exception_code := SQLCODE;
    v_exception_msg  := SUBSTR(SQLERRM, 1, 100);
    v_result := 'FAILURE';
    v_reason := v_exception_code || ' - ' || v_exception_msg;
    v_log_result := log_activity('PROCEDURE','CALC_NEGATIVE_CHARGES','EH','Issue in CALC_NEGATIVE_CHARGES - '||v_reason,v_result,'admin', sysdate,v_gs_id);
  END;
--  -- dbms_output.put_line(' v_result - '|| v_result);
RETURN v_result;
END CALC_NEGATIVE_CHARGES;