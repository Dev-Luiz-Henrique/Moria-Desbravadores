package br.com.moria.models;

import jakarta.persistence.*;
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