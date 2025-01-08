package br.com.moria.domains.auth;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Interface de serviço para autenticação e carregamento de detalhes do membro.
 *
 * <p>Extende a interface {@link UserDetailsService} do Spring Security para
 * fornecer funcionalidades específicas para o gerenciamento de autenticação
 * e autorização de membros.</p>
 */
public interface IMembroDetailsService extends UserDetailsService {

}
