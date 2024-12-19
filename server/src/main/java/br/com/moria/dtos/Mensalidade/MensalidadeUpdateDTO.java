package br.com.moria.dtos.Mensalidade;

import java.time.LocalDateTime;

import br.com.moria.enums.FormaPagamento;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MensalidadeUpdateDTO {

    private int id;

    @NotNull(message = "O campo data é obrigatória")
    private LocalDateTime data;

    @NotNull(message = "O campo data de vencimento é obrigatória")
    private LocalDateTime dataVencimento;

    @NotNull(message = "O campo data de pagamento é obrigatória")
    private LocalDateTime dataPagamento;

    @NotNull(message = "O campo valor é obrigatório")
    @DecimalMin(value = "0.00", message = "O campo valor deve ser maior que 0")
    private Double valor;

    @NotNull(message = "O campo pagamento realizado é obrigatório")
    private boolean pagamentoRealizado;

    @NotNull(message = "O campo forma de pagamento é obrigatório")
    private FormaPagamento formaPagamento;

    @NotBlank(message = "O campo comprovante é obrigatório")
    private String comprovante;

    @NotBlank(message = "O campo observações é obrigatório")
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
