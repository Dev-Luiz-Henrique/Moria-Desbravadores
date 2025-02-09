package br.com.moria.shared.exceptions;

import br.com.moria.shared.utils.MessageUtil;
import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * Exceção personalizada para erros de validação.
 *
 * <p>Indica que os dados fornecidos não atendem aos requisitos de validação.
 * Essa exceção retorna um status HTTP 400 (Bad Request) e fornece detalhes
 * adicionais dos erros em forma de mapa.</p>
 *
 * @see ApplicationException
 */
public class ValidationException extends ApplicationException {

    private final Map<String, String> errors;

    /**
     * Construtor privado para criar a exceção com detalhes de erros de validação.
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
     * Cria uma nova instância da exceção com uma mensagem obtida do {@link MessageUtil},
     * sem a necessidade de especificar erros detalhados.
     *
     * @param messageKey a chave da mensagem no {@link MessageUtil}.
     * @return uma nova instância de {@link ValidationException}.
     */
    public static ValidationException of(String messageKey) {
        String message = MessageUtil.getMessage(messageKey);
        return new ValidationException(message, null);
    }

    /**
     * Cria uma nova instância da exceção com uma mensagem padrão e os erros de validação.
     *
     * @param errors o mapa contendo os erros de validação.
     * @return uma nova instância de {@link ValidationException}.
     */
    public static ValidationException of(Map<String, String> errors) {
        String message = MessageUtil.getMessage("validation.error.default");
        return new ValidationException(message, errors);
    }

    /**
     * Cria uma nova instância da exceção com uma mensagem obtida do {@link MessageUtil} e os erros de validação.
     *
     * @param messageKey a chave da mensagem no {@link MessageUtil}.
     * @param errors o mapa contendo os erros de validação.
     * @return uma nova instância de {@link ValidationException}.
     */
    public static ValidationException of(String messageKey, Map<String, String> errors) {
        String message = MessageUtil.getMessage(messageKey);
        return new ValidationException(message, errors);
    }
}
