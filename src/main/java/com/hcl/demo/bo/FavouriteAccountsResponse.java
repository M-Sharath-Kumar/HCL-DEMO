package com.hcl.demo.bo;

public class FavouriteAccountsResponse {

	private String accountNumber;
	private String accountName;
	private String bankName;
	private String customerUUID;
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getCustomerUUID() {
		return customerUUID;
	}
	public void setCustomerUUID(String customerUUID) {
		this.customerUUID = customerUUID;
	}
	
}
