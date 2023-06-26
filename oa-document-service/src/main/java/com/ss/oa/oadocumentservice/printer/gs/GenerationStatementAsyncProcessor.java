package com.ss.oa.oadocumentservice.printer.gs;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import com.ss.oa.oadocumentservice.repo.GenerationStatementRepo;
import com.ss.oa.oadocumentservice.vo.GenerationStatement;
import com.ss.oa.oadocumentservice.vo.GenerationStatementIdJpa;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.doc.DocInfoRepository;
import com.ss.oashared.model.DocInfo;

@Component
public class GenerationStatementAsyncProcessor {

	@Value("${gen-stmt-data.url}")
	private String dataServiceUrl;

	@Value("${tmp-stmt-data.url}")
	private String tmpGsUrl;

	@Value("${gen-stmt-getAll.url}")
	private String getAllgsUrl;

	@Value("${gen-stmt-pageId.url}")
	private String getByPageId;

	@Value("${gen-stmt-pageCount.url}")
	private String gsPageCount;

	@Autowired
	GenerationStatementAsyncBatchProcessor generationStatementAsyncBatchProcessor;

	@Autowired
	DocInfoRepository docInfoRepository;

	@Autowired
	GenerationStatementRepo gsRepo;
	//----------Fetch GS list to generate Pdf

	String commonLogMessage ="GenerationStatment Printer - txnId ";
	
	 
	@Async("asyncThreadPoolTaskExecutor")
	public void triggerGSPdfGeneration(Map<String, String> criteria, String txnId,Model model) throws IOException {
		String month = criteria.get("month");String year=criteria.get("year");String docId = "EMPTY";
		String cm = commonLogMessage+txnId;
		long counts = getGsCount(docId,month,year);
		intiatePdfGeneration(docId, criteria.get("month"), criteria.get("year"), model, counts, txnId);
	}
		
	public void intiatePdfGeneration(String docId,String month,String year,Model model,long counts,String txnId) throws IOException {
		int pageSize = 10;int pageNum = 0;
		do {
				List<GenerationStatementIdJpa> genStmts = fetchData(docId,month,year,model,pageNum,pageSize);
				List<String> ids = genStmts.stream().map(GenerationStatementIdJpa::getId).collect(Collectors.toList());
				System.out.println(counts);
				System.out.println(pageNum);
				generationStatementAsyncBatchProcessor.generatePdfFile(ids, txnId, month, year);
				pageNum++;
				counts = counts-pageSize;
			}
			while(counts>0);
	}

	public List<GenerationStatementIdJpa> fetchData(String docId,String month,String year,Model model,int pageNum,int pageSize) {
		Pageable paging = PageRequest.of(pageNum, pageSize);
        Page<GenerationStatementIdJpa> p = gsRepo.findByDocIdAndStmtMonthAndStmtYear(paging, docId, month, year);
		model.addAttribute("number", p.getNumber());
        model.addAttribute("totalPages", p.getTotalPages());
        model.addAttribute("totalElements", p.getTotalElements());
        model.addAttribute("size", p.getSize());
        model.addAttribute("ids", p.getContent());
		return p.getContent();
	}
	
	public long getGsCount(String docId,String month,String year) {
		RestTemplate restTemplate = new RestTemplate();
		String urlCount = gsPageCount+"?"+"docId="+docId+"&stmtMonth="+month+"&"+"stmtYear="+year;
	    ResponseEntity<String> count  = restTemplate.getForEntity (urlCount, String.class); 
	    long counts = 0;
        counts = Long.parseLong(count.getBody());
		return counts;
	}
	
	// ----------------Regenerate Pdf for GS
	
	public String regeneratePdf(String gsId,String txnId) throws IOException {
			List<GenerationStatement> tempGsList = new ArrayList<GenerationStatement>();
			String url = dataServiceUrl+gsId;
			GenerationStatement gs = CommonUtils.getTemplate().getForObject(url, GenerationStatement.class);
			Optional<DocInfo> doc = docInfoRepository.findById(gs.getDocId());
			if(doc.isPresent()) {
				doc.get().setIsEnabled("Y");
				docInfoRepository.save(doc.get());
			}
			gs.setDocId("EMPTY");
			String month = gs.getStatementMonth();
			String year = gs.getStatementYear();
			System.out.println("month&year-->"+month+"-"+year);
			tempGsList.add(gs);
			List<String> ids = tempGsList.stream().map(GenerationStatement::getId).collect(Collectors.toList());
			generationStatementAsyncBatchProcessor.generatePdfFile(ids, txnId,month,year);			
		return gsId;
	}

	//-----------Delete GS-pdf from fileLocation
	
	public String deleteFile(String txnId) {
		String commonLog ="GenerationStatment Printer - txnId "+txnId;
		List<DocInfo> doc = new ArrayList<DocInfo>();
		doc = docInfoRepository.getEnabled();
		System.out.println("docEnabled-->"+doc);
		System.out.println(commonLog + "  Start-time -->  " +  LocalDateTime.now());
		try {
			for(DocInfo docLoop:doc) {
				if(docLoop.getIsEnabled().equals("Y")) {
					File file = new File(docLoop.getDocPath());
					if(file.delete()) {
						System.out.println("file deleted");
					}
					else {
						System.out.println("file not deleted");
					}
					docInfoRepository.deleteById(docLoop.getId());
				}
			}
			System.out.println(commonLog + "  End-time -->  " +  LocalDateTime.now());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return txnId;
	}
}
