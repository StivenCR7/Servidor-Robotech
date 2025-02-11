package com.spring.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.web.model.Categorias;

public interface CategoriasRepository extends JpaRepository<Categorias, Integer>{
	
	List<Categorias> findByTorneos_Id(Integer torneoId);
	
}
