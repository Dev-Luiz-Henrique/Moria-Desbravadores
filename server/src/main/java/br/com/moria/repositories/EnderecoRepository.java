package br.com.moria.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.moria.models.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    
    Optional<Endereco> findByCep(String cep);
}