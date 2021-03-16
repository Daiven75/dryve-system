package com.lucasilva.dryve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasilva.dryve.model.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model, String>{

}