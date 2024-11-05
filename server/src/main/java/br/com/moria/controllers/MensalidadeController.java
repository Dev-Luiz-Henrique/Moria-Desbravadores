package br.com.moria.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.moria.enums.FormaPagamento;
import br.com.moria.models.Membro;
import br.com.moria.models.Mensalidade;
import br.com.moria.services.interfaces.IMembroService;
import br.com.moria.services.interfaces.IMensalidadeService;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/mensalidades")
@Validated
@CrossOrigin(origins = "https://proud-wave-0042c520f.5.azurestaticapps.net")
public class MensalidadeController {

    @Autowired
    private IMensalidadeService mensalidadeService;

    @Autowired
    private IMembroService membroService;

    @PostMapping("/{id}/manual")
    public ResponseEntity<Object> gerarMensalidadeManual(@PathVariable("id") int idMembro) {
        try {
        	Membro membro = membroService.findById(idMembro);
            Mensalidade mensalidade = mensalidadeService.gerarMensalidadeManual(membro);
            return ResponseEntity.status(HttpStatus.CREATED).body(mensalidade);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("code", HttpStatus.BAD_REQUEST.value(), "message", e.getMessage()));
        }
    }

    @PostMapping("/{id}/pagamento")
    public ResponseEntity<Mensalidade> registrarPagamento(@PathVariable int id,
                                                          @RequestParam FormaPagamento formaPagamento,
                                                          @RequestParam("file") MultipartFile file) {
        try {
            Mensalidade mensalidade = mensalidadeService.registrarPagamento(id, formaPagamento, file);
            return ResponseEntity.ok(mensalidade);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Mensalidade>> findAll() {
        List<Mensalidade> mensalidades = mensalidadeService.findAll();
        return ResponseEntity.ok(mensalidades);
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<Mensalidade>> findDataInterval(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        List<Mensalidade> mensalidades = mensalidadeService.findDataInterval(start, end);
        return ResponseEntity.ok(mensalidades);
    }

    @GetMapping("/mensalidade-membro")
    public ResponseEntity<Mensalidade> findMembroAndDataInterval(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
                                                                 @RequestParam int id) {
    	try {
    		Membro membro = membroService.findById(id);
    		Mensalidade mensalidade = mensalidadeService.findMembroAndDataInterval(membro, start, end);
     	    return ResponseEntity.ok(mensalidade);
    	} catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
