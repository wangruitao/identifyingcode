package org.template.com.common.interceptor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.template.com.common.config.MyUsernamePasswordAuthenticationToken;
import org.template.com.common.config.ValidCodeErrorException;
import org.template.com.common.config.ValidateCodeHandle;

import com.google.code.kaptcha.Constants;

public class MyValidCodeProcessingFilter extends AbstractAuthenticationProcessingFilter {

	private String usernameParam = "username";
    private String passwordParam = "password";
    private String validCodeParam = "validateCode";

    public MyValidCodeProcessingFilter() {
        super(new AntPathRequestMatcher("/login.html", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String username = request.getParameter(usernameParam);
        String password = request.getParameter(passwordParam);
        String validCode = request.getParameter(validCodeParam);
        valid(validCode, request.getSession());
        MyUsernamePasswordAuthenticationToken token = new MyUsernamePasswordAuthenticationToken(username, password, validCode);
        return this.getAuthenticationManager().authenticate(token);
    }

    public void valid(String validCode, HttpSession session) {
        if (validCode == null) {
            throw new ValidCodeErrorException("验证码为空!");
        }
        if (!session.getAttribute(Constants.KAPTCHA_SESSION_KEY).equals(validCode)) {
            throw new ValidCodeErrorException("验证码错误!");
        }
    }

}
