package br.com.moria.models;

import br.com.moria.enums.CargoVoluntario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "voluntarios")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
public class Voluntario extends Membro {

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "cargo", nullable = false, length = 40)
    private CargoVoluntario cargo;
}