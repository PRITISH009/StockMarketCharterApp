package com.stock_exchange_service.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.stock_exchange_service.companyclient.CompanyClient;
import com.stock_exchange_service.dao.RegisteredCompanyDao;
import com.stock_exchange_service.dao.StockExchangeDAO;
import com.stock_exchange_service.dto.StockExchangeDto;
import com.stock_exchange_service.mapper.StockExchangeMapper;
import com.stock_exchange_service.model.RegisteredCompanies;
import com.stock_exchange_service.model.StockExchange;

@Service
@EnableTransactionManagement
public class StockExchangeServiceImpl implements StockExchangeService {
	
	private StockExchangeDAO stockExchangeDao;
	private RegisteredCompanyDao registeredCompanyDao;
	private StockExchangeMapper mapper;
	
	private CompanyClient companyClient;
	
	@Autowired
	public StockExchangeServiceImpl(StockExchangeDAO dao, StockExchangeMapper mapper, RegisteredCompanyDao companyDao, CompanyClient companyClient) {
		this.stockExchangeDao = dao;
		this.mapper = mapper;
		this.registeredCompanyDao = companyDao;
		this.companyClient = companyClient;
	}
	

	@Transactional
	@Override
	public Iterable<StockExchange> getAllStockExchanges() {
		return stockExchangeDao.findAll();
	}
	
	@Transactional
	@Override
	public StockExchangeDto createNewExchange(StockExchangeDto stockExchangeDetails) {
		if(stockExchangeDao.findByName(stockExchangeDetails.getName()).isPresent()) {
			return null;
		}
		stockExchangeDao.save(mapper.dtoToStockExchange(stockExchangeDetails));
		return stockExchangeDetails;
	}

	@Transactional
	@Override
	public StockExchangeDto updateExchangeDetails(StockExchangeDto updatedDetails) {
		if(stockExchangeDao.findByName(updatedDetails.getName()).isPresent()) {
			StockExchange exchange = stockExchangeDao.findByName(updatedDetails.getName()).get();
			stockExchangeDao.save(exchange.builder()
				.brief(updatedDetails.getBrief())
				.contactAddress(updatedDetails.getContactAddress())
				.Remarks(updatedDetails.getRemarks())
				.build());
			return updatedDetails;
		}
		return null;
	}


	@Override
	public String registerCompany(String companyName, String stockExchangeName) {
		RegisteredCompanies newRegistration = new RegisteredCompanies();
		newRegistration.setCompanyName(companyName);
		System.out.println(stockExchangeDao.findByName(stockExchangeName).get());
		newRegistration.setStockExchangeId(stockExchangeDao.findByName(stockExchangeName).get().getId());
		registeredCompanyDao.save(newRegistration);
		return companyName;
	}
	
	
}
