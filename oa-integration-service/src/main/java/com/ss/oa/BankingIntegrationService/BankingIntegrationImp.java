package com.ss.oa.BankingIntegrationService;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.ss.oa.BankingIntegrationService.model.BankingRequest;
import com.ss.oa.BankingIntegrationService.model.BankingResponse;
import com.ss.oa.BankingIntegrationService.model.BankingSaveResponse;
import com.ss.oa.BankingIntegrationService.model.BankingSavrResponseImp;
import com.ss.oa.vo.McMeterReading;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.BankingIntegration;
import com.ss.oashared.model.InvoiceHeader;
import com.ss.oashared.model.PrintPayload;
import com.ss.oashared.model.TranscoInvoiceRecipt;

@RestController
@RequestMapping("/Bank")
public class BankingIntegrationImp {

	@Autowired
	BankingIntegrationRepository bankingIntegrationRepository;
	@Resource
	@Qualifier("appJdbc")
	private JdbcOperations jdbcOperationsAppdb;

	@Value("${ReciptData.url}")
	private String printUrl;

	@Autowired
	private JavaMailSender javaMailSender;

	// List a;
	@PostMapping("/validate")
	public Object validate(@RequestBody BankingRequest validator) {

		System.out.println(validator);

		String Sql = "Select * from T_INVOICE_HDR where VIRTUALACCOUNTNO = '" + validator.getVirtualAccountNo()
				+ "' AND PAYMENTRECIV ='N' ORDER BY LINE_YEAR,LINE_MONTH ASC fetch first 1 row only";

		List<InvoiceHeader> BankValid = jdbcOperationsAppdb.query(Sql, new InvoiceMapper());
		BankingResponse bankingResponse = new BankingResponse();
//		 
//		 System.out.println(BankValid.get(0).getInvFromDt());
//		 System.out.println(validator.getTranAmt());
//		 System.out.println(BankValid.get(0).getTotalInvAmt().toString());
//		 System.out.println(BankValid.get(0).getViritualAccno().equals(validator.getVirtualAccountNo()));

		if (!BankValid.isEmpty()) {
			// String string = validator.getTranAmt().toString();
			if (BankValid.get(0).getViritualAccno().equals(validator.getVirtualAccountNo())
					&& validator.getTranAmt().equals(BankValid.get(0).getTotalInvAmt().toString())) {

				bankingResponse.setStatus("Success");
				return bankingResponse;
			} else {
				bankingResponse.setStatus("Failed");
				return bankingResponse;

			}

		}

		bankingResponse.setStatus("Failed");
		bankingResponse.setResult(validator.getUtr());
		bankingResponse.setReceiptNo("null");
		return bankingResponse;

	}

	final class InvoiceMapper implements RowMapper<InvoiceHeader> {

		public InvoiceMapper() {
			super();
		}

		@Override
		public InvoiceHeader mapRow(ResultSet resultSet, int rownum) throws SQLException {
			InvoiceHeader invoiceheader = new InvoiceHeader();

			invoiceheader.setId(resultSet.getString("ID"));
			invoiceheader.setmOrgId(resultSet.getString("M_ORG_ID"));
			invoiceheader.setmOrgName(resultSet.getString("M_ORG_NAME"));
			invoiceheader.setInvCode(resultSet.getString("INVCODE"));
			invoiceheader.setInvoiceType(resultSet.getString("INVOICETYPE"));
			invoiceheader.setCommDt(resultSet.getString("COMMDT"));

			invoiceheader.setLineYear(resultSet.getString("LINE_YEAR"));
			invoiceheader.setLineMonth(resultSet.getString("LINE_MONTH"));
			invoiceheader.setCusName(resultSet.getString("CUSNAME"));
			invoiceheader.setCusEmail(resultSet.getString("CUSEMAIL"));
			invoiceheader.setCusPhNo(resultSet.getString("CUSPHNO"));
			invoiceheader.setCusState(resultSet.getString("CUSSTATE"));
			invoiceheader.setCusAddress(resultSet.getString("CUSADDRESS"));
			invoiceheader.setCusPin(resultSet.getString("CUSPIN"));
			invoiceheader.setCusPan(resultSet.getString("CUSPAN"));
			invoiceheader.setCusStateCode(resultSet.getString("CUSSTATECODE"));
			invoiceheader.setAgmtType(resultSet.getString("AGMTTYPE"));
			invoiceheader.setCusGst(resultSet.getString("CUSGST"));
			invoiceheader.setmCompanyId(resultSet.getString("M_COMPANY_ID"));
			invoiceheader.setmCompServId(resultSet.getString("M_COMP_SERV_ID"));
			invoiceheader.setmCompServNo(resultSet.getString("M_COMP_SERV_NO"));
			invoiceheader.setSupName(resultSet.getString("SUPNAME"));
			invoiceheader.setmSubstationId(resultSet.getString("M_SUBSTATION_ID"));
			invoiceheader.setmSubstationCode(resultSet.getString("M_SUBSTATION_CODE"));
			invoiceheader.setmSubstationName(resultSet.getString("M_SUBSTATION_NAME"));
			invoiceheader.setEeOperation(resultSet.getString("EEOPERATION"));
			invoiceheader.setRefInvNo(resultSet.getString("REFINVNO"));
			invoiceheader.setSupState(resultSet.getString("SUPSTATE"));
			invoiceheader.setSupStateCode(resultSet.getString("SUPSTATECODE"));
			invoiceheader.setSupPan(resultSet.getString("SUPPAN"));
			invoiceheader.setSupGst(resultSet.getString("SUPGST"));

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
			if (resultSet.getString("INVFROMDT") != null) {
				invoiceheader.setInvFromDt(LocalDateTime.parse(resultSet.getString("INVFROMDT"), formatter));
			}
			if (resultSet.getString("INVTODT") != null) {
				invoiceheader.setInvToDt(LocalDateTime.parse(resultSet.getString("INVTODT"), formatter));
			}
			if (resultSet.getString("AGMTDT") != null) {
				invoiceheader.setAgmtDt(LocalDateTime.parse(resultSet.getString("AGMTDT"), formatter));
			}

			if (resultSet.getString("AMENDT") != null) {
				invoiceheader.setAmenDt(LocalDateTime.parse(resultSet.getString("AMENDT"), formatter));
			}

			invoiceheader.setAgmtDuration(resultSet.getString("AGMTDURATION"));
			invoiceheader.setSubTotalForGst(resultSet.getBigDecimal("SUBTOTALFORGST"));
			invoiceheader.setSubTotalForOthers(resultSet.getBigDecimal("SUBTOTALFOROTHERS"));
			invoiceheader.setIgstTaxPer(resultSet.getBigDecimal("IGSTTAXPER"));
			invoiceheader.setCgstTaxPer(resultSet.getBigDecimal("CGSTTAXPER"));
			invoiceheader.setSgstTaxPer(resultSet.getBigDecimal("SGSTTAXPER"));
			invoiceheader.setSgstTaxAmt(resultSet.getBigDecimal("SGSTTAXAMT"));
			invoiceheader.setIgstTaxAmt(resultSet.getBigDecimal("IGSTTAXAMT"));
			invoiceheader.setCgstTaxAmt(resultSet.getBigDecimal("CGSTTAXAMT"));
			invoiceheader.setTotalTaxAmt(resultSet.getBigDecimal("TOTALTAXAMT"));
			invoiceheader.setTotalInvAmt(resultSet.getBigDecimal("TOTALINVAMT"));
			invoiceheader.setSubtotalothers(resultSet.getBigDecimal("SUBTOTALOTHERS"));
			invoiceheader.setInvAmtInWords(resultSet.getString("INVAMTINWORDS"));
			invoiceheader.setBankName(resultSet.getString("BANKNAME"));
			invoiceheader.setBankBranch(resultSet.getString("BANKBRANCH"));
			invoiceheader.setBankIfscCode(resultSet.getString("BANKIFSCCODE"));
			invoiceheader.setBankAccNo(resultSet.getString("BANKACCNO"));
			invoiceheader.setCreatedBy(resultSet.getString("CREATEDBY"));

			if (resultSet.getString("CREATEDDATE") != null) {
				invoiceheader.setCreatedDate(LocalDateTime.parse(resultSet.getString("CREATEDDATE"), formatter));
			}

			invoiceheader.setModifiedBy(resultSet.getString("MODIFIEDBY"));

			if (resultSet.getString("MODIFIEDDATE") != null) {
				invoiceheader.setModifiedDate(LocalDateTime.parse(resultSet.getString("MODIFIEDDATE"), formatter));
			}

			invoiceheader.setPdfDocId(resultSet.getString("PDFDOCID"));
			invoiceheader.setDocId(resultSet.getString("DOCID"));
			invoiceheader.setInvStatus(resultSet.getString("INVSTATUS"));
			invoiceheader.setInvCreatedBy(resultSet.getString("INVCREATEDBY"));

			if (resultSet.getString("INVCREATEDDATE") != null) {
				invoiceheader.setInvCreatedDate(LocalDateTime.parse(resultSet.getString("INVCREATEDDATE"), formatter));
			}
			if (resultSet.getString("INVFORWARDDATE") != null) {
				invoiceheader.setInvForwardDate(LocalDateTime.parse(resultSet.getString("INVFORWARDDATE"), formatter));
			}

			invoiceheader.setInvForwardBy(resultSet.getString("INVFORWARDBY"));
			if (resultSet.getString("INVAPPROVEDDATE") != null) {
				invoiceheader
						.setInvApprovedDate(LocalDateTime.parse(resultSet.getString("INVAPPROVEDDATE"), formatter));
			}

			invoiceheader.setInvApprovedBy(resultSet.getString("INVAPPROVEDBY"));

			if (resultSet.getString("ZENPOSTEDDATE") != null) {
				invoiceheader.setZenPostedDate(LocalDateTime.parse(resultSet.getString("ZENPOSTEDDATE"), formatter));
			}

			invoiceheader.setIsEmailSent(resultSet.getString("ISEMAILSENT"));

			if (resultSet.getString("EMAILSENTDT") != null) {
				invoiceheader.setEmailSentDt(LocalDateTime.parse(resultSet.getString("EMAILSENTDT"), formatter));
			}
			invoiceheader.setEmailSentBy(resultSet.getString("EMAILSENTBY"));
			invoiceheader.setQrCode(resultSet.getString("QRCODE"));

			if (resultSet.getString("QRCODERECEIVEDDT") != null) {
				invoiceheader
						.setQrCodeReceivedDt(LocalDateTime.parse(resultSet.getString("QRCODERECEIVEDDT"), formatter));
			}
			invoiceheader.setTransactiontype(resultSet.getString("TRANSACTIONTYPE"));
			invoiceheader.setDocumenttype(resultSet.getString("DOCUMENTTYPE"));
			invoiceheader.setHsnsac(resultSet.getString("HSNSAC"));
			invoiceheader.setDescription(resultSet.getString("DESCRIPTI0N"));
			invoiceheader.setUqc(resultSet.getString("UQC"));
			invoiceheader.setQuantity(resultSet.getString("QUANTITY"));
			invoiceheader.setCess(resultSet.getString("CESS"));
			if (resultSet.getString("REVERSECHRG") != null) {
				invoiceheader.setReversecharge(resultSet.getString("REVERSECHRG").charAt(0));
			}
			invoiceheader.setSupladdr1(resultSet.getString("SUPLRADDR1"));
			invoiceheader.setSuplierlocality(resultSet.getString("SUPLIERLOCALITY"));
			invoiceheader.setSuplierpincode(resultSet.getString("SUPLRPINCODE"));
			invoiceheader.setSuplierphone(resultSet.getString("SUPLRPHONE"));
			invoiceheader.setSuplieremail(resultSet.getString("SUPLREMAIL"));
			invoiceheader.setIrn(resultSet.getString("ZENIRN"));
			invoiceheader.setAckno(resultSet.getString("ZENACKNO"));
			invoiceheader.setStatuscons(resultSet.getString("STATUSCONS"));
			invoiceheader.setViritualAccno(resultSet.getString("VIRTUALACCOUNTNO"));
			invoiceheader.setPaymentreciv(resultSet.getString("PAYMENTRECIV").charAt(0));
			invoiceheader.setPaymentDate(resultSet.getString("PAYMENTRECIVDATE"));

			return invoiceheader;
		}

	}

	@PostMapping("/save")
	public Object save(@RequestBody BankingIntegration savedetails) {

		System.out.println(savedetails.getTransactionTimee() + "joined");

		String Sql = "Select * from T_INVOICE_HDR where VIRTUALACCOUNTNO = '" + savedetails.getVirtualAccountNo()
				+ "'AND PAYMENTRECIV ='N' ORDER BY LINE_YEAR,LINE_MONTH ASC fetch first 1 row only";

		List<InvoiceHeader> BankValid = jdbcOperationsAppdb.query(Sql, new InvoiceMapper());
		if (!BankValid.isEmpty()) {
			if (savedetails.getTranAmt().toString().equals(BankValid.get(0).getTotalInvAmt().toString())) {
				String Sql1 = "SELECT T_INVOICE_BANK_RECEPTNO.nextval FROM T_INVOICE_HDR tih WHERE rownum=1";
				System.out.println("Came here");
				List<String> ReciptNo = jdbcOperationsAppdb.query(Sql1, new InvoiceMapperforReciptNo());
				savedetails.setReceiptNo("TNEBOA" + ReciptNo.get(0));
				bankingIntegrationRepository.save(savedetails);
				BankingSaveResponse response = new BankingSaveResponse();
				BankingSavrResponseImp responseinteg = new BankingSavrResponseImp();
				TranscoInvoiceRecipt transcoInvoiceRecipt = new TranscoInvoiceRecipt();
				if (savedetails.getTranAmt() != null) {
					transcoInvoiceRecipt.setAmountPaid(savedetails.getTranAmt().toString());
				}
				transcoInvoiceRecipt.setGeneratorName(BankValid.get(0).getCusName());
				transcoInvoiceRecipt.setGeneratorNo(BankValid.get(0).getmCompServNo());
				transcoInvoiceRecipt.setReciptMonth(BankValid.get(0).getLineMonth());
				transcoInvoiceRecipt.setReciptYear(BankValid.get(0).getLineYear());
				transcoInvoiceRecipt.setReciptNo(savedetails.getReceiptNo());
				transcoInvoiceRecipt.setViritualAccNo(BankValid.get(0).getViritualAccno());
				transcoInvoiceRecipt.setBankTransactionDetails(savedetails.getBanktranid());

				TranscoInvoiceRecipt ReciptMail = MailCall(transcoInvoiceRecipt);
				System.out.println("document path---" + ReciptMail.getDocPath());
				String toEmail = BankValid.get(0).getCusEmail();
				String body = "This is email body";
				String subject = "This is email subject";
				String attachment = ReciptMail.getDocPath();

				MimeMessage mimeMessage = javaMailSender.createMimeMessage();
				MimeMessageHelper mimeMessageHelper;
				try {
					mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

					mimeMessageHelper.setFrom("oadonotreply@tnebnet.org");
					mimeMessageHelper.setTo(toEmail);
					mimeMessageHelper.setText(body);
					mimeMessageHelper.setSubject(subject);
					System.out.println("done");
					FileSystemResource fileSystemResource = new FileSystemResource(new File(attachment));
					mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
				    javaMailSender.send(mimeMessage);
                    System.out.println("Mail Sended--"+BankValid.get(0).getmCompServNo());
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				responseinteg.setUtr(savedetails.getUtr());
				responseinteg.setStatus("Success");
				response.setStatus("Success");
				// responseintegList = null;

				List<BankingSavrResponseImp> responseintegList = new ArrayList<>();

				responseintegList.add(responseinteg);
				response.setRemarks(responseintegList);
				jdbcOperationsAppdb.execute("UPDATE T_INVOICE_HDR SET PAYMENTRECIV ='Y',PAYMENTRECIVDATE ='"
						+ LocalDateTime.now() + "' WHERE VIRTUALACCOUNTNO ='" + BankValid.get(0).getViritualAccno()
						+ "'AND LINE_YEAR='" + BankValid.get(0).getLineYear() + "' AND LINE_MONTH = '"
						+ BankValid.get(0).getLineMonth() + "'");

				return response;

			}

		}

		BankingSaveResponse response = new BankingSaveResponse();
		BankingSavrResponseImp responseinteg = new BankingSavrResponseImp();

		responseinteg.setUtr(savedetails.getUtr());
		responseinteg.setStatus("Data MisMatch");
		response.setStatus(" Failed");
		// responseintegList = null;

		List<BankingSavrResponseImp> responseintegList = new ArrayList<>();

		responseintegList.add(responseinteg);
		response.setRemarks(responseintegList);

		return response;

	}

	final class InvoiceMapperforReciptNo implements RowMapper<String> {

		public InvoiceMapperforReciptNo() {
			super();
		}

		@Override
		public String mapRow(ResultSet resultSet, int rownum) throws SQLException {
			String Recipno = new String();
			Recipno = resultSet.getString("NEXTVAL");
			return Recipno;
		}

	}

	public TranscoInvoiceRecipt MailCall(TranscoInvoiceRecipt transcoInvoiceRecipt) throws OpenAccessException {
		RestTemplate restTemplate = CommonUtils.getTemplate();
		TranscoInvoiceRecipt payload = new TranscoInvoiceRecipt();

		payload = transcoInvoiceRecipt;

		String url = printUrl;
		System.out.println("Transco Invoice printer");
		System.out.println(url);
		HttpEntity<TranscoInvoiceRecipt> request = new HttpEntity<TranscoInvoiceRecipt>(payload,
				CommonUtils.getHttpHeader("", ""));
		payload = restTemplate.postForObject(url, request, TranscoInvoiceRecipt.class);
		System.out.println(payload);
		return payload;
	}

	// Crud operations performed code

	@GetMapping("/get")
	public Iterable<BankingIntegration> getAll() {
		return bankingIntegrationRepository.findAll();
	}

	@CrossOrigin(origins = "*")
	@PutMapping("/update/{virtualAccountNo}")
	public Optional<BankingIntegration> findAllByAccno(@PathVariable(value = "virtualAccountNo") String vaccno,
			@RequestBody List<BankingIntegration> data) throws OpenAccessException {
		bankingIntegrationRepository.saveAll(data);
		return bankingIntegrationRepository.findById(vaccno);

	}

	@CrossOrigin(origins = "*")
	@PostMapping("/add/{utr}")
	public BankingIntegration Add(@RequestBody BankingIntegration data) throws OpenAccessException {
		return bankingIntegrationRepository.save(data);

	}

	@CrossOrigin(origins = "*")
	@DeleteMapping("/del/{utr}")
	public void deleteByUtrNo(@PathVariable(value = "utr") String utr) throws OpenAccessException {
		bankingIntegrationRepository.deleteById(utr);
	}

}
