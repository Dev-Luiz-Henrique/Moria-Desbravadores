package br.com.moria.domains.auth.services;

import br.com.moria.domains.auth.enums.AuthPermission;
import br.com.moria.domains.membro.enums.MembroFuncao;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Implementação do serviço de autorização.
 *
 * <p>Responsável por verificar as permissões do usuário autenticado.<p>
 */
@Service("authService")
public class AuthServiceImpl implements IAuthService {

    @Override
    public boolean hasPermission(AuthPermission permission) {
        MembroFuncao currentRole = getCurrentUserRole();
        return currentRole != null && currentRole.hasPermission(permission);
    }

    @Override
    public boolean hasPermission(String permission) {
        MembroFuncao currentRole = getCurrentUserRole();
        return currentRole != null && currentRole.hasPermission(permission);
    }

    @Override
    public MembroFuncao getCurrentUserRole() {
        return SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(authority -> authority.startsWith("ROLE_")
                        ? authority.substring(5) : authority)
                .map(MembroFuncao::valueOf)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Set<AuthPermission> getCurrentUserPermissions() {
        MembroFuncao currentRole = getCurrentUserRole();
        return currentRole != null ? currentRole.getPermissions() : null;
    }
}
