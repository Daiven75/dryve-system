package com.lucasilva.dryve.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasilva.dryve.enums.ErroType;
import com.lucasilva.dryve.model.Brand;
import com.lucasilva.dryve.repository.BrandRepository;
import com.lucasilva.dryve.service.exceptions.ObjectNotFoundException;

@Service
public class BrandService {

	@Autowired
	private BrandRepository brandRepository;
	
	public Brand findById(String id) {
		Optional<Brand> brand = brandRepository.findById(id);
		return brand.orElseThrow(
				() -> new ObjectNotFoundException(ErroType.BRAND_NOT_FOUND.toString()));
	}
}