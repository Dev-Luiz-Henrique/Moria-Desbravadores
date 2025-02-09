package br.com.moria.domains.auth;

import br.com.moria.domains.auth.dtos.AuthRequestDTO;
import br.com.moria.domains.auth.dtos.AuthResponseDTO;
import br.com.moria.shared.exceptions.InternalServerErrorException;
import br.com.moria.shared.exceptions.ValidationException;
import br.com.moria.shared.utils.MessageUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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

            if (!(userDetails instanceof MembroDetails))
                throw InternalServerErrorException.of("auth.user.details.invalid");

            AuthResponseDTO response = new AuthResponseDTO();
            response.setToken(jwt);
            response.setEmail(userDetails.getUsername());
            response.setRole(userDetails.getAuthorities().stream()
                    .findFirst().map(GrantedAuthority::getAuthority).orElse("ROLE_NULL"));
            response.setExpiresAt(jwtUtil.extractExpiration(jwt));
            response.setUserId(((MembroDetails) userDetails).getId());

            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            throw ValidationException.of("auth.credentials.invalid");
        } catch (RuntimeException e) {
            throw InternalServerErrorException.of("auth.unexpected.error");
        }
    }
}
