package com.app.dictionary.model;

import javax.persistence.Column;

public class UkrainianWord extends Word {

    @Column(name = "language")
    private String language = "Ukrainian";

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
