package br.com.moria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.moria.models.Recurso;

public interface RecursoRepository extends JpaRepository<Recurso, Integer> {

}
