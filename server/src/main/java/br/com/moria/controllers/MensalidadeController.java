package br.com.moria.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import br.com.moria.dtos.Mensalidade.MensalidadeResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.moria.enums.FormaPagamento;
import br.com.moria.services.interfaces.IMensalidadeService;
import br.com.moria.utils.DateTimeUtil;

@RestController
@RequestMapping("/mensalidades")
public class MensalidadeController {

    private final IMensalidadeService mensalidadeService;

    @Autowired
    public MensalidadeController(IMensalidadeService mensalidadeService){
        this.mensalidadeService = mensalidadeService;
    }

    @PostMapping("/{idMembro}")
    public ResponseEntity<MensalidadeResponseDTO> create(@PathVariable int idMembro) {
        MensalidadeResponseDTO mensalidade = mensalidadeService.createManual(idMembro);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensalidade);
    }

    @PostMapping("/{id}/pagamento")
    public ResponseEntity<MensalidadeResponseDTO> updatePagamento(@PathVariable int id,
                                                                  @RequestParam FormaPagamento formaPagamento,
                                                                  @RequestParam("file") MultipartFile file) throws IOException {
        MensalidadeResponseDTO mensalidade = mensalidadeService.updatePagamentoById(id, formaPagamento, file);
        return ResponseEntity.ok(mensalidade);
    }

    @GetMapping
    public ResponseEntity<List<MensalidadeResponseDTO>> findAll() {
        List<MensalidadeResponseDTO> mensalidades = mensalidadeService.findAll();
        return ResponseEntity.ok(mensalidades);
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<MensalidadeResponseDTO>> findDataInterval(@RequestParam String start,
                                                                         @RequestParam String end) {
        LocalDateTime startDateTime = DateTimeUtil.parse(start);
        LocalDateTime endDateTime = DateTimeUtil.parse(end);

        List<MensalidadeResponseDTO> mensalidades = mensalidadeService.findByDateInterval(startDateTime, endDateTime);
        return ResponseEntity.ok(mensalidades);
    }

    @GetMapping("/membro/{id}")
    public ResponseEntity<List<MensalidadeResponseDTO>> findMembro(@PathVariable int membroId) {
        List<MensalidadeResponseDTO> mensalidade = mensalidadeService.findByMembro(membroId);
        return ResponseEntity.ok(mensalidade);
    }

    @GetMapping("/membro/{id}/intervalo")
    public ResponseEntity<List<MensalidadeResponseDTO>> findMembroAndDataInterval(@PathVariable int membroId,
                                                                                  @RequestParam LocalDateTime start,
                                                                                  @RequestParam LocalDateTime end) {
        List<MensalidadeResponseDTO> mensalidade = mensalidadeService.findByMembroAndDateInterval(membroId, start, end);
        return ResponseEntity.ok(mensalidade);
    }
}
