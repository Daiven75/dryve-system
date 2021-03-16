package com.lucasilva.dryve.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucasilva.dryve.dto.VehicleDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Entity
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String board;
	private Double adPrice;
	private Integer year;
	private Double priceKBB;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dateRegistrer; 
	
	@ManyToOne
	@JoinColumn(name = "model_id")
	private Model model;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand;
	
	public Vehicle(VehicleDTO vehicleDTO) {
		this.board = vehicleDTO.getBoard();
		this.adPrice = vehicleDTO.getAdPrice();
		this.dateRegistrer = new Date();
		this.year = vehicleDTO.getYear();
	}
}