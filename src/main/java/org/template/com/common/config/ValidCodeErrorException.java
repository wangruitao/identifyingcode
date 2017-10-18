package org.template.com.common.config;

import org.springframework.security.core.AuthenticationException;

public class ValidCodeErrorException extends AuthenticationException {

	public ValidCodeErrorException(String msg) {
        super(msg);
    }
	
	public ValidCodeErrorException(String msg, Throwable t) {
		super(msg, t);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
