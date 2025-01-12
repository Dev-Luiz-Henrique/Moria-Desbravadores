package br.com.moria.domains.auth.enums;

import java.util.Set;

public enum AuthPermission {
    ALL,
    VIEW_OWN_ENTITIES,
    MANAGE_MEMBROS,
    VIEW_MEMBROS,
    MANAGE_EVENTOS,
    VIEW_EVENTOS,
    MANAGE_INSCRICOES,
    VIEW_INSCRICOES,
    MANAGE_RECURSOS,
    VIEW_RECURSOS,
    MANAGE_MENSALIDADES,
    VIEW_MENSALIDADES;

    public static final Set<AuthPermission> FULL_ACCESS = Set.of(
            MANAGE_MENSALIDADES,
            MANAGE_RECURSOS,
            MANAGE_MEMBROS,
            MANAGE_EVENTOS,
            MANAGE_INSCRICOES
    );

    public static final Set<AuthPermission> VIEW_ONLY = Set.of(
            VIEW_MEMBROS,
            VIEW_EVENTOS,
            VIEW_INSCRICOES
    );

    public static final Set<AuthPermission> VIEW_OWN = Set.of(
            VIEW_OWN_ENTITIES
    );
}
