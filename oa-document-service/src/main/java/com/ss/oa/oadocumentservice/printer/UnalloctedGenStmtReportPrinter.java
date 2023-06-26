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
public class UnalloctedGenStmtReportPrinter {

	@Value("${file.location}")
	private String fileLocation;

	@Value("${unallocated-gen-stmt-report.url}")
	private String dataServiceUrl;

	@Autowired
	CommonUtils commonUtils;

	@Autowired
	private TemplateEngine htmlTemplateEngine;

	private final String TEMPLATE_NAME = "UnalloctedGenStmtReport";
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

	public List<GenericReportOutput> fetchReportData(PrintPayload pl) {

		Map<String, String> ipCriteria = pl.getFilterCriteria();

		String ip1 = ipCriteria.get("ip1");
		String ip2 = ipCriteria.get("ip2");
		String ip3 = ipCriteria.get("ip3");
		String ip4 = ipCriteria.get("ip4");
		String ip5 = ipCriteria.get("ip5");
		String ip6 = ipCriteria.get("ip6");
		String ip7 = ipCriteria.get("ip7");
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
		if (ip5 != null && !ip5.isEmpty()) {
			url += "&ip5=" + ip5;
		}
		if (ip6 != null && !ip6.isEmpty()) {
			url += "&ip6=" + ip6;
		}
		if (ip7 != null && !ip7.isEmpty()) {
			url += "&ip7=" + ip7;
		}

		return Arrays.asList(CommonUtils.getTemplate().getForObject(dataServiceUrl + url, GenericReportOutput[].class));
	}

	public Context setContext(List<GenericReportOutput> UnallocatedGenList) {
		final Context ctx = new Context(Locale.ENGLISH);

		try {
			System.out.println("in context--");

			ctx.setVariable("UnallocatedGenList", UnallocatedGenList);

			double totalop6 = 0;
			double totalop7 = 0;
			double totalop8 = 0;
			double totalop9 = 0;
			double totalop10 = 0;
			double totalop11 = 0;
			double totalop12 = 0;
			double totalop13 = 0;
			double totalop14 = 0;
			double totalop15 = 0;
			double totalop16 = 0;
			DecimalFormat f = new DecimalFormat("0.00");

			for (GenericReportOutput row : UnallocatedGenList) {

				if (row.getOp6() != null) {
					totalop6 = totalop6 + Double.parseDouble(row.getOp6());
				}
				if (row.getOp7() != null) {
					totalop7 = totalop7 + Double.parseDouble(row.getOp7());
				}
				if (row.getOp8() != null) {
					totalop8 = totalop8 + Double.parseDouble(row.getOp8());
				}
				if (row.getOp9() != null) {
					totalop9 = totalop9 + Double.parseDouble(row.getOp9());
				}
				if (row.getOp10() != null) {
					totalop10 = totalop10 + Double.parseDouble(row.getOp10());
				}
				if (row.getOp11() != null) {
					totalop11 = totalop11 + Double.parseDouble(row.getOp11());
				}
				if (row.getOp12() != null) {
					totalop12 = totalop12 + Double.parseDouble(row.getOp12());
				}
				if (row.getOp13() != null) {
					totalop13 = totalop13 + Double.parseDouble(row.getOp13());
				}
				if (row.getOp14() != null) {
					totalop14 = totalop14 + Double.parseDouble(row.getOp14());
				}
				if (row.getOp15() != null) {
					totalop15 = totalop15 + Double.parseDouble(row.getOp15());
				}
				if (row.getOp16() != null) {
					totalop16 = totalop16 + Double.parseDouble(row.getOp16());
				}

			}

			ctx.setVariable("totalop6", f.format(totalop6));
			ctx.setVariable("totalop7", f.format(totalop7));
			ctx.setVariable("totalop8", f.format(totalop8));
			ctx.setVariable("totalop9", f.format(totalop9));
			ctx.setVariable("totalop10", f.format(totalop10));
			ctx.setVariable("totalop11", f.format(totalop11));
			ctx.setVariable("totalop12", f.format(totalop12));
			ctx.setVariable("totalop13", f.format(totalop13));

			ctx.setVariable("totalop14", f.format(totalop14));
			ctx.setVariable("totalop15", f.format(totalop15));
			ctx.setVariable("totalop16", f.format(totalop16));

		} catch (Exception e) {
			e.printStackTrace();
			throw new OpenAccessException(e.getLocalizedMessage());
		}
		return ctx;
	}

}
