package br.com.moria.domains.recurso.services;

import br.com.moria.domains.recurso.Recurso;
import br.com.moria.domains.recurso.dtos.RecursoResponseDTO;
import br.com.moria.shared.exceptions.NotFoundResourceException;

import java.util.List;

/**
 * Interface de serviço para operações relacionadas a visualização de recursos.
 *
 * <p>Define métodos para visualização de dados de recursos, como obter informações detalhadas
 * sobre um recurso específica e listar recursos.</p>
 *
 * @see RecursoResponseDTO
 */
public interface IRecursoQueryService {

    /**
     * Busca um recurso pelo ID, lançando uma exceção se não for encontrado.
     *
     * @param id o identificador do recurso.
     * @return o recurso encontrado.
     * @throws NotFoundResourceException se o recurso não for encontrado.
     */
    Recurso findRecursoById(int id);

    /**
     * Retorna a contagem total de recursos cadastrados.
     *
     * @return o número total de recursos.
     */
    long count();

    /**
     * Retorna todos os recursos cadastrados.
     *
     * @return uma lista de todos os recursos.
     */
    List<RecursoResponseDTO> findAll();

    /**
     * Busca os detalhes de um recurso pelo ID.
     *
     * @param id o identificador do recurso.
     * @return os detalhes do recurso encontrado.
     * @throws NotFoundResourceException se o recurso não for encontrado.
     */
    RecursoResponseDTO findById(int id);

    /**
     * Busca todos os recursos associados a um evento específico.
     *
     * @param eventoId o identificador do evento.
     * @return uma lista de recursos relacionados ao evento.
     * @throws NotFoundResourceException se o evento não for encontrado.
     */
    List<RecursoResponseDTO> findRecursosByEventoId(int eventoId);
}
