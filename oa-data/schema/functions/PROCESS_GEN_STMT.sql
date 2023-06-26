CREATE OR REPLACE FUNCTION OPENACCESS."PROCESS_GEN_STMT" 
(
  v_month IN VARCHAR2,
  v_year in varchar2
) RETURN VARCHAR2 AS

v_batch_based boolean:=false;
v_duration_based boolean:=false;
v_process_id  VARCHAR2(50);
v_gen_count  NUMBER:=0;
v_mrh T_METER_READING_HDR%ROWTYPE;
v_mr_c1 T_METER_READING_SLOT%ROWTYPE;
v_mr_c2 T_METER_READING_SLOT%ROWTYPE;
v_mr_c3 T_METER_READING_SLOT%ROWTYPE;
v_mr_c4 T_METER_READING_SLOT%ROWTYPE;
v_mr_c5 T_METER_READING_SLOT%ROWTYPE;
v_gs T_GEN_STMT%ROWTYPE;
v_gs_c1 T_GEN_STMT_SLOT%ROWTYPE;
v_gs_c2 T_GEN_STMT_SLOT%ROWTYPE;
v_gs_c3 T_GEN_STMT_SLOT%ROWTYPE;
v_gs_c4 T_GEN_STMT_SLOT%ROWTYPE;
v_gs_c5 T_GEN_STMT_SLOT%ROWTYPE;
v_no_records BOOLEAN:=TRUE;
v_created_Date DATE := SYSDATE;
v_created_By  varchar2(50):= 'admin';
v_status varchar2(50);
v_reason varchar2(200);
v_exception_code  NUMBER;
v_exception_msg  VARCHAR2(200);
v_result varchar(300):='SUCCESS';
v_log_result varchar(300):='SUCCESS';
v_imported BOOLEAN;

BEGIN

        BEGIN
    v_log_result := log_activity('PROCEDURE','PROCESS_GEN_STMT','INIT','Start '||v_process_id,null,v_created_By, sysdate);

                FOR meter IN (SELECT mh.M_COMPANY_METER_ID, READING_MONTH, READING_YEAR,
                                                nvl(bg.C1,0) bc1, nvl(bg.C2,0) bc2, nvl(bg.C3,0) bc3,nvl(bg.C4,0) bc4,nvl(bg.C5,0) bc5,
                                                cs.M_ORG_ID, c.ID M_COMPANY_ID,cm.M_COMPANY_SERVICE_ID, c.NAME DISP_COMPANY_NAME, cs."number" DISP_SERVICE_NUMBER, cs.VOLTAGE_CODE INJECTING_VOLTAGE_CODE, o.NAME DISP_ORG_NAME,
            p.fuel_type_Code
                                                FROM T_METER_READING_HDR mh
                                                INNER JOIN M_COMPANY_METER cm ON mh.M_COMPANY_METER_ID = cm.ID
                                                INNER JOIN M_COMPANY_SERVICE cs ON cm.M_COMPANY_SERVICE_ID = cs.id
            inner join M_POWERPLANT  p on cm.M_COMPANY_SERVICE_ID = p.m_service_id
                                                INNER JOIN M_COMPANY c ON cs.M_COMPANY_ID = c.id
                                                INNER JOIN M_ORG o ON cs.M_ORG_ID = o.id
                        LEFT JOIN T_BANKING_BALANCE bg ON bg.BANKING_SERVICE_ID = cs.BANKING_SERVICE_ID
                                                WHERE mh.M_GEN_STMT_ID IS NULL AND mh.GS_BATCH_ID IS NULL
                                                AND READING_MONTH = v_month AND READING_YEAR = v_year
                                                 )
                LOOP
                        BEGIN
        v_log_result := log_activity('PROCEDURE','PROCESS_GEN_STMT','IN-LOOP-START','Start '||meter.DISP_SERVICE_NUMBER,null,v_created_By, sysdate);
                                -- as there is meter-readings to process, we have to set the flag accordingly and start the generation process
                                if(v_no_records) THEN
                                        v_no_records := FALSE;
                                        v_process_id := T_PROCESS_GS_SEQ.nextval;
                                        INSERT INTO T_PROCESS_GS (ID,SYS_DT,STATUS,START_DT,END_DT,REMARKS)
                                                VALUES (v_process_id,v_created_Date,'PROCESSING',v_created_Date,NULL,NULL) ;
                                        --v_log_result := log_activity('PROCEDURE','PROCESS_GEN_STMT','STARTED','Start - '||v_process_id,null,v_created_By, sysdate);

                                END IF;

                                -- -- dbms_output.put_line('meter.M_COMPANY_METER_ID - '||meter.M_COMPANY_METER_ID);
                                -- -- dbms_output.put_line('meter.M_COMPANY_SERVICE_ID - '||meter.M_COMPANY_SERVICE_ID);
                                -- -- dbms_output.put_line('meter.READING_Year - '||meter.READING_Year);
                                -- -- dbms_output.put_line('meter.READING_MONTH - '||meter.READING_MONTH);

                                -- to find generator capacity
                                SELECT count(*) INTO v_gen_count FROM M_GENERATOR g JOIN M_POWERPLANT p ON g.M_POWERPLANT_ID = p.ID WHERE p.M_SERVICE_ID = meter.M_COMPANY_SERVICE_ID ;

                                if(v_gen_count =0) THEN
                                        -- setup issue - generator not configured properly for this service
                                        v_log_result := log_activity('PROCEDURE','PROCESS_GEN_STMT','ISSUE','Setup Issue - '||v_process_id||' - No generator configured for service-id-->'||meter.M_COMPANY_SERVICE_ID,null,v_created_By, sysdate,v_process_id);

          v_result := 'FAILURE - No generator configured';
          GOTO LOOP_END;
                                ELSE
          -- TODO - Sum() should be revisited
                                        SELECT sum(nvl(CAPACITY,0))  INTO v_gs.MACHINE_CAPACITY FROM M_GENERATOR g JOIN M_POWERPLANT p ON g.M_POWERPLANT_ID = p.ID WHERE p.M_SERVICE_ID = meter.M_COMPANY_SERVICE_ID ;
                                END IF;


                                -- -- dbms_output.put_line('v_gs.MACHINE_CAPACITY - '||v_gs.MACHINE_CAPACITY);

                                SELECT * INTO v_mrh FROM T_METER_READING_HDR WHERE  M_COMPANY_METER_ID = meter.M_COMPANY_METER_ID AND READING_MONTH = meter.READING_MONTH AND READING_Year = meter.READING_Year;


                                -- -- dbms_output.put_line('v_mrh.id - '||v_mrh.id);

                                SELECT * INTO v_mr_c1 FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mrh.id AND SLOT_CODE = 'C1';
                                SELECT * INTO v_mr_c2 FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mrh.id AND SLOT_CODE = 'C2';
                                SELECT * INTO v_mr_c3 FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mrh.id AND SLOT_CODE = 'C3';
                                SELECT * INTO v_mr_c4 FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mrh.id AND SLOT_CODE = 'C4';
                                SELECT * INTO v_mr_c5 FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mrh.id AND SLOT_CODE = 'C5';


                                -- -- dbms_output.put_line('all slots- '||v_mrh.id);
                                -- intialise PK, FKs for GenStmt and GenSlots
                                v_gs.id := T_GEN_STMT_SEQ.nextval;
                                v_gs_c1.id := T_GEN_STMT_SLOT_SEQ.nextval;v_gs_c1.T_GEN_STMT_ID := v_gs.id;
                                v_gs_c2.id := T_GEN_STMT_SLOT_SEQ.nextval;v_gs_c2.T_GEN_STMT_ID := v_gs.id;
                                v_gs_c3.id := T_GEN_STMT_SLOT_SEQ.nextval;v_gs_c3.T_GEN_STMT_ID := v_gs.id;
                                v_gs_c4.id := T_GEN_STMT_SLOT_SEQ.nextval;v_gs_c4.T_GEN_STMT_ID := v_gs.id;
                                v_gs_c5.id := T_GEN_STMT_SLOT_SEQ.nextval;v_gs_c5.T_GEN_STMT_ID := v_gs.id;

                                -- set values in gen stmt
                                v_gs.STATUS_CODE := 'CREATED';
                                v_gs.M_COMPANY_METER_ID := v_mrh.M_COMPANY_METER_ID ;
                                v_gs.T_MR_IDS := v_mrh.M_COMPANY_METER_ID ;
                                v_gs.REF_NUMBER := v_process_id;
                                v_gs.mf := v_mrh.mf;
                                v_gs.STMT_GEN_DATE := v_created_Date ;
                                v_gs.STMT_MONTH :=  v_mrh.READING_MONTH;
                                v_gs.STMT_YEAR := v_mrh.READING_YEAR;
                                v_gs.INIT_STMT_DT := v_mrh.INIT_READING_DT;
                                v_gs.FINAL_STMT_DT := v_mrh.FINAL_READING_DT;
                                -- RKVAH_INIT, RKVAH_FINAL, KVAH_INIT, KVAH_FINAL are not relevant currently
                                v_gs.KVAH_DIFF := v_mrh.KVAH_DIFF;
                                v_gs.KVAH_UNITS := v_mrh.KVAH_UNITS;
                                v_gs.RKVAH_DIFF := v_mrh.RKVAH_DIFF;
                                v_gs.RKVAH_UNITS := v_mrh.RKVAH_UNITS;
                                v_gs.TOTAL_EXPORT_GEN := v_mr_c1.EXP_UNITS+v_mr_c2.EXP_UNITS+v_mr_c3.EXP_UNITS+v_mr_c4.EXP_UNITS+v_mr_c5.EXP_UNITS;
                                v_gs.TOTAL_IMPORT_GEN := v_mr_c1.IMP_UNITS+v_mr_c2.IMP_UNITS+v_mr_c3.IMP_UNITS+v_mr_c4.IMP_UNITS+v_mr_c5.IMP_UNITS;
                                v_gs.M_ORG_ID := meter.M_ORG_ID;
                                v_gs.M_COMPANY_ID := meter.M_COMPANY_ID;
                                v_gs.M_COMPANY_SERVICE_ID := meter.M_COMPANY_SERVICE_ID;
                                v_gs.DISP_COMPANY_NAME := meter.DISP_COMPANY_NAME;
                                v_gs.DISP_SERVICE_NUMBER := meter.DISP_SERVICE_NUMBER;
                                v_gs.INJECTING_VOLTAGE_CODE := meter.INJECTING_VOLTAGE_CODE;
                                v_gs.DISP_ORG_NAME := meter.DISP_ORG_NAME;
                                v_gs.net_generation := v_mr_c1.NET_UNITS+v_mr_c2.NET_UNITS+v_mr_c3.NET_UNITS+v_mr_c4.NET_UNITS+v_mr_c5.NET_UNITS;
                                v_gs.C1 := v_mr_c1.NET_UNITS;
                                v_gs.C2 := v_mr_c2.NET_UNITS;
                                v_gs.C3 := v_mr_c3.NET_UNITS;
                                v_gs.C4 := v_mr_c4.NET_UNITS;
                                v_gs.C5 := v_mr_c5.NET_UNITS;
                                v_gs.created_by := v_created_By;
                                v_gs.created_dt := v_created_date;
                                v_gs.TOTAL_CHARGED_AMOUNT := 0; --actual value calculated by calc_gs_charges()

                                if(to_number(v_gs.TOTAL_EXPORT_GEN)*0.01 >= to_number(v_gs.RKVAH_UNITS)) THEN
                                        v_gs.penalty_rate := '0.25';
                                ELSE
                                        v_gs.penalty_rate := '0.50';
                                END IF;


                                -- -- dbms_output.put_line('gen stmt - values set');

                                SELECT v_mr_c1.SLOT_CODE,v_mr_c1.IMP_INIT, v_mr_c1.IMP_FINAL, v_mr_c1.IMP_DIFF, v_mr_c1.IMP_UNITS, v_mr_c1.EXP_INIT, v_mr_c1.EXP_FINAL, v_mr_c1.EXP_DIFF, v_mr_c1.EXP_UNITS, meter.bc1, v_mr_c1.NET_UNITS
                                INTO  v_gs_c1.SLOT_CODE,v_gs_c1.IMP_INIT, v_gs_c1.IMP_FINAL, v_gs_c1.IMP_DIFF, v_gs_c1.IMP_UNITS, v_gs_c1.EXP_INIT, v_gs_c1.EXP_FINAL, v_gs_c1.EXP_DIFF, v_gs_c1.EXP_UNITS, v_gs_c1.BANKED_BALANCE, v_gs_c1.NET_UNITS
                                FROM dual;

                                SELECT  v_mr_c2.SLOT_CODE,v_mr_c2.IMP_INIT, v_mr_c2.IMP_FINAL, v_mr_c2.IMP_DIFF, v_mr_c2.IMP_UNITS, v_mr_c2.EXP_INIT, v_mr_c2.EXP_FINAL, v_mr_c2.EXP_DIFF, v_mr_c2.EXP_UNITS, meter.bc2, v_mr_c2.NET_UNITS
                                INTO  v_gs_c2.SLOT_CODE,v_gs_c2.IMP_INIT, v_gs_c2.IMP_FINAL, v_gs_c2.IMP_DIFF, v_gs_c2.IMP_UNITS, v_gs_c2.EXP_INIT, v_gs_c2.EXP_FINAL, v_gs_c2.EXP_DIFF, v_gs_c2.EXP_UNITS, v_gs_c2.BANKED_BALANCE, v_gs_c2.NET_UNITS
                                FROM dual;

                                SELECT  v_mr_c3.SLOT_CODE,v_mr_c3.IMP_INIT, v_mr_c3.IMP_FINAL, v_mr_c3.IMP_DIFF, v_mr_c3.IMP_UNITS, v_mr_c3.EXP_INIT, v_mr_c3.EXP_FINAL, v_mr_c3.EXP_DIFF, v_mr_c3.EXP_UNITS, meter.bc3, v_mr_c3.NET_UNITS
                                INTO  v_gs_c3.SLOT_CODE,v_gs_c3.IMP_INIT, v_gs_c3.IMP_FINAL, v_gs_c3.IMP_DIFF, v_gs_c3.IMP_UNITS, v_gs_c3.EXP_INIT, v_gs_c3.EXP_FINAL, v_gs_c3.EXP_DIFF, v_gs_c3.EXP_UNITS, v_gs_c3.BANKED_BALANCE, v_gs_c3.NET_UNITS
                                FROM dual;

                                SELECT  v_mr_c4.SLOT_CODE,v_mr_c4.IMP_INIT, v_mr_c4.IMP_FINAL, v_mr_c4.IMP_DIFF, v_mr_c4.IMP_UNITS, v_mr_c4.EXP_INIT, v_mr_c4.EXP_FINAL, v_mr_c4.EXP_DIFF, v_mr_c4.EXP_UNITS, meter.bc4, v_mr_c4.NET_UNITS
                                INTO  v_gs_c4.SLOT_CODE,v_gs_c4.IMP_INIT, v_gs_c4.IMP_FINAL, v_gs_c4.IMP_DIFF, v_gs_c4.IMP_UNITS, v_gs_c4.EXP_INIT, v_gs_c4.EXP_FINAL, v_gs_c4.EXP_DIFF, v_gs_c4.EXP_UNITS, v_gs_c4.BANKED_BALANCE, v_gs_c4.NET_UNITS
                                FROM dual;

                                SELECT  v_mr_c5.SLOT_CODE,v_mr_c5.IMP_INIT, v_mr_c5.IMP_FINAL, v_mr_c5.IMP_DIFF, v_mr_c5.IMP_UNITS, v_mr_c5.EXP_INIT, v_mr_c5.EXP_FINAL, v_mr_c5.EXP_DIFF, v_mr_c5.EXP_UNITS, meter.bc5, v_mr_c5.NET_UNITS
                                INTO  v_gs_c5.SLOT_CODE,v_gs_c5.IMP_INIT, v_gs_c5.IMP_FINAL, v_gs_c5.IMP_DIFF, v_gs_c5.IMP_UNITS, v_gs_c5.EXP_INIT, v_gs_c5.EXP_FINAL, v_gs_c5.EXP_DIFF, v_gs_c5.EXP_UNITS, v_gs_c5.BANKED_BALANCE, v_gs_c5.NET_UNITS
                                FROM dual;


                                -- -- dbms_output.put_line('gen stmt slots - insert');

                                INSERT INTO T_GEN_STMT VALUES  v_gs;
                                INSERT INTO T_GEN_STMT_SLOT VALUES  v_gs_c1;
                                INSERT INTO T_GEN_STMT_SLOT VALUES  v_gs_c2;
                                INSERT INTO T_GEN_STMT_SLOT VALUES  v_gs_c3;
                                INSERT INTO T_GEN_STMT_SLOT VALUES  v_gs_c4;
                                INSERT INTO T_GEN_STMT_SLOT VALUES  v_gs_c5;

                                -- -- dbms_output.put_line('gen stmt slots - calling charges');

        if(meter.fuel_type_Code = '03' or meter.fuel_type_Code = '04') then
          -- non-fossil fuels
          v_result := calc_gs_charges(v_gs.id);
          -- -- dbms_output.put_line('gen stmt slots - after charges');
        else
          --fossil fuels; charges are calculated in energy-sale
          null;
        end if;
        v_result := 'SUCCESS';
                                <<LOOP_END>>
        v_log_result := log_activity('PROCEDURE','PROCESS_GEN_STMT','IN-LOOP-END','End -'||meter.DISP_SERVICE_NUMBER,v_result,v_created_By, sysdate);
                                COMMIT;
                        exception
                          when others then
                            v_exception_code := SQLCODE;
                            v_exception_msg := SUBSTR(SQLERRM, 1, 200);
                            v_result := 'FAILURE';
                            v_reason := v_exception_code || ' - ' || v_exception_msg;
                            -- -- dbms_output.put_line('Error while processing each meter-reading - '||v_reason);

          v_log_result := log_activity('PROCEDURE','PROCESS_GEN_STMT','IN-LOOP-END','Exception -'||meter.DISP_SERVICE_NUMBER, v_reason,null,v_created_By, sysdate);
                        END;
                END LOOP;


                if(v_no_records) THEN
                        v_log_result := log_activity('PROCEDURE','PROCESS_GEN_STMT',null,'No Records to process',null,v_created_By, v_created_Date);

                ELSE
                        -- -- dbms_output.put_line('v_no_records- false. process-id->'||v_process_id);
      NULL;
                END IF;

        exception
          when others then
            v_exception_code := SQLCODE;
            v_exception_msg := SUBSTR(SQLERRM, 1, 200);
            v_result := 'FAILURE';
            v_reason := v_exception_code || ' - ' || v_exception_msg;
            -- -- dbms_output.put_line(v_reason);
        END;
   <<THE_END>>

  IF (v_no_records) THEN
        v_result := 'FAILURE';
        v_reason := 'No meter reading available to process generation statements';
         v_result := v_result || ' - ' || v_reason;

  else
          IF (V_RESULT = 'SUCCESS') THEN
                update T_PROCESS_GS set status='COMPLETED' where id = v_process_id;
          ELSE

                update T_PROCESS_GS set status='ERROR',  REMARKS=v_reason  where id = v_process_id;
            v_result := v_result || ' - ' || v_reason;
          END IF;
  END IF;

  v_log_result := log_activity('PROCEDURE','PROCESS_GEN_STMT','END','Start - '||v_process_id,V_RESULT,v_created_By, v_created_Date);

  COMMIT;

  return v_result;

END process_gen_stmt;