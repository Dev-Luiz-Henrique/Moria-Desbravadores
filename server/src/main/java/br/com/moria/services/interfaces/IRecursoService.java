package br.com.moria.services.interfaces;

import java.util.List;

import br.com.moria.dtos.Recurso.RecursoCreateDTO;
import br.com.moria.dtos.Recurso.RecursoResponseDTO;
import br.com.moria.dtos.Recurso.RecursoUpdateDTO;

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
public interface IRecursoService {

    /**
     * Retorna a contagem total de recursos cadastrados.
     *
     * @return o número total de recursos.
     */
    long count();

    /**
     * Cria um novo recurso com base nos dados fornecidos.
     *
     * @param recursoCreateDTO os dados do recurso a ser criado.
     * @return os detalhes do recurso criado.
     */
    RecursoResponseDTO create(RecursoCreateDTO recursoCreateDTO);

    /**
     * Atualiza as informações de um recurso existente.
     *
     * @param recursoUpdateDTO os dados atualizados do recurso.
     * @return os detalhes do recurso atualizado.
     */
    RecursoResponseDTO update(RecursoUpdateDTO recursoUpdateDTO);

    /**
     * Remove um recurso pelo ID.
     *
     * @param id o identificador do recurso.
     */
    void delete(int id);

    /**
     * Retorna todos os recursos cadastrados.
     *
     * @return uma lista de todos os recursos.
     */
    List<RecursoResponseDTO> findAll();

    /**
     * Busca os detalhes de um recurso pelo ID.
     *
     * @param id o identificador do recurso.
     * @return os detalhes do recurso encontrado.
     */
    RecursoResponseDTO findById(int id);

    /**
     * Busca todos os recursos associados a um evento específico.
     *
     * @param eventoId o identificador do evento.
     * @return uma lista de recursos relacionados ao evento.
     */
    List<RecursoResponseDTO> findRecursosByEventoId(int eventoId);
}
