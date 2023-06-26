package com.ss.oa.oadocumentservice.printer;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.ss.oa.oadocumentservice.repo.GenerationStatementRepo;
import com.ss.oa.oadocumentservice.repo.GenunitsRepo;
import com.ss.oa.oadocumentservice.repo.VCompanyServiceDocrepo;
import com.ss.oa.oadocumentservice.vo.CeeReport;
import com.ss.oa.oadocumentservice.vo.GenerationStatementIdJpa;
import com.ss.oa.oadocumentservice.vo.GenerationStatementJpa;
import com.ss.oa.oadocumentservice.vo.GenericReportOutput;
import com.ss.oa.oadocumentservice.vo.TranscoInvoice;
import com.ss.oa.oadocumentservice.vo.TranscoInvoiceCharges;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.InvoiceHeader;
import com.ss.oashared.model.PrintPayload;
import com.ss.oashared.model.VCompanyService;


@Service
public class TranscoInvoiceOandMChargesPrinter {
	@Value("${file.location}")
	private String fileLocation;

	@Value("${transco-invoic.url}")
	private String dataServiceUrl;

	@Value("${transco-invoic-charges.url}")
	private String dataServiceChargesUrl;

	@Value("${qr.location}")
	private String QrCodepath;
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	GenunitsRepo generationStmtRepo;
	
	@Autowired
	VCompanyServiceDocrepo vcomrepo;

	@Autowired
	private TemplateEngine htmlTemplateEngine;

	private final String TEMPLATE_NAME = "transco-invoice-Charges";
	private final String FILE_EXTENSION = "pdf";

	public PrintPayload process(PrintPayload pl) {
		try {
			pl.setDocId(System.currentTimeMillis() + "");

			pl.setFileExtension(FILE_EXTENSION);
			pl.setFileName(TEMPLATE_NAME + pl.getDocId() + "." + pl.getFileExtension());
			pl.setFileNameToUser(pl.getFileName());
			pl.setDocPath(fileLocation + "/" + TEMPLATE_NAME + "/" + pl.getFileName());

			commonUtils.generatePdf(pl,
					this.htmlTemplateEngine.process(TEMPLATE_NAME, setContext(fetchReportData(pl))));

		} catch (OpenAccessException oae) {
			throw oae;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		return pl;
	}

	public TranscoInvoice fetchReportData(PrintPayload pl) {

		String Url = "?id=" + pl.getFilterCriteria().get("id");

		System.out.println(dataServiceUrl);
		System.out.println(CommonUtils.getTemplate().getForObject(dataServiceUrl + Url, TranscoInvoice.class));
		return (CommonUtils.getTemplate().getForObject(dataServiceUrl + Url, TranscoInvoice.class));

	}

	public Context setContext(TranscoInvoice transcoInvoice) throws WriterException, IOException {
		final Context ctx = new Context(Locale.ENGLISH);
		System.out.println("came here for " + transcoInvoice);
		System.out.println(setChargesContext(transcoInvoice.getId()));

		
		List<TranscoInvoiceCharges> invoiceCharges = setChargesContext(transcoInvoice.getId());
		transcoInvoice.setCusAddress(StringUtils.capitalize(transcoInvoice.getCusAddress().toLowerCase()));
		transcoInvoice.setmSubstationName(StringUtils.capitalize(transcoInvoice.getmSubstationName().toLowerCase()));
		Integer TotalAmount = 0;
		for (int i = 0; i <= invoiceCharges.size() - 1; i++) {
			
			if(invoiceCharges.get(i).getItemBeforeTaxAmt() == null) {
				
				invoiceCharges.get(i).setItemBeforeTaxAmt("0");
				
			}

			if (invoiceCharges.get(i).getItemTotalAmt() != null) {

				TotalAmount = commonUtils.toInt(invoiceCharges.get(i).getItemTotalAmt().toString()) + TotalAmount;
			}
		}
		
		if(transcoInvoice.getCusGst().equals("GST-NOT-REGISTERED")) {
			
			
			transcoInvoice.setTransactiontype("B2C");
			
			
		}
		List<GenerationStatementJpa> genStmtJpa=generationStmtRepo.findByUnits(transcoInvoice.getLineMonth(),transcoInvoice.getLineYear(),transcoInvoice.getmCompServNo());
		
		String rkunits="";
		String netgen="";
		Double penaltyRate=null;
		for(GenerationStatementJpa gendata:genStmtJpa) {
			rkunits=gendata.getRkvahUnits();
			netgen=gendata.getNetGeneration();
			
		}
		Double rk=Double.parseDouble(rkunits);
		Double net=Double.parseDouble(netgen);
		if(rk>=net) {
			penaltyRate=0.25;
		}else {
			 penaltyRate=0.5;
		}
		ctx.setVariable("Rkvaunit", rk);
		ctx.setVariable("penaltyRate", penaltyRate);	
		ctx.setVariable("month",transcoInvoice.getLineMonth());
		ctx.setVariable("year",transcoInvoice.getLineYear());
		ctx.setVariable("comservno",transcoInvoice.getmCompServNo());

		YearMonth yearMonthObject = YearMonth.of(Integer.valueOf(transcoInvoice.getLineYear()),Integer.valueOf(transcoInvoice.getLineMonth()));
		Integer daysInMonth = yearMonthObject.lengthOfMonth();
		System.out.println(daysInMonth);

		
		ctx.setVariable("nofdays",daysInMonth);
		String Totalinwords = commonUtils.convert(TotalAmount);
		System.out.println("total--" + TotalAmount);
		ctx.setVariable("Totalinwords", Totalinwords);
		ctx.setVariable("TotalAmount", TotalAmount);
		ctx.setVariable("transcoInvoice", transcoInvoice);
		ctx.setVariable("capacity", transcoInvoice.getCapacity());
		
		ctx.setVariable("invoiceCharges", invoiceCharges);
		ctx.setVariable("SchedulingCharges", invoiceCharges.stream().filter(scharge -> scharge.getItemDesc().equals("Scheduling Charges")).collect(Collectors.toList()));
		ctx.setVariable("RkPenalty",invoiceCharges.stream().filter(pcharge -> pcharge.getItemDesc().equals("RKvah Penalty")).collect(Collectors.toList()));
		ctx.setVariable("SystemOperation",invoiceCharges.stream().filter(socharge -> socharge.getItemDesc().equals("System Operation Charges")).collect(Collectors.toList()));
		ctx.setVariable("Transmission",invoiceCharges.stream().filter(tcharge -> tcharge.getItemDesc().equals("Transmission Charges")).collect(Collectors.toList()));
		ctx.setVariable("OMCharges",invoiceCharges.stream().filter(omcharge -> omcharge.getItemDesc().equals("O&M Charges")).collect(Collectors.toList()));
		VCompanyService serv=vcomrepo.findByMCompServNumber(transcoInvoice.getmCompServNo());
		System.out.println(serv);
		String isRec="";
		if(serv.getmCompServNumber().equals(transcoInvoice.getmCompServNo())) {
		isRec=serv.getIsRec();
		System.out.println(isRec);
		}
		ctx.setVariable("isRec", isRec);
		// } catch (Exception e) {
		// e.printStackTrace();
		// throw new OpenAccessException(e.getLocalizedMessage());
		// }
		return ctx;
	}

	public List<TranscoInvoiceCharges> setChargesContext(String invid) {

		String Url = "?invid=" + invid;
		System.out.println("Came new here   " + Url);

		return Arrays.asList(
				CommonUtils.getTemplate().getForObject(dataServiceChargesUrl + Url, TranscoInvoiceCharges[].class));
	}


	
	
}
