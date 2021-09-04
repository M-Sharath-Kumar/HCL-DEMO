package com.hcl.demo.bo;

public class CustomerFavAccountsResponse {

	private int customerId;
	private String IBANAccountNumber;
	private String accountName;
	private String bankName;
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getIBANAccountNumber() {
		return IBANAccountNumber;
	}
	public void setIBANAccountNumber(String iBANAccountNumber) {
		IBANAccountNumber = iBANAccountNumber;
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
	
}
