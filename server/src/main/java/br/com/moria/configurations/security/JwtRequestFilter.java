package br.com.moria.configurations.security;

import java.io.IOException;

import br.com.moria.domains.auth.JwtUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filtro para interceptação de requisições HTTP com autenticação JWT.
 *
 * <p>Esta classe é um componente Spring e implementa o filtro 'OncePerRequestFilter', garantindo que
 * cada requisição HTTP seja processada apenas uma vez durante o ciclo de requisição.</p>
 *
 * <p>Ela verifica a presença de um token JWT no cabeçalho 'Authorization', valida o token,
 * e autentica o usuário no contexto de segurança do Spring.</p>
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserDetailsService membroDetailsService;
    private final JwtUtil jwtUtil;

    /**
     * Construtor para injeção de dependências.
     *
     * @param membroDetailsService Serviço para buscar detalhes do usuário autenticado.
     * @param jwtUtil Utilitário para manipulação de tokens JWT.
     */
    @Autowired
    public JwtRequestFilter(UserDetailsService membroDetailsService, JwtUtil jwtUtil) {
        this.membroDetailsService = membroDetailsService;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Função principal para interceptação e autenticação de requisições HTTP.
     *
     * @param request A requisição HTTP recebida.
     * @param response A resposta HTTP associada.
     * @param chain O filtro subsequente no pipeline de requisições.
     * @throws ServletException se ocorrer um erro de servlet.
     * @throws IOException se ocorrer um erro de I/O.
     */
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain chain)
            throws ServletException, IOException {
        try {
            final String authorizationHeader = request.getHeader("Authorization");

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String jwt = authorizationHeader.substring(7);
                validateAndAuthenticate(jwt, request);
            }

            chain.doFilter(request, response);
        } catch (Exception ex) {
            handleErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
        }
    }

    /**
     * Valida o token JWT e autentica o usuário no contexto de segurança.
     *
     * @param jwt     o token JWT a ser validado.
     * @param request a requisição HTTP associada à autenticação.
     * @throws RuntimeException se o token for inválido ou expirado.
     */
    private void validateAndAuthenticate(String jwt, HttpServletRequest request) {
        String username = jwtUtil.extractUsername(jwt);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = membroDetailsService.loadUserByUsername(username);

            if (!jwtUtil.validateToken(jwt, userDetails))
                throw new RuntimeException("Token inválido ou expirado."); // TODO exceção personlaizada

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }

    /**
     * Manipula a resposta em caso de erro de autenticação.
     *
     * @param response a resposta HTTP.
     * @param status   o status HTTP a ser retornado.
     * @param message  a mensagem de erro.
     * @throws IOException se ocorrer um erro ao escrever na resposta.
     */
    private void handleErrorResponse(@NotNull HttpServletResponse response,
                                     int status,
                                     String message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(String.format("{\"error\": \"%s\"}", message));
    }
}


