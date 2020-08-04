package com.company_service.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.company_service.dao.BoDDAO;
import com.company_service.dao.CompanyDAO;
import com.company_service.dao.SectorDAO;
import com.company_service.dto.BodDto;
import com.company_service.dto.CompanyDto;
import com.company_service.dto.SectorDto;
import com.company_service.exceptions.CompanyNotFoundException;
import com.company_service.mapper.CompanyServiceMapper;
import com.company_service.model.BOD;
import com.company_service.model.Company;
import com.company_service.model.Sector;
import com.company_service.shared.DetailsObject;
import com.company_service.shared.RegistrationObject;
import com.company_service.shared.SectorDetails;
import com.company_service.stockExchangeClient.StockExchangeClient;

@Service
@EnableTransactionManagement
public class CompanyServiceImpl implements CompanyService{
	private CompanyDAO companyDao;
	private BoDDAO boDDao;
	private SectorDAO sectorDao;
	
	private CompanyServiceMapper mapper;
	private StockExchangeClient stockExchangeClient;

	
	@Autowired
	public CompanyServiceImpl(CompanyDAO companyDao, BoDDAO bodDoa, SectorDAO sectorDao, CompanyServiceMapper mapper, StockExchangeClient client) {
		this.companyDao = companyDao;
		this.boDDao = bodDoa;
		this.sectorDao = sectorDao;
		this.mapper = mapper;
		this.stockExchangeClient = client;
	}
	
	@Transactional
	@Override
	public DetailsObject addNewCompany(DetailsObject companyDetails) {
	
		//Check if Company Already Exists
		if(companyDao.findByCompanyName(companyDetails.getCompanyDetails().getCompanyName()).isPresent()) {
			return null;
		}
		else {
			
			//Get Company, Sector and Board Of Directors Details from Details Object
			CompanyDto companyDto = companyDetails.getCompanyDetails();
			SectorDto sectorDto = companyDetails.getSectorDetails();
			ArrayList<BodDto> bodDtoList = companyDetails.getBodDetails();
			
			//Create an Entity Object of Company company and Sector sector and Set all the value of Sector Object to be referenced
			//in the Company Object to sector
			Company company = mapper.dtoToCompany(companyDto);
			Sector sector = sectorDao.findBySectorName(sectorDto.getSectorName()).get();
			company.setSector(sector);
			
			//Save the Company Details in the company Table
			companyDao.save(company);
			
			//Get Complete Company Object for referencing in the Board of Directors by company name from Database.
			Company completeCompanyObject = companyDao.findByCompanyName(company.getCompanyName()).get();
			
			//Create an Array List for Storing and Saving all the Board of Directors
			ArrayList<BOD> boardOfDirectors = new ArrayList<>();
			BOD director;
			
			//Loop Through all the Board of Directors Dto object, set company Reference and add it to List of Directors
			for(BodDto directorDto : bodDtoList) {
				director = mapper.dtoToBod(directorDto);
				director.setCompany(completeCompanyObject);
				boardOfDirectors.add(director);
			}
			//Save all the Board of Directors
			boDDao.saveAll(boardOfDirectors);
		}
		
		return companyDetails;
	}

	
	
	@Transactional
	@Override
	public Iterable<Company> getAllCompanies() {
		return companyDao.findAll();
	}
	
	@Transactional
	@Override
	public DetailsObject updateCompanyDetails(DetailsObject companyDetails) {
		if(companyDao.findByCompanyName(companyDetails.getCompanyDetails().getCompanyName()).isPresent()) {
			CompanyDto companyDto = companyDetails.getCompanyDetails();
			ArrayList<BodDto> bodDto = companyDetails.getBodDetails();
			SectorDto sectorDto = companyDetails.getSectorDetails();
			
			Company company = companyDao.findByCompanyName(companyDto.getCompanyName()).get();
			
			if(companyDto.getCeo() != company.getCeo())
				company.setCeo(companyDto.getCeo());
			
			if(companyDto.getCompanyTurnOver() != company.getCompanyTurnOver())
				company.setCompanyTurnOver(companyDto.getCompanyTurnOver());
			
			if(companyDto.getWriteUp() != company.getWriteUp())
				company.setWriteUp(companyDto.getWriteUp());
			
			if(sectorDto.getSectorName() != company.getSector().getSectorName()) {
				company.setSector(sectorDao.findBySectorName(sectorDto.getSectorName()).get());
			}
			
			companyDao.save(company);
			
			boDDao.deleteByCompanyId(company.getCompanyId());
			
			boDDao.saveAll(mapper.dtoListToBod(bodDto, company));
			
			return companyDetails;

		}
		return null;
	}

	@Transactional
	@Override
	public Iterable<BOD> getAllBoardMembersByCompanyName(String companyName) {
		Company company = companyDao.findByCompanyName(companyName).get();
		return boDDao.findByCompanyId(company.getCompanyId());
	}

	@Transactional
	@Override
	public SectorDetails addNewSector(SectorDetails sectorDetails) {
		if(sectorDao.findBySectorName(sectorDetails.getSectorName()).isPresent()) {
			return null;
		}
		sectorDao.save(mapper.detailsToSector(sectorDetails));
		return sectorDetails;
	}
	
	@Transactional
	@Override
	public CompanyDto getCompanyByCompanyName(String name) {
		if(companyDao.findByCompanyName(name).isPresent()) {
			return mapper.CompanyToDto(companyDao.findByCompanyName(name).get());
		}
		return null;
	}
	
	@Transactional
	@Override
	public String testStockExchange() {
		return stockExchangeClient.testStockExchange();
	}

	@Override
	public String registerCompany(String companyName, String stockExchange) {
		return stockExchangeClient.registerCompany(companyName, stockExchange);
	}
	
	
}
