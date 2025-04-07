package br.com.moria.domains.evento.services;

import br.com.moria.domains.evento.Evento;
import br.com.moria.domains.evento.dtos.EventoResponseDTO;
import br.com.moria.shared.enums.EntityType;
import br.com.moria.shared.exceptions.NotFoundResourceException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface de serviço para operações relacionadas a eventos.
 *
 * <p>Define métodos para consulta de dados do evento como obter informações detalhadas
 * sobre um evento específico e listar eventos.</p>
 *
 * @see Evento
 * @see EventoResponseDTO
 */
public interface IEventoQueryService {

    /**
     * Verifica se um evento existe pelo ID, lançando uma exceção se não for encontrado.
     *
     * @param id o identificador do evento.
     * @throws NotFoundResourceException se o evento não for encontrado.
     */
    default void assertEventoExists(int id) {
        if (!existsById(id))
            throw NotFoundResourceException.forEntity(EntityType.EVENTO, id);
    }

    /**
     * Busca um evento pelo ID.
     *
     * @param id o identificador do evento.
     * @return o evento encontrado.
     * @throws NotFoundResourceException se o evento não for encontrado.
     */
    Evento findEventoById(int id);

    /**
     * Retorna a contagem total de eventos cadastrados.
     *
     * @return o número total de eventos.
     */
    long count();

    /**
     * Verifica se um evento existe pelo ID.
     *
     * @param id o identificador do evento.
     * @return {@code true} se o evento existir, caso contrário {@code false}.
     */
    boolean existsById(int id);

    /**
     * Retorna todos os eventos cadastrados.
     *
     * @return uma lista de todos os eventos.
     */
    List<EventoResponseDTO> findAll();

    /**
     * Busca os detalhes de um evento pelo ID.
     *
     * @param id o identificador do evento.
     * @return os detalhes do evento.
     * @throws NotFoundResourceException se o evento não for encontrado.
     */
    EventoResponseDTO findById(int id);

    /**
     * Busca eventos cujo nome contenha a palavra-chave especificada.
     *
     * @param keyword parte do nome a ser pesquisado.
     * @return uma lista de eventos que atendem ao critério de pesquisa.
     */
    List<EventoResponseDTO> findByNomeContaining(String keyword);

    /**
     * Busca eventos pela data de início.
     *
     * @param date a data de início dos eventos.
     * @return uma lista de eventos que começam na data especificada.
     */
    List<EventoResponseDTO> findByDataInicio(LocalDateTime date);

    /**
     * Busca eventos pela data de término.
     *
     * @param date a data de término dos eventos.
     * @return uma lista de eventos que terminam na data especificada.
     */
    List<EventoResponseDTO> findByDataFim(LocalDateTime date);

    /**
     * Busca eventos por um intervalo de datas de início.
     *
     * @param start a data inicial do intervalo.
     * @param end a data final do intervalo.
     * @return uma lista de eventos dentro do intervalo especificado.
     */
    List<EventoResponseDTO> findByDataInicioInterval(LocalDateTime start, LocalDateTime end);
}
