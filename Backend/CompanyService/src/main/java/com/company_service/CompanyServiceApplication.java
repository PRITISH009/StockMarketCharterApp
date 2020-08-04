package com.company_service;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.company_service.model.BOD;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class CompanyServiceApplication{
	
	public static void main(String[] args) {
		SpringApplication.run(CompanyServiceApplication.class, args);
		
	}
}
