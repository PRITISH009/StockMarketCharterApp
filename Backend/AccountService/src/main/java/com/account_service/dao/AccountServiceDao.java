package com.account_service.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.account_service.model.Accounts;

@Repository
public interface AccountServiceDao extends CrudRepository<Accounts, Integer> {
	Optional<Accounts> findByUsername(String username);
}
