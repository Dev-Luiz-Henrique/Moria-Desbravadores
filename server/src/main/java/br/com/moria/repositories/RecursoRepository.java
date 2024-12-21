package br.com.moria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.moria.models.Recurso;
import java.util.List;

public interface RecursoRepository extends JpaRepository<Recurso, Integer> {
    List<Recurso> findByEventoId(int eventoId);
}
