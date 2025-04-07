package br.com.moria.domains.inscricao.services;

import br.com.moria.domains.inscricao.dtos.InscricaoCreateDTO;
import br.com.moria.domains.inscricao.dtos.InscricaoResponseDTO;
import br.com.moria.domains.inscricao.dtos.InscricaoUpdateDTO;
import br.com.moria.shared.exceptions.DuplicatedResourceException;
import br.com.moria.shared.exceptions.NotFoundResourceException;
import br.com.moria.shared.exceptions.ValidationException;

/**
 * Interface de serviço para operações relacionadas a incrições.
 *
 * <p>Define métodos para manipulação de dados de incrições, incluindo criação,
 * atualização, exclusão e operações específicas.</p>
 *
 * @see InscricaoResponseDTO
 * @see InscricaoCreateDTO
 * @see InscricaoUpdateDTO
 */
public interface IInscricaoCommandService {

    /**
     * Cria uma inscrição com base nos dados fornecidos.
     *
     * @param inscricaoCreateDTO os dados da inscrição a ser criada.
     * @return os detalhes da inscrição criada.
     * @throws NotFoundResourceException se o evento ou o membro não forem encontrados.
     * @throws DuplicatedResourceException se já existir uma inscrição para o membro e evento informados.
     */
    InscricaoResponseDTO create(InscricaoCreateDTO inscricaoCreateDTO);

    /**
     * Atualiza as informações de uma inscrição existente.
     *
     * @param inscricao os dados atualizados da inscrição.
     * @return os detalhes da inscrição atualizada.
     * @throws NotFoundResourceException se a inscrição, o evento ou o membro não forem encontrados.
     */
    InscricaoResponseDTO update(InscricaoUpdateDTO inscricao);

    /**
     * Remove uma inscrição pelo ID.
     *
     * @param id o identificador da inscrição.
     * @throws NotFoundResourceException se a inscrição não for encontrada.
     */
    void delete(int id);

    /**
     * Atualiza o status de uma inscrição específica com base nos IDs do membro e do evento.
     *
     * @param membroId o identificador do membro.
     * @param eventoId o identificador do evento.
     * @return os detalhes da inscrição com o status atualizado.
     * @throws NotFoundResourceException se a inscrição não for encontrada.
     * @throws ValidationException se a inscrição já estiver ativa.
     */
    InscricaoResponseDTO updateStatusInscricao(int membroId, int eventoId);
}
