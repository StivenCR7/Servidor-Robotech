package com.spring.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.web.model.Categorias;
import com.spring.web.model.Torneos;
import com.spring.web.repository.CategoriasRepository;
import com.spring.web.repository.TorneoRepository;

@Service
public class CategoriaServices {

	@Autowired
    private CategoriasRepository categoriaRepository;

    @Autowired
    private TorneoRepository torneoRepository;

    public Categorias crearCategoria(Integer torneoId, Categorias categoria) {
        Torneos torneo = torneoRepository.findById(torneoId)
                .orElseThrow(() -> new RuntimeException("Torneo no encontrado"));
        categoria.setTorneos(torneo);
        return categoriaRepository.save(categoria);
    }

    public List<Categorias> obtenerCategoriasPorTorneo(Integer torneoId) {
        return categoriaRepository.findByTorneos_Id(torneoId);
    }

    public void eliminarCategoria(Integer id) {
        categoriaRepository.deleteById(id);
    }
}
