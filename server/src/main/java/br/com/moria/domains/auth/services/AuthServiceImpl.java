package br.com.moria.domains.auth.services;

import br.com.moria.domains.auth.enums.AuthPermission;
import br.com.moria.domains.membro.enums.MembroFuncao;
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
        return currentRole != null && currentRole.getPermissions().contains(permission);
    }

    @Override
    public boolean hasPermission(String permission) {
        try {
            AuthPermission authPermission = AuthPermission.valueOf(permission);
            return hasPermission(authPermission);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public Set<AuthPermission> getCurrentUserPermissions() {
        MembroFuncao currentRole = getCurrentUserRole();
        return currentRole != null ? currentRole.getPermissions() : Collections.emptySet();
    }

    /**
     * Obtém a função atual do usuário autenticado com base nas autoridades atribuídas no contexto de segurança.
     *
     * <p>A primeira autoridade válida encontrada será convertida para um enum {@link MembroFuncao}.</p>
     *
     * @return a função atual do usuário, ou {@code null} se nenhuma função válida for encontrada.
     */
    private MembroFuncao getCurrentUserRole() {
        return SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(this::extractRoleName)
                .map(this::safeGetMembroFuncao)
                .findFirst()
                .orElse(null);
    }

    /**
     * Extrai o nome da função de uma autoridade, removendo o prefixo "ROLE_" se presente.
     *
     * @param authority a autoridade a ser processada.
     * @return o nome da função sem o prefixo.
     */
    private String extractRoleName(@NotNull String authority) {
        if (authority.startsWith("ROLE_")) return authority.substring(5);
        return authority;
    }

    /**
     * Converte o nome da função em um enum {@link MembroFuncao}, tratando valores inválidos.
     *
     *
     * @param authority o nome da função a ser convertido.
     * @return o enum {@link MembroFuncao} correspondente, ou {@code null} se inválido.
     */
    private MembroFuncao safeGetMembroFuncao(String authority) {
        try {
            return MembroFuncao.valueOf(authority);
        } catch (IllegalArgumentException e) {
            // TODO Realizar Log e tratar erro
            return null;
        }
    }
}
