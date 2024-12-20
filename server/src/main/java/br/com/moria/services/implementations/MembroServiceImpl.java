package br.com.moria.services.implementations;

import java.io.IOException;
import java.util.List;

import br.com.moria.dtos.Membro.MembroCreateDTO;
import br.com.moria.dtos.Membro.MembroResponseDTO;
import br.com.moria.dtos.Membro.MembroUpdateDTO;
import br.com.moria.mappers.MembroMapper;
import br.com.moria.services.interfaces.IEnderecoService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.moria.dtos.FileResponseDTO;
import br.com.moria.models.Endereco;
import br.com.moria.models.Membro;
import br.com.moria.repositories.MembroRepository;
import br.com.moria.services.interfaces.IFileService;
import br.com.moria.services.interfaces.IMembroService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class MembroServiceImpl implements IMembroService {

    private final MembroMapper membroMapper;
    private final MembroRepository membroRepository;
    private final IEnderecoService enderecoService;
    private final IFileService uploadService;
    private final PasswordEncoder passwordEncoder;

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

    private void existsByEmail(String email) {
        if (membroRepository.existsByEmail(email))
            throw new IllegalArgumentException("Membro com email já cadastrado");
    }

    private void existsByCpf(String cpf) {
        if (membroRepository.existsByCpf(cpf))
            throw new IllegalArgumentException("Membro com CPF já cadastrado");
    }

    private Membro getMembroById(int id) {
        return membroRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Membro não encontrado"));
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
        Membro existingMembro = getMembroById(membroUpdateDTO.getId());

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
        Membro existingMembro = getMembroById(id);
        membroRepository.delete(existingMembro);
    }

    @Override
    public List<MembroResponseDTO> findAll() {
        List<Membro> membros = membroRepository.findAll();
        return membroMapper.toResponseDTO(membros);
    }

    @Override
    public MembroResponseDTO findById(int id) {
        Membro existingMembro = getMembroById(id);
        return membroMapper.toResponseDTO(existingMembro);
    }

    @Override
    public MembroResponseDTO findByCpf(String cpf) {
        Membro membro = membroRepository.findByCpf(cpf)
                .orElseThrow(() -> new EntityNotFoundException("Membro não encontrado"));
        return membroMapper.toResponseDTO(membro);
    }

	@Override
	public MembroResponseDTO findByEmail(String email) {
        Membro membro = membroRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Membro não encontrado"));
        return membroMapper.toResponseDTO(membro);
	}

    @Override
    public List<MembroResponseDTO> findByNomeContaining(String nome) {
        List<Membro> membros = membroRepository.findByNomeContaining(nome);
        return membroMapper.toResponseDTO(membros);
    }

	@Override
	public List<MembroResponseDTO> findByAtivo(Boolean ativo) {
        List<Membro> membros = membroRepository.findByAtivo(ativo);
        return membroMapper.toResponseDTO(membros);
	}

    @Override
    public MembroResponseDTO updateFichaSaudeById(int id, @NotNull MultipartFile file) throws IOException {
        Membro existingMembro = getMembroById(id);
        String filePath = uploadService.uploadFile(file, "fichaSaude");
        existingMembro.setFichaSaude(filePath);

        Membro updatedMembro = membroRepository.save(existingMembro);
        return membroMapper.toResponseDTO(updatedMembro);
    }

    @Override
    public FileResponseDTO getFichaSaudeById(int id) throws IOException {
        Membro existingMembro = getMembroById(id);
        String filePath = existingMembro.getFichaSaude();

        return uploadService.downloadFile(filePath);
    }
}