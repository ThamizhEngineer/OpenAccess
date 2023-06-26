package com.ss.oa.masterservice;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.oa.report.vo.MasterDataEntity;


public interface MasterDataServiceRepository extends CrudRepository<MasterDataEntity, String>{
	
    @Query("SELECT new com.ss.oa.report.vo.MasterDataEntity(master.orgId,master.orgCode,master.orgName) "
    		+ "FROM MasterDataEntity master WHERE NVL(LOWER(master.serviceId),'0') LIKE LOWER(CONCAT('%',NVL(:serviceId,''),'%')) AND NVL(LOWER(master.orgId),'0') LIKE LOWER(CONCAT('%',NVL(:orgId,''),'%')) "
    		+ "AND NVL(LOWER(master.substationId),'0') LIKE LOWER(CONCAT('%',NVL(:substationId,''),'%')) AND NVL(LOWER(master.feederId),'0') LIKE LOWER(CONCAT('%',NVL(:feederId,''),'%')) "
    		+ "AND NVL(LOWER(master.companyId),'0') LIKE LOWER(CONCAT('%',NVL(:companyId,''),'%')) AND NVL(LOWER(master.companymeterId),'0') LIKE LOWER(CONCAT('%',NVL(:companymeterId,''),'%')) "
    		+ "AND NVL(LOWER(master.serviceTypeCode),'0') LIKE LOWER(CONCAT('%',NVL(:serviceTypeCode,''),'%'))")
	List<MasterDataEntity> getOrgService(@Param("serviceId")String serviceId,@Param("orgId")String orgId,@Param("companyId")String companyId,
			@Param("substationId")String substationId,@Param("feederId")String feederId,@Param("companymeterId")String companymeterId,@Param("serviceTypeCode")String serviceTypeCode);
     
    @Query("SELECT new com.ss.oa.report.vo.MasterDataEntity(master.serviceId,master.serviceTypeCode,master.serviceTypeName,master.serviceNumber,master.companyId,master.companyCode,master.companyName,master.capacity,master.voltageCode,master.voltageName,master.fuelTypeCode,master.fuelTypeName) "
    		+ "FROM MasterDataEntity master WHERE NVL(LOWER(master.serviceId),'0') LIKE LOWER(CONCAT('%',NVL(:serviceId,''),'%')) AND NVL(LOWER(master.orgId),'0') LIKE LOWER(CONCAT('%',NVL(:orgId,''),'%')) "
    		+ "AND NVL(LOWER(master.substationId),'0') LIKE LOWER(CONCAT('%',NVL(:substationId,''),'%')) AND NVL(LOWER(master.feederId),'0') LIKE LOWER(CONCAT('%',NVL(:feederId,''),'%')) "
    		+ "AND NVL(LOWER(master.companyId),'0') LIKE LOWER(CONCAT('%',NVL(:companyId,''),'%')) AND NVL(LOWER(master.companymeterId),'0') LIKE LOWER(CONCAT('%',NVL(:companymeterId,''),'%')) "
    		+ "AND NVL(LOWER(master.serviceTypeCode),'0') LIKE LOWER(CONCAT('%',NVL(:serviceTypeCode,''),'%'))")
	List<MasterDataEntity> getCompanyService(@Param("serviceId")String serviceId,@Param("orgId")String orgId,@Param("companyId")String companyId,
			@Param("substationId")String substationId,@Param("feederId")String feederId,@Param("companymeterId")String companymeterId,@Param("serviceTypeCode")String serviceTypeCode);
    
    @Query("SELECT new com.ss.oa.report.vo.MasterDataEntity(master.serviceId,master.serviceTypeCode,master.serviceTypeName,master.serviceNumber,master.voltageCode,master.voltageName) "
    		+ "FROM MasterDataEntity master WHERE LOWER(master.serviceId) LIKE LOWER(CONCAT('%',:serviceId, '%')) AND LOWER(master.orgId) LIKE LOWER(CONCAT('%',:orgId, '%')) "
    		+ "AND LOWER(master.substationId) LIKE LOWER(CONCAT('%',:substationId, '%')) AND LOWER(master.feederId) LIKE LOWER(CONCAT('%',:feederId, '%')) "
    		+ "AND LOWER(master.companyId) LIKE LOWER(CONCAT('%',:companyId, '%')) AND LOWER(master.companymeterId) LIKE LOWER(CONCAT('%',:companymeterId, '%')) "
    		+ "AND LOWER(master.serviceTypeCode) LIKE LOWER(CONCAT('%',:serviceTypeCode, '%'))")
	List<MasterDataEntity> getService(@Param("serviceId")String serviceId,@Param("orgId")String orgId,@Param("companyId")String companyId,
			@Param("substationId")String substationId,@Param("feederId")String feederId,@Param("companymeterId")String companymeterId,@Param("serviceTypeCode")String serviceTypeCode);
    
    @Query("SELECT new com.ss.oa.report.vo.MasterDataEntity(master.companyId,master.companyCode,master.companyName,master.isBuyer,master.isSeller) "
    		+ "FROM MasterDataEntity master WHERE LOWER(master.serviceId) LIKE LOWER(CONCAT('%',:serviceId, '%')) AND LOWER(master.orgId) LIKE LOWER(CONCAT('%',:orgId, '%')) "
    		+ "AND LOWER(master.substationId) LIKE LOWER(CONCAT('%',:substationId, '%')) AND LOWER(master.feederId) LIKE LOWER(CONCAT('%',:feederId, '%')) "
    		+ "AND LOWER(master.companyId) LIKE LOWER(CONCAT('%',:companyId, '%')) AND LOWER(master.companymeterId) LIKE LOWER(CONCAT('%',:companymeterId, '%')) "
    		+ "AND LOWER(master.serviceTypeCode) LIKE LOWER(CONCAT('%',:serviceTypeCode, '%'))")
	List<MasterDataEntity> getCompany(@Param("serviceId")String serviceId,@Param("orgId")String orgId,@Param("companyId")String companyId,
			@Param("substationId")String substationId,@Param("feederId")String feederId,@Param("companymeterId")String companymeterId,@Param("serviceTypeCode")String serviceTypeCode);
    
    @Query("SELECT new com.ss.oa.report.vo.MasterDataEntity(master.serviceNumber,master.orgName,master.companymeterId,master.modemNumber,master.meterNumber,master.multiplicationFactor,master.meterMakeCode,master.meterCt1,master.meterCt2,master.meterCt3,master.meterPt1,master.meterPt2,master.meterPt3) "
	+ "FROM MasterDataEntity master WHERE NVL(LOWER(master.serviceId),'0') LIKE LOWER(CONCAT('%',NVL(:serviceId,''),'%')) AND NVL(LOWER(master.orgId),'0') LIKE LOWER(CONCAT('%',NVL(:orgId,''),'%')) "
	+ "AND NVL(LOWER(master.substationId),'0') LIKE LOWER(CONCAT('%',NVL(:substationId,''),'%')) AND NVL(LOWER(master.feederId),'0') LIKE LOWER(CONCAT('%',NVL(:feederId,''),'%')) "
	+ "AND NVL(LOWER(master.companyId),'0') LIKE LOWER(CONCAT('%',NVL(:companyId,''),'%')) AND NVL(LOWER(master.companymeterId),'0') LIKE LOWER(CONCAT('%',NVL(:companymeterId,''),'%')) "
	+ "AND NVL(LOWER(master.serviceTypeCode),'0') LIKE LOWER(CONCAT('%',NVL(:serviceTypeCode,''),'%'))"
	+ "AND NVL(LOWER(master.typeOfSs),'') LIKE LOWER(CONCAT('%',NVL(:typeOfSs,''),'%'))")
	List<MasterDataEntity> getMeter(@Param("serviceId")String serviceId,@Param("orgId")String orgId,@Param("companyId")String companyId,
			@Param("substationId")String substationId,@Param("feederId")String feederId,@Param("companymeterId")String companymeterId,@Param("serviceTypeCode")String serviceTypeCode,@Param("typeOfSs")String typeOfSs);
    
}
