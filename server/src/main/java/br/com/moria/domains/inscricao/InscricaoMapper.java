package br.com.moria.domains.inscricao;

import java.util.List;

import br.com.moria.domains.evento.EventoMapper;
import br.com.moria.domains.inscricao.dtos.InscricaoCreateDTO;
import br.com.moria.domains.inscricao.dtos.InscricaoResponseDTO;
import br.com.moria.domains.inscricao.dtos.InscricaoUpdateDTO;
import br.com.moria.domains.membro.MembroMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
