package com.ss.oa.transaction.generationstatementjpa;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.transaction.jpamodel.GenerationStatementIdJpa;
import com.ss.oa.transaction.jpamodel.GenerationStatementJpa;

@RestController
@RequestMapping("/gs-jpa")
public class GenerationStatementJpaService {
	
	@Autowired
	GenerationStatementJpaRepo gsJpaRepo;
	
	@CrossOrigin(origins = "*")
	@GetMapping("/count")
	public Iterable<GenerationStatementJpa> gsListCount(Model model,Pageable pageable,
			@RequestParam(value = "stmtMonth", required = false) String stmtMonth,
			@RequestParam(value = "stmtYear", required = false) String stmtYear){
		Page<GenerationStatementJpa> pages=gsPage(pageable,stmtMonth,stmtYear);
		model.addAttribute("number", pages.getNumber());
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("totalElements", pages.getTotalElements());
        model.addAttribute("size", pages.getSize());
        model.addAttribute("esisummary", pages.getContent());
        
        return pages;
		
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/gen-id/{id}")
	public Optional<GenerationStatementJpa> gsListCount(@PathVariable("id") String id){
		
        
        return gsJpaRepo.findById(id);	
	}
	
	
	@CrossOrigin(origins = "*")
	@GetMapping("/gen-id")
	public Iterable<GenerationStatementIdJpa> gsFindById(Model model,
			//Pageable pageable,
			@RequestParam(value = "pageNum", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "docId", required = false) String docId,
			@RequestParam(value = "stmtMonth", required = false) String stmtMonth,
			@RequestParam(value = "stmtYear", required = false) String stmtYear){ 
		
		Pageable paging = PageRequest.of(pageNo, pageSize);
		
		Page<GenerationStatementIdJpa> pages=gsPages(paging,docId,stmtMonth,stmtYear);
		model.addAttribute("number", pages.getNumber());
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("totalElements", pages.getTotalElements());
        model.addAttribute("size", pages.getSize());
        model.addAttribute("esisummary", pages.getContent());
        return pages.getContent();

	}  
	
	
	public Page<GenerationStatementIdJpa> gsPages(Pageable pageable,String docId,String stmtMonth,String stmtYear){
		return gsJpaRepo.findByDocIdAndStmtMonthAndStmtYear(pageable, docId, stmtMonth, stmtYear);
//		return gsJpaRepo.findByStmtMonthAndStmtYear(pageable, stmtMonth, stmtYear);
		
	}
	
	public Page<GenerationStatementJpa> gsPage(Pageable pageable,String stmtMonth,String stmtYear){
		return gsJpaRepo.gsPage(pageable, stmtMonth, stmtYear);
	}
	
	@GetMapping("/counts/_internal")
	public long gsDocIdCount(@RequestParam(value = "docId", required = false) String docId,@RequestParam(value = "stmtMonth", required = false) String stmtMonth,
			@RequestParam(value = "stmtYear", required = false) String stmtYear) {
		return gsJpaRepo.gsDocCount(docId,stmtMonth,stmtYear);
	}
	

}
