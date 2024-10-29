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
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    @NotBlank(message = "Cidade não pode ser vazia.")
    @Size(min = 3, max = 255, message = "Cidade deve ter entre 3 e 255 caracteres.")
    @Column(name = "cidade", nullable = false, length = 255)
    private String cidade;

    @NotBlank(message = "Estado não pode ser vazio.")
    @Pattern(regexp = "[A-Z]{2}", message = "Estado deve ter exatamente 2 letras maiúsculas.")
    @Column(name = "estado", nullable = false, length = 2)
    private String estado;
}