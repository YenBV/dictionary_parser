package com.app.dictionary.model;

import java.util.List;

public final class MultiLanguageWord {

//TODO:2021-03-22:yenbv: update to
// List<Word> ukrWords;
// List<Word> germanWords;

    private final List<Word> words;

    public MultiLanguageWord(List<Word> words) {
        this.words = words;
    }

    public List<Word> getWords() {
        return words;
    }
}
