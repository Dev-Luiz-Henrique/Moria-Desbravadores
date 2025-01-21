package br.com.moria.domains.mensalidade.dtos;

import java.time.LocalDateTime;

import br.com.moria.shared.enums.FormaPagamento;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para criação de uma nova mensalidade.
 *
 * <p>Encapsula os dados necessários para cadastrar uma nova mensalidade, garantindo validações
 * essenciais para integridade e consistência das informações.</p>
 */
public class MensalidadeCreateDTO {

    @NotNull(message = "{javax.validation.constraints.NotNull}")
    private int idMembro;

    @NotNull(message = "{javax.validation.constraints.NotNull}")
    private LocalDateTime data;

    @NotNull(message = "{javax.validation.constraints.NotNull}")
    @FutureOrPresent(message = "{javax.validation.constraints.FutureOrPresent}")
    private LocalDateTime dataVencimento;

    @NotNull(message = "{javax.validation.constraints.NotNull}")
    @FutureOrPresent(message = "{javax.validation.constraints.FutureOrPresent}")
    private LocalDateTime dataPagamento;

    @NotNull(message = "{javax.validation.constraints.NotNull}")
    @DecimalMin(value = "0.0", inclusive = false, message = "{javax.validation.constraints.DecimalMin}")
    private Double valor;

    @NotNull(message = "{javax.validation.constraints.NotNull}")
    private boolean pagamentoRealizado;

    @NotNull(message = "{javax.validation.constraints.NotNull}")
    private FormaPagamento formaPagamento;

    @NotBlank(message = "{javax.validation.constraints.NotBlank}")
    private String comprovante;

    @NotBlank(message = "{javax.validation.constraints.NotBlank}")
    private String observacoes;

    public int getIdMembro() {
        return idMembro;
    }

    public void setIdMembro(int idMembro) {
        this.idMembro = idMembro;
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
