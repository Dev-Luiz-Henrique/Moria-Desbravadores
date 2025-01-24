package br.com.moria.domains.auth.exceptions;

import br.com.moria.shared.exceptions.ApplicationException;
import br.com.moria.shared.utils.MessageUtil;
import org.springframework.http.HttpStatus;

/**
 * Exceção personalizada para indicar acesso proibido.
 *
 * <p>Essa exceção é lançada quando um usuário tenta acessar um recurso ou realizar
 * uma operação para a qual não possui permissão. Retorna um status HTTP 403 (Forbidden).</p>
 *
 * @see ApplicationException
 */
public class ForbiddenException extends ApplicationException {

    /**
     * Construtor privado para criar a exceção com uma mensagem específica.
     *
     * @param message a mensagem descritiva do erro.
     */
    private ForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, message, "FORBIDDEN");
    }

    /**
     * Cria uma nova instância da exceção com uma mensagem padrão.
     *
     * @return uma nova instância de {@link ForbiddenException}.
     */
    public static ForbiddenException ofDefault() {
        String message = MessageUtil.getMessage("auth.access.forbidden");
        return new ForbiddenException(message);
    }

    /**
     * Cria uma nova instância da exceção com uma mensagem obtida do {@link MessageUtil}.
     *
     * @param messageKey a chave da mensagem no {@link MessageUtil}.
     * @return uma nova instância de {@link ForbiddenException}.
     */
    public static ForbiddenException of(String messageKey) {
        String message = MessageUtil.getMessage(messageKey);
        return new ForbiddenException(message);
    }
}
