package br.com.moria.services.implementations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.moria.dtos.FileResponseDTO;
import br.com.moria.services.interfaces.IFileService;

@Service
public class FileServiceImpl implements IFileService {

    private String uploadBaseDir = "/uploads";

    private static final List<String> SUPPORTED_CONTENT_TYPES = Arrays.asList(
        "image/png", 
        "image/jpeg",
        "image/bmp",
        "image/webp",
        "image/svg+xml",
        "image/tiff"
    );

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
        if (!SUPPORTED_CONTENT_TYPES.contains(contentType)) {
            throw new IllegalArgumentException("Tipo de arquivo não permitido. Formatos aceitos: " 
                + SUPPORTED_CONTENT_TYPES);
        }

        // TODO: Lançar Exception personalida para tamanho máximo excedido, com erro 413
        if (file.getSize() > 1 * 1024 * 1024) { // 5MB
			throw new IllegalArgumentException("O tamanho do arquivo deve ser inferior a 5MB.");
		}

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        Path relativePath = Paths.get(uploadDir, uniqueFileName);
        return relativePath.toString();
    }

    private byte[] downloadFile(String filePath) throws IOException {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("Caminho de arquivo inválido fornecido");
        }

        Path fullPath = Paths.get(System.getProperty("user.dir"), uploadBaseDir, filePath);

        if (!Files.exists(fullPath)) {
			throw new IOException("Arquivo não encontrado");
		}
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

	@Override
	public String uploadImagemEvento(MultipartFile file) throws IOException {
		return uploadFile(file, "imagemEvento");
	}

	@Override
	public FileResponseDTO downloadImagemEvento(String filePath) throws IOException {
		return new FileResponseDTO(downloadFile(filePath), getContentType(filePath));
	}
}