package br.com.moria.shared.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * Classe que representa um erro padronizado da API.
 *
 * <p>Contém informações detalhadas sobre o erro ocorrido, incluindo status HTTP,
 * mensagem, código de erro, data e hora, além de detalhes adicionais e causas.</p>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

    private final HttpStatus status;
    private final String message;
    private final String errorCode;
    private final LocalDateTime timestamp;
    private final Map<String, Object> details = new HashMap<>();
    private final List<String> causes = new ArrayList<>();

    /**
     * Construtor para inicializar o erro com status, mensagem e código de erro.
     *
     * @param status    o status HTTP associado ao erro.
     * @param message   a mensagem descritiva do erro.
     * @param errorCode o código único associado ao erro.
     */
    public ApiError(HttpStatus status, String message, String errorCode) {
        this(status, message, errorCode, LocalDateTime.now());
    }

    /**
     * Construtor completo para inicializar o erro com timestamp personalizado.
     *
     * @param status    o status HTTP associado ao erro.
     * @param message   a mensagem descritiva do erro.
     * @param errorCode o código único associado ao erro.
     * @param timestamp a data e hora do erro.
     */
    public ApiError(HttpStatus status, String message, String errorCode, LocalDateTime timestamp) {
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
        this.timestamp = timestamp != null ? timestamp : LocalDateTime.now();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Map<String, Object> getDetails() {
        return details;
    }

    public List<String> getCauses() {
        return causes;
    }

    public void addDetail(String key, Object value) {
        details.put(key, value);
    }

    public void addCause(String cause) {
        this.causes.add(cause);
    }

    /**
     * Cria uma instância de {@link ApiError} com status, mensagem e código de erro.
     *
     * @param status    o status HTTP do erro.
     * @param message   a mensagem descritiva do erro.
     * @param errorCode o código único associado ao erro.
     * @return uma nova instância de {@link ApiError}.
     */
    public static ApiError of(HttpStatus status, String message, String errorCode) {
        return new ApiError(status, message, errorCode);
    }

    /**
     * Cria uma instância de {@link ApiError} com detalhes adicionais.
     *
     * @param status    o status HTTP do erro.
     * @param message   a mensagem descritiva do erro.
     * @param errorCode o código único associado ao erro.
     * @param details   o mapa de detalhes adicionais do erro.
     * @return uma nova instância de {@link ApiError} com detalhes adicionais.
     */
    public static ApiError withDetails(HttpStatus status, String message, String errorCode, Map<String, Object> details) {
        ApiError error = new ApiError(status, message, errorCode);
        error.details.putAll(details);
        return error;
    }
}
