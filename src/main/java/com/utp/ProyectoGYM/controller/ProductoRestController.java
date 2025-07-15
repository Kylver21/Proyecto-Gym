package com.utp.ProyectoGYM.controller;

import com.utp.ProyectoGYM.dto.ProductoDTO;
import com.utp.ProyectoGYM.modelo.Producto;
import com.utp.ProyectoGYM.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174", "http://localhost:5175"})
public class ProductoRestController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<ProductoDTO> listarProductos() {
        return productoService.obtenerTodos().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerProductoPorId(@PathVariable Long id) {
        Optional<Producto> producto = productoService.obtenerPorId(id);
        return producto.map(p -> ResponseEntity.ok(convertirADTO(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody ProductoDTO productoDTO) {
        Producto producto = convertirAEntidad(productoDTO);
        Producto nuevoProducto = productoService.guardar(producto);
        return ResponseEntity.status(201).body(convertirADTO(nuevoProducto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        if (!productoService.obtenerPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Producto producto = convertirAEntidad(productoDTO);
        producto.setId(id);
        Producto actualizado = productoService.guardar(producto);
        return ResponseEntity.ok(convertirADTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        if (!productoService.obtenerPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private ProductoDTO convertirADTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setEstado(producto.getEstado());
        return dto;
    }

    private Producto convertirAEntidad(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setId(dto.getId());
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setEstado(dto.getEstado() != null ? dto.getEstado() : true);
        // Los campos imagen_url y fecha_creacion son manejados automáticamente por la base de datos
        return producto;
    }
}
