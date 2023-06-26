CREATE OR REPLACE FUNCTION OPENACCESS."TEMP_CLEAR_ES_FOR_BK_FIX" 
(
  I_MONTH IN VARCHAR2 
, I_YEAR IN VARCHAR2 
) RETURN VARCHAR2 AS 

v_number varchar2(50);
v_service_count number;
o_res_code varchar2(50);
o_res_desc varchar2(200);

BEGIN

  for s in(select * FROM T_ENERGY_SALE WHERE MONTH='05' and  BC1+BC2+BC3+BC4+BC5>0)
  loop
    select count(*) into v_service_count from m_company_service where id=s.seller_comp_serv_id;
    if v_service_count=0 then
    select "number" into v_number from m_company_service where id=s.seller_comp_serv_id;
    DELETE_TXN.DELETE_BY_SERVICE('BANKING-ISSUEFIX-240619',v_number,I_MONTH,I_YEAR,'Y','N','N','N',o_res_code,o_res_desc);
    update T_ENERGY_SALE set PROCESS_REMARKS='MANUAL-BANKING-UPDATE-240619' WHERE SELLER_COMP_SERV_ID=s.seller_comp_serv_id AND MONTH=I_MONTH AND YEAR=I_YEAR;
    end if;
  end loop;
  RETURN NULL;
END TEMP_CLEAR_ES_FOR_BK_FIX;

