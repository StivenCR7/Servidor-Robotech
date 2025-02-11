package com.spring.web.model;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Encuentros {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String resultado;
	private Date fecha_encuentro;
	
	//Relacion con rondas
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="rondas_id")
	private Rondas rondas;
	
	@ManyToMany(mappedBy = "encuentros")
	@JsonBackReference
    private Set<Robots> robots;

	public Encuentros() {}
	public Encuentros(Integer id, String resultado, Date fecha_encuentro, Rondas rondas, Set<Robots> robots) {
		super();
		this.id = id;
		this.resultado = resultado;
		this.fecha_encuentro = fecha_encuentro;
		this.rondas = rondas;
		this.robots = robots;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public Date getFecha_encuentro() {
		return fecha_encuentro;
	}

	public void setFecha_encuentro(Date fecha_encuentro) {
		this.fecha_encuentro = fecha_encuentro;
	}

	public Rondas getRondas() {
		return rondas;
	}

	public void setRondas(Rondas rondas) {
		this.rondas = rondas;
	}

	public Set<Robots> getRobots() {
		return robots;
	}

	public void setRobots(Set<Robots> robots) {
		this.robots = robots;
	}
	
}
