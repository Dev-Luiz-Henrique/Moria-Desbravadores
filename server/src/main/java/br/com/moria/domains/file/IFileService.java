package br.com.moria.domains.file;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 * Interface de serviço para operações de gerenciamento de arquivos.
 *
 * <p>Define métodos para upload, download e exclusão de arquivos.</p>
 *
 * @see FileResponseDTO
 */
public interface IFileService {

    /**
     * Faz o upload de um arquivo para um diretório especificado.
     *
     * @param file o arquivo a ser enviado.
     * @param directory o diretório onde o arquivo será salvo.
     * @return o caminho do arquivo salvo.
     * @throws IOException se ocorrer um erro ao salvar o arquivo.
     */
    String uploadFile(MultipartFile file, String directory) throws IOException;

    /**
     * Faz o download de um arquivo com base no caminho fornecido.
     *
     * @param filePath o caminho do arquivo a ser baixado.
     * @return os dados do arquivo baixado.
     * @throws IOException se ocorrer um erro ao acessar o arquivo.
     */
    FileResponseDTO downloadFile(String filePath) throws IOException;

    /**
     * Exclui um arquivo com base no caminho fornecido.
     *
     * @param filePath o caminho do arquivo a ser excluído.
     * @throws IOException se ocorrer um erro ao excluir o arquivo.
     */
    void deleteFile(String filePath) throws IOException;
}
