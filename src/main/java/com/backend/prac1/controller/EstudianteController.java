package com.backend.prac1.controller;

import com.backend.prac1.model.Estudiante;
import com.backend.prac1.dto.EstudianteDTO;
import com.backend.prac1.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @GetMapping
    public List<Estudiante> listarTodos() {
        return estudianteRepository.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> obtenerPorId(@PathVariable Long id) {
        Optional<Estudiante> estudiante = estudianteRepository.obtenerPorId(id);
        if (estudiante.isPresent()) {
            return ResponseEntity.ok(estudiante.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Estudiante> crearEstudiante(@RequestBody EstudianteDTO estudianteDTO) {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(estudianteDTO.getNombre());
        estudiante.setEdad(estudianteDTO.getEdad());
        estudiante.setEmail(estudianteDTO.getEmail()); // Agregado el email
        
        Estudiante estudianteGuardado = estudianteRepository.guardar(estudiante);
        return ResponseEntity.status(HttpStatus.CREATED).body(estudianteGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminado = estudianteRepository.eliminar(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizarEstudiante(@PathVariable Long id, @RequestBody Estudiante estudianteActualizado) {
        Optional<Estudiante> estudiante = estudianteRepository.obtenerPorId(id);
        if (estudiante.isPresent()) {
            Estudiante estudianteExistente = estudiante.get();
            estudianteExistente.setNombre(estudianteActualizado.getNombre());
            estudianteExistente.setEdad(estudianteActualizado.getEdad());
            estudianteExistente.setEmail(estudianteActualizado.getEmail()); // Agregado el email
            estudianteRepository.guardar(estudianteExistente);
            return ResponseEntity.ok(estudianteExistente);
        }
        return ResponseEntity.notFound().build();
    }
}
