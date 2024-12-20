package br.com.moria.dtos.Inscricao;

import br.com.moria.enums.StatusParticipacao;
import jakarta.validation.constraints.NotNull;

public class InscricaoCreateDTO {

    @NotNull(message = "O campo id do evento é obrigatório")
    private int eventoId;

    @NotNull(message = "O campo id do membro é obrigatório")
    private int membroId;

    @NotNull(message = "O campo status de participação é obrigatório")
    private StatusParticipacao statusParticipacao;
    
    @NotNull(message = "O campo inscrito é obrigatório")
    private Boolean inscrito;

    public int getEventoId() {
        return eventoId;
    }

    public void setEventoId(int eventoId) {
        this.eventoId = eventoId;
    }

    public int getMembroId() {
        return membroId;
    }

    public void setMembroId(int membroId) {
        this.membroId = membroId;
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
