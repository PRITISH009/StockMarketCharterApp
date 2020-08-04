package com.stock_exchange_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockExchangeDto {
	private String name;
	private String brief;
	private String contactAddress;
	private String Remarks;
}
