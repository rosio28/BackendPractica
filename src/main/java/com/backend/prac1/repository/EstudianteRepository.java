package com.backend.prac1.repository;

import com.backend.prac1.model.Estudiante;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteRepository {
    private final List<Estudiante> estudiantes = new ArrayList<>();
    private Long contadorId = 1L;

    public List<Estudiante> listarTodos() {
        return estudiantes;
    }

    public Optional<Estudiante> obtenerPorId(Long id) {
        return estudiantes.stream()
                .filter(est -> est.getId().equals(id))
                .findFirst();
    }

    public Estudiante guardar(Estudiante estudiante) {
        if (estudiante.getId() == null) {
            estudiante.setId(contadorId++);
        }
        estudiantes.add(estudiante);
        return estudiante;
    }

    public boolean eliminar(Long id) {
        return estudiantes.removeIf(est -> est.getId().equals(id));
    }
}
