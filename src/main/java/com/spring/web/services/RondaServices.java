package com.spring.web.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.web.model.Categorias;
import com.spring.web.model.Encuentros;
import com.spring.web.model.Robots;
import com.spring.web.model.Rondas;
import com.spring.web.repository.CategoriasRepository;
import com.spring.web.repository.EncuentrosRepository;
import com.spring.web.repository.RobotsRepository;
import com.spring.web.repository.RondasRepository;

@Service
public class RondaServices {

	@Autowired
	private RobotsRepository robotsRepository;

	@Autowired
	private EncuentrosRepository encuentrosRepository;

	@Autowired
	private CategoriasRepository categoriasRepository;

	@Autowired
	private RondasRepository rondasRepository;
	
	public List<Rondas> obtenerRondasPorCategoria(Integer categoriaId) {
        return rondasRepository.findByCategorias_Id(categoriaId); // Consulta de rondas por categoría
    }

	public List<Encuentros> generarRondas(Integer categoriaId) {
		// Buscar la categoría por su ID
		Categorias categoria = categoriasRepository.findById(categoriaId)
				.orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

		// Obtener los robots de la categoría
		List<Robots> robots = robotsRepository.findByCategorias_Id(categoriaId);
		if (robots.isEmpty()) {
			throw new RuntimeException("No hay robots registrados en esta categoría");
		}

		List<Encuentros> encuentros = new ArrayList<>();

		// Generación de encuentros según el formato de la categoría
		switch (categoria.getFormato().toLowerCase()) {
		case "suizo":
			encuentros = generarFormatoSuizo(robots, categoria);
			break;
		case "todos contra todos":
			encuentros = generarFormatoTodosContraTodos(robots, categoria);
			break;
		default:
			throw new RuntimeException("Formato de categoría desconocido: " + categoria.getFormato());
		}

		// Guardar los encuentros generados y devolverlos
		return encuentrosRepository.saveAll(encuentros);
	}

	// Añadir robots al encuentro
	public void agregarRobotAEncuentro(Encuentros encuentro, Robots robot) {
		// Implementación de la generación de rondas
	}

	public Encuentros agregarRobotAEncuentro(Integer encuentroId, Integer robotId) {
		// Buscar el encuentro por su ID
		Encuentros encuentro = encuentrosRepository.findById(encuentroId)
				.orElseThrow(() -> new RuntimeException("Encuentro no encontrado"));

		// Buscar el robot por su ID
		Robots robot = robotsRepository.findById(robotId)
				.orElseThrow(() -> new RuntimeException("Robot no encontrado"));

		// Agregar el robot al encuentro
		encuentro.getRobots().add(robot);

		// Guardar el encuentro actualizado
		return encuentrosRepository.save(encuentro);
	}

	// Generar el formato "Suizo"
	private List<Encuentros> generarFormatoSuizo(List<Robots> robots, Categorias categoria) {
		List<Encuentros> encuentros = new ArrayList<>();
		Collections.shuffle(robots); // Mezclar robots aleatoriamente

		// Crear y guardar la ronda
		Rondas ronda = new Rondas();
		ronda.setNombre("Ronda 1 - Formato Suizo");
		ronda.setTipo("Clasificatoria");
		ronda.setCategorias(categoria); // Asignar la categoría a la ronda
		rondasRepository.save(ronda);

		// Emparejar robots
		for (int i = 0; i < robots.size() - 1; i += 2) {
			Encuentros encuentro = new Encuentros();
			Set<Robots> robotsEncuentro = new HashSet<>();
			robotsEncuentro.add(robots.get(i));
			robotsEncuentro.add(robots.get(i + 1));

			encuentro.setRobots(robotsEncuentro); // Asignar robots al encuentro
			encuentro.setRondas(ronda); // Asignar la ronda al encuentro
			encuentro.setFecha_encuentro(new Date()); // Fecha actual
			encuentros.add(encuentro);
		}

		// Si hay un número impar de robots, dejar uno libre
		if (robots.size() % 2 != 0) {
			Encuentros encuentroLibre = new Encuentros();
			Set<Robots> robotsLibre = new HashSet<>();
			robotsLibre.add(robots.get(robots.size() - 1));
			encuentroLibre.setRobots(robotsLibre); // Asignar el robot libre
			encuentroLibre.setRondas(ronda); // Asignar la ronda
			encuentroLibre.setFecha_encuentro(new Date()); // Fecha actual
			encuentros.add(encuentroLibre);
		}

		return encuentrosRepository.saveAll(encuentros); // Guardar y devolver los encuentros
	}

	// Generar el formato "Todos contra todos"
	private List<Encuentros> generarFormatoTodosContraTodos(List<Robots> robots, Categorias categoria) {
		List<Encuentros> encuentros = new ArrayList<>();

		// Crear y guardar la ronda
		Rondas ronda = new Rondas();
		ronda.setNombre("Ronda 1 - Todos contra Todos");
		ronda.setTipo("Eliminatoria");
		ronda.setCategorias(categoria); // Asignar la categoría a la ronda
		rondasRepository.save(ronda);

		// Emparejar todos los robots entre sí
		for (int i = 0; i < robots.size(); i++) {
			for (int j = i + 1; j < robots.size(); j++) {
				Encuentros encuentro = new Encuentros();
				Set<Robots> robotsEncuentro = new HashSet<>();
				robotsEncuentro.add(robots.get(i));
				robotsEncuentro.add(robots.get(j));

				encuentro.setRobots(robotsEncuentro); // Asignar robots al encuentro
				encuentro.setRondas(ronda); // Asignar la ronda al encuentro
				encuentro.setFecha_encuentro(new Date()); // Fecha actual
				encuentros.add(encuentro);
			}
		}

		return encuentrosRepository.saveAll(encuentros); // Guardar y devolver los encuentros
	}

}
