package br.com.moria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.moria.models.Evento;

public interface EventoRepository extends JpaRepository<Evento, Integer>{

}
