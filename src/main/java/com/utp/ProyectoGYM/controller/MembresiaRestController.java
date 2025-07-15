package com.utp.ProyectoGYM.controller;

import com.utp.ProyectoGYM.dto.MembresiaDTO;
import com.utp.ProyectoGYM.services.MembresiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/membresias")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174", "http://localhost:5175"})
public class MembresiaRestController {

    @Autowired
    private MembresiaService membresiaService;

    @GetMapping
    public List<MembresiaDTO> listarMembresias() {
        return membresiaService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembresiaDTO> obtenerMembresiaPorId(@PathVariable Long id) {
        Optional<MembresiaDTO> membresia = membresiaService.obtenerPorId(id);
        return membresia.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MembresiaDTO> crearMembresia(@RequestBody MembresiaDTO membresiaDTO) {
        MembresiaDTO nuevaMembresia = membresiaService.guardar(membresiaDTO);
        return ResponseEntity.status(201).body(nuevaMembresia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MembresiaDTO> actualizarMembresia(@PathVariable Long id, @RequestBody MembresiaDTO membresiaDTO) {
        if (!membresiaService.obtenerPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        membresiaDTO.setId(id);
        MembresiaDTO actualizado = membresiaService.guardar(membresiaDTO);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMembresia(@PathVariable Long id) {
        if (!membresiaService.obtenerPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        membresiaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}