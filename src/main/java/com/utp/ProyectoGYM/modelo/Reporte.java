package com.utp.ProyectoGYM.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reporte")
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String tipo;
    private String nombre;
    private String descripcion;
    
    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;
    
    @Column(name = "fecha_fin")
    private LocalDate fechaFin;
    
    @Column(columnDefinition = "JSON")
    private String parametros;
    
    @Column(name = "datos_reporte", columnDefinition = "JSON")
    private String datosReporte;
    
    @ManyToOne
    @JoinColumn(name = "usuario_creador_id")
    private Usuario usuarioCreador;
    
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    
    private String estado;
    
    @Column(name = "archivo_url")
    private String archivoUrl;
    
    @Column(name = "total_registros")
    private Integer totalRegistros;
    
    // Constructores
    public Reporte() {}
    
    public Reporte(String tipo, String nombre, String descripcion) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = LocalDateTime.now();
        this.estado = "GENERADO";
        this.totalRegistros = 0;
    }
    
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
    
    public Usuario getUsuarioCreador() { return usuarioCreador; }
    public void setUsuarioCreador(Usuario usuarioCreador) { this.usuarioCreador = usuarioCreador; }
    
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    public String getArchivoUrl() { return archivoUrl; }
    public void setArchivoUrl(String archivoUrl) { this.archivoUrl = archivoUrl; }
    
    public Integer getTotalRegistros() { return totalRegistros; }
    public void setTotalRegistros(Integer totalRegistros) { this.totalRegistros = totalRegistros; }
}
