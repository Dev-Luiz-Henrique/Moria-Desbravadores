package br.com.moria.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.moria.enums.EstadoCivil;
import br.com.moria.enums.TipoMembro;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "membros")
@Getter
@Setter
public class Membro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "Endereço não pode ser nulo.")
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    private Endereco endereco;

    @OneToMany(mappedBy = "membro", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference("membroInscricoes")
    private List<Inscricao> inscricoes;

    @OneToMany(mappedBy = "membro", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Mensalidade> mensalidades;

    @NotBlank(message = "Nome não pode ser vazio.")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres.")
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "Sobrenome não pode ser vazio.")
    @Pattern(regexp = "[MFO]", message = "Sexo deve ser 'M', 'F' ou 'O'.")
    @Column(name = "sexo", nullable = false, length = 1)
    private String sexo;

    @NotNull(message = "Data de nascimento não pode ser nula.")
    @Past(message = "A data de nascimento deve ser uma data passada.")
    @Column(name = "data_nascimento", nullable = false, columnDefinition= "DATETIME")
    private LocalDate dataNascimento;

    @NotBlank(message = "Telefone não pode ser vazio.")
    @Pattern(regexp = "\\d{10}", message = "Telefone deve ter exatamente 10 dígitos.")
    @Column(name = "telefone", nullable = false, columnDefinition = "CHAR(10)")
    private String telefone;

    @NotBlank(message = "Celular não pode ser vazio.")
    @Pattern(regexp = "\\d{11}", message = "Celular deve ter exatamente 11 dígitos.")
    @Column(name = "celular", nullable = false, columnDefinition = "CHAR(11)")
    private String celular;

    @NotBlank(message = "Email não pode ser vazio.")
    @Email(message = "O formato do email é inválido.")
    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;

    @NotBlank(message = "Senha não pode ser vazia.")
    @Size(max = 255, message = "Senha deve ter no máximo 255 caracteres.")
    @Column(name = "senha", nullable = false, length = 255)
    private String senha;

    @Size(max = 255, message = "Logradouro deve ter no máximo 255 caracteres.")
    @Column(name = "logradouro", length = 255)
    private String logradouro;

    @NotNull(message = "Número não pode ser nulo.")
    @Min(value = 1, message = "Número deve ser maior que 0.")
    @Column(name = "numero", nullable = false)
    private int numero;

    @NotBlank(message = "CPF não pode ser vazio.")
    @Pattern(regexp = "\\d{11}", message = "CPF deve ter exatamente 11 dígitos.")
    @Column(name = "cpf", nullable = false, columnDefinition = "CHAR(11)", unique = true)
    private String cpf;

    @NotBlank(message = "RG não pode ser vazio.")
    @Size(max = 20, message = "RG deve ter no máximo 20 caracteres.")
    @Column(name = "rg", nullable = false, length = 20)
    private String rg;

    @NotBlank(message = "Órgão Expedidor não pode ser vazio.")
    @Size(max = 15, message = "Órgão Expedidor deve ter no máximo 15 caracteres.")
    @Column(name = "orgao_expedidor", nullable = false, length = 15)
    private String orgaoExpedidor;

    @NotBlank(message = "Tamanho da camisa não pode ser vazio.")
    @Size(max = 5, message = "Tamanho da camisa deve ter no máximo 5 caracteres.")
    @Column(name = "tamanho_camisa", nullable = false, length = 5)
    private String tamanhoCamisa;

    @NotNull(message = "Estado civil não pode ser nulo.")
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_civil", nullable = false, length = 10)
    private EstadoCivil estadoCivil;

    @NotNull(message = "Batizado não pode ser nulo.")
    @Column(name = "batizado", nullable = false)
    private boolean batizado;

    @NotNull(message = "Data de cadastro não pode ser nula.")
    @Column(name = "data_cadastro", nullable = false, columnDefinition= "DATETIME")
    private LocalDateTime dataCadastro = LocalDateTime.now();

    @NotNull(message = "Ativo não pode ser nulo.")
    @Column(name = "ativo", nullable = false)
    private boolean ativo = true;

    @Size(max = 255, message = "Ficha de Saúde deve ter no máximo 255 caracteres.")
    @Column(name = "ficha_saude", length = 255)
    private String fichaSaude;

    @NotNull(message = "Tipo de membro não pode ser nulo.")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 20)
    private TipoMembro tipo;

    public void setSexo(String sexo) {
        if (sexo != null) {
			this.sexo = sexo.toUpperCase();
		} else {
			this.sexo = null;
		}
    }
}