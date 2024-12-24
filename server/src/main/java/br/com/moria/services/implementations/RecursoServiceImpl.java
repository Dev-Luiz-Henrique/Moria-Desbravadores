package br.com.moria.services.implementations;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moria.dtos.Recurso.RecursoCreateDTO;
import br.com.moria.dtos.Recurso.RecursoResponseDTO;
import br.com.moria.dtos.Recurso.RecursoUpdateDTO;
import br.com.moria.mappers.RecursoMapper;
import br.com.moria.models.Evento;
import br.com.moria.models.Recurso;
import br.com.moria.repositories.RecursoRepository;
import br.com.moria.services.interfaces.IEventoService;
import br.com.moria.services.interfaces.IRecursoService;

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

    private void validateEvento(int eventoId){
        if (!eventoService.existsById(eventoId))
            throw new IllegalArgumentException("Evento não encontrado para o recurso fornecido.");
    }

    private Recurso findRecursoById(int id){
        return recursoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Recurso não encontrado."));
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
