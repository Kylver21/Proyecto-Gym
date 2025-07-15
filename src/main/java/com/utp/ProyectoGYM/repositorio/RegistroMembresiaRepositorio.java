package com.utp.ProyectoGYM.repositorio;

import com.utp.ProyectoGYM.modelo.RegistroMembresia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistroMembresiaRepositorio extends JpaRepository<RegistroMembresia, Long> {
    
    List<RegistroMembresia> findByUsuarioId(Long usuarioId);
}