package br.com.moria.domains.auth;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Interface de serviço para validação e autenticação de tokens JWT.
 *
 * <p>Define métodos para manipulação de tokens JWT, incluindo validação e
 * autenticação no contexto de uma requisição HTTP.</p>
 */
public interface IJwtService {

    /**
     * Valida e autentica um token JWT.
     *
     * @param jwt o token JWT a ser validado.
     * @param request o objeto {@link HttpServletRequest} associado à requisição atual.
     * @throws IllegalArgumentException se o token for inválido ou expirado.
     */
    void validateAndAuthenticate(String jwt, HttpServletRequest request);
}
