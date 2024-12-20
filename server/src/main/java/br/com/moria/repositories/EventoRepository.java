package br.com.moria.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.moria.models.Evento;

public interface EventoRepository extends JpaRepository<Evento, Integer>{
    boolean existsById(int id);
	List<Evento> findByNomeContaining(String palavraChave);
	List<Evento> findByDataInicio(LocalDateTime data);
	List<Evento> findByDataFim(LocalDateTime data);
	List<Evento> findByDataInicioBetween(LocalDateTime start, LocalDateTime end);
}
