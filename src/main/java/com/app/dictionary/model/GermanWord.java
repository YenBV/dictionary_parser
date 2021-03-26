package com.app.dictionary.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "german_word")
public class GermanWord extends Word {

    @Column(name = "language")
    private String language = "German";

    @ManyToOne(cascade = CascadeType.ALL)
    private Dictionary dictionary;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Dictionary getMultiLanguageWord() {
        return dictionary;
    }

    public void setMultiLanguageWord(Dictionary dictionary) {
        this.dictionary = dictionary;
    }
}
