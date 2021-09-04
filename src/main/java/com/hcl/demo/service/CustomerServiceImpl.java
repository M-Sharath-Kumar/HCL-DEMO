package com.hcl.demo.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.demo.bo.CustomerBO;
import com.hcl.demo.entities.CustomerEntity;
import com.hcl.demo.exceptions.LoginException;
import com.hcl.demo.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	private Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * This method is for Checking the Login of the Customer
	 */
	@Override
	public String getCustomerData(CustomerBO customerBO) {
		logger.info("CustomerServiceImpl :: getCustomerData() ---> Started");
		String responseData;
		Integer customerId = customerBO.getCustomerId();
		
		// Validating CutomerId
		if(customerId <= 0 || customerId == null) {
			throw new LoginException("Invalid Customer ID");
		}
		
		Optional<CustomerEntity> customerEntity = customerRepository.findById(customerId);
		
		if(customerEntity.isPresent()) {
			String customerName = customerEntity.get().getCustomerName() ; 
			responseData = "Welcome, " + customerName; 
		}
		else {
			responseData = "No Data found for the customerId";
		}
		
		logger.info("CustomerServiceImpl :: getCustomerData() ---> Finished");
		return responseData;
	}

}
