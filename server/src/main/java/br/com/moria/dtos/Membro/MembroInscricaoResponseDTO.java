package br.com.moria.dtos.Membro;

import br.com.moria.enums.TipoMembro;

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
    private TipoMembro tipo;
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
    
    public TipoMembro getTipo() {
        return tipo;
    }
    
    public void setTipo(TipoMembro tipo) {
        this.tipo = tipo;
    }
    
    public boolean isAtivo() {
        return ativo;
    }
    
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
