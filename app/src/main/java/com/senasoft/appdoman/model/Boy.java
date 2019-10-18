package com.senasoft.appdoman.model;


public class Boy {

    private int id;
    private String name;
    private String genero;
    private String fecha_nacimiento;
    private String url_avatar;
    private int score;


    public Boy() {
    }

    public Boy(int id, String name, String genero, String fecha_nacimiento, String url_avatar, int score) {
        this.id = id;
        this.name = name;
        this.genero = genero;
        this.fecha_nacimiento = fecha_nacimiento;
        this.url_avatar = url_avatar;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getUrl_avatar() {
        return url_avatar;
    }

    public void setUrl_avatar(String url_avatar) {
        this.url_avatar = url_avatar;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
