	package com.ss.oa.transaction.gridConnectivity;

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
import com.ss.oa.master.vo.Generator;
import com.ss.oa.transaction.vo.ApplicationStatus;
import com.ss.oa.transaction.vo.CaptiveUserContribution;
import com.ss.oa.transaction.vo.DocCheckListItem;
import com.ss.oa.transaction.vo.EquityShareVotingRights;
import com.ss.oa.transaction.vo.GridConnectivity;
import com.ss.oa.transaction.vo.Loan;
import com.ss.oa.transaction.vo.QuantumAllocation;
import com.ss.oa.transaction.vo.Transformer;

@Scope("prototype")
@Component
public class GridConnectivityDaoImpl extends BaseDaoJdbc implements GridConnectivityDao{
	
@Resource
private JdbcOperations jdbcOperations;

@Override
public List<GridConnectivity> getAllGcs(Map<String, String> criteria) {
	
	String sql="SELECT gc.ID,gc.APPLICATION_NUMBER,gc.GENERATING_TYPE_NAME,gc.PLANT_NAME,gc.COMPANY_NAME,gc.TEMP_HT_SUPPLY_NUMBER,gc.M_SUBSTATION_ID,substation.NAME AS M_SUBSTATION_NAME, \n" + 
			"			 gc.PROPOSED_COMMISSION_DT,gc.VOLTAGE_CODE,voltagecode.VALUE_DESC AS VOLTAGE_NAME,gc.CLASS_VOLTAGE_PHASE,gc.CLASS_VOLTAGE_FREQUENCY,gc.AVAILED_HT_SUPPLY,gc.AVAILED_HT_SUPPLY_NO,gc.AVAILED_SANCTIONED_DEMAND,\n" + 
			"			 gc.FUEL_TYPE_CODE,fuelcodes.VALUE_DESC AS FUEL_TYPE_NAME,gc.FUEL_LINKAGE_ARRANGED,gc.FUEL_LINKAGE_DETAILS,gc.STATUS_CODE,gc.LINE1,gc.CITY,gc.TOWN,gc.STATE_CODE,statecode.VALUE_DESC AS STATE_NAME,gc.PINCODE,gc.VILLAGE,gc.TALUK_CODE,\n" + 
			"			 talukcode.VALUE_DESC AS TALUK_NAME,gc.DISTRICT_CODE,districtcode.VALUE_DESC AS DISTRICT_NAME,gc.PLS_SF_NO,gc.PL_VILLAGE,gc.PL_PINCODE,gc.PL_TOWN,gc.PL_TALUK_CODE,pltalukcodes.VALUE_DESC AS PL_TALUK_NAME,gc.PL_DISTRICT_CODE,pldistrictcodes.VALUE_DESC AS PL_DISTRICT_NAME,gc.WIND_PASS_CODE,windpasscode.VALUE_DESC AS WIND_PASS_NAME,gc.WIND_ZONE_AREA_CODE,gc.SCO_TAMILNADU,\n" + 
			"			 gc.SCO_MINISTRY,gc.SCO_CIVIL,gc.CG_INDUSTRY_TYPE,gc.CG_SUPPORT_FUEL,gc.CG_IS_PARALLEL_RUN,gc.CG_IS_STANDBY,gc.CG_CYCLE,gc.CG_HAS_PROOF,gc.PLANT_CAPACITY,  \n" + 
			"			 gc.ANNUAL_EXPECTED_QUANTUM,gc.EXPECTED_CUF,gc.AUXILIARY_CONSUMPTION,gc.INDUSTRIAL_CONSUMPTION,gc.PER_UNIT_COST,gc.FIRM_POWER,gc.INFIRM_POWER,gc.PROPOSED_POWER_STB, \n" + 
			"			 gc.PROPOSED_POWER_CAPTIVE,gc.PROPOSED_POWER_3PTY,gc.ID_TOTAL_COST,gc.ID_TOTAL_CURRENCY,gc.ID_TOTAL_EXCHANGE_RATE,gc.ID_PROPOSED_DEBT_EQUITY_RATIO,gc.FINAL_ORG_ID,finalorg.NAME AS FINAL_ORG_NAME,\n" + 
			"			 gc.FINAL_SUBSTATION_ID,finalsub.NAME AS FINAL_SUBSTATION_NAME,gc.FINAL_FEEDER_ID,feeder.NAME AS FINAL_FEEDER_NAME,gc.FINAL_SS_TYPE_NAME,gc.FINAL_FEEDER_TYPE_NAME,gc.GEN_SERVICE_NUMBER,gc.GEN_SERVICE_APPROVAL_NUMBER, \n" + 
			"			 gc.GEN_SERVICE_DATE,gc.FINAL_COD,gc.FINAL_COPD,gc.FINAL_IS_STB,gc.FINAL_IS_WHEELING,gc.FINAL_PP_RATE,gc.FINAL_STB_TARIFF_ORDER,gc.FINAL_STB_TENDER_NUMBER,gc.\n" + 
			"			 FINAL_STB_TENDER_DATE,gc.FINAL_WHEELING_FROM_DATE,gc.FINAL_WHEELING_TO_DATE,gc.REMARKS,\n" + 
			"			 gc.M_ORG_ID,org.NAME AS M_ORG_NAME,gc.GENERATING_TYPE_ISCAPTIVE,gc.METER_CT1,gc.METER_CT2,gc.METER_CT3,gc.METER_PT1,gc.METER_PT2,gc.METER_PT3,  \n" + 
			"			 gc.METER_NUMBER,gc.METER_MAKE_CODE,gc.ACCURACY_CLASS_CODE,gc.IS_ABTMETER,gc.MF,gc.MODEM_NUMBER,gc.PARALLEL_OPERATION\n" + 
			"			 FROM  T_GRID_CONNECTIVITY gc  \n" + 
			"			 LEFT JOIN V_CODES fuelcodes ON gc.FUEL_TYPE_CODE =fuelcodes.VALUE_CODE AND fuelcodes.LIST_CODE='FUEL_TYPE_CODE' \n" + 
			"			 LEFT JOIN M_ORG org ON gc.M_ORG_ID = org.ID  \n" + 
			"			 LEFT JOIN M_ORG finalorg ON gc.FINAL_ORG_ID = finalorg.ID  \n" + 
			"			 LEFT JOIN M_SUBSTATION substation ON gc.M_SUBSTATION_ID = substation.ID \n" + 
			"			 LEFT JOIN M_SUBSTATION finalsub ON gc.FINAL_SUBSTATION_ID = finalsub.ID  \n" + 
			"			 LEFT JOIN M_FEEDER feeder ON gc.FINAL_FEEDER_ID = feeder.ID  \n" + 
			"			 LEFT JOIN V_CODES statecode ON gc.STATE_CODE = statecode.VALUE_CODE AND statecode.LIST_CODE='STATE_CODE' \n" + 
			"			 LEFT JOIN V_CODES districtcode ON gc.DISTRICT_CODE = districtcode.VALUE_CODE AND districtcode.LIST_CODE='DISTRICT_CODE' \n" + 
			"			 LEFT JOIN V_CODES talukcode ON gc.TALUK_CODE = talukcode.VALUE_CODE AND talukcode.LIST_CODE='TALUK_CODE' \n" + 
			"			 LEFT JOIN V_CODES pltalukcodes on gc.PL_TALUK_CODE = pltalukcodes.VALUE_CODE AND  pltalukcodes.LIST_CODE = 'TALUK_CODE' \n" + 
			"			 LEFT JOIN V_CODES pldistrictcodes on gc.PL_DISTRICT_CODE = pldistrictcodes.VALUE_CODE AND  pldistrictcodes.LIST_CODE = 'DISTRICT_CODE' \n" + 
			"			 LEFT JOIN V_CODES windpasscode on gc.WIND_PASS_CODE = windpasscode.VALUE_CODE and windpasscode.LIST_CODE = 'WIND_PASS_CODE' \n" + 
			"			 LEFT JOIN V_CODES voltagecode ON gc.VOLTAGE_CODE = voltagecode.VALUE_CODE AND voltagecode.LIST_CODE='VOLTAGE_CODE' WHERE 1=1";
	
	System.out.println(sql);
	
    if(!criteria.isEmpty())
	{
		if(criteria.get("application-number")!=null){
			sql += "and upper(gc.APPLICATION_NUMBER)= upper('"+criteria.get("application-number")+"')";
		}
		if(criteria.get("plant-name")!=null){
			sql += "and upper(gc.PLANT_NAME) = upper('"+criteria.get("plant-name")+"')";
		}
		if(criteria.get("generating-plant-type")!=null){
			sql += "and upper(gc.GENERATING_TYPE_NAME) = upper('"+criteria.get("generating-plant-type")+"')";
		}
		if(criteria.get("fuel")!=null){
			sql += "and upper(gc.FUEL_TYPE_CODE) = upper('"+criteria.get("fuel")+"')";
		}
		if(criteria.get("company-name")!=null){
			sql += "and upper(gc.COMPANY_NAME) = upper('"+criteria.get("company-name")+"')";
		}
		if(criteria.get("application-status")!=null){
			sql += "and upper(gc.STATUS_CODE) = upper('"+criteria.get("application-status")+"')";
		}
	}
	
	return jdbcOperations.query(sql, new GcMapper());
}
@Override
public GridConnectivity getGcById(String id) {
	GridConnectivity gc= new GridConnectivity();
	  String sql="SELECT gc.ID,gc.APPLICATION_NUMBER,gc.GENERATING_TYPE_NAME,gc.PLANT_NAME,gc.COMPANY_NAME,gc.TEMP_HT_SUPPLY_NUMBER,gc.M_SUBSTATION_ID,substation.NAME AS M_SUBSTATION_NAME, \n" + 
	  		"			 gc.PROPOSED_COMMISSION_DT,gc.VOLTAGE_CODE,voltagecode.VALUE_DESC AS VOLTAGE_NAME,gc.CLASS_VOLTAGE_PHASE,gc.CLASS_VOLTAGE_FREQUENCY,gc.AVAILED_HT_SUPPLY,gc.AVAILED_HT_SUPPLY_NO,gc.AVAILED_SANCTIONED_DEMAND,\n" + 
	  		"			 gc.FUEL_TYPE_CODE,fuelcodes.VALUE_DESC AS FUEL_TYPE_NAME,gc.FUEL_LINKAGE_ARRANGED,gc.FUEL_LINKAGE_DETAILS,gc.STATUS_CODE,gc.LINE1,gc.CITY,gc.TOWN,gc.STATE_CODE,statecode.VALUE_DESC AS STATE_NAME,gc.PINCODE,gc.VILLAGE,gc.TALUK_CODE,\n" + 
	  		"			 talukcode.VALUE_DESC AS TALUK_NAME,gc.DISTRICT_CODE,districtcode.VALUE_DESC AS DISTRICT_NAME,gc.PLS_SF_NO,gc.PL_VILLAGE,gc.PL_PINCODE,gc.PL_TOWN,gc.PL_TALUK_CODE,pltalukcodes.VALUE_DESC AS PL_TALUK_NAME,gc.PL_DISTRICT_CODE,pldistrictcodes.VALUE_DESC AS PL_DISTRICT_NAME,gc.WIND_PASS_CODE,windpasscode.VALUE_DESC AS WIND_PASS_NAME,gc.WIND_ZONE_AREA_CODE,gc.SCO_TAMILNADU,\n" + 
	  		"			 gc.SCO_MINISTRY,gc.SCO_CIVIL,gc.CG_INDUSTRY_TYPE,gc.CG_SUPPORT_FUEL,gc.CG_IS_PARALLEL_RUN,gc.CG_IS_STANDBY,gc.CG_CYCLE,gc.CG_HAS_PROOF,gc.PLANT_CAPACITY,  \n" + 
	  		"			 gc.ANNUAL_EXPECTED_QUANTUM,gc.EXPECTED_CUF,gc.AUXILIARY_CONSUMPTION,gc.INDUSTRIAL_CONSUMPTION,gc.PER_UNIT_COST,gc.FIRM_POWER,gc.INFIRM_POWER,gc.PROPOSED_POWER_STB, \n" + 
	  		"			 gc.PROPOSED_POWER_CAPTIVE,gc.PROPOSED_POWER_3PTY,gc.ID_TOTAL_COST,gc.ID_TOTAL_CURRENCY,gc.ID_TOTAL_EXCHANGE_RATE,gc.ID_PROPOSED_DEBT_EQUITY_RATIO,gc.FINAL_ORG_ID,finalorg.NAME AS FINAL_ORG_NAME,\n" + 
	  		"			 gc.FINAL_SUBSTATION_ID,finalsub.NAME AS FINAL_SUBSTATION_NAME,gc.FINAL_FEEDER_ID,feeder.NAME AS FINAL_FEEDER_NAME,gc.FINAL_SS_TYPE_NAME,gc.FINAL_FEEDER_TYPE_NAME,gc.GEN_SERVICE_NUMBER,gc.GEN_SERVICE_APPROVAL_NUMBER, \n" + 
	  		"			 gc.GEN_SERVICE_DATE,gc.FINAL_COD,gc.FINAL_COPD,gc.FINAL_IS_STB,gc.FINAL_IS_WHEELING,gc.FINAL_PP_RATE,gc.FINAL_STB_TARIFF_ORDER,gc.FINAL_STB_TENDER_NUMBER,gc.\n" + 
	  		"			 FINAL_STB_TENDER_DATE,gc.FINAL_WHEELING_FROM_DATE,gc.FINAL_WHEELING_TO_DATE,gc.REMARKS,\n" + 
	  		"			 gc.M_ORG_ID,org.NAME AS M_ORG_NAME,gc.GENERATING_TYPE_ISCAPTIVE,gc.METER_CT1,gc.METER_CT2,gc.METER_CT3,gc.METER_PT1,gc.METER_PT2,gc.METER_PT3,  \n" + 
	  		"			 gc.METER_NUMBER,gc.METER_MAKE_CODE,gc.ACCURACY_CLASS_CODE,gc.IS_ABTMETER,gc.MF,gc.MODEM_NUMBER,GC.PARALLEL_OPERATION\n" + 
	  		"			 FROM T_GRID_CONNECTIVITY gc  \n" + 
	  		"			 LEFT JOIN V_CODES fuelcodes ON gc.FUEL_TYPE_CODE =fuelcodes.VALUE_CODE AND fuelcodes.LIST_CODE='FUEL_TYPE_CODE' \n" + 
	  		"			 LEFT JOIN M_ORG org ON gc.M_ORG_ID = org.ID  \n" + 
	  		"			 LEFT JOIN M_ORG finalorg ON gc.FINAL_ORG_ID = finalorg.ID  \n" + 
	  		"			 LEFT JOIN M_SUBSTATION substation ON gc.M_SUBSTATION_ID = substation.ID \n" + 
	  		"			 LEFT JOIN M_SUBSTATION finalsub ON gc.FINAL_SUBSTATION_ID = finalsub.ID  \n" + 
	  		"			 LEFT JOIN M_FEEDER feeder ON gc.FINAL_FEEDER_ID = feeder.ID  \n" + 
	  		"			 LEFT JOIN V_CODES statecode ON gc.STATE_CODE = statecode.VALUE_CODE AND statecode.LIST_CODE='STATE_CODE' \n" + 
	  		"			 LEFT JOIN V_CODES districtcode ON gc.DISTRICT_CODE = districtcode.VALUE_CODE AND districtcode.LIST_CODE='DISTRICT_CODE' \n" + 
	  		"			 LEFT JOIN V_CODES talukcode ON gc.TALUK_CODE = talukcode.VALUE_CODE AND talukcode.LIST_CODE='TALUK_CODE' \n" + 
	  		"			 LEFT JOIN V_CODES pltalukcodes on gc.PL_TALUK_CODE = pltalukcodes.VALUE_CODE AND  pltalukcodes.LIST_CODE = 'TALUK_CODE' \n" + 
	  		"			 LEFT JOIN V_CODES pldistrictcodes on gc.PL_DISTRICT_CODE = pldistrictcodes.VALUE_CODE AND  pldistrictcodes.LIST_CODE = 'DISTRICT_CODE' \n" + 
	  		"			 LEFT JOIN V_CODES windpasscode on gc.WIND_PASS_CODE = windpasscode.VALUE_CODE and windpasscode.LIST_CODE = 'WIND_PASS_CODE' \n" + 
	  		"			 LEFT JOIN V_CODES voltagecode ON gc.VOLTAGE_CODE = voltagecode.VALUE_CODE AND voltagecode.LIST_CODE='VOLTAGE_CODE' WHERE gc.ID=?";
	  
	  System.out.println(sql);
	  gc= jdbcOperations.queryForObject(sql,new Object[]{id}, new GcMapper());
	  
		 List<Generator> genUnits =new ArrayList<Generator>();
		
		 String sql1="SELECT gcgen.ID,gcgen.GC_ID,gcgen.NAME,gcgen.CAPACITY,gcgen.MAKE_CODE,gcgen.MW_RATING,gcgen.MV_RATING,gcgen.KV_RATING,gcgen.DAMPING_FACTOR,gcgen.AMATEUR_RESISTANCE,\n" + 
		 		"gcgen.DIRECT_ASSISTANCE_REACTANCE,gcgen.NEGATIVE_SEQUENCE_REACTANCE,gcgen.ZERO_SEQUENCE_REACTANCE,gcgen.IS_CAPTIVE,gcgen.WINDING_CONNECTION,gcgen.MASS_NUMBER,\n" + 
		 		"STIFFNESS_COEFFICIENT,gcgen.SERIAL_NO,gcgen.ROTOR_DIA,gcgen.HUB_HEIGHT,gcgen.REMARKS,gcgen.CREATED_BY,gcgen.CREATED_DATE,gcgen.MODIFIED_BY,\n" + 
		 		"gcgen.MODIFIED_DATE,gcgen.ENABLED\n" + 
		 		"FROM T_GC_GEN_UNIT gcgen WHERE gcgen.GC_ID=?";
		 
		 System.out.println(sql1);
		 genUnits=jdbcOperations.query(sql1,new Object[]{id}, new GeneratorUnitsMapper());
		 gc.setGenUnits(genUnits);
		 
		 
		 List<Transformer> transformers =new ArrayList<Transformer>();
		 
		 String sql2="SELECT transformers.ID,transformers.GC_ID,transformers.NAME,transformers.MVA_RATING,transformers.PRIMARY_VOLTAGE_CODE,pvoltagecode.VALUE_DESC AS PRIMARY_VOLTAGE_NAME,transformers.SECONDARY_VOLTAGE_CODE,\n" + 
		 		"svoltagecode.VALUE_DESC AS SECONDARY_VOLTAGE_NAME,transformers.COOLING_TYPE,transformers.WINDING_CONFIGURATION,transformers.BREAKER_RATING,transformers.TAP_SETTING,transformers.TAP_NUMBER_MAX,\n" + 
		 		"transformers.TAP_NUMBER_MIN,transformers.TAP_RATIO,transformers.TAP_STEP,transformers.TAP_VOLTAGE_MAX,transformers.TAP_VOLTAGE_MIN,\n" + 
		 		"transformers.PHASE_DISPLACEMENT,transformers.IMPEDENCE_PERCENTAGE,transformers.LEAK_REACTANCE,transformers.RESISTANCE,transformers.REACTANCE,\n" + 
		 		"transformers.REMARKS,transformers.CREATED_BY,transformers.CREATED_DATE,transformers.MODIFIED_BY,transformers.MODIFIED_DATE,transformers.ENABLED\n" + 
		 		"FROM T_GC_TRANSFORMERS transformers\n" + 
		 		"LEFT JOIN V_CODES svoltagecode ON transformers.SECONDARY_VOLTAGE_CODE = svoltagecode.VALUE_CODE AND svoltagecode.LIST_CODE='VOLTAGE_CODE'\n" + 
		 		"LEFT JOIN V_CODES pvoltagecode ON transformers.PRIMARY_VOLTAGE_CODE = pvoltagecode.VALUE_CODE AND pvoltagecode.LIST_CODE='VOLTAGE_CODE' WHERE transformers.GC_ID=?";
		 
		 System.out.println(sql2);
		 transformers=jdbcOperations.query(sql2,new Object[]{id}, new TransformerMapper());
		 gc.setTransformers(transformers);
		 
		 List<QuantumAllocation> quantumAllocation=new ArrayList<QuantumAllocation>();
		 
		 String sql3= " SELECT quantamallocation.ID,quantamallocation.GC_ID,quantamallocation.BUYER_COMP_SERV_ID,quantamallocation.BUYER_ORG_ID,quantamallocation.CAPTIVE_COMPANY_NAME,\n" + 
		 		"		 		quantamallocation.QUANTUM,quantamallocation.SHARED_PERCENTAGE,INJECTING_VOLTAGE_CODE,involtagecode.VALUE_DESC AS INJECTION_VOLTAGE_NAME,quantamallocation.DRAWAL_VOLTAGE_CODE,	\n" + 
		 		"                drvoltagecode.VALUE_DESC AS DRAWAL_VOLTAGE_NAME,quantamallocation.REMARKS,quantamallocation.CREATED_BY,quantamallocation.CREATED_DATE,quantamallocation.MODIFIED_BY,quantamallocation.MODIFIED_DATE,quantamallocation.ENABLED,\n" + 
		 		"                companyservice.\"number\" as BUYER_COMP_SERV_NUMBER,companyservice.M_COMPANY_ID,companyservice.M_COMPANY_NAME\n" + 
		 		"		 		FROM T_GC_QUANTUM_ALLOCATION quantamallocation\n" + 
		 		"		 		LEFT JOIN V_CODES involtagecode ON quantamallocation.INJECTING_VOLTAGE_CODE = involtagecode.VALUE_CODE AND involtagecode.LIST_CODE='VOLTAGE_CODE'\n" + 
		 		"		 		LEFT JOIN V_CODES drvoltagecode ON quantamallocation.DRAWAL_VOLTAGE_CODE = drvoltagecode.VALUE_CODE AND drvoltagecode.LIST_CODE='VOLTAGE_CODE'\n" + 
		 		"                left join v_company_service companyservice on quantamallocation.BUYER_COMP_SERV_ID = companyservice.id WHERE quantamallocation.GC_ID=?";
				 
		 System.out.println(sql3);
		 quantumAllocation=jdbcOperations.query(sql3,new Object[]{id}, new QuantumAllocationMapper());
		 gc.setCaptiveQuantumAllocation(quantumAllocation);
		 
		 List<ApplicationStatus> applicationStatus=new ArrayList<ApplicationStatus>();
		 
		 String sql4="SELECT applicationstatus.ID,applicationstatus.GC_ID,applicationstatus.GC_STATUS_TYPE_CODE,applicationstatus.GC_STATUS_UPDATE_DATE,applicationstatus.GC_STATUS_UPDATE_BY,\n" + 
		 		"applicationstatus.GC_STATUS_REMARKS,applicationstatus.CREATED_BY,applicationstatus.CREATED_DATE,applicationstatus.MODIFIED_BY,applicationstatus.MODIFIED_DATE,\n" + 
		 		"applicationstatus.ENABLED\n" + 
		 		"FROM T_GC_APPLICATION_STATUS applicationstatus WHERE applicationstatus.GC_ID=?";
		 
		 System.out.println(sql4);
		 applicationStatus=jdbcOperations.query(sql4,new Object[]{id}, new ApplicationStatusMapper());
		 gc.setApplicationStatus(applicationStatus);
		 
		 List<Loan> idLoans=new ArrayList<Loan>();
		 
		 String sql5="SELECT loan.ID,loan.GC_ID,loan.LOAN_ORIGIN,loan.SOURCE_NAME,loan.SOURCE_ADDRESS,loan.LOAN_AMOUNT,loan.CURRENCY,loan.EXCHANGE_RATE,loan.REMARKS,\n" + 
		 		"loan.CREATED_BY,loan.CREATED_DATE,loan.MODIFIED_BY,loan.MODIFIED_DATE,loan.ENABLED\n" + 
		 		"FROM T_GC_LOAN  loan WHERE loan.GC_ID=?";
		 System.out.println(sql5);
		 idLoans=jdbcOperations.query(sql5,new Object[]{id}, new LoanMapper());
		 gc.setIdLoans(idLoans);
		 
		 List<CaptiveUserContribution> captiveUserContribution=new ArrayList<CaptiveUserContribution>();
		 
		 String sql6="SELECT tableb.ID,tableb.GC_ID,tableb.CLASS_OF_SHAREHOLDER,tableb.NUMBER_OF_EQUTIY_SHARES,tableb.VALUE_OF_EQUTIY_SHARES,tableb.AMOUNT_OF_EQUTIY_SHARES,\n" + 
		 		"tableb.NUMBER_OF_VOTING_RIGHTS,tableb.PERCT_IN_EQUTIY_SHARES,tableb.PERCT_IN_VOTING_RIGHTS,tableb.PERCT_IN_VOTING_WITH_EQUITY,tableb.REMARKS,\n" + 
		 		"tableb.CREATED_BY,tableb.CREATED_DATE,tableb.MODIFIED_BY,tableb.MODIFIED_DATE,tableb.ENABLED\n" + 
		 		"FROM T_GC_ID_TABLEB  tableb WHERE tableb.GC_ID=?";	
		 System.out.println(sql6);

		 captiveUserContribution=jdbcOperations.query(sql6,new Object[]{id}, new CaptiveUserContributionMapper());
		 gc.setIdCaptiveUserContributions(captiveUserContribution);
		 
		 List<EquityShareVotingRights> equityShareVotingRights=new ArrayList<EquityShareVotingRights>();
		 
		 String sql7="SELECT tablea.ID,tablea.GC_ID,tablea.CLASS_OF_EQUTIY_SHARES,tablea.NUMBER_OF_EQUTIY_SHARES,tablea.VALUE_OF_EQUTIY_SHARES,tablea.AMOUNT_OF_EQUTIY_SHARES,\n" + 
		 		"tablea.NUMBER_OF_VOTING_RIGHTS,tablea.PERCT_IN_EQUTIY_SHARES,tablea.PERCT_IN_VOTING_RIGHTS,tablea.PERCT_IN_VOTING_WITH_EQUITY,tablea.REMARKS,\n" + 
		 		"tablea.CREATED_BY,tablea.CREATED_DATE,tablea.MODIFIED_BY,tablea.MODIFIED_DATE,tablea.ENABLED\n" + 
		 		"FROM T_GC_ID_TABLEA tablea WHERE tablea.GC_ID=?";
		 System.out.println(sql7);
		 equityShareVotingRights=jdbcOperations.query(sql7,new Object[]{id}, new EquityShareVotingRightsMapper());
		 gc.setIdEquityShareVotingRights(equityShareVotingRights);
		 
		 List<DocCheckListItem> checkList=new ArrayList<DocCheckListItem>();
		 
		 String sql8="SELECT checklist.ID,checklist.GC_ID,checklist.CHECKLIST_CODE,checklist.IS_COMPLETE,checklist.COMPLETED_DT,checklist.REMARKS,checklist.CREATED_BY, \n" + 
		 		"		 		checklist.CREATED_DATE,checklist.MODIFIED_BY,checklist.MODIFIED_DATE,checklist.ENABLED,checklistcode.VALUE_DESC as CHECKLIST_NAME,checklist.IS_SUBMITTED\n" + 
		 		"		 		FROM T_GC_CHECKLIST checklist\n" + 
		 		"		 		left join v_codes checklistcode on checklist.CHECKLIST_CODE = checklistcode.VALUE_CODE AND checklistcode.LIST_CODE='CHECK_LIST_CODE'  WHERE checklist.GC_ID=?";
		 System.out.println(sql8);
		 checkList=jdbcOperations.query(sql8,new Object[]{id}, new DocCheckListItemMapper());
		 gc.setCheckList(checkList);

		 
		return gc;
	  		

}

@Override
public String addGc(GridConnectivity gc) {
	String result="";
	try {
		gc.setId(generateId());
		 
		String sql ="INSERT INTO T_GRID_CONNECTIVITY(APPLICATION_NUMBER, GENERATING_TYPE_NAME, PLANT_NAME, COMPANY_NAME, TEMP_HT_SUPPLY_NUMBER, M_SUBSTATION_ID, PROPOSED_COMMISSION_DT, VOLTAGE_CODE, \n" + 
				"								CLASS_VOLTAGE_PHASE, CLASS_VOLTAGE_FREQUENCY, AVAILED_HT_SUPPLY, AVAILED_HT_SUPPLY_NO,AVAILED_SANCTIONED_DEMAND, FUEL_TYPE_CODE, FUEL_LINKAGE_ARRANGED,FUEL_LINKAGE_DETAILS,\n" + 
				"								STATUS_CODE, LINE1, CITY, TOWN, STATE_CODE, PINCODE, VILLAGE, TALUK_CODE, \n" + 
				"								DISTRICT_CODE, PLS_SF_NO, PL_VILLAGE,PL_PINCODE,PL_TOWN, PL_TALUK_CODE, PL_DISTRICT_CODE, WIND_PASS_CODE, \n" + 
				"				                WIND_ZONE_AREA_CODE, SCO_TAMILNADU, SCO_MINISTRY, SCO_CIVIL, CG_INDUSTRY_TYPE, CG_SUPPORT_FUEL, CG_IS_PARALLEL_RUN, CG_IS_STANDBY, \n" + 
				"				                CG_CYCLE, CG_HAS_PROOF, PLANT_CAPACITY, ANNUAL_EXPECTED_QUANTUM, EXPECTED_CUF, AUXILIARY_CONSUMPTION, INDUSTRIAL_CONSUMPTION, PER_UNIT_COST,\n" + 
				"				                FIRM_POWER, INFIRM_POWER, PROPOSED_POWER_STB, PROPOSED_POWER_CAPTIVE, PROPOSED_POWER_3PTY, ID_TOTAL_COST,ID_TOTAL_CURRENCY, ID_TOTAL_EXCHANGE_RATE, \n" + 
				"				                ID_PROPOSED_DEBT_EQUITY_RATIO,FINAL_ORG_ID,FINAL_SUBSTATION_ID, FINAL_FEEDER_ID, FINAL_SS_TYPE_NAME,FINAL_FEEDER_TYPE_NAME, GEN_SERVICE_NUMBER, GEN_SERVICE_APPROVAL_NUMBER, \n" + 
				"				 				GEN_SERVICE_DATE, FINAL_COD, FINAL_COPD, FINAL_IS_STB, FINAL_IS_WHEELING,FINAL_PP_RATE, FINAL_STB_TARIFF_ORDER, FINAL_STB_TENDER_NUMBER, \n" + 
				"				 				FINAL_STB_TENDER_DATE, FINAL_WHEELING_FROM_DATE,FINAL_WHEELING_TO_DATE, REMARKS,M_ORG_ID, GENERATING_TYPE_ISCAPTIVE,METER_CT1,METER_CT2,\n" + 
				"								METER_CT3,METER_PT1,METER_PT2,METER_PT3,METER_NUMBER, METER_MAKE_CODE,ACCURACY_CLASS_CODE,IS_ABTMETER,\n" + 
				"								MF,MODEM_NUMBER,PARALLEL_OPERATION, ID)  VALUES\n" + 
				"								(?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,\n" + 
				"								 ?,?,?,?,?,?,?,?,\n" + 
				"				 				 ?,?,?,?,?,?,?,?,\n" + 
				"				 				 ?,?,?,?,?,?,?,?,\n" + 
				"				                 ?,?,?,?,?,?,?,?,\n" + 
				"				                 ?,?,?,?,?,?,?,?,\n" + 
				"		                         ?,?,?,?,?,?,?,?,\n" + 
				"				                 ?,?,?,?,?,?,?,?,\n" + 
				"				                 TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,?,  \n" + 
				"				                 TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,?,\n" + 
				"			                     ?,?,?,?,?,?,?,?,\n" + 
				"			                     ?,?,?,?)  ";
		
		if(jdbcOperations.update(sql,setGcValues(gc))>0) {
			result= gc.getId();
		}else {
			result = "Failue";
		}
		}catch(Exception e) {
			result= "Failue";
			e.printStackTrace();
			
	
		
	}
	
	return result;
}

@Override
public String updateGc(String id, GridConnectivity gc) {
	String result="";
	
	gc.setId(id);
	System.out.println("In update gc dao impl");
	System.out.println(gc);
	try {
		String sql ="UPDATE T_GRID_CONNECTIVITY\n" + 
				"SET APPLICATION_NUMBER=?, GENERATING_TYPE_NAME=?, PLANT_NAME=?, COMPANY_NAME=?, TEMP_HT_SUPPLY_NUMBER=?, M_SUBSTATION_ID=?,\n" + 
				"PROPOSED_COMMISSION_DT=TO_DATE(?,'YYYY-MM-DD'), VOLTAGE_CODE=?, CLASS_VOLTAGE_PHASE=?, CLASS_VOLTAGE_FREQUENCY=?, AVAILED_HT_SUPPLY=?,AVAILED_HT_SUPPLY_NO=?, AVAILED_SANCTIONED_DEMAND=?,\n" + 
				"FUEL_TYPE_CODE=?, FUEL_LINKAGE_ARRANGED=?, FUEL_LINKAGE_DETAILS=?, STATUS_CODE=?, LINE1=?, CITY=?, TOWN=?, STATE_CODE=?, PINCODE=?, VILLAGE=?,\n" + 
				"TALUK_CODE=?, DISTRICT_CODE=?, PLS_SF_NO=?, PL_VILLAGE=?,PL_PINCODE=?, PL_TOWN=?, PL_TALUK_CODE=?, PL_DISTRICT_CODE=?, WIND_PASS_CODE=?, \n" + 
				"WIND_ZONE_AREA_CODE=?, SCO_TAMILNADU=?, SCO_MINISTRY=?, SCO_CIVIL=?, CG_INDUSTRY_TYPE=?, CG_SUPPORT_FUEL=?, CG_IS_PARALLEL_RUN=?, CG_IS_STANDBY=?, \n" + 
				"CG_CYCLE=?, CG_HAS_PROOF=?, PLANT_CAPACITY=?, ANNUAL_EXPECTED_QUANTUM=?, EXPECTED_CUF=?, AUXILIARY_CONSUMPTION=?, INDUSTRIAL_CONSUMPTION=?,\n" + 
				"PER_UNIT_COST=?, FIRM_POWER=?, INFIRM_POWER=?, PROPOSED_POWER_STB=?, PROPOSED_POWER_CAPTIVE=?, PROPOSED_POWER_3PTY=?, ID_TOTAL_COST=?,\n" + 
				"ID_TOTAL_CURRENCY=?, ID_TOTAL_EXCHANGE_RATE=?, ID_PROPOSED_DEBT_EQUITY_RATIO=?, FINAL_ORG_ID=?,FINAL_SUBSTATION_ID=?, FINAL_FEEDER_ID=?,\n" + 
				"FINAL_SS_TYPE_NAME=?, FINAL_FEEDER_TYPE_NAME=?, GEN_SERVICE_NUMBER=?, GEN_SERVICE_APPROVAL_NUMBER=?, GEN_SERVICE_DATE=TO_DATE(?,'YYYY-MM-DD'),\n" + 
				"FINAL_COD=TO_DATE(?,'YYYY-MM-DD'), FINAL_COPD=TO_DATE(?,'YYYY-MM-DD'), FINAL_IS_STB=?, FINAL_IS_WHEELING=?, FINAL_PP_RATE=?, FINAL_STB_TARIFF_ORDER=?, FINAL_STB_TENDER_NUMBER=?, \n" + 
				"FINAL_STB_TENDER_DATE=TO_DATE(?,'YYYY-MM-DD'), FINAL_WHEELING_FROM_DATE=TO_DATE(?,'YYYY-MM-DD'), FINAL_WHEELING_TO_DATE=TO_DATE(?,'YYYY-MM-DD'), REMARKS=?, \n" + 
				"M_ORG_ID=?,GENERATING_TYPE_ISCAPTIVE=?,METER_CT1=?,METER_CT2=?,METER_CT3=?,METER_PT1=?,METER_PT2=?,METER_PT3=?,\n" + 
				"METER_NUMBER=?,METER_MAKE_CODE=?,ACCURACY_CLASS_CODE=?,IS_ABTMETER=?,MF=?,MODEM_NUMBER=?,PARALLEL_OPERATION=? WHERE ID=?";
		
		
				
				if(jdbcOperations.update(sql, setGcValues(gc)) > 0){
			result = gc.getId();				
		}
		else{
			result =  "FAILURE";
		}
	}catch(Exception e) {
		result = "FAILURE";
		e.printStackTrace();
	}
	return result;
	
}



private Object[] setGcValues(GridConnectivity gc) {
	
	
	return new Object[] {
	
			gc.getApplnNumber(),
			gc.getGeneratingTypeName(),
			gc.getPlantName(),
			gc.getCompanyName(),
			gc.getTempHtSupplyNumber(),
			gc.getSsId(),
			gc.getProposedCommissionDate(),
			gc.getSsVoltageCode(),
			gc.getClassVoltagePhase(),
			gc.getClassVoltageFrequency(),
			gc.getAvailedHtSupply(),
			gc.getAvailedHtSupplyNo(),
			gc.getAvailedSanctionedDemand(),
			gc.getFuelTypeCode(),
			gc.getFuelLinkageArranged(),
			gc.getFuelLinkageDetails(),
			gc.getStatusCode(),
			gc.getLine1(),
			gc.getCity(),
			gc.getTown(),
			gc.getState(),
			gc.getPinCode(),
			gc.getVillage(),
			gc.getTaluk(),
			gc.getDistrict(),
			gc.getPlSfNo(),
			gc.getPlVillage(),
			gc.getPlPinCode(),
			gc.getPlTown(),
			gc.getPlTalukCode(),
			gc.getPlDistrictCode(),
			gc.getPlWindPassCode(),
			gc.getPlWindZoneArea(),
			gc.getScoTamilNadu(),
			gc.getScoMinistry(),
			gc.getScoCivil(),
			gc.getCgIndustryType(),
			gc.getCgSupportFuel(),
			gc.getCgIsParallelRun(),
			gc.getCgIsStandBy(),
			gc.getCgCycle(),
			gc.getCgHasProof(),
			gc.getPlantCapacity(),
			gc.getAnnualExpectedQuantum(),
			gc.getExpectedCuf(),
			gc.getAuxiliaryConsumption(),
			gc.getIndustrialConsumption(),
			gc.getPerUnitCost(),
			gc.getFirmPower(),
			gc.getInfirmPower(),
			gc.getProposedPowerSTB(),
			gc.getProposedPowerCaptive(),
			gc.getProposedPower3PT(),
			gc.getIdTotalCost(),
			gc.getIdTotalCurrency(),
			gc.getIdTotalExchangeRate(),
			gc.getIdproposedDebtEquityRatio(),
			gc.getFinalOrgId(),
			gc.getFinalSsId(),
			gc.getFinalFeederId(),
			gc.getFinalSsTypeName(),
			gc.getFinalFeederTypeName(),
			gc.getGenServiceNumber(),
			gc.getGenServiceApprovalNumber(),
			gc.getGenServiceDate(),
			gc.getFinalCod(),
			gc.getFinalCopd(),
			gc.getFinalIsStb(),
			gc.getFinalIsWheeling(),
			gc.getFinalPpRate(),
			gc.getFinalStbTariffOrder(),
			gc.getFinalStbTenderNumber(),
			gc.getFinalStbTenderDate(),
			gc.getFinalWheelingFromDate(),
			gc.getFinalWheelingToDate(),
			gc.getRemarks(),
			gc.getOrgId(),
			gc.getGeneratingTypeIsCaptive(),
			gc.getMeterCt1(),
			gc.getMeterCt2(),
			gc.getMeterCt3(),
			gc.getMeterPt1(),
			gc.getMeterPt2(),
			gc.getMeterPt3(),
			gc.getMeterNumber(),
			gc.getMeterMakeCode(),
			gc.getAccuracyClassCode(),
			gc.getIsAbtMeter(),
			gc.getMultiplicationFactor(),
			gc.getModemNumber(),
			gc.getParallelOperation(),
			gc.getId()
	};	
}

@Override
public String addGenUnits(Generator genUnits) {
	
	String result="";
	try {
		genUnits.setId(generateId());
		String sql ="INSERT INTO T_GC_GEN_UNIT\n" + 
				"(GC_ID, NAME, CAPACITY, MAKE_CODE, MW_RATING, MV_RATING, KV_RATING, DAMPING_FACTOR, AMATEUR_RESISTANCE,\n" + 
				"DIRECT_ASSISTANCE_REACTANCE, NEGATIVE_SEQUENCE_REACTANCE, ZERO_SEQUENCE_REACTANCE, IS_CAPTIVE, WINDING_CONNECTION, \n" + 
				"MASS_NUMBER, STIFFNESS_COEFFICIENT, SERIAL_NO, ROTOR_DIA, HUB_HEIGHT, REMARKS, CREATED_BY, CREATED_DATE, MODIFIED_BY,\n" + 
				"MODIFIED_DATE, ENABLED,ID)\n" + 
				"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,TO_DATE(?,'yyyy-mm-dd'),?,?)";
		
		if(jdbcOperations.update(sql,setGenUnitValues(genUnits))>0) {
			result= genUnits.getId();
		}else {
			result = "Failure";
		}
		}catch(Exception e) {
			result= "Failure";
			e.printStackTrace();
		
	}
	System.out.println(genUnits);
	return result;
}

@Override
public String updateGenUnits(Generator genUnits) {
	String result="";
	
	try {
		String sql ="UPDATE T_GC_GEN_UNIT\n" + 
				"SET GC_ID=?, NAME=?, CAPACITY=?, MAKE_CODE=?, MW_RATING=?, MV_RATING=?, KV_RATING=?, DAMPING_FACTOR=?, AMATEUR_RESISTANCE=?,\n" + 
				"DIRECT_ASSISTANCE_REACTANCE=?, NEGATIVE_SEQUENCE_REACTANCE=?, ZERO_SEQUENCE_REACTANCE=?, IS_CAPTIVE=?, WINDING_CONNECTION=?, \n" + 
				"MASS_NUMBER=?, STIFFNESS_COEFFICIENT=?, SERIAL_NO=?, ROTOR_DIA=?, HUB_HEIGHT=?, REMARKS=?, CREATED_BY=?, CREATED_DATE=TO_DATE(?,'yyyy-mm-dd'),\n" + 
				"MODIFIED_BY=?, MODIFIED_DATE=TO_DATE(?,'yyyy-mm-dd'), ENABLED=?\n" + 
				"WHERE ID=?";
		
				
				if(jdbcOperations.update(sql, setGenUnitValues(genUnits)) > 0){
			result = genUnits.getId();				
		}
		else{
			result =  "FAILURE";
		}
	}catch(Exception e) {
		result = "FAILURE";
		e.printStackTrace();
	}
	return result;
	
}

private Object[] setGenUnitValues(Generator genUnits) {
	return new Object[] {		
			
			genUnits.getGcId(),
			genUnits.getName(),
			genUnits.getCapacity(),
			genUnits.getMakeCode(),
			genUnits.getMwRating(),
			genUnits.getMvRating(),
			genUnits.getKvRating(),
			genUnits.getDampingFactor(),
			genUnits.getAmateurResistance(),
			genUnits.getDirectAssistanceReactance(),
			genUnits.getNegativeSequenceReactance(),
			genUnits.getZeroSequenceReactance(),
			genUnits.getIsCaptive(),
			genUnits.getWindingConnection(),
			genUnits.getMassNumber(),
			genUnits.getStiffnessCoefficient(),
			genUnits.getSerialNumber(),
			genUnits.getRotorDia(),
			genUnits.getHubHeight(),
			genUnits.getRemarks(),
			genUnits.getCreatedBy(),
			genUnits.getCreatedDate(),
			genUnits.getModifiedBy(),
			genUnits.getModifiedDate(),
			genUnits.getEnabled(),

			genUnits.getId()			    
	};
}

@Override
public String addTransformers(Transformer transformers) {
	String result="";
	try {
		transformers.setId(generateId());
		String sql ="INSERT INTO T_GC_TRANSFORMERS\n" + 
				"(GC_ID, NAME, MVA_RATING, PRIMARY_VOLTAGE_CODE, SECONDARY_VOLTAGE_CODE, COOLING_TYPE, WINDING_CONFIGURATION, BREAKER_RATING, \n" + 
				"TAP_SETTING, TAP_NUMBER_MAX, TAP_NUMBER_MIN, TAP_RATIO, TAP_STEP, TAP_VOLTAGE_MAX, TAP_VOLTAGE_MIN, PHASE_DISPLACEMENT, IMPEDENCE_PERCENTAGE, \n" + 
				"LEAK_REACTANCE, RESISTANCE, REACTANCE, REMARKS, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, ENABLED,ID)\n" + 
				"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,TO_DATE(?,'yyyy-mm-dd'),?,?)";
		
		if(jdbcOperations.update(sql,setTransformerValues(transformers))>0) {
			result= transformers.getId();
		}else {
			result = "Failure";
		}
		}catch(Exception e) {
			result= "Failure";
			e.printStackTrace();
		
	}
	return result;
}

@Override
public String updateTransformers(Transformer transformers) {
	String result="";
	
	try {
		String sql ="UPDATE T_GC_TRANSFORMERS\n" + 
				"SET GC_ID=?, NAME=?, MVA_RATING=?, PRIMARY_VOLTAGE_CODE=?, SECONDARY_VOLTAGE_CODE=?, COOLING_TYPE=?,\n" + 
				"WINDING_CONFIGURATION=?, BREAKER_RATING=?, TAP_SETTING=?, TAP_NUMBER_MAX=?, TAP_NUMBER_MIN=?, TAP_RATIO=?,\n" + 
				"TAP_STEP=?, TAP_VOLTAGE_MAX=?, TAP_VOLTAGE_MIN=?, PHASE_DISPLACEMENT=?, IMPEDENCE_PERCENTAGE=?, LEAK_REACTANCE=?, \n" + 
				"RESISTANCE=?, REACTANCE=?, REMARKS=?, CREATED_BY=?, CREATED_DATE=TO_DATE(?,'yyyy-mm-dd'), MODIFIED_BY=?, MODIFIED_DATE=TO_DATE(?,'yyyy-mm-dd'), ENABLED=?\n" + 
				"WHERE ID=?";
		
				
				if(jdbcOperations.update(sql, setTransformerValues(transformers)) > 0){
			result = transformers.getId();				
		}
		else{
			result =  "FAILURE";
		}
	}catch(Exception e) {
		result = "FAILURE";
		e.printStackTrace();
	}
	return result;
	
}

private Object[] setTransformerValues(Transformer transformers) {
	return new Object[] {		
			
			transformers.getGcId(),
			transformers.getName(),
			transformers.getMvaRating(),
			transformers.getPrimaryVoltageCode(),
			transformers.getSecondaryVoltageCode(),
			transformers.getCoolingType(),
			transformers.getWindingConfig(),
			transformers.getBreakerRating(),
			transformers.getTapSetting(),
			transformers.getTapNumberMax(),
			transformers.getTapNumberMin(),
			transformers.getTapRatio(),
			transformers.getTapStep(),
			transformers.getTapVoltageMax(),
			transformers.getTapVoltageMin(),
			transformers.getPhaseDisplacement(),
			transformers.getImpedencePercentage(),
			transformers.getLeakReact(),
			transformers.getResistance(),
			transformers.getReact(),			
			transformers.getRemarks(),
			transformers.getCreatedBy(),
			transformers.getCreatedDate(),
			transformers.getModifiedBy(),
			transformers.getModifiedDate(),
			transformers.getEnabled(),

			transformers.getId()			    
	};
}

@Override
public String addQuantumAllocation(QuantumAllocation quantumAllocation) {
	String result="";
	try {
		quantumAllocation.setId(generateId());
		String sql ="INSERT INTO T_GC_QUANTUM_ALLOCATION\n" + 
				"(GC_ID, BUYER_COMP_SERV_ID, BUYER_ORG_ID, CAPTIVE_COMPANY_NAME, QUANTUM, INJECTING_VOLTAGE_CODE, DRAWAL_VOLTAGE_CODE, REMARKS,\n" + 
				"CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, ENABLED,SHARED_PERCENTAGE, ID)\n" + 
				"VALUES(?,?,?,?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,TO_DATE(?,'yyyy-mm-dd'),?,?,?)";
		
		if(jdbcOperations.update(sql,setQuantumAllocation(quantumAllocation))>0) {
			result= quantumAllocation.getId();
		}else {
			result = "Failure";
		}
		}catch(Exception e) {
			result= "Failure";
			e.printStackTrace();
		
	}
	return result;
}

@Override
public String updateQuantumAllocation( QuantumAllocation quantumAllocation) {
	String result="";
	
	try {
		String sql ="UPDATE T_GC_QUANTUM_ALLOCATION\n" + 
				"SET GC_ID=?, BUYER_COMP_SERV_ID=?, BUYER_ORG_ID=?, CAPTIVE_COMPANY_NAME=?, QUANTUM=?, INJECTING_VOLTAGE_CODE=?,\n" + 
				"DRAWAL_VOLTAGE_CODE=?, REMARKS=?, CREATED_BY=?, CREATED_DATE=TO_DATE(?,'yyyy-mm-dd'), MODIFIED_BY=?, MODIFIED_DATE=TO_DATE(?,'yyyy-mm-dd'), ENABLED=?, SHARED_PERCENTAGE=?\n" + 
				"WHERE ID=?";
		
				
				if(jdbcOperations.update(sql, setQuantumAllocation(quantumAllocation)) > 0){
			result = quantumAllocation.getId();				
		}
		else{
			result =  "FAILURE";
		}
	}catch(Exception e) {
		result = "FAILURE";
		e.printStackTrace();
	}
	return result;
	
}


private Object[] setQuantumAllocation(QuantumAllocation quantumAllocation) {
	return new Object[] {		
			
			quantumAllocation.getGcId(),
			quantumAllocation.getBuyerCompServiceId(),
			quantumAllocation.getBuyerOrgId(),
			quantumAllocation.getCaptiveCompanyName(),
			quantumAllocation.getQuantum(),
			quantumAllocation.getInjectionVoltageCode(),
			quantumAllocation.getDrawalVoltageCode(),
			quantumAllocation.getRemarks(),
			quantumAllocation.getCreatedBy(),
			quantumAllocation.getCreatedDate(),
			quantumAllocation.getModifiedBy(),
			quantumAllocation.getModifiedDate(),
			quantumAllocation.getEnabled(),
			quantumAllocation.getSharedPercentage(),

			quantumAllocation.getId()			    
	};
}

@Override
public String addApplicationStatus(ApplicationStatus applicationStatus) {
	String result="";
	try {
		applicationStatus.setId(generateId());
		String sql ="INSERT INTO T_GC_APPLICATION_STATUS\n" + 
				"(GC_ID, GC_STATUS_TYPE_CODE, GC_STATUS_UPDATE_DATE, GC_STATUS_UPDATE_BY, GC_STATUS_REMARKS, CREATED_BY,\n" + 
				"CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, ENABLED,ID)\n" + 
				"VALUES(?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,TO_DATE(?,'yyyy-mm-dd'),?,?)";
		
		if(jdbcOperations.update(sql,setApplicationStatus(applicationStatus))>0) {
			result= applicationStatus.getId();
		}else {
			result = "Failure";
		}
		}catch(Exception e) {
			result= "Failure";
			e.printStackTrace();
		
	}
	return result;
}

@Override
public String updateApplicationStatus( ApplicationStatus applicationStatus) {
	String result="";
	
	try {
		String sql ="UPDATE T_GC_APPLICATION_STATUS\n" + 
				"SET GC_ID=?, GC_STATUS_TYPE_CODE=?, GC_STATUS_UPDATE_DATE=?, GC_STATUS_UPDATE_BY=?, \n" + 
				"GC_STATUS_REMARKS=?, CREATED_BY=?, CREATED_DATE=TO_DATE(?,'yyyy-mm-dd'), MODIFIED_BY=?, MODIFIED_DATE=TO_DATE(?,'yyyy-mm-dd'), ENABLED=?\n" + 
				"WHERE ID=?";
		
				
				if(jdbcOperations.update(sql, setApplicationStatus(applicationStatus)) > 0){
			result = applicationStatus.getId();				
		}
		else{
			result =  "FAILURE";
		}
	}catch(Exception e) {
		result = "FAILURE";
		e.printStackTrace();
	}
	return result;
	
}

private Object[] setApplicationStatus(ApplicationStatus applicationStatus) {
	return new Object[] {		
			
			applicationStatus.getGcId(),
			applicationStatus.getGcStatusTypeCode(),
			applicationStatus.getGcStatusUpdateDate(),
			applicationStatus.getGcStatusUpdateBy(),
			applicationStatus.getGcStatusRemarks(),
			applicationStatus.getCreatedBy(),
			applicationStatus.getCreatedDate(),
			applicationStatus.getModifiedBy(),
			applicationStatus.getModifiedDate(),
			applicationStatus.getEnabled(),

			applicationStatus.getId()			    
	};
}

@Override
public String addLoan(Loan idLoans) {
	String result="";
	try {
		idLoans.setId(generateId());
		String sql ="INSERT INTO T_GC_LOAN\n" + 
				"( GC_ID, LOAN_ORIGIN, SOURCE_NAME, SOURCE_ADDRESS, LOAN_AMOUNT, CURRENCY, EXCHANGE_RATE, REMARKS,\n" + 
				"CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, ENABLED, ID)\n" + 
				"VALUES(?,?,?,?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,TO_DATE(?,'yyyy-mm-dd'),?,?)";
		
		if(jdbcOperations.update(sql,setLoan(idLoans))>0) {
			result= idLoans.getId();
		}else {
			result = "Failure";
		}
		}catch(Exception e) {
			result= "Failure";
			e.printStackTrace();
		
	}
	return result;
}

@Override
public String updateLoan( Loan idLoans) {
	String result="";

	try {
		String sql ="UPDATE T_GC_LOAN\n" + 
				"SET GC_ID=?, LOAN_ORIGIN=?, SOURCE_NAME=?, SOURCE_ADDRESS=?, LOAN_AMOUNT=?, CURRENCY=?, EXCHANGE_RATE=?, \n" + 
				"REMARKS=?, CREATED_BY=?, CREATED_DATE=TO_DATE(?,'yyyy-mm-dd'), MODIFIED_BY=?, MODIFIED_DATE=TO_DATE(?,'yyyy-mm-dd'), ENABLED=?\n" + 
				"WHERE ID=?";
		
				
				if(jdbcOperations.update(sql, setLoan(idLoans)) > 0){
			result = idLoans.getId();				
		}
		else{
			result =  "FAILURE";
		}
	}catch(Exception e) {
		result = "FAILURE";
		e.printStackTrace();
	}
	return result;
	
}

private Object[] setLoan(Loan idLoans) {
	return new Object[] {		
			
			idLoans.getGcId(),
			idLoans.getLoanOrigin(),
			idLoans.getLoanSourceName(),
			idLoans.getLoanSourceAddress(),
			idLoans.getLoanAmount(),
			idLoans.getLoanCurrency(),
			idLoans.getLoanExchangeRate(),
			idLoans.getRemarks(),

			idLoans.getCreatedBy(),
			idLoans.getCreatedDate(),
			idLoans.getModifiedBy(),
			idLoans.getModifiedDate(),
			idLoans.getEnabled(),

			idLoans.getId()			    
	};
}

@Override
public String addCaptiveUserContribution(CaptiveUserContribution captiveUserContribution) {
	String result="";
	try {
		captiveUserContribution.setId(generateId());
		String sql ="INSERT INTO T_GC_ID_TABLEB\n" + 
				"(GC_ID, CLASS_OF_SHAREHOLDER, NUMBER_OF_EQUTIY_SHARES, VALUE_OF_EQUTIY_SHARES, \n" + 
				"AMOUNT_OF_EQUTIY_SHARES, NUMBER_OF_VOTING_RIGHTS, PERCT_IN_EQUTIY_SHARES, PERCT_IN_VOTING_RIGHTS, PERCT_IN_VOTING_WITH_EQUITY,\n" + 
				"REMARKS, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, ENABLED, ID)\n" + 
				"VALUES(?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,TO_DATE(?,'yyyy-mm-dd'),?,?)";
		
		if(jdbcOperations.update(sql,setCaptiveUserContribution(captiveUserContribution))>0) {
			result= captiveUserContribution.getId();
		}else {
			result = "Failure";
		}
		}catch(Exception e) {
			result= "Failure";
			e.printStackTrace();
		
	}
	return result;
}

@Override
public String updateCaptiveUserContribution(CaptiveUserContribution captiveUserContribution) {
	String result="";

	try {
		String sql ="UPDATE T_GC_ID_TABLEB\n" + 
				"SET GC_ID=?, CLASS_OF_SHAREHOLDER=?, NUMBER_OF_EQUTIY_SHARES=?, VALUE_OF_EQUTIY_SHARES=?, AMOUNT_OF_EQUTIY_SHARES=?\n" + 
				", NUMBER_OF_VOTING_RIGHTS=?, PERCT_IN_EQUTIY_SHARES=?, PERCT_IN_VOTING_RIGHTS=?, PERCT_IN_VOTING_WITH_EQUITY=?, REMARKS=?,\n" + 
				"CREATED_BY=?, CREATED_DATE=TO_DATE(?,'yyyy-mm-dd'), MODIFIED_BY=?, MODIFIED_DATE=TO_DATE(?,'yyyy-mm-dd'), ENABLED=?\n" + 
				"WHERE ID=?";
		
				
				if(jdbcOperations.update(sql, setCaptiveUserContribution(captiveUserContribution)) > 0){
			result = captiveUserContribution.getId();				
		}
		else{
			result =  "FAILURE";
		}
	}catch(Exception e) {
		result = "FAILURE";
		e.printStackTrace();
	}
	return result;
	
}

private Object[] setCaptiveUserContribution(CaptiveUserContribution captiveUserContribution) {
	return new Object[] {		
			
			captiveUserContribution.getGcId(),
			captiveUserContribution.getClassOfShareHolder(),
			captiveUserContribution.getNoOfEquityShares(),
			captiveUserContribution.getValueOfEquityShares(),
			captiveUserContribution.getAmountOfEquityShares(),
			captiveUserContribution.getNoOfVotingRights(),
			captiveUserContribution.getPercentageHoldingInEquityShares(),
			captiveUserContribution.getPercentageHoldingInVotingRights(),
			captiveUserContribution.getPercentageHoldingInVotingWithEquity(),
			
			captiveUserContribution.getRemarks(),
			captiveUserContribution.getCreatedBy(),
			captiveUserContribution.getCreatedDate(),
			captiveUserContribution.getModifiedBy(),
			captiveUserContribution.getModifiedDate(),
			captiveUserContribution.getEnabled(),

			captiveUserContribution.getId()			    
	};
}

@Override
public String addEquityShareVotingRights(EquityShareVotingRights equityShareVotingRights) {
	String result="";
	try {
		equityShareVotingRights.setId(generateId());
		String sql ="INSERT INTO T_GC_ID_TABLEA\n" + 
				"(GC_ID, CLASS_OF_EQUTIY_SHARES, NUMBER_OF_EQUTIY_SHARES, VALUE_OF_EQUTIY_SHARES, AMOUNT_OF_EQUTIY_SHARES, NUMBER_OF_VOTING_RIGHTS, \n" + 
				"PERCT_IN_EQUTIY_SHARES, PERCT_IN_VOTING_RIGHTS, PERCT_IN_VOTING_WITH_EQUITY, REMARKS, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, ENABLED, ID)\n" + 
				"VALUES(?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,TO_DATE(?,'yyyy-mm-dd'),?,?)";
		
		if(jdbcOperations.update(sql,setEquityShareVotingRights(equityShareVotingRights))>0) {
			result= equityShareVotingRights.getId();
		}else {
			result = "Failure";
		}
		}catch(Exception e) {
			result= "Failure";
			e.printStackTrace();
		
	}
	return result;
}

@Override
public String updateEquityShareVotingRights(EquityShareVotingRights equityShareVotingRights) {
	String result="";
	
	try {
		String sql ="UPDATE T_GC_ID_TABLEA\n" + 
				"SET GC_ID=?, CLASS_OF_EQUTIY_SHARES=?, NUMBER_OF_EQUTIY_SHARES=?, VALUE_OF_EQUTIY_SHARES=?, AMOUNT_OF_EQUTIY_SHARES=?,\n" + 
				"NUMBER_OF_VOTING_RIGHTS=?, PERCT_IN_EQUTIY_SHARES=?, PERCT_IN_VOTING_RIGHTS=?, PERCT_IN_VOTING_WITH_EQUITY=?, REMARKS=?,\n" + 
				"CREATED_BY=?, CREATED_DATE=TO_DATE(?,'yyyy-mm-dd'), MODIFIED_BY=?, MODIFIED_DATE=TO_DATE(?,'yyyy-mm-dd'), ENABLED=?\n" + 
				"WHERE ID=?";
		
				
				if(jdbcOperations.update(sql, setEquityShareVotingRights(equityShareVotingRights)) > 0){
			result = equityShareVotingRights.getId();				
		}
		else{
			result =  "FAILURE";
		}
	}catch(Exception e) {
		result = "FAILURE";
		e.printStackTrace();
	}
	return result;
	
}

private Object[] setEquityShareVotingRights(EquityShareVotingRights equityShareVotingRights) {
	return new Object[] {		
			
			equityShareVotingRights.getGcId(),
			equityShareVotingRights.getClassOfEquityShares(),
			equityShareVotingRights.getNoOfEquityShares(),
			equityShareVotingRights.getValueOfEquityShares(),
			equityShareVotingRights.getAmountOfEquityShares(),
			equityShareVotingRights.getNoOfVotingRights(),
			equityShareVotingRights.getPercentageHoldingInEquityShares(),
			equityShareVotingRights.getPercentageHoldingInVotingRights(),
			equityShareVotingRights.getPercentageHoldingInVotingWithEquity(),
						
			equityShareVotingRights.getRemarks(),
			equityShareVotingRights.getCreatedBy(),
			equityShareVotingRights.getCreatedDate(),
			equityShareVotingRights.getModifiedBy(),
			equityShareVotingRights.getModifiedDate(),
			equityShareVotingRights.getEnabled(),

			equityShareVotingRights.getId()			    
	};
}

@Override
public String addDocCheckListItem(DocCheckListItem checkList) {
	String result="";
	try {
		checkList.setId(generateId());
		String sql ="INSERT INTO T_GC_CHECKLIST\n" + 
				"(GC_ID, CHECKLIST_CODE, IS_COMPLETE, COMPLETED_DT, REMARKS, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, ENABLED, IS_SUBMITTED, ID)\n" + 
				"VALUES(?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,?,TO_DATE(?,'yyyy-mm-dd'),?,TO_DATE(?,'yyyy-mm-dd'),?,?,?)";
		if(jdbcOperations.update(sql,setDocCheckListItem(checkList))>0) {
			result= checkList.getId();
			System.out.println("add checklist");

			System.out.println(checkList);

		}else {
			result = "Failure";
		}
		}catch(Exception e) {
			result= "Failure";
			e.printStackTrace();
		
	}
	return result;
}

@Override
public String updateDocCheckListItem(DocCheckListItem checkList) {
	String result="";
//	checkList.setId(id);
	try {
		String sql ="UPDATE T_GC_CHECKLIST\n" + 
				"SET GC_ID=?, CHECKLIST_CODE=?, IS_COMPLETE=?, COMPLETED_DT=TO_DATE(?,'yyyy-mm-dd'), REMARKS=?, CREATED_BY=?, CREATED_DATE=TO_DATE(?,'yyyy-mm-dd'),\n" + 
				"MODIFIED_BY=?, MODIFIED_DATE=TO_DATE(?,'yyyy-mm-dd'), ENABLED=?, IS_SUBMITTED=?\n" + 
				"WHERE ID=?";	
		System.out.println("ChecklIST SQL AND ID");

			System.out.println(sql);
//			System.out.println(id);

				if(jdbcOperations.update(sql, setDocCheckListItem(checkList)) > 0){
			result = checkList.getId();	
			System.out.println("checklist GC impl");

			System.out.println(checkList);
		}
		else{
			result =  "FAILURE";
		}
	}catch(Exception e) {
		result = "FAILURE";
		e.printStackTrace();
	}
	return result;
	
}

private Object[] setDocCheckListItem(DocCheckListItem checkList) {
	return new Object[] {		
			
			checkList.getGcId(),
			checkList.getDocumentCode(),
			checkList.getIsComplete(),
			checkList.getCompletedDate(),						
			checkList.getRemarks(),
			checkList.getCreatedBy(),
			checkList.getCreatedDate(),
			checkList.getModifiedBy(),
			checkList.getModifiedDate(),
			checkList.getEnabled(),
			checkList.getDocSubmitted(),
			checkList.getId()	

	};

}
	
final class GcMapper implements RowMapper<GridConnectivity>{
	
	public  GcMapper() {
		super();
	}
	
	@Override
	public GridConnectivity mapRow(ResultSet resultSet, int rownum) throws SQLException {
		GridConnectivity gc= new GridConnectivity();
		
		
		
			gc.setApplnNumber(resultSet.getString("APPLICATION_NUMBER"));
			gc.setGeneratingTypeName(resultSet.getString("GENERATING_TYPE_NAME"));
			gc.setGeneratingTypeIsCaptive(resultSet.getString("GENERATING_TYPE_ISCAPTIVE"));
			gc.setPlantName(resultSet.getString("PLANT_NAME"));
	        gc.setCompanyName(resultSet.getString("COMPANY_NAME"));
	        gc.setTempHtSupplyNumber(resultSet.getString("TEMP_HT_SUPPLY_NUMBER"));
	        gc.setSsId(resultSet.getString("M_SUBSTATION_ID"));
	        gc.setSsName(resultSet.getString("M_SUBSTATION_NAME"));
	        gc.setProposedCommissionDate(resultSet.getString("PROPOSED_COMMISSION_DT"));
	        gc.setSsVoltageCode(resultSet.getString("VOLTAGE_CODE"));
	        gc.setSsVoltageName(resultSet.getString("VOLTAGE_NAME"));
	        gc.setClassVoltagePhase(resultSet.getString("CLASS_VOLTAGE_PHASE"));
	        gc.setClassVoltageFrequency(resultSet.getString("CLASS_VOLTAGE_FREQUENCY"));
	        gc.setFuelTypeCode(resultSet.getString("FUEL_TYPE_CODE"));
	        gc.setFuelTypeName(resultSet.getString("FUEL_TYPE_NAME"));

	        gc.setFuelLinkageArranged(resultSet.getString("FUEL_LINKAGE_ARRANGED"));
	        gc.setFuelLinkageDetails(resultSet.getString("FUEL_LINKAGE_DETAILS"));
	        gc.setStatusCode(resultSet.getString("STATUS_CODE"));
	        gc.setLine1(resultSet.getString("LINE1"));
	        gc.setCity(resultSet.getString("CITY"));
	        gc.setTown(resultSet.getString("TOWN"));
			gc.setState(resultSet.getString("STATE_CODE"));

	        gc.setPinCode(resultSet.getString("PINCODE"));
			gc.setVillage(resultSet.getString("VILLAGE"));
			gc.setTaluk(resultSet.getString("TALUK_CODE"));
			gc.setDistrict(resultSet.getString("DISTRICT_CODE"));
			gc.setPlSfNo(resultSet.getString("PLS_SF_NO"));
			gc.setPlVillage(resultSet.getString("PL_VILLAGE"));
			gc.setPlPinCode(resultSet.getString("PL_PINCODE"));
			gc.setPlTown(resultSet.getString("PL_TOWN"));
			gc.setPlTalukCode(resultSet.getString("PL_TALUK_CODE"));
			gc.setPlTalukName(resultSet.getString("PL_TALUK_NAME"));

			gc.setPlDistrictCode(resultSet.getString("PL_DISTRICT_CODE"));
			gc.setPlDistrictName(resultSet.getString("PL_DISTRICT_NAME"));

			gc.setPlWindPassCode(resultSet.getString("WIND_PASS_CODE"));
			gc.setPlWindPassName(resultSet.getString("WIND_PASS_NAME"));

			gc.setPlWindZoneArea(resultSet.getString("WIND_ZONE_AREA_CODE"));
			gc.setCgIndustryType(resultSet.getString("CG_INDUSTRY_TYPE"));
			gc.setCgSupportFuel(resultSet.getString("CG_SUPPORT_FUEL"));
			gc.setCgIsParallelRun(resultSet.getString("CG_IS_PARALLEL_RUN"));
			gc.setCgIsStandBy(resultSet.getString("CG_IS_STANDBY"));
			gc.setCgCycle(resultSet.getString("CG_CYCLE"));
			gc.setCgHasProof(resultSet.getString("CG_HAS_PROOF"));
			gc.setPlantCapacity(resultSet.getString("PLANT_CAPACITY"));
			gc.setAnnualExpectedQuantum(resultSet.getString("ANNUAL_EXPECTED_QUANTUM"));
			gc.setExpectedCuf(resultSet.getString("EXPECTED_CUF"));
			gc.setAuxiliaryConsumption(resultSet.getString("AUXILIARY_CONSUMPTION"));	
			gc.setIndustrialConsumption(resultSet.getString("INDUSTRIAL_CONSUMPTION"));
			gc.setPerUnitCost(resultSet.getString("PER_UNIT_COST"));
			gc.setFirmPower(resultSet.getString("FIRM_POWER"));
			gc.setInfirmPower(resultSet.getString("INFIRM_POWER"));		
			gc.setProposedPowerSTB(resultSet.getString("PROPOSED_POWER_STB"));
			gc.setProposedPowerCaptive(resultSet.getString("PROPOSED_POWER_CAPTIVE"));
			gc.setProposedPower3PT(resultSet.getString("PROPOSED_POWER_3PTY"));			
			gc.setIdTotalCost(resultSet.getString("ID_TOTAL_COST"));
			gc.setIdTotalCurrency(resultSet.getString("ID_TOTAL_CURRENCY"));
			gc.setIdTotalExchangeRate(resultSet.getString("ID_TOTAL_EXCHANGE_RATE"));
			gc.setIdproposedDebtEquityRatio(resultSet.getString("ID_PROPOSED_DEBT_EQUITY_RATIO"));
			gc.setFinalOrgId(resultSet.getString("FINAL_ORG_ID"));
			gc.setFinalSsId(resultSet.getString("FINAL_SUBSTATION_ID"));
			gc.setFinalFeederId(resultSet.getString("FINAL_FEEDER_ID"));
			gc.setFinalSsTypeName(resultSet.getString("FINAL_SS_TYPE_NAME"));
			gc.setFinalFeederTypeName(resultSet.getString("FINAL_FEEDER_TYPE_NAME"));
			gc.setGenServiceNumber(resultSet.getString("GEN_SERVICE_NUMBER"));
			gc.setGenServiceApprovalNumber(resultSet.getString("GEN_SERVICE_APPROVAL_NUMBER"));
			gc.setGenServiceDate(resultSet.getString("GEN_SERVICE_DATE"));
			gc.setFinalIsStb(resultSet.getString("FINAL_IS_STB"));
			gc.setFinalIsWheeling(resultSet.getString("FINAL_IS_WHEELING"));
			gc.setFinalPpRate(resultSet.getString("FINAL_PP_RATE"));
			gc.setFinalStbTariffOrder(resultSet.getString("FINAL_STB_TARIFF_ORDER"));		
			gc.setFinalStbTenderNumber(resultSet.getString("FINAL_STB_TENDER_NUMBER"));
			gc.setFinalStbTenderDate(resultSet.getString("FINAL_STB_TENDER_DATE"));
			gc.setFinalWheelingFromDate(resultSet.getString("FINAL_WHEELING_FROM_DATE"));
			gc.setFinalWheelingToDate(resultSet.getString("FINAL_WHEELING_TO_DATE"));			
			gc.setAvailedHtSupply(resultSet.getString("AVAILED_HT_SUPPLY"));
			gc.setAvailedHtSupplyNo(resultSet.getString("AVAILED_HT_SUPPLY_NO"));
			gc.setAvailedSanctionedDemand(resultSet.getString("AVAILED_SANCTIONED_DEMAND"));
			gc.setScoTamilNadu(resultSet.getString("SCO_TAMILNADU"));
			gc.setScoMinistry(resultSet.getString("SCO_MINISTRY"));
			gc.setScoCivil(resultSet.getString("SCO_CIVIL"));
			gc.setFinalCod(resultSet.getString("FINAL_COD"));
			gc.setFinalCopd(resultSet.getString("FINAL_COPD"));
			gc.setRemarks(resultSet.getString("REMARKS"));			
//			gc.setCreatedBy(resultSet.getString("CREATED_BY"));
//			gc.setCreatedDate(resultSet.getString("CREATED_DATE"));
//			gc.setModifiedBy(resultSet.getString("MODIFIED_BY"));
//			gc.setModifiedDate(resultSet.getString("MODIFIED_DATE"));
//			gc.setEnabled(resultSet.getString("ENABLED"));
			gc.setOrgName(resultSet.getString("M_ORG_NAME"));
			gc.setOrgId(resultSet.getString("M_ORG_ID"));
			gc.setFinalOrgName(resultSet.getString("FINAL_ORG_NAME"));
			gc.setFinalSsName(resultSet.getString("FINAL_SUBSTATION_NAME"));
			gc.setFinalFeederName(resultSet.getString("FINAL_FEEDER_NAME"));
			gc.setMeterCt1(resultSet.getString("METER_CT1"));
			gc.setMeterCt2(resultSet.getString("METER_CT2"));
			gc.setMeterCt3(resultSet.getString("METER_CT3"));
			gc.setMeterPt1(resultSet.getString("METER_PT1"));
			gc.setMeterPt2(resultSet.getString("METER_PT2"));
			gc.setMeterPt3(resultSet.getString("METER_PT3"));
			gc.setMeterNumber(resultSet.getString("METER_NUMBER"));
			gc.setMeterMakeCode(resultSet.getString("METER_MAKE_CODE"));
			gc.setAccuracyClassCode(resultSet.getString("ACCURACY_CLASS_CODE"));
			gc.setIsAbtMeter(resultSet.getString("IS_ABTMETER"));
			gc.setMultiplicationFactor(resultSet.getString("MF"));
			gc.setModemNumber(resultSet.getString("MODEM_NUMBER"));
			gc.setParallelOperation(resultSet.getString("PARALLEL_OPERATION"));
			
	        gc.setId(resultSet.getString("ID"));
        return 	gc ;
	}
}
final class GeneratorUnitsMapper implements RowMapper<Generator>{

	public GeneratorUnitsMapper() {
		super();
	}
	@Override
	public Generator mapRow(ResultSet resultSet, int rownum) throws SQLException {
		Generator genUnits = new Generator();
		
		
		genUnits.setGcId(resultSet.getString("GC_ID"));
        genUnits.setName(resultSet.getString("NAME"));
		genUnits.setCapacity(resultSet.getString("CAPACITY"));
		genUnits.setMakeCode(resultSet.getString("MAKE_CODE"));
		genUnits.setMwRating(resultSet.getString("MW_RATING"));
		genUnits.setMvRating(resultSet.getString("MV_RATING"));
		genUnits.setKvRating(resultSet.getString("KV_RATING"));
		genUnits.setDampingFactor(resultSet.getString("DAMPING_FACTOR"));
		genUnits.setAmateurResistance(resultSet.getString("AMATEUR_RESISTANCE"));
		genUnits.setDirectAssistanceReactance(resultSet.getString("DIRECT_ASSISTANCE_REACTANCE"));
		genUnits.setNegativeSequenceReactance(resultSet.getString("NEGATIVE_SEQUENCE_REACTANCE"));
		genUnits.setZeroSequenceReactance( resultSet.getString("ZERO_SEQUENCE_REACTANCE"));
		genUnits.setIsCaptive(resultSet.getString("IS_CAPTIVE"));
		genUnits.setWindingConnection(resultSet.getString("WINDING_CONNECTION"));
		genUnits.setMassNumber(resultSet.getString("MASS_NUMBER"));		
		genUnits.setStiffnessCoefficient(resultSet.getString("STIFFNESS_COEFFICIENT"));
		genUnits.setSerialNumber(resultSet.getString("SERIAL_NO"));
		genUnits.setRotorDia(resultSet.getString("ROTOR_DIA"));
		genUnits.setHubHeight(resultSet.getString("HUB_HEIGHT"));
		genUnits.setEnabled(resultSet.getString("ENABLED"));
		
		genUnits.setRemarks(resultSet.getString("REMARKS"));
		genUnits.setCreatedBy(resultSet.getString("CREATED_BY"));
		genUnits.setCreatedDate(resultSet.getString("CREATED_DATE"));
		genUnits.setModifiedBy(resultSet.getString("MODIFIED_BY"));
		genUnits.setModifiedDate(resultSet.getString("MODIFIED_DATE"));

        genUnits.setId(resultSet.getString("ID"));
        
		return  genUnits;
	}
}

final class TransformerMapper implements RowMapper<Transformer>{

	public TransformerMapper() {
		super();
	}
	@Override
	public Transformer mapRow(ResultSet resultSet, int rownum) throws SQLException {
		Transformer transformers = new Transformer();
		
		
		transformers.setGcId(resultSet.getString("GC_ID"));
		transformers.setName(resultSet.getString("NAME"));
		transformers.setMvaRating(resultSet.getString("MVA_RATING"));
		transformers.setPrimaryVoltageCode(resultSet.getString("PRIMARY_VOLTAGE_CODE"));
		transformers.setPrimaryVoltageName(resultSet.getString("PRIMARY_VOLTAGE_NAME"));

		transformers.setSecondaryVoltageCode(resultSet.getString("SECONDARY_VOLTAGE_CODE"));
		transformers.setSecondaryVoltageName(resultSet.getString("SECONDARY_VOLTAGE_NAME"));

		transformers.setCoolingType(resultSet.getString("COOLING_TYPE"));
		transformers.setWindingConfig(resultSet.getString("WINDING_CONFIGURATION"));
		transformers.setBreakerRating(resultSet.getString("BREAKER_RATING"));
		transformers.setTapSetting(resultSet.getString("TAP_SETTING"));
		transformers.setTapNumberMax(resultSet.getString("TAP_NUMBER_MAX"));
		transformers.setTapNumberMin(resultSet.getString("TAP_NUMBER_MIN"));
		transformers.setTapRatio( resultSet.getString("TAP_RATIO"));
		transformers.setTapVoltageMax(resultSet.getString("TAP_VOLTAGE_MAX"));
		transformers.setTapVoltageMin(resultSet.getString("TAP_VOLTAGE_MIN"));
		transformers.setPhaseDisplacement(resultSet.getString("PHASE_DISPLACEMENT"));
		
		transformers.setImpedencePercentage(resultSet.getString("IMPEDENCE_PERCENTAGE"));
		transformers.setLeakReact(resultSet.getString("LEAK_REACTANCE"));
		transformers.setResistance(resultSet.getString("RESISTANCE"));
		transformers.setReact(resultSet.getString("REACTANCE"));
		transformers.setRemarks(resultSet.getString("REMARKS"));
		transformers.setCreatedBy(resultSet.getString("CREATED_BY"));
		transformers.setCreatedDate(resultSet.getString("CREATED_DATE"));
		transformers.setModifiedBy(resultSet.getString("MODIFIED_BY"));
		transformers.setModifiedDate(resultSet.getString("MODIFIED_DATE"));
		transformers.setEnabled(resultSet.getString("ENABLED"));
		transformers.setTapStep(resultSet.getString("TAP_STEP"));

		
		transformers.setId(resultSet.getString("ID"));
        
		return  transformers;
	}
}

final class QuantumAllocationMapper implements RowMapper<QuantumAllocation>{

	public QuantumAllocationMapper() {
		super();
	}
	@Override
	public QuantumAllocation mapRow(ResultSet resultSet, int rownum) throws SQLException {
		QuantumAllocation quantumAllocation = new QuantumAllocation();
		
		
		quantumAllocation.setGcId(resultSet.getString("GC_ID"));
        quantumAllocation.setBuyerCompServiceId(resultSet.getString("BUYER_COMP_SERV_ID"));
		quantumAllocation.setBuyerOrgId(resultSet.getString("BUYER_ORG_ID"));
		quantumAllocation.setCaptiveCompanyName(resultSet.getString("CAPTIVE_COMPANY_NAME"));
		quantumAllocation.setQuantum(resultSet.getString("QUANTUM"));
		
		
		quantumAllocation.setInjectionVoltageCode(resultSet.getString("INJECTING_VOLTAGE_CODE"));
		quantumAllocation.setInjectionVoltageName(resultSet.getString("INJECTION_VOLTAGE_NAME"));

		quantumAllocation.setDrawalVoltageCode(resultSet.getString("DRAWAL_VOLTAGE_CODE"));
		quantumAllocation.setDrawalVoltageName(resultSet.getString("DRAWAL_VOLTAGE_NAME"));

		quantumAllocation.setCreatedBy(resultSet.getString("CREATED_BY"));
		quantumAllocation.setCreatedDate(resultSet.getString("CREATED_DATE"));
		quantumAllocation.setModifiedBy(resultSet.getString("MODIFIED_BY"));
		quantumAllocation.setModifiedDate(resultSet.getString("MODIFIED_DATE"));
		quantumAllocation.setEnabled( resultSet.getString("ENABLED"));
		quantumAllocation.setRemarks(resultSet.getString("REMARKS"));

		quantumAllocation.setBuyerCompServiceNumber(resultSet.getString("BUYER_COMP_SERV_NUMBER"));
		quantumAllocation.setBuyerCompId(resultSet.getString("M_COMPANY_ID"));
		quantumAllocation.setBuyerCompName(resultSet.getString("M_COMPANY_NAME"));
		quantumAllocation.setSharedPercentage(resultSet.getString("SHARED_PERCENTAGE"));
        quantumAllocation.setId(resultSet.getString("ID"));
        
		return  quantumAllocation;
	}
}

final class ApplicationStatusMapper implements RowMapper<ApplicationStatus>{

	public ApplicationStatusMapper() {
		super();
	}
	@Override
	public ApplicationStatus mapRow(ResultSet resultSet, int rownum) throws SQLException {
		ApplicationStatus applicationStatus = new ApplicationStatus();
		
		
		applicationStatus.setGcId(resultSet.getString("GC_ID"));
        applicationStatus.setCreatedBy(resultSet.getString("CREATED_BY"));
		applicationStatus.setCreatedDate(resultSet.getString("CREATED_DATE"));
		applicationStatus.setModifiedBy(resultSet.getString("MODIFIED_BY"));
		applicationStatus.setModifiedDate(resultSet.getString("MODIFIED_DATE"));
		applicationStatus.setGcStatusTypeCode(resultSet.getString("GC_STATUS_TYPE_CODE"));
		applicationStatus.setGcStatusUpdateDate(resultSet.getString("GC_STATUS_UPDATE_DATE"));
		applicationStatus.setGcStatusUpdateBy(resultSet.getString("GC_STATUS_UPDATE_BY"));
		applicationStatus.setGcStatusRemarks(resultSet.getString("GC_STATUS_REMARKS"));
		applicationStatus.setEnabled(resultSet.getString("ENABLED"));
        applicationStatus.setId(resultSet.getString("ID"));
        
		return  applicationStatus;
	}
}

final class LoanMapper implements RowMapper<Loan>{

	public LoanMapper() {
		super();
	}
	@Override
	public Loan mapRow(ResultSet resultSet, int rownum) throws SQLException {
		Loan idLoans = new Loan();
		
		
		idLoans.setGcId(resultSet.getString("GC_ID"));
		idLoans.setLoanOrigin(resultSet.getString("LOAN_ORIGIN"));
		idLoans.setLoanSourceName(resultSet.getString("SOURCE_NAME"));
		idLoans.setLoanSourceAddress(resultSet.getString("SOURCE_ADDRESS"));
		idLoans.setLoanAmount(resultSet.getString("LOAN_AMOUNT"));
		idLoans.setLoanCurrency(resultSet.getString("CURRENCY"));
		idLoans.setLoanExchangeRate(resultSet.getString("EXCHANGE_RATE"));
		idLoans.setRemarks( resultSet.getString("REMARKS"));
        idLoans.setCreatedBy(resultSet.getString("CREATED_BY"));
		idLoans.setCreatedDate(resultSet.getString("CREATED_DATE"));
		idLoans.setModifiedBy(resultSet.getString("MODIFIED_BY"));
		idLoans.setModifiedDate(resultSet.getString("MODIFIED_DATE"));
		idLoans.setEnabled(resultSet.getString("ENABLED"));
        idLoans.setId(resultSet.getString("ID"));
        
		return  idLoans;
	}
}

final class CaptiveUserContributionMapper implements RowMapper<CaptiveUserContribution>{

	public CaptiveUserContributionMapper() {
		super();
	}
	@Override
	public CaptiveUserContribution mapRow(ResultSet resultSet, int rownum) throws SQLException {
		CaptiveUserContribution captiveUserContribution = new CaptiveUserContribution();
		
		
		captiveUserContribution.setGcId(resultSet.getString("GC_ID"));

		captiveUserContribution.setClassOfShareHolder(resultSet.getString("CLASS_OF_SHAREHOLDER"));
		captiveUserContribution.setNoOfEquityShares(resultSet.getString("NUMBER_OF_EQUTIY_SHARES"));
		captiveUserContribution.setValueOfEquityShares(resultSet.getString("VALUE_OF_EQUTIY_SHARES"));
		captiveUserContribution.setAmountOfEquityShares(resultSet.getString("AMOUNT_OF_EQUTIY_SHARES"));
		captiveUserContribution.setNoOfVotingRights(resultSet.getString("NUMBER_OF_VOTING_RIGHTS"));
		captiveUserContribution.setPercentageHoldingInEquityShares(resultSet.getString("PERCT_IN_EQUTIY_SHARES"));
		captiveUserContribution.setPercentageHoldingInVotingRights( resultSet.getString("PERCT_IN_VOTING_RIGHTS"));
		captiveUserContribution.setPercentageHoldingInVotingWithEquity(resultSet.getString("PERCT_IN_VOTING_WITH_EQUITY"));
		captiveUserContribution.setRemarks(resultSet.getString("REMARKS"));
		captiveUserContribution.setEnabled(resultSet.getString("ENABLED"));
        captiveUserContribution.setCreatedBy(resultSet.getString("CREATED_BY"));
		captiveUserContribution.setCreatedDate(resultSet.getString("CREATED_DATE"));
		captiveUserContribution.setModifiedBy(resultSet.getString("MODIFIED_BY"));
		captiveUserContribution.setModifiedDate(resultSet.getString("MODIFIED_DATE"));
        captiveUserContribution.setId(resultSet.getString("ID"));
        
		return  captiveUserContribution;
	}
}

final class EquityShareVotingRightsMapper implements RowMapper<EquityShareVotingRights>{

	public EquityShareVotingRightsMapper() {
		super();
	}
	@Override
	public EquityShareVotingRights mapRow(ResultSet resultSet, int rownum) throws SQLException {
		EquityShareVotingRights equityShareVotingRights = new EquityShareVotingRights();
		
		
		equityShareVotingRights.setGcId(resultSet.getString("GC_ID"));
        equityShareVotingRights.setClassOfEquityShares(resultSet.getString("CLASS_OF_EQUTIY_SHARES"));
		equityShareVotingRights.setNoOfEquityShares(resultSet.getString("NUMBER_OF_EQUTIY_SHARES"));
		equityShareVotingRights.setValueOfEquityShares(resultSet.getString("VALUE_OF_EQUTIY_SHARES"));
		equityShareVotingRights.setAmountOfEquityShares(resultSet.getString("AMOUNT_OF_EQUTIY_SHARES"));
		equityShareVotingRights.setNoOfVotingRights(resultSet.getString("NUMBER_OF_VOTING_RIGHTS"));
		equityShareVotingRights.setPercentageHoldingInEquityShares(resultSet.getString("PERCT_IN_EQUTIY_SHARES"));
		equityShareVotingRights.setPercentageHoldingInVotingRights(resultSet.getString("PERCT_IN_VOTING_RIGHTS"));
		equityShareVotingRights.setPercentageHoldingInVotingWithEquity( resultSet.getString("PERCT_IN_VOTING_WITH_EQUITY"));
		equityShareVotingRights.setRemarks(resultSet.getString("REMARKS"));
		equityShareVotingRights.setCreatedBy(resultSet.getString("CREATED_BY"));
		equityShareVotingRights.setCreatedDate(resultSet.getString("CREATED_DATE"));
		equityShareVotingRights.setModifiedBy(resultSet.getString("MODIFIED_BY"));
		equityShareVotingRights.setModifiedDate(resultSet.getString("MODIFIED_DATE"));
		equityShareVotingRights.setEnabled(resultSet.getString("ENABLED"));
        equityShareVotingRights.setId(resultSet.getString("ID"));
        
		return  equityShareVotingRights;
	}
}

final class DocCheckListItemMapper implements RowMapper<DocCheckListItem>{

	public DocCheckListItemMapper() {
		super();
	}
	@Override
	public DocCheckListItem mapRow(ResultSet resultSet, int rownum) throws SQLException {
		DocCheckListItem checkList = new DocCheckListItem();
		
		
		checkList.setGcId(resultSet.getString("GC_ID"));
		checkList.setDocumentCode(resultSet.getString("CHECKLIST_CODE"));
		checkList.setIsComplete(resultSet.getString("IS_COMPLETE"));
		checkList.setCompletedDate(resultSet.getString("COMPLETED_DT"));
		checkList.setRemarks(resultSet.getString("REMARKS"));
		checkList.setEnabled(resultSet.getString("ENABLED"));
		checkList.setDocSubmitted(resultSet.getString("IS_SUBMITTED"));
		checkList.setRemarks(resultSet.getString("REMARKS"));
        checkList.setCreatedBy(resultSet.getString("CREATED_BY"));
		checkList.setCreatedDate(resultSet.getString("CREATED_DATE"));
		checkList.setModifiedBy(resultSet.getString("MODIFIED_BY"));
		checkList.setModifiedDate(resultSet.getString("MODIFIED_DATE"));
		checkList.setDocumentDesc(resultSet.getString("CHECKLIST_NAME"));
        checkList.setId(resultSet.getString("ID"));
        System.out.println("set Checklist");

        System.out.println(checkList);
		return  checkList;
	}
}
}
