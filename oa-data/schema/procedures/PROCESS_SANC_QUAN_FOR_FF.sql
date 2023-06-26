CREATE OR REPLACE PROCEDURE OPENACCESS."PROCESS_SANC_QUAN_FOR_FF" (in_remarks in varchar2, out_result out varchar2) IS
v_sum number;
v_quantum number;
v_no_of_days number;
  BEGIN
select to_number(add_months(trunc(sysdate,'MM'),1) - trunc(sysdate,'MM')) into v_no_of_days from dual;

    for sin in(select * from m_signup where remarks=in_remarks)
    loop
        v_quantum:=(to_number(sin.SANCTIONED_QUANTUM)*1000*v_no_of_days*24);
        for imp in(select sum(c1) c1, sum(c2) c2 , sum(c3) c3, sum(c4) c4, sum(c5) c5 from import_trade_rel 
        where remarks=sin.remarks and seller_company_service_no = sin.HTSC_NUMBER group by seller_company_service_no)
        loop
            v_sum := to_number(imp.c1+imp.c2+imp.c3+imp.c4+imp.c5);
--            if (v_quantum <= v_sum) then
            if (v_sum <= v_quantum) then
            out_result:= 'valid_ff_data';
            update import_trade_rel set import_remarks='valid_ff_data' 
            where remarks=sin.remarks and seller_company_service_no = sin.HTSC_NUMBER;
            update m_signup set import_remarks='valid_ff_data' where remarks=in_remarks and HTSC_NUMBER=sin.HTSC_NUMBER;
            else
            out_result:='not_valid_ff_data';
            update import_trade_rel set import_remarks='not_valid_ff_data' 
            where remarks=sin.remarks and seller_company_service_no = sin.HTSC_NUMBER;
            update m_signup set import_remarks='not_valid_ff_data' where remarks=in_remarks and HTSC_NUMBER=sin.HTSC_NUMBER;
            end if;
        end loop;
      end loop;  
END PROCESS_SANC_QUAN_FOR_FF;

