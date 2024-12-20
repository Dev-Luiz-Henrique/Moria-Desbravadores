package br.com.moria.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.moria.dtos.Membro.MembroCreateDTO;
import br.com.moria.dtos.Membro.MembroInscricaoResponseDTO;
import br.com.moria.dtos.Membro.MembroResponseDTO;
import br.com.moria.dtos.Membro.MembroUpdateDTO;
import br.com.moria.models.Membro;

@Mapper(uses = {EnderecoMapper.class}, componentModel = "spring")
public interface MembroMapper {

    @Mapping(target = "enderecoResponseDTO", source = "endereco")
    MembroResponseDTO toResponseDTO(Membro membro);

    @Mapping(target = "enderecoResponseDTO", source = "endereco")
    List<MembroResponseDTO> toResponseDTO(List<Membro> membros);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
    @Mapping(target = "endereco", source = "enderecoCreateDTO")
    Membro toEntity(MembroCreateDTO membroCreateDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
    @Mapping(target = "senha", ignore = true)
    @Mapping(target = "endereco", source = "enderecoCreateDTO")
    Membro toEntity(MembroUpdateDTO membroUpdateDTO);

    MembroInscricaoResponseDTO toInscricaoResponseDTO(Membro membro);
}
