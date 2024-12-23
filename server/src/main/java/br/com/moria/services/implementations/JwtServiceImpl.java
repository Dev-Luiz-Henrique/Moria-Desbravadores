package br.com.moria.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import br.com.moria.services.interfaces.IJwtService;
import br.com.moria.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Implementação do serviço de validação e autenticação JWT.
 *
 * <p>Responsável por validar tokens JWT e autenticar usuários no contexto de segurança.</p>
 */
@Service
public class JwtServiceImpl implements IJwtService {

    private final UserDetailsService membroDetailsService;
    private final JwtUtil jwtUtil;

    /**
     * Construtor para injeção de dependências.
     *
     * @param membroDetailsService o serviço para carregamento de detalhes do membro.
     * @param jwtUtil               utilitário para manipulação de tokens JWT.
     */
    @Autowired
    public JwtServiceImpl(UserDetailsService membroDetailsService, JwtUtil jwtUtil) {
        this.membroDetailsService = membroDetailsService;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Valida o token JWT e autentica o usuário no contexto de segurança.
     *
     * @param jwt    o token JWT a ser validado.
     * @param request a requisição HTTP associada à autenticação.
     * @throws RuntimeException se o token for inválido ou expirado.
     */
    @Override
    public void validateAndAuthenticate(String jwt, HttpServletRequest request) {
        String username = jwtUtil.extractUsername(jwt);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = membroDetailsService.loadUserByUsername(username);

            if (!jwtUtil.validateToken(jwt, userDetails))
                throw new RuntimeException("Token inválido ou expirado."); // TODO Exceção personalizada

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }
}
