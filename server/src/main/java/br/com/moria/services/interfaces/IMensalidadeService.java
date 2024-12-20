package br.com.moria.services.interfaces;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.moria.dtos.Membro.MembroResponseDTO;
import br.com.moria.enums.FormaPagamento;
import br.com.moria.models.Mensalidade;

public interface IMensalidadeService {
    public void gerarMensalidadesMensal();
    public Mensalidade gerarMensalidadeManual(MembroResponseDTO membroResponseDTO);
    public Mensalidade registrarPagamento(int id, FormaPagamento formaPagamento, MultipartFile file) throws IOException;
    public List<Mensalidade> findAll();
    public List<Mensalidade> findDataInterval(LocalDateTime start, LocalDateTime end);
    public Mensalidade findMembroAndDataInterval(MembroResponseDTO membro, LocalDateTime start, LocalDateTime end);
}
