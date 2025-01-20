package br.com.moria.shared.exceptions;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

/**
 * Classe base para exceções personalizadas na aplicação.
 *
 * <p>Fornece informações padronizadas como status HTTP, código de erro,
 * mensagem descritiva e o timestamp do erro.</p>
 *
 * <p>Essa classe é abstrata e deve ser estendida para criar exceções específicas
 * da aplicação.</p>
 */
public abstract class ApplicationException extends RuntimeException {

    private final HttpStatus status;
    private final String errorCode;
    private final LocalDateTime timestamp;

    /**
     * Construtor para inicializar uma exceção de aplicação com detalhes específicos.
     *
     * @param status    o status HTTP associado ao erro.
     * @param message   a mensagem descritiva do erro.
     * @param errorCode o código de erro associado ao erro.
     */
    public ApplicationException(HttpStatus status, String message, String errorCode) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
