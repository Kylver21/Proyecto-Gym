package com.utp.ProyectoGYM.config;

import com.utp.ProyectoGYM.modelo.Usuario;
import com.utp.ProyectoGYM.repositorio.UsuarioRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UsuarioRepositorio usuarioRepo;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Acceso libre a Swagger, login, registro y recursos estáticos
                .requestMatchers(
                    "/swagger-ui/**", "/v3/api-docs/**", "/api/auth/**", "/login.html", "/registro.html", "/home.html",
                    "/static/**", "/css/**", "/js/**"
                ).permitAll()
                // Solo ADMIN puede acceder a gestión de usuarios
                .requestMatchers("/api/usuarios/**").hasRole("ADMIN")
                // Usuarios autenticados pueden ver otros recursos
                .anyRequest().authenticated()
            )
            .csrf(csrf -> csrf.disable())
            .httpBasic(httpBasic -> httpBasic.disable())
            .formLogin(form -> form.disable());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Usuario usuario = usuarioRepo.findAll().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
            // Suponiendo que tienes un campo 'rol' en Usuario o deberás adaptarlo
            String rol = "USER";
            return new org.springframework.security.core.userdetails.User(
                usuario.getUsername(),
                usuario.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + rol))
            );
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

