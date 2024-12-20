package br.com.moria.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.moria.dtos.Recurso.RecursoCreateDTO;
import br.com.moria.dtos.Recurso.RecursoResponseDTO;
import br.com.moria.dtos.Recurso.RecursoUpdateDTO;
import br.com.moria.models.Recurso;

@Mapper(componentModel = "spring")
public interface RecursoMapper {

    @Mapping(target = "idEvento", source = "evento.id")
    RecursoResponseDTO toResponseDTO(Recurso recurso);

    @Mapping(target = "idEvento", source = "evento.id")
    List<RecursoResponseDTO> toResponseDTO(List<Recurso> recursos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "evento", ignore = true)
    Recurso toEntity(RecursoCreateDTO recursoCreateDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "evento", ignore = true)
    Recurso toEntity(RecursoUpdateDTO recursoUpdateDTO);
}