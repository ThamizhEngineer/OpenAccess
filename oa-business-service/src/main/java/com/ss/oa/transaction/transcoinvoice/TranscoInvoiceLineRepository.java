package com.ss.oa.transaction.transcoinvoice;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.oashared.model.InvoiceLine;


public interface TranscoInvoiceLineRepository extends CrudRepository<InvoiceLine,Integer> {
	@Query(value="SELECT * FROM T_INVOICE_LINE  WHERE T_INV_HDR_ID = ?1 AND CHARGECODE IN ('C002','C003','C004','C005','C007') ORDER BY ITEMNO DESC", nativeQuery=true)
	List<InvoiceLine> findByTInvHdrIds(String tInvHdrId);

}
