package br.com.moria.domains.membro.enums;

import br.com.moria.domains.auth.enums.AuthPermission;

import java.util.EnumSet;
import java.util.Set;

import static br.com.moria.domains.auth.enums.AuthPermission.*;

public enum MembroFuncao {
    ADMINISTRADOR(Set.of(ALL)),
    DIRETOR_CLUBE(FULL_ACCESS),
    DIRETOR_ASSOCIADO(FULL_ACCESS),
    TESOUREIRO(Set.of(
            MANAGE_MENSALIDADES,
            MANAGE_RECURSOS,
            VIEW_MEMBROS,
            VIEW_EVENTOS,
            VIEW_INSCRICOES
    )),
    SECRETARIO(Set.of(
            MANAGE_MEMBROS,
            MANAGE_EVENTOS,
            MANAGE_INSCRICOES,
            VIEW_MENSALIDADES,
            VIEW_RECURSOS
    )),
    INSTRUTOR_CLASSES_PROGRESSIVAS(VIEW_ONLY),
    CONSELHEIRO_UNIDADE(VIEW_ONLY),
    CAPELAO(VIEW_ONLY),
    AUXILIAR(VIEW_ONLY),
    DESBRAVADOR(VIEW_OWN),
    RESPONSAVEL(VIEW_OWN);

    private final Set<AuthPermission> permissions;

    MembroFuncao(Set<AuthPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<AuthPermission> getPermissions() {
        return permissions;
    }

    public boolean hasPermission(AuthPermission permission) {
        return permissions.contains(permission) || permissions.contains(ALL);
    }

    public boolean hasPermission(String permission) {
        if (permission == null || permission.isEmpty()) return false;

        return EnumSet.allOf(AuthPermission.class)
                .stream()
                .anyMatch(authPermission -> authPermission.name().equals(permission)) &&
                    hasPermission(AuthPermission.valueOf(permission));
    }
}
