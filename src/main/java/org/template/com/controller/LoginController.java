package org.template.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

//	@Autowired
//	private RedisTemplate redisTemplate;
	
	@RequestMapping(path="/login")
	public String login() {
		return "login";
	}
	
}
