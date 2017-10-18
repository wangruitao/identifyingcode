package org.template.com.common.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class MyUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 1L;
	
	private String validCode;
	
    public MyUsernamePasswordAuthenticationToken(String principal, String credentials, String validCode) {
        super(principal, credentials);
        this.validCode = validCode;
    }
    public String getValidCode() {
        return validCode;
    }
    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }
}
