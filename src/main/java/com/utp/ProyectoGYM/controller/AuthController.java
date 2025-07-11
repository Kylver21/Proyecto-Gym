package com.utp.ProyectoGYM.controller;

import com.utp.ProyectoGYM.dto.LoginRequest;
import com.utp.ProyectoGYM.dto.LoginResponse;
import com.utp.ProyectoGYM.modelo.Usuario;
import com.utp.ProyectoGYM.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        Optional<Usuario> usuarioOpt = usuarioRepositorio.findByUsername(loginRequest.getUsername());
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            // Verificar la contraseña (usando {noop} para deshabilitar temporalmente el encriptado)
            if (passwordEncoder.matches(loginRequest.getPassword(), usuario.getPassword())) {
                // Configurar la sesión
                session.setAttribute("userId", usuario.getId());
                session.setAttribute("username", usuario.getUsername());
                session.setAttribute("rol", usuario.getRol());
                session.setAttribute("authenticated", true);
                
                return ResponseEntity.ok(new LoginResponse(
                    true, 
                    "Inicio de sesión exitoso", 
                    usuario.getUsername(), 
                    usuario.getRol()
                ));
            }
        }
        return ResponseEntity.badRequest()
            .body(new LoginResponse(false, "Credenciales inválidas", null, null));
    }

    @PostMapping("/logout")
    public ResponseEntity<LoginResponse> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(new LoginResponse(true, "Sesión cerrada exitosamente", null, null));
    }

    @GetMapping("/check")
    public ResponseEntity<LoginResponse> checkAuth(HttpSession session) {
        Boolean authenticated = (Boolean) session.getAttribute("authenticated");
        if (authenticated != null && authenticated) {
            String username = (String) session.getAttribute("username");
            String rol = (String) session.getAttribute("rol");
            return ResponseEntity.ok(new LoginResponse(
                true, 
                "Usuario autenticado", 
                username, 
                rol
            ));
        }
        return ResponseEntity.ok(new LoginResponse(false, "No autenticado", null, null));
    }
}