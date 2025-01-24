package br.com.moria.domains.auth.exceptions;

import br.com.moria.shared.exceptions.ApplicationException;
import br.com.moria.shared.utils.MessageUtil;
import org.springframework.http.HttpStatus;

/**
 * Exceção personalizada para indicar acesso não autorizado.
 *
 * <p>Essa exceção é lançada quando um usuário não autenticado tenta acessar
 * um recurso protegido. Retorna um status HTTP 401 (Unauthorized).</p>
 *
 * @see ApplicationException
 */
public class UnauthorizedException extends ApplicationException {

    /**
     * Construtor privado para criar a exceção com uma mensagem específica.
     *
     * @param message a mensagem descritiva do erro.
     */
    private UnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message, "UNAUTHORIZED");
    }

    /**
     * Cria uma nova instância da exceção com uma mensagem padrão.
     *
     * <p>A mensagem padrão é obtida do {@link MessageUtil} com a chave
     * "access.unauthorized.default".</p>
     *
     * @return uma nova instância de {@link UnauthorizedException}.
     */
    public static UnauthorizedException ofDefault() {
        String message = MessageUtil.getMessage("auth.unauthorized.default");
        return new UnauthorizedException(message);
    }

    /**
     * Cria uma nova instância da exceção com uma mensagem obtida do {@link MessageUtil}.
     *
     * @param messageKey a chave da mensagem no {@link MessageUtil}.
     * @return uma nova instância de {@link UnauthorizedException}.
     */
    public static UnauthorizedException of(String messageKey) {
        String message = MessageUtil.getMessage(messageKey);
        return new UnauthorizedException(message);
    }
}
