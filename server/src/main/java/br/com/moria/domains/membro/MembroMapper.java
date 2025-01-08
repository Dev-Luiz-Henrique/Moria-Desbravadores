package br.com.moria.domains.membro;

import java.util.List;

import br.com.moria.domains.endereco.EnderecoMapper;
import br.com.moria.domains.membro.dtos.MembroCreateDTO;
import br.com.moria.domains.membro.dtos.MembroInscricaoResponseDTO;
import br.com.moria.domains.membro.dtos.MembroResponseDTO;
import br.com.moria.domains.membro.dtos.MembroUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
