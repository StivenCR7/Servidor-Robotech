package com.spring.web.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Competidores {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String apellido;
	@Column(unique=true,nullable = false,length = 8)
	private Integer dni;
	private String alias;
	@Column(unique=true,nullable = false)
	private String correo;
	private Integer edad;
	
	//Relacion con estados
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="estado_id")
	private Estados estados;
	
	//Relaciones con clubes
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="club_id")
	private Clubes clubes;
	
	

	public Competidores() {}

	public Competidores(Integer id, String nombre, String apellido, Integer dni, String alias, String correo,
			Integer edad, Estados estados, Clubes clubes) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.alias = alias;
		this.correo = correo;
		this.edad = edad;
		this.estados = estados;
		this.clubes = clubes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Estados getEstados() {
		return estados;
	}

	public void setEstados(Estados estados) {
		this.estados = estados;
	}

	public Clubes getClubes() {
		return clubes;
	}

	public void setClubes(Clubes clubes) {
		this.clubes = clubes;
	}

	
}
