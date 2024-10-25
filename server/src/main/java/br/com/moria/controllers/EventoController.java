package br.com.moria.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import br.com.moria.models.Evento;
import br.com.moria.services.interfaces.IEventoService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/eventos")
@Validated
public class EventoController {

    @Autowired
    private IEventoService eventoService;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Evento evento) {
        try {
            Evento createdEvento = eventoService.create(evento);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEvento);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("code", HttpStatus.BAD_REQUEST.value(), "message", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> update(@PathVariable int id, @Valid @RequestBody Evento evento) {
        evento.setId(id);
        try {
            Evento updatedEvento = eventoService.update(evento);
            return ResponseEntity.ok(updatedEvento);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        try {
            eventoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Evento>> findAll() {
        List<Evento> eventos = eventoService.findAll();
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> findById(@PathVariable int id) {
        try {
            Evento evento = eventoService.findById(id);
            return ResponseEntity.ok(evento);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Evento>> findNomeContaining(@RequestParam(name="k") String keyword) {
        List<Evento> eventos = eventoService.findNomeContaining(keyword);
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<Evento>> findDataInicioInterval(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        List<Evento> eventos = eventoService.findDataInicioInterval(start, end);
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/data-inicio")
    public ResponseEntity<List<Evento>> findDataInicio(@RequestParam LocalDateTime date) {
        List<Evento> eventos = eventoService.findDataInicio(date);
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/data-fim")
    public ResponseEntity<List<Evento>> findDataFim(@RequestParam LocalDateTime date) {
        List<Evento> eventos = eventoService.findDataFim(date);
        return ResponseEntity.ok(eventos);
    }
}
