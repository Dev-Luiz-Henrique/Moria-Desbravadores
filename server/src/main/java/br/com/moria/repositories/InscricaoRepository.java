package br.com.moria.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.moria.enums.StatusParticipacao;
import br.com.moria.models.Inscricao;

public interface InscricaoRepository extends JpaRepository<Inscricao, Integer> {
    Optional<Inscricao> findByMembroIdAndEventoId(int membroId,int eventoId);
	List<Inscricao> findByStatusParticipacao(StatusParticipacao status);
    boolean existsByIdAndInscritoTrue(int inscricaoId);
    List<Inscricao> findByEventoId(int eventoId);
}
