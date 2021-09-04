package com.hcl.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hcl.demo.bo.CommonResponseDetails;
import com.hcl.demo.exceptions.LoginException;
import com.hcl.demo.exceptions.ValidationException;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(ControllerAdvisor.class);	
	
	@ExceptionHandler(LoginException.class)
    public ResponseEntity<CommonResponseDetails> handleException(
    		LoginException ex, WebRequest request) {

    	CommonResponseDetails res= new CommonResponseDetails();
    	res.setDescription("UNAUTHORIZED");
    	res.setMessage(ex.getMessage());
        return new ResponseEntity<CommonResponseDetails>(res, HttpStatus.UNAUTHORIZED);
    }
	
	@ExceptionHandler(ValidationException.class)
    public ResponseEntity<CommonResponseDetails> handleValidationException(
    		ValidationException ex, WebRequest request) {

    	CommonResponseDetails res= new CommonResponseDetails();
    	res.setDescription("INCORRECT_DATA");
    	res.setMessage(ex.getMessage());
        return new ResponseEntity<CommonResponseDetails>(res, HttpStatus.UNAUTHORIZED);
    }
	
}
