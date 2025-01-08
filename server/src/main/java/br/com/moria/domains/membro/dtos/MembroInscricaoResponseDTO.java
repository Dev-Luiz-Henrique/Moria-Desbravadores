package br.com.moria.domains.membro.dtos;

import br.com.moria.domains.membro.enums.MembroFuncao;

/**
 * DTO de resposta para informações resumidas de inscrição de membro.
 *
 * <p>Fornece informações básicas sobre um membro, normalmente usadas para exibição em
 * listagens ou retornos de operações relacionadas a inscrições.</p>
 */
public class MembroInscricaoResponseDTO {
    
    private int id;
    private String nome;
    private String cpf;
    private MembroFuncao funcao;
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
    
    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
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
