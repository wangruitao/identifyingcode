package org.template.com.common.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerAdvice {

	private Boolean isJson(WebRequest request){
        String header = request.getHeader("content-type");

        if(!StringUtils.isEmpty(header)) {
        	return header.contains("json");
        } 
        
        return true;
       
    }


    @ExceptionHandler(Exception.class)
    public Object handleBaseException(WebRequest request, Exception e) {
    	if(isJson(request)) {
    		ResponseEntity<String> response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    		return response;
    	} else {
    		ModelAndView model = new ModelAndView("error");
    		model.addObject("errorMessage", e.getMessage());
    		return model;
    	}

    }
	
    //用来设置WebDataBinder， WebDataBinder用来自动绑定前台请求参数到后台Model中
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
    	
    }
    
    @ModelAttribute
    public void addAttribute(Model model) {
    	model.addAttribute("msg", "额外信息");
    }
}
