package com.ss.oa.oadocumentservice.printer;

import java.text.DecimalFormat;
import java.util.Arrays;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ss.oa.oadocumentservice.vo.GenericReportOutput;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.PrintPayload;

@Service
public class ConsolidateEnergyAdjustmentReportPrint {

	@Value("${file.location}")
	private String fileLocation;

	@Value("${consolidate-energy-adjustment-report.url}")
	private String dataServiceUrl;

	@Autowired
	CommonUtils commonUtils;

	@Autowired
	private TemplateEngine htmlTemplateEngine;

	private final String TEMPLATE_NAME = "consolidate-energy-adjustment-report";
	private final String FILE_EXTENSION = "pdf";

	public PrintPayload process(PrintPayload pl) {
		try {
			pl.setDocId(System.currentTimeMillis() + "");

			pl.setFileExtension(FILE_EXTENSION);
			pl.setFileName(TEMPLATE_NAME + pl.getDocId() + "." + pl.getFileExtension());
			pl.setFileNameToUser(pl.getFileName());
			pl.setDocPath(fileLocation + "/" + TEMPLATE_NAME + "/" + pl.getFileName());

			commonUtils.generatePdf(pl, this.htmlTemplateEngine.process(TEMPLATE_NAME,
					setContext(fetchReportData(pl), pl.getFilterCriteria())));

		} catch (OpenAccessException oae) {
			throw oae;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		return pl;
	}

	public List<GenericReportOutput> fetchReportData(PrintPayload pl) {
		Map<String, String> ipCriteria = pl.getFilterCriteria();

		String ip1 = ipCriteria.get("ip1");
		System.out.println("123" + ip1);
		String ip2 = ipCriteria.get("ip2");
		String ip3 = ipCriteria.get("ip3");
		String ip4 = ipCriteria.get("ip4");
		String url = "";

		if (ip1 != null && !ip1.isEmpty()) {
			url += "&ip1=" + ip1;
		}
		if (ip2 != null && !ip2.isEmpty()) {
			url += "&ip2=" + ip2;
		}
		if (ip3 != null && !ip3.isEmpty()) {
			url += "&ip3=" + ip3;
		}
		if (ip4 != null && !ip4.isEmpty()) {
			url += "&ip4=" + ip4;
		}

		System.out.println("url with crietria--" + url);

		return Arrays.asList(CommonUtils.getTemplate().getForObject(dataServiceUrl + url, GenericReportOutput[].class));
	}

	public Context setContext(List<GenericReportOutput> ConsolidateEnergyAdjustmentReport,
			Map<String, String> ipCriteria) {
		final Context ctx = new Context(Locale.ENGLISH);

		try {
			System.out.println("in context--");
			System.out.println("test" + ConsolidateEnergyAdjustmentReport);

			String ip1 = ipCriteria.get("ip1");
			ctx.setVariable("serviceNo", ip1);
			System.out.println("serviceNo" + ip1);

			String ip2 = ipCriteria.get("ip2");
			ctx.setVariable("year", ip2);
			System.out.println("year" + ip2);

			ctx.setVariable("ConsolidateEnergyAdjustmentReport", ConsolidateEnergyAdjustmentReport);
			double totalop4 = 0;
			int totalop5 = 0;
			int totalop6 = 0;
			int totalop7 = 0;
			int totalop8 = 0;
			int totalop9 = 0;
			int totalop10 = 0;
			int totalop11 = 0;
			int totalop12= 0;
			int totalop13 = 0;
			int totalop14 = 0;
			int totalop15= 0;
			int totalop16 = 0;
			int totalop17 = 0;
			int totalop18 = 0;
			int totalop19 = 0;
			int totalop20 = 0;
			int totalop21 = 0;
			int totalop22 = 0;
			int totalop23 = 0;
			int totalop24 = 0;
			int totalop25 = 0;
			int totalop26 = 0;
			int totalop27= 0;
			int totalop28 = 0;
			int totalop29 = 0;
			int totalop30 = 0;
			int totalop31 = 0;
			int totalop32 = 0;
			int totalop33 = 0;
			int totalop34 = 0;

			DecimalFormat f = new DecimalFormat("0.00");

			for (GenericReportOutput row : ConsolidateEnergyAdjustmentReport) {
				
				if(row.getOp4().equals("-")) {
					
					row.setOp4("0");
				}

				if (row.getOp4() != null) {
					totalop4 = totalop4 + Double.parseDouble(row.getOp4());
				}
				if (row.getOp5() != null) {
					totalop5 = totalop5 + Integer.parseInt(row.getOp5());
				}
				if (row.getOp6() != null) {
					totalop6 = totalop6 + Integer.parseInt(row.getOp6());
				}
				if (row.getOp7() != null) {
					totalop7 = totalop7 + Integer.parseInt(row.getOp7());
				}
				if (row.getOp8() != null) {
					totalop8 = totalop8 + Integer.parseInt(row.getOp8());
				}
				if (row.getOp9() != null) {
					totalop9 = totalop9 + Integer.parseInt(row.getOp9());
				}
				if (row.getOp10() != null) {
					totalop10 = totalop10 + Integer.parseInt(row.getOp10());
				}
				if (row.getOp11() != null) {
					totalop11 = totalop11 + Integer.parseInt(row.getOp11());
				}
				if (row.getOp12() != null) {
					totalop12 = totalop12 + Integer.parseInt(row.getOp12());
				}
				if (row.getOp13() != null) {
					totalop13 = totalop13 + Integer.parseInt(row.getOp13());
				}
				if (row.getOp14() != null) {
					totalop14 = totalop14 + Integer.parseInt(row.getOp14());
				}
				if (row.getOp15() != null) {
					totalop15 = totalop15 + Integer.parseInt(row.getOp15());
				}
				if (row.getOp16() != null) {
					totalop16 = totalop16 + Integer.parseInt(row.getOp16());
				}
				if (row.getOp17() != null) {
					totalop17 = totalop17 + Integer.parseInt(row.getOp17());
				}
				if (row.getOp18() != null) {
					totalop18 = totalop18 + Integer.parseInt(row.getOp18());
				}
				if (row.getOp19() != null) {
					totalop19 = totalop19 + Integer.parseInt(row.getOp19());
				}
				if (row.getOp20() != null) {
					totalop20 = totalop20 + Integer.parseInt(row.getOp20());
				}
				if (row.getOp21() != null) {
					totalop21 = totalop21 + Integer.parseInt(row.getOp21());
				}
				if (row.getOp22() != null) {
					totalop22 = totalop22 + Integer.parseInt(row.getOp22());
				}
				if (row.getOp23() != null) {
					totalop23 = totalop23 + Integer.parseInt(row.getOp23());
				}
				if (row.getOp24() != null) {
					totalop24 = totalop24 + Integer.parseInt(row.getOp24());
				}
				if (row.getOp25() != null) {
					totalop25 = totalop25 + Integer.parseInt(row.getOp25());
				}
				if (row.getOp26() != null) {
					totalop26 = totalop26 + Integer.parseInt(row.getOp26());
				}
				if (row.getOp27() != null) {
					totalop27 = totalop27 + Integer.parseInt(row.getOp27());
				}
				if (row.getOp28() != null) {
					totalop28 = totalop28 + Integer.parseInt(row.getOp28());
				}
				if (row.getOp29() != null) {
					totalop29 = totalop29 + Integer.parseInt(row.getOp29());
				}
				if (row.getOp30() != null) {
					totalop30 = totalop30 + Integer.parseInt(row.getOp30());
				}
				if (row.getOp31() != null) {
					totalop31 = totalop31 + Integer.parseInt(row.getOp31());
				}
				if (row.getOp32() != null) {
					totalop32 = totalop32 + Integer.parseInt(row.getOp32());
				}
				if (row.getOp33() != null) {
					totalop33 = totalop33 + Integer.parseInt(row.getOp33());
				}
				if (row.getOp34() != null) {
					totalop34 = totalop34 + Integer.parseInt(row.getOp34());
				}
			}

			ctx.setVariable("totalop4", f.format(totalop4));
			ctx.setVariable("totalop5", totalop5);
			ctx.setVariable("totalop6", totalop6);
			ctx.setVariable("totalop7", totalop7);
			ctx.setVariable("totalop8", totalop8);
			ctx.setVariable("totalop9", totalop9);
			ctx.setVariable("totalop10", totalop10);
			ctx.setVariable("totalop11", totalop11);
			ctx.setVariable("totalop12", totalop12);
			ctx.setVariable("totalop13", totalop13);

			ctx.setVariable("totalop14", totalop14);
			ctx.setVariable("totalop15", totalop15);
			ctx.setVariable("totalop16", totalop16);
			ctx.setVariable("totalop17", totalop17);
			ctx.setVariable("totalop18", totalop18);
			ctx.setVariable("totalop19", totalop19);
			ctx.setVariable("totalop20", totalop20);
			ctx.setVariable("totalop21", totalop21);
			ctx.setVariable("totalop22", totalop22);
			ctx.setVariable("totalop23", totalop23);

			ctx.setVariable("totalop24", totalop24);
			ctx.setVariable("totalop25", totalop25);
			ctx.setVariable("totalop26", totalop26);
			ctx.setVariable("totalop27", totalop27);
			ctx.setVariable("totalop28", totalop28);
			ctx.setVariable("totalop29", totalop29);
			ctx.setVariable("totalop30", totalop30);
			ctx.setVariable("totalop31", totalop31);
			ctx.setVariable("totalop32", totalop32);
			ctx.setVariable("totalop33", totalop33);
			ctx.setVariable("totalop34", totalop34);

		} catch (Exception e) {
			e.printStackTrace();
			throw new OpenAccessException(e.getLocalizedMessage());
		}
		return ctx;
	}
}
