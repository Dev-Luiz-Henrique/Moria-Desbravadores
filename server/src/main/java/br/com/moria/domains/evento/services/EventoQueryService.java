package br.com.moria.domains.evento.services;

import br.com.moria.domains.evento.Evento;
import br.com.moria.domains.evento.EventoMapper;
import br.com.moria.domains.evento.EventoRepository;
import br.com.moria.domains.evento.dtos.EventoResponseDTO;
import br.com.moria.shared.enums.EntityType;
import br.com.moria.shared.exceptions.NotFoundResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventoQueryService implements IEventoQueryService {

    private final EventoRepository eventoRepository;
    private final EventoMapper eventoMapper;

    @Autowired
    public EventoQueryService(EventoRepository eventoRepository, EventoMapper eventoMapper) {
        this.eventoRepository = eventoRepository;
        this.eventoMapper = eventoMapper;
    }

    @Override
    public Evento findEventoById(int id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> NotFoundResourceException.forEntity(EntityType.EVENTO, id));
    }

    @Override
    public EventoResponseDTO findById(int id) {
        return eventoMapper.toResponseDTO(
                eventoRepository.findById(id)
                        .orElseThrow(() -> NotFoundResourceException.forEntity(EntityType.EVENTO, id))
        );
    }

    @Override
    public List<EventoResponseDTO> findAll() {
        return eventoMapper.toResponseDTO(eventoRepository.findAll());
    }

    @Override
    public List<EventoResponseDTO> findByNomeContaining(String keyword) {
        return eventoMapper.toResponseDTO(eventoRepository.findByNomeContaining(keyword));
    }

    @Override
    public List<EventoResponseDTO> findByDataInicioInterval(LocalDateTime start, LocalDateTime end) {
        return eventoMapper.toResponseDTO(eventoRepository.findByDataInicioBetween(start, end));
    }

    @Override
    public List<EventoResponseDTO> findByDataInicio(LocalDateTime date) {
        return eventoMapper.toResponseDTO(eventoRepository.findByDataInicio(date));
    }

    @Override
    public List<EventoResponseDTO> findByDataFim(LocalDateTime date) {
        return eventoMapper.toResponseDTO(eventoRepository.findByDataFim(date));
    }

    @Override
    public long count() {
        return eventoRepository.count();
    }

    @Override
    public boolean existsById(int id) {
        return eventoRepository.existsById(id);
    }
}
