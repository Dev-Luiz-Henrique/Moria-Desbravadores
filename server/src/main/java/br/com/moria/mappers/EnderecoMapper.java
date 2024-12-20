package br.com.moria.mappers;

import br.com.moria.dtos.Endereco.EnderecoCreateDTO;
import br.com.moria.dtos.Endereco.EnderecoResponseDTO;
import br.com.moria.dtos.Endereco.EnderecoUpdateDTO;
import br.com.moria.models.Endereco;
import org.mapstruct.Mapping;

import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring")
public interface EnderecoMapper {

    EnderecoResponseDTO toResponseDTO(Endereco endereco);
    List<EnderecoResponseDTO> toResponseDTO(List<Endereco> enderecos);

    @Mapping(target = "id", ignore = true)
    Endereco toEntity(EnderecoCreateDTO enderecoCreateDTO);

    @Mapping(target = "id", ignore = true)
    Endereco toEntity(EnderecoUpdateDTO enderecoUpdateDTO);
}
