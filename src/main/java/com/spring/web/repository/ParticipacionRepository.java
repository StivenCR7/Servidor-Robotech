package com.spring.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.web.model.Categorias;
import com.spring.web.model.Participaciones;
import com.spring.web.model.Robots;

public interface ParticipacionRepository extends JpaRepository<Participaciones, Integer> {


	// Método para encontrar participaciones por categoría
	List<Participaciones> findByCategorias(Categorias categoria);
    
    boolean existsByRobotsAndCategorias(Robots robot, Categorias categoria);

	long countByCategorias(Categorias categoria);

}
