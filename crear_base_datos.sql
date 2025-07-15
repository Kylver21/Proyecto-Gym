-- Script para crear la base de datos del sistema de gimnasio
-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS bdgym;
USE bdgym;

-- Tabla USUARIO
CREATE TABLE IF NOT EXISTS usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    rol VARCHAR(20) NOT NULL DEFAULT 'CLIENTE',
    estado BOOLEAN DEFAULT TRUE
);

-- Tabla MEMBRESIA
CREATE TABLE IF NOT EXISTS membresia (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL,
    descripcion VARCHAR(255),
    precio DECIMAL(10, 2) NOT NULL,
    duracion_dias INT NOT NULL,
    estado BOOLEAN DEFAULT TRUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla REGISTRO_MEMBRESIA
CREATE TABLE IF NOT EXISTS registro_membresia (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    membresia_id BIGINT NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    estado VARCHAR(20) DEFAULT 'ACTIVA',
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (membresia_id) REFERENCES membresia(id) ON DELETE CASCADE
);

-- Tabla PAGO
CREATE TABLE IF NOT EXISTS pago (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    registro_membresia_id BIGINT NOT NULL,
    monto DECIMAL(10, 2) NOT NULL,
    fecha_pago DATE NOT NULL,
    metodo_pago VARCHAR(50) NOT NULL,
    estado VARCHAR(20) DEFAULT 'PENDIENTE',
    comprobante_url VARCHAR(255),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (registro_membresia_id) REFERENCES registro_membresia(id) ON DELETE CASCADE
);

-- Tabla PRODUCTO
CREATE TABLE IF NOT EXISTS producto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    imagen_url VARCHAR(255),
    estado BOOLEAN DEFAULT TRUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tipos de membresía iniciales
INSERT INTO membresia (tipo, descripcion, precio, duracion_dias) VALUES
('MENSUAL', 'Acceso ilimitado por 1 mes', 100.00, 30),
('TRIMESTRAL', 'Acceso ilimitado por 3 meses', 270.00, 90),
('ANUAL', 'Acceso ilimitado por 1 año (2 meses gratis)', 1000.00, 365),
('DIARIO', 'Acceso por 1 día', 15.00, 1);

-- Productos iniciales
INSERT INTO producto (nombre, descripcion, precio, stock) VALUES
('Botella deportiva', 'Botella de agua deportiva 750ml', 25.00, 50),
('Toalla de gimnasio', 'Toalla deportiva antimicrobiana', 35.00, 30),
('Guantes de levantamiento', 'Guantes para pesas con soporte para muñeca', 45.00, 20),
('Banda elástica', 'Banda de resistencia media 15kg', 30.00, 40),
('Candado para casillero', 'Candado de combinación', 15.00, 100);
