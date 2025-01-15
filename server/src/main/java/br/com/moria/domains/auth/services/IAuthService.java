package br.com.moria.domains.auth.services;


import br.com.moria.domains.auth.enums.AuthPermission;
import br.com.moria.domains.membro.enums.MembroFuncao;

import java.util.Set;

/**
 * Interface de serviço para gerenciamento de autorização de usuários.
 *
 * <p>Define métodos para verificar permissões, recuperar a função atual do usuário e
 * obter as permissões associadas à função atual.</p>
 *
 * @see AuthPermission
 * @see MembroFuncao
 */
public interface IAuthService {

    /**
     * Verifica se o usuário atual possui a permissão especificada.
     *
     * @param permission a permissão necessária, representada por uma instância de {@link AuthPermission}.
     * @return {@code true} se o usuário tiver a permissão, caso contrário, {@code false}.
     */
    boolean hasPermission(AuthPermission permission);

    /**
     * Verifica se o usuário atual possui a permissão especificada como string.
     *
     * @param permission a permissão necessária, representada como {@link String}.
     * @return {@code true} se o usuário tiver a permissão, caso contrário, {@code false}.
     */
    boolean hasPermission(String permission);

    /**
     * Verifica se o usuário atual é o proprietário do item especificado.
     *
     * @param itemOwnerId o ID do proprietário do item.
     * @return {@code true} se o usuário atual for o proprietário, caso contrário, {@code false}.
     */
    boolean isCurrentUserOwner(int itemOwnerId);

    /**
     * Verifica se o usuário atual possui a permissão especificada ou é o proprietário do item.
     *
     * @param permission a permissão necessária, representada por uma instância de {@link AuthPermission}.
     * @param ownerId o ID do proprietário do item.
     * @return {@code true} se o usuário tiver a permissão ou for o proprietário, caso contrário, {@code false}.
     */
    boolean hasPermissionOrIsOwner(AuthPermission permission, int ownerId);

    /**
     * Obtém as permissões associadas à função atual do usuário.
     *
     * <p>As permissões determinam o que o usuário pode acessar ou realizar dentro do sistema
     * e são representadas como um conjunto de instâncias de {@link AuthPermission}.</p>
     *
     * @return o conjunto de permissões da função atual ou {@code null} se o usuário não estiver autenticado.
     */
    Set<AuthPermission> getCurrentUserPermissions();
}
