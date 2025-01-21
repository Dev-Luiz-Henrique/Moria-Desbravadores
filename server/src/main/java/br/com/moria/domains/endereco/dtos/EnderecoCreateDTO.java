package br.com.moria.domains.endereco.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO para criação de um novo endereço.
 *
 * <p>Encapsula os dados necessários para cadastrar um novo endereço, garantindo validações
 * essenciais para integridade e consistência das informações.</p>
 */
public class EnderecoCreateDTO {

    @NotBlank(message = "{javax.validation.constraints.NotBlank}")
    @Pattern(regexp = "^\\d{8}$", message = "{javax.validation.constraints.Pattern.cep}")
    private String cep;

    @Size(min = 3, max = 255, message = "{javax.validation.constraints.Size}")
    private String logradouro;

    @NotBlank(message = "{javax.validation.constraints.NotBlank}")
    @Size(min = 3, max = 255, message = "{javax.validation.constraints.Size}")
    private String bairro;

    @NotBlank(message = "{javax.validation.constraints.NotBlank}")
    @Size(min = 3, max = 255, message = "{javax.validation.constraints.Size}")
    private String cidade;

    @NotBlank(message = "{javax.validation.constraints.NotBlank}")
    @Pattern(regexp = "^[A-Z]{2}$", message = "{javax.validation.constraints.Pattern.estado}")
    private String estado;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}