# 🏋️ ProyectoGYM - Sistema de Gestión de Gimnasio

Sistema completo de gestión para gimnasios desarrollado con **Spring Boot** (Backend) y **React** (Frontend), creado para administrar usuarios, membresías, pag---

## 🚀 Asignar Membresía Premium para Pruebas

Para probar el frontend con el usuario TEST, puedes ejecutar:

```bash
mysql -u root -p bdgym < asignar_membresia_test.sql
```

Este script asignará una membresía Premium al usuario TEST para pruebas.

---

## 💾 Backup y Restauración de Base de Datos

### Crear Backup

**Opción 1: Script Automático (Recomendado)**
```bash
# PowerShell
.\crear_backup.ps1

# Batch
crear_backup.bat
```

**Opción 2: Comando Manual**
```bash
# Windows
& "C:\Program Files\MySQL\MySQL Server 8.0\bin\mysqldump.exe" -u root -p dbgym > backup_dbgym_$(Get-Date -Format 'yyyyMMdd_HHmmss').sql

# Linux/Mac
mysqldump -u root -p dbgym > backup_dbgym_$(date +%Y%m%d_%H%M%S).sql
```

### Restaurar Backup

**Opción 1: Script Automático**
```bash
# PowerShell (selección interactiva)
.\restaurar_backup.ps1

# Con archivo específico
.\restaurar_backup.ps1 -ArchivoBackup "backup_dbgym_20250715_010605.sql"
```

**Opción 2: Comando Manual**
```bash
# Windows
& "C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe" -u root -p dbgym < backup_dbgym_20250715_010605.sql

# Linux/Mac
mysql -u root -p dbgym < backup_dbgym_20250715_010605.sql
```

### Programar Backups Automáticos

Para crear backups automáticos, puedes usar el Programador de Tareas de Windows:

1. Abrir "Programador de tareas"
2. Crear tarea básica
3. Programar para ejecutar `crear_backup.bat` diariamente
4. Configurar la ruta del proyecto como directorio de trabajoos y generar reportes. ¡Optimiza la operación de tu gimnasio con una plataforma moderna y segura!

---

## 🚀 Características Principales

| Funcionalidad          | Descripción                                                                                     |
|-----------------------|-------------------------------------------------------------------------------------------------|
| 🔐 **Autenticación & Autorización** | - Login y registro de usuarios<br>- Autenticación por sesiones HTTP<br>- Roles: `ADMIN`, `EMPLEADO`, `CLIENTE`<br>- Contraseñas encriptadas (BCrypt)<br>- Control de acceso granular |
| 👥 **Gestión de Usuarios**           | - CRUD de perfiles<br>- Estados: Activo/Inactivo<br>- Permisos específicos por rol                                      |
| 🏃‍♂️ **Gestión de Membresías**      | - Tipos: Mensual, Trimestral, Semestral, Anual, Premium<br>- Registro y control de membresías<br>- Estados: Activa, Vencida, Cancelada |
| 💰 **Sistema de Pagos**              | - Registro de pagos y métodos: Efectivo, Tarjeta, Transferencia<br>- Estados: Completado, Pendiente, Cancelado<br>- Historial de pagos por usuario |
| 🛒 **Gestión de Productos**          | - Catálogo y control de inventario<br>- Precios y descripciones<br>- Gestión de stock y estado de productos             |
| 📊 **Sistema de Reportes**           | - Reportes de ventas e ingresos<br>- Estadísticas de membresías<br>- Análisis de usuarios y pagos<br>- Reportes de inventario |

---

## 🛠️ Tecnologías Utilizadas

**Backend**  
- Java 21+  
- Spring Boot 3.x  
- Spring Security  
- Spring Data JPA  
- Hibernate  
- MySQL  
- BCrypt  
- Maven  

**Frontend**  
- React 18+  
- TypeScript  
- Axios  
- React Router  
- Material-UI / Tailwind CSS  

**Base de Datos**  
- MySQL 8.0+  
- Esquema: `bdgym`
---

## 📁 Estructura del Proyecto

```
ProyectoGYM/
├── src/main/java/com/utp/ProyectoGYM/
│   ├── config/
│   │   └── SecurityConfig.java           # Configuración de seguridad
│   ├── controller/
│   │   ├── AuthController.java           # Autenticación
│   │   ├── UsuarioRestController.java    # Usuarios
│   │   ├── MembresiaRestController.java  # Membresías
│   │   ├── PagoRestController.java       # Pagos
│   │   ├── ProductoRestController.java   # Productos
│   │   └── ReporteRestController.java    # Reportes
│   ├── modelo/
│   │   ├── Usuario.java                  # Entidad Usuario
│   │   ├── Membresia.java                # Entidad Membresía
│   │   ├── RegistroMembresia.java        # Registro Membresía
│   │   ├── Pago.java                     # Entidad Pago
│   │   ├── Producto.java                 # Entidad Producto
│   │   └── Reporte.java                  # Entidad Reporte
│   ├── dto/
│   │   ├── LoginRequest.java             # DTO login
│   │   ├── LoginResponse.java            # Respuesta login
│   │   ├── RegisterRequest.java          # DTO registro
│   │   └── ...
│   ├── repositorio/
│   │   └── ...                           # Repositorios JPA
│   ├── services/
│   │   └── ...                           # Servicios de negocio
│   └── ProyectoGymApplication.java       # Clase principal
├── src/main/resources/
│   ├── application.properties            # Configuración
│   └── ...                               # Scripts SQL
└── pom.xml                               # Dependencias Maven
```

---

## 🔧 Instalación y Configuración

### Prerrequisitos

- **Java 21+**
- **Maven 3.8+**
- **MySQL 8.0+**
- **Node.js 18+** (Frontend)

### Pasos de instalación

```bash
# Clona el repositorio
git clone https://github.com/Kylver21/Proyecto-Gym
cd ProyectoGYM
```

**Configura la base de datos:**  
Crea la base de datos `bdgym` y ejecuta los scripts SQL incluidos: `crear_base_datos.sql` y `datos_prueba.sql`.

**Configura application.properties:**
```properties
# Base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/bdgym
spring.datasource.username=root
spring.datasource.password=tu_password

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

# Puerto del servidor
server.port=8080
```

**Backend:**  
```bash
mvn clean install
mvn spring-boot:run
```

**Frontend:**  
```bash
cd frontend
npm install
npm run dev
```

---

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

---

## 🛡️ Roles y Permisos

- **ADMIN:** Acceso total a todas las funcionalidades
- **EMPLEADO:** Gestión de membresías y pagos
- **CLIENTE:** Acceso limitado a su información personal

**Seguridad:**  
- Sesiones HTTP con Spring Security  
- Contraseñas encriptadas con BCrypt  
- Manejo de errores personalizado

---

## 📊 Base de Datos

### Tablas Principales

| Tabla               | Descripción                      |
|---------------------|----------------------------------|
| usuario             | Información de usuarios          |
| membresia           | Tipos de membresías              |
| registro_membresia  | Membresías activas por usuario   |
| pago                | Registro de pagos                |
| producto            | Catálogo de productos            |
| reporte             | Sistema de reportes              |

---

## 📝 Usuarios de Prueba

Después de ejecutar el script `datos_prueba.sql`, tendrás disponibles:

- **Admin**: `admin1` / `admin123`
- **Empleado**: `empleado1` / `empleado123`
- **Cliente**: `cliente1` / `cliente123`

---

## � Asignar Membresía Premium para Pruebas

Para probar el frontend con el usuario TEST, puedes ejecutar:

```bash
mysql -u root -p bdgym < asignar_membresia_test.sql
```

Este script asignará una membresía Premium al usuario TEST para pruebas.

---

## 💡 Contribución

¿Te gustaría mejorar el proyecto? ¡Las contribuciones son bienvenidas!  
Por favor, abre un issue o un pull request con tus sugerencias o mejoras.

---

## 📬 Contacto

¿Dudas o sugerencias?  
Puedes contactarme en [GitHub](https://github.com/Kylver21) o por correo: kilverpaucar1@gmail.com

---

> **ProyectoGYM**: ¡Haz tu gimnasio más eficiente y profesional!
