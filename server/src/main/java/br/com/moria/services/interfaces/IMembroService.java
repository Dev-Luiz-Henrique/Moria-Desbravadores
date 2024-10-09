package br.com.moria.services.interfaces;

import java.util.List;

import br.com.moria.models.Membro;

public interface IMembroService {

    Membro create(Membro membro);

    Membro update(Long id, Membro membro);

    void delete(Long id);

    Membro findById(Long id);

    List<Membro> findAll();
}