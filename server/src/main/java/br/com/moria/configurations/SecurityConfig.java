package br.com.moria.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.moria.enums.TipoMembro;
import br.com.moria.services.interfaces.IJwtService;
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
    public JwtRequestFilter jwtRequestFilter() {
        return jwtRequestFilter;
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        configureInscricaoRoutes(http);
        configureRecursosRoutes(http);
        configureMensalidadesRoutes(http);
        configureEventosRoutes(http);
        configureMembrosRoutes(http);
        configureOthersRoutes(http);

        http
            //.authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
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
            .csrf(csrf -> csrf.disable());

        http.addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
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
    


    private void configureMensalidadesRoutes(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.GET, "/mensalidades/**").hasAnyAuthority(TipoMembro.VOLUNTARIOS)
            .requestMatchers("/mensalidades/**").hasAuthority(TipoMembro.TESOUREIRO.name())
        );
    }

    private void configureEventosRoutes(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.POST, "/membros/{id}/imagem").hasAuthority(TipoMembro.SECRETARIO.name())
            .requestMatchers(HttpMethod.GET, "/membros/{id}/imagem").permitAll()
            .requestMatchers(HttpMethod.GET, "/eventos/**").permitAll()
            .requestMatchers("/eventos/**").hasAuthority(TipoMembro.SECRETARIO.name())
        );
    }

    private void configureMembrosRoutes(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.GET, "/membros/email").authenticated()
            .requestMatchers(HttpMethod.POST, "/membros/{id}/ficha-saude").hasAuthority(TipoMembro.SECRETARIO.name())
            .requestMatchers(HttpMethod.GET, "/membros/{id}/ficha-saude").hasAnyAuthority(TipoMembro.VOLUNTARIOS)
            .requestMatchers(HttpMethod.GET, "/membros/{id}").authenticated()
            .requestMatchers(HttpMethod.GET, "/membros/**").hasAnyAuthority(TipoMembro.VOLUNTARIOS)
            .requestMatchers("/membros/**").hasAnyAuthority(TipoMembro.SECRETARIO.name())
        );
    }

    private void configureInscricaoRoutes(HttpSecurity http) throws Exception {
    	http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.PUT, "/inscricoes/confirmar").authenticated()
            .requestMatchers(HttpMethod.GET, "/inscricoes/{id}").hasAnyAuthority(TipoMembro.VOLUNTARIOS)
            .requestMatchers(HttpMethod.GET, "/inscricoes/**").hasAnyAuthority(TipoMembro.VOLUNTARIOS)
            .requestMatchers("/inscricoes/**").hasAuthority(TipoMembro.SECRETARIO.name())
        );
    }

    private void configureRecursosRoutes(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.GET, "/recursos/{id}").hasAnyAuthority(TipoMembro.VOLUNTARIOS)
            .requestMatchers(HttpMethod.GET, "/recursos/**").hasAnyAuthority(TipoMembro.VOLUNTARIOS)
            .requestMatchers("/recursos/**").hasAnyAuthority(TipoMembro.SECRETARIO.name(), TipoMembro.TESOUREIRO.name())
        );
    }

    private void configureOthersRoutes(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/login").permitAll()
            .requestMatchers("/**").hasAnyRole(TipoMembro.DIRETOR_CLUBE.name(), TipoMembro.DIRETOR_ASSOCIADO.name())
            .anyRequest().authenticated()
        );
    }
}