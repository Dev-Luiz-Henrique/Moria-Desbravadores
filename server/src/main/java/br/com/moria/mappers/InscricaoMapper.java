package br.com.moria.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.moria.dtos.Inscricao.InscricaoCreateDTO;
import br.com.moria.dtos.Inscricao.InscricaoUpdateDTO;
import br.com.moria.dtos.Inscricao.InscricaoResponseDTO;
import br.com.moria.models.Inscricao;

@Mapper(uses = {EventoMapper.class, MembroMapper.class}, componentModel = "spring")
public interface InscricaoMapper {

    @Mapping(target = "membroInscricaoResponseDTO", source = "membro")
    @Mapping(target = "eventoInscricaoResponseDTO", source = "evento")
    InscricaoResponseDTO toResponseDTO(Inscricao inscricao);

    @Mapping(target = "membroInscricaoResponseDTO", source = "membro")
    @Mapping(target = "eventoInscricaoResponseDTO", source = "evento")
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
