package com.hcl.bank.service;

import com.hcl.bank.dto.UserRequestDto;
import com.hcl.bank.entity.User;
import com.hcl.bank.exception.UserAlreadyExistsException;

public interface UserService {

	User saveUser(UserRequestDto userRequestDto) throws UserAlreadyExistsException;

	User getUser(String accountNumber);

	User updateUser(String accountNumber, UserRequestDto userRequestDto);

}
