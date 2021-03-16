package com.lucasilva.dryve.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lucasilva.dryve.model.ModelYear;

@Repository
public interface ModelYearRepository extends JpaRepository<ModelYear, String>{

	@Transactional(readOnly = true)
	List<ModelYear> findByYear(Integer year);
	
	@Transactional(readOnly = true)
	Optional<ModelYear> findByModelIdAndYear(String modelId, Integer year);
}