package com.spring.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.web.model.Rondas;

public interface RondasRepository extends JpaRepository<Rondas, Integer>{

	List<Rondas> findByCategorias_Id(Integer categoriaId); // Método para obtener rondas por categoría
}
