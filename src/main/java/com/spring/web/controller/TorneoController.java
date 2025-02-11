package com.spring.web.controller;

import com.spring.web.model.Categorias;
import com.spring.web.model.Torneos;
import com.spring.web.services.CategoriaServices;
import com.spring.web.services.TorneoServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/torneos")
public class TorneoController {

	@Autowired
    private CategoriaServices categoriaService;
	
	@Autowired
    private TorneoServices torneoService;

	//crear el torneo
    @PostMapping("/crear")
    public ResponseEntity<Torneos> crearTorneo(@RequestBody Torneos torneo) {
        return new ResponseEntity<>(torneoService.crearTorneo(torneo), HttpStatus.CREATED);
    }

    //Listar todos los torneos
    @GetMapping("/listar")
    public List<Torneos> obtenerTodosLosTorneos() {
        return torneoService.obtenerTodosLosTorneos();
    }

    //Obtener torneo por ID
    @GetMapping("/obtener/{id}")
    public ResponseEntity<Torneos> obtenerTorneoPorId(@PathVariable Integer id) {
        return new ResponseEntity<>(torneoService.obtenerTorneoPorId(id), HttpStatus.OK);
    }

    //Eliminar torneo
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarTorneo(@PathVariable Integer id) {
        torneoService.eliminarTorneo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    //crear categoria
    @PostMapping("/{torneoId}/categorias")
    public ResponseEntity<Categorias> crearCategoria(@PathVariable Integer torneoId, @RequestBody Categorias categoria) {
        return new ResponseEntity<>(categoriaService.crearCategoria(torneoId, categoria), HttpStatus.CREATED);
    }

    //Obtener las categorias por torneo
    @GetMapping("/{torneoId}/todasCate")
    public List<Categorias> obtenerCategoriasPorTorneo(@PathVariable Integer torneoId) {
        return categoriaService.obtenerCategoriasPorTorneo(torneoId);
    }

    //Eliminar categoria por ID
    @DeleteMapping("/deleteCate/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Integer id) {
        categoriaService.eliminarCategoria(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
