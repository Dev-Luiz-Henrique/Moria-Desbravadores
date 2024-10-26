package br.com.moria.dtos;

public class FileResponseDTO {
    private byte[] fileBytes;
    private String contentType;

    public FileResponseDTO(byte[] fileBytes, String contentType) {
        this.fileBytes = fileBytes;
        this.contentType = contentType;
    }

    public byte[] getFileBytes() {
        return fileBytes;
    }

    public String getContentType() {
        return contentType;
    }
}
