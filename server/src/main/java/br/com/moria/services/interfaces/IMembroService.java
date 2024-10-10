package br.com.moria.services.interfaces;

import java.util.List;

import br.com.moria.models.Membro;

public interface IMembroService {

    public Membro create(Membro membro);
    public Membro update(Long id, Membro membro);
    public void delete(Long id);
    public Membro findById(Long id);
    public List<Membro> findAll();
}