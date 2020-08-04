package com.stock_exchange_service.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stock_exchange_service.model.StockExchange;

@Repository
public interface StockExchangeDAO extends CrudRepository<StockExchange, Integer>{
	Optional<StockExchange> findByName(String name);
}
