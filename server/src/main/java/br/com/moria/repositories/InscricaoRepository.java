package br.com.moria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.moria.enums.StatusParticipacao;
import br.com.moria.models.Evento;
import br.com.moria.models.Inscricao;
import br.com.moria.models.Membro;

public interface InscricaoRepository extends JpaRepository<Inscricao, Integer> {
	public List<Inscricao> findByStatusParticipacao(StatusParticipacao status);
    public boolean existsByIdAndInscritoTrue(int inscricaoId);
    public List<Membro> findByEventoAndInscritoTrue(Evento evento);
}
