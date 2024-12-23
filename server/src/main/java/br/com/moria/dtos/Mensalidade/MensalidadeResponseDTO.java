package br.com.moria.dtos.Mensalidade;

import java.time.LocalDateTime;

import br.com.moria.enums.FormaPagamento;
import br.com.moria.models.Membro;

/**
 * DTO de resposta para informações detalhadas de uma mensalidade.
 *
 * <p>Fornece informações completas sobre uma mensalidade, normalmente usadas para exibição em
 * detalhes ou edição de informações.</p>
 */
public class MensalidadeResponseDTO {
    
    private int id;
    private Membro membro;
    private LocalDateTime data;
    private LocalDateTime dataVencimento;
    private LocalDateTime dataPagamento;
    private Double valor;
    private boolean pagamentoRealizado;
    private FormaPagamento formaPagamento;
    private String comprovante;
    private String observacoes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Membro getMembro() {
        return membro;
    }

    public void setMembro(Membro membro) {
        this.membro = membro;
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
}
