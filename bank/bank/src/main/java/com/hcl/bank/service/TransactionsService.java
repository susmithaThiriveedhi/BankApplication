package com.hcl.bank.service;

import java.util.List;

import com.hcl.bank.dto.TransactionsRequestDto;
import com.hcl.bank.dto.TransactionsResponseDto;
import com.hcl.bank.entity.Transactions;

public interface TransactionsService {
	Transactions saveTransaction(TransactionsRequestDto transactionRequestDto);

	List<Transactions> getTransactions(String accountNumber, int month, int year);

	//List<TransactionsResponseDto> getTransactions(String accountNumber,int month,int year);
	
}
