package com.senasoft.appdoman.model;

public class Fase {

    private int idFase;
    private String userName;
    private int score;
    private int level;

    public Fase() {
    }

    public Fase(int idFase, String userName, int score, int level) {
        this.idFase = idFase;
        this.userName = userName;
        this.score = score;
        this.level = level;
    }

    public int getId() {
        return idFase;
    }

    public void setId(int idFase) {
        this.idFase = idFase;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
