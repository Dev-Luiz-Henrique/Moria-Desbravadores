package br.com.moria.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.moria.dtos.Membro.MembroCreateDTO;
import br.com.moria.dtos.Membro.MembroResponseDTO;
import br.com.moria.dtos.Membro.MembroUpdateDTO;
import br.com.moria.models.Membro;

@Mapper(uses = {EnderecoMapper.class}, componentModel = "spring")
public interface MembroMapper {

    MembroResponseDTO toResponseDTO(Membro membro);
    List<MembroResponseDTO> toResponseDTO(List<Membro> membros);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
    @Mapping(target = "endereco", source = "enderecoCreateDTO")
    Membro toEntity(MembroCreateDTO membroCreateDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
    @Mapping(target = "senha", ignore = true)
    @Mapping(target = "endereco", source = "enderecoUpdateDTO")
    Membro toEntity(MembroUpdateDTO membroUpdateDTO);
}
