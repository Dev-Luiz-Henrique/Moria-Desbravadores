package br.com.moria.domains.endereco;

import br.com.moria.domains.endereco.dtos.EnderecoCreateDTO;
import br.com.moria.domains.endereco.dtos.EnderecoResponseDTO;
import br.com.moria.domains.endereco.dtos.EnderecoUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    EnderecoResponseDTO toResponseDTO(Endereco endereco);
    List<EnderecoResponseDTO> toResponseDTO(List<Endereco> enderecos);

    @Mapping(target = "id", ignore = true)
    Endereco toEntity(EnderecoCreateDTO enderecoCreateDTO);

    @Mapping(target = "id", ignore = true)
    Endereco toEntity(EnderecoUpdateDTO enderecoUpdateDTO);
}
