package com.ss.oa.oaasummary;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ss.oa.report.vo.OaaSummary;

public interface OaaSummaryRepository extends PagingAndSortingRepository<OaaSummary, String>{

	Page<OaaSummary> findByGenCompServId(Pageable pageable,String genCompServId);

	Page<OaaSummary> findBySaleTypeCode(Pageable pageable, String saleTypeCode);

	Page<OaaSummary> findByStatusCode(Pageable pageable, String statusCode);

	Page<OaaSummary> findByGenCompId(Pageable pageable, String genCompId);

	Page<OaaSummary> findByGenEndOrgId(Pageable pageable, String genEndOrgId);

	Page<OaaSummary> findByFuelTypeCode(Pageable pageable, String fuelTypeCode);

	Page<OaaSummary> findByGenVoltageCode(Pageable pageable, String genVoltageCode);

	Page<OaaSummary> findByMonth(Pageable pageable, String month);

	Page<OaaSummary> findByYear(Pageable pageable, String year);


	
}
