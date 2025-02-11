package com.spring.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.web.model.Encuentros;
import com.spring.web.repository.EncuentrosRepository;

@Service
public class EncuentroService {

	@Autowired
    private EncuentrosRepository encuentroRepository;

    // Obtener encuentros por ronda
    public List<Encuentros> obtenerEncuentrosPorRonda(Integer rondaId) {
        return encuentroRepository.findByRondas_Id(rondaId);
    }
    
}
