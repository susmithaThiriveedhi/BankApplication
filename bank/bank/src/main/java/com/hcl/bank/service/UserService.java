package com.hcl.bank.service;

import com.hcl.bank.dto.UserRequestDto;
import com.hcl.bank.entity.User;

public interface UserService {

	User saveUser(UserRequestDto userRequestDto);

	User getUser(String accountNumber);

	User updateUser(String accountNumber, UserRequestDto userRequestDto);

//	UserRequestDto getUser(String accountNumber);

}
