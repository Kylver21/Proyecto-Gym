package com.utp.ProyectoGYM.repositorio;

import com.utp.ProyectoGYM.modelo.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReporteRepositorio extends JpaRepository<Reporte, Long> {
    
    List<Reporte> findByTipo(String tipo);
    
    List<Reporte> findByUsuarioCreadorId(Long usuarioId);
    
    List<Reporte> findByEstado(String estado);
    
    @Query("SELECT r FROM Reporte r WHERE r.fechaCreacion >= :fechaDesde AND r.fechaCreacion <= :fechaHasta")
    List<Reporte> findByFechaCreacionBetween(@Param("fechaDesde") LocalDate fechaDesde, @Param("fechaHasta") LocalDate fechaHasta);
    
    @Query("SELECT r FROM Reporte r WHERE r.tipo = :tipo AND r.estado = :estado ORDER BY r.fechaCreacion DESC")
    List<Reporte> findByTipoAndEstadoOrderByFechaCreacionDesc(@Param("tipo") String tipo, @Param("estado") String estado);
    
    @Query("SELECT COUNT(r) FROM Reporte r WHERE r.tipo = :tipo")
    Long countByTipo(@Param("tipo") String tipo);
    
    @Query("SELECT DISTINCT r.tipo FROM Reporte r ORDER BY r.tipo")
    List<String> findDistinctTipos();
}
