package br.com.moria.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moria.models.Endereco;
import br.com.moria.models.Membro;
import br.com.moria.repositories.EnderecoRepository;
import br.com.moria.repositories.MembroRepository;
import br.com.moria.services.interfaces.IMembroService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class MembroServiceImpl implements IMembroService {

	@Autowired
    private MembroRepository membroRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Override
    public Membro create(Membro membro) {

        Endereco endereco = membro.getEndereco();
        membro.setEndereco(enderecoRepository.findByCep(endereco.getCep())
            .orElseGet(() -> enderecoRepository.save(endereco)));

        if (membroRepository.findByEmail(membro.getEmail()).isPresent()) {
			throw new IllegalArgumentException("Email já cadastrado.");
		}

        if (membroRepository.findByCpf(membro.getCpf()).isPresent()) {
			throw new IllegalArgumentException("CPF já cadastrado.");
		}

        return membroRepository.save(membro);
    }

    @Override
    public Membro update(Membro membro) {
        // Detalhar implementacao posteriormente
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
}