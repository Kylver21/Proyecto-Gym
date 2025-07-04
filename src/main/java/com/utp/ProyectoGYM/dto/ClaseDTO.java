package com.utp.ProyectoGYM.dto;

public class ClaseDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String instructor;
    private String horario;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }
}
