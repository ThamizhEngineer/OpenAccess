package com.ss.oa.ledger.oahtcharges;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

import com.ss.oa.ledger.vo.OaHtCharges;
@Scope("prototype")
public interface OaHtChargesRepository extends CrudRepository<OaHtCharges, String> {

	List<OaHtCharges> findBySellerCompanyServiceNumber(String sellerCompanyServiceNumber);
}
