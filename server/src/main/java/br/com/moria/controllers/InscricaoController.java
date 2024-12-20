package br.com.moria.controllers;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.moria.dtos.Inscricao.InscricaoCreateDTO;
import br.com.moria.dtos.Inscricao.InscricaoResponseDTO;
import br.com.moria.dtos.Inscricao.InscricaoUpdateDTO;
import br.com.moria.enums.StatusParticipacao;
import br.com.moria.services.interfaces.IInscricaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/inscricoes")
public class InscricaoController {

    private final IInscricaoService inscricaoService;

    @Autowired
    public InscricaoController(IInscricaoService inscricaoService) {
        this.inscricaoService = inscricaoService;
    }
    
    @PostMapping
    public ResponseEntity<InscricaoResponseDTO> create(@Valid @RequestBody InscricaoCreateDTO inscricaoCreateDTO) {
        InscricaoResponseDTO createdInscricao = inscricaoService.create(inscricaoCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInscricao);
    }

    @PutMapping("/confirmar")
    public ResponseEntity<InscricaoResponseDTO> confirmarInscricao(@RequestParam int membroId, @RequestParam int eventoId) {
        InscricaoResponseDTO updatedInscricao = inscricaoService.updateStatusInscricao(membroId, eventoId);
        return ResponseEntity.ok(updatedInscricao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InscricaoResponseDTO> update(@PathVariable int id, @RequestBody @NotNull InscricaoUpdateDTO inscricaoUpdateDTO) {
        inscricaoUpdateDTO.setId(id);
        InscricaoResponseDTO updatedInscricao = inscricaoService.update(inscricaoUpdateDTO);
        return ResponseEntity.ok(updatedInscricao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        inscricaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<InscricaoResponseDTO>> findAll() {
        List<InscricaoResponseDTO> inscricoes = inscricaoService.findAll();
        return ResponseEntity.ok(inscricoes);
    }

    @GetMapping("/status")
    public ResponseEntity<List<InscricaoResponseDTO>> findStatusParticipacao(@RequestParam StatusParticipacao status) {
        List<InscricaoResponseDTO> inscricoes = inscricaoService.findByStatusParticipacao(status);
        return ResponseEntity.ok(inscricoes);
    }

    @GetMapping("/inscrito")
    public ResponseEntity<Boolean> isInscrito(@RequestParam int id) {
        boolean inscrito = inscricaoService.isInscrito(id);
        return ResponseEntity.ok(inscrito);
    }

    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<List<InscricaoResponseDTO>> findInscricoesById(@PathVariable int eventoId) {
        List<InscricaoResponseDTO> inscricoes = inscricaoService.findInscricoesByEventoId(eventoId);
    	return ResponseEntity.ok(inscricoes);
    }
}
