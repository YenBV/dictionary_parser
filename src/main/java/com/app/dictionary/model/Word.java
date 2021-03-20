package com.app.dictionary.model;

import java.util.ArrayList;
import java.util.List;

public class Word {

    private final String word;
    private final String type;
    private final String definition;
    private final boolean important;
    private final List<Word> meanings;
    //TODO:2021-03-20:yen: proposal.
    private String language;

    private Word(Builder builder) {
        this.word = builder.word;
        this.type = builder.type;
        this.definition = builder.definition;
        this.important = builder.important;
        this.meanings = builder.meanings == null ? new ArrayList<>() : builder.meanings;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String word;
        private String type;
        private String definition;
        private boolean important;
        private List<Word> meanings;

        public Builder setWord(String word) {
            this.word = word;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setDefinition(String definition) {
            this.definition = definition;
            return this;
        }

        public Builder setImportant(boolean important) {
            this.important = important;
            return this;
        }

        public void setMeanings(List<Word> meanings) {
            this.meanings = meanings;
        }

        public Word build() {
            return new Word(this);
        }
    }

    public String getWord() {
        return word;
    }

    public String getType() {
        return type;
    }

    public String getDefinition() {
        return definition;
    }

    public boolean isImportant() {
        return important;
    }

    public List<Word> getMeanings() {
        return meanings;
    }

    public List<Word> addMeaning(Word word) {
        meanings.add(word);
        return meanings;
    }

    public List<Word> addMeanings(List<Word> words) {
        meanings.addAll(words);
        return meanings;
    }

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                ", type='" + type + '\'' +
                ", definition='" + definition + '\'' +
                ", important=" + important +
                '}';
    }
}
