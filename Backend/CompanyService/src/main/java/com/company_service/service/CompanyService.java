package com.company_service.service;


import com.company_service.dto.CompanyDto;
import com.company_service.exceptions.CompanyNotFoundException;
import com.company_service.model.BOD;
import com.company_service.model.Company;
import com.company_service.shared.DetailsObject;
import com.company_service.shared.RegistrationObject;
import com.company_service.shared.SectorDetails;

public interface CompanyService {
	DetailsObject addNewCompany(DetailsObject companyDetails);
	Iterable<Company> getAllCompanies();
	DetailsObject updateCompanyDetails(DetailsObject companyDetails);
	Iterable<BOD> getAllBoardMembersByCompanyName(String companyName);
	SectorDetails addNewSector(SectorDetails sectorDetails);
	CompanyDto getCompanyByCompanyName(String name);
	String testStockExchange();
	String registerCompany(String companyName, String stockExchange);
}
