package com.hcl.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hcl.demo.bo.CustomerBO;
import com.hcl.demo.bo.CustomerFavAccount;
import com.hcl.demo.bo.FavouriteAccountsResponse;
import com.hcl.demo.entities.BankDetails;
import com.hcl.demo.entities.CustomerEntity;
import com.hcl.demo.entities.CustomerFavouriteAccounts;
import com.hcl.demo.exceptions.LoginException;
import com.hcl.demo.exceptions.ValidationException;
import com.hcl.demo.repository.BankDetailsRepository;
import com.hcl.demo.repository.CustomerRepository;
import com.hcl.demo.repository.FavouriteAccountsReposiroty;

@Service
public class CustomerServiceImpl implements CustomerService {

	private Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private FavouriteAccountsReposiroty favAccountsRepository;
	
	@Autowired
	private BankDetailsRepository bankDetailsRepository;

	/**
	 * This method is for Checking the Login of the Customer
	 */
	@Override
	public String getCustomerData(CustomerBO customerBO) {
		logger.info("CustomerServiceImpl :: getCustomerData() ---> Started");
		String responseData;
		Integer customerId = customerBO.getCustomerId();

		// Validating CutomerId
		if (customerId <= 0 || customerId == null) {
			throw new LoginException("Invalid Customer ID");
		}

		Optional<CustomerEntity> customerEntity = customerRepository.findById(customerId);

		if (customerEntity.isPresent()) {
			String customerName = customerEntity.get().getCustomerName();
			responseData = "Welcome, " + customerName;
		} else {
			responseData = "No Data found for the customerId";
		}

		logger.info("CustomerServiceImpl :: getCustomerData() ---> Finished");
		return responseData;
	}

	/**
	 * This method is for fetching Customer Favourite Accounts
	 */
	@Override
	public List<FavouriteAccountsResponse> getFavouriteAccounts(Integer customerId, Pageable pageable) {
		logger.info("CustomerServiceImpl :: getFavouriteAccounts() ---> Started");
		List<FavouriteAccountsResponse> responseList = new ArrayList<>();
		FavouriteAccountsResponse response;
		// Validating CutomerId
		if (customerId <= 0 || customerId == null) {
			throw new LoginException("Invalid Customer ID");
		}
		
		int pageSize = pageable.getPageSize();
		int pageNo = pageable.getPageSize();
		
		Pageable paging = PageRequest.of(pageNo, pageSize);
		
		Page<CustomerFavouriteAccounts> pagedResult = favAccountsRepository.fetchByCustomerId(customerId, paging);

		for(CustomerFavouriteAccounts acc : pagedResult.toList()) {
			response = new FavouriteAccountsResponse();
			response.setAccountName(acc.getAccountName());
			response.setAccountNumber(acc.getIBANAccountNumber());
			String bankName = getBankName(acc.getIBANAccountNumber());
			response.setBankName(bankName);
			responseList.add(response);
		}
		
		logger.info("CustomerServiceImpl :: getFavouriteAccounts() ---> Started");
		return responseList;
	}

	/**
	 * This method is for creating Customer Favourite Account
	 */
	@Override
	public void createFavAccount(CustomerFavAccount customerFavAccount) {
		logger.info("CustomerServiceImpl :: createFavAccount() ---> Started");
		String accountName = customerFavAccount.getAccountName();
		String accountNumber = customerFavAccount.getAccountNumber();
		Integer customerId = customerFavAccount.getCustomerId();

		validateAccountName(accountName);
		validateIBANNumber(accountNumber);

		// Validating CutomerId
		if (customerId <= 0 || customerId == null) {
			throw new LoginException("Invalid Customer ID");
		}

		//fetching BankName
		String bankName = getBankName(accountNumber);
		
		CustomerFavouriteAccounts favAccountsEntity = new CustomerFavouriteAccounts();

		favAccountsEntity.setUuid(UUID.randomUUID().toString());
		favAccountsEntity.setAccountName(accountName);
		favAccountsEntity.setIBANAccountNumber(accountNumber);
		favAccountsEntity.setCustomerId(customerId);
		
		favAccountsRepository.save(favAccountsEntity);
		logger.debug("Favourite Accounts Saved Successfully");

		logger.info("CustomerServiceImpl :: createFavAccount() ---> Finished");
	}

	////// Private Methods for Validation //////////////////
	private void validateAccountName(String accountName) {
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9\\\\s -]*$");
		accountName = accountName.trim();

		Matcher accountNameMatcher = pattern.matcher(accountName);

		if (StringUtils.isEmpty(accountName)) {
			throw new ValidationException("AccountName cannot be null or Empty");
		}
		if (accountName.length() > 50) {
			throw new ValidationException("AccountName length cannot exceed more than 50 characters");
		}
		if (!accountNameMatcher.matches()) {
			throw new ValidationException("Invalid AccountName, Please Enter a valid input");
		}
	}

	private void validateIBANNumber(String ibanNumber) {
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9]*$");
		ibanNumber = ibanNumber.trim();

		Matcher accountNameMatcher = pattern.matcher(ibanNumber);

		if (StringUtils.isEmpty(ibanNumber)) {
			throw new ValidationException("IBAN/Account Number cannot be null or Empty");
		}
		if (ibanNumber.length() < 20) {
			throw new ValidationException("AccountNumber length cannot exceed more than 20 characters");
		}
		if (!accountNameMatcher.matches()) {
			throw new ValidationException("Invalid IBAN/AccountNumber, Please Enter a valid input");
		}
	}
	
	private String getBankName(String accountNumber) {
		String bankName = null;
		accountNumber = accountNumber.trim().substring(4, 8);
		
		Optional<BankDetails> bankDetails = bankDetailsRepository.findById(accountNumber);
		
		if(bankDetails.isPresent()) {
			bankName = bankDetails.get().getBankName();
		}
		else {
			throw new ValidationException("Enter Valid IBAN/Account Number");
		}
		
		return bankName;
	}

}
