package com.ss.oashared.interceptor;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.*;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.AuthUser;
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter{
	@Autowired
	TokenService tokenService;
	@Value("${secure.service}")
	private String secureService;


	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());



	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3)
			throws Exception {
// 		log.info("afterCompletion...Request Completed ");
        
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model)
			throws Exception {

				
//	log.info("In postHandle we are Executing the Request ");
	

	 
	}


	
	public boolean skipRequestCheck(HttpServletRequest request) {
		boolean skip = false;
		String uri = request.getRequestURI();
		System.out.println(uri);
		if(request.getMethod().equalsIgnoreCase("OPTIONS")) {
			skip =true;
		}
		else if(uri.contains("/_internal")) {
			skip = true;
		}
		else if (uri.contains("/oa-auth-service") && uri.contains("/tokens") && uri.contains("/login") ) {
			skip = true;
		}
		else if (uri.contains("/oa-service") && uri.contains("/transaction") && uri.contains("/all-gs-for-company") ) {
			skip = true;
		}
		else if (uri.contains("/login-page-data")) {
            skip = true;
        }
		else if (uri.contains("/print")) {
            skip = true;
        }
		else if(uri.contains("/Bank")) {
			
			skip = true; 
		}
		return skip;
	}

	@Override
	 public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response,Object object) throws Exception{
		OpenAccessException exception = null; 
		try {
			if(skipRequestCheck(request)) {
				return true;
			}
			if(secureService.equals("true")) {
				if(request.getHeader("authorization")!=null&&!request.getHeader("authorization").isEmpty()) {
					AuthUser authUser= new AuthUser();
					authUser.setToken(request.getHeader("authorization"));
					
					tokenService.verifyToken(authUser);
					return true;
					
				}else {
					throw new Exception("authorization is Mandatory");
				}
			}
		}catch (Exception e) {
			exception = new OpenAccessException(e.getMessage());
		}
		
		if(exception != null) {
			 ObjectMapper mapper = new ObjectMapper();
			  response.setContentType("application/json");
			  response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			  response.getWriter().write(mapper.writeValueAsString(exception.getMessage()));
			  return false;
		}
		else
			return true;
		
		}		
}
