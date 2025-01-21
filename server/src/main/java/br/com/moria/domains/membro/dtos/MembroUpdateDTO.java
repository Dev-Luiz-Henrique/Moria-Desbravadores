package br.com.moria.domains.membro.dtos;

import java.time.LocalDate;

import br.com.moria.domains.endereco.dtos.EnderecoCreateDTO;
import br.com.moria.domains.membro.enums.MembroEstadoCivil;
import br.com.moria.domains.membro.enums.MembroFuncao;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * DTO para atualização de um membro existente.
 *
 * <p>Encapsula os dados necessários para atualizar as informações de um membro já cadastrado,
 * garantindo validações essenciais para integridade e consistência das informações.</p>
 */
public class MembroUpdateDTO {

    private int id;

    @NotBlank(message = "{javax.validation.constraints.NotBlank}")
    @Size(min = 3, max = 100, message = "{javax.validation.constraints.Size}")
    private String nome;

    @NotBlank(message = "{javax.validation.constraints.NotBlank}")
    @Pattern(regexp = "[MFO]", message = "{javax.validation.constraints.Pattern.sexo}")
    private String sexo;

    @NotNull(message = "{javax.validation.constraints.NotNull}")
    @Past(message = "{javax.validation.constraints.Past}")
    private LocalDate dataNascimento;

    @NotBlank(message = "{javax.validation.constraints.NotBlank}")
    @Pattern(regexp = "\\d{11}", message = "{javax.validation.constraints.Pattern.cpf}")
    @Size(min = 11, max = 11, message = "{javax.validation.constraints.Size}")
    private String cpf;

    @NotBlank(message = "{javax.validation.constraints.NotBlank}")
    @Size(max = 20, message = "{javax.validation.constraints.Size}")
    private String rg;

    @NotBlank(message = "{javax.validation.constraints.NotBlank}")
    @Size(max = 15, message = "{javax.validation.constraints.Size}")
    private String orgaoExpedidor;

    @NotNull(message = "{javax.validation.constraints.NotNull}")
    private MembroEstadoCivil estadoCivil;

    @NotNull(message = "{javax.validation.constraints.NotNull}")
    private boolean batizado;

    @NotBlank(message = "{javax.validation.constraints.NotBlank}")
    @Pattern(regexp = "\\d{10}", message = "{javax.validation.constraints.Pattern.telefone}")
    private String telefone;

    @NotBlank(message = "{javax.validation.constraints.NotBlank}")
    @Pattern(regexp = "\\d{11}", message = "{javax.validation.constraints.Pattern.celular}")
    private String celular;

    @NotBlank(message = "{javax.validation.constraints.NotBlank}")
    @Email(message = "{javax.validation.constraints.Email}")
    private String email;

    @NotNull(message = "{javax.validation.constraints.NotNull}")
    @Positive(message = "{javax.validation.constraints.Positive}")
    private int numero;

    @Valid
    @JsonProperty("endereco")
    private EnderecoCreateDTO enderecoCreateDTO;

    @NotBlank(message = "{javax.validation.constraints.NotBlank}")
    @Size(max = 5, message = "{javax.validation.constraints.Size}")
    private String tamanhoCamisa;

    @Size(max = 255, message = "{javax.validation.constraints.Size}")
    private String fichaSaude;

    @NotNull(message = "{javax.validation.constraints.NotNull}")
    private MembroFuncao funcao;

    @NotNull(message = "{javax.validation.constraints.NotNull}")
    private boolean ativo;

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
