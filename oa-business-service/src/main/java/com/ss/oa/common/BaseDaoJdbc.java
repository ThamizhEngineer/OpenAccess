package com.ss.oa.common;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcOperations;

import com.ss.oa.ledger.vo.Agreement;
import com.ss.oa.master.vo.Company;
import com.ss.oa.master.vo.CompanyServiceVO;
import com.ss.oa.master.vo.Feeder;
import com.ss.oa.master.vo.Powerplant;
import com.ss.oa.master.vo.Org;
import com.ss.oa.master.vo.Substation;
import com.ss.oa.master.vo.Tariff;
import com.ss.oa.master.vo.User;
import com.ss.oa.transaction.vo.AmendmentType;
import com.ss.oa.transaction.vo.CompanyMeterChange;
import com.ss.oa.transaction.vo.CompanyNameChange;
import com.ss.oa.transaction.vo.Consent;
import com.ss.oa.transaction.vo.Cs;
import com.ss.oa.transaction.vo.EnergySaleIntent;
import com.ss.oa.transaction.vo.Payment;
import com.ss.oa.transaction.vo.Epa;
import com.ss.oa.transaction.vo.Ewa;
import com.ss.oa.transaction.vo.Ipaa;
import com.ss.oa.transaction.vo.LogService;
import com.ss.oa.transaction.vo.MeterReadingImport;
import com.ss.oa.transaction.vo.Noc;
import com.ss.oa.transaction.vo.NocGenerator;
import com.ss.oa.transaction.vo.EnergySaleDescription;

 

import com.ss.oa.transaction.vo.Oaa;

import com.ss.oa.transaction.vo.StandingClearence;

@Scope("prototype")
public class BaseDaoJdbc {

	@Resource
	private JdbcOperations jdbcOperations;
	
	public BaseDaoJdbc() {
		super();
	}
	
	public String generateId(){
		UUID uuid = UUID.randomUUID();
        return uuid.toString();
	}
	
	public String generateCode(String className, String param){
		try {
			Map<String, String[]> valuesMap = new HashMap<String, String[]>();
			if (param==null || param.trim().isEmpty()) {
				valuesMap.put(Feeder.class.getSimpleName(), new String[]{"FED", "m_feeder_id_seq"});
				valuesMap.put(Org.class.getSimpleName(), new String[]{"ORG", "m_org_seq"});
				valuesMap.put(Substation.class.getSimpleName(), new String[]{"FE", "m_substation_id_seq"});
				valuesMap.put(Tariff.class.getSimpleName(), new String[]{"TAR", "m_tariff_seq"});
				valuesMap.put(User.class.getSimpleName(), new String[]{"USR", "m_user_seq"});
				valuesMap.put(Powerplant.class.getSimpleName(), new String[]{"PP", "m_powerplant_seq"});
				valuesMap.put(Company.class.getSimpleName(), new String[]{"COM", "m_company_seq"});
				valuesMap.put(EnergySaleIntent.class.getSimpleName(), new String[]{"ESI", "T_ES_INTENT_SEQ"});			
				valuesMap.put(MeterReadingImport.class.getSimpleName(), new String[]{"MRI", "IMP_MR_HEADER_SEQ"});
				valuesMap.put(Noc.class.getSimpleName(), new String[]{"NOC", "T_NOC_SEQ"});
				valuesMap.put(Consent.class.getSimpleName(), new String[]{"CON", "T_CONSENT_SEQ"});
				valuesMap.put(Ewa.class.getSimpleName(), new String[]{"EWA", "T_EWA_SEQ"});
				valuesMap.put(Ipaa.class.getSimpleName(), new String[]{"IPAA", "T_INPRINCIPLE_APPLN_SEQ"});
				valuesMap.put(NocGenerator.class.getSimpleName(), new String[]{"NOC_GEN", "T_NOC_GENERATOR_SEQ"});
				valuesMap.put(AmendmentType.class.getSimpleName(), new String[]{"AMEND", "T_AMENDMENT_SEQ"});
				valuesMap.put(Agreement.class.getSimpleName(), new String[]{"AGREEMENT", "F_AGREEMENT_SEQ"});
				valuesMap.put(StandingClearence.class.getSimpleName(), new String[]{"STANDING_CLEARENCE", "T_STANDING_CLEARENCE_SEQ"});
				valuesMap.put(LogService.class.getSimpleName(), new String[]{"", "T_ACTIVITY_LOG_SEQ"});
				valuesMap.put(Oaa.class.getSimpleName(), new String[]{"OAA", "T_OAA_SEQ"});
				valuesMap.put(Epa.class.getSimpleName(), new String[]{"PPA", "T_EPA_SEQ"});
				valuesMap.put(Cs.class.getSimpleName(), new String[]{"CS", "T_CS_SEQ"});
				valuesMap.put(CompanyMeterChange.class.getSimpleName(), new String[]{"CMC", "T_CMC_SEQ"});
				valuesMap.put(CompanyNameChange.class.getSimpleName(), new String[]{"CNC", "T_CNC_SEQ"});
				valuesMap.put(Payment.class.getSimpleName(), new String[]{"PMT", "F_ES_PAYMENTS_SEQ"});
			}
			else {
				if(className.equalsIgnoreCase(CompanyServiceVO.class.getSimpleName())) {
					if(param.equalsIgnoreCase("01")){
						valuesMap.put(CompanyServiceVO.class.getSimpleName(), new String[]{"BG", "m_company_serv_seq"});
					}
					else if(param.equalsIgnoreCase("02")) {
						valuesMap.put(CompanyServiceVO.class.getSimpleName(), new String[]{"BR", "m_company_serv_seq"});
					}
					else if(param.equalsIgnoreCase("03")) {
						valuesMap.put(CompanyServiceVO.class.getSimpleName(), new String[]{"SR", "m_company_serv_seq"});
					}
					else if(param.equalsIgnoreCase("04")) {
						valuesMap.put(CompanyServiceVO.class.getSimpleName(), new String[]{"TL", "m_company_serv_seq"});
					}
					else if(param.equalsIgnoreCase("05")) {
						valuesMap.put(CompanyServiceVO.class.getSimpleName(), new String[]{"DL", "m_company_serv_seq"});
					}
					else {
						valuesMap.put(CompanyServiceVO.class.getSimpleName(), new String[]{"UN", "m_company_serv_seq"});
					} 
				}
			}
			String[] values = valuesMap.get(className);
			String sql="select "+values[1]+".nextval from dual";
			return values[0]+jdbcOperations.queryForObject(sql,String.class);
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
	}
 public String getChargeDescription(EnergySaleDescription energySaleDescription) {
	 
	 String sqlQuery="SELECT chargedef.ID,chargedef.CHARGE_CODE,chargedef.CHARGE_DESC,chargedef.CHARGE_TYPE_CODE,chargedef.CREATED_DATE,chargedef.ENABLED,chargedef.FORMULA,\n" + 
	 		"chargedef.REMARKS,chargedef.UNIT_CHARGE FROM M_CHARGE_DEFN chargedef WHERE 1=1 ";
	 
//	 String sqlQuery="SELECT * FROM M_CHARGE_DEFN WHERE 1=1";
	 return sqlQuery;
 }
	
	public String getStringFromResultSet(java.sql.ResultSet rs, String columnName){
		try{
			return rs.getString(columnName);	
		}catch (Exception e) {
			System.out.println("Exception in getStringFromResultSet- "+columnName+" - "+e.getMessage());
			return null;
		}
	}
	public static void  main(String args[]) {
		
		BaseDaoJdbc baseDaoJdbc = new BaseDaoJdbc();
		System.out.println(baseDaoJdbc.generateId());
		System.out.println(baseDaoJdbc.generateCode(Feeder.class.getSimpleName(),""));
	}

}
