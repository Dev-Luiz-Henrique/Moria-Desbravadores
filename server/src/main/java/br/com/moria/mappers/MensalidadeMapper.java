package br.com.moria.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.moria.dtos.Mensalidade.MensalidadeCreateDTO;
import br.com.moria.dtos.Mensalidade.MensalidadeResponseDTO;
import br.com.moria.dtos.Mensalidade.MensalidadeUpdateDTO;
import br.com.moria.models.Mensalidade;

@Mapper
public interface MensalidadeMapper {

    MensalidadeMapper INSTANCE = Mappers.getMapper(MensalidadeMapper.class);

    MensalidadeResponseDTO toResponseDTO(Mensalidade mensalidade);
    List<MensalidadeResponseDTO> toResponseDTO(List<Mensalidade> membros);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "membro", ignore = true)
    Mensalidade toEntity(MensalidadeCreateDTO mensalidadeCreateDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "membro", ignore = true)
    Mensalidade toEntity(MensalidadeUpdateDTO mensalidadeUpdateDTO);
}
