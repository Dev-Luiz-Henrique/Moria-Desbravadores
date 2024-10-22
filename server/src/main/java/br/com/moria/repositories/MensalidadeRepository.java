package br.com.moria.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.moria.models.Membro;
import br.com.moria.models.Mensalidade;

public interface MensalidadeRepository extends JpaRepository<Mensalidade, Integer>{
	
	boolean existsByMembroAndDataBetween(Membro membro, LocalDateTime start, LocalDateTime end);
	public List<Mensalidade> findByDataBetween(LocalDateTime start, LocalDateTime end);
}
