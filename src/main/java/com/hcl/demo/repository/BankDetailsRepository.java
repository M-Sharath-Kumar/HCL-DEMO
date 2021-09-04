package com.hcl.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.demo.entities.BankDetails;

@Repository
public interface BankDetailsRepository extends JpaRepository<BankDetails, String> {

}
