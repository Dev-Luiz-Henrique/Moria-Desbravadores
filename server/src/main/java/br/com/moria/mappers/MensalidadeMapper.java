package br.com.moria.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.moria.dtos.Mensalidade.MensalidadeCreateDTO;
import br.com.moria.dtos.Mensalidade.MensalidadeResponseDTO;
import br.com.moria.dtos.Mensalidade.MensalidadeUpdateDTO;
import br.com.moria.models.Mensalidade;

@Mapper(componentModel = "spring")
public interface MensalidadeMapper {

    @Mapping(target = "idMembro", source = "membro.id")
    MensalidadeResponseDTO toResponseDTO(Mensalidade mensalidade);

    @Mapping(target = "idMembro", source = "membro.id")
    List<MensalidadeResponseDTO> toResponseDTO(List<Mensalidade> mensalidades);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "membro", ignore = true)
    Mensalidade toEntity(MensalidadeCreateDTO mensalidadeCreateDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "membro", ignore = true)
    Mensalidade toEntity(MensalidadeUpdateDTO mensalidadeUpdateDTO);
}
