package com.lucasilva.dryve.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasilva.dryve.enums.ErroType;
import com.lucasilva.dryve.model.Model;
import com.lucasilva.dryve.repository.ModelRepository;
import com.lucasilva.dryve.service.exceptions.ObjectNotFoundException;

@Service
public class ModelService {

	@Autowired
	private ModelRepository modelRepository;
	
	public Model findById(String id) {
		Optional<Model> model = modelRepository.findById(id);
		return model.orElseThrow(
				() -> new ObjectNotFoundException(ErroType.MODEL_NOT_FOUND.toString()));
	}
}