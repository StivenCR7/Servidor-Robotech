package com.spring.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.web.model.Encuentros;

public interface EncuentrosRepository extends JpaRepository<Encuentros, Integer>{

	 // Obtener encuentros por ronda
    List<Encuentros> findByRondas_Id(Integer rondaId);
}
