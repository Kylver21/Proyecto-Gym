package com.utp.ProyectoGYM.controller;

import com.utp.ProyectoGYM.dto.RegistroMembresiaDTO;
import com.utp.ProyectoGYM.services.RegistroMembresiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/registro-membresias")
public class RegistroMembresiaRestController {

    @Autowired
    private RegistroMembresiaService registroMembresiaService;

    @GetMapping
    public List<RegistroMembresiaDTO> listar() {
        return registroMembresiaService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroMembresiaDTO> obtenerPorId(@PathVariable Long id) {
        Optional<RegistroMembresiaDTO> registro = registroMembresiaService.obtenerPorId(id);
        return registro.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RegistroMembresiaDTO> crear(@RequestBody RegistroMembresiaDTO dto) {
        RegistroMembresiaDTO nuevo = registroMembresiaService.guardar(dto);
        return ResponseEntity.status(201).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroMembresiaDTO> actualizar(@PathVariable Long id, @RequestBody RegistroMembresiaDTO dto) {
        if (!registroMembresiaService.obtenerPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        dto.setId(id);
        RegistroMembresiaDTO actualizado = registroMembresiaService.guardar(dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!registroMembresiaService.obtenerPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        registroMembresiaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}