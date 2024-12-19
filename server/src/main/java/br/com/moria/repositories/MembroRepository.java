package br.com.moria.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.moria.models.Membro;

public interface MembroRepository extends JpaRepository<Membro, Integer> {

    Optional<Membro> findByEmail(String email);
    Optional<Membro> findByCpf(String cpf);
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    List<Membro> findByAtivo(Boolean ativo);
    List<Membro> findByNomeContaining(String nome);
}