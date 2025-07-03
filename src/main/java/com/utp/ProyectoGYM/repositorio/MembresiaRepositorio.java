package com.utp.ProyectoGYM.repositorio;

import com.utp.ProyectoGYM.modelo.Membresia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembresiaRepositorio extends JpaRepository<Membresia, Long> {
}