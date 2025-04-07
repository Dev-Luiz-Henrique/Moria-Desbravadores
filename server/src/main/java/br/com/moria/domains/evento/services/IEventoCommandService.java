package br.com.moria.domains.evento.services;

import br.com.moria.domains.evento.Evento;
import br.com.moria.domains.evento.dtos.EventoCreateDTO;
import br.com.moria.domains.evento.dtos.EventoResponseDTO;
import br.com.moria.domains.evento.dtos.EventoUpdateDTO;
import br.com.moria.domains.file.FileResponseDTO;
import br.com.moria.shared.exceptions.NotFoundResourceException;
import br.com.moria.shared.exceptions.ValidationException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Interface de serviço para operações relacionadas a eventos.
 *
 * <p>Define métodos para manipulação de dados de eventos, incluindo criação, atualização,
 * exclusão e operações específicas como gestão de imagens do evento.</p>
 *
 * @see Evento
 * @see EventoResponseDTO
 * @see EventoCreateDTO
 * @see EventoUpdateDTO
 */
public interface IEventoCommandService {

    /**
     * Cria um evento com base nos dados fornecidos.
     *
     * @param eventoCreateDTO os dados do evento a ser criado.
     * @return os detalhes do evento criado.
     * @throws ValidationException se a data de término for anterior à data de início.
     */
    EventoResponseDTO create(EventoCreateDTO eventoCreateDTO);

    /**
     * Atualiza as informações de um evento existente.
     *
     * @param eventoUpdateDTO os dados do evento a serem atualizados.
     * @return os detalhes do evento atualizado.
     * @throws NotFoundResourceException se o evento não for encontrado.
     * @throws ValidationException se a data de término for anterior à data de início.
     */
    EventoResponseDTO update(EventoUpdateDTO eventoUpdateDTO);

    /**
     * Remove um evento pelo ID.
     *
     * @param id o identificador do evento.
     * @throws NotFoundResourceException se o evento não for encontrado.
     */
    void delete(int id);

    /**
     * Atualiza a imagem de um evento pelo ID.
     *
     * @param id o identificador do evento.
     * @param file o arquivo da imagem.
     * @return os detalhes do evento com a imagem atualizada.
     * @throws NotFoundResourceException se o evento não for encontrado.
     * @throws IOException se houver erro ao processar o arquivo.
     */
    EventoResponseDTO updateImagemById(int id, MultipartFile file) throws IOException;

    /**
     * Retorna a imagem de um evento pelo ID.
     *
     * @param id o identificador do evento.
     * @return os dados do arquivo da imagem.
     * @throws NotFoundResourceException se o evento não for encontrado.
     * @throws IOException se houver erro ao acessar o arquivo.
     */
    FileResponseDTO findImagemById(int id) throws IOException;
}
