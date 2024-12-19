package br.com.moria.dtos.Membro;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.moria.dtos.Endereco.EnderecoResponseDTO;
import br.com.moria.enums.EstadoCivil;
import br.com.moria.enums.TipoMembro;

public class MembroResponseDTO {

    private int id;
    private String nome;
    private String sexo;
    private LocalDate dataNascimento;
    private String cpf;
    private String rg;
    private String orgaoExpedidor;
    private EstadoCivil estadoCivil;
    private boolean batizado;
    private String telefone;
    private String celular;
    private String email;
    private int numero;
    private EnderecoResponseDTO enderecoResponseDTO;
    private String tamanhoCamisa;
    private String fichaSaude;
    private TipoMembro tipo;
    private LocalDateTime dataCadastro;
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
    
    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }
    
    public void setEstadoCivil(EstadoCivil estadoCivil) {
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

    public EnderecoResponseDTO getEnderecoResponseDTO() {
        return enderecoResponseDTO;
    }

    public void setEnderecoResponseDTO(EnderecoResponseDTO enderecoResponseDTO) {
        this.enderecoResponseDTO = enderecoResponseDTO;
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
    
    public TipoMembro getTipo() {
        return tipo;
    }
    
    public void setTipo(TipoMembro tipo) {
        this.tipo = tipo;
    }
    
    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }
    
    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
    public boolean isAtivo() {
        return ativo;
    }
    
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
