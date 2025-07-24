package com.utp.ProyectoGYM.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

public class RegistroMembresiaDTO {
    private Long id;
    private Long usuarioId;
    private Long membresiaId;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaInicio;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaFin;
    
    private String estado;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public Long getMembresiaId() { return membresiaId; }
    public void setMembresiaId(Long membresiaId) { this.membresiaId = membresiaId; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}