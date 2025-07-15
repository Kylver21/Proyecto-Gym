package com.utp.ProyectoGYM.dto;

import com.utp.ProyectoGYM.modelo.Usuario;

public class AuthCheckResponse {
    private boolean authenticated;
    private Usuario user;

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}
