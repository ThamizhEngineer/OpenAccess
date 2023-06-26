package com.ss.oa.master.org;


import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import com.ss.oa.master.vo.Org;
@Scope("prototype")
public interface OrgRepository extends CrudRepository<Org, String>{
	Optional<Org> findById(String id);
}
