package com.app.dictionary.model;

import lombok.Data;

import java.util.List;

@Data
public class Dictionary {
    private String id;
    private List<UkrainianWord> ukrainianWords;
    private List<GermanWord> germanWords;

    public Dictionary() {
    }

    public Dictionary(
            List<UkrainianWord> ukrainianWords,
            List<GermanWord> germanWords) {
        this.ukrainianWords = ukrainianWords;
        this.germanWords = germanWords;
    }
}
