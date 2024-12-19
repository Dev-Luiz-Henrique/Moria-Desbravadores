package br.com.moria.controllers;

import java.io.IOException;
import java.util.List;

import br.com.moria.dtos.Membro.MembroCreateDTO;
import br.com.moria.dtos.Membro.MembroResponseDTO;
import br.com.moria.dtos.Membro.MembroUpdateDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import br.com.moria.dtos.FileResponseDTO;
import br.com.moria.models.Membro;
import br.com.moria.services.interfaces.IMembroService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/membros")
public class MembroController {

    private final IMembroService membroService;

    @Autowired
    public MembroController(IMembroService membroService) {
        this.membroService = membroService;
    }

    @PostMapping
    public ResponseEntity<MembroResponseDTO> create(@Valid @RequestBody MembroCreateDTO membroCreateDTO) {
        MembroResponseDTO createdMembro = membroService.create(membroCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMembro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MembroResponseDTO> update(@PathVariable int id,
                                                    @Valid @RequestBody @NotNull MembroUpdateDTO membroUpdateDTO) {
        membroUpdateDTO.setId(id);
        MembroResponseDTO updatedMembro = membroService.update(membroUpdateDTO);
        return ResponseEntity.ok(updatedMembro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        membroService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<MembroResponseDTO>> findAll() {
        List<MembroResponseDTO> membros = membroService.findAll();
        return ResponseEntity.ok(membros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembroResponseDTO> findById(@PathVariable int id) {
        MembroResponseDTO membro = membroService.findById(id);
        return ResponseEntity.ok(membro);
    }

    @GetMapping("/cpf")
    public ResponseEntity<MembroResponseDTO> findByCpf(@RequestParam String cpf) {
        MembroResponseDTO membro = membroService.findByCpf(cpf);
        return ResponseEntity.ok(membro);
    }

    @GetMapping("/email")
    public ResponseEntity<MembroResponseDTO> findByEmail(@RequestParam String email) {
        MembroResponseDTO membro = membroService.findByEmail(email);
        return ResponseEntity.ok(membro);
    }

    @GetMapping("/nome")
    public ResponseEntity<List<MembroResponseDTO>> findByName(@RequestParam String nome) {
        List<MembroResponseDTO> membros = membroService.findByNomeContaining(nome);
        return ResponseEntity.ok(membros);
    }

    //
    // TODO Review this file methods
    //

    @PostMapping("/{id}/ficha-saude")
    public ResponseEntity<String> uploadFichaSaude(@PathVariable int id,
                                                   @RequestParam("file")
                                                   @NotNull MultipartFile file) throws IOException {
        if (file.isEmpty())
			return ResponseEntity.badRequest().body("Nenhum arquivo foi enviado");

        Membro membro = membroService.updateFichaSaudeById(id, file);
        return ResponseEntity.ok("Ficha de saúde salva em: " + membro.getFichaSaude());
    }

    @GetMapping("/{id}/ficha-saude")
    public ResponseEntity<byte[]> downloadFichaSaude(@PathVariable int id) throws IOException {
        FileResponseDTO fileResponse = membroService.getFichaSaudeById(id);
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(fileResponse.getContentType()))
            .body(fileResponse.getFileBytes());
    }
}