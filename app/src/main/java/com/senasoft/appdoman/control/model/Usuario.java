package com.senasoft.appdoman.control.model;

public class Usuario {
    private String usuId;
    private String usuName;
    private String usuUserName;
    private String usuPassword;
    private String usuGenero;

    public Usuario(String usuId, String usuName, String usuUserName, String usuPassword, String usuGenero) {
        this.usuId = usuId;
        this.usuName = usuName;
        this.usuUserName = usuUserName;
        this.usuPassword = usuPassword;
        this.usuGenero = usuGenero;
    }

    public Usuario() {
    }

    public String getUsuId() {
        return usuId;
    }

    public void setUsuId(String usuId) {
        this.usuId = usuId;
    }

    public String getUsuName() {
        return usuName;
    }

    public void setUsuName(String usuName) {
        this.usuName = usuName;
    }

    public String getUsuUserName() {
        return usuUserName;
    }

    public void setUsuUserName(String usuUserName) {
        this.usuUserName = usuUserName;
    }

    public String getUsuPassword() {
        return usuPassword;
    }

    public void setUsuPassword(String usuPassword) {
        this.usuPassword = usuPassword;
    }

    public String getUsuGenero() {
        return usuGenero;
    }

    public void setUsuGenero(String usuGenero) {
        this.usuGenero = usuGenero;
    }
}
