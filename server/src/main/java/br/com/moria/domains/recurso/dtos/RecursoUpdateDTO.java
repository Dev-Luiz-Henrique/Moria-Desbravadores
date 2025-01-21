package br.com.moria.domains.recurso.dtos;

import java.math.BigDecimal;

import br.com.moria.domains.recurso.enums.RecursoCategoria;
import br.com.moria.shared.enums.FormaPagamento;
import br.com.moria.shared.enums.StatusPagamento;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO para atualização de um recurso existente.
 *
 * <p>Encapsula os dados necessários para atualizar as informações de um recurso já cadastrado,
 * garantindo validações essenciais para integridade e consistência das informações.</p>
 */
public class RecursoUpdateDTO {

    private int id;

    @NotNull(message = "{javax.validation.constraints.NotNull}")
    private int idEvento;

    @NotBlank(message = "{javax.validation.constraints.NotBlank}")
    @Size(max = 255, message = "{javax.validation.constraints.Size}")
    private String nome;

    @NotBlank(message = "{javax.validation.constraints.NotBlank}")
    @Size(max = 255, message = "{javax.validation.constraints.Size}")
    private String descricao;

    @NotNull(message = "{javax.validation.constraints.NotNull}")
    @DecimalMin(value = "0.00", message = "{javax.validation.constraints.DecimalMin}")
    private BigDecimal valor;

    @NotNull(message = "{javax.validation.constraints.NotNull}")
    @DecimalMin(value = "0.00", message = "{javax.validation.constraints.DecimalMin}")
    private Double quantidade;

    @NotNull(message = "{javax.validation.constraints.NotNull}")
    private FormaPagamento formaPagamento;

    @NotNull(message = "{javax.validation.constraints.NotNull}")
    private RecursoCategoria categoria;

    @NotNull(message = "{javax.validation.constraints.NotNull}")
    private StatusPagamento statusPagamento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
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

    public RecursoCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(RecursoCategoria categoria) {
        this.categoria = categoria;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }
}
