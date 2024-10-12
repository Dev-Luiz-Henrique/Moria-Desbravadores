package br.com.moria.services.implementations;

import java.util.List;
import java.util.Optional;

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
        Optional<Endereco> existingEndereco = enderecoRepository.findByCep(endereco.getCep());
        
        if (existingEndereco.isPresent()) 
            membro.setEndereco(existingEndereco.get());
        else
            enderecoRepository.save(endereco);

        return membroRepository.save(membro);
    }

    @Override
    public Membro update(Membro membro) {
        /*Membro existingMembro = membroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Membro não encontrado"));*/
        
        
        return membroRepository.save(membro);
    }

    @Override
    public void delete(int id) {
        Membro existingMembro = membroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Membro não encontrado"));
        
        membroRepository.delete(existingMembro);
    }

    @Override
    public Membro findById(int id) {
        return membroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Membro não encontrado"));
    }

    @Override
    public List<Membro> findAll() {
        return membroRepository.findAll();
    }
}