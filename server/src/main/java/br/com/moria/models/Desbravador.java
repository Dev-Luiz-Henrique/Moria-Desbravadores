package br.com.moria.models;

import br.com.moria.enums.CargoDesbravador;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "desbravadores")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
public class Desbravador extends Membro {

    @Min(value = 0, message = "Medalhas não pode ser negativo.")
    @Column(name = "medalhas", nullable = false, columnDefinition = "SMALLINT DEFAULT 0")
    private int medalhas;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "cargo", nullable = false, length = 20)
    private CargoDesbravador cargo;

    @NotBlank
    @Size(max = 20, message = "Unidade deve ter no máximo 20 caracteres.")
    @Column(name = "unidade", nullable = false, length = 20)
    private String unidade;
}