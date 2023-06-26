package com.ss.oa.oadocumentservice.printer;

import java.text.DecimalFormat;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ss.oa.oadocumentservice.vo.CeeReport;
import com.ss.oa.oadocumentservice.vo.SrcpReport;
import com.ss.oa.oadocumentservice.vo.TempSrcp;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.PrintPayload;

@Service
public class SrcpReportPrinter {

	@Value("${file.location}")
	private String fileLocation;

	@Value("${srcp-report.url}")
	private String dataServiceUrl;

	@Autowired
	CommonUtils commonUtils;

	@Autowired
	private TemplateEngine htmlTemplateEngine;

	private final String TEMPLATE_NAME = "SrcpReport";
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

	public List<SrcpReport> fetchReportData(PrintPayload pl) {

		Map<String, String> ipCriteria = pl.getFilterCriteria();
		String ncesDivisionCode = ipCriteria.get("ncesDivisionCode");
		String month = ipCriteria.get("month");
		String year = ipCriteria.get("year");
		String url = "?dummy=1";

		if (ncesDivisionCode != null && !ncesDivisionCode.isEmpty()) {
			url += "&ncesDivisionCode=" + ncesDivisionCode;
		}
		if (month != null && !month.isEmpty()) {
			url += "&month=" + month;
		}
		if (year != null && !year.isEmpty()) {
			url += "&year=" + year;
		}
		System.out.println("url with crietria--" + url);

		return Arrays.asList(CommonUtils.getTemplate().getForObject(dataServiceUrl + url, SrcpReport[].class));
	}

	public Context setContext(List<SrcpReport> SrcpReportlist) {
//		System.out.println(SrcpReportlist);
		final Context ctx = new Context(Locale.ENGLISH);

		TempSrcp tempSrcp = new TempSrcp();
		TempSrcp tempSrcp1 = new TempSrcp();
		TempSrcp tempSrcp2 = new TempSrcp();
		ArrayList<TempSrcp> tempSrcpList = new ArrayList<TempSrcp>();
		ArrayList<TempSrcp> tempSrcpList1 = new ArrayList<TempSrcp>();
		double totalop3 = 0;
		double totalop4 = 0;
		double totalop5 = 0;
		double totalop6 = 0;
		double totalop7 = 0;
		double totalop8 = 0;
		double totalop9 = 0;
		double totalop10 = 0;
		double totalop11 = 0;
		DecimalFormat f = new DecimalFormat("0.00");

		try {
			for (SrcpReport srpclist : SrcpReportlist) {

				if (srpclist.getInstalledBy() == null) {

					srpclist.setInstalledBy("");
				}
				// System.out.println(srpclist);
				if (srpclist.getInstalledBy().equals("CENTRAL")) {
					if (srpclist.getNcesDivisionCode() != null || !srpclist.getNcesDivisionCode().isEmpty()) {
						if (srpclist.getNcesDivisionCode().equals("NCESTRVL")) {
							tempSrcp1.setTvlTotalCapacity(srpclist.getTotalCapacitySum());
							tempSrcp1.setTvlTotalGeneration(srpclist.getTotalGenerationSum());
							tempSrcp1.setTvlWeg(srpclist.getNoOfWeg());
							totalop3 = totalop3 + tempSrcp1.getTvlWeg();
							totalop6 = totalop6 + tempSrcp1.getTvlTotalCapacity();
							totalop9 = totalop9 + tempSrcp1.getTvlTotalGeneration();

						}
						if (srpclist.getNcesDivisionCode().equals("NCESUDML")) {
							tempSrcp1.setUduTotalCapacity(srpclist.getTotalCapacitySum());
							tempSrcp1.setUduTotalGeneration(srpclist.getTotalGenerationSum());
							tempSrcp1.setUduWeg(srpclist.getNoOfWeg());
							totalop4 = totalop4 + tempSrcp1.getUduWeg();
							totalop7 = totalop7 + tempSrcp1.getUduTotalCapacity();
							totalop10 = totalop10 + tempSrcp1.getUduTotalGeneration();
						}
						tempSrcp1.setInstBy(srpclist.getInstalledBy());
						tempSrcp1.setMonth(srpclist.getMonth());
						tempSrcp1.setYear(srpclist.getYear());
						tempSrcp1.setTtWeg((tempSrcp1.getTvlWeg() != null ? tempSrcp1.getTvlWeg() : 0)
								+ (tempSrcp1.getUduWeg() != null ? tempSrcp1.getUduWeg() : 0));

						tempSrcp1.setTtCapSum((tempSrcp1.getTvlTotalCapacity() != null ? tempSrcp1.getTvlTotalCapacity()
								: 0) + (tempSrcp1.getUduTotalCapacity() != null ? tempSrcp1.getUduTotalCapacity() : 0));
						tempSrcp1.setTtGenSum(
								(tempSrcp1.getTvlTotalGeneration() != null ? tempSrcp1.getTvlTotalGeneration() : 0)
										+ (tempSrcp1.getUduTotalGeneration() != null ? tempSrcp1.getUduTotalGeneration()
												: 0));

					}

				}

				if (srpclist.getInstalledBy().equals("PRIVATE")) {
					if (srpclist.getNcesDivisionCode() != null || !srpclist.getNcesDivisionCode().isEmpty()) {
						if (srpclist.getNcesDivisionCode().equals("NCESTRVL")) {
							tempSrcp.setTvlTotalCapacity(srpclist.getTotalCapacitySum());
							tempSrcp.setTvlTotalGeneration(srpclist.getTotalGenerationSum());
							tempSrcp.setTvlWeg(srpclist.getNoOfWeg());
							totalop3 = totalop3 + tempSrcp.getTvlWeg();
							totalop6 = totalop6 + tempSrcp.getTvlTotalCapacity();
							totalop9 = totalop9 + tempSrcp.getTvlTotalGeneration();
						}
						if (srpclist.getNcesDivisionCode().equals("NCESUDML")) {
							tempSrcp.setUduTotalCapacity(srpclist.getTotalCapacitySum());
							tempSrcp.setUduTotalGeneration(srpclist.getTotalGenerationSum());
							tempSrcp.setUduWeg(srpclist.getNoOfWeg());
							totalop4 = totalop4 + tempSrcp.getUduWeg();
							totalop7 = totalop7 + tempSrcp.getUduTotalCapacity();
							totalop10 = totalop10 + tempSrcp.getUduTotalGeneration();
						}
						tempSrcp.setInstBy(srpclist.getInstalledBy());
						tempSrcp.setMonth(srpclist.getMonth());
						tempSrcp.setYear(srpclist.getYear());
						tempSrcp.setTtWeg((tempSrcp.getTvlWeg() != null ? tempSrcp.getTvlWeg() : 0)
								+ (tempSrcp.getUduWeg() != null ? tempSrcp.getUduWeg() : 0));

						tempSrcp.setTtCapSum((tempSrcp.getTvlTotalCapacity() != null ? tempSrcp.getTvlTotalCapacity()
								: 0) + (tempSrcp.getUduTotalCapacity() != null ? tempSrcp.getUduTotalCapacity() : 0));
						tempSrcp.setTtGenSum(
								(tempSrcp.getTvlTotalGeneration() != null ? tempSrcp.getTvlTotalGeneration() : 0)
										+ (tempSrcp.getUduTotalGeneration() != null ? tempSrcp.getUduTotalGeneration()
												: 0));

						System.out.println("checking.........." + tempSrcp);

					}

					// tempSrcpList.add(tempSrcp);
				}
				if (srpclist.getInstalledBy().equals("TANGEDCO")) {
					if (srpclist.getNcesDivisionCode() != null || !srpclist.getNcesDivisionCode().isEmpty()) {
						if (srpclist.getNcesDivisionCode().equals("NCESTRVL")) {
							tempSrcp2.setTvlTotalCapacity(srpclist.getTotalCapacitySum());
							tempSrcp2.setTvlTotalGeneration(srpclist.getTotalGenerationSum());
							tempSrcp2.setTvlWeg(srpclist.getNoOfWeg());
							totalop3 = totalop3 + tempSrcp2.getTvlWeg();
							totalop6 = totalop6 + tempSrcp2.getTvlTotalCapacity();
							totalop9 = totalop9 + tempSrcp2.getTvlTotalGeneration();
						}
						if (srpclist.getNcesDivisionCode().equals("NCESUDML")) {
							tempSrcp2.setUduTotalCapacity(srpclist.getTotalCapacitySum());
							tempSrcp2.setUduTotalGeneration(srpclist.getTotalGenerationSum());
							tempSrcp2.setUduWeg(srpclist.getNoOfWeg());
							totalop4 = totalop4 + tempSrcp2.getUduWeg();
							totalop7 = totalop7 + tempSrcp2.getUduTotalCapacity();
							totalop10 = totalop10 + tempSrcp2.getUduTotalGeneration();
						}
						tempSrcp2.setInstBy(srpclist.getInstalledBy());
						tempSrcp2.setMonth(srpclist.getMonth());
						tempSrcp2.setYear(srpclist.getYear());
						tempSrcp2.setTtWeg((tempSrcp2.getTvlWeg() != null ? tempSrcp2.getTvlWeg() : 0)
								+ (tempSrcp2.getUduWeg() != null ? tempSrcp2.getUduWeg() : 0));

						tempSrcp2.setTtCapSum((tempSrcp2.getTvlTotalCapacity() != null ? tempSrcp2.getTvlTotalCapacity()
								: 0) + (tempSrcp2.getUduTotalCapacity() != null ? tempSrcp2.getUduTotalCapacity() : 0));
						tempSrcp2.setTtGenSum(
								(tempSrcp2.getTvlTotalGeneration() != null ? tempSrcp2.getTvlTotalGeneration() : 0)
										+ (tempSrcp2.getUduTotalGeneration() != null ? tempSrcp2.getUduTotalGeneration()
												: 0));

					}

					// tempSrcpList.add(tempSrcp2);
				}

				// System.out.println("tmplist--"+tempSrcpList);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			System.out.println("in context--");
			// System.out.println(tempSrcp);
			tempSrcpList.add(tempSrcp1);
			tempSrcpList.add(tempSrcp2);
			tempSrcpList.add(tempSrcp);
			totalop5 = tempSrcp1.getTtWeg() + tempSrcp.getTtWeg() + tempSrcp2.getTtWeg();
			totalop8 = tempSrcp1.getTtCapSum() + tempSrcp.getTtCapSum() + tempSrcp2.getTtCapSum();
			totalop11 = tempSrcp1.getTtGenSum() + tempSrcp.getTtGenSum() + tempSrcp2.getTtGenSum();
			ctx.setVariable("totalop3", f.format(totalop3));
			ctx.setVariable("totalop4", f.format(totalop4));
			ctx.setVariable("totalop5", f.format(totalop5));
			ctx.setVariable("totalop6", f.format(totalop6));
			ctx.setVariable("totalop7", f.format(totalop7));
			ctx.setVariable("totalop8", f.format(totalop8));
			ctx.setVariable("totalop9", f.format(totalop9));
			ctx.setVariable("totalop10", f.format(totalop10));
			ctx.setVariable("totalop11", f.format(totalop11));
			ctx.setVariable("srcpReportlist", tempSrcpList);

		} catch (Exception e) {
			e.printStackTrace();
			throw new OpenAccessException(e.getLocalizedMessage());
		}
		return ctx;
	}

}
