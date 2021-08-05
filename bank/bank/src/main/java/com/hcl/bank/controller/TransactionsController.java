package com.hcl.bank.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.bank.dto.TransactionsRequestDto;
import com.hcl.bank.dto.TransactionsResponseDto;
import com.hcl.bank.entity.Transactions;
import com.hcl.bank.service.TransactionsService;

@RestController
@RequestMapping("/transactions")
@Validated
public class TransactionsController {
	@Autowired
	TransactionsService transactionsService;
	
	@PostMapping
	public ResponseEntity<String> saveTransaction(@Valid @RequestBody TransactionsRequestDto transactionsRequestDto) {
	    Transactions transactions=transactionsService.saveTransaction(transactionsRequestDto);
		return new ResponseEntity<>("Transaction \""+transactions.getTransactionId()+"\" is successfully completed", HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<?> getTransactions(@RequestParam String accountNumber,@Valid @RequestParam @Min(1) @Max(12) int month,@Valid @RequestParam @Min(2021) @Max(3000) int year){
		//List<TransactionsResponseDto> transactionsResponseDto=transactionsService.getTransactions(accountNumber,month,year);
		List<Transactions> transactions=transactionsService.getTransactions(accountNumber,month,year);
		if(transactions.size()!=0) {
			//return new ResponseEntity<>(transactionsResponseDto,HttpStatus.OK);
			return new ResponseEntity<>(transactions,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("No Transactions Found",HttpStatus.NOT_FOUND);
		}
	}
}
