package br.com.moria.domains.inscricao;

import br.com.moria.domains.evento.Evento;
import br.com.moria.domains.inscricao.enums.InscricaoStatusParticipacao;
import br.com.moria.domains.membro.Membro;
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

/**
 * Entidade que representa uma inscrição de um membro em um evento no sistema.
 *
 * <p>Encapsula todas as informações relacionadas a uma inscrição.</p>
 */
@Entity
@Table(
        name = "inscricoes",
        uniqueConstraints = { @UniqueConstraint(columnNames = {"id_membro", "id_evento"}) }
)
public class Inscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_membro", referencedColumnName = "id")
    private Membro membro;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_evento", referencedColumnName = "id")
    private Evento evento;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_participacao", nullable = false, length = 20)
    private InscricaoStatusParticipacao statusParticipacao;

    @Column(name = "inscrito", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean inscrito;

    // TODO Implementar autorizacao
    /*@NotBlank
    @Size(max = 255, message = "Path da autorizacao deve ter no máximo 255 caracteres.")
    @Column(name = "autorizacao", nullable = false, length = 255)
    private String autorizacao;*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Membro getMembro() {
        return membro;
    }

    public void setMembro(Membro membro) {
        this.membro = membro;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public InscricaoStatusParticipacao getStatusParticipacao() {
        return statusParticipacao;
    }

    public void setStatusParticipacao(InscricaoStatusParticipacao statusParticipacao) {
        this.statusParticipacao = statusParticipacao;
    }

    public Boolean getInscrito() {
        return inscrito;
    }

    public void setInscrito(Boolean inscrito) {
        this.inscrito = inscrito;
    }
}