package br.com.moria.domains.inscricao.dtos;

import br.com.moria.domains.inscricao.enums.InscricaoStatusParticipacao;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para criação de uma nova inscrição.
 *
 * <p>Encapsula os dados necessários para cadastrar uma nova inscrição, garantindo validações
 * essenciais para integridade e consistência das informações.</p>
 * */
public class InscricaoCreateDTO {

    @NotNull(message = "O campo id do evento é obrigatório")
    private int eventoId;

    @NotNull(message = "O campo id do membro é obrigatório")
    private int membroId;

    @NotNull(message = "O campo status de participação é obrigatório")
    private InscricaoStatusParticipacao statusParticipacao;
    
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