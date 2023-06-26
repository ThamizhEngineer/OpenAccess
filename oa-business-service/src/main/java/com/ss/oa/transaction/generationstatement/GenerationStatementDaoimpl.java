package com.ss.oa.transaction.generationstatement;

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

import com.ss.oa.transaction.vo.GenerationStatement;
import com.ss.oa.transaction.vo.GenerationStatementCharge;
import com.ss.oa.transaction.vo.GenerationStatementSlot;

@Scope("prototype")
@Component
public class GenerationStatementDaoimpl extends BaseDaoJdbc implements GenerationStatementDao {
	@Resource
	private JdbcOperations jdbcOperations;

	@Override
	public List<GenerationStatement> getAllGenerationStatements(Map<String, String> criteria) {
		String sql = "select gs.ID,gs.STATUS_CODE,gs.REMARKS,gs.M_COMPANY_METER_ID,gs.REF_NUMBER,gs.T_MR_IDS,gs.MF,  \n"
				+ "				 				 				 gs.MACHINE_CAPACITY,gs.COMMISSION_DATE,gs.STMT_GEN_DATE,gs.STMT_MONTH,gs.STMT_YEAR,gs.INIT_STMT_DT,gs.FINAL_STMT_DT,gs.RKVAH_INIT,gs.RKVAH_FINAL,gs.RKVAH_DIFF,gs.RKVAH_UNITS,gs.KVAH_INIT,   \n"
				+ "				 				 				 gs.KVAH_FINAL,gs.KVAH_DIFF,gs.KVAH_UNITS,gs.TOTAL_IMPORT_GEN,gs.TOTAL_EXPORT_GEN,gs.M_ORG_ID,gs.M_COMPANY_ID,gs.M_COMPANY_SERVICE_ID,gs.DISP_COMPANY_NAME,gs.DISP_SERVICE_NUMBER,gs.INJECTING_VOLTAGE_CODE,\n"
				+ "		    				 				     gs.INJECTING_VOLTAGE_DESC,gs.DISP_ORG_NAME,gs.POWER_FACTOR,gs.NET_GENERATION,gs.TOTAL_CHARGED_AMOUNT,gs.c1,gs.c2,gs.c3,gs.c4,gs.c5,gs.file_name,  \n"
				+ "								 				 gs.PLANT_CLASS_TYPE_CODE,gs.PLANT_CLASS_TYPE_DESC,gs.TARIFF_RATE,gs.NET_PAYABLE,gs.IS_CAPTIVE,gs.IS_STB ,gs.TARIFF_NET_AMOUNT,gs.TYPE_OF_SS,gs.FLOW_TYPE_CODE,gs.IS_REC,gs.TOTAL_SS_LOSS,gs.SS_LOSS_PERCENT,gs.M_SUBSTATION_ID,gs.M_SUBSTATION_NAME,gs.IS_METER_CHANGE,gs.MR_SOURCE_CODE,gs.DISP_FUEL_TYPE_CODE, fuel.FUEL_NAME as DISP_FUEL_TYPE_NAME, fuel.FUEL_GROUP as DISP_FUEL_TYPE_GROUP,gs.DOC_ID,gs.STMT_REMARKS\n"
				+ "								 				 from T_GEN_STMT gs \n"
				+ "												 LEFT JOIN M_FUEL fuel on gs.DISP_FUEL_TYPE_CODE = fuel.FUEL_CODE WHERE 1=1";
		if (!criteria.isEmpty()) {

			if (criteria.get("company_name") != null) {
				sql += "and upper(gs.DISP_COMPANY_NAME) like upper('%" + criteria.get("company_name") + "%')";
			}
			if (criteria.get("edc_name") != null) {
				sql += "and upper(gs.DISP_ORG_NAME) like upper('%" + criteria.get("edc_name") + "%')";
			}
			if (criteria.get("company-id") != null) {
				sql += "and upper(gs.M_COMPANY_ID)= upper('" + criteria.get("company-id") + "')";
			}
			if (criteria.get("edc-id") != null) {
				sql += "and upper(gs.M_ORG_ID) = upper('" + criteria.get("edc-id") + "')";
			}
			if (criteria.get("service-id") != null) {
				sql += "and upper(gs.M_COMPANY_SERVICE_ID) = upper('" + criteria.get("service-id") + "')";
			}
			if (criteria.get("service-number") != null) {
				sql += "and upper(gs.DISP_SERVICE_NUMBER) = upper('" + criteria.get("service-number") + "')";
			}
			if (criteria.get("statement-month") != null) {
				sql += "and upper(gs.STMT_MONTH) = upper('" + criteria.get("statement-month") + "')";
			}
			if (criteria.get("statement-year") != null) {
				sql += "and upper(gs.STMT_YEAR) = upper('" + criteria.get("statement-year") + "')";
			}
			if (criteria.get("isCaptive") != null) {
				sql += "and upper(gs.IS_CAPTIVE) = upper('" + criteria.get("isCaptive") + "')";
			}
			if (criteria.get("fuelTypeCode") != null) {
				sql += "and upper(gs.DISP_FUEL_TYPE_CODE) = upper('" + criteria.get("fuelTypeCode") + "')";
			}
			if (criteria.get("fuelTypeName") != null) {
				sql += "and upper(fuel.FUEL_NAME) = upper('" + criteria.get("fuelTypeName") + "')";
			}
			if (criteria.get("fuelGroupe") != null) {
				sql += "and upper(fuel.FUEL_GROUP) = upper('" + criteria.get("fuelGroupe") + "')";
			}
			if (criteria.get("flowTypeCode") != null) {
				sql += "and upper(gs.FLOW_TYPE_CODE) = upper('" + criteria.get("flowTypeCode") + "')";
			}
			if (criteria.get("allocated") != null) {
				if (criteria.get("allocated").equals("Y")) {
					sql += "and upper(gs.status_code) = 'ALLOCATED'";
				} else if (criteria.get("allocated").equals("N")) {
					sql += "AND gs.DISP_SERVICE_NUMBER  NOT IN (SELECT D_SELL_COMP_SERV_NUMBER FROM F_ENERGY_SALE_ORDER  WHERE MONTH = " + criteria.get("statement-month")+" AND YEAR=" + criteria.get("statement-year") +")";
				}
			}
		}
	

		System.out.println(sql);
		return (ArrayList<GenerationStatement>) jdbcOperations.query(sql, new GenerationStatementMapper());
	}

	@Override
	public List<GenerationStatement> getAllBuyerGenerationStatements(Map<String, String> criteria) {
		String sql = "select * from t_gen_stmt where m_company_service_id in \r\n"
				+ "(select distinct m_seller_comp_service_id from m_trade_relationship where m_buyer_comp_service_id in \r\n"
				+ "(select id from m_company_service where comp_ser_type_code='02'";
		if (!criteria.isEmpty()) {
			if (criteria.get("edc-id") != null) {
				sql += "and upper(m_org_id) = upper('" + criteria.get("edc-id") + "')))";
			}
			if (criteria.get("service-number") != null) {
				sql += "and upper(DISP_SERVICE_NUMBER) = upper('" + criteria.get("service-number") + "')";
			}
			if (criteria.get("statement-month") != null) {
				sql += "and upper(stmt_month) = upper('" + criteria.get("statement-month") + "')";
			}
			if (criteria.get("statement-year") != null) {
				sql += "and upper(stmt_year) = upper('" + criteria.get("statement-year") + "')";
			}
			if (criteria.get("fuelTypeCode") != null) {
				sql += "and upper(DISP_FUEL_TYPE_CODE) = upper('" + criteria.get("fuelTypeCode") + "')";
			}
		}
		System.out.println(criteria);

		System.out.println(sql);
		return (ArrayList<GenerationStatement>) jdbcOperations.query(sql, new GenerationStatementMapper());
	}

	@Override
	public GenerationStatement getGenerationStatementById(String id) {
		System.out.println("in daoimplgetbyid");

		GenerationStatement generationStatement = new GenerationStatement();
		List<GenerationStatementCharge> generationStatementCharges = new ArrayList<GenerationStatementCharge>();
		List<GenerationStatementSlot> generationStatementSlots = new ArrayList<GenerationStatementSlot>();

		if (id.contains("forview")) {
			id = id.replaceAll("forview", "");
			System.out.println(id + "--- it is id");
			String sql1 = "select gscharge.ID,gscharge.REMARKS,gscharge.T_GEN_STMT_ID,gscharge.CHARGE_CODE,gscharge.CHARGE_DESC,gscharge.CHARGE_TYPE_CODE,chargetypecode.value_desc as CHARGE_TYPE_NAME, gscharge.UNIT_CHARGE,nvl((case when gscharge.TOTAL_CHARGES = '0' THEN GSCHARGE.total_charges_bak else gscharge.TOTAL_CHARGES end),'0') as TOTAL_CHARGES,gscharge.IEC_RATE,gscharge.ETAX_RATE \n"
					+ "from T_GEN_STMT_CHARGE gscharge\n"
					+ "left join v_codes chargetypecode on gscharge.CHARGE_TYPE_CODE = chargetypecode.VALUE_CODE and chargetypecode.LIST_CODE='CHARGE_TYPE_CODE' where T_GEN_STMT_ID=?";
			generationStatementCharges = jdbcOperations.query(sql1, new Object[] { id },
					new GenerationStatementChargeMapper());
		} else {

			String sql1 = "select gscharge.ID,gscharge.REMARKS,gscharge.T_GEN_STMT_ID,gscharge.CHARGE_CODE,gscharge.CHARGE_DESC,gscharge.CHARGE_TYPE_CODE,chargetypecode.value_desc as CHARGE_TYPE_NAME, gscharge.UNIT_CHARGE,gscharge.TOTAL_CHARGES,gscharge.IEC_RATE,gscharge.ETAX_RATE \n"
					+ "from T_GEN_STMT_CHARGE gscharge\n"
					+ "left join v_codes chargetypecode on gscharge.CHARGE_TYPE_CODE = chargetypecode.VALUE_CODE and chargetypecode.LIST_CODE='CHARGE_TYPE_CODE' where T_GEN_STMT_ID=?";
			generationStatementCharges = jdbcOperations.query(sql1, new Object[] { id },
					new GenerationStatementChargeMapper());
		}

		String sql = "select gs.ID,gs.STATUS_CODE,gs.REMARKS,gs.M_COMPANY_METER_ID,gs.REF_NUMBER,gs.T_MR_IDS,gs.MF,  \n"
				+ "				 				 				 gs.MACHINE_CAPACITY,gs.COMMISSION_DATE,gs.STMT_GEN_DATE,gs.STMT_MONTH,gs.STMT_YEAR,gs.INIT_STMT_DT,gs.FINAL_STMT_DT,gs.RKVAH_INIT,gs.RKVAH_FINAL,gs.RKVAH_DIFF,gs.RKVAH_UNITS,gs.KVAH_INIT,   \n"
				+ "				 				 				 gs.KVAH_FINAL,gs.KVAH_DIFF,gs.KVAH_UNITS,gs.TOTAL_IMPORT_GEN,gs.TOTAL_EXPORT_GEN,gs.M_ORG_ID,gs.M_COMPANY_ID,gs.M_COMPANY_SERVICE_ID,gs.DISP_COMPANY_NAME,gs.DISP_SERVICE_NUMBER,gs.INJECTING_VOLTAGE_CODE,\n"
				+ "		    				 				     gs.INJECTING_VOLTAGE_DESC,gs.DISP_ORG_NAME,gs.POWER_FACTOR,gs.NET_GENERATION,gs.TOTAL_CHARGED_AMOUNT,gs.c1,gs.c2,gs.c3,gs.c4,gs.c5,gs.file_name,  \n"
				+ "								 				 gs.PLANT_CLASS_TYPE_CODE,gs.PLANT_CLASS_TYPE_DESC,gs.TARIFF_RATE,gs.NET_PAYABLE,gs.IS_CAPTIVE,gs.IS_STB ,gs.TARIFF_NET_AMOUNT,gs.TYPE_OF_SS,gs.FLOW_TYPE_CODE,gs.IS_REC,gs.TOTAL_SS_LOSS,gs.SS_LOSS_PERCENT,gs.M_SUBSTATION_ID,gs.M_SUBSTATION_NAME,gs.IS_METER_CHANGE,gs.MR_SOURCE_CODE,gs.DISP_FUEL_TYPE_CODE, fuel.FUEL_NAME as DISP_FUEL_TYPE_NAME, fuel.FUEL_GROUP as DISP_FUEL_TYPE_GROUP,gs.DOC_ID, gs.STMT_REMARKS \n"
				+ "								 				 from T_GEN_STMT gs \n"
				+ "                                                LEFT JOIN M_FUEL fuel on gs.DISP_FUEL_TYPE_CODE = fuel.FUEL_CODE where gs.id =?";

		generationStatement = jdbcOperations.queryForObject(sql, new Object[] { id }, new GenerationStatementMapper());
		String sql2 = "select gsslot.ID,gsslot.REMARKS,gsslot.REF_NUMBER,gsslot.T_GEN_STMT_ID,gsslot.SLOT_CODE,slotcode.value_desc as SLOT_NAME,gsslot.IMP_INIT,gsslot.IMP_FINAL,gsslot.IMP_DIFF,gsslot.IMP_UNITS,gsslot.EXP_INIT,gsslot.EXP_FINAL,gsslot.EXP_DIFF,\n"
				+ "gsslot.EXP_UNITS,gsslot.BANKED_BALANCE,gsslot.NET_UNITS from T_GEN_STMT_SLOT gsslot\n"
				+ "left join v_codes slotcode on gsslot.SLOT_CODE = slotcode.VALUE_CODE and slotcode.LIST_CODE='SLOT_CODE'\n"
				+ "where T_GEN_STMT_ID=?";
		generationStatementSlots = jdbcOperations.query(sql2, new Object[] { id }, new GenerationStatementSlotMapper());

		generationStatement.setGenerationStatementCharges(generationStatementCharges);
		generationStatement.setGenerationStatementSlots(generationStatementSlots);
		return generationStatement;
	}

	@Override
	public String addGenerationStatement(GenerationStatement generationStatement) {
		String result = "";
		try {
			generationStatement.setId(generateId());
			String sql = "insert into T_GEN_STMT(STATUS_CODE,REMARKS,M_COMPANY_METER_ID,REF_NUMBER,T_MR_IDS,MF,MACHINE_CAPACITY,COMMISSION_DATE,STMT_GEN_DATE,STMT_MONTH,STMT_YEAR,\n"
					+ "INIT_STMT_DT,FINAL_STMT_DT,RKVAH_INIT,RKVAH_FINAL,RKVAH_DIFF,RKVAH_UNITS,KVAH_INIT,KVAH_FINAL,KVAH_DIFF,KVAH_UNITS,\n"
					+ "TOTAL_IMPORT_GEN,TOTAL_EXPORT_GEN,M_ORG_ID,M_COMPANY_ID,M_COMPANY_SERVICE_ID,DISP_COMPANY_NAME,DISP_SERVICE_NUMBER,INJECTING_VOLTAGE_CODE,DISP_ORG_NAME,POWER_FACTOR,NET_GENERATION,TOTAL_CHARGED_AMOUNT,C1,C2,C3,C4,C5,FILE_NAME,MR_SOURCE_CODE,ID,DOC_ID)\n"
					+ "values(?,?,?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,?,\n"
					+ "TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,?,?,?,?,\n"
					+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			if (jdbcOperations.update(sql, setGenerationStatementValues(generationStatement)) > 0) {
				result = generationStatement.getId();

			} else {
				result = "FAILURE";
			}
		} catch (Exception e) {
			result = "FAILURE";
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String updateGenerationStatement(String id, GenerationStatement generationStatement) {
		String result = "";
		generationStatement.setId(id);
		try {

//			String sql = "update T_GEN_STMT SET STATUS_CODE=?,REMARKS=?,M_COMPANY_METER_ID=?,REF_NUMBER=?,T_MR_IDS=?,MF=?,MACHINE_CAPACITY=?,COMMISSION_DATE=?,STMT_GEN_DATE=TO_DATE(?,'yyyy-mm-dd'),STMT_MONTH=?,STMT_YEAR=?, \n" + 
//					"INIT_STMT_DT=TO_DATE(?,'yyyy-mm-dd'),FINAL_STMT_DT=TO_DATE(?,'yyyy-mm-dd'),RKVAH_INIT=?,RKVAH_FINAL=?,RKVAH_DIFF=?,RKVAH_UNITS=?,KVAH_INIT=?,KVAH_FINAL=?,KVAH_DIFF=?,KVAH_UNITS=?, \n" + 
//					"TOTAL_IMPORT_GEN=?,TOTAL_EXPORT_GEN=?,M_ORG_ID=?,M_COMPANY_ID=?,M_COMPANY_SERVICE_ID=?,DISP_COMPANY_NAME=?,DISP_SERVICE_NUMBER=?,INJECTING_VOLTAGE_CODE=?,DISP_ORG_NAME=?,POWER_FACTOR=?,NET_GENERATION=?,TOTAL_CHARGED_AMOUNT=?,C1=?,C2=?,C3=?,C4=?,C5=?,FILE_NAME=? where id=?"; 
//					
			String sql = "update T_GEN_STMT SET STATUS_CODE=?,REMARKS=?,M_COMPANY_METER_ID=?,REF_NUMBER=?,T_MR_IDS=?,MF=?,MACHINE_CAPACITY=?,COMMISSION_DATE=TO_DATE(?,'yyyy-mm-dd'),STMT_GEN_DATE=TO_DATE(?,'yyyy-mm-dd'),STMT_MONTH=?,STMT_YEAR=?, \n"
					+ "INIT_STMT_DT=TO_DATE(?,'yyyy-mm-dd'),FINAL_STMT_DT=TO_DATE(?,'yyyy-mm-dd'),RKVAH_INIT=?,RKVAH_FINAL=?,RKVAH_DIFF=?,RKVAH_UNITS=?,KVAH_INIT=?,KVAH_FINAL=?,KVAH_DIFF=?,KVAH_UNITS=?, \n"
					+ "TOTAL_IMPORT_GEN=?,TOTAL_EXPORT_GEN=?,M_ORG_ID=?,M_COMPANY_ID=?,M_COMPANY_SERVICE_ID=?,DISP_COMPANY_NAME=?,DISP_SERVICE_NUMBER=?,INJECTING_VOLTAGE_CODE=?,DISP_ORG_NAME=?,\n"
					+ "POWER_FACTOR=?,NET_GENERATION=?,TOTAL_CHARGED_AMOUNT=?,C1=?,C2=?,C3=?,C4=?,C5=?,FILE_NAME=?,IS_CAPTIVE=?,IS_STB=?,TARIFF_NET_AMOUNT=?,TYPE_OF_SS=?,MR_SOURCE_CODE=? where id=?";
			if (jdbcOperations.update(sql, setGenerationStatementValues(generationStatement)) > 0) {
				result = generationStatement.getId();

			} else {
				result = "FAILURE";
			}
		} catch (Exception e) {
			result = "FAILURE";
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String addGenerationStatementCharge(GenerationStatementCharge generationStatementCharge) {
		String result = "";
		try {
			generationStatementCharge.setId(generateId());
			String sql = "insert into T_GEN_STMT_CHARGE(REMARKS,T_GEN_STMT_ID,CHARGE_CODE,CHARGE_DESC,CHARGE_TYPE_CODE,UNIT_CHARGE,TOTAL_CHARGES,ID)values(?,?,?,?,?,?,?,?)";
			if (jdbcOperations.update(sql, setGenerationStatementChargeValues(generationStatementCharge)) > 0) {
				result = generationStatementCharge.getId();

			} else {
				result = "FAILURE";
			}
		} catch (Exception e) {
			result = "FAILURE";
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String updateGenerationStatementCharge(GenerationStatementCharge generationStatementCharge) {
		String result = "";
		try {

			String sql = "update T_GEN_STMT_CHARGE set REMARKS=?,T_GEN_STMT_ID=?,CHARGE_CODE=?,CHARGE_DESC=?,CHARGE_TYPE_CODE=?,UNIT_CHARGE=?,TOTAL_CHARGES=? where ID=?";
			if (jdbcOperations.update(sql, setGenerationStatementChargeValues(generationStatementCharge)) > 0) {
				result = generationStatementCharge.getId();

			} else {
				result = "FAILURE";
			}
		} catch (Exception e) {
			result = "FAILURE";
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String addGenerationStatementSlot(GenerationStatementSlot generationStatementSlot) {
		String result = "";
		try {
			generationStatementSlot.setId(generateId());
			String sql = "insert into T_GEN_STMT_SLOT(REMARKS,REF_NUMBER,T_GEN_STMT_ID,SLOT_CODE,IMP_INIT,IMP_FINAL,IMP_DIFF,\n"
					+ "IMP_UNITS,EXP_INIT,EXP_FINAL,EXP_DIFF,EXP_UNITS,BANKED_BALANCE,NET_UNITS,ID) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			if (jdbcOperations.update(sql, setGenerationStatementSlotValues(generationStatementSlot)) > 0) {
				result = generationStatementSlot.getId();

			} else {
				result = "FAILURE";
			}
		} catch (Exception e) {
			result = "FAILURE";
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String updateGenerationStatementSlot(GenerationStatementSlot generationStatementSlot) {
		String result = "";
		try {

			String sql = "update T_GEN_STMT_SLOT SET REMARKS=?,REF_NUMBER=?,T_GEN_STMT_ID=?,SLOT_CODE=?,IMP_INIT=?,IMP_FINAL=?,IMP_DIFF=?,\n"
					+ "IMP_UNITS=?,EXP_INIT=?,EXP_FINAL=?,EXP_DIFF=?,EXP_UNITS=?,BANKED_BALANCE=?,NET_UNITS=? where ID=?";
			if (jdbcOperations.update(sql, setGenerationStatementSlotValues(generationStatementSlot)) > 0) {
				result = generationStatementSlot.getId();

			} else {
				result = "FAILURE";
			}
		} catch (Exception e) {
			result = "FAILURE";
			e.printStackTrace();
		}
		return result;
	}

	private Object[] setGenerationStatementValues(GenerationStatement generationStatement) {
		return new Object[] { generationStatement.getStatusCode(), generationStatement.getRemarks(),
				generationStatement.getCompanyMeterId(), generationStatement.getReferenceNumber(),
				generationStatement.getMrIds(), generationStatement.getMf(), generationStatement.getMachineCapacity(),
				generationStatement.getCommissionDate(), generationStatement.getStatementGenerationDate(),
				generationStatement.getStatementMonth(), generationStatement.getStatementYear(),
				generationStatement.getInitStatementDate(), generationStatement.getFinalStatementDate(),
				generationStatement.getrKvahInitial(), generationStatement.getrKvahFinal(),
				generationStatement.getrKvahDifference(), generationStatement.getrKvahUnits(),
				generationStatement.getkVahInitial(), generationStatement.getkVahFinal(),
				generationStatement.getkVahDifference(), generationStatement.getkVahUnits(),
				generationStatement.getTotalImportGeneration(), generationStatement.getTotalExportGeneration(),
				generationStatement.getOrgId(), generationStatement.getCompanyId(),
				generationStatement.getCompanyServiceId(), generationStatement.getDispCompanyName(),
				generationStatement.getDispCompanyServiceNumber(), generationStatement.getInjectingVoltageCode(),
				generationStatement.getDispOrgName(), generationStatement.getPowerFactor(),
				generationStatement.getNetGeneration(), generationStatement.getTotalChargedAmount(),
				generationStatement.getC1(), generationStatement.getC2(), generationStatement.getC3(),
				generationStatement.getC4(), generationStatement.getC5(), generationStatement.getFileName(),
				generationStatement.getIsCaptive(), generationStatement.getIsStb(),
				generationStatement.getTariffNetAmount(), generationStatement.getTypeOfSS(),
				generationStatement.getMrSourceCode(), generationStatement.getId(),
				generationStatement.getDispFuelTypeCode(), generationStatement.getDispFuelTypeName(),
				generationStatement.getDispFuelTypeGroup(), generationStatement.getDocId()

		};
	}

	private Object[] setGenerationStatementChargeValues(GenerationStatementCharge generationStatementCharge) {
		return new Object[] { generationStatementCharge.getRemarks(),
				generationStatementCharge.getGenerationStatementId(), generationStatementCharge.getChargeCode(),
				generationStatementCharge.getChargeDesc(), generationStatementCharge.getChargeTypeCode(),
				generationStatementCharge.getUnitCharge(), generationStatementCharge.getTotalCharges(),
				generationStatementCharge.getId()

		};

	}

	private Object[] setGenerationStatementSlotValues(GenerationStatementSlot generationStatementSlot) {
		return new Object[] { generationStatementSlot.getRemarks(), generationStatementSlot.getReferenceNumber(),
				generationStatementSlot.getGenerationStatementId(), generationStatementSlot.getSlotCode(),
				generationStatementSlot.getImpInitial(), generationStatementSlot.getImpFinal(),
				generationStatementSlot.getImpDifference(), generationStatementSlot.getImpUnits(),
				generationStatementSlot.getExpInitial(), generationStatementSlot.getExpFinal(),
				generationStatementSlot.getExpDifference(), generationStatementSlot.getExpUnits(),
				generationStatementSlot.getBankedBalance(), generationStatementSlot.getNetUnits(),
				generationStatementSlot.getId()

		};

	}

	final class GenerationStatementMapper implements RowMapper<GenerationStatement> {

		public GenerationStatementMapper() {
			super();
		}

		public GenerationStatement mapRow(ResultSet resultSet, int rownum) throws SQLException {
			GenerationStatement generationStatement = new GenerationStatement();
			generationStatement.setId(resultSet.getString("ID"));
			generationStatement.setStatusCode(resultSet.getString("STATUS_CODE"));
			generationStatement.setRemarks(resultSet.getString("REMARKS"));
			generationStatement.setCompanyMeterId(resultSet.getString("M_COMPANY_METER_ID"));
			generationStatement.setReferenceNumber(resultSet.getString("REF_NUMBER"));
			generationStatement.setMrIds(resultSet.getString("T_MR_IDS"));
			generationStatement.setMf(resultSet.getString("MF"));
			generationStatement.setMachineCapacity(resultSet.getString("MACHINE_CAPACITY"));
			generationStatement.setCommissionDate(resultSet.getString("COMMISSION_DATE"));
			generationStatement.setStatementGenerationDate(resultSet.getString("STMT_GEN_DATE"));
			generationStatement.setStatementMonth(resultSet.getString("STMT_MONTH"));
			generationStatement.setStatementYear(resultSet.getString("STMT_YEAR"));
			generationStatement.setInitStatementDate(resultSet.getString("INIT_STMT_DT"));
			generationStatement.setFinalStatementDate(resultSet.getString("FINAL_STMT_DT"));
			generationStatement.setrKvahInitial(resultSet.getString("RKVAH_INIT"));
			generationStatement.setrKvahFinal(resultSet.getString("RKVAH_FINAL"));
			generationStatement.setrKvahDifference(resultSet.getString("RKVAH_DIFF"));
			generationStatement.setrKvahUnits(resultSet.getString("RKVAH_UNITS"));
			generationStatement.setkVahInitial(resultSet.getString("KVAH_INIT"));
			generationStatement.setkVahFinal(resultSet.getString("KVAH_FINAL"));
			generationStatement.setkVahDifference(resultSet.getString("KVAH_DIFF"));
			generationStatement.setkVahUnits(resultSet.getString("KVAH_UNITS"));
			generationStatement.setTotalImportGeneration(resultSet.getString("TOTAL_IMPORT_GEN"));
			generationStatement.setTotalExportGeneration(resultSet.getString("TOTAL_EXPORT_GEN"));
			generationStatement.setOrgId(resultSet.getString("M_ORG_ID"));
			generationStatement.setCompanyId(resultSet.getString("M_COMPANY_ID"));
			generationStatement.setCompanyServiceId(resultSet.getString("M_COMPANY_SERVICE_ID"));
			generationStatement.setDispCompanyName(resultSet.getString("DISP_COMPANY_NAME"));
			generationStatement.setDispCompanyServiceNumber(resultSet.getString("DISP_SERVICE_NUMBER"));
			generationStatement.setInjectingVoltageCode(resultSet.getString("INJECTING_VOLTAGE_CODE"));
			generationStatement.setInjectingVoltageName(resultSet.getString("INJECTING_VOLTAGE_DESC"));
			generationStatement.setDispOrgName(resultSet.getString("DISP_ORG_NAME"));
			generationStatement.setPowerFactor(resultSet.getString("POWER_FACTOR"));
			generationStatement.setNetGeneration(resultSet.getString("NET_GENERATION"));
			generationStatement.setTotalChargedAmount(resultSet.getString("TOTAL_CHARGED_AMOUNT"));
			generationStatement.setC1(resultSet.getString("C1"));
			generationStatement.setC2(resultSet.getString("C2"));
			generationStatement.setC3(resultSet.getString("C3"));
			generationStatement.setC4(resultSet.getString("C4"));
			generationStatement.setC5(resultSet.getString("C5"));
			generationStatement.setFileName(resultSet.getString("FILE_NAME"));
			generationStatement.setPlantClassTypeCode(resultSet.getString("PLANT_CLASS_TYPE_CODE"));
			generationStatement.setPlantClassTypeDesc(resultSet.getString("PLANT_CLASS_TYPE_DESC"));
			generationStatement.setTariffRate(resultSet.getString("TARIFF_RATE"));
			generationStatement.setNetPayable(resultSet.getString("NET_PAYABLE"));
			generationStatement.setIsCaptive(resultSet.getString("IS_CAPTIVE"));
			generationStatement.setIsStb(resultSet.getString("IS_STB"));
			generationStatement.setTariffNetAmount(resultSet.getString("TARIFF_NET_AMOUNT"));
			generationStatement.setTypeOfSS(resultSet.getString("TYPE_OF_SS"));
			generationStatement.setFlowTypeCode(resultSet.getString("FLOW_TYPE_CODE"));
			generationStatement.setIsRec(resultSet.getString("IS_REC"));
			generationStatement.setTotalSsLoss(resultSet.getString("TOTAL_SS_LOSS"));
			generationStatement.setSsLossPercent(resultSet.getString("SS_LOSS_PERCENT"));
			generationStatement.setDisSubstationId(resultSet.getString("M_SUBSTATION_ID"));
			generationStatement.setDisSubstationName(resultSet.getString("M_SUBSTATION_NAME"));
			generationStatement.setIsMeterChange(resultSet.getString("IS_METER_CHANGE"));
			generationStatement.setMrSourceCode(resultSet.getString("MR_SOURCE_CODE"));
			generationStatement.setDispFuelTypeCode(resultSet.getString("DISP_FUEL_TYPE_CODE"));
			generationStatement.setDispFuelTypeName(resultSet.getString("DISP_FUEL_TYPE_NAME"));
			generationStatement.setDispFuelTypeGroup(resultSet.getString("DISP_FUEL_TYPE_GROUP"));
			generationStatement.setDocId(resultSet.getString("DOC_ID"));
			generationStatement.setStmtRemarks(resultSet.getString("STMT_REMARKS"));

			return generationStatement;
		}
	}

	final class GenerationStatementChargeMapper implements RowMapper<GenerationStatementCharge> {

		public GenerationStatementChargeMapper() {
			super();
		}

		public GenerationStatementCharge mapRow(ResultSet resultSet, int rownum) throws SQLException {
			GenerationStatementCharge generationStatementCharge = new GenerationStatementCharge();
			generationStatementCharge.setId(resultSet.getString("ID"));
			generationStatementCharge.setRemarks(resultSet.getString("REMARKS"));
			generationStatementCharge.setGenerationStatementId(resultSet.getString("T_GEN_STMT_ID"));
			generationStatementCharge.setChargeCode(resultSet.getString("CHARGE_CODE"));
			generationStatementCharge.setChargeDesc(resultSet.getString("CHARGE_DESC"));
			generationStatementCharge.setChargeTypeCode(resultSet.getString("CHARGE_TYPE_CODE"));
			generationStatementCharge.setChargeTypeName(resultSet.getString("CHARGE_TYPE_NAME"));
			generationStatementCharge.setUnitCharge(resultSet.getString("UNIT_CHARGE"));
			generationStatementCharge.setTotalCharges(resultSet.getString("TOTAL_CHARGES"));
			generationStatementCharge.setEtaxRate(resultSet.getString("ETAX_RATE"));
			generationStatementCharge.setIecRate(resultSet.getString("IEC_RATE"));
			
			return generationStatementCharge;
		}
	}

	final class GenerationStatementSlotMapper implements RowMapper<GenerationStatementSlot> {

		public GenerationStatementSlotMapper() {
			super();
		}

		public GenerationStatementSlot mapRow(ResultSet resultSet, int rownum) throws SQLException {
			GenerationStatementSlot generationStatementSlot = new GenerationStatementSlot();
			generationStatementSlot.setId(resultSet.getString("ID"));
			generationStatementSlot.setRemarks(resultSet.getString("REMARKS"));
			generationStatementSlot.setReferenceNumber(resultSet.getString("REF_NUMBER"));
			generationStatementSlot.setGenerationStatementId(resultSet.getString("T_GEN_STMT_ID"));
			generationStatementSlot.setSlotCode(resultSet.getString("SLOT_CODE"));
			generationStatementSlot.setSlotName(resultSet.getString("SLOT_NAME"));
			generationStatementSlot.setImpInitial(resultSet.getString("IMP_INIT"));
			generationStatementSlot.setImpFinal(resultSet.getString("IMP_FINAL"));
			generationStatementSlot.setImpDifference(resultSet.getString("IMP_DIFF"));
			generationStatementSlot.setImpUnits(resultSet.getString("IMP_UNITS"));
			generationStatementSlot.setExpInitial(resultSet.getString("EXP_INIT"));
			generationStatementSlot.setExpFinal(resultSet.getString("EXP_FINAL"));
			generationStatementSlot.setExpDifference(resultSet.getString("EXP_DIFF"));
			generationStatementSlot.setExpUnits(resultSet.getString("EXP_UNITS"));
			generationStatementSlot.setBankedBalance(resultSet.getString("BANKED_BALANCE"));
			generationStatementSlot.setNetUnits(resultSet.getString("NET_UNITS"));
			return generationStatementSlot;
		}
	}

}
