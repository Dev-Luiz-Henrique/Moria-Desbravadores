package br.com.moria.services.implementations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.moria.dtos.FileResponseDTO;
import br.com.moria.services.interfaces.IFileService;

@Service
public class FileServiceImpl implements IFileService {

    private String uploadBaseDir = "/uploads";

    private String uploadFile(MultipartFile file, String uploadDir) throws IOException {
        Path uploadPath = Paths.get(System.getProperty("user.dir"), uploadBaseDir, uploadDir);

        if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
			throw new IllegalArgumentException("O nome do arquivo não pode ser nulo.");
		}
        String fileName = StringUtils.cleanPath(originalFilename);

        // Adiciona um timestamp ao nome do arquivo para evitar conflitos
        String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
        Path filePath = uploadPath.resolve(uniqueFileName);

        String contentType = file.getContentType();
        if (!"image/png".equals(contentType) && !"image/jpeg".equals(contentType))
			throw new IllegalArgumentException("Tipo de arquivo não permitido. Formatos aceitos: jpeg e png.");

        if (file.getSize() > 5 * 1024 * 1024) // 5MB
			throw new IllegalArgumentException("O tamanho do arquivo deve ser inferior a 5MB.");

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        Path relativePath = Paths.get(uploadDir, uniqueFileName);
        return relativePath.toString();
    }

    private byte[] downloadFile(String filePath) throws IOException {
        Path fullPath = Paths.get(System.getProperty("user.dir"), uploadBaseDir, filePath);
        System.out.println(fullPath);
    
        if (!Files.exists(fullPath))
            throw new IOException("Arquivo não encontrado");
        return Files.readAllBytes(fullPath);
    }

    private String getContentType(String filePath) throws IOException {
        Path fullPath = Paths.get(System.getProperty("user.dir"), uploadBaseDir, filePath);
        return Files.probeContentType(fullPath);
    }

    @Override
    public String uploadFichaSaude(MultipartFile file) throws IOException {
        return uploadFile(file, "fichaSaude");
    }

	@Override
	public String uploadComprovantePagamento(MultipartFile file) throws IOException {
		return uploadFile(file, "comprovantePagamento");
	}

	@Override
	public String uploadAutorizacaoResponsavel(MultipartFile file) throws IOException {
		return uploadFile(file, "autorizacaoResponsavel");
	}

    @Override
    public FileResponseDTO downloadFichaSaude(String filePath) throws IOException {
        return new FileResponseDTO(downloadFile(filePath), getContentType(filePath));
    }
}