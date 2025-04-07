package br.com.moria.domains.recurso.services;

import java.util.List;

import br.com.moria.domains.evento.services.IEventoQueryService;
import br.com.moria.domains.recurso.Recurso;
import br.com.moria.domains.recurso.RecursoMapper;
import br.com.moria.domains.recurso.RecursoRepository;
import br.com.moria.domains.recurso.dtos.RecursoResponseDTO;
import br.com.moria.shared.enums.EntityType;
import br.com.moria.shared.exceptions.NotFoundResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementação do serviço para operações relacionadas a visualização de recursos.
 *
 * <p>Fornece funcionalidades para consulta de recursos, como obter dados detalhados de um recurso
 * e listar recursos com base em diferentes critérios.</p>
 *
 * @see IRecursoQueryService
 */
@Service
public class RecursoQueryService implements IRecursoQueryService {

    private final RecursoMapper recursoMapper;
    private final RecursoRepository recursoRepository;
    private final IEventoQueryService eventoQueryService;

    /**
     * Construtor para injeção de dependências.
     *
     * @param recursoMapper        o mapeador de recursos.
     * @param recursoRepository    o repositório de recursos.
     */
    @Autowired
    public RecursoQueryService(RecursoMapper recursoMapper,
                               RecursoRepository recursoRepository,
                               IEventoQueryService eventoQueryService) {
        this.recursoMapper = recursoMapper;
        this.recursoRepository = recursoRepository;
        this.eventoQueryService = eventoQueryService;
    }

    /**
     * Busca um recurso pelo ID, lançando uma exceção se não for encontrado.
     *
     * @param id o identificador do recurso.
     * @return o recurso encontrado.
     * @throws NotFoundResourceException se o recurso não for encontrado.
     */
    @Override
    public Recurso findRecursoById(int id){
        return recursoRepository.findById(id)
                .orElseThrow(() -> NotFoundResourceException.forEntity(EntityType.RECURSO, id));
    }

    @Override
    public long count() {
        return recursoRepository.count();
    }

	@Override
	public List<RecursoResponseDTO> findAll() {
		List<Recurso> recursos = recursoRepository.findAll();
		return recursoMapper.toResponseDTO(recursos);
	}

    @Override
    public RecursoResponseDTO findById(int id) {
        Recurso recurso = findRecursoById(id);
        return recursoMapper.toResponseDTO(recurso);
    }

    @Override
    public List<RecursoResponseDTO> findRecursosByEventoId(int eventoId) {
        eventoQueryService.assertEventoExists(eventoId);

        List<Recurso> recursos = recursoRepository.findByEventoId(eventoId);
        return recursoMapper.toResponseDTO(recursos);
    }
}
