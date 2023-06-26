package com.ss.oashared.interceptor;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.ss.oashared.model.AuthUserTypeAccess;


public interface AuthUserTypeAccessRepository extends CrudRepository<AuthUserTypeAccess, String>{
	
	
	List<AuthUserTypeAccess> findByUserTypeCode(String userTypeCode);
	
	
}
