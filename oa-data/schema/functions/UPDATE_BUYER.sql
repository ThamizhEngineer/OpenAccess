CREATE OR REPLACE FUNCTION OPENACCESS."UPDATE_BUYER" RETURN VARCHAR2 AS
v_buyer_cur sys_refcursor ;
v_buyer_number varchar2(100);
v_buyer_count number:=0;
BEGIN
OPEN v_buyer_cur for select buyernumber from temp_buyer_update;
    LOOP
    FETCH v_buyer_cur INTO v_buyer_number;
    select count(*) into v_buyer_count from m_company_service where   "number"=v_buyer_number;
    if v_buyer_count=1 then
    --already exist
    --update in remarks - buyer exits
    UPDATE temp_buyer_update SET  REMARKS = 'Buyer Already Exists';
    else
    UPDATE temp_buyer_update SET REMARKS = 'New Buyer';
    --update in remarks - buyer does not exist
    end if;
  EXIT WHEN v_buyer_cur%NOTFOUND;
END LOOP;
  RETURN 'success';
END ;

