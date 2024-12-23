package br.com.moria.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;

import br.com.moria.dtos.FileResponseDTO;
import br.com.moria.dtos.Evento.EventoCreateDTO;
import br.com.moria.dtos.Evento.EventoResponseDTO;
import br.com.moria.dtos.Evento.EventoUpdateDTO;
import br.com.moria.services.interfaces.IEventoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final IEventoService eventoService;

    @Autowired
    public EventoController(IEventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PostMapping
    public ResponseEntity<EventoResponseDTO> create(@Valid @RequestBody EventoCreateDTO eventoCreateDTO) {
        EventoResponseDTO createdEvento = eventoService.create(eventoCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoResponseDTO> update(@PathVariable int id,
                                                    @Valid @RequestBody @NotNull EventoUpdateDTO eventoUpdateDTO) {
        eventoUpdateDTO.setId(id);
        EventoResponseDTO updatedEvento = eventoService.update(eventoUpdateDTO);
        return ResponseEntity.ok(updatedEvento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        eventoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EventoResponseDTO>> findAll() {
        List<EventoResponseDTO> eventos = eventoService.findAll();
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoResponseDTO> findById(@PathVariable int id) {
        EventoResponseDTO evento = eventoService.findById(id);
        return ResponseEntity.ok(evento);
    }

    @GetMapping("/search")
    public ResponseEntity<List<EventoResponseDTO>> findNomeContaining(@RequestParam(name="k") String keyword) {
        List<EventoResponseDTO> eventos = eventoService.findByNomeContaining(keyword);
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<EventoResponseDTO>> findDataInicioInterval(@RequestParam LocalDateTime start,
                                                                          @RequestParam LocalDateTime end) {
        List<EventoResponseDTO> eventos = eventoService.findByDataInicioInterval(start, end);
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/data-inicio")
    public ResponseEntity<List<EventoResponseDTO>> findDataInicio(@RequestParam LocalDateTime date) {
        List<EventoResponseDTO> eventos = eventoService.findByDataInicio(date);
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/data-fim")
    public ResponseEntity<List<EventoResponseDTO>> findDataFim(@RequestParam LocalDateTime date) {
        List<EventoResponseDTO> eventos = eventoService.findByDataFim(date);
        return ResponseEntity.ok(eventos);
    }

    @PostMapping("/{id}/imagem")
    public ResponseEntity<EventoResponseDTO> uploadImagem(@PathVariable int id,
                                                          @RequestParam("file") @NotNull MultipartFile file) throws IOException {
        EventoResponseDTO updatedEvento = eventoService.updateImagemById(id, file);
        return ResponseEntity.ok(updatedEvento);
    }

    @GetMapping("/{id}/imagem")
    public ResponseEntity<byte[]> downloadImagem(@PathVariable int id) throws IOException {
        FileResponseDTO fileResponse = eventoService.findImagemById(id);
        
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(fileResponse.contentType()))
            .header("Content-Disposition", "attachment; filename=\"" + fileResponse.fileName() + "\"")
            .header("Content-Length", String.valueOf(fileResponse.fileSize()))
            .body(fileResponse.fileBytes());
    }
}
