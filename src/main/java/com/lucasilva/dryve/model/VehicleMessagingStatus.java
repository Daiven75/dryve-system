package com.lucasilva.dryve.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class VehicleMessagingStatus {

	private Vehicle vehicle;
	private String status;
	private String message;
}