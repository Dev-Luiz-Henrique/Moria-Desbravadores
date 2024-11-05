package br.com.moria.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;

import br.com.moria.dtos.FileResponseDTO;
import br.com.moria.models.Membro;
import br.com.moria.services.interfaces.IMembroService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/membros")
@Validated
@CrossOrigin(origins = "https://proud-wave-0042c520f.5.azurestaticapps.net")
public class MembroController {

    @Autowired
    private IMembroService membroService;

    @PostMapping
    public ResponseEntity<Membro> create(@Valid @RequestBody Membro membro) {
        Membro createdMembro = membroService.create(membro);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMembro);  
    }

    @PutMapping("/{id}")
    public ResponseEntity<Membro> update(@PathVariable int id, @RequestBody Membro membro) {
        membro.setId(id);
        Membro updatedMembro = membroService.update(membro);
        return ResponseEntity.ok(updatedMembro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        membroService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Membro>> findAll() {
        List<Membro> membros = membroService.findAll();
        return ResponseEntity.ok(membros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Membro> findById(@PathVariable int id) {
        Membro membro = membroService.findById(id);
        return ResponseEntity.ok(membro);
    }

    @GetMapping("/email")
    public ResponseEntity<Membro> findByEmail(@RequestParam String email) {
        Membro membro = membroService.findByEmail(email);
        return ResponseEntity.ok(membro);
    }

    @GetMapping("/nome")
    public ResponseEntity<List<Membro>> findByName(@RequestParam String nome) {
        List<Membro> membros = membroService.findByNomeContaining(nome);
        return ResponseEntity.ok(membros);
    }

    @PostMapping("/{id}/ficha-saude")
    public ResponseEntity<String> uploadFichaSaude(@PathVariable int id, @RequestParam("file") MultipartFile file) throws IOException {
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