package br.com.moria.services.implementations;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.moria.dtos.FileResponseDTO;
import br.com.moria.models.Endereco;
import br.com.moria.models.Membro;
import br.com.moria.repositories.EnderecoRepository;
import br.com.moria.repositories.MembroRepository;
import br.com.moria.services.interfaces.IMembroService;
import br.com.moria.services.interfaces.IFileService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class MembroServiceImpl implements IMembroService {

	@Autowired
    private MembroRepository membroRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private IFileService uploadService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Membro create(Membro membro) {

        Endereco endereco = membro.getEndereco();
        membro.setEndereco(enderecoRepository.findByCep(endereco.getCep())
            .orElseGet(() -> enderecoRepository.save(endereco)));

        if (membroRepository.findByEmail(membro.getEmail()) != null) {
			throw new IllegalArgumentException("Email já cadastrado.");
		}

        if (membroRepository.findByCpf(membro.getCpf()) != null) {
			throw new IllegalArgumentException("CPF já cadastrado.");
		}

        membro.setSenha(passwordEncoder.encode(membro.getSenha()));
        return membroRepository.save(membro);
    }

    @Override
    public Membro update(Membro membro) {
    	Endereco endereco = membro.getEndereco();
        membro.setEndereco(enderecoRepository.findByCep(endereco.getCep())
            .orElseGet(() -> enderecoRepository.save(endereco))); 
        return membroRepository.save(membro);
    }

    @Override
    public void delete(int id) {
        Membro existingMembro = membroRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Membro não encontrado"));

        membroRepository.delete(existingMembro);
    }

    @Override
    public List<Membro> findAll() {
        return membroRepository.findAll();
    }

    @Override
    public Membro findById(int id) {
        return membroRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Membro não encontrado"));
    }

	@Override
	public Membro findByEmail(String email) {
		return membroRepository.findByEmail(email);
	}

	@Override
	public Membro findByCpf(String cpf) {
		return membroRepository.findByCpf(cpf);
	}

	@Override
	public List<Membro> findByAtivo(Boolean ativo) {
		return membroRepository.findByAtivo(ativo);
	}

    @Override
    public Membro updateFichaSaudeById(int id, MultipartFile file) throws IOException {
        Membro existingMembro = membroRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Membro não encontrado"));

        String filePath = uploadService.uploadFichaSaude(file);
        existingMembro.setFichaSaude(filePath);

        return membroRepository.save(existingMembro);
    }

    @Override
    public FileResponseDTO getFichaSaudeById(int id) throws IOException {
        Membro membro = membroRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Membro não encontrado"));

        String filePath = membro.getFichaSaude();
        return uploadService.downloadFichaSaude(filePath);
    }
}