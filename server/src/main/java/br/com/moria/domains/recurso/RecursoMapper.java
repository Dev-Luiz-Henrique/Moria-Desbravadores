package br.com.moria.domains.recurso;

import java.util.List;

import br.com.moria.domains.recurso.dtos.RecursoCreateDTO;
import br.com.moria.domains.recurso.dtos.RecursoResponseDTO;
import br.com.moria.domains.recurso.dtos.RecursoUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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