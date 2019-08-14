package com.senasoft.appdoman.model;

public class Palabra {

    private String palId;
    private String palName;
    private String palCategory;

    public Palabra() {
    }

    public Palabra(String palId, String palName, String palCategory) {
        this.palId = palId;
        this.palName = palName;
        this.palCategory = palCategory;
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

    public String getPalCategory() {
        return palCategory;
    }

    public void setPalCategory(String palCategory) {
        this.palCategory = palCategory;
    }
}
