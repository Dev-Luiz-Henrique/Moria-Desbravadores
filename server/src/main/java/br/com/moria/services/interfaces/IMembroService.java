package br.com.moria.services.interfaces;

import java.io.IOException;
import java.util.List;

import br.com.moria.dtos.Membro.MembroCreateDTO;
import br.com.moria.dtos.Membro.MembroResponseDTO;
import br.com.moria.dtos.Membro.MembroUpdateDTO;
import br.com.moria.models.Membro;

import org.springframework.web.multipart.MultipartFile;

import br.com.moria.dtos.FileResponseDTO;

public interface IMembroService {
    List<Membro> findAllMembrosByAtivo(boolean ativo);
    Membro findMembroById(int id);
    boolean existsById(int id);
    MembroResponseDTO create(MembroCreateDTO membroCreateDTO);
    MembroResponseDTO update(MembroUpdateDTO membroUpdateDTO);
    void delete(int id);
    List<MembroResponseDTO> findAll();
    MembroResponseDTO findById(int id);
    MembroResponseDTO findByCpf(String cpf);
    MembroResponseDTO findByEmail(String email);
    List<MembroResponseDTO> findByNomeContaining(String nome);
    List<MembroResponseDTO> findByAtivo(boolean ativo);
    MembroResponseDTO updateFichaSaudeById(int id, MultipartFile file) throws IOException;
    FileResponseDTO findFichaSaudeById(int id) throws IOException;
}
