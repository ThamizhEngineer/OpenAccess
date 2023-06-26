package com.ss.oa.master.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.BaseDaoJdbc;
import com.ss.oa.common.OpenAccessException;
import com.ss.oa.master.vo.Org;

@RestController
@RequestMapping("/master/orgs")
@Scope("prototype")
public class OrgService extends BaseDaoJdbc {
	
	@Autowired
	private OrgRepository orgRepository;
	


	@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<Org> getOrg() throws OpenAccessException {
		return orgRepository.findAll();
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/{id}")
	public Org getOrgById(@PathVariable(value="id")String id)throws OpenAccessException{
		return orgRepository.findById(id).get();
		
	}

	@CrossOrigin(origins = "*")
	@PostMapping
	public Org addOrg(@RequestBody Org org) throws OpenAccessException {
		if(org.getCode()== null || org.getCode().isEmpty()){
			org.setCode(generateCode(Org.class.getSimpleName(),""));
		}
		return orgRepository.save(org);

	}

	@CrossOrigin(origins = "*")
	@PutMapping("/{id}")
	public Org updateOrgById(@RequestBody Org org) throws OpenAccessException {
		return orgRepository.save(org);

	}

	@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	public void deleteOrgById(@PathVariable(value = "id") String id) throws OpenAccessException {
		orgRepository.deleteById(id);
	}

}
