package br.com.moria.domains.inscricao.dtos;

import br.com.moria.domains.inscricao.enums.InscricaoStatusParticipacao;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para atualização de uma inscrição existente.
 *
 * <p>Encapsula os dados necessários para atualizar as informações de uma inscrição já cadastrada,
 * garantindo validações essenciais para integridade e consistência das informações.</p>
 */
public class InscricaoUpdateDTO {

    private int id;

    @NotNull(message = "{javax.validation.constraints.NotNull}")
    private int eventoId;

    @NotNull(message = "{javax.validation.constraints.NotNull}")
    private int membroId;

    @NotNull(message = "{javax.validation.constraints.NotNull}")
    private InscricaoStatusParticipacao statusParticipacao;

    @NotNull(message = "{javax.validation.constraints.NotNull}")
    private Boolean inscrito;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
