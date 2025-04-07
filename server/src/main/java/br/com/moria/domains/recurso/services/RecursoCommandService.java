package br.com.moria.domains.recurso.services;

import br.com.moria.domains.evento.Evento;
import br.com.moria.domains.evento.services.IEventoQueryService;
import br.com.moria.domains.recurso.Recurso;
import br.com.moria.domains.recurso.RecursoMapper;
import br.com.moria.domains.recurso.RecursoRepository;
import br.com.moria.domains.recurso.dtos.RecursoCreateDTO;
import br.com.moria.domains.recurso.dtos.RecursoResponseDTO;
import br.com.moria.domains.recurso.dtos.RecursoUpdateDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Interface de serviço para operações relacionadas a recursos.
 *
 * <p>Define métodos para manipulação de dados de recursos, incluindo criação,
 * atualização, exclusão e operações específicas.</p>
 *
 * @see IRecursoCommandService
 */
@Service
public class RecursoCommandService implements IRecursoCommandService {

    private final RecursoMapper recursoMapper;
    private final RecursoRepository recursoRepository;
    private final IRecursoQueryService recursoQueryService;
    private final IEventoQueryService eventoQueryService;

    /**
     * Construtor para injeção de dependências.
     *
     * @param recursoMapper        o mapeador de recursos.
     * @param recursoRepository    o repositório de recursos.
     * @param recursoQueryService  o serviço de consulta de recursos.
     * @param eventoQueryService   o serviço de consulta de eventos.
     */
    @Autowired
    public RecursoCommandService(RecursoMapper recursoMapper,
                                   RecursoRepository recursoRepository,
                                   IRecursoQueryService recursoQueryService,
                                   IEventoQueryService eventoQueryService) {
        this.recursoMapper = recursoMapper;
        this.recursoRepository = recursoRepository;
        this.recursoQueryService = recursoQueryService;
        this.eventoQueryService = eventoQueryService;
    }

    @Override
    public RecursoResponseDTO create(@NotNull RecursoCreateDTO recursoCreateDTO) {
        eventoQueryService.assertEventoExists(recursoCreateDTO.getIdEvento());

        Evento evento = eventoQueryService.findEventoById(recursoCreateDTO.getIdEvento());
        Recurso recurso = recursoMapper.toEntity(recursoCreateDTO);
        recurso.setEvento(evento);

        Recurso savedRecurso = recursoRepository.save(recurso);
        return recursoMapper.toResponseDTO(savedRecurso);
    }

    @Override
    public RecursoResponseDTO update(@NotNull RecursoUpdateDTO recursoUpdateDTO) {
        recursoQueryService.findRecursoById(recursoUpdateDTO.getId());
        eventoQueryService.assertEventoExists(recursoUpdateDTO.getIdEvento());

        Evento evento = eventoQueryService.findEventoById(recursoUpdateDTO.getIdEvento());
        Recurso recurso = recursoMapper.toEntity(recursoUpdateDTO);
        recurso.setEvento(evento);

        Recurso updateRecurso = recursoRepository.save(recurso);
        return recursoMapper.toResponseDTO(updateRecurso);
    }

    @Override
    public void delete(int id) {
        recursoQueryService.findRecursoById(id);
        recursoRepository.deleteById(id);
    }
}
