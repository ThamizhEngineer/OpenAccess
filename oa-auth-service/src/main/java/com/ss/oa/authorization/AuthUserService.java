package com.ss.oa.authorization;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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




import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.AuthUser;
import com.ss.oashared.model.Response;
import com.ss.oashared.interceptor.AuthUserRepository;
import com.ss.oashared.interceptor.TokenService;





@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "auth/users")
public class AuthUserService {
	@Autowired
	AuthUserRepository authUserRepository;
	@Autowired
	CommonUtils commonUtils;
	@Autowired
	TokenService tokenService;
	@CrossOrigin(origins="*")
	@GetMapping
	public Iterable<AuthUser> getAuthUser(@RequestHeader(name="token",required=false)String token)throws OpenAccessException{
		
		if(token!=null) {
			AuthUser authUser= new AuthUser();
			authUser.setToken(token);
			System.out.println(token);
		  tokenService.verifyToken(authUser);
			
		}else {
			return authUserRepository.findAll();	
		}
		
		return authUserRepository.findAll();
		
	}
	@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public Optional<AuthUser> getUserById(@PathVariable(value = "id") Integer id) throws OpenAccessException {
		return authUserRepository.findById(id+"");

	}
	
	@CrossOrigin(origins="*")
	@PostMapping
	public AuthUser addAuthUser(@RequestBody AuthUser authUser)throws OpenAccessException{
		authUser.setId(commonUtils.generateId());
		return authUserRepository.save(authUser);
		
	}

	@CrossOrigin(origins = "*")
	@PatchMapping("/{id}")
	public AuthUser updateuserById(@RequestBody AuthUser authUser) throws OpenAccessException {
		return authUserRepository.save(authUser);

	}
	
	
	@CrossOrigin(origins = "*")
	@PatchMapping("/confirmMaster/{userName}")
	public AuthUser setIsMasterConfirmed(@PathVariable(value = "userName") String userName) throws OpenAccessException {
		System.out.println(userName);
		List<AuthUser> auth= authUserRepository.findByUserName(userName);
		AuthUser authUser=new AuthUser();
		authUser = auth.get(0);
		authUser.setMasterConfirmed("Y");
		System.out.println(authUser);
		return authUserRepository.save(authUser);
	}

	
	
	@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable(value = "id") Integer id) throws OpenAccessException {
		authUserRepository.deleteById(id+"");
	}
	@CrossOrigin(origins = "*")
	@PatchMapping("/{id}/change-password")
	public Object changePasswordById(@PathVariable(value = "id")  Integer id, @RequestBody AuthUser authUser)
			throws OpenAccessException {

		if (authUser.getPassword() == null || authUser.getPassword().isEmpty()) {
			throw new OpenAccessException("Password cannot be empty");
		}
		AuthUser currUser = getUserById(id).get();

		currUser.setPassword(authUser.getPassword());

		authUserRepository.save(currUser);

		return Response.returnSuccess();
	}
	
	@CrossOrigin(origins = "*")
	@PatchMapping("/{token}/change-password/token")
	public Object changePasswordByToken(@PathVariable(value = "token")  String token, @RequestBody AuthUser authUser)
			throws OpenAccessException {

		if (authUser.getPassword() == null || authUser.getPassword().isEmpty()) {
			throw new OpenAccessException("Password cannot be empty");
		}
		AuthUser currUser = authUserRepository.findByToken(token).get(0);

		currUser.setPassword(authUser.getPassword());

		authUserRepository.save(currUser);

		return Response.returnSuccess();
	}
	@CrossOrigin(origins = "*")
	@PatchMapping("/{token}/validate-password/token")
	public Object validatePasswordByToken(@PathVariable(value = "token")  String token, @RequestBody AuthUser authUser)
			throws OpenAccessException {

		if (authUser.getPassword() == null || authUser.getPassword().isEmpty()) {
			throw new OpenAccessException("Password cannot be empty");
		}
		AuthUser currUser = authUserRepository.findByToken(token).get(0);

		if (currUser.getPassword().equals(authUser.getPassword())) {
			
			return Response.returnSuccess();
		}
		else {
			
			
			return Response.returnFailure();
		}
		

		

		
	}
	
}
