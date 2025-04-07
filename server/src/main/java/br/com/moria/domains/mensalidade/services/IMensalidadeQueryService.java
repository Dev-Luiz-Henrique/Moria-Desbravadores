package br.com.moria.domains.mensalidade.services;

import br.com.moria.domains.mensalidade.Mensalidade;
import br.com.moria.domains.mensalidade.dtos.MensalidadeResponseDTO;
import br.com.moria.shared.enums.FormaPagamento;
import br.com.moria.shared.exceptions.NotFoundResourceException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface de serviço para operações relacionadas a visualização de mensalidades.
 *
 * <p>Define métodos para visualização de dados de mensalidades, como obter informações detalhadas
 * sobre uma mensalidade específica e listar mensalidades.</p>
 *
 * @see Mensalidade
 * @see MensalidadeResponseDTO
 * @see FormaPagamento
 */
public interface IMensalidadeQueryService {

    /**
     * Busca uma mensalidade pelo ID, lançando uma exceção se não for encontrada.
     *
     * @param id o identificador da mensalidade.
     * @return a mensalidade encontrada.
     * @throws NotFoundResourceException se a mensalidade não for encontrada.
     */
    Mensalidade findMensalidadeById(int id);

    /**
     * Retorna a contagem total de mensalidades cadastradas.
     *
     * @return o número total de mensalidades.
     */
    long count();

    /**
     * Retorna todas as mensalidades cadastradas.
     *
     * @return uma lista de todas as mensalidades.
     */
    List<MensalidadeResponseDTO> findAll();

    /**
     * Busca uma mensalidade pelo ID.
     *
     * @param id o identificador da mensalidade.
     * @return os detalhes da mensalidade encontrada.
     * @throws NotFoundResourceException se a mensalidade não for encontrada.
     */
    MensalidadeResponseDTO findById(int id);

    /**
     * Busca mensalidades dentro de um intervalo de datas.
     *
     * @param start a data inicial do intervalo.
     * @param end a data final do intervalo.
     * @return uma lista de mensalidades dentro do intervalo especificado.
     */
    List<MensalidadeResponseDTO> findByDateInterval(LocalDateTime start, LocalDateTime end);

    /**
     * Busca mensalidades de um membro específico dentro de um intervalo de datas.
     *
     * @param idMembro o identificador do membro.
     * @param start a data inicial do intervalo.
     * @param end a data final do intervalo.
     * @return uma lista de mensalidades do membro dentro do intervalo especificado.
     * @throws NotFoundResourceException se o membro não for encontrado.
     */
    List<MensalidadeResponseDTO> findByMembroAndDateInterval(int idMembro, LocalDateTime start, LocalDateTime end);

    /**
     * Busca todas as mensalidades de um membro específico.
     *
     * @param idMembro o identificador do membro.
     * @return uma lista de mensalidades do membro.
     * @throws NotFoundResourceException se o membro não for encontrado.
     */
    List<MensalidadeResponseDTO> findByMembro(int idMembro);
}
