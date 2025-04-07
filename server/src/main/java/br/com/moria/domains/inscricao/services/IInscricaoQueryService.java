package br.com.moria.domains.inscricao.services;

import br.com.moria.domains.inscricao.Inscricao;
import br.com.moria.domains.inscricao.enums.InscricaoStatusParticipacao;
import br.com.moria.domains.inscricao.dtos.InscricaoResponseDTO;
import br.com.moria.shared.exceptions.NotFoundResourceException;

import java.util.List;

/**
 * Interface de serviço para operações relacionadas a visualização de incrições.
 *
 * <p>Define métodos para visualização de dados de incrições, como obter informações detalhadas
 * sobre uma incrição específica e listar incrições.</p>
 *
 * @see InscricaoResponseDTO
 */
public interface IInscricaoQueryService {

	/**
	 * Busca uma incrição pelo ID, lançando uma exceção se não for encontrada.
	 *
	 * @param id o identificador da incrição.
	 * @return a incrição encontrada.
	 * @throws NotFoundResourceException se a incrição não for encontrada.
	 */
	Inscricao findInscricaoById(int id);

	/**
	 * Retorna a contagem total de inscrições cadastradas.
	 *
	 * @return o número total de inscrições.
	 */
	long count();

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
	 * Verifica se um membro está inscrito com base no ID.
	 *
	 * @param id o identificador da inscrição.
	 * @return {@code true} se o membro estiver inscrito, caso contrário {@code false}.
	 */
	boolean isInscrito(int id);
}
