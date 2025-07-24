package com.utp.ProyectoGYM.controller;

import com.utp.ProyectoGYM.dto.LoginRequest;
import com.utp.ProyectoGYM.dto.RegisterRequest;
import com.utp.ProyectoGYM.dto.LoginResponse;
import com.utp.ProyectoGYM.dto.AuthCheckResponse;
import com.utp.ProyectoGYM.modelo.Usuario;
import com.utp.ProyectoGYM.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(
    origins = {"http://localhost:5173", "http://localhost:5174", "http://localhost:3000", "http://localhost:3001"}, 
    allowCredentials = "true",
    methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}
)
public class AuthController {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody RegisterRequest request) {
        try {
            // Verificar si el username ya existe
            if (usuarioRepositorio.findByUsername(request.getUsername()).isPresent()) {
                LoginResponse response = new LoginResponse();
                response.setSuccess(false);
                response.setMessage("El nombre de usuario ya está en uso");
                return ResponseEntity.badRequest().body(response);
            }

            // Verificar si el email ya existe
            if (usuarioRepositorio.findByEmail(request.getEmail()).isPresent()) {
                LoginResponse response = new LoginResponse();
                response.setSuccess(false);
                response.setMessage("El email ya está registrado");
                return ResponseEntity.badRequest().body(response);
            }

            // Crear nuevo usuario
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setUsername(request.getUsername());
            nuevoUsuario.setPassword(passwordEncoder.encode(request.getPassword()));
            nuevoUsuario.setNombre(request.getNombre());
            nuevoUsuario.setApellido(request.getApellido());
            nuevoUsuario.setEmail(request.getEmail());
            
            // Validar y establecer rol correctamente
            String rolNormalizado = validateAndNormalizeRole(request.getRol());
            nuevoUsuario.setRol(rolNormalizado);
            nuevoUsuario.setEstado(true);
            
            // Guardar usuario
            Usuario usuarioGuardado = usuarioRepositorio.save(nuevoUsuario);
            
            // No enviar la contraseña en la respuesta
            usuarioGuardado.setPassword(null);
            
            LoginResponse response = new LoginResponse();
            response.setSuccess(true);
            response.setMessage("Usuario registrado exitosamente");
            response.setUser(usuarioGuardado);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            System.err.println("❌ Error en register: " + e.getMessage());
            e.printStackTrace();
            LoginResponse response = new LoginResponse();
            response.setSuccess(false);
            response.setMessage("Error interno del servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request, HttpSession session) {
        try {
            Optional<Usuario> usuarioOpt = usuarioRepositorio.findByUsername(request.getUsername());
            
            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();
                
                // Verificar si el usuario está activo
                if (!usuario.isEstado()) {
                    System.out.println("❌ Usuario inactivo: " + request.getUsername());
                    LoginResponse response = new LoginResponse();
                    response.setSuccess(false);
                    response.setMessage("Usuario inactivo");
                    return ResponseEntity.badRequest().body(response);
                }
                
                // Verificar la contraseña
                if (passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
                    // Normalizar el rol antes de guardarlo en sesión
                    String rolNormalizado = validateAndNormalizeRole(usuario.getRol());
                    usuario.setRol(rolNormalizado);
                    
                    // Configurar la sesión
                    session.setAttribute("userId", usuario.getId());
                    session.setAttribute("username", usuario.getUsername());
                    session.setAttribute("rol", rolNormalizado);
                    session.setAttribute("authenticated", true);
                    
                    // Configurar duración de sesión (30 minutos)
                    session.setMaxInactiveInterval(30 * 60);
                    
                    // No enviar la contraseña en la respuesta
                    usuario.setPassword(null);
                    
                    LoginResponse response = new LoginResponse();
                    response.setSuccess(true);
                    response.setMessage("Login exitoso");
                    response.setUser(usuario);
                    
                    return ResponseEntity.ok(response);
                }
            }
            
            System.out.println("❌ Credenciales inválidas para: " + request.getUsername());
            LoginResponse response = new LoginResponse();
            response.setSuccess(false);
            response.setMessage("Credenciales inválidas");
            return ResponseEntity.badRequest().body(response);
                
        } catch (Exception e) {
            System.err.println("❌ Error en login: " + e.getMessage());
            e.printStackTrace();
            LoginResponse response = new LoginResponse();
            response.setSuccess(false);
            response.setMessage("Error interno del servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<LoginResponse> logout(HttpSession session) {
        session.invalidate();
        LoginResponse response = new LoginResponse();
        response.setSuccess(true);
        response.setMessage("Sesión cerrada exitosamente");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/check")
    public ResponseEntity<AuthCheckResponse> checkAuth(HttpSession session) {
        Boolean authenticated = (Boolean) session.getAttribute("authenticated");
        AuthCheckResponse response = new AuthCheckResponse();
        
        if (authenticated != null && authenticated) {
            Long userId = (Long) session.getAttribute("userId");
            
            // Buscar el usuario completo para la respuesta
            Optional<Usuario> usuarioOpt = usuarioRepositorio.findById(userId);
            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();
                
                // Asegurar que el rol esté normalizado
                String rolNormalizado = validateAndNormalizeRole(usuario.getRol());
                usuario.setRol(rolNormalizado);
                
                usuario.setPassword(null); // No enviar la contraseña
                
                response.setAuthenticated(true);
                response.setUser(usuario);
                return ResponseEntity.ok(response);
            }
        }
        
        response.setAuthenticated(false);
        response.setUser(null);
        return ResponseEntity.ok(response);
    }

    // Método para validar y normalizar roles
    private String validateAndNormalizeRole(String inputRole) {
        if (inputRole == null || inputRole.trim().isEmpty()) {
            return "CLIENTE";
        }
        
        // Normalizar el rol (quitar ROLE_ si existe y convertir a mayúsculas)
        String normalizedRole = inputRole.replace("ROLE_", "").toUpperCase().trim();
        
        // Validar que sea un rol válido
        switch (normalizedRole) {
            case "ADMIN":
            case "EMPLEADO":
            case "CLIENTE":
                return normalizedRole;
            default:
                System.out.println("❌ Rol inválido '" + normalizedRole + "', usando CLIENTE por defecto");
                return "CLIENTE";
        }
    }
}