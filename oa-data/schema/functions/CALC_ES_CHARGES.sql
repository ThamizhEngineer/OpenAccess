CREATE OR REPLACE FUNCTION OPENACCESS."CALC_ES_CHARGES" (
    v_es_id IN VARCHAR2 )
  RETURN VARCHAR2
AS
  v_is_fossil_fuel boolean := true;
  v_gs_id        VARCHAR2(150);
  v_seller_comp_serv_id VARCHAR2(150);
  v_adjstd_customers number;
  v_evaluated      NUMBER;
  v_id             NUMBER;
  v_no_of_days number:=0;
  v_machine_capacity number:=0;
  v_penalty_rate number:=0;
  v_rkvah_inits number:=0;
  v_total_charges number:=0;
  v_grand_total_charges number:=0;
  v_formula        VARCHAR2(150);
  v_charge_type    VARCHAR2(150);
  v_unit_charge    VARCHAR2(150);
  v_charge         VARCHAR2(150);
  v_fuel_type_Code VARCHAR2(50);
  v_charge_percentage NUMBER:=0;
  v_net_generation VARCHAR2(150);
  v_result varchar(300):='SUCCESS';
  v_log_result varchar(300):='SUCCESS';
  v_exception_code VARCHAR2(150);
  v_exception_msg  VARCHAR2(150);
  v_reason VARCHAR2(300);
  v_month VARCHAR2(20);   v_year VARCHAR2(20); v_c008total_charges number;
  v_other_charges_count number;

BEGIN
  BEGIN

    select es.T_GEN_STMT_ID, es.SELLER_COMP_SERV_ID, count(t_energy_sale_id) into v_gs_id,v_seller_comp_serv_id, v_adjstd_customers from T_ES_USAGE_SUMMARY eus inner join t_energy_sale es on eus.t_energy_sale_id = es.id
    where t_energy_sale_id = v_es_id
    group by t_energy_sale_id, es.T_GEN_STMT_ID,es.SELLER_COMP_SERV_ID ;

	  SELECT gs.machine_capacity, (gs.FINAL_STMT_DT - gs.INIT_STMT_DT)+1 no_of_days, gs.penalty_rate, gs.RKVAH_UNITS ,gs.NET_GENERATION, gs.disp_fuel_type_Code,gs.stmt_month,gs.stmt_year
    INTO v_machine_capacity,v_no_of_days,v_penalty_rate, v_rkvah_inits,v_net_generation, v_fuel_type_Code,v_month,v_year
    FROM T_GEN_STMT gs where id = v_gs_id;
--           -- dbms_output.put_line(v_seller_comp_serv_id);

-- For gen other charges
    select count(*) into v_other_charges_count from T_GEN_OTHER_CHARGES where M_COMPANY_SERVICE_ID=v_seller_comp_serv_id and month=v_month and year=v_year and CHARGE_CODE='C008';

    	if(v_fuel_type_Code = '02') then
            v_charge_percentage:= 50;
        ELSIF(v_fuel_type_Code = '18') THEN
            v_charge_percentage:= 40;
        else
            v_charge_percentage:= 100;
        end if;

    if(v_fuel_type_Code = '03' or v_fuel_type_Code = '04') then
          -- non-fossil fuels
       v_is_fossil_fuel:=false;
    else
      v_is_fossil_fuel := true;
    end if;

--               -- dbms_output.put_line('2');


		FOR meter IN (SELECT CHARGE_CODE, CHARGE_DESC, CHARGE_TYPE_CODE, UNIT_CHARGE FROM M_CHARGE_defn d JOIN  M_CHARGES_MAP m ON d.id = m.M_CHARGE_ID AND CONTEXT = 'GENERATOR_STATEMENT' )
		LOOP
      BEGIN
        if(meter.charge_code = 'C001') then
          if(v_is_fossil_fuel) then
            continue; -- No meter reading charges
          else
            v_total_charges:= to_number(CALC_CHARGES(meter.charge_code,'','',''));
          end if;
        ELSIF(meter.charge_code = 'C002') then
             v_total_charges:= to_number(CALC_CHARGES(meter.charge_code,v_machine_capacity,v_no_of_days,''));
        ELSIF(meter.charge_code = 'C003') then
            v_total_charges:= to_number(CALC_CHARGES(meter.charge_code,v_machine_capacity,v_no_of_days,''));
        ELSIF(meter.charge_code = 'C004') then
            v_total_charges:= to_number(CALC_CHARGES(meter.charge_code,v_machine_capacity,v_no_of_days,''));
        ELSIF(meter.charge_code = 'C005') then
          if(v_is_fossil_fuel) then
              continue; --  No rkvah penalty
            else
              IF((to_number(v_net_generation)*0.1)>=v_rkvah_inits) then
                  v_penalty_rate:= 0.25;
              ELSE
                  v_penalty_rate:=0.5;
              end if;
--           -- dbms_output.put_line('4');

              v_total_charges:= to_number(CALC_CHARGES(meter.charge_code,v_penalty_rate,v_rkvah_inits,''));
            end if;
        ELSIF(meter.charge_code = 'C006') then
          if(v_is_fossil_fuel) then
            continue; -- No negative charges
          else
            v_total_charges:= to_number(CALC_CHARGES(meter.charge_code,v_charge,v_net_generation,''));
          end if;
        ELSIF(meter.charge_code = 'C007') then
          if(v_is_fossil_fuel) then
            v_total_charges:= to_number(CALC_CHARGES(meter.charge_code,'160',v_no_of_days,v_adjstd_customers));
--                       -- dbms_output.put_line('4');

--   for gen other charges                    
    if v_other_charges_count=1 then
        select to_number(TOTAL_CHARGES) into v_c008total_charges from t_gen_other_charges where M_COMPANY_SERVICE_ID=v_seller_comp_serv_id and month=v_month and year=v_year and CHARGE_CODE='C008';

        IF (meter.charge_code = 'C008') then
        v_total_charges:= v_c008total_charges;
        END IF;

    end if;


          else
            v_total_charges:= to_number(CALC_CHARGES(meter.charge_code,v_no_of_days,'',''));
          end if;
        else
          continue;
        end if;
        v_total_charges := ROUND(v_total_charges * v_charge_percentage/100);
--                   -- dbms_output.put_line('5');



        INSERT INTO  T_ES_CHARGE
        (ID, T_ENERGY_SALE_ID, M_COMP_SERV_ID,CHARGE_CODE, TOTAL_CHARGE)
        VALUES(T_ES_CHARGE_SEQ.nextval, v_es_id, v_seller_comp_serv_id, meter.charge_code, v_total_charges);

        v_grand_total_charges :=v_grand_total_charges+v_total_charges;
      EXCEPTION
      WHEN OTHERS THEN
        v_exception_code := SQLCODE;
        v_exception_msg  := SUBSTR(SQLERRM, 1, 100);
        v_result := 'FAILURE';
        v_reason := v_exception_code || ' - ' || v_exception_msg;
        v_log_result := log_activity('PROCEDURE','CALC_ES_CHARGES','EH-LOOP','Error while processing charge ('||meter.charge_code ||')- '||v_reason,v_result,'admin', sysdate,v_gs_id);
      END;

    END LOOP;

      update t_energy_sale set net_charges_allocated = v_grand_total_charges where id = v_es_id;

  EXCEPTION
  WHEN OTHERS THEN
    v_exception_code := SQLCODE;
    v_exception_msg  := SUBSTR(SQLERRM, 1, 100);
    v_result := 'FAILURE';
    v_reason := v_exception_code || ' - ' || v_exception_msg;
    v_log_result := log_activity('PROCEDURE','CALC_GS_CHARGES','EH','Isse in CALC_ES_CHARGES - '||v_reason,v_result,'admin', sysdate,v_es_id);
    --SELECT nvl(max(id),0) into v_id  FROM error_table;
    --INSERT INTO ERROR_TABLE  VALUES (v_id,'DB-CALC_CHARGES', v_exception_code || ' - ' || v_exception_msg ,'');
  END;
RETURN v_result;
END CALC_ES_CHARGES;