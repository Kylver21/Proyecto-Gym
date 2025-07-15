package com.utp.ProyectoGYM.controller;

import com.utp.ProyectoGYM.dto.ReporteDTO;
import com.utp.ProyectoGYM.services.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174", "http://localhost:5175"})
public class ReporteRestController {
    
    @Autowired
    private ReporteService reporteService;
    
    @GetMapping
    public List<ReporteDTO> listarReportes() {
        return reporteService.obtenerTodos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ReporteDTO> obtenerReportePorId(@PathVariable Long id) {
        Optional<ReporteDTO> reporte = reporteService.obtenerPorId(id);
        return reporte.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/tipo/{tipo}")
    public List<ReporteDTO> obtenerReportesPorTipo(@PathVariable String tipo) {
        return reporteService.obtenerPorTipo(tipo);
    }
    
    @GetMapping("/usuario/{usuarioId}")
    public List<ReporteDTO> obtenerReportesPorUsuario(@PathVariable Long usuarioId) {
        return reporteService.obtenerPorUsuario(usuarioId);
    }
    
    @GetMapping("/estado/{estado}")
    public List<ReporteDTO> obtenerReportesPorEstado(@PathVariable String estado) {
        return reporteService.obtenerPorEstado(estado);
    }
    
    @GetMapping("/tipos")
    public List<String> obtenerTiposDisponibles() {
        return reporteService.obtenerTiposDisponibles();
    }
    
    @PostMapping
    public ResponseEntity<ReporteDTO> crearReporte(@RequestBody ReporteDTO reporteDTO) {
        ReporteDTO nuevoReporte = reporteService.crearReporte(reporteDTO);
        return ResponseEntity.status(201).body(nuevoReporte);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ReporteDTO> actualizarReporte(@PathVariable Long id, @RequestBody ReporteDTO reporteDTO) {
        if (!reporteService.obtenerPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        reporteDTO.setId(id);
        ReporteDTO actualizado = reporteService.actualizarReporte(reporteDTO);
        return ResponseEntity.ok(actualizado);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReporte(@PathVariable Long id) {
        if (!reporteService.obtenerPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        reporteService.eliminarReporte(id);
        return ResponseEntity.noContent().build();
    }
    
    // Endpoints específicos para generar reportes
    @PostMapping("/generar/ventas")
    public ResponseEntity<ReporteDTO> generarReporteVentas(
            @RequestParam LocalDate fechaInicio,
            @RequestParam LocalDate fechaFin,
            @RequestParam Long usuarioId) {
        ReporteDTO reporte = reporteService.generarReporteVentas(fechaInicio, fechaFin, usuarioId);
        return ResponseEntity.status(201).body(reporte);
    }
    
    @PostMapping("/generar/membresias")
    public ResponseEntity<ReporteDTO> generarReporteMembresias(@RequestParam Long usuarioId) {
        ReporteDTO reporte = reporteService.generarReporteMembresias(usuarioId);
        return ResponseEntity.status(201).body(reporte);
    }
    
    @PostMapping("/generar/usuarios")
    public ResponseEntity<ReporteDTO> generarReporteUsuarios(@RequestParam Long usuarioId) {
        ReporteDTO reporte = reporteService.generarReporteUsuarios(usuarioId);
        return ResponseEntity.status(201).body(reporte);
    }
    
    @PostMapping("/generar/productos")
    public ResponseEntity<ReporteDTO> generarReporteProductos(@RequestParam Long usuarioId) {
        ReporteDTO reporte = reporteService.generarReporteProductos(usuarioId);
        return ResponseEntity.status(201).body(reporte);
    }
    
    @PostMapping("/generar/pagos")
    public ResponseEntity<ReporteDTO> generarReportePagos(
            @RequestParam LocalDate fechaInicio,
            @RequestParam LocalDate fechaFin,
            @RequestParam Long usuarioId) {
        ReporteDTO reporte = reporteService.generarReportePagos(fechaInicio, fechaFin, usuarioId);
        return ResponseEntity.status(201).body(reporte);
    }
}
