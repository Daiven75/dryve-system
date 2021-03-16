package com.lucasilva.dryve.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class VehicleDTO {

	@NotEmpty(message = "Mandatory filling")
	private String board;
	
	@NotEmpty(message = "Mandatory filling")
	private String brandId;
	
	@NotEmpty(message = "Mandatory filling")
	private String modelId;
	
	@NotNull(message = "Mandatory filling")
	private Double adPrice;
	
	@NotNull(message = "Mandatory filling")
	private Integer year;
}