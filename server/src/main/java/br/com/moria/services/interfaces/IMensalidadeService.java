package br.com.moria.services.interfaces;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.moria.models.Membro;
import br.com.moria.models.Mensalidade;

public interface IMensalidadeService {
    public void gerarMensalidadesMensal();
    public Mensalidade gerarMensalidadeManual(Membro membro);
    public Mensalidade updateComprovantePagamentoById(int id, MultipartFile file) throws IOException;
    public List<Mensalidade> findAll();
    public List<Mensalidade> findDataInterval(LocalDateTime start, LocalDateTime end);
}
