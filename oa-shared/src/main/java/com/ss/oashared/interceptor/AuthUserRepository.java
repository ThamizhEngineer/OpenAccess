package com.ss.oashared.interceptor;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ss.oashared.model.AuthUser;

public interface AuthUserRepository extends CrudRepository<AuthUser, String>{
	List<AuthUser> findByToken(String token);
	List<AuthUser> findByUserName(String userName);
	List<AuthUser> findByUserNameAndPassword(String userName, String password);

}
