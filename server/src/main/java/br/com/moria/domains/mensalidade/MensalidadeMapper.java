package br.com.moria.domains.mensalidade;

import java.util.List;

import br.com.moria.domains.mensalidade.dtos.MensalidadeCreateDTO;
import br.com.moria.domains.mensalidade.dtos.MensalidadeResponseDTO;
import br.com.moria.domains.mensalidade.dtos.MensalidadeUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
