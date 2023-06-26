package com.ss.oa.integration.samastNocReading;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ss.oa.common.BaseDaoJdbc;
import com.ss.oa.vo.ExtSamastAppln;

@Component
public class ExtSamstApplnDaoImpl extends BaseDaoJdbc implements ExtSamstApplnDao{
	
	@Value("${samastdb.schemaname}")     private String samastSchemaName;
	@Value("${samastdb.importviewname}")     private String importViewName;
	//@Value("${samastdb.importappldateformat}")     private String importApplDateFormat;
	@Resource
	@Qualifier("samastJdbc")
	private JdbcOperations jdbcOperations;
	
	@Override
	public  List<ExtSamastAppln> getSamastNocByStatus(String fromDt,String toDt){
		String sql;			

		if(fromDt.equals("new-record")) {		
			sql="SELECT * From "+samastSchemaName+"."+importViewName+" where upper(edc_status) = 'WAITING' OR upper(edc_status) ='PROCESSING'";
		}else {	
			sql="SELECT * From "+samastSchemaName+"."+importViewName+" where upper(edc_status) = 'WAITING' AND TRUNC(APPL_DATE) BETWEEN TO_DATE('"+fromDt+"','yyyy-mm-dd') AND TO_DATE('"+toDt+"','yyyy-mm-dd') ";
		}
		return (ArrayList<ExtSamastAppln>) jdbcOperations.query(sql,new SamstApplnReadingMapper());
	}
	
	@Override
	public  ArrayList<ExtSamastAppln> getSamastNocByApprovalStatus(String fromDt,String toDt){
		String sql;				
		if(fromDt.equals("new-record")) {	
			sql="SELECT * From "+samastSchemaName+"."+importViewName+" where upper(APPLICATION_STATUS)='ACCEPTED'";
		}else {
			 sql="SELECT * From "+samastSchemaName+"."+importViewName+" where upper(APPLICATION_STATUS)='ACCEPTED' AND TRUNC(APPL_DATE) BETWEEN TO_DATE('"+fromDt+"','yyyy-mm-dd') AND TO_DATE('"+toDt+"','yyyy-mm-dd')";
		}
		return (ArrayList<ExtSamastAppln>) jdbcOperations.query(sql,new SamstApplnReadingMapper());
	}
	
	final class SamstApplnReadingMapper implements RowMapper<ExtSamastAppln>{
		
		public SamstApplnReadingMapper() {
			super();
		}

		@Override
		public ExtSamastAppln mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			ExtSamastAppln extSamstAppln = new ExtSamastAppln();
			
			extSamstAppln.setAppId(rs.getString("APP_ID"));
			extSamstAppln.setApplRefNo(rs.getString("APPL_REF_NO"));
			extSamstAppln.setApplNo(rs.getString("APPL_NO"));
			extSamstAppln.setApplDate(rs.getString("APPL_DATE"));
			extSamstAppln.setAppCategory(rs.getString("APP_CATEGORY"));
			extSamstAppln.setCustomerName(rs.getString("CUSTOMER_NAME"));
			extSamstAppln.setEntityInj(rs.getString("ENTITY_INJ"));
			extSamstAppln.setEntityInjEdc(rs.getString("ENTITY_INJ_EDC"));
			extSamstAppln.setEntityDrl(rs.getString("ENTITY_DRL"));
			extSamstAppln.setEntityDrlEdc(rs.getString("ENTITY_DRL_EDC"));
			extSamstAppln.setPeriodStartDate(rs.getString("PERIOD_START_DATE"));
			extSamstAppln.setPeriodEndDate(rs.getString("PERIOD_END_DATE"));			
			extSamstAppln.setQuantum(rs.getString("QUANTUM"));
			extSamstAppln.setCategory1(rs.getString("CATEGORY1"));
			extSamstAppln.setCategory2(rs.getString("CATEGORY2"));
			extSamstAppln.setEorRegType(rs.getString("EOR_REG_TYPE"));
			extSamstAppln.setEosGcApprovalNumber(rs.getString("EOS_GC_APPROVAL_NUMBER"));
			extSamstAppln.setEosFeederNameSeller(rs.getString("EOS_FEEDER_NAME_SELLER"));
			extSamstAppln.setEosVolLvlFeeder(rs.getString("EOS_VOL_LVL_FEEDER"));			
			extSamstAppln.setEosInjSubstation(rs.getString("EOS_INJ_SUBSTATION"));
			extSamstAppln.setEosVolLvlSubstation(rs.getString("EOS_VOL_LVL_SUBSTATION"));
			extSamstAppln.setEocLossPer(rs.getString("EOC_LOSS_PER"));
			extSamstAppln.setEvacuationCapacity(rs.getString("EVACUATION_CAPACITY"));
			extSamstAppln.setEosUtilInjEmbed(rs.getString("EOS_UTIL_INJ_EMBED"));
			extSamstAppln.setEosUtilInjEmbedLabel(rs.getString("EOS_UTIL_INJ_EMBED_LABEL"));
			extSamstAppln.setEobUtilDrawalEmbedLabel(rs.getString("EOB_UTIL_DRAWAL_EMBED_LABEL"));
			extSamstAppln.setEobUtilDraEmbed(rs.getString("EOB_UTIL_DRA_EMBED"));
			extSamstAppln.setEobHtConsumerNumber(rs.getString("EOB_HT_CONSUMER_NUMBER"));
			extSamstAppln.setEobFeederNameBuyer(rs.getString("EOB_FEEDER_NAME_BUYER"));
			extSamstAppln.setEobVolLvlFeeder(rs.getString("EOB_VOL_LVL_FEEDER"));
			extSamstAppln.setEobDraSubstation(rs.getString("EOB_DRA_SUBSTATION"));
			extSamstAppln.setEobVolLvlSubstation(rs.getString("EOB_VOL_LVL_SUBSTATION"));			
			extSamstAppln.setEobDrawalLimit(rs.getString("EOB_DRAWAL_LIMIT"));
			extSamstAppln.setEobBuyerType(rs.getString("EOB_BUYER_TYPE"));
			extSamstAppln.setEoiRegName(rs.getString("EOI_REG_NAME"));
			extSamstAppln.setEoiRegAddress(rs.getString("EOI_REG_ADDRESS"));
			extSamstAppln.setEoiRegMobile1No(rs.getString("EOI_REG_MOBILE1_NO"));
			extSamstAppln.setEdcStatus(rs.getString("EDC_STATUS"));
			extSamstAppln.setApplicationStatus(rs.getString("APPLICATION_STATUS"));
			extSamstAppln.setAppType(rs.getString("APP_TYPE"));
			extSamstAppln.setIpApprNo(rs.getString("IP_APPR_NO"));
			extSamstAppln.setSellerEdcCode(rs.getString("SELLER_EDC_CODE"));
			extSamstAppln.setBuyerEdcCode(rs.getString("BUYER_EDC_CODE"));
			extSamstAppln.setApprovalNo(rs.getString("APPROVAL_NO"));
			return extSamstAppln;
		}		
	}
}
