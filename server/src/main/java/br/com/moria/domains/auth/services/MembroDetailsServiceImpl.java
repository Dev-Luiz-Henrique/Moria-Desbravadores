package br.com.moria.domains.auth.services;

import java.util.ArrayList;
import java.util.List;

import br.com.moria.domains.auth.MembroDetails;
import br.com.moria.shared.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.moria.domains.membro.Membro;
import br.com.moria.domains.membro.MembroRepository;

/**
 * Implementação do serviço de detalhes do membro para autenticação.
 *
 * <p>Carrega os detalhes de um membro com base no e-mail, fornecendo as autoridades
 * necessárias para o Spring Security.</p>
 */
@Service
public class MembroDetailsServiceImpl implements IMembroDetailsService {

    private final MembroRepository membroRepository;

    /**
     * Construtor para injeção de dependências.
     *
     * @param membroRepository o repositório para operações com a entidade {@link Membro}.
     */
    @Autowired
    public MembroDetailsServiceImpl(MembroRepository membroRepository) {
        this.membroRepository = membroRepository;
    }

    /**
     * Carrega o membro pelo e-mail fornecido, criando um {@link UserDetails} para autenticação.
     *
     * @param email o e-mail do membro.
     * @return os detalhes do membro, incluindo sua função.
     * @throws UsernameNotFoundException se o membro não for encontrado.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Membro membro = membroRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        MessageUtil.getMessage("auth.user.not.found", email))
                );

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + membro.getFuncao().name()));

        return new MembroDetails(
                membro.getId(),
                membro.getEmail(),
                membro.getSenha(),
                authorities
        );
    }
}
