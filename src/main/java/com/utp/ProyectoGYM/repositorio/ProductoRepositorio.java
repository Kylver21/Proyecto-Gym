package com.utp.ProyectoGYM.repositorio;

import com.utp.ProyectoGYM.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
}
