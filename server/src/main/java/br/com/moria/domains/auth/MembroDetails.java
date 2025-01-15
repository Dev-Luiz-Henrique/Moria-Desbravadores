package br.com.moria.domains.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Classe que representa os detalhes do membro autenticado.
 */
public class MembroDetails extends User {

    private final int id;

    /**
     * Construtor.
     *
     * @param id          o ID do membro.
     * @param username    o e-mail do membro.
     * @param password    a senha do membro.
     * @param authorities as autoridades do membro.
     */
    public MembroDetails(int id, String username, String password,
                         Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
    }

    /**
     * Retorna o ID do membro autenticado.
     *
     * @return o ID do membro.
     */
    public int getId() {
        return id;
    }
}
