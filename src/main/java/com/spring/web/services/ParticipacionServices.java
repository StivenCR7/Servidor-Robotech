package com.spring.web.services;

import java.util.List;

import com.spring.web.model.Participaciones;

public interface ParticipacionServices {

	List<Participaciones> ObtenerAllParticipaciones();
	Participaciones saveParticipaciones(Participaciones participacion);
	Participaciones ObtenerPorIdParticipacion(Integer id);
	
}
