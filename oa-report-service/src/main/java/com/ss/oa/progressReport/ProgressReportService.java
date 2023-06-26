package com.ss.oa.progressReport;

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

import com.ss.oa.report.vo.ProgressReport;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.PrintPayload;

import oracle.jdbc.OracleTypes;

@RestController
@RequestMapping("report/progress-report")
public class ProgressReportService {
	
	@Autowired
	ProgressReportRepository progressReportRepository;
	@Resource
	private DataSource dataSource;
	@Autowired
	CommonUtils commonUtils;
	
	@Value("${print.url}")
	private String printUrl;
	

	@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<ProgressReport> getProgressReport(@RequestParam(value="month",required=false)String month,
			@RequestParam(value="year",required=false)String year)throws OpenAccessException
	{
		progressReportData(month,year);
		
		boolean searchByMonth=false,searchByYear=false,searchByMonthAndYear=false;
		
		if(month!=null&&!month.isEmpty()) {
			searchByMonth=true;
		}
		if(year!=null&&!year.isEmpty()) {
			searchByYear=true;
		}
		
		if((month!=null&&!month.isEmpty())&&(year!=null&&!year.isEmpty())) {
			searchByMonthAndYear=true;
		}
		if(searchByMonthAndYear) {
			return progressReportRepository.findByMonthAndYear(month, year);
		}
		
		if(searchByMonth) {
			return progressReportRepository.findByMonth(month);
		}
		if(searchByYear) {
			return progressReportRepository.findByYear(year);
		}
		
		
		
		return progressReportRepository.findAll();
	}
	
	
	
	public String progressReportData(String month,String year) {
		String progressReportFunction = "{ ?=call PROGRESS_REPORT_RR(?,?)}"; 
		String res= "SUCCESS";
		Connection conn = null;
		CallableStatement stmt = null;
				try {
					conn =  dataSource.getConnection();
					stmt = conn.prepareCall(progressReportFunction);
			        stmt.registerOutParameter(1, OracleTypes.NVARCHAR);
			        stmt.setString(2, month);
			        stmt.setString(3, year);
			        stmt.execute();
			        stmt.close();
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
	public Iterable<ProgressReport> getProgressInternal(@RequestParam(value="month",required=false)String month,
			@RequestParam(value="year",required=false)String year)throws OpenAccessException{
		return getProgressReport(month, year);
	}
	
	
	@GetMapping("/print")
	public ResponseEntity<StreamingResponseBody>
  printEnergyAdjOrder(@RequestParam(value="month",required=false)String month,
			@RequestParam(value="year",required=false)String year)
			throws FileNotFoundException{
		return  commonUtils.fetchFileAsStreamResponse(printCall(month, year));

	}
	
	
	public PrintPayload  printCall(String month,String year) throws OpenAccessException {
		RestTemplate restTemplate = CommonUtils.getTemplate();
		PrintPayload payload = new PrintPayload();
		payload.setName(PrintPayload.PrintTypes.ProgressReport);
		payload.getFilterCriteria().put("month", month);
		payload.getFilterCriteria().put("year", year);
		String url=printUrl;
		HttpEntity<PrintPayload> request = new HttpEntity<PrintPayload>(payload, CommonUtils.getHttpHeader("", ""));
		payload  = restTemplate.postForObject(url,request, PrintPayload.class); 
		return payload;
	}
	
}
