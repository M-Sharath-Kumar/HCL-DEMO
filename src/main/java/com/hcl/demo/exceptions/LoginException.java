package com.hcl.demo.exceptions;

public class LoginException extends RuntimeException {

	public LoginException(String msg) {
		super(String.format(msg));
	}

}
