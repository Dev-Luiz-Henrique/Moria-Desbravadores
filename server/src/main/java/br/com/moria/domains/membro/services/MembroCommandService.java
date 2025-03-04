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
import br.com.moria.domains.membro.enums.MembroFuncao;
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
 * Implementação do serviço para operações relacionadas à gestão de membros.
 *
 * <p>Fornece funcionalidades para criação, atualização e exclusão de membros,
 * bem como operações específicas, como o gerenciamento de fichas de saúde.</p>
 *
 * @see IMembroCommandService
 */
@Service
public class MembroCommandService implements IMembroCommandService {

    private final MembroMapper membroMapper;
    private final MembroRepository membroRepository;
    private final IMembroQueryService membroQueryService;
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
    public MembroCommandService(MembroMapper membroMapper,
                             MembroRepository membroRepository,
                             IMembroQueryService membroQueryService,
                             IEnderecoService enderecoService,
                             IFileService uploadService,
                             PasswordEncoder passwordEncoder) {
        this.membroMapper = membroMapper;
        this.membroRepository = membroRepository;
        this.membroQueryService = membroQueryService;
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

    public MembroResponseDTO selfRegister(MembroCreateDTO dto) {
        dto.setFuncao(MembroFuncao.filterAllowed(dto.getFuncao()));
        return create(dto);
    }

    @Override
    public MembroResponseDTO update(@NotNull MembroUpdateDTO membroUpdateDTO) {
        Membro existingMembro = membroQueryService.findMembroById(membroUpdateDTO.getId());

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
        Membro existingMembro = membroQueryService.findMembroById(id);
        membroRepository.delete(existingMembro);
    }

    @Override
    public MembroResponseDTO updateFichaSaudeById(int id, @NotNull MultipartFile file) throws IOException {
        Membro existingMembro = membroQueryService.findMembroById(id);
        String filePath = uploadService.uploadFile(file, "fichaSaude");
        existingMembro.setFichaSaude(filePath);

        Membro updatedMembro = membroRepository.save(existingMembro);
        return membroMapper.toResponseDTO(updatedMembro);
    }

    @Override
    public FileResponseDTO findFichaSaudeById(int id) throws IOException {
        Membro existingMembro = membroQueryService.findMembroById(id);
        String filePath = existingMembro.getFichaSaude();
        return uploadService.downloadFile(filePath);
    }
}
