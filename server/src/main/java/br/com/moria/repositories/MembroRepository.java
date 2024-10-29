package br.com.moria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.moria.models.Membro;

public interface MembroRepository extends JpaRepository<Membro, Integer> {

    public Membro findByEmail(String email);
    public Membro findByCpf(String cpf);
    public List<Membro> findByAtivo(Boolean ativo);
    public List<Membro> findByNomeContaining(String nome);
}