package br.com.moria.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moria.models.Membro;
import br.com.moria.repositories.MembroRepository;
import br.com.moria.services.interfaces.IMembroService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class MembroServiceImpl implements IMembroService {

    private final MembroRepository membroRepository;

    @Autowired
    public MembroServiceImpl(MembroRepository membroRepository) {
        this.membroRepository = membroRepository;
    }

    @Override
    public Membro create(Membro membro) {
        return membroRepository.save(membro);
    }

    @Override
    public Membro update(Long id, Membro membro) {
        return null;
        // Implementar posteriormente o método, devido a duvidas e a 
        // falta de debates referentes a forma de como sera feito a atualização

        /*Membro existingMembro = membroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Membro não encontrado"));
        
        existingMembro.setNome(membro.getNome());
        existingMembro.setSexo(membro.getSexo());
        // ...

        return membroRepository.save(existingMembro);*/
    }

    @Override
    public void delete(Long id) {
        Membro existingMembro = membroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Membro não encontrado"));
        
        membroRepository.delete(existingMembro);
    }

    @Override
    public Membro findById(Long id) {
        return membroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Membro não encontrado"));
    }

    @Override
    public List<Membro> findAll() {
        return membroRepository.findAll();
    }
}