package com.ss.oa.report.reportservice;

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

import com.ss.oa.report.vo.Report;

@Component
@Scope("prototype")
public class ReportServiceDaoImpl implements ReportServiceDao {

	@Resource
	private JdbcOperations jdbcOperations;

	@Override
	public List<Report> getGenerationPerOrg(Map<String, String> criteria) {

		String searchCriteria = "";
		if (!criteria.isEmpty()) {
			if (criteria.get("org-id") != null) {
				searchCriteria += "and upper(energyledger.M_ORG_ID)  like upper('%" + criteria.get("org-id") + "%') ";
			}
			if (criteria.get("fuel-type-code") != null) {
				searchCriteria += "and upper(vcompanyservice.FUEL_TYPE_CODE)  like upper('%"
						+ criteria.get("fuel-type-code") + "%') ";
			}
			if (criteria.get("is-captive") != null) {
				searchCriteria += "and upper(company.IS_CAPTIVE)  like upper('%" + criteria.get("is-captive") + "%') ";
			}
			if (criteria.get("month") != null) {
				searchCriteria += "and upper(energyledger.MONTH)  like upper('%" + criteria.get("month") + "%') ";
			}
			if (criteria.get("year") != null) {
				searchCriteria += "and upper(energyledger.YEAR)  like upper('%" + criteria.get("year") + "%') ";
			}
		}

		String sql = "select energyledger.M_ORG_ID,energyledger.D_ORG_NAME,energyledger.D_ORG_CODE,energyledger.MONTH,energyledger.YEAR,SUM(energyledger.ENERGY_IN) as TOTAL_QUANTUM,vcompanyservice.FUEL_TYPE_CODE,company.IS_CAPTIVE\n"
				+ "from F_ENERGY_LEDGER energyledger\n"
				+ "LEFT JOIN V_COMPANY_SERVICE vcompanyservice ON energyledger.M_COMPANY_SERVICE_ID = vcompanyservice.ID\n"
				+ "LEFT JOIN M_COMPANY company ON energyledger.M_COMPANY_ID = company.ID WHERE 1=1 " + searchCriteria
				+ "\n"
				+ "GROUP BY energyledger.M_ORG_ID,energyledger.MONTH,energyledger.YEAR,energyledger.D_ORG_NAME,energyledger.D_ORG_CODE,vcompanyservice.FUEL_TYPE_CODE,company.IS_CAPTIVE";
		System.out.println("SQL 1");
		System.out.println(searchCriteria);
		System.out.println(sql);

		return (ArrayList<Report>) jdbcOperations.query(sql, new GenerationPerOrgMapper());

	}

	@Override
	public List<Report> getGenerationPerService(Map<String, String> criteria) {
		String searchCriteria = "";

		if (!criteria.isEmpty()) {
			if (criteria.get("org-id") != null) {
				searchCriteria += "and upper(energyledger.M_ORG_ID)  like upper('%" + criteria.get("org-id") + "%') ";
			}
			if (criteria.get("fuel-type-code") != null) {
				searchCriteria += "and upper(vcompanyservice.FUEL_TYPE_CODE)  like upper('%"
						+ criteria.get("fuel-type-code") + "%') ";
			}
			if (criteria.get("is-captive") != null) {
				searchCriteria += "and upper(company.IS_CAPTIVE)  like upper('%" + criteria.get("is-captive") + "%') ";
			}
			if (criteria.get("month") != null) {
				searchCriteria += "and upper(energyledger.MONTH)  like upper('%" + criteria.get("month") + "%') ";
			}
			if (criteria.get("year") != null) {
				searchCriteria += "and upper(energyledger.YEAR)  like upper('%" + criteria.get("year") + "%') ";
			}
		}

		String sql = "select energyledger.M_ORG_ID,energyledger.D_ORG_NAME,energyledger.D_ORG_CODE,energyledger.M_COMPANY_ID,energyledger.D_COMP_NAME,energyledger.D_COMP_CODE,energyledger.M_COMPANY_SERVICE_ID,\n"
				+ "energyledger.D_COMP_SERV_NUMBER,energyledger.MONTH,energyledger.YEAR,SUM(energyledger.ENERGY_IN) as TOTAL_QUANTUM,vcompanyservice.FUEL_TYPE_CODE,company.IS_CAPTIVE \n"
				+ "from F_ENERGY_LEDGER energyledger\n"
				+ "LEFT JOIN V_COMPANY_SERVICE vcompanyservice ON energyledger.M_COMPANY_SERVICE_ID = vcompanyservice.ID\n"
				+ "LEFT JOIN M_COMPANY company ON energyledger.M_COMPANY_ID = company.ID where 1=1 " + searchCriteria
				+ "\n"
				+ "GROUP BY energyledger.M_ORG_ID,energyledger.MONTH,energyledger.YEAR,energyledger.D_ORG_NAME,energyledger.D_ORG_CODE,\n"
				+ "energyledger.M_COMPANY_ID,energyledger.D_COMP_NAME,energyledger.D_COMP_CODE,energyledger.M_COMPANY_SERVICE_ID,energyledger.D_COMP_SERV_NUMBER,\n"
				+ "vcompanyservice.FUEL_TYPE_CODE,company.IS_CAPTIVE";

		System.out.println("SQL 2");
		System.out.println(searchCriteria);
		System.out.println(sql);

		return (ArrayList<Report>) jdbcOperations.query(sql, new GenerationPerServiceMapper());

	}

	final class GenerationPerOrgMapper implements RowMapper<Report> {

		@Override
		public Report mapRow(ResultSet resultSet, int rownum) throws SQLException {
			Report report = new Report();

			report.setOrgId(resultSet.getString("M_ORG_ID"));
			report.setOrgCode(resultSet.getString("D_ORG_CODE"));
			report.setOrgName(resultSet.getString("D_ORG_NAME"));
			report.setMonth(resultSet.getString("MONTH"));
			report.setYear(resultSet.getString("YEAR"));
			report.setTotalQuantum(resultSet.getString("TOTAL_QUANTUM"));
			report.setFuelTypeCode(resultSet.getString("FUEL_TYPE_CODE"));
			report.setIsCaptive(resultSet.getString("IS_CAPTIVE"));
			return report;
		}
	}

	final class GenerationPerServiceMapper implements RowMapper<Report> {

		@Override
		public Report mapRow(ResultSet resultSet, int rownum) throws SQLException {
			Report report = new Report();

			report.setOrgId(resultSet.getString("M_ORG_ID"));
			report.setOrgCode(resultSet.getString("D_ORG_CODE"));
			report.setOrgName(resultSet.getString("D_ORG_NAME"));
			report.setCompanyId(resultSet.getString("M_COMPANY_ID"));
			report.setCompanyCode(resultSet.getString("D_COMP_CODE"));
			report.setCompanyName(resultSet.getString("D_COMP_NAME"));
			report.setCompanyServiceId(resultSet.getString("M_COMPANY_SERVICE_ID"));
			report.setCompanyServiceNumber(resultSet.getString("D_COMP_SERV_NUMBER"));
			report.setMonth(resultSet.getString("MONTH"));
			report.setYear(resultSet.getString("YEAR"));
			report.setTotalQuantum(resultSet.getString("TOTAL_QUANTUM"));
			report.setFuelTypeCode(resultSet.getString("FUEL_TYPE_CODE"));
			report.setIsCaptive(resultSet.getString("IS_CAPTIVE"));

			return report;
		}
	}

	@Override
	public List<Report> fetchGeneratorBasedConsumerUsage(Map<String, String> criteria) {
		String searchCriteria = "";
		if (!criteria.isEmpty()) {
			if (criteria.get("parent-org-code") != null) {
				searchCriteria += "and upper(ORG.PARENT_ORG_CODE)  = upper('" + criteria.get("parent-org-code") + "') ";
			}
			if (criteria.get("voltage-code") != null) {
				searchCriteria += "and upper(NVL(COMPANYSERVICE.VOLTAGE_CODE,''))  = upper('"
						+ criteria.get("voltage-code") + "') ";
			}
			if (criteria.get("is-captive") != null) {
				searchCriteria += "and upper(TRADERELATIONSHIP.IS_CAPTIVE)  = upper('" + criteria.get("is-captive")
						+ "') ";
			}

			System.out.println(criteria.get("voltage-code"));
			System.out.println(searchCriteria);

		}
		String sql = "SELECT COMPANYSERVICE.ID AS COMP_SERV_ID,COMPANYSERVICE.\"number\" AS COMP_SERV_NUMBER, COMPANYSERVICE.M_COMPANY_ID,COMPANYSERVICE.M_COMPANY_NAME,POWERPLANT.DISTRICT_CODE,DISTRICTCODE.VALUE_DESC AS DISTRICT_NAME ,POWERPLANT.TOTAL_CAPACITY,COMPANYSERVICE.M_ORG_ID,COMPANYSERVICE.M_ORG_CODE ,COMPANYSERVICE.M_ORG_NAME,\n"
				+ "COMPANYSERVICE.VOLTAGE_CODE,COMPANYSERVICE.VOLTAGE_NAME,COUNT(TRADERELATIONSHIP.M_BUYER_COMP_SERVICE_ID) AS NO_OF_BUYERS,SUM(TRADERELATIONSHIP.C1+TRADERELATIONSHIP.C2+TRADERELATIONSHIP.C3+TRADERELATIONSHIP.C4+TRADERELATIONSHIP.C5) AS TOTAL_UNITS_BOUGHT,TRADERELATIONSHIP.IS_CAPTIVE,ORG.PARENT_ORG_ID,ORG.PARENT_ORG_CODE,ORG.PARENT_ORG_NAME\n"
				+ "FROM M_POWERPLANT POWERPLANT \n"
				+ "LEFT JOIN V_COMPANY_SERVICE COMPANYSERVICE ON POWERPLANT.M_SERVICE_ID = COMPANYSERVICE.ID\n"
				+ "LEFT JOIN V_CODES DISTRICTCODE ON POWERPLANT.DISTRICT_CODE = DISTRICTCODE.VALUE_CODE AND DISTRICTCODE.LIST_CODE = 'DISTRICT_CODE'\n"
				+ "LEFT JOIN M_TRADE_RELATIONSHIP TRADERELATIONSHIP ON POWERPLANT.M_SERVICE_ID = TRADERELATIONSHIP.M_SELLER_COMP_SERVICE_ID\n"
				+ "LEFT JOIN V_ORG ORG ON COMPANYSERVICE.M_ORG_CODE = ORG.ORG_CODE where 1=1 " + searchCriteria + "\n"
				+ "GROUP BY  COMPANYSERVICE.ID, COMPANYSERVICE.M_COMPANY_ID,COMPANYSERVICE.\"number\",COMPANYSERVICE.M_COMPANY_NAME,POWERPLANT.DISTRICT_CODE,DISTRICTCODE.VALUE_DESC,POWERPLANT.TOTAL_CAPACITY,COMPANYSERVICE.M_ORG_ID,COMPANYSERVICE.M_ORG_CODE ,COMPANYSERVICE.M_ORG_NAME,\n"
				+ "COMPANYSERVICE.VOLTAGE_CODE,COMPANYSERVICE.VOLTAGE_NAME,ORG.PARENT_ORG_ID,ORG.PARENT_ORG_CODE,ORG.PARENT_ORG_NAME,TRADERELATIONSHIP.IS_CAPTIVE";
		System.out.println(sql);

		return (ArrayList<Report>) jdbcOperations.query(sql, new GeneratorBasedConsumerUsageMapper());
	}

	final class GeneratorBasedConsumerUsageMapper implements RowMapper<Report> {

		@Override
		public Report mapRow(ResultSet resultSet, int rownum) throws SQLException {
			Report report = new Report();
			report.setCompanyServiceId(resultSet.getString("COMP_SERV_ID"));
			report.setCompanyServiceNumber(resultSet.getString("COMP_SERV_NUMBER"));
			report.setCompanyId(resultSet.getString("M_COMPANY_ID"));
			report.setCompanyName(resultSet.getString("M_COMPANY_NAME"));
			report.setDistrictCode(resultSet.getString("DISTRICT_CODE"));
			report.setDistrictName(resultSet.getString("DISTRICT_NAME"));
			report.setTotalCapacity(resultSet.getString("TOTAL_CAPACITY"));
			report.setOrgId(resultSet.getString("M_ORG_ID"));
			report.setOrgCode(resultSet.getString("M_ORG_CODE"));
			report.setOrgName(resultSet.getString("M_ORG_NAME"));
			report.setVoltageCode(resultSet.getString("VOLTAGE_CODE"));
			report.setVoltageName(resultSet.getString("VOLTAGE_NAME"));
			report.setNoOfBuyers(resultSet.getString("NO_OF_BUYERS"));
			report.setTotalUnitsBought(resultSet.getString("TOTAL_UNITS_BOUGHT"));
			report.setIsCaptive(resultSet.getString("IS_CAPTIVE"));
			report.setParentOrgId(resultSet.getString("PARENT_ORG_ID"));
			report.setParentOrgCode(resultSet.getString("PARENT_ORG_CODE"));
			report.setParentOrgName(resultSet.getString("PARENT_ORG_NAME"));

			return report;
		}
	}

}
