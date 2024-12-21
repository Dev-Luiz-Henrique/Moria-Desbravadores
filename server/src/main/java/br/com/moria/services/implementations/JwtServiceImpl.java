package br.com.moria.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import br.com.moria.services.interfaces.IJwtService;
import br.com.moria.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtServiceImpl implements IJwtService {

    private final UserDetailsService membroDetailsService;
    private final JwtUtil jwtUtil;

    @Autowired
    public JwtServiceImpl(UserDetailsService membroDetailsService, JwtUtil jwtUtil) {
        this.membroDetailsService = membroDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void validateAndAuthenticate(String jwt, HttpServletRequest request) {
        String username = jwtUtil.extractUsername(jwt);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = membroDetailsService.loadUserByUsername(username);

            if (!jwtUtil.validateToken(jwt, userDetails))
                throw new RuntimeException("Token inválido ou expirado."); // TODO Exceção personalizada

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }
}
