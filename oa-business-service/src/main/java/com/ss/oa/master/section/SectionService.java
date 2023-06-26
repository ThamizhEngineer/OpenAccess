package com.ss.oa.master.section;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.OpenAccessException;

@RestController
@RequestMapping(path = "/master/sections")
public class SectionService {
	
	@Autowired
	SectionRepository sectionRepository;
	
	@GetMapping
	public Iterable<Section> getAllSections(@RequestParam(value="mOrgId")String mOrgId )throws OpenAccessException{
		if(mOrgId!=null&&!mOrgId.isEmpty()) {
			
			return sectionRepository.findByMOrgId(mOrgId);
		}
		
		return null;
	}
	
	
	@GetMapping("/{id}")
	public Optional<Section> getAllSectionById(@PathVariable(value="id")Integer id)throws OpenAccessException{
		return sectionRepository.findById(id);
	}
	
	@PostMapping
	public Section addSection (@RequestBody Section section)throws OpenAccessException{
		return sectionRepository.save(section);
	}
	
	@PatchMapping("/{id}")
	public Section updateSection(@RequestBody Section section)throws OpenAccessException{
		return sectionRepository.save(section);
	}
	@DeleteMapping("/{id}/delete")
	public void deleteSection(@PathVariable(value="id")Integer id)throws OpenAccessException{
		 sectionRepository.deleteById(id);
	}

}
