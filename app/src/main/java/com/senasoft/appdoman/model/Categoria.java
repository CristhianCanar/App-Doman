package com.senasoft.appdoman.control.model;

public class Categoria {
    private String catId;
    private String catName;

    public Categoria(String catId, String catName) {
        this.catId = catId;
        this.catName = catName;
    }

    public Categoria() {
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
