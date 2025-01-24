package br.com.moria.shared.exceptions;

import br.com.moria.shared.utils.MessageUtil;
import org.springframework.http.HttpStatus;

/**
 * Exceção personalizada para erros internos do servidor.
 *
 * <p>Essa exceção é lançada quando ocorre um erro inesperado na aplicação.
 * Ela retorna um status HTTP 500 (Internal Server Error).</p>
 *
 * @see ApplicationException
 */
public class InternalServerErrorException extends ApplicationException {

    /**
     * Construtor privado para criar a exceção com uma mensagem específica.
     *
     * @param message a mensagem descritiva do erro.
     */
    private InternalServerErrorException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message, "INTERNAL_SERVER_ERROR");
    }

    /**
     * Cria uma nova instância da exceção com uma mensagem padrão.
     *
     * @return uma nova instância de {@link InternalServerErrorException}.
     */
    public static InternalServerErrorException ofDefault() {
        String message = MessageUtil.getMessage("internal.server.error.default");
        return new InternalServerErrorException(message);
    }

    /**
     * Cria uma nova instância da exceção com uma mensagem obtida do {@link MessageUtil}.
     *
     * @param messageKey a chave da mensagem no {@link MessageUtil}.
     * @return uma nova instância de {@link InternalServerErrorException}.
     */
    public static InternalServerErrorException of(String messageKey) {
        String message = MessageUtil.getMessage(messageKey);
        return new InternalServerErrorException(message);
    }
}
