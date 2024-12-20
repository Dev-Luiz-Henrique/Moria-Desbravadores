package br.com.moria.mappers;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.moria.dtos.Evento.EventoCreateDTO;
import br.com.moria.dtos.Evento.EventoInscricaoResponseDTO;
import br.com.moria.dtos.Evento.EventoUpdateDTO;
import br.com.moria.dtos.Evento.EventoResponseDTO;
import br.com.moria.models.Evento;

@Mapper(uses = {EnderecoMapper.class}, componentModel = "spring")
public interface EventoMapper {

    @Mapping(target = "enderecoResponseDTO", source = "endereco")
    EventoResponseDTO toResponseDTO(Evento evento);

    @Mapping(target = "enderecoResponseDTO", source = "endereco")
    List<EventoResponseDTO> toResponseDTO(List<Evento> eventos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "endereco", source = "enderecoCreateDTO")
    Evento toEntity(EventoCreateDTO eventoCreateDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "endereco", source = "enderecoCreateDTO")
    Evento toEntity(EventoUpdateDTO eventoUpdateDTO);

    EventoInscricaoResponseDTO toInscricaoResponse(Evento evento);
}