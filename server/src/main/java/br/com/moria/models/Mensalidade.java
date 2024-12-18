package br.com.moria.models;

import java.time.LocalDateTime;

import br.com.moria.enums.FormaPagamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "mensalidades")
public class Mensalidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "Membro não pode ser nulo.")
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_membro", referencedColumnName = "id")
    private Membro membro;

    @NotNull(message = "Data não pode ser nula.")
    @Column(name = "data", nullable = false, columnDefinition= "DATETIME")
    private LocalDateTime data;

    @NotNull(message = "Data de vencimento não pode ser nula.")
    @Column(name = "data_vencimento", nullable = false, columnDefinition= "DATETIME")
    private LocalDateTime dataVencimento;

    @Column(name = "data_pagamento", columnDefinition= "DATETIME")
    private LocalDateTime dataPagamento;

    @NotNull(message = "Valor não pode ser nulo.")
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "valor", nullable = false, columnDefinition = "DOUBLE(8,2)")
    private Double valor;

    @Column(name = "pagamento_realizado", nullable = false)
    private boolean pagamentoRealizado;

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pagamento", length = 20)
    private FormaPagamento formaPagamento;

    @Size(max = 255, message = "Caminho do comprovante deve ter no máximo 255 caracteres.")
    @Column(name = "comprovante", length = 255)
    private String comprovante;

    @Size(max = 500, message = "Observações devem ter no máximo 500 caracteres.")
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