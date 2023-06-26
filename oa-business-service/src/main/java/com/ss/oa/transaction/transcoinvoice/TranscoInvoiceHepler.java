package com.ss.oa.transaction.transcoinvoice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.model.InvoiceHeader;
import com.ss.oashared.model.PrintPayload;
import com.ss.oashared.model.ZenInvoice;

@Component
public class TranscoInvoiceHepler {

	@Autowired
	TranscoInvoiceRepository transcorepo;

	@Autowired
	ZenInvoice zenInvoice;
	
	@Value("${integrationservice.endpoint.baseurl1}")
	private  String IntegrationUrl;

	public String SendToZenprotal(String invid)  {
		System.out.println("came asd fdfdsfsd ds f " + transcorepo.findbyId(invid));
		LocalDateTime localDate = LocalDateTime.now();
		int priviousmont = localDate.getMonthValue();
		System.out.println(priviousmont);
		InvoiceHeader invoicedetails = transcorepo.findbyId(invid);

		if (invoicedetails.getCusName() != null) {
			zenInvoice.setRecipientLegalName(invoicedetails.getCusName());
		}
		if (invoicedetails.getmCompServNo() != null) {
			zenInvoice.setServiceNo(invoicedetails.getmCompServNo());
		}
		if (invoicedetails.getLineMonth() != null) {
			zenInvoice.setBillMonth(invoicedetails.getLineMonth());
		}
		if (invoicedetails.getLineYear() != null) {
			zenInvoice.setBillYear(invoicedetails.getLineYear());
		}
		if (invoicedetails.getCusGst() != null) {
			zenInvoice.setCustomerGstIn(invoicedetails.getCusGst());
		}
		if (invoicedetails.getDocId() != null) {
			zenInvoice.setDocumentNo(invoicedetails.getDocId());
		}
		if (invoicedetails.getDocumenttype() != null) {
			zenInvoice.setDocumentType(invoicedetails.getDocumenttype());
		}
		if (invoicedetails.getInvoiceDt() != null) {
			DateTimeFormatter Format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			//invoicedetails.setInvoiceDt(invoicedetails.getInvoiceDt().format(Format));
			zenInvoice.setDocumentDate(invoicedetails.getInvoiceDt().format(Format));
		}
		if (invoicedetails.getSubTotalForGst() != null) {
			zenInvoice.setTaxableValue(invoicedetails.getSubTotalForGst().toString());
		}
		if (invoicedetails.getTotalTaxAmt() != null) {
			zenInvoice.setGstTaxRate(invoicedetails.getTotalTaxAmt().toString());
		}
		if (invoicedetails.getHsnsac() != null) {
			zenInvoice.setHsnSac(invoicedetails.getHsnsac());
		}
		if (invoicedetails.getIgstTaxAmt() != null) {
			zenInvoice.setIgstAmount(invoicedetails.getIgstTaxAmt());
		}
		if (invoicedetails.getCgstTaxAmt() != null) {
			zenInvoice.setCgstAmount(invoicedetails.getCgstTaxAmt());
		}
//		if (invoicedetails.getIrn() != null) {
//			zenInvoice.setIrn(invoicedetails.getIrn());
//		}
		if (invoicedetails.getSupStateCode() != null) {
			zenInvoice.setPlaceOfsupply(invoicedetails.getSupStateCode());
		}
//		if (invoicedetails.getSupState() == null) {
//			zenInvoice.setPlaceOfsupply("   ");
//		}
		if (invoicedetails.getQuantity() != null) {
			zenInvoice.setQuantity(invoicedetails.getQuantity());
		}
		if (invoicedetails.getSgstTaxAmt() != null) {
			zenInvoice.setSgstAmount(invoicedetails.getSgstTaxAmt());
		}
		if (invoicedetails.getCusAddress() != null) {
			zenInvoice.setRecipientAddr1(invoicedetails.getCusAddress());
		}
		if (invoicedetails.getmSubstationName() != null) {
			zenInvoice.setRecipientLocality(invoicedetails.getmSubstationName());
		}
		if (invoicedetails.getmSubstationName() == null) {
			zenInvoice.setRecipientLocality("                                                            ");
		}
		if (invoicedetails.getCusPin() != null) {
			zenInvoice.setRecipientPin(invoicedetails.getCusPin());
		}
		if (invoicedetails.getDescription() != null) {
			zenInvoice.setDescription(invoicedetails.getDescription());
		}
		if (invoicedetails.getCusPhNo() != null) {
			zenInvoice.setRecipientPhone(invoicedetails.getCusPhNo());
		}
		if (invoicedetails.getCusEmail() != null) {
			zenInvoice.setRecipientEmail(invoicedetails.getCusEmail());
		}
		
//		if (invoicedetails.getQrCodeReceivedDt() != null) {
//			zenInvoice.setAckDate(invoicedetails.getQrCodeReceivedDt());
//		}
//		if (invoicedetails.getAckno() != null) {
//			zenInvoice.setAckNo(invoicedetails.getAckno());
//		}
		if (invoicedetails.getmOrgName() != null) {
			zenInvoice.setDepartment(invoicedetails.getmOrgName());
		}
		if (invoicedetails.getTotalInvAmt() != null) {
			zenInvoice.setTotalAmount(invoicedetails.getTotalInvAmt().toString());
		}
		if (invoicedetails.getSubTotalForOthers() != null) {
			zenInvoice.setOtherChrg(invoicedetails.getSubTotalForOthers().toString());
		}
		if (invoicedetails.getCess() != null) {
			zenInvoice.setCess(invoicedetails.getCess());
		}
		if (invoicedetails.getCess() == null) {
			zenInvoice.setCess("         ");
		}
		if (invoicedetails.getReversecharge() != null) {
			zenInvoice.setReverseChrg(invoicedetails.getReversecharge());
		}
		if (invoicedetails.getStatuscons() != null) {
			zenInvoice.setStatusCons(invoicedetails.getStatuscons());
		}
//		if (invoicedetails.getInvStatus() != null) {
//			zenInvoice.setStatusEinv(invoicedetails.getInvStatus());
//		}
		if (invoicedetails.getSuplierlocality() != null) {
			zenInvoice.setSuplrLocality(invoicedetails.getSuplierlocality());
		}
		if (invoicedetails.getSupladdr1() != null) {
			zenInvoice.setSuplrAddr1(invoicedetails.getSupladdr1());
		}
		if (invoicedetails.getSuplieremail() != null) {
			zenInvoice.setSuplrEmail(invoicedetails.getSuplieremail());
		}
		if (invoicedetails.getSupName() != null) {
			zenInvoice.setSuplrLegalName(invoicedetails.getSupName());
		}
		if (invoicedetails.getSuplierphone() != null) {
			zenInvoice.setSuplrPhone(invoicedetails.getSuplierphone());
		}
		if (invoicedetails.getSuplierpincode() != null) {
			zenInvoice.setSuplrPinCode(invoicedetails.getSuplierpincode());
		}
		if (invoicedetails.getSupGst() != null) {
			zenInvoice.setTaxPayerGstIn(invoicedetails.getSupGst());
		}
		if (invoicedetails.getUqc() != null) {
			zenInvoice.setUqc(invoicedetails.getUqc());
		}
		if (invoicedetails.getUqc() == null) {
			zenInvoice.setUqc("    ");
		}
		if (invoicedetails.getSupGst() != null) {
			zenInvoice.setTaxPayerGstIn(invoicedetails.getSupGst());
		}
		if (invoicedetails.getTransactiontype() != null) {
			zenInvoice.setTransactionType(invoicedetails.getTransactiontype());
		}
		
		
		if (zenInvoice.getCustomerGstIn()!=null && zenInvoice.getCustomerGstIn().equals("GST-NOT-REGISTERED")) {
			System.out.println("For Gst");
			zenInvoice.setTransactionType("B2C");
			zenInvoice.setCustomerGstIn("URP");
		}
		


		RestTemplate restTemplate = CommonUtils.getTemplate();

		String url = IntegrationUrl+"zen/insertforzen/_internal";
		System.out.println("Transco Invoice printer");
		System.out.println(url);
		HttpEntity<ZenInvoice> request = new HttpEntity<ZenInvoice>(zenInvoice, CommonUtils.getHttpHeader("", ""));
		zenInvoice = restTemplate.postForObject(url, request, ZenInvoice.class);
		System.out.println("printing it --" + zenInvoice);

		return "";
	}

}
