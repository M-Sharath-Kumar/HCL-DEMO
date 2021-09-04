package com.hcl.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.demo.bo.CustomerBO;
import com.hcl.demo.bo.CustomerFavAccount;
import com.hcl.demo.bo.FavouriteAccountsResponse;
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
	 * 
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

	/**
	 * This API is for fetching Favourite Account based on customerId
	 * @param customerId
	 * @param pageable
	 * @return
	 */
	@GetMapping("/fetch/fav/accounts/{customerId}")
	public List<FavouriteAccountsResponse> getCustomerFavouriteAccounts(
			@PathVariable("customerId") Integer customerId, Pageable pageable) {
		logger.info("CustomerController :: getCustomer() ---> Started");
		List<FavouriteAccountsResponse> responseList;
		responseList = customerService.getFavouriteAccounts(customerId, pageable);
		logger.info("CustomerController :: getCustomer() ---> Finished");
		return responseList;
	}

	/**
	 * This API is for customer Favourite account create
	 * @param customerFavAccount
	 * @return
	 */
	@PostMapping("/customer/create")
	public ResponseEntity<HttpStatus> createFavAccount(@RequestBody CustomerFavAccount customerFavAccount) {
		logger.info("CustomerController :: createFavAccount() ---> Started");
		customerService.createFavAccount(customerFavAccount);
		logger.info("CustomerController :: createFavAccount() ---> Finished");
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
}
