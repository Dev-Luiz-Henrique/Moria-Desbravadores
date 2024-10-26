package br.com.moria.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/membros")
@Validated
public class MembroController {

    @Autowired
    private IMembroService membroService;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Membro membro) {
        try {
            Membro createdMembro = membroService.create(membro);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdMembro);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            	Map.of("code", HttpStatus.BAD_REQUEST.value(), "message", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Membro> update(@PathVariable int id, @RequestBody Membro membro) {
        membro.setId(id);
        try {
            Membro updatedMembro = membroService.update(membro);
            return ResponseEntity.ok(updatedMembro);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
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

    @GetMapping("/email")
    public ResponseEntity<Membro> findByEmail(@RequestParam String email) {
        try {
            Membro membro = membroService.findByEmail(email);
            return ResponseEntity.ok(membro);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/{id}/ficha-saude")
    public ResponseEntity<String> uploadFichaSaude(@PathVariable int id, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
			return ResponseEntity.badRequest().body("Nenhum arquivo foi enviado");
		}

        try {
            Membro membro = membroService.updateFichaSaudeById(id, file);
            return ResponseEntity.ok("Ficha de saúde salva em: " + membro.getFichaSaude());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao fazer upload da ficha de saúde: " + e.getMessage());
        }
    }

    @GetMapping("/{id}/ficha-saude")
    public ResponseEntity<byte[]> downloadFichaSaude(@PathVariable int id) {
        try {
            FileResponseDTO fileResponse = membroService.getFichaSaudeById(id);
            return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileResponse.getContentType()))
                .body(fileResponse.getFileBytes());

        } catch (IOException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}