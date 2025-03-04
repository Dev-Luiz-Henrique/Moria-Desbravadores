package br.com.moria.domains.membro.services;

import br.com.moria.domains.membro.Membro;
import br.com.moria.domains.membro.MembroMapper;
import br.com.moria.domains.membro.MembroRepository;
import br.com.moria.domains.membro.dtos.MembroResponseDTO;
import br.com.moria.shared.enums.EntityType;
import br.com.moria.shared.exceptions.NotFoundResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementação do serviço para operações relacionadas à visualização de membros.
 *
 * <p>Fornece funcionalidades para consulta de membros, como obter dados detalhados de um membro
 * e listar membros com base em diferentes critérios.</p>
 *
 * @see IMembroQueryService
 */
@Service
public class MembroQueryService implements IMembroQueryService {

    private final MembroRepository membroRepository;
    private final MembroMapper membroMapper;

    /**
     * Construtor para injeção de dependências.
     *
     * @param membroMapper       o mapper para conversão entre DTO e entidade.
     * @param membroRepository   o repositório para operações com a entidade {@link Membro}.
     */
    @Autowired
    public MembroQueryService(MembroRepository membroRepository, MembroMapper membroMapper) {
        this.membroRepository = membroRepository;
        this.membroMapper = membroMapper;
    }

    @Override
    public List<Membro> findAllMembrosByAtivo(boolean ativo){
        return membroRepository.findByAtivo(ativo);
    }

    @Override
    public Membro findMembroById(int id) {
        return membroRepository.findById(id)
                .orElseThrow(() -> NotFoundResourceException.forEntity(EntityType.MEMBRO, id));
    }

    @Override
    public long count(){
        return membroRepository.count();
    }

    @Override
    public boolean existsById(int id) {
        return membroRepository.existsById(id);
    }

    @Override
    public List<MembroResponseDTO> findAll() {
        return membroMapper.toResponseDTO(membroRepository.findAll());
    }

    @Override
    public MembroResponseDTO findById(int id) {
        return membroMapper.toResponseDTO(
                membroRepository.findById(id).orElseThrow(() -> NotFoundResourceException.forEntity(EntityType.MEMBRO, id))
        );
    }

    @Override
    public MembroResponseDTO findByCpf(String cpf) {
        return membroMapper.toResponseDTO(
                membroRepository.findByCpf(cpf).orElseThrow(() ->
                        NotFoundResourceException.forEntity(EntityType.MEMBRO, "business.membro.cpf.not_found"))
        );
    }

    @Override
    public MembroResponseDTO findByEmail(String email) {
        return membroMapper.toResponseDTO(
                membroRepository.findByEmail(email).orElseThrow(() ->
                        NotFoundResourceException.forEntity(EntityType.MEMBRO, "business.membro.email.not_found"))
        );
    }

    @Override
    public List<MembroResponseDTO> findByNomeContaining(String nome) {
        return membroMapper.toResponseDTO(membroRepository.findByNomeContaining(nome));
    }

    @Override
    public List<MembroResponseDTO> findByAtivo(boolean ativo) {
        return membroMapper.toResponseDTO(membroRepository.findByAtivo(ativo));
    }
}
