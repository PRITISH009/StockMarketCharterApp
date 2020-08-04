package com.stock_exchange_service.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stock_exchange_service.model.RegisteredCompanies;

@Repository
public interface RegisteredCompanyDao extends CrudRepository<RegisteredCompanies, Integer> {

}
