package br.com.moria.dtos.Evento;

import java.time.LocalDateTime;

import br.com.moria.models.Endereco;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EventoUpdateDTO {

    private int id;

    @NotBlank(message = "O campo nome é obrigatório")
    @Size(max = 50, message = "O campo nome deve ter no máximo 50 caracteres")
    private String nome;
    
    @NotBlank(message = "O campo descrição é obrigatório")
    @Size(max = 500, message = "O campo descrição deve ter no máximo 500 caracteres")
    private String descricao;

    @NotBlank(message = "O campo atração é obrigatório")
    @Size(max = 50, message = "O campo atração deve ter no máximo 50 caracteres")
    private String atracao;

    @NotBlank(message = "O campo imagem é obrigatório")
    private String imagem;

    @NotNull(message = "O campo data de início é obrigatório")
    @Future(message = "A data de início deve ser uma data futura")
    private LocalDateTime dataInicio;

    @NotNull(message = "O campo data de fim é obrigatório")
    @Future(message = "A data de fim deve ser uma data futura")
    private LocalDateTime dataFim;

    @NotNull(message = "O campo valor é obrigatório")
    private boolean publico;

    @NotNull(message = "O campo numero é obrigatório")
    @Min(value = 1, message = "O campo numero deve ser maior que 0")
    private int numero;

    @NotNull(message = "O campo endereço é obrigatório")
    private Endereco endereco;

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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}