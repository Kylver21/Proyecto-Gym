package com.utp.ProyectoGYM.controller;

import com.utp.ProyectoGYM.dto.PagoDTO;
import com.utp.ProyectoGYM.services.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pagos")
public class PagoRestController {

    @Autowired
    private PagoService pagoService;

    @GetMapping
    public List<PagoDTO> listarPagos() {
        return pagoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDTO> obtenerPagoPorId(@PathVariable Long id) {
        Optional<PagoDTO> pago = pagoService.obtenerPorId(id);
        return pago.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PagoDTO> crearPago(@RequestBody PagoDTO pagoDTO) {
        PagoDTO nuevoPago = pagoService.guardar(pagoDTO);
        return ResponseEntity.status(201).body(nuevoPago);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoDTO> actualizarPago(@PathVariable Long id, @RequestBody PagoDTO pagoDTO) {
        if (!pagoService.obtenerPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        pagoDTO.setId(id);
        PagoDTO actualizado = pagoService.guardar(pagoDTO);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Long id) {
        if (!pagoService.obtenerPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        pagoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}