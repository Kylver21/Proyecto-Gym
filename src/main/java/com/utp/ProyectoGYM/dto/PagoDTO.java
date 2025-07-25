
package com.utp.ProyectoGYM.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

public class PagoDTO {
    private Long id;
    private Long registroMembresiaId;
    private Double monto;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaPago;
    
    private String metodoPago;
    private String estado;

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

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
