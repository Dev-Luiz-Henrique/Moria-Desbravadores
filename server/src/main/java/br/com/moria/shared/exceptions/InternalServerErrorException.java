package br.com.moria.shared.exceptions;

import br.com.moria.shared.utils.MessageUtil;
import org.springframework.http.HttpStatus;

/**
 * Exceção personalizada para erros internos do servidor.
 *
 * <p>Representa uma exceção com status HTTP 500 (Internal Server Error),
 * geralmente utilizada quando ocorre um erro inesperado na aplicação.</p>
 *
 * @see ApplicationException
 */
public class InternalServerErrorException extends ApplicationException {

    /**
     * Construtor privado para inicializar a exceção com uma mensagem específica.
     *
     * @param message a mensagem descritiva do erro.
     */
    private InternalServerErrorException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message, "INTERNAL_SERVER_ERROR");
    }

    /**
     * Cria uma instância da exceção utilizando uma mensagem padrão.
     *
     * <p>A mensagem é obtida a partir do utilitário {@link MessageUtil} com a chave
     * "internal.server.error.default".</p>
     *
     * @return uma nova instância de {@link InternalServerErrorException}.
     */
    public static InternalServerErrorException ofDefault() {
        String message = MessageUtil.getMessage("internal.server.error.default");
        return new InternalServerErrorException(message);
    }
}
