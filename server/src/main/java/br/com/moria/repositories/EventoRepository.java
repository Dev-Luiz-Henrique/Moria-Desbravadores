package br.com.moria.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.moria.models.Evento;

public interface EventoRepository extends JpaRepository<Evento, Integer>{

	public List<Evento> findByNomeContaining(String palavraChave);
	public List<Evento> findByDataInicio(LocalDateTime data);
	public List<Evento> findByDataFim(LocalDateTime data);
	public List<Evento> findByDataBetween(LocalDateTime start, LocalDateTime end);
}
