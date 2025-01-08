package br.com.moria.domains.endereco;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório responsável por operações de acesso a dados relacionadas à entidade {@link Endereco}.
 *
 * <p>Esta interface herda de {@link JpaRepository}, fornecendo métodos CRUD padrão, além de consultas específicas
 * definidas por meio de métodos personalizados.</p>
 *
 * @see Endereco
 * @see JpaRepository
 */
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    /**
     * Busca um endereço pelo CEP.
     *
     * @param cep o CEP do endereço.
     * @return um {@link Optional} contendo o endereço, caso encontrado.
     */
    Optional<Endereco> findByCep(String cep);

    /**
     * Verifica se existe um endereço cadastrado com o CEP informado.
     *
     * @param cep o CEP a ser verificado.
     * @return {@code true} se o endereço com o CEP informado existir, {@code false} caso contrário.
     */
    boolean existsByCep(String cep);
}
