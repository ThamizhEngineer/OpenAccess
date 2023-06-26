package com.ss.oa.transaction.transcoinvoice;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;


import com.ss.oa.model.transaction.Job;

import com.ss.oa.model.transaction.UnAllocatedRemarks;

import com.ss.oa.transaction.vo.BankingBalance;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.InvoiceHeader;
import com.ss.oashared.model.InvoiceLine;
import com.ss.oashared.model.PrintPayload;

import oracle.jdbc.OracleTypes;

@Controller
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/invoicetransco")
public class TranscoInvoiceService {
	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
	@Resource
	private DataSource dataSource;

	@Value("${printapi.url}")
	private String printUrl;

	@Value("${printapicharge.url}")
	private String printchargeUrl;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	TranscoInvoiceRepository transcorepo;
	Connection conn = null;
	CallableStatement stmt = null;
	@Autowired
	TranscoInvoiceService transcoService;

	@Autowired
	TranscoInvoiceHepler transcoInvoiceHepler;

	@Autowired
	private CommonUtils commonUtils;

	@Autowired
	TranscoInvoiceLineRepository transcoInvoiceLineRepository;

	@GetMapping("/getall")
	private Iterable<InvoiceHeader> fetchAll() {

		return transcorepo.findAll();
	}

	@GetMapping("/all")

	private Iterable<InvoiceHeader> getall(@RequestParam(name = "mOrgId", required = false) String mOrgId,
			@RequestParam(name = "lineMonthYear", required = false) String lineMonthYear,
			@RequestParam(name = "lineYear", required = false) String lineYear,
			@RequestParam(name = "mCompServId", required = false) String mCompServId,
			@RequestParam(name = "invStatus", required = false) String invStatus) throws Exception {

		System.out.println(invStatus);

		System.out.println("came in");
		//String regex = "^0+(?!$)";
		//lineMonth=lineMonth.replaceAll(regex, "");
		String Month;
		Month=CommonUtils.monthConversiontoInteger(lineMonthYear.substring(4,7));
		lineYear=lineMonthYear.substring(11, 15);
		
		//System.out.println("print this  --" + transcorepo.findAll());
		// return transcorepo.findAll();
		return transcorepo.fetchByAdvanceFilter(mOrgId,Month, lineYear, mCompServId, invStatus);

		// return transcorepo.fetchByAdvanceFilter();

	}

	@CrossOrigin(origins = "*")
	@GetMapping("/forwardtoEdc")
	private String forwardToEdc(@RequestParam(name = "Invoicenum", required = false) String invoiceid,
			@RequestParam(name = "month", required = false) String month,
			@RequestParam(name = "year", required = false) String year,
			@RequestParam(name = "mComservid", required = false) String mCompid,
			@RequestParam(name = "UserName", required = false) String UserName) {

		LocalDateTime localDate = LocalDateTime.now();
		InvoiceHeader updateinvoice = transcorepo.findBycombo(mCompid, invoiceid, year, month);
		updateinvoice.setInvStatus("FORWARDED-TO-EDC");
		updateinvoice.setModifiedDate(localDate);
		updateinvoice.setInvForwardDate(localDate);
		updateinvoice.setInvForwardBy(UserName);
		transcorepo.save(updateinvoice);
		return "Successfully invoice forwarded to EDC";
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/confirm")
	private String confirm(@RequestParam(name = "Invoicenum", required = false) String invoiceid,
			@RequestParam(name = "month", required = false) String month,
			@RequestParam(name = "year", required = false) String year,
			@RequestParam(name = "mComservid", required = false) String mCompid,
			@RequestParam(name = "UserName", required = false) String UserName) {

		LocalDateTime localDate = LocalDateTime.now();

		InvoiceHeader updateinvoice = transcorepo.findBycombo(mCompid, invoiceid, year, month);
		System.out.println(updateinvoice.getId());
		transcoInvoiceHepler.SendToZenprotal(updateinvoice.getId());
		updateinvoice.setInvStatus("TO BE CONFIRMED");
		updateinvoice.setModifiedDate(localDate);
		updateinvoice.setInvoiceDt(localDate);
		updateinvoice.setInvApprovedDate(localDate);
		updateinvoice.setInvApprovedBy(UserName);
		transcorepo.save(updateinvoice);
		System.out.println("Finished");
		return "Invoice Successfully Confirmed";
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/buklinvoiceforEdc")
	private String BulkinvoiceForwardEdc(@RequestBody List<String> invoiclist,
			@RequestParam(name = "username", required = false) String username) throws Exception {
		System.out.println("came in sdfdsfdf");
		System.out.println(invoiclist.get(0));
		for (int i = 0; i <= invoiclist.size() - 1; i++) {
			InvoiceHeader bulkinvoice = transcorepo.findbyId(invoiclist.get(i));
			transcoService.forwardToEdc(bulkinvoice.getId(), bulkinvoice.getLineMonth(), bulkinvoice.getLineYear(),
					bulkinvoice.getmCompServId(), username);

		}
		return "Successfully invoices forwarded to EDC";

	}

	@CrossOrigin(origins = "*")
	@PutMapping("/buklinvoiceconfirm")
	private String BulkinvoiceConfirms(@RequestBody List<String> invoiclist,
			@RequestParam(name = "username", required = false) String username) {

		for (int i = 0; i <= invoiclist.size() - 1; i++) {
			//transcoInvoiceHepler.SendToZenprotal(invoiclist.get(i));
			InvoiceHeader bulkinvoice = transcorepo.findbyId(invoiclist.get(i));
			System.out.println(bulkinvoice);
			transcoService.confirm(bulkinvoice.getId(), bulkinvoice.getLineMonth(), bulkinvoice.getLineYear(),
					bulkinvoice.getmCompServNo(), username);

		}
		return "Invoices Successfully Confirmed";

	}

	@GetMapping("/forDetail")
	private InvoiceHeader forDetails(@RequestParam(name = "lineMonth", required = false) String lineMonth,
			@RequestParam(name = "lineYear", required = false) String lineYear,
			@RequestParam(name = "mCompServId", required = false) String mCompServId,
			@RequestParam(name = "invid", required = false) String invid) {
		System.out.println("Came for detail");
		System.out.println(commonUtils.convert(25444));

		InvoiceHeader fortotalwords = transcorepo.findBycombo(mCompServId, invid, lineYear, lineMonth);
		//fortotalwords.setTotalinwords(commonUtils.convert(fortotalwords.getTotalInvAmt().intValue()));
		
		return fortotalwords;

	}
	
	@GetMapping("/fortotalinwords")
	private String fortotalinwords(@RequestParam(name = "total", required = false) Integer total) {
		
		System.out.println(total);
		return commonUtils.convert(total);
		
	}

	@GetMapping("forChargesDetail")
	private List<InvoiceLine> forDeatilsofcharge(@RequestParam(name = "invid", required = false) String invid) {
		System.out.println(transcoInvoiceLineRepository.findByTInvHdrIds(invid));
		List<InvoiceLine> charges = transcoInvoiceLineRepository.findByTInvHdrIds(invid); 
		//InvoiceLine chagresadd = new InvoiceLine();
		for(int i = 0 ; i<4;i++) {
			InvoiceLine chagresadd = new InvoiceLine();
//			if(i==0) {
//				chagresadd.setItemDesc("BPSC");
//				chagresadd.setItemBeforeTaxAmt(null);
//				charges.add(chagresadd);
//				}
			if(i==3) {
				chagresadd.setItemDesc("Other Charges");
				chagresadd.setItemBeforeTaxAmt(null);
				charges.add(chagresadd);
				}
			if(i==0) {
				chagresadd.setItemDesc("IGST@18%");
				chagresadd.setItemBeforeTaxAmt(null);
				charges.add(chagresadd);
				}
			if(i==1) {
				chagresadd.setItemDesc("CGST@9%");
				for(int j =0 ;j<charges.size();j++) {
					if(charges.get(j).getItemDesc().equals("O&M Charges")) {
						chagresadd.setItemBeforeTaxAmt(charges.get(j).getCgstTaxAmt());	
						}
			        }
				
				
				
				charges.add(chagresadd);
				}
			if(i==2) {
				chagresadd.setItemDesc("SGST@9%");
				for(int j =0 ;j<charges.size();j++) {
					if(charges.get(j).getItemDesc().equals("O&M Charges")) {
						chagresadd.setItemBeforeTaxAmt(charges.get(j).getSgstTaxAmt());	
						}
			        }
				
				charges.add(chagresadd);
				}
			
		}
		
		
		return charges;

	}

	@PutMapping("/sendmail")
	public String sendMailWithAttachment(@RequestBody List<String> invoiclist,
			@RequestParam(name = "username", required = false) String username) throws MessagingException {

		System.out.println("came in zdaddsadsds---"+invoiclist.size());
		// System.out.println(invoiclist.get(0).getCusEmail());
		int i;
		LocalDateTime localDate = LocalDateTime.now();
		try {

			// commonUtils.getFile(printCall("2"));
			for (i = 0; i <= invoiclist.size() - 1; i++) {
				InvoiceHeader bulkinvoice = transcorepo.findbyId(invoiclist.get(i));
				String toEmail = bulkinvoice.getCusEmail();
				String body = "This is email body";
				String subject = "This is email subject";
				String attachment = commonUtils.getFile(printCall(invoiclist.get(i)));
				MimeMessage mimeMessage = javaMailSender.createMimeMessage();
				MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
				mimeMessageHelper.setFrom("oadonotreply@tnebnet.org");
				mimeMessageHelper.setTo(toEmail);
				mimeMessageHelper.setText(body);
				mimeMessageHelper.setSubject(subject);
				System.out.println("done");
				FileSystemResource fileSystemResource = new FileSystemResource(new File(attachment));
				mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
				System.out.println("done1");
				try {
					javaMailSender.send(mimeMessage);
				} catch (MailSendException e) {
					System.out.println(e);

					return "Mail with attachment is Unsuccessfull for " + bulkinvoice.getmCompServNo();

				}

				
				System.out.printf("Mail with attachment sent successfully..");
				bulkinvoice.setInvStatus("FINAL");
				bulkinvoice.setEmailSentBy(username);
				bulkinvoice.setEmailSentDt(localDate);
				transcorepo.save(bulkinvoice);
			}

		}

		catch (Exception e) {
			System.out.println(e);
			return "Mail with attachment is Unsuccessfull";
		}
		return "Mail with attachment sent successfully..";

	}

	@GetMapping("/print/transcoInvoice")
	public ResponseEntity<StreamingResponseBody> printTranscoInvoiceReportService(
			@RequestParam(value = "id", required = false) String invoiceid) throws FileNotFoundException {

		return commonUtils.fetchFileAsStreamResponse(printCall(invoiceid));

	}

	public PrintPayload printCall(String invoiceid) throws OpenAccessException {
		RestTemplate restTemplate = CommonUtils.getTemplate();
		PrintPayload payload = new PrintPayload();
		payload.getFilterCriteria().put("id", invoiceid);
		payload.setName(PrintPayload.PrintTypes.TranscoInvoiceReport);

		String url = printUrl;
		System.out.println("Transco Invoice printer");
		System.out.println(url);
		HttpEntity<PrintPayload> request = new HttpEntity<PrintPayload>(payload, CommonUtils.getHttpHeader("", ""));
		payload = restTemplate.postForObject(url, request, PrintPayload.class);
		System.out.println(payload);
		return payload;
	}

	@GetMapping("/print/transcoInvoiceOMCharges")
	public ResponseEntity<StreamingResponseBody> printTranscoChargeInvoiceReportService(
			@RequestParam(value = "id", required = false) String invoiceid) throws FileNotFoundException {

		return commonUtils.fetchFileAsStreamResponse(printChargeCall(invoiceid));

	}

	public PrintPayload printChargeCall(String invoiceid) throws OpenAccessException {
		RestTemplate restTemplate = CommonUtils.getTemplate();
		PrintPayload payload = new PrintPayload();
		payload.getFilterCriteria().put("id", invoiceid);
		payload.setName(PrintPayload.PrintTypes.TranscoInvoiceChargesReport);

		String url = printchargeUrl;
		System.out.println("Transco Invoice Charges printer");
		System.out.println(url);
		HttpEntity<PrintPayload> request = new HttpEntity<PrintPayload>(payload, CommonUtils.getHttpHeader("", ""));
		payload = restTemplate.postForObject(url, request, PrintPayload.class);
		System.out.println(payload);
		return payload;
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/getall/_internal")
	public Optional<InvoiceHeader> getAllInvoice(@RequestParam(value = "id", required = false) String invoiceid) {
		System.out.println("loaded this");

		return transcorepo.findById(invoiceid);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/invoiceCharge/_internal")
	public List<InvoiceLine> invoicechagesinpdf(@RequestParam(name = "invid", required = false) String invid) {

		return forDeatilsofcharge(invid);

	}

}
