package com.lucasilva.dryve.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lucasilva.dryve.model.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>{

	@Transactional(readOnly = true)
	Vehicle findByBoard(String board);
	
	@Transactional(readOnly = true)
	Page<Vehicle> findByBoardContaining(String board, Pageable pageRequest);
}