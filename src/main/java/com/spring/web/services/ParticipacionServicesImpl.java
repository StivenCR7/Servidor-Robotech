package com.spring.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.web.model.Participaciones;
import com.spring.web.repository.ParticipacionRepository;

@Service
public class ParticipacionServicesImpl implements ParticipacionServices{

	@Autowired
	private ParticipacionRepository participacionRe;

	@Override
	public List<Participaciones> ObtenerAllParticipaciones() {
		return participacionRe.findAll();
	}

	@Override
	public Participaciones saveParticipaciones(Participaciones participacion) {
		return participacionRe.save(participacion);
	}

	@Override
	public Participaciones ObtenerPorIdParticipacion(Integer id) {
		return participacionRe.findById(id).orElse(null);
	}
	
	
}
