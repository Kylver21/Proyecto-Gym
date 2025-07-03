package com.utp.ProyectoGYM.repositorio;

import com.utp.ProyectoGYM.modelo.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepositorio extends JpaRepository<Pago, Long> {
}
