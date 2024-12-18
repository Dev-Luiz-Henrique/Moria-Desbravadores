package br.com.moria.mappers;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.moria.dtos.Evento.EventoCreateDTO;
import br.com.moria.dtos.Evento.EventoUpdateDTO;
import br.com.moria.dtos.Evento.EventoResponseDTO;
import br.com.moria.models.Evento;

@Mapper
public interface EventoMapper {
    EventoMapper INSTANCE = Mappers.getMapper(EventoMapper.class);

    EventoResponseDTO toResponseDTO(Evento evento);
    List<EventoResponseDTO> toResponseDTO(List<Evento> eventos);

    @Mapping(target = "id", ignore = true)
    Evento toEntity(EventoCreateDTO eventoCreateDTO);

    @Mapping(target = "id", ignore = true)
    Evento toEntity(EventoUpdateDTO eventoUpdateDTO);
}