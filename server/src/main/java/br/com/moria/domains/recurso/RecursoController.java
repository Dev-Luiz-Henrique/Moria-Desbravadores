package br.com.moria.domains.recurso;

import java.util.List;

import br.com.moria.domains.recurso.dtos.RecursoCreateDTO;
import br.com.moria.domains.recurso.dtos.RecursoResponseDTO;
import br.com.moria.domains.recurso.dtos.RecursoUpdateDTO;
import br.com.moria.domains.recurso.services.IRecursoCommandService;
import br.com.moria.domains.recurso.services.IRecursoQueryService;
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
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/recursos")
public class RecursoController {

    private final IRecursoCommandService recursoCommandService;
    private final IRecursoQueryService recursoQueryService;

    @Autowired
    public RecursoController(IRecursoCommandService recursoCommandService, IRecursoQueryService recursoQueryService) {
        this.recursoCommandService = recursoCommandService;
        this.recursoQueryService = recursoQueryService;
    }

    @PostMapping
    public ResponseEntity<RecursoResponseDTO> create(@Valid @RequestBody RecursoCreateDTO recursoCreateDTO) {
        RecursoResponseDTO createdRecurso = recursoCommandService.create(recursoCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRecurso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecursoResponseDTO> update(@PathVariable int id,
                                                     @RequestBody @NotNull RecursoUpdateDTO recursoUpdateDTO) {
        recursoUpdateDTO.setId(id);
        RecursoResponseDTO updatedRecurso = recursoCommandService.update(recursoUpdateDTO);
        return ResponseEntity.ok(updatedRecurso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        recursoCommandService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<RecursoResponseDTO>> findAll() {
        List<RecursoResponseDTO> recursos = recursoQueryService.findAll();
        return ResponseEntity.ok(recursos);
    }

    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<List<RecursoResponseDTO>> findRecursosById(@PathVariable int eventoId) {
        List<RecursoResponseDTO> recursos = recursoQueryService.findRecursosByEventoId(eventoId);
        return ResponseEntity.ok(recursos);
    }
}
