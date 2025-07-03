package com.utp.ProyectoGYM.dto;

import java.time.LocalDateTime;

public class LoginDTO {
    private Long id;
    private Long usuarioId;
    private LocalDateTime fecha;
    private Boolean exito;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public Boolean getExito() { return exito; }
    public void setExito(Boolean exito) { this.exito = exito; }
}