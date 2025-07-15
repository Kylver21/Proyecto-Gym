
package com.utp.ProyectoGYM.modelo;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private RegistroMembresia registroMembresia;

    private Double monto;
    private LocalDate fechaPago;
    private String metodoPago;
    private String estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RegistroMembresia getRegistroMembresia() {
        return registroMembresia;
    }

    public void setRegistroMembresia(RegistroMembresia registroMembresia) {
        this.registroMembresia = registroMembresia;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}