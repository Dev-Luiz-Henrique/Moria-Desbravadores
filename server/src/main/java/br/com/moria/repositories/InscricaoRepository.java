package br.com.moria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.moria.enums.StatusParticipacao;
import br.com.moria.models.Inscricao;

public interface InscricaoRepository extends JpaRepository<Inscricao, Integer> {
	public List<Inscricao> findByStatusParticipacao(StatusParticipacao status);
    public boolean existsByIdAndInscritoTrue(int inscricaoId);
}
