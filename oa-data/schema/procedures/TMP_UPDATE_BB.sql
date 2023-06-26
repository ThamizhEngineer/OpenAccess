CREATE OR REPLACE PROCEDURE OPENACCESS.TMP_UPDATE_BB
(
  i_reading_month IN VARCHAR2 , i_reading_year IN VARCHAR2 , i_batch_key in varchar2
) is
O_RESULT_CODE VARCHAR2(100);
O_RESULT_DESC VARCHAR2(100);
v_remarks VARCHAR2(100);
BEGIN
    v_remarks :='UPDATE-BB-'||systimestamp;
    delete from t_banking_balance where month =i_reading_month and year= i_reading_year and banking_service_id in
        (
        select distinct s.banking_service_id from bk_upd_curvalues bk inner join m_company_service s on s."number"=bk.service_no where bk.batch_key=i_batch_key
        minus
        select distinct so.banking_service_id from f_energy_sale_order so inner join m_company_service s on s.id=so.seller_comp_serv_id where month=i_reading_month and year=i_reading_year and d_sell_comp_serv_number in
        (select service_no from bk_upd_curvalues  where batch_key=i_batch_key)
        and nvl(total_banking_units_sold,0) >0
        );


    INSERT INTO INT_DELETE_TXN (REMARKS,GEN_SERVICE_NUMBER, READING_MONTH, READING_YEAR, CREATED_BY, CREATED_DT)
        select v_remarks REMARKS,  GEN_SERVICE_NUMBER,i_reading_month READING_MONTH,i_reading_year READING_YEAR,'TMP_UPDATE_BB' CREATED_BY,sysdate CREATED_DT from (
         select distinct s."number" GEN_SERVICE_NUMBER from bk_upd_curvalues bk inner join m_company_service s on s."number"=bk.service_no where bk.batch_key=i_batch_key
            minus
            select distinct s."number" GEN_SERVICE_NUMBER from f_energy_sale_order so inner join m_company_service s on s.id=so.seller_comp_serv_id where month=i_reading_month and year=i_reading_year and d_sell_comp_serv_number in
            (select service_no from bk_upd_curvalues where batch_key=i_batch_key)
            and nvl(total_banking_units_sold,0) >0);
            
    O_RESULT_CODE := BK_TO_UPDATE_CURC1();
    
    DELETE_TXN.PROCESS_INT_DELETE_TXN(v_remarks,'Y','Y','Y','N',O_RESULT_CODE,O_RESULT_DESC );
    
    O_RESULT_CODE := CREATE_GS(null,i_reading_month,i_reading_year);
    
END TMP_UPDATE_BB;