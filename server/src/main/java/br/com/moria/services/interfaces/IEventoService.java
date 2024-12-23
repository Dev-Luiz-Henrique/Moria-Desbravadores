package br.com.moria.services.interfaces;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.moria.dtos.FileResponseDTO;
import br.com.moria.dtos.Evento.EventoCreateDTO;
import br.com.moria.dtos.Evento.EventoResponseDTO;
import br.com.moria.dtos.Evento.EventoUpdateDTO;
import br.com.moria.models.Evento;

/**
 * Interface de serviço para operações relacionadas a eventos.
 *
 * <p>Define métodos para manipulação de dados de eventos, incluindo criação, leitura,
 * atualização, exclusão e operações específicas como gestão de imagens do evento.</p>
 *
 * @see Evento
 * @see EventoResponseDTO
 * @see EventoCreateDTO
 * @see EventoUpdateDTO
 */
public interface IEventoService {

	/**
	 * Busca um evento pelo ID.
	 *
	 * @param id o identificador do evento.
	 * @return o evento encontrado.
	 */
	Evento findEventoById(int id);

	/**
	 * Verifica se um evento existe pelo ID.
	 *
	 * @param id o identificador do evento.
	 * @return {@code true} se o evento existir, caso contrário {@code false}.
	 */
	boolean existsById(int id);

	/**
	 * Cria um evento com base nos dados fornecidos.
	 *
	 * @param eventoCreateDTO os dados do evento a ser criado.
	 * @return os detalhes do evento criado.
	 */
	EventoResponseDTO create(EventoCreateDTO eventoCreateDTO);

	/**
	 * Atualiza as informações de um evento existente.
	 *
	 * @param eventoUpdateDTO os dados do evento a serem atualizados.
	 * @return os detalhes do evento atualizado.
	 */
	EventoResponseDTO update(EventoUpdateDTO eventoUpdateDTO);

	/**
	 * Remove um evento pelo ID.
	 *
	 * @param id o identificador do evento.
	 */
	void delete(int id);

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

	/**
	 * Atualiza a imagem de um evento pelo ID.
	 *
	 * @param id o identificador do evento.
	 * @param file o arquivo da imagem.
	 * @return os detalhes do evento com a imagem atualizada.
	 * @throws IOException se houver erro ao processar o arquivo.
	 */
	EventoResponseDTO updateImagemById(int id, MultipartFile file) throws IOException;

	/**
	 * Retorna a imagem de um evento pelo ID.
	 *
	 * @param id o identificador do evento.
	 * @return os dados do arquivo da imagem.
	 * @throws IOException se houver erro ao acessar o arquivo.
	 */
	FileResponseDTO findImagemById(int id) throws IOException;
}
