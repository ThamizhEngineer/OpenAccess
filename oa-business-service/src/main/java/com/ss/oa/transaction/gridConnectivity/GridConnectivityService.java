package com.ss.oa.transaction.gridConnectivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.common.BaseDaoJdbc;
import com.ss.oa.common.OpenAccessException;
import com.ss.oa.master.company.CompanyService;
import com.ss.oa.master.powerplant.PowerplantService;
import com.ss.oa.master.vo.Company;
import com.ss.oa.master.vo.Generator;
import com.ss.oa.master.vo.Meter;
import com.ss.oa.master.vo.Powerplant;
import com.ss.oa.transaction.vo.ApplicationStatus;
import com.ss.oa.transaction.vo.CaptiveUserContribution;
import com.ss.oa.transaction.vo.DocCheckListItem;
import com.ss.oa.transaction.vo.EquityShareVotingRights;
import com.ss.oa.transaction.vo.GridConnectivity;
import com.ss.oa.transaction.vo.Loan;
import com.ss.oa.transaction.vo.QuantumAllocation;
import com.ss.oa.transaction.vo.Transformer;
import com.ss.oa.master.vo.CompanyShareHolder;


@Scope("prototype")
@Component
public class GridConnectivityService extends BaseDaoJdbc {
	
	
	@Autowired
	PowerplantService powerplantService;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	GridConnectivityDao Dao;
	
	
	String gcId;
	
	public List<GridConnectivity> getAllGcs(Map<String, String> criteria){
		
		return Dao.getAllGcs(criteria);
	}
	
	public GridConnectivity getGcById(String id) {
		
		return Dao.getGcById(id);
	}
	
	public String addGc(GridConnectivity gc) {
		gc.setStatusCode("CREATED");
		gcId = Dao.addGc(gc);
		System.out.println("gcid" + gcId);
		
		if(gc.getGenUnits()!=null) {
			
			for(Generator genUnits :gc.getGenUnits()) {
				genUnits.setGcId(gcId);
	
				Dao.addGenUnits(genUnits);
			}
		}
		if(gc.getTransformers()!=null) {
		
			for(Transformer transformers :gc.getTransformers()) {
				transformers.setGcId(gcId);
			
				Dao.addTransformers(transformers);
			}	
		}
		if(gc.getCaptiveQuantumAllocation()!=null) {
		
			for(QuantumAllocation quantumAllocation :gc.getCaptiveQuantumAllocation()) {
				quantumAllocation.setGcId(gcId);
			
				Dao.addQuantumAllocation(quantumAllocation);
			}	
		}
		if(gc.getApplicationStatus()!=null) {
		
			for(ApplicationStatus applicationStatus :gc.getApplicationStatus()) {
				applicationStatus.setGcId(gcId);
		
				Dao.addApplicationStatus(applicationStatus);
			}	
		}
		if(gc.getIdLoans()!=null) {
		
			for(Loan idLoans :gc.getIdLoans()) {
				idLoans.setGcId(gcId);
		
				Dao.addLoan(idLoans);
			}	
		}
		if(gc.getIdCaptiveUserContributions()!=null) {
		
			for(CaptiveUserContribution captiveUserContribution :gc.getIdCaptiveUserContributions()) {
				captiveUserContribution.setGcId(gcId);
				Dao.addCaptiveUserContribution(captiveUserContribution);
			}	
		}
		if(gc.getIdEquityShareVotingRights()!=null) {
		
			for(EquityShareVotingRights equityShareVotingRights :gc.getIdEquityShareVotingRights()) {
				equityShareVotingRights.setGcId(gcId);
				
				Dao.addEquityShareVotingRights(equityShareVotingRights);
			}
		}
		if(gc.getCheckList()!=null) {
	
			for(DocCheckListItem checkList :gc.getCheckList()) {
				checkList.setGcId(gcId);
		
			Dao.addDocCheckListItem(checkList);
		}	
		}
	
		return gcId;

			
		}
	

	public String updateGc(String id, GridConnectivity gc) {
		System.out.println(id);
		System.out.println(gc);
		gcId = Dao.updateGc(id, gc);
		
		if(gc.getGenUnits()!=null) {
			for(Generator genUnits :gc.getGenUnits()) {
				if(genUnits.getId()!=null) {
					
					System.out.println(genUnits);
					Dao.updateGenUnits( genUnits);
				}else {
					
					genUnits.setGcId(id);
					Dao.addGenUnits(genUnits);					
				}				
			}
		}
		if(gc.getTransformers()!=null) {
			for(Transformer transformers :gc.getTransformers()) {
				if(transformers.getId()!=null) {
					
					System.out.println(transformers);
					Dao.updateTransformers(transformers);
				}else {
					
					transformers.setGcId(id);;
					Dao.addTransformers(transformers);					
				}				
			}
		}
		if(gc.getCaptiveQuantumAllocation()!=null) {
			for(QuantumAllocation quantumAllocation :gc.getCaptiveQuantumAllocation()) {
				if(quantumAllocation.getId()!=null) {
					
					System.out.println(quantumAllocation);
					Dao.updateQuantumAllocation(quantumAllocation);
				}else {
					
					quantumAllocation.setGcId(id);;
					Dao.addQuantumAllocation(quantumAllocation);					
				}				
			}
		}
		if(gc.getApplicationStatus()!=null) {
			for(ApplicationStatus applicationStatus :gc.getApplicationStatus()) {
				if(applicationStatus.getId()!=null) {
					
					System.out.println(applicationStatus);
					Dao.updateApplicationStatus(applicationStatus);
				}else {
					
					applicationStatus.setGcId(id);;
					Dao.addApplicationStatus(applicationStatus);					
				}				
			}
		}
		if(gc.getIdLoans()!=null) {
			for(Loan idLoans :gc.getIdLoans()) {
				if(idLoans.getId()!=null) {
					
					System.out.println(idLoans);
					Dao.updateLoan(idLoans);
				}else {
					
					idLoans.setGcId(id);;
					Dao.addLoan(idLoans);					
				}				
			}
		}
		
		if(gc.getIdCaptiveUserContributions()!=null) {
			for(CaptiveUserContribution captiveUserContribution :gc.getIdCaptiveUserContributions()) {
				if(captiveUserContribution.getId()!=null) {
					
					System.out.println(captiveUserContribution);
					Dao.updateCaptiveUserContribution( captiveUserContribution);
				}else {
					
					captiveUserContribution.setGcId(id);
					Dao.addCaptiveUserContribution(captiveUserContribution);					
				}				
			}
		}
		
		if(gc.getIdEquityShareVotingRights()!=null) {
			for(EquityShareVotingRights equityShareVotingRights :gc.getIdEquityShareVotingRights()) {
				if(equityShareVotingRights.getId()!=null) {
					
					System.out.println(equityShareVotingRights);
					Dao.updateEquityShareVotingRights( equityShareVotingRights);
				}else {
					
					equityShareVotingRights.setGcId(id);
					Dao.addEquityShareVotingRights(equityShareVotingRights);				
				}				
			}
		}
		
		if(gc.getCheckList()!=null) {
			for(DocCheckListItem checkList :gc.getCheckList()) {
				
				if(checkList.getId()!=null) {
					Dao.updateDocCheckListItem(checkList);
				}else {
					 
					checkList.setGcId(id);
					Dao.addDocCheckListItem(checkList);				
				}				
			}
		}
		return gcId;
	}
	
	
	public String completeGridConnectivity( GridConnectivity gridConnectivity) {
		System.out.println(gridConnectivity);
	

		if(gridConnectivity.getStatusCode()!=null&&gridConnectivity.getStatusCode().equals("COMPLETED")) {
			throw new OpenAccessException("Grid Connectivity Completion Failed - cannot complete already Completed Grid Connectivity");	
			
		}else {	
		com.ss.oa.master.vo.CompanyServiceVO service = new  com.ss.oa.master.vo.CompanyServiceVO();
		List<com.ss.oa.master.vo.CompanyServiceVO> serviceList = new 	ArrayList<com.ss.oa.master.vo.CompanyServiceVO>();
		Meter meter = new Meter();
		List<Meter> meterList = new ArrayList<Meter>();
	



		System.out.println(gridConnectivity);
		
		String companyId="";
		String serviceId="";
		String powerplantId="";
		String gcId="";
		
		Company company = new Company();
		
		company.setName(gridConnectivity.getCompanyName());
		company.setCompanyTypeCode("02");
		company.setIsSeller("Y");
		company.setIsBuyer("N");


		serviceId = generateId();
		service.setId(serviceId);
		service.setTypeCode("03");
		service.setSubstationId(gridConnectivity.getSsId());
		service.setSubstationName(gridConnectivity.getSsName());
		service.setVoltageCode(gridConnectivity.getSsVoltageCode());
		service.setVoltageName(gridConnectivity.getSsVoltageName());
		service.setOrgId(gridConnectivity.getOrgId());
		service.setOrgName(gridConnectivity.getOrgName());
		service.setNumber(gridConnectivity.getGenServiceNumber());
		service.setFeederId(gridConnectivity.getFinalFeederId());
		service.setFeederCode(gridConnectivity.getFinalFeederCode());
		service.setCapacity(gridConnectivity.getPlantCapacity());
		service.setEnabled("Y");
		serviceList.add(service);
		meter.setMeterNumber(gridConnectivity.getMeterNumber());
		meter.setMeterMakeCode(gridConnectivity.getMeterMakeCode());
		meter.setAccuracyClassCode(gridConnectivity.getAccuracyClassCode());
		meter.setIsAbtMeter(gridConnectivity.getIsAbtMeter());
		meter.setMultiplicationFactor(gridConnectivity.getMultiplicationFactor());
	    meter.setEnabled("Y");
	    meter.setMeterCt1(gridConnectivity.getMeterCt1());
	    meter.setMeterCt2(gridConnectivity.getMeterCt2());
	    meter.setMeterCt3(gridConnectivity.getMeterCt3());
	    meter.setMeterPt1(gridConnectivity.getMeterPt1());
	    meter.setMeterPt2(gridConnectivity.getMeterPt2());
	    meter.setMeterPt3(gridConnectivity.getMeterPt3());
	    meterList.add(meter);
		company.setServices(serviceList);
	    company.setMeters(meterList);
		List <CompanyShareHolder> CompanyShareHolderList = new ArrayList<CompanyShareHolder>();
		for(QuantumAllocation quantumAllocation:gridConnectivity.getCaptiveQuantumAllocation()) {
		
			CompanyShareHolder CompanyShareHolder =new CompanyShareHolder();

			
			CompanyShareHolder.setShareHolderCompanyId(quantumAllocation.getBuyerCompId());
			CompanyShareHolder.setShareHolderCompanyCode(quantumAllocation.getBuyerCompCode());
			CompanyShareHolder.setShareHolderCompanyName(quantumAllocation.getBuyerCompName());
			CompanyShareHolder.setQuantum(quantumAllocation.getQuantum());
			CompanyShareHolder.setShareHolderCompanyServiceId(quantumAllocation.getBuyerCompServiceId());
			CompanyShareHolder.setShare(quantumAllocation.getSharedPercentage());
			CompanyShareHolder.setCompanyId(companyId);			

			CompanyShareHolderList.add(CompanyShareHolder);
		
	
			

		  

		}
		System.out.println(CompanyShareHolderList);
		
		  company.setShareHolders(CompanyShareHolderList);
		System.out.println(company);
		companyId =companyService.addCompany(company); 
		


		
		
		
		Powerplant powerplant = new Powerplant();
		List<Generator> generatorList = new ArrayList<Generator>();

		powerplant.setSubStationId(gridConnectivity.getSsId());
		powerplant.setOrgId(gridConnectivity.getOrgId());
		powerplant.setOrgName(gridConnectivity.getOrgName());
		powerplant.setLine1(gridConnectivity.getLine1());
		powerplant.setCity(gridConnectivity.getCity());
		powerplant.setStateCode(gridConnectivity.getState());
		powerplant.setPinCode(gridConnectivity.getPinCode());
		powerplant.setVillage(gridConnectivity.getVillage());
		powerplant.setTown(gridConnectivity.getTown());
		powerplant.setTalukCode(gridConnectivity.getPlTalukCode());
		powerplant.setDistrictCode(gridConnectivity.getPlDistrictCode());
		powerplant.setPlantTypeCode(gridConnectivity.getPlantTypeCode());
		powerplant.setPlantTypeName(gridConnectivity.getPlantTypeName());
		powerplant.setServiceId(serviceId);
		powerplant.setFuelTypeCode(gridConnectivity.getFuelTypeCode());
		powerplant.setFuelTypeName(gridConnectivity.getFuelTypeName());
		powerplant.setPlSfNo(gridConnectivity.getPlSfNo());
		powerplant.setPlVillage(gridConnectivity.getPlVillage());
		powerplant.setPlTown(gridConnectivity.getPlTown());
		powerplant.setPlVillage(gridConnectivity.getPlVillage());
		powerplant.setPlDistrictCode(gridConnectivity.getPlDistrictCode());
		powerplant.setPlDistrictName(gridConnectivity.getPlDistrictName());
		powerplant.setPlTalukCode(gridConnectivity.getPlTalukCode());
		powerplant.setPlTalukName(gridConnectivity.getPlTalukName());
		powerplant.setWindPassCode(gridConnectivity.getPlWindPassCode());
		powerplant.setWindPassName(gridConnectivity.getPlWindPassName());
		powerplant.setWindZoneAreaCode(gridConnectivity.getPlWindZoneArea());
		powerplant.setCommissionDate(gridConnectivity.getFinalCod());
		
		
		for(Generator genUnit:gridConnectivity.getGenUnits()) {
		Generator generator =  new Generator();	

		generator.setMakeCode(genUnit.getMakeCode());
		generator.setGcId(genUnit.getGcId());
		generator.setCapacity(genUnit.getCapacity());
		generator.setMakeCode(genUnit.getMakeCode());
		generator.setSerialNumber(genUnit.getSerialNumber());
		generator.setRotorDia(genUnit.getRotorDia());
		generator.setHubHeight(genUnit.getHubHeight());
		generator.setMwRating(genUnit.getMwRating());
		generator.setMvRating(genUnit.getMvRating());
		generator.setKvRating(genUnit.getKvRating());
		generator.setDampingFactor(genUnit.getDampingFactor());
		generator.setAmateurResistance(genUnit.getAmateurResistance());
		generator.setDirectAssistanceReactance(genUnit.getDirectAssistanceReactance());
		generator.setNegativeSequenceReactance(genUnit.getNegativeSequenceReactance());
		generator.setZeroSequenceReactance(genUnit.getZeroSequenceReactance());
		generator.setDirectAssistanceReactance(genUnit.getDirectAxisSubTransientReactance());
		generator.setDirectAxisTransientReactance(genUnit.getDirectAxisTransientReactance());
		generator.setDirectAxisSubTransientReactance(genUnit.getDirectAxisSubTransientReactance());
		generator.setInertia(genUnit.getInertia());
		generator.setWindingConnection(genUnit.getWindingConnection());
		generator.setMassNumber(genUnit.getMassNumber());
		generator.setStiffnessCoefficient(genUnit.getStiffnessCoefficient());
		generator.setFinalCod(genUnit.getFinalCod());
		generator.setFinalCopd(genUnit.getFinalCopd());
		
		generatorList.add(generator);					
		powerplant.setGenerators(generatorList);
			
		
		}
		
		System.out.println(powerplant);
		powerplantId = powerplantService.addPowerplant(powerplant);
		System.out.println(powerplantId);
		if(powerplantId!=null) {
			gridConnectivity.setStatusCode("COMPLETED");
			gcId =updateGc(gridConnectivity.getId(), gridConnectivity);
			
		}
		
		
		
		
		return gcId;
	}
		
		
		
	}


}
