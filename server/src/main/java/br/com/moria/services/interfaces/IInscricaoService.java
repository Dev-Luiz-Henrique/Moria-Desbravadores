package br.com.moria.services.interfaces;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.moria.enums.StatusParticipacao;
import br.com.moria.models.Inscricao;

public interface IInscricaoService {
	public Inscricao create(Inscricao inscricao);
	public Inscricao updateStatusParticipacao(int id, StatusParticipacao status);
	public Inscricao updateAutorizacaoResponsavel(int id, MultipartFile file) throws IOException;
	public void delete(int id);
	public List<Inscricao> findAll();
	public List<Inscricao> findStatusParticipacao(StatusParticipacao status);
}
