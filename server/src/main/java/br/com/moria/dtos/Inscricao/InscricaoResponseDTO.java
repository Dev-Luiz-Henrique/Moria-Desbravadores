package br.com.moria.dtos.Inscricao;

import br.com.moria.dtos.Evento.EventoInscricaoResponseDTO;
import br.com.moria.dtos.Membro.MembroInscricaoResponseDTO;
import br.com.moria.enums.StatusParticipacao;

/**
 * DTO de resposta para informações detalhadas de uma inscrição.
 *
 * <p>Fornece informações completas sobre uma inscrição, normalmente usadas para exibição em
 * detalhes ou edição de informações.</p>
 */
public class InscricaoResponseDTO {

    private int id;
    private MembroInscricaoResponseDTO membroInscricaoResponseDTO ;
    private EventoInscricaoResponseDTO eventoInscricaoResponseDTO;
    private StatusParticipacao statusParticipacao;
    private boolean inscrito;

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public MembroInscricaoResponseDTO getMembroInscricaoResponseDTO() {
        return membroInscricaoResponseDTO;
    }

    public void setMembroInscricaoResponseDTO(MembroInscricaoResponseDTO membroInscricaoResponseDTO) {
        this.membroInscricaoResponseDTO = membroInscricaoResponseDTO;
    }

    public EventoInscricaoResponseDTO getEventoInscricaoResponseDTO() {
        return eventoInscricaoResponseDTO;
    }

    public void setEventoInscricaoResponseDTO(EventoInscricaoResponseDTO eventoInscricaoResponseDTO) {
        this.eventoInscricaoResponseDTO = eventoInscricaoResponseDTO;
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
