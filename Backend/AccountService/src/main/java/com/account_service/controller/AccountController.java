package com.account_service.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.account_service.dto.UserDetailsDto;
import com.account_service.service.AccountServiceImpl;

@RestController
@RequestMapping("account-service")
public class AccountController {
	
	private AccountServiceImpl accountService;
	
	@Autowired
	public AccountController(AccountServiceImpl service) {
		this.accountService = service;
	}
	
	@RequestMapping(path = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDetailsDto> login(@RequestBody UserDetailsDto userDetails){
		UserDetailsDto result = accountService.login(userDetails);
		if(result == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
		else
			return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@RequestMapping(path = "/addAccount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDetailsDto> createAccount(@RequestBody UserDetailsDto userDetails){
		UserDetailsDto result = accountService.createNewAccount(userDetails);
		if(result == null)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
		else
			return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@RequestMapping(path = "/deleteAccount", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDetailsDto> deleteAccount(@RequestBody UserDetailsDto userDetails){
		UserDetailsDto result = accountService.deleteAccount(userDetails);
		if(result == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
		else
			return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@RequestMapping(path = "/updateAccount", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDetailsDto> updateAccount(@RequestBody UserDetailsDto userDetails){
		UserDetailsDto result = accountService.updateAccount(userDetails);
		if(result == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
		else
			return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@RequestMapping(path = "/allAccounts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<UserDetailsDto>> showAllAccounts(){
		return ResponseEntity.status(HttpStatus.OK).body(accountService.getAllAccounts());
	}
}
