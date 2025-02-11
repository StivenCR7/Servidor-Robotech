package com.spring.web.model;


import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Robots {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String peso;
	private String dimensiones;
	private String foto;
	
	// Relación con Estados
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="estados_id")
	private Estados estados;
	
	// Relación con Competidores
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "competidores_id")
    @JsonManagedReference 
    private Competidores competidores;

    // Relación con Categorías
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categorias_id")
    @JsonManagedReference 
    private Categorias categorias;


    //Relacion con encuentros 
    @ManyToMany
    @JoinTable(
        name = "robots_encuentros", // Nombre de la tabla intermedia
        joinColumns = @JoinColumn(name = "robots_id"), // Columna que referencia a Robots
        inverseJoinColumns = @JoinColumn(name = "encuentros_id") // Columna que referencia a Encuentros
    )
    private Set<Encuentros> encuentros;

    public Robots() {}
    
	public Robots(Integer id, String nombre, String peso, String dimensiones, String foto, Estados estados,
			Competidores competidores, Categorias categorias, Set<Encuentros> encuentros) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.peso = peso;
		this.dimensiones = dimensiones;
		this.foto = foto;
		this.estados = estados;
		this.competidores = competidores;
		this.categorias = categorias;
		this.encuentros = encuentros;
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


	public String getPeso() {
		return peso;
	}


	public void setPeso(String peso) {
		this.peso = peso;
	}


	public String getDimensiones() {
		return dimensiones;
	}


	public void setDimensiones(String dimensiones) {
		this.dimensiones = dimensiones;
	}


	public String getFoto() {
		return foto;
	}


	public void setFoto(String foto) {
		this.foto = foto;
	}


	public Estados getEstados() {
		return estados;
	}


	public void setEstados(Estados estados) {
		this.estados = estados;
	}


	public Competidores getCompetidores() {
		return competidores;
	}


	public void setCompetidores(Competidores competidores) {
		this.competidores = competidores;
	}


	public Categorias getCategorias() {
		return categorias;
	}


	public void setCategorias(Categorias categorias) {
		this.categorias = categorias;
	}


	public Set<Encuentros> getEncuentros() {
		return encuentros;
	}


	public void setEncuentros(Set<Encuentros> encuentros) {
		this.encuentros = encuentros;
	}
}
