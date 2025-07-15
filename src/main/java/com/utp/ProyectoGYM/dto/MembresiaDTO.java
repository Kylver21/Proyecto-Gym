package com.utp.ProyectoGYM.dto;

public class MembresiaDTO {
    private Long id;
    private String tipo;
    private String descripcion;
    private Double precio;
    private Integer duracionDias;
    private Boolean estado;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
    public Integer getDuracionDias() { return duracionDias; }
    public void setDuracionDias(Integer duracionDias) { this.duracionDias = duracionDias; }
    public Boolean getEstado() { return estado; }
    public void setEstado(Boolean estado) { this.estado = estado; }
}