package br.com.moria.domains.membro.services;

import java.io.IOException;
import java.util.List;

import br.com.moria.domains.membro.Membro;
import br.com.moria.domains.membro.dtos.MembroCreateDTO;
import br.com.moria.domains.membro.dtos.MembroResponseDTO;
import br.com.moria.domains.membro.dtos.MembroUpdateDTO;
import br.com.moria.shared.exceptions.DuplicatedResourceException;
import br.com.moria.shared.exceptions.NotFoundResourceException;
import org.springframework.web.multipart.MultipartFile;

import br.com.moria.domains.file.FileResponseDTO;

/**
 * Interface de serviço para operações relacionadas a membros.
 *
 * <p>Define métodos para manipulação de dados de membros, incluindo criação, atualização,
 * exclusão e operações específicas como gestão de fichas de saúde.</p>
 *
 * @see Membro
 * @see MembroResponseDTO
 * @see MembroCreateDTO
 * @see MembroUpdateDTO
 */
public interface IMembroCommandService {

    /**
     * Cria um novo membro com base nos dados fornecidos.
     *
     * @param membroCreateDTO os dados do membro a ser criado.
     * @return os detalhes do membro criado.
     * @throws DuplicatedResourceException se já existir um membro com o mesmo e-mail ou CPF.
     */
    MembroResponseDTO create(MembroCreateDTO membroCreateDTO);

    /**
     * Cria um novo membro com base nos dados fornecidos.
     * Permite a criação de membros com funções não administrativas.
     *
     * @param dto os dados do membro a ser cadastrado.
     * @return os detalhes do membro cadastrado.
     */
    MembroResponseDTO selfRegister(MembroCreateDTO dto);

    /**
     * Atualiza as informações de um membro existente.
     *
     * @param membroUpdateDTO os dados do membro a serem atualizados.
     * @return os detalhes do membro atualizado.
     * @throws NotFoundResourceException se o membro não for encontrado.
     * @throws DuplicatedResourceException se o novo e-mail ou CPF já estiver cadastrado.
     */
    MembroResponseDTO update(MembroUpdateDTO membroUpdateDTO);

    /**
     * Remove um membro pelo ID.
     *
     * @param id o identificador do membro.
     * @throws NotFoundResourceException se o membro não for encontrado.
     */
    void delete(int id);

    /**
     * Retorna a ficha de saúde de um membro pelo ID.
     *
     * @param id o identificador do membro.
     * @return os dados do arquivo da ficha de saúde.
     * @throws IOException se houver erro ao acessar o arquivo.
     * @throws NotFoundResourceException se o membro não for encontrado.
     */
    FileResponseDTO findFichaSaudeById(int id) throws IOException, NotFoundResourceException;

    /**
     * Atualiza a ficha de saúde de um membro pelo ID.
     *
     * @param id o identificador do membro.
     * @param file o arquivo da ficha de saúde.
     * @return os detalhes do membro com a ficha de saúde atualizada.
     * @throws IOException se houver erro ao processar o arquivo.
     * @throws NotFoundResourceException se o membro não for encontrado.
     */
    MembroResponseDTO updateFichaSaudeById(int id, MultipartFile file) throws IOException, NotFoundResourceException;
}
