package com.utp.ProyectoGYM.repositorio;

import com.utp.ProyectoGYM.modelo.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PagoRepositorio extends JpaRepository<Pago, Long> {
    
    @Query("SELECT p FROM Pago p WHERE p.registroMembresia.usuario.id = :usuarioId")
    List<Pago> findByUsuarioId(@Param("usuarioId") Long usuarioId);
}
