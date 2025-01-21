package br.com.moria.domains.evento.dtos;

import java.time.LocalDateTime;

import br.com.moria.domains.endereco.dtos.EnderecoCreateDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * DTO para criação de um novo evento.
 *
 * <p>Encapsula os dados necessários para cadastrar um novo evento, garantindo validações
 * essenciais para integridade e consistência das informações.</p>
 */
public class EventoCreateDTO {

    @NotBlank(message = "{javax.validation.constraints.NotBlank}")
    @Size(max = 50, message = "{javax.validation.constraints.Size}")
    private String nome;

    @NotBlank(message = "{javax.validation.constraints.NotBlank}")
    @Size(max = 500, message = "{javax.validation.constraints.Size}")
    private String descricao;

    @NotBlank(message = "{javax.validation.constraints.NotBlank}")
    @Size(max = 50, message = "{javax.validation.constraints.Size}")
    private String atracao;

    @NotBlank(message = "{javax.validation.constraints.NotBlank}")
    private String imagem;

    @NotNull(message = "{javax.validation.constraints.NotNull}")
    @Future(message = "{javax.validation.constraints.Future}")
    private LocalDateTime dataInicio;

    @NotNull(message = "{javax.validation.constraints.NotNull}")
    @Future(message = "{javax.validation.constraints.Future}")
    private LocalDateTime dataFim;

    @NotNull(message = "{javax.validation.constraints.NotNull}")
    private boolean publico;

    @NotNull(message = "{javax.validation.constraints.NotNull}")
    @Positive(message = "{javax.validation.constraints.Positive}")
    private int numero;

    @Valid
    @JsonProperty("endereco")
    private EnderecoCreateDTO enderecoCreateDTO;

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

    public EnderecoCreateDTO getEnderecoCreateDTO() {
        return enderecoCreateDTO;
    }

    public void setEnderecoCreateDTO(EnderecoCreateDTO enderecoCreateDTO) {
        this.enderecoCreateDTO = enderecoCreateDTO;
    }
}
