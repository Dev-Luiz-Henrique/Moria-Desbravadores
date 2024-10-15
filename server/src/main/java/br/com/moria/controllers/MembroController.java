package br.com.moria.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.moria.models.Membro;
import br.com.moria.services.interfaces.IMembroService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/membros")
public class MembroController {

    @Autowired
    private IMembroService membroService;

    @PostMapping
    public ResponseEntity<Membro> create(@Valid @RequestBody Membro membro) {
        try {
            Membro createdMembro = membroService.create(membro);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdMembro);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Membro> update(@PathVariable int id, @RequestBody Membro membro) {
        membro.setId(id);
        Membro updatedMembro = membroService.update(membro);
        return ResponseEntity.ok(updatedMembro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        try {
            membroService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Membro>> findAll() {
        List<Membro> membros = membroService.findAll();
        return ResponseEntity.ok(membros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Membro> findById(@PathVariable int id) {
        try {
            Membro membro = membroService.findById(id);
            return ResponseEntity.ok(membro);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}