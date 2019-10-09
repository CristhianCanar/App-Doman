package com.senasoft.appdoman.model;

public class Word {

    private int id;
    private String name;
    private int id_categoriy;
    private String url_auidio;

    public Word() {
    }

    public Word(int id, String name, int id_categoriy, String url_auidio) {
        this.id = id;
        this.name = name;
        this.id_categoriy = id_categoriy;
        this.url_auidio = url_auidio;
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

    public int getId_categoriy() {
        return id_categoriy;
    }

    public void setId_categoriy(int id_categoriy) {
        this.id_categoriy = id_categoriy;
    }

    public String getUrl_auidio() {
        return url_auidio;
    }

    public void setUrl_auidio(String url_auidio) {
        this.url_auidio = url_auidio;
    }
}
