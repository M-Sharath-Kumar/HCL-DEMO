package com.hcl.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.demo.entities.CustomerFavouriteAccounts;

@Repository
public interface FavouriteAccountsReposiroty extends JpaRepository<CustomerFavouriteAccounts, String>{

	@Query("select fav from CustomerFavouriteAccounts fav where fav.customerId = :id")
	Page<CustomerFavouriteAccounts> fetchByCustomerId(@Param("id") Integer customerId, Pageable pageable);
	
}
