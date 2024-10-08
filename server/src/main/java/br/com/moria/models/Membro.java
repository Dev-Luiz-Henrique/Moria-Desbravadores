package br.com.moria.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.moria.enums.EstadoCivil;
import br.com.moria.enums.TipoMembro;

@Entity
@Table(name = "Membros")
@Getter
@Setter
public class Membro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "Id_Endereco", referencedColumnName = "id")
    private Endereco endereco;

    @NotBlank
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres.")
    @Column(name = "Nome", nullable = false, length = 100)
    private String nome;

    @NotBlank
    @Pattern(regexp = "[MFO]", message = "Sexo deve ser 'M', 'F' ou 'O'.")
    @Column(name = "Sexo", nullable = false, length = 1)
    private char sexo;

    @NotNull
    @Past(message = "A data de nascimento deve ser uma data passada.")
    @Column(name = "Data_Nascimento", nullable = false)
    private LocalDate dataNascimento;

    @NotBlank
    @Pattern(regexp = "\\d{10}", message = "Telefone deve ter exatamente 10 dígitos.")
    @Column(name = "Telefone", nullable = false, length = 10)
    private String telefone;

    @NotBlank
    @Pattern(regexp = "\\d{11}", message = "Celular deve ter exatamente 11 dígitos.")
    @Column(name = "Celular", nullable = false, length = 11)
    private String celular;

    @NotBlank
    @Email(message = "O formato do email é inválido.")
    @Column(name = "Email", nullable = false, length = 255, unique = true)
    private String email;

    @NotBlank
    @Column(name = "Senha", nullable = false)
    private byte[] senha;

    @Size(max = 255, message = "Logradouro deve ter no máximo 255 caracteres.")
    @Column(name = "Logradouro", length = 255)
    private String logradouro;

    @NotNull(message = "Número é obrigatório.")
    @Min(value = 1, message = "Número deve ser maior que 0.")
    @Column(name = "Numero", nullable = false)
    private int numero;

    @NotBlank
    @Pattern(regexp = "\\d{11}", message = "CPF deve ter exatamente 11 dígitos.")
    @Column(name = "CPF", nullable = false, length = 11, unique = true)
    private String cpf;

    @NotBlank
    @Size(max = 20, message = "RG deve ter no máximo 20 caracteres.")
    @Column(name = "RG", nullable = false, length = 20)
    private String rg;

    @NotBlank
    @Size(max = 15, message = "Órgão Expedidor deve ter no máximo 15 caracteres.")
    @Column(name = "Orgao_Expedidor", nullable = false, length = 15)
    private String orgaoExpedidor;

    @Size(max = 50, message = "Profissional de Saúde deve ter no máximo 50 caracteres.")
    @Column(name = "Profissional_Saude", length = 50)
    private String profissionalSaude;

    @NotBlank
    @Size(max = 5, message = "Tamanho da camisa deve ter no máximo 5 caracteres.")
    @Column(name = "Tamanho_Camisa", nullable = false, length = 5)
    private String tamanhoCamisa;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "Estado_Civil", nullable = false, length = 10)
    private EstadoCivil estadoCivil;

    @NotNull
    @Column(name = "Batizado", nullable = false)
    private boolean batizado;

    @NotNull
    @Column(name = "Data_Cadastro", nullable = false)
    private LocalDateTime dataCadastro = LocalDateTime.now();;

    @NotNull
    @Column(name = "Ativo", nullable = false)
    private boolean ativo = true;

    @NotBlank
    @Size(max = 255, message = "Ficha de Saúde deve ter no máximo 255 caracteres.")
    @Column(name = "Ficha_Saude", nullable = false, length = 255)
    private String fichaSaude;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "Tipo", nullable = false, length = 20)
    private TipoMembro tipo;

    public void setSexo(char sexo) {
        this.sexo = Character.toUpperCase(sexo);
    }
}