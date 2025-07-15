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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UsuarioRepositorio usuarioRepo;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests(auth -> auth
                // Acceso libre a login, registro, Swagger y H2 Console
                .requestMatchers(
                    "/api/auth/**",
                    "/v3/api-docs/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/webjars/**",
                    "/h2-console/**"
                ).permitAll()
                // Acceso libre a todas las APIs para desarrollo - cambiar en producción
                .requestMatchers("/api/**").permitAll()
                // Solo ADMIN puede gestionar usuarios (comentado para desarrollo)
                // .requestMatchers("/api/usuarios/**").hasRole("ADMIN")
                // ADMIN y EMPLEADO pueden gestionar membresías y pagos (comentado para desarrollo)
                // .requestMatchers("/api/membresias/**", "/api/pagos/**").hasAnyRole("ADMIN", "EMPLEADO")
                .anyRequest().authenticated()
            )
            // ❌ REMOVIDO: .httpBasic() - Esto causaba el popup
            // ✅ AGREGADO: Configuración de sesiones para SPA
            .sessionManagement(session -> 
                session
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .maximumSessions(1)
                    .maxSessionsPreventsLogin(false)
            )
            // ✅ MEJORADO: Headers de seguridad sin WWW-Authenticate
            .headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin())
                .xssProtection(xss -> xss.headerValue(XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK))
                .contentSecurityPolicy(csp -> csp.policyDirectives("default-src 'self'"))
                .httpStrictTransportSecurity(hstsConfig -> hstsConfig.disable()) // Opcional para desarrollo
            )
            // ✅ AGREGADO: Manejo personalizado de errores de autenticación
            .exceptionHandling(exceptions -> exceptions
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setStatus(401);
                    response.setContentType("application/json");
                    response.getWriter().write("{\"error\":\"No autorizado\",\"message\":\"Debe iniciar sesión\"}");
                })
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.setStatus(403);
                    response.setContentType("application/json");
                    response.getWriter().write("{\"error\":\"Acceso denegado\",\"message\":\"No tiene permisos suficientes\"}");
                })
            );
        
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(
            "http://localhost:5173",
            "http://localhost:5174",
            "http://127.0.0.1:5173",
            "http://127.0.0.1:5174"
        ));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Usuario usuario = usuarioRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
            
            // Los roles en la BD son: ADMIN, EMPLEADO, CLIENTE (sin ROLE_)
            String rol = "USER";
            if (usuario.getRol() != null && !usuario.getRol().isEmpty()) {
                rol = usuario.getRol();
            }
                
            return User.builder()
                    .username(usuario.getUsername())
                    .password(usuario.getPassword()) // Ya está hasheado con BCrypt
                    .roles(rol)
                    .build();
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}