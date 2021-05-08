package com.app.dictionary.model;

import lombok.Data;

import java.util.List;

@Data
public class WordArticle {
    private String id;
    private Word word;
    private List<Word> otherLanguageWords;
    private boolean falseParallel;

    public WordArticle() {
    }

    public WordArticle(
            Word word,
            List<Word> otherLanguageWords) {
        this.otherLanguageWords = otherLanguageWords;
        this.word = word;
    }
}
