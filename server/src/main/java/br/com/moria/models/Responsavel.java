package br.com.moria.models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "responsaveis")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
public class Responsavel extends Membro {
    // Nenhum campo adicional necessário no momento.
}