package com.app.dictionary.dto;

import com.app.dictionary.model.WordDefinition;

import java.util.List;

public class WordDTO {

    private String word;
    private String morphologyEndings;
    private String morphologyCategory;
    private List<WordDefinition> definitions;
    private boolean important;
    private String language;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMorphologyEndings() {
        return morphologyEndings;
    }

    public void setMorphologyEndings(String morphologyEndings) {
        this.morphologyEndings = morphologyEndings;
    }

    public String getMorphologyCategory() {
        return morphologyCategory;
    }

    public void setMorphologyCategory(String morphologyCategory) {
        this.morphologyCategory = morphologyCategory;
    }

    public List<WordDefinition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<WordDefinition> definitions) {
        this.definitions = definitions;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
