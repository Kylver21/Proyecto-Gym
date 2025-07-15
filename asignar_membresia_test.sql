-- Script para asignar membresía Premium al usuario TEST
-- Usar la base de datos correcta
USE bdgym;

-- Verificar que el usuario TEST existe
SELECT id, username, nombre, apellido, rol FROM usuario WHERE username = 'TEST';

-- Verificar que la membresía Premium existe
SELECT id, tipo, descripcion, precio, duracion_dias FROM membresia WHERE tipo = 'PREMIUM';

-- Asignar membresía Premium al usuario TEST
-- Nota: Cambiar el usuario_id según el ID real del usuario TEST en tu base de datos
INSERT INTO registro_membresia (usuario_id, membresia_id, fecha_inicio, fecha_fin, estado)
SELECT 
    u.id as usuario_id,
    m.id as membresia_id,
    CURDATE() as fecha_inicio,
    DATE_ADD(CURDATE(), INTERVAL m.duracion_dias DAY) as fecha_fin,
    'ACTIVA' as estado
FROM usuario u, membresia m
WHERE u.username = 'TEST' AND m.tipo = 'PREMIUM';

-- Crear el pago correspondiente para la membresía Premium
INSERT INTO pago (registro_membresia_id, monto, fecha_pago, metodo_pago, estado)
SELECT 
    rm.id as registro_membresia_id,
    m.precio as monto,
    CURDATE() as fecha_pago,
    'TARJETA' as metodo_pago,
    'COMPLETADO' as estado
FROM registro_membresia rm
JOIN usuario u ON rm.usuario_id = u.id
JOIN membresia m ON rm.membresia_id = m.id
WHERE u.username = 'TEST' 
  AND m.tipo = 'PREMIUM'
  AND rm.estado = 'ACTIVA'
  AND rm.fecha_inicio = CURDATE();

-- Verificar que la membresía se asignó correctamente
SELECT 
    u.username,
    u.nombre,
    u.apellido,
    m.tipo as membresia_tipo,
    m.descripcion as membresia_descripcion,
    m.precio as membresia_precio,
    rm.fecha_inicio,
    rm.fecha_fin,
    rm.estado as membresia_estado,
    p.monto as pago_monto,
    p.metodo_pago,
    p.estado as pago_estado
FROM usuario u
JOIN registro_membresia rm ON u.id = rm.usuario_id
JOIN membresia m ON rm.membresia_id = m.id
LEFT JOIN pago p ON rm.id = p.registro_membresia_id
WHERE u.username = 'TEST' 
  AND rm.estado = 'ACTIVA'
ORDER BY rm.fecha_inicio DESC;
