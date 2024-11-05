package br.com.moria.controllers;

import java.util.List;

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
@CrossOrigin(origins = "https://proud-wave-0042c520f.5.azurestaticapps.net")
public class InscricaoController {

    @Autowired
    private IInscricaoService inscricaoService;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Inscricao inscricao) {
        Inscricao createdInscricao = inscricaoService.create(inscricao);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInscricao);
    }

    @PutMapping("/confirmar")
    public ResponseEntity<Inscricao> confirmarInscricao(@RequestParam int membroId, @RequestParam int eventoId) {
        Inscricao updatedInscricao = inscricaoService.updateStatusInscricao(membroId, eventoId);
        return ResponseEntity.ok(updatedInscricao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inscricao> update(@PathVariable int id, @RequestBody Inscricao inscricao) {
        inscricao.setId(id);
        Inscricao updatedInscricao = inscricaoService.update(inscricao);
        return ResponseEntity.ok(updatedInscricao);
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

    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<List<Inscricao>> findInscricoesById(@PathVariable int eventoId) {
    	List<Inscricao> inscricoes = inscricaoService.findInscricoesByEventoId(eventoId);
    	return ResponseEntity.ok(inscricoes);
    }
}