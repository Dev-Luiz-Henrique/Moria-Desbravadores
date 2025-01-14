package br.com.moria.domains.membro.enums;

import br.com.moria.domains.auth.enums.AuthPermission;

import java.util.Set;

/**
 * Enumeração que define as funções associadas aos membros de um clube ou organização.
 *
 * <p>Cada função é mapeada para um conjunto de permissões específicas,
 * controlando o acesso a recursos e operações disponíveis para os membros.</p>
 */
public enum MembroFuncao {
    DIRETOR_CLUBE(AuthPermission.FULL_ACCESS),
    DIRETOR_ASSOCIADO(AuthPermission.FULL_ACCESS),
    TESOUREIRO(AuthPermission.combine(AuthPermission.VIEW_ONLY, Set.of(
            AuthPermission.MANAGE_MENSALIDADES,
            AuthPermission.MANAGE_RECURSOS
    ))),
    SECRETARIO(AuthPermission.combine(AuthPermission.VIEW_ONLY, Set.of(
            AuthPermission.MANAGE_MEMBROS,
            AuthPermission.MANAGE_EVENTOS,
            AuthPermission.MANAGE_INSCRICOES
    ))),
    INSTRUTOR_CLASSES_PROGRESSIVAS(AuthPermission.VIEW_ONLY),
    CONSELHEIRO_UNIDADE(AuthPermission.VIEW_ONLY),
    CAPELAO(AuthPermission.VIEW_ONLY),
    AUXILIAR(AuthPermission.VIEW_ONLY),
    DESBRAVADOR(AuthPermission.VIEW_OWN),
    RESPONSAVEL(AuthPermission.VIEW_OWN);

    private final Set<AuthPermission> permissions;

    /**
     * Construtor para associar permissões à função.
     *
     * @param permissions o conjunto de permissões atribuídas à função.
     */
    MembroFuncao(Set<AuthPermission> permissions) {
        this.permissions = permissions;
    }

    /**
     * Obtém as permissões associadas à função.
     *
     * @return o conjunto de permissões atribuídas à função.
     */
    public Set<AuthPermission> getPermissions() {
        return permissions;
    }
}
