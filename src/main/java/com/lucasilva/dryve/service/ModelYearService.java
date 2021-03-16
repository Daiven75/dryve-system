package com.lucasilva.dryve.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasilva.dryve.enums.ErroType;
import com.lucasilva.dryve.model.ModelYear;
import com.lucasilva.dryve.repository.ModelYearRepository;
import com.lucasilva.dryve.service.exceptions.ObjectNotFoundException;

@Service
public class ModelYearService {

	@Autowired
	private ModelYearRepository modelYearRepository;
	
	public List<ModelYear> findByYear(Integer year) {
		return modelYearRepository.findByYear(year);
	}
	
	public ModelYear findByModelIdAndYear(String modelId, Integer year) {
		Optional<ModelYear> modelYear = modelYearRepository.findByModelIdAndYear(modelId, year);
		return modelYear.orElseThrow(
				() -> new ObjectNotFoundException(ErroType.MODEL_YEAR_NOT_FOUND.toString()));
	}
}