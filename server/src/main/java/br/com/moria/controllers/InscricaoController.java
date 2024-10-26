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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.moria.enums.StatusParticipacao;
import br.com.moria.models.Inscricao;
import br.com.moria.services.interfaces.IInscricaoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/inscricoes")
@Validated
public class InscricaoController {

    @Autowired
    private IInscricaoService inscricaoService;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Inscricao inscricao) {
        try {
            System.out.println("Desgraca de controller");
            Inscricao createdInscricao = inscricaoService.create(inscricao);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdInscricao);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("code", HttpStatus.BAD_REQUEST.value(), "message", e.getMessage()));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("code", HttpStatus.NOT_FOUND.value(), "message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                Map.of("code", HttpStatus.INTERNAL_SERVER_ERROR.value(), "message", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inscricao> update(@PathVariable int id, @RequestBody Inscricao inscricao) {
        inscricao.setId(id);
        try {
            Inscricao updatedInscricao = inscricaoService.update(inscricao);
            return ResponseEntity.ok(updatedInscricao);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        try {
            inscricaoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Inscricao>> findAll() {
        List<Inscricao> inscricoes = inscricaoService.findAll();
        return ResponseEntity.ok(inscricoes);
    }

    @GetMapping("/status")
    public ResponseEntity<List<Inscricao>> findStatusParticipacao(@RequestParam StatusParticipacao status) {
        List<Inscricao> inscricoes = inscricaoService.findByStatusParticipacao(status);
        return ResponseEntity.ok(inscricoes);
    }

    @GetMapping("/inscrito")
    public ResponseEntity<Boolean> isInscrito(@RequestParam int id) {
        boolean inscrito = inscricaoService.isInscrito(id);
        return ResponseEntity.ok(inscrito);
    }
}