package com.hcl.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bank_details")
public class BankDetails {

	@Id
	@Column(name = "iban_code")
	private String IBANNumber;

	@Column(name = "bank_name")
	private String bankName;

	public String getIBANNumber() {
		return IBANNumber;
	}

	public void setIBANNumber(String iBANNumber) {
		IBANNumber = iBANNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
}
