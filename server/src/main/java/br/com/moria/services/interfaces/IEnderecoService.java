package br.com.moria.services.interfaces;

import br.com.moria.dtos.Endereco.EnderecoCreateDTO;
import br.com.moria.dtos.Endereco.EnderecoResponseDTO;
import br.com.moria.dtos.Endereco.EnderecoUpdateDTO;
import br.com.moria.models.Endereco;

/**
 * Interface de serviço para operações relacionadas a endereços.
 *
 * <p>Define métodos para manipulação de dados de endereços, incluindo criação, leitura,
 * atualização, exclusão e operações específicas.</p>
 *
 * @see Endereco
 * @see EnderecoResponseDTO
 * @see EnderecoCreateDTO
 * @see EnderecoUpdateDTO
 */
public interface IEnderecoService {

    /**
     * Cria um endereço com base nos dados fornecidos.
     *
     * @param enderecoCreateDTO os dados do endereço a ser criado.
     * @return os detalhes do endereço criado.
     */
    EnderecoResponseDTO create(EnderecoCreateDTO enderecoCreateDTO);

    /**
     * Atualiza as informações de um endereço existente.
     *
     * @param enderecoUpdateDTO os dados do endereço a serem atualizados.
     * @return os detalhes do endereço atualizado.
     */
    EnderecoResponseDTO update(EnderecoUpdateDTO enderecoUpdateDTO);

    /**
     * Remove um endereço pelo ID.
     *
     * @param id o identificador do endereço.
     */
    void delete(int id);

    /**
     * Busca um endereço pelo ID.
     *
     * @param id o identificador do endereço.
     * @return os detalhes do endereço.
     */
    EnderecoResponseDTO findById(int id);

    /**
     * Busca ou cria um endereço com base nos dados fornecidos.
     * Se o endereço já existir, retorna o endereço existente.
     * Caso contrário, cria um endereço.
     *
     * @param enderecoCreateDTO os dados do endereço a ser buscado ou criado.
     * @return o endereço encontrado ou criado.
     */
    Endereco findOrCreate(EnderecoCreateDTO enderecoCreateDTO);
}
