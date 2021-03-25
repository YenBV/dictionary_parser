package com.app.dictionary.model;

import javax.persistence.Column;

public class GermanWord extends Word {

    @Column(name = "language")
    private String language = "German";

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
