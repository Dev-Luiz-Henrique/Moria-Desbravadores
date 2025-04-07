package br.com.moria.domains.inscricao;

import java.util.List;

import br.com.moria.domains.inscricao.dtos.InscricaoCreateDTO;
import br.com.moria.domains.inscricao.dtos.InscricaoResponseDTO;
import br.com.moria.domains.inscricao.dtos.InscricaoUpdateDTO;
import br.com.moria.domains.inscricao.enums.InscricaoStatusParticipacao;
import br.com.moria.domains.inscricao.services.IInscricaoCommandService;
import br.com.moria.domains.inscricao.services.IInscricaoQueryService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/inscricoes")
public class InscricaoController {

    private final IInscricaoCommandService inscricaoCommandService;
    private final IInscricaoQueryService inscricaoQueryService;

    @Autowired
    public InscricaoController(IInscricaoCommandService inscricaoCommandService,
                               IInscricaoQueryService inscricaoQueryService) {
        this.inscricaoCommandService = inscricaoCommandService;
        this.inscricaoQueryService = inscricaoQueryService;
    }

    @PreAuthorize("@authService.hasPermission('VIEW_INSCRICOES')")
    @PostMapping
    public ResponseEntity<InscricaoResponseDTO> create(@Valid @RequestBody InscricaoCreateDTO inscricaoCreateDTO) {
        InscricaoResponseDTO createdInscricao = inscricaoCommandService.create(inscricaoCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInscricao);
    }

    @PreAuthorize("@authService.hasPermission('VIEW_INSCRICOES')")
    @PutMapping("/confirmar")
    public ResponseEntity<InscricaoResponseDTO> confirmarInscricao(@RequestParam int membroId, @RequestParam int eventoId) {
        InscricaoResponseDTO updatedInscricao = inscricaoCommandService.updateStatusInscricao(membroId, eventoId);
        return ResponseEntity.ok(updatedInscricao);
    }

    @PreAuthorize("@authService.hasPermission('VIEW_INSCRICOES')")
    @PutMapping("/{id}")
    public ResponseEntity<InscricaoResponseDTO> update(@PathVariable int id, @RequestBody @NotNull InscricaoUpdateDTO inscricaoUpdateDTO) {
        inscricaoUpdateDTO.setId(id);
        InscricaoResponseDTO updatedInscricao = inscricaoCommandService.update(inscricaoUpdateDTO);
        return ResponseEntity.ok(updatedInscricao);
    }

    @PreAuthorize("@authService.hasPermission('VIEW_INSCRICOES')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        inscricaoCommandService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("@authService.hasPermission('VIEW_INSCRICOES')")
    @GetMapping
    public ResponseEntity<List<InscricaoResponseDTO>> findAll() {
        List<InscricaoResponseDTO> inscricoes = inscricaoQueryService.findAll();
        return ResponseEntity.ok(inscricoes);
    }

    @PreAuthorize("@authService.hasPermission('VIEW_INSCRICOES')")
    @GetMapping("/status")
    public ResponseEntity<List<InscricaoResponseDTO>> findStatusParticipacao(@RequestParam InscricaoStatusParticipacao status) {
        List<InscricaoResponseDTO> inscricoes = inscricaoQueryService.findByStatusParticipacao(status);
        return ResponseEntity.ok(inscricoes);
    }

    @PreAuthorize("@authService.hasPermission('VIEW_INSCRICOES')")
    @GetMapping("/inscrito")
    public ResponseEntity<Boolean> isInscrito(@RequestParam int id) {
        boolean inscrito = inscricaoQueryService.isInscrito(id);
        return ResponseEntity.ok(inscrito);
    }

    @PreAuthorize("@authService.hasPermission('VIEW_INSCRICOES')")
    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<List<InscricaoResponseDTO>> findInscricoesById(@PathVariable int eventoId) {
        List<InscricaoResponseDTO> inscricoes = inscricaoQueryService.findInscricoesByEventoId(eventoId);
    	return ResponseEntity.ok(inscricoes);
    }
}
