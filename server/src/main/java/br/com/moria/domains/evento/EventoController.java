package br.com.moria.domains.evento;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import br.com.moria.domains.evento.dtos.EventoCreateDTO;
import br.com.moria.domains.evento.dtos.EventoResponseDTO;
import br.com.moria.domains.evento.dtos.EventoUpdateDTO;
import br.com.moria.domains.evento.services.IEventoCommandService;
import br.com.moria.domains.evento.services.IEventoQueryService;
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

import br.com.moria.domains.file.FileResponseDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final IEventoCommandService eventoCommandService;
    private final IEventoQueryService eventoQueryService;

    @Autowired
    public EventoController(IEventoCommandService eventoCommandService, IEventoQueryService eventoQueryService) {
        this.eventoCommandService = eventoCommandService;
        this.eventoQueryService = eventoQueryService;
    }

    @PostMapping
    public ResponseEntity<EventoResponseDTO> create(@Valid @RequestBody EventoCreateDTO eventoCreateDTO) {
        EventoResponseDTO createdEvento = eventoCommandService.create(eventoCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoResponseDTO> update(@PathVariable int id,
                                                    @Valid @RequestBody @NotNull EventoUpdateDTO eventoUpdateDTO) {
        eventoUpdateDTO.setId(id);
        EventoResponseDTO updatedEvento = eventoCommandService.update(eventoUpdateDTO);
        return ResponseEntity.ok(updatedEvento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        eventoCommandService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EventoResponseDTO>> findAll() {
        List<EventoResponseDTO> eventos = eventoQueryService.findAll();
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoResponseDTO> findById(@PathVariable int id) {
        EventoResponseDTO evento = eventoQueryService.findById(id);
        return ResponseEntity.ok(evento);
    }

    @GetMapping("/search")
    public ResponseEntity<List<EventoResponseDTO>> findNomeContaining(@RequestParam(name="k") String keyword) {
        List<EventoResponseDTO> eventos = eventoQueryService.findByNomeContaining(keyword);
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<EventoResponseDTO>> findDataInicioInterval(@RequestParam LocalDateTime start,
                                                                          @RequestParam LocalDateTime end) {
        List<EventoResponseDTO> eventos = eventoQueryService.findByDataInicioInterval(start, end);
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/data-inicio")
    public ResponseEntity<List<EventoResponseDTO>> findDataInicio(@RequestParam LocalDateTime date) {
        List<EventoResponseDTO> eventos = eventoQueryService.findByDataInicio(date);
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/data-fim")
    public ResponseEntity<List<EventoResponseDTO>> findDataFim(@RequestParam LocalDateTime date) {
        List<EventoResponseDTO> eventos = eventoQueryService.findByDataFim(date);
        return ResponseEntity.ok(eventos);
    }

    @PostMapping("/{id}/imagem")
    public ResponseEntity<EventoResponseDTO> uploadImagem(@PathVariable int id,
                                                          @RequestParam("file") @NotNull MultipartFile file) throws IOException {
        EventoResponseDTO updatedEvento = eventoCommandService.updateImagemById(id, file);
        return ResponseEntity.ok(updatedEvento);
    }

    @GetMapping("/{id}/imagem")
    public ResponseEntity<byte[]> downloadImagem(@PathVariable int id) throws IOException {
        FileResponseDTO fileResponse = eventoCommandService.findImagemById(id);
        
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(fileResponse.contentType()))
            .header("Content-Disposition", "attachment; filename=\"" + fileResponse.fileName() + "\"")
            .header("Content-Length", String.valueOf(fileResponse.fileSize()))
            .body(fileResponse.fileBytes());
    }
}
