package br.com.moria.domains.auth.services;

import br.com.moria.domains.auth.MembroDetails;
import br.com.moria.domains.auth.enums.AuthPermission;
import br.com.moria.domains.auth.exceptions.ForbiddenException;
import br.com.moria.domains.auth.exceptions.UnauthorizedException;
import br.com.moria.domains.membro.enums.MembroFuncao;
import br.com.moria.shared.exceptions.ValidationException;
import br.com.moria.shared.utils.MessageUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

/**
 * Implementação do serviço de autorização de usuários.
 *
 * <p>Responsável por verificar permissões de acesso com base nas funções do usuário
 * autenticado no contexto de segurança.</p>
 */
@Service("authService")
public class AuthServiceImpl implements IAuthService {

    @Override
    public boolean hasPermission(AuthPermission permission) {
        MembroFuncao currentRole = getCurrentUserRole();
        if (currentRole == null)
            throw ForbiddenException.of("auth.role.not_found");
        return currentRole.getPermissions().contains(permission);
    }

    @Override
    public boolean hasPermission(String permission) {
        try {
            AuthPermission authPermission = AuthPermission.valueOf(permission);
            return hasPermission(authPermission);
        } catch (IllegalArgumentException e) {
            throw ValidationException.of("auth.permission.invalid",
                    Collections.singletonMap("permission", permission));
        }
    }

    @Override
    public boolean isCurrentUserOwner(int itemOwnerId) {
        int currentUserId = getCurrentUserId();
        return currentUserId == itemOwnerId;
    }

    @Override
    public boolean hasPermissionOrIsOwner(AuthPermission permission, int ownerId) {
        return hasPermission(permission) || isCurrentUserOwner(ownerId);
    }

    @Override
    public Set<AuthPermission> getCurrentUserPermissions() {
        MembroFuncao currentRole = getCurrentUserRole();
        if (currentRole == null)
            throw ForbiddenException.of("auth.role.not_found");
        return currentRole.getPermissions();
    }

    /**
     * Obtém o ID do usuário autenticado.
     *
     * @return o ID do usuário autenticado.
     * @throws UnauthorizedException se o usuário não estiver autenticado.
     */
    private int getCurrentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof MembroDetails)
            return ((MembroDetails) principal).getId();

        throw UnauthorizedException.of("auth.user.not_authenticated");
    }

    /**
     * Obtém a função atual do usuário autenticado com base nas autoridades atribuídas no contexto de segurança.
     *
     * <p>A primeira autoridade válida encontrada será convertida para um enum {@link MembroFuncao}.</p>
     *
     * @return a função atual do usuário, ou {@code null} se nenhuma função válida for encontrada.
     * @throws ForbiddenException se nenhuma função válida for encontrada.
     */
    private MembroFuncao getCurrentUserRole() {
        return SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(this::extractRoleName)
                .map(this::safeGetMembroFuncao)
                .findFirst()
                .orElseThrow(() -> ForbiddenException.of("auth.role.not_found"));
    }

    /**
     * Extrai o nome da função de uma autoridade, removendo o prefixo "ROLE_" se presente.
     *
     * @param authority a autoridade a ser processada.
     * @return o nome da função sem o prefixo.
     */
    private String extractRoleName(@NotNull String authority) {
        return authority.startsWith("ROLE_") ? authority.substring(5) : authority;
    }

    /**
     * Converte o nome da função em um enum {@link MembroFuncao}, tratando valores inválidos.
     *
     * @param authority o nome da função a ser convertido.
     * @return o enum {@link MembroFuncao} correspondente.
     * @throws ValidationException se o nome da função for inválido.
     */
    private MembroFuncao safeGetMembroFuncao(String authority) {
        try {
            return MembroFuncao.valueOf(authority);
        } catch (IllegalArgumentException e) {
            throw ValidationException.of("auth.role.invalid",
                    Collections.singletonMap("role", authority));
        }
    }
}
