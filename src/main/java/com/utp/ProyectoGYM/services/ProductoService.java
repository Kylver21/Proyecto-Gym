package com.utp.ProyectoGYM.services;

import com.utp.ProyectoGYM.modelo.Producto;
import com.utp.ProyectoGYM.repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepositorio productoRepositorio;

    public List<Producto> obtenerTodos() {
        return productoRepositorio.findAll();
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepositorio.findById(id);
    }

    public Producto guardar(Producto producto) {
        return productoRepositorio.save(producto);
    }

    public void eliminar(Long id) {
        productoRepositorio.deleteById(id);
    }
}
