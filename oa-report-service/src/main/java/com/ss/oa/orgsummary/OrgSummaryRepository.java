package com.ss.oa.orgsummary;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.oa.report.vo.OrgSummary;


public interface OrgSummaryRepository extends CrudRepository<OrgSummary, String>{
    @Query("SELECT new com.ss.oa.report.vo.OrgSummary(org.id,org.code,org.name,org.typeCode,org.parentCode,org.ncesDivisionCode,org.fuelTypeCode) FROM OrgSummary org WHERE NVL(LOWER(org.parentCode),'0') LIKE LOWER(CONCAT('%',NVL(:parentCode,''),'%')) AND NVL(LOWER(org.ncesDivisionCode),'0') LIKE LOWER(CONCAT('%',NVL(:ncesDivisionCode,''),'%'))"
    		+ "AND NVL(LOWER(org.typeCode),'0') LIKE LOWER(CONCAT('%',NVL(:typeCode,''),'%'))AND NVL(LOWER(org.fuelTypeCode),'0') LIKE LOWER(CONCAT('%',NVL(:fuelTypeCode,''),'%')) order by to_number(org.id)")
    List<OrgSummary> getOrg(@Param("parentCode")String parentCode,@Param("ncesDivisionCode")String ncesDivisionCode,@Param("typeCode")String typeCode,@Param("fuelTypeCode")String fuelTypeCode);

}
