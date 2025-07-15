package com.utp.ProyectoGYM.dto;

public class AuthResponse {
    private boolean success;
    private String message;
    private UsuarioDTO user;

    // Constructores
    public AuthResponse() {}

    public AuthResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public AuthResponse(boolean success, String message, UsuarioDTO user) {
        this.success = success;
        this.message = message;
        this.user = user;
    }

    // Getters y setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UsuarioDTO getUser() {
        return user;
    }

    public void setUser(UsuarioDTO user) {
        this.user = user;
    }
}
