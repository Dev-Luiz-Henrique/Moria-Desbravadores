package br.com.moria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.moria.models.Membro;

public interface MembroRepository extends JpaRepository<Membro, Integer> {

}