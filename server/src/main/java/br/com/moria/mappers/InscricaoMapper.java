package br.com.moria.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.moria.dtos.Inscricao.InscricaoCreateDTO;
import br.com.moria.dtos.Inscricao.InscricaoUpdateDTO;
import br.com.moria.dtos.Inscricao.InscricaoResponseDTO;
import br.com.moria.models.Inscricao;

@Mapper
public interface InscricaoMapper {
    
    InscricaoMapper INSTANCE = Mappers.getMapper(InscricaoMapper.class);

    InscricaoResponseDTO toResponseDTO(Inscricao inscricao);
    List<InscricaoResponseDTO> toResponseDTO(List<Inscricao> inscricoes);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "membro", ignore = true)
    @Mapping(target = "evento", ignore = true)
    Inscricao toEntity(InscricaoCreateDTO inscricaoCreateDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "membro", ignore = true)
    @Mapping(target = "evento", ignore = true)
    Inscricao toEntity(InscricaoUpdateDTO inscricaoUpdateDTO);
}
