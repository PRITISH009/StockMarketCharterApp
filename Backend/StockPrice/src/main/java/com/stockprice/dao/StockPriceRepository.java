package com.stockprice.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stockprice.model.StockPrice;

@Repository
public interface StockPriceRepository extends CrudRepository<StockPrice, String>
{
	public List<StockPrice> findByCompanyCodeAndStockExchangeName(String companyCode, String stockExchangeName); 
}
