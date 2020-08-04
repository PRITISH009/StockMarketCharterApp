package com.stock_exchange_service.service;

import com.stock_exchange_service.dto.StockExchangeDto;
import com.stock_exchange_service.model.StockExchange;

public interface StockExchangeService {
	Iterable<StockExchange> getAllStockExchanges();
	StockExchangeDto createNewExchange(StockExchangeDto stockExchangeDetails);
	StockExchangeDto updateExchangeDetails(StockExchangeDto updatedDetails);
	String registerCompany(String companyName, String stockExchangeName);
}
