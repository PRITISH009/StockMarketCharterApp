package com.stock_exchange_service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.stock_exchange_service.dto.StockExchangeDto;
import com.stock_exchange_service.model.StockExchange;

@Component
public class StockExchangeMapper {
	private ModelMapper mapper = new ModelMapper();
	
	public StockExchange dtoToStockExchange(StockExchangeDto dto) {
		return mapper.map(dto, StockExchange.class);
	}
	
	public StockExchangeDto stockExchangeToDto(StockExchange exchange) {
		return mapper.map(exchange, StockExchangeDto.class);
	}
}
