package br.com.moria.domains.evento.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.moria.domains.endereco.dtos.EnderecoResponseDTO;

/**
 * DTO de resposta para informações detalhadas de um evento.
 *
 * <p>Fornece informações completas sobre um evento, normalmente usadas para exibição em
 * detalhes ou edição de informações.</p>
 */
public class EventoResponseDTO {

    private int id;
    private String nome;
    private String descricao;
    private String atracao;
    private String imagem;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private boolean publico;
    private int numero;

    @JsonProperty("endereco")
    private EnderecoResponseDTO enderecoResponseDTO;
    
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
    
    public String getAtracao() {
        return atracao;
    }
    
    public void setAtracao(String atracao) {
        this.atracao = atracao;
    }
    
    public String getImagem() {
        return imagem;
    }
    
    public void setImagem(String imagem) {
        this.imagem = imagem;
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

    public boolean isPublico() {
        return publico;
    }
    
    public void setPublico(boolean publico) {
        this.publico = publico;
    }
    
    public int getNumero() {
        return numero;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public EnderecoResponseDTO getEnderecoResponseDTO() {
        return enderecoResponseDTO;
    }
    
    public void setEnderecoResponseDTO(EnderecoResponseDTO enderecoResponseDTO) {
        this.enderecoResponseDTO = enderecoResponseDTO;
    }
}