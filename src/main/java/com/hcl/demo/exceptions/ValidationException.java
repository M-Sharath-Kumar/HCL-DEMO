package com.hcl.demo.exceptions;

public class ValidationException extends RuntimeException {

	public ValidationException(String msg) {
		super(String.format(msg));
	}

}
