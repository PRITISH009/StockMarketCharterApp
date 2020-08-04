package com.stock_exchange_service.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDetails {
	private String companyName;
	private float companyTurnOver;
	private String writeUp;
	private String ceo;
}
