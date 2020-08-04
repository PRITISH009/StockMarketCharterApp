package com.account_service.service;

import com.account_service.dto.UserDetailsDto;

public interface AccountService {
	UserDetailsDto login(UserDetailsDto userDetails);
	UserDetailsDto createNewAccount(UserDetailsDto userDetails);
	UserDetailsDto updateAccount(UserDetailsDto userDetails);
	UserDetailsDto deleteAccount(UserDetailsDto userDetails);
	Iterable<UserDetailsDto> getAllAccounts();
	
}
