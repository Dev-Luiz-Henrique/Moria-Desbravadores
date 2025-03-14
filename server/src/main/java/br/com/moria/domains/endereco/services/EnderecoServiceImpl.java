package br.com.moria.domains.endereco.services;

import br.com.moria.domains.endereco.Endereco;
import br.com.moria.domains.endereco.EnderecoMapper;
import br.com.moria.domains.endereco.EnderecoRepository;
import br.com.moria.domains.endereco.dtos.EnderecoCreateDTO;
import br.com.moria.domains.endereco.dtos.EnderecoResponseDTO;
import br.com.moria.domains.endereco.dtos.EnderecoUpdateDTO;
import br.com.moria.shared.enums.EntityType;
import br.com.moria.shared.exceptions.DuplicatedResourceException;
import br.com.moria.shared.exceptions.NotFoundResourceException;
import br.com.moria.shared.exceptions.ValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementação do serviço de operações relacionadas a endereços.
 *
 * <p>Esta classe fornece a lógica para manipular endereços, incluindo validações,
 * criação, atualização, exclusão.</p>
 *
 * @see IEnderecoService
 */
@Service
public class EnderecoServiceImpl implements IEnderecoService {   // TODO Revisar essa lógica copletamente

    private final EnderecoMapper enderecoMapper;
    private final EnderecoRepository enderecoRepository;

    /**
     * Construtor para injeção de dependências.
     *
     * @param enderecoMapper      o mapper para conversão entre DTO e entidade.
     * @param enderecoRepository  o repositório para operações com a entidade {@link Endereco}.
     */
    @Autowired
    public EnderecoServiceImpl(EnderecoMapper enderecoMapper, EnderecoRepository enderecoRepository) {
        this.enderecoMapper = enderecoMapper;
        this.enderecoRepository = enderecoRepository;
    }

    /**
     * Verifica se já existe um endereço com o CEP informado.
     *
     * @param cep o CEP a ser verificado.
     * @throws ValidationException se já existir um endereço com o CEP fornecido.
     */
    private void existsByCep(String cep) {
        if (enderecoRepository.existsByCep(cep))
            throw DuplicatedResourceException.forEntity(EntityType.ENDERECO, "business.endereco.cep.duplicated");
    }

    /**
     * Busca um endereço pelo ID, lançando uma exceção se não for encontrado.
     *
     * @param id o identificador do endereço.
     * @return o endereço encontrado.
     * @throws NotFoundResourceException se o endereço não for encontrado.
     */
    private Endereco findEnderecoById(int id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> NotFoundResourceException.forEntity(EntityType.ENDERECO, id));
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
        Endereco existingEndereco = findEnderecoById(enderecoUpdateDTO.getId());

        if (!existingEndereco.getCep().equals(enderecoUpdateDTO.getCep()))
            existsByCep(enderecoUpdateDTO.getCep());

        Endereco endereco = enderecoMapper.toEntity(enderecoUpdateDTO);
        Endereco updatedEndereco = enderecoRepository.save(endereco);
        return enderecoMapper.toResponseDTO(updatedEndereco);
    }

    @Override
    public void delete(int id) {
        Endereco existingEndereco = findEnderecoById(id);
        enderecoRepository.delete(existingEndereco);
    }

    @Override
    public EnderecoResponseDTO findById(int id) {
        Endereco existingEndereco = findEnderecoById(id);
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
