package br.com.moria.services.implementations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.moria.dtos.FileResponseDTO;
import br.com.moria.services.interfaces.IFileService;

/**
 * Implementação do serviço de gerenciamento de arquivos.
 *
 * <p>Fornece funcionalidades para upload, download e exclusão de arquivos.</p>
 */
@Service
public class FileServiceImpl implements IFileService { // TODO improve better security

    @Value("${file.upload.base-dir}")
    private String uploadBaseDir;

    @Value("${file.upload.max-size}")
    private long maxFileSize;

    private static final List<String> SUPPORTED_CONTENT_TYPES = Arrays.asList(
        "image/png", "image/jpeg", "image/bmp", "image/webp", "image/svg+xml", "image/tiff"
    );

    /**
     * Obtém o caminho seguro para o diretório especificado, garantindo que esteja dentro do diretório base configurado.
     *
     * @param dir o diretório solicitado.
     * @return o caminho seguro como um {@link Path}.
     * @throws IOException se houver falha ao criar o diretório.
     */
    private Path getSafePath(String dir) throws IOException {
        Path uploadPath = Paths.get(System.getProperty("user.dir"), uploadBaseDir, dir).normalize();
        if (!uploadPath.startsWith(Paths.get(System.getProperty("user.dir"), uploadBaseDir).normalize()))
            throw new SecurityException("Tentativa de acessar caminho fora do diretório permitido");

        if (!Files.exists(uploadPath))
            Files.createDirectories(uploadPath);
        return uploadPath;
    }

    /**
     * Obtém o tipo de conteúdo de um arquivo com base no caminho especificado.
     *
     * @param filePath o caminho do arquivo.
     * @return o tipo de conteúdo como uma string.
     * @throws IOException se houver falha ao acessar o arquivo.
     */
    private String getContentType(String filePath) throws IOException {
        Path fullPath = Paths.get(System.getProperty("user.dir"), uploadBaseDir, filePath);
        return Files.probeContentType(fullPath);
    }

    /**
     * Valida as propriedades do arquivo enviado, incluindo tamanho e tipo de conteúdo.
     *
     * @param file o arquivo enviado.
     * @throws IllegalArgumentException se o arquivo for inválido.
     */
    private void validateFile(@NotNull MultipartFile file) {
        if (file.isEmpty())
            throw new IllegalArgumentException("O arquivo está vazio");
        if (file.getSize() > maxFileSize)
            throw new IllegalArgumentException("O tamanho do arquivo excede o limite permitido");

        String contentType = file.getContentType();
        if (!SUPPORTED_CONTENT_TYPES.contains(contentType))
            throw new IllegalArgumentException("Tipo de arquivo não permitido. Formatos aceitos: "
                + SUPPORTED_CONTENT_TYPES);
    }

    /**
     * Gera um nome de arquivo sanitizado e único com base no nome original.
     *
     * @param originalFilename o nome original do arquivo.
     * @return o nome sanitizado do arquivo.
     */
    private String sanitizeFileName(String originalFilename) {
        String fileName = StringUtils.cleanPath(originalFilename);
        if (fileName.contains(".."))
            throw new IllegalArgumentException("O nome do arquivo contém caracteres inválidos");
        return System.currentTimeMillis() + "_" + fileName;
    }

    @Override
    public String uploadFile(MultipartFile file, String dir) throws IOException {
        validateFile(file);

        Path uploadPath = getSafePath(dir);
        String sanitizedFileName = sanitizeFileName(file.getOriginalFilename());
        Path filePath = uploadPath.resolve(sanitizedFileName);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return Paths.get(dir, sanitizedFileName).toString();
    }

    /**
     * Lê o conteúdo de um arquivo com base no caminho fornecido.
     *
     * @param filePath o caminho do arquivo.
     * @return o conteúdo do arquivo como um array de bytes.
     * @throws IOException se o arquivo não for encontrado ou não puder ser lido.
     */
    private byte[] downloadFileContent(String filePath) throws IOException {
        Path fullPath = Paths.get(System.getProperty("user.dir"), uploadBaseDir, filePath);
        if (!Files.exists(fullPath))
            throw new IOException("Arquivo não encontrado");
        return Files.readAllBytes(fullPath);
    }

    @Override
    public FileResponseDTO downloadFile(String filePath) throws IOException {
        byte[] fileContent = downloadFileContent(filePath);
        String contentType = getContentType(filePath);

        Path fullPath = Paths.get(System.getProperty("user.dir"), uploadBaseDir, filePath);
        String fileName = fullPath.getFileName().toString();
        long fileSize = Files.size(fullPath);

        return new FileResponseDTO(fileContent, contentType, fileName, fileSize);
    }

    @Override
    public void deleteFile(String filePath) throws IOException {
        Path fullPath = Paths.get(System.getProperty("user.dir"), uploadBaseDir, filePath).normalize();
        if (Files.exists(fullPath))
            Files.delete(fullPath);
        else
            throw new IOException("Arquivo não encontrado");
    }
}
