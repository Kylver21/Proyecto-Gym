package com.utp.ProyectoGYM.controller;

import com.utp.ProyectoGYM.dto.LoginDTO;
import com.utp.ProyectoGYM.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/logins")
public class LoginRestController {

    @Autowired
    private LoginService loginService;

    @GetMapping
    public List<LoginDTO> listarLogins() {
        return loginService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoginDTO> obtenerLoginPorId(@PathVariable Long id) {
        Optional<LoginDTO> login = loginService.obtenerPorId(id);
        return login.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LoginDTO> crearLogin(@RequestBody LoginDTO loginDTO) {
        LoginDTO nuevoLogin = loginService.guardar(loginDTO);
        return ResponseEntity.status(201).body(nuevoLogin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoginDTO> actualizarLogin(@PathVariable Long id, @RequestBody LoginDTO loginDTO) {
        if (!loginService.obtenerPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        loginDTO.setId(id);
        LoginDTO actualizado = loginService.guardar(loginDTO);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLogin(@PathVariable Long id) {
        if (!loginService.obtenerPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        loginService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}