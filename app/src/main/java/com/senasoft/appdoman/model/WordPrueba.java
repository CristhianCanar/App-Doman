package com.senasoft.appdoman.model;

public class WordPrueba {

    private int id;
    private int id_prueba;
    private int id_word;
    private int es_correcta;

    public WordPrueba() {
    }

    public WordPrueba(int id, int id_prueba, int id_word, int es_correcta) {
        this.id = id;
        this.id_prueba = id_prueba;
        this.id_word = id_word;
        this.es_correcta = es_correcta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_prueba() {
        return id_prueba;
    }

    public void setId_prueba(int id_prueba) {
        this.id_prueba = id_prueba;
    }

    public int getId_word() {
        return id_word;
    }

    public void setId_word(int id_word) {
        this.id_word = id_word;
    }

    public int getEs_correcta() {
        return es_correcta;
    }

    public void setEs_correcta(int es_correcta) {
        this.es_correcta = es_correcta;
    }
}
