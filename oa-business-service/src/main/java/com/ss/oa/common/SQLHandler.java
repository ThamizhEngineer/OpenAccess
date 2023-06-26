package com.ss.oa.common;

import java.util.Map;

import org.springframework.context.annotation.Scope;
@Scope("prototype")
public class SQLHandler{
	
	static Map<String, String> ipCriteria;
	
	public static String GetCustomQuery(String reportName, Map<String,String> criteria){
		ipCriteria = criteria;
		String returnSql="";
		switch(reportName){
		case "GENERATOR-WISE-CONSUMER-REPORT":
			returnSql = getGeneratorWiseConsumerReport();
			break;
		case "PERIOD-BASED-APPROVAL-REPORT":
			returnSql = getPeriodBasedApprovalReport();
			break;
		case "PERIOD-BASED-AMENDMENT-REPORT":
			returnSql = getPeriodBasedAmendmentReport();
			break;
		case "TOTAL-TRANSACTION-DETAILS":
			returnSql = getTotalTransactionDetails();
			break;
		case "CONSUMER-REPORT":
			returnSql = getConsumerReport();
			break;
		case "GENERATION-REPORT":
			returnSql = getGenerationReport();
			break;
		case "WEG-GENERATOR-REPORT":
			returnSql = getWegGeneratorReport();
			break;	
			
		case "UNIMPORTED-WEG-SERVICES":
			returnSql = getUnimportedWegServicesReport();
			break;
			
		case "WEG-TRANSACTION-STATUS":
			returnSql = getWegTransactionStatusReport();
			break;
			
		case "NIL-GENERATION-REPORT":
			returnSql = getNilGenerationReport();
			break;
			
		case "UNALLOCATED-GEN-STMT-REPORT":
			returnSql = getUnalloctedGenStmtReport();
			break;
		
		case "ENERGY-LEDGER-REPORT":
			returnSql = getEnergyLedgerReport();
			break;
			
		case "REMARKS-BASED-BANKING-REPORT":
			returnSql = getRemarksBasedBankingReport();
			break;
			
		case "ENERGY-GENERATION-CHARGES-REPORT":
			returnSql = getEnergyGenerationChargesReport();
			break;
			
		case "CONS-ENERGY-ADJUSTED-ORDER-REPORT":
			returnSql = getConsEnergyAdjustedOrderReport();
			break;
			
		case "CONS-ENERGY-ADJUSTED-CHARGES-REPORT":
			returnSql = getConsEnergyAdjustedChargesReport();
			break;
			
		case "CONSOLIDATE-ENERGY-ADJUSTED-CHARGES-REPORT":
			returnSql = getConsolidateEnergyAdjustedChargesReport();
			break;
			
		case "CONTACT-DETAILS-OF-GENERATOR-REPORT":
			returnSql = getContactDetailsOfGeneratorReport();
			break;
			
		case "TNERC-NET-GENERATION-REPORT":
			returnSql = getTnercNetGenerationReport();
			break;
			
			
		case "TNERC-ENERGY-SUMMARY-REPORT":
			returnSql = getTnercEnergySummaryReport();
			break;
			
		case "GENERATOR-WISE-GENERATION-REPORT":
			returnSql = getGeneratorWiseGenerationReport();
			break;
		
		case "CONSUMER-WISE-ENERGY-ADJUSTMENT-ORDER-REPORT":
			returnSql = getConsumerWiseEnergyAdjustmentOrderReport();
			break;
			
		case "UNUTILISED-BANKING":
			returnSql = getUnutilisedBankingReport();
			break;
			
		case "SALE-TO-BOARD-LEDGER":
			returnSql = getSaleToBoardLedgerReport();
			break;
			
		case "GEN-CHARGES-ALLOC-TO-HT":
			returnSql = getGenChargesAllocToHtReport();
			break;
			
		case "UNUTILISED-BANKING-MAR-2020":
			returnSql = getUnutilisedBankingMar2020();
			break;
			
		case "UNUTILISED-BANKING-MAR-NEW":
			returnSql = getUnutilisedBankingMarNew();
			break;
			
		case "PROGRESS-REPORT-NEW":
			returnSql = getProgressReportNew();
			break;
			
		case "ENERGY-AUDIT-REPORT":
			returnSql = getEnergyAuditReport();
			break;
			
		case "GENERATOR-SUMMARY":
			returnSql = getGeneratorSummaryReport();
			break;
			
		case "WEG-CAPTIVE-GROUPWISE":
			returnSql = getWgeCaptiveGroupWiseReport();
			break;
			
		case "Progress-report-manual-reading":
			returnSql = getProgressReportManualReading();
			break;
			
		case "Progress-report-Meter-reading":
			returnSql = getProgressReportMeterReading();
			break;
			
		case "Progress-report-captive-allotment":
			returnSql = getProgressReportCaptiveAllotment();
			break;
			
		case "Progress-report-third-party-allotment":
			returnSql = getProgressReportThirdPartyAllotment();
			break;
			
		case "Progress-report-total-allotment":
			returnSql = getProgressReportTotalAllotment();
			break;
			
		case "Meter-change-report":
			returnSql = getMeterChangeReport();
			break;
			
		case "Gen-summary-charges":
			returnSql = getGenSummaryCharges();
			break;
			
		case "Meter-change-list":
			returnSql = getMeterChangeList();
			break;
			
		case "Samast-Appln-Report":
			returnSql = getSamastAppln();
			break;
			
		case "Ext-Samast-Appln-Table":
			returnSql = getExtSamastAppln();
			break;
			
		case "AMR DOWNLODE STATUS REPORT":
			returnSql = getAmrdownlodestatus();
			break;
			
		case "UTILITY-REPORT":
			returnSql = getUtilityReport();
			break; 
			
		case "Master-Report":
			returnSql = getMasterReport();
			break;
			
		case "WEG-10(1)SS LOSS REPORT":
			returnSql = getWEG10SSlossReport();
			break;
		case "TECHNICAL INFO REPORT":
			returnSql = getTechnicalInfo();
			break;
		case "Delete-Transaction-Report":
			returnSql =getDeleteTransactionReport();
			break;
		case "TANTRANSCO-GENERATOR-INFO-REPORT":
			returnSql = getTantranscoGeneratorInforeport();
			break;
		case "TANTRANSCO-PAYMENT-REPORT":
			returnSql = getTantranscoPaymentInforeport();
			break;
		case "METER READING SERVICE STATUS REPORT":
			returnSql = getMeterReadingReport();
			break;
		case "TNERC-BANKING-REPORT09-2022":
			returnSql = TnercBankingReport092022();
			break;
		case "SOLAR FEEDER EDC WISE REPORT":
			returnSql = SolarFeederEdcReport();
			break;
			
		case "METER READING SERVICE DETAIL REPORT":
		    returnSql = getMeterReadingDetailReport();
		    break;
		    
		case "MASTER SOLAR REPORT":
		    returnSql = getMasterSolarReport();
		    break;
		    
		case "MASTER WIND REPORT":
		    returnSql = getMasterWindReport();
		    break;
		  
		case "GENERATION REPORT":
			 returnSql = getGenerationstmtReport();
		    
		default:
			break;
				
		}
		return returnSql;
	}
	
	
	
	
	private static String TnercBankingReport092022() {
		String MorgId="";
		String Month="";
		String Year= "";
		
		
		
       if(ipCriteria.containsKey("ip1")) {
			
			MorgId=ipCriteria.get("ip1");
		}
       if(ipCriteria.containsKey("ip3")) {
			
    	   Month=ipCriteria.get("ip3");
		}
       if(ipCriteria.containsKey("ip2")) {
			
			Year=ipCriteria.get("ip2");
		}
       
       
       
       String Sql="SELECT M_COMPANY_SERVICE_NUM,M_COMPANY_NAME ,TO_CHAR(COMMISSION_DATE,'dd-mm-yyyy'), (CASE FLOW_TYPE_CODE WHEN'IS-CAPTIVE' THEN 'CAPTIVE' ELSE FLOW_TYPE_CODE END ), BANKING_310822_C1, BANKING_310822_C2, BANKING_310822_C3, \n"
       		+ "BANKING_310822_C4, BANKING_310822_C5, BANKING_310822_TOTAL, BANKING_310822_REARRANGED_C1, BANKING_310822_REARRANGED_C2, BANKING_310822_REARRANGED_C3, \n"
       		+ "BANKING_310822_REARRANGED_C4, BANKING_310822_REARRANGED_C5, BANKING_310822_REARRANGED_TOTAL, BANKING_REMARKS\n"
       		+ "FROM OPENACCESS.TNERC_BANKING_CFC_REV_D493_28SEP22 WHERE M_ORG_NAME LIKE '%"+MorgId+"%' AND  READING_MONTH LIKE '%"+Month+"%'  AND READING_YEAR LIKE '%"+Year+"%' AND LENGTH (M_COMPANY_SERVICE_NUM)=12";
       
		return Sql;
	}




	private static String getTantranscoPaymentInforeport() {
		String MorgId="";
		String PaymentYear="";
		String serviceNo="";
		String PaymentStatus="";
		String PaymentMonth="";
		
		if(ipCriteria.containsKey("ip1")) {
			
			MorgId=ipCriteria.get("ip1");
		}
        if(ipCriteria.containsKey("ip2")) {
			
        	PaymentMonth=ipCriteria.get("ip2");
		}
		if(ipCriteria.containsKey("ip5")) {
			
			PaymentYear=ipCriteria.get("ip5");
		}
        if(ipCriteria.containsKey("ip3") && ipCriteria.get("ip3").length() == 11) {
			
			serviceNo=ipCriteria.get("ip3");
		}
        if(ipCriteria.containsKey("ip4")) {
			
			PaymentStatus=ipCriteria.get("ip4");
		}
        String SqlQuery="SELECT M_COMP_SERV_NO,VIRTUALACCOUNTNO,CUSNAME,LINE_YEAR,LINE_MONTH,TOTALINVAMT FROM T_INVOICE_HDR WHERE PAYMENTRECIV LIKE '%"+PaymentStatus+"%' "
        		+ "AND M_COMP_SERV_NO LIKE '%"+serviceNo+"%' AND LINE_YEAR  LIKE '%"+PaymentYear+"%' AND LINE_MONTH  LIKE '%"+PaymentMonth+"%' AND M_ORG_ID LIKE '%"+MorgId+"%' \n"
        		+ "";
        
        
        
		return SqlQuery;
	}




	private static String getTantranscoGeneratorInforeport() {
		String MorgId="";
		String Fueltype="";
		String serviceNo="";
		String flowtype="";
		
		if(ipCriteria.containsKey("ip1")) {
			
			MorgId=ipCriteria.get("ip1");
		}
		if(ipCriteria.containsKey("ip2")) {
			
			Fueltype=ipCriteria.get("ip2");
		}
        if(ipCriteria.containsKey("ip3") && ipCriteria.get("ip3").length() == 11) {
			
			serviceNo=ipCriteria.get("ip3");
		}
        if(ipCriteria.containsKey("ip4")) {
			
			flowtype=ipCriteria.get("ip4");
		}
		
		
	String SqlQuery="SELECT DISTINCT  vcs.M_ORG_NAME,vcs.M_SUBSTATION_NAME,vcs.M_COMPANY_NAME,vcs.M_COMP_SERV_NUMBER,mcs.REG_OFFICE_ADDR,mtr.M_SELLER_COMPANY_ID,\n"
			+ "mcs.CONTACT_PHONE_NO,mcs.CONTACT_EMAIL,mc.GST,mc.PAN,vcs.CAPACITY,vcs.FLOW_TYPE_CODE,vcs.IS_REC ,TO_CHAR (max(mtr.AGMT_DT),'dd-mm-yyyy'), \n"
			+ "round(max(months_between(mtr.TO_DATE , mtr.FROM_DATE)/12)) AS agr FROM V_COMPANY_SERVICE vcs \n"
			+ "LEFT JOIN M_COMPANY_SERVICE mcs on vcs.M_COMP_SERV_NUMBER=mcs.\"number\"\n"
			+ "LEFT JOIN M_TRADE_RELATIONSHIP mtr ON vcs.M_COMPANY_ID = mtr.M_SELLER_COMPANY_Id\n"
			+ "LEFT JOIN M_COMPANY mc ON mc.ID = vcs.M_COMPANY_ID  WHERE vcs.M_ORG_ID like '%"+MorgId+"%' AND vcs.FUEL_TYPE_CODE like '%"+Fueltype+"%' AND vcs.M_COMP_SERV_NUMBER LIKE '%"+serviceNo+"%' AND vcs.FLOW_TYPE_CODE LIKE '%"+flowtype+"%' AND LENGTH(vcs.M_COMP_SERV_NUMBER) = 12 GROUP BY vcs.M_ORG_NAME,\n"
			+ "vcs.M_SUBSTATION_NAME,vcs.M_COMPANY_NAME,vcs.M_COMP_SERV_NUMBER,mcs.REG_OFFICE_ADDR,mtr.M_SELLER_COMPANY_ID,mcs.CONTACT_PHONE_NO,\n"
			+ "mcs.CONTACT_EMAIL,mc.GST,mc.PAN,vcs.CAPACITY,vcs.FLOW_TYPE_CODE,vcs.IS_REC\n"
			+ "";
	
	
		return SqlQuery ;
	}
	private static String getDeleteTransactionReport() {
		String month="";
		String year="";
		String m_org_id="";
		if(ipCriteria.containsKey("ip1")){
			month  = ipCriteria.get("ip1");
		}
		if(ipCriteria.containsKey("ip2")){
		year = ipCriteria.get("ip2");
		}
		if(ipCriteria.containsKey("ip4")){
			m_org_id  = ipCriteria.get("ip4");
		}
		String sqlQuery = "select service_no,month,year,reading,statement,allotment,ledger,Remarks,created_date from delete_txn_log where month LIKE '%"+month+"%' and year LIKE '%"+year+"%' and m_org_id LIKE '%"+m_org_id+"%'";
		
	return sqlQuery;
		
	}
	
	
	
	
	
	private static String getTechnicalInfo() {
		String sqlQuery="select s.m_org_code edc_code, s.m_org_name edc_name,s.m_comp_serv_number service_number, s.m_company_name seller_comp_name, s.m_company_code seller_comp_code, s.is_captive seller_is_captive,  \n"
				+ "s.m_substation_code, s.m_substation_name,  s.m_feeder_name,\n"
				+ "s.fuel_type_code, s.fuel_type_name, s.fuel_group_name, s.is_rec, s.flow_type_code,s.voltage_code, s.voltage_name,  \n"
				+ "s.capacity total_capacity, s.commission_date, p.status plant_status ,\n"
				+ "s.meter_number, s.meter_make_code, mc.value_desc meter_make_name, s.is_abtmeter, s.mf, s.meter_ct1,  s.meter_ct2,  s.meter_ct3, s.meter_pt1, s.meter_pt2, s.meter_pt3\n"
				+ "from v_company_service s left join m_powerplant p on s.id = p.M_SERVICE_ID \n"
				+ "left join v_codes mc on s.meter_make_code=mc.value_code and mc.list_code='METER_MAKE_CODE' where s.is_seller = 'Y'";
		if(ipCriteria != null && !ipCriteria.isEmpty()){
			if(ipCriteria.containsKey("ip1")){				
				sqlQuery +="AND M_ORG_CODE LIKE'%" + ipCriteria.get("ip1") + "%'";
			}
			if(ipCriteria.containsKey("ip2")){
				sqlQuery +="AND M_COMP_SERV_NUMBER LIKE'%" + ipCriteria.get("ip2") + "%'";
			}
			if(ipCriteria.containsKey("ip3")){
				sqlQuery +="AND FLOW_TYPE_CODE LIKE'%" + ipCriteria.get("ip3") + "%'";
			}
		}
		
		sqlQuery +="AND length(M_COMP_SERV_NUMBER) ='12'";
		
		
		return sqlQuery;
		
	}


	
	private static String getWEG10SSlossReport()
	{
		String Month = "";
		String Year  = "";
		String OrgId = "";
		
		
		if(ipCriteria.containsKey("ip1")){
			Month  =	ipCriteria.get("ip1");
					}
		if(ipCriteria.containsKey("ip2")){
			Year  =	ipCriteria.get("ip2");
					}
		if(ipCriteria.containsKey("ip3")){
			OrgId  =	ipCriteria.get("ip3");
					}
		
		
		
		String sqlQuery="select a.M_SUBSTATION_NAME,a.M_ORG_NAME,a.M_SUBSTATION_ID,a.totalservices\n"
				+ ",b.billingss,b.exp_units,b.reading_month,b.reading_year,a.M_ORG_ID from\n"
				+ "(select vcs.M_SUBSTATION_NAME,VCS.M_ORG_NAME,vcs.M_SUBSTATION_ID,vcs.M_ORG_ID\n"
				+ ",count(vcs.M_SUBSTATION_ID) as totalservices\n"
				+ "from v_company_service vcs\n"
				+ "where vcs.TYPE_OF_SS='SECTION 10(1)SS' and \n"
				+ "(VCS.FUEL_TYPE_CODE=DECODE ( vcs.M_SUBSTATION_ID,'1133' , 'SOLAR', '3014','SOLAR','WIND' ) OR \n"
				+ "VCS.FUEL_TYPE_CODE=DECODE ( vcs.M_SUBSTATION_ID,'1133' , 'WIND' ,'3014','WIND') ) AND LENGTH (VCS.M_COMP_SERV_NUMBER)=12\n"
				+ "group by vcs.M_SUBSTATION_NAME,vcs.M_SUBSTATION_ID,VCS.M_ORG_NAME,VCS .M_ORG_ID\n"
				+ ")a,\n"
				+ "(select vcs.M_SUBSTATION_NAME,VCS.M_ORG_NAME,vcs.M_SUBSTATION_ID,(count(vcs.M_SUBSTATION_ID)/5) as billingss,\n"
				+ "sum(rs.exp_units) as exp_units,rh.reading_month,rh.reading_year\n"
				+ "from t_meter_reading_slot rs\n"
				+ "left join t_meter_reading_hdr rh on rh.id = rs.t_meter_reading_hdr_id\n"
				+ "left join v_company_service vcs on vcs.m_company_meter_id = rh.m_company_meter_id\n"
				+ "where  rh.reading_month like '%"+Month+"%' and rh.reading_year like '%"+Year+"%'\n"
				+ "and vcs.TYPE_OF_SS='SECTION 10(1)SS' and \n"
				+ "(VCS.FUEL_TYPE_CODE=DECODE ( vcs.M_SUBSTATION_ID,'1133' , 'SOLAR', '3014','SOLAR','WIND' ) OR \n"
				+ "VCS.FUEL_TYPE_CODE=DECODE ( vcs.M_SUBSTATION_ID,'1133' , 'WIND' ,'3014','WIND') ) AND LENGTH (VCS.M_COMP_SERV_NUMBER)<=12\n"
				+ "group by vcs.M_SUBSTATION_NAME,vcs.M_SUBSTATION_ID,rh.reading_month,rh.reading_year,VCS.M_ORG_NAME\n"
				+ "order by vcs.M_SUBSTATION_NAME\n"
				+ ")b\n"
				+ "where a.M_SUBSTATION_NAME=b.M_SUBSTATION_NAME AND a.M_ORG_ID like '%"+OrgId+"%'";
		
		
				
		
		return sqlQuery;
	}
	
	private static String getUtilityReport() 
	
	{
		
		String Circle="";
		String Month ="";
		String Year ="";
		String TypeofChange = "";
		if(ipCriteria.containsKey("ip1")){
		 Month  =	ipCriteria.get("ip1");
				}
		if(ipCriteria.containsKey("ip2")){
			Year =	ipCriteria.get("ip2");
					}
		if(ipCriteria.containsKey("ip3")){
			 Circle  =	ipCriteria.get("ip3");
					}
		if(ipCriteria.containsKey("ip4")){
			 TypeofChange  =	ipCriteria.get("ip4");
					}
		
		

		String sqlQuery = "select * from V_UTILITYREPORT_new where CIRCLE LIKE '%"+Circle+"%'  and  month LIKE '%"+Month+"%' and year LIKE '%"+Year+"%' and  TYPE_OF_CHANGE LIKE '%"+TypeofChange+"%'";
		
			return sqlQuery;
			
	
	}
	
	private static String getMasterReport()
	{   
		String m_org_id="";
		String fueltypecode ="";
		String Month ="";
		String Year = "";
		if(ipCriteria.containsKey("ip1")){
			m_org_id  = ipCriteria.get("ip1");
		}
		if(ipCriteria.containsKey("ip2")){
			fueltypecode = ipCriteria.get("ip2");
		}
		if(ipCriteria.containsKey("ip3")){
			Month  = ipCriteria.get("ip3");
		}
		if(ipCriteria.containsKey("ip4")){
			Year  = ipCriteria.get("ip4");
		}
		String sqlQuery="select distinct vcs.m_org_id, vcs.m_org_name\n"
				+ ",nvl(thirdparty.count,0 )as THIRD_PARTY_COUNT ,nvl(thirdparty.capacity,0) as THIRD_PARTY_CAPACITY,\n"
				+ "nvl(captive.count,0) as CAPTIVE_COUNT,nvl(captive.capacity,0)as CAPTIVE_CAPACITY\n"
				+ ",nvl(stb.count,0) as stb_count,nvl(stb.capacity,0) as stb_capacity\n"
				+ ",(nvl(thirdparty.count,0) + nvl(captive.count,0) + nvl(stb.count,0)) as Total_count \n"
				+ ",(nvl(thirdparty.capacity,0) + nvl(captive.capacity,0) + nvl(stb.capacity,0)) as Total_capacity \n"
				+ "from v_company_service vcs \n"
				+ "left join \n"
				+ "  (\n"
				+ "  SELECT ser.M_ORG_ID, ser.m_org_name,COUNT( hdr.M_COMPANY_METER_ID) AS COUNT\n"
				+ "  ,SUM(ser.capacity)as capacity FROM T_METER_READING_HDR hdr\n"
				+ "   LEFT JOIN v_company_service ser ON hdr.M_COMPANY_METER_ID=ser.M_COMPANY_METER_ID\n"
				+ "   LEFT JOIN T_GEN_STMT TGS ON ser.M_COMPANY_METER_ID=TGS.M_COMPANY_METER_ID\n"
				+ "   WHERE COMP_SER_TYPE_CODE='03' and length(\"number\")='12' and hdr.READING_MONTH like '%" + Month + "%' AND READING_YEAR like '%" + Year + "%' and ser.fuel_type_code like'%" + fueltypecode + "%'\n"
				+ "   and  tgs.STMT_MONTH like '%" + Month + "%' AND tgs.STMT_YEAR like '%" + Year + "%' and tgs.DISP_FUEL_TYPE_CODE like'%" + fueltypecode + "%'\n"
				+ "   and ser.FLOW_TYPE_CODE in ('THIRD-PARTY')\n"
				+ "   GROUP BY ser.M_ORG_ID,ser.m_org_name,ser.FLOW_TYPE_CODE\n"
				+ "  )THIRDPARTY\n"
				+ "on vcs.m_org_id=thirdparty.m_org_id\n"
				+ "left join \n"
				+ "  (\n"
				+ "   SELECT ser.M_ORG_ID, ser.m_org_name,COUNT( hdr.M_COMPANY_METER_ID) AS COUNT\n"
				+ "  ,SUM(ser.capacity)as capacity  FROM T_METER_READING_HDR hdr\n"
				+ "   LEFT JOIN v_company_service ser ON hdr.M_COMPANY_METER_ID=ser.M_COMPANY_METER_ID\n"
				+ "   LEFT JOIN T_GEN_STMT TGS ON ser.M_COMPANY_METER_ID=TGS.M_COMPANY_METER_ID\n"
				+ "   WHERE COMP_SER_TYPE_CODE='03' and length(\"number\")='12' and hdr.READING_MONTH like'%" + Month + "%' AND READING_YEAR like '%" + Year + "%' and ser.fuel_type_code like'%" + fueltypecode + "%'\n"
				+ "   and  tgs.STMT_MONTH like '%" + Month + "%' AND tgs.STMT_YEAR  like '%" + Year + "%' and tgs.DISP_FUEL_TYPE_CODE like '%" + fueltypecode + "%'\n"
				+ "   and ser.FLOW_TYPE_CODE in ('IS-CAPTIVE')\n"
				+ "   GROUP BY ser.M_ORG_ID,ser.m_org_name,ser.FLOW_TYPE_CODE\n"
				+ "  )CAPTIVE\n"
				+ "on vcs.m_org_id=captive.m_org_id\n"
				+ "left join \n"
				+ "  (\n"
				+ "   SELECT ser.M_ORG_ID, ser.m_org_name,COUNT( hdr.M_COMPANY_METER_ID) AS COUNT\n"
				+ "  ,SUM(ser.capacity)as capacity FROM T_METER_READING_HDR hdr\n"
				+ "   LEFT JOIN v_company_service ser ON hdr.M_COMPANY_METER_ID=ser.M_COMPANY_METER_ID\n"
				+ "   LEFT JOIN T_GEN_STMT TGS ON ser.M_COMPANY_METER_ID=TGS.M_COMPANY_METER_ID\n"
				+ "   WHERE COMP_SER_TYPE_CODE='03' and length(\"number\")='12' and hdr.READING_MONTH like '%" + Month + "%' AND READING_YEAR like '%" + Year + "%' and \n"
				+ "   ser.fuel_type_code  like '%" + fueltypecode + "%'\n"
				+ "   and  tgs.STMT_MONTH like '%" + Month + "%' AND tgs.STMT_YEAR  like '%" + Year + "%' and tgs.DISP_FUEL_TYPE_CODE like '%" + fueltypecode + "%'\n"
				+ "   and ser.FLOW_TYPE_CODE in ('STB')\n"
				+ "   GROUP BY ser.M_ORG_ID,ser.m_org_name,ser.FLOW_TYPE_CODE\n"
				+ "  )STB\n"
				+ "on vcs.m_org_id=stb.m_org_id \n"
				+ "where vcs.fuel_type_code like '%" + fueltypecode + "%' and vcs.m_org_id is not null and vcs.m_org_id like'%" + m_org_id + "%'\n"
				+ "order by vcs.M_ORG_ID\n";
				
		return sqlQuery;
	}
	
	
	
	
	

	private static String getAmrdownlodestatus() {
		String readingType = "";
		if(ipCriteria.containsKey("ip5")){
		 readingType  =	ipCriteria.get("ip5");
				}
			
		String sqlQuery="SELECT ser.M_ORG_ID, ser.m_org_name, ser.M_COMP_SERV_NUMBER,MCM.METER_NUMBER,\n"
				+ "ser.FUEL_TYPE_CODE,HDR.READING_MONTH,hdr.READING_YEAR,TO_CHAR(hdr.sys_dt,'DD-MON-YYYY')sys_dt\n"
				+ ",(case when hdr.mr_source_code='01' then 'AMR' else 'MANUAL' end) as downloadstatus,TO_CHAR(tgs.STMT_GEN_DATE,'DD-MON-YYYY')STMT_DATE\n"
				+ " FROM T_METER_READING_HDR hdr\n"
				+ "LEFT JOIN v_company_service ser ON hdr.M_COMPANY_METER_ID=ser.M_COMPANY_METER_ID\n"
				+ "LEFT JOIN M_COMPANY_METER MCM ON hdr.M_COMPANY_METER_ID=MCM.ID\n"
				+"LEFT JOIN T_GEN_STMT tgs ON hdr.M_GEN_STMT_ID =tgs.ID\n"
				+ "WHERE hdr.READING_MONTH='" + ipCriteria.get("ip3") + "' AND hdr.READING_YEAR ='"+ipCriteria.get("ip2")+"' and ser.fuel_type_code in('"+ipCriteria.get("ip4")+"') AND ser.M_ORG_ID in('"+ipCriteria.get("ip1")+"')  AND hdr.mr_source_code LIKE '%"+readingType+"%'\n"
				+ "ORDER BY hdr.sys_dt ";
		
		return sqlQuery;
	}
	private static String getWegTransactionStatusReport() {
		String sqlQuery ="select distinct ms.m_org_id as SELLER_EDC,gs.DISP_ORG_NAME as SELLER_EDC_NAME,\n" + 
				"gs.DISP_COMPANY_NAME as SELLER_COMPANY_NAME,\n" + 
				"ms.\"number\" as SERVICE_NO,\n" + 
				" CASE \n" + 
				"   WHEN NVL(gs.IS_CAPTIVE,'NULL')='Y' THEN 'CAPTIVE'\n" + 
				"   WHEN NVL(gs.IS_CAPTIVE,'NULL')='N' THEN 'STB'\n" + 
				"   WHEN gs.FLOW_TYPE_CODE='WEG-THIRD-PARTY' THEN 'WEG-THIRD-PARTY'\n" + 
				"   END AS FLOW_TYPE ,\n" + 
				"   CASE \n" + 
				"   WHEN NVL(au.master_confirmed,'NULL') ='Y' THEN 'CONFIRMED' \n" + 
				"   WHEN NVL(au.master_confirmed,'NULL') ='N' THEN 'NOT-CONFIRMED' \n" + 
				"   ELSE 'NOT-CONFIRMED'  \n" + 
				"   END AS MASTER_CONFIRMATION,\n" + 
				"so.month as ALLOTMENT_MONTH,so.year as ALLOTMENT_YEAR,\n" + 
				"mr.STATUS_CODE as mr_status,gs.status_code as GS_STATUS,es.status_code as ES_STATUS\n" + 
				"from m_company_service ms\n" + 
				"left join m_company co on co.id=ms.m_company_id\n" + 
				"left join M_COMPANY_METER meter on ms.id=meter.M_COMPANY_SERVICE_ID\n" + 
				"left join T_METER_READING_HDR mr on meter.id=mr.M_COMPANY_METER_ID \n" + 
				"left join t_gen_stmt gs on gs.M_COMPANY_SERVICE_ID=ms.id\n" + 
				"left join t_energy_sale es on es.SELLER_COMP_SERV_ID=ms.id and es.T_GEN_STMT_ID = gs.id\n" + 
				"left join f_energy_sale_order so on so.SELLER_COMP_SERV_ID=ms.id and so.t_energy_sale_id=es.id\n" + 
				"left join auth_user au on au.company_service_id=ms.id\n" + 
				"where ms.COMP_SER_TYPE_CODE='03' and es.status_code is not null" ;
		
		if(ipCriteria != null && !ipCriteria.isEmpty()){
			if(ipCriteria.containsKey("ip1")){				sqlQuery +=" and so.month ='" + ipCriteria.get("ip1") + "'";
			}
			if(ipCriteria.containsKey("ip2")){
				sqlQuery +=" and so.year ='" + ipCriteria.get("ip2") + "'";
			}
			if(ipCriteria.containsKey("ip3")){
				sqlQuery +=" and ms.m_org_id ='" + ipCriteria.get("ip3") + "'";
			}
			if(ipCriteria.containsKey("ip4")){
				sqlQuery +=" and au.master_confirmed ='" + ipCriteria.get("ip4") + "'";
			}
			if(ipCriteria.containsKey("ip5")){
				sqlQuery +=" and  ms.\"number\"  ='" + ipCriteria.get("ip5") + "'";
			}
		
			sqlQuery += "order by  upper(ms.\"number\") " ;
		    
			
		    
	  }
		return sqlQuery;
	}


	private static String getTotalTransactionDetails() {
		String sqlQuery = "select OAA_APPROVAL_NUMBER,applied_dt, comp.NAME company_name,serv.\"number\",nvl(pp.NAME,comp.NAME) gen_name, "
						+"oa.PEAK_UNITS, oa.OFF_PEAK_UNITS, '' appln_process_fee, '' receipt_no, '' receipt_date, OAA_APPROVAL_NUMBER, approved_dt, "
						+"oa.AGMT_PERIOD_CODE STATUS_CODE from t_oaa oa left join M_COMPANY_SERVICE serv on oa.BUYER_COMP_SERV_ID = serv.id "
						+"left join m_company comp on serv.M_COMPANY_ID = comp.id "
						+"left join M_POWERPLANT pp on pp.M_SERVICE_ID = serv.id where 1=1 ";
		
	if(ipCriteria != null && !ipCriteria.isEmpty()){
			if(ipCriteria.containsKey("ip1") && ipCriteria.containsKey("ip2")){
				sqlQuery += "and applied_dt between to_date('"+ ipCriteria.get("ip1") + "', 'yyyy-mm-dd') and to_date('"+ ipCriteria.get("ip2") +"', 'yyyy-mm-dd') ";
			}
			if(ipCriteria.containsKey("ip3")){
				sqlQuery += "AND AGMT_PERIOD_CODE = '" + ipCriteria.get("ip3") +"' ";
			}
		}
		return sqlQuery;
	}

	private static String getPeriodBasedAmendmentReport() {
		String sqlQuery = "select agmt_period_code, amendment_type_CODE ,count(id) total_applications,count(decode(IS_CAPTIVE,'Y','Y',NULL)) no_of_cpp, "
				+"count(decode(IS_CAPTIVE,'N','N',NULL))  no_of_non_cpp  ,TO_CHAR(CREATED_DATE,'MONTH') MONTH,TO_CHAR(CREATED_DATE,'YYYY') YEAR, "
				+"count(decode(STATUS_CODE,'PROCESS','PROCESS',NULL)) IN_PROCESS,count(decode(STATUS_CODE,'COMPLETED','COMPLETED',NULL)) CLEARED, "
				+"count(decode(STATUS_CODE,'REJECTED','REJECTED',NULL)) RETURNED FROM T_AMENDMENT WHERE 1=1 ";
		
		if(ipCriteria != null && !ipCriteria.isEmpty()){
			if(ipCriteria.containsKey("ip1")){
				sqlQuery += "AND upper(AGMT_PERIOD_CODE) = upper('" + ipCriteria.get("ip1") + "') ";
			}
			if(ipCriteria.containsKey("ip2")){
				sqlQuery += "AND upper(amendment_type_CODE) = upper('" + ipCriteria.get("ip2") + "') ";
			}
			if(ipCriteria.containsKey("ip3") && ipCriteria.containsKey("ip4")){
				sqlQuery += "AND TO_CHAR(CREATED_DATE,'MM') = '" + ipCriteria.get("ip3") + "' and TO_CHAR(CREATED_DATE,'YYYY') = '" 
							+ ipCriteria.get("ip4") + "' ";
			}
		}
		
		sqlQuery += "GROUP BY agmt_period_code, amendment_type_CODE, decode(IS_CAPTIVE,'Y','Y',NULL),decode(IS_CAPTIVE,'N','N',NULL), "
					+"TO_CHAR(CREATED_DATE,'MONTH') ,TO_CHAR(CREATED_DATE,'YYYY'), decode(STATUS_CODE,'PROCESS','PROCESS',NULL), "
					+"decode(STATUS_CODE,'COMPLETED','COMPLETED',NULL),decode(STATUS_CODE,'REJECTED','REJECTED',NULL)";
		return sqlQuery;
	}
	
	private static String getTnercEnergySummaryReport(){
		 
		 String sqlQuery =" select STMT_MONTH,STMT_YEAR,  TOTAL_UNITS_GENERATED, TOTAL_UNITS_SOLD,TOTAL_GEN_UNITS_SOLD,TOTAL_UNITS_ELIGIBLE_FOR_BANKING,TOTAL_UNITS_BANKED,\r\n" + 
		 		" TOTAL_RETURNED_FROM_HT_BKG,\r\n" + 
		 		" FINAL_UNITS_BANKED,SUM (FINAL_UNITS_BANKED) OVER (ORDER BY  DISP_FUEL_TYPE_CODE, flow_type_code, STMT_YEAR desc , STMT_MONTH asc ) AS cumulative_banking,\r\n" + 
		 		" TOTAL_UNITS_CONSUMED_FROM_BKG, SUM (TOTAL_UNITS_CONSUMED_FROM_BKG) OVER (ORDER BY  DISP_FUEL_TYPE_CODE, flow_type_code, STMT_YEAR desc , STMT_MONTH asc ) AS cumulative_banking_withdrawal,\r\n" + 
		 		" FINAL_BANKING_BALANCE\r\n" + 
		 		" from v_rp_summary\r\n" + 
		 		" where DISP_FUEL_TYPE_CODE='WIND' and FLOW_TYPE_CODE = 'IS-CAPTIVE'";
		 if(ipCriteria != null && !ipCriteria.isEmpty()){
				if(ipCriteria.containsKey("ip1")){
//					sqlQuery += "AND upper(AGMT_PERIOD_CODE) = upper('" + ipCriteria.get("ip1") + "') ";
					sqlQuery +=" and  CASE  when (to_number(STMT_MONTH) > 3) THEN to_number(STMT_YEAR) ELSE to_number(STMT_YEAR)-1 END = '" + ipCriteria.get("ip1") + "'";
				}
				
			}
		    sqlQuery +="ORDER by  STMT_YEAR asc , STMT_MONTH asc";

		    return sqlQuery;


	 }
	
	private static String getConsolidateEnergyAdjustedChargesReport(){
		String sqlQuery ="select\r\n" + 
				"rownum, tgs.STMT_MONTH month, tgs.STMT_year year, tgs.DISP_ORG_NAME edc_name, tgs.DISP_SERVICE_NUMBER service_no, tgs.DISP_COMPANY_NAME generator_name, tgs.DISP_FUEL_TYPE_NAME fuel_type , tgs.FLOW_TYPE_CODE flow_type, tgs.IS_REC rec, tgs.MF , tgs.TARIFF_RATE tariff,\r\n" + 
				"lines.D_BUYER_COMP_SERV_NAME buyer_sc_no, lines.D_BUYER_COMP_NAME buyer_name,\r\n" + 
				"nvl(C002.total_charges,0) O_M_Charges,\r\n" + 
				"nvl(C003.total_charges,0) Transmission_Charges,\r\n" + 
				"nvl(C004.total_charges,0) System_Operation_Charges,\r\n" + 
				"nvl(C005.total_charges,0) RKvah_Penalty,\r\n" + 
				"nvl(C006.total_charges,0) Negative_Energy_Charges,\r\n" + 
				"nvl(C007.total_charges,0) Scheduling_Charges,\r\n" + 
				"nvl(C001.total_charges,0) Meter_reading_Charges,\r\n" + 
				"nvl(C008.total_charges,0) other_charges,\r\n" + 
				"nvl(C002.total_charges,0) + nvl(C003.total_charges,0) + nvl(C004.total_charges,0) + nvl(C005.total_charges,0) + nvl(C006.total_charges,0) + nvl(C007.total_charges,0) + nvl(C001.total_charges,0) + nvl(C008.total_charges,0)  total_charges\r\n" + 
				"from T_GEN_STMT tgs\r\n" + 
				"LEFT JOIN f_energy_sale_order orders ON ORDERS.\"YEAR\" = tgs.STMT_YEAR  AND ORDERS.\"MONTH\" = tgs.STMT_MONTH AND orders.D_SELL_COMP_SERV_NUMBER =  tgs.DISP_SERVICE_NUMBER\r\n" + 
				"left join f_energy_sale_order_lines lines on lines.f_energy_sale_order_id=orders.id\r\n" + 
				"left join f_energy_charges C001 on C001.f_energy_sale_order_id=orders.id and lines.buyer_comp_serv_id=c001.m_company_service_id and C001.charge_code='C001'\r\n" + 
				"left join f_energy_charges C002 on C002.f_energy_sale_order_id=orders.id and lines.buyer_comp_serv_id=c002.m_company_service_id and C002.charge_code='C002'\r\n" + 
				"left join f_energy_charges C003 on orders.id=C003.f_energy_sale_order_id and lines.buyer_comp_serv_id=c003.m_company_service_id and C003.charge_code='C003'\r\n" + 
				"left join f_energy_charges C004 on orders.id=C004.f_energy_sale_order_id and lines.buyer_comp_serv_id=c004.m_company_service_id and C004.charge_code='C004'\r\n" + 
				"left join f_energy_charges C005 on orders.id=C005.f_energy_sale_order_id and lines.buyer_comp_serv_id=c005.m_company_service_id and C005.charge_code='C005'\r\n" + 
				"left join f_energy_charges C006 on orders.id=C006.f_energy_sale_order_id and lines.buyer_comp_serv_id=c006.m_company_service_id and C006.charge_code='C006'\r\n" + 
				"left join f_energy_charges C007 on orders.id=C007.f_energy_sale_order_id and lines.buyer_comp_serv_id=c007.m_company_service_id and C007.charge_code='C007'\r\n" + 
				"left join f_energy_charges C008 on orders.id=C007.f_energy_sale_order_id and lines.buyer_comp_serv_id=c007.m_company_service_id and C007.charge_code='C008'\r\n" + 
				"WHERE  1=1 ";
		if(ipCriteria != null && !ipCriteria.isEmpty()){
			 if(ipCriteria.containsKey("ip1")){
					sqlQuery +=" and tgs.DISP_ORG_CODE = '" + ipCriteria.get("ip1") + "'";
				}
			 if(ipCriteria.containsKey("ip2")){
					sqlQuery +=" and tgs.STMT_MONTH = '" + ipCriteria.get("ip2") + "'";
				}
			 if(ipCriteria.containsKey("ip3")){
					sqlQuery +=" and tgs.STMT_YEAR = '" + ipCriteria.get("ip3") + "'";
				}
			 if(ipCriteria.containsKey("ip4")){
					sqlQuery +=" and tgs.DISP_SERVICE_NUMBER = '" + ipCriteria.get("ip4") + "'";
				}
			 sqlQuery +="ORDER BY tgs.DISP_ORG_NAME ASC, tgs.DISP_SERVICE_NUMBER asc, tgs.STMT_YEAR ASC, tgs.STMT_MONTH ASC";
		 }
		return sqlQuery;
		
	}
	private static String getConsEnergyAdjustedOrderReport() {
		 String sqlQuery ="SELECT RD_MONTH, RD_YEAR, BUYER_SERVICE_NUMBER CONSUMER_NO, nvl(LOSS_PERCENT,'-') LOSS_PERCENT,\n"
		 		+ "round(nvl(ALLOTED_C1,0),0) ALLOT_GROSS_C1, round(nvl(ALLOTED_C2,0),0) ALLOT_GROSS_C2,round(nvl(ALLOTED_C3,0),0) ALLOT_GROSS_C3, round(nvl(ALLOTED_C4,0),0) ALLOT_GROSS_C4, round(nvl(ALLOTED_C5,0),0) ALLOT_GROSS_C5,\n"
		 		+ "round(nvl(ALLOTED_C1,0),0) +round(nvl(ALLOTED_C2,0),0)+round(nvl(ALLOTED_C3,0),0)+round(nvl(ALLOTED_C4,0),0)+round(nvl(ALLOTED_C5,0),0) AS totalallotedgross ,\n"
		 		+ "round(nvl(LD_C1,0),0)  ALLOT_NET_C1, round(nvl(LD_C2,0),0)   ALLOT_NET_C2, round(nvl(LD_C3,0),0)   ALLOT_NET_C3, round(nvl(LD_C4,0),0)   ALLOT_NET_C4, round(nvl(LD_C5,0),0)   ALLOT_NET_C5,\n"
		 		+ "round(nvl(LD_C1,0),0)+round(nvl(LD_C2,0),0)+round(nvl(LD_C3,0),0)+round(nvl(LD_C4,0),0)+round(nvl(LD_C5,0),0) AS totalallotednet,\n"
		 		+ "round(nvl(ADJC1,0),0) ADJ_NET_C1, round(nvl(ADJC2,0),0) ADJ_NET_C2, round(nvl(ADJC3,0),0) ADJ_NET_C3, round(nvl(ADJC4,0),0) ADJ_NET_C4, round(nvl(ADJC5,0),0) ADJ_NET_C5,\n"
		 		+ "round(nvl(ADJC1,0),0)+round(nvl(ADJC2,0),0)+round(nvl(ADJC3,0),0)+round(nvl(ADJC4,0),0)+round(nvl(ADJC5,0),0) AS totalAdjustednet,\n"
		 		+ "round(nvl(HT_BB_C1,0),0) SURP_BB_GROSS_C1, round(nvl(HT_BB_C2,0),0) SURP_BB_GROSS_C2, round(nvl(HT_BB_C3,0),0) SURP_BB_GROSS_C3, round(nvl(HT_BB_C4,0),0) SURP_BB_GROSS_C4, round(nvl(HT_BB_C5,0),0) SURP_BB_GROSS_C5,\n"
		 		+ "round(nvl(HT_BB_C1,0),0)+round(nvl(HT_BB_C2,0),0)+round(nvl(HT_BB_C3,0),0)+round(nvl(HT_BB_C4,0),0)+round(nvl(HT_BB_C5,0),0) AS totalsurpbankgross,\n"
		 		+ "round(nvl(C1_WITHLOSS,0),0) SURP_BB_NET_C1, round(nvl(C2_WITHLOSS,0),0) SURP_BB_NET_C2, round(nvl(C3_WITHLOSS,0),0) SURP_BB_NET_C3, round(nvl(C4_WITHLOSS,0),0) SURP_BB_NET_C4, round(nvl(C5_WITHLOSS,0),0) SURP_BB_NET_C5,\n"
		 		+ "round(nvl(C1_WITHLOSS,0),0)+round(nvl(C2_WITHLOSS,0),0)+round(nvl(C3_WITHLOSS,0),0)+round(nvl(C4_WITHLOSS,0),0)+round(nvl(C5_WITHLOSS,0),0) AS totalsurpbanknet \n"
		 		+ "FROM OPENACCESS.ENERGY_ADJUSTED_ORDER\n"
		 		+ "WHERE 1=1 ";
		 
		 if(ipCriteria != null && !ipCriteria.isEmpty()){
			 if(ipCriteria.containsKey("ip1")){
					sqlQuery +=" and SELLER_SERVICE_NUMBER = '" + ipCriteria.get("ip1") + "'";
				}
			 if(ipCriteria.containsKey("ip2")){
					sqlQuery +=" and CASE  when (to_number(RD_MONTH) > 3) THEN to_number(rd_year) ELSE to_number(rd_year)-1 END = '" + ipCriteria.get("ip2") + "'";
				}
		 }
		  sqlQuery +="ORDER by  rd_year asc , RD_MONTH ASC, BUYER_SERVICE_NUMBER ASC";

		    return sqlQuery;
	 }
 
	private static String getPeriodBasedApprovalReport() {
		String sqlQuery = "SELECT agmt_period_code,  count(id) total_applications,count(IS_CAPTIVE) no_of_cpp, count(IS_CAPTIVE) no_of_non_cpp  ,"
				+ "TO_CHAR(CREATED_DATE,'MONTH') MONTH,TO_CHAR(CREATED_DATE,'YYYY') YEAR, count(decode(STATUS_CODE,'PROCESS','PROCESS',NULL)) IN_PROCESS, "
				+ "count(decode(STATUS_CODE,'COMPLETED','COMPLETED',NULL)) CLEARED,count(decode(STATUS_CODE,'REJECTED','REJECTED',NULL)) RETURNED "
				+ "FROM T_ES_INTENT WHERE 1=1 ";
		
		if(ipCriteria != null && !ipCriteria.isEmpty()){
			if(ipCriteria.containsKey("ip1")){
				sqlQuery += "and upper(AGMT_PERIOD_CODE) = upper('" + ipCriteria.get("ip1") + "') ";
			}
			if(ipCriteria.containsKey("ip2") && ipCriteria.containsKey("ip3")){
				sqlQuery += "and upper(TO_CHAR(CREATED_DATE,'MM')) = upper('" +ipCriteria.get("ip2") + "') and TO_CHAR(CREATED_DATE,'YYYY') = '" 
							+ ipCriteria.get("ip3") + "' ";
			}
		}
		
		sqlQuery += "GROUP BY agmt_period_code, IS_CAPTIVE ,TO_CHAR(CREATED_DATE,'MONTH') ,TO_CHAR(CREATED_DATE,'YYYY'), "
					+"decode(STATUS_CODE,'PROCESS','PROCESS',NULL),decode(STATUS_CODE,'COMPLETED','COMPLETED',NULL),decode(STATUS_CODE,'REJECTED','REJECTED',NULL)";
		return sqlQuery;
	}

	private static String getGeneratorWiseConsumerReport() {
		String sqlQuery = "SELECT vt.seller_org_name, vt.M_SELLER_COMPANY_NAME , vt.M_SELLER_COMP_SERVICE_NUMBER gn_serviceno, \r\n" + 
				"vt.SELLER_ORG_ID ,vt.flow_type_code,vt.FUEL_TYPE_CODE,vt.BUYER_ORG_NAME ,vt.BUYER_ORG_ID, vt.BUYER_ORG_ID ,vt.M_BUYER_COMPANY_NAME,\r\n" + 
				"vt.M_BUYER_COMP_SERVICE_NUMBER ht_serviceno, vt.FROM_DATE , vt.TO_DATE,vcs.commission_date,tr.agmt_dt \r\n" + 
				"FROM V_TRADERELATIONSHIP vt\r\n" + 
				"left join v_company_service vcs on vcs.\"number\"=vt.M_SELLER_COMP_SERVICE_NUMBER\r\n" + 
				"left join m_trade_relationship tr on tr.id=vt.id where 1=1 ";
				
		if(ipCriteria != null && !ipCriteria.isEmpty()){
			if(ipCriteria.containsKey("ip1")){
				sqlQuery += "and vt.seller_org_id LIKE '%" + ipCriteria.get("ip1") + "%' ";
			}
			if(ipCriteria.containsKey("ip2")){
				sqlQuery += "and vt.M_SELLER_COMP_SERVICE_NUMBER LIKE '%" + ipCriteria.get("ip2") + "%' ";
			}
			if(ipCriteria.containsKey("ip3")){
				sqlQuery += "and vt.from_month LIKE '%" + ipCriteria.get("ip3") + "%' ";
			}
			if(ipCriteria.containsKey("ip4")){
				sqlQuery += "and vt.from_year LIKE '%" + ipCriteria.get("ip4") + "%' ";
			}
			if(ipCriteria.containsKey("ip5")){
				sqlQuery += "and vt.flow_type_code LIKE '%" + ipCriteria.get("ip5") + "%' ";
			}
			if(ipCriteria.containsKey("ip6")){
				sqlQuery += "and vt.FUEL_TYPE_CODE LIKE '%" + ipCriteria.get("ip6") + "%' ";
			}
		}
		
		sqlQuery +="ORDER BY vt.seller_org_name, vt.M_SELLER_COMPANY_NAME , vt.M_SELLER_COMP_SERVICE_NUMBER, vt.BUYER_ORG_NAME ,\r\n" + 
				"vt.M_BUYER_COMPANY_NAME ,vt.FROM_DATE , vt.TO_DATE";
		
	return sqlQuery;
	}
	
//	private static String getGeneratorWiseConsumerReport(){
//		
//		String sqlQuery = "SELECT EDC_NAME, COMPANY_NAME, LOCATION, "
//				+ "INJECTING_VOLTAGE_NAME, FUEL_NAME, INSTALLED_CAPACITY , THIRD_PARTY, CAPTIVE, STOA, MTOA, LTOA FROM ( "
//				+ "SELECT service.\"number\" SERVICE_NUMBER, org.NAME EDC_NAME ,"
//				+ "comp.NAME COMPANY_NAME,pp.PL_VILLAGE||', '||pp.PL_TOWN||', '|| taluk_codes.VALUE_DESC||', '|| district_codes.VALUE_DESC LOCATION, voltage_codes.VALUE_DESC INJECTING_VOLTAGE_NAME, fuel_codes.VALUE_DESC FUEL_NAME, pp.TOTAL_CAPACITY INSTALLED_CAPACITY , 0 THIRD_PARTY, 0 CAPTIVE,0 STOA, 0 MTOA,0 LTOA, '1' MONTH, '1' YEAR, ORG.ID ORG_ID, voltage_codes.VALUE_CODE VOLTAGE_CODE, fuel_codes.VALUE_CODE FUEL_CODE FROM M_POWERPLANT pp "
//				+ "LEFT JOIN M_COMPANY_SERVICE service ON pp.M_SERVICE_ID = service.id "
//				+ "LEFT JOIN M_COMPANY comp ON service.M_COMPANY_ID = comp.ID "
//				+ "LEFT JOIN M_ORG org ON pp.M_ORG_ID = org.id "
//				+ "LEFT JOIN V_CODES fuel_codes ON fuel_codes.LIST_CODE='FUEL_TYPE_CODE' AND pp.FUEL_TYPE_CODE = fuel_codes.VALUE_CODE "
//				+ "LEFT JOIN V_CODES taluk_codes ON taluk_codes.LIST_CODE='TALUK_CODE' AND pp.PL_TALUK_CODE = taluk_codes.VALUE_CODE "
//				+ "LEFT JOIN V_CODES district_codes ON district_codes.LIST_CODE='DISTRICT_CODE' AND pp.PL_DISTRICT_CODE = district_codes.VALUE_CODE "
//				+ "LEFT JOIN V_CODES voltage_codes ON voltage_codes.LIST_CODE='VOLTAGE_CODE' AND service.VOLTAGE_CODE = voltage_codes.VALUE_CODE) "
//				+ "WHERE 1 = 1 ";
//		
//		if(ipCriteria != null && !ipCriteria.isEmpty()){
//			if(ipCriteria.containsKey("ip1")){
//				sqlQuery += "and MONTH LIKE '%" + ipCriteria.get("ip1") + "%' ";
//			}
//			if(ipCriteria.containsKey("ip2")){
//				sqlQuery += "and YEAR LIKE '%" + ipCriteria.get("ip2") + "%' ";
//			}
//			if(ipCriteria.containsKey("ip3")){
//				sqlQuery += "and ORG_ID LIKE '%" + ipCriteria.get("ip3") + "%' ";
//			}
//			if(ipCriteria.containsKey("ip4")){
//				sqlQuery += "and VOLTAGE_CODE LIKE '%" + ipCriteria.get("ip4") + "%' ";
//			}
//			if(ipCriteria.containsKey("ip5")){
//				sqlQuery += "and FUEL_CODE LIKE '%" + ipCriteria.get("ip5") + "%' ";
//			}
//		}
//				
//		
//	return sqlQuery;
//	}
	
	private static String getConsumerReport() {
		String sqlQuery = " SELECT sellercompanyservice.\"number\" AS SELLER_COM_SERV_NUMBER,sellercompanyservice.M_COMPANY_NAME AS SELLER_COMP_NAME,\n" + 
				"									 buyerorg.NAME AS BUYER_END_ORG_NAME, vcompanyservice.\"number\" AS BUYER_COM_SERV_NUMBER,vcompanyservice.M_COMPANY_NAME AS BUYER_COMP_NAME,\n" + 
				"								     to_char(oaa.FROM_DT,'YYYY-MM-DD')FROM_DT,to_char(oaa.TO_DT,'YYYY-MM-DD')TO_DT,oaa.APPROVED_TOTAL_UNITS,oaa.SELLER_IS_CAPTIVE, \n" + 
				"								     oaa.STATUS_CODE ,oaa.FLOW_TYPE_CODE\n" + 
				"								     FROM T_OAA oaa    \n" + 
				"									LEFT JOIN V_COMPANY_SERVICE vcompanyservice ON oaa.BUYER_COMP_SERV_ID = vcompanyservice.ID  \n" + 
				"									LEFT JOIN T_NOC noc ON oaa.T_ES_INTENT_ID = noc.T_ES_INTENT_ID AND oaa.BUYER_COMP_SERV_ID = noc.BUYER_COMP_SERV_ID\n" + 
				"									LEFT JOIN  V_COMPANY_SERVICE sellercompanyservice ON oaa.SELLER_COMP_SERV_ID = sellercompanyservice.ID  \n" + 
				"									LEFT JOIN M_ORG buyerorg ON oaa.BUYER_END_ORG_ID = buyerorg.ID  \n" + 
				"									LEFT JOIN M_ORG sellerorg ON oaa.SELLER_END_ORG_ID = sellerorg.ID where 1=1\n" + 
				"									";
		
		
		if(ipCriteria != null && !ipCriteria.isEmpty()){
			if(ipCriteria.containsKey("ip1")){
				sqlQuery += "and oaa.STATUS_CODE LIKE '%" + ipCriteria.get("ip1") + "%' ";
			}
			if(ipCriteria.containsKey("ip2")){
				sqlQuery += "and oaa.SELLER_IS_CAPTIVE LIKE '%" + ipCriteria.get("ip2") + "%' ";
			}
			if(ipCriteria.containsKey("ip3")){
				sqlQuery += "and oaa.FLOW_TYPE_CODE LIKE '%" + ipCriteria.get("ip3") + "%' ";
			}
//			if(ipCriteria.containsKey("ip4")){
//				sqlQuery += "and VOLTAGE_CODE LIKE '%" + ipCriteria.get("ip4") + "%' ";
//			}
//			if(ipCriteria.containsKey("ip5")){
//				sqlQuery += "and FUEL_CODE LIKE '%" + ipCriteria.get("ip5") + "%' ";
//			}
		} 
		return sqlQuery;
		
	}
	
	private static String getWegGeneratorReport(){
		
		String sqlQuery ="SELECT tr.M_BUYER_COMP_SERVICE_ID,buyercs.M_ORG_ID AS BUYER_ORG,buyercs.M_ORG_NAME AS BUYER_ORG_,buyercompany.NAME AS BUYER_COMPANY_NAME,buyercs.\"number\" AS BUYER_SERVICE_NUMBER,tr.M_SELLER_COMP_SERVICE_ID,sellercs.M_ORG_ID AS SELLER_ORG,sellercs.M_ORG_NAME AS SELLER_ORG_NAME,sellercompany.NAME AS SELLER_COMPANY_NAME,sellercs.\"number\" AS WEG_SERVICE_NO,tr.IS_CAPTIVE,pp.COMMISSION_DATE,\n" + 
				"                 sellercs.CAPACITY AS SELLER_MACHINE_CAPACITY,genmakecodes.VALUE_DESC AS MAKE_DESC,tariff.RATE\n" + 
				"                 FROM M_TRADE_RELATIONSHIP tr  \n" + 
				"                 LEFT JOIN V_COMPANY_SERVICE sellercs ON tr.M_SELLER_COMP_SERVICE_ID = sellercs.ID\n" + 
				"                 LEFT JOIN V_COMPANY_SERVICE buyercs ON tr.M_BUYER_COMP_SERVICE_ID = buyercs.ID\n" + 
				"                 LEFT JOIN M_COMPANY sellercompany ON tr.M_SELLER_COMPANY_ID = sellercompany.ID\n" + 
				"                 LEFT JOIN M_COMPANY buyercompany ON tr.M_BUYER_COMPANY_ID = buyercompany.ID\n" + 
				"                 LEFT JOIN M_POWERPLANT pp ON tr.M_SELLER_COMP_SERVICE_ID =pp.M_SERVICE_ID  \n" + 
				"                 LEFT JOIN M_GENERATOR gen ON pp.ID = gen.M_POWERPLANT_ID\n" + 
				"                 LEFT JOIN V_CODES genmakecodes ON genmakecodes.LIST_CODE='GENERATOR_MAKE_CODE' AND gen.MAKE_CODE=genmakecodes.VALUE_CODE\n" + 
				"                 LEFT JOIN M_TARIFF tariff ON pp.PLANT_CLASS_TYPE_CODE = tariff.WEG_GROUP_CODE WHERE 1=1 ";
		
		if(ipCriteria != null && !ipCriteria.isEmpty()){
			if(ipCriteria.containsKey("ip1")){
				sqlQuery += "and sellercs.M_ORG_ID LIKE '%" + ipCriteria.get("ip1") + "%' ";
			}
			if(ipCriteria.containsKey("ip2")){
				sqlQuery += "and sellercs.CAPACITY LIKE '%" + ipCriteria.get("ip2") + "%' ";
			}
			if(ipCriteria.containsKey("ip3")){
				sqlQuery += "and tr.IS_CAPTIVE LIKE '%" + ipCriteria.get("ip3") + "%' ";
			}
			if(ipCriteria.containsKey("ip4")){
				sqlQuery += "and buyercs.M_ORG_ID LIKE '%" + ipCriteria.get("ip4") + "%' ";
			}
			if(ipCriteria.containsKey("ip5")){
				sqlQuery += "and gen.MAKE_CODE LIKE '%" + ipCriteria.get("ip5") + "%' ";
			}
		}
				
		System.out.println(ipCriteria);
		System.out.println(sqlQuery);

	return sqlQuery;
	}

	
	private static String getGenerationReport(){
		
		String sqlQuery =" select  stmt.DISP_ORG_NAME as EDC_NAME,stmt.DISP_SERVICE_NUMBER as SERVICE_NUMBER,stmt.DISP_COMPANY_NAME AS COMPANY_NAME,stmt.STMT_MONTH AS MONTH ,stmt.STMT_YEAR AS YEAR,\n" + 
				"	 stmt.c1,stmt.c2,stmt.c3,stmt.c4,stmt.c5,stmt.NET_GENERATION from  T_GEN_STMT stmt  where 1=1 ";
		
		if(ipCriteria != null && !ipCriteria.isEmpty()){
			if(ipCriteria.containsKey("ip1")){
				sqlQuery += "and stmt.STMT_MONTH LIKE '%" + ipCriteria.get("ip1") + "%' ";
			}
			if(ipCriteria.containsKey("ip2")){
				sqlQuery += "and stmt.STMT_YEAR LIKE '%" + ipCriteria.get("ip2") + "%' ";
			}
			if(ipCriteria.containsKey("ip3")){
				sqlQuery += "and stmt.DISP_ORG_NAME LIKE '%" + ipCriteria.get("ip3") + "%' ";
			}
			 
		}
				
	return sqlQuery;
	}
	
	private static String getUnimportedWegServicesReport()
	{
		
		
//			sqlQuery="select  ROW_NUMBER() OVER(ORDER BY  \"number\" ASC) AS RowNo,M_COMPANY_NAME,\"number\",M_ORG_NAME,M_SECTION_ID,M_SECTION_NAME,M_SECTION_CODE,METER_NUMBER from V_COMPANY_SERVICE where COMP_SER_TYPE_CODE='03' and M_ORG_ID = '" + ipCriteria.get("ip3")+"'and M_SECTION_ID= '"+ipCriteria.get("ip4")+"'and M_SECTION_NAME= '"+ipCriteria.get("ip5")+"'and M_SECTION_CODE= '"+ipCriteria.get("ip6") + "' AND NOT (m_company_code = 'IEX' OR m_company_code = 'TNEB') AND not (m_company_meter_id  is null) and m_company_meter_id not in (select M_COMPANY_METER_ID from T_METER_READING_HDR where READING_MONTH ='" + ipCriteria.get("ip1") + "' and READING_YEAR = '" + ipCriteria.get("ip2") + "') ";
			String sqlQuery="select  ROW_NUMBER() OVER(ORDER BY  \"number\" ASC) AS RowNo,M_COMPANY_NAME,\"number\",M_ORG_NAME,M_SECTION_NAME,METER_NUMBER,FUEL_TYPE_CODE,FUEL_TYPE_NAME,M_SUBSTATION_NAME,type_of_ss,M_FEEDER_NAME from V_COMPANY_SERVICE where COMP_SER_TYPE_CODE='03'" ;
					if(ipCriteria != null && !ipCriteria.isEmpty()){
						if(ipCriteria.containsKey("ip3")){ 
						sqlQuery += "and M_ORG_ID LIKE '%" + ipCriteria.get("ip3") + "%' ";
						
						}
						if(ipCriteria.containsKey("ip4")){
							sqlQuery += "and M_SECTION_ID LIKE '%" + ipCriteria.get("ip4") + "%' ";	
						}
						
						if(ipCriteria.containsKey("ip5")){
							sqlQuery += "and FUEL_TYPE_CODE LIKE '%" + ipCriteria.get("ip5") + "%' ";	
						}
						if(ipCriteria.containsKey("ip6")){
							sqlQuery += "and TYPE_OF_SS like '%" + ipCriteria.get("ip6") + "%' ";	
						}
					}
						
					sqlQuery += "AND NOT (m_company_code = 'IEX' OR m_company_code = 'TNEB') AND not (m_company_meter_id  is null) and m_company_meter_id not in (select M_COMPANY_METER_ID from T_METER_READING_HDR where ";
							if(ipCriteria != null && !ipCriteria.isEmpty()){
								if(ipCriteria.containsKey("ip1")){
									sqlQuery += "READING_MONTH LIKE '%" + ipCriteria.get("ip1") + "%' ";
									
								}
								if(ipCriteria.containsKey("ip2")){
									sqlQuery += "and READING_YEAR LIKE '%" + ipCriteria.get("ip2") + "%' ";
								}
								 
								
							}sqlQuery += ")AND LENGTH(\"number\") = 12";
				System.out.println(sqlQuery);			
		return sqlQuery;
		
					
	
	}
	
	
private static String getNilGenerationReport() 

{
		String sqlQuery ="select ROW_NUMBER() OVER (ORDER BY gs.DISP_SERVICE_NUMBER) AS ROW_NO,gs.DISP_SERVICE_NUMBER,GS.DISP_COMPANY_NAME,gs.DISP_ORG_NAME,gs.STMT_GEN_DATE,gs.TOTAL_EXPORT_GEN, gs.TOTAL_IMPORT_GEN,gs.NET_GENERATION,\n" + 
				"			 		    C001.total_charges Meter_Reading_Charges,\n" + 
				"					    C002.total_charges O_M_Charges,  \n" + 
				"						C003.total_charges Transmission_Charges,  \n" + 
				"						C004.total_charges System_Operation_Charges, \n" + 
				"						C005.total_charges RKvah_Penalty, \n" + 
				"						C006.total_charges Negative_Energy_Charges, \n" + 
				"						C007.total_charges Scheduling_Charges,GS.M_ORG_ID, \n" + 
				"                        gs.STMT_MONTH, gs.STMT_YEAR,gs.IS_CAPTIVE \n" + 
				"						from t_gen_stmt gs  \n" + 
				"						left join t_gen_stmt_charge C001 on C001.t_gen_stmt_id = gs.id AND C001.CHARGE_CODE = 'C001'\n" + 
				"						left join t_gen_stmt_charge C002 on C002.t_gen_stmt_id = gs.id and C002.CHARGE_CODE = 'C002' \n" + 
				"				     	left join t_gen_stmt_charge C003 on C003.t_gen_stmt_id = gs.id and C003.CHARGE_CODE = 'C003'  \n" + 
				"						left join t_gen_stmt_charge C004 on C004.t_gen_stmt_id = gs.id and C004.CHARGE_CODE = 'C004' \n" + 
				"						left join t_gen_stmt_charge C005 on C005.t_gen_stmt_id = gs.id and C005.CHARGE_CODE = 'C005'  \n" + 
				"				     	left join t_gen_stmt_charge C006 on C006.t_gen_stmt_id = gs.id and C006.CHARGE_CODE = 'C006' \n" + 
				"				    	left join t_gen_stmt_charge C007 on C007.t_gen_stmt_id = gs.id and C007.CHARGE_CODE = 'C007' where gs.NET_GENERATION <= 0 " ;
		
		
		if(ipCriteria != null && !ipCriteria.isEmpty()){
			if(ipCriteria.containsKey("ip1")){ 
				sqlQuery += "and gs.STMT_MONTH LIKE '%" + ipCriteria.get("ip1") + "%' ";
			}
			if(ipCriteria.containsKey("ip2")){
				sqlQuery += "and gs.STMT_YEAR LIKE '%" + ipCriteria.get("ip2") + "%' ";
			}
			if(ipCriteria.containsKey("ip3")){
				sqlQuery += "and gs.DISP_ORG_NAME LIKE '%" + ipCriteria.get("ip3") + "%' ";
			}
			if(ipCriteria.containsKey("ip4")){
				sqlQuery += "and gs.DISP_SERVICE_NUMBER LIKE '%" + ipCriteria.get("ip4") + "%' ";
			}
		
			
		}	
		sqlQuery += " order by ROW_NO asc ";
		
		
		return sqlQuery;
     }


private static String getUnalloctedGenStmtReport() {
	
	
	String Month = "";
	String Year = "";
	String OrgId = "";
	
	if(ipCriteria.containsKey("ip1")){
		 Month  =	ipCriteria.get("ip1");
				}
	if(ipCriteria.containsKey("ip2")){
		 Year  =	ipCriteria.get("ip2");
				}
	if(ipCriteria.containsKey("ip3")){
		 OrgId  =	ipCriteria.get("ip3");
				}
	String sqlQuery ="select ROW_NUMBER() OVER (ORDER BY gs.DISP_SERVICE_NUMBER) AS ROW_NO,gs.DISP_SERVICE_NUMBER,GS.DISP_COMPANY_NAME,gs.DISP_ORG_NAME,\n"
			+ "TO_CHAR(gs.STMT_GEN_DATE, 'DD-MON-YYYY HH:MI:SS AM') STMT_GEN_DATE,gs.TOTAL_IMPORT_GEN, gs.TOTAL_EXPORT_GEN, gs.NET_GENERATION,\n"
			+ "C001.total_charges Meter_Reading_Charges,\n"
			+ "C002.total_charges O_M_Charges,\n"
			+ "C003.total_charges Transmission_Charges, C004.total_charges System_Operation_Charges, C005.total_charges RKvah_Penalty,\n"
			+ "C006.total_charges Negative_Energy_Charges, C007.total_charges Scheduling_Charges, nvl(C001.total_charges,0) + nvl(C002.total_charges,0) + nvl(C003.total_charges,0) + nvl(C004.total_charges,0) + nvl(C005.total_charges,0) + nvl(C006.total_charges,0) + nvl(C007.total_charges,0) tota_charges,\n"
			+ "to_char(to_date(gs.STMT_MONTH,'mm'),'Month') STMT_MONTH, gs.STMT_YEAR,GS.STATUS_CODE,GS.M_ORG_ID,gs.IS_CAPTIVE,gs.IS_STB\n"
			+ "from t_gen_stmt gs\n"
			+ "left join t_gen_stmt_charge C001 on C001.t_gen_stmt_id = gs.id AND C001.CHARGE_CODE = 'C001'\n"
			+ "left join t_gen_stmt_charge C002 on C002.t_gen_stmt_id = gs.id and C002.CHARGE_CODE = 'C002'\n"
			+ "left join t_gen_stmt_charge C003 on C003.t_gen_stmt_id = gs.id and C003.CHARGE_CODE = 'C003'\n"
			+ "left join t_gen_stmt_charge C004 on C004.t_gen_stmt_id = gs.id and C004.CHARGE_CODE = 'C004'\n"
			+ "left join t_gen_stmt_charge C005 on C005.t_gen_stmt_id = gs.id and C005.CHARGE_CODE = 'C005'\n"
			+ "left join t_gen_stmt_charge C006 on C006.t_gen_stmt_id = gs.id and C006.CHARGE_CODE = 'C006'\n"
			+ "left join t_gen_stmt_charge C007 on C007.t_gen_stmt_id = gs.id and C007.CHARGE_CODE = 'C007'\n"
			+ "--and ((gs.id not in(select T_GEN_STMT_ID from t_energy_sale) or (gs.id in(select T_GEN_STMT_ID from t_energy_sale where STATUS_CODE='CREATED'))))\n"
			+ "where DISP_ORG_CODE LIKE '%"+OrgId+"%' AND (GS.STATUS_CODE='CREATED' and gs.IS_STB = 'N' \n"
			+ " and STMT_YEAR LIKE  '%"+Year+"%'   AND STMT_MONTH LIKE '%"+Month+"%' and \n"
			+ "((gs.id not in(select T_GEN_STMT_ID from t_energy_sale) or (gs.id in(select T_GEN_STMT_ID from t_energy_sale where STATUS_CODE='CREATED'))\n"
			+ ")) ) OR\n"
			+ "(DISP_ORG_CODE LIKE '%"+OrgId+"%' AND GS.STATUS_CODE='ALLOCATED' and gs.IS_STB = 'N' \n"
			+ " and STMT_YEAR LIKE  '%"+Year+"%'   AND STMT_MONTH LIKE '%"+Month+"%' and \n"
			+ "  (gs.id  in(select T_GEN_STMT_ID from t_energy_sale where  STATUS_CODE='CREATED')))"; //based on conversation with nallasivan, STB records should not be shown ,recently updated on 31/03/2022
			
	return sqlQuery;
  }

private static String getEnergyAuditReport() {
	String sqlQuery = "select enadj.seller_service_number,enadj.d_sell_comp_name seller_company,enadj.d_sell_org_name seller_edc,enadj.fuel_type_code fuel_type,enadj.rd_month,enadj.rd_year,\n" +
				"gs.c1 gc1,gs.c2 gc2,gs.c3 gc3,gs.c4 gc4,gs.c5 gc5,\n" +
				"bb.open_c1 bc1,bb.open_c2 bc2,bb.open_c3 bc3,bb.open_c4 bc4,bb.open_c5 bc5,\n" +
				"sum(enadj.alloted_c1) alloted_c1,sum(enadj.alloted_c2) alloted_c2,sum(enadj.alloted_c3) alloted_c3,sum(enadj.alloted_c4) alloted_c4,sum(enadj.alloted_c5) alloted_c5,\n" +
				"sum(enadj.adjc1) adjc1,sum(enadj.adjc2) adjc2,sum(enadj.adjc3) adjc3,sum(enadj.adjc4) adjc4,sum(enadj.adjc5) adjc5,\n" +
				"sum(enadj.c1_withloss) c1_withloss,sum(enadj.c2_withloss) c2_withloss,sum(enadj.c3_withloss) c3_withloss,sum(enadj.c4_withloss) c4_withloss,sum(enadj.c5_withloss) c5_withloss,\n" +
				"bb.decr_ea1_c1 unallc1,bb.decr_ea1_c2 unallc2,bb.decr_ea1_c3 unallc3,bb.decr_ea1_c4 unallc4,bb.decr_ea1_c5 unallc5,\n" +
				"bb.curr_c1 closec1,bb.curr_c2 closec2,bb.curr_c3 closec3,bb.curr_c4 closec4,bb.curr_c5 closec5 \n" +
				"from energy_adjusted_order enadj\n" +
				"left join t_gen_stmt gs on gs.disp_service_number=enadj.seller_service_number and gs.stmt_month=enadj.rd_month and gs.stmt_year=enadj.rd_year\n" +
				"left join t_exs_banking_balance bb on bb.m_company_service_num=enadj.seller_service_number and bb.reading_month=enadj.rd_month and bb.reading_year=enadj.rd_year\n" +
				"where 1=1 " ;
				
		if(ipCriteria != null && !ipCriteria.isEmpty()){
			if(ipCriteria.containsKey("ip1")){
				sqlQuery += " and enadj.SELLER_SERVICE_NUMBER ='" + ipCriteria.get("ip1") + "'" ;
			}
			if(ipCriteria.containsKey("ip2") && ipCriteria.containsKey("ip3") && ipCriteria.containsKey("ip4") && ipCriteria.containsKey("ip5")){
				sqlQuery +=" and  enadj.RD_YEAR ||'-'|| enadj.RD_MONTH  between ('"+ ipCriteria.get("ip3") + "') ||'-'|| ('"+ ipCriteria.get("ip2") +"') and ('"+ ipCriteria.get("ip5") +"') ||'-'|| ('"+ ipCriteria.get("ip4")+"')";
		 }
//			if(ipCriteria.containsKey("ip2")){
//				sqlQuery += " and enadj.RD_MONTH ='" + ipCriteria.get("ip2") + "'" ;
//			}
//			if(ipCriteria.containsKey("ip3")){
//				sqlQuery += "and enadj.RD_YEAR ='" + ipCriteria.get("ip3") + "'";
//			}
		}
		
		sqlQuery+= "group by enadj.seller_service_number,enadj.d_sell_comp_name,enadj.d_sell_org_name,enadj.fuel_type_code,enadj.rd_month,enadj.rd_year,\n" +
				"gs.c1,gs.c2,gs.c3,gs.c4,gs.c5,bb.open_c1,bb.open_c2,bb.open_c3,bb.open_c4,bb.open_c5,bb.decr_ea1_c1,bb.decr_ea1_c2,bb.decr_ea1_c3,bb.decr_ea1_c4,bb.decr_ea1_c5,\n" +
				"bb.curr_c1,bb.curr_c2,bb.curr_c3,bb.curr_c4,bb.curr_c5 \n "+
				"order by enadj.RD_YEAR,enadj.RD_MONTH";
		
		return sqlQuery;
				
}

private static String getEnergyLedgerReport() {
	
	String sqlQuery = " select ea.seller_service_number,ea.RD_MONTH month,ea.RD_YEAR year,ea.d_sell_org_name,\n" + 
			"bb.open_total_units as opening_bb,\n" + 
			"gs.NET_GENERATION generated_units,\n"+
			"sum(round(nvl(ea.alloted_c1,0)+nvl(ea.alloted_c2,0)+nvl(ea.alloted_c3,0)+nvl(ea. alloted_c4,0)+nvl(ea.alloted_c5,0))) alloted_gross,\n" + 
			"sum(round(nvl(ea.ld_c1,0)+nvl(ea.ld_c2,0)+nvl(ea.ld_c3,0)+nvl(ea.ld_c4,0)+nvl(ea. ld_c5,0))) alloted_net,\n" + 
			"sum(round(nvl(ea.adjc1,0)+nvl(ea.adjc2,0)+nvl(ea.adjc3,0)+nvl(ea.adjc4,0)+nvl(ea. adjc5,0))) adjusted_net,\n" + 
			"sum(round(nvl(ea.ht_bb_c1,0)+nvl(ea.ht_bb_c2,0)+nvl(ea.ht_bb_c3,0)+nvl(ea.ht_bb_c4 ,0)+nvl(ea.ht_bb_c5,0))) ht_banking,\n" + 
			"bb.decr_ea1_total_units Unalloted_units,bb.curr_total_units closing_balance_units,ea.d_sell_comp_name\n" + 
			"from energy_adjusted_order ea\n" + 
			"left join t_exs_banking_balance bb on bb.m_company_service_num=ea.SELLER_SERVICE_NUMBER and bb.reading_month=ea.RD_MONTH and bb.reading_year=ea.RD_YEAR\n" + 
			"left join t_gen_stmt gs on gs.disp_service_number=ea.SELLER_SERVICE_NUMBER and gs.stmt_month=ea.RD_MONTH and gs.stmt_year=ea.RD_YEAR\n" + 
			"where gs.STMT_MONTH is not null\n" ;
	
	if(ipCriteria != null && !ipCriteria.isEmpty()){
		 if(ipCriteria.containsKey("ip1") && ipCriteria.containsKey("ip2") && ipCriteria.containsKey("ip3") && ipCriteria.containsKey("ip4")){
				sqlQuery +=" and  ea.rd_year ||'-'|| ea.rd_month  between ('"+ ipCriteria.get("ip3") + "') ||'-'|| ('"+ ipCriteria.get("ip1") +"')  and ('"+ ipCriteria.get("ip4") +"') ||'-'|| ('"+ ipCriteria.get("ip2")+"')";
		 }

		if(ipCriteria.containsKey("ip5")){
			sqlQuery += "and ea.SELLER_SERVICE_NUMBER LIKE '%" + ipCriteria.get("ip5") + "%'";
		}
		if(ipCriteria.containsKey("ip6")){
			sqlQuery += "and gs.disp_org_code LIKE '%" + ipCriteria.get("ip6") + "%' ";
		}
	}	
	sqlQuery += "group by ea.d_sell_comp_name,ea.seller_service_number, ea.RD_MONTH, ea.RD_YEAR, ea.d_sell_org_name, bb.open_total_units, \r\n" + 
			"bb.decr_ea1_total_units, bb.curr_total_units, gs.disp_org_code, gs.NET_GENERATION\r\n" + 
			"order by ea.RD_YEAR,ea.RD_MONTH";
	
	return sqlQuery;
	
}

private static String getRemarksBasedBankingReport() {

	String sqlQuery ="select ROW_NUMBER() OVER (ORDER BY vcs.m_comp_serv_number) AS ROW_NO, vcs.m_comp_serv_number Gen_Service_No,tbb.c1 open_c1,tbb.c2 open_c2,tbb.c3 open_c3,tbb.c4 open_c4,tbb.c5 open_c5,\n" + 
			"tbb.curr_c1 close_c1,tbb.curr_c2 close_c2,tbb.curr_c3 close_c3,tbb.curr_c4 close_c4,tbb.curr_c5 close_c5,\n" + 
			"tbb.Surplus_c1,tbb.Surplus_c2,tbb.Surplus_c3,tbb.Surplus_c4,tbb.Surplus_c5,tbb.remarks\n" + 
			"from t_banking_balance tbb left join\n" + 
			"v_company_service vcs on vcs.m_company_id = Tbb.M_Company_Id\n" + 
			"where tbb.remarks IS NOT NULL and tbb.remarks !='DELETE-TXN' " ;
	 
	if(ipCriteria != null && !ipCriteria.isEmpty()){
		if(ipCriteria.containsKey("ip1")){
			sqlQuery += "and tbb.MONTH LIKE '%" + ipCriteria.get("ip1") + "%' ";
		}
		if(ipCriteria.containsKey("ip2")){
			sqlQuery += "and tbb.YEAR LIKE '%" + ipCriteria.get("ip2") + "%' ";
		}
		if(ipCriteria.containsKey("ip3")){
			sqlQuery += "and vcs.m_comp_serv_number LIKE '%" + ipCriteria.get("ip3") + "%' ";
		}
		if(ipCriteria.containsKey("ip4")){
			sqlQuery += "and vcs.M_ORG_NAME LIKE '%" + ipCriteria.get("ip4") + "%' ";
		}
	
    }
	
	 sqlQuery += " order by ROW_NO asc ";
		
		return sqlQuery;
		
  }


 private static String getEnergyGenerationChargesReport() {
	 

		String sqlQuery ="  select ROW_NUMBER() OVER (ORDER BY gs.DISP_ORG_NAME,gs.DISP_SERVICE_NUMBER) AS ROW_NO,gs.DISP_ORG_NAME,gs.DISP_SERVICE_NUMBER,GS.DISP_COMPANY_NAME,gs.STMT_MONTH,gs.STMT_YEAR, \n" + 
				"				gs.MACHINE_CAPACITY,gs.MF,gs.DISP_FUEL_TYPE_CODE,gs.FLOW_TYPE_CODE,gs.TOTAL_EXPORT_GEN,gs.TOTAL_IMPORT_GEN,gs.NET_GENERATION, \n" + 
				"				C001.total_charges Meter_Reading_Charges,  \n" + 
				"				C002.total_charges O_M_Charges,  \n" + 
				"				C003.total_charges Transmission_Charges,  \n" + 
				"				C004.total_charges System_Operation_Charges,  \n" + 
				"				C005.total_charges RKvah_Penalty, \n" + 
				"				C006.total_charges Negative_Energy_Charges,  \n" + 
				"				C007.total_charges Scheduling_Charges, \n" + 
				"				C008.total_charges other_Charges \n" + 
				"				from t_gen_stmt gs \n" + 
				"				left join t_gen_stmt_charge C001 on C001.t_gen_stmt_id = gs.id AND C001.CHARGE_CODE = 'C001' \n" + 
				"				left join t_gen_stmt_charge C002 on C002.t_gen_stmt_id = gs.id and C002.CHARGE_CODE = 'C002' \n" + 
				"				left join t_gen_stmt_charge C003 on C003.t_gen_stmt_id = gs.id and C003.CHARGE_CODE = 'C003' \n" + 
				"				left join t_gen_stmt_charge C004 on C004.t_gen_stmt_id = gs.id and C004.CHARGE_CODE = 'C004' \n" + 
				"				left join t_gen_stmt_charge C005 on C005.t_gen_stmt_id = gs.id and C005.CHARGE_CODE = 'C005' \n" + 
				"				left join t_gen_stmt_charge C006 on C006.t_gen_stmt_id = gs.id and C006.CHARGE_CODE = 'C006' \n" + 
				"				left join t_gen_stmt_charge C007 on C007.t_gen_stmt_id = gs.id and C007.CHARGE_CODE = 'C007' \n" + 
				"				left join t_gen_stmt_charge C008 on C008.t_gen_stmt_id = gs.id and C008.CHARGE_CODE = 'C008' where  gs.status_code IS NOT NULL "   ;
		 
		if(ipCriteria != null && !ipCriteria.isEmpty()){
			if(ipCriteria.containsKey("ip1")){
				sqlQuery += "and gs.STMT_MONTH LIKE '%" + ipCriteria.get("ip1") + "%' ";
			}
			if(ipCriteria.containsKey("ip2")){
				sqlQuery += "and gs.STMT_YEAR LIKE '%" + ipCriteria.get("ip2") + "%' ";
			}
			if(ipCriteria.containsKey("ip3")){
				sqlQuery += "and gs.DISP_SERVICE_NUMBER LIKE '%" + ipCriteria.get("ip3") + "%' ";
			}
			if(ipCriteria.containsKey("ip4")){
				sqlQuery += "and gs.M_ORG_ID LIKE '%" + ipCriteria.get("ip4") + "%' ";
			}
			if(ipCriteria.containsKey("ip5")){
				sqlQuery += "and gs.FLOW_TYPE_CODE LIKE '%" + ipCriteria.get("ip5") + "%' ";
			}
			if(ipCriteria.containsKey("ip6")){
				sqlQuery += "and gs.DISP_FUEL_TYPE_CODE LIKE '%" + ipCriteria.get("ip6") + "%' ";
			}
			if(ipCriteria.containsKey("ip7")){
				sqlQuery += "and gs.IS_REC LIKE '%" + ipCriteria.get("ip7") + "%' ";
			}
		}
		
		sqlQuery += " order by ROW_NO asc ";
		
		return sqlQuery;
	 
      }
 
//  private static String getConsEnergyAdjustedOrderReport() {
//	  
//	  String sqlQuery ="select ROW_NUMBER() OVER (ORDER BY cons.SELLER_SERVICE_NO,cons.SELLER_ORG_ID) AS ROW_NO, \n" + 
//	  		"cons.SELLER_SERVICE_NO,cons.READING_MNTH,cons.READING_YR,cons.SELLER_ORG_ID,cons.SELLER_NAME\n" + 
//	  		"cons.ALLOTED_C1,cons.ALLOTED_C2,cons.ALLOTED_C3,cons.ALLOTED_C4,cons.ALLOTED_C5,\n" + 
//	  		"cons.LD_C1,cons.LD_C2,cons.LD_C3,cons.LD_C4,cons.LD_C5,\n" + 
//	  		"cons.ADJUSTED_C1,cons.ADJUSTED_C2,cons.ADJUSTED_C3,cons.ADJUSTED_C4,cons.ADJUSTED_C5,\n" + 
//	  		"cons.HT_BB_C1,cons.HT_BB_C2,cons.HT_BB_C3,cons.HT_BB_C4,cons.HT_BB_C5,\n" + 
//	  		"cons.C1_WITHLOSS,cons.C2_WITHLOSS,cons.C3_WITHLOSS,cons.C4_WITHLOSS,cons.C5_WITHLOSS,\n" + 
//	  		"from Cons_Energy_Adjusted_Order cons " ;
//			 
//	  if(ipCriteria != null && !ipCriteria.isEmpty()){
//			if(ipCriteria.containsKey("ip1")){
//				sqlQuery += "and cons.READING_MNTH LIKE '%" + ipCriteria.get("ip1") + "%' ";
//			}
//			if(ipCriteria.containsKey("ip2")){
//				sqlQuery += "and cons.READING_YR LIKE '%" + ipCriteria.get("ip2") + "%' ";
//			}
//			if(ipCriteria.containsKey("ip3")){
//				sqlQuery += "and cons.SELLER_SERVICE_NO LIKE '%" + ipCriteria.get("ip3") + "%' ";
//			}
//			if(ipCriteria.containsKey("ip4")){
//				sqlQuery += "and cons.SELLER_ORG_ID LIKE '%" + ipCriteria.get("ip4") + "%' ";
//			}
//			
//		}
//		
//		sqlQuery += " order by ROW_NO asc ";
//		
//		return sqlQuery;
//	 
//    }
  
 private static String getConsEnergyAdjustedChargesReport() {
	  
	  String sqlQuery ="select ROW_NUMBER() OVER (ORDER BY cons.SELLER_SERVICE_NO) AS ROW_NO, \n" + 
	  		"cons.SELLER_SERVICE_NO,cons.READING_MNTH,cons.READING_YR,\n" + 
	  		"cons.METER_READING_CHARGES,cons.O_M_CHARGES,cons.TRANSMISSION_CHARGES,\n" + 
	  		"cons.SYSTEM_OPERATION_CHARGES,cons.RKVAH_PENALTY,cons.NEGATIVE_ENERGY_CHARGES,\n" + 
	  		"cons.SCHEDULING_CHARGES,cons.OTHER_CHARGES from Cons_Energy_Adjusted_Charges cons" ;
			 
	  if(ipCriteria != null && !ipCriteria.isEmpty()){
			if(ipCriteria.containsKey("ip1")){
				sqlQuery += "and cons.READING_MNTH LIKE '%" + ipCriteria.get("ip1") + "%' ";
			}
			if(ipCriteria.containsKey("ip2")){
				sqlQuery += "and cons.READING_YR LIKE '%" + ipCriteria.get("ip2") + "%' ";
			}
			if(ipCriteria.containsKey("ip3")){
				sqlQuery += "and cons.SELLER_SERVICE_NO LIKE '%" + ipCriteria.get("ip3") + "%' ";
			}
			
		}
		
		sqlQuery += " order by ROW_NO asc ";
		
		return sqlQuery;
	 
    }
  
 
  private static String getContactDetailsOfGeneratorReport() {
	  
	  String sqlQuery ="select ROW_NUMBER() OVER (ORDER BY mcs.\"number\",mcs.M_ORG_ID) AS ROW_NO,mcs.M_ORG_ID,mcs.\"number\",Mc.Name,mcs.contact_full_name,\n" + 
	  		"mcs.contact_designation,mcs.contact_email,mcs.contact_phone_no,Mcs.Reg_Office_Addr,Mcs.Plant_Addr,PP.STATUS,mc.GST FROM  M_POWERPLANT PP \n" + 
	  		"LEFT JOIN M_COMPANY_SERVICE  mcs ON  mcs.id = pp.M_SERVICE_ID\n" + 
	  		"LEFT JOIN M_COMPANY mc on mc.id = Mcs.M_Company_Id where Mcs.Comp_Ser_Type_Code='03' " ;
		 
		if(ipCriteria != null && !ipCriteria.isEmpty()){
			if(ipCriteria.containsKey("ip1")){
				sqlQuery += " and mcs.M_ORG_ID LIKE '%" + ipCriteria.get("ip1") + "%' ";
			}
		if(ipCriteria != null && !ipCriteria.isEmpty())
				if(ipCriteria.containsKey("ip2")){
					sqlQuery += " and mcs.FUEL_TYPE_CODE LIKE '%" + ipCriteria.get("ip2") + "%' ";
				}
		   }
				 sqlQuery += " AND LENGTH(mcs.\"number\")=12 order by ROW_NO asc ";
					
					return sqlQuery;
					
		   }
  
 private static String getTnercNetGenerationReport() {
	  
	  String sqlQuery ="SELECT ROW_NO,STMT_MONTH,STMT_YEAR,month_year,stb,captive, third_party, total_gen, round(stb/total_gen*100) stb_percent, round(captive/total_gen*100) captive_percent, round(third_party/total_gen*100) third_party_percent, 100 total_percent FROM\n" + 
	  		"(\n" + 
	  		"SELECT  ROW_NUMBER() OVER (ORDER BY stb.STMT_MONTH) AS ROW_NO,to_char(to_date('01-'||stb.STMT_MONTH||'-'||stb.STMT_YEAR,'dd-MM-YYYY'),'MON-YYYY') month_year,stb.STMT_MONTH,stb.STMT_YEAR, stb.NET_GENERATION stb,captive.NET_GENERATION captive,third_party.NET_GENERATION third_party, (stb.NET_GENERATION +captive.NET_GENERATION +third_party.NET_GENERATION ) total_gen FROM\n" + 
	  		"(SELECT gs.STMT_MONTH,gs.STMT_YEAR,    round(sum(gs.NET_GENERATION )/1000000,2)  NET_GENERATION\n" + 
	  		"FROM t_gen_stmt gs where nvl(gs.flow_type_code,'') = 'STB' GROUP BY gs.STMT_YEAR , gs.STMT_MONTH , flow_type_code ORDER BY gs.STMT_YEAR desc , gs.STMT_MONTH desc, flow_type_code ) stb,\n" + 
	  		"(SELECT gs.STMT_MONTH,gs.STMT_YEAR,        round(sum(gs.NET_GENERATION )/1000000,2)  NET_GENERATION\n" + 
	  		"FROM t_gen_stmt gs where nvl(gs.flow_type_code,'') = 'IS-CAPTIVE' GROUP BY gs.STMT_YEAR , gs.STMT_MONTH , flow_type_code ORDER BY gs.STMT_YEAR desc , gs.STMT_MONTH desc, flow_type_code ) captive,\n" + 
	  		"(SELECT gs.STMT_MONTH,gs.STMT_YEAR,        round(sum(gs.NET_GENERATION )/1000000,2)  NET_GENERATION\n" + 
	  		"FROM t_gen_stmt gs where nvl(gs.flow_type_code,'') = 'THIRD-PARTY' GROUP BY gs.STMT_YEAR , gs.STMT_MONTH , flow_type_code ORDER BY gs.STMT_YEAR desc , gs.STMT_MONTH desc, flow_type_code ) third_party\n" + 
	  		"WHERE\n" + 
	  		"stb.STMT_MONTH = captive.STMT_MONTH  AND stb.STMT_YEAR=captive.STMT_YEAR\n" + 
	  		"AND third_party.STMT_MONTH = captive.STMT_MONTH  AND third_party.STMT_YEAR=captive.STMT_YEAR\n" + 
	  		"AND stb.STMT_MONTH = third_party.STMT_MONTH  AND stb.STMT_YEAR=third_party.STMT_YEAR\n" + 
	  		") gen";
	  sqlQuery += " order by ROW_NO asc ";
		
		return sqlQuery;
  }
 
 private static String getGeneratorWiseGenerationReport() {
	 String sqlQuery ="select A.disp_service_number service_no,C.METER_NUMBER,A.disp_company_name company_name,A.disp_org_code edc,A.stmt_month month,\r\n" + 
	 		"A.stmt_year year,A.machine_capacity capacity,\r\n" + 
	 		"A.MF,A.IS_REC,A.total_export_gen,A.total_import_gen,\r\n" + 
	 		"A.net_generation,A.flow_type_code,A.disp_fuel_type_name fuel_type_name from  t_gen_stmt A\r\n" + 
	 		"LEFT JOIN m_company_service B ON B.\"number\" =A.disp_service_number\r\n" + 
	 		"LEFT JOIN M_COMPANY_METER C ON c.m_company_service_id=b.id\r\n" + 
	 		"where 1=1 ";
	 
	 if(ipCriteria != null && !ipCriteria.isEmpty()){
//		 if(ipCriteria.containsKey("ip1")){
//				sqlQuery += " and A.STMT_MONTH >= '" + ipCriteria.get("ip1") + "'" ;
//			}
//		 if(ipCriteria.containsKey("ip2")){
//				sqlQuery += " and A.STMT_MONTH <= '" + ipCriteria.get("ip2") + "'" ;
//			}
//		 if(ipCriteria.containsKey("ip3")){
//				sqlQuery += " and A.STMT_YEAR >= '" + ipCriteria.get("ip3") + "'" ;
//			}
//		 if(ipCriteria.containsKey("ip4")){
//				sqlQuery += " and A.STMT_YEAR <= '" + ipCriteria.get("ip4") + "'" ;
//			}
		 
		 if(ipCriteria.containsKey("ip1") && ipCriteria.containsKey("ip2")){
				sqlQuery +=" and A.STMT_MONTH between ('"+ ipCriteria.get("ip1") + "') and ('"+ ipCriteria.get("ip2") +"') ";
			}
		 if(ipCriteria.containsKey("ip3") && ipCriteria.containsKey("ip4")){
				sqlQuery +=" and A.STMT_YEAR between ('"+ ipCriteria.get("ip3") + "') and ('"+ ipCriteria.get("ip4") +"') ";
			}
			if(ipCriteria.containsKey("ip5")){
				sqlQuery += "and A.disp_fuel_type_code LIKE '%" + ipCriteria.get("ip5") + "%' ";
			}
			if(ipCriteria.containsKey("ip6")){
				sqlQuery += "and A.FLOW_TYPE_CODE LIKE '%" + ipCriteria.get("ip6") + "%' ";
			}
			if(ipCriteria.containsKey("ip7")){
				sqlQuery += "and A.disp_org_code LIKE '%" + ipCriteria.get("ip7") + "%' ";
			}
		}
	return sqlQuery;
 }
 
 private static String getConsumerWiseEnergyAdjustmentOrderReport() {
	 String sqlQuery ="SELECT eao.RD_MONTH , eao.RD_YEAR , eao.d_sell_org_name SELLER_ORG_NAME, eao.SELLER_SERVICE_NUMBER seller_service_no,eao.D_sell_comp_name SELLER_COMPANY_NAME,round(nvl(eao.total_export_gen,0),0) TotalExportGeneration,round(nvl(eao.total_export_gen,0),0)-round(nvl(eao.TOTAL_IMPORT_GEN,0),0) NET_GENERATION,  \r\n" + 
	 		"eao.D_BUYER_ORG_NAME buyer_org_name,  eao.BUYER_SERVICE_NUMBER BUYER_SERVICE_NO, eao.D_BUYER_COMP_NAME BUYER_COMP_NAME,\r\n" + 
	 		"round(nvl(ADJC1,0),0) + round(nvl(ADJC2,0),0) + round(nvl(ADJC3,0),0) + round(nvl(ADJC4,0),0)+ round(nvl(ADJC5,0),0)  ADJUSTED_UNITS\r\n" + 
	 		"from energy_adjusted_order eao\r\n" + 
	 		"where 1=1 ";
	 
	 if(ipCriteria != null && !ipCriteria.isEmpty()){			
		 if(ipCriteria.containsKey("ip1") && ipCriteria.containsKey("ip2") && ipCriteria.containsKey("ip3") && ipCriteria.containsKey("ip4")){
				sqlQuery +=" and  eao.rd_year ||'-'|| eao.rd_month ||'-'||'02' between ('"+ ipCriteria.get("ip3") + "') ||'-'|| ('"+ ipCriteria.get("ip1") +"') ||'-'|| ('"+ "01" +"') and ('"+ ipCriteria.get("ip4") +"') ||'-'|| ('"+ ipCriteria.get("ip2")+"')||'-'|| ('"+"28"+"')";
		 }
	 }
		 
//	 if(ipCriteria != null && !ipCriteria.isEmpty()){			
//		 if(ipCriteria.containsKey("ip1") && ipCriteria.containsKey("ip2")){
//			 System.out.println(ipCriteria.containsKey("ip1") && ipCriteria.containsKey("ip2"));
//				sqlQuery +=" and eao.rd_month between ('"+ ipCriteria.get("ip1") + "') and ('"+ ipCriteria.get("ip2") +"') ";
//		 }
//	 }
//	 if(ipCriteria != null && !ipCriteria.isEmpty()){
//		 if(ipCriteria.containsKey("ip3") && ipCriteria.containsKey("ip4")){
//				sqlQuery +=" and eao.rd_year between ('"+ ipCriteria.get("ip3") + "') and ('"+ ipCriteria.get("ip4") +"') ";
//			}
//	 }
	 if(ipCriteria != null && !ipCriteria.isEmpty()){
			if(ipCriteria.containsKey("ip5")){
				sqlQuery += "and d_sell_org_name = '" + ipCriteria.get("ip5") + "' ";
			}
	 }
	 if(ipCriteria != null && !ipCriteria.isEmpty()){
			if(ipCriteria.containsKey("ip6")){
				sqlQuery += "and seller_service_number = '" + ipCriteria.get("ip6") + "' ";
			}
		}
	 sqlQuery += "ORDER BY RD_YEAR , RD_MONTH  , d_sell_org_name , eao.SELLER_SERVICE_NUMBER, eao.D_BUYER_ORG_NAME,BUYER_SERVICE_NUMBER";
	 
	return sqlQuery;
 }
 
 
 private static String getUnutilisedBankingReport(){
	 String sqlQuery ="select (select name from m_org r where r.code=substr(veb.M_COMPANY_SERVICE_NUM,6,3)) circle,\r\n" + 
	 		"veb.M_COMPANY_SERVICE_NUM Service,veb.M_COMPANY_NAME,vcs.COMMISSION_DATE,\r\n" + 
	 		" MT.RATE AS rate,( 0.75*rate) as ER,\r\n" + 
	 		"(veb.CURR_C1  + veb.CURR_C2 + veb.CURR_C3 +veb.CURR_C4 + veb.CURR_C5) as UB,\r\n" + 
	 		" ROUND((veb.CURR_C1  + veb.CURR_C2 + veb.CURR_C3 +veb.CURR_C4 + veb.CURR_C5) *(0.75*rate),2) AS AMT_UB\r\n" +	 		
	 		"from v_exs_balance veb\r\n" + 
	 		"left join v_COMPANY_SERVICE  vcs on vcs.m_company_id = veb.M_COMPANY_ID\r\n" + 
	 		"left join M_POWERPLANT MP on MP.M_SERVICE_ID = VCS.ID\r\n" + 
	 		"left join m_tariff MT on MT.WEG_GROUP_CODE= MP.PLANT_CLASS_TYPE_CODE\r\n" + 
	 		"where vcs.FUEL_TYPE_CODE ='WIND' AND \r\n" + 
	 		" vcs.Flow_TYPE_CODE IN ('IS-CAPTIVE') AND\r\n" + 
	 		"(veb.CURR_C1  + veb.CURR_C2 + veb.CURR_C3 + veb.CURR_C4 + veb.CURR_C5) >0 \r\n" + 
	 		"and veb.reading_month='03'";
	 
	 if(ipCriteria != null && !ipCriteria.isEmpty()){	
			if(ipCriteria.containsKey("ip1")){
				sqlQuery += "and veb.reading_year LIKE '%" + ipCriteria.get("ip1") + "%' ";
			}
			if(ipCriteria.containsKey("ip2")){
				sqlQuery += "and vcs.m_org_code LIKE '%" + ipCriteria.get("ip2") + "%' ";
			}
	    }
	 sqlQuery += "order by veb.M_COMPANY_SERVICE_NUM";
	 
	 return sqlQuery;
 }
 
 private static String getSaleToBoardLedgerReport(){
	 String sqlQuery ="select gs.DISP_ORG_NAME,gs.DISP_SERVICE_NUMBER,GS.DISP_COMPANY_NAME,gs.Machine_capacity,gs.MF,\r\n" + 
	 		"gs.DISP_FUEL_TYPE_CODE,gs.M_SUBSTATION_NAME,gs.TYPE_OF_SS,gs.FLOW_TYPE_CODE,gs.TOTAL_EXPORT_GEN,\r\n" + 
	 		"gs.TOTAL_IMPORT_GEN,gs.NET_GENERATION,gs.tariff_rate,gs.TARIFF_NET_AMOUNT,Gs.Net_Payable,\r\n" + 
	 		"gs.STMT_GEN_DATE,gs.STMT_MONTH,gs.STMT_YEAR,gs.init_stmt_dt,gs.final_stmt_dt,\r\n" + 
	 		"C001.total_charges Meter_Reading_Charges,C002.total_charges OM_Charges,\r\n" + 
	 		"C003.total_charges Transmission_Charges,C004.total_charges System_Operation_Charges,\r\n" + 
	 		"C005.total_charges RKvah_Penalty,C006.total_charges \r\n" + 
	 		"IMPORT_Energy_Charges,C007.total_charges Scheduling_Charges\r\n" + 
	 		"from t_gen_stmt gs \r\n" + 
	 		"left join t_gen_stmt_charge C001 on C001.t_gen_stmt_id = gs.id and C001.CHARGE_CODE = 'C001'\r\n" + 
	 		"left join t_gen_stmt_charge C002 on C002.t_gen_stmt_id = gs.id and C002.CHARGE_CODE = 'C002'\r\n" + 
	 		"left join t_gen_stmt_charge C003 on C003.t_gen_stmt_id = gs.id and C003.CHARGE_CODE = 'C003'\r\n" + 
	 		"left join t_gen_stmt_charge C004 on C004.t_gen_stmt_id = gs.id and C004.CHARGE_CODE = 'C004'\r\n" + 
	 		"left join t_gen_stmt_charge C005 on C005.t_gen_stmt_id = gs.id and C005.CHARGE_CODE = 'C005'\r\n" + 
	 		"left join t_gen_stmt_charge C006 on C006.t_gen_stmt_id = gs.id and C006.CHARGE_CODE = 'C006'\r\n" + 
	 		"left join t_gen_stmt_charge C007 on C007.t_gen_stmt_id = gs.id and C007.CHARGE_CODE = 'C007' \r\n" + 
	 		"where gs.flow_type_code='STB'";
	 
	 if(ipCriteria != null && !ipCriteria.isEmpty()){
			if(ipCriteria.containsKey("ip1")){
				sqlQuery += "and gs.STMT_MONTH LIKE '%" + ipCriteria.get("ip1") + "%' ";
			}
			if(ipCriteria.containsKey("ip2")){
				sqlQuery += "and gs.STMT_YEAR LIKE '%" + ipCriteria.get("ip2") + "%' ";
			}
			if(ipCriteria.containsKey("ip3")){
				sqlQuery += "and gs.disp_org_code LIKE '%" + ipCriteria.get("ip3") + "%' ";
			}
			if(ipCriteria.containsKey("ip4")){
				sqlQuery += "and gs.DISP_FUEL_TYPE_CODE LIKE '%" + ipCriteria.get("ip4") + "%' ";
			}
		
	    }
	 return sqlQuery;
 }
 
 private static String getGenChargesAllocToHtReport() {
	 
	 String sqlQuery ="select TGS.disp_company_name GENERATOR_NAME,TGS.DISP_SERVICE_NUMBER\r\n" + 
	 		"GEN_SER_NUM,TGS.MACHINE_CAPACITY,TGS.flow_type_code,TGS.TYPE_OF_SS,TGS.IS_REC,\r\n" + 
	 		"TGS.M_ORG_ID,MCS.\"number\"as HT_SC_NO,MC.NAME\r\n" + 
	 		"HT_SER_NAME,MCD.CHARGE_DESC,TEC.CHARGE_CODE,TEC.TOTAL_CHARGE,\r\n" + 
	 		"TGS.STMT_MONTH,TGS.STMT_YEAR\r\n" + 
	 		"from T_GEN_STMT TGS\r\n" + 
	 		"LEFT JOIN T_ENERGY_SALE TES ON TGS.ID=TES.T_GEN_STMT_ID\r\n" + 
	 		"LEFT JOIN T_ES_CHARGE TEC on tec.T_ENERGY_SALE_ID=TES.ID\r\n" + 
	 		"LEFT JOIN M_COMPANY_SERVICE MCS ON TEC.M_COMP_SERV_ID=MCS.ID\r\n" + 
	 		"LEFT JOIN M_CHARGE_DEFN MCD ON TEC.CHARGE_CODE=MCD.CHARGE_CODE\r\n" + 
	 		"LEFT JOIN M_COMPANY MC ON MCS.M_COMPANY_ID=MC.ID\r\n" + 
	 		"WHERE tec.total_charge>'00'";	 
	 
	 if(ipCriteria != null && !ipCriteria.isEmpty()){
			if(ipCriteria.containsKey("ip1")){
				sqlQuery += "and TGS.STMT_MONTH LIKE '%" + ipCriteria.get("ip1") + "%' ";
			}
			if(ipCriteria.containsKey("ip2")){
				sqlQuery += "and TGS.STMT_YEAR LIKE '%" + ipCriteria.get("ip2") + "%' ";
			}
			if(ipCriteria.containsKey("ip3")){
				sqlQuery += "and TGS.flow_type_code LIKE '%" + ipCriteria.get("ip3") + "%' ";
			}
			if(ipCriteria.containsKey("ip4")){
				sqlQuery += "and TGS.disp_fuel_type_code LIKE '%" + ipCriteria.get("ip4") + "%' ";
			}
			if(ipCriteria.containsKey("ip5")){
				sqlQuery += "and TGS.M_ORG_ID LIKE '%" + ipCriteria.get("ip5") + "%' ";
			}
			if(ipCriteria.containsKey("ip6")){
				sqlQuery += "and TGS.DISP_SERVICE_NUMBER LIKE '%" + ipCriteria.get("ip6") + "%' ";
			}
		
	    }

	 sqlQuery += "order by M_ORG_ID,gen_ser_num";
	return sqlQuery;
	 
 }
 
 private static String getUnutilisedBankingMar2020(){
	 String sqlQuery ="SELECT (select name from m_org r where r.code=substr(vcs.\"number\",6,3)) circle, \r\n"
			 + "vcs.\"number\" Service,vcs.M_COMPANY_NAME,vcs.COMMISSION_DATE,\r\n"
			 + "tbb.CURR_C1,tbb.CURR_C2,tbb.CURR_C3,tbb.CURR_C4,tbb.CURR_C5,\r\n"
			 + "(sum(nvl(tbb.CURR_C1,0)+ nvl(tbb.SURPLUS_C1,0))+sum(nvl(tbb.CURR_C2,0)+ nvl(tbb.SURPLUS_C2,0))+sum(nvl(tbb.CURR_C3,0)+ nvl(tbb.SURPLUS_C3,0))\r\n"
			 + "+sum(nvl(tbb.CURR_C4,0)+ nvl(tbb.SURPLUS_C4,0))+sum(nvl(tbb.CURR_C5,0)+ nvl(tbb.SURPLUS_C5,0)) ) as UB,\r\n"
			 + "MT.RATE AS rate,( 0.75*rate) as ER ,((sum(nvl(tbb.CURR_C1,0)+ nvl(tbb.SURPLUS_C1,0))+ sum(nvl(tbb.CURR_C2,0)+ nvl(tbb.SURPLUS_C2,0))+\r\n"
			 + "sum(nvl(tbb.CURR_C3,0)+ nvl(tbb.SURPLUS_C3,0))+sum(nvl(tbb.CURR_C4,0)+ nvl(tbb.SURPLUS_C4,0))+sum(nvl(tbb.CURR_C5,0)+\r\n"
			 + "nvl(tbb.SURPLUS_C5,0)) )*(0.75*rate)) AS AMT_UB,vcs.tr_flow_type_code\r\n"
			 + "from T_BANKING_BALANCE_mar_20 tbb\r\n"
			 + "left join v_COMPANY_SERVICE vcs on vcs.M_COMPANY_ID =tbb.M_COMPANY_ID\r\n"
			 + "left join M_POWERPLANT MP on MP.M_SERVICE_ID = VCS.ID\r\n"
			 + "left join m_tariff MT on MT.WEG_GROUP_CODE= MP.PLANT_CLASS_TYPE_CODE\r\n"
			 + "where vcs.Flow_TYPE_CODE ='IS-CAPTIVE' AND \r\n" + "MP.FUEL_TYPE_CODE ='WIND' and \r\n"
			 + "((nvl(tbb.CURR_C1,0)+ nvl(tbb.SURPLUS_C1,0)) + (nvl(tbb.CURR_C2,0)+ nvl(tbb.SURPLUS_C2,0))+(nvl(tbb.CURR_C3,0)+ nvl(tbb.SURPLUS_C3,0)) +\r\n"
			 + "(nvl(tbb.CURR_C4,0)+ nvl(tbb.SURPLUS_C4,0))+(nvl(tbb.CURR_C5,0)+ nvl(tbb.SURPLUS_C5,0)) )>0\r\n"
			 + "and tbb.month='03' \r\n" + "and vcs.FUEL_TYPE_CODE ='WIND'";
	 
	 if(ipCriteria != null && !ipCriteria.isEmpty()){
			if(ipCriteria.containsKey("ip1")){
				sqlQuery += "and vcs.m_org_id LIKE '%" + ipCriteria.get("ip1") + "%' ";
			}
			if(ipCriteria.containsKey("ip2")){
				sqlQuery += "and tbb.year LIKE '%" + ipCriteria.get("ip2") + "%' ";
			}
			
		}
	 sqlQuery +="group by vcs.\"number\", vcs.M_COMPANY_NAME, vcs.COMMISSION_DATE,tbb.CURR_C1,tbb.CURR_C2,tbb.CURR_C3,tbb.CURR_C4,tbb.CURR_C5, MT.RATE, ( 0.75*rate), \r\n"
			 + "0.75*rate, rate, vcs.tr_flow_type_code\r\n" + "ORDER BY 1,2";
	 
	return sqlQuery;
	 
 }
 
 private static String getUnutilisedBankingMarNew(){
	 String sqlQuery ="select (select name from m_org r where r.code=substr(veb.M_COMPANY_SERVICE_NUM,6,3)) circle,\r\n"
			 + "veb.M_COMPANY_SERVICE_NUM Service,veb.M_COMPANY_NAME,vcs.COMMISSION_DATE,\r\n"
			 + "veb.CURR_C1,veb.CURR_C2,veb.CURR_C3,veb.CURR_C4,veb.CURR_C5,\r\n"
			 + "(veb.CURR_C1 + veb.CURR_C2 + veb.CURR_C3 +veb.CURR_C4 + veb.CURR_C5) as UB,MT.RATE AS rate,\r\n"
			 + "( 0.75*rate) as ER,(veb.CURR_C1 + veb.CURR_C2 + veb.CURR_C3 +veb.CURR_C4 + veb.CURR_C5) *(0.75*rate) AS AMT_UB,\r\n"
			 + " vcs.TR_FLOW_TYPE_CODE NATUREOFWHEELED\r\n" + "from T_exs_BANKING_BALANCE veb\r\n"
			 + "left join v_COMPANY_SERVICE vcs on vcs.m_company_id = veb.M_COMPANY_ID\r\n"
			 + "left join M_POWERPLANT MP on MP.M_SERVICE_ID = VCS.ID\r\n"
			 + "left join m_tariff MT on MT.WEG_GROUP_CODE= MP.PLANT_CLASS_TYPE_CODE\r\n"
			 + "where vcs.FUEL_TYPE_CODE ='WIND' AND \r\n" + " vcs.Flow_TYPE_CODE IN ('IS-CAPTIVE') AND\r\n"
			 + "(veb.CURR_C1 + veb.CURR_C2 + veb.CURR_C3 + veb.CURR_C4 + veb.CURR_C5) >0 \r\n"
			 + "and veb.reading_month='03' \r\n" + "and veb.reading_year!='2020'";
	 
	 if(ipCriteria != null && !ipCriteria.isEmpty()){
			if(ipCriteria.containsKey("ip1")){
				sqlQuery += "and vcs.m_org_id LIKE '%" + ipCriteria.get("ip1") + "%' ";
			}
			if(ipCriteria.containsKey("ip2")){
				sqlQuery += "and veb.reading_year LIKE '%" + ipCriteria.get("ip2") + "%' ";
			}
			
		}
	 sqlQuery +="order by 1,2";
	return sqlQuery;
 
 }
 
 private static String getProgressReportNew(){
	 String sqlQuery ="select * from progress_report_view where 1=1 ";
	 
	 if(ipCriteria != null && !ipCriteria.isEmpty()){
			if(ipCriteria.containsKey("ip1")){
				sqlQuery += "and reading_month LIKE '%" + ipCriteria.get("ip1") + "%' ";
			}
			if(ipCriteria.containsKey("ip2")){
				sqlQuery += "and reading_year LIKE '%" + ipCriteria.get("ip2") + "%' ";
			}
			if(ipCriteria.containsKey("ip3")){
				sqlQuery += "and fuel_type LIKE '%" + ipCriteria.get("ip3") + "%' ";
			}
		}
	 sqlQuery +="order by org_id";
	return sqlQuery;
	 
 }
 
 private static String getGeneratorSummaryReport() {
	 String sqlQuery ="select s.m_org_name,s.m_org_code,s.fuel_type_code,s.m_comp_serv_number,\r\n" + 
	 		"s.is_captive,\r\n" + 
	 		"(case when s.is_rec='Y' AND s.is_captive='Y' then 'Yes' end) as CAPTIVE_REC,\r\n" + 
	 		"count ((case when s.is_rec='Y' AND s.is_captive='Y' then '1' end)) count_CAPTIVE_REC,\r\n" + 
	 		"(case when s.is_rec='N' AND s.is_captive='N' then 'Yes' end) as CAPTIVE_NON_REC,\r\n" + 
	 		"count ((case when s.is_rec='N' AND s.is_captive='N' then '1' end)) count_CAPTIVE_NON_REC,\r\n" + 
	 		"s.is_third_party,\r\n" + 
	 		"(case when s.is_rec='Y' and s.is_third_party='Y' then 'Yes' end) as THIRD_PARTY_REC,\r\n" + 
	 		"count ((case when s.is_rec='Y' AND s.is_third_party='Y' then '1' end)) count_THIRD_PARTY_REC,\r\n" + 
	 		"(case when s.is_rec='N' and s.is_third_party='N' then 'Yes' end) as THIRD_PARTY_NON_REC,\r\n" + 
	 		"count ((case when s.is_rec='N' and s.is_third_party='N' then '1' end)) count_THIRD_PARTY_NON_REC,\r\n" + 
	 		"s.is_stb,\r\n" + 
	 		"(case when s.is_rec='Y' and s.is_stb='Y' then 'Yes' end) as STB_REC,\r\n" + 
	 		"count ((case when s.is_rec='Y' AND s.is_stb='Y' then '1' end)) count_STB_REC,\r\n" + 
	 		"(case when s.is_rec='N' and s.is_stb='N' then 'Yes' end) as STB_NON_REC,\r\n" + 
	 		"count ((case when s.is_rec='N' and s.is_stb='N' then '1' end)) count_STB_NON_REC,\r\n" + 
	 		"pp.status\r\n" + 
	 		"from v_company_service s\r\n" + 
	 		"left join m_powerplant pp on pp.m_service_id=s.id where 1=1 ";
	 
	 if(ipCriteria != null && !ipCriteria.isEmpty()){
			if(ipCriteria.containsKey("ip1")){
				sqlQuery += "and s.fuel_type_code LIKE '%" + ipCriteria.get("ip1") + "%' ";
			}
			if(ipCriteria.containsKey("ip2")){
				sqlQuery += "and s.m_org_code LIKE '%" + ipCriteria.get("ip2") + "%' ";
			}
		}
	 sqlQuery +="group by s.m_org_name, s.m_org_code, s.fuel_type_code, s.m_comp_serv_number, s.is_captive, \r\n" + 
	 		"(case when s.is_rec='Y' AND s.is_captive='Y' then 'Yes' end), (case when s.is_rec='N' AND s.is_captive='N' then 'Yes' end), s.is_third_party, (case when s.is_rec='Y' and s.is_third_party='Y' then 'Yes' end), (case when s.is_rec='N' and s.is_third_party='N' then 'Yes' end), \r\n" + 
	 		"s.is_stb, (case when s.is_rec='Y' and s.is_stb='Y' then 'Yes' end), (case when s.is_rec='N' and s.is_stb='N' then 'Yes' end), pp.status\r\n" + 
	 		"order by s.m_org_name";
	return sqlQuery;
	 
 }
 
 private static String getWgeCaptiveGroupWiseReport() {
	 String sqlQuery ="SELECT weg_group_name,SUM(weg_count),sum(imp),sum(expo),SUM(NET_GEN),SUM(ADJ),STMT_MONTH,STMT_YEAR,FUEL_TYPE_CODE,FLOW_TYPE_CODE\r\n" + 
	 		"from weg_captive_group_wise where 1 = 1";
	 
	 if(ipCriteria != null && !ipCriteria.isEmpty()){
		 if(ipCriteria.containsKey("ip1") && ipCriteria.containsKey("ip2") && ipCriteria.containsKey("ip3") && ipCriteria.containsKey("ip4")){
				sqlQuery +=" and  STMT_YEAR ||'-'|| STMT_MONTH  between ('"+ ipCriteria.get("ip3") + "') ||'-'|| ('"+ ipCriteria.get("ip1") +"')  and ('"+ ipCriteria.get("ip4") +"') ||'-'|| ('"+ ipCriteria.get("ip2")+"')";
		 }

		if(ipCriteria.containsKey("ip5")){
			sqlQuery += "and FUEL_TYPE_CODE LIKE '%" + ipCriteria.get("ip5") + "%'";
		}
		if(ipCriteria.containsKey("ip6")){
			sqlQuery += "and FLOW_TYPE_CODE LIKE '%" + ipCriteria.get("ip6") + "%' ";
		}
	}
	 sqlQuery +="group by STMT_MONTH, weg_group_name, STMT_YEAR, FUEL_TYPE_CODE, \r\n" + 
	 		"FLOW_TYPE_CODE\r\n" + 
	 		"order by STMT_YEAR,STMT_MONTH";
	return sqlQuery;	
 }
 
 private static String getProgressReportMeterReading() {
	 String sqlQuery ="select * from progress_report_meter_reading where 1=1 ";
	 
	 if(ipCriteria != null && !ipCriteria.isEmpty()){
		if(ipCriteria.containsKey("ip1")){
			sqlQuery += " and fuel_type_code LIKE '%" + ipCriteria.get("ip1") + "%'";
		}
		if(ipCriteria.containsKey("ip2")){
			sqlQuery += " and reading_month LIKE '%" + ipCriteria.get("ip2") + "%' ";
		}
		if(ipCriteria.containsKey("ip3")){
			sqlQuery += " and reading_year LIKE '%" + ipCriteria.get("ip3") + "%' ";
		}
	}
	 sqlQuery +="order by m_org_id";
	 
	return sqlQuery;	
 }

 private static String  getProgressReportCaptiveAllotment() {
	 String sqlQuery ="select * from progress_report_captive_allotment where 1=1 ";
	 if(ipCriteria != null && !ipCriteria.isEmpty()){
		if(ipCriteria.containsKey("ip1")){
			sqlQuery += "and DISPLAY_FUEL_TYPE LIKE '%" + ipCriteria.get("ip1") + "%'";
		}
		if(ipCriteria.containsKey("ip2")){
			sqlQuery += "and month LIKE '%" + ipCriteria.get("ip2") + "%' ";
		}
		if(ipCriteria.containsKey("ip3")){
			sqlQuery += "and year LIKE '%" + ipCriteria.get("ip3") + "%' ";
		}
	}
	 sqlQuery +="order by edc";
	return sqlQuery;	
 }
 
 private static String  getProgressReportThirdPartyAllotment() {
	 String sqlQuery ="select * from progress_report_third_allotment where 1=1 ";
	 if(ipCriteria != null && !ipCriteria.isEmpty()){
		if(ipCriteria.containsKey("ip1")){
			sqlQuery += "and DISPLAY_FUEL_TYPE LIKE '%" + ipCriteria.get("ip1") + "%'";
		}
		if(ipCriteria.containsKey("ip2")){
			sqlQuery += "and month LIKE '%" + ipCriteria.get("ip2") + "%' ";
		}
		if(ipCriteria.containsKey("ip3")){
			sqlQuery += "and year LIKE '%" + ipCriteria.get("ip3") + "%' ";
		}
	}
	 sqlQuery +="order by edc";
	return sqlQuery;	
 }
 
 private static String  getProgressReportTotalAllotment() {
	 String sqlQuery ="select * from progress_report_total_allotment where 1=1 ";
	 if(ipCriteria != null && !ipCriteria.isEmpty()){
		if(ipCriteria.containsKey("ip1")){
			sqlQuery += "and DISPLAY_FUEL_TYPE LIKE '%" + ipCriteria.get("ip1") + "%'";
		}
		if(ipCriteria.containsKey("ip2")){
			sqlQuery += "and month LIKE '%" + ipCriteria.get("ip2") + "%' ";
		}
		if(ipCriteria.containsKey("ip3")){
			sqlQuery += "and year LIKE '%" + ipCriteria.get("ip3") + "%' ";
		}
	}
	 sqlQuery +="order by edc";
	return sqlQuery;	
 }
 
// private static String  getProgressReportManualReading() {
//	 String sqlQuery ="SELECT ROW_NUMBER() OVER (ORDER BY hdr.READING_MONTH) AS ROW_NO, ser.M_ORG_ID AS M_ORG_ID,COUNT( hdr.M_COMPANY_METER_ID) AS COUNT,\r\n" + 
//	 		"hdr.READING_MONTH AS READING_MONTH,hdr.reading_year AS READING_YEAR,ser.FUEL_TYPE_CODE AS FUEL_TYPE_CODE\r\n" + 
//	 		"FROM T_METER_READING_HDR hdr\r\n" + 
//	 		"LEFT JOIN v_company_service ser ON hdr.M_COMPANY_METER_ID=ser.M_COMPANY_METER_ID \r\n" + 
//	 		"where hdr.MR_SOURCE_CODE in ('02')";
//	 if(ipCriteria != null && !ipCriteria.isEmpty()){
//		if(ipCriteria.containsKey("ip1")){
//			sqlQuery += "and fuel_type_code LIKE '%" + ipCriteria.get("ip1") + "%'";
//		}
//		if(ipCriteria.containsKey("ip2")){
//			sqlQuery += "and reading_month LIKE '%" + ipCriteria.get("ip2") + "%' ";
//		}
//		if(ipCriteria.containsKey("ip3")){
//			sqlQuery += "and reading_year LIKE '%" + ipCriteria.get("ip3") + "%' ";
//		}
//	}
//	 sqlQuery +="group by ser.M_ORG_ID, hdr.READING_MONTH, hdr.reading_year, ser.FUEL_TYPE_CODE\r\n" + 
//	 		"order by ser.m_org_id";
//	return sqlQuery;	
// }
 
 private static String  getProgressReportManualReading() {
	 String sqlQuery ="select * from progress_report_manual_reading where 1=1 ";
	 if(ipCriteria != null && !ipCriteria.isEmpty()){
		if(ipCriteria.containsKey("ip1")){
			sqlQuery += "and fuel_type_code LIKE '%" + ipCriteria.get("ip1") + "%'";
		}
		if(ipCriteria.containsKey("ip2")){
			sqlQuery += "and reading_month LIKE '%" + ipCriteria.get("ip2") + "%' ";
		}
		if(ipCriteria.containsKey("ip3")){
			sqlQuery += "and reading_year LIKE '%" + ipCriteria.get("ip3") + "%' ";
		}
	}
	 sqlQuery +="order by m_org_id";
	return sqlQuery;	
 }
 
 private static String  getMeterChangeList() {
	 String sqlQuery ="select count(service_no),ser.m_org_id, ser.m_org_name  from meter_change_list mc\r\n"
	 		+ "left join v_company_service ser on ser.m_comp_serv_number = mc.service_no where 1=1 ";
	 if(ipCriteria != null && !ipCriteria.isEmpty()){
		if(ipCriteria.containsKey("ip1")){
			sqlQuery += "and LPAD(month,2,'0') LIKE '%" + ipCriteria.get("ip2") + "%'";
		}
		if(ipCriteria.containsKey("ip2")){
			sqlQuery += "and year LIKE '%" + ipCriteria.get("ip3") + "%' ";
		}
		if(ipCriteria.containsKey("ip3")){
			sqlQuery += "and fuel LIKE '%" + ipCriteria.get("ip1") + "%' ";
		}
	}
	 sqlQuery +="group by ser.m_org_id, ser.m_org_name \r\n"
	 		+ "ORDER BY ser.M_ORG_ID";
	return sqlQuery;	
 }
 
 
 private static String getMeterChangeReport() {
	 String sqlQuery ="select imc.SERVICE_NO,imc.OLD_METER_NO,imc.NEW_METER_NO,amr.status,amr.modified_date\r\n" + 
	 		"from int_meter_change imc \r\n" + 
	 		"left join m_company_service mcs on mcs.\"number\" = imc.service_no\r\n" + 
	 		"left join V_meter_change_from_amr amr on amr.serviceno = imc.service_no\r\n" + 
	 		"where 1=1 ";
	 if(ipCriteria != null && !ipCriteria.isEmpty()){
		if(ipCriteria.containsKey("ip1")){
			sqlQuery += "and imc.reading_month LIKE '%" + ipCriteria.get("ip1") + "%'";
		}
		if(ipCriteria.containsKey("ip2")){
			sqlQuery += "and imc.reading_year LIKE '%" + ipCriteria.get("ip2") + "%' ";
		}
		if(ipCriteria.containsKey("ip3")){
			sqlQuery += "and mcs.fuel_type_code LIKE '%" + ipCriteria.get("ip3") + "%' ";
		}
		if(ipCriteria.containsKey("ip4")){
			sqlQuery += "and mcs.m_org_id LIKE '%" + ipCriteria.get("ip4") + "%' ";
		}
	}
//	 sqlQuery +="order by m_org_id";
	return sqlQuery;
 }
 
 private static String getGenSummaryCharges() {
	 String sqlQuery ="select tgs1.DISP_ORG_NAME,COUNT(tgs1.DISP_SERVICE_NUMBER) SERVICECOUNT,\r\n" + 
	 		"tgs1.FLOW_TYPE_CODE ,SUM(tgs1.DISP_TOTAL_CAPACITY) , \r\n" + 
	 		"SUM(tgsc1.TOTAL_CHARGES) as MRC, SUM(tgsc2.TOTAL_CHARGES) OMCharges,\r\n" + 
	 		"SUM(tgsc3.TOTAL_CHARGES) TransmissionCharges,\r\n" + 
	 		"SUM(tgsc4.TOTAL_CHARGES) SOC,SUM(tgsc5.TOTAL_CHARGES) \r\n" + 
	 		"RKvahPenalty,SUM(tgsc6.TOTAL_CHARGES) IEC,SUM(tgsc7.TOTAL_CHARGES) \r\n" + 
	 		"Scheduling,\r\n" + 
	 		"SUM(tgsc8.TOTAL_CHARGES) OtherCharges\r\n" + 
	 		"from t_gen_stmt tgs1\r\n" + 
	 		"left join (select * from T_GEN_STMT_CHARGE where CHARGE_CODE in ('C001') ) \r\n" + 
	 		"tgsc1 on tgs1.id= tgsc1.T_GEN_STMT_Id\r\n" + 
	 		"left join (select * from T_GEN_STMT_CHARGE where CHARGE_CODE in ('C002') ) \r\n" + 
	 		"tgsc2 on tgs1.id= tgsc2.T_GEN_STMT_Id\r\n" + 
	 		"left join (select * from T_GEN_STMT_CHARGE where CHARGE_CODE in ('C003') ) \r\n" + 
	 		"tgsc3 on tgs1.id= tgsc3.T_GEN_STMT_Id\r\n" + 
	 		"left join (select * from T_GEN_STMT_CHARGE where CHARGE_CODE in ('C004') ) \r\n" + 
	 		"tgsc4 on tgs1.id= tgsc4.T_GEN_STMT_Id\r\n" + 
	 		"left join (select * from T_GEN_STMT_CHARGE where CHARGE_CODE in ('C005') ) \r\n" + 
	 		"tgsc5 on tgs1.id= tgsc5.T_GEN_STMT_Id\r\n" + 
	 		"left join (select * from T_GEN_STMT_CHARGE where CHARGE_CODE in ('C006') ) \r\n" + 
	 		"tgsc6 on tgs1.id= tgsc6.T_GEN_STMT_Id\r\n" + 
	 		"left join (select * from T_GEN_STMT_CHARGE where CHARGE_CODE in ('C007') ) \r\n" + 
	 		"tgsc7 on tgs1.id= tgsc7.T_GEN_STMT_Id\r\n" + 
	 		"left join (select * from T_GEN_STMT_CHARGE where CHARGE_CODE in ('C008') ) \r\n" + 
	 		"tgsc8 on tgs1.id= tgsc8.T_GEN_STMT_Id where 1=1 ";
	 if(ipCriteria != null && !ipCriteria.isEmpty()){
		if(ipCriteria.containsKey("ip1")){
			sqlQuery += "and tgs1.STMT_MONTH LIKE '%" + ipCriteria.get("ip1") + "%'";
		}
		if(ipCriteria.containsKey("ip2")){
			sqlQuery += "and tgs1.STMT_YEAR LIKE '%" + ipCriteria.get("ip2") + "%' ";
		}
		if(ipCriteria.containsKey("ip3")){
			sqlQuery += "and tgs1.DISP_ORG_CODE LIKE '%" + ipCriteria.get("ip3") + "%' ";
		}
		if(ipCriteria.containsKey("ip4")){
			sqlQuery += "and tgs1.FLOW_TYPE_CODE LIKE '%" + ipCriteria.get("ip4") + "%' ";
		}
		if(ipCriteria.containsKey("ip5")){
			sqlQuery += "and tgs1.DISP_FUEL_TYPE_CODE LIKE '%" + ipCriteria.get("ip5") + "%' ";
		}
		if(ipCriteria.containsKey("ip6")){
			sqlQuery += "and tgs1.IS_REC LIKE '%" + ipCriteria.get("ip6") + "%' ";
		}
		if(ipCriteria.containsKey("ip7")){
			sqlQuery += "and tgs1.disp_fuel_type_group LIKE '%" + ipCriteria.get("ip7") + "%' ";
		}

	}
	 sqlQuery +=" group by tgs1.FLOW_TYPE_CODE, tgs1.DISP_ORG_NAME UNION ALL \n"
	 		+ " SELECT '' ,COUNT(tgs1.DISP_SERVICE_NUMBER) SERVICECOUNT,'',\n"
	 		+ "SUM(tgs1.DISP_TOTAL_CAPACITY) , \n"
	 		+ "SUM(tgsc1.TOTAL_CHARGES) as MRC, SUM(tgsc2.TOTAL_CHARGES) OMCharges,\n"
	 		+ "SUM(tgsc3.TOTAL_CHARGES) TransmissionCharges,\n"
	 		+ "SUM(tgsc4.TOTAL_CHARGES) SOC,SUM(tgsc5.TOTAL_CHARGES) \n"
	 		+ "RKvahPenalty,SUM(tgsc6.TOTAL_CHARGES) IEC,SUM(tgsc7.TOTAL_CHARGES) \n"
	 		+ "Scheduling,\n"
	 		+ "SUM(tgsc8.TOTAL_CHARGES) OtherCharges\n"
	 		+ "from t_gen_stmt tgs1\n"
	 		+ "left join (select * from T_GEN_STMT_CHARGE where CHARGE_CODE in ('C001') ) \n"
	 		+ "tgsc1 on tgs1.id= tgsc1.T_GEN_STMT_Id\n"
	 		+ "left join (select * from T_GEN_STMT_CHARGE where CHARGE_CODE in ('C002') ) \n"
	 		+ "tgsc2 on tgs1.id= tgsc2.T_GEN_STMT_Id\n"
	 		+ "left join (select * from T_GEN_STMT_CHARGE where CHARGE_CODE in ('C003') ) \n"
	 		+ "tgsc3 on tgs1.id= tgsc3.T_GEN_STMT_Id\n"
	 		+ "left join (select * from T_GEN_STMT_CHARGE where CHARGE_CODE in ('C004') ) \n"
	 		+ "tgsc4 on tgs1.id= tgsc4.T_GEN_STMT_Id\n"
	 		+ "left join (select * from T_GEN_STMT_CHARGE where CHARGE_CODE in ('C005') ) \n"
	 		+ "tgsc5 on tgs1.id= tgsc5.T_GEN_STMT_Id\n"
	 		+ "left join (select * from T_GEN_STMT_CHARGE where CHARGE_CODE in ('C006') ) \n"
	 		+ "tgsc6 on tgs1.id= tgsc6.T_GEN_STMT_Id\n"
	 		+ "left join (select * from T_GEN_STMT_CHARGE where CHARGE_CODE in ('C007') ) \n"
	 		+ "tgsc7 on tgs1.id= tgsc7.T_GEN_STMT_Id\n"
	 		+ "left join (select * from T_GEN_STMT_CHARGE where CHARGE_CODE in ('C008') ) \n"
	 		+ "tgsc8 on tgs1.id= tgsc8.T_GEN_STMT_Id where 1=1 ";
	 
	 if(ipCriteria != null && !ipCriteria.isEmpty()){
			if(ipCriteria.containsKey("ip1")){
				sqlQuery += "and tgs1.STMT_MONTH LIKE '%" + ipCriteria.get("ip1") + "%'";
			}
			if(ipCriteria.containsKey("ip2")){
				sqlQuery += "and tgs1.STMT_YEAR LIKE '%" + ipCriteria.get("ip2") + "%' ";
			}
			if(ipCriteria.containsKey("ip3")){
				sqlQuery += "and tgs1.DISP_ORG_CODE LIKE '%" + ipCriteria.get("ip3") + "%' ";
			}
			if(ipCriteria.containsKey("ip4")){
				sqlQuery += "and tgs1.FLOW_TYPE_CODE LIKE '%" + ipCriteria.get("ip4") + "%' ";
			}
			if(ipCriteria.containsKey("ip5")){
				sqlQuery += "and tgs1.DISP_FUEL_TYPE_CODE LIKE '%" + ipCriteria.get("ip5") + "%' ";
			}
			if(ipCriteria.containsKey("ip6")){
				sqlQuery += "and tgs1.IS_REC LIKE '%" + ipCriteria.get("ip6") + "%' ";
			}
			if(ipCriteria.containsKey("ip7")){
				sqlQuery += "and tgs1.disp_fuel_type_group LIKE '%" + ipCriteria.get("ip7") + "%' ";
			}

		}
	
	 return sqlQuery;
 }
 
 private static String getSamastAppln(){
	 String sqlQuery ="select * from v_ext_samast_appln where 1=1 ";
	 if(ipCriteria != null && !ipCriteria.isEmpty()){
		if(ipCriteria.containsKey("ip1")){
			sqlQuery += "and appl_date LIKE '%" + ipCriteria.get("ip1") + "%'";
		}
		if(ipCriteria.containsKey("ip2")){
			sqlQuery += "and appl_no LIKE '%" + ipCriteria.get("ip2") + "%'";
		}
	}
	 
	 sqlQuery +="order by appl_no";
	return sqlQuery;
 }
 
 private static String getExtSamastAppln(){
	 String sqlQuery ="select * from ext_samast_appln";
	return sqlQuery;
 }

 private static String getMeterReadingReport() {
	    
		String OrgId="";
		String Month="";
		String Year="";
		String FuelCode="";
		
		if(ipCriteria.containsKey("ip1")) {
			OrgId = ipCriteria.get("ip1");
		}
		if(ipCriteria.containsKey("ip2")){
			 Month = ipCriteria.get("ip2");
		 }
		if(ipCriteria.containsKey("ip3")){
			Year = ipCriteria.get("ip3");
		}
		if(ipCriteria.containsKey("ip4")){
			FuelCode = ipCriteria.get("ip4");
		}
		
		String SqlQuery="SELECT vcs.M_ORG_ID,vcs.m_org_name ,count(vcs.M_ORG_ID) AS Total_Services,count(CASE WHEN  vcs.FLOW_TYPE_CODE='IS-CAPTIVE' THEN 1 END) AS flowtypeCaptive, count(CASE WHEN  vcs.FLOW_TYPE_CODE='THIRD-PARTY' THEN 1 END) AS flowtypeThird,count(CASE WHEN  vcs.FLOW_TYPE_CODE='STB' THEN 1 END) AS flowtypeSTB \n"
				+ " ,count(a.M_COMPANY_METER_ID) AS AMR,count(E1.FLOW_TYPE_CODE) AS flowtypeCaptiveAMR,count(D1.FLOW_TYPE_CODE) AS flowtypeThirdAMR, count(F1.FLOW_TYPE_CODE) AS flowtypeSTBAMR \n"
				+ " ,count(C.M_COMPANY_METER_ID) AS ManualReading,count(E2.FLOW_TYPE_CODE) AS flowtypeCaptiveMANUAL,count(D2.FLOW_TYPE_CODE) AS flowtypeThirdMANUAL, count(F2.FLOW_TYPE_CODE) AS flowtypeSTBMANUAL \n"
				+ " ,count(b.ID) AS MACHINE_NOT_RUNNING_DC_SERVICES,count(B2.ID) AS flowtypeCaptiveMNR,count(B1.ID) AS flowtypeThirdMNR, count(B3.ID) AS flowtypeSTBMNR FROM V_COMPANY_SERVICE VCS\n"
				+ "LEFT JOIN\n"
				+ "(SELECT ser.M_ORG_ID, ser.m_org_name,ser.capacity,ser.FLOW_TYPE_CODE ,ser.M_COMPANY_METER_ID FROM T_METER_READING_HDR hdr\n"
				+ "LEFT JOIN v_company_service ser ON hdr.M_COMPANY_METER_ID=ser.M_COMPANY_METER_ID\n"
				+ "WHERE hdr.READING_MONTH LIKE '%" + Month + "%' AND READING_YEAR LIKE '%"+ Year +"%' AND hdr.MR_SOURCE_CODE ='01' AND ser.CAPACITY IS NOT null  AND REGEXP_LIKE(ser.CAPACITY , '^[[:digit:]]+$')\n"
				+ "ORDER BY ser.M_ORG_ID ) A ON VCS.M_COMPANY_METER_ID=A.M_COMPANY_METER_ID\n"
				+ "LEFT JOIN\n"
				+ "(SELECT ser21.M_ORG_ID, ser21.m_org_name,ser21.capacity,ser21.FLOW_TYPE_CODE ,ser21.M_COMPANY_METER_ID FROM T_METER_READING_HDR hdr21\n"
				+ "LEFT JOIN v_company_service ser21 ON hdr21.M_COMPANY_METER_ID=ser21.M_COMPANY_METER_ID\n"
				+ "WHERE hdr21.READING_MONTH LIKE '%" + Month + "%' AND READING_YEAR LIKE '%"+ Year +"%' AND hdr21.MR_SOURCE_CODE ='01' AND ser21.FLOW_TYPE_CODE ='THIRD-PARTY'  AND ser21.CAPACITY IS NOT null  AND REGEXP_LIKE(ser21.CAPACITY , '^[[:digit:]]+$')\n"
				+ "ORDER BY ser21.M_ORG_ID ) D1 ON VCS.M_COMPANY_METER_ID=D1.M_COMPANY_METER_ID\n"
				+ "LEFT JOIN\n"
				+ "(SELECT ser31.M_ORG_ID, ser31.m_org_name,ser31.capacity,ser31.FLOW_TYPE_CODE ,ser31.M_COMPANY_METER_ID FROM T_METER_READING_HDR hdr31\n"
				+ "LEFT JOIN v_company_service ser31 ON hdr31.M_COMPANY_METER_ID=ser31.M_COMPANY_METER_ID\n"
				+ "WHERE hdr31.READING_MONTH LIKE '%" + Month + "%' AND READING_YEAR LIKE '%"+ Year +"%' AND ser31.FLOW_TYPE_CODE ='IS-CAPTIVE' AND hdr31.MR_SOURCE_CODE ='01' AND ser31.CAPACITY IS NOT null  AND REGEXP_LIKE(ser31.CAPACITY , '^[[:digit:]]+$')\n"
				+ "ORDER BY ser31.M_ORG_ID ) E1 ON VCS.M_COMPANY_METER_ID=E1.M_COMPANY_METER_ID\n"
				+ "LEFT JOIN\n"
				+ "(SELECT ser41.M_ORG_ID, ser41.m_org_name,ser41.capacity,ser41.FLOW_TYPE_CODE ,ser41.M_COMPANY_METER_ID FROM T_METER_READING_HDR hdr41\n"
				+ "LEFT JOIN v_company_service ser41 ON hdr41.M_COMPANY_METER_ID=ser41.M_COMPANY_METER_ID\n"
				+ "WHERE hdr41.READING_MONTH LIKE '%" + Month + "%' AND READING_YEAR LIKE '%"+ Year +"%' AND ser41.FLOW_TYPE_CODE ='STB' AND hdr41.MR_SOURCE_CODE ='01' AND ser41.CAPACITY IS NOT null  AND REGEXP_LIKE(ser41.CAPACITY , '^[[:digit:]]+$')\n"
				+ "ORDER BY ser41.M_ORG_ID ) F1 ON VCS.M_COMPANY_METER_ID=F1.M_COMPANY_METER_ID\n"
				+ "LEFT JOIN\n"
				+ "(SELECT ser22.M_ORG_ID, ser22.m_org_name,ser22.capacity,ser22.FLOW_TYPE_CODE ,ser22.M_COMPANY_METER_ID FROM T_METER_READING_HDR hdr22\n"
				+ "LEFT JOIN v_company_service ser22 ON hdr22.M_COMPANY_METER_ID=ser22.M_COMPANY_METER_ID\n"
				+ "WHERE hdr22.READING_MONTH LIKE '%" + Month + "%' AND READING_YEAR LIKE '%"+ Year +"%' AND hdr22.MR_SOURCE_CODE ='02' AND ser22.FLOW_TYPE_CODE ='THIRD-PARTY'  AND ser22.CAPACITY IS NOT null  AND REGEXP_LIKE(ser22.CAPACITY , '^[[:digit:]]+$')\n"
				+ "ORDER BY ser22.M_ORG_ID ) D2 ON VCS.M_COMPANY_METER_ID=D2.M_COMPANY_METER_ID\n"
				+ "LEFT JOIN\n"
				+ "(SELECT ser32.M_ORG_ID, ser32.m_org_name,ser32.capacity,ser32.FLOW_TYPE_CODE ,ser32.M_COMPANY_METER_ID FROM T_METER_READING_HDR hdr32\n"
				+ "LEFT JOIN v_company_service ser32 ON hdr32.M_COMPANY_METER_ID=ser32.M_COMPANY_METER_ID\n"
				+ "WHERE hdr32.READING_MONTH LIKE '%" + Month + "%' AND READING_YEAR LIKE '%"+ Year +"%' AND ser32.FLOW_TYPE_CODE ='IS-CAPTIVE' AND hdr32.MR_SOURCE_CODE ='02' AND ser32.CAPACITY IS NOT null  AND REGEXP_LIKE(ser32.CAPACITY , '^[[:digit:]]+$')\n"
				+ "ORDER BY ser32.M_ORG_ID ) E2 ON VCS.M_COMPANY_METER_ID=E2.M_COMPANY_METER_ID\n"
				+ "LEFT JOIN\n"
				+ "(SELECT ser42.M_ORG_ID, ser42.m_org_name,ser42.capacity,ser42.FLOW_TYPE_CODE ,ser42.M_COMPANY_METER_ID FROM T_METER_READING_HDR hdr42\n"
				+ "LEFT JOIN v_company_service ser42 ON hdr42.M_COMPANY_METER_ID=ser42.M_COMPANY_METER_ID\n"
				+ "WHERE hdr42.READING_MONTH LIKE '%" + Month + "%' AND READING_YEAR LIKE '%"+ Year +"%' AND ser42.FLOW_TYPE_CODE ='STB' AND hdr42.MR_SOURCE_CODE ='02' AND ser42.CAPACITY IS NOT null  AND REGEXP_LIKE(ser42.CAPACITY , '^[[:digit:]]+$')\n"
				+ "ORDER BY ser42.M_ORG_ID ) F2 ON VCS.M_COMPANY_METER_ID=F2.M_COMPANY_METER_ID\n"
				+ "LEFT JOIN\n"
				+ "(SELECT ser1.M_ORG_ID, ser1.m_org_name,ser1.capacity ,ser1.M_COMPANY_METER_ID FROM T_METER_READING_HDR hdr1\n"
				+ "LEFT JOIN v_company_service ser1 ON hdr1.M_COMPANY_METER_ID=ser1.M_COMPANY_METER_ID\n"
				+ "WHERE hdr1.READING_MONTH LIKE '%" + Month + "%' AND READING_YEAR LIKE '%"+ Year +"%' AND hdr1.MR_SOURCE_CODE ='02'  AND ser1.CAPACITY IS NOT null  AND REGEXP_LIKE(ser1.CAPACITY , '^[[:digit:]]+$')\n"
				+ "ORDER BY ser1.M_ORG_ID ) C ON VCS.M_COMPANY_METER_ID=C.M_COMPANY_METER_ID\n"
				+ "LEFT join(\n"
				+ "SELECT vcs2.M_ORG_ID, vcs2.m_org_name,vcs2.ID  FROM M_POWERPLANT mp \n"
				+ "LEFT JOIN V_COMPANY_SERVICE vcs2 ON mp.M_SERVICE_ID =vcs2.ID \n"
				+ "WHERE mp.STATUS IN ('DCS','MNS')\n"
				+ "ORDER BY vcs2.M_ORG_ID ) B ON VCS.ID=B.ID\n"
				+ "LEFT join(\n"
				+ "SELECT vcs21.M_ORG_ID, vcs21.m_org_name,vcs21.ID  FROM M_POWERPLANT mp1 \n"
				+ "LEFT JOIN V_COMPANY_SERVICE vcs21 ON mp1.M_SERVICE_ID =vcs21.ID \n"
				+ "WHERE mp1.STATUS IN ('DCS','MNS') AND VCS21 .FLOW_TYPE_CODE ='THIRD-PARTY'\n"
				+ "ORDER BY vcs21.M_ORG_ID ) B1 ON VCS.ID=B1.ID\n"
				+ "LEFT join(\n"
				+ "SELECT vcs22.M_ORG_ID, vcs22.m_org_name,vcs22.ID  FROM M_POWERPLANT mp2 \n"
				+ "LEFT JOIN V_COMPANY_SERVICE vcs22 ON mp2.M_SERVICE_ID =vcs22.ID \n"
				+ "WHERE mp2.STATUS IN ('DCS','MNS') AND VCS22 .FLOW_TYPE_CODE ='IS-CAPTIVE'\n"
				+ "ORDER BY vcs22.M_ORG_ID ) B2 ON VCS.ID=B2.ID\n"
				+ "LEFT join(\n"
				+ "SELECT vcs23.M_ORG_ID, vcs23.m_org_name,vcs23.ID  FROM M_POWERPLANT mp3 \n"
				+ "LEFT JOIN V_COMPANY_SERVICE vcs23 ON mp3.M_SERVICE_ID =vcs23.ID \n"
				+ "WHERE mp3.STATUS IN ('DCS','MNS') AND VCS23 .FLOW_TYPE_CODE ='STB'\n"
				+ "ORDER BY vcs23.M_ORG_ID ) B3 ON VCS.ID=B3.ID\n"
				+ "where VCS.M_ORG_ID LIKE '%"+ OrgId +"%' AND LENGTH(vcs.\"number\")=12  AND VCS .FUEL_TYPE_CODE LIKE '%"+ FuelCode +"%'\n"
				+ "GROUP BY vcs.M_ORG_ID,vcs.m_org_name\n"
				+ "ORDER BY vcs.m_org_id";	 
		 
		 return SqlQuery ;
		}
 private static String SolarFeederEdcReport() {
	 
	 String OrgId="";
     String Month="";
     String Year="";
     String Substation="";
     
     if(ipCriteria.containsKey("ip4")) {
         OrgId = ipCriteria.get("ip4");
     }
     if(ipCriteria.containsKey("ip1")){
          Month = ipCriteria.get("ip1");
      }
     if(ipCriteria.containsKey("ip2")){
         Year = ipCriteria.get("ip2");
     }
     if(ipCriteria.containsKey("ip3")){
    	 Substation = ipCriteria.get("ip3");
     }
	 
	 String sqlQuery ="SELECT upper(ed.M_SUBSTATION_NAME), mo.ID ,ed.M_ORG_NAME, ed.M_FEEDER_NAME, ed.METERNO, ed.VOLTAGE, ed.TOTALSERVICES, ed.BILLINGSS, ed.EXP_UNITS, ed.READING_MONTH,\n"
	 		+ "   ed.READING_YEAR, ed.FEEDER_LINE_LENGTH\n"
	 		+ "	 		FROM OPENACCESS.SOLARFEEDER_EDCWISE ed\n"
	 		+ "	 	LEFT JOIN M_ORG mo ON ed.M_ORG_NAME=mo.NAME\n"
	 		+ "	 		where 1=1 AND mo.ID LIKE '%"+OrgId+"%' AND ed.READING_MONTH LIKE '%"+Month+"%' AND ed.READING_YEAR  LIKE '%"+Year+"%' AND ed.M_SUBSTATION_NAME LIKE '%"+Substation+"%'";
return sqlQuery;
 }
private static String getMeterReadingDetailReport() {
     
     String OrgId="";
     String Month="";
     String Year="";
     String FuelCode="";
     String Sourcecode="";
     String Name="";
     String Data="";
     String FlowType="";
     
     if(ipCriteria.containsKey("ip1")) {
         OrgId = ipCriteria.get("ip1");
     }
     if(ipCriteria.containsKey("ip2")){
          Month = ipCriteria.get("ip2");
      }
     if(ipCriteria.containsKey("ip3")){
         Year = ipCriteria.get("ip3");
     }
     if(ipCriteria.containsKey("ip4")){
         FuelCode = ipCriteria.get("ip4");
     }
     if(ipCriteria.containsKey("ip5")) {
     	Sourcecode = ipCriteria.get("ip5");
     }
     if(ipCriteria.containsKey("ip6")){
      	Name = ipCriteria.get("ip6");
      } 
     if(ipCriteria.containsKey("ip7")){
    	 FlowType = ipCriteria.get("ip7");
       } 
     
      if(Name=="Total" || Name.equals("Total")) {
      Data="SELECT vcs.M_ORG_ID,vcs.m_org_name,vcs.\"number\",vcs.M_COMPANY_NAME ,decode(vcs.FLOW_TYPE_CODE,'IS-CAPTIVE','CAPTIVE','STB','STB','THIRD-PARTY','THIRD-PARTY'),VCS .METER_NUMBER FROM V_COMPANY_SERVICE vcs WHERE vcs.M_ORG_ID LIKE '%"+OrgId+"%' AND length(vcs.\"number\")=12 AND vcs.FUEL_TYPE_CODE LIKE '%"+FuelCode+"%'";  
      }
      
      if(Name.equals("AMR")) {
      Data="SELECT vcs.M_ORG_ID,vcs.m_org_name ,a.\"number\",vcs.M_COMPANY_NAME,decode(vcs.FLOW_TYPE_CODE,'IS-CAPTIVE','CAPTIVE','STB','STB','THIRD-PARTY','THIRD-PARTY'),VCS .METER_NUMBER, a.CREATED_DATE,count(a.M_COMPANY_METER_ID) AS AMR\n"
      		+ ",count(vcs.M_COMPANY_METER_ID) AS TotalNoOfServices FROM V_COMPANY_SERVICE VCS\n"
      		+ "LEFT JOIN\n"
      		+ "(SELECT ser.M_ORG_ID, ser.m_org_name,ser.capacity ,ser.\"number\",hdr.CREATED_DATE,ser.M_COMPANY_METER_ID FROM T_METER_READING_HDR hdr\n"
      		+ "LEFT JOIN v_company_service ser ON hdr.M_COMPANY_METER_ID=ser.M_COMPANY_METER_ID\n"
      		+ "WHERE hdr.READING_MONTH LIKE '%" + Month + "%' AND READING_YEAR LIKE '%"+ Year +"%' AND hdr.MR_SOURCE_CODE ='"+Sourcecode+"' AND ser.CAPACITY IS NOT null  AND REGEXP_LIKE(ser.CAPACITY , '^[[:digit:]]+$')\n"
      		+ "ORDER BY ser.M_ORG_ID ) A ON VCS.M_COMPANY_METER_ID=A.M_COMPANY_METER_ID\n"
      		+ "where VCS.M_ORG_ID LIKE '%"+ OrgId +"%' AND LENGTH(vcs.\"number\")=12  AND VCS .FUEL_TYPE_CODE LIKE '%"+ FuelCode +"%' AND VCS.M_COMPANY_METER_ID=A.M_COMPANY_METER_ID\n"
      		+ "GROUP BY vcs.M_ORG_ID,vcs.m_org_name,a.\"number\",vcs.M_COMPANY_NAME,vcs.FLOW_TYPE_CODE,VCS .METER_NUMBER,a.CREATED_DATE\n"
      		+ "ORDER BY vcs.m_org_id";
      }
      if(Name.equals("MANUAL")) {
    	  Data=" SELECT vcs.M_ORG_ID,vcs.m_org_name ,a.\"number\",vcs.M_COMPANY_NAME,decode(vcs.FLOW_TYPE_CODE,'IS-CAPTIVE','CAPTIVE','STB','STB','THIRD-PARTY','THIRD-PARTY'),VCS .METER_NUMBER , to_char(a.CREATED_DATE,'DD-MM-YYYY'),count(a.M_COMPANY_METER_ID) AS ManualReadingServices\n"
    	  		+ ",count(vcs.M_COMPANY_METER_ID) AS TotalNoOfServices FROM V_COMPANY_SERVICE VCS\n"
    	  		+ "LEFT JOIN\n"
    	  		+ "(SELECT ser.M_ORG_ID,hdr.CREATED_DATE ,ser.m_org_name,ser.capacity ,ser.\"number\",ser.M_COMPANY_METER_ID FROM T_METER_READING_HDR hdr\n"
    	  		+ "LEFT JOIN v_company_service ser ON hdr.M_COMPANY_METER_ID=ser.M_COMPANY_METER_ID\n"
    	  		+ "WHERE hdr.READING_MONTH LIKE '%" + Month + "%' AND READING_YEAR LIKE '%"+ Year +"%'  AND hdr.MR_SOURCE_CODE ='"+Sourcecode+"' AND ser.CAPACITY IS NOT null  AND REGEXP_LIKE(ser.CAPACITY , '^[[:digit:]]+$')\n"
    	  		+ "ORDER BY ser.M_ORG_ID ) A ON VCS.M_COMPANY_METER_ID=A.M_COMPANY_METER_ID\n"
    	  		+ "where VCS.M_ORG_ID LIKE '%"+ OrgId +"%' AND LENGTH(vcs.\"number\")=12  AND VCS .FUEL_TYPE_CODE LIKE '%"+ FuelCode +"%' AND VCS.M_COMPANY_METER_ID=A.M_COMPANY_METER_ID\n"
    	  		+ "GROUP BY vcs.M_ORG_ID,vcs.m_org_name,a.\"number\",vcs.M_COMPANY_NAME,vcs.FLOW_TYPE_CODE,VCS .METER_NUMBER,A.created_date\n"
    	  		+ "ORDER BY vcs.m_org_id";
      }
       
      if(Name=="NotReading" || Name.equals("NotReading")) {
     Data="SELECT vcs.M_ORG_ID,vcs.m_org_name ,vcs.\"number\",vcs.M_COMPANY_NAME,decode(vcs.FLOW_TYPE_CODE,'IS-CAPTIVE','CAPTIVE','STB','STB','THIRD-PARTY','THIRD-PARTY'),VCS .METER_NUMBER,count(b.ID) AS NotReadingServices\n"
  			+ ",count(vcs.M_COMPANY_METER_ID) AS TotalNoOfServices FROM V_COMPANY_SERVICE VCS\n"
  			+ "LEFT JOIN\n"
  			+ "(\n"
  			+ "SELECT vcs2.M_ORG_ID, vcs2.m_org_name,vcs2.ID  FROM M_POWERPLANT mp \n"
  			+ "LEFT JOIN V_COMPANY_SERVICE vcs2 ON mp.M_SERVICE_ID =vcs2.ID \n"
  			+ "WHERE mp.STATUS IN ('DCS','MNS')\n"
  			+ "ORDER BY vcs2.M_ORG_ID ) B ON VCS.ID=B.ID\n"
  			+ "where VCS.M_ORG_ID LIKE '%"+ OrgId +"%' AND LENGTH(vcs.\"number\")=12  AND VCS .FUEL_TYPE_CODE LIKE '%"+ FuelCode +"%' AND vcs.ID=b.ID\n"
  			+ "GROUP BY vcs.M_ORG_ID,vcs.m_org_name,vcs.\"number\",vcs.M_COMPANY_NAME,vcs.FLOW_TYPE_CODE,VCS .METER_NUMBER\n"
  			+ "ORDER BY vcs.m_org_id";
     
      }
      
      if(Name.equals("TOTALCAPTIVE") || Name.equals("TOTALTHIRD-PARTY") || Name.equals("TOTALSTB")) {
    	  Data="SELECT vcs.M_ORG_ID,vcs.m_org_name,vcs.\"number\",vcs.M_COMPANY_NAME ,decode(vcs.FLOW_TYPE_CODE,'IS-CAPTIVE','CAPTIVE','STB','STB','THIRD-PARTY','THIRD-PARTY'),VCS .METER_NUMBER FROM V_COMPANY_SERVICE vcs WHERE vcs.M_ORG_ID LIKE '%"+OrgId+"%' AND length(vcs.\"number\")=12 AND vcs.FUEL_TYPE_CODE LIKE '%"+FuelCode+"%' AND vcs.FLOW_TYPE_CODE LIKE '%"+FlowType+"%'";
    			  
      }
      
      if(Name.equals("AMRCAPTIVE") || Name.equals("AMRTHIRD-PARTY") || Name.equals("AMRSTB")) {
    	  Data=" SELECT vcs.M_ORG_ID,vcs.m_org_name ,a.\"number\",vcs.M_COMPANY_NAME,decode(vcs.FLOW_TYPE_CODE,'IS-CAPTIVE','CAPTIVE','STB','STB','THIRD-PARTY','THIRD-PARTY'),VCS .METER_NUMBER FROM V_COMPANY_SERVICE VCS\n"
    	  		+ "LEFT JOIN\n"
    	  		+ "(SELECT ser.M_ORG_ID, ser.m_org_name,ser.capacity ,ser.\"number\",ser.M_COMPANY_METER_ID FROM T_METER_READING_HDR hdr\n"
    	  		+ "LEFT JOIN v_company_service ser ON hdr.M_COMPANY_METER_ID=ser.M_COMPANY_METER_ID\n"
    	  		+ "WHERE hdr.READING_MONTH LIKE '%" + Month + "%' AND READING_YEAR LIKE '%"+ Year +"%' AND hdr.MR_SOURCE_CODE ='01' AND ser.CAPACITY IS NOT null  AND REGEXP_LIKE(ser.CAPACITY , '^[[:digit:]]+$')\n"
    	  		+ "ORDER BY ser.M_ORG_ID ) A ON VCS.M_COMPANY_METER_ID=A.M_COMPANY_METER_ID\n"
    	  		+ "where VCS.M_ORG_ID LIKE '%"+ OrgId +"%' AND LENGTH(A.\"number\")=12  AND VCS .FUEL_TYPE_CODE LIKE '%"+ FuelCode +"%' AND vcs.FLOW_TYPE_CODE LIKE '%" + FlowType + "%' AND VCS.M_COMPANY_METER_ID=A.M_COMPANY_METER_ID\n"
    	  		+ "GROUP BY vcs.M_ORG_ID,vcs.m_org_name,a.\"number\",vcs.M_COMPANY_NAME,vcs.FLOW_TYPE_CODE,VCS .METER_NUMBER\n"
    	  		+ "ORDER BY vcs.m_org_id";
      }
      
      if(Name.equals("MANUALCAPTIVE")|| Name.equals("MANUALTHIRD-PARTY")|| Name.equals("MANUALSTB")) {
    	  Data=" SELECT vcs.M_ORG_ID,vcs.m_org_name ,a.\"number\",vcs.M_COMPANY_NAME,decode(vcs.FLOW_TYPE_CODE,'IS-CAPTIVE','CAPTIVE','STB','STB','THIRD-PARTY','THIRD-PARTY'),VCS .METER_NUMBER,to_char(a.CREATED_DATE,'DD-MM-YYYY') FROM V_COMPANY_SERVICE VCS\n"
      	  		+ "LEFT JOIN\n"
      	  		+ "(SELECT ser.M_ORG_ID,hdr.CREATED_DATE, ser.m_org_name,ser.capacity ,ser.\"number\",ser.M_COMPANY_METER_ID FROM T_METER_READING_HDR hdr\n"
      	  		+ "LEFT JOIN v_company_service ser ON hdr.M_COMPANY_METER_ID=ser.M_COMPANY_METER_ID\n"
      	  		+ "WHERE hdr.READING_MONTH LIKE '%" + Month + "%' AND READING_YEAR LIKE '%"+ Year +"%' AND hdr.MR_SOURCE_CODE ='02' AND ser.CAPACITY IS NOT null  AND REGEXP_LIKE(ser.CAPACITY , '^[[:digit:]]+$')\n"
      	  		+ "ORDER BY ser.M_ORG_ID ) A ON VCS.M_COMPANY_METER_ID=A.M_COMPANY_METER_ID\n"
      	  		+ "where VCS.M_ORG_ID LIKE '%"+ OrgId +"%' AND LENGTH(A.\"number\")=12  AND VCS .FUEL_TYPE_CODE LIKE '%"+ FuelCode +"%' AND vcs.FLOW_TYPE_CODE LIKE '%" + FlowType + "%' AND VCS.M_COMPANY_METER_ID=A.M_COMPANY_METER_ID\n"
      	  		+ "GROUP BY vcs.M_ORG_ID,vcs.m_org_name,a.\"number\",vcs.M_COMPANY_NAME,vcs.FLOW_TYPE_CODE,VCS .METER_NUMBER,A.created_date\n"
      	  		+ "ORDER BY vcs.m_org_id";
      }
      
      if(Name.equals("NOTRUNNINGCAPTIVE")|| Name.equals("NOTRUNNINGTHIRD-PARTY")|| Name.equals("NOTRUNNINGSTB")) {
    	  Data="SELECT vcs.M_ORG_ID,vcs.m_org_name ,vcs.\"number\",vcs.M_COMPANY_NAME,decode(vcs.FLOW_TYPE_CODE,'IS-CAPTIVE','CAPTIVE','STB','STB','THIRD-PARTY','THIRD-PARTY'),VCS .METER_NUMBER,count(b.ID) AS NotReadingServices\n"
    	  		+ ",count(vcs.M_COMPANY_METER_ID),vcs.FLOW_TYPE_CODE AS TotalNoOfServices FROM V_COMPANY_SERVICE VCS\n"
    	  		+ "LEFT JOIN\n"
    	  		+ "(\n"
    	  		+ "SELECT vcs2.M_ORG_ID, vcs2.m_org_name,vcs2.ID  FROM M_POWERPLANT mp \n"
    	  		+ "LEFT JOIN V_COMPANY_SERVICE vcs2 ON mp.M_SERVICE_ID =vcs2.ID \n"
    	  		+ "WHERE mp.STATUS IN ('DCS','MNS')\n"
    	  		+ "ORDER BY vcs2.M_ORG_ID ) B ON VCS.ID=B.ID\n"
    	  		+ "where VCS.M_ORG_ID LIKE '%"+ OrgId +"%' AND LENGTH(vcs.\"number\")=12  AND VCS .FUEL_TYPE_CODE LIKE '%"+ FuelCode +"%' AND vcs.FLOW_TYPE_CODE LIKE '%" + FlowType + "%' AND vcs.ID=b.ID\n"
    	  		+ "GROUP BY vcs.M_ORG_ID,vcs.m_org_name,vcs.\"number\",vcs.M_COMPANY_NAME,vcs.FLOW_TYPE_CODE,vcs.METER_NUMBER\n"
    	  		+ "ORDER BY vcs.m_org_id";
      }
      
      String SqlQuery=Data;
     
      return SqlQuery ;
     }

private static String getMasterSolarReport(){
	
	 String OrgId="";
     String Year="";
     String FlowType="";
     String Substation="";
     String isRec="";
     String District="";
     String status="";
	
     if(ipCriteria.containsKey("ip1")) {
         OrgId = ipCriteria.get("ip1");
     }
     if(ipCriteria.containsKey("ip2")){
         Year = ipCriteria.get("ip2");
     }
     if(ipCriteria.containsKey("ip3")){
    	 FlowType = ipCriteria.get("ip3");
     }
     if(ipCriteria.containsKey("ip4")){
    	 Substation = ipCriteria.get("ip4");
     }
     if(ipCriteria.containsKey("ip5")){
    	 isRec = ipCriteria.get("ip5");
     }
     if(ipCriteria.containsKey("ip6")){
    	 District = ipCriteria.get("ip6");
     }
     if(ipCriteria.containsKey("ip7")){
    	 status = ipCriteria.get("ip7");
     }
	
	 String sqlQuery ="select * from (\n"
	 		+ "select * from (\n"
	 		+ "select s.m_org_id ORG_ID,s.m_org_name ORG_NAME,s.\"number\" as SPG_HT_Service_No,s.m_company_name\n"
	 		+ "Generator_Name,to_char(s.commission_date,'dd-mm-yyyy'),mg.NO_OF_UNITS,mg.capacity,s.capacity as total_Capacity,s.district_name,\n"
	 		+ "s.flow_type_code,decode(s.IS_REC,'N','Non-REC','Y','REC'),s.fuel_type_name,s.VOLTAGE_NAME,\n"
	 		+ "pp.plant_class_type_code,tr.rate,tr.weg_group_name,\n"
	 		+ "mm.ct_ratio,mm.pt_ratio,mm.mf,mm.MODEM_NUMBER,mm.METER_NUMBER,\n"
	 		+ "vc.VALUE_DESC as \"METER_MAKE\",\n"
	 		+ "s.M_SUBSTATION_NAME,s.M_FEEDER_NAME,s.M_FEEDER_ID,mf.feeder_line_length,s.TYPE_OF_SS,pp.STATUS,pp.STATUS_REMARKS\n"
	 		+ "from v_company_service s\n"
	 		+ "left join m_powerplant pp on s.id=pp.m_service_id\n"
	 		+ "left join m_tariff tr on pp.plant_class_type_code=tr.weg_group_code\n"
	 		+ "left join m_company_meter mm on s.m_company_meter_id=mm.id\n"
	 		+ "left join M_generator mg on pp.id=mg.M_POWERPLANT_ID\n"
	 		+ "left join v_codes vc on mm.meter_make_code=vc.value_code\n"
	 		+ "left join m_feeder MF on s.m_feeder_id =MF.ID\n"
	 		+ "where s.comp_ser_type_code='03' and s.fuel_type_code='SOLAR'  AND s.FLOW_TYPE_CODE like '%"+FlowType+"%' and s.M_SUBSTATION_NAME like '%"+Substation+"%' and s.IS_REC like '%"+isRec+"%' AND s.DISTRICT_NAME like '%"+District+"%' AND pp.STATUS like '%"+status+"%' AND \n"
	 		+ "length(s.\"number\")='12' AND s.m_org_id LIKE '%"+OrgId+"%'AND  CASE when(to_number(to_char(s.commission_date,'MM')) >3) THEN to_number(to_char(s.commission_date,'YYYY')) ELSE to_number(to_char(s.commission_date,'YYYY'))-1 END LIKE '%"+Year+"%' AND\n"
	 		+ "  vc.list_code='METER_MAKE_CODE'\n"
	 		+ "  ) sub1 order by 1 asc\n"
	 		+ "  ) sub1 order by 2 asc";
	return sqlQuery;
}

private static String getMasterWindReport(){
	
	 String OrgId="";
    String Year="";
    String FlowType="";
    String Substation="";
    String isRec="";
    String District="";
    String status="";
	
    if(ipCriteria.containsKey("ip1")) {
        OrgId = ipCriteria.get("ip1");
    }
    if(ipCriteria.containsKey("ip2")){
        Year = ipCriteria.get("ip2");
    }
    if(ipCriteria.containsKey("ip3")){
   	 FlowType = ipCriteria.get("ip3");
    }
    if(ipCriteria.containsKey("ip4")){
   	 Substation = ipCriteria.get("ip4");
    }
    if(ipCriteria.containsKey("ip5")){
   	 isRec = ipCriteria.get("ip5");
    }
    if(ipCriteria.containsKey("ip6")){
   	 District = ipCriteria.get("ip6");
    }
    if(ipCriteria.containsKey("ip7")){
      	 status = ipCriteria.get("ip7");
       }
	
	 String sqlQuery ="select * from (\n"
	 		+ "select * from (\n"
	 		+ "select s.m_org_id ORG_ID,s.m_org_name ORG_NAME,s.\"number\" as SPG_HT_Service_No,s.m_company_name\n"
	 		+ "Generator_Name,to_char(s.commission_date,'dd-mm-yyyy'),mg.NO_OF_UNITS,mg.capacity,s.capacity as total_Capacity,s.district_name,\n"
	 		+ "s.flow_type_code,decode(s.IS_REC,'N','Non-REC','Y','REC'),s.fuel_type_name,s.VOLTAGE_NAME,\n"
	 		+ "pp.plant_class_type_code,tr.rate,tr.weg_group_name,\n"
	 		+ "mm.ct_ratio,mm.pt_ratio,mm.mf,mm.MODEM_NUMBER,mm.METER_NUMBER,\n"
	 		+ "vc.VALUE_DESC as \"METER_MAKE\",\n"
	 		+ "s.M_SUBSTATION_NAME,s.M_FEEDER_NAME,s.M_FEEDER_ID,mf.feeder_line_length,s.TYPE_OF_SS,pp.STATUS,pp.STATUS_REMARKS\n"
	 		+ "from v_company_service s\n"
	 		+ "left join m_powerplant pp on s.id=pp.m_service_id\n"
	 		+ "left join m_tariff tr on pp.plant_class_type_code=tr.weg_group_code\n"
	 		+ "left join m_company_meter mm on s.m_company_meter_id=mm.id\n"
	 		+ "left join M_generator mg on pp.id=mg.M_POWERPLANT_ID\n"
	 		+ "left join v_codes vc on mm.meter_make_code=vc.value_code\n"
	 		+ "left join m_feeder MF on s.m_feeder_id =MF.ID\n"
	 		+ "where s.comp_ser_type_code='03' and s.fuel_type_code='WIND'  AND s.FLOW_TYPE_CODE like '%"+FlowType+"%' and s.M_SUBSTATION_NAME like '%"+Substation+"%' and s.IS_REC like '%"+isRec+"%' AND s.DISTRICT_NAME like '%"+District+"%' AND pp.STATUS like '%"+status+"%' AND \n"
	 		+ "length(s.\"number\")='12' AND s.m_org_id LIKE '%"+OrgId+"%'AND  CASE when(to_number(to_char(s.commission_date,'MM')) >3) THEN to_number(to_char(s.commission_date,'YYYY')) ELSE to_number(to_char(s.commission_date,'YYYY'))-1 END LIKE '%"+Year+"%' AND\n"
	 		+ "  vc.list_code='METER_MAKE_CODE'\n"
	 		+ "  ) sub1 order by 1 asc\n"
	 		+ "  ) sub1 order by 2 asc";
	return sqlQuery;
} 

private static String getGenerationstmtReport(){
	
	String OrgId="";
	String month="";
	String year="";
	String fuel="";
	
	if(ipCriteria.containsKey("ip1")) {
		OrgId=ipCriteria.get("ip1");
	}
	if(ipCriteria.containsKey("ip2")) {
		month=ipCriteria.get("ip2");
	}
	if(ipCriteria.containsKey("ip3")) {
		year=ipCriteria.get("ip3");
	}
	if(ipCriteria.containsKey("ip4")) {
		fuel=ipCriteria.get("ip4");
	}
	
	 String sqlQuery ="select distinct vcs.m_org_id, vcs.m_org_name\n"
	 		+ ",thirdparty.count as thirdparty_count ,thirdparty.capacity as thirdparty,captive.count as captive_count,captive.capacity as captive\n"
	 		+ ",stb.count as stb_count,stb.capacity as stb\n"
	 		+ ",(thirdparty.count + captive.count + stb.count) as total_count\n"
	 		+ ",(thirdparty.capacity + captive.capacity + stb.capacity) as total_capacity\n"
	 		+ ",thirdparty.importgenration as thirdpartyimport,thirdparty.exportgenration as thirdpartyexport,thirdparty.netgenration  as thirdpartynet\n"
	 		+ ",captive.importgenration as captiveimport,captive.exportgenration as captiveexport,captive.netgenration as captivenet\n"
	 		+ ",stb.importgenration as stbimport,stb.exportgenration as stbexport,stb.netgenration as stbnet\n"
	 		+ "from v_company_service vcs\n"
	 		+ "left join\n"
	 		+ "  (\n"
	 		+ "  SELECT ser.M_ORG_ID, ser.m_org_name,COUNT( hdr.M_COMPANY_METER_ID) AS COUNT\n"
	 		+ "  ,SUM(ser.capacity)as capacity,SUM(TGS.TOTAL_IMPORT_GEN) as IMPORTGENRATION\n"
	 		+ "  ,SUM(TGS.TOTAL_EXPORT_GEN) as EXPORTGENRATION\n"
	 		+ "  ,SUM(TGS.NET_GENERATION) as NETGENRATION,SUM(TGS.RKVAH_UNITS) as RKVAH_UNITS FROM T_METER_READING_HDR hdr\n"
	 		+ "   LEFT JOIN v_company_service ser ON hdr.M_COMPANY_METER_ID=ser.M_COMPANY_METER_ID\n"
	 		+ "   LEFT JOIN T_GEN_STMT TGS ON ser.M_COMPANY_METER_ID=TGS.M_COMPANY_METER_ID\n"
	 		+ "   WHERE hdr.READING_MONTH LIKE '%"+month+"%' AND READING_YEAR LIKE '%"+year+"%' and ser.fuel_type_code LIKE '%"+fuel+"%'\n"
	 		+ "   and  tgs.STMT_MONTH LIKE '%"+month+"%' AND tgs.STMT_YEAR LIKE '%"+year+"%' and tgs.DISP_FUEL_TYPE_CODE LIKE '%"+fuel+"%'\n"
	 		+ "   and ser.FLOW_TYPE_CODE in ('THIRD-PARTY')\n"
	 		+ "   GROUP BY ser.M_ORG_ID,ser.m_org_name,ser.FLOW_TYPE_CODE\n"
	 		+ "  )THIRDPARTY\n"
	 		+ "on vcs.m_org_id=thirdparty.m_org_id\n"
	 		+ "left join\n"
	 		+ "  (\n"
	 		+ "   SELECT ser.M_ORG_ID, ser.m_org_name,COUNT( hdr.M_COMPANY_METER_ID) AS COUNT\n"
	 		+ "  ,SUM(ser.capacity)as capacity,SUM(TGS.TOTAL_IMPORT_GEN) as IMPORTGENRATION\n"
	 		+ "  ,SUM(TGS.TOTAL_EXPORT_GEN) as EXPORTGENRATION\n"
	 		+ "  ,SUM(TGS.NET_GENERATION) as NETGENRATION,SUM(TGS.RKVAH_UNITS) as RKVAH_UNITS FROM T_METER_READING_HDR hdr\n"
	 		+ "   LEFT JOIN v_company_service ser ON hdr.M_COMPANY_METER_ID=ser.M_COMPANY_METER_ID\n"
	 		+ "   LEFT JOIN T_GEN_STMT TGS ON ser.M_COMPANY_METER_ID=TGS.M_COMPANY_METER_ID\n"
	 		+ "   WHERE hdr.READING_MONTH LIKE '%"+month+"%' AND READING_YEAR LIKE '%"+year+"%' and ser.fuel_type_code LIKE '%"+fuel+"%'\n"
	 		+ "   and  tgs.STMT_MONTH LIKE '%"+month+"%' AND tgs.STMT_YEAR LIKE '%"+year+"%' and tgs.DISP_FUEL_TYPE_CODE LIKE '%"+fuel+"%'\n"
	 		+ "   and ser.FLOW_TYPE_CODE in ('IS-CAPTIVE')\n"
	 		+ "   GROUP BY ser.M_ORG_ID,ser.m_org_name,ser.FLOW_TYPE_CODE\n"
	 		+ "  )CAPTIVE\n"
	 		+ "on vcs.m_org_id=captive.m_org_id\n"
	 		+ "left join\n"
	 		+ "  (\n"
	 		+ "   SELECT ser.M_ORG_ID, ser.m_org_name,COUNT( hdr.M_COMPANY_METER_ID) AS COUNT\n"
	 		+ "  ,SUM(ser.capacity)as capacity,SUM(TGS.TOTAL_IMPORT_GEN) as IMPORTGENRATION\n"
	 		+ "  ,SUM(TGS.TOTAL_EXPORT_GEN) as EXPORTGENRATION\n"
	 		+ "  ,SUM(TGS.NET_GENERATION) as NETGENRATION,SUM(TGS.RKVAH_UNITS) as RKVAH_UNITS FROM T_METER_READING_HDR hdr\n"
	 		+ "   LEFT JOIN v_company_service ser ON hdr.M_COMPANY_METER_ID=ser.M_COMPANY_METER_ID\n"
	 		+ "   LEFT JOIN T_GEN_STMT TGS ON ser.M_COMPANY_METER_ID=TGS.M_COMPANY_METER_ID\n"
	 		+ "   WHERE hdr.READING_MONTH LIKE '%"+month+"%' AND READING_YEAR LIKE '%"+year+"%' and ser.fuel_type_code LIKE '%"+fuel+"%'\n"
	 		+ "   and  tgs.STMT_MONTH LIKE '%"+month+"%' AND tgs.STMT_YEAR LIKE '%"+year+"%' and tgs.DISP_FUEL_TYPE_CODE LIKE '%"+fuel+"%'\n"
	 		+ "   and ser.FLOW_TYPE_CODE in ('STB')\n"
	 		+ "   GROUP BY ser.M_ORG_ID,ser.m_org_name,ser.FLOW_TYPE_CODE\n"
	 		+ "  )STB\n"
	 		+ "on vcs.m_org_id=stb.m_org_id WHERE vcs.M_ORG_ID LIKE '%"+OrgId+"%' AND LENGTH(vcs.\"number\")=12  AND VCS .FUEL_TYPE_CODE LIKE '%"+fuel+"%' order by vcs.M_ORG_ID";
	 return sqlQuery;
}


}