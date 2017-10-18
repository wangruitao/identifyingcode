package org.template.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.template.com.model.Users;
import org.template.com.service.UserService;

@Controller
public class HelloController extends BaseController {

    @RequestMapping("/index")
    public String index() {
    	System.out.println("登陆用户：" + getCurrentUser().getUsername());
        return "index";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
    
    @Autowired
	UserService userService;
	
	
	@RequestMapping("/insert")
	@ResponseBody
	public boolean insert() {
		boolean isSuc = userService.insert("tyest");
		return isSuc;
		
	}
	
	@RequestMapping(value = "/query/{id}")
	@ResponseBody
	public Users queryEntry(ModelMap model,@PathVariable("id") Long id) {
//		int i = 1/0;
		System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");  
		return userService.queryEntry(id);
	}
	
	@RequestMapping(value = "/query1/{id}")
	@ResponseBody
	public Users queryEntry1(ModelMap model,@PathVariable("id") Long id) {
		return userService.queryEntry(id);
	}
	
	@RequestMapping(value = "/queryid/{id}")
	public String queryById(ModelMap model, @PathVariable("id") Long id) {
		model.put("us", userService.queryEntry(id));
//		int i = 1/0;
		return "single";
	}

	@RequestMapping(value = "/delete/{id}")
	public boolean deleteById(@PathVariable("id") Long id) {
		return userService.delete(id);
	}
	
	@RequestMapping(value = "/imodel")
	@ResponseBody
	public Users insertModel() {
		Users us = new Users();
		us.setLogName("ces");
		us.setName("测试用的");
		us.setUserLevel(0);
		us.setUserLoc(9);
		us.setUserPassword("ceshiyongd");
		Long id = userService.insertModel(us);
		us.setId(id);
		return us;
	}
	
	@RequestMapping(value = "/update")
	@ResponseBody
	public Boolean update() {
		Users us = new Users();
		us.setId(19L);
		us.setName("测试王瑞涛是的");
		us.setUserLevel(0);
		us.setUserLoc(450);
		us.setUserPassword("ceshiyongd");
		return userService.update(us) == null ? false : true;
	}
}
