package com.lucasilva.dryve.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucasilva.dryve.model.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class VehicleDetailsResponse {

	public VehicleDetailsResponse(Vehicle vehicle) {
		this.board = vehicle.getBoard();
		this.adPrice = vehicle.getAdPrice();
		this.year = vehicle.getYear();
		this.priceKBB = vehicle.getPriceKBB();
		this.dateRegistrer = vehicle.getDateRegistrer();
		this.brand = vehicle.getBrand().getName();
		this.model = vehicle.getModel().getName();
	}
	
	private String board;
	private Double adPrice;
	private Integer year;
	private Double priceKBB;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dateRegistrer;
	
	private String brand;
	private String model;
}