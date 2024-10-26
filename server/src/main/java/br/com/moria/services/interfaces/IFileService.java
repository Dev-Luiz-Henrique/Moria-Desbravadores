package br.com.moria.services.interfaces;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import br.com.moria.dtos.FileResponseDTO;

public interface IFileService {
    public String uploadFichaSaude(MultipartFile file) throws IOException;
    public String uploadComprovantePagamento(MultipartFile file) throws IOException;
    public String uploadAutorizacaoResponsavel(MultipartFile file) throws IOException;
    public FileResponseDTO downloadFichaSaude(String filePath) throws IOException;
}