package br.com.moria.domains.membro.dtos;

import java.time.LocalDate;

import br.com.moria.domains.endereco.dtos.EnderecoCreateDTO;
import br.com.moria.domains.membro.enums.MembroEstadoCivil;
import br.com.moria.domains.membro.enums.MembroFuncao;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO para criação de um novo membro.
 *
 * <p>Encapsula os dados necessários para cadastrar um novo membro, garantindo validações
 * essenciais para integridade e consistência das informações.</p>
 */
public class MembroCreateDTO {
    
    @NotBlank(message = "O campo nome é obrigatório")
    @Size(min = 3, max = 100, message = "O campo nome deve conter entre 3 e 100 caracteres")
    private String nome;

    @NotBlank(message = "O campo sexo é obrigatório")
    @Pattern(regexp = "[MFO]", message = "Sexo deve ser 'M', 'F' ou 'O'.")
    private String sexo;

    @NotNull(message = "O campo data de nascimento é obrigatório")
    @Past(message = "A data de nascimento deve ser anterior a data atual")
    private LocalDate dataNascimento;

    @NotBlank(message = "O campo CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "O campo CPF deve conter exatamente 11 dígitos")
    @Size(min = 11, max = 11, message = "O campo CPF deve conter exatamente 11 dígitos")
    private String cpf;

    @NotBlank(message = "O campo RG é obrigatório")
    @Size(max = 20, message = "O campo RG deve conter no máximo 20 dígitos")
    private String rg;

    @NotBlank(message = "O campo orgão expedidor é obrigatório")
    @Size(max = 15, message = "Órgão Expedidor deve ter no máximo 15 caracteres.")
    private String orgaoExpedidor;

    @NotNull(message = "O campo naturalidade é obrigatório")
    private MembroEstadoCivil estadoCivil;

    @NotNull(message = "O campo batizado é obrigatório")
    private boolean batizado;

    @NotBlank(message = "O campo telefone é obrigatório")
    @Pattern(regexp = "\\d{10}", message = "O campo telefone deve conter exatamente 10 dígitos")
    private String telefone;

    @NotBlank(message = "O campo celular é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "O campo celular deve conter exatamente 11 dígitos")
    private String celular;

    @NotBlank(message = "O campo email é obrigatório")
    @Email(message = "O campo email deve ser um email válido")
    private String email;

    @NotBlank(message = "O campo senha é obrigatório")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", 
        message = "A senha deve conter pelo menos 8 caracteres, uma letra maiúscula, " +
                "uma letra minúscula, um número e um caractere especial")
    @Size(max = 255, message = "O campo senha deve conter no máximo 255 caracteres")
    private String senha;

    @NotNull(message = "O campo numero é obrigatório")
    @Min(value = 1, message = "O campo numero deve ser maior que 0")
    private int numero;

    @Valid
    @JsonProperty("endereco")
    private EnderecoCreateDTO enderecoCreateDTO;
    
    @NotBlank(message = "O tamanho da camisa é obrigatório")
    @Size(max = 5, message = "O campo tamanho da camisa deve conter no máximo 5 caracteres")
    private String tamanhoCamisa;
    
    @Size(max = 255, message = "O campo ficha de saúde deve conter no máximo 255 caracteres")
    private String fichaSaude;

    @NotNull(message = "O campo função é obrigatório")
    private MembroFuncao funcao;

    @NotNull(message = "O campo ativo é obrigatório")
    private boolean ativo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getOrgaoExpedidor() {
        return orgaoExpedidor;
    }

    public void setOrgaoExpedidor(String orgaoExpedidor) {
        this.orgaoExpedidor = orgaoExpedidor;
    }

    public MembroEstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(MembroEstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public boolean isBatizado() {
        return batizado;
    }

    public void setBatizado(boolean batizado) {
        this.batizado = batizado;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public String getTamanhoCamisa() {
        return tamanhoCamisa;
    }

    public void setTamanhoCamisa(String tamanhoCamisa) {
        this.tamanhoCamisa = tamanhoCamisa;
    }

    public String getFichaSaude() {
        return fichaSaude;
    }

    public void setFichaSaude(String fichaSaude) {
        this.fichaSaude = fichaSaude;
    }

    public MembroFuncao getFuncao() {
        return funcao;
    }

    public void setFuncao(MembroFuncao funcao) {
        this.funcao = funcao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
