# 🏋️ ProyectoGYM - Sistema de Gestión de Gimnasio

Sistema completo de gestión para gimnasios desarrollado con **Spring Boot** (Backend) y **React** (Frontend), que permite administrar usuarios, membresías, pagos, productos y generar reportes.

## 🚀 Características Principales

### 🔐 **Autenticación y Autorización**
- Sistema de login y registro de usuarios
- Autenticación basada en sesiones HTTP
- Roles de usuario: **ADMIN**, **EMPLEADO**, **CLIENTE**
- Encriptación de contraseñas con BCrypt
- Control de acceso por roles

### 👥 **Gestión de Usuarios**
- Registro y autenticación de usuarios
- Gestión completa de perfiles (CRUD)
- Diferentes roles con permisos específicos
- Estados de usuario (activo/inactivo)

### 🏃‍♂️ **Gestión de Membresías**
- Tipos de membresía: Mensual, Trimestral, Semestral, Anual
- Registro de membresías por usuario
- Control de fechas de inicio y vencimiento
- Estados: ACTIVA, VENCIDA, CANCELADA

### 💰 **Sistema de Pagos**
- Registro de pagos de membresías
- Métodos de pago: Efectivo, Tarjeta, Transferencia
- Estados de pago: COMPLETADO, PENDIENTE, CANCELADO
- Historial de pagos por usuario

### 🛒 **Gestión de Productos**
- Catálogo de productos del gimnasio
- Control de inventario y stock
- Precios y descripciones
- Estados de productos

### 📊 **Sistema de Reportes**
- Reportes de ventas e ingresos
- Estadísticas de membresías
- Análisis de usuarios registrados
- Reportes de inventario
- Análisis de pagos

## 🛠️ Tecnologías Utilizadas

### Backend (Spring Boot)
- **Java 21+**
- **Spring Boot 3.x**
- **Spring Security** - Autenticación y autorización
- **Spring Data JPA** - Persistencia de datos
- **Hibernate** - ORM
- **MySQL** - Base de datos
- **BCrypt** - Encriptación de contraseñas
- **Maven** - Gestión de dependencias

### Frontend (React)
- **React 18+**
- **TypeScript**
- **Axios** - Cliente HTTP
- **React Router** - Navegación
- **Material-UI / Tailwind CSS** - Interfaz de usuario

### Base de Datos
- **MySQL 8.0+**
- Esquema: `dbgym`

## 📁 Estructura del Proyecto

```
ProyectoGYM/
├── src/main/java/com/utp/ProyectoGYM/
│   ├── config/
│   │   └── SecurityConfig.java          # Configuración de seguridad
│   ├── controller/
│   │   ├── AuthController.java          # Autenticación
│   │   ├── UsuarioRestController.java   # Gestión de usuarios
│   │   ├── MembresiaRestController.java # Gestión de membresías
│   │   ├── PagoRestController.java      # Gestión de pagos
│   │   ├── ProductoRestController.java  # Gestión de productos
│   │   └── ReporteRestController.java   # Sistema de reportes
│   ├── modelo/
│   │   ├── Usuario.java                 # Entidad Usuario
│   │   ├── Membresia.java              # Entidad Membresía
│   │   ├── RegistroMembresia.java      # Registro de membresías
│   │   ├── Pago.java                   # Entidad Pago
│   │   ├── Producto.java               # Entidad Producto
│   │   └── Reporte.java                # Entidad Reporte
│   ├── dto/
│   │   ├── LoginRequest.java           # DTO para login
│   │   ├── LoginResponse.java          # DTO respuesta login
│   │   ├── RegisterRequest.java        # DTO para registro
│   │   └── [Otros DTOs...]
│   ├── repositorio/
│   │   └── [Repositorios JPA...]
│   ├── services/
│   │   └── [Servicios de negocio...]
│   └── ProyectoGymApplication.java     # Clase principal
├── src/main/resources/
│   ├── application.properties          # Configuración
│   └── [Scripts SQL...]
└── pom.xml                            # Dependencias Maven
```

## 🔧 Instalación y Configuración

### Prerrequisitos
- Java 21 o superior
- Maven 3.8+
- MySQL 8.0+
- Node.js 18+ (para el frontend)

### 1. Clonar el Repositorio
```bash
git clone https://github.com/Kylver21/Proyecto-Gym.git
cd ProyectoGYM
```

### 2. Configurar Base de Datos
```sql
-- Crear base de datos
CREATE DATABASE dbgym;

-- Ejecutar scripts SQL incluidos en el proyecto
-- (Ver archivos: crear_base_datos.sql, datos_prueba.sql)
```

### 3. Configurar application.properties
```properties
# Base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/dbgym
spring.datasource.username=root
spring.datasource.password=tu_password

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

# Puerto del servidor
server.port=8080
```

### 4. Ejecutar el Backend
```bash
# Compilar y ejecutar
mvn clean install
mvn spring-boot:run

# O usar el wrapper incluido
./mvnw spring-boot:run
```

### 5. Configurar y Ejecutar Frontend
```bash
# Ir a la carpeta del frontend
cd frontend

# Instalar dependencias
npm install

# Ejecutar en modo desarrollo
npm run dev
```

## 🌐 Endpoints de la API

### Autenticación (`/api/auth/`)
- `POST /login` - Iniciar sesión
- `POST /register` - Registrar usuario
- `POST /logout` - Cerrar sesión
- `GET /check` - Verificar autenticación

### Usuarios (`/api/usuarios/`)
- `GET /` - Obtener todos los usuarios
- `GET /{id}` - Obtener usuario por ID
- `POST /` - Crear usuario
- `PUT /{id}` - Actualizar usuario
- `DELETE /{id}` - Eliminar usuario

### Membresías (`/api/membresias/`)
- `GET /` - Obtener todas las membresías
- `GET /{id}` - Obtener membresía por ID
- `POST /` - Crear membresía
- `PUT /{id}` - Actualizar membresía
- `DELETE /{id}` - Eliminar membresía

### Pagos (`/api/pagos/`)
- `GET /` - Obtener todos los pagos
- `GET /usuario/{usuarioId}` - Pagos por usuario
- `POST /` - Crear pago
- `PUT /{id}` - Actualizar pago
- `DELETE /{id}` - Eliminar pago

### Productos (`/api/productos/`)
- `GET /` - Obtener todos los productos
- `GET /{id}` - Obtener producto por ID
- `POST /` - Crear producto
- `PUT /{id}` - Actualizar producto
- `DELETE /{id}` - Eliminar producto

### Reportes (`/api/reportes/`)
- `GET /` - Obtener todos los reportes
- `POST /generar/ventas` - Generar reporte de ventas
- `POST /generar/membresias` - Generar reporte de membresías
- `POST /generar/usuarios` - Generar reporte de usuarios

## 🔒 Seguridad

### Configuración de CORS
```java
@CrossOrigin(origins = {
    "http://localhost:5173",
    "http://localhost:5174"
})
```

### Roles y Permisos
- **ADMIN**: Acceso completo al sistema
- **EMPLEADO**: Gestión de membresías y pagos
- **CLIENTE**: Acceso limitado a su información

### Autenticación
- Sesiones HTTP con Spring Security
- Contraseñas encriptadas con BCrypt
- Manejo de errores personalizado

## 📊 Base de Datos

### Tablas Principales
- `usuario` - Información de usuarios
- `membresia` - Tipos de membresías
- `registro_membresia` - Membresías activas por usuario
- `pago` - Registro de pagos
- `producto` - Catálogo de productos
- `reporte` - Sistema de reportes

## 🚀 Despliegue

### Desarrollo
```bash
# Backend
mvn spring-boot:run

# Frontend
npm run dev
```

### Producción
```bash
# Backend
mvn clean package
java -jar target/ProyectoGYM-0.0.1-SNAPSHOT.jar

# Frontend
npm run build
```

## 📝 Usuarios de Prueba

Después de ejecutar el script `datos_prueba.sql`, tendrás disponibles:

- **Admin**: `admin1` / `admin123`
- **Empleado**: `empleado1` / `empleado123`
- **Cliente**: `cliente1` / `cliente123`

## 🤝 Contribución

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📝 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo LICENSE para más detalles.

## 👥 Autores

- **Kylver21** - *Desarrollo inicial* - [Kylver21](https://github.com/Kylver21)

## 📞 Contacto

- GitHub: [Kylver21](https://github.com/Kylver21)
- Proyecto: [Proyecto-Gym](https://github.com/Kylver21/Proyecto-Gym)

---

⭐ Si te gusta este proyecto, ¡dale una estrella en GitHub!
