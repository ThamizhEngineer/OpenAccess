package com.ss.oa.master.user;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.MasterRestService;
import com.ss.oa.common.OpenAccessException;
import com.ss.oashared.interceptor.AuthUserRepository;
import com.ss.oashared.model.AuthUser;


@RestController
@RequestMapping("/users")
@Scope("prototype")
public class UserRestService extends MasterRestService {
	
	@Autowired
	private AuthUserRepository authUserRepository;
	
	@CrossOrigin(origins = "*")
	@GetMapping("/confirmMaster/{userName}")
	public AuthUser setIsMasterConfirmed(@PathVariable(value = "userName") String userName) throws OpenAccessException {
		System.out.println(userName);
		List<AuthUser> auth= authUserRepository.findByUserName(userName);
		AuthUser authUser=new AuthUser();
		authUser = auth.get(0);
		authUser.setMasterConfirmed("Y");
		System.out.println(authUser);
		return authUserRepository.save(authUser);
	}
	
//	
//	@RequestMapping(value="/users", method = RequestMethod.GET)
//	public ResponseEntity<List<User>> getUsers(@RequestParam(name="id",required=false) String id)
//	{	
//		try {
//			
//			Map<String, String> criteria = new HashMap<String, String>();
//			criteria.put("id", id);
//			System.out.println("criteria-->"+criteria.toString());
//			//TO-DO
//			//modify this service to include date based search
//			return ResponseEntity.ok(service.getAllUser(criteria));
//		
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//		}
//	}
//	
//	@RequestMapping(value="/user/{id}", method = RequestMethod.GET)
//	public ResponseEntity<User> getUserById(@PathVariable("id")String id)
//	{
//
//		try {
//			return ResponseEntity.ok(service.getUserById(id));
//		
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//		}
//	}
//	
//	@RequestMapping(value="/user", method = RequestMethod.POST)
//	public ResponseEntity<String> addUser(@RequestBody User user)
//	{
//		String result = "";
//		
//		try {
//			result = service.addUser(user);
//			if(result.matches("FAILURE")){
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
//			}
//			else{
//				return ResponseEntity.ok(result);
//			}
//		} catch (Exception e) {
//
//			e.printStackTrace();
//			result =  "FAILURE";
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
//		}
//		
//	}
//	
//	@RequestMapping(value="/user/{id}", method = RequestMethod.PUT)
//	public ResponseEntity<String> updateUser(@PathVariable("id")String id,@RequestBody User user)
//	{
//		String result = "";
//		try {
//			result = service.updateUser(id, user);
//			if(result.matches("FAILURE")){
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
//			}
//			else{
//				return ResponseEntity.ok(result);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			result =  "FAILURE";
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
//		}
//	}
//
//	@RequestMapping(value="/user/{id}", method = RequestMethod.DELETE)
//	public ResponseEntity<String> deleteUser(@PathVariable("id")String id)
//	{
//		String result = "";
//		try {
//			result = service.deleteUser(id);
//			if(result.matches("FAILURE")){
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
//			}
//			else{
//				return ResponseEntity.ok(result);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			result =  "FAILURE";
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
//		}
//	}
	
}
