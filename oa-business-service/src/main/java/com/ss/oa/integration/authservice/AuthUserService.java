package com.ss.oa.integration.authservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ss.oa.common.OpenAccessException;


import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.interceptor.AuthUserRepository;
import com.ss.oashared.interceptor.TokenService;
import com.ss.oashared.model.AuthUser;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "auth/users")
@Scope("prototype")
public class AuthUserService {
	@Autowired
	AuthUserRepository authUserRepository;
	@Autowired
	TokenService tokenService;

	@CrossOrigin(origins="*")
	@GetMapping
	public Iterable<AuthUser> getAuthUser(@RequestHeader(name="token",required=false)String token)throws OpenAccessException{
		return authUserRepository.findAll();
		
	}
	@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public Optional<AuthUser> getUserById(@PathVariable(value = "id") String id) throws OpenAccessException {
		return authUserRepository.findById(id);

	}
	
	@CrossOrigin(origins="*")
	@PostMapping
	public AuthUser addAuthUser(@RequestBody AuthUser authUser)throws OpenAccessException{
		return authUserRepository.save(authUser);
		
	}

	@CrossOrigin(origins = "*")
	@PatchMapping("/{id}")
	public AuthUser updateuserById(@RequestBody AuthUser authUser) throws OpenAccessException {


		return authUserRepository.save(authUser);

	}

	@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable(value = "id") String id) throws OpenAccessException {
		authUserRepository.deleteById(id);
	}
	
}
