package br.com.moria.services.interfaces;

import br.com.moria.dtos.Endereco.EnderecoCreateDTO;
import br.com.moria.dtos.Endereco.EnderecoResponseDTO;
import br.com.moria.dtos.Endereco.EnderecoUpdateDTO;
import br.com.moria.models.Endereco;

public interface IEnderecoService {

    EnderecoResponseDTO create(EnderecoCreateDTO enderecoCreateDTO);
    EnderecoResponseDTO update(EnderecoUpdateDTO enderecoUpdateDTO);
    void delete(int id);
    EnderecoResponseDTO findById(int id);
    Endereco findOrCreate(EnderecoCreateDTO enderecoCreateDTO);
}
