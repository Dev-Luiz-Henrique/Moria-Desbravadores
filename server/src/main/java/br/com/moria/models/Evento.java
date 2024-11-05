package br.com.moria.models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "eventos")
@Getter
@Setter
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "Endereço não pode ser nulo.")
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    private Endereco endereco;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    @JsonManagedReference
	private List<Recurso> recursos;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    @JsonManagedReference("eventoInscricoes")
    private List<Inscricao> inscricoes;

    @NotBlank(message = "Nome não pode ser vazio.")
    @Size(max = 50, message = "Nome deve ter no máximo 50 caracteres.")
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @NotBlank(message = "Atração não pode ser vazia.")
    @Size(max = 50, message = "Atração deve ter no máximo 50 caracteres.")
    @Column(name = "atracao", nullable = false, length = 50)
    private String atracao;

    @NotBlank(message = "Descrição não pode ser vazia.")
    @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres.")
    @Column(name = "descricao", nullable = false, length = 500)
    private String descricao;

    @Size(max = 255, message = "Caminho da imagem deve ter no máximo 255 caracteres.")
    @Column(name = "imagem", length = 255)
    private String imagem;

    @NotNull(message = "Data de início não pode ser nula.")
    @Future(message = "A data de início do evento deve ser uma data futura.")
    @Column(name = "data_inicio", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime dataInicio;

    @NotNull(message = "Data fim não pode ser nula.")
    @Future(message = "A data fim do evento deve ser uma data futura.")
    @Column(name = "data_fim", nullable = false , columnDefinition= "DATETIME")
    private LocalDateTime dataFim;

    @NotNull(message = "Número não pode ser nulo.")
    @Min(value = 1, message = "Número deve ser maior que 0.")
    @Column(name = "numero", nullable = false)
    private int numero;

    @NotNull(message = "Logradouro não pode ser nulo.")
    @Size(max = 255, message = "Logradouro deve ter no máximo 255 caracteres.")
    @Column(name = "logradouro", length = 255)
    private String logradouro;

    @Column(name = "publico", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean publico;
}