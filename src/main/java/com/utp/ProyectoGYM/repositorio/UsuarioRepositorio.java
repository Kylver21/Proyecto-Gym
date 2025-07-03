package com.utp.ProyectoGYM.repositorio;

import com.utp.ProyectoGYM.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
}