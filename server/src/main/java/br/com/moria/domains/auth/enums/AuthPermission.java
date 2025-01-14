package br.com.moria.domains.auth.enums;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 * Enumeração que define as permissões de autenticação para diferentes operações do sistema.
 *
 * <p>As permissões são usadas para controlar o acesso a entidades e funcionalidades específicos,
 * agrupando permissões em conjuntos como acesso total, apenas visualização ou visualização própria.</p>
 *
 * <p>Permite combinações personalizadas de permissões para atender a requisitos específicos.</p>
 */
public enum AuthPermission {
    VIEW_MEMBROS,
    VIEW_EVENTOS,
    VIEW_INSCRICOES,
    VIEW_MENSALIDADES,
    VIEW_RECURSOS,
    MANAGE_MEMBROS,
    MANAGE_EVENTOS,
    MANAGE_INSCRICOES,
    MANAGE_MENSALIDADES,
    MANAGE_RECURSOS;

    /** Conjunto de permissões que fornece acesso completo a todas as entidades. */
    public static final Set<AuthPermission> FULL_ACCESS = EnumSet.allOf(AuthPermission.class);

    /** Conjunto de permissões para visualização de entidades apenas. */
    public static final Set<AuthPermission> VIEW_ONLY = EnumSet.of(
            VIEW_MEMBROS, VIEW_EVENTOS, VIEW_INSCRICOES, VIEW_MENSALIDADES, VIEW_RECURSOS
    );

    /** Conjunto de permissões para visualização de entidades próprias. */
    public static final Set<AuthPermission> VIEW_OWN = EnumSet.of(
            VIEW_MEMBROS, VIEW_EVENTOS, VIEW_INSCRICOES
    );

    /**
     * Combina dois conjuntos de permissões.
     *
     * @param base       o conjunto base de permissões.
     * @param additional permissões adicionais a serem combinadas.
     * @return um novo conjunto contendo todas as permissões combinadas.
     */
    public static Set<AuthPermission> combine(Set<AuthPermission> base, Set<AuthPermission> additional) {
        return new HashSet<>(EnumSet.copyOf(base));
    }
}
