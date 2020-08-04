package com.feing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping(value = "/feing")
public class FeingClientController {
	private CompanyClient companyClient;
	
	@Autowired
	public FeingClientController(CompanyClient companyClient) {
		this.companyClient = companyClient;
	}
	
	@GetMapping(value = "{id}")
	@HystrixCommand(fallbackMethod = "defaultResponse")
	public ResponseEntity<String> TestingFunction(@PathVariable(name = "id") String id){
		String response = companyClient.test(id);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<String> defaultResponse(String err) {
		System.out.println("You are seeing this fallback response because the underlying microservice is down.");
		err = "Fallback error as the microservice is down.";
		return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
