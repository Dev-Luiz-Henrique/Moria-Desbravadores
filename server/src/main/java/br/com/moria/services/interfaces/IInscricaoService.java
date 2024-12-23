package br.com.moria.services.interfaces;

import java.util.List;

import br.com.moria.dtos.Inscricao.InscricaoCreateDTO;
import br.com.moria.dtos.Inscricao.InscricaoResponseDTO;
import br.com.moria.dtos.Inscricao.InscricaoUpdateDTO;
import br.com.moria.enums.StatusParticipacao;

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
	 * Cria uma inscrição com base nos dados fornecidos.
	 *
	 * @param inscricaoCreateDTO os dados da inscrição a ser criada.
	 * @return os detalhes da inscrição criada.
	 */
	InscricaoResponseDTO create(InscricaoCreateDTO inscricaoCreateDTO);

	/**
	 * Atualiza as informações de uma inscrição existente.
	 *
	 * @param inscricao os dados atualizados da inscrição.
	 * @return os detalhes da inscrição atualizada.
	 */
	InscricaoResponseDTO update(InscricaoUpdateDTO inscricao);

	/**
	 * Remove uma inscrição pelo ID.
	 *
	 * @param id o identificador da inscrição.
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
	 */
	InscricaoResponseDTO findById(int id);

	/**
	 * Busca inscrições pelo status de participação.
	 *
	 * @param status o status de participação desejado.
	 * @return uma lista de inscrições com o status especificado.
	 */
	List<InscricaoResponseDTO> findByStatusParticipacao(StatusParticipacao status);

	/**
	 * Busca todas as inscrições associadas a um evento específico.
	 *
	 * @param eventoId o identificador do evento.
	 * @return uma lista de inscrições relacionadas ao evento.
	 */
	List<InscricaoResponseDTO> findInscricoesByEventoId(int eventoId);

	/**
	 * Atualiza o status de uma inscrição específica com base nos IDs do membro e do evento.
	 *
	 * @param membroId o identificador do membro.
	 * @param eventoId o identificador do evento.
	 * @return os detalhes da inscrição com o status atualizado.
	 */
	InscricaoResponseDTO updateStatusInscricao(int membroId, int eventoId);

	/**
	 * Verifica se um membro está inscrito com base no ID.
	 *
	 * @param id o identificador do membro.
	 * @return {@code true} se o membro estiver inscrito, caso contrário {@code false}.
	 */
	boolean isInscrito(int id);
}
