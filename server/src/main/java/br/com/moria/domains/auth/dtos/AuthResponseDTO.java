package br.com.moria.domains.auth.dtos;

import java.util.Date;

/**
 * DTO para resposta de autenticação
 *
 * <p>Fornece informações completas sobre uma autenticação bem-sucedida, incluindo o token de acesso,
 * informações do usuário e a data de expiração do token.</p>
 */
public class AuthResponseDTO {

    private String token;
    private String email;
    private Integer userId;
    private String role;
    private Date expiresAt;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }
}
