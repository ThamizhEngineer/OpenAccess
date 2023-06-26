package com.ss.oa.master.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ss.oa.common.BaseDaoJdbc;

import com.ss.oa.master.vo.Company;
import com.ss.oa.master.vo.CompanyServiceVO;
import com.ss.oa.master.vo.Meter;
import com.ss.oa.master.vo.CompanyShareHolder;


@Component
@Scope("prototype")
public class CompanyDaoImpl extends BaseDaoJdbc implements CompanyDao {
	@Resource
	private JdbcOperations jdbcOperations;
	 
	@Override
	public List<Company> getAllCompanies(Map<String, String> criteria) {
		
		String companyQuery="select c.id,c.code,c.name,c.COMPANY_TYPE_CODE company_type_code, typecodes.value_Desc company_type_name,c.registration_no,c.registration_date,c.cob_date,c.incorp_place,c.is_captive,c.captive_purpose,c.pan,c.tan,c.cst,c.gst,c.enabled, c.remarks, \n" + 
				"sv.service_type_codes,  REPLACE(REPLACE(REPLACE(sv.service_type_codes,'01', 'BankingService'),'02','BuyerService'),'03','SellerService') Service_type_names, banking_service_id, banking_service_number,unadjusted_service_id, unadjusted_service_number,c.IS_BUYER,c.IS_SELLER from m_company c              \n" + 
				"left join v_codes typecodes on c.COMPANY_TYPE_CODE= typecodes.Value_Code AND  typecodes.list_code = 'COMPANY_TYPE_CODE'\n" + 
				"LEFT JOIN (SELECT M_COMPANY_ID , LISTAGG(COMP_SER_TYPE_CODE, ',') WITHIN GROUP (ORDER BY COMP_SER_TYPE_CODE)   AS service_type_codes FROM   M_COMPANY_service GROUP BY M_COMPANY_ID) sv ON c.id=sv.M_COMPANY_ID where 1=1 ";
		
		if(!criteria.isEmpty())
		{
			if(criteria.get("company_code")!=null){
				companyQuery += "and upper(c.code) = upper('"+criteria.get("company_code")+"') ";
			}
			if(criteria.get("company_name")!=null){
				companyQuery += "and upper(c.name) like upper('%"+criteria.get("company_name")+"%')";
			}
			if(criteria.get("service_associated")!=null){
				companyQuery += "and upper(sv.service_type_codes) like upper('%"+criteria.get("service_associated")+"%')";
			}
			if(criteria.get("enabled")!=null){
				companyQuery += "and upper(c.enabled) like upper('%"+criteria.get("enabled")+"%') ";
			}
			if(criteria.get("is-buyer")!=null){
				companyQuery += "and upper(c.IS_BUYER) like upper('%"+criteria.get("is-buyer")+"%') ";
			}
			if(criteria.get("is-seller")!=null){
				companyQuery += "and upper(c.IS_SELLER) like upper('%"+criteria.get("is-seller")+"%') ";
			}
			
		}
		System.out.println(companyQuery);
			return (ArrayList<Company>) jdbcOperations.query(companyQuery,new CompanyMapper());
	}

	@Override
	public Company getCompanyById(String id) {
			
		Company company = new Company();
	
		List<CompanyServiceVO> companyServiceVO =new ArrayList<CompanyServiceVO>();
		List<Meter> meter =new ArrayList<Meter>();
		List<CompanyShareHolder> companyShareHolder =  new ArrayList<CompanyShareHolder>();

		String companyQuery="select c.id,c.code,c.name,c.COMPANY_TYPE_CODE company_type_code, typecodes.value_Desc company_type_name,c.registration_no,c.registration_date,c.cob_date,c.incorp_place,c.is_captive,c.captive_purpose,c.pan,c.tan,c.cst,c.gst, c.cin, c.llpin,c.enabled, c.remarks, \n" + 
				"sv.service_type_codes,  REPLACE(REPLACE(REPLACE(sv.service_type_codes,'01', 'BankingService'),'02','BuyerService'),'03','SellerService') Service_type_names, banking_service_id, banking_service_number,unadjusted_service_id, unadjusted_service_number,c.IS_BUYER,c.IS_SELLER   from m_company c              \n" + 
				"left join v_codes typecodes on c.COMPANY_TYPE_CODE= typecodes.Value_Code AND  typecodes.list_code = 'COMPANY_TYPE_CODE'\n" + 
				"LEFT JOIN (SELECT M_COMPANY_ID , LISTAGG(COMP_SER_TYPE_CODE, ',') WITHIN GROUP (ORDER BY COMP_SER_TYPE_CODE)   AS service_type_codes FROM   M_COMPANY_service GROUP BY M_COMPANY_ID) sv ON c.id=sv.M_COMPANY_ID";
	
		String sql1 ="select companyservice.id,companyservice.COMP_SER_TYPE_CODE, typecodes.value_desc as COMP_SER_TYPE_NAME,companyservice.\"number\",companyservice.m_company_id,company.code as M_COMPANY_CODE,company.name AS M_COMPANY_NAME,companyservice.m_org_id, \n" + 
				"org.code as M_ORG_CODE, org.name as M_ORG_NAME,companyservice.capacity,companyservice.m_substation_id,substation.code as M_SUBSTATION_CODE,substation.name as M_SUBSTATION_NAME,companyservice.m_feeder_id,feeder.code as M_FEEDER_CODE,feeder.name as M_FEEDER_NAME,\n" + 
				"companyservice.ref_number,companyservice.voltage_code,voltagecodes.VALUE_DESC as VOLTAGE_NAME,companyservice.FUEL_TYPE_CODE,fueltypecode.VALUE_DESC as FUEL_TYPE_NAME,companyservice.tariff,companyservice.enabled, \n" + 
				"companyservice.TYPE,companyservice.BANKING_SERVICE_ID,companyservice.BANKING_SERVICE_NUMBER,companyservice.TL_SERVICE_ID,companyservice.TL_SERVICE_NUMBER, \n" + 
				"companyservice.DL_SERVICE_ID,companyservice.DL_SERVICE_NUMBER,companyservice.UNADJUSTED_SERVICE_ID,companyservice.UNADJUSTED_SERVICE_NUMBER  \n" + 
				"from m_company_service companyservice  \n" + 
				"left join m_company company on company.id =companyservice.M_COMPANY_ID  \n" + 
				"left join m_org org on org.id=companyservice.M_ORG_ID  \n" + 
				"left join m_substation substation on substation.id = companyservice.M_SUBSTATION_ID   \n" + 
				"left join m_feeder feeder on feeder.id = companyservice.M_FEEDER_ID  \n" + 
				"left join v_codes typecodes on companyservice.COMP_SER_TYPE_CODE = typecodes.value_code and typecodes.list_code='SERVICE_TYPE_CODE'\n" + 
				"left join v_codes voltagecodes on companyservice.VOLTAGE_Code= voltagecodes.Value_Code AND  voltagecodes.list_code = 'VOLTAGE_CODE' \n" + 
				"left join v_codes fueltypecode on companyservice.FUEL_TYPE_CODE= fueltypecode.Value_Code AND  fueltypecode.list_code = 'FUEL_TYPE_CODE' where m_company_id = ? ";
		
		company =jdbcOperations.queryForObject(companyQuery+" where id= ? ",new Object[]{id},new CompanyMapper());
		companyServiceVO =jdbcOperations.query(sql1,new Object[]{id},new CompanyServiceMapper());
		
	
		String sql2 ="select meter.id,meter.m_company_service_id,companyservice.TYPE as M_COMPANY_SERVICE_TYPE_CODE,companyservicecode.VALUE_DESC as M_COMPANY_SERVICE_TYPE_NAME,companyservice.\"number\" as M_COMPANY_SERVICE_NUMBER,company.id as M_COMPANY_ID,company.name as M_COMPANY_NAME,meter.meter_number,meter.meter_make_code,metermakecode.value_desc as METER_MAKE_NAME,\n" + 
				"meter.accuracy_class_code,accuracyclasscode.value_desc as ACCURACY_CLASS_NAME,meter.is_abtmeter,meter.mf,meter.METER_CT1,meter.METER_CT2,meter.METER_CT3,meter.METER_PT1,meter.METER_PT2,meter.METER_PT3,meter.MODEM_NUMBER\n" + 
				"from m_company_meter meter\n" + 
				"left join v_codes metermakecode on meter.meter_make_code = metermakecode.value_code and  metermakecode.list_code='METER_MAKE_CODE'\n" + 
				"left join v_codes accuracyclasscode on meter.accuracy_class_code =accuracyclasscode.value_code and accuracyclasscode.list_code='ACCURACY_CLASS_TYPE_CODE' \n" + 
				"inner join m_company_service companyservice on meter.m_company_service_id=companyservice.id\n" + 
				"left join v_codes companyservicecode on companyservice.TYPE= companyservicecode.value_code and  companyservicecode.list_code='SERVICE_TYPE_CODE'\n" + 
				"inner join m_company company on company.id=companyservice.m_company_id where company.id=?";

		meter =jdbcOperations.query(sql2,new Object[]{id},new MeterMapper());
		
		String sql3="select shareholder.id,shareholder.m_company_id,company.code as M_COMPANY_CODE,company.name as M_COMPANY_NAME ,\n" + 
				"shareholder.M_SHAREHOLDER_COMPANY_ID,sharecompany.code as M_SHAREHOLDER_COMPANY_CODE,sharecompany.name as M_SHAREHOLDER_COMPANY_NAME,\n" + 
				"shareholder.\"share\",shareholder.measure,shareholder.enabled,shareholder.QUANTUM,shareholder.M_SHAREHOLDER_COMP_SERV_ID\n" + 
				"from m_company_shareholder  shareholder \n" + 
				"left join m_company company on shareholder.m_company_id=company.id \n" + 
				"left join m_company sharecompany on shareholder.M_SHAREHOLDER_COMPANY_ID =sharecompany.id where shareholder.m_company_id =?";
		companyShareHolder=jdbcOperations.query(sql3,new Object[] {id},new CompanyShareHolderMapper());
		
		company.setServices(companyServiceVO);
		company.setMeters(meter);
		company.setShareHolders(companyShareHolder);
	    return company;
	}

	@Override
	public String addCompany(Company company) {	
		
		String result = "";	
		try {
			//generate primary key
			company.setId(generateId());
			if(company.getCode()== null || company.getCode().isEmpty()){
				company.setCode(generateCode(Company.class.getSimpleName(),""));
			}
			System.out.println("In add company");
			//insert record
			String sql1="insert into m_Company(code,name,COMPANY_TYPE_CODE,registration_no,registration_date,cob_date,incorp_place,is_captive,captive_purpose,pan,tan,cst,gst,cin,llpin,enabled,remarks, banking_service_id, banking_service_number,unadjusted_service_id, unadjusted_service_number,IS_BUYER,IS_SELLER, id) values(?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			if(jdbcOperations.update(sql1, setValues(company)) > 0){
				result = company.getId();	
				
			}
			else{
				result =  "FAILURE";
			}
		} catch (Exception e) {
			result =  "FAILURE";
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public String updateCompany(String id,Company company) {
		String result = "";
		company.setId(id);		
		try {
			
			String sql="update m_company set code=?,name=?,COMPANY_TYPE_CODE=?,registration_no=?,registration_date=TO_DATE(?,'yyyy-mm-dd'),cob_date=TO_DATE(?,'yyyy-mm-dd'),incorp_place=?,is_captive=?,captive_purpose=?,pan=?,tan=?,cst=?,gst=?,cin=?,llpin=?,enabled=?,remarks=?,banking_service_id=?,banking_service_number=?,unadjusted_service_id=?,unadjusted_service_number=?,IS_BUYER=?,IS_SELLER=? where id=?";
						
			if(jdbcOperations.update(sql,setValues(company)) > 0){
				result =company.getId();
			}
			else{
				System.out.println("hi");
				result =  "FAILURE";
			}
		} catch (Exception e) {
			result =  "FAILURE";
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String deleteCompany(String id) {
		String result = "";
		try {
			String sql="delete from m_company where id=?";
			if(jdbcOperations.update(sql,new Object[]{id})>0){
				result =  id;
			}
			else{
				result =  "FAILURE";
			}
		} catch (Exception e) {
			result =  "FAILURE";
			e.printStackTrace();
		}
		return result;
	}
	
	public String addCompanyService(CompanyServiceVO companyServiceVO) {
		String result = "";
//		CompanyServiceVO bankingService = new CompanyServiceVO();
		 
		try {
			//create bankingService object
			//generate serviceId
			//set service type as banking
			//generate service code with param as banking
			//set company id
			//set org id
			//set enabled
			//insert a bankingService
			
//			bankingService.setId(generateId());			
//			bankingService.setTypeCode("01");
//			bankingService.setCode(generateCode(companyServiceVO.getClass().getSimpleName(), "01"));
//			bankingService.setCompanyId(companyServiceVO.getCompanyId());
//			bankingService.setOrgId(companyServiceVO.getOrgId());
//			bankingService.setEnabled(companyServiceVO.getEnabled());
//			
//			System.out.println(bankingService);
//			
//			String sql="insert into m_company_service(COMP_SER_TYPE_CODE,\"number\",m_company_id,m_org_id,capacity,m_substation_id,m_feeder_id,ref_number,voltage_code,tariff,enabled,id) values(?,?,?,?,?,?,?,?,?,?,?,?)";		
//			if(jdbcOperations.update(sql, setCompanyServiceValues(bankingService)) > 0){
//				result = bankingService.getId();				
//			}
//			else{
//				result =  "FAILURE";
//			}
			if(companyServiceVO.getId()==null || companyServiceVO.getId().trim().isEmpty()){
				companyServiceVO.setId(generateId());
			}
			companyServiceVO.setCode(generateCode(companyServiceVO.getClass().getSimpleName(), companyServiceVO.getTypeCode()));
			
			//insert record
		
			
			String sql1="insert into m_company_service(COMP_SER_TYPE_CODE,\"number\",m_company_id,m_org_id,capacity,m_substation_id,m_feeder_id,ref_number,voltage_code,tariff,enabled,TYPE,BANKING_SERVICE_ID,BANKING_SERVICE_NUMBER,TL_SERVICE_ID,TL_SERVICE_NUMBER,DL_SERVICE_ID,DL_SERVICE_NUMBER,UNADJUSTED_SERVICE_ID,UNADJUSTED_SERVICE_NUMBER,id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";		
			if(jdbcOperations.update(sql1, setCompanyServiceValues(companyServiceVO)) > 0){
				result = companyServiceVO.getId();				
			}
			else{
				result =  "FAILURE";
			}
		} catch (Exception e) {
			result =  "FAILURE";
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String addCompanyMeter(Meter meter) {
		String result="";
		try {
			meter.setId(generateId());
			System.out.println(meter);
			String sql = "insert into m_company_meter(m_company_service_id,meter_number,meter_make_code,accuracy_class_code,is_abtmeter,mf,METER_CT1,METER_CT2,METER_CT3,METER_PT1,METER_PT2,METER_PT3,MODEM_NUMBER,id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			if(jdbcOperations.update(sql,setMeterValues(meter))>0) {
				result = meter.getId();
			}else {
				result = "FAILURE";
			}
			
		}catch(Exception e) {
			result = "FAILURE";
			e.printStackTrace();
		}
		return result;
		
	}

	public String addCompanyShareHolder(CompanyShareHolder companyShareHolder) {
		String result="";
		try {
			companyShareHolder.setId(generateId());
			System.out.println(companyShareHolder);
			String sql ="insert into M_COMPANY_SHAREHOLDER(M_COMPANY_ID,M_SHAREHOLDER_COMPANY_ID,\"share\",MEASURE,ENABLED,QUANTUM,M_SHAREHOLDER_COMP_SERV_ID,ID)values(?,?,?,?,?,?,?,?)";
			if(jdbcOperations.update(sql,setCompanyShareHolderValues(companyShareHolder))>0) {
				result = companyShareHolder.getId();
			}else {
				result = "FAILURE";
			}
		}catch(Exception e) {
			result = "FAILURE";
			e.printStackTrace();
		}
		
		return result;
		
	}
	public String updateCompanyService(CompanyServiceVO companyServiceVO) {
		String result = "";	
		 
		try {			
			
			String sql="update m_company_service set COMP_SER_TYPE_CODE=?,\"number\"=?,m_company_id=?,m_org_id=?,capacity=?,m_substation_id=?,m_feeder_id=?,ref_number=?,voltage_code=?,tariff=?,enabled=?,TYPE=?,BANKING_SERVICE_ID=?,BANKING_SERVICE_NUMBER=?,TL_SERVICE_ID=?,TL_SERVICE_NUMBER=?,DL_SERVICE_ID=?,DL_SERVICE_NUMBER=?,UNADJUSTED_SERVICE_ID=?,UNADJUSTED_SERVICE_NUMBER=? where id=?";
					
			if(jdbcOperations.update(sql,setCompanyServiceValues(companyServiceVO)) > 0){
				result =companyServiceVO.getId();
			}
			else{
				System.out.println("hi");
				result =  "FAILURE";
			}
		} catch (Exception e) {
			result =  "FAILURE";
			e.printStackTrace();
		}
		
		return result;
	}
	public String updateCompanyMeter(Meter meter) {
		String result="";
		try {
			String sql ="update  m_company_meter set m_company_service_id=?,meter_number=?,meter_make_code=?,accuracy_class_code=?,is_abtmeter=?,mf=?,METER_CT1=?,METER_CT2=?,METER_CT3=?,METER_PT1=?,METER_PT2=?,METER_PT3=?,MODEM_NUMBER=? where id=?";
			if(jdbcOperations.update(sql,setMeterValues(meter))>0) {
				result = meter.getId();
			}else {
				result="FAILURE";
			}
		}catch(Exception e) {
			result="FAILURE";
			e.printStackTrace();			
		}
		return result;
	}
	
	public String updateCompanyShareHolder(CompanyShareHolder companyShareHolder) {
		String result="";
		try {
			String sql ="update M_COMPANY_SHAREHOLDER set M_COMPANY_ID=?,M_SHAREHOLDER_COMPANY_ID=?,\"share\"=?,MEASURE=?,ENABLED=?,QUANTUM=?,M_SHAREHOLDER_COMP_SERV_ID=? where id=?";
			if(jdbcOperations.update(sql,setCompanyShareHolderValues(companyShareHolder))>0) {
				result = companyShareHolder.getId();
			}else {
				result ="FAILURE";
			}
		}catch(Exception e) {
			result ="FAILURE";
			e.printStackTrace();
		}
		
		return result;
	}
	
	private Object[] setValues(Company company){
		return new Object[]{
				company.getCode(),
				company.getName(),
				company.getCompanyTypeCode(),
				company.getRegistrationNo(),
				company.getRegistrationDate(),
				company.getCobDate(),
				company.getIncorpPlace(),
				company.getIsCaptive(),
				company.getCaptivePurpose(),
				company.getPan(),
				company.getTan(),
				company.getCst(),
				company.getGst(),
				company.getCin(),
				company.getLlpin(),
				company.getEnabled(),
				company.getRemarks(),
				company.getBankingServiceId(),
				company.getBankingServiceNumber(),
				company.getUnadjustedServiceId(),
				company.getUnadjustedServiceNumber(),
				company.getIsBuyer(),
				company.getIsSeller(),
				company.getId()
				};
	}
	
	private Object[] setMeterValues(Meter meter) {
		return new Object[] {
				meter.getServiceId(),
				meter.getMeterNumber(),
				meter.getMeterMakeCode(),
				meter.getAccuracyClassCode(),
				meter.getIsAbtMeter(),
				meter.getMultiplicationFactor(),
				meter.getMeterCt1(),
				meter.getMeterCt2(),
				meter.getMeterCt3(),
				meter.getMeterPt1(),
				meter.getMeterPt2(),
				meter.getMeterPt3(),
				meter.getModemNumber(),
				meter.getId()
		};
	}
	
	private Object[] setCompanyShareHolderValues(CompanyShareHolder companyShareHolder) {
		return new Object[] {
				companyShareHolder.getCompanyId(),
				companyShareHolder.getShareHolderCompanyId(),
				companyShareHolder.getShare(),
				companyShareHolder.getMeasure(),
				companyShareHolder.getEnabled(),
				companyShareHolder.getQuantum(),
				companyShareHolder.getShareHolderCompanyServiceId(),
				companyShareHolder.getId()
				
		};
		
	}
	
	final class CompanyMapper implements RowMapper<Company>
	{

		public CompanyMapper() {
				super();
		}

		public Company mapRow(ResultSet resultSet, int rownum) throws SQLException {
			Company company=new Company();
		
			company.setId(getStringFromResultSet(resultSet,"id"));
			company.setCode(getStringFromResultSet(resultSet,"code"));
			company.setName(getStringFromResultSet(resultSet,"name"));
			company.setCompanyTypeCode(getStringFromResultSet(resultSet,"company_type_code"));
			company.setCompanyTypeName(getStringFromResultSet(resultSet,"company_type_name"));
			company.setRegistrationNo(getStringFromResultSet(resultSet,"registration_no"));
			company.setRegistrationDate(getStringFromResultSet(resultSet,"registration_date"));
			company.setCobDate(getStringFromResultSet(resultSet,"cob_date"));
			company.setIncorpPlace(getStringFromResultSet(resultSet,"incorp_place"));
			company.setIsCaptive(getStringFromResultSet(resultSet,"is_captive"));
			company.setCaptivePurpose(getStringFromResultSet(resultSet,"captive_purpose"));
			company.setPan(getStringFromResultSet(resultSet,"pan"));
			company.setTan(getStringFromResultSet(resultSet,"tan"));
			company.setCst(getStringFromResultSet(resultSet,"cst"));
			company.setGst(getStringFromResultSet(resultSet,"gst"));
			company.setCin(getStringFromResultSet(resultSet,"cin"));
			company.setLlpin(getStringFromResultSet(resultSet,"llpin"));
			company.setRemarks(getStringFromResultSet(resultSet,"remarks"));
			company.setEnabled(getStringFromResultSet(resultSet,"enabled"));
			company.setServiceTypeCode(getStringFromResultSet(resultSet,"service_type_codes"));	
			company.setServiceTypeName(getStringFromResultSet(resultSet,"service_type_names"));	
			company.setBankingServiceId(getStringFromResultSet(resultSet,"banking_service_id"));	
			company.setBankingServiceNumber(getStringFromResultSet(resultSet,"banking_service_number"));
			company.setUnadjustedServiceId(getStringFromResultSet(resultSet,"unadjusted_service_id"));	
			company.setUnadjustedServiceNumber(getStringFromResultSet(resultSet,"unadjusted_service_number"));
			company.setIsBuyer(getStringFromResultSet(resultSet,"IS_BUYER"));
			company.setIsSeller(getStringFromResultSet(resultSet,"IS_SELLER"));
			
			return company;
		}
	}
	
	final class CompanyServiceMapper implements RowMapper<CompanyServiceVO>
	{

		public CompanyServiceMapper() {
				super();
		}

		public CompanyServiceVO mapRow(ResultSet resultSet, int rownum) throws SQLException {
			
			CompanyServiceVO service = new CompanyServiceVO();		
			service.setId(getStringFromResultSet(resultSet,"id"));
			service.setTypeCode(getStringFromResultSet(resultSet,"COMP_SER_TYPE_CODE"));
			service.setTypeName(getStringFromResultSet(resultSet,"COMP_SER_TYPE_NAME"));
			service.setNumber(getStringFromResultSet(resultSet,"number"));
			service.setCompanyId(getStringFromResultSet(resultSet,"m_company_id"));
			service.setCompanyCode(getStringFromResultSet(resultSet,"m_company_code"));
			service.setCompanyName(getStringFromResultSet(resultSet,"m_company_name"));
			service.setOrgId(getStringFromResultSet(resultSet,"m_org_id"));
			service.setOrgCode(getStringFromResultSet(resultSet,"m_org_code"));
			service.setOrgName(getStringFromResultSet(resultSet,"m_org_name"));
			service.setCapacity(getStringFromResultSet(resultSet,"capacity"));
			service.setSubstationId(getStringFromResultSet(resultSet,"m_substation_id"));
			service.setSubstationCode(getStringFromResultSet(resultSet,"m_substation_code"));
			service.setSubstationName(getStringFromResultSet(resultSet,"m_substation_name"));
			service.setFeederId(getStringFromResultSet(resultSet,"m_feeder_id"));
			service.setFeederCode(getStringFromResultSet(resultSet,"m_feeder_code"));
			service.setFeederName(getStringFromResultSet(resultSet,"m_feeder_name"));
			service.setRefNumber(getStringFromResultSet(resultSet,"ref_number"));
			service.setVoltageCode(getStringFromResultSet(resultSet,"VOLTAGE_CODE"));
			service.setVoltageName(getStringFromResultSet(resultSet,"VOLTAGE_NAME"));
			service.setTariff(getStringFromResultSet(resultSet,"tariff"));
			service.setEnabled(getStringFromResultSet(resultSet,"enabled"));
			service.setType(getStringFromResultSet(resultSet,"COMP_SER_TYPE_NAME"));
			service.setBankingServiceId(getStringFromResultSet(resultSet,"BANKING_SERVICE_ID"));
			service.setBankingServiceNumber(getStringFromResultSet(resultSet,"BANKING_SERVICE_NUMBER"));
			service.setTlServiceId(getStringFromResultSet(resultSet,"TL_SERVICE_ID"));
			service.setTlServiceNumber(getStringFromResultSet(resultSet,"TL_SERVICE_NUMBER"));
			service.setDlServiceId(getStringFromResultSet(resultSet,"DL_SERVICE_ID"));
			service.setDlServiceNumber(getStringFromResultSet(resultSet,"DL_SERVICE_NUMBER"));
			service.setUnadjustedServiceId(getStringFromResultSet(resultSet,"UNADJUSTED_SERVICE_ID"));
			service.setUnadjustedServiceNumber(getStringFromResultSet(resultSet,"UNADJUSTED_SERVICE_NUMBER"));
//			service.setAccuracyClassCode(getStringFromResultSet(resultSet,"ACCURACY_CLASS_CODE"));
//			service.setIsAbtMeter(getStringFromResultSet(resultSet,"IS_ABTMETER"));
//			service.setModemNumber(getStringFromResultSet(resultSet,"MODEM_NUMBER"));
//			service.setMeterNumber(getStringFromResultSet(resultSet,"METER_NUMBER"));
//			service.setMeterMakeCode(getStringFromResultSet(resultSet,"METER_MAKE_CODE"));
//			service.setMultiplicationFactor(getStringFromResultSet(resultSet,"MF"));
//			service.setFuelTypeCode(getStringFromResultSet(resultSet,"FUEL_TYPE_CODE"));
//			service.setFuelTypeName(getStringFromResultSet(resultSet,"FUEL_TYPE_NAME"));
//			service.setMeterCt1(getStringFromResultSet(resultSet,"METER_CT1"));
//			service.setMeterCt2(getStringFromResultSet(resultSet,"METER_CT2"));
//			service.setMeterCt3(getStringFromResultSet(resultSet,"METER_CT3"));
//			service.setMeterPt1(getStringFromResultSet(resultSet,"METER_PT1"));
//			service.setMeterPt2(getStringFromResultSet(resultSet,"METER_PT2"));
//			service.setMeterPt3(getStringFromResultSet(resultSet,"METER_PT3"));
			return service;
		}
	}	
	final class MeterMapper implements RowMapper<Meter>
	{

		public MeterMapper() {
				super();
		}

		public Meter mapRow(ResultSet resultSet, int rownum) throws SQLException {
			Meter meter = new Meter();
			meter.setId(getStringFromResultSet(resultSet,"id"));
			meter.setServiceId(getStringFromResultSet(resultSet,"m_company_service_id"));
			meter.setServiceTypeCode(getStringFromResultSet(resultSet,"m_company_service_type_code"));
			meter.setServiceTypeName(getStringFromResultSet(resultSet,"m_company_service_type_name"));
			meter.setServiceNumber(getStringFromResultSet(resultSet,"m_company_service_number"));
			meter.setCompanyId(getStringFromResultSet(resultSet,"m_company_id"));
			meter.setCompanyName(getStringFromResultSet(resultSet,"m_company_name"));
			meter.setMeterNumber(getStringFromResultSet(resultSet,"meter_number"));
			meter.setMeterMakeCode(getStringFromResultSet(resultSet,"meter_make_code"));
			meter.setMeterMakeName(getStringFromResultSet(resultSet,"meter_make_name"));
			meter.setAccuracyClassCode(getStringFromResultSet(resultSet,"accuracy_class_code"));
			meter.setAccuracyClassName(getStringFromResultSet(resultSet,"accuracy_class_name"));
			meter.setIsAbtMeter(getStringFromResultSet(resultSet,"is_abtmeter"));
			meter.setMultiplicationFactor(getStringFromResultSet(resultSet,"mf"));	
			meter.setMeterCt1(getStringFromResultSet(resultSet,"METER_CT1"));
			meter.setMeterCt2(getStringFromResultSet(resultSet,"METER_CT2"));
			meter.setMeterCt3(getStringFromResultSet(resultSet,"METER_CT3"));
			meter.setMeterPt1(getStringFromResultSet(resultSet,"METER_PT1"));
			meter.setMeterPt2(getStringFromResultSet(resultSet,"METER_PT2"));
			meter.setMeterPt3(getStringFromResultSet(resultSet,"METER_PT3"));
			meter.setModemNumber(getStringFromResultSet(resultSet,"MODEM_NUMBER"));
			return meter;
		}
	}
	
	final class CompanyShareHolderMapper implements RowMapper<CompanyShareHolder>{

		public CompanyShareHolderMapper() {
			super();
		}
		@Override
		public CompanyShareHolder mapRow(ResultSet resultSet, int rownum) throws SQLException {
			CompanyShareHolder companyShareHolder =  new CompanyShareHolder();
			companyShareHolder.setId(getStringFromResultSet(resultSet,"id"));
			companyShareHolder.setCompanyId(getStringFromResultSet(resultSet,"m_company_id"));
			companyShareHolder.setCompanyCode(getStringFromResultSet(resultSet,"m_company_code"));
			companyShareHolder.setCompanyName(getStringFromResultSet(resultSet,"m_company_name"));
			companyShareHolder.setShareHolderCompanyId(getStringFromResultSet(resultSet,"M_SHAREHOLDER_COMPANY_ID"));
			companyShareHolder.setShareHolderCompanyCode(getStringFromResultSet(resultSet,"M_SHAREHOLDER_COMPANY_CODE"));
			companyShareHolder.setShareHolderCompanyName(getStringFromResultSet(resultSet,"M_SHAREHOLDER_COMPANY_NAME"));
			companyShareHolder.setShare(getStringFromResultSet(resultSet,"SHARE"));
			companyShareHolder.setMeasure(getStringFromResultSet(resultSet,"MEASURE"));
			companyShareHolder.setEnabled(getStringFromResultSet(resultSet,"ENABLED"));
			companyShareHolder.setQuantum(getStringFromResultSet(resultSet,"QUANTUM"));
			companyShareHolder.setShareHolderCompanyServiceId(getStringFromResultSet(resultSet,"M_SHAREHOLDER_COMP_SERV_ID"));

			
		    
			return companyShareHolder;
		}
		
	}

	private Object[] setCompanyServiceValues(CompanyServiceVO companyServiceVO){
		return new Object[]{
				companyServiceVO.getTypeCode(),
				companyServiceVO.getNumber(),
				companyServiceVO.getCompanyId(),
				companyServiceVO.getOrgId(),
				companyServiceVO.getCapacity(),
				companyServiceVO.getSubstationId(),
				companyServiceVO.getFeederId(),
				companyServiceVO.getRefNumber(),
				companyServiceVO.getVoltageCode(),
				companyServiceVO.getTariff(),
				companyServiceVO.getEnabled(),
				companyServiceVO.getType(),
				companyServiceVO.getBankingServiceId(),
				companyServiceVO.getBankingServiceNumber(),
				companyServiceVO.getTlServiceId(),
				companyServiceVO.getTlServiceNumber(),
				companyServiceVO.getDlServiceId(),
				companyServiceVO.getDlServiceNumber(),
				companyServiceVO.getUnadjustedServiceId(),
				companyServiceVO.getUnadjustedServiceNumber(),
				companyServiceVO.getId(),
				
				};
	}

	@Override
	public List<CompanyServiceVO> getAllCompanyServices(Map<String, String> criteria) throws Exception {
		
		try {
			String sql="select * from v_company_service where 1=1 ";
			if(!criteria.isEmpty())
			{
				if(criteria.get("type")!=null){
					sql += "and upper(COMP_SER_TYPE_CODE) = '"+criteria.get("type")+"' ";
				}
				
				if(criteria.get("edc-id")!=null){
					sql += "and upper(m_org_id) = '"+criteria.get("edc-id")+"' ";
				}
				
				if(criteria.get("comp-service-numbers")!=null){
					sql += "and upper(\"number\") in ('"+criteria.get("comp-service-numbers")+"') ";
				}
				
				if(criteria.get("comp-service-id")!=null){
					sql += "and upper(id) = upper('"+criteria.get("comp-service-id")+"')";
				}
				
				
				if(criteria.get("enabled")!=null){
					sql += "and upper(enabled) = '"+criteria.get("enabled")+"' ";
				}
				
				if(criteria.get("is-internal")!=null){
					sql += "and upper(nvl(COMPANY_IS_INTERNAL,'N')) = '"+criteria.get("is-internal")+"' ";
				}
				
				sql +="ORDER BY \"number\" ";
				
			}
			System.out.println(sql);
			
			return (List<CompanyServiceVO>) jdbcOperations.query(sql,new CompanyServiceMapper());
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error while fetching all Company Services");
		}
	}
	

}