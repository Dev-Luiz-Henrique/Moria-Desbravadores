package br.com.moria.domains.auth.enums;

import java.util.EnumSet;
import java.util.Set;

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
    MANAGE_RECURSOS,

    FULL_ACCESS(EnumSet.allOf(AuthPermission.class)),
    VIEW_ONLY(EnumSet.of(
            VIEW_MEMBROS, VIEW_EVENTOS, VIEW_INSCRICOES, VIEW_MENSALIDADES, VIEW_RECURSOS
    )),
    VIEW_OWN(EnumSet.of(
            VIEW_MEMBROS, VIEW_EVENTOS, VIEW_INSCRICOES
    ));

    private final Set<AuthPermission> permissions;

    AuthPermission(Set<AuthPermission> permissions) {
        this.permissions = permissions;
    }

    AuthPermission() {
        this.permissions = Set.of(this);
    }

    public Set<AuthPermission> getPermissions() {
        return permissions;
    }
}
