package br.com.moria.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.moria.dtos.Recurso.RecursoCreateDTO;
import br.com.moria.dtos.Recurso.RecursoResponseDTO;
import br.com.moria.dtos.Recurso.RecursoUpdateDTO;
import br.com.moria.models.Recurso;

@Mapper
public interface RecursoMapper {

    RecursoMapper INSTANCE = Mappers.getMapper(RecursoMapper.class);

    RecursoResponseDTO toResponseDTO(Recurso recurso);
    List<RecursoResponseDTO> toResponseDTO(List<Recurso> recursos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "evento", ignore = true)
    Recurso toEntity(RecursoCreateDTO recursoCreateDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "evento", ignore = true)
    Recurso toEntity(RecursoUpdateDTO recursoUpdateDTO);
}