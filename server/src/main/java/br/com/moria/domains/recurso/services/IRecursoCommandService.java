package br.com.moria.domains.recurso.services;

import br.com.moria.domains.recurso.dtos.RecursoCreateDTO;
import br.com.moria.domains.recurso.dtos.RecursoResponseDTO;
import br.com.moria.domains.recurso.dtos.RecursoUpdateDTO;
import br.com.moria.shared.exceptions.NotFoundResourceException;

/**
 * Interface de serviço para operações relacionadas a recursos.
 *
 * <p>Define métodos para manipulação de dados de recursos, incluindo criação,
 * atualização, exclusão e operações específicas.</p>
 *
 * @see RecursoResponseDTO
 * @see RecursoCreateDTO
 * @see RecursoUpdateDTO
 */
public interface IRecursoCommandService {

    /**
     * Cria um novo recurso com base nos dados fornecidos.
     *
     * @param recursoCreateDTO os dados do recurso a ser criado.
     * @return os detalhes do recurso criado.
     * @throws NotFoundResourceException se o evento associado ao recurso não for encontrado.
     */
    RecursoResponseDTO create(RecursoCreateDTO recursoCreateDTO);

    /**
     * Atualiza as informações de um recurso existente.
     *
     * @param recursoUpdateDTO os dados atualizados do recurso.
     * @return os detalhes do recurso atualizado.
     * @throws NotFoundResourceException se o recurso ou o evento associado não for encontrado.
     */
    RecursoResponseDTO update(RecursoUpdateDTO recursoUpdateDTO);

    /**
     * Remove um recurso pelo ID.
     *
     * @param id o identificador do recurso.
     * @throws NotFoundResourceException se o recurso não for encontrado.
     */
    void delete(int id);
}
