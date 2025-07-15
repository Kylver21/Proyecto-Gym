🏋️ ProyectoGYM - Sistema de Gestión de Gimnasio
Sistema completo de gestión para gimnasios desarrollado con Spring Boot (Backend) y React (Frontend), que permite administrar usuarios, membresías, pagos, productos y generar reportes.

🚀 Características Principales
🔐 Autenticación y Autorización
Sistema de login y registro de usuarios
Autenticación basada en sesiones HTTP
Roles de usuario: ADMIN, EMPLEADO, CLIENTE
Encriptación de contraseñas con BCrypt
Control de acceso por roles
👥 Gestión de Usuarios
Registro y autenticación de usuarios
Gestión completa de perfiles (CRUD)
Diferentes roles con permisos específicos
Estados de usuario (activo/inactivo)
🏃‍♂️ Gestión de Membresías
Tipos de membresía: Mensual, Trimestral, Semestral, Anual
Registro de membresías por usuario
Control de fechas de inicio y vencimiento
Estados: ACTIVA, VENCIDA, CANCELADA
💰 Sistema de Pagos
Registro de pagos de membresías
Métodos de pago: Efectivo, Tarjeta, Transferencia
Estados de pago: COMPLETADO, PENDIENTE, CANCELADO
Historial de pagos por usuario
🛒 Gestión de Productos
Catálogo de productos del gimnasio
Control de inventario y stock
Precios y descripciones
Estados de productos
📊 Sistema de Reportes
Reportes de ventas e ingresos
Estadísticas de membresías
Análisis de usuarios registrados
Reportes de inventario
Análisis de pagos
🛠️ Tecnologías Utilizadas
Backend (Spring Boot)
Java 21+
Spring Boot 3.x
Spring Security - Autenticación y autorización
Spring Data JPA - Persistencia de datos
Hibernate - ORM
MySQL - Base de datos
BCrypt - Encriptación de contraseñas
Maven - Gestión de dependencias
Frontend (React)
React 18+
TypeScript
Axios - Cliente HTTP
React Router - Navegación
Material-UI / Tailwind CSS - Interfaz de usuario
Base de Datos
MySQL 8.0+
Esquema: dbgym
📁 Estructura del Proyecto:
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

🔧 Instalación y Configuración
Prerrequisitos
Java 21 o superior
Maven 3.8+
MySQL 8.0+
Node.js 18+ (para el frontend)
1. Clonar el Repositorio
git clone https://github.com/tu-usuario/ProyectoGYM.git
cd ProyectoGYM

Roles y Permisos
ADMIN: Acceso completo al sistema
EMPLEADO: Gestión de membresías y pagos
CLIENTE: Acceso limitado a su información
Autenticación
Sesiones HTTP con Spring Security
Contraseñas encriptadas con BCrypt
Manejo de errores personalizado
📊 Base de Datos
Tablas Principales
usuario - Información de usuarios
membresia - Tipos de membresías
registro_membresia - Membresías activas por usuario
pago - Registro de pagos
producto - Catálogo de productos
reporte - Sistema de reportes
