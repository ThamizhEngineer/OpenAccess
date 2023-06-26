package com.ss.oa.transaction.transcoinvoice;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ss.oashared.model.InvoiceHeader;

import org.springframework.stereotype.Repository;


@EnableJpaRepositories
@Repository
public interface TranscoInvoiceRepository extends CrudRepository<InvoiceHeader ,String>{

	
	@Query(value="SELECT * FROM T_INVOICE_HDR WHERE M_ORG_ID LIKE CONCAT('%',NVL( ?1,'')) AND line_Year LIKE  CONCAT('%',NVL( ?3,'')) AND line_MONTH LIKE CONCAT('%',NVL( ?2,'')) AND M_COMP_SERV_NO LIKE CONCAT('%',NVL( ?4,'')) AND INVSTATUS LIKE CONCAT(NVL( ?5,''),'%')",nativeQuery=true)
	List<InvoiceHeader> fetchByAdvanceFilter(String orgid,String month,String year,String mdispid,String invStatus);
	
	
	
	@Query(value="SELECT * FROM T_INVOICE_HDR WHERE M_COMP_SERV_NO = ?1 AND id = ?2 AND LINE_YEAR = ?3 AND LINE_MONTH = ?4"
			, nativeQuery = true)
	InvoiceHeader findBycombo(String compservid,String invoiceid,String year,String month);

	@Query(value="select * from T_INVOICE_HDR", nativeQuery=true)
	List<InvoiceHeader> findalls();
	
	@Query(value="SELECT * FROM T_INVOICE_HDR WHERE id = ?1 "
			, nativeQuery = true)
	InvoiceHeader findbyId(String invoiceid);
		
}
