package br.com.moria.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.moria.models.Membro;

/**
 * Repositório responsável por operações de acesso a dados relacionadas à entidade {@link Membro}.
 *
 * <p>Esta interface herda de {@link JpaRepository}, fornecendo métodos CRUD padrão, além de consultas específicas
 * definidas por meio de métodos personalizados.</p>
 *
 * @see Membro
 * @see JpaRepository
 */
public interface MembroRepository extends JpaRepository<Membro, Integer> {

    /**
     * Busca um membro pelo CPF.
     *
     * @param cpf o CPF do membro.
     * @return um {@link Optional} contendo o membro, caso encontrado.
     */
    Optional<Membro> findByCpf(String cpf);

    /**
     * Busca um membro pelo endereço de e-mail.
     *
     * @param email o endereço de e-mail do membro.
     * @return um {@link Optional} contendo o membro, caso encontrado.
     */
    Optional<Membro> findByEmail(String email);

    /**
     * Verifica se existe um membro cadastrado com o CPF informado.
     *
     * @param cpf o CPF a ser verificado.
     * @return {@code true} se existir um membro com o CPF informado, caso contrário {@code false}.
     */
    boolean existsByCpf(String cpf);

    /**
     * Verifica se existe um membro cadastrado com o e-mail informado.
     *
     * @param email o endereço de e-mail a ser verificado.
     * @return {@code true} se existir um membro com o e-mail informado, caso contrário {@code false}.
     */
    boolean existsByEmail(String email);

    /**
     * Busca todos os membros ativos ou inativos.
     *
     * @param ativo status de atividade do membro ({@code true} para ativos, {@code false} para inativos).
     * @return uma lista de membros filtrados pelo status de atividade.
     */
    List<Membro> findByAtivo(Boolean ativo);

    /**
     * Busca membros cujo nome contenha a string especificada.
     *
     * @param nome parte do nome a ser pesquisado.
     * @return uma lista de membros que atendem ao critério de pesquisa.
     */
    List<Membro> findByNomeContaining(String nome);
}
