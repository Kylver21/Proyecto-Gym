# 🏋️ ProyectoGYM

Sistema completo de gestión para gimnasios desarrollado con **Spring Boot** (Backend) y **React** (Frontend), creado para administrar usuarios, membresías, pagos, productos y generar reportes. ¡Optimiza la operación de tu gimnasio con una plataforma moderna y segura!

---

## 🚀 Características Principales

| Funcionalidad          | Descripción                                                                                     |
|-----------------------|-------------------------------------------------------------------------------------------------|
| 🔐 **Autenticación & Autorización** | - Login y registro de usuarios<br>- Autenticación por sesiones HTTP<br>- Roles: `ADMIN`, `EMPLEADO`, `CLIENTE`<br>- Contraseñas encriptadas (BCrypt)<br>- Control de acceso granular |
| 👥 **Gestión de Usuarios**           | - CRUD de perfiles<br>- Estados: Activo/Inactivo<br>- Permisos específicos por rol                                      |
| 🏃‍♂️ **Gestión de Membresías**      | - Tipos: Mensual, Trimestral, Semestral, Anual<br>- Registro y control de membresías<br>- Estados: Activa, Vencida, Cancelada |
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
- Esquema: `dbgym`

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
git clone https://github.com/tu-usuario/ProyectoGYM.git
cd ProyectoGYM
```

**Configura la base de datos:**  
Crea la base de datos `dbgym` y ajusta tus credenciales en `src/main/resources/application.properties`.

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

## 💡 Contribución

¿Te gustaría mejorar el proyecto? ¡Las contribuciones son bienvenidas!  
Por favor, abre un issue o un pull request con tus sugerencias o mejoras.

---

## 📬 Contacto

¿Dudas o sugerencias?  
Puedes contactarme en [GitHub](https://github.com/Kylver21) o por correo: kilverpaucar1@gmail.com

---

> **ProyectoGYM**: ¡Haz tu gimnasio más eficiente y profesional!
