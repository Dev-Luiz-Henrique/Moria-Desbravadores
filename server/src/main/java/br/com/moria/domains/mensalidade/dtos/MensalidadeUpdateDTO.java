package br.com.moria.domains.mensalidade.dtos;

import java.time.LocalDateTime;

import br.com.moria.shared.enums.FormaPagamento;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para atualização de uma mensalidade existente.
 *
 * <p>Encapsula os dados necessários para atualizar as informações de uma mensalidade já cadastrada,
 * garantindo validações essenciais para integridade e consistência das informações.</p>
 */
public class MensalidadeUpdateDTO {

    private int id;

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
}
