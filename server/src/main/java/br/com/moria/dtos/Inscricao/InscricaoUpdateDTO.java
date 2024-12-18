package br.com.moria.dtos.Inscricao;

import br.com.moria.enums.StatusParticipacao;
import jakarta.validation.constraints.NotNull;

public class InscricaoUpdateDTO {

    @NotNull(message = "O campo id é obrigatório")
    private int id;

    @NotNull(message = "O campo status de participação é obrigatório")
    private StatusParticipacao statusParticipacao;
    
    @NotNull(message = "O campo inscrito é obrigatório")
    private Boolean inscrito;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StatusParticipacao getStatusParticipacao() {
        return statusParticipacao;
    }

    public void setStatusParticipacao(StatusParticipacao statusParticipacao) {
        this.statusParticipacao = statusParticipacao;
    }

    public Boolean getInscrito() {
        return inscrito;
    }
    
    public void setInscrito(Boolean inscrito) {
        this.inscrito = inscrito;
    }
}
