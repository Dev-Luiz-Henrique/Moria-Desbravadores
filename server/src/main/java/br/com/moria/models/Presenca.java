package br.com.moria.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "presenca", uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"id_membro", "id_evento"})
	})
@Getter
@Setter
public class Presenca {

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

    @Column(name = "inscrito", nullable = false)
    private boolean inscrito = false;

    @Size(max = 20, message = "Status de participação deve ter no máximo 20 caracteres.")
    @Column(name = "status_participacao", length = 20)
    private String statusParticipacao;

    @Lob
    @Column(name = "autorizacao")
    private byte[] autorizacao;
}