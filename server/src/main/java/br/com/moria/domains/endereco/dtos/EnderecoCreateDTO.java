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

    @NotBlank(message = "O campo CEP é obrigatório")
    @Pattern(regexp = "\\d{8}", message = "O campo CEP deve ter exatamente 8 dígitos")
    private String cep;

    @Size(min = 3, max = 255, message = "O campo logradouro deve ter entre 3 e 255 caracteres")
    private String logradouro;

    @NotBlank(message = "O campo bairro é obrigatório")
    @Size(min = 3, max = 255, message = "O campo bairro deve ter entre 3 e 255 caracteres")
    private String bairro;

    @NotBlank(message = "O campo cidade é obrigatório")
    @Size(min = 3, max = 255, message = "O campo cidade deve ter entre 3 e 255 caracteres")
    private String cidade;

    @NotBlank(message = "O campo estado é obrigatório")
    @Pattern(regexp = "[A-Z]{2}", message = "O campo estado deve ter exatamente 2 letras maiúsculas")
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