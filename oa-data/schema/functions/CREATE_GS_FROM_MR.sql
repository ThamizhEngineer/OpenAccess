CREATE OR REPLACE FUNCTION OPENACCESS."CREATE_GS_FROM_MR" 
(
  v_gs_process_id in varchar2,
  v_mr_id in varchar2
) RETURN VARCHAR2 AS 
 
v_stage varchar2(50);
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
v_reason varchar2(200):='';
v_exception_code  NUMBER;
v_exception_msg  VARCHAR2(200);
v_result varchar(300):='';
v_plant_class_code VARCHAR2(200);
v_plant_class_desc VARCHAR2(200);
v_gen_month  VARCHAR2(50); 
v_gen_year  VARCHAR2(50); 
v_gen_comp_servi_id VARCHAR2(200);
tariff_rates VARCHAR2(200);
v_log_result varchar(300):='SUCCESS';
v_imported BOOLEAN;

v_gen_voltage_desc VARCHAR2(50); 
v_gen_org_code VARCHAR2(50); 
v_gen_iscaptive VARCHAR2(50); 
v_gen_isstb VARCHAR2(50);
v_gen_isstb_count NUMBER; 
v_gen_ssid VARCHAR2(50) ;
v_gen_ssname  VARCHAR2(50);
v_gen_sstype  VARCHAR2(50);
v_gen_ffid   VARCHAR2(50);
v_gen_ffname VARCHAR2(50);
v_tariff_net_amount VARCHAR2(150);  
v_net_payable VARCHAR2(150);
v_total_charged_amount  VARCHAR2(150);
v_flow_type_code  VARCHAR2(150);

v_prev_mr_id varchar2(50);
v_prev_mr T_METER_READING_HDR%ROWTYPE;
v_prev_mr_c1 T_METER_READING_SLOT%ROWTYPE;
v_prev_mr_c2 T_METER_READING_SLOT%ROWTYPE;
v_prev_mr_c3 T_METER_READING_SLOT%ROWTYPE;
v_prev_mr_c4 T_METER_READING_SLOT%ROWTYPE;
v_prev_mr_c5 T_METER_READING_SLOT%ROWTYPE;

BEGIN

	BEGIN	
        v_stage := '-1-start';
        v_log_result := log_activity('PROCEDURE','create_gs_from_mr','Start',v_reason,v_result,v_created_By, sysdate,v_mr_id,v_gs_process_id);

		FOR meter IN (SELECT distinct mh.M_COMPANY_METER_ID, mh.READING_MONTH, mh.READING_YEAR,mh.MR_SOURCE_CODE SOURCE_CODE,
						nvl(bg.open_c1,0) bc1, nvl(bg.OPEN_C2,0) bc2, nvl(bg.OPEN_C3,0) bc3,nvl(bg.OPEN_C4,0) bc4,nvl(bg.OPEN_C5,0) bc5,  pp.status disp_pp_op_status, cs.capacity disp_capacity, cs.total_capacity  disp_total_capacity, cm.meter_number disp_meter_number,
						cs.M_ORG_ID, c.ID M_COMPANY_ID,cm.M_COMPANY_SERVICE_ID, c.NAME DISP_COMPANY_NAME, cs."number" DISP_SERVICE_NUMBER, cs.VOLTAGE_CODE INJECTING_VOLTAGE_CODE, o.NAME DISP_ORG_NAME,tr.flow_type_code,
						pp.FUEL_TYPE_CODE DISP_FUEL_TYPE_CODE, fuel.FUEL_NAME  DISP_FUEL_TYPE_NAME,fuel.FUEL_GROUP DISP_FUEL_TYPE_GROUP,pp.COMMISSION_DATE COMMISSION_DATE,cs.IS_REC IS_REC,cs.TOTAL_CAPACITY TOTAL_CAPACITY, excess_units.FIND_TYPE_FN(cs."number") excess_unit_type
						FROM T_METER_READING_HDR mh 
						INNER JOIN M_COMPANY_METER cm ON mh.M_COMPANY_METER_ID = cm.ID
						INNER JOIN M_COMPANY_SERVICE cs ON cm.M_COMPANY_SERVICE_ID = cs.id
						INNER JOIN M_COMPANY c ON cs.M_COMPANY_ID = c.id
						INNER JOIN M_ORG o ON cs.M_ORG_ID = o.id
                        INNER JOIN M_TRADE_RELATIONSHIP tr ON tr.m_seller_comp_service_id=cs.id
                        LEFT JOIN t_exs_banking_balance bg ON bg.BANKING_SERVICE_ID = cs.BANKING_SERVICE_ID and to_number(bg.reading_month)= to_number(mh.READING_MONTH) and to_number(bg.reading_year)= to_number(mh.READING_YEAR)
                        LEFT JOIN M_POWERPLANT pp ON cs.id = pp.M_SERVICE_ID
                        LEFT JOIN M_FUEL fuel ON pp.FUEL_TYPE_CODE = fuel.FUEL_CODE			
						WHERE  mh.M_GEN_STMT_ID IS NULL AND mh.GS_BATCH_ID IS NULL  
                        and mh.id = v_mr_id)
		LOOP
			BEGIN

        v_stage := '-2-meter-loop-start';

        -- as there is meter-readings to process, we have to set the flag accordingly and start the generation process
				if(v_no_records) THEN
					v_no_records := FALSE;

				END IF;


        v_log_result := log_activity('PROCEDURE','meter.id','Start',meter.M_COMPANY_SERVICE_ID,v_result,v_created_By, sysdate,v_mr_id,v_gs_process_id);

				-- -- dbms_output.put_line('meter.M_COMPANY_METER_ID - '||meter.M_COMPANY_METER_ID);
				-- -- dbms_output.put_line('meter.M_COMPANY_SERVICE_ID - '||meter.M_COMPANY_SERVICE_ID);
				-- -- dbms_output.put_line('meter.READING_Year - '||meter.READING_Year);
				-- -- dbms_output.put_line('meter.READING_MONTH - '||meter.READING_MONTH);

				-- to find generator capacity
				SELECT count(*) INTO v_gen_count FROM M_GENERATOR g JOIN M_POWERPLANT p ON g.M_POWERPLANT_ID = p.ID WHERE p.M_SERVICE_ID = meter.M_COMPANY_SERVICE_ID ;


        v_stage := '-3-find generator capacity';
				-- -- dbms_output.put_line('v_gen_count- '||v_gen_count);
     v_log_result := log_activity('PROCEDURE','meter.id','v_stage',meter.M_COMPANY_SERVICE_ID,v_stage,v_created_By, sysdate,v_mr_id,v_gs_process_id);
				if(v_gen_count =0) THEN
					-- setup issue - generator not configured properly for this service
					v_log_result := log_activity('PROCEDURE','PROCESS_GEN_STMT','ISSUE','Setup Issue - '||v_process_id||' - No generator configured for service-id-->'||meter.M_COMPANY_SERVICE_ID,null,v_created_By, v_created_Date);
					CONTINUE;
				ELSE	
          -- TODO - Sum() should be revisited
                          v_stage := '-4-Sum() should be revisited';
					--SELECT sum(nvl(CAPACITY,0))  INTO v_gs.MACHINE_CAPACITY FROM M_GENERATOR g JOIN M_POWERPLANT p ON g.M_POWERPLANT_ID = p.ID WHERE p.M_SERVICE_ID = meter.M_COMPANY_SERVICE_ID ;
                   v_gs.MACHINE_CAPACITY:=meter.TOTAL_CAPACITY;
				END IF;


				-- -- dbms_output.put_line('v_gs.MACHINE_CAPACITY - '||v_gs.MACHINE_CAPACITY);
                v_stage := '-5-Getmr header';

				SELECT * INTO v_mrh FROM T_METER_READING_HDR WHERE  M_COMPANY_METER_ID = meter.M_COMPANY_METER_ID AND READING_MONTH = meter.READING_MONTH AND READING_Year = meter.READING_Year;


    -- -- dbms_output.put_line('v_mrh.id - '||v_mrh.id);

                v_stage := '-6-';

				SELECT * INTO v_mr_c1 FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mrh.id AND SLOT_CODE = 'C1';
				SELECT * INTO v_mr_c2 FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mrh.id AND SLOT_CODE = 'C2';
				SELECT * INTO v_mr_c3 FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mrh.id AND SLOT_CODE = 'C3';
				SELECT * INTO v_mr_c4 FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mrh.id AND SLOT_CODE = 'C4';
				SELECT * INTO v_mr_c5 FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mrh.id AND SLOT_CODE = 'C5';

               -- -- dbms_output.put_line('crossed stage 6');

        --check if there previous meter-reading is to be included in this gen-stmt
        v_prev_mr_id := FIND_PREV_MR(v_mrh.id,'Y'); 
        if(v_prev_mr_id is not null )then
          -- previous meter reading is to be merged with the current meter reading
          SELECT * INTO v_prev_mr FROM T_METER_READING_HDR WHERE id =  v_prev_mr_id;
          SELECT * INTO v_prev_mr_c1 FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_prev_mr_id AND SLOT_CODE = 'C1';
          SELECT * INTO v_prev_mr_c2 FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_prev_mr_id AND SLOT_CODE = 'C2';
          SELECT * INTO v_prev_mr_c3 FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_prev_mr_id AND SLOT_CODE = 'C3';
          SELECT * INTO v_prev_mr_c4 FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_prev_mr_id AND SLOT_CODE = 'C4';
          SELECT * INTO v_prev_mr_c5 FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_prev_mr_id AND SLOT_CODE = 'C5';

          ----- modify current meter reading record according to the same ----
         -- -- dbms_output.put_line('crossed prev_mr');

          --modify header
          v_mrh.INIT_READING_DT := v_prev_mr.INIT_READING_DT;
          v_mrh.EXP_RKVAH_INIT := v_prev_mr.EXP_RKVAH_INIT;
          v_mrh.IMP_RKVAH_INIT := v_prev_mr.IMP_RKVAH_INIT;
          v_mrh.EXP_KVAH_INIT := v_prev_mr.EXP_KVAH_INIT;
          v_mrh.IMP_KVAH_INIT := v_prev_mr.IMP_KVAH_INIT;
          v_mrh.RKVAH_DIFF := nvl(v_mrh.IMP_RKVAH_FINAL,0) - nvl(v_mrh.IMP_RKVAH_INIT, 0);
          v_mrh.RKVAH_UNITS := v_mrh.RKVAH_DIFF*v_mrh.mf;
          v_mrh.KVAH_DIFF := nvl((v_mrh.EXP_KVAH_FINAL - v_mrh.EXP_KVAH_INIT)- (v_mrh.IMP_KVAH_FINAL - v_mrh.IMP_KVAH_INIT), 0);
          v_mrh.KVAH_UNITS := v_mrh.KVAH_DIFF*v_mrh.mf;

         -- -- dbms_output.put_line('crossed hdr data');
          -- modify slot data
          -- -- dbms_output.put_line('crossed slot data');

          --slot1 changes
          v_mr_c1.IMP_INIT := nvl(v_prev_mr_c1.IMP_INIT,0); v_mr_c1.EXP_INIT := nvl(v_prev_mr_c1.EXP_INIT,0);
          v_mr_c1.IMP_DIFF := v_mr_c1.IMP_FINAL - v_mr_c1.IMP_INIT ;v_mr_c1.EXP_DIFF := v_mr_c1.EXP_FINAL - v_mr_c1.EXP_INIT ;


          v_mr_c1.IMP_UNITS := v_mr_c1.IMP_DIFF * v_mrh.mf; v_mr_c1.EXP_UNITS := v_mr_c1.EXP_DIFF * v_mrh.mf;
          if (to_number(v_mr_c1.EXP_DIFF)<0) then
          v_mr_c1.EXP_UNITS :=0;
          end if;
          v_mr_c1.net_units := ROUND(v_mr_c1.EXP_UNITS - v_mr_c1.IMP_UNITS,0) ; if(to_number(v_mr_c1.net_units )<0) THEN v_mr_c1.net_units := 0; END IF;

          --slot2 changes
          v_mr_c2.IMP_INIT := nvl(v_prev_mr_c2.IMP_INIT,0); v_mr_c2.EXP_INIT := nvl(v_prev_mr_c2.EXP_INIT,0);
          v_mr_c2.IMP_DIFF := v_mr_c2.IMP_FINAL - v_mr_c2.IMP_INIT ;v_mr_c2.EXP_DIFF := v_mr_c2.EXP_FINAL - v_mr_c2.EXP_INIT ;
          v_mr_c2.IMP_UNITS := v_mr_c2.IMP_DIFF * v_mrh.mf; v_mr_c2.EXP_UNITS := v_mr_c2.EXP_DIFF * v_mrh.mf;
           if (to_number(v_mr_c2.EXP_DIFF)<0) then
          v_mr_c2.EXP_UNITS :=0;
          end if;
          v_mr_c2.net_units := ROUND(v_mr_c2.EXP_UNITS - v_mr_c2.IMP_UNITS,0) ; if(to_number(v_mr_c2.net_units ) <0) THEN v_mr_c2.net_units := 0; END IF;

          --slot3 changes
          v_mr_c3.IMP_INIT := nvl(v_prev_mr_c3.IMP_INIT,0); v_mr_c3.EXP_INIT := nvl(v_prev_mr_c3.EXP_INIT,0);
          v_mr_c3.IMP_DIFF := v_mr_c3.IMP_FINAL - v_mr_c3.IMP_INIT ;v_mr_c3.EXP_DIFF := v_mr_c3.EXP_FINAL - v_mr_c3.EXP_INIT ;
          v_mr_c3.IMP_UNITS := v_mr_c3.IMP_DIFF * v_mrh.mf; v_mr_c3.EXP_UNITS := v_mr_c3.EXP_DIFF * v_mrh.mf;
          if (to_number(v_mr_c3.EXP_DIFF)<0) then
          v_mr_c3.EXP_UNITS :=0;
          end if;
          v_mr_c3.net_units := ROUND(v_mr_c3.EXP_UNITS - v_mr_c3.IMP_UNITS,0) ; if(to_number(v_mr_c3.net_units ) <0) THEN v_mr_c3.net_units := 0; END IF;  

          --slot4 changes
          v_mr_c4.IMP_INIT := nvl(v_prev_mr_c4.IMP_INIT,0); v_mr_c4.EXP_INIT := nvl(v_prev_mr_c4.EXP_INIT,0);
          v_mr_c4.IMP_DIFF := v_mr_c4.IMP_FINAL - v_mr_c4.IMP_INIT ;v_mr_c4.EXP_DIFF := v_mr_c4.EXP_FINAL - v_mr_c4.EXP_INIT ;
          v_mr_c4.IMP_UNITS := v_mr_c4.IMP_DIFF * v_mrh.mf; v_mr_c4.EXP_UNITS := v_mr_c4.EXP_DIFF * v_mrh.mf;
         if (to_number(v_mr_c4.EXP_DIFF)<0) then
          v_mr_c4.EXP_UNITS :=0;
          end if;
          v_mr_c4.net_units := ROUND(v_mr_c4.EXP_UNITS - v_mr_c4.IMP_UNITS,0) ; if(to_number(v_mr_c4.net_units ) <0) THEN v_mr_c4.net_units := 0; END IF;

          --slot5 changes
          v_mr_c5.IMP_INIT := nvl(v_prev_mr_c5.IMP_INIT,0); v_mr_c5.EXP_INIT := nvl(v_prev_mr_c5.EXP_INIT,0);
          v_mr_c5.IMP_DIFF := v_mr_c5.IMP_FINAL - v_mr_c5.IMP_INIT ;v_mr_c5.EXP_DIFF := v_mr_c5.EXP_FINAL - v_mr_c5.EXP_INIT ;
          v_mr_c5.IMP_UNITS := v_mr_c5.IMP_DIFF * v_mrh.mf; v_mr_c5.EXP_UNITS := v_mr_c5.EXP_DIFF * v_mrh.mf;
          if (to_number(v_mr_c5.EXP_DIFF)<0) then
          v_mr_c5.EXP_UNITS :=0;
          end if;
          v_mr_c5.net_units := ROUND(v_mr_c5.EXP_UNITS - v_mr_c5.IMP_UNITS,0) ; if(to_number(v_mr_c5.net_units ) <0) THEN v_mr_c5.net_units := 0; END IF;  

          -- update summary info in header 
          v_mrh.total_import_gen := v_mr_c1.IMP_UNITS + v_mr_c2.IMP_UNITS + v_mr_c3.IMP_UNITS + v_mr_c4.IMP_UNITS + v_mr_c5.IMP_UNITS;
					v_mrh.total_export_gen := v_mr_c1.EXP_UNITS + v_mr_c2.EXP_UNITS + v_mr_c3.EXP_UNITS + v_mr_c4.EXP_UNITS + v_mr_c5.EXP_UNITS;
					v_mrh.net_gen_units := v_mr_c1.NET_UNITS + v_mr_c2.NET_UNITS + v_mr_c3.NET_UNITS + v_mr_c4.NET_UNITS + v_mr_c5.NET_UNITS;

          ----- modify current meter reading record - end ----
        end if;

				---- -- dbms_output.put_line('all slots- '||v_mrh.id);
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
				v_gs.REF_NUMBER := v_gs_process_id;
        --v_gs.gs_batch_id := v_process_id;
				v_gs.mf := v_mrh.mf;
				v_gs.STMT_GEN_DATE := v_created_Date ;
				v_gs.STMT_MONTH :=  v_mrh.READING_MONTH;
				v_gs.STMT_YEAR := v_mrh.READING_YEAR;
				v_gs.INIT_STMT_DT := v_mrh.INIT_READING_DT;
				v_gs.FINAL_STMT_DT := v_mrh.FINAL_READING_DT;
				-- RKVAH_INIT, RKVAH_FINAL, KVAH_INIT, KVAH_FINAL are not relevant currently
				--RKVAH_INIT, RKVAH_FINAL, KVAH_INIT, KVAH_FINAL - IMPORT VALUES 
				v_gs.KVAH_INIT := v_mrh.IMP_KVAH_INIT;
        v_gs.KVAH_FINAL := v_mrh.IMP_KVAH_FINAL;
        v_gs.RKVAH_INIT := v_mrh.IMP_RKVAH_INIT;
        v_gs.RKVAH_FINAL := v_mrh.IMP_RKVAH_FINAL;
				v_gs.KVAH_DIFF := v_mrh.KVAH_DIFF;
				v_gs.KVAH_UNITS := v_mrh.KVAH_UNITS;
				v_gs.RKVAH_DIFF := v_mrh.RKVAH_DIFF;
				v_gs.RKVAH_UNITS := v_mrh.RKVAH_UNITS;
                v_mr_c1.EXP_UNITS := round(v_mr_c1.EXP_UNITS,0); v_mr_c1.IMP_UNITS := round(v_mr_c1.IMP_UNITS,0);v_mr_c1.NET_UNITS := round(v_mr_c1.NET_UNITS,0);
                v_mr_c2.EXP_UNITS := round(v_mr_c2.EXP_UNITS,0); v_mr_c2.IMP_UNITS := round(v_mr_c2.IMP_UNITS,0);v_mr_c2.NET_UNITS := round(v_mr_c2.NET_UNITS,0);
                v_mr_c3.EXP_UNITS := round(v_mr_c3.EXP_UNITS,0); v_mr_c3.IMP_UNITS := round(v_mr_c3.IMP_UNITS,0);v_mr_c3.NET_UNITS := round(v_mr_c3.NET_UNITS,0);
                v_mr_c4.EXP_UNITS := round(v_mr_c4.EXP_UNITS,0); v_mr_c4.IMP_UNITS := round(v_mr_c4.IMP_UNITS,0);v_mr_c4.NET_UNITS := round(v_mr_c4.NET_UNITS,0);
                v_mr_c5.EXP_UNITS := round(v_mr_c5.EXP_UNITS,0); v_mr_c5.IMP_UNITS := round(v_mr_c5.IMP_UNITS,0);v_mr_c5.NET_UNITS := round(v_mr_c5.NET_UNITS,0);

				v_gs.TOTAL_EXPORT_GEN := v_mr_c1.EXP_UNITS+v_mr_c2.EXP_UNITS+v_mr_c3.EXP_UNITS+v_mr_c4.EXP_UNITS+v_mr_c5.EXP_UNITS;
				v_gs.TOTAL_IMPORT_GEN := v_mr_c1.IMP_UNITS+v_mr_c2.IMP_UNITS+v_mr_c3.IMP_UNITS+v_mr_c4.IMP_UNITS+v_mr_c5.IMP_UNITS; 

				v_gs.MR_SOURCE_CODE := meter.SOURCE_CODE ;
                v_gs.M_ORG_ID := meter.M_ORG_ID;
				v_gs.M_COMPANY_ID := meter.M_COMPANY_ID;
				v_gs.M_COMPANY_SERVICE_ID := meter.M_COMPANY_SERVICE_ID;
				v_gs.DISP_COMPANY_NAME := meter.DISP_COMPANY_NAME;
				v_gs.DISP_SERVICE_NUMBER := meter.DISP_SERVICE_NUMBER;
				v_gs.INJECTING_VOLTAGE_CODE := meter.INJECTING_VOLTAGE_CODE;
				v_gs.DISP_ORG_NAME := meter.DISP_ORG_NAME; 
				v_gs.FLOW_TYPE_CODE := meter.FLOW_TYPE_CODE; 
				v_gs.net_generation := v_mr_c1.NET_UNITS+v_mr_c2.NET_UNITS+v_mr_c3.NET_UNITS+v_mr_c4.NET_UNITS+v_mr_c5.NET_UNITS;
				v_gs.C1 := v_mr_c1.NET_UNITS;
				v_gs.C2 := v_mr_c2.NET_UNITS;
				v_gs.C3 := v_mr_c3.NET_UNITS;
				v_gs.C4 := v_mr_c4.NET_UNITS;
				v_gs.C5 := v_mr_c5.NET_UNITS;
				v_gs.disp_fuel_type_code := meter.disp_fuel_type_code;
				v_gs.disp_fuel_type_name := meter.disp_fuel_type_name;
                v_gs.disp_fuel_type_group := meter.disp_fuel_type_group;
                v_gs.COMMISSION_DATE:= meter.COMMISSION_DATE;
                v_gs.IS_REC := meter.IS_REC;
                v_gs.disp_meter_number := meter.disp_meter_number;
                v_gs.disp_total_capacity := meter.disp_total_capacity;
                v_gs.disp_capacity := meter.disp_capacity;
                v_gs.disp_pp_op_status := meter.disp_pp_op_status;
                v_gs.EXCESS_UNIT_TYPE := meter.EXCESS_UNIT_TYPE; 
				v_gs.created_by := v_created_By;
				v_gs.created_dt := SYSDATE;
				v_gs.enabled := 'Y';
				v_gs.TOTAL_CHARGED_AMOUNT := 0; --actual value calculated by calc_gs_charges() 
				v_gs.NET_PAYABLE := 0; --actual value calculated by calc_gs_charges() 
				if(to_number(v_gs.TOTAL_EXPORT_GEN)*0.01 >= to_number(v_gs.RKVAH_UNITS)) THEN
					v_gs.penalty_rate := '0.25';
				ELSE
					v_gs.penalty_rate := '0.50';
				END IF;
                v_stage := '-7-';

                -- if excess_unit_type <> BANKING, clear the banking
                -- important note -even if a service has banking-balance for that month, if the excess_unit_type for that month is not banking, then dont include those units in GS
                -- those pending banking balance will be used later when its eligible.
                if( v_gs.excess_unit_type <> 'BANKING')
                then
                     v_gs_c1.BANKED_BALANCE := 0;
                     v_gs_c2.BANKED_BALANCE := 0;
                     v_gs_c3.BANKED_BALANCE := 0;
                     v_gs_c4.BANKED_BALANCE := 0;
                     v_gs_c5.BANKED_BALANCE := 0;

                end if;
				---- -- dbms_output.put_line('gen stmt - values set');

				SELECT v_mr_c1.SLOT_CODE,v_mr_c1.IMP_INIT, v_mr_c1.IMP_FINAL, v_mr_c1.IMP_DIFF, round(v_mr_c1.IMP_UNITS,0),v_mr_c1.EXP_INIT, v_mr_c1.EXP_FINAL, v_mr_c1.EXP_DIFF, round(v_mr_c1.EXP_UNITS,0), round(meter.bc1,0), round(v_mr_c1.NET_UNITS,0), 'Y',sysdate
				INTO  v_gs_c1.SLOT_CODE,v_gs_c1.IMP_INIT, v_gs_c1.IMP_FINAL, v_gs_c1.IMP_DIFF, v_gs_c1.IMP_UNITS, v_gs_c1.EXP_INIT, v_gs_c1.EXP_FINAL, v_gs_c1.EXP_DIFF, v_gs_c1.EXP_UNITS, v_gs_c1.BANKED_BALANCE, v_gs_c1.NET_UNITS, v_gs_c1.enabled, v_gs_c1.created_Date
				FROM dual;

				SELECT  v_mr_c2.SLOT_CODE,v_mr_c2.IMP_INIT, v_mr_c2.IMP_FINAL, v_mr_c2.IMP_DIFF, round(v_mr_c2.IMP_UNITS,0), v_mr_c2.EXP_INIT, v_mr_c2.EXP_FINAL, v_mr_c2.EXP_DIFF, round(v_mr_c2.EXP_UNITS,0), round(meter.bc2,0), round(v_mr_c2.NET_UNITS,0), 'Y',sysdate
				INTO  v_gs_c2.SLOT_CODE,v_gs_c2.IMP_INIT, v_gs_c2.IMP_FINAL, v_gs_c2.IMP_DIFF, v_gs_c2.IMP_UNITS, v_gs_c2.EXP_INIT, v_gs_c2.EXP_FINAL, v_gs_c2.EXP_DIFF, v_gs_c2.EXP_UNITS, v_gs_c2.BANKED_BALANCE, v_gs_c2.NET_UNITS, v_gs_c2.enabled, v_gs_c2.created_Date
				FROM dual;

				SELECT  v_mr_c3.SLOT_CODE,v_mr_c3.IMP_INIT, v_mr_c3.IMP_FINAL, v_mr_c3.IMP_DIFF, round(v_mr_c3.IMP_UNITS,0), v_mr_c3.EXP_INIT, v_mr_c3.EXP_FINAL, v_mr_c3.EXP_DIFF, round(v_mr_c3.EXP_UNITS,0), round(meter.bc3,0), round(v_mr_c3.NET_UNITS,0), 'Y',sysdate
				INTO  v_gs_c3.SLOT_CODE,v_gs_c3.IMP_INIT, v_gs_c3.IMP_FINAL, v_gs_c3.IMP_DIFF, v_gs_c3.IMP_UNITS, v_gs_c3.EXP_INIT, v_gs_c3.EXP_FINAL, v_gs_c3.EXP_DIFF, v_gs_c3.EXP_UNITS, v_gs_c3.BANKED_BALANCE, v_gs_c3.NET_UNITS, v_gs_c3.enabled, v_gs_c3.created_Date
				FROM dual;

				SELECT  v_mr_c4.SLOT_CODE,v_mr_c4.IMP_INIT, v_mr_c4.IMP_FINAL, v_mr_c4.IMP_DIFF, round(v_mr_c4.IMP_UNITS,0), v_mr_c4.EXP_INIT, v_mr_c4.EXP_FINAL, v_mr_c4.EXP_DIFF, round(v_mr_c4.EXP_UNITS,0), round(meter.bc4,0), round(v_mr_c4.NET_UNITS,0), 'Y',sysdate
				INTO  v_gs_c4.SLOT_CODE,v_gs_c4.IMP_INIT, v_gs_c4.IMP_FINAL, v_gs_c4.IMP_DIFF, v_gs_c4.IMP_UNITS, v_gs_c4.EXP_INIT, v_gs_c4.EXP_FINAL, v_gs_c4.EXP_DIFF, v_gs_c4.EXP_UNITS, v_gs_c4.BANKED_BALANCE, v_gs_c4.NET_UNITS, v_gs_c4.enabled, v_gs_c4.created_Date
				FROM dual;

				SELECT  v_mr_c5.SLOT_CODE,v_mr_c5.IMP_INIT, v_mr_c5.IMP_FINAL, v_mr_c5.IMP_DIFF, round(v_mr_c5.IMP_UNITS,0), v_mr_c5.EXP_INIT, v_mr_c5.EXP_FINAL, v_mr_c5.EXP_DIFF, round(v_mr_c5.EXP_UNITS,0), round(meter.bc5,0), round(v_mr_c5.NET_UNITS,0), 'Y',sysdate
				INTO  v_gs_c5.SLOT_CODE,v_gs_c5.IMP_INIT, v_gs_c5.IMP_FINAL, v_gs_c5.IMP_DIFF, v_gs_c5.IMP_UNITS, v_gs_c5.EXP_INIT, v_gs_c5.EXP_FINAL, v_gs_c5.EXP_DIFF, v_gs_c5.EXP_UNITS, v_gs_c5.BANKED_BALANCE, v_gs_c5.NET_UNITS, v_gs_c5.enabled, v_gs_c1.created_Date
				FROM dual;


				---- -- dbms_output.put_line('gen stmt slots - insert');

				INSERT INTO  T_GEN_STMT VALUES  v_gs;
				INSERT INTO  T_GEN_STMT_SLOT VALUES  v_gs_c1;
				INSERT INTO  T_GEN_STMT_SLOT VALUES  v_gs_c2;
				INSERT INTO  T_GEN_STMT_SLOT VALUES  v_gs_c3;
				INSERT INTO  T_GEN_STMT_SLOT VALUES  v_gs_c4;
				INSERT INTO  T_GEN_STMT_SLOT VALUES  v_gs_c5;



                 -- -- dbms_output.put_line('gen stmt slots - calling charges');
  --              if(v_gs.disp_fuel_type_group='RE') then
                       v_result := calc_gs_charges(v_gs.id);
               --             end if;



 			 -- -- dbms_output.put_line('gen stmt slots - after charges');

        update t_meter_reading_hdr set gs_batch_id = v_gs_process_id, M_GEN_STMT_ID = v_gs.id where id = v_mr_id;

                             v_stage := '-8-';

        SELECT STMT_MONTH,STMT_YEAR,M_COMPANY_SERVICE_ID INTO v_gen_month,v_gen_year,v_gen_comp_servi_id FROM T_GEN_STMT gen WHERE gen.ID= v_gs.id;

        SELECT pp.PLANT_CLASS_TYPE_CODE,tariff.WEG_GROUP_NAME,tariff.RATE into  v_plant_class_code,v_plant_class_desc,tariff_rates FROM M_POWERPLANT pp
        LEFT JOIN M_TARIFF tariff ON pp.PLANT_CLASS_TYPE_CODE=tariff.WEG_GROUP_CODE 
        LEFT JOIN T_GEN_STMT gen ON pp.m_service_id = gen.M_COMPANY_SERVICE_ID and gen.ID=v_gs.id
        WHERE  gen.M_COMPANY_SERVICE_ID=v_gen_comp_servi_id;

              -- -- dbms_output.put_line('tariff rate- '||tariff_rates);
               -- -- dbms_output.put_line('v_plant_class_code- '||v_plant_class_code);
            -- -- dbms_output.put_line('v_plant_class_desc- '||v_plant_class_desc);
              -- -- dbms_output.put_line('service id- '||v_gen_comp_servi_id);


        select flow_type_code into v_flow_type_code from M_COMPANY_SERVICE where id=v_gen_comp_servi_id;
                -- -- dbms_output.put_line('v_flow_type_code 2-'|| v_flow_type_code );


        UPDATE T_GEN_STMT SET PLANT_CLASS_TYPE_CODE=v_plant_class_code,PLANT_CLASS_TYPE_DESC=v_plant_class_desc,TARIFF_RATE=tariff_rates WHERE ID=v_gs.id;

                        v_stage := '-9-';

--        SELECT companygs.IS_CAPTIVE INTO v_gen_iscaptive FROM V_COMPANY_SERVICE companygs
--        LEFT JOIN T_GEN_STMT gengs ON companygs.M_COMPANY_ID = gengs.M_COMPANY_ID and companygs.IS_CAPTIVE='Y' WHERE gengs.ID=v_gs.id;
--        
--        -- -- dbms_output.put_line('v_gen_iscaptive 2-'|| v_gen_iscaptive );

  --      SELECT COUNT(compgs.IS_CAPTIVE) into v_gen_isstb_count from V_COMPANY_SERVICE  compgs
  --      LEFT JOIN T_GEN_STMT stbgs on compgs.ID = stbgs.M_COMPANY_SERVICE_ID and compgs.M_COMPANY_ID='TNEB' WHERE stbgs.ID=v_gs.id ;



--        -- -- dbms_output.put_line('v_gen_isstb_count-2-'|| v_gen_isstb_count );

        SELECT orggs.CODE INTO v_gen_org_code FROM M_ORG orggs
        LEFT JOIN T_GEN_STMT gengs1 ON orggs.ID = gengs1.M_ORG_ID WHERE gengs1.ID=v_gs.id;

        -- -- dbms_output.put_line('v_gen_org_code-2-'|| v_gen_org_code );

        SELECT codes.VALUE_DESC INTO v_gen_voltage_desc FROM V_CODES codes
        LEFT JOIN T_GEN_STMT gengs2 ON codes.VALUE_CODE=gengs2.INJECTING_VOLTAGE_CODE AND codes.LIST_NAME='Voltage'
        WHERE gengs2.ID=v_gs.id;

      -- -- dbms_output.put_line('v_gen_voltage_desc-2-'|| v_gen_voltage_desc);

--        if ( v_gen_isstb_count) > 0 THEN
--          v_gen_isstb := 'Y'; 
--         ELSE 
--          v_gen_isstb := 'N'; 
--        END IF;

       --nEW mAY NEED CHANGE 
        if (v_flow_type_code='STB') THEN
            v_gen_isstb := 'Y'; 
         ELSE 
          v_gen_isstb := 'N'; 
        END IF;
          -- -- dbms_output.put_line('v_gen_isstb-'|| v_gen_isstb );

        if (v_flow_type_code='IS-CAPTIVE') THEN
           v_gen_iscaptive := 'Y'; 
         ELSE 
          v_gen_iscaptive := 'N'; 
        END IF;
          -- -- dbms_output.put_line('v_flow_type_code-'|| v_flow_type_code );



        --IF(v_gen_isstb_count) > 0 THEN
           v_tariff_net_amount :=  to_number((v_gs.net_generation)* tariff_rates) ;
            -- -- dbms_output.put_line('v_tariff_net_amount- '||v_tariff_net_amount );
           SELECT gs.TOTAL_CHARGED_AMOUNT INTO v_total_charged_amount FROM T_GEN_STMT gs where gs.ID=v_gs.id;
           -- -- dbms_output.put_line('v_total_charged_amoun- '||v_total_charged_amount );
           v_net_payable := v_tariff_net_amount - v_total_charged_amount;
           -- -- dbms_output.put_line('v_net_payable- '||v_net_payable);
        --   ELSE
        --   CONTINUE;
        --END IF;

         -- -- dbms_output.put_line('v_gen_isstb--'|| v_gen_isstb);

        UPDATE T_GEN_STMT SET IS_CAPTIVE=v_gen_iscaptive,IS_STB=v_gen_isstb,DISP_ORG_CODE=v_gen_org_code,INJECTING_VOLTAGE_DESC=v_gen_voltage_desc,TARIFF_NET_AMOUNT=v_tariff_net_amount,NET_PAYABLE = v_net_payable WHERE ID=v_gs.id;


        --select  ss.ID,ss.NAME,ss.TYPE_OF_SS INTO v_gen_ssid, v_gen_ssname,v_gen_sstype  FROM  M_SUBSTATION  ss
        --LEFT JOIN T_GEN_STMT gengs3 ON  ss.M_ORG_ID = gengs3.M_ORG_ID   WHERE gengs3.ID=v_gs.id ;
        --
        --select  ff.ID,ff.NAME INTO v_gen_ffid, v_gen_ffname  FROM  M_FEEDER ff
        --LEFT JOIN T_GEN_STMT gengs4 ON  ff.VOLTAGE_CODE= gengs4.INJECTING_VOLTAGE_CODE  WHERE gengs4.ID=v_gs.id ;
                        v_stage := '-10-';

        select service.M_SUBSTATION_ID,service.M_SUBSTATION_NAME,service.TYPE_OF_SS ,service.M_FEEDER_ID,service.M_FEEDER_NAME INTO v_gen_ssid, v_gen_ssname,v_gen_sstype,v_gen_ffid, v_gen_ffname
        from v_company_service service LEFT JOIN T_GEN_STMT gengs3 ON  service.id = gengs3.M_COMPANY_SERVICE_ID WHERE gengs3.ID=v_gs.id ;

         -- -- dbms_output.put_line('v_gen_ssid- '||v_gen_ssid);  -- -- dbms_output.put_line('v_gen_sstype- '||v_gen_sstype);   -- -- dbms_output.put_line('v_gen_ffname- '||v_gen_ffname);

        UPDATE T_GEN_STMT SET M_SUBSTATION_ID = v_gen_ssid, M_SUBSTATION_NAME = v_gen_ssname,M_FEEDER_ID = v_gen_ffid,M_FEEDER_NAME = v_gen_ffname ,TYPE_OF_SS =v_gen_sstype  WHERE ID=v_gs.id;

				COMMIT;
			exception
			  when others then
			    v_exception_code := SQLCODE;
			    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
			    v_result := 'FAILURE';
			    v_reason := v_exception_code || ' - ' || v_exception_msg;
          v_log_result := log_activity('PROCEDURE','create_gs_from_mr','EH','Error while processing each meter-reading - '||v_reason,v_result,v_created_By, sysdate,v_mr_id,v_gs_process_id);
			END;
		END LOOP;


       -- ---- -- dbms_output.put_line('all slots- '||v_mrh.id);
		if(v_no_records) THEN
			v_result := 'FAILURE';
            if(v_reason IS NULL OR v_reason ='') then v_reason := 'No records to process'; end if;
		END IF;

        if( V_RESULT != '') THEN
			v_result := 'SUCCESS'; 
		END IF; 

	exception
	  when others then
	    v_exception_code := SQLCODE;
	    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
	    v_result := 'FAILURE';
	    v_reason := v_exception_code || ' - ' || v_exception_msg;
	    -- -- -- dbms_output.put_line(v_reason);

      v_log_result := log_activity('PROCEDURE','create_gs_from_mr','EH',v_result,v_reason||v_stage,v_created_By, sysdate,v_mr_id,v_gs_process_id);
	END;
   <<THE_END>>


      v_log_result := log_activity('PROCEDURE','create_gs_from_mr','End',v_result,v_reason||v_stage,v_created_By, sysdate,v_mr_id,v_gs_process_id);

  COMMIT;

  return V_RESULT; 

END create_gs_from_mr;