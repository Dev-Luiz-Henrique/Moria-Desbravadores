package br.com.moria.configurations.security;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.moria.domains.membro.enums.MembroFuncao;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;

    @Autowired
    public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(@NotNull HttpSecurity http) throws Exception {
        //configureInscricaoRoutes(http);
        //configureRecursosRoutes(http);
        //configureMensalidadesRoutes(http);
        //configureEventosRoutes(http);
        //configureMembrosRoutes(http);
        //configureOthersRoutes(http);

        http
            .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
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
            .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .csrf(AbstractHttpConfigurer::disable);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            @NotNull AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    private void configureMensalidadesRoutes(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.GET, "/mensalidades/**").hasAnyAuthority(MembroFuncao.VOLUNTARIOS)
            .requestMatchers("/mensalidades/**").hasAuthority(MembroFuncao.TESOUREIRO.name())
        );
    }

    private void configureEventosRoutes(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.POST, "/membros/{id}/imagem").hasAuthority(MembroFuncao.SECRETARIO.name())
            .requestMatchers(HttpMethod.GET, "/membros/{id}/imagem").permitAll()
            .requestMatchers(HttpMethod.GET, "/eventos/**").permitAll()
            .requestMatchers("/eventos/**").hasAuthority(MembroFuncao.SECRETARIO.name())
        );
    }

    private void configureMembrosRoutes(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.GET, "/membros/email").authenticated()
            .requestMatchers(HttpMethod.POST, "/membros/{id}/ficha-saude").hasAuthority(MembroFuncao.SECRETARIO.name())
            .requestMatchers(HttpMethod.GET, "/membros/{id}/ficha-saude").hasAnyAuthority(MembroFuncao.VOLUNTARIOS)
            .requestMatchers(HttpMethod.GET, "/membros/{id}").authenticated()
            .requestMatchers(HttpMethod.GET, "/membros/**").hasAnyAuthority(MembroFuncao.VOLUNTARIOS)
            .requestMatchers("/membros/**").hasAnyAuthority(MembroFuncao.SECRETARIO.name())
        );
    }

    private void configureInscricaoRoutes(HttpSecurity http) throws Exception {
    	http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.PUT, "/inscricoes/confirmar").authenticated()
            .requestMatchers(HttpMethod.GET, "/inscricoes/{id}").hasAnyAuthority(MembroFuncao.VOLUNTARIOS)
            .requestMatchers(HttpMethod.GET, "/inscricoes/**").hasAnyAuthority(MembroFuncao.VOLUNTARIOS)
            .requestMatchers("/inscricoes/**").hasAuthority(MembroFuncao.SECRETARIO.name())
        );
    }

    private void configureRecursosRoutes(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.GET, "/recursos/{id}").hasAnyAuthority(MembroFuncao.VOLUNTARIOS)
            .requestMatchers(HttpMethod.GET, "/recursos/**").hasAnyAuthority(MembroFuncao.VOLUNTARIOS)
            .requestMatchers("/recursos/**").hasAnyAuthority(MembroFuncao.SECRETARIO.name(), MembroFuncao.TESOUREIRO.name())
        );
    }

    private void configureOthersRoutes(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/login").permitAll()
            .requestMatchers("/**").hasAnyRole(MembroFuncao.DIRETOR_CLUBE.name(), MembroFuncao.DIRETOR_ASSOCIADO.name())
            .anyRequest().authenticated()
        );
    }
}