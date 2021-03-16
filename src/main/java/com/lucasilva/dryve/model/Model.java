package com.lucasilva.dryve.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Entity
public class Model {

	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	private String name;
	
	@ManyToOne()
	@JoinColumn(name="brand_id")
	private Brand brand;
	
	@JsonIgnore
	@OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
	private List<ModelYear> modelYear = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="model")
	private List<Vehicle> vehicle = new ArrayList<>();
	
	public Model(String id, String name, Brand brand) {
		this.id = id;
		this.name = name;
		this.brand = brand;
	}
}