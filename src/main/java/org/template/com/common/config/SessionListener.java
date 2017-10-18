package org.template.com.common.config;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.template.com.model.Users;
import org.template.com.service.UserService;

@WebListener
public class SessionListener implements HttpSessionListener{  
	@Autowired
	private UserService userService;
  
	public void sessionCreated(HttpSessionEvent arg0) {  
		// TODO Auto-generated method stub  
	}  
  
  
	public void sessionDestroyed(HttpSessionEvent arg0) {  
	// TODO Auto-generated method stub  
		HttpSession session = arg0.getSession();   
		SecurityContextImpl securityContextImpl = ((SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT"));
		if (securityContextImpl != null) {
			//取Session中用户信息
			Users ud = (Users) securityContextImpl.getAuthentication().getPrincipal();
//			Users user = new Users();
//			user.setId(ud.getId());
//			user.setOnlineFlg(0);
//			userService.update(user);
		}
	}  
} 