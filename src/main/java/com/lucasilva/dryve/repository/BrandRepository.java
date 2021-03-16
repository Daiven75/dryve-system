package com.lucasilva.dryve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasilva.dryve.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String>{

}