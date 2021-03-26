package com.app.dictionary.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "dictionary")
public class Dictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @OneToMany(mappedBy = "dictionary")
    private List<UkrainianWord> ukrainianWords;

    @OneToMany(mappedBy = "dictionary", cascade = CascadeType.ALL)
    private List<GermanWord> germanWords;

    public Dictionary() {
    }

    public Dictionary(
            List<UkrainianWord> ukrainianWords,
            List<GermanWord> germanWords) {
        this.ukrainianWords = ukrainianWords;
        this.germanWords = germanWords;
    }

    public long getId() {
        return id;
    }

    public List<UkrainianWord> getUkrainianWords() {
        return ukrainianWords;
    }

    public void setUkrainianWords(List<UkrainianWord> ukrainianWords) {
        this.ukrainianWords = ukrainianWords;
    }

    public List<GermanWord> getGermanWords() {
        return germanWords;
    }

    public void setGermanWords(List<GermanWord> germanWords) {
        this.germanWords = germanWords;
    }
}
