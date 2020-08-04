package com.stock_exchange_service.companyclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stock_exchange_service.shared.CompanyDetails;

@FeignClient("company-service")
public interface CompanyClient {
	
	@RequestMapping(path = "company-service/getCompany/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CompanyDetails getCompanyByName(@PathVariable String name);
}
