-- Script para poblar la base de datos con datos de prueba para el sistema de gimnasio
-- Usar la base de datos correcta
USE bdgym;

-- Limpiar tablas existentes (en orden correcto para evitar errores de FK)
DELETE FROM pago;
DELETE FROM registro_membresia;
DELETE FROM membresia;
DELETE FROM producto;
DELETE FROM usuario;

-- Reiniciar AUTO_INCREMENT
ALTER TABLE usuario AUTO_INCREMENT = 1;
ALTER TABLE membresia AUTO_INCREMENT = 1;
ALTER TABLE producto AUTO_INCREMENT = 1;
ALTER TABLE registro_membresia AUTO_INCREMENT = 1;
ALTER TABLE pago AUTO_INCREMENT = 1;

-- Insertar usuarios (contraseñas encriptadas con BCrypt)
INSERT INTO usuario (username, password, nombre, apellido, email, rol, estado) VALUES
('admin', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'Administrador', 'Sistema', 'admin@gym.com', 'ADMIN', true),
('empleado1', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'Carlos', 'López', 'empleado1@gym.com', 'EMPLEADO', true),
('cliente1', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'María', 'García', 'maria@email.com', 'CLIENTE', true),
('cliente2', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'Juan', 'Pérez', 'juan@email.com', 'CLIENTE', true),
('cliente3', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'Ana', 'Martínez', 'ana@email.com', 'CLIENTE', true),
('cliente4', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'Luis', 'Rodríguez', 'luis@email.com', 'CLIENTE', true);

-- Insertar membresías
INSERT INTO membresia (tipo, descripcion, precio, duracion_dias, estado) VALUES
('MENSUAL', 'Membresía mensual completa con acceso a todas las áreas', 50.00, 30, true),
('TRIMESTRAL', 'Membresía trimestral con 15% de descuento', 140.00, 90, true),
('SEMESTRAL', 'Membresía semestral con 25% de descuento', 250.00, 180, true),
('ANUAL', 'Membresía anual con 35% de descuento', 450.00, 365, true),
('ESTUDIANTE', 'Membresía especial para estudiantes', 35.00, 30, true),
('PREMIUM', 'Membresía premium con entrenador personal', 120.00, 30, true);

-- Insertar productos
INSERT INTO producto (nombre, descripcion, precio, stock, estado) VALUES
('Proteína Whey', 'Proteína de suero de alta calidad - 1kg', 25.99, 50, true),
('Shaker', 'Botella mezcladora de 600ml', 8.50, 100, true),
('Creatina', 'Creatina monohidrato 300g', 15.99, 30, true),
('Pre-entreno', 'Suplemento pre-entreno energizante', 32.50, 25, true),
('Aminoácidos BCAA', 'Aminoácidos esenciales para recuperación', 22.00, 40, true),
('Toalla Gym', 'Toalla de microfibra para gimnasio', 12.00, 60, true),
('Guantes', 'Guantes de entrenamiento antideslizantes', 18.50, 35, true),
('Camiseta Gym', 'Camiseta deportiva transpirable', 15.00, 75, true),
('Cinta Kinesiológica', 'Cinta terapéutica para lesiones', 9.99, 20, true),
('Multivitamínico', 'Complejo vitamínico deportivo', 28.00, 45, true);

-- Insertar registros de membresías (algunos activos, otros vencidos)
INSERT INTO registro_membresia (usuario_id, membresia_id, fecha_inicio, fecha_fin, estado) VALUES
-- Membresías activas
(3, 1, '2025-01-01', '2025-01-31', 'ACTIVA'),
(4, 2, '2024-12-01', '2025-03-01', 'ACTIVA'),
(5, 4, '2024-07-01', '2025-07-01', 'ACTIVA'),
(6, 3, '2024-11-01', '2025-05-01', 'ACTIVA'),
-- Membresías vencidas
(3, 5, '2024-06-01', '2024-07-01', 'VENCIDA'),
(4, 1, '2024-05-01', '2024-06-01', 'VENCIDA'),
-- Membresía cancelada
(5, 1, '2024-08-01', '2024-09-01', 'CANCELADA');

-- Insertar pagos
INSERT INTO pago (registro_membresia_id, monto, fecha_pago, metodo_pago, estado) VALUES
-- Pagos para membresías activas
(1, 50.00, '2025-01-01', 'TARJETA', 'COMPLETADO'),
(2, 140.00, '2024-12-01', 'TRANSFERENCIA', 'COMPLETADO'),
(3, 450.00, '2024-07-01', 'EFECTIVO', 'COMPLETADO'),
(4, 250.00, '2024-11-01', 'TARJETA', 'COMPLETADO'),
-- Pagos para membresías vencidas
(5, 35.00, '2024-06-01', 'EFECTIVO', 'COMPLETADO'),
(6, 50.00, '2024-05-01', 'TARJETA', 'COMPLETADO'),
-- Pago pendiente
(7, 50.00, '2024-08-01', 'TRANSFERENCIA', 'PENDIENTE');

-- Verificar los datos insertados
SELECT 'USUARIOS' as tabla, COUNT(*) as total FROM usuario
UNION ALL
SELECT 'MEMBRESIAS' as tabla, COUNT(*) as total FROM membresia
UNION ALL
SELECT 'PRODUCTOS' as tabla, COUNT(*) as total FROM producto
UNION ALL
SELECT 'REGISTROS_MEMBRESIA' as tabla, COUNT(*) as total FROM registro_membresia
UNION ALL
SELECT 'PAGOS' as tabla, COUNT(*) as total FROM pago;
