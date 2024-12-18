package br.com.moria.dtos.Recurso;

import java.math.BigDecimal;

import br.com.moria.enums.FormaPagamento;
import br.com.moria.enums.RecursoEvento;
import br.com.moria.enums.StatusPagamento;
import br.com.moria.models.Evento;

public class RecursoResponseDTO {
    
    private int id;
    private Evento evento;
    private String nome;
    private String descricao;
    private BigDecimal valor;
    private Double quantidade;
    private FormaPagamento formaPagamento;
    private RecursoEvento categoria;
    private StatusPagamento statusPagamento;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Evento getEvento() {
        return evento;
    }
    
    public void setEvento(Evento evento) {
        this.evento = evento;
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
    
    public BigDecimal getValor() {
        return valor;
    }
    
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    
    public Double getQuantidade() {
        return quantidade;
    }
    
    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }
    
    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }
    
    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    
    public RecursoEvento getCategoria() {
        return categoria;
    }
    
    public void setCategoria(RecursoEvento categoria) {
        this.categoria = categoria;
    }
    
    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }
    
    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }
}
