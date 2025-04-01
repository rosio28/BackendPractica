package com.backend.prac1.service;


import com.backend.prac1.model.Estudiante;
import com.backend.prac1.repository.EstudianteRepository;

import java.util.List;
import java.util.Optional;

public class EstudianteService {
    private final EstudianteRepository repository = new EstudianteRepository();

    public List<Estudiante> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Estudiante> obtenerPorId(Long id) {
        return repository.obtenerPorId(id);
    }

    public Estudiante guardar(Estudiante estudiante) {
        return repository.guardar(estudiante);
    }

    public boolean eliminar(Long id) {
        return repository.eliminar(id);
    }
}
