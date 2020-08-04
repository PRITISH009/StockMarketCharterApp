package com.stock_exchange_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stock_exchange_service.dto.StockExchangeDto;
import com.stock_exchange_service.model.StockExchange;
import com.stock_exchange_service.service.StockExchangeServiceImpl;

@RestController
@RequestMapping("stock-exchange-service")
public class StockExchangeController {
	@Autowired
	private StockExchangeServiceImpl stockExchangeService;
	
	@RequestMapping(path = "/getAllExchanges", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<StockExchange>> getAllStockExchanges(){
		return ResponseEntity.status(HttpStatus.OK).body(stockExchangeService.getAllStockExchanges());
	}
	
	@RequestMapping(path = "/addNewExchange", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockExchangeDto> addNewStockExchange(@RequestBody StockExchangeDto stockExchangeDetails){
		StockExchangeDto result = stockExchangeService.createNewExchange(stockExchangeDetails);
		if(result == null)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
		else
			return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@RequestMapping(path = "/updateExchange", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockExchangeDto> updateStockExchange(@RequestBody StockExchangeDto updatedDetails){
		StockExchangeDto result = stockExchangeService.updateExchangeDetails(updatedDetails);
		if(result == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
		else
			return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@RequestMapping(path = "/test-stock-exchange-service", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> testStockExchange(){
		return ResponseEntity.status(HttpStatus.OK).body("Testing 123");
	}
	
	@RequestMapping(path = "/register/{companyName}/{stockExchangeName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerCompany(@PathVariable String companyName, @PathVariable String stockExchangeName){
		return ResponseEntity.status(HttpStatus.OK).body(stockExchangeService.registerCompany(companyName, stockExchangeName));
	}
}
