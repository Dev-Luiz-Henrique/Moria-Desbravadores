package br.com.moria.services.interfaces;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import br.com.moria.dtos.FileResponseDTO;

public interface IFileService {
    String uploadFile(MultipartFile file, String directory) throws IOException;
    FileResponseDTO downloadFile(String filePath) throws IOException;
    void deleteFile(String filePath) throws IOException;
}