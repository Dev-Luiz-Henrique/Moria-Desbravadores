package br.com.moria.services.implementations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.moria.services.interfaces.IUploadService;

@Service
public class UploadServiceImpl implements IUploadService {

    private String uploadBaseDir = "/uploads";

    private String uploadFile(MultipartFile file, String uploadDir) throws IOException {
        Path uploadPath = Paths.get(System.getProperty("user.dir"), uploadBaseDir, uploadDir);

        if (!Files.exists(uploadPath)) 
            Files.createDirectories(uploadPath);

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null)
            throw new IllegalArgumentException("O nome do arquivo não pode ser nulo.");
        String fileName = StringUtils.cleanPath(originalFilename);

        // Adiciona um timestamp ao nome do arquivo para evitar conflitos
        String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
        Path filePath = uploadPath.resolve(uniqueFileName);

        String contentType = file.getContentType();
        if (!"image/png".equals(contentType) && !"image/jpeg".equals(contentType))
            throw new IllegalArgumentException("Tipo de arquivo não permitido para foto de perfil.");

        if (file.getSize() > 5 * 1024 * 1024) // 5MB
            throw new IllegalArgumentException("O tamanho do arquivo deve ser inferior a 5MB.");

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        Path relativePath = Paths.get(uploadDir, fileName);
        return relativePath.toString();
    }

    @Override
    public String uploadFichaSaude(MultipartFile file) throws IOException {
        return uploadFile(file, "fichaSaude");
    }
}