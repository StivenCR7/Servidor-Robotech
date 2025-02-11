package com.spring.web.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.web.repository.TorneoRepository;
import com.spring.web.model.*;

@Service
public class TorneoServices {

	@Autowired
	private TorneoRepository torneoRepository;

	public Torneos crearTorneo(Torneos torneo) {
		return torneoRepository.save(torneo);
	}

	public List<Torneos> obtenerTodosLosTorneos() {
		return torneoRepository.findAll();
	}

	public Torneos obtenerTorneoPorId(Integer id) {
		return torneoRepository.findById(id).orElseThrow(() -> new RuntimeException("Torneo no encontrado"));
	}

	public void eliminarTorneo(Integer id) {
		torneoRepository.deleteById(id);
	}
}
