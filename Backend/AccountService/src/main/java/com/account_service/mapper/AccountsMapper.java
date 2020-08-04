package com.account_service.mapper;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.account_service.dto.UserDetailsDto;
import com.account_service.model.Accounts;

@Component
public class AccountsMapper {
	private ModelMapper mapper = new ModelMapper();
	
	public ArrayList<UserDetailsDto> getDtoList(ArrayList<Accounts> allAccounts){
		ArrayList<UserDetailsDto> accountsDto = new ArrayList<>(); 
		for(Accounts account : allAccounts) {
			accountsDto.add(mapper.map(account, UserDetailsDto.class));
			
		}
		return accountsDto;
	}
	
	public Accounts dtoToAccount(UserDetailsDto userDetails) {
		return mapper.map(userDetails, Accounts.class);
	}
}
