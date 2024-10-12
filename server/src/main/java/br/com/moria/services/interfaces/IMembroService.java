package br.com.moria.services.interfaces;

import java.util.List;

import br.com.moria.models.Membro;

public interface IMembroService {

    public Membro create(Membro membro);
    public Membro update(Membro membro);
    public void delete(int id);
    public Membro findById(int id);
    public List<Membro> findAll();
}