package br.com.moria.services.interfaces;

import java.util.List;

import br.com.moria.enums.StatusParticipacao;
import br.com.moria.models.Evento;
import br.com.moria.models.Inscricao;
import br.com.moria.models.Membro;

public interface IInscricaoService {
	public Inscricao create(Inscricao inscricao);
	public Inscricao update(Inscricao inscricao);
	public void delete(int id);
	public List<Inscricao> findAll();
    public Inscricao findById(int id);
	public List<Inscricao> findByStatusParticipacao(StatusParticipacao status);
    public boolean isInscrito(int id);
    public List<Membro> findByEventoAndInscritoTrue(Evento evento);
}
