package com.hcl.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.demo.bo.CustomerBO;
import com.hcl.demo.service.CustomerService;

/**
 * 
 * @author Sharath
 *
 */

@RestController
@RequestMapping("/hcl/demo")
public class CustomerController {
	
	private Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;
	
	/**
	 * This API is for customer login 
	 * @param customerBO
	 * @return
	 */
	@PostMapping("/customer/login")
	public ResponseEntity<String> getCustomer(@RequestBody CustomerBO customerBO) {
		logger.info("CustomerController :: getCustomer() ---> Started");
		String response;
		response = customerService.getCustomerData(customerBO);
		logger.info("CustomerController :: getCustomer() ---> Finished");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	
	
}
