package br.com.moria.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.moria.models.Membro;
import br.com.moria.repositories.MembroRepository;
import br.com.moria.services.interfaces.IMembroDetailsService;

@Service
public class MembroDetailsServiceImpl implements IMembroDetailsService {

    @Autowired
    private MembroRepository membroRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Membro membro = membroRepository.findByEmail(email);
        if (membro == null)
            throw new UsernameNotFoundException("Usuário não encontrado com o email: " + email);

        // Adiciona a authority baseada no tipo de membro
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(membro.getTipo().name()));

        return new org.springframework.security.core.userdetails.User(
            membro.getEmail(), 
            membro.getSenha(), 
            authorities 
        );
    }
}