package com.lucasilva.dryve.dto;

import com.lucasilva.dryve.model.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class VehiclePageResponse {

	public VehiclePageResponse(Vehicle vehicle) {
		this.id = vehicle.getId();
		this.board = vehicle.getBoard();
		this.brand = vehicle.getBrand().getName();
		this.model = vehicle.getModel().getName();
	}
	
	private Long id; 
	private String board;
	private String brand;
	private String model;
}