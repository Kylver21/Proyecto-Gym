-- Tabla para el sistema de reportes
USE bdgym;

-- Tabla REPORTE
CREATE TABLE IF NOT EXISTS reporte (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL,                    -- 'VENTAS', 'MEMBRESIAS', 'USUARIOS', 'PRODUCTOS', 'PAGOS'
    nombre VARCHAR(100) NOT NULL,                 -- Nombre descriptivo del reporte
    descripcion TEXT,                             -- Descripción del reporte
    fecha_inicio DATE,                            -- Fecha de inicio del período del reporte
    fecha_fin DATE,                               -- Fecha de fin del período del reporte
    parametros JSON,                              -- Parámetros adicionales en formato JSON
    datos_reporte JSON,                           -- Datos del reporte en formato JSON
    usuario_creador_id BIGINT,                    -- Usuario que generó el reporte
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(20) DEFAULT 'GENERADO',        -- 'GENERADO', 'PROCESANDO', 'COMPLETADO', 'ERROR'
    archivo_url VARCHAR(255),                     -- URL del archivo generado (PDF, Excel, etc.)
    total_registros INT DEFAULT 0,                -- Número total de registros en el reporte
    FOREIGN KEY (usuario_creador_id) REFERENCES usuario(id) ON DELETE SET NULL,
    INDEX idx_tipo (tipo),
    INDEX idx_fecha_creacion (fecha_creacion),
    INDEX idx_estado (estado)
);

-- Tabla REPORTE_DETALLE (Para reportes más complejos)
CREATE TABLE IF NOT EXISTS reporte_detalle (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    reporte_id BIGINT NOT NULL,
    seccion VARCHAR(50) NOT NULL,               -- 'RESUMEN', 'DETALLE', 'GRAFICOS', 'ESTADISTICAS'
    titulo VARCHAR(100),                        -- Título de la sección
    contenido JSON,                             -- Contenido de la sección en JSON
    orden INT DEFAULT 0,                        -- Orden de la sección en el reporte
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (reporte_id) REFERENCES reporte(id) ON DELETE CASCADE,
    INDEX idx_reporte_seccion (reporte_id, seccion)
);

-- Tipos de reportes predefinidos
INSERT INTO reporte (tipo, nombre, descripcion, estado, datos_reporte) VALUES
('VENTAS', 'Reporte de Ventas Mensual', 'Resumen de ventas y productos vendidos en el período', 'COMPLETADO', 
'{"total_ventas": 0, "productos_vendidos": 0, "metodo_pago_mas_usado": "EFECTIVO"}'),

('MEMBRESIAS', 'Reporte de Membresías Activas', 'Estado actual de todas las membresías del gimnasio', 'COMPLETADO',
'{"total_membresias": 0, "membresias_activas": 0, "membresias_vencidas": 0, "tipo_mas_popular": "MENSUAL"}'),

('USUARIOS', 'Reporte de Usuarios Registrados', 'Estadísticas de usuarios registrados en el sistema', 'COMPLETADO',
'{"total_usuarios": 0, "usuarios_activos": 0, "usuarios_inactivos": 0, "nuevos_usuarios_mes": 0}'),

('PRODUCTOS', 'Reporte de Inventario', 'Estado actual del inventario de productos', 'COMPLETADO',
'{"total_productos": 0, "productos_bajo_stock": 0, "producto_mas_vendido": "", "valor_inventario": 0}'),

('PAGOS', 'Reporte de Pagos y Ingresos', 'Análisis de pagos e ingresos del gimnasio', 'COMPLETADO',
'{"total_ingresos": 0, "pagos_pendientes": 0, "pagos_completados": 0, "metodo_pago_preferido": "TARJETA"}');

-- Vistas para reportes rápidos
CREATE VIEW vista_reporte_ventas AS
SELECT 
    DATE_FORMAT(p.fecha_pago, '%Y-%m') as mes,
    COUNT(*) as total_ventas,
    SUM(p.monto) as ingresos_totales,
    AVG(p.monto) as ingreso_promedio,
    p.metodo_pago
FROM pago p 
WHERE p.estado = 'COMPLETADO'
GROUP BY DATE_FORMAT(p.fecha_pago, '%Y-%m'), p.metodo_pago
ORDER BY mes DESC;

CREATE VIEW vista_reporte_membresias AS
SELECT 
    m.tipo,
    COUNT(rm.id) as total_registros,
    SUM(CASE WHEN rm.estado = 'ACTIVA' THEN 1 ELSE 0 END) as activas,
    SUM(CASE WHEN rm.estado = 'VENCIDA' THEN 1 ELSE 0 END) as vencidas,
    SUM(CASE WHEN rm.estado = 'CANCELADA' THEN 1 ELSE 0 END) as canceladas,
    AVG(m.precio) as precio_promedio
FROM membresia m
LEFT JOIN registro_membresia rm ON m.id = rm.membresia_id
GROUP BY m.tipo, m.precio;

CREATE VIEW vista_reporte_usuarios AS
SELECT 
    u.rol,
    COUNT(*) as total_usuarios,
    SUM(CASE WHEN u.estado = TRUE THEN 1 ELSE 0 END) as activos,
    SUM(CASE WHEN u.estado = FALSE THEN 1 ELSE 0 END) as inactivos
FROM usuario u
GROUP BY u.rol;

CREATE VIEW vista_reporte_productos AS
SELECT 
    p.nombre,
    p.stock,
    p.precio,
    CASE 
        WHEN p.stock <= 10 THEN 'BAJO_STOCK'
        WHEN p.stock <= 50 THEN 'STOCK_MEDIO'
        ELSE 'STOCK_ALTO'
    END as nivel_stock,
    p.estado
FROM producto p
ORDER BY p.stock ASC;
