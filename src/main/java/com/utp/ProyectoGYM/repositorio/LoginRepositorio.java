package com.utp.ProyectoGYM.repositorio;

import com.utp.ProyectoGYM.modelo.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepositorio extends JpaRepository<Login, Long> {
}