package com.utp.ProyectoGYM.controller;

import com.utp.ProyectoGYM.dto.UsuarioDTO;
import com.utp.ProyectoGYM.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174", "http://localhost:5175"})
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<UsuarioDTO> usuario = usuarioService.obtenerPorId(id);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO nuevoUsuario = usuarioService.guardar(usuarioDTO);
        return ResponseEntity.status(201).body(nuevoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        if (!usuarioService.obtenerPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        usuarioDTO.setId(id);
        UsuarioDTO actualizado = usuarioService.guardar(usuarioDTO);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        if (!usuarioService.obtenerPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}