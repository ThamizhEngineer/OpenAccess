CREATE OR REPLACE FUNCTION OPENACCESS."DELETE_USING_ES_ID" 
(
  V_ES_ID IN VARCHAR2 
) RETURN VARCHAR2 AS 
BEGIN



          delete from F_ENERGY_CHARGES where F_ENERGY_SALE_ORDER_ID='30980';
          DELETE from f_energy_ledger where F_ENERGY_SALE_ORDER_ID='30980';
          delete from F_ENERGY_SALE_ORDER_LINES where T_ENERGY_SALE_ID='310ef273-ce3d-41ca-936b-fb06f6728ea7';
          delete from f_energy_sale_order where T_ENERGY_SALE_ID='310ef273-ce3d-41ca-936b-fb06f6728ea7';


          delete from T_ES_CHARGE where t_energy_sale_id = '310ef273-ce3d-41ca-936b-fb06f6728ea7';
          delete from T_ES_USAGE_DETAIL where t_energy_sale_id = '310ef273-ce3d-41ca-936b-fb06f6728ea7';
          delete from T_ES_USAGE_SUMMARY where t_energy_sale_id = '310ef273-ce3d-41ca-936b-fb06f6728ea7';
          delete from T_ENERGY_SALE where id = '310ef273-ce3d-41ca-936b-fb06f6728ea7';

  RETURN 'SUCCESS';
END DELETE_USING_ES_ID;

