package com.ss.oa.transaction.solarLineLoss;


import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.ss.oa.common.TransactionRestService;
import com.ss.oa.transaction.vo.Vcompany;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.PrintPayload;

@Scope("prototype")
@RestController
public class VCompanyRestService extends TransactionRestService {
	
	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
	@Resource
	private DataSource dataSource;

	@Value("${printsolarfeederlineloss.url}")
	private String printUrl;
	
	@Autowired
	private VCompanyDaoService service;
	
	@Autowired
	private CommonUtils commonUtils;
	
	@RequestMapping(value="/getallFeeder", method = RequestMethod.GET)
	public ResponseEntity<List<Vcompany>> getAll(@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "month", required = false) String month,@RequestParam(value = "year", required = false) String year
			)
	{
try {
			
			Map<String,String> criteria = new HashMap<String,String>();
			
			if(orgId!=null) {
				criteria.put("orgId", orgId);
			}
			
			if(month!=null) {
				criteria.put("month", month);
			}
			if(year!=null) {
				criteria.put("year", year);
			}
			
			
			return ResponseEntity.ok(service.getAllFeeder(criteria));
	
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/getallFeeder/_internal", method = RequestMethod.GET)
	public ResponseEntity<List<Vcompany>> getAllPrint(@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "month", required = false) String month,@RequestParam(value = "year", required = false) String year
			)
	{
try {
			
			Map<String,String> criteria = new HashMap<String,String>();
			
			if(orgId!=null) {
				criteria.put("orgId", orgId);
			}
			
			if(month!=null) {
				criteria.put("month", month);
			}
			if(year!=null) {
				criteria.put("year", year);
			}
			
			
			return ResponseEntity.ok(service.getAllFeeder(criteria));
	
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	
	
	@GetMapping("/print/feederlineloss")
	public ResponseEntity<StreamingResponseBody> printTranscoInvoiceReportService(@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "month", required = false) String month,@RequestParam(value = "year", required = false) String year) throws FileNotFoundException {

		return commonUtils.fetchFileAsStreamResponse(printCall(orgId,month,year));

	}

	public PrintPayload printCall(String orgId, String month, String year) throws OpenAccessException {
		RestTemplate restTemplate = CommonUtils.getTemplate();
		PrintPayload payload = new PrintPayload();
		payload.getFilterCriteria().put("orgId", orgId);
		payload.getFilterCriteria().put("month", month);
		payload.getFilterCriteria().put("year", year);
		payload.setName(PrintPayload.PrintTypes.SolarFeederLineLoss);

		String url = printUrl;
		System.out.println("solar feeder line loss printer");
		System.out.println(url);
		HttpEntity<PrintPayload> request = new HttpEntity<PrintPayload>(payload, CommonUtils.getHttpHeader("", ""));
		payload = restTemplate.postForObject(url, request, PrintPayload.class);
		System.out.println(payload);
		return payload;
	}
}
