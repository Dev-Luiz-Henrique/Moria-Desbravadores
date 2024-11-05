package br.com.moria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.moria.configurations.JwtUtil;
import br.com.moria.services.implementations.MembroDetailsServiceImpl;
import lombok.Getter;
import lombok.Setter;

@RestController
@CrossOrigin(origins = "https://proud-wave-0042c520f.5.azurestaticapps.net")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MembroDetailsServiceImpl membroDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Usuário ou senha inválidos", e);
        }

        final UserDetails userDetails = membroDetailsService.loadUserByUsername(authRequest.getEmail());

        final String jwt = jwtUtil.generateToken(userDetails);

        return jwt;
    }
}

@Getter
@Setter
class AuthRequest {
    private String email;
    private String password;
}