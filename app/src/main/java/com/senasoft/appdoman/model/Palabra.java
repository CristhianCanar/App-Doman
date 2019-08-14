package com.senasoft.appdoman.model;

public class Palabra {

    private String palId;
    private String palName;

    public Palabra() {
    }

    public Palabra(String palId, String palName) {
        this.palId = palId;
        this.palName = palName;
    }

    public String getPalId() {
        return palId;
    }

    public void setPalId(String palId) {
        this.palId = palId;
    }

    public String getPalName() {
        return palName;
    }

    public void setPalName(String palName) {
        this.palName = palName;
    }
}
