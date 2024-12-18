package br.com.moria.controllers;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    public ResponseEntity<Recurso> create(@Valid @RequestBody Recurso recurso) {
        Recurso createdRecurso = recursoService.create(recurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRecurso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recurso> update(@PathVariable int id, @RequestBody @NotNull Recurso recurso) {
        recurso.setId(id);
        Recurso updatedRecurso = recursoService.update(recurso);
        return ResponseEntity.ok(updatedRecurso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        recursoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Recurso>> findAll() {
        List<Recurso> recursos = recursoService.findAll();
        return ResponseEntity.ok(recursos);
    }

    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<List<Recurso>> findRecursosById(@PathVariable int eventoId) {
        List<Recurso> recursos = recursoService.findRecursosByEvento(eventoId);
        return ResponseEntity.ok(recursos);
    }
}
