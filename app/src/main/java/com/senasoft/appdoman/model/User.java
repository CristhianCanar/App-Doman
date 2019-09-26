package com.senasoft.appdoman.model;

public class User {
    private String usuId;
    private String usuName;
    private String usuGenero;
    private String urlAvatar;

    public User() {
    }

    public User(String usuId, String usuName, String usuGenero, String urlAvatar) {
        this.usuId = usuId;
        this.usuName = usuName;
        this.usuGenero = usuGenero;
        this.urlAvatar = urlAvatar;
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

    public String getUsuGenero() {
        return usuGenero;
    }

    public void setUsuGenero(String usuGenero) {
        this.usuGenero = usuGenero;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }
}
