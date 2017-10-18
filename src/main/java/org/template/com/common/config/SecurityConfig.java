package org.template.com.common.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.template.com.common.interceptor.MyValidCodeProcessingFilter;
import org.template.com.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends AbstractSecurityWebApplicationInitializer {


	    @Autowired
	    private UserService customerService;

	    @Configuration
	    @Order(1)
	    public static class FrontendWebSecurityConfigureAdapter extends WebSecurityConfigurerAdapter {

	        @Autowired
	        private MyValidCodeProcessingFilter myValidCodeProcessingFilter;

	     
			
	        @Override
			public void configure(WebSecurity web) throws Exception {
	        	web.ignoring().antMatchers("/kaptcha/*");
			}



			@Override
	        protected void configure(HttpSecurity http) throws Exception {
	        	
	        	   http.csrf().disable().authorizeRequests().anyRequest().authenticated().and()
	        	   .addFilterBefore(myValidCodeProcessingFilter, UsernamePasswordAuthenticationFilter.class)
	        	   .formLogin()
	   			.loginPage("/login.html")
	   			.loginProcessingUrl("/login.html").failureUrl("/login.html")
	   					.permitAll().and().logout().permitAll();
	   			
	   			// 自定义注销
	   			http.logout().logoutUrl("/logout.html").logoutSuccessUrl("/login.html");
	   			
	        }

	    }

	    @Bean(name = "frontAuthenticationProvider")
	    public MyAuthenticationProvider frontAuthenticationProvider() {
	        MyAuthenticationProvider myAuthenticationProvider = new MyAuthenticationProvider();
	        myAuthenticationProvider.setUserDetailsService(customerService);
	        return myAuthenticationProvider;
	    }

	    @Bean
	    public AuthenticationManager authenticationManager() {
	        List<AuthenticationProvider> list = new ArrayList<>();
	        list.add(frontAuthenticationProvider());
	        AuthenticationManager authenticationManager = new ProviderManager(list);
	        return authenticationManager;
	    }

	    @Bean
	    public MyValidCodeProcessingFilter myValidCodeProcessingFilter(AuthenticationManager authenticationManager) {
	        MyValidCodeProcessingFilter filter = new MyValidCodeProcessingFilter();
	        filter.setAuthenticationManager(authenticationManager);
	        filter.setAuthenticationSuccessHandler(frontAuthenticationSuccessHandler());
	        filter.setAuthenticationFailureHandler(frontAuthenticationFailureHandler());
	        return filter;
	    }

	    @Bean
	    public FrontAuthenticationFailureHandler frontAuthenticationFailureHandler() {
	        return new FrontAuthenticationFailureHandler("/login");
	    }

	    @Bean
	    public FrontAuthenticationSuccessHandler frontAuthenticationSuccessHandler() {
	        return new FrontAuthenticationSuccessHandler("/index.html");
	    }

	    @Bean
	    public MyAuthenticationEntryPoint myAuthenticationEntryPoint() {
	        return new MyAuthenticationEntryPoint("/login");
	    }

}
