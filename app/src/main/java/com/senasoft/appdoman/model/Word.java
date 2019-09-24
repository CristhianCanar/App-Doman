package com.senasoft.appdoman.model;

public class Word {

    private String palId;
    private String palName;
    private String palCategory;
    private String uriAudio;

    public Word() {
    }

    public Word(String palId, String palName, String palCategory, String uriAudio) {
        this.palId = palId;
        this.palName = palName;
        this.palCategory = palCategory;
        this.uriAudio = uriAudio;
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


    public String getUriAudio() {
        return uriAudio;
    }

    public void setUriAudio(String uriAudio) {
        this.uriAudio = uriAudio;
    }
}
