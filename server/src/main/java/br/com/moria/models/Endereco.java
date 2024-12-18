package br.com.moria.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "CEP não pode ser vazio.")
    @Pattern(regexp = "\\d{8}", message = "CEP deve ter exatamente 8 dígitos.")
    @Column(name = "cep", nullable = false, unique = true, columnDefinition = "CHAR(8)")
    private String cep;

    @NotBlank(message = "Logradouro não pode ser vazio.")
    @Size(min = 3, max = 255, message = "Bairro deve ter entre 3 e 255 caracteres.")
    @Column(name = "bairro", nullable = false, length = 255)
    private String bairro;

    @Size(max = 255, message = "Logradouro deve ter no máximo 255 caracteres.")
    @Column(name = "logradouro", length = 255)
    private String logradouro;

    @NotBlank(message = "Cidade não pode ser vazia.")
    @Size(min = 3, max = 255, message = "Cidade deve ter entre 3 e 255 caracteres.")
    @Column(name = "cidade", nullable = false, length = 255)
    private String cidade;

    @NotBlank(message = "Estado não pode ser vazio.")
    @Pattern(regexp = "[A-Z]{2}", message = "Estado deve ter exatamente 2 letras maiúsculas.")
    @Column(name = "estado", nullable = false, length = 2)
    private String estado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}