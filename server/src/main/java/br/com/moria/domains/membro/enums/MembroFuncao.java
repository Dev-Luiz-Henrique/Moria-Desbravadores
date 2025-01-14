package br.com.moria.domains.membro.enums;

import br.com.moria.domains.auth.enums.AuthPermission;

import java.util.Set;

public enum MembroFuncao {
    DIRETOR_CLUBE(AuthPermission.FULL_ACCESS.getPermissions()),
    DIRETOR_ASSOCIADO(AuthPermission.FULL_ACCESS.getPermissions()),
    TESOUREIRO(Set.of(
            AuthPermission.MANAGE_MENSALIDADES,
            AuthPermission.MANAGE_RECURSOS,
            AuthPermission.VIEW_MEMBROS,
            AuthPermission.VIEW_EVENTOS,
            AuthPermission.VIEW_INSCRICOES
    )),
    SECRETARIO(Set.of(
            AuthPermission.MANAGE_MEMBROS,
            AuthPermission.MANAGE_EVENTOS,
            AuthPermission.MANAGE_INSCRICOES,
            AuthPermission.VIEW_MENSALIDADES,
            AuthPermission.VIEW_RECURSOS
    )),
    INSTRUTOR_CLASSES_PROGRESSIVAS(AuthPermission.VIEW_ONLY.getPermissions()),
    CONSELHEIRO_UNIDADE(AuthPermission.VIEW_ONLY.getPermissions()),
    CAPELAO(AuthPermission.VIEW_ONLY.getPermissions()),
    AUXILIAR(AuthPermission.VIEW_ONLY.getPermissions()),
    DESBRAVADOR(AuthPermission.VIEW_OWN.getPermissions()),
    RESPONSAVEL(AuthPermission.VIEW_OWN.getPermissions());

    private final Set<AuthPermission> permissions;

    MembroFuncao(Set<AuthPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<AuthPermission> getPermissions() {
        return permissions;
    }
}
