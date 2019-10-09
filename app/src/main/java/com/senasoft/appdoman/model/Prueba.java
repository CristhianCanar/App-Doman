package com.senasoft.appdoman.model;

public class Prueba {

    private int id;
    private int num_words;
    private int id_boy;

    public Prueba() {
    }

    public Prueba(int id, int num_words, int id_boy) {
        this.id = id;
        this.num_words = num_words;
        this.id_boy = id_boy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_words() {
        return num_words;
    }

    public void setNum_words(int num_words) {
        this.num_words = num_words;
    }

    public int getId_boy() {
        return id_boy;
    }

    public void setId_boy(int id_boy) {
        this.id_boy = id_boy;
    }
}
