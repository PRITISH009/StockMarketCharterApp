package com.company_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.company_service.dao.BoDDAO;
import com.company_service.dao.CompanyDAO;
import com.company_service.dao.SectorDAO;
import com.company_service.dto.CompanyDto;
import com.company_service.dto.SectorDto;
import com.company_service.exceptions.CompanyNotFoundException;
import com.company_service.model.BOD;
import com.company_service.model.Company;
import com.company_service.model.Sector;
import com.company_service.service.CompanyServiceImpl;
import com.company_service.shared.DetailsObject;
import com.company_service.shared.RegistrationObject;
import com.company_service.shared.SectorDetails;

@RestController
@RequestMapping("company-service")
public class CompanyController {
	
	
	@Autowired
	private CompanyServiceImpl companyService;
	
	@RequestMapping(path = "/createCompany", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DetailsObject> addNewCompany(@RequestBody DetailsObject companyDetails){
		DetailsObject result = companyService.addNewCompany(companyDetails);
		if(result != null)
			return ResponseEntity.status(HttpStatus.CREATED).body(result);
		else
			return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
	}
	
	@RequestMapping(path = "/allCompanies", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Company>> getAllCompanies(){
		return ResponseEntity.status(HttpStatus.OK).body(companyService.getAllCompanies());
	}
	
	@RequestMapping(path = "/updateCompany", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DetailsObject> updateCompanyDetails(@RequestBody DetailsObject companyDetails){
		return ResponseEntity.status(HttpStatus.OK).body(companyService.updateCompanyDetails(companyDetails));
	}
	
	
	@RequestMapping(path="/getBoardMembers/{companyName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getBoardOfDirectorsOfCompany(@PathVariable String companyName){
		return ResponseEntity.status(HttpStatus.OK).body(companyService.getAllBoardMembersByCompanyName(companyName));
	}
	
	@RequestMapping(path="/addSector", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SectorDetails> addNewSector(@RequestBody SectorDetails sectorDetails){
		return ResponseEntity.status(HttpStatus.CREATED).body(companyService.addNewSector(sectorDetails));
	}
	
	@RequestMapping(path = "/getCompany/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CompanyDto> getCompanybyName(@PathVariable String name){
		CompanyDto result = companyService.getCompanyByCompanyName(name);
		if(result == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
		else
			return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@RequestMapping(path = "/test-stock-exchange-service", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> testStockExchange(){
		return ResponseEntity.status(HttpStatus.OK).body(companyService.testStockExchange());
	}
	
	@RequestMapping(path = "/registerCompany/{companyName}/{stockExchange}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerCompany(@PathVariable String companyName, @PathVariable String stockExchange){
		return ResponseEntity.status(HttpStatus.OK).body(companyService.registerCompany(companyName, stockExchange));
	}
}

