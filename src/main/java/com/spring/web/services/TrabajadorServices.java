package com.spring.web.services;

import java.util.List;

import com.spring.web.model.Trabajadores;

public interface TrabajadorServices {

	public List<Trabajadores> getAllTrabajadores();
	
	public Trabajadores seleccionarEmailTrabajadores(String correo);
	
	public Trabajadores seleccionarIdTrabajadores(Integer id);
	
	public Trabajadores saveTrabajadores(Trabajadores trabajador);
	
	void deleteTrabajadores(Integer id);
}
