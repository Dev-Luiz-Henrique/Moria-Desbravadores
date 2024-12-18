package br.com.moria.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.moria.enums.StatusParticipacao;
import br.com.moria.models.Inscricao;

public interface InscricaoRepository extends JpaRepository<Inscricao, Integer> {
    public Optional<Inscricao> findByMembroIdAndEventoId(int membroId,int eventoId);
	public List<Inscricao> findByStatusParticipacao(StatusParticipacao status);
    public boolean existsByIdAndInscritoTrue(int inscricaoId);
    public List<Inscricao> findByEventoId(int eventoId);
}
