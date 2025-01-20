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
 * DTO para criação de um novo recurso.
 *
 * <p>Encapsula os dados necessários para cadastrar um novo recurso, garantindo validações
 * essenciais para integridade e consistência das informações.</p>
 */
public class RecursoCreateDTO {

    @NotNull(message = "O campo id evento é obrigatório")
    private int idEvento;
    
    @NotBlank(message = "O campo nome é obrigatório")
    @Size(max = 255, message = "O nome deve ter no máximo 255 caracteres")
    private String nome;

    @NotBlank(message = "O campo descrição é obrigatório")
    @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres")
    private String descricao;
    
    @NotNull(message = "O campo valor é obrigatório")
    @DecimalMin(value = "0.00", message = "O valor deve ser maior que 0")
    private BigDecimal valor;

    @NotNull(message = "O campo quantidade é obrigatório")
    @DecimalMin(value = "0.00", message = "A quantidade deve ser maior que 0")
    private Double quantidade;

    @NotNull(message = "O campo forma de pagamento é obrigatório")
    private FormaPagamento formaPagamento;

    @NotNull(message = "O campo categoria é obrigatório")
    private RecursoCategoria categoria;

    @NotNull(message = "O campo status de pagamento é obrigatório")
    private StatusPagamento statusPagamento;

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
