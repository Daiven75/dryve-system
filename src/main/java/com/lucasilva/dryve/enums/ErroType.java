package com.lucasilva.dryve.enums;

import lombok.Getter;

@Getter
public enum ErroType {

	VALIDATION_ERRORS("DRV-0000", "Validation errors"),
	ERROR_CONSULT_PRICE_KBB("DRV-0001", "Error when querying price in the table KBB"),
	YEAR_NOT_FOUND("DRV-0002", "Year not found in tables"),
	BOARD_ALREADY_EXISTS("DRV-0003", "Already exists this board in other vehicle"),
	VEHICLE_NOT_FOUND("DRV-0004", "Vehicle not found"), 
	BRAND_NOT_FOUND("DRV-0005", "Brand not found"), 
	MODEL_NOT_FOUND("DRV-0006", "Model not found"), 
	MODEL_YEAR_NOT_FOUND("DRV-0007", "Model year not found");
	
	private String code;
	private String description;
	
	private ErroType(String code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public String toString() {
		return this.code + " - " + this.description;
	}
}