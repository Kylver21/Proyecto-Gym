package com.utp.ProyectoGYM.modelo;
import jakarta.persistence.*;
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(name = "apellido", nullable = false)
    private String apellido;
    
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    
    // ✅ CAMBIO PRINCIPAL: No valor por defecto aquí
    @Column(name = "rol", nullable = false)
    private String rol;
    
    // ✅ CAMBIO: Usar boolean primitive para evitar problemas con null
    @Column(name = "estado", nullable = false)
    private boolean estado = true;

    // Constructores
    public Usuario() {
        // ✅ CAMBIO: Establecer valores por defecto en constructor vacío
        this.rol = "CLIENTE";
        this.estado = true;
    }

    public Usuario(String username, String password, String nombre, String apellido, String email, String rol) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        // ✅ CAMBIO: Validar y normalizar rol
        this.rol = validateAndNormalizeRole(rol);
        this.estado = true;
    }

    // ✅ NUEVO: Método para validar y normalizar roles
    private String validateAndNormalizeRole(String inputRole) {
        if (inputRole == null || inputRole.trim().isEmpty()) {
            return "CLIENTE";
        }
        
        // Normalizar el rol (quitar ROLE_ si existe y convertir a mayúsculas)
        String normalizedRole = inputRole.replace("ROLE_", "").toUpperCase().trim();
        
        // Validar que sea un rol válido
        switch (normalizedRole) {
            case "ADMIN":
            case "EMPLEADO":
            case "CLIENTE":
                return normalizedRole;
            default:
                return "CLIENTE";
        }
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    // ✅ CAMBIO: Validar rol en el setter
    public void setRol(String rol) {
        this.rol = validateAndNormalizeRole(rol);
    }

    // ✅ CAMBIO: Usar boolean primitive
    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    // ✅ NUEVO: Método de conveniencia para verificar roles
    public boolean isAdmin() {
        return "ADMIN".equals(this.rol);
    }

    public boolean isEmpleado() {
        return "EMPLEADO".equals(this.rol);
    }

    public boolean isCliente() {
        return "CLIENTE".equals(this.rol);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", rol='" + rol + '\'' +
                ", estado=" + estado +
                '}';
    }
}