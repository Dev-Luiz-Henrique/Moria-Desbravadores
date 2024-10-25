package br.com.moria.models;

import br.com.moria.enums.StatusParticipacao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "inscricoes", uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"id_membro", "id_evento"})
	})
@Getter
@Setter
public class Inscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_membro", referencedColumnName = "id")
    private Membro membro;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_evento", referencedColumnName = "id")
    private Evento evento;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status_participacao", nullable = false, length = 20)
    private StatusParticipacao statusParticipacao;

    @NotBlank
    @Size(max = 255, message = "Path da autorizacao deve ter no máximo 255 caracteres.")
    @Column(name = "autorizacao", nullable = false, length = 255)
    private String autorizacao;
}