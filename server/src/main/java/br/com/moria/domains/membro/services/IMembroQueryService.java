package br.com.moria.domains.membro.services;

import br.com.moria.domains.membro.Membro;
import br.com.moria.domains.membro.dtos.MembroResponseDTO;
import br.com.moria.shared.exceptions.NotFoundResourceException;

import java.util.List;

/**
 * Interface de serviço para operações relacionadas à visualização de membros.
 *
 * <p>Define métodos para consulta de dados do membro, como obter informações detalhadas
 * sobre um membro específico e listar membros.</p>
 *
 * @see Membro
 * @see MembroResponseDTO
 */
public interface IMembroQueryService {

    /**
     * Retorna uma lista de todos os membros ativos ou inativos.
     *
     * @param ativo {@code true} para membros ativos, {@code false} para inativos.
     * @return uma lista de membros filtrados pelo status de atividade.
     */
    List<Membro> findAllMembrosByAtivo(boolean ativo);

    /**
     * Busca um membro pelo ID.
     *
     * @param id o identificador do membro.
     * @return o membro encontrado.
     * @throws NotFoundResourceException se o membro não for encontrado.
     */
    Membro findMembroById(int id);

    /**
     * Retorna a contagem total de membros cadastrados.
     *
     * @return o número total de membros.
     */
    long count();

    /**
     * Verifica se um membro existe pelo ID.
     *
     * @param id o identificador do membro.
     * @return {@code true} se o membro existir, caso contrário {@code false}.
     */
    boolean existsById(int id);

    /**
     * Retorna todos os membros cadastrados.
     *
     * @return uma lista de todos os membros.
     */
    List<MembroResponseDTO> findAll();

    /**
     * Busca um membro pelo ID.
     *
     * @param id o identificador do membro.
     * @return os detalhes do membro.
     * @throws NotFoundResourceException se o membro não for encontrado.
     */
    MembroResponseDTO findById(int id);

    /**
     * Busca um membro pelo CPF.
     *
     * @param cpf o CPF do membro.
     * @return os detalhes do membro.
     * @throws NotFoundResourceException se o membro não for encontrado.
     */
    MembroResponseDTO findByCpf(String cpf);

    /**
     * Busca um membro pelo endereço de e-mail.
     *
     * @param email o e-mail do membro.
     * @return os detalhes do membro.
     * @throws NotFoundResourceException se o membro não for encontrado.
     */
    MembroResponseDTO findByEmail(String email);

    /**
     * Busca membros cujo nome contenha a string especificada.
     *
     * @param nome parte do nome a ser pesquisado.
     * @return uma lista de membros que atendem ao critério de pesquisa.
     */
    List<MembroResponseDTO> findByNomeContaining(String nome);

    /**
     * Retorna uma lista de membros ativos ou inativos.
     *
     * @param ativo {@code true} para ativos, {@code false} para inativos.
     * @return uma lista de {@link MembroResponseDTO}.
     */
    List<MembroResponseDTO> findByAtivo(boolean ativo);
}
