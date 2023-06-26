package com.ss.oa.report.genericreportingservice;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.OpenAccessException;

import com.ss.oa.common.ReportRestService;
import com.ss.oa.report.vo.GenericReportOutput;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.model.PrintPayload;

@RestController
@Scope("prototype")
public class GenericReportingRestService extends ReportRestService {
	@Autowired
	GenericReportingService genericReportingService;
	
	@Autowired
	CommonUtils commonUtils;
	
	@RequestMapping(value = "/generic-report", method = RequestMethod.GET)
	public ResponseEntity<List<GenericReportOutput>> getGenericReportResult(
			@RequestParam(value = "name") String reportName,@RequestParam(value = "ip1", required = false) String ip1,
			@RequestParam(value = "ip2", required = false) String ip2,
			@RequestParam(value = "ip3", required = false) String ip3,
			@RequestParam(value = "ip4", required = false) String ip4,
			@RequestParam(value = "ip5", required = false) String ip5,
			@RequestParam(value = "ip6", required = false) String ip6,
			@RequestParam(value = "ip7", required = false) String ip7,
			@RequestParam(value = "ip8", required = false) String ip8,
			@RequestParam(value = "ip9", required = false) String ip9,
			@RequestParam(value = "ip10", required = false) String ip10) {
		Map<String, String> ipCriteria = new HashMap<String, String>();
		if (ip1 != null && ip1.trim() != "")
			ipCriteria.put("ip1", ip1);
		if (ip2 != null && ip2.trim() != "")
			ipCriteria.put("ip2", ip2);
		if (ip3 != null && ip3.trim() != "")
			ipCriteria.put("ip3", ip3);
		if (ip4 != null && ip4.trim() != "")
			ipCriteria.put("ip4", ip4);
		if (ip5 != null && ip5.trim() != "")
			ipCriteria.put("ip5", ip5);
		if (ip6 != null && ip6.trim() != "")
			ipCriteria.put("ip6", ip6);
		if (ip7 != null && ip7.trim() != "")
			ipCriteria.put("ip7", ip7);
		if (ip8 != null && ip8.trim() != "")
			ipCriteria.put("ip8", ip8);
		if (ip9 != null && ip9.trim() != "")
			ipCriteria.put("ip9", ip9);
		if (ip10 != null && ip10.trim() != "")
			ipCriteria.put("ip10", ip10);
		System.out.println(reportName);
		System.out.println(ipCriteria);
        System.out.println(ResponseEntity.ok(genericReportingService.getGenericReportResult(reportName, ipCriteria)));
		return ResponseEntity.ok(genericReportingService.getGenericReportResult(reportName, ipCriteria));
	}

	@RequestMapping(value = "/print-report/{name}", method = RequestMethod.GET)
	public ResponseEntity<StreamingResponseBody>
 PrintGenerationStatement(@PathVariable("name") String name,@RequestParam(value = "ip1", required = false) String ip1,
			@RequestParam(value = "ip2", required = false) String ip2,
			@RequestParam(value = "ip3", required = false) String ip3,
			@RequestParam(value = "ip4", required = false) String ip4,
			@RequestParam(value = "ip5", required = false) String ip5,
			@RequestParam(value = "ip6", required = false) String ip6,
			@RequestParam(value = "ip7", required = false) String ip7,
 			@RequestParam(defaultValue = "pdf") String fileType)
			throws IOException, FileNotFoundException {
		Map<String, String> ipCriteria = new HashMap<String, String>();
		if (ip1 != null && ip1.trim() != "")
			ipCriteria.put("ip1", ip1);
		if (ip2 != null && ip2.trim() != "")
			ipCriteria.put("ip2", ip2);
		if (ip3 != null && ip3.trim() != "")
			ipCriteria.put("ip3", ip3);
		if (ip4 != null && ip4.trim() != "")
			ipCriteria.put("ip4", ip4);
		if (ip5 != null && ip5.trim() != "")
			ipCriteria.put("ip5", ip5);
		if (ip6 != null && ip6.trim() != "")
			ipCriteria.put("ip6", ip6);
		if (ip7 != null && ip7.trim() != "")
			ipCriteria.put("ip7", ip7);
		
		PrintPayload payload = new PrintPayload();
		payload.setFileExtension(fileType);
//		if(name.equals("TnercNetGenerationReport")) {
//			payload.setName(PrintPayload.PrintTypes.TnercNetGenerationReport);
//		}
//		if(name.equals("TnercEnergySummaryReport")) {
//			payload.setName(PrintPayload.PrintTypes.TnercEnergySummaryReport);
//		}
		if(name.equals("EnergyAllotmentReport"))
		{
			payload.setName(PrintPayload.PrintTypes.EnergyAllotmentReport);
		}
		else if(name.equals("UnutilisedBankingReport")) {
			payload.setName(PrintPayload.PrintTypes.UnutilisedBankingReport);
		}
		else if(name.equals("GenChargesAllocToHtReport")) {
			payload.setName(PrintPayload.PrintTypes.GenChargesAllocToHtReport);
		}
		else if(name.equals("UnutilisedBankingMarNew")) {
			payload.setName(PrintPayload.PrintTypes.UnutilisedBankingMarNew);
		}
		else if(name.equals("UnutilisedBankingMar2020")) {
			payload.setName(PrintPayload.PrintTypes.UnutilisedBankingMar2020);
		}
		else if(name.equals("ProgressReportNew")) {
			payload.setName(PrintPayload.PrintTypes.ProgressReportNew);
		}
		else if(name.equals("SaleToBoardLedgerReport")) {
			payload.setName(PrintPayload.PrintTypes.SaleToBoardLedgerReport);
		}
		else if(name.equals("GeneratorWiseGenerationReport")) {
			payload.setName(PrintPayload.PrintTypes.GeneratorWiseGenerationReport);
		}
		else if(name.equals("ConsumerWiseEnergyAdjustmentOrderReport")) {
			payload.setName(PrintPayload.PrintTypes.ConsumerWiseEnergyAdjustmentOrderReport);
		}
		else if(name.equals("ConsolidateEnergyAdjustmentReport")) {
			payload.setName(PrintPayload.PrintTypes.ConsolidateEnergyAdjustmentReport);
		}
		else if(name.equals("ConsolidateEnergyAdjustedChargesReport")) {
			payload.setName(PrintPayload.PrintTypes.ConsolidateEnergyAdjustedChargesReport);
		}
		else if(name.equals("TnercEnergySummaryReport")) {
			payload.setName(PrintPayload.PrintTypes.TnercEnergySummaryReport);
		}
		else if(name.equals("nilGenerationReport"))
		{
			payload.setName(PrintPayload.PrintTypes.NilGenerationReport);
		}else if(name.equals("unallocatedGenReport"))
		{
			payload.setName(PrintPayload.PrintTypes.UnalloctedGenStmtReport);
		}else if(name.equals("AMR DOWNLODE STATUS REPORT"))
		{
			payload.setName(PrintPayload.PrintTypes.AmrDownloadReport);

		}else if(name.equals("TECHNICAL INFO REPORT"))
		{
			payload.setName(PrintPayload.PrintTypes.TechnicalInfoReport);

		}else if(name.equals("TNERC-BANKING-REPORT09-2022"))
		{
			payload.setName(PrintPayload.PrintTypes.TnercBankingReport092022);

		}else if(name.equals("SOLAR FEEDER EDC WISE REPORT")){
			payload.setName(PrintPayload.PrintTypes.SolarFeederEDCWiseReport);
			
		}else if(name.equals("METER READING SERVICE STATUS REPORT"))
        {
            payload.setName(PrintPayload.PrintTypes.MeterReadingServicesImportStatusReport);

        }else if(name.equals("METER READING SERVICE DETAIL REPORT"))
        {
            payload.setName(PrintPayload.PrintTypes.MonthyProgressReportForTotal);

        }
        else if(name.equals("MASTER SOLAR REPORT"))
        {
            payload.setName(PrintPayload.PrintTypes.MasterSolarReport);
       }
        else if(name.equals("MASTER WIND REPORT"))
        {
            payload.setName(PrintPayload.PrintTypes.MasterWindReport);
       }
        else if(name.equals("GENERATION REPORT"))
        {
            payload.setName(PrintPayload.PrintTypes.GenerationstmtReport);
       }
		else {
			payload.setName(PrintPayload.PrintTypes.UnimportedWegReport);
		}
		payload.getFilterCriteria().put("ip1",ip1);
		payload.getFilterCriteria().put("ip2",ip2);
		payload.getFilterCriteria().put("ip3",ip3);	
		payload.getFilterCriteria().put("ip4",ip4);	
		payload.getFilterCriteria().put("ip5",ip5);	
		payload.getFilterCriteria().put("ip6",ip6);	
		payload.getFilterCriteria().put("ip7",ip7);	
		
		System.out.println(payload);
		return commonUtils.fetchFileAsStreamResponse(genericReportingService.printReport(payload));
	}
	@RequestMapping(value = "/generic-report/_internal", method = RequestMethod.GET)
	public ResponseEntity<List<GenericReportOutput>> getGenericReportResultInternal(
			@RequestParam(value = "name") String reportName, @RequestParam(value = "ip1", required = false) String ip1,
			@RequestParam(value = "ip2", required = false) String ip2,
			@RequestParam(value = "ip3", required = false) String ip3,
			@RequestParam(value = "ip4", required = false) String ip4,
			@RequestParam(value = "ip5", required = false) String ip5,
			@RequestParam(value = "ip6", required = false) String ip6,
			@RequestParam(value = "ip7", required = false) String ip7,
			@RequestParam(value = "ip8", required = false) String ip8,
			@RequestParam(value = "ip9", required = false) String ip9,
			@RequestParam(value = "ip10", required = false) String ip10)throws OpenAccessException{
				return getGenericReportResult(reportName, ip1, ip2, ip3, ip4, ip5, ip6, ip7, ip8, ip9, ip10);
		
	}
}