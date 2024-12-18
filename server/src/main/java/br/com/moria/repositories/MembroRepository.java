package br.com.moria.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.moria.models.Membro;

public interface MembroRepository extends JpaRepository<Membro, Integer> {
    public Optional<Membro> findByEmail(String email);
    public Optional<Membro> findByCpf(String cpf);
    public List<Membro> findByAtivo(Boolean ativo);
    public List<Membro> findByNomeContaining(String nome);
}