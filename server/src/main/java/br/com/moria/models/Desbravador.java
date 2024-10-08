package br.com.moria.models;

import br.com.moria.enums.CargoDesbravador;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Desbravadores")
@PrimaryKeyJoinColumn(name = "Id")
@Getter
@Setter
public class Desbravador extends Membro {

    @Min(value = 0, message = "Medalhas não pode ser negativo.")
    @Column(name = "Medalhas", nullable = false, columnDefinition = "SMALLINT DEFAULT 0")
    private int medalhas;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "Cargo", nullable = false, length = 20)
    private CargoDesbravador cargo;

    @NotBlank
    @Size(max = 20, message = "Unidade deve ter no máximo 20 caracteres.")
    @Column(name = "Unidade", nullable = false, length = 20)
    private String unidade;
}