package org.template.com.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public abstract class BaseController {

	/**
	 * serlvet api .
	 */
	@Resource
	private HttpServletRequest request;

	public final HttpServletRequest getRequest() {
		return request;
	}

	@Resource
	private HttpServletResponse response;

	public final HttpServletResponse getResponse() {
		return response;
	}

	@InitBinder
	public void initBinder(HttpServletRequest request, WebDataBinder binder) throws Exception {

		// SimpleDateFormat dateFormat = new SimpleDateFormat(
		// "yyyy-MM-dd HH:mm:ss"); // 可以設定任意的日期格式
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		// dateFormat.setLenient(false);
		dateFormat2.setLenient(false);
		// 格式化时间
		// binder.registerCustomEditor(Date.class, new CustomDateEditor(
		// dateFormat, true));

		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat2, true));
		// 去掉空格
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));

	}

	public final UserDetails getCurrentUser() {

		UserDetails userDetails = null;
		try {
			userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return userDetails;
	}

}
