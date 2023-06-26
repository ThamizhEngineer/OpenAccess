package com.ss.oa.transaction.ipaa;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ss.oa.master.company.ServiceRepository;
import com.ss.oa.master.traderelationship.TradeRelationshipService;
import com.ss.oa.master.vo.TradeRelationship;
import com.ss.oa.model.transaction.IexNoc;
import com.ss.oa.model.transaction.IpaStandingClearance;
import com.ss.oa.transaction.iexnoc.IexNocRepository;
import com.ss.oa.transaction.ipaastandingclearance.IpaaStandingClearanceRepository;
import com.ss.oa.transaction.standingclearence.StandingClearenceService;
import com.ss.oa.transaction.vo.Ipaa;
import com.ss.oa.transaction.vo.IpaaLine;
import com.ss.oa.transaction.vo.StandingClearence;
import com.ss.oashared.common.CommonUtils;


@Service
public class IpaaBusinessHelper {
	@Autowired
	IexNocRepository iexNocRepository;
	
	@Autowired
	IpaaStandingClearanceRepository ipaScRepo;
	
	@Autowired
	StandingClearenceService standingClearenceService;
	
	@Autowired
	TradeRelationshipService tradeRelationshipService;
	
	@Autowired
	private CommonUtils commonUtils;
	
	@Autowired
	IpaaDao ipadaoo;
	
	@Autowired
	ServiceRepository serviceRepo;

	String ipaId="";
	String ipaaId="";
	String buyerCompServId="";


	
	public String addIpaSc(IpaStandingClearance ipaStandingClearance) {
		
		ipaaId=ipaStandingClearance.getIpaId();
		Ipaa ipa= ipadaoo.getIpaaById(ipaaId);
		for(IpaaLine ipaline:ipa.getIpaaLines()){
			buyerCompServId =ipaline.getBuyerCompServiceId();
			
		 ipaStandingClearance.setId(commonUtils.generateId());
		 ipaStandingClearance.setBuyerCompServId(buyerCompServId);
		 System.out.println("addIpa-"+ipaStandingClearance);
		 ipaScRepo.save(ipaStandingClearance);
		 ipaId=ipaStandingClearance.getId();
		 System.out.println("Ipa-id"+ipaId);
		 procesStandingClearance(ipaId);
		}
		 return ipaId;
	}
	
	public String procesStandingClearance(String ipaScId) {
		
		Optional<IpaStandingClearance> ipaStandingClearanceipa = ipaScRepo.findById(ipaScId);
		System.out.println("ipaStandingClearanceipa - In process - "+ipaStandingClearanceipa);
		IpaStandingClearance updateIpasc=ipaScRepo.findById(ipaScId).get();
		System.out.println("updateIpasc - In process - "+updateIpasc);

		//getting IPA by ID
		Ipaa getIpaa= ipadaoo.getIpaaById(ipaStandingClearanceipa.get().getIpaId());
		System.out.println("getIpaa - In process - "+getIpaa);

		
		//getting Buyer company service by id
		Optional<com.ss.oa.model.master.Service> getBuyerService = serviceRepo.findById(ipaStandingClearanceipa.get().getBuyerCompServId());
		
		//Converting date
        LocalDate geFromdate = ipaStandingClearanceipa.get().getFromDt();
        LocalDate getTodate = ipaStandingClearanceipa.get().getToDt();
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("uuuu/MM/d");
        String fromtText = geFromdate.format(formatters);
        String toText = getTodate.format(formatters);
        
        //Logic Starts
		if(ipaStandingClearanceipa.get().getIexNocId() == null || ipaStandingClearanceipa.get().getIexNocId().isEmpty()) {
			IexNoc iexNoc = new IexNoc();
			
			
			//Create IEX-NOC
			iexNoc.setId(commonUtils.generateId());
			iexNoc.setTypeCode("IEX-CONSUMER-NOC");
			iexNoc.setStatusCode("CREATED");
			iexNoc.setBuyerCompanyServiceId(ipaStandingClearanceipa.get().getBuyerCompServId());
			iexNoc.setProposedCapacity(ipaStandingClearanceipa.get().getQuantum());
			iexNoc.setApprovedCapacity(ipaStandingClearanceipa.get().getQuantum());
			iexNocRepository.save(iexNoc);
			
			//Update IPA-SC
			updateIpasc.setId(ipaScId);
			updateIpasc.setIexNocId(iexNoc.getId());
			updateIpasc.setNocIexStatus("APPLIED");
			ipaScRepo.save(updateIpasc);
			
			
		}
		else if(ipaStandingClearanceipa.get().getIexNocId() != null && (ipaStandingClearanceipa.get().getScId() == null || ipaStandingClearanceipa.get().getScId().isEmpty())) {
			StandingClearence sc = new StandingClearence(); 
            
			//Create IPA-SC
			sc.setStatusCode("CREATED");
			sc.setBuyerCompanyServiceId(ipaStandingClearanceipa.get().getBuyerCompServId());
			sc.setFromDate(fromtText);
			sc.setToDate(toText);
			standingClearenceService.addStandingClearence(sc);
			
			//Update IPA-SC
			updateIpasc.setId(ipaScId);
			updateIpasc.setScId(sc.getId());
			updateIpasc.setNocIexStatus("APPROVED");
			updateIpasc.setScStatus("APPLIED");
			ipaScRepo.save(updateIpasc);
		}
		
		else if(ipaStandingClearanceipa.get().getScId() !=null) {
			TradeRelationship tr = new TradeRelationship();
			
			//Add tradeRealtionship
			tr.setId(commonUtils.generateId());
			tr.setBuyerCompServiceId(ipaStandingClearanceipa.get().getBuyerCompServId());
			tr.setBuyerCompanyId(getBuyerService.get().getCompanyId());
			tr.setSellerCompServiceId(getIpaa.getSellerCompanyServiceId());
			tr.setSellerCompanyId("IEX");
			tr.setFromDate(fromtText);
			tr.setToDate(toText);
			tr.setIsCaptive(getIpaa.getIsCaptive());
			tr.setStatusCode("COMPLETED");
			tr.setQuantum(ipaStandingClearanceipa.get().getQuantum());
			tradeRelationshipService.addTradeRelationship(tr);
			
			//Update IPA-SC
			updateIpasc.setScStatus("APPROVED");
			ipaScRepo.save(updateIpasc);
		}
				
		
		return ipaScId;
	}
	
}
