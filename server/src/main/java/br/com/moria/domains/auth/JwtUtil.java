package br.com.moria.domains.auth;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * Utilitário para manipulação de tokens JWT (JSON Web Token).
 * <p>
 * Esta classe fornece métodos para gerar, validar e extrair informações de tokens JWT.
 * </p>
 */
@Component
public class JwtUtil {

    /**
     * Tempo de expiração do token JWT em milissegundos.
     */
    @Value("${jwt.expiration}")
    private long jwtExpirationInMs;

    /**
     * Chave secreta usada para assinar os tokens JWT.
     */
    private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * Gera um token JWT com base nos detalhes do usuário.
     *
     * @param userDetails Detalhes do usuário.
     * @return Token JWT gerado.
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", userDetails.getAuthorities());
        return createToken(claims, userDetails.getUsername());
    }

    /**
     * Valida um token JWT comparando o nome de usuário e verificando se está expirado.
     *
     * @param token Token JWT.
     * @param userDetails Detalhes do usuário.
     * @return {@code true} se o token for válido, {@code false} caso contrário.
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Cria um token JWT com reivindicações e assunto especificados.
     *
     * @param claims Reivindicações adicionais.
     * @param subject Assunto do token (geralmente o nome de usuário).
     * @return Token JWT gerado.
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(SECRET_KEY)
                .compact();
    }

    /**
     * Extrai o nome de usuário do token JWT.
     *
     * @param token Token JWT.
     * @return Nome de usuário extraído do token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrai a data de expiração do token JWT.
     *
     * @param token Token JWT.
     * @return Data de expiração do token.
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extrai uma reivindicação específica do token JWT.
     *
     * @param <T> Tipo da reivindicação.
     * @param token Token JWT.
     * @param claimsResolver Função para resolver a reivindicação.
     * @return Valor da reivindicação.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extrai todas as reivindicações do token JWT.
     *
     * @param token Token JWT.
     * @return Todas as reivindicações presentes no token.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Verifica se o token JWT está expirado.
     *
     * @param token Token JWT.
     * @return {@code true} se o token estiver expirado, {@code false} caso contrário.
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
