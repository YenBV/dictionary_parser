package com.app.dictionary.model;

import java.util.ArrayList;
import java.util.List;

public final class Dictionary {

    private final List<MultiLanguageWord> multiLanguageWords = new ArrayList<>();

    public void add(MultiLanguageWord word) {
        multiLanguageWords.add(word);
    }

    public List<MultiLanguageWord> getMultiLanguageWords() {
        return multiLanguageWords;
    }
}
