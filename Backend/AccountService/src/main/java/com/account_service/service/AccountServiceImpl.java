package com.account_service.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.account_service.dao.AccountServiceDao;
import com.account_service.dto.UserDetailsDto;
import com.account_service.mapper.AccountsMapper;
import com.account_service.model.Accounts;

@Service
public class AccountServiceImpl implements AccountService {
	private AccountServiceDao accountServiceDao;
	private AccountsMapper mapper;
	
	@Autowired
	public AccountServiceImpl(AccountServiceDao dao, AccountsMapper mapper) {
		this.accountServiceDao = dao;
		this.mapper = mapper;
	}

	@Override
	public UserDetailsDto login(UserDetailsDto userDetails) {
		if(accountServiceDao.findByUsername(userDetails.getUsername()).isPresent()) {
			Accounts userAccount = accountServiceDao.findByUsername(userDetails.getUsername()).get();
			if(userDetails.getPassword().equals(userAccount.getPassword())) {
				return userDetails;
			}
		}
		return null;
	}

	@Override
	public UserDetailsDto createNewAccount(UserDetailsDto userDetails) {
		if(accountServiceDao.findByUsername(userDetails.getUsername()).isPresent()) {
			return null;
		}
		accountServiceDao.save(mapper.dtoToAccount(userDetails));
		return userDetails;
	}

	@Override
	public UserDetailsDto updateAccount(UserDetailsDto userDetails) {
		if(accountServiceDao.findByUsername(userDetails.getUsername()).isPresent()) {
			Accounts account = accountServiceDao.findByUsername(userDetails.getUsername()).get();
			account.setPassword(userDetails.getPassword());
			accountServiceDao.save(account);
			return userDetails;
		}
		return null;
	}

	@Override
	public UserDetailsDto deleteAccount(UserDetailsDto userDetails) {
		if(accountServiceDao.findByUsername(userDetails.getUsername()).isPresent()) {
			Accounts account = accountServiceDao.findByUsername(userDetails.getUsername()).get();
			if(userDetails.getPassword().equals(account.getPassword())) {
				accountServiceDao.delete(accountServiceDao.findByUsername(userDetails.getUsername()).get());
				return userDetails;
			}
		}
		return null;
	}

	@Override
	public ArrayList<UserDetailsDto> getAllAccounts() {
		return mapper.getDtoList((ArrayList<Accounts>) accountServiceDao.findAll());
	}
	
	
}
