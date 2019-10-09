package com.senasoft.appdoman.model;

public class Category {

    private int id;
    private String name;
    private String url_image;

    public Category() {
    }

    public Category(int id, String name, String url_image) {
        this.id = id;
        this.name = name;
        this.url_image = url_image;
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

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    @Override
    public String toString() {
        return name;
    }
}
