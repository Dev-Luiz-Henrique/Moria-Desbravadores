package br.com.moria.domains.inscricao.services;

import br.com.moria.domains.inscricao.enums.InscricaoStatusParticipacao;
import br.com.moria.domains.inscricao.dtos.InscricaoCreateDTO;
import br.com.moria.domains.inscricao.dtos.InscricaoResponseDTO;
import br.com.moria.domains.inscricao.dtos.InscricaoUpdateDTO;
import br.com.moria.shared.exceptions.DuplicatedResourceException;
import br.com.moria.shared.exceptions.NotFoundResourceException;
import br.com.moria.shared.exceptions.ValidationException;

import java.util.List;

/**
 * Interface de serviço para operações relacionadas a inscrições.
 *
 * <p>Define métodos para manipulação de dados de inscrições, incluindo criação,
 * atualização, exclusão e operações específicas.</p>
 *
 * @see InscricaoResponseDTO
 * @see InscricaoCreateDTO
 * @see InscricaoUpdateDTO
 */
public interface IInscricaoService {

	/**
	 * Retorna a contagem total de inscrições cadastradas.
	 *
	 * @return o número total de inscrições.
	 */
	long count();

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
	 * Retorna todas as inscrições cadastradas.
	 *
	 * @return uma lista de todas as inscrições.
	 */
	List<InscricaoResponseDTO> findAll();

	/**
	 * Busca os detalhes de uma inscrição pelo ID.
	 *
	 * @param id o identificador da inscrição.
	 * @return os detalhes da inscrição encontrada.
	 * @throws NotFoundResourceException se a inscrição não for encontrada.
	 */
	InscricaoResponseDTO findById(int id);

	/**
	 * Busca inscrições pelo status de participação.
	 *
	 * @param status o status de participação desejado.
	 * @return uma lista de inscrições com o status especificado.
	 */
	List<InscricaoResponseDTO> findByStatusParticipacao(InscricaoStatusParticipacao status);

	/**
	 * Busca todas as inscrições associadas a um evento específico.
	 *
	 * @param eventoId o identificador do evento.
	 * @return uma lista de inscrições relacionadas ao evento.
	 * @throws NotFoundResourceException se o evento não for encontrado.
	 */
	List<InscricaoResponseDTO> findInscricoesByEventoId(int eventoId);

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

	/**
	 * Verifica se um membro está inscrito com base no ID.
	 *
	 * @param id o identificador da inscrição.
	 * @return {@code true} se o membro estiver inscrito, caso contrário {@code false}.
	 */
	boolean isInscrito(int id);
}
