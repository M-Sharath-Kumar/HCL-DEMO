package com.hcl.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer_fav_accounts")
public class CustomerFavouriteAccounts {

	@Id
	@Column(name = "uuid")
	private String uuid;
	
	@Column(name = "customer_id")
	private int customerId;
	
	@Column(name = "account_name")
	private String accountName;
	
	@Column(name = "iban_account_number")
	private String IBANAccountNumber;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getIBANAccountNumber() {
		return IBANAccountNumber;
	}

	public void setIBANAccountNumber(String iBANAccountNumber) {
		IBANAccountNumber = iBANAccountNumber;
	}
	
}
