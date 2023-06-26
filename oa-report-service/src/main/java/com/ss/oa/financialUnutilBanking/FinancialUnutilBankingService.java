package com.ss.oa.financialUnutilBanking;

import java.io.FileNotFoundException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ss.oa.report.vo.FinancialUnutilBanking;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.PrintPayload;

import oracle.jdbc.OracleTypes;

@RestController
@RequestMapping("report/financial-unutil-report")
public class FinancialUnutilBankingService {

	
	@Autowired
	FinancialUnutilBankingRepo financialUnutilBankingRepo;
	
	@Resource
	private DataSource dataSource;
	
	@Autowired
	CommonUtils commonUtils;
	
	@Value("${print.url}")
	private String printUrl;

	@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<FinancialUnutilBanking> getUnutilBankReport(@RequestParam(value="edcNo",required=false)String edcNo,
			@RequestParam(value="month",required=false)String month,
			@RequestParam(value="year",required=false)String year)throws OpenAccessException
	{
		UnutilBankReportData(month,year);
		
		boolean searchByStMonth=false,searchByStYear=false,searchByEdcNo=false,searchByEdcNoAndStMonthAndStYear=false;
		
		if(month!=null&&!month.isEmpty()) {
			searchByStMonth=true;
		}
		if(year!=null&&!year.isEmpty()) {
			searchByStYear=true;
		}
		if(edcNo!=null&&!edcNo.isEmpty()) {
			searchByEdcNo=true;
		}
		
		if((month!=null&&!month.isEmpty())&&(year!=null&&!year.isEmpty())&&edcNo!=null&&!edcNo.isEmpty()) {
			searchByEdcNoAndStMonthAndStYear=true;
		}
		if(searchByEdcNoAndStMonthAndStYear) {
			return financialUnutilBankingRepo.findByEdcNoAndStMonthAndStYear(edcNo,month, year);
		}
		
		if(searchByStMonth) {
			return financialUnutilBankingRepo.findByStMonth(month);
		}
		if(searchByStYear) {
			return financialUnutilBankingRepo.findByStYear(year);
		}
		if(searchByEdcNo) {
			return financialUnutilBankingRepo.findByEdcNo(edcNo);
		}
		
		
		
		return financialUnutilBankingRepo.findAll();
	}
	
	
	
	public String UnutilBankReportData(String month,String year) {
		String unutilBankReportFunction = "{ ?=call FINANCIAL_UNUTILIZED_BANKING_REPORT(?,?)}"; 
		String res= "SUCCESS";
		Connection conn = null;
		CallableStatement stmt = null;
			try {
					conn =  dataSource.getConnection();
					stmt = conn.prepareCall(unutilBankReportFunction);
			        stmt.registerOutParameter(1, OracleTypes.NVARCHAR);
			        stmt.setString(2, month);
			        stmt.setString(3, year);
			        stmt.execute();
					conn.close();

			      
			} catch (Exception e) {
			      e.printStackTrace();
			} finally {
				
				try {
					if((stmt!= null) && (!stmt.isClosed())) {
						stmt.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					if((conn!= null) && (!conn.isClosed())) {
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
				return res;
		}
	
	
	@CrossOrigin(origins = "*")
	@GetMapping("/_internal")
	public Iterable<FinancialUnutilBanking> getUnutilBankInternal(@RequestParam(value="edcNo",required=false)String edcNo,
			@RequestParam(value="month",required=false)String month,
			@RequestParam(value="year",required=false)String year)throws OpenAccessException{
		return getUnutilBankReport(edcNo,month, year);
	}
	
	
	@GetMapping("/print")
	public ResponseEntity<StreamingResponseBody>
  printEnergyAdjOrder(@RequestParam(value="edcNo",required=false)String edcNo,
			@RequestParam(value="month",required=false)String month,
			@RequestParam(value="year",required=false)String year)
			throws FileNotFoundException{
		return  commonUtils.fetchFileAsStreamResponse(printCall(edcNo,month, year));

	}
	
	
	public PrintPayload  printCall(String edcNo,String month,String year) throws OpenAccessException {
		RestTemplate restTemplate = CommonUtils.getTemplate();
		PrintPayload payload = new PrintPayload();
		payload.setName(PrintPayload.PrintTypes.FinancialUnutilBankReport);
		payload.getFilterCriteria().put("edcNo", edcNo);
		payload.getFilterCriteria().put("month", month);
		payload.getFilterCriteria().put("year", year);
		String url=printUrl;
		HttpEntity<PrintPayload> request = new HttpEntity<PrintPayload>(payload, CommonUtils.getHttpHeader("", ""));
		payload  = restTemplate.postForObject(url,request, PrintPayload.class); 
		return payload;
	}
	
	
}
