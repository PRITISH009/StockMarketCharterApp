package com.feing.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("company-service")
public interface CompanyClient {
	@GetMapping(value = "/testing/{id}")
	public String test(@PathVariable(name = "id") String id);
}
