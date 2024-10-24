package br.com.moria.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/**").permitAll()
                /*.requestMatchers("/login").permitAll()
                .requestMatchers("/membros/**").hasAuthority(TipoMembro.SECRETARIO.name())
                .requestMatchers("/**").hasAnyRole(TipoMembro.DIRETOR_CLUBE.name(), TipoMembro.DIRETOR_ASSOCIADO.name())*/
                .anyRequest().authenticated()
            )
            .exceptionHandling(exceptionHandling ->
                exceptionHandling
                    .authenticationEntryPoint((request, response, authException) -> {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.getWriter().write("Acesso não autorizado: Token JWT inválido ou não fornecido.");
                    })
                    .accessDeniedHandler((request, response, accessDeniedException) -> {
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        response.getWriter().write("Você não tem permissão para acessar este recurso.");
                    })
            )
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // Define a politica de criacao de sessao como sem estado (STATELESS), comum em APIs RESTful.
            )
            .csrf(csrf -> csrf.disable());
            // Desabilita a proteção CSRF (Cross-Site Request Forgery).
            // Isso e geralmente seguro para APIs RESTful que nao usam sessoes baseadas em cookies.

        http.addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
        // Adiciona o filtro JWT (jwtRequestFilter()) antes do filtro padrao de autenticacao baseado em nome de usuário e senha
        // Isso garante que as requisicoes sejam processadas pelo filtro JWT antes da autenticacao padrao.

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JwtRequestFilter jwtRequestFilter() {
        return new JwtRequestFilter();
    }
}