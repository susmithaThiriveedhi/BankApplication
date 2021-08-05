package com.hcl.bank.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.bank.dto.TransactionsRequestDto;
import com.hcl.bank.dto.TransactionsResponseDto;
import com.hcl.bank.dto.UserRequestDto;
import com.hcl.bank.entity.Transactions;
import com.hcl.bank.entity.User;
import com.hcl.bank.repository.TransactionsRepository;
import com.hcl.bank.service.TransactionsService;
import com.hcl.bank.service.UserService;

@Service
public class TransactionsServiceImpl implements TransactionsService{
	
	@Autowired
	TransactionsRepository transactionsRepository;

	@Autowired
	UserService userService;
	
	@Override
	public Transactions saveTransaction(TransactionsRequestDto transactionsRequestDto) {
		double amount=transactionsRequestDto.getAmount();	
		String fromAccount=transactionsRequestDto.getFromAccountNumber();
		String toAccount=transactionsRequestDto.getToAccountNumber();
		
		User fromUser=userService.getUser(fromAccount);
		UserRequestDto fromUserRequestDto=new UserRequestDto();
		BeanUtils.copyProperties(fromUser,fromUserRequestDto);
		fromUser.setBalance(fromUser.getBalance()-amount);
		userService.updateUser(fromAccount,fromUserRequestDto);
		
//		UserRequestDto fromUserRequestDto=userService.getUser(toAccount);
//		User fromUser=new User();
//		BeanUtils.copyProperties(fromUserRequestDto,fromUser);
//		fromUser.setBalance(fromUser.getBalance()-amount);
//		userService.updateUser(fromAccount,fromUserRequestDto);
//		
//		UserRequestDto toUserRequestDto=userService.getUser(toAccount);
//		User toUser=new User();
//		BeanUtils.copyProperties(toUserRequestDto,toUser);
//		toUser.setBalance(toUser.getBalance()+amount);
//		userService.updateUser(toAccount,toUserRequestDto);
		
		User toUser=userService.getUser(toAccount);
		UserRequestDto toUserRequestDto=new UserRequestDto();
		BeanUtils.copyProperties(toUser,toUserRequestDto);
		toUser.setBalance(toUser.getBalance()+amount);
		userService.updateUser(toAccount,toUserRequestDto);
		
		Transactions transactions=new Transactions();
		BeanUtils.copyProperties(transactionsRequestDto,transactions);
		transactions.setTransactionDate(new Date());
		return transactionsRepository.save(transactions);
	}

//	@Override
//	public List<TransactionsResponseDto> getTransactions(String accountNumber, int month, int year) {
//		List<Transactions> transactions=transactionsRepository.getTransactions(accountNumber,month,year);
//		
//		List<TransactionsResponseDto> transactionsResponseDtos=new ArrayList<>();
//		TransactionsResponseDto transactionsResponseDto=new TransactionsResponseDto();
//		for(Transactions transaction: transactions) {
//			BeanUtils.copyProperties(transaction,transactionsResponseDto);
//			transactionsResponseDtos.add(transactionsResponseDto);
//		}
//		return transactionsResponseDtos;
//	}
	
	@Override
	public List<Transactions> getTransactions(String accountNumber, int month, int year) {
		List<Transactions> transactions=transactionsRepository.getTransactions(accountNumber,month,year);
		return transactions;
	}
}
