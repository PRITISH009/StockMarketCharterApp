package com.stockprice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StockPrice 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String companyCode;
	private String stockExchangeName;
	private double price;
	private String date;
	private String time;
}
