package br.com.moria.domains.membro;

import java.io.IOException;
import java.util.List;

import br.com.moria.domains.membro.dtos.MembroCreateDTO;
import br.com.moria.domains.membro.dtos.MembroResponseDTO;
import br.com.moria.domains.membro.dtos.MembroUpdateDTO;
import br.com.moria.domains.membro.services.IMembroCommandService;
import br.com.moria.domains.membro.services.IMembroQueryService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;

import br.com.moria.domains.file.FileResponseDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/membros")
public class MembroController {

    private final IMembroCommandService membroCommandService;
    private final IMembroQueryService membroQueryService;

    @Autowired
    public MembroController(IMembroCommandService membroCommandService, IMembroQueryService membroQueryService) {
        this.membroCommandService = membroCommandService;
        this.membroQueryService = membroQueryService;
    }

    @PreAuthorize("@authService.hasPermission('MANAGE_MEMBROS')")
    @PostMapping
    public ResponseEntity<MembroResponseDTO> create(@Valid @RequestBody MembroCreateDTO membroCreateDTO) {
        MembroResponseDTO createdMembro = membroCommandService.create(membroCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMembro);
    }

    @PostMapping("/self-register")
    public ResponseEntity<MembroResponseDTO> selfRegister(@Valid @RequestBody MembroCreateDTO membroCreateDTO) {
        MembroResponseDTO createdMembro = membroCommandService.selfRegister(membroCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMembro);
    }

    @PreAuthorize("@authService.hasPermission('MANAGE_MEMBROS')")
    @PutMapping("/{id}")
    public ResponseEntity<MembroResponseDTO> update(@PathVariable int id,
                                                    @Valid @RequestBody @NotNull MembroUpdateDTO membroUpdateDTO) {
        membroUpdateDTO.setId(id);
        MembroResponseDTO updatedMembro = membroCommandService.update(membroUpdateDTO);
        return ResponseEntity.ok(updatedMembro);
    }

    @PreAuthorize("@authService.hasPermission('MANAGE_MEMBROS')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        membroCommandService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("@authService.hasPermission('VIEW_MEMBROS')")
    @GetMapping
    public ResponseEntity<List<MembroResponseDTO>> findAll() {
        List<MembroResponseDTO> membros = membroQueryService.findAll();
        return ResponseEntity.ok(membros);
    }

    @PreAuthorize("@authService.hasPermissionOrIsOwner('VIEW_MEMBROS', #id)")
    @GetMapping("/{id}")
    public ResponseEntity<MembroResponseDTO> findById(@PathVariable int id) {
        MembroResponseDTO membro = membroQueryService.findById(id);
        return ResponseEntity.ok(membro);
    }

    @PreAuthorize("@authService.hasPermission('VIEW_MEMBROS')")
    @GetMapping("/cpf")
    public ResponseEntity<MembroResponseDTO> findByCpf(@RequestParam String cpf) {
        MembroResponseDTO membro = membroQueryService.findByCpf(cpf);
        return ResponseEntity.ok(membro);
    }

    @PreAuthorize("@authService.hasPermission('VIEW_MEMBROS')")
    @GetMapping("/email")
    public ResponseEntity<MembroResponseDTO> findByEmail(@RequestParam String email) {
        MembroResponseDTO membro = membroQueryService.findByEmail(email);
        return ResponseEntity.ok(membro);
    }

    @PreAuthorize("@authService.hasPermission('VIEW_MEMBROS')")
    @GetMapping("/nome")
    public ResponseEntity<List<MembroResponseDTO>> findByName(@RequestParam String nome) {
        List<MembroResponseDTO> membros = membroQueryService.findByNomeContaining(nome);
        return ResponseEntity.ok(membros);
    }

    @PreAuthorize("@authService.hasPermission('VIEW_MEMBROS')")
    @PostMapping("/{id}/ficha-saude")
    public ResponseEntity<MembroResponseDTO> uploadFichaSaude(
            @PathVariable int id, @RequestParam("file") @NotNull MultipartFile file) throws IOException {
        MembroResponseDTO updatedMembro = membroCommandService.updateFichaSaudeById(id, file);
        return ResponseEntity.ok(updatedMembro);
    }

    @PreAuthorize("@authService.hasPermissionOrIsOwner('VIEW_MEMBROS', #id)")
    @GetMapping("/{id}/ficha-saude")
    public ResponseEntity<byte[]> downloadFichaSaude(@PathVariable int id) throws IOException {
        FileResponseDTO fileResponse = membroCommandService.findFichaSaudeById(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileResponse.contentType()))
                .header("Content-Disposition", "attachment; filename=\"" + fileResponse.fileName() + "\"")
                .header("Content-Length", String.valueOf(fileResponse.fileSize()))
                .body(fileResponse.fileBytes());
    }
}