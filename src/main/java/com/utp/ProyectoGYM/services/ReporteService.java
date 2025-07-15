package com.utp.ProyectoGYM.services;

import com.utp.ProyectoGYM.dto.ReporteDTO;
import com.utp.ProyectoGYM.modelo.Reporte;
import com.utp.ProyectoGYM.modelo.Usuario;
import com.utp.ProyectoGYM.repositorio.ReporteRepositorio;
import com.utp.ProyectoGYM.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReporteService {
    
    @Autowired
    private ReporteRepositorio reporteRepositorio;
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    public List<ReporteDTO> obtenerTodos() {
        return reporteRepositorio.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public Optional<ReporteDTO> obtenerPorId(Long id) {
        return reporteRepositorio.findById(id)
                .map(this::convertirADTO);
    }
    
    public List<ReporteDTO> obtenerPorTipo(String tipo) {
        return reporteRepositorio.findByTipo(tipo).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<ReporteDTO> obtenerPorUsuario(Long usuarioId) {
        return reporteRepositorio.findByUsuarioCreadorId(usuarioId).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<ReporteDTO> obtenerPorEstado(String estado) {
        return reporteRepositorio.findByEstado(estado).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<String> obtenerTiposDisponibles() {
        return reporteRepositorio.findDistinctTipos();
    }
    
    public ReporteDTO crearReporte(ReporteDTO reporteDTO) {
        Reporte reporte = convertirAEntidad(reporteDTO);
        reporte.setFechaCreacion(LocalDateTime.now());
        reporte.setEstado("GENERADO");
        
        Reporte guardado = reporteRepositorio.save(reporte);
        return convertirADTO(guardado);
    }
    
    public ReporteDTO actualizarReporte(ReporteDTO reporteDTO) {
        Reporte reporte = convertirAEntidad(reporteDTO);
        Reporte guardado = reporteRepositorio.save(reporte);
        return convertirADTO(guardado);
    }
    
    public void eliminarReporte(Long id) {
        reporteRepositorio.deleteById(id);
    }
    
    public ReporteDTO generarReporteVentas(LocalDate fechaInicio, LocalDate fechaFin, Long usuarioId) {
        ReporteDTO reporteDTO = new ReporteDTO();
        reporteDTO.setTipo("VENTAS");
        reporteDTO.setNombre("Reporte de Ventas - " + fechaInicio + " a " + fechaFin);
        reporteDTO.setDescripcion("Reporte de ventas generado automáticamente");
        reporteDTO.setFechaInicio(fechaInicio);
        reporteDTO.setFechaFin(fechaFin);
        reporteDTO.setUsuarioCreadorId(usuarioId);
        
        // Aquí se implementaría la lógica para generar los datos del reporte
        String datosReporte = generarDatosVentas(fechaInicio, fechaFin);
        reporteDTO.setDatosReporte(datosReporte);
        
        return crearReporte(reporteDTO);
    }
    
    public ReporteDTO generarReporteMembresias(Long usuarioId) {
        ReporteDTO reporteDTO = new ReporteDTO();
        reporteDTO.setTipo("MEMBRESIAS");
        reporteDTO.setNombre("Reporte de Membresías - " + LocalDate.now());
        reporteDTO.setDescripcion("Reporte de estado de membresías");
        reporteDTO.setUsuarioCreadorId(usuarioId);
        
        String datosReporte = generarDatosMembresias();
        reporteDTO.setDatosReporte(datosReporte);
        
        return crearReporte(reporteDTO);
    }
    
    public ReporteDTO generarReporteUsuarios(Long usuarioId) {
        ReporteDTO reporteDTO = new ReporteDTO();
        reporteDTO.setTipo("USUARIOS");
        reporteDTO.setNombre("Reporte de Usuarios - " + LocalDate.now());
        reporteDTO.setDescripcion("Reporte de usuarios registrados");
        reporteDTO.setUsuarioCreadorId(usuarioId);
        
        String datosReporte = generarDatosUsuarios();
        reporteDTO.setDatosReporte(datosReporte);
        
        return crearReporte(reporteDTO);
    }
    
    public ReporteDTO generarReporteProductos(Long usuarioId) {
        ReporteDTO reporteDTO = new ReporteDTO();
        reporteDTO.setTipo("PRODUCTOS");
        reporteDTO.setNombre("Reporte de Inventario - " + LocalDate.now());
        reporteDTO.setDescripcion("Reporte de estado del inventario");
        reporteDTO.setUsuarioCreadorId(usuarioId);
        
        String datosReporte = generarDatosProductos();
        reporteDTO.setDatosReporte(datosReporte);
        
        return crearReporte(reporteDTO);
    }
    
    public ReporteDTO generarReportePagos(LocalDate fechaInicio, LocalDate fechaFin, Long usuarioId) {
        ReporteDTO reporteDTO = new ReporteDTO();
        reporteDTO.setTipo("PAGOS");
        reporteDTO.setNombre("Reporte de Pagos - " + fechaInicio + " a " + fechaFin);
        reporteDTO.setDescripcion("Reporte de pagos e ingresos");
        reporteDTO.setFechaInicio(fechaInicio);
        reporteDTO.setFechaFin(fechaFin);
        reporteDTO.setUsuarioCreadorId(usuarioId);
        
        String datosReporte = generarDatosPagos(fechaInicio, fechaFin);
        reporteDTO.setDatosReporte(datosReporte);
        
        return crearReporte(reporteDTO);
    }
    
    // Métodos privados para generar datos de reportes
    private String generarDatosVentas(LocalDate fechaInicio, LocalDate fechaFin) {
        // Implementar lógica para generar datos de ventas
        return "{\"total_ventas\": 0, \"ingresos\": 0, \"periodo\": \"" + fechaInicio + " - " + fechaFin + "\"}";
    }
    
    private String generarDatosMembresias() {
        // Implementar lógica para generar datos de membresías
        return "{\"total_membresias\": 0, \"activas\": 0, \"vencidas\": 0}";
    }
    
    private String generarDatosUsuarios() {
        // Implementar lógica para generar datos de usuarios
        return "{\"total_usuarios\": 0, \"activos\": 0, \"inactivos\": 0}";
    }
    
    private String generarDatosProductos() {
        // Implementar lógica para generar datos de productos
        return "{\"total_productos\": 0, \"bajo_stock\": 0, \"valor_inventario\": 0}";
    }
    
    private String generarDatosPagos(LocalDate fechaInicio, LocalDate fechaFin) {
        // Implementar lógica para generar datos de pagos
        return "{\"total_pagos\": 0, \"ingresos_totales\": 0, \"periodo\": \"" + fechaInicio + " - " + fechaFin + "\"}";
    }
    
    private ReporteDTO convertirADTO(Reporte reporte) {
        ReporteDTO dto = new ReporteDTO();
        dto.setId(reporte.getId());
        dto.setTipo(reporte.getTipo());
        dto.setNombre(reporte.getNombre());
        dto.setDescripcion(reporte.getDescripcion());
        dto.setFechaInicio(reporte.getFechaInicio());
        dto.setFechaFin(reporte.getFechaFin());
        dto.setParametros(reporte.getParametros());
        dto.setDatosReporte(reporte.getDatosReporte());
        dto.setFechaCreacion(reporte.getFechaCreacion());
        dto.setEstado(reporte.getEstado());
        dto.setArchivoUrl(reporte.getArchivoUrl());
        dto.setTotalRegistros(reporte.getTotalRegistros());
        
        if (reporte.getUsuarioCreador() != null) {
            dto.setUsuarioCreadorId(reporte.getUsuarioCreador().getId());
            dto.setNombreUsuarioCreador(reporte.getUsuarioCreador().getNombre() + " " + reporte.getUsuarioCreador().getApellido());
        }
        
        return dto;
    }
    
    private Reporte convertirAEntidad(ReporteDTO dto) {
        Reporte reporte = new Reporte();
        reporte.setId(dto.getId());
        reporte.setTipo(dto.getTipo());
        reporte.setNombre(dto.getNombre());
        reporte.setDescripcion(dto.getDescripcion());
        reporte.setFechaInicio(dto.getFechaInicio());
        reporte.setFechaFin(dto.getFechaFin());
        reporte.setParametros(dto.getParametros());
        reporte.setDatosReporte(dto.getDatosReporte());
        reporte.setFechaCreacion(dto.getFechaCreacion());
        reporte.setEstado(dto.getEstado() != null ? dto.getEstado() : "GENERADO");
        reporte.setArchivoUrl(dto.getArchivoUrl());
        reporte.setTotalRegistros(dto.getTotalRegistros() != null ? dto.getTotalRegistros() : 0);
        
        if (dto.getUsuarioCreadorId() != null) {
            Optional<Usuario> usuario = usuarioRepositorio.findById(dto.getUsuarioCreadorId());
            usuario.ifPresent(reporte::setUsuarioCreador);
        }
        
        return reporte;
    }
}
