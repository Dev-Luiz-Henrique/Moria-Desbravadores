package br.com.moria.domains.membro.services;

import java.io.IOException;
import java.util.List;

import br.com.moria.domains.membro.Membro;
import br.com.moria.domains.membro.dtos.MembroCreateDTO;
import br.com.moria.domains.membro.dtos.MembroResponseDTO;
import br.com.moria.domains.membro.dtos.MembroUpdateDTO;
import org.springframework.web.multipart.MultipartFile;

import br.com.moria.domains.file.FileResponseDTO;

/**
 * Interface de serviço para operações relacionadas a membros.
 *
 * <p>Define métodos para manipulação de dados de membros, incluindo criação, leitura,
 * atualização, exclusão e operações específicas como gestão de fichas de saúde.</p>
 *
 * @see Membro
 * @see MembroResponseDTO
 * @see MembroCreateDTO
 * @see MembroUpdateDTO
 */
public interface IMembroService {

    /**
     * Retorna uma lista de todos os membros ativos ou inativos.
     *
     * @param ativo {@code true} para membros ativos, {@code false} para inativos.
     * @return uma lista de membros filtrados pelo status de atividade.
     */
    List<Membro> findAllMembrosByAtivo(boolean ativo);

    /**
     * Busca um membro pelo ID.
     *
     * @param id o identificador do membro.
     * @return o membro encontrado.
     */
    Membro findMembroById(int id);

    /**
     * Retorna a contagem total de membros cadastrados.
     *
     * @return o número total de membros.
     */
    long count();

    /**
     * Verifica se um membro existe pelo ID.
     *
     * @param id o identificador do membro.
     * @return {@code true} se o membro existir, caso contrário {@code false}.
     */
    boolean existsById(int id);

    /**
     * Cria um membro com base nos dados fornecidos.
     *
     * @param membroCreateDTO os dados do membro a ser criado.
     * @return os detalhes do membro criado.
     */
    MembroResponseDTO create(MembroCreateDTO membroCreateDTO);

    /**
     * Atualiza as informações de um membro existente.
     *
     * @param membroUpdateDTO os dados do membro a serem atualizados.
     * @return os detalhes do membro atualizado.
     */
    MembroResponseDTO update(MembroUpdateDTO membroUpdateDTO);

    /**
     * Remove um membro pelo ID.
     *
     * @param id o identificador do membro.
     */
    void delete(int id);

    /**
     * Retorna todos os membros cadastrados.
     *
     * @return uma lista de todos os membros.
     */
    List<MembroResponseDTO> findAll();

    /**
     * Busca um membro pelo ID.
     *
     * @param id o identificador do membro.
     * @return os detalhes do membro.
     */
    MembroResponseDTO findById(int id);

    /**
     * Busca um membro pelo CPF.
     *
     * @param cpf o CPF do membro.
     * @return os detalhes do membro.
     */
    MembroResponseDTO findByCpf(String cpf);

    /**
     * Busca um membro pelo endereço de e-mail.
     *
     * @param email o e-mail do membro.
     * @return os detalhes do membro.
     */
    MembroResponseDTO findByEmail(String email);

    /**
     * Busca membros cujo nome contenha a string especificada.
     *
     * @param nome parte do nome a ser pesquisado.
     * @return uma lista de membros que atendem ao critério de pesquisa.
     */
    List<MembroResponseDTO> findByNomeContaining(String nome);

    /**
     * Retorna uma lista de membros ativos ou inativos.
     *
     * @param ativo {@code true} para ativos, {@code false} para inativos.
     * @return uma lista de {@link MembroResponseDTO}.
     */
    List<MembroResponseDTO> findByAtivo(boolean ativo);

    /**
     * Retorna a ficha de saúde de um membro pelo ID.
     *
     * @param id o identificador do membro.
     * @return os dados do arquivo da ficha de saúde.
     * @throws IOException se houver erro ao acessar o arquivo.
     */
    FileResponseDTO findFichaSaudeById(int id) throws IOException;

    /**
     * Atualiza a ficha de saúde de um membro pelo ID.
     *
     * @param id o identificador do membro.
     * @param file o arquivo da ficha de saúde.
     * @return os detalhes do membro com a ficha de saúde atualizada.
     * @throws IOException se houver erro ao processar o arquivo.
     */
    MembroResponseDTO updateFichaSaudeById(int id, MultipartFile file) throws IOException;
}
