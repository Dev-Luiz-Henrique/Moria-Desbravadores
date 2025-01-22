package br.com.moria.shared.exceptions;

import br.com.moria.shared.utils.MessageUtil;
import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * Exceção personalizada para erros de validação.
 *
 * <p>Representa uma exceção com status HTTP 400 (Bad Request), indicando
 * que os dados fornecidos não atendem aos requisitos de validação.</p>
 *
 * <p>Além da mensagem, esta exceção armazena os erros detalhados em forma
 * de um mapa, onde cada chave representa o campo inválido e o valor
 * descreve o erro correspondente.</p>
 *
 * @see ApplicationException
 */
public class ValidationException extends ApplicationException {

    private final Map<String, String> errors;

    /**
     * Construtor privado para inicializar a exceção com detalhes de erros.
     *
     * @param message a mensagem descritiva do erro.
     * @param errors  o mapa contendo os erros de validação.
     */
    private ValidationException(String message, Map<String, String> errors) {
        super(HttpStatus.BAD_REQUEST, message, "VALIDATION_ERROR");
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    /**
     * Cria uma instância da exceção com uma mensagem padrão e detalhes dos erros.
     *
     * <p>A mensagem é obtida a partir do utilitário {@link MessageUtil} com a chave
     * "validation.error.default".</p>
     *
     * @param errors o mapa contendo os erros de validação.
     * @return uma nova instância de {@link ValidationException}.
     */
    public static ValidationException of(Map<String, String> errors) {
        String message = MessageUtil.getMessage("validation.error.default");
        return new ValidationException(message, errors);
    }

    /**
     * Cria uma instância da exceção com uma mensagem personalizada e detalhes dos erros.
     *
     * @param customMessage a mensagem personalizada descrevendo o erro.
     * @param errors        o mapa contendo os erros de validação.
     * @return uma nova instância de {@link ValidationException}.
     */
    public static ValidationException of(String customMessage, Map<String, String> errors) {
        return new ValidationException(customMessage, errors);
    }
}
