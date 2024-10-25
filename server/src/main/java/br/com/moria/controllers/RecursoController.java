package br.com.moria.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.moria.models.Recurso;
import br.com.moria.services.interfaces.IRecursoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/recursos")
@Validated
public class RecursoController {

    @Autowired
    private IRecursoService recursoService;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Recurso recurso) {
        try {
            Recurso createdRecurso = recursoService.create(recurso);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRecurso);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            	Map.of("code", HttpStatus.BAD_REQUEST.value(), "message", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recurso> update(@PathVariable int id, @RequestBody Recurso recurso) {
        recurso.setId(id);
        try {
            Recurso updatedRecurso = recursoService.update(recurso);
            return ResponseEntity.ok(updatedRecurso);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        try {
            recursoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Recurso>> findAll() {
        List<Recurso> recursos = recursoService.findAll();
        return ResponseEntity.ok(recursos);
    }
}
