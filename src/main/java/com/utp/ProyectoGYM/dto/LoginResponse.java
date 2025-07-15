
package com.utp.ProyectoGYM.dto;

import com.utp.ProyectoGYM.modelo.Usuario;

public class LoginResponse {
    private boolean success;
    private String message;
    private Usuario user;

    // Constructores
    public LoginResponse() {}

    public LoginResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public LoginResponse(boolean success, String message, Usuario user) {
        this.success = success;
        this.message = message;
        this.user = user;
    }

    // Getters y Setters
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public Usuario getUser() { return user; }
    public void setUser(Usuario user) { this.user = user; }
}