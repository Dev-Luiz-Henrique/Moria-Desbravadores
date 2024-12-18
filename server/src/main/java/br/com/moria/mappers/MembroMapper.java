package br.com.moria.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.moria.dtos.Membro.MembroCreateDTO;
import br.com.moria.dtos.Membro.MembroResponseDTO;
import br.com.moria.dtos.Membro.MembroUpdateDTO;
import br.com.moria.models.Membro;

@Mapper
public interface MembroMapper {

    MembroMapper INSTANCE = Mappers.getMapper(MembroMapper.class);

    MembroResponseDTO toResponseDTO(Membro membro);
    List<MembroResponseDTO> toResponseDTO(List<Membro> membros);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
    Membro toEntity(MembroCreateDTO membroCreateDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true) 
    @Mapping(target = "senha", ignore = true)
    Membro toEntity(MembroUpdateDTO membroUpdateDTO);
}