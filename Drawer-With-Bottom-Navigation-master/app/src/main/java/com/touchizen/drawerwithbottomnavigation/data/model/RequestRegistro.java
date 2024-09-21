package com.touchizen.drawerwithbottomnavigation.data.model;

public class RequestRegistro {
    private String usuario;
    private String email;
    private String password;
    private boolean aceptoAvisoPrivacidad;

    public boolean isAceptoAvisoPrivacidad() {
        return aceptoAvisoPrivacidad;
    }

    public void setAceptoAvisoPrivacidad(boolean aceptoAvisoPrivacidad) {
        this.aceptoAvisoPrivacidad = aceptoAvisoPrivacidad;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getters y Setters
}
