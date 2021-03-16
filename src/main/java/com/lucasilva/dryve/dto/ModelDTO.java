package com.lucasilva.dryve.dto;

import com.lucasilva.dryve.model.Brand;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ModelDTO {

	private String name;
	
	private Brand brand;
}