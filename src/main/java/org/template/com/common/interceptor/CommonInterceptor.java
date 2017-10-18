package org.template.com.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CommonInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// System.out.println("在整个请求结束之后被调用，也就是在DispatcherServlet渲染了对应的视图之后执行");
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// System.out.println("afterConcurrentHandlingStarted()方法用于处理异步请求");
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// System.out.println("请求处理之后进行调用，但是在视图被渲染之前(Controller方法调用之后)");
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// System.out.println("在请求处理之前进行调用（Controller方法调用之前）");
		String path = request.getContextPath();
		String scheme = request.getScheme();
		String serverName = request.getServerName();
		int port = request.getServerPort();
		String basePath = scheme + "://" + serverName + ":" + port + path;
		request.setAttribute("basePath", basePath);

		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession()
				.getAttribute("SPRING_SECURITY_CONTEXT");

		if (securityContextImpl == null) {// 如果获取不到登录的session
			// 如果是ajax请求
			if (request.getHeader("x-requested-with") != null
					&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
				response.setHeader("sessionstatus", "timeout"); // 响应头设置session状态
				return false; // session超时，ajax访问返回false
			}
		}
		return true;
	}

}
