package com.app.dictionary.model;

import java.util.ArrayList;
import java.util.List;

public final class Dictionary {
    private final List<Word> words = new ArrayList<>();

    public void add(Word word) {
        words.add(word);
    }

    public List<Word> getWords() {
        return words;
    }
}
