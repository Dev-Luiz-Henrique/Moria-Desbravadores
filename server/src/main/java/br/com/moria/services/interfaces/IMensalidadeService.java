package br.com.moria.services.interfaces;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.moria.dtos.Mensalidade.MensalidadeResponseDTO;
import br.com.moria.enums.FormaPagamento;

public interface IMensalidadeService {
    void createMensalidadeAuto();
    MensalidadeResponseDTO create(int idMembro);
    List<MensalidadeResponseDTO> findAll();
    List<MensalidadeResponseDTO> findByDateInterval(LocalDateTime start, LocalDateTime end);
    List<MensalidadeResponseDTO> findByMembroAndDateInterval(int idMembro, LocalDateTime start, LocalDateTime end);
    List<MensalidadeResponseDTO> findByMembro(int idMembro);
    MensalidadeResponseDTO updatePagamentoById(int id, FormaPagamento formaPagamento, MultipartFile file) throws IOException;
}
