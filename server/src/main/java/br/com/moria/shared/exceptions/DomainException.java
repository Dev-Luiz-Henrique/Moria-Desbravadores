package br.com.moria.shared.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Classe base para exceções relacionadas a domínios específicos na aplicação.
 *
 * <p>Estende {@link ApplicationException} e adiciona o atributo {@code domainName},
 * que identifica o nome do domínio relacionado ao erro.</p>
 *
 * <p>Essa classe é abstrata e deve ser estendida para criar exceções específicas
 * para diferentes domínios da aplicação.</p>
 */
public abstract class DomainException extends ApplicationException {

    private final String domainName;

    /**
     * Construtor para inicializar uma exceção de domínio com detalhes específicos.
     *
     * @param status     o status HTTP associado ao erro.
     * @param message    a mensagem descritiva do erro.
     * @param errorCode  o código de erro associado ao erro.
     * @param domainName o nome do domínio relacionado ao erro.
     */
    protected DomainException(HttpStatus status, String message, String errorCode, String domainName) {
        super(status, message, errorCode);
        this.domainName = domainName;
    }

    public String getDomainName() {
        return domainName;
    }
}
