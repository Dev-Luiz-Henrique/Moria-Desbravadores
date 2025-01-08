package br.com.moria.domains.evento;
import java.util.List;

import br.com.moria.domains.endereco.EnderecoMapper;
import br.com.moria.domains.evento.dtos.EventoCreateDTO;
import br.com.moria.domains.evento.dtos.EventoInscricaoResponseDTO;
import br.com.moria.domains.evento.dtos.EventoResponseDTO;
import br.com.moria.domains.evento.dtos.EventoUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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