package com.spring.web.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Participaciones {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer puesto;
	
	// Relación con Categorías
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categorias_id")
    private Categorias categorias;
    
    // Relación con robots
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "robots_id")
    @JsonBackReference
    private Robots robots;
    
    public Participaciones() {}
	public Participaciones(Integer id, Integer puesto, Categorias categorias, Robots robots) {
		super();
		this.id = id;
		this.puesto = puesto;
		this.categorias = categorias;
		this.robots = robots;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPuesto() {
		return puesto;
	}

	public void setPuesto(Integer puesto) {
		this.puesto = puesto;
	}

	public Categorias getCategorias() {
		return categorias;
	}

	public void setCategorias(Categorias categorias) {
		this.categorias = categorias;
	}

	public Robots getRobots() {
		return robots;
	}

	public void setRobots(Robots robots) {
		this.robots = robots;
	}
    
}
