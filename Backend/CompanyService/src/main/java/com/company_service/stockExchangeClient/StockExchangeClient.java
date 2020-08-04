package com.company_service.stockExchangeClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.company_service.shared.RegistrationObject;

@FeignClient("stock-exchange-service")
public interface StockExchangeClient {
	
	@RequestMapping(path = "stock-exchange-service/test-stock-exchange-service", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String testStockExchange();
	
	@RequestMapping(path = "stock-exchange-service/register/{companyName}/{stockExchange}")
	public String registerCompany(@PathVariable String companyName, @PathVariable String stockExchange);
}
