package br.com.moria.domains.mensalidade;

import java.time.LocalDateTime;

import br.com.moria.domains.membro.Membro;
import br.com.moria.enums.FormaPagamento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entidade que representa mensalidade de um membro no sistema.
 *
 * <p>Encapsula todas as informações relacionadas a uma mensalidade.</p>
 */
@Entity
@Table(name = "mensalidades")
public class Mensalidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_membro", referencedColumnName = "id")
    private Membro membro;

    @Column(name = "data", nullable = false, columnDefinition= "DATETIME")
    private LocalDateTime data;

    @Column(name = "data_vencimento", nullable = false, columnDefinition= "DATETIME")
    private LocalDateTime dataVencimento;

    @Column(name = "data_pagamento", columnDefinition= "DATETIME")
    private LocalDateTime dataPagamento;

    @Column(name = "valor", nullable = false, columnDefinition = "DOUBLE(8,2)")
    private Double valor;

    @Column(name = "pagamento_realizado", nullable = false)
    private boolean pagamentoRealizado;

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pagamento", length = 20)
    private FormaPagamento formaPagamento;

    @Column(name = "comprovante")
    private String comprovante;

    @Column(name = "observacoes", length = 500)
    private String observacoes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public LocalDateTime getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDateTime dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public boolean isPagamentoRealizado() {
        return pagamentoRealizado;
    }

    public void setPagamentoRealizado(boolean pagamentoRealizado) {
        this.pagamentoRealizado = pagamentoRealizado;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getComprovante() {
        return comprovante;
    }

    public void setComprovante(String comprovante) {
        this.comprovante = comprovante;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Membro getMembro() {
        return membro;
    }

    public void setMembro(Membro membro) {
        this.membro = membro;
    }
}