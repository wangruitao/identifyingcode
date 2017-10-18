package org.template.com.common.config;

import java.util.Properties;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.template.com.common.interceptor.CommonInterceptor;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}

	@Bean
	public CommonInterceptor timeInterceptor() {
		return new CommonInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(timeInterceptor());
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}
	
	

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		//开启对/blog/123.json的支持
		configurer.favorPathExtension(true).useJaf(false)
		//关闭 /blog/123?format=json 的支持
		.favorParameter(false).parameterName("mediaType")
		.ignoreAcceptHeader(true).defaultContentType(MediaType.APPLICATION_JSON)
		.mediaType("json", MediaType.APPLICATION_JSON)
		.mediaType("xml", MediaType.APPLICATION_XML)
		.mediaType("html", MediaType.TEXT_HTML);
		
	}

	//web 相关配置
	@Bean
	public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
		ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
		registration.getUrlMappings().clear();
		registration.addUrlMappings("*.json");
		registration.addUrlMappings("*.xml");
		registration.addUrlMappings("*.html");
		registration.addUrlMappings("*.htm");
		registration.addUrlMappings("*.js");
		registration.addUrlMappings("*.css");
		registration.addUrlMappings("*.jpg");
		registration.addUrlMappings("*.png");
		registration.addUrlMappings("*.jif");
		return registration;
	}
	
	@Bean
	public DefaultKaptcha captchaProducer(){
		DefaultKaptcha captchaProducer =new DefaultKaptcha();
		Properties properties =new Properties();
		properties.setProperty("kaptcha.border","yes");
		properties.setProperty("kaptcha.border.color","105,179,90");
		properties.setProperty("kaptcha.textproducer.font.color","blue");
		properties.setProperty("kaptcha.image.width","125");
		properties.setProperty("kaptcha.image.height","45");
		properties.setProperty("kaptcha.textproducer.font.size","45");
		properties.setProperty("kaptcha.session.key","code");
		properties.setProperty("kaptcha.textproducer.char.length","4");
		properties.setProperty("kaptcha.textproducer.font.names","宋体,楷体,微软雅黑");
		Config config=new Config(properties);
		captchaProducer.setConfig(config);
		return  captchaProducer;
	}
}
