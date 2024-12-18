package br.com.moria.dtos.Inscricao;

import br.com.moria.enums.StatusParticipacao;
import br.com.moria.models.Evento;
import br.com.moria.models.Membro;

public class InscricaoResponseDTO {

    private int id;
    private Membro membro;
    private Evento evento;
    private StatusParticipacao statusParticipacao;
    private boolean inscrito;

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
    
    public StatusParticipacao getStatusParticipacao() {
        return statusParticipacao;
    }
    
    public void setStatusParticipacao(StatusParticipacao statusParticipacao) {
        this.statusParticipacao = statusParticipacao;
    }
    
    public boolean isInscrito() {
        return inscrito;
    }
    
    public void setInscrito(boolean inscrito) {
        this.inscrito = inscrito;
    }
}
