CREATE OR REPLACE PROCEDURE OPENACCESS."TMP_UPDATE_GS" is
v_result VARCHAR2(100);
BEGIN

 /* updating GS to allocated if ES is available
  for ES in (SELECT T_GEN_STMT_ID FROM T_ENERGY_SALE ES JOIN T_GEN_STMT  GS ON GS.ID=ES.T_GEN_STMT_ID  WHERE GS.STATUS_CODE ='CREATED')
  loop
    update T_GEN_STMT set status_code='ALLOCATED', MODIFIED_BY='ADMIN', MODIFIED_DT=SYSDATE where ID=ES.T_GEN_STMT_ID; 
  end loop;

  */

  /*
  -- updating GS.flow-type-code for is-captive for month-02 (similar for  3rd party and stb)
 for GS in (select gs.id from t_gen_stmt gs join m_company_Service ser on ser."number"=gs.DISP_SERVICE_NUMBER
                  where ser.flow_type_code ='IS-CAPTIVE' and gs.flow_type_code is null
                  and stmt_month='02')
  loop
    update T_GEN_STMT set flow_type_code='IS-CAPTIVE', MODIFIED_BY='ADMIN', MODIFIED_DT=SYSDATE where ID=GS.ID; 

  end loop;
  */
  null;

END TMP_UPDATE_GS;

