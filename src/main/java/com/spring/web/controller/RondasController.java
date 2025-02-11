package com.spring.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.web.model.Encuentros;
import com.spring.web.model.Rondas;
import com.spring.web.services.EncuentroService;
import com.spring.web.services.RondaServices;

@RestController
@RequestMapping("/rondas")
public class RondasController {

	@Autowired
	private RondaServices rondasService;

	@Autowired
	private EncuentroService encuentroService;

	@PostMapping("/generar/{categoriaId}")
	public ResponseEntity<List<Encuentros>> generarRondas(@PathVariable Integer categoriaId) {
		try {
			// Llamar al servicio para generar las rondas basadas en el formato de la
			// categoría
			List<Encuentros> encuentrosGenerados = rondasService.generarRondas(categoriaId);

			// Si la generación fue exitosa, devolver los encuentros generados
			return ResponseEntity.ok(encuentrosGenerados);
		} catch (RuntimeException e) {
			// Si ocurre un error (por ejemplo, categoría no encontrada o robots no
			// registrados), devolver un error 400
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@PostMapping("/agregarRobot/{encuentroId}/{robotId}")
	public ResponseEntity<String> agregarRobotEncuentro(@PathVariable Integer encuentroId,
			@PathVariable Integer robotId) {
		try {
			// Llamar al servicio para agregar un robot a un encuentro específico
			@SuppressWarnings("unused")
			Encuentros encuentro = rondasService.agregarRobotAEncuentro(encuentroId, robotId);

			// Si la operación fue exitosa, devolver un mensaje de éxito
			return ResponseEntity.ok("Robot agregado exitosamente al encuentro");
		} catch (RuntimeException e) {
			// Si ocurre algún error, devolver un error 400 con el mensaje
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al agregar el robot al encuentro");
		}
	}

	// Obtener encuentros por ronda
	@GetMapping("/encuentros/{rondaId}")
	public ResponseEntity<List<Encuentros>> obtenerEncuentrosPorRonda(@PathVariable Integer rondaId) {
		List<Encuentros> encuentros = encuentroService.obtenerEncuentrosPorRonda(rondaId);
		System.out.println(encuentros);
		return new ResponseEntity<>(encuentros, HttpStatus.OK);
	}

	// Endpoint para obtener todas las rondas de una categoría o torneo
	@GetMapping("/listar/{categoriaId}")
	public ResponseEntity<List<Rondas>> obtenerRondasPorCategoria(@PathVariable Integer categoriaId) {
		try {
			List<Rondas> rondas = rondasService.obtenerRondasPorCategoria(categoriaId);
			return new ResponseEntity<>(rondas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
