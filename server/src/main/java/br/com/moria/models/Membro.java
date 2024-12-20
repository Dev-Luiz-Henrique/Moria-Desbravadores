package br.com.moria.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.moria.enums.EstadoCivil;
import br.com.moria.enums.TipoMembro;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "membros")
public class Membro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "sexo", nullable = false, length = 1)
    private String sexo;

    @Column(name = "data_nascimento", nullable = false, columnDefinition= "DATETIME")
    private LocalDate dataNascimento;

    @Column(name = "cpf", nullable = false, columnDefinition = "CHAR(11)", unique = true)
    private String cpf;

    @Column(name = "rg", nullable = false, length = 20)
    private String rg;

    @Column(name = "orgao_expedidor", nullable = false, length = 15)
    private String orgaoExpedidor;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_civil", nullable = false, length = 10)
    private EstadoCivil estadoCivil;

    @Column(name = "batizado", nullable = false)
    private boolean batizado;

    @Column(name = "telefone", nullable = false, columnDefinition = "CHAR(10)")
    private String telefone;

    @Column(name = "celular", nullable = false, columnDefinition = "CHAR(11)")
    private String celular;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "numero", nullable = false)
    private int numero;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    private Endereco endereco;

    @Column(name = "tamanho_camisa", nullable = false, length = 5)
    private String tamanhoCamisa;

    @Column(name = "ficha_saude")
    private String fichaSaude;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 20)
    private TipoMembro tipo;

    @Column(name = "data_cadastro", nullable = false, columnDefinition= "DATETIME")
    private LocalDateTime dataCadastro = LocalDateTime.now();

    @Column(name = "ativo", nullable = false)
    private boolean ativo = true;

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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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