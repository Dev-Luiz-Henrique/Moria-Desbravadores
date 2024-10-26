package br.com.moria.services.interfaces;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {

    public String uploadFichaSaude(MultipartFile file) throws IOException;
    public String uploadComprovantePagamento(MultipartFile file) throws IOException;
    public String uploadAutorizacaoResponsavel(MultipartFile file) throws IOException;
}