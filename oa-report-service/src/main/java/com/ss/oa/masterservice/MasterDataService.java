package com.ss.oa.masterservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ss.oa.report.vo.MasterDataEntity;
import com.ss.oashared.common.OpenAccessException;

@RestController
@RequestMapping("/report/masterdata")
public class MasterDataService {
	
	@Autowired
	MasterDataServiceRepository masterServiceRepository;
	
	@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<MasterDataEntity> getMasterDate() throws OpenAccessException {
		return masterServiceRepository.findAll();
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/data/{select}")
	public List<MasterDataEntity>getDataService(@PathVariable(value="select")String select,
			@RequestParam(value = "serviceId", required = false) String serviceId,
            @RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "companyId", required = false) String companyId,
            @RequestParam(value = "substationId", required = false) String substationId,
			@RequestParam(value = "feederId", required = false) String feederId,
            @RequestParam(value = "companymeterId", required = false) String companymeterId,
            @RequestParam(value = "serviceTypeCode", required = false) String serviceTypeCode,
            @RequestParam(value = "typeOfSs", required = false) String typeOfSs) {
		
		boolean searchByselect = false;
	      if (select != null && !select.isEmpty())
	    	  searchByselect = true;
    	  System.out.println(select);
    	  
	      {
	      
	      if (select.equals("COMPSERVICE")) {
	            return masterServiceRepository.getCompanyService(serviceId,orgId,companyId,substationId,feederId,companymeterId,serviceTypeCode);
	        }
	      else if (select.equals("ORG")) {
	            return masterServiceRepository.getOrgService(serviceId,orgId,companyId,substationId,feederId,companymeterId,serviceTypeCode);
	      }
	      else if (select.equals("SERVICE")) {
	            return masterServiceRepository.getService(serviceId,orgId,companyId,substationId,feederId,companymeterId,serviceTypeCode);
	      }
	      else if (select.equals("COMPANY")) {
	            return masterServiceRepository.getCompany(serviceId,orgId,companyId,substationId,feederId,companymeterId,serviceTypeCode);
	      }
	      else if (select.equals("METER")) {
	            return masterServiceRepository.getMeter(serviceId,orgId,companyId,substationId,feederId,companymeterId,serviceTypeCode,typeOfSs);
	      }
          return masterServiceRepository.getCompanyService(serviceId,orgId,companyId,substationId,feederId,companymeterId,serviceTypeCode);
	      }
	}
	
}
