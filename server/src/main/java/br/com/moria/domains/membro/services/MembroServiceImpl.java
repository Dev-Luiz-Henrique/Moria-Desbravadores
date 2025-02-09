package br.com.moria.domains.membro.services;

import java.io.IOException;
import java.util.List;

import br.com.moria.domains.endereco.services.IEnderecoService;
import br.com.moria.domains.membro.Membro;
import br.com.moria.domains.membro.MembroMapper;
import br.com.moria.domains.membro.MembroRepository;
import br.com.moria.domains.membro.dtos.MembroCreateDTO;
import br.com.moria.domains.membro.dtos.MembroResponseDTO;
import br.com.moria.domains.membro.dtos.MembroUpdateDTO;
import br.com.moria.shared.enums.EntityType;
import br.com.moria.shared.exceptions.DuplicatedResourceException;
import br.com.moria.shared.exceptions.NotFoundResourceException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.moria.domains.file.FileResponseDTO;
import br.com.moria.domains.endereco.Endereco;
import br.com.moria.domains.file.IFileService;
import jakarta.persistence.EntityNotFoundException;

/**
 * Implementação do serviço para operações relacionadas a membros.
 *
 * <p>Fornece funcionalidades para criação, atualização, exclusão e consulta de membros,
 * bem como operações específicas, como o gerenciamento de fichas de saúde.</p>
 *
 * @see IMembroService
 */
@Service
public class MembroServiceImpl implements IMembroService {

    private final MembroMapper membroMapper;
    private final MembroRepository membroRepository;
    private final IEnderecoService enderecoService;
    private final IFileService uploadService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Construtor para injeção de dependências.
     *
     * @param membroMapper       o mapper para conversão entre DTO e entidade.
     * @param membroRepository   o repositório para operações com a entidade {@link Membro}.
     * @param enderecoService    o serviço para manipulação de endereços.
     * @param uploadService      o serviço para manipulação de arquivos.
     * @param passwordEncoder    o codificador de senhas.
     */
    @Autowired
    public MembroServiceImpl(MembroMapper membroMapper,
                             MembroRepository membroRepository,
                             IEnderecoService enderecoService,
                             IFileService uploadService,
                             PasswordEncoder passwordEncoder) {
        this.membroMapper = membroMapper;
        this.membroRepository = membroRepository;
        this.enderecoService = enderecoService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Verifica se já existe um membro com o e-mail fornecido.
     *
     * @param email o e-mail a ser verificado.
     * @throws DuplicatedResourceException se o e-mail já estiver cadastrado.
     */
    private void existsByEmail(String email) {
        if (membroRepository.existsByEmail(email))
            throw DuplicatedResourceException.forEntity(EntityType.MEMBRO,"business.membro.email.duplicated");
    }

    /**
     * Verifica se já existe um membro com o CPF fornecido.
     *
     * @param cpf o CPF a ser verificado.
     * @throws DuplicatedResourceException se o CPF já estiver cadastrado.
     */
    private void existsByCpf(String cpf) {
        if (membroRepository.existsByCpf(cpf))
            throw DuplicatedResourceException.forEntity(EntityType.MEMBRO, "business.membro.cpf.duplicated");
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
    public MembroResponseDTO create(@NotNull MembroCreateDTO membroCreateDTO) {
        existsByEmail(membroCreateDTO.getEmail());
        existsByCpf(membroCreateDTO.getCpf());
        membroCreateDTO.setSenha(passwordEncoder.encode(membroCreateDTO.getSenha()));

        Endereco endereco = enderecoService.findOrCreate(membroCreateDTO.getEnderecoCreateDTO());
        Membro membro = membroMapper.toEntity(membroCreateDTO);
        membro.setEndereco(endereco);

        Membro savedMembro = membroRepository.save(membro);
        return membroMapper.toResponseDTO(savedMembro);
    }

    @Override
    public MembroResponseDTO update(@NotNull MembroUpdateDTO membroUpdateDTO) {
        Membro existingMembro = findMembroById(membroUpdateDTO.getId());

        if (!existingMembro.getEmail().equals(membroUpdateDTO.getEmail()))
            existsByEmail(membroUpdateDTO.getEmail());
        if (!existingMembro.getCpf().equals(membroUpdateDTO.getCpf()))
            existsByCpf(membroUpdateDTO.getCpf());

        Endereco endereco = enderecoService.findOrCreate(membroUpdateDTO.getEnderecoCreateDTO());
        Membro membro = membroMapper.toEntity(membroUpdateDTO);
        membro.setEndereco(endereco);

        Membro updatedMembro = membroRepository.save(membro);
        return membroMapper.toResponseDTO(updatedMembro);
    }

    @Override
    public void delete(int id) {
        Membro existingMembro = findMembroById(id);
        membroRepository.delete(existingMembro);
    }

    @Override
    public List<MembroResponseDTO> findAll() {
        List<Membro> membros = membroRepository.findAll();
        return membroMapper.toResponseDTO(membros);
    }

    @Override
    public MembroResponseDTO findById(int id) {
        Membro existingMembro = findMembroById(id);
        return membroMapper.toResponseDTO(existingMembro);
    }

    @Override
    public MembroResponseDTO findByCpf(String cpf) {
        Membro membro = membroRepository.findByCpf(cpf)
                .orElseThrow(() -> NotFoundResourceException.forEntity(
                        EntityType.MEMBRO, "business.membro.cpf.not_found"));
        return membroMapper.toResponseDTO(membro);
    }

	@Override
	public MembroResponseDTO findByEmail(String email) {
        Membro membro = membroRepository.findByEmail(email)
                .orElseThrow(() -> NotFoundResourceException.forEntity(
                        EntityType.MEMBRO, "business.membro.email.not_found"));
        return membroMapper.toResponseDTO(membro);
	}

    @Override
    public List<MembroResponseDTO> findByNomeContaining(String nome) {
        List<Membro> membros = membroRepository.findByNomeContaining(nome);
        return membroMapper.toResponseDTO(membros);
    }

	@Override
	public List<MembroResponseDTO> findByAtivo(boolean ativo) {
        List<Membro> membros = membroRepository.findByAtivo(ativo);
        return membroMapper.toResponseDTO(membros);
	}

    @Override
    public MembroResponseDTO updateFichaSaudeById(int id, @NotNull MultipartFile file) throws IOException {
        Membro existingMembro = findMembroById(id);
        String filePath = uploadService.uploadFile(file, "fichaSaude");
        existingMembro.setFichaSaude(filePath);

        Membro updatedMembro = membroRepository.save(existingMembro);
        return membroMapper.toResponseDTO(updatedMembro);
    }

    @Override
    public FileResponseDTO findFichaSaudeById(int id) throws IOException {
        Membro existingMembro = findMembroById(id);
        String filePath = existingMembro.getFichaSaude();
        return uploadService.downloadFile(filePath);
    }
}
