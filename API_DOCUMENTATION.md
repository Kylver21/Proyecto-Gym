# 📚 Documentación Completa de API - ProyectoGYM

## 🌐 Base URL
```
http://localhost:8080
```

## 🔐 Autenticación
Todas las APIs (excepto /api/auth/*) requieren autenticación vía sesiones HTTP.

---

## 🔑 AUTENTICACIÓN (`/api/auth/`)

### 1. **POST** `/api/auth/login`
Iniciar sesión en el sistema.

**Request Body:**
```json
{
  "username": "admin1",
  "password": "admin123"
}
```

**Response (200 OK):**
```json
{
  "success": true,
  "message": "Login exitoso",
  "user": {
    "id": 1,
    "username": "admin1",
    "nombre": "Administrador",
    "apellido": "Sistema",
    "email": "admin@gym.com",
    "rol": "ADMIN",
    "estado": true,
    "password": null
  }
}
```

**Response (400 Bad Request):**
```json
{
  "success": false,
  "message": "Credenciales inválidas"
}
```

### 2. **POST** `/api/auth/register`
Registrar nuevo usuario.

**Request Body:**
```json
{
  "username": "nuevo_usuario",
  "password": "password123",
  "nombre": "Juan",
  "apellido": "Pérez",
  "email": "juan@email.com",
  "rol": "CLIENTE"
}
```

**Response (201 Created):**
```json
{
  "success": true,
  "message": "Usuario registrado exitosamente",
  "user": {
    "id": 5,
    "username": "nuevo_usuario",
    "nombre": "Juan",
    "apellido": "Pérez",
    "email": "juan@email.com",
    "rol": "CLIENTE",
    "estado": true,
    "password": null
  }
}
```

### 3. **POST** `/api/auth/logout`
Cerrar sesión.

**Response (200 OK):**
```json
{
  "success": true,
  "message": "Sesión cerrada exitosamente"
}
```

### 4. **GET** `/api/auth/check`
Verificar estado de autenticación.

**Response (200 OK) - Autenticado:**
```json
{
  "authenticated": true,
  "user": {
    "id": 1,
    "username": "admin1",
    "nombre": "Administrador",
    "apellido": "Sistema",
    "email": "admin@gym.com",
    "rol": "ADMIN",
    "estado": true
  }
}
```

**Response (200 OK) - No autenticado:**
```json
{
  "authenticated": false,
  "user": null
}
```

---

## 👥 USUARIOS (`/api/usuarios/`)

### 1. **GET** `/api/usuarios`
Obtener todos los usuarios.

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "username": "admin1",
    "nombre": "Administrador",
    "apellido": "Sistema",
    "email": "admin@gym.com",
    "rol": "ADMIN",
    "estado": true,
    "password": null
  },
  {
    "id": 2,
    "username": "empleado1",
    "nombre": "Carlos",
    "apellido": "López",
    "email": "empleado1@gym.com",
    "rol": "EMPLEADO",
    "estado": true,
    "password": null
  }
]
```

### 2. **GET** `/api/usuarios/{id}`
Obtener usuario por ID.

**Response (200 OK):**
```json
{
  "id": 1,
  "username": "admin1",
  "nombre": "Administrador",
  "apellido": "Sistema",
  "email": "admin@gym.com",
  "rol": "ADMIN",
  "estado": true,
  "password": null
}
```

### 3. **POST** `/api/usuarios`
Crear nuevo usuario.

**Request Body:**
```json
{
  "username": "nuevo_cliente",
  "password": "password123",
  "nombre": "María",
  "apellido": "García",
  "email": "maria@email.com",
  "rol": "CLIENTE",
  "estado": true
}
```

### 4. **PUT** `/api/usuarios/{id}`
Actualizar usuario existente.

**Request Body:**
```json
{
  "username": "usuario_actualizado",
  "nombre": "María José",
  "apellido": "García López",
  "email": "maria.jose@email.com",
  "rol": "CLIENTE",
  "estado": true
}
```

### 5. **DELETE** `/api/usuarios/{id}`
Eliminar usuario.

**Response (204 No Content)**

---

## 🏃‍♂️ MEMBRESÍAS (`/api/membresias/`)

### 1. **GET** `/api/membresias`
Obtener todas las membresías.

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "tipo": "MENSUAL",
    "descripcion": "Membresía mensual completa con acceso a todas las áreas",
    "precio": 50.00,
    "duracionDias": 30,
    "estado": true
  },
  {
    "id": 6,
    "tipo": "PREMIUM",
    "descripcion": "Membresía premium con entrenador personal",
    "precio": 120.00,
    "duracionDias": 30,
    "estado": true
  }
]
```

### 2. **POST** `/api/membresias`
Crear nueva membresía.

**Request Body:**
```json
{
  "tipo": "VIP",
  "descripcion": "Membresía VIP con todos los beneficios",
  "precio": 200.00,
  "duracionDias": 30,
  "estado": true
}
```

---

## 💰 PAGOS (`/api/pagos/`)

### 1. **GET** `/api/pagos`
Obtener todos los pagos.

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "registroMembresiaId": 1,
    "monto": 50.00,
    "fechaPago": "2025-01-01",
    "metodoPago": "TARJETA",
    "estado": "COMPLETADO"
  }
]
```

### 2. **GET** `/api/pagos/usuario/{usuarioId}`
Obtener pagos por usuario.

### 3. **POST** `/api/pagos`
Procesar nuevo pago.

**Request Body:**
```json
{
  "registroMembresiaId": 1,
  "monto": 50.00,
  "fechaPago": "2025-07-24",
  "metodoPago": "TARJETA",
  "estado": "COMPLETADO"
}
```

**Métodos de Pago Válidos:**
- `"EFECTIVO"`
- `"TARJETA"`
- `"TRANSFERENCIA"`

**Estados de Pago Válidos:**
- `"COMPLETADO"`
- `"PENDIENTE"`
- `"CANCELADO"`

### 4. **PUT** `/api/pagos/{id}`
Actualizar estado de pago.

**Request Body:**
```json
{
  "estado": "COMPLETADO",
  "metodoPago": "EFECTIVO"
}
```

---

## 🛒 PRODUCTOS (`/api/productos/`)

### 1. **GET** `/api/productos`
Obtener todos los productos.

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "nombre": "Proteína Whey",
    "descripcion": "Proteína de suero de alta calidad - 1kg",
    "precio": 25.99,
    "stock": 50,
    "estado": true
  }
]
```

### 2. **POST** `/api/productos`
Crear nuevo producto.

**Request Body:**
```json
{
  "nombre": "Creatina Premium",
  "descripcion": "Creatina monohidrato de alta pureza",
  "precio": 35.00,
  "stock": 25,
  "estado": true
}
```

---

## 📝 REGISTRO DE MEMBRESÍAS (`/api/registro-membresias/`)

### 1. **GET** `/api/registro-membresias`
Obtener todos los registros de membresías.

### 2. **GET** `/api/registro-membresias/usuario/{usuarioId}`
Obtener membresías de un usuario específico.

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "usuarioId": 3,
    "membresiaId": 6,
    "fechaInicio": "2025-07-24",
    "fechaFin": "2025-08-23",
    "estado": "ACTIVA"
  }
]
```

### 3. **POST** `/api/registro-membresias`
Asignar membresía a usuario.

**Request Body:**
```json
{
  "usuarioId": 3,
  "membresiaId": 6,
  "fechaInicio": "2025-07-24",
  "fechaFin": "2025-08-23",
  "estado": "ACTIVA"
}
```

**Estados de Membresía Válidos:**
- `"ACTIVA"`
- `"VENCIDA"`
- `"CANCELADA"`

---

## 📊 REPORTES (`/api/reportes/`)

### 1. **GET** `/api/reportes`
Obtener todos los reportes.

### 2. **GET** `/api/reportes/tipos`
Obtener tipos de reportes disponibles.

**Response (200 OK):**
```json
[
  "VENTAS",
  "MEMBRESIAS",
  "USUARIOS",
  "PRODUCTOS",
  "PAGOS"
]
```

### 3. **POST** `/api/reportes/generar/ventas`
Generar reporte de ventas.

**Query Parameters:**
- `fechaInicio`: 2025-01-01
- `fechaFin`: 2025-12-31
- `usuarioId`: 1

**Example URL:**
```
POST /api/reportes/generar/ventas?fechaInicio=2025-01-01&fechaFin=2025-12-31&usuarioId=1
```

### 4. **POST** `/api/reportes/generar/membresias`
Generar reporte de membresías.

**Query Parameters:**
- `usuarioId`: 1

### 5. **POST** `/api/reportes/generar/usuarios`
Generar reporte de usuarios.

### 6. **POST** `/api/reportes/generar/productos`
Generar reporte de productos.

### 7. **POST** `/api/reportes/generar/pagos`
Generar reporte de pagos.

**Query Parameters:**
- `fechaInicio`: 2025-01-01
- `fechaFin`: 2025-12-31
- `usuarioId`: 1

---

## 🚨 Códigos de Estado HTTP

| Código | Descripción |
|--------|-------------|
| 200 | OK - Solicitud exitosa |
| 201 | Created - Recurso creado exitosamente |
| 204 | No Content - Eliminación exitosa |
| 400 | Bad Request - Error en la solicitud |
| 401 | Unauthorized - No autenticado |
| 403 | Forbidden - Sin permisos |
| 404 | Not Found - Recurso no encontrado |
| 500 | Internal Server Error - Error del servidor |

---

## 🔒 Roles y Permisos

### ADMIN
- ✅ Acceso completo a todos los endpoints
- ✅ Gestión de usuarios, membresías, pagos, productos
- ✅ Generación de reportes

### EMPLEADO
- ✅ Gestión de membresías (`/api/membresias/*`)
- ✅ Gestión de pagos (`/api/pagos/*`)
- ✅ Gestión de registro de membresías (`/api/registro-membresias/*`)
- ✅ Gestión de productos (`/api/productos/*`)
- ❌ NO puede gestionar usuarios

### CLIENTE
- ✅ Ver sus propias membresías
- ✅ Ver sus propios pagos
- ✅ Ver productos disponibles
- ❌ NO puede modificar datos de otros usuarios

---

## 💡 Consejos para el Frontend

### 1. **Manejo de Sesiones**
```javascript
// Verificar autenticación al cargar la app
const checkAuth = async () => {
  const response = await fetch('/api/auth/check', {
    credentials: 'include' // Importante para cookies de sesión
  });
  const data = await response.json();
  return data.authenticated;
};
```

### 2. **Manejo de Errores de Pago**
```javascript
const procesarPago = async (pagoData) => {
  try {
    const response = await fetch('/api/pagos', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      credentials: 'include',
      body: JSON.stringify({
        registroMembresiaId: pagoData.membresiaId,
        monto: pagoData.monto,
        fechaPago: new Date().toISOString().split('T')[0],
        metodoPago: pagoData.metodoPago, // "EFECTIVO", "TARJETA", "TRANSFERENCIA"
        estado: "COMPLETADO"
      })
    });
    
    if (!response.ok) {
      throw new Error('Error al procesar el pago');
    }
    
    return await response.json();
  } catch (error) {
    console.error('Error:', error);
    throw error;
  }
};
```

### 3. **Configuración de Axios (Recomendado)**
```javascript
import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api',
  withCredentials: true, // Para manejar sesiones
  headers: {
    'Content-Type': 'application/json'
  }
});

// Interceptor para manejo de errores
api.interceptors.response.use(
  response => response,
  error => {
    if (error.response?.status === 401) {
      // Redirigir al login
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);
```

### 4. **Validaciones de Frontend**
```javascript
// Validar método de pago
const metodosValidos = ['EFECTIVO', 'TARJETA', 'TRANSFERENCIA'];
const estadosValidos = ['COMPLETADO', 'PENDIENTE', 'CANCELADO'];

// Validar roles
const rolesValidos = ['ADMIN', 'EMPLEADO', 'CLIENTE'];
```

---

¡Con esta documentación, tu frontend puede consumir todas las funcionalidades del backend sin errores! 🚀
