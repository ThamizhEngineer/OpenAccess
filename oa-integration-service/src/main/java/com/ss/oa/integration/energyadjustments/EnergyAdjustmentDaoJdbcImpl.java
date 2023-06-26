package com.ss.oa.integration.energyadjustments;
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
import com.ss.oa.vo.EnergyAdjustmentForDAO;
@Component
public class EnergyAdjustmentDaoJdbcImpl extends BaseDaoJdbc implements EnergyAdjustmentDao {
	@Resource
	private JdbcOperations jdbcOperations;
	@Override
	public List<EnergyAdjustmentForDAO> getAllEnergyAdjustments(Map<String, String> criteria) {
		String sql="SELECT distinct el.id, el.D_COMP_SERV_NUMBER service_number,el.D_ORG_CODE ORG_CODE,el.D_ORG_NAME ORG_NAME, el.D_COMP_NAME buyer_name, el.MONTH billing_month, el.YEAR billing_year, \n" + 
				"es.D_SELL_COMP_SERV_NUMBER seller_number, es.D_SELL_COMP_NAME seller_name,el.D_COMMISSION_DATE commission_date,el.D_IS_REC is_rec,el.D_ADJUSTMENT_PRIORITY adjustment_priority,el.ALLOW_LOWER_SLOT_ADMT ALLOW_LOWER_SLOT_ADMT,\n" + 
				"esl.c1 inj_c1, esl.c2 inj_c2, esl.c3 inj_c3, esl.c4 inj_c4, esl.c5 inj_c5, esl.TOTAL_UNITS_SOLD inj_units,\n" + 
				"nvl(dl.c1,0) dl_c1, nvl(dl.c2,0) dl_c2, nvl(dl.c3,0) dl_c3, nvl(dl.c4,0) dl_c4, nvl(dl.c5,0) dl_c5, nvl(dl.ENERGY_OUT,0) dl_units,\n" + 
				"nvl(tl.c1,0) tl_c1, nvl(tl.c2,0) tl_c2, nvl(tl.c3,0) tl_c3, nvl(tl.c4,0) tl_c4, nvl(tl.c5,0) tl_c5, nvl(tl.ENERGY_OUT,0) tl_units,\n" + 
				"nvl(tl.c1,0)+nvl(dl.c1,0) l_c1,nvl(tl.c2,0)+nvl(dl.c2,0) l_c2,nvl(tl.c3,0)+nvl(dl.c3,0) l_c3,nvl(tl.c4,0)+nvl(dl.c4,0) l_c4,nvl(tl.c5,0)+nvl(dl.c5,0) l_c5,nvl(tl.ENERGY_OUT,0)+nvl(dl.ENERGY_OUT ,0) l_units,\n" + 
				"nvl(el.c1,0) dr_c1, nvl(el.c2,0) dr_c2, nvl(el.c3,0) dr_c3, nvl(el.c4,0) dr_c4, nvl(el.c5,0) dr_c5, nvl(el.ENERGY_OUT,0) dr_units,\n" + 
				"C001.CHARGE_CODE C001_CHARGE_CODE, C001.CHARGE_DESC C001_CHARGE_DESC,nvl(C001.TOTAL_CHARGES,0) C001_TOTAL_CHARGES,\n" + 
				"C002.CHARGE_CODE C002_CHARGE_CODE, C002.CHARGE_DESC C002_CHARGE_DESC,nvl(C002.TOTAL_CHARGES,0) C002_TOTAL_CHARGES,\n" + 
				"C003.CHARGE_CODE C003_CHARGE_CODE, C003.CHARGE_DESC C003_CHARGE_DESC,nvl(C003.TOTAL_CHARGES,0) C003_TOTAL_CHARGES,\n" + 
				"C004.CHARGE_CODE C004_CHARGE_CODE, C004.CHARGE_DESC C004_CHARGE_DESC,nvl(C004.TOTAL_CHARGES,0) C004_TOTAL_CHARGES,\n" + 
				"C005.CHARGE_CODE C005_CHARGE_CODE, C005.CHARGE_DESC C005_CHARGE_DESC,nvl(C005.TOTAL_CHARGES,0) C005_TOTAL_CHARGES,\n" + 
				"C006.CHARGE_CODE C006_CHARGE_CODE, C006.CHARGE_DESC C006_CHARGE_DESC,nvl(C006.TOTAL_CHARGES,0) C006_TOTAL_CHARGES, \n" + 
				"C007.CHARGE_CODE C007_CHARGE_CODE, C007.CHARGE_DESC C007_CHARGE_DESC,nvl(C007.TOTAL_CHARGES,0) C007_TOTAL_CHARGES,\n" + 
				"C008.CHARGE_CODE C008_CHARGE_CODE, C008.CHARGE_DESC C008_CHARGE_DESC,nvl(C008.TOTAL_CHARGES,0) C008_TOTAL_CHARGES \n" + 
				"FROM F_ENERGY_LEDGER  el LEFT JOIN F_ENERGY_SALE_ORDER es ON el.F_ENERGY_SALE_ORDER_ID = es.id  \n" + 
				"LEFT JOIN F_ENERGY_SALE_ORDER_LINES esl  ON el.F_ENERGY_SALE_ORDER_LINES_ID = esl.id \n" + 
				"LEFT JOIN F_ENERGY_LEDGER tl  ON el.F_ENERGY_SALE_ORDER_LINES_ID = tl.F_ENERGY_SALE_ORDER_LINES_ID AND tl.SERVICE_TYPE_CODE = '04' \n" + 
				"LEFT JOIN F_ENERGY_LEDGER dl  ON el.F_ENERGY_SALE_ORDER_LINES_ID = dl.F_ENERGY_SALE_ORDER_LINES_ID AND dl.SERVICE_TYPE_CODE = '05' \n" + 
				"LEFT JOIN F_ENERGY_CHARGES C001  ON el.F_ENERGY_SALE_ORDER_ID = C001.F_ENERGY_SALE_ORDER_ID AND C001.CHARGE_CODE = 'C001' AND C001.M_COMPANY_SERVICE_ID = el.M_COMPANY_SERVICE_ID \n" + 
				"LEFT JOIN F_ENERGY_CHARGES C002  ON el.F_ENERGY_SALE_ORDER_ID = C002.F_ENERGY_SALE_ORDER_ID AND C002.CHARGE_CODE = 'C002' AND C002.M_COMPANY_SERVICE_ID = el.M_COMPANY_SERVICE_ID \n" + 
				"LEFT JOIN F_ENERGY_CHARGES C003  ON el.F_ENERGY_SALE_ORDER_ID = C003.F_ENERGY_SALE_ORDER_ID AND C003.CHARGE_CODE = 'C003' AND C003.M_COMPANY_SERVICE_ID = el.M_COMPANY_SERVICE_ID \n" + 
				"LEFT JOIN F_ENERGY_CHARGES C004  ON el.F_ENERGY_SALE_ORDER_ID = C004.F_ENERGY_SALE_ORDER_ID AND C004.CHARGE_CODE = 'C004' AND C004.M_COMPANY_SERVICE_ID = el.M_COMPANY_SERVICE_ID \n" + 
				"LEFT JOIN F_ENERGY_CHARGES C005  ON el.F_ENERGY_SALE_ORDER_ID = C005.F_ENERGY_SALE_ORDER_ID AND C005.CHARGE_CODE = 'C005' AND C005.M_COMPANY_SERVICE_ID = el.M_COMPANY_SERVICE_ID \n" + 
				"LEFT JOIN F_ENERGY_CHARGES C006  ON el.F_ENERGY_SALE_ORDER_ID = C006.F_ENERGY_SALE_ORDER_ID AND C006.CHARGE_CODE = 'C006' AND C006.M_COMPANY_SERVICE_ID = el.M_COMPANY_SERVICE_ID \n" + 
				"LEFT JOIN F_ENERGY_CHARGES C007  ON el.F_ENERGY_SALE_ORDER_ID = C007.F_ENERGY_SALE_ORDER_ID AND C007.CHARGE_CODE = 'C007' AND C007.M_COMPANY_SERVICE_ID = el.M_COMPANY_SERVICE_ID \n" + 
				"LEFT JOIN F_ENERGY_CHARGES C008  ON el.F_ENERGY_SALE_ORDER_ID = C008.F_ENERGY_SALE_ORDER_ID AND C008.CHARGE_CODE = 'C008' AND C008.M_COMPANY_SERVICE_ID = el.M_COMPANY_SERVICE_ID \n" + 
				"where el.service_type_code='02'";
		
		
//		System.out.println("criteria-"+criteria);

		if(!criteria.isEmpty())
		{
			if(criteria.get("serviceNumber")!=null){
				sql += "AND el.D_COMP_SERV_NUMBER = '"+criteria.get("serviceNumber")+"' ";
			}
			if(criteria.get("billingMonth")!=null){
				sql += "and el.\"MONTH\"='"+criteria.get("billingMonth")+"' ";
			}
			if(criteria.get("billingYear")!=null){
				sql += "and el.\"YEAR\"='"+criteria.get("billingYear")+"' ";
			}
			if(criteria.get("orgCode")!=null){
				sql += "AND el.D_ORG_CODE = '"+criteria.get("orgCode")+"' ";
			}

		}
//		System.out.println("sql-"+sql);
		
		return   (ArrayList<EnergyAdjustmentForDAO>) jdbcOperations.query(sql,new EnergyAdjustmentMapper());
			 
	}

	final class EnergyAdjustmentMapper implements RowMapper<EnergyAdjustmentForDAO>
	{

		public EnergyAdjustmentMapper() {
				super();
		}

		public EnergyAdjustmentForDAO mapRow(ResultSet resultset, int rownum) throws SQLException {
			EnergyAdjustmentForDAO energyAdjustment=new EnergyAdjustmentForDAO();
			energyAdjustment.setServiceNumber(resultset.getString("service_number"));
			energyAdjustment.setOrgCode(resultset.getString("ORG_CODE"));
			energyAdjustment.setOrgName(resultset.getString("ORG_NAME"));
			energyAdjustment.setBuyerName(resultset.getString("buyer_name"));
			energyAdjustment.setBillingMonth(resultset.getString("billing_month"));
			energyAdjustment.setBillingYear(resultset.getString("billing_year"));
			energyAdjustment.setSellerServiceNumber(resultset.getString("seller_number"));
			energyAdjustment.setSellerServiceName(resultset.getString("seller_name"));
			energyAdjustment.setCommissionDate(resultset.getString("commission_date"));
			energyAdjustment.setIsRec(resultset.getString("is_rec"));
			energyAdjustment.setAdjustmentPriority(resultset.getString("adjustment_priority"));
			energyAdjustment.setAllowLowerSlotAdmt(resultset.getString("ALLOW_LOWER_SLOT_ADMT"));
			energyAdjustment.setInj_c1(resultset.getString("inj_c1"));
			energyAdjustment.setInj_c2(resultset.getString("inj_c2"));
			energyAdjustment.setInj_c3(resultset.getString("inj_c3"));
			energyAdjustment.setInj_c4(resultset.getString("inj_c4"));
			energyAdjustment.setInj_c5(resultset.getString("inj_c5"));
			energyAdjustment.setInj_units(resultset.getString("inj_units"));
			energyAdjustment.setTl_c1(resultset.getString("tl_c1"));
			energyAdjustment.setTl_c2(resultset.getString("tl_c2"));
			energyAdjustment.setTl_c3(resultset.getString("tl_c3"));
			energyAdjustment.setTl_c4(resultset.getString("tl_c4"));
			energyAdjustment.setTl_c5(resultset.getString("tl_c5"));
			energyAdjustment.setTl_units(resultset.getString("tl_units"));
			energyAdjustment.setDl_c1(resultset.getString("dl_c1"));
			energyAdjustment.setDl_c2(resultset.getString("dl_c2"));
			energyAdjustment.setDl_c3(resultset.getString("dl_c3"));
			energyAdjustment.setDl_c4(resultset.getString("dl_c4"));
			energyAdjustment.setDl_c5(resultset.getString("dl_c5"));
			energyAdjustment.setDl_units(resultset.getString("dl_units"));
			energyAdjustment.setL_c1(resultset.getString("l_c1"));
			energyAdjustment.setL_c2(resultset.getString("l_c2"));
			energyAdjustment.setL_c3(resultset.getString("l_c3"));
			energyAdjustment.setL_c4(resultset.getString("l_c4"));
			energyAdjustment.setL_c5(resultset.getString("l_c5"));
			energyAdjustment.setL_units(resultset.getString("l_units"));
			energyAdjustment.setDr_c1(resultset.getString("dr_c1"));
			energyAdjustment.setDr_c2(resultset.getString("dr_c2"));
			energyAdjustment.setDr_c3(resultset.getString("dr_c3"));
			energyAdjustment.setDr_c4(resultset.getString("dr_c4"));
			energyAdjustment.setDr_c5(resultset.getString("dr_c5"));
			energyAdjustment.setDr_units(resultset.getString("dr_units"));
			energyAdjustment.setC001_CHARGE_CODE(resultset.getString("C001_CHARGE_CODE"));
			energyAdjustment.setC001_CHARGE_DESC(resultset.getString("C001_CHARGE_DESC"));
			energyAdjustment.setC001_TOTAL_CHARGES(resultset.getString("C001_TOTAL_CHARGES"));
			energyAdjustment.setC002_CHARGE_CODE(resultset.getString("C002_CHARGE_CODE"));
			energyAdjustment.setC002_CHARGE_DESC(resultset.getString("C002_CHARGE_DESC"));
			energyAdjustment.setC002_TOTAL_CHARGES(resultset.getString("C002_TOTAL_CHARGES"));
			energyAdjustment.setC003_CHARGE_CODE(resultset.getString("C003_CHARGE_CODE"));
			energyAdjustment.setC003_CHARGE_DESC(resultset.getString("C003_CHARGE_DESC"));
			energyAdjustment.setC003_TOTAL_CHARGES(resultset.getString("C003_TOTAL_CHARGES"));
			energyAdjustment.setC004_CHARGE_CODE(resultset.getString("C004_CHARGE_CODE"));
			energyAdjustment.setC004_CHARGE_DESC(resultset.getString("C004_CHARGE_DESC"));
			energyAdjustment.setC004_TOTAL_CHARGES(resultset.getString("C004_TOTAL_CHARGES"));
			energyAdjustment.setC005_CHARGE_CODE(resultset.getString("C005_CHARGE_CODE"));
			energyAdjustment.setC005_CHARGE_DESC(resultset.getString("C005_CHARGE_DESC"));
			energyAdjustment.setC005_TOTAL_CHARGES(resultset.getString("C005_TOTAL_CHARGES"));
			energyAdjustment.setC006_CHARGE_CODE(resultset.getString("C006_CHARGE_CODE"));
			energyAdjustment.setC006_CHARGE_DESC(resultset.getString("C006_CHARGE_DESC"));
			energyAdjustment.setC006_TOTAL_CHARGES(resultset.getString("C006_TOTAL_CHARGES"));
			energyAdjustment.setC007_CHARGE_CODE(resultset.getString("C007_CHARGE_CODE"));
			energyAdjustment.setC007_CHARGE_DESC(resultset.getString("C007_CHARGE_DESC"));
			energyAdjustment.setC007_TOTAL_CHARGES(resultset.getString("C007_TOTAL_CHARGES"));
			energyAdjustment.setC008_CHARGE_CODE(resultset.getString("C008_CHARGE_CODE"));
			energyAdjustment.setC008_CHARGE_DESC(resultset.getString("C008_CHARGE_DESC"));
			energyAdjustment.setC008_TOTAL_CHARGES(resultset.getString("C008_TOTAL_CHARGES"));
			return energyAdjustment;
		}
	}
}
