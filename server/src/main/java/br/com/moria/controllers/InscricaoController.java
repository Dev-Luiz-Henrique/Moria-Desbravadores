package br.com.moria.controllers;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

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
            Inscricao createdInscricao = inscricaoService.create(inscricao);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdInscricao);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("code", HttpStatus.BAD_REQUEST.value(), "message", e.getMessage()));
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Inscricao> updateStatusParticipacao(@PathVariable int id, @RequestBody StatusParticipacao status) {
        try {
            Inscricao updatedInscricao = inscricaoService.updateStatusParticipacao(id, status);
            return ResponseEntity.ok(updatedInscricao);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/{id}/autorizacao")
    public ResponseEntity<String> uploadAutorizacaoResponsavel(@PathVariable int id, @RequestParam("file") MultipartFile file) {
    	if (file.isEmpty()) {
			return ResponseEntity.badRequest().body("Nenhum arquivo foi enviado");
		}

        try {
            Inscricao inscricao = inscricaoService.updateAutorizacaoResponsavel(id, file);
            return ResponseEntity.ok("Autorização do Responsável salva em: " + inscricao.getAutorizacao());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao fazer upload da autorização do responsável: " + e.getMessage());
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
        List<Inscricao> inscricoes = inscricaoService.findStatusParticipacao(status);
        return ResponseEntity.ok(inscricoes);
    }
}
