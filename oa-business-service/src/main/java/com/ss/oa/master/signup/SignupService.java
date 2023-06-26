package com.ss.oa.master.signup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import com.ss.oa.common.OpenAccessException;
import com.ss.oa.master.vo.Signup;
import com.ss.oa.master.vo.TradeRelationship;
import com.ss.oa.master.vo.Generator;
import com.ss.oa.master.company.CompanyService;
import com.ss.oa.master.powerplant.PowerplantService;
import com.ss.oa.master.traderelationship.TradeRelationshipService;
import com.ss.oa.master.user.UserService;


@Component
@Scope("prototype")
public class SignupService {
	@Autowired
	SignupDao dao;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	TradeRelationshipService tradeRelationshipService;
	
	@Autowired
	PowerplantService powerplantService;
	
	@Autowired
	UserService userService;
	
	
	
	
	@Resource
	private DataSource dataSource;

	private SimpleJdbcCall functionConfirmSignup;


	
	public SignupService() {
		super();
	}
	
	public List<Signup> getAllSignup(Map<String,String> criteria)
	{
		return dao.getAllSignup(criteria);
	}
	
	public Signup getSignupById(String signupId)
	{
		return dao.getSignupById(signupId);
	}
	
	private Signup genUnits(Signup signup) {
		int i=1;
		for(Generator generator:signup.getGenUnits()) {
		
			if(i==1) {
			signup.setGuModel1(generator.getMakeCode());
			signup.setGuCapacity1(generator.getCapacity());
			signup.setNoOfGu1(generator.getNoOfUnits());
			}
			if(i==2) {
				signup.setGuModel2(generator.getMakeCode());
				signup.setGuCapacity2(generator.getCapacity());
				signup.setNoOfGu2(generator.getNoOfUnits());
				}
			if(i==3) {
				signup.setGuModel3(generator.getMakeCode());
				signup.setGuCapacity3(generator.getCapacity());
				signup.setNoOfGu3(generator.getNoOfUnits());
				}
			if(i==4) {
				signup.setGuModel4(generator.getMakeCode());
				signup.setGuCapacity4(generator.getCapacity());
				signup.setNoOfGu4(generator.getNoOfUnits());
				}
			if(i==5) {
				signup.setGuModel5(generator.getMakeCode());
				signup.setGuCapacity5(generator.getCapacity());
				signup.setNoOfGu5(generator.getNoOfUnits());
				}
			if(i==6) {
				signup.setGuModel6(generator.getMakeCode());
				signup.setGuCapacity6(generator.getCapacity());
				signup.setNoOfGu6(generator.getNoOfUnits());
				}
			i++;
		}

	
		return signup;
	}
	
	public String addSignup(Signup signup)
	{
		String companyId  ="";
		signup = genUnits(signup);
	
		
			companyId = dao.addSignup(signup);
		if(signup.getSignupTradeRelationships()!=null) {
			for(TradeRelationship tradeRelationship:signup.getSignupTradeRelationships()) {
				tradeRelationship.setSignupId(companyId);
				String tradeRelationshipId = dao.addSignupTradeRelationship(tradeRelationship);
				//System.out.println("Signup tradeRelationshipId-"+tradeRelationshipId);
			}
		}
		return companyId;
	}
	public String updateSignup(String signupId,Signup signup)
	{	signup = genUnits(signup);
		String result = "";
		if(signup.getIsComplete()!=null && signup.getIsComplete().equals("Y")) {			
			throw new OpenAccessException("Signup Update Failed - cannot update completed signup");			
		} else {			
			result= dao.updateSignup(signupId, signup);
			for(TradeRelationship tradeRelationship :signup.getSignupTradeRelationships()){
				
				if(tradeRelationship.getId()!=null && tradeRelationship.getId().trim().length()>0) {
					dao.updateSignupTradeRelationship(tradeRelationship);			
					
				}else {	
					
						tradeRelationship.setSignupId(signupId);
						String tradeRelationshipId=dao.addSignupTradeRelationship(tradeRelationship);	
						//System.out.println("Signup tradeRelationshipId-"+tradeRelationshipId);					 
					
				}
			}
		}		
	
		return result;

	}
	
//	public String completeSignup(String signupId,Signup signup) {
//		
//		if(signup==null){
//			signup = dao.getSignupById(signupId);
//		}
//		
//		if(signup.getIsComplete()!=null && signup.getIsComplete().equals("Y")) {	
//			
//			throw new OpenAccessException("Signup Update Failed - cannot update completed signup");	
//			
//		} else {	
//			System.out.println("In signup complete");
//			com.ss.oa.master.vo.CompanyServiceVO service = new  com.ss.oa.master.vo.CompanyServiceVO();
//			List<com.ss.oa.master.vo.CompanyServiceVO> serviceList = new 	ArrayList<com.ss.oa.master.vo.CompanyServiceVO>();
//			List<com.ss.oa.master.vo.Meter> meterList = new ArrayList<com.ss.oa.master.vo.Meter>();
//			String companyId="";
//			String serviceId="";
//			String powerplantId="";
//			Company company = new Company();		
//			company.setName(signup.getCompanyName());
//			company.setCompanyTypeCode("01");
//			company.setRegistrationNo(signup.getRegistrationNumber());
//			company.setRegistrationDate(signup.getRegistrationDate());
//			company.setCobDate(null);
//			company.setIncorpPlace(null);
//			company.setIsCaptive(signup.getIsCaptive());
//			company.setCaptivePurpose(null);
//			company.setPan(null);
//			company.setTan(null);
//			company.setCst(null);
//			company.setGst(null);
//			company.setEnabled("Y");
			
			
			//please note that Signup.SignupPurposeTypes.Buyer is not same as CompanyServiceVO.ServiceTypes.Buyer
			// also interestingly Signup.SignupPurposeTypes.Buyer is not same as Signup.SignupPurposeTypes.Buyer.toString()
			
//			if(signup.getPurposeCode().equals(Signup.SignupPurposeTypes.Buyer.toString())) {
//				service.setTypeCode(CompanyServiceVO.ServiceTypes.Buyer.toString());
//				company.setIsBuyer("Y");
//				company.setIsSeller("N");
//			}else if(signup.getPurposeCode().equals(Signup.SignupPurposeTypes.Seller.toString())) {
//				service.setTypeCode(CompanyServiceVO.ServiceTypes.Seller.toString());
//				company.setIsBuyer("N");
//				company.setIsSeller("Y");
//			}
//			
//
//			
//			serviceId = dao.generateId();
//			service.setId(serviceId);
//			service.setNumber(signup.getHtscNumber());
//			service.setOrgId(signup.getOrgId());
//			service.setSubstationId(signup.getSubstationId());
//			service.setFeederId(signup.getFeederId());
//			service.setCapacity(signup.getTotalCapacity());
//			service.setTariff(signup.getTariff());
//			service.setVoltageCode(signup.getVoltageCode());
//			service.setBankingServiceId(dao.generateId());
//			service.setUnadjustedServiceId(dao.generateId());
//			service.setTlServiceId(dao.generateId());
//			service.setDlServiceId(dao.generateId());
//			service.setBankingServiceNumber(dao.generateCode(CompanyServiceVO.class.getSimpleName(), "01"));
//			service.setTlServiceNumber(dao.generateCode(CompanyServiceVO.class.getSimpleName(), "04"));
//			service.setDlServiceNumber(dao.generateCode(CompanyServiceVO.class.getSimpleName(), "05"));
//			service.setUnadjustedServiceNumber(dao.generateCode(CompanyServiceVO.class.getSimpleName(), "06"));
//			service.setEnabled("Y");
//			serviceList.add(service);
//			
//			if(!signup.getPurposeCode().equals("01")) {
//				Meter meter = new Meter();
//				meter.setMeterNumber(signup.getMeterNumber());
//				meter.setMeterMakeCode(signup.getMeterMakeCode());
//				meter.setAccuracyClassCode(signup.getAccuracyClassCode());
//				meter.setIsAbtMeter(signup.getIsAbtMeter());
//				meter.setMultiplicationFactor(signup.getMultiplicationFactor());
//			    meter.setEnabled("Y");
//				meterList.add(meter);
//			
//			}
//			
//			company.setServices(serviceList);
//			company.setMeters(meterList);
//			System.out.println(company);
//			companyId =companyService.addCompany(company); 
//			
//			if(signup.getSignupTradeRelationships()!=null ){
//				for (TradeRelationship tradeRelationship : signup.getSignupTradeRelationships()) {
//					tradeRelationship.setId(null);
//					tradeRelationship.setSellerCompanyId(companyId);
//					tradeRelationship.setSellerCompServiceId(serviceId);
//					tradeRelationshipService.addTradeRelationship(tradeRelationship);
//				}
//			}
//			
//			if(!signup.getPurposeCode().equals("01")) {
//				Powerplant powerplant = new Powerplant();
//		
//				List<Generator> generatorList = new ArrayList<Generator>();
//				powerplant.setPlantTypeCode(signup.getPowerPlantTypeCode());
//				powerplant.setFuelTypeCode("02");
//				powerplant.setOrgId(signup.getOrgId());
//				powerplant.setSubStationId(signup.getSubstationId());
//				powerplant.setCompanyId(signup.getCompanyCode());
//				powerplant.setServiceId(serviceId);
//				powerplant.setCommissionDate(signup.getDateOfCommission());
//				powerplant.setPurpose(signup.getPurposeCode());
//				powerplant.setTotalCapacity(signup.getTotalCapacity());
//				powerplant.setWindPassName(signup.getWindPassName());
//				powerplant.setLine1(signup.getAddressLine());
//				powerplant.setVillage(signup.getVillage());
//				powerplant.setTalukCode(signup.getTalukCode());
//				powerplant.setCity(signup.getCity());
//				powerplant.setDistrictCode(signup.getDistrictCode());
//				powerplant.setStateCode(signup.getStateCode());
//				powerplant.setApplicationDate(signup.getApplicationDate());
//				powerplant.setApprovalDate(signup.getDateOfApproval());
//				powerplant.setEnabled("Y");
//				if(signup.getNoOfGenerators()==null||signup.getNoOfGenerators().equals("0")) {
//					signup.setNoOfGenerators("1");					
//				}		
//				  
//				Integer capacity = 200 ;
				//Integer capacity = Integer.parseInt(signup.getGeneratorCapacity())*Integer.parseInt(signup.getNoOfGenerators());
//				powerplant.setTotalCapacity(capacity.toString());
//			
//					for(Generator genUnit:signup.getGenUnits()) {
//						Generator generator =  new Generator();					
//						generator.setMakeCode(genUnit.getMakeCode());
//						generator.setCapacity(genUnit.getCapacity());
//						generator.setNoOfUnits(genUnit.getNoOfUnits());					
//						generatorList.add(generator);					
//						powerplant.setGenerators(generatorList);
//					}
//					
//					
//				
//				
//				System.out.println(powerplant);
//				powerplantId = powerplantService.addPowerplant(powerplant);
//				System.out.println(powerplantId);
//			
//				
//			}
//			
//			if(signup.getUserId()!=null) {
//			Master user = new Master();
//			user.setFirstName(signup.getUserName());
//			user.setType("E");
//			user.setEnabled("Y");
//			user.setOrgId(signup.getOrgId());
//			user.setCompanyId(companyId);
//		
//			userService.addUser(user);
//			
//			UserProfile userProfile = new UserProfile();
//			userProfile.setFirstName(signup.getUserName());
//			userProfile.setUserType("E");
//			userProfile.setUsername(signup.getUserName());
//			userProfile.setPassword(signup.getPassword());
//			userProfile.setSystemKey(companyId);
//			userProfile.setSystemRefKey("OA");
//		
//			tokenService.addUserProfile(userProfile);
//			}
//			if(signup.getIsComplete()!=null && signup.getIsComplete().equals("N")) {
//				signup.setIsComplete("Y");
//			}
//			
//			return dao.updateSignup(signupId, signup);
//
//				
//		}
//		 		
//	}
	

	
	public String completeSignup(String signupId,Signup signup) {
		
		
		System.out.println("In complete signup");
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		functionConfirmSignup = new SimpleJdbcCall(jdbcTemplate).withFunctionName("CONFIRM_SIGNUP");		
		System.out.println("functionConfirmSignup"+functionConfirmSignup);
		SqlParameterSource in = new MapSqlParameterSource().addValue("V_SIGNUP_ID", signupId);
		
		return functionConfirmSignup.executeFunction(String.class, in);
	
		 		
	}
	public String completeManySignups(String remarks) {
		String result = "SUCCESS";
		List<String> results = new ArrayList<String>();
		try{
			
			Map<String,String> criteria =new HashMap<String,String>();
			if(remarks!=null) {
				criteria.put("remarks",remarks);
				criteria.put("isComplete","N");
			}
			else{
				throw new OpenAccessException("Please share remarks. Only Signups with those remarks will be completed!");
			}
			List<Signup> signups = this.getAllSignup(criteria);
			int i=0;
			for (Signup signup : signups) { 
				try {
					this.completeSignup(signup.getId(), null);	
				} catch (Exception e) {
					e.printStackTrace();
					results.add(signup.getId()+"-"+e.getMessage());
				}
				if(i++ > 200) {
                    break;
                }; 
			}
		} catch (Exception e) {
			e.printStackTrace();
			results.add(e.getMessage());
		}
		if(!results.isEmpty()){
			result = "FAILURE - "+results;
		}
		
		
		return result;
	}

}
