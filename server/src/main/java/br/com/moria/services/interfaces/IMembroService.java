package br.com.moria.services.interfaces;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.moria.dtos.FileResponseDTO;
import br.com.moria.models.Membro;

public interface IMembroService {

    public Membro create(Membro membro);
    public Membro update(Membro membro);
    public void delete(int id);
    public Membro findById(int id);
    public List<Membro> findAll();
    public Membro findByEmail(String email);
    public Membro findByCpf(String cpf);
    public List<Membro> findByAtivo(Boolean ativo);
    public Membro updateFichaSaudeById(int id, MultipartFile file) throws IOException;
    public FileResponseDTO getFichaSaudeById(int id) throws IOException;
}