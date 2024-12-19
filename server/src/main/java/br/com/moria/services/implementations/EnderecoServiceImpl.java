package br.com.moria.services.implementations;

import br.com.moria.dtos.Endereco.EnderecoCreateDTO;
import br.com.moria.dtos.Endereco.EnderecoResponseDTO;
import br.com.moria.dtos.Endereco.EnderecoUpdateDTO;
import br.com.moria.mappers.EnderecoMapper;
import br.com.moria.models.Endereco;
import br.com.moria.repositories.EnderecoRepository;
import br.com.moria.services.interfaces.IEnderecoService;
import jakarta.persistence.EntityNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoServiceImpl implements IEnderecoService {

    private final EnderecoMapper enderecoMapper;
    private final EnderecoRepository enderecoRepository;

    @Autowired
    public EnderecoServiceImpl(EnderecoMapper enderecoMapper, EnderecoRepository enderecoRepository) {
        this.enderecoMapper = enderecoMapper;
        this.enderecoRepository = enderecoRepository;
    }

    private void existsByCep(String cep) {
        if (enderecoRepository.existsByCep(cep))
            throw new IllegalArgumentException("Endereço com CEP já cadastrado.");
    }

    private Endereco getEnderecoById(int id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado"));
    }

    @Override
    public EnderecoResponseDTO create(@NotNull EnderecoCreateDTO enderecoCreateDTO) {
        existsByCep(enderecoCreateDTO.getCep());

        Endereco endereco = enderecoMapper.toEntity(enderecoCreateDTO);
        Endereco savedEndereco = enderecoRepository.save(endereco);
        return enderecoMapper.toResponseDTO(savedEndereco);
    }

    @Override
    public EnderecoResponseDTO update(@NotNull EnderecoUpdateDTO enderecoUpdateDTO) {
        Endereco existingEndereco = enderecoRepository.findById(enderecoUpdateDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado"));

        if (!existingEndereco.getCep().equals(enderecoUpdateDTO.getCep()))
            existsByCep(enderecoUpdateDTO.getCep());

        Endereco endereco = enderecoMapper.toEntity(enderecoUpdateDTO);
        Endereco savedEndereco = enderecoRepository.save(endereco);
        return enderecoMapper.toResponseDTO(savedEndereco);
    }

    @Override
    public void delete(int id) {
        Endereco existingEndereco = getEnderecoById(id);
        enderecoRepository.delete(existingEndereco);
    }

    @Override
    public EnderecoResponseDTO findById(int id) {
        Endereco existingEndereco = getEnderecoById(id);
        return enderecoMapper.toResponseDTO(existingEndereco);
    }

    @Override
    public Endereco findOrCreate(@NotNull EnderecoCreateDTO enderecoCreateDTO) {
        return enderecoRepository.findByCep(enderecoCreateDTO.getCep())
                .orElseGet(() -> {
                    Endereco newEndereco = enderecoMapper.toEntity(enderecoCreateDTO);
                    return enderecoRepository.save(newEndereco);
                });
    }
}
