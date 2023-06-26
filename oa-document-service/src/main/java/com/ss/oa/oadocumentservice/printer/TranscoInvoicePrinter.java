package com.ss.oa.oadocumentservice.printer;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

import com.ss.oa.oadocumentservice.vo.CeeReport;
import com.ss.oa.oadocumentservice.vo.GenericReportOutput;
import com.ss.oa.oadocumentservice.vo.TranscoInvoice;
import com.ss.oa.oadocumentservice.vo.TranscoInvoiceCharges;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.InvoiceHeader;
import com.ss.oashared.model.PrintPayload;

@Service
public class TranscoInvoicePrinter {
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
	private TemplateEngine htmlTemplateEngine;

	private final String TEMPLATE_NAME = "transco-invoice";
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
		Boolean QrCheck = null;
		System.out.println("came here for " + transcoInvoice);
		System.out.println(setChargesContext(transcoInvoice.getId()));

		try {
			if (transcoInvoice.getQrCode() != null) {
				String str = transcoInvoice.getQrCode();
				// String str = "";
				// path where we want to get QR Code
				String path = QrCodepath;
				// Encoding charset to be used
				String charset = "UTF-8";
				Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
				// generates QR code with Low level(L) error correction capability
				hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
				// invoking the user-defined method that creates the QR code
				generateQRcode(str, path, charset, hashMap, 175, 200);

				// increase or decrease height and width accodingly
				// prints if the QR code is generated
				QrCheck = true;
				System.out.println("QR Code created successfully.");
			}
			if (transcoInvoice.getQrCode() == null) {
				File fileToDelete = new File(QrCodepath);
				boolean success = fileToDelete.delete();
				System.out.println("QR Code is null");
				QrCheck = false;

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new OpenAccessException(e.getLocalizedMessage());
		}

		// try {
		System.out.println("in context--");
		System.out.println("test" + transcoInvoice.getmOrgId());
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
		String Totalinwords = commonUtils.convert(TotalAmount);
		System.out.println("total--" + TotalAmount);
		ctx.setVariable("Totalinwords", Totalinwords);
		ctx.setVariable("TotalAmount", TotalAmount);
		ctx.setVariable("transcoInvoice", transcoInvoice);
		
		
		ctx.setVariable("invoiceCharges", invoiceCharges);
		// ctx.setVariable("QrCheck", QrCheck);
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

	public void generateQRcode(String data, String path, String charset, Map map, int h, int w)
			throws WriterException, IOException {
//the BitMatrix class represents the 2D matrix of bits  
//MultiFormatWriter is a factory class that finds the appropriate Writer subclass for the BarcodeFormat requested and encodes the barcode with the supplied contents.  
		BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset),
				BarcodeFormat.QR_CODE, w, h);
		MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));
	}

}
