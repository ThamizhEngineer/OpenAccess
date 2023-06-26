package com.ss.oashared.interceptor;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.AuthUser;
import com.ss.oashared.model.AuthUserProfileAccess;


@RestController
@RequestMapping(path = "/tokens")
public class TokenService {

	@Autowired
AuthUserRepository authUserRepository;
	@Autowired
	CommonUtils commonUtils;
	
	private static SecretKeySpec secretKey;
	private static byte[] key;

	String password=null;
	
	String password1=null;
	
	String privilage=null;
	
	String uniqueid=null;

	private String passKey="openaccesstneb12";
	
	@CrossOrigin(origins = "*")
	@PostMapping(path = "/login")
	public AuthUser generateToken(@RequestBody(required = false) AuthUser authUser) {
		// return dao.getAllUser(criteria);
		
		try {
			setKey(passKey);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            password=new String(cipher.doFinal(Base64.getDecoder().decode(authUser.getPassword())));
            uniqueid=new String(cipher.doFinal(Base64.getDecoder().decode(authUser.getUniqueId())));
            
          
            
            
           if (authUser != null) {
				List<AuthUser> authUserList = new ArrayList<AuthUser>();
				
				
				authUserList = authUserRepository.findByUserName(authUser.getUserName());
				if (authUserList != null && !authUserList.isEmpty()) {
					for (AuthUser authLoop : authUserList) {
						if (authLoop.getPassword().equals(password)) {
							LocalDateTime ldt = LocalDateTime.now().plusHours(1);
							authLoop.setTokenValidityDt(ldt);
							authLoop.setToken(commonUtils.generateId());
							authLoop.setLastLoggedDt(LocalDateTime.now());
						    if(authLoop.getIsLoggedIn().equals("Y")) {
								System.out.println("USER ALREADY IN ACCESS !!!");
							}
							else {
								
							authUser = authUserRepository.save(authLoop);
							}
							authUser.setPassword(null);
							authUser.setUniqueId(uniqueid);
						} else {

							throw new OpenAccessException("Password does not match");
						}
					}
				} else {
					throw new OpenAccessException("User doesnt exist");
				}
			}
          
		} catch (OpenAccessException oae) {
			throw oae;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new OpenAccessException("System Error while processing your request");
		}
		
		
		try {
			
			
			
			if(authUser.getIsSuperUser()!=null) {
            authUser.setIsSuperUser("encrepted"+authUser.getIsSuperUser()+"data");
			authUser.setIsSuperUser(Base64.getEncoder().encodeToString(authUser.getIsSuperUser().getBytes()));
			}
			if(authUser.getCompanyId()!=null) {
			authUser.setCompanyId(Base64.getEncoder().encodeToString(authUser.getCompanyId().getBytes()));
			}
			if(authUser.getCompanyServiceId()!=null) {
			authUser.setCompanyServiceId(Base64.getEncoder().encodeToString(authUser.getCompanyServiceId().getBytes()));
			}
			if(authUser.getContactNumber()!=null) {
	//		authUser.setConfirmedDate(Base64.getEncoder().encodeToString(authUser.getConfirmedDate().toString().getBytes()));
			authUser.setContactNumber(Base64.getEncoder().encodeToString(authUser.getContactNumber().getBytes()));
			}
			if(authUser.getDuration()!=null) {
			authUser.setDuration(Base64.getEncoder().encodeToString(authUser.getDuration().getBytes()));
			}
			if(authUser.getEdcCode()!=null) {
			authUser.setEdcCode(Base64.getEncoder().encodeToString(authUser.getEdcCode().getBytes()));
			}
			if(authUser.getEmailId()!=null) {
			authUser.setEmailId(Base64.getEncoder().encodeToString(authUser.getEmailId().getBytes()));
			}
			if(authUser.getFirstName()!=null) {
			authUser.setFirstName(Base64.getEncoder().encodeToString(authUser.getFirstName().getBytes()));
			}
			if(authUser.getId()!=null) {
			authUser.setId(Base64.getEncoder().encodeToString(authUser.getId().getBytes()));
			}
			if(authUser.getLastName()!=null) {
		//	authUser.setLastLoggedDt(Base64.getEncoder().encodeToString(authUser.getIsSuperUser().getBytes()));
			authUser.setLastName(Base64.getEncoder().encodeToString(authUser.getLastName().getBytes()));
			}
			if(authUser.getLoginStopMessage()!=null) {
			authUser.setLoginStopMessage(Base64.getEncoder().encodeToString(authUser.getLoginStopMessage().getBytes()));
			}
			if(authUser.getIsLoggedIn()!=null) {
				authUser.setIsLoggedIn(Base64.getEncoder().encodeToString(authUser.getIsLoggedIn().getBytes()));
				}
			
			if(authUser.getMasterConfirmed()!=null) {
			authUser.setMasterConfirmed(Base64.getEncoder().encodeToString(authUser.getMasterConfirmed().getBytes()));
			}
			if(authUser.getOrgId()!=null) {
			authUser.setOrgId(Base64.getEncoder().encodeToString(authUser.getOrgId().getBytes()));
			}
			if(authUser.getPassword()!=null) {
			authUser.setPassword(Base64.getEncoder().encodeToString(authUser.getPassword().getBytes()));
			}
			if(authUser.getSystemKeyCode()!=null) {
			authUser.setSystemKeyCode(Base64.getEncoder().encodeToString(authUser.getSystemKeyCode().getBytes()));
			}
			if(authUser.getSystemRefKey()!=null) {
			authUser.setSystemRefKey(Base64.getEncoder().encodeToString(authUser.getSystemRefKey().getBytes()));
			}
			if(authUser.getToken()!=null) {
			authUser.setToken(Base64.getEncoder().encodeToString(authUser.getToken().getBytes()));
			}
			if(authUser.getUserName()!=null) {
		//	authUser.setTokenValidityDt(Base64.getEncoder().encodeToString(authUser.getIsSuperUser().getBytes()));
			authUser.setUserName(Base64.getEncoder().encodeToString(authUser.getUserName().getBytes()));
			}
//			if(authUser.getUserTypeCode()!=null) {
//			authUser.setUserTypeCode(Base64.getEncoder().encodeToString(authUser.getUserTypeCode().getBytes()));
//			}
			if(authUser.getUserTypeName()!=null) {
			authUser.setUserTypeName(Base64.getEncoder().encodeToString(authUser.getUserTypeName().getBytes()));
			}
			
			
			
			AuthUserProfileAccess[] AccesslistArr = authUser.getAccessList().toArray(new AuthUserProfileAccess[authUser.getAccessList().size()]);
		
			for(AuthUserProfileAccess Accesslist:AccesslistArr){
				
				
				if (Accesslist.getId()!=null) {
					Accesslist.setId(Base64.getEncoder().encodeToString(Accesslist.getId().getBytes()));
				}
				if (Accesslist.getFunctionality()!=null) {
					Accesslist.setFunctionality(Base64.getEncoder().encodeToString(Accesslist.getFunctionality().getBytes()));
				}
				if (Accesslist.getFeature()!=null) {
					Accesslist.setFeature(Base64.getEncoder().encodeToString(Accesslist.getFeature().getBytes()));
				}
				if (Accesslist.getDescription()!=null) {
					Accesslist.setDescription(Base64.getEncoder().encodeToString(Accesslist.getDescription().getBytes()));
				}
				if (Accesslist.getSystemKeyCode()!=null) {
					Accesslist.setSystemKeyCode(Base64.getEncoder().encodeToString(Accesslist.getSystemKeyCode().getBytes()));
				}
				if (Accesslist.getUserTypeCode()!=null) {
					Accesslist.setUserTypeCode(Base64.getEncoder().encodeToString(Accesslist.getUserTypeCode().getBytes()));
				}
				if (Accesslist.getOrgTypeCode()!=null) {
					Accesslist.setOrgTypeCode(Base64.getEncoder().encodeToString(Accesslist.getOrgTypeCode().getBytes()));
				}
				
			}
			
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return authUser;

	}
	

	 public static void setKey(String myKey)
		    {
		       
		        try {
		            key = myKey.getBytes("UTF-8");		            
		            secretKey = new SecretKeySpec(key, "AES");
		        }
		        catch (Exception e) {
		            e.printStackTrace();
		        }
		        
		    }

	@CrossOrigin(origins = "*")
	@PostMapping(path = "/logout")
	public String logoutToken(@RequestBody(required = false) AuthUser authUser) {

		String result = "";
		if (authUser != null) {
			List<AuthUser> authUserList = new ArrayList<>();
			authUserList = authUserRepository.findByUserName(authUser.getUserName());
			if (authUserList != null && !authUserList.isEmpty()) {
				for (AuthUser authLoop : authUserList) {
					if (authLoop.getToken() != null && authLoop.getTokenValidityDt() != null) {
						if (authLoop.getToken().equals(authUser.getToken())) {
							if(authLoop.getLoginStopMessage()!=null && authLoop.getLoginStopMessage().length()>0) {
								result = "failure";
								throw new OpenAccessException(authLoop.getLoginStopMessage());
							}
							authUser = authLoop;
							authLoop.setToken(null);
							authLoop.setTokenValidityDt(null);
							authLoop.setIsLoggedIn("N");
							authUser = authUserRepository.save(authLoop);
							result = "success";
						} else {
							result = "failure";
							throw new OpenAccessException("Token doesnt exist");

						}
					} else {
						result = "failure";
						throw new OpenAccessException("Token doesnt exist");
					}
				}
			} else {
				result = "failure";
				throw new OpenAccessException("User doesnt exist");
			}
		}

		return result;

	}

	@CrossOrigin(origins = "*")
	@PostMapping(path = "/validate")
	public AuthUser verifyToken(AuthUser authUser) throws OpenAccessException {

		return hideSensitiveInfo(internalValidation(authUser));

	}

	@CrossOrigin(origins = "*")
	@PostMapping(path = "/refresh")
	public AuthUser refreshToken(@RequestBody(required = false) AuthUser authUser) {
		if (authUser != null) {
			List<AuthUser> authUserList = new ArrayList<AuthUser>();
			authUserList = authUserRepository.findByUserName(authUser.getUserName());
			if (authUserList != null && !authUserList.isEmpty()) {
				for (AuthUser authLoop : authUserList) {
					if (authLoop.getUserName() != null && authLoop.getTokenValidityDt() != null) {
						if (authLoop.getToken().equals(authUser.getToken())) {
							authUser = authLoop;
							LocalDateTime fromDateTime = LocalDateTime.now();
							long minutes = fromDateTime.until(authLoop.getTokenValidityDt(), ChronoUnit.MINUTES);
							if (minutes > 0) {
								authLoop.setTokenValidityDt(LocalDateTime.now().plusHours(1));
								authUser = authUserRepository.save(authLoop);
							} else {
								throw new OpenAccessException("Token is expired or wrong");
							}
						} else {
							throw new OpenAccessException("Token doesnt exist");
						}
					} else {
						throw new OpenAccessException("Token doesnt exist");
					}
				}
			} else {
				throw new OpenAccessException("User doesnt exist");
			}

		}
		return authUser;
	}

	public AuthUser internalValidation(AuthUser authUser) {
		AuthUser validatedUser = null;
		List<AuthUser> authuserList = new ArrayList<AuthUser>();
		if (authUser.getToken() != null) {
			authuserList = authUserRepository.findByToken(authUser.getToken());
			if (authuserList != null && !(authuserList.isEmpty())) {
				for (AuthUser authLoop : authuserList) {
					if (authLoop.getToken() != null && authLoop.getTokenValidityDt() != null) {
						if (authLoop.getToken().equals(authUser.getToken())) {
							LocalDateTime fromDateTime = LocalDateTime.now();
							long minutes = fromDateTime.until(authLoop.getTokenValidityDt(), ChronoUnit.MINUTES);
							int minutesInt = (int) minutes;
							int hoursCal = minutesInt / 60;
							int minutesCal = minutesInt % 60;
							authLoop.setDuration(Integer.toString(hoursCal) + ":" + Integer.toString(minutesCal));
							if (minutes <= 0) {
								throw new OpenAccessException("Token Expired..");
							} else {
								authLoop.setLastLoggedDt(fromDateTime);
								validatedUser = authLoop;
								break;
							}
						}
					}
				}
			}
		} else if (authUser.getUserName() != null && authUser.getPassword() != null) {
			authuserList = authUserRepository.findByUserNameAndPassword(authUser.getUserName(), authUser.getPassword());
			if (authuserList != null && !(authuserList.isEmpty())) {

				for (AuthUser userLoop : authuserList) {
					if (userLoop.getUserName().equals(authUser.getUserName())
							&& (userLoop.getPassword().equals(authUser.getPassword()))) {
						validatedUser = authUser;
						break;
					}
				}
			}

			else {
				throw new OpenAccessException("AuthUser Doesnot Exist");
			}

		} else {
			throw new OpenAccessException("Token or userCredentials is mandatory");
		}
		if (validatedUser == null) {
			throw new OpenAccessException("AuthUser not validated");

		}
		return validatedUser;
	}

	private AuthUser hideSensitiveInfo(AuthUser authUser) {
		if (authUser != null) {
			authUser.setPassword(null);
		}
		return authUser;
	}
	
	
		@CrossOrigin(origins = "*")
		@PostMapping(path = "/setloggedin")
		public String setLoggedIn(@RequestBody(required = false) AuthUser authUser) {
		
			
			
			
			if (authUser != null) {
				List<AuthUser> authUserList = new ArrayList<AuthUser>();
				
				
				authUserList = authUserRepository.findByUserName(authUser.getUserName());
				if (authUserList != null && !authUserList.isEmpty()) {
					for (AuthUser authLoop : authUserList) {
						if (authLoop.getPassword().equals(password)) {
							
							
							authLoop.setIsLoggedIn("Y");
			                authUser = authUserRepository.save(authLoop);
							
						} 
					}
				} 
			}
			
			return "completed";
		
	}
}
