package br.com.moria.domains.recurso.services;

import java.util.List;

import br.com.moria.domains.recurso.Recurso;
import br.com.moria.domains.recurso.RecursoMapper;
import br.com.moria.domains.recurso.RecursoRepository;
import br.com.moria.domains.recurso.dtos.RecursoCreateDTO;
import br.com.moria.domains.recurso.dtos.RecursoResponseDTO;
import br.com.moria.domains.recurso.dtos.RecursoUpdateDTO;
import br.com.moria.shared.enums.EntityType;
import br.com.moria.shared.exceptions.NotFoundResourceException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moria.domains.evento.Evento;
import br.com.moria.domains.evento.services.IEventoService;

@Service
public class RecursoServiceImpl implements IRecursoService {

    private final RecursoMapper recursoMapper;
    private final RecursoRepository recursoRepository;
    private final IEventoService eventoService;

    @Autowired
    public RecursoServiceImpl(RecursoMapper recursoMapper,
                              RecursoRepository recursoRepository,
                              IEventoService eventoService) {
        this.recursoMapper = recursoMapper;
        this.recursoRepository = recursoRepository;
        this.eventoService = eventoService;
    }

    /**
     * Valida se o evento existe.
     *
     * @param eventoId o identificador do evento.
     * @throws NotFoundResourceException se o evento não for encontrado.
     */
    private void validateEvento(int eventoId){
        if (!eventoService.existsById(eventoId))
            throw NotFoundResourceException.forEntity(EntityType.EVENTO, eventoId);
    }

    /**
     * Busca um recurso pelo ID, lançando uma exceção se não for encontrado.
     *
     * @param id o identificador do recurso.
     * @return o recurso encontrado.
     * @throws NotFoundResourceException se o recurso não for encontrado.
     */
    private Recurso findRecursoById(int id){
        return recursoRepository.findById(id)
                .orElseThrow(() -> NotFoundResourceException.forEntity(EntityType.RECURSO, id));
    }

    @Override
    public long count() {
        return recursoRepository.count();
    }

    @Override
    public RecursoResponseDTO create(@NotNull RecursoCreateDTO recursoCreateDTO) {
        validateEvento(recursoCreateDTO.getIdEvento());

        Evento evento = eventoService.findEventoById(recursoCreateDTO.getIdEvento());
        Recurso recurso = recursoMapper.toEntity(recursoCreateDTO);
        recurso.setEvento(evento);

        Recurso savedRecurso = recursoRepository.save(recurso);
        return recursoMapper.toResponseDTO(savedRecurso);
    }

	@Override
	public RecursoResponseDTO update(@NotNull RecursoUpdateDTO recursoUpdateDTO) {
        findRecursoById(recursoUpdateDTO.getId());
        validateEvento(recursoUpdateDTO.getIdEvento());

        Evento evento = eventoService.findEventoById(recursoUpdateDTO.getIdEvento());
        Recurso recurso = recursoMapper.toEntity(recursoUpdateDTO);
        recurso.setEvento(evento);

        Recurso updateRecurso = recursoRepository.save(recurso);
		return recursoMapper.toResponseDTO(updateRecurso);
	}

	@Override
	public void delete(int id) {
        findRecursoById(id);
        recursoRepository.deleteById(id);
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
        validateEvento(eventoId);
        List<Recurso> recursos = recursoRepository.findByEventoId(eventoId);
        return recursoMapper.toResponseDTO(recursos);
    }
}
