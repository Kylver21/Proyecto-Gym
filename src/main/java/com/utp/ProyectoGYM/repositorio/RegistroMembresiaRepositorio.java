package com.utp.ProyectoGYM.repositorio;

import com.utp.ProyectoGYM.modelo.RegistroMembresia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroMembresiaRepositorio extends JpaRepository<RegistroMembresia, Long> {
}