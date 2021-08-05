package com.hcl.bank.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.bank.dto.UserRequestDto;
import com.hcl.bank.entity.User;
import com.hcl.bank.service.UserService;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping
	public ResponseEntity<String> saveUser(@Valid @RequestBody UserRequestDto userRequestDto) {
		User user=userService.saveUser(userRequestDto);
		return new ResponseEntity<>("Account \""+user.getAccountNumber()+"\" Created with an opening balance of \""+user.getBalance()+"\"", HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<?> getUser(@RequestParam String accountNumber) {
		User user=userService.getUser(accountNumber);
		if(user!=null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(accountNumber+" Does Not Exist",HttpStatus.NOT_FOUND);
		}
	}
	
//	@GetMapping
//	public ResponseEntity<?> getUser(@RequestParam String accountNumber) {
//		UserRequestDto userRequestDto=userService.getUser(accountNumber);
//		if(userRequestDto!=null) {
//			return new ResponseEntity<>(userRequestDto, HttpStatus.OK);
//		}else {
//			return new ResponseEntity<>(accountNumber+" Does Not Exist",HttpStatus.NOT_FOUND);
//		}
//	}
	
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestParam String accountNumber,@RequestBody UserRequestDto userRequestDto){
		userService.updateUser(accountNumber,userRequestDto);
		return new ResponseEntity<>("Updated Successfully", HttpStatus.CREATED);
	}
}
