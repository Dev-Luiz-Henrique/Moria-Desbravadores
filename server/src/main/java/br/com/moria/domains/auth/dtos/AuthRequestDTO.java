package br.com.moria.domains.auth.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO para requisição de autenticação
 *
 * <p>Encapsula os dados necessários para uma requisição de autenticação, garantindo validações
 * essenciais para integridade e consistência das informações.</p>
 */
public class AuthRequestDTO {

    @Email(message = "{javax.validation.constraints.Email}")
    @NotBlank(message = "{javax.validation.constraints.NotBlank}")
    private String email;

    @NotBlank(message = "{javax.validation.constraints.NotBlank}")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
