package com.utp.ProyectoGYM.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReporteDTO {
    private Long id;
    private String tipo;
    private String nombre;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String parametros;
    private String datosReporte;
    private Long usuarioCreadorId;
    private String nombreUsuarioCreador;
    private LocalDateTime fechaCreacion;
    private String estado;
    private String archivoUrl;
    private Integer totalRegistros;
    
    // Constructores
    public ReporteDTO() {}
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
    
    public String getParametros() { return parametros; }
    public void setParametros(String parametros) { this.parametros = parametros; }
    
    public String getDatosReporte() { return datosReporte; }
    public void setDatosReporte(String datosReporte) { this.datosReporte = datosReporte; }
    
    public Long getUsuarioCreadorId() { return usuarioCreadorId; }
    public void setUsuarioCreadorId(Long usuarioCreadorId) { this.usuarioCreadorId = usuarioCreadorId; }
    
    public String getNombreUsuarioCreador() { return nombreUsuarioCreador; }
    public void setNombreUsuarioCreador(String nombreUsuarioCreador) { this.nombreUsuarioCreador = nombreUsuarioCreador; }
    
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    public String getArchivoUrl() { return archivoUrl; }
    public void setArchivoUrl(String archivoUrl) { this.archivoUrl = archivoUrl; }
    
    public Integer getTotalRegistros() { return totalRegistros; }
    public void setTotalRegistros(Integer totalRegistros) { this.totalRegistros = totalRegistros; }
}
