
package com.utp.ProyectoGYM.dto;

import java.time.LocalDate;

public class PagoDTO {
    private Long id;
    private Long registroMembresiaId;
    private Double monto;
    private LocalDate fechaPago;
    private String metodoPago;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getRegistroMembresiaId() { return registroMembresiaId; }
    public void setRegistroMembresiaId(Long registroMembresiaId) { this.registroMembresiaId = registroMembresiaId; }

    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }

    public LocalDate getFechaPago() { return fechaPago; }
    public void setFechaPago(LocalDate fechaPago) { this.fechaPago = fechaPago; }

    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
}
