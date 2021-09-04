package com.hcl.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hcl.demo.bo.CustomerBO;
import com.hcl.demo.bo.CustomerFavAccount;
import com.hcl.demo.bo.FavouriteAccountsResponse;

public interface CustomerService {
	
	String getCustomerData(CustomerBO customerBO);
	
	List<FavouriteAccountsResponse> getFavouriteAccounts(Integer customerId, Pageable pageable);
	
	void createFavAccount(CustomerFavAccount customerFavAccount);

}
