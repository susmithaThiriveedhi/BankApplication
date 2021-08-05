package com.hcl.bank.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class TransactionsRequestDto {
	@NotNull
	private String fromAccountNumber;
	
	@NotNull
	private String toAccountNumber;
	
	@NotNull
	@Max(5000)
	private double amount;

	private String remarks;

	public String getFromAccountNumber() {
		return fromAccountNumber;
	}

	public void setFromAccountNumber(String fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}

	public String getToAccountNumber() {
		return toAccountNumber;
	}

	public void setToAccountNumber(String toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
