TRIGGER "OPENACCESS"."T_SLDC_NOC_TRIGGER" 
BEFORE UPDATE ON T_SLDC_NOC 
for each row 
BEGIN
  if(:new.noc_purpose='Seller') then    
        UPDATE ext_samast_appln a
        SET  a.SELLER_NOC_STATUS = :new.status, a.SELLER_NOC_QUANTITY = :new.approved_quantity,a.SELLER_NOC_START_DT = :new.approved_period_start_date ,a.seller_NOC_END_DT=:new.approved_period_end_date, a.modified_by='T_SLDC_NOC_TRIGGER', a.modified_dt=sysdate
        where seller_sldc_noc_id = :new.id;
        if(not(nvl(:old.status,'X')='CE-APPROVED') and :new.status='CE-APPROVED') then  
            UPDATE ext_samast_appln a SET a.seller_ce_approval_date = sysdate where seller_sldc_noc_id = :new.id;
        end if;
  else
        UPDATE ext_samast_appln a
        SET  a.buyer_NOC_STATUS = :new.status, a.buyer_NOC_QUANTITY = :new.approved_quantity,a.buyer_NOC_START_DT = :new.approved_period_start_date ,a.BUYER_NOC_END_DT=:new.approved_period_end_date, a.modified_by='T_SLDC_NOC_TRIGGER', a.modified_dt=sysdate
        where buyer_sldc_noc_id = :new.id;
        if(not(nvl(:old.status,'X')='CE-APPROVED') and :new.status='CE-APPROVED') then  
            UPDATE ext_samast_appln a SET a.buyer_ce_approval_date = sysdate where buyer_sldc_noc_id = :new.id;
        end if;
    end if;
END;