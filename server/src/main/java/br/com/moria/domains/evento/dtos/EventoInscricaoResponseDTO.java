package br.com.moria.domains.evento.dtos;

import java.time.LocalDateTime;

/**
 * DTO de resposta para informações resumidas de inscrição de evento.
 *
 * <p>Fornece informações básicas sobre um evento, normalmente usadas para exibição em
 * listagens ou retornos de operações relacionadas a inscrições.</p>
 */
public class EventoInscricaoResponseDTO {
    
    private int id;
    private String nome;
    private String descricao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public LocalDateTime getDataInicio() {
        return dataInicio;
    }
    
    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }
    
    public LocalDateTime getDataFim() {
        return dataFim;
    }
    
    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }
}
