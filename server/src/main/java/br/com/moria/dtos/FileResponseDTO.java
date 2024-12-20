package br.com.moria.dtos;

public class FileResponseDTO {
    private final byte[] fileBytes;
    private final String contentType;
    private final String fileName;
    private final long fileSize;

    public FileResponseDTO(byte[] fileBytes, String contentType, String fileName, long fileSize) {
        this.fileBytes = fileBytes;
        this.contentType = contentType;
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    public byte[] getFileBytes() {
        return fileBytes;
    }

    public String getContentType() {
        return contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public long getFileSize() {
        return fileSize;
    }
}
