package br.com.moria.dtos;

/**
 * DTO de resposta para informações sobre um arquivo.
 *
 * <p>Fornece os detalhes sobre um arquivo, incluindo o conteúdo, tipo, nome e tamanho.</p>
 */
public record FileResponseDTO(byte[] fileBytes,
                              String contentType,
                              String fileName,
                              long fileSize) {
}
