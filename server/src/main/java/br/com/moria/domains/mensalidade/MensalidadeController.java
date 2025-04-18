package br.com.moria.domains.mensalidade;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import br.com.moria.domains.mensalidade.dtos.MensalidadeResponseDTO;
import br.com.moria.domains.mensalidade.services.IMensalidadeCommandService;
import br.com.moria.domains.mensalidade.services.IMensalidadeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.moria.shared.enums.FormaPagamento;
import br.com.moria.shared.utils.DateTimeUtil;

@RestController
@RequestMapping("/mensalidades")
public class MensalidadeController {

    private final IMensalidadeQueryService mensalidadeQueryService;
    private final IMensalidadeCommandService mensalidadeCommandService;

    @Autowired
    public MensalidadeController(IMensalidadeQueryService mensalidadeQueryService,
                                 IMensalidadeCommandService mensalidadeCommandService) {
        this.mensalidadeQueryService = mensalidadeQueryService;
        this.mensalidadeCommandService = mensalidadeCommandService;
    }

    @PreAuthorize("@authService.hasPermission('MANAGE_MENSALIDADES')")
    @PostMapping("/{idMembro}")
    public ResponseEntity<MensalidadeResponseDTO> create(@PathVariable int idMembro) {
        MensalidadeResponseDTO mensalidade = mensalidadeCommandService.createManual(idMembro);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensalidade);
    }

    @PreAuthorize("@authService.hasPermission('MANAGE_MENSALIDADES')")
    @PostMapping("/{id}/pagamento")
    public ResponseEntity<MensalidadeResponseDTO> updatePagamento(@PathVariable int id,
                                                                  @RequestParam FormaPagamento formaPagamento,
                                                                  @RequestParam("file") MultipartFile file) throws IOException {
        MensalidadeResponseDTO mensalidade = mensalidadeCommandService.updatePagamentoById(id, formaPagamento, file);
        return ResponseEntity.ok(mensalidade);
    }

    @PreAuthorize("@authService.hasPermission('VIEW_MENSALIDADES')")
    @GetMapping
    public ResponseEntity<List<MensalidadeResponseDTO>> findAll() {
        List<MensalidadeResponseDTO> mensalidades = mensalidadeQueryService.findAll();
        return ResponseEntity.ok(mensalidades);
    }

    @PreAuthorize("@authService.hasPermission('VIEW_MENSALIDADES')")
    @GetMapping("/periodo")
    public ResponseEntity<List<MensalidadeResponseDTO>> findDataInterval(@RequestParam String start,
                                                                         @RequestParam String end) {
        LocalDateTime startDateTime = DateTimeUtil.parse(start);
        LocalDateTime endDateTime = DateTimeUtil.parse(end);

        List<MensalidadeResponseDTO> mensalidades = mensalidadeQueryService.findByDateInterval(startDateTime, endDateTime);
        return ResponseEntity.ok(mensalidades);
    }

    @PreAuthorize("@authService.hasPermission('VIEW_MENSALIDADES')")
    @GetMapping("/membro/{id}")
    public ResponseEntity<List<MensalidadeResponseDTO>> findMembro(@PathVariable int membroId) {
        List<MensalidadeResponseDTO> mensalidade = mensalidadeQueryService.findByMembro(membroId);
        return ResponseEntity.ok(mensalidade);
    }

    @PreAuthorize("@authService.hasPermission('VIEW_MENSALIDADES')")
    @GetMapping("/membro/{id}/intervalo")
    public ResponseEntity<List<MensalidadeResponseDTO>> findMembroAndDataInterval(@PathVariable int membroId,
                                                                                  @RequestParam LocalDateTime start,
                                                                                  @RequestParam LocalDateTime end) {
        List<MensalidadeResponseDTO> mensalidade = mensalidadeQueryService.findByMembroAndDateInterval(membroId, start, end);
        return ResponseEntity.ok(mensalidade);
    }
}
