package br.com.moria.domains.inscricao.dtos;

import br.com.moria.domains.evento.dtos.EventoInscricaoResponseDTO;
import br.com.moria.domains.inscricao.enums.InscricaoStatusParticipacao;
import br.com.moria.domains.membro.dtos.MembroInscricaoResponseDTO;

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
    private InscricaoStatusParticipacao statusParticipacao;
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
    
    public InscricaoStatusParticipacao getStatusParticipacao() {
        return statusParticipacao;
    }
    
    public void setStatusParticipacao(InscricaoStatusParticipacao statusParticipacao) {
        this.statusParticipacao = statusParticipacao;
    }
    
    public boolean isInscrito() {
        return inscrito;
    }
    
    public void setInscrito(boolean inscrito) {
        this.inscrito = inscrito;
    }
}
