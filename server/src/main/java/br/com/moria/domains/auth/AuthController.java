package br.com.moria.domains.auth;

import br.com.moria.domains.auth.dtos.AuthRequestDTO;
import br.com.moria.domains.auth.dtos.AuthResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AuthRequestDTO authRequestDTO) throws Exception {
        try {
            // Authenticate the user using the UserDetailsService implementation (MembroDetailsService).
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.getEmail(), authRequestDTO.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            final String jwt = jwtUtil.generateToken(userDetails);

            AuthResponseDTO response = new AuthResponseDTO();
            response.setToken(jwt);
            response.setEmail(userDetails.getUsername());
            response.setRole(userDetails.getAuthorities().stream()
                    .findFirst().map(GrantedAuthority::getAuthority).orElse("ROLE_NULL"));
            response.setExpiresAt(jwtUtil.extractExpiration(jwt));
            if (userDetails instanceof MembroDetails) response.setUserId(((MembroDetails) userDetails).getId()); // TODO SEGURO?

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new Exception("Usuário ou senha inválidos", e);
        }
    }
}
