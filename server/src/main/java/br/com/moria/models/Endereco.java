package br.com.moria.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @NotBlank
    @Pattern(regexp = "\\d{8}", message = "CEP deve ter exatamente 8 dígitos.")
    @Column(name = "CEP", nullable = false, unique = true, columnDefinition = "CHAR(8)")
    private String cep;

    @NotBlank
    @Size(min = 3, max = 255, message = "Bairro deve ter entre 3 e 255 caracteres.")
    @Column(name = "Bairro", nullable = false, length = 255)
    private String bairro;

    @NotBlank
    @Size(min = 3, max = 255, message = "Cidade deve ter entre 3 e 255 caracteres.")
    @Column(name = "Cidade", nullable = false, length = 255)
    private String cidade;

    @NotBlank
    @Pattern(regexp = "[A-Z]{2}", message = "Estado deve ter exatamente 2 letras maiúsculas.")
    @Column(name = "Estado", nullable = false, length = 2)
    private String estado;
}